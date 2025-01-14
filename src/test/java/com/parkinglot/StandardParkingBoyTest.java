package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {
    @Test
    void should_park_to_first_parking_lot_when_park_given_standard_parking_boy_and_two_parking_lots_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
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
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
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
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket parkingTicketFirstCar = parkingBoy.park(firstCar);
        ParkingTicket parkingTicketSecondCar = parkingBoy.park(secondCar);
        //when
        Car fetchedFirstCar = parkingBoy.fetch(parkingTicketFirstCar);
        Car fetchedSecondCar = parkingBoy.fetch(parkingTicketSecondCar);
        //then
        assertNotNull(parkingTicketFirstCar);
        assertNotNull(parkingTicketSecondCar);
        assertEquals(firstCar, fetchedFirstCar);
        assertEquals(secondCar, fetchedSecondCar);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_exception_when_fetch_given_standard_parking_boy_and_invalid_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(new ParkingTicket());
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_given_standard_parking_boy_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
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
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
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
    void should_park_car_in_first_lot_when_park_given_two_lots_with_different_capacity_and_standard_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(5);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        ParkingTicket firstParkingTicket = parkingBoy.park(car1);
        ParkingTicket secondParkingTicket = parkingBoy.park(car2);
        //then
        assertNotNull(firstParkingTicket);
        assertNotNull(secondParkingTicket);
        assertEquals(8, firstParkingLot.getAvailableCapacity());
        assertEquals(5, secondParkingLot.getAvailableCapacity());
    }
}
