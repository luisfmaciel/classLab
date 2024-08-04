package br.edu.infnet.classlab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ClassLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassLabApplication.class, args);
    }

}
