package com.dashuai.poker.bo.player;

public class PlayerFoldAction implements PlayerAction {
    private PlayerEngine playerEngine;

    public PlayerFoldAction(PlayerEngine playerEngine) {
        this.playerEngine = playerEngine;
    }

    @Override
    public void action(int n) {
        playerEngine.fold();
    }
}
