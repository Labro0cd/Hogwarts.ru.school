package ru.hogwarts.school.school.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.school.Service.InfoService;
import ru.hogwarts.school.school.Service.InfoServiceProduction;


@RestController
public class InfoController {

    private final InfoService infoServiceProduction;

    public InfoController(InfoService infoServiceProduction) {
        this.infoServiceProduction = infoServiceProduction;
    }

    @GetMapping("/port")
    public String getPort() {
        return infoServiceProduction.getPort();
    }
}
