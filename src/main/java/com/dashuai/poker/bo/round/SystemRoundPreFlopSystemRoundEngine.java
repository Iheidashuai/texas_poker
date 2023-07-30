package com.dashuai.poker.bo.round;

import com.dashuai.poker.bo.player.Player;

import java.util.Collections;

public class SystemRoundPreFlopSystemRoundEngine extends SystemRoundBase {
    public SystemRoundPreFlopSystemRoundEngine(RoundSnapshot roundSnapshot) {
        super(roundSnapshot);
    }

    @Override
    public void process() {
        roundSnapshot.init(Player.initPlayers());
        Collections.shuffle(roundSnapshot.getUnDealtCards());
        for (Player player : roundSnapshot.getAlivePlayers()) {
            player.getCard(roundSnapshot.getPokerEngine().getNPorkerCards(2));
        }
    }
}
