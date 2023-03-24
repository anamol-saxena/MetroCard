package com.example.geektrust.main;

import com.example.geektrust.MetroStationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MetroStationServiceTest {

    MetroStationService metroStationService = new MetroStationService();

    @Test
    public void callStartMethodThrowsException () throws IOException {
        Assertions.assertThrows(IOException.class, () -> {
            metroStationService.start(new String[]{"url1"});
        });
    }

    @Test
    public void callStartMethod () throws IOException {
        metroStationService.start(new String[]{"src/test/resources/input.txt"});
    }
}
