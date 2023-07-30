package com.dashuai.poker.bo.round;

public class SystemRoundFactory {
    RoundSnapshot roundSnapshot;

    public SystemRoundFactory(RoundSnapshot roundSnapshot) {
        this.roundSnapshot = roundSnapshot;
    }

    public static SystemRoundFactory getInstance(RoundSnapshot roundSnapshot) {
        return new SystemRoundFactory(roundSnapshot);
    }

    public void start() {
        new SystemRoundPreFlopSystemRoundEngine(roundSnapshot).process();
    }

    public void nextRound() {
        roundSnapshot.setRound(roundSnapshot.getRound().next());

        SystemRoundEngine systemRoundEngine = null;
        switch (roundSnapshot.getRound()) {
            case PRE_FLOP:
                systemRoundEngine = new SystemRoundPreFlopSystemRoundEngine(roundSnapshot);
                break;
            case FLOP:
                systemRoundEngine = new SystemRoundFlopSystemRoundEngine(roundSnapshot);
                break;
            case TURN:
                systemRoundEngine = new SystemRoundTurnSystemRoundEngine(roundSnapshot);
                break;
            case RIVER:
                systemRoundEngine = new SystemRoundRiverSystemRoundEngine(roundSnapshot);
                break;
            case SHOW_DOWN:
                systemRoundEngine = new SystemRoundShowDownSystemRoundEngine(roundSnapshot);
                break;
            default:
                throw new RuntimeException("roundEnum is not exist");
        }
        systemRoundEngine.process();
    }

}
