package com.allstate.payments.unittests;

import com.allstate.payments.data.PaymentRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ServiceTests {
    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @Test
    public void testGetPaymentCount() {
        CreditCardTransaction tx1 = new CreditCardTransaction();
        CreditCardTransaction tx2 = new CreditCardTransaction();
        CreditCardTransaction tx3 = new CreditCardTransaction();
        CreditCardTransaction tx4 = new CreditCardTransaction();

        tx1.setId(1);
        tx2.setId(12);
        tx3.setId(123);
        tx4.setId(1234);

        List<CreditCardTransaction> expected = new ArrayList <CreditCardTransaction>();
        expected.add(tx1);
        expected.add(tx2);
        expected.add(tx3);
        expected.add(tx4);

        Mockito.when(paymentRepository.findAll()).thenReturn(expected);
        Map<String, String> actual = paymentService.getPaymentCount();
        assertEquals("4", actual.get("count"));
    }
}
