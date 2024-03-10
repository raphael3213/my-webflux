package com.demo.myreactive.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {

    @GetMapping("/flux")
    public Flux<Integer> getflux(){
        return Flux.range(0, 1_000).delayElements(Duration.ofSeconds(1)).log();
    }


    @GetMapping(value = "/flux-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFluxAsStream(){
        return Flux.range(0, 10).delayElements(Duration.ofSeconds(1)).log();
    }

}
