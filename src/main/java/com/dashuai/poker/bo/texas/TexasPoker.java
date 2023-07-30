package com.dashuai.poker.bo.texas;

import com.dashuai.poker.bo.player.Player;
import com.dashuai.poker.bo.player.PlayerActionEnum;
import com.dashuai.poker.bo.poker.HandType;

import java.util.List;

public interface TexasPoker {
    void start();

    void nextRound();

    void nextPlayer();

    void playerAction(PlayerActionEnum actionEnum, int n);

    boolean isEnd();

    List<Player> getWinners();
}
