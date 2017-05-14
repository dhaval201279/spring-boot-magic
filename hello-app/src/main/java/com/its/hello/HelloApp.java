package com.its.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dhaval Shah
 */

@SpringBootApplication
public class HelloApp {

	public static void main(String[] args) {
		/*new SpringApplicationBuilder(HelloApp.class)
				.listeners(new HelloApplicationListener())
				.run(args);*/
		SpringApplication.run(HelloApp.class,args);
	}

	/*@Bean
	public HelloService helloService() {
		System.out.println("Entering HelloApp : helloService");
		System.out.println("Instantiating ConsoleHelloService and leaving HelloApp : helloService");
		return new ConsoleHelloService("Howdy","!");
	}*/

}
