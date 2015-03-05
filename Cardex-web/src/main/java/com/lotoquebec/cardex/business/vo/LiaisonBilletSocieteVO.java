package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;


public class LiaisonBilletSocieteVO {

	private long cleLDD = 0;
	private long siteLDD = 0;
    private Dossier dossier = null;
    private Societe societe = null;
    private long role = 0;

    
    public LiaisonBilletSocieteVO(){};

    public LiaisonBilletSocieteVO(long cleLDD, long siteLDD, Dossier dossier, long role, Societe societe) {
		super();
		this.cleLDD = cleLDD;
		this.siteLDD = siteLDD;
		this.dossier = dossier;
		this.role = role;
		this.societe = societe;
	}

	public Dossier getDossier() {
		return dossier;
	}
    
	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
	public Societe getSociete() {
		return societe;
	}
	
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	public long getRole() {
		return role;
	}
	
	public void setRole(long role) {
		this.role = role;
	}

	public long getCleLDD() {
		return cleLDD;
	}

	public void setCleLDD(long cleLDD) {
		this.cleLDD = cleLDD;
	}

	public long getSiteLDD() {
		return siteLDD;
	}

	public void setSiteLDD(long siteLDD) {
		this.siteLDD = siteLDD;
	}
	
	public int hashCode(){
		return 1;
	}
	
	public boolean equals (Object o) {
		
		if (o instanceof LiaisonBilletSocieteVO == false)
			return false;
		LiaisonBilletSocieteVO liaisonBilletSocieteVO = (LiaisonBilletSocieteVO) o;
		
		return getDossier().getCle() == liaisonBilletSocieteVO.getDossier().getCle()
		 && getDossier().getSite() == liaisonBilletSocieteVO.getDossier().getSite()
		 && role == liaisonBilletSocieteVO.getRole()
		 && getSociete().getCle() == liaisonBilletSocieteVO.getSociete().getCle()
		 && getSociete().getSite() == liaisonBilletSocieteVO.getSociete().getSite();		 
	}
	
}