package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

import java.time.LocalDate;

public class Patient extends Person {
    private LocalDate dateOfBirth;
    private String bloodGroup;

    public Patient(String id, String name, String phone, String email, LocalDate dateOfBirth, String bloodGroup) {
        super(id, name, phone, email);
        Validator.requireNonBank(bloodGroup, "Blood Group");
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        Validator.requireNonBank(bloodGroup, "Blood Group");
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                "name='" + getName() + '\'' +
                "dateOfBirth=" + dateOfBirth +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
