package com.parkinglot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class SmartParkingBoyTest {
    @Test
    void should_park_to_first_parking_lot_when_park_given_two_empty_parking_lots_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_lot_when_park_given_two_parking_lots_first_has_parked_car_smart_parking_boy_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car parkedCarInFirstLot = new Car();
        ParkingTicket parkingTicketCarInFirstLot = parkingBoy.park(parkedCarInFirstLot);
        Car carToBeParkedInSecondLot = new Car();
        //when
        ParkingTicket parkingTicketCarInSecondLot = parkingBoy.park(carToBeParkedInSecondLot);
        //then
        assertNotNull(parkingTicketCarInFirstLot);
        assertNotNull(parkingTicketCarInSecondLot);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_two_cars_given_two_parking_lots_both_with_parked_cars_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = parkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = parkingBoy.park(secondCar);
        //when
        Car firstParkedCar = parkingBoy.fetch(firstParkingTicket);
        Car secondParkedCar = parkingBoy.fetch(secondParkingTicket);
        //then
        assertNotNull(firstParkedCar);
        assertNotNull(secondParkedCar);
        assertEquals(10, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }
}