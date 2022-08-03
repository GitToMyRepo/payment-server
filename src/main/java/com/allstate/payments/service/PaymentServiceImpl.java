package com.allstate.payments.service;

import com.allstate.payments.data.PaymentRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.exception.InvalidTransactionException;
import com.allstate.payments.exception.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<CreditCardTransaction> getAllTransactions() {
        List<CreditCardTransaction> payments = this.paymentRepository.findAll();
        System.out.println("returned " + payments);
        //        new ArrayList<>();
        //payments.add(new CreditCardTransaction(1, 100.0, "USA", "USD", LocalDate.now(), "0001", 1, 0.01, "Visa"));
       // payments.add(new CreditCardTransaction(1, 200.0, "FRA", "EUR", LocalDate.of(2022,5,1), "0002", 2, 0.02, "MasterCard"));
        return payments;
    }

    @Override
    public String getNumberOfPayments() {
        return "{\"volume\" : " + getAllTransactions().size() + "}";
    }

    @Override
    public Map<String, String> getPaymentCount() {
        Map<String, String> count = new HashMap<String, String>();
        count.put("count", String.valueOf(getAllTransactions().size()));
        return count;
    }

    @Override
    public List<CreditCardTransaction> getAllTransactionsForCountry(String country) {
        return this.paymentRepository.findAllByCountry(country);
    }

    @Override
    public List<CreditCardTransaction> getTransaction(Integer id) {
        return this.paymentRepository.findAllById(id);
    }

    @Override
    public CreditCardTransaction getTransactionById(Integer id) {
        Optional<CreditCardTransaction> optionalTran = paymentRepository.findById(id);
        if (optionalTran.isPresent()) {
            return optionalTran.get();
        } else {
            throw new TransactionNotFoundException("Transaction not found: " + id);
        }
    }

    @Override
    public CreditCardTransaction addTransaction(CreditCardTransaction transaction) {
        try {
            return this.paymentRepository.save(transaction);
        } catch (Exception e) {
            throw new InvalidTransactionException(e.getMessage());
        }
    }

    @Override
    public CreditCardTransaction updateTransaction(Integer id, CreditCardTransaction transaction) {
        System.out.println("updating " + transaction);
        CreditCardTransaction tranx = null;
        try {
            tranx = this.paymentRepository.findById(id).get();
            System.out.println("found " + tranx);
            transaction.setId(tranx.getId());
            if (transaction.getOrderId() == null) transaction.setOrderId(tranx.getOrderId());
            if (transaction.getTaxCode() == null) transaction.setTaxCode(tranx.getTaxCode());
            //tranx = transaction;
            CreditCardTransaction saved = this.paymentRepository.save(transaction);
            System.out.println("saved " + saved);
            return saved;
        } catch (Exception e) {
            throw new InvalidTransactionException(e.getMessage());
        }
    }
}
