package org.example.restapi;

import lombok.Setter;
import org.example.core.Score;
import org.example.core.ScoreFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@RestController
@RequestMapping("/api/v1/game/score")
public class GameScoreController {
    private Score currentScore;

    public GameScoreController(ScoreFactory scoreFactory) {
        currentScore = scoreFactory.loveAll();
    }

    @GetMapping
    public ScoreDto getScore() {
        return ScoreDto.fromDomain(currentScore);
    }
}