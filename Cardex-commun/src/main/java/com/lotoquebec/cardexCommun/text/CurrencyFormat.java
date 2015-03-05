package com.lotoquebec.cardexCommun.text;

import java.text.ParseException;
import java.text.DecimalFormat;
import java.lang.Number;
import java.util.Locale;

public class CurrencyFormat {


    private static DecimalFormat numberFormatterFrench
            = (DecimalFormat)DecimalFormat.getInstance(Locale.CANADA_FRENCH);

    private static DecimalFormat numberFormatterEnglish
            = (DecimalFormat)DecimalFormat.getInstance(Locale.US);

    /**
     *
     * @param locale
     * @param extended
     * @return
     */
    public static DecimalFormat getFormatter(Locale locale) {
        if ( locale.equals(Locale.ENGLISH) ) {
          return numberFormatterEnglish;
        }
        else {
          return numberFormatterFrench;
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static double parse(String value, Locale locale) throws ParseException {
        if (null != value  && 0 != value.trim().length()) {
            DecimalFormat numberFormat
                    = getFormatter(locale);
            //On force l'affichage de deux décimales.
            numberFormat.setMinimumFractionDigits(2);
            Number number = numberFormat.parse(value);
            return number.doubleValue();
        } else {
            return 0;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String format(double value, Locale locale) {
        DecimalFormat numberFormat
                = getFormatter(locale);
        return numberFormat.format(value);
    }

}