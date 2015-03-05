package com.lotoquebec.cardexCommun.business.vo;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.business.EntiteCardex;


public class LiaisonEntiteVO implements Serializable {

	private static final long serialVersionUID = 332410198855417815L;
	private long cle = 0;
    private long site = 0;
    private long role = 0;

    private EntiteCardex sourceEntite;
    private EntiteCardex destinationEntite;
    
    public LiaisonEntiteVO() {}

    /**
	 * @param cle
	 * @param site
	 */
	public LiaisonEntiteVO(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}


	// get / set
	
	public long getCle() {
		return cle;
	}

	public void setCle(long cle) {
		this.cle = cle;
	}

	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public EntiteCardex getSourceEntite() {
		return sourceEntite;
	}

	public void setSourceEntite(EntiteCardex sourceEntite) {
		this.sourceEntite = sourceEntite;
	}

	public EntiteCardex getDestinationEntite() {
		return destinationEntite;
	}

	public void setDestinationEntite(EntiteCardex destinationEntite) {
		this.destinationEntite = destinationEntite;
	}

}