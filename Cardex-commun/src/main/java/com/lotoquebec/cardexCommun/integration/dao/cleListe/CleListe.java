/*
 * Created on 20-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public abstract class CleListe implements Serializable{

	private static final long serialVersionUID = -3678418904204939651L;
	protected String langue = "";
	protected boolean discriminantValeurRequis = false;
	protected String discriminantValeur = "";
	
	public CleListe(String langue) {
		super();
		this.langue = langue;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	public int hashCode() {
		return 1;
	}
	public boolean equals(Object obj) {
		
		if (obj.getClass().equals( this.getClass() ) == false)
			return false;
		
		CleListe cleListe = (CleListe) obj;
		
		if (StringUtils.equals( langue, cleListe.getLangue() )  == false){
			return false;
		}
		return true;
	}
	
	public boolean isDiscriminantValeurRequis() {
		return discriminantValeurRequis;
	}
	
	public String getDiscriminantValeur() {
		return discriminantValeur;
	}
	
	public boolean isDiscreminantVide(){
		return StringUtils.isEmpty(discriminantValeur);
	}
	
}
