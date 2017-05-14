package com.its.hello;

import hello.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Dhaval Shah
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    private HelloService service;

    public HelloCommandLineRunner(HelloService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        this.service.sayHello("World");
    }
}
