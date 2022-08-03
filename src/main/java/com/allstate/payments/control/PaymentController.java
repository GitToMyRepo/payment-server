package com.allstate.payments.control;

import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }

    @GetMapping("")
    public List<CreditCardTransaction> getAll(@RequestParam(name="orderid", required = false) Integer id,
                                              @RequestParam(name="country", required = false) String country) {
        if (id != null) {
            return this.paymentService.getTransaction(id);
        } else if (country != null) {
            return this.paymentService.getAllTransactionsForCountry(country);
        } else {
            return paymentService.getAllTransactions();
        }
    }

    @GetMapping("/volume")
    public String getNumberOfPayments() {
        return paymentService.getNumberOfPayments();
    }

    @GetMapping("/count")
    public Map<String, String> getPaymentCount() {
        return paymentService.getPaymentCount();
    }

    @GetMapping(value ="/{id}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CreditCardTransaction getPaymentById(@PathVariable("id") Integer id) {
        return paymentService.getTransactionById(id);
    }

    @PostMapping
    public CreditCardTransaction addPayment(@RequestBody CreditCardTransaction transaction) {
        System.out.println("adding " + transaction);
        CreditCardTransaction saved = this.paymentService.addTransaction(transaction);
        System.out.println("saved " + saved);
        return saved;
    }

    @PutMapping("/{id}")
    public CreditCardTransaction updatePayment(@PathVariable("id") Integer id, @RequestBody CreditCardTransaction transaction) {
        System.out.println("updating " + id);
        System.out.println("with " + transaction);
        CreditCardTransaction saved = this.paymentService.updateTransaction(id, transaction);
        System.out.println("saved " + saved);
        return saved;
    }
}
