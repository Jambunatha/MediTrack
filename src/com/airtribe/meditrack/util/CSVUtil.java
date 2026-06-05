package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public final class CSVUtil {
    private CSVUtil() {
        // Private constructor to prevent instantiation
    }

    public static void exportDoctors(Path path, List<Doctor> doctors) throws IOException {
        String content = "id,name,specialization,fee\n" + doctors.stream()
                .map(d -> String.join(",", d.getId(), d.getName(), d.getSpecialization(), String.valueOf(d.getConsultationFee())))
                .collect(Collectors.joining("\n"));
        Files.writeString(path, content);
    }

    public static void exportPatients(Path path, List<Patient> patients) throws IOException {
        String content = "id,name,dob,bloodGroup\n" + patients.stream()
                .map(p -> String.join(",", p.getId(), p.getName(), p.getDateOfBirth().toString(), p.getBloodGroup()))
                .collect(Collectors.joining("\n"));
        Files.writeString(path, content);
    }

    public static void exportAppointment(Path path, List<Appointment> appointments) throws IOException {
        String content = "id,patientId,doctorId,dateTime,status\n" + appointments.stream()
                .map(a -> String.join(",", a.getId(), a.getPatientId(), a.getDoctorId(),
                        DateUtil.formatDateTime(a.getAppointmentDateTime()), a.getStatus().name()))
                .collect(Collectors.joining("\n"));
        Files.writeString(path, content);
    }
}
