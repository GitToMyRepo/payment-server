package com.allstate.payments.data;

import com.allstate.payments.domain.CreditCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<CreditCardTransaction, Integer> {
    public List<CreditCardTransaction> findAllByCountry(String country);
    public List<CreditCardTransaction> findAllById(Integer id);
    public Optional<CreditCardTransaction> findById(Integer id);
}
