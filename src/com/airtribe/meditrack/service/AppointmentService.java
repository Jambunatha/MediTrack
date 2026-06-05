package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private final DataStore<Appointment> appointmentDataStore = new DataStore<>();

    public Appointment bookAppointment(String patientId, Doctor doctor, LocalDateTime slot, String reason) {
        boolean doctorBusy = appointmentDataStore.findAll().stream()
                .filter(a -> a.getStatus() == Appointment.Status.SCHEDULED)
                .anyMatch(a ->
                        a.getDoctorId().equals(doctor.getId()) && a.getAppointmentDateTime().equals(slot));

        if (doctorBusy) {
            throw new InvalidDataException("Doctor is not available for this slot");
        }

        Appointment appointment = new Appointment(IdGenerator.generate("APT-"), patientId, doctor.getId(),
                slot, reason);
        appointmentDataStore.save(appointment.getId(), appointment);
        return appointment;
    }

    public Appointment getById(String id) {
        return appointmentDataStore.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDataStore.findAll();
    }

    public void cancelAppointment(String id) {
        Appointment appointment = getById(id);
        appointment.markCancelled();
    }

    public BillSummary completeAndGenerateBill(String id, Doctor doctor, BigDecimal discount) {
        Appointment appointment = getById(id);
        appointment.markCompleted();

        Bill bill = new Bill(IdGenerator.generate("BILL-"), appointment.getId(), doctor.getConsultationFee(),
                BigDecimal.valueOf(Constants.DEFAULT_TAX_RATE), discount == null ? BigDecimal.ZERO : discount);
        return bill.toSummary();
    }
}
