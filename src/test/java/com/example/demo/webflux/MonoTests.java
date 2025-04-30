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
}
