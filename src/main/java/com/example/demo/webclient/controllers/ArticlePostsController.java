package com.example.demo.webclient.controllers;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.services.ArticlePostService;
import com.example.demo.webclient.exceptions.ArticleNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@Slf4j
public class ArticlePostsController {

    @Autowired
    ArticlePostService articlePostService;

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

        return articlePostService.getArticlePost(id)
                .log()
                .doOnNext(s -> log.info("response: " + s));
    }
/*
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFound(ArticleNotFoundException ex) {
        log.info("global exception");
        ArticlePost article = ArticlePost.builder().body("hi from controller exception").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(article);
    }
 */
}
