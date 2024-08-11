package br.edu.infnet.classlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClassLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassLabApplication.class, args);
    }

}
