package com.example.geektrust.domain;

import com.example.geektrust.constant.PassengerType;

public class PassengerCount implements Comparable<PassengerCount> {

    private final int count;
    private final PassengerType passengerType;

    public PassengerCount(PassengerType passengerType, int count) {
        this.count = count;
        this.passengerType = passengerType;
    }

    public int getCount() {
        return this.count;
    }

    public PassengerType getPassengerType() {
        return this.passengerType;
    }

    @Override
    public int compareTo(PassengerCount o) {
        return o.getCount() - this.getCount() == 0 ?
                this.passengerType.name().charAt(0) - o.passengerType.name().charAt(0)
                : o.getCount() - this.getCount();
    }
}
