package com.demo.myreactive.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void fluxTest(){
    Flux<String> stringFlux =
        Flux.just("SpringBoot", "Spring", "React Spring")
//            .concatWith(Flux.error(new RuntimeException("This is a custom error")))
                .log();

    stringFlux.subscribe(
        (value) -> {
          System.out.println(value);
          return;
        },
        (error) -> {
          System.out.printf("Error : %s", error.getMessage());
        },
        () -> System.out.println("Completed"));
    }


    @Test
    public void fluxTestElements_withoutError(){
        Flux<String> stringFlux =
                Flux.just("SpringBoot", "Spring", "React Spring").log();

        StepVerifier.create(stringFlux).expectNext("SpringBoot").expectNext("Spring").expectNext("React Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_withError(){
        Flux<String> stringFlux =
                Flux.just("SpringBoot", "Spring", "React Spring")
                        .concatWith(Flux.error(new RuntimeException("This is a custom error")))
                        .log();

        StepVerifier.create(stringFlux)
                .expectNext("SpringBoot")
                .expectNext("Spring")
                .expectNext("React Spring")
                .verifyError(RuntimeException.class);
    }

    @Test
    public void fluxTestElementsSimple_withError(){
        Flux<String> stringFlux =
                Flux.just("SpringBoot", "Spring", "React Spring")
                        .concatWith(Flux.error(new RuntimeException("This is a custom error")))
                        .log();

        StepVerifier.create(stringFlux)
                .expectNext("SpringBoot", "Spring", "React Spring")
                .verifyError(RuntimeException.class);
    }

    @Test
    public void fluxTestElementsCount_withoutError(){
        Flux<String> stringFlux =
                Flux.just("SpringBoot", "Spring", "React Spring")
                        .log();

        StepVerifier.create(stringFlux).expectNextCount(3).verifyComplete();
    }
}
