package com.example.geektrust.service.impl;

import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.service.CheckInService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.PrintSummaryService;
import org.junit.jupiter.api.Test;

public class PrintSummaryImplTest {

    private MetroCardService metroCardService = new MetroCardServiceImpl();
    private CheckInService checkInService = new CheckInServiceImpl(metroCardService);
    private PrintSummaryService printSummaryService = new PrintSummaryServiceImpl(checkInService);

    @Test
    public void testPrintSummaryCall () {
        printSummaryService.printSummary();
    }
}
