package org.example.rest;

import lombok.Setter;
import org.example.core.Score;
import org.example.core.ScoreFactory;
import org.example.core.usecases.CountScoreForSequence;
import org.example.core.usecases.InputParser;
import org.example.core.usecases.PlayerToScore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

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

    @PostMapping("/A")
    public ScoreDto scorePointForPlayerA() {
        currentScore = currentScore.scorePointForPlayerA();
        return ScoreDto.fromDomain(currentScore);
    }

    @PostMapping("/B")
    public ScoreDto scorePointForPlayerB() {
        currentScore = currentScore.scorePointForPlayerB();
        return ScoreDto.fromDomain(currentScore);
    }

    @PostMapping("/sequence/{sequence}")
    public ScoreDto scoreSequence(@PathVariable("sequence") String sequenceStr) {
        List<PlayerToScore> sequence = InputParser.parse(sequenceStr);
        CountScoreForSequence countScoreForSequence = new CountScoreForSequence(currentScore);
        currentScore = countScoreForSequence.executeSequence(sequence, score -> {
        });
        return ScoreDto.fromDomain(currentScore);
    }
}
