package com.lotoquebec.cardex.business;

import java.math.BigDecimal;
import java.util.Date;

import com.lotoquebec.cardex.business.vo.DossierVO;
/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un Billet.
 *
 */
public interface Billet
{
    // Getters
    public long getCle();
 
    public long getSite();

    public String getNom();
    
    public String getNumeroControl();
    
    public BigDecimal getValeur();
    
    public Boolean getExtra();

    public Boolean getParticipationTirage();

    public Boolean getExtraGagnant();

    public long getTypeMise();
    
    public Boolean getFormuleGroupe();

    public BigDecimal getMontantLot();
    
    public String getNumeroDetaillantProvenance();
    
    public long getCleSocieteProvenance();
    
    public long getSiteSocieteProvenance();
    
    public Date getDateAchat();
    
    public String getNumeroDetaillantValidation();
    
    public long getCleSocieteValidation();
    
    public long getSiteSocieteValidation();
    
    public Date getDateValidation();
    
    public Date getDateCreation();
    
    public String getNomDetaillantProvenance();

    public String getNomDetaillantValidation();

    public long getLien();

    public long getLienSite();

    public String getLienGenre();

    public String getCreateur();

    public DossierVO getDossierVO();

    public long getTypeLoterie();

    public String getNumeroDetaillantVerification();

    public String getNomDetaillantVerification();

    public long getCleSocieteVerification();

    public Date getDateVerification();

    public String getNumeroDetaillantFautif();

    public long getSiteSocieteVerification();

    public String getNomDetaillantFautif();

    public long getCleSocieteFautif();

    public long getSiteSocieteFautif();

    public Date getDatePaiement();
    
    public BigDecimal getMontantExtra();

    // Setters    
    public void setCle(long cle);
    
    public void setSite(long site);
    
    public void setNom(String nom);
    
    public void setNumeroControl(String numeroControl);
    
    public void setValeur(BigDecimal valeur);

    public void setExtra(Boolean extra);
    
    public void setParticipationTirage(Boolean participationTirage);

    public void setExtraGagnant(Boolean extraGagnant);

    public void setFormuleGroupe(Boolean formuleGroupe);

    public void setTypeMise(long typeMise);
    
    public void setMontantLot(BigDecimal montantLot);
    
    public void setNumeroDetaillantProvenance(String numeroDetaillantProvenance);
    
    public void setCleSocieteProvenance(long cleSocieteProvenance);
    
    public void setSiteSocieteProvenance(long siteSocieteProvenance);
    
    public void setDateAchat(Date dateAchat);
    
    public void setNumeroDetaillantValidation(String numeroDetaillantValidation);
    
    public void setCleSocieteValidation(long cleSocieteValidation);
    
    public void setSiteSocieteValidation(long siteSocieteValidation);
    
    public void setDateValidation(Date dateValidation);
    
    public void setDateCreation(Date dateCreation);
    
    public void setNomDetaillantProvenance(String nomDetaillantProvenance);

    public void setNomDetaillantValidation(String nomDetaillantValidation);

    public void setLien(long lien);

    public void setLienSite(long lienSite);

    public void setLienGenre(String lienGenre);

    public void setCreateur(String createur);
    
    public void setDossierVO(DossierVO dossierVO);

    public void setTypeLoterie(long typeLoterie);

    public void setNumeroDetaillantVerification(String numeroDetaillantVerification);

    public void setNomDetaillantVerification(String nomDetaillantVerification);

    public void setCleSocieteVerification(long cleSocieteVerification);

    public void setSiteSocieteVerification(long siteSocieteVerification);

    public void setDateVerification(Date dateVerification);

    public void setNumeroDetaillantFautif(String numeroDetaillantFautif);

    public void setNomDetaillantFautif(String nomDetaillantFautif);

    public void setCleSocieteFautif(long cleSocieteFautif);

    public void setSiteSocieteFautif(long siteSocieteFautif);

    public void setDatePaiement(Date datePaiement);

    public void setMontantExtra(BigDecimal montantExtra);

    //Indique si la donnée est un EXTRA
    public Boolean isExtra();
    
    //Indique si la donnée est une participation au tirage
    public Boolean isParticipationTirage();
    
    //Indique si la donnée est un EXTRA gagnant
    public Boolean isExtraGagnant();
    
    //Indique si la donnée est une formule groupe
    public Boolean isFormuleGroupe();
      
    //Indique si la donnée possède une société provenance
    public Boolean isPossedeSocieteProvenance();

    //Indique si la donnée possède une société validation
    public Boolean isPossedeSocieteValidation();

    //Indique si la donnée possède une société vérification
    public Boolean isPossedeSocieteVerification();

    //Indique si la donnée possède une société fautive
    public Boolean isPossedeSocieteFautif();

}
