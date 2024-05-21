package edu.gatech.seclass.jobcompare6300.util;

import java.math.BigInteger;

public class IntUtil {

    private IntUtil(){}

    public static boolean isGreaterThanIntMaxValue(final String input){
        if(input == null || input.length() == 0){
            return false;
        }
        try {
            final BigInteger bigInt = new BigInteger(input);
            final BigInteger max = new BigInteger("" + Integer.MAX_VALUE);
            if (max.compareTo(bigInt) < 0) {
                return true;
            } else if (max.compareTo(bigInt) == 0) {
                return false;
            } else {//max.compareTo(bigInt) > 0
                return false;
            }
        }catch (NumberFormatException numberFormatException){
            return false;
        }
    }
}
