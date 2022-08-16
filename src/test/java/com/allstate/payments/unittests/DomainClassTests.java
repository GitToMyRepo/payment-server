package com.allstate.payments.unittests;

import com.allstate.payments.domain.CreditCardTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainClassTests {
    @Test
    public void testEqualityForCreditCardTransaction() {
        CreditCardTransaction tx1 = new CreditCardTransaction();
        CreditCardTransaction tx2 = new CreditCardTransaction();

        tx1.setId(12345);
        tx2.setId(12345);

        assertEquals(tx1, tx2);
    }
}
