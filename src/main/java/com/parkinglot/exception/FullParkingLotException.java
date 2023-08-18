package com.parkinglot.exception;

public class FullParkingLotException extends RuntimeException{
    public FullParkingLotException() {
        super("No available position.");
    }
}
