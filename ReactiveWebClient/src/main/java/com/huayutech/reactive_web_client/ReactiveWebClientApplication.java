package com.huayutech.reactive_web_client;

import com.huayutech.reactive_web_client.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class ReactiveWebClientApplication {

    @Bean
    WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner launch(WebClient webClient) {
        return args -> {
            webClient.get().uri("/commodities/1")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(cr -> cr.bodyToFlux(Item.class))
                    .subscribe(v -> {
                        System.out.println("Received from MS: " + v.getName());
                    });
        };
    }


    public static void main(String[] args) {
        //SpringApplication.run(ReactiveWebClientApplication.class, args);
        new SpringApplicationBuilder(ReactiveWebClientApplication.class)
                .properties(Collections.singletonMap("server.port", "8081"))
                .run(args);
    }

}
