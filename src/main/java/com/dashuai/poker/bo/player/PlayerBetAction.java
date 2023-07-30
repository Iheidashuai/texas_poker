package com.dashuai.poker.bo.player;

public class PlayerBetAction implements PlayerAction {
    private PlayerEngine playerEngine;

    public PlayerBetAction(PlayerEngine playerEngine) {
        this.playerEngine = playerEngine;
    }

    @Override
    public void action(int n) {
        playerEngine.bet(n);
    }
}
