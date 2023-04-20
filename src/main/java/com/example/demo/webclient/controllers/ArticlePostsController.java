package com.example.demo.webclient.controllers;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.services.ArticlePostService;
import com.example.demo.webclient.exceptions.ArticleNotFoundException;
import com.example.demo.webclient.services.MongoService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.function.Supplier;

@RestController
@RequestMapping("/employees")
@Slf4j
public class ArticlePostsController {

    @Autowired
    MongoService service;

    @Autowired
    ArticlePostService articlePostService;

    Counter requestCounter;

    public ArticlePostsController(MeterRegistry registry) {

        requestCounter = Counter.builder("article.posts.requestcount")
                .tag("version", "v1")
                .description("Article Posts Counter")
                .register(registry);

        Gauge.builder("usercontroller.usercount",fetchUserCount()).
                tag("version","v1").
                description("usercontroller descrip").
                register(registry);
    }

    @Operation(summary="Get a Post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the article",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticlePost.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public Mono<ArticlePost> getArticlePostById(@PathVariable("id") Integer id) {
        log.info("ArticlePostsController:  controller (id={})", id);
        requestCounter.increment();

        return articlePostService.getArticlePost(id)
                .log()
                .doOnNext(s -> log.info("response: " + s));

    }

    @GetMapping("/tryMongo")
    public void runMongoTest() {
        log.info("Deleting all records...");
        service.deleteAll();

        log.info("adding item...");
        service.addItem();

        log.info("printing Count...");
        service.printCount();

        log.info("printing all items...");
        service.printAllItems();

        log.info("adding test apple");
        service.addTestItem();


        log.info("printing testapple item");
        service.printByName("testapple");

        log.info("updating testapple...");
        service.updateItem();


        log.info("printing all items...");
        service.printAllItems();

        log.info("print all item in 'fruit' category");
        service.printAllItemsByCategory();
    }

    @Operation(summary="Get a Post by ID and Next One")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the articles",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticlePost.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content) })
    @GetMapping("/{id}/fetchAhead")
    public Mono<ArticlePost> getArticlePostByIdPlusNext(@PathVariable("id") Integer id) {
        log.info("ArticlePostsController: getArticlePostByIdPlusNext controller (id={})", id);

        return articlePostService.getTwoArticlePosts(id).log();
    }

    // supplies user count
    public Supplier<Number> fetchUserCount() {
        return ()->4;
    }


}
