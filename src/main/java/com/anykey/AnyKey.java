package com.anykey;

import com.anykey.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AnyKey {
    public static void main(String[] args) {
        SpringApplication.run(AnyKey.class, args);
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SwaggerConfig.class);

    }
}
