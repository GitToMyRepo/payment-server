package com.allstate.payments.unittests;

import com.allstate.payments.control.PaymentController;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ControllerTests {

    @Autowired
    PaymentController paymentController;

    @MockBean
    PaymentService paymentService;

    @Test
    public void checkThatNumberOfTransactionsIsAMapWithAKeyOfVolume() {
        Map<String, String> expected = new HashMap<String, String>();
        expected.put("count", "12345");
        Mockito.when(paymentService.getPaymentCount()).thenReturn(expected);
        Map<String, String> actual = paymentController.getPaymentCount();
        assertEquals("12345", actual.get("count"));
        assertEquals(expected, actual);
    }
}
