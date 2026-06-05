package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MediTrack!");

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();

        Doctor doctor = new Doctor(IdGenerator.generate("DOC-"), "Dr. Jambunatha Koni", "8884887080",
                "jambu@gmail.com", "Cardiology", new BigDecimal("1500"));
        Patient patient = new Patient(IdGenerator.generate("PAT-"), "John Doe", "9845673456",
                "john.deo@gmail.com", DateUtil.parseDate("1993-02-24"), "A+");

        doctorService.addDoctor(doctor);
        patientService.addPatient(patient);

        String recommendation = AIHelper.suggestSpecialization("Mild chest pain");
        System.out.println("Recommended specialty for symptom 'Mild chest pain': " + recommendation);

        Appointment appointment = appointmentService.bookAppointment(patient.getId(), doctor,
                LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0),
                "Follow-up checkup");

        BillSummary billSummary = appointmentService.completeAndGenerateBill(
                appointment.getId(), doctor, new BigDecimal("100"));

        System.out.println("Booked: " + appointment);
        System.out.println("Bill: " + billSummary);

        try {
            CSVUtil.exportDoctors(Path.of("doctors.csv"), doctorService.getAllDoctors());
            CSVUtil.exportPatients(Path.of("patient.csv"), patientService.getAllPatients());
            CSVUtil.exportAppointment(Path.of("appointment.csv"), appointmentService.getAllAppointments());
            System.out.println("CSV exports generated in project root path.");
        } catch (IOException e) {
            System.out.println("CSV export failed: " + e.getMessage());
        }

    }
}
