package com.lotoquebec.cardexCommun.text;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.lotoquebec.cardexCommun.util.StringUtils;

public class TimestampFormat {

    private static SimpleDateFormat dateFormatter
            = new SimpleDateFormat ("yyyy-MM-dd");
    private static SimpleDateFormat dateFormatterFrench
            = new SimpleDateFormat ("yyyy-MM-dd");
    private static SimpleDateFormat dateFormatterEnglish
            = new SimpleDateFormat ("MM/dd/yyyy");
    private static SimpleDateFormat dateTimeFormatterFrench
            = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateTimeFormatterEnglish
            = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");

    /**
     *
     * @param locale
     * @param extended
     * @return
     */
    public static SimpleDateFormat getFormatter(Locale locale, boolean extended) {
        if ( locale.equals(Locale.US) ) {
            if ( extended ) {
                return dateTimeFormatterEnglish;
            }
            else {
                return dateFormatterEnglish;
            }
        }
        else {
            if ( extended ) {
                return dateTimeFormatterFrench;
            }
            else {
                return dateFormatterFrench;
            }
        }
    }

    /**
     *
     * @param extended
     * @return
     */
    public static SimpleDateFormat getFormatter(boolean extended) {
        Locale locale = Locale.getDefault();
        if ( locale.equals(Locale.US) ) {
            if ( extended ) {
                return dateTimeFormatterEnglish;
            }
            else {
                return dateFormatterEnglish;
            }
        }
        else {
            if ( extended ) {
                return dateTimeFormatterFrench;
            }
            else {
                return dateFormatterFrench;
            }
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static Timestamp parse(String value) throws ParseException {
      if (null != value  && 0 != value.trim().length()) {
        return new Timestamp (dateFormatter.parse(value).getTime());
      }else {
        return null;
      }
    }

    public static Timestamp parseFrenchTemps(String value) throws ParseException {
        if (null != value  && 0 != value.trim().length()) {
          return new Timestamp (dateTimeFormatterFrench.parse(value).getTime());
        }else {
          return null;
        }
    }
    
    /**
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static Timestamp parse(String value, Locale locale, boolean extended)
            throws ParseException {
        if (null != value  && 0 != value.trim().length()) {
        	
        	// Il existe certain cas où on format la dateDebut sans hh:mm:ss
        	if (extended && value.length() == 10)
        		value += " 00:00:00";
        	
        	if ( locale.equals(Locale.US) )
        		value = StringUtils.replace(value, "-", "/");
        	else
        		value = StringUtils.replace(value, "/", "-");
        		
            SimpleDateFormat simpleDateFormat
                    = getFormatter(locale, extended);
            return new Timestamp(simpleDateFormat.parse(value).getTime());
        } else {
            return null;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String format(Timestamp value) {
        if (null != value ) {
            return dateFormatter.format(value);
        } else {
            return null;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String format(Timestamp value, Locale locale,
            boolean extended) {
        if (null != value ) {
            SimpleDateFormat simpleDateFormat
                    = getFormatter(locale, extended);
            return simpleDateFormat.format(value);
        } else {
            return null;
        }
    }
}