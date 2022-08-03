package com.allstate.payments.service;

import com.allstate.payments.domain.CreditCardTransaction;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    public List<CreditCardTransaction> getAllTransactions();
    public String getNumberOfPayments();
    public Map<String, String> getPaymentCount();
    public List<CreditCardTransaction> getAllTransactionsForCountry(String country);
    public List<CreditCardTransaction> getTransaction(Integer id);
    public CreditCardTransaction getTransactionById(Integer id);
    public CreditCardTransaction addTransaction(CreditCardTransaction transaction);
    public CreditCardTransaction updateTransaction(Integer id, CreditCardTransaction transaction);
}
