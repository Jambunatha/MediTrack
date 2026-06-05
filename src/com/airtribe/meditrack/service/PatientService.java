package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {
    private final DataStore<Patient> patientDataStore = new DataStore<>();

    public void addPatient(Patient patient) {
        patientDataStore.save(patient.getId(), patient);
    }

    public List<Patient> getAllPatients() {
        return patientDataStore.findAll();
    }

    @Override
    public Optional<Patient> searchById(String id) {
        return patientDataStore.findById(id);
    }

    @Override
    public List<Patient> searchByName(String name) {
        return patientDataStore.findAll().stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
