package com.dashuai.poker.bo.round;

public enum RoundEnum {
    UN_START(-1, "unStart"),
    PRE_FLOP(0, "preFlop"),
    FLOP(1, "process"),
    TURN(2, "turn"),
    RIVER(3, "river"),
    SHOW_DOWN(4, "showDown");

    private int code;
    private String name;

    RoundEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public RoundEnum next() {
        if (this == SHOW_DOWN) {
            throw new RuntimeException("is over");
        }
        return getRoundEnum(this.code + 1);
    }

    public static RoundEnum getRoundEnum(int code) {
        for (RoundEnum roundEnum : RoundEnum.values()) {
            if (roundEnum.getCode() == code) {
                return roundEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
