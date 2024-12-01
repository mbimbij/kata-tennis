package org.example.shared.core;

import lombok.Getter;

@Getter
public class TennisSet {
    private SetScore setScore;
    private GameScore currentGameScore;

    public TennisSet() {
        this.setScore = new SetScore(0, 0);
        this.currentGameScore = GameScoreFactory.getInstance().loveAll();
    }

    public TennisSet(SetScore setScore, GameScore gameScore) {
        this.setScore = setScore;
        this.currentGameScore = gameScore;
    }

    public void scorePointForPlayerA() {
        currentGameScore = currentGameScore.scorePointForPlayerA();
        if(currentGameScore.playerAWon()){
            setScore = setScore.scorePointForPlayerA();
            currentGameScore = GameScoreFactory.getInstance().loveAll();
        }
    }

    public boolean isOver() {
        return setScore.playerAScore() == 6 && setScore.playerBScore() <= 4 || setScore.playerAScore() == 7;
    }

    public Player getWinner() {
        if(isOver()){
            return Player.A;
        }
        return null;
    }
}
