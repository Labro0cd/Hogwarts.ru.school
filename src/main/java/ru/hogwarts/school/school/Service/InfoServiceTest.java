package ru.hogwarts.school.school.Service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class InfoServiceTest {

    public String getPort() {
        return "Port: 7777";
    }
}
