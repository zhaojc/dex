package com.lotoquebec.cardexCommun.util;
import java.util.Locale;

public class CodeLangue {

  private int langue;
  private static final int FRANCAIS_NUMERIC_VALUE = 1;
  private static final int ANGLAIS_NUMERIC_VALUE = 15472;


  private CodeLangue(int langue) {
    this.langue = langue;
  }

  public static final CodeLangue FRANCAIS  = new CodeLangue(CodeLangue.FRANCAIS_NUMERIC_VALUE);
  public static final CodeLangue ANGLAIS   = new CodeLangue(CodeLangue.ANGLAIS_NUMERIC_VALUE);

  public static Locale valueOf(int langue){
    if (CodeLangue.FRANCAIS_NUMERIC_VALUE == langue) {
      return Locale.CANADA_FRENCH;
    }else if (CodeLangue.ANGLAIS_NUMERIC_VALUE == langue){
      return Locale.US;
    }else {
      // default locale for unexpected language code:
      return Locale.CANADA_FRENCH;
    }
  }

  public static CodeLangue valueOf(Locale locale) {
    if (Locale.CANADA_FRENCH.equals(locale)) {
      return CodeLangue.FRANCAIS;
    }else if (Locale.US.equals(locale)){
      return CodeLangue.ANGLAIS;
    }else {
      // default language code for unexpected locale:
      return CodeLangue.FRANCAIS;
    }
  }

  public int intValue() {
    return this.langue;
  }

}