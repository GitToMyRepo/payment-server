package com.allstate.payments.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name="cctransactions")
public class CreditCardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private String country;
    private String currency;
    private LocalDate date;
    @Column(name="order_id")
    private String orderId;
    @Column(name="tax_code")
    private Integer taxCode;
    @Column(name="tax_rate")
    private Double taxRate;
    private String type;

    public CreditCardTransaction() {
    }

    public CreditCardTransaction(Integer id, Double amount, String country, String currency, LocalDate date, String orderId, Integer taxCode, Double taxRate, String type) {
        this.id = id;
        this.amount = amount;
        this.country = country;
        this.currency = currency;
        this.date = date;
        this.orderId = orderId;
        this.taxCode = taxCode;
        this.taxRate = taxRate;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTaxCode() {
        return taxCode;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "CreditCardTransaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", orderId='" + orderId + '\'' +
                ", taxCode=" + taxCode +
                ", taxRate=" + taxRate +
                ", type='" + type + '\'' +
                '}';
    }
}
