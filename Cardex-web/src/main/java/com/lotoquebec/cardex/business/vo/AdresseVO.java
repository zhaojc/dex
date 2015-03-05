package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ProvinceAbreviationCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.UniteAbreviationCle;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/28 17:28:14 $
 * @see com.lotoquebec.cardex.business.Adresse
 */
public class AdresseVO implements Adresse {

    private long cle = 0;
    private long site = 0;
    private String numeroMunicipal = "";
    private long typeRue  = 0;
    private String nomRue = "";
    private long pointCardinal = 0;
    private long unite = 0;
    private String numeroUnite = "";
    private String adressePostal = "";
    private String adresse = "";
    private String adresse2 = "";
    private long typeUtilTelephone1 = 0;
    private String telephone1 = "";
    private long typeUtilTelephone2 = 0;
    private String telephone2 = "";
    private long typeUtilTelephone3 = 0;
    private String telephone3 = "";
    private long pays = 0;
    private String adresseElectronique1 = "";
    private String adresseElectronique2 = "";
    private long province = 0;
    private long statut = 0;
    private long ville = 0;
    private String codePostal = "";
    private String commentaire = "";
    private String createur = "";
    private String modificateur = "";
    private Timestamp dateCreation = null;
    private Timestamp dateModification = null;
    private long lien = 0;
    private long lienSite = 0;
    private String villeDescription = "";
    private String paysDescription = "";
    private String provinceAbreviation = "";
    private String provinceDescription = "";
    private String pointCardinalDescription = "";
    private String typeRueDescription  = "";
    private String uniteDescription  = "";
    private String message = "";
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";
    //Indique si la donnée vient de RDD
	private boolean indicateurRdd = false;
	//Sert à savoir si l'adresse appartient à un employé (classe "LQ et ses filiales", clé 24004, dans une société)
	//Utilisé dans AdresseDAO
	private long classe = 0;
    //Ajout des périodes d'utilisation du téléphone
    private long periodeTelephone1 = 0;
    private long periodeTelephone2 = 0;
    private long periodeTelephone3 = 0;
    
    
    /**
     * Constructeur de AdresseVO par défaut.
     */
    public AdresseVO() {}


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne l'adresse.
     *
     * @return String Valeur de l'adresse en caractère.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Retourne la seconde adresse.
     *
     * @return String Valeur de la seconde adresse en caractère.
     */
    public String getAdresse2() {
        return this.adresse2;
    }

    /**
     * Retourne le premier numéro de téléphone.
     *
     * @return String Valeur du premier numéro de téléphone en caractère.
     */
    public String getTelephone1() {
        return this.telephone1;
    }

    /**
     * Retourne le deuxième numéro de téléphone.
     *
     * @return String Valeur du deuxième numéro de téléphone en caractère.
     */
    public String getTelephone2() {
        return this.telephone2;
    }

    /**
     * Retourne le pays.
     *
     * @return long Valeur du pays.
     */
    public long getPays() {
        return this.pays;
    }

