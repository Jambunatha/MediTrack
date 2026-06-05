package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    public enum Status {
        SCHEDULED,
        COMPLETED,
        CANCELLED
    }

    private final String id;
    private final String patientId;
    private final String doctorId;
    private final LocalDateTime appointmentDateTime;
    private final String reason;
    private Status status;

    public Appointment(String id, String patientId, String doctorId, LocalDateTime appointmentDateTime, String reason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDateTime = appointmentDateTime;
        this.reason = reason;
        this.status = Status.SCHEDULED;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public String getReason() {
        return reason;
    }

    public Status getStatus() {
        return status;
    }

    public void markCompleted() {
        this.status = Status.COMPLETED;
    }

    public void markCancelled() {
        this.status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", appointmentDateTime=" + appointmentDateTime +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                '}';
    }
}
