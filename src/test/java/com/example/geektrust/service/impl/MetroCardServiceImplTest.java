package com.example.geektrust.service.impl;

import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.service.MetroCardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetroCardServiceImplTest {

    private final MetroCardService metroCardService = new MetroCardServiceImpl();

    @Test
    public void testAddMetroCard() {
        MetroCard metroCard = new MetroCard("MC1", 101);
        metroCardService.addCard("MC1", 101);
        Assertions.assertEquals(metroCardService.getCards().get(0).getBalance(), metroCard.getBalance());
        Assertions.assertEquals(metroCardService.getCards().get(0).getCardId(), metroCard.getCardId());
    }
}
