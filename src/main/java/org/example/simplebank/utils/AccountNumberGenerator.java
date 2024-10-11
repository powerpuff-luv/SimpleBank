package org.example.simplebank.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AccountNumberGenerator {
    public String generateRandomAccountNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            stringBuffer.append(random.nextInt(10));
        }

        return stringBuffer.toString();
    }
}
