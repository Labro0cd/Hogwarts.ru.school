package ru.hogwarts.school.school.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Profile("production")
public class InfoServiceProduction implements InfoService {

    @Value("${server.port}")
    private String port;

    @Override
    public String getPort() {
        return port;
    }

    public int getSum_1_000_000() {
        int sum = IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
        return sum;
    }
}