    /**
     * Retourne le troisième numéro de téléphone.
     *
     * @return String Valeur du troisième numéro de téléphone en caractère.
     */
    public String getTelephone3() {
        return this.telephone3;
    }

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince() {
        return this.province;
    }

    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille() {
        return this.ville;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne le code postal.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification() {
        return this.dateModification;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte une adresse.
     *
     * @param adresse Valeur de l'adresse en caractère.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Affecte une seconde adresse.
     *
     * @param adresse2 Valeur de la seconde adresse en caractère.
     */
    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    /**
     * Affecte un premier numéro de téléphone.
     *
     * @param telephone1 Valeur du premier numéro de téléphone en caractère.
     */
    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    /**
     * Affecte un deuxième numéro de téléphone.
     *
     * @param telephone2 Valeur du deuxième numéro de téléphone en caractère.
     */
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays.
     */
    public void setPays(long pays) {
        this.pays = pays;
    }

    /**
     * Affecte un troisième numéro de téléphone.
     *
     * @param telephone3 Valeur du troisième numéro de téléphone en caractère.
     */
    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province) {
        this.province = province;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville) {
        this.ville = ville;
    }

    /**
     * Affecte un code postal.
     *
     * @param codePostal Valeur du code postal en caractère.
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caractère.
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }
    
	public String getAdresseElectronique1() {
		return adresseElectronique1;
	}
	
	public void setAdresseElectronique1(String adresseElectronique1) {
		this.adresseElectronique1 = adresseElectronique1;
	}
	
	public String getAdresseElectronique2() {
		return adresseElectronique2;
	}
	
	public void setAdresseElectronique2(String adresseElectronique2) {
		this.adresseElectronique2 = adresseElectronique2;
	}
	
	public String getAdressePostal() {
		return adressePostal;
	}
	
	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}
	
	public String getNomRue() {
		return nomRue;
	}
	
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	
	public String getNumeroMunicipal() {
		return numeroMunicipal;
	}
	public void setNumeroMunicipal(String numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}
	
	public long getPointCardinal() {
		return pointCardinal;
	}
	
	public void setPointCardinal(long pointCardinal) {
		this.pointCardinal = pointCardinal;
	}
	
	public long getTypeRue() {
		return typeRue;
	}
	
	public void setTypeRue(long typeRue) {
		this.typeRue = typeRue;
	}
	
	public String getNumeroUnite() {
		return numeroUnite;
	}
	
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	
	public long getUnite() {
		return unite;
	}
	
	public void setUnite(long unite) {
		this.unite = unite;
	}
	
	public long getTypeUtilTelephone1() {
		return typeUtilTelephone1;
	}
	public void setTypeUtilTelephone1(long typeUtilTelephone1) {
		this.typeUtilTelephone1 = typeUtilTelephone1;
	}
	public long getTypeUtilTelephone2() {
		return typeUtilTelephone2;
	}
	public void setTypeUtilTelephone2(long typeUtilTelephone2) {
		this.typeUtilTelephone2 = typeUtilTelephone2;
	}
	public long getTypeUtilTelephone3() {
		return typeUtilTelephone3;
	}
	public void setTypeUtilTelephone3(long typeUtilTelephone3) {
		this.typeUtilTelephone3 = typeUtilTelephone3;
	}
	
    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du AdresseVO.
     *
     * @return String Valeur de tout les attributs du AdresseVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[AdresseVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', adresse = '" + adresse);
        stringBuffer.append("', adresse2 = '" + adresse2);
        stringBuffer.append("', telephone1 = '" + telephone1);
        stringBuffer.append("', telephone2 = '" + telephone2);
        stringBuffer.append("', pays = '" + pays);
        stringBuffer.append("', telephone3 = '" + telephone3);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', codePostal = '" + codePostal);
        stringBuffer.append("', commentaire = '" + commentaire);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateCreation = '"
                    + TimestampFormat.format(dateCreation));
        stringBuffer.append("', dateModification = '"
                    + TimestampFormat.format(dateModification));
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
    
	public String getCodePostalAE() {
		return codePostal;
	}

	public String getLigneAdresseAE() {
		String sortie = "";
		
		sortie += formerElementAdresse( getNumeroMunicipal() );
		sortie += formerElementAdresse( getTypeRueDescription() );
		sortie += formerElementAdresse( getNomRue() );
		sortie += formerElementAdresse( obtenirAbreviationPointCardinal() );
		sortie += formerElementAdresse( getUniteDescription() );
		sortie += formerElementAdresse( getNumeroUnite() );
		sortie += formerElementAdresse( getAdressePostal() );
		
		return sortie;

	}
	
	private String obtenirAbreviationPointCardinal(){
		
		if (StringUtils.isNotEmpty(pointCardinalDescription)){
			return String.valueOf(pointCardinalDescription.charAt(0));
		}
		return "";
	}
	
	private static String formerElementAdresse(String element){
		
		if (StringUtils.isNotEmpty( element ))
			return element + " ";
		return "";
	}	

	public String getPaysAE() {
		return paysDescription;
	}

	public String getProvinceAE() {
		return provinceAbreviation;
	}

	public String getVilleAE() {
		return villeDescription;
	}


	public String getVilleDescription() {
		return villeDescription;
	}


	public void setVilleDescription(String villeDescription) {
		this.villeDescription = villeDescription;
	}


	public String getPaysDescription() {
		return paysDescription;
	}


	public void setPaysDescription(String paysDescription) {
		this.paysDescription = paysDescription;
	}


	public String getProvinceDescription() {
		return provinceDescription;
	}


	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}


	public String getPointCardinalDescription() {
		return pointCardinalDescription;
	}


	public void setPointCardinalDescription(String pointCardinalDescription) {
		this.pointCardinalDescription = pointCardinalDescription;
	}


	public String getTypeRueDescription() {
		return typeRueDescription;
	}


	public void setTypeRueDescription(String typeRueDescription) {
		this.typeRueDescription = typeRueDescription;
	}


	public String getProvinceAbreviation() {
		return provinceAbreviation;
	}


	public void setProvinceAbreviation(String provinceAbreviation) {
		this.provinceAbreviation = provinceAbreviation;
	}


	public String getUniteDescription() {
		return uniteDescription;
	}


	public void setUniteDescription(String uniteDescription) {
		this.uniteDescription = uniteDescription;
	}

	
    public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void assignerDescription(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	setVilleDescription( cache.obtenirLabel(subject, getVille(), new VilleCleMultiExterneListeCache(subject, getProvince())) );
    	setPaysDescription( cache.obtenirLabel(subject, getPays(), new PaysCleListeCache(subject)) );
    	setProvinceAbreviation( cache.obtenirLabel(subject, getProvince(), new ProvinceAbreviationCle(subject, getPays())) );
    	setProvinceDescription( cache.obtenirLabel(subject, getProvince(), new ProvinceCleMultiListeCache(subject, getPays())) );
    	setTypeRueDescription( cache.obtenirLabel(subject, getTypeRue(), new TypeRueCleListeCache(subject)) );
    	setPointCardinalDescription( cache.obtenirLabel(subject, getPointCardinal(), new CardinaliteCleListeCache(subject)) );
    	setUniteDescription( cache.obtenirLabel(subject, getUnite(), new UniteAbreviationCle(subject)) );
    }    

	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}

	/**
	 * @param dateChangement dateChangement à définir
	 */
	public void setDateChangement(Timestamp dateChangement) {
		this.dateChangement = dateChangement;
	}

	/**
	 * @return changePar
	 */
	public String getChangePar() {
		return changePar;
	}

	/**
	 * @param changePar changePar à définir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}
	
	/**
	 * @return indicateurRdd
	 */
	public boolean isIndicateurRdd() {
		return indicateurRdd;
	}

	/**
	 * @param indicateurRdd indicateurRdd à définir
	 */
	public void setIndicateurRdd(boolean indicateurRdd) {
		this.indicateurRdd = indicateurRdd;
	}


	public long getClasse() {
		return classe;
	}


	public void setClasse(long classe) {
		this.classe = classe;
	}


	public long getPeriodeTelephone1() {
		return periodeTelephone1;
	}


	public void setPeriodeTelephone1(long periodeTelephone1) {
		this.periodeTelephone1 = periodeTelephone1;
	}


	public long getPeriodeTelephone2() {
		return periodeTelephone2;
	}


	public void setPeriodeTelephone2(long periodeTelephone2) {
		this.periodeTelephone2 = periodeTelephone2;
	}


	public long getPeriodeTelephone3() {
		return periodeTelephone3;
	}


	public void setPeriodeTelephone3(long periodeTelephone3) {
		this.periodeTelephone3 = periodeTelephone3;
	}

}