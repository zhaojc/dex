package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.Comparator;

import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.StringComparator;

public class LabelValueBeanComparator implements Comparator, Serializable {
	
	StringComparator comparator = new StringComparator();
	
    public int compare(Object o1,Object o2) {
      if (o1 instanceof LabelValueBean && o2 instanceof LabelValueBean ) {
        LabelValueBean l1 = (LabelValueBean)o1;
        LabelValueBean l2 = (LabelValueBean)o2;
        if ((l1.getLabel() == null || l1.getLabel().length() == 0)) {
          return -1;
        }else if ((l2.getLabel() == null || l2.getLabel().length() == 0)) {
          return 1;
        }else{

        	//return String.CASE_INSENSITIVE_ORDER.compare(l1.getLabel(),l2.getLabel());
        	return comparator.compare(l1.getLabel(),l2.getLabel());
        }
      }else {
        return -1;
      }
    }
  }
