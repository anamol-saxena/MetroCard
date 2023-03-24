package com.example.geektrust;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        MetroStationService metroStationService = new MetroStationService();
        metroStationService.start(args);
    }
}
