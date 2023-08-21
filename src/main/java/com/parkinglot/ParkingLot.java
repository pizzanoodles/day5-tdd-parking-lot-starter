package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private final int capacity;
    public ParkingLot() {
        this.capacity = 10;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    private boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new FullParkingLotException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (ticketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketCarMap.remove(parkingTicket);
    }

    public int getAvailableCapacity() {
        return capacity - ticketCarMap.size();
    }

    public boolean hasAvailableCapacity() {
        return ((capacity - ticketCarMap.size()) != 0);
    }

    public boolean hasParkedCars() {
        return !ticketCarMap.isEmpty();
    }

    public double getAvailablePositionRate() {
        return ((double) getAvailableCapacity() / (double) capacity);
    }

    //TODO: Test cases for parking lot is missing
}
