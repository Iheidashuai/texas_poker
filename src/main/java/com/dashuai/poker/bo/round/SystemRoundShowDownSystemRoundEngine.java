package com.dashuai.poker.bo.round;

public class SystemRoundShowDownSystemRoundEngine extends SystemRoundBase {

    public SystemRoundShowDownSystemRoundEngine(RoundSnapshot roundSnapshot) {
        super(roundSnapshot);
    }
    @Override
    public void process() {
       this.roundSnapshot.showDown();
    }
}
