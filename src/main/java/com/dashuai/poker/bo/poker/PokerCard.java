package com.dashuai.poker.bo.poker;

import org.springframework.data.util.Pair;

import java.util.*;

public class PokerCard {
    // 定义花色和点数的枚举


    // 扑克牌的属性
    private final PokerSuit suit; // 花色
    private final PokerRank rank; // 点数

    // 构造方法
    public PokerCard(PokerSuit suit, PokerRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getter 方法
    public PokerSuit getSuit() {
        return suit;
    }

    public PokerRank getRank() {
        return rank;
    }

    // 展示牌面的方法
    public void displayCard() {
        System.out.println(rank + " of " + suit);
    }

    public static List<PokerCard> init() {
        List<PokerCard> deck = new ArrayList<>();
        for (PokerSuit suit : PokerSuit.values()) {
            for (PokerRank rank : PokerRank.values()) {
                deck.add(new PokerCard(suit, rank));
            }
        }
        return deck;
    }

    /**
     * 比较两个玩家手上的牌面大小
     * @param hand1 玩家1的手牌
     * @param hand2 玩家2的手牌
     * @param communityCards 桌面上的公共牌
     * @return true 表示玩家 1 获胜，false 表示玩家 2 获胜
     */
    private static boolean compareHands(List<PokerCard> hand1, List<PokerCard> hand2, List<PokerCard> communityCards) {
        // 获取每个玩家的最大牌型
        HandType handType1 = getHandType(hand1, communityCards);
        HandType handType2 = getHandType(hand2, communityCards);

        // 比较牌型大小
        return handType1.compareTo(handType2) > 0;
    }

    /**
     * 获取玩家手上的最大牌型
     * @param hand 玩家手上的牌
     * @param communityCards 桌面上的公共牌
     * @return 玩家手上的最大牌型
     */
    public static HandType getHandType(List<PokerCard> hand, List<PokerCard> communityCards) {
        List<PokerCard> allCards = new ArrayList<>(hand);
        allCards.addAll(communityCards);

        // 统计点数出现次数
        Map<PokerRank, Integer> rankCount = new HashMap<>();
        for (PokerCard card : allCards) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        // 判断是否有同花
        boolean hasFlush = false;
        for (PokerSuit suit : PokerSuit.values()) {
            long count = allCards.stream().filter(card -> card.getSuit() == suit).count();
            if (count >= 5) {
                hasFlush = true;
                break;
            }
        }

        // 判断是否有顺子
        List<PokerRank> ranks = new ArrayList<>(rankCount.keySet());
        Collections.sort(ranks);
        boolean hasStraight = false;
        for (int i = 0; i <= ranks.size() - 5; i++) {
            if (ranks.get(i).ordinal() + 4 == ranks.get(i + 4).ordinal()) {
                hasStraight = true;
                break;
            }
        }

        // 判断牌型
        if (hasFlush && hasStraight) {
            // 同花顺
            if (ranks.contains(PokerRank.ACE) && ranks.contains(PokerRank.KING)) {
                return HandType.ROYAL_FLUSH;
            } else {
                return HandType.STRAIGHT_FLUSH;
            }
        } else if (rankCount.containsValue(4)) {
            // 四条
            return HandType.FOUR_OF_A_KIND;
        } else if (rankCount.containsValue(3) && rankCount.containsValue(2)) {
            // 葫芦
            return HandType.FULL_HOUSE;
        } else if (hasFlush) {
            // 同花
            return HandType.FLUSH;
        } else if (hasStraight) {
            // 顺子
            return HandType.STRAIGHT;
        } else if (rankCount.containsValue(3)) {
            // 三条
            return HandType.THREE_OF_A_KIND;
        } else if (rankCount.values().stream().filter(count -> count == 2).count() == 2) {
            // 两对
            return HandType.TWO_PAIR;
        } else if (rankCount.containsValue(2)) {
            // 一对
            return HandType.ONE_PAIR;
        } else {
            // 高牌
            return HandType.HIGH_CARD;
        }
    }

    // 比较 n 个玩家手牌的牌型，并返回牌型最大的玩家们的标识列表
    public static Pair<Map<String, HandType>, List<String>> compareNHands(Map<String, List<PokerCard>> playersHands, List<PokerCard> communityCards) {
        Map<String, HandType> playersHandTypes = new HashMap<>();
        List<String> winners = new ArrayList<>();
        HandType maxHandType = null;

        for (Map.Entry<String, List<PokerCard>> entry : playersHands.entrySet()) {
            String player = entry.getKey();
            List<PokerCard> hand = entry.getValue();
            HandType handType = getHandType(hand, communityCards);
            playersHandTypes.put(player, handType);
            if (maxHandType == null || handType.compareTo(maxHandType) > 0) {
                maxHandType = handType;
                winners.clear();
                winners.add(player);
            } else if (handType.compareTo(maxHandType) == 0) {
                winners.add(player);
            }
        }

        return Pair.of(playersHandTypes, winners);
    }

    /**
     * 随机获取 n 张牌
     */
    public static List<PokerCard> getCards(List<PokerCard> deck, int num) {
        if (deck.size() < num) {
            throw new IllegalArgumentException("牌不够了");
        }

        List<PokerCard> hand = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int index = (int) (Math.random() * deck.size());
            hand.add(deck.remove(index));
        }
        return hand;
    }
}

