package com.parkinglot;

import static org.junit.jupiter.api.Assertions.*;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;
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

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_two_parking_lots_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_two_parking_lots_a_used_ticket_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket ticketToBeUsed = parkingBoy.park(car);
        Car validCar = parkingBoy.fetch(ticketToBeUsed);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertNotNull(validCar);
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_FullParkingLotException_when_park_given_two_full_parking_lots_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car());
        }
        //when
        FullParkingLotException fullParkingLotException = assertThrows(FullParkingLotException.class, () -> {
            parkingBoy.park(new Car());
        });
        //then
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(0, secondParkingLot.getAvailableCapacity());
        assertEquals("No available position.", fullParkingLotException.getMessage());
    }

    @Test
    void should_park_car_in_second_lot_when_park_given_two_parking_lots_with_different_capacities_and_smart_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(15);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        //then
        assertNotNull(parkingTicket);
        assertEquals(10, firstParkingLot.getAvailableCapacity());
        assertEquals(14, secondParkingLot.getAvailableCapacity());
    }
}