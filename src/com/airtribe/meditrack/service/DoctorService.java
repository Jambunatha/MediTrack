package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {
    private final DataStore<Doctor> doctorDataStore = new DataStore<>();

    public void addDoctor(Doctor Doctor) {
        doctorDataStore.save(Doctor.getId(), Doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorDataStore.findAll();
    }

    @Override
    public Optional<Doctor> searchById(String id) {
        return doctorDataStore.findById(id);
    }

    @Override
    public List<Doctor> searchByName(String name) {
        return doctorDataStore.findAll().stream()
                .filter(doctor -> doctor.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
