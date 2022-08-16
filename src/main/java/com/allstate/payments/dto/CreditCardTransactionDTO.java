package com.allstate.payments.dto;

import com.allstate.payments.domain.CreditCardTransaction;

import java.time.LocalDate;

public class CreditCardTransactionDTO {
    private String country;
    private LocalDate date;
    private Integer taxCode;
    private Double taxRate;

    public String getCountry() {
        return country;
    }

//    public LocalDate getDate() {
//        return date;
//    }

    public Integer getTaxCode() {
        return taxCode;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CreditCardTransaction toCreditCardTransaction() {
        return new CreditCardTransaction(null, null, country, null, date, null, taxCode, taxRate, null);
    }

    @Override
    public String toString() {
        return "CreditCardTransactionDTO{" +
                "date=" + date +
                ", taxCode=" + taxCode +
                ", taxRate=" + taxRate +
                '}';
    }
}
