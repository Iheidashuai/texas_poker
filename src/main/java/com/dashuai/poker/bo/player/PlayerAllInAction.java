package com.dashuai.poker.bo.player;

public class PlayerAllInAction implements PlayerAction {
    private PlayerEngine playerEngine;

    public PlayerAllInAction(PlayerEngine playerEngine) {
        this.playerEngine = playerEngine;
    }

    @Override
    public void action(int n) {
        playerEngine.allIn();
    }
}
