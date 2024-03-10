package com.demo.myreactive.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

  @Test
  public void monoTest() {

      Mono<String> stringMono = Mono.just("Spring");
  }

    @Test
    public void monoTestElements_withoutError(){
        Mono<String> stringMono = Mono.just("Spring").log();

        StepVerifier.create(stringMono).expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTest_withError(){
    Mono<Object> stringMono = Mono.error(new RuntimeException("My exception")).log();

        StepVerifier.create(stringMono)
                .verifyError(RuntimeException.class);
    }
}
