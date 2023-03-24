package com.example.geektrust.constant;

public enum PassengerType {
    ADULT(200),
    SENIOR_CITIZEN(100),
    KID(50);

    private final int val;

    PassengerType(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }
}
