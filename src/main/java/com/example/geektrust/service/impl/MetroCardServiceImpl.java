package com.example.geektrust.service.impl;

import com.example.geektrust.constant.Common;
import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.service.MetroCardService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroCardServiceImpl implements MetroCardService {

    private final Map<String, MetroCard> metroCards = new HashMap<>();

    @Override
    public void addCard(String cardId, int balance) {
        MetroCard metroCard = new MetroCard(cardId, balance);
        metroCards.put(cardId, metroCard);
//        System.out.println(cardId+" "+balance);
    }

    @Override
    public List<MetroCard> getCards() {
        List<MetroCard> list = new ArrayList<>();
        for (Map.Entry<String, MetroCard> entry : metroCards.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public int transactCard(String cardId, int amount) {
        MetroCard metroCard = metroCards.get(cardId);
        int balance = metroCard.getBalance(), diff = balance - amount;
        if (diff < Common.ZERO) {
            metroCard.setBalance(Common.ZERO);
            metroCards.put(cardId, metroCard);
            return Math.abs(balance - amount);
        }
        metroCard.setBalance(diff);
        metroCards.put(cardId, metroCard);
        return Common.ZERO;
    }
}
