package com.manerajona.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.Locale;

/**
 * Methods for manipulating strings.
 *
 * @author David Buster
 * @author Annie Lonny
 * @author Jessamine Gerónimo
 * @since 1.0
 */
public class StringUtils {

    /**
     * Returns a formatted decimal using the specified decimal pattern and argument.
     *
     * @param pattern  The pattern for the string format.
     * @param argument The object to format.
     * @return The formatted decimal.
     * @throws java.lang.IllegalArgumentException If the pattern is incompatible with the given argument.
     * @since 1.1
     */
    public static String formatDecimal(String pattern, Object argument)
            throws IllegalArgumentException {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setDecimalFormatSymbols(
                new DecimalFormatSymbols(Locale.getDefault())
        );
        return decimalFormat.format(argument);
    }


    /**
     * Returns the total with the currency symbol and the formatted decimal amount.
     *
     * @param currency {@link Currency} in which the amount is expressed.
     * @param amount   the total amount in {@link BigDecimal}.
     * @return The total using currency symbol and decimal format, e.g. $100,000.00.
     * @see #formatDecimal(String, Object)
     * @since 1.2
     */
    public static String formatTotal(Currency currency, BigDecimal amount) {
        return "%s %s".formatted(
                currency.getSymbol(Locale.getDefault()),
                formatDecimal("#,##0.00", amount)
        );
    }

    /**
     * Returns the formatted phone number based on the following patterns:
     * <ul>
     * <li>Pattern for 10-digit numbers: <em>(XXX) XXX-XXXX</em></li>
     * <li>Pattern for 7-digit numbers: <em>XXX-XXXX</em></li>
     * </ul>
     * <p><b>Note: The method will remove any non-digit characters from the input string.</b>
     *
     * @param phoneNumber the string phone number.
     * @return the formatted phone number.
     * @since 1.3
     */
    public static String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceAll("\\D", "");
        if (formattedNumber.length() == 10) {
            formattedNumber = formattedNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (formattedNumber.length() == 7) {
            formattedNumber = formattedNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        }
        return formattedNumber;
    }

    /**
     * Returns a masked credit card number whose last 4 characters are shown while the rest is masked using the
     * {@code maskCharacter} parameter of type {@link Character}.
     * <br>
     * <pre class="code">
     * StringUtils.maskCreditCardNumber("4111111111111111") = "XXXXXXXXXXXX1111"
     * </pre>
     *
     * @param creditCardNumber the credit card number to be masked.
     * @param maskCharacter    this {@code Character} masks the digits of the card.
     * @return the masked credit card number.
     * @since 1.4
     */
    public static String maskCreditCardNumber(String creditCardNumber, Character maskCharacter) {
        int numDigitsToMask = Math.max(0, creditCardNumber.length() - 4);
        String maskedDigits = maskCharacter.toString().repeat(numDigitsToMask);
        String lastDigits = creditCardNumber.substring(creditCardNumber.length() - 4);

        return maskedDigits + lastDigits;
    }
}