package com.example.geektrust.service.impl;

import com.example.geektrust.constant.Common;
import com.example.geektrust.constant.PassengerType;
import com.example.geektrust.constant.Station;
import com.example.geektrust.domain.CheckIn;
import com.example.geektrust.service.CheckInService;
import com.example.geektrust.service.MetroCardService;

import java.util.HashMap;
import java.util.Map;

public class CheckInServiceImpl implements CheckInService {

    Map<String, Integer> stationAmountMap;
    Map<String, Integer> stationDiscountMap;
    Map<Station, Map<PassengerType, Integer>> stationTypeCountMap;
    Map<String, Station> passengerMap;

    MetroCardService metroCardService;

    public CheckInServiceImpl(MetroCardService metroCardService) {
        this.metroCardService = metroCardService;
        stationAmountMap = new HashMap<>();
        stationDiscountMap = new HashMap<>();
        stationTypeCountMap = new HashMap<>();
        for (Station station : Station.values()) {
            stationAmountMap.put(station.name(), Common.ZERO);
            stationDiscountMap.put(station.name(), Common.ZERO);
            stationTypeCountMap.put(station, new HashMap<>());
        }
        passengerMap = new HashMap<>();
    }

    @Override
    public void checkInPassenger(CheckIn checkIn) {
        String cardId = checkIn.getCardId();

        if (passengerMap.containsKey(cardId)) {
            int amount = checkIn.getPassengerType().getVal() / Common.TWO;
            int collection = stationAmountMap.get(checkIn.getFronStation().name()) + amount;
            int discount = stationDiscountMap.get(checkIn.getFronStation().name()) + amount;
            stationDiscountMap.put(checkIn.getFronStation().name(), discount);

            int remaining = metroCardService.transactCard(cardId, amount);
            if (amount != Common.ZERO) {
                collection += remaining * Common.PERCENTAGE;
            }
            //            System.out.println(amount+" "+collection+" "+discount+" "+remaining);
            stationAmountMap.put(checkIn.getFronStation().name(), collection);
            passengerMap.remove(cardId);
        } else {
            int amount = checkIn.getPassengerType().getVal();
            int collection = stationAmountMap.get(checkIn.getFronStation().name()) + amount;

            int remaining = metroCardService.transactCard(cardId, amount);
            if (amount != Common.ZERO) {
                collection += remaining * Common.PERCENTAGE;
            }
            //            System.out.println(amount+" "+collection+" "+remaining);
            stationAmountMap.put(checkIn.getFronStation().name(), collection);
            passengerMap.put(cardId, checkIn.getFronStation());
        }

        updatePassengerCount(stationTypeCountMap.get(checkIn.getFronStation()), checkIn.getPassengerType(), checkIn.getFronStation());
    }

    private void updatePassengerCount(Map<PassengerType, Integer> tempMap, PassengerType passengerType, Station station) {
        if (tempMap.containsKey(passengerType)) {
            int val = tempMap.get(passengerType) + 1;
            tempMap.put(passengerType, val);
        } else {
            tempMap.put(passengerType, 1);
        }
        stationTypeCountMap.put(station, tempMap);
    }

    @Override
    public Map<Station, Map<PassengerType, Integer>> getStationTypeCountMap() {
        return this.stationTypeCountMap;
    }

    @Override
    public Map<String, Integer> getStationAmountMap() {
        return this.stationAmountMap;
    }

    @Override
    public Map<String, Integer> getStationDiscountMap() {
        return this.stationDiscountMap;
    }
}
