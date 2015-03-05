package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SousCategoriesCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SousCategoriesNatureCle;
import com.lotoquebec.cardexCommun.model.VOForm;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SousCategorieForm extends VOForm<SousCategorieVO> implements Serializable, LabelValue {

	private static final long serialVersionUID = -3563314107734116262L;
	private String description = "";
	
	public SousCategorieForm(SousCategorieVO sousCategorieVO) {
		this.entite = sousCategorieVO;
	}
	/*
	public long getCle() {
		return entiteCardex.getCle();
	}*/
	
	public void setCle(long cle) {
		this.entite.setCle(cle);
	}
	
	public boolean isApprouve() {
		return entite.getDateApprobation() != null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
		ListeCache listeCache = ListeCache.getInstance();
		description = listeCache.obtenirLabel(subject, getValue(), new SousCategoriesCle(subject));	
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject, String nature) throws BusinessResourceException {
		ListeCache listeCache = ListeCache.getInstance();
		description = listeCache.obtenirLabel(subject, getValue(), new SousCategoriesNatureCle(subject,nature));	
	}
	
	
	public String getDateApprobation() {
		return DateFormat.format(entite.getDateApprobation());
	}

	public String getApprouvePar() {
		return entite.getApprouvePar();
	}

	public void setApprouvePar(String approuvePar) {
		this.entite.setApprouvePar(approuvePar);
	}

	public String getCreerPar() {
		return entite.getCreerPar();
	}

	public void setCreerPar(String creerPar) {
		this.entite.setCreerPar(creerPar);
	}

	public String getDateCreation() {
		return DateFormat.format(entite.getDateCreation());
	}

	public String getLabel() {
		return description;
	}

	public String getValue() {
		return String.valueOf(entite.getCle());
	}

	public long getCle() {
		return entite.getCle();
	}
	
}
