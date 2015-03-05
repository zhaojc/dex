/*
 * Created on 16-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchiePPV;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.model.LienCascade;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.model.RetourFocus;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * @author levassc
 */

public class CriteresRechercheAdressesForm extends ActionForm implements RechercheListeResultat, RetourFocus, CriteresRecherche{

    private LienCascade cascadeES = new HierarchieES();
    private String numeroMunicipal = "";
    private String typeRue  = "";
    private String nomRue = "";
    private String pointCardinal = "";
    private String unite = "";
    private String numeroUnite = "";
    private String adressePostal = "";
    private String telephone = "";
    private LienCascade cascadePPV = new HierarchiePPV();
    private String codePostal = "";
    private String entiteRecherche = GlobalConstants.ChoixRechercheAdresse.SUJET; //Société ou Sujet
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private String nomChampRetourFocus = "";
    private String adresseElectronique = "";
    private String dateCreationDu = "";
    private String dateCreationAu = "";    
    private int sequence = 0;
    
    public void init(CardexUser user){
    	setEntite(String.valueOf(user.getEntite()));
        setSiteOrigine(String.valueOf(user.getSite()));
        numeroMunicipal = "";
        typeRue  = "";
        nomRue = "";
        pointCardinal = "";
        unite = "";
        numeroUnite = "";
        adressePostal = "";
        telephone = "";
        cascadePPV.set(HierarchiePPV.PAYS, GlobalConstants.Adresse.PAYS );
        cascadePPV.set(HierarchiePPV.PROVINCE, GlobalConstants.Adresse.PROVINCE );
        cascadePPV.set(HierarchiePPV.VILLE, "");
        codePostal = "";
        entiteRecherche = GlobalConstants.ChoixRechercheAdresse.SUJET; //Société ou Sujet
        listeResultat.vider();   
        listeResultatAudit.vider();
        adresseElectronique = "";
        dateCreationDu = "";
        dateCreationAu = "";
        genererNumeroSequence();
    }
    
    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
    public void preRecherche() {
    	codePostal = codePostal.toUpperCase();
    }    
    
	public String getAdressePostal() {
		return adressePostal;
	}
	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getEntite() {
		return cascadeES.get(HierarchieES.ENTITE);
	}
	public String getStringEntite() {
		return cascadeES.get(HierarchieES.ENTITE);
	}	
	public void setEntite(String entite) {
		cascadeES.set(HierarchieES.ENTITE, entite);
	}
	public String getEntiteRecherche() {
		return entiteRecherche;
	}
	public void setEntiteRecherche(String entiteRecherche) {
		this.entiteRecherche = entiteRecherche;
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
	public String getNumeroUnite() {
		return numeroUnite;
	}
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	public String getPays() {
		return cascadePPV.get(HierarchiePPV.PAYS);
	}
	public void setPays(String pays) {
		cascadePPV.set(HierarchiePPV.PAYS, pays);
	}
	public String getPointCardinal() {
		return pointCardinal;
	}
	public void setPointCardinal(String pointCardinal) {
		this.pointCardinal = pointCardinal;
	}
	public String getProvince() {
		return cascadePPV.get(HierarchiePPV.PROVINCE);
	}
	public void setProvince(String province) {
		cascadePPV.set(HierarchiePPV.PROVINCE, province);
	}
	public String getSiteOrigine() {
		return cascadeES.get(HierarchieES.SITE_ORIGINE);
	}
	public void setSiteOrigine(String siteOrigine) {
		cascadeES.set(HierarchieES.SITE_ORIGINE, siteOrigine);
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTypeRue() {
		return typeRue;
	}
	public void setTypeRue(String typeRue) {
		this.typeRue = typeRue;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public String getVille() {
		return cascadePPV.get(HierarchiePPV.VILLE);
	}
	public void setVille(String ville) {
		cascadePPV.set(HierarchiePPV.VILLE, ville);
	}
	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	public void setListeResultat(ListeResultat listeResultat) {
		this.listeResultat = listeResultat;
	}
	public void setListeResultat(List listeResultat) {
		this.listeResultat.setResultatComplet( listeResultat );
	}
	public String getNomChampRetourFocus() {
		return nomChampRetourFocus;
	}
	public void setNomChampRetourFocus(String nomChampRetourFocus) {
		this.nomChampRetourFocus = nomChampRetourFocus;
	}
	public String getAdresseElectronique() {
		return adresseElectronique;
	}
	public void setAdresseElectronique(String adresseElectronique) {
		this.adresseElectronique = adresseElectronique;
	}

	public String getDateCreationDu() {
		return dateCreationDu;
	}

	public void setDateCreationDu(String dateCreationDu) {
		this.dateCreationDu = dateCreationDu;
	}

	public String getDateCreationAu() {
		return dateCreationAu;
	}

	public void setDateCreationAu(String dateCreationAu) {
		this.dateCreationAu = dateCreationAu;
	}
	
	public ListeResultat getListeResultatAudit() {
		return listeResultatAudit;
	}
	
	public void setListeResultatAudit(List list) {
		this.listeResultatAudit.setResultatCompletAudit(list);
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}
