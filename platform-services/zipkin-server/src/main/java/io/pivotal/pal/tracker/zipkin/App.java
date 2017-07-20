package io.pivotal.pal.tracker.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
