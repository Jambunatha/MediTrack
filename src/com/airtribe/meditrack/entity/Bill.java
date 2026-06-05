package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bill implements Payable {
    private final String id;
    private final String appointmentId;
    private final BigDecimal consultationFee;
    private final BigDecimal taxRate;
    private final BigDecimal discount;

    public Bill(String id, String appointmentId, BigDecimal consultationFee, BigDecimal taxRate, BigDecimal discount) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.consultationFee = consultationFee;
        this.taxRate = taxRate;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public BigDecimal calculateAmount() {
        BigDecimal tax = consultationFee.multiply(taxRate);
        return consultationFee.add(tax).subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    public BillSummary toSummary() {
        return new BillSummary(id, appointmentId, consultationFee, taxRate, discount, calculateAmount());
    }
}
