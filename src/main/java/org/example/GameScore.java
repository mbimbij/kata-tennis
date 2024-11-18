package org.example;

public abstract class GameScore {
    abstract GameScore scorePointForPlayerA();

    abstract GameScore scorePointForPlayerB();

    public String format(GameScore gameScore) {
        if (gameScore.playerAWon()) {
            return "Player A wins the game";
        }
        if (gameScore.playerBWon()) {
            return "Player B wins the game";
        }
        if (gameScore.isDeuce()) {
            return "Deuce";
        }
        if (gameScore.isAdvantagePlayerA()) {
            return "Advantage Player A";
        }
        if (gameScore.isAdvantagePlayerB()) {
            return "Advantage Player B";
        }
        return gameScore.getDefaultFormattedScore(gameScore);
    }

    protected abstract String getDefaultFormattedScore(GameScore gameScore);

    protected abstract boolean playerAWon();

    protected abstract boolean playerBWon();

    protected abstract boolean isDeuce();

    protected abstract boolean isAdvantagePlayerA();

    protected abstract boolean isAdvantagePlayerB();

    public boolean scoreEquals(GameScore that) {
        return this.isDeuce() && that.isDeuce()
               || this.isAdvantagePlayerA() && that.isAdvantagePlayerA()
               || this.isAdvantagePlayerB() && that.isAdvantagePlayerB()
               || this.playerAWon() && that.playerAWon()
               || this.playerBWon() && that.playerBWon()
               || scoreDetailsEqual(that);
    }

    protected abstract boolean scoreDetailsEqual(GameScore o);
}
