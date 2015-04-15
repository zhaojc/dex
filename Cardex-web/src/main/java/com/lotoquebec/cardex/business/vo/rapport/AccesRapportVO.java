package com.lotoquebec.cardex.business.vo.rapport;


public class AccesRapportVO extends CritereRapportVO{

	private String intervenant = "";
	private long site = 0;
	private long groupe = 0;
	private long nombreAcces = 1;
	private String genre = "%";
	private String nature = "%";
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}

	public long getGroupe()
    {
        return groupe;
    }

    public void setGroupe(long groupe)
    {
        this.groupe = groupe;
    }

    /**
	 * @return nombreAcces
	 */
	public long getNombreAcces() {
		return nombreAcces;
	}

	/**
	 * @param nombreAcces nombreAcces � d�finir
	 */
	public void setNombreAcces(long nombreAcces) {
		this.nombreAcces = nombreAcces;
	}

	/**
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre genre � d�finir
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature nature � d�finir
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	
}
