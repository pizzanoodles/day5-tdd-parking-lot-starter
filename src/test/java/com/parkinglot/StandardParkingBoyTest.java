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
}
