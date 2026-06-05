package com.airtribe.meditrack.entity;

import java.math.BigDecimal;

public final class BillSummary {
    private final String billId;
    private final String appointmentId;
    private final BigDecimal consultationFee;
    private final BigDecimal taxRate;
    private final BigDecimal discount;
    private final BigDecimal finalAmount;

    public BillSummary(String billId, String appointmentId, BigDecimal consultationFee, BigDecimal taxRate,
                       BigDecimal discount, BigDecimal finalAmount) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.consultationFee = consultationFee;
        this.taxRate = taxRate;
        this.discount = discount;
        this.finalAmount = finalAmount;
    }

    public String getBillId() {
        return billId;
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

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", consultationFee=" + consultationFee +
                ", taxRate=" + taxRate +
                ", discount=" + discount +
                ", finalAmount=" + finalAmount +
                '}';
    }
}
