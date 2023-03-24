package com.example.geektrust.service.impl;

import com.example.geektrust.constant.PassengerType;
import com.example.geektrust.constant.Station;
import com.example.geektrust.domain.PassengerCount;
import com.example.geektrust.service.CheckInService;
import com.example.geektrust.service.PrintSummaryService;

import java.util.Map;
import java.util.PriorityQueue;

public class PrintSummaryServiceImpl implements PrintSummaryService {

    CheckInService checkInService;

    public PrintSummaryServiceImpl(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @Override
    public void printSummary() {

        Map<String, Integer> stationAmountMap = checkInService.getStationAmountMap();
        Map<String, Integer> stationDiscountMap = checkInService.getStationDiscountMap();
        Map<Station, Map<PassengerType, Integer>> stationTypeCountMap = checkInService.getStationTypeCountMap();

        for (Station station : Station.values()) {
            System.out.println("TOTAL_COLLECTION " + station.name() + " " + stationAmountMap.get(station.name()) + " " + stationDiscountMap.get(station.name()));
            System.out.println("PASSENGER_TYPE_SUMMARY");
            PriorityQueue<PassengerCount> sortedCount = convertToQueue(stationTypeCountMap.get(station));
            while (!sortedCount.isEmpty()) {
                PassengerCount passengerCount = sortedCount.poll();
                System.out.println(passengerCount.getPassengerType().name() + " " + passengerCount.getCount());
            }
        }
    }

    private PriorityQueue<PassengerCount> convertToQueue(Map<PassengerType, Integer> map) {
        PriorityQueue<PassengerCount> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<PassengerType, Integer> entry : map.entrySet()) {
            priorityQueue.add(new PassengerCount(entry.getKey(), entry.getValue()));
        }
        return priorityQueue;
    }
}
