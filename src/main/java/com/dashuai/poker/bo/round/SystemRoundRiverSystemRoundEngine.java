package com.dashuai.poker.bo.round;

public class SystemRoundRiverSystemRoundEngine extends SystemRoundBase {
    private static final int FLOP_CARD_NUM = 1;
    public SystemRoundRiverSystemRoundEngine(RoundSnapshot roundSnapshot) {
        super(roundSnapshot);
    }
    @Override
    public void process() {
         this.roundSnapshot.flop(FLOP_CARD_NUM);
    }
}
