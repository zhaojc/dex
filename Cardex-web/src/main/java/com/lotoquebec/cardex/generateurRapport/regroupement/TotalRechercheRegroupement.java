/*
 * Created on 4-Feb-2008
 */
package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.util.Iterator;
import java.util.List;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class TotalRechercheRegroupement {
	
	private long nombreTotalMinutes = 0;
	private double quota = 0.00;
	private double nombreHeuresProductive = 0;
	
	
	public long getNombreTotalMinutes() {
		return nombreTotalMinutes;
	}
	public void setNombreTotalMinutes(long nombreTotalMinutes) {
		this.nombreTotalMinutes = nombreTotalMinutes;
	}
	public double getQuota() {
		return quota;
	}
	public void setQuota(double quota) {
		this.quota = quota;
	}
	public void addMinutes(long nombreMinutes) {
		nombreTotalMinutes += nombreMinutes;
	}
	public void init(){
		nombreTotalMinutes = 0;
		quota = 0;
	}
	public int getTotalHeures(){
		return (int) nombreTotalMinutes / 60;
	}
	public String getTotalHeuresFormate(){
		return getTotalHeures()+" h "+getTotalMinutes();
	}
	public String getTotalMinutes(){
		String nbMinutes = String.valueOf( (int) nombreTotalMinutes % 60 );
		return StringUtils.right("00"+nbMinutes, 2);
	}	
	public void calculerQuota(List listeResultatPresentationRegroupement){
		quota = 0;
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			quota += resultatPresentationRegroupement.getQuota();
		}
	}
	public double getNombreHeuresProductive() {
		return nombreHeuresProductive;
	}
	public void setNombreHeuresProductive(double nombreHeuresNonComptabilise) {
		this.nombreHeuresProductive = nombreHeuresNonComptabilise;
	}
	public long getMinutesNonComptablilise(){
		return (long) ((nombreHeuresProductive*60)-nombreTotalMinutes);
	}

}
