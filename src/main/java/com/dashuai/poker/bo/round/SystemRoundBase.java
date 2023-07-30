package com.dashuai.poker.bo.round;

public abstract class SystemRoundBase implements SystemRoundEngine {
    RoundSnapshot roundSnapshot;

    public SystemRoundBase(RoundSnapshot roundSnapshot) {
        this.roundSnapshot = roundSnapshot;
    }
}
