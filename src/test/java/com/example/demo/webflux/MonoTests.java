package com.example.demo.webflux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MonoTests {

    @Test
    public void flatMapManyTest() {
        List<String> characterList = new ArrayList<>();
        Mono.just("data")
                .flatMapMany(item -> Flux.just(item.split("")))
                .doOnNext(characterList::add)
                .subscribe();
        assertThat(characterList).hasSize(4);
    }

    @Test
    public void defaultIfEmptyTest() {
        Mono<String> mono = Mono.empty();
        Mono<String> mono2 = mono.defaultIfEmpty("default");

        StepVerifier.create(mono2)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    public void switchIfEmptyTest() {
        Mono<String> mono = Mono.empty();
        Mono<String> fallbackMono = Mono.just("fallback");
        Mono<String> mono2 = mono.switchIfEmpty(fallbackMono);

        StepVerifier.create(mono2)
                .expectNext("fallback")
                .verifyComplete();
    }

    @Test
    public void onErrorReturnTest() {
        Mono<String> mono = Mono.error(new RuntimeException("error"));
        Mono<String> mono2 = mono.onErrorReturn("default");

        StepVerifier.create(mono2)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    public void onErrorResume() {
        Mono<String> mono = Mono.error(new RuntimeException("error"));
        Mono<String> fallbackMono = Mono.just("fallback");
        Mono<String> mono2 = mono.onErrorResume(e -> fallbackMono);

        StepVerifier.create(mono2)
                .expectNext("fallback")
                .verifyComplete();
    }

    @Test
    public void onErrorContinue() {
        List<String> errorList = new ArrayList<>();
        Flux<String> flux = Flux.just("a", "b", "c")
                .map(item -> {
                    if (item.equals("b")) {
                        throw new RuntimeException("error");
                    }
                    return item;
                })
                .onErrorContinue((e, item) -> errorList.add(item.toString()));

        StepVerifier.create(flux)
                .expectNext("a")
                .expectNext("c")
                .expectNextCount(0)
                .verifyComplete();

        assertThat(errorList).containsExactly("b");
    }

    @Test
    public void onErrorMap() {
        Mono<String> mono = Mono.just("data")
                .map(item -> {
                    if (item.equals("data")) {
                        throw new RuntimeException("error");
                    }
                    return item;
                })
                .onErrorMap(e -> new IllegalArgumentException("mapped error", e));

        StepVerifier.create(mono)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void doOnError() {
        List<String> errorList = new ArrayList<>();
        Mono<String> mono = Mono.just("data")
                .map(item -> {
                    if (item.equals("data")) {
                        throw new RuntimeException("error");
                    }
                    return item;
                })
                .doOnError(e -> errorList.add(e.getMessage()));

        StepVerifier.create(mono)
                .expectError(RuntimeException.class)
                .verify();

        assertThat(errorList).containsExactly("error");
    }

    @Test
    public void mergeTest() {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");

        Flux<String> mergedFlux = Flux.merge(mono1, mono2);

        StepVerifier.create(mergedFlux)
                .expectNext("Hello")
                .expectNext("World")
                .verifyComplete();
    }

    @Test
    public void mergeWithTest() {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");

        Flux<String> mergedMono = mono1.mergeWith(mono2);

        StepVerifier.create(mergedMono)
                .expectNext("Hello")
                .expectNext("World")
                .verifyComplete();
    }

    @Test
    public void mergeSequenctial() {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");

        Flux<String> mergedMono = Flux.mergeSequential(mono1, mono2);

        StepVerifier.create(mergedMono)
                .expectNext("Hello")
                .expectNext("World")
                .verifyComplete();
    }
}
