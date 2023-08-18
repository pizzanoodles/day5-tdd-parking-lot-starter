package com.parkinglot;

import static org.junit.jupiter.api.Assertions.*;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

class SuperParkingBoyTest {
    @Test
    void should_park_car_when_park_given_two_empty_parking_lots_and_super_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLOt = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLOt);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLOt.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_lot_when_park_given_two_parking_lots_and_9_cars_and_super_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        for (int i = 0; i < 8; i++) {
            parkingBoy.park(new Car());
        }
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(5, firstParkingLot.getAvailableCapacity());
        assertEquals(6, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_correct_car_with_each_ticket_when_fetch_given_two_parking_lots_with_parked_cars_and_super_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket firstParkingTicket = parkingBoy.park(car1);
        ParkingTicket secondParkingTicket = parkingBoy.park(car2);
        //when
        Car firstParkedCar = parkingBoy.fetch(firstParkingTicket);
        Car secondParkedCar = parkingBoy.fetch(secondParkingTicket);
        //then
        assertEquals(car1, firstParkedCar);
        assertEquals(car2, secondParkedCar);
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_parking_lot_with_invalid_ticket_and_super_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_parking_lot_used_ticket_and_super_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(parkingTicket);
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_FullParkingLotException_when_park_given_two_full_parking_lots_and_super_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car());
        }
        //when
        FullParkingLotException fullParkingLotException = assertThrows(FullParkingLotException.class, () -> {
            parkingBoy.park(new Car());
        });
        //then
        assertEquals("No available position.", fullParkingLotException.getMessage());
    }
}