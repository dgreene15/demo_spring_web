package com.example.demo.webclient.controllers;

import com.example.demo.webclient.services.MyService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@Slf4j
@RestController
public class MetricsCounter {

    Counter requestCounter;

    @Autowired
    MyService myService;

    public MetricsCounter(MeterRegistry registry) {
        this.requestCounter = Counter.builder("metric.counter.requestcount")
                .tag("version", "v1")
                .description("My Counter")
                .register(registry);

        Gauge.builder("usercontroller.usercount",fetchUserCount()).
                tag("version","v1").
                description("usercontroller descrip").
                register(registry);
    }

    @GetMapping("/counter")
    public String counter() {
        log.info("greeting");
        return myService.greet("default");
    }

    // supplies user count
    public Supplier<Number> fetchUserCount() {
        return ()->4;
    }
}