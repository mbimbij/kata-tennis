package org.example.scoring.core;

import lombok.Getter;

@Getter
public class TennisSet {
    public static final String CANNOT_SCORE_ON_OVER_SET_ERROR_MESSAGE = "Set already has a winner. Cannot score a point";
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

    public TennisSet(int playerAScore, int playerBScore) {
        this.setScore = new SetScore(playerAScore, playerBScore);
        this.currentGameScore = GameScoreFactory.getInstance().loveAll();
    }

    public void scorePointForPlayerA() {
        if(isOver()){
            throw new IllegalStateException(CANNOT_SCORE_ON_OVER_SET_ERROR_MESSAGE);
        }
        currentGameScore = currentGameScore.scorePointForPlayerA();
        if(currentGameScore.playerAWon()){
            setScore = setScore.scorePointForPlayerA();
            currentGameScore = GameScoreFactory.getInstance().loveAll();
        }
    }

    public void scorePointForPlayerB() {
        if(isOver()){
            throw new IllegalStateException(CANNOT_SCORE_ON_OVER_SET_ERROR_MESSAGE);
        }
        currentGameScore = currentGameScore.scorePointForPlayerB();
        if(currentGameScore.playerBWon()){
            setScore = setScore.scorePointForPlayerB();
            currentGameScore = GameScoreFactory.getInstance().loveAll();
        }
    }

    public boolean isOver() {
        return playerAWon() || playerBWon();
    }

    private boolean playerBWon() {
        return playerWon(setScore.playerBScore(), setScore.playerAScore());
    }

    private boolean playerAWon() {
        return playerWon(setScore.playerAScore(), setScore.playerBScore());
    }

    private boolean playerWon(int score, int scoreOther) {
        return score == 6 && scoreOther <= 4 || score == 7;
    }

    public Player getWinner() {
        if(playerAWon()){
            return Player.A;
        } else if (playerBWon()) {
            return Player.B;
        }
        return null;
    }
}
