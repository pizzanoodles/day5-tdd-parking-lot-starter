package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {
    @Test
    void should_park_to_first_parking_lot_when_park_given_standard_parking_boy_and_two_parking_lots_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_lot_when_park_given_standard_parking_boy_two_parking_lots_first_is_full_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        List<ParkingTicket> parkingTicketsforLot1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        Car carToBeParkedInLot2 = new Car();
        //when
        ParkingTicket parkingTicket = parkingBoy.park(carToBeParkedInLot2);
        //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_twice_given_standard_parking_boy_two_parking_lots_both_with_parked_car_and_two_tickets() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car carInFirstLot = new Car();
        Car carInSecondLot = new Car();
        ParkingTicket parkingTicketFirstCar = parkingBoy.park(carInFirstLot);
        ParkingTicket parkingTicketSecondCar = parkingBoy.park(carInSecondLot);
        //when
        Car fetchedFirstCar = parkingBoy.fetch(parkingTicketFirstCar);
        Car fetchedSecondCar = parkingBoy.fetch(parkingTicketSecondCar);
        //then
        assertNotNull(parkingTicketFirstCar);
        assertNotNull(parkingTicketSecondCar);
        assertEquals(carInFirstLot, fetchedFirstCar);
        assertEquals(carInSecondLot, fetchedSecondCar);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_exception_when_fetch_given_standard_parking_boy_and_invalid_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingBoy.park(car);
        //when
        Car validCar = parkingBoy.fetch(validParkingTicket);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertNotNull(validCar);
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_given_standard_parking_boy_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //when
        Car validCar = parkingBoy.fetch(parkingTicket);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertNotNull(validCar);
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_FullParkingLotException_when_park_given_standard_parking_boy_and_two_full_parking_lots() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
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
}
