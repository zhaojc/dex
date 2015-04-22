package com.lotoquebec.cardex.generateurRapport.entite;

import java.util.Date;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;

public class ActiviteQuotidienneRapportVO extends CritereRapportVO{

	private static final long serialVersionUID = 8287230589591322268L;
	private long site;	
	private long nature;
	
	public ActiviteQuotidienneRapportVO() {
		super();
	}

	public ActiviteQuotidienneRapportVO(Date dateDebutDu, Date dateDebutAu) {
		super();
		this.dateDebutDu = dateDebutDu;
		this.dateDebutAu = dateDebutAu;
	}

	// get / set
	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}

	public long getNature() {
		return nature;
	}

	public void setNature(long nature) {
		this.nature = nature;
	}
	

}
