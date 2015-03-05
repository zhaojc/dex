/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.lotoquebec.cardexCommun.rapport.SousCollection;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class ResultatPresentationRegroupement implements SousCollection{

	private long cle = 0;
	private String nomRegroupement = "";
	private double pourcentage = 0;
	private double quota = 0;
	private int quotaMin = 0;
	private long nombreMinutes = 0;
	private String endroit = "";
	private long cleIntervenant = 0;
	private String nomIntervenant = "";
	private String prenomIntervenant = "";
	private String matricule = "";
	private String categorie = "";
	private String type = "";
	private String moisLettres = "";
	private String moisNombre = "";
	private List listeSubResultatPresentationRegroupement = new ArrayList(); //ResultatPresentationRegroupement
	
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getIntervenant() {
		return nomIntervenant + " " + prenomIntervenant;
	}
	public long getNombreMinutes() {
		return nombreMinutes;
	}
	public String getHeures(){
	    String signeMinutes="";
        if (nombreMinutes < 0 && Math.abs(nombreMinutes) < 60)
            signeMinutes="-";
      
      return signeMinutes + String.valueOf((int) nombreMinutes / 60);   
	}
	public String getMinutes(){
		String nbMinutes = String.valueOf( Math.abs((int) nombreMinutes % 60) );
		return StringUtils.right("00"+nbMinutes, 2);
	}	
	public void setNombreMinutes(long nombreMinutes) {
		this.nombreMinutes = nombreMinutes;
	}
	public void addNombreMinutes(long nombreMinutes) {
		this.nombreMinutes += nombreMinutes;
	}	
	public String getNomRegroupement() {
		return nomRegroupement;
	}
	public void setNomRegroupement(String nomRegroupement) {
		this.nomRegroupement = nomRegroupement;
	}
	public double getQuota() {
		return quota;
	}
	public void setQuota(double quota) {
		this.quota = quota;
	}
	public int getQuotaMin() {
		return quotaMin;
	}
	public void setQuotaMin(int quotaMin) {
		this.quotaMin = quotaMin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public double getEcart(){
		return pourcentage - quota;
	}
	
	public String getEndroit() {
		return endroit;
	}
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	public List getListeSubResultatPresentationRegroupement() {
		return listeSubResultatPresentationRegroupement;
	}
	public void setListeSubResultatPresentationRegroupement(
			List listeSubResultatPresentationRegroupement) {
		this.listeSubResultatPresentationRegroupement = listeSubResultatPresentationRegroupement;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.util.SousCollection#addSousCollection(java.lang.Object)
	 */
	public void addSousCollection(Object element) {
		listeSubResultatPresentationRegroupement.add( element ); 
	}
	
	public long calculerNombreMinutesSub(){
		long nbMinutes = 0;
		
		if (listeSubResultatPresentationRegroupement.size() > 0){
			Iterator iter = listeSubResultatPresentationRegroupement.iterator();
			
			while (iter.hasNext()) {
				ResultatPresentationRegroupement regroupement = (ResultatPresentationRegroupement) iter.next();
				
				if (regroupement.getListeSubResultatPresentationRegroupement().size() > 0){
					long nbMinutesSub = regroupement.calculerNombreMinutesSub();
					nbMinutes += nbMinutesSub;
					regroupement.setNombreMinutes( nbMinutesSub );
				}else{
					nbMinutes += regroupement.getNombreMinutes();
				}
			}
		}else{
			nbMinutes = getNombreMinutes();
		}
		return nbMinutes;
	}
	
	private double getPourcentage(long iNombreMinutes, long nombreTotalMinutes){
		if (nombreTotalMinutes == 0)
			return 0; // division par 0
		double nbrMinutes = iNombreMinutes * 100;
		return nbrMinutes / nombreTotalMinutes ;
	}
	
	public void calculerPoucentage(long nombreTotalMinutes){
		this.setPourcentage( getPourcentage( this.getNombreMinutes(), nombreTotalMinutes ));
		
		if (listeSubResultatPresentationRegroupement.size() > 0){
			Iterator iter = listeSubResultatPresentationRegroupement.iterator();
			
			while (iter.hasNext()) {
				ResultatPresentationRegroupement regroupement = (ResultatPresentationRegroupement) iter.next();
				regroupement.calculerPoucentage( this.getNombreMinutes() );
			}
		}
	}	
	
	public Object cloneSansCollection(){
		ResultatPresentationRegroupement regroupement = new ResultatPresentationRegroupement();
		
		try {
			Map map = BeanUtils.describe(this);
			map.remove("listeSubResultatPresentationRegroupement");
			BeanUtils.populate(regroupement, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return regroupement;
	}
	public String getNomIntervenant() {
		return nomIntervenant;
	}
	public void setNomIntervenant(String nomIntervenant) {
		this.nomIntervenant = nomIntervenant;
	}
	public String getPrenomIntervenant() {
		return prenomIntervenant;
	}
	public void setPrenomIntervenant(String prenomIntervenant) {
		this.prenomIntervenant = prenomIntervenant;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.util.SousCollection#getSousCollection(java.lang.Object)
	 */
	public Collection getSousCollection() {
		return listeSubResultatPresentationRegroupement;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public long getCleIntervenant() {
		return cleIntervenant;
	}
	public void setCleIntervenant(long cleIntervenant) {
		this.cleIntervenant = cleIntervenant;
	}
	public long getCle() {
		return cle;
	}
	public void setCle(long cle) {
		this.cle = cle;
	}
	public String getHeureFormate(){
		return getHeures()+" h "+getMinutes();
	}
	/**
	 * @return annee_mois_lettres
	 */
	public String getMoisLettres() {
		return moisLettres;
	}
	/**
	 * @param annee_mois_lettres annee_mois_lettres à définir
	 */
	public void setMoisLettres(String moisLettres) {
		this.moisLettres = moisLettres;
	}
	/**
	 * @return annee_mois_nombre
	 */
	public String getMoisNombre() {
		return moisNombre;
	}
	/**
	 * @param annee_mois_nombre annee_mois_nombre à définir
	 */
	public void setMoisNombre(String moisNombre) {
		this.moisNombre = moisNombre;
	}	
}
