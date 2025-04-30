package com.example.demo.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxTests {

    /**
     * concatMap
     * - preserves the order of the elements
     * - used for async processing
     */
    @Test
    public void concatMapTest() {
        Flux<String> words = Flux.just("hello", "world", "reactor");
        Mono<String> result = words.concatMap(word ->
           {
            return Flux.just(word.split("")).delayElements(Duration.ofMillis(100))
                    .map(String::toUpperCase);
           }).collect(Collectors.joining(", "));

        // StepVerifier is used with verify so wait on async calls to complete to verify
        StepVerifier.create(result)
               // .expectNext("H, E, L, L, O, W, O, R, L, D, R, E, A, C, T, O, R")
                .assertNext(r -> {
                    //System.out.println("Result3: " + r);
                    assertThat(r).isEqualTo("H, E, L, L, O, W, O, R, L, D, R, E, A, C, T, O, R");
                })
                .expectComplete()
                .verify(Duration.ofSeconds(5));;
    }

    /**
     * transform
     * - works with Mono and Flux
     * - Allows reusing function to apply
     * - Applies to the entire stream
     */
    @Test
    public void transformTest() {
        Flux<String> names1 = Flux.just("alice", "bob");
        Flux<String> names2 = Flux.just("charlie", "david", "eve");

        Function<Flux<String>, Flux<String>> greetTransformer = flux ->
                flux.map(name -> "Hello, " + name + "!");

        Flux<String> greetings1 = names1.transform(greetTransformer);
        Flux<String> greetings2 = names2.transform(greetTransformer);

        StepVerifier.create(greetings1)
                .expectNext("Hello, alice!")
                .expectNext("Hello, bob!")
                .verifyComplete();

        StepVerifier.create(greetings2)
                .expectNext("Hello, charlie!")
                .expectNext("Hello, david!")
                .expectNext("Hello, eve!")
                .verifyComplete();
    }

    /**
     * concat
     * - Concatenates multiple Flux, Monos sequences into a single sequence
     * - Preserves the order of the elements
     */
    @Test
    public void concatTest() {
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> concatenatedFlux = Flux.concat(flux1, flux2);

        StepVerifier.create(concatenatedFlux)
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }
}
