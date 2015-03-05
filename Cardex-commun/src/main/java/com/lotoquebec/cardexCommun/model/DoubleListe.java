package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class DoubleListe implements Serializable{

	private List<LabelValue> gaucheCol = new ArrayList<LabelValue>();
	private List<LabelValue> droiteCol = new ArrayList<LabelValue>();
	private List<LabelValue> totalCol = new ArrayList<LabelValue>();

	public List<LabelValue> getGaucheCols() {
		return gaucheCol;
	}
	
	public String[] getGaucheColSTR() {
		return convertColToStr(getGaucheCols());
	}
	
	public void setGaucheColSTR(String[] str)  {
		setGaucheCols(convertStrLabelValueBean(str));
	}
	
	private String[] convertColToStr(Collection<LabelValue> col) {
		Collection temp = new ArrayList();
		for (Iterator iter = col.iterator(); iter.hasNext();) {
			LabelValueBean element = (LabelValueBean) iter.next();
			temp.add(element.getValue());
		}

		return (String[]) temp.toArray(new String[temp.size()]);
	}
	
	public void addGaucheCol(LabelValue add) {
		gaucheCol.add(add);
		totalCol.add(add);
	}
	
	public void setGaucheCols(List<LabelValue> collection) {
		gaucheCol = collection;
		totalCol.addAll(collection);
	}
	
	public void viderGaucheCol() {
		gaucheCol = new ArrayList();
	}
	
	private List<LabelValue> convertStrLabelValueBean(String[] str) {
		List<LabelValue> resultat = new ArrayList<LabelValue>();
		for (int i = 0; i < str.length; i++) {
			LabelValue element = trouverLabelValueBean(str[i]);
			resultat.add(element);
		}
		return resultat;
	}
	
	private LabelValue trouverLabelValueBean(String value) {

		if (StringUtils.isNotEmpty(value)) {
			
			for(LabelValue labelValue:getTotalCols()){
				
				if (value.equals(labelValue.getValue()))
					return labelValue;
			}
			
			/*Iterator iter = getTotalCols().iterator();

			while (iter.hasNext()) {
				LabelValueBean element = (LabelValueBean) iter.next();
				if (value.equals(element.getValue()))
					return element;
			}*/
		} else
			throw new AssertionError("La valeur de la recherche de 'trouverLabelValueBean' ne peut être null");

		return null;
	}

	public List<LabelValue> getDroiteCols() {
		return droiteCol;
	}
	
	public void setDroiteColSTR(String[] str) throws Exception {
		setDroiteCols(convertStrLabelValueBean(str));
	}
	
	public String[] getDroiteColSTR() {
		return convertColToStr(getDroiteCols());
	}

	public List<String> getDroiteColLabel() {
		List<String> resultat = new ArrayList<String>();
		for (LabelValue labelValueBean: droiteCol) {
			resultat.add(labelValueBean.getLabel());
		}
		return resultat;
	}

	
	public void addDroiteCol(LabelValue add) {
		droiteCol.add(add);
		totalCol.add(add);
	}
	
	public void setDroiteCols(List<LabelValue> collection) {
		droiteCol = collection;
		totalCol.addAll(collection);
	}
	
	public void viderDroiteCol() {
		setDroiteCols(new ArrayList(0)); 
	}

	public List<LabelValue> getTotalCols() {
		return totalCol;
	}
	
	public String[] getTotalColSTR() {
		return convertColToStr(getTotalCols());
	}	
	
	public void setTotalCol() {
		setTotalCol(new ArrayList());
		totalCol.addAll(getDroiteCols());
		totalCol.addAll(getGaucheCols());
	}
	
	private void setTotalCol(List<LabelValue> collection) {
		totalCol = collection;
	}
	
	public void viderTotalCol() {
		setTotalCol(new ArrayList());
	}
	
    /**
     * @param dblListe
     * @return
     */
    public boolean isEmpty( )
    {
        if( this.gaucheCol.size( ) == 0  &&  this.droiteCol.size( ) == 0  &&  this.totalCol.size( ) == 0 )
            return true;
        
        return false;
    }
    
    public void vider(){
    	viderDroiteCol();
    	viderGaucheCol();
    	viderTotalCol();
    }
    
    public void assignerValeurListeChoisie(Collection<String> choix, List<LabelValue> labelValueList){
    	vider();
    	
    	for (LabelValue labelValueBean:labelValueList){
    		
    		if (StringUtils.isEmpty(labelValueBean.getValue()))
    			continue;
    		
    		if (choix != null && choix.contains(labelValueBean.getValue()))
    			addDroiteCol(labelValueBean);
    		else
    			addGaucheCol(labelValueBean);
    	}
    	trier();
    }
    
    /**
     * Tous les valeurs dans choix seront dans la liste de droite.
     * @param choix
     * @param labelValueList
     */
    public void assignerValeurTousLesChoix(List<LabelValue> choix, List<LabelValue> labelValueList){
    	vider();
		droiteCol.addAll(choix);
		totalCol.addAll(choix);
		
    	for (LabelValue labelValueBean:labelValueList){
    		
    		if (StringUtils.isEmpty(labelValueBean.getValue()))
    			continue;
    		
    		if (choix.contains(labelValueBean))
    			continue;
    		else
    			addGaucheCol(labelValueBean);
    	}
    	trier();
    }
    
    public void trier(){
    	Collections.sort( droiteCol, new LabelValueBeanComparator() );
    	Collections.sort( gaucheCol, new LabelValueBeanComparator() );
    }
}
