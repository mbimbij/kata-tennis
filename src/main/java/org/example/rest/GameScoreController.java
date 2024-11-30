package org.example.rest;

import lombok.Setter;
import org.example.shared.core.Score;
import org.example.shared.core.ScoreFactory;
import org.example.shared.core.usecases.CountScoreForSequence;
import org.example.shared.core.usecases.InputParser;
import org.example.shared.core.usecases.PlayerToScore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@RestController
@RequestMapping("/api/v1/game/score")
public class GameScoreController {
    private final ScoreFactory factory;
    private Score currentScore;

    public GameScoreController(ScoreFactory scoreFactory) {
        this.factory = scoreFactory;
        currentScore = this.factory.loveAll();
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
        currentScore = countScoreForSequence.executeSequence(sequence);
        return ScoreDto.fromDomain(currentScore);
    }

    @DeleteMapping
    public ScoreDto resetScore() {
        currentScore = factory.loveAll();
        return ScoreDto.fromDomain(currentScore);
    }
}
