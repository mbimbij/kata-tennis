package org.example.countscorerestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game/score")
public class GameScoreController {
    @GetMapping
    public String getScore() {
        return "Hello, Spring Boot!";
    }
}
