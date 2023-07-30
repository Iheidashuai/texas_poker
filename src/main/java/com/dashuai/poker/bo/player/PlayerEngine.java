package com.dashuai.poker.bo.player;

import com.dashuai.poker.bo.poker.PokerCard;

import java.util.List;

public interface PlayerEngine {
    /**
     * 下注
     * @param bet
     */
    void bet(int bet);

    /**
     * 玩家弃牌
     */
    void fold();

    /**
     * 梭哈
     */
    void allIn();

    void getPokerCards(List<PokerCard> pokerCards);
}
