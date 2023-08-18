package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(parkingLot.getParkingTicketOfCar(car), parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_the_car_given_parking_lot_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car carToPark = new Car();
        ParkingTicket parkingTicket = parkingLot.park(carToPark);
        //when
        Car parkedCar = parkingLot.fetch(parkingTicket);
        //then
        assertEquals(carToPark, parkedCar);
    }

    @Test
    void should_return_right_car_per_ticket_when_fetch_the_cars_twice_given_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car2);
        //when
        Car parkedCar1 = parkingLot.fetch(ticket1);
        Car parkedCar2 = parkingLot.fetch(ticket2);
        //then
        assertEquals(car1, parkedCar1);
        assertEquals(car2, parkedCar2);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_or_invalid_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket invalidTicket = new ParkingTicket();
        //when
        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(invalidTicket);
        });
    }

    @Test
    void should_return_nothing_when_fetch_using_a_used_parking_ticket_given_parking_lot_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        Car parkedCar = parkingLot.fetch(parkingTicket);
        Car noCar = parkingLot.fetch(parkingTicket);
        //then
        assertNull(noCar);
    }

    @Test
    void should_return_nothing_when_parking_given_parking_lot_with_full_capacity_10() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingTicket> parkingTickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingTickets.add(parkingLot.park(new Car()));
        }
        //when
        ParkingTicket noTicket = parkingLot.park(new Car());
        //then
        assertNull(noTicket);
    }
}