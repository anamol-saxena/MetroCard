package com.example.geektrust.domain;

public class MetroCard {

    private final String cardId;
    private int balance;

    public MetroCard(String cardId, int balance) {
        this.cardId = cardId;
        this.balance = balance;
    }

    public String getCardId() {
        return this.cardId;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
