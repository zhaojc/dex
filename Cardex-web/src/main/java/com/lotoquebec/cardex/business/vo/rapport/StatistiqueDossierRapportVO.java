package com.lotoquebec.cardex.business.vo.rapport;


/**
 * Utiliser par 
 * CDX_0140	Rapport statistique par catégories avec graphique
 * CDX_0141	Rapport statistique par mois avec graphique
 * CDX_0142	Rapport statistique par types avec graphique
 * CDX_0145	Rapport statistique sur le temps consacré
 * CDX_0149	Rapport statistique par endroits regroupés
 * CDX_0250 Rapport détail des visites de clients mystères
 * 
 * @author levassc
 *
 */
public class StatistiqueDossierRapportVO extends RapportVO{

	private long site = 0;
	private long genre = 0;
	private long nature = 0;
	private long type = 0;
	private long fonde = 0;
	private long categorie = 0;
	private String region = ""; //Utilisé dans les rapports de clients mystères
	private String vague = ""; //Utilisé dans les rapports de clients mystères
    private String district = ""; //Utilisé dans les rapports de clients mystères
	private long regroupement = 0; //Utilisé dans le CDX_0149
	
	public long getRegroupement() {
		return regroupement;
	}

	public void setRegroupement(long regroupement) {
		this.regroupement = regroupement;
	}

	/**
	 * @return vague
	 */
	public String getVague() {
		return vague;
	}

	/**
	 * @param vague vague à définir
	 */
	public void setVague(String vague) {
		this.vague = vague;
	}

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
	
	public long getType() {
		return type;
	}
	
	public void setType(long type) {
		this.type = type;
	}
	
	public long getFonde() {
		return fonde;
	}
	
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}

	public long getGenre() {
		return genre;
	}

	public void setGenre(long genre) {
		this.genre = genre;
	}

	/**
	 * @return categorie
	 */
	public long getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie categorie à définir
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region region à définir
	 */
	public void setRegion(String region) {
		this.region = region;
	}

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }
	
}
