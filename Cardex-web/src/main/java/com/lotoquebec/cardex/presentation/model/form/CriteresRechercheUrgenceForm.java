package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheUrgenceHtmlForm;
import com.lotoquebec.cardex.presentation.model.UrgenceHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulaire de recherche du
 * service d'urgence.
 *
 * @author $Author: mazzucr $
 *
 * com.lotoquebec.cardex.presentation.model.CriteresRechercheUrgenceHtmlForm
 */
public class CriteresRechercheUrgenceForm extends ValidatorForm
        implements CriteresRechercheUrgenceHtmlForm,RechercheListeResultat {

    private String classeDescription = ""; 
    private String classe = "";
    private String numeroDossier = "";
    private String societe = "";
    private String unite = "";
    private String district = "";
    private String contact = "";
    private String contactPrenom = "";
    private String ville = "";
    private String evenement = "";
    private String fonction = "";
    private String matricule = "";
    private String motif = "";
    private String statut = "";
    private String confidentialite = "";
    private String lienSiteSociete = "";
    private String entite = "";
    private String lienSociete = "";
    private String siteOrigine = "";
    
    private ArrayList urgence = new ArrayList();
    private ListeResultat listeResultat = new ListeResultat();
    private int sequence = 0;

    /**
     * @return the statut
     */
    public String getStatut()
    {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut)
    {
        this.statut = statut;
    }

    /**
     * @return the classe
     */
    public String getClasseDescription()
    {
        return classeDescription;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasseDescription(String classeDescription)
    {
        this.classeDescription = classeDescription;
    }

    /**
     * @return the numeroDossier
     */
    public String getNumeroDossier()
    {
        return numeroDossier;
    }

    /**
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(String numeroDossier)
    {
        this.numeroDossier = numeroDossier;
    }

    /**
     * @return the societe
     */
    public String getSociete()
    {
        return societe;
    }

    /**
     * @param societe the societe to set
     */
    public void setSociete(String societe)
    {
        this.societe = societe;
    }

    /**
     * @return the unite
     */
    public String getUnite()
    {
        return unite;
    }

    /**
     * @param unite the unite to set
     */
    public void setUnite(String unite)
    {
        this.unite = unite;
    }

    /**
     * @return the district
     */
    public String getDistrict()
    {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district)
    {
        this.district = district;
    }

    /**
     * @return the contact
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * @return the numeroFiche
     */
    public String getClasse()
    {
        return classe;
    }

    /**
     * @param numeroFiche the numeroFiche to set
     */
    public void setClasse(String classe)
    {
        this.classe = classe;
    }

    /**
     * @return the contactPrenom
     */
    public String getContactPrenom()
    {
        return contactPrenom;
    }

    /**
     * @param contactPrenom the contactPrenom to set
     */
    public void setContactPrenom(String contactPrenom)
    {
        this.contactPrenom = contactPrenom;
    }

    /**
     * @return the ville
     */
    public String getVille()
    {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville)
    {
        this.ville = ville;
    }

    /**
     * @return the evenement
     */
    public String getEvenement()
    {
        return evenement;
    }

    /**
     * @param evenement the evenement to set
     */
    public void setEvenement(String evenement)
    {
        this.evenement = evenement;
    }

    /**
     * @return the fonction
     */
    public String getFonction()
    {
        return fonction;
    }

    /**
     * @param fonction the fonction to set
     */
    public void setFonction(String fonction)
    {
        this.fonction = fonction;
    }

    /**
     * @return the matricule
     */
    public String getMatricule()
    {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }

    /**
     * @return the confidentialite
     */
    public String getConfidentialite()
    {
        return confidentialite;
    }

    /**
     * @param confidentialite the confidentialite to set
     */
    public void setConfidentialite(String confidentialite)
    {
        this.confidentialite = confidentialite;
    }

    /**
     * @return the lienSiteSociete
     */
    public String getLienSiteSociete()
    {
        return lienSiteSociete;
    }

    /**
     * @param lienSiteSociete the lienSiteSociete to set
     */
    public void setLienSiteSociete(String lienSiteSociete)
    {
        this.lienSiteSociete = lienSiteSociete;
    }

    /**
     * @return the entite
     */
    public String getEntite()
    {
        return entite;
    }

    /**
     * @param entite the entite to set
     */
    public void setEntite(String entite)
    {
        this.entite = entite;
    }

    /**
     * @return the lienSociete
     */
    public String getLienSociete()
    {
        return lienSociete;
    }

    /**
     * @param lienSociete the lienSociete to set
     */
    public void setLienSociete(String lienSociete)
    {
        this.lienSociete = lienSociete;
    }

    /**
     * @return the siteOrigine
     */
    public String getSiteOrigine()
    {
        return siteOrigine;
    }

    /**
     * @param siteOrigine the siteOrigine to set
     */
    public void setSiteOrigine(String siteOrigine)
    {
        this.siteOrigine = siteOrigine;
    }

    /**
     * @param listeResultat the listeResultat to set
     */
    public void setListeResultat(ListeResultat listeResultat)
    {
        this.listeResultat = listeResultat;
    }

    /**
     * @return the urgence
     */
    public Collection getUrgences()
    {
        return urgence;
    }

    /**
     * @param urgence the urgence to set
     */
    public void setUrgences(ArrayList urgence)
    {
        this.urgence = urgence;
    }

    /**
     * @return the listeResultat
     */
    public ListeResultat getListeResultat()
    {
        return listeResultat;
    }

    /**
     * @param listeResultat the listeResultat to set
     */
    public void setListeResultat(List list)
    {
        this.listeResultat.setResultatComplet( list);
    }

    /**
     * @return the sequence
     */
    public int getSequence()
    {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(int sequence)
    {
        this.sequence = sequence;
    }

    /**
     * Constructeur de CriteresRechercheVehiculeForm par défaut.
     */
    public CriteresRechercheUrgenceForm() {}
 
    /**
     * Ajoute un service d'urgence.
     *
     * @param sujet Valeur du service d'urgence à ajouter.
     */
    public void addUrgence(UrgenceHtmlForm urgence) {
        this.urgence.add(urgence);
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheUrgenceForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheUrgenceForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheUrgenceForm : ");
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', societe = '" + societe);
        stringBuffer.append("', lienSociete = '" + lienSociete);
        stringBuffer.append("', contact = '" + contact);
        stringBuffer.append("', contactPrenom = '" + contactPrenom);
        stringBuffer.append("', motif = '" + motif);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', unite = '" + unite);
        stringBuffer.append("', district = '" + district);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', evenement = '" + evenement);
        stringBuffer.append("', fonction = '" + fonction);
        stringBuffer.append("', matricule = '" + matricule);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
    /**
     * Réinitialise tous les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(CardexAuthenticationSubject subject) {
    	
        this.numeroDossier = "";
        this.societe = "";
        this.lienSociete = "";
        this.unite = "";
        this.district = "";
        this.contact = "";
        this.contactPrenom = "";
        this.ville = "";
        this.evenement = "";
        this.fonction = "";
        this.matricule = "";
        this.motif = "";
        this.statut = "";
        this.classeDescription = "";
        this.urgence = new ArrayList();
        genererNumeroSequence();
    }

    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
        sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }

    /**
     * @return the motif
     */
    public String getMotif()
    {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(String motif)
    {
        this.motif = motif;
    }   
}