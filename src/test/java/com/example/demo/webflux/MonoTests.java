package com.example.demo.webflux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
