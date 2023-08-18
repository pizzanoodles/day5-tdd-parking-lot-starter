package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Map<Car, ParkingTicket> carsAndTickets = new HashMap<>();
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        carsAndTickets.put(car, parkingTicket);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return new Car();
    }

    public ParkingTicket getParkingTicketOfCar(Car car) {
        return carsAndTickets.get(car);
    }
}
