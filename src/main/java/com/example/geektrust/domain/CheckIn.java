package com.example.geektrust.domain;

import com.example.geektrust.constant.PassengerType;
import com.example.geektrust.constant.Station;

public class CheckIn {

    private final String cardId;
    private final PassengerType passengerType;
    private final Station fronStation;

    public CheckIn(String cardId, String passengerType, String fromStation) {
        this.cardId = cardId;
        this.passengerType = PassengerType.valueOf(passengerType);
        this.fronStation = Station.valueOf(fromStation);
    }

    public String getCardId() {
        return this.cardId;
    }

    public PassengerType getPassengerType() {
        return this.passengerType;
    }

    public Station getFronStation() {
        return this.fronStation;
    }

}
