package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestRunner {
    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        if (runTest("Add Doctor and Patient", TestRunner::testAddDoctorAndPatient)) {
            passed++;
        } else {
            failed++;
        }

        if (runTest("Prevent Duplicate Doctor Slot", TestRunner::testDuplicateDoctorSlot)) {
            passed++;
        } else {
            failed++;
        }

        if (runTest("Bill Generation", TestRunner::testBillGeneration)) {
            passed++;
        } else {
            failed++;
        }

        System.out.println("\nManual tests complete. Passed=" + passed + ", Failed=" + failed);
    }

    private static boolean runTest(String name, Runnable test) {
        try {
            test.run();
            System.out.println("[PASS] " + name);
            return true;
        } catch (Throwable t) {
            System.out.println("[FAIL] " + name + " -> " + t.getMessage());
            return false;
        }
    }

    private static void testAddDoctorAndPatient() {
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();

        Doctor doctor = new Doctor(IdGenerator.generate("DOC-"), "Dr. Test", "9999988888", "doc@test.com",
                "General", BigDecimal.valueOf(500));
        Patient patient = new Patient(IdGenerator.generate("PAT-"), "Pat Test", "8888877777", "pat@test.com",
                DateUtil.parseDate("2000-01-01"), "A+");

        doctorService.addDoctor(doctor);
        patientService.addPatient(patient);

        assertTrue(doctorService.searchById(doctor.getId()).isPresent(), "Doctor should exist");
        assertTrue(patientService.searchById(patient.getId()).isPresent(), "Patient should exist");
    }

    private static void testDuplicateDoctorSlot() {
        Doctor doctor = new Doctor(IdGenerator.generate("DOC-"), "Dr. Slot", "7777766666", "slot@test.com",
                "Neuro", BigDecimal.valueOf(700));
        AppointmentService appointmentService = new AppointmentService();
        LocalDateTime slot = LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0);

        appointmentService.bookAppointment("PAT-1", doctor, slot, "Headache");

        try {
            appointmentService.bookAppointment("PAT-2", doctor, slot, "Migraine");
            throw new AssertionError("Expected duplicate slot validation to fail");
        } catch (InvalidDataException expected) {
            // expected branch
        }
    }

    private static void testBillGeneration() {
        Doctor doctor = new Doctor(IdGenerator.generate("DOC-"), "Dr. Bill", "6666655555", "bill@test.com",
                "Ortho", BigDecimal.valueOf(1000));
        AppointmentService appointmentService = new AppointmentService();

        Appointment appointment = appointmentService.bookAppointment(
                "PAT-9", doctor, LocalDateTime.now().plusDays(2), "Knee pain");

        BigDecimal finalAmount = appointmentService.completeAndGenerateBill(
                appointment.getId(), doctor, BigDecimal.ZERO).getFinalAmount();

        assertTrue(finalAmount.compareTo(BigDecimal.valueOf(1000)) > 0, "Final amount should include tax");
        assertTrue(appointment.getStatus() == Appointment.Status.COMPLETED, "Appointment must be completed");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
