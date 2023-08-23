package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {

    @Value("${server.port}")
    private String port;

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public String getPort() {
        logger.info("Was invoked method for get port");

        return port;
    }

    public int getValue() {
        logger.info("Was invoked method for get value");

//        long startTime = System.nanoTime();
        int sum = Stream
                .iterate(1_000_000, a -> a - 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println(timeElapsed);
        return sum;
    }
}
