package com.lotoquebec.cardex.business.vo;

import java.util.Date;

import com.lotoquebec.cardexCommun.business.vo.VO;


public class SousCategorieVO implements VO{

	private static final long serialVersionUID = 8183026933738897486L;
    private long cle = 0;
    private long cleDossier;
    private long siteDossier;
	private String creerPar = "";
	private Date dateCreation = null;
	private Date dateApprobation = null;
	private String approuvePar = "";
	
	public SousCategorieVO() {}
	
	@Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cle ^ (cle >>> 32));
        result = prime * result + (int) (cleDossier ^ (cleDossier >>> 32));
        result = prime * result + (int) (siteDossier ^ (siteDossier >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SousCategorieVO other = (SousCategorieVO) obj;
        if (cle != other.cle)
            return false;
        if (cleDossier != other.cleDossier)
            return false;
        if (siteDossier != other.siteDossier)
            return false;
        return true;
    }

    // get / set
	public long getCle() {
		return cle;
	}
	
	public void setSousCategorie(long cle) {
		this.cle = cle;
	}
	
	public Date getDateApprobation() {
		return dateApprobation;
	}

	public void setDateApprobation(Date dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	public String getApprouvePar() {
		return approuvePar;
	}

	public void setApprouvePar(String approuvePar) {
		this.approuvePar = approuvePar;
	}

	public void setCle(long cle) {
		this.cle = cle;
	}

	public Boolean isApprouve() {
		return dateApprobation != null;
	}

	public String getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(String creerPar) {
		this.creerPar = creerPar;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

    public long getCleDossier()
    {
        return cleDossier;
    }

    public void setCleDossier(long cleDossier)
    {
        this.cleDossier = cleDossier;
    }

    public long getSiteDossier()
    {
        return siteDossier;
    }

    public void setSiteDossier(long siteDossier)
    {
        this.siteDossier = siteDossier;
    }
	
}
