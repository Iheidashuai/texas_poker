package com.dashuai.poker.bo.round;


public class SystemRoundFlopSystemRoundEngine extends SystemRoundBase {
    private static final int FLOP_CARD_NUM = 3;

    public SystemRoundFlopSystemRoundEngine(RoundSnapshot roundSnapshot) {
        super(roundSnapshot);
    }

    public void process() {
        this.roundSnapshot.flop(FLOP_CARD_NUM);
    }
}
