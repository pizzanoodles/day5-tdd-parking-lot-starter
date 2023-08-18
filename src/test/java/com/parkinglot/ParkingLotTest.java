package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_the_car_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        assertEquals(parkingLot.getParkingTicketOfCar(car) ,parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_the_car_given_parking_lot_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        //when
        parkingLot.fetch(parkingTicket);
        //then
    }

}