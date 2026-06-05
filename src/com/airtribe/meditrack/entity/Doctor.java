package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

import java.math.BigDecimal;

public class Doctor extends Person {
    private String specialization;
    private BigDecimal consultationFee;

    public Doctor(String id, String name, String phone, String email,
                  String specialization, BigDecimal consultationFee) {
        super(id, name, phone, email);
        Validator.requireNonBank(specialization, "Specialization");
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        Validator.requireNonBank(specialization, "Specialization");
        this.specialization = specialization;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getId() + '\'' +
                "name='" + getName() + '\'' +
                "specialization='" + specialization + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }
}
