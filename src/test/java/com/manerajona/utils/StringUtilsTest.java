package com.manerajona.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

class StringUtilsTest {

    @Test
    void formatDecimal() {
        assert StringUtils.formatDecimal(
                "#,##0.00",
                new BigDecimal("1000000.50")
        ).equals("1,000,000.50");
    }

    @Test
    void formatTotal() {
        assert StringUtils.formatTotal(
                Currency.getInstance("USD"),
                new BigDecimal("1000000.50")
        ).equals("$ 1,000,000.50");
    }

    @Test
    void formatPhoneNumber() {
        assert StringUtils.formatPhoneNumber("0766882266").equals("(076) 688-2266");
        assert StringUtils.formatPhoneNumber("6882266").equals("688-2266");
    }

    @Test
    void maskCreditCardNumber() {
        assert StringUtils.maskCreditCardNumber("4111111111111111", 'X')
                .equals("XXXXXXXXXXXX1111");
    }
}