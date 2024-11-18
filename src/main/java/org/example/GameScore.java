package org.example;

public abstract class GameScore {
    abstract GameScore scorePointForPlayerA();

    abstract GameScore scorePointForPlayerB();

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
