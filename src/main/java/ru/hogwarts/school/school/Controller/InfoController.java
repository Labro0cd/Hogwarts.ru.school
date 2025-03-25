package ru.hogwarts.school.school.Controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.school.Service.InfoServiceTest;


@RestController
public class InfoController {

    private final InfoServiceTest infoServiceTest;

    public InfoController(InfoServiceTest infoServiceTest) {
        this.infoServiceTest = infoServiceTest;
    }

    @GetMapping("/port")
    public String getPort() {
        return infoServiceTest.getPort();
    }
}
