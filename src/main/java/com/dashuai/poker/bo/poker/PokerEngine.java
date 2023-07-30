package com.dashuai.poker.bo.poker;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

public interface PokerEngine {
    /**
     * 洗牌
     */
    void shuffle();

    /**
     * 获取 n 张牌
     * @param n
     * @return n 张牌
     */
    List<PokerCard> getNPorkerCards(int n);

    /**
     * 比较 n 个玩家手上的牌面大小
     * @param playersHands 玩家手牌 Map<玩家名称,手牌>
     * @param communityCards 桌面上的公共牌
     * @return 获胜玩家 id
     */
    Pair<Map<String, HandType>, List<String>> compareNHands(Map<String, List<PokerCard>> playersHands, List<PokerCard> communityCards);
}
