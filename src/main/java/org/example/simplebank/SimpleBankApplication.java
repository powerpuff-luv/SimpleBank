package org.example.simplebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * TODO описать эндпоинты, параметры, модели данных в OpenAPI и Javadoc
 * <a href="http://localhost:8080/simplebank/swagger-ui/index.html">swagger-ui</a>
 * TODO добавить логирование
 */
@SpringBootApplication
public class SimpleBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleBankApplication.class, args);
    }
}

