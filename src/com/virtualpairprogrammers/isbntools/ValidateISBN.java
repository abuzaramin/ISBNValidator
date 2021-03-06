package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {


    public static final int LONG_ISBN_LENGHT = 13;
    public static final int SHORT_ISBN_LENGHT = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN (String isbn) {

        if (isbn.length() == LONG_ISBN_LENGHT) {
            return isThisValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGHT) {
            return isThisValidShortISBN(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private boolean isThisValidShortISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < 10; i++) {

            if (!Character.isDigit(isbn.charAt(i))) {

                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN number can only contain numeric digits");
                }

            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGHT - i);
            }

        }
        return (total % SHORT_ISBN_MULTIPLIER == 0) ;
    }

    private boolean isThisValidLongISBN(String isbn) {
        int total = 0;
        for (int i = 0 ; i < LONG_ISBN_LENGHT ; i++) {
            if (i% 2  == 0 ) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return (total % LONG_ISBN_MULTIPLIER == 0 ) ;
    }
}
