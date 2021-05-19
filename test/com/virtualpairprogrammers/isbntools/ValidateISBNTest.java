package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {

    @Test
    public void checkAValid10DigitISBN () {
     ValidateISBN validator = new ValidateISBN();
     boolean result = validator.checkISBN("0140449116");
     assertTrue(result, "first Value");
     result = validator.checkISBN("0140177396");
     assertTrue(result, "second Value");
    }

    @Test
    public void checkAValid13DigitISBN () {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue(result, "first Value");
        result = validator.checkISBN("9781853267338");
        assertTrue(result, "second Value");
    }

    @Test
    public void TenDigitISBNNumbersEndingInAnXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result );
    }

    @Test
    public void checkAnInValidate10DigitISBN () {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkAnInValidate13DigitISBN () {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853267337");
        assertFalse(result);
    }


    @Test
    public void nineDigitISBNAreNotAlloowed () {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
         validator.checkISBN("123456789");
        });
    }

    @Test
    public void nonNumericISBNsAreNotAllowed () {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("helloworld");
        });
    }
}
