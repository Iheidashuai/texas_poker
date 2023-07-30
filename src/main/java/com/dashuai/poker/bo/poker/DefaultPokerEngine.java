package com.dashuai.poker.bo.poker;

import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dashuai.poker.bo.poker.PokerCard.getHandType;

public class DefaultPokerEngine implements PokerEngine {

    private List<PokerCard> deck;

    public DefaultPokerEngine(List<PokerCard> deck) {
        this.deck = deck;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public List<PokerCard> getNPorkerCards(int num) {
        return PokerCard.getCards(deck, num);
    }

    @Override
    public Pair<Map<String, HandType>, List<String>> compareNHands(Map<String, List<PokerCard>> playersHands, List<PokerCard> communityCards) {
        return PokerCard.compareNHands(playersHands, communityCards);
    }
}
