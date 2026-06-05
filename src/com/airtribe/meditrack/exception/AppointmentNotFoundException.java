package com.airtribe.meditrack.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String id) {
        super("Appointment not found for id: " + id);
    }
}
