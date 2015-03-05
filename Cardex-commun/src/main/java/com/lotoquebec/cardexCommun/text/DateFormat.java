package com.lotoquebec.cardexCommun.text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateFormat {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd");
    public static final String DATE_DEFAULT = null;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SANS_TIRET = "yyyyMMdd";
    public static final String DATE_FORMAT_AVEC_HEURE = "yyyy-MM-dd kk:mm:ss";
    public static String Format_SQL = "YYYY-MM-DD HH24:MI:SS";

    public static Date parse(String value) throws ParseException {
      if (null != value  && 0 != value.trim().length()) {
        return dateFormatter.parse(value);
      }else {
        return null;
      }
    }

    public static String format(Date value) {
      if (null != value ) {
        return dateFormatter.format(value);
      }else {
        return null;
      }
    }
    
    public static String format(Date value, String dateFormat) {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat (dateFormat);
      if (null != value ) {
        return dateFormatter.format(value);
      }else {
        return null;
      }
    }
}