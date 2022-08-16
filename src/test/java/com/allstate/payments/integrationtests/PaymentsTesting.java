package com.allstate.payments.integrationtests;

import com.allstate.payments.data.PaymentRepository;
import com.allstate.payments.dto.CreditCardTransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@AutoConfigureMockMvc
public class PaymentsTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentRepository paymentRepository;

    @Test
    public void checkNewTransactionsAreAddedToDatabase() throws Exception {
        CreditCardTransactionDTO creditCardTransactionDTO = new CreditCardTransactionDTO();
        creditCardTransactionDTO.setDate(LocalDate.now());
        creditCardTransactionDTO.setTaxCode(23);
        creditCardTransactionDTO.setTaxRate(0.36);
        creditCardTransactionDTO.setCountry("NIR");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(creditCardTransactionDTO);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/payment/create")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);
        ResultActions result = mockMvc.perform(request).andExpect(status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(creditCardTransactionDTO.toCreditCardTransaction());

        JSONAssert.assertEquals(expectedJson, responseJson, false);

        Mockito.verify(paymentRepository).save(creditCardTransactionDTO.toCreditCardTransaction());
    }
}
