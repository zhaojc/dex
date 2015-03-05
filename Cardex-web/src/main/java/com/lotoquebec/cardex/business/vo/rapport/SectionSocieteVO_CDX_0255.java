package com.lotoquebec.cardex.business.vo.rapport;

import java.util.Date;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class SectionSocieteVO_CDX_0255 implements Cloneable{

	private Dossier echantillonDossier;
	private Date derniereMAJDate;
	private boolean annulation;
	private String annulationCommentaire = "";
	private String autreCommentaire = "";
	private String vague;
	private Date changementGestionDetaillantDate;  // Date a laquelle la société a changé de propriétaire

	private VisiteVO_CDX_0255[] visites = new VisiteVO_CDX_0255[5];

	public SectionSocieteVO_CDX_0255() {
		super();
		
		for (int i = 0; i < visites.length; i++)
			visites[i] = new VisiteVO_CDX_0255();
		visites[2].setNbJoursSuspension(15);
		visites[3].setNbJoursSuspension(30);
		visites[4].setNbJoursSuspension(365);
	}

	public void assignerDernierDossier(Date dateDebutDossier){
		
		if (derniereMAJDate == null || derniereMAJDate.before(dateDebutDossier)){
			derniereMAJDate = dateDebutDossier;
		}
	}

	public void ajouterAnnulationCommentaire(String commentaireAnnulation){
		this.annulationCommentaire = ajoutString(this.annulationCommentaire, commentaireAnnulation);
	}
	
	public void ajouterAutreCommentaire(String autreCommentaire){
		this.autreCommentaire = ajoutString(this.autreCommentaire, autreCommentaire);
	}
	
	public String ajoutString(String source, String ajout){
		
		if (StringUtils.isEmpty(ajout))
			return source;
		
		if (StringUtils.isNotEmpty(source))
			source += " / ";
		return source += ajout;
	}
	
	// Si la date de création de la nouvelle adresse est plus récente.
	/*public boolean isAssignerAdresse(Date creationAdresseDate){
		return this.creationAdresseDate == null || this.creationAdresseDate.before(creationAdresseDate);
	}*/

	
	public String getAnnulation(){
		
		if (annulation)
			return "X";
		return "";
	}
	
	@Override
	public SectionSocieteVO_CDX_0255 clone() throws CloneNotSupportedException {
		SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255 = new SectionSocieteVO_CDX_0255();
		Dossier dossier = new DossierVO(this.echantillonDossier.getCle(), this.echantillonDossier.getSite());
		dossier.setNumeroDossier(this.echantillonDossier.getNumeroDossier());
		sectionSocieteVO_CDX_0255.setEchantillonDossier(dossier);
		sectionSocieteVO_CDX_0255.setDerniereMAJDate(this.derniereMAJDate);
		sectionSocieteVO_CDX_0255.setAnnulation(this.annulation);
		sectionSocieteVO_CDX_0255.setAnnulationCommentaire(this.annulationCommentaire);
		sectionSocieteVO_CDX_0255.setAutreCommentaire(this.autreCommentaire);
		sectionSocieteVO_CDX_0255.setVague(this.vague);
		sectionSocieteVO_CDX_0255.setChangementGestionDetaillantDate(this.changementGestionDetaillantDate);
		
		for (int i=0;i<this.visites.length;i++)
			sectionSocieteVO_CDX_0255.getVisites()[i] = this.visites[i].clone();
		
		return sectionSocieteVO_CDX_0255;
	}

	// get / set
	public Date getDerniereMAJDate() {
		return derniereMAJDate;
	}

	public void setDerniereMAJDate(Date derniereMAJDate) {
		this.derniereMAJDate = derniereMAJDate;
	}

	public boolean isAnnulation() {
		return annulation;
	}

	public void setAnnulation(boolean annulation) {
		this.annulation = annulation;
	}

	public String getAnnulationCommentaire() {
		return annulationCommentaire;
	}

	public void setAnnulationCommentaire(String annulationCommentaire) {
		this.annulationCommentaire = annulationCommentaire;
	}

	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}

	public VisiteVO_CDX_0255[] getVisites() {
		return visites;
	}

	public void setVisites(VisiteVO_CDX_0255[] visites) {
		this.visites = visites;
	}

	public String getAutreCommentaire() {
		return autreCommentaire;
	}

	public void setAutreCommentaire(String autreCommentaire) {
		this.autreCommentaire = autreCommentaire;
	}

	public Date getChangementGestionDetaillantDate() {
		return changementGestionDetaillantDate;
	}

	public void setChangementGestionDetaillantDate(
			Date changementGestionDetaillantDate) {
		this.changementGestionDetaillantDate = changementGestionDetaillantDate;
	}

	public Dossier getEchantillonDossier() {
		return echantillonDossier;
	}

	public void setEchantillonDossier(Dossier echantillonDossier) {
		this.echantillonDossier = echantillonDossier;
	}

	
}
