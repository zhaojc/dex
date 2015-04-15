package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


public class HebdomadaireEspacejeuxDossierRapportForm_CDX_0144 extends CriteresRapportForm{

	private static final long serialVersionUID = 4984721436397378736L;
	private HierarchieES hierarchieES = new HierarchieES();
	
	
	public HebdomadaireEspacejeuxDossierRapportForm_CDX_0144() {
		super(new HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144());
	}
	
	@Override
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        // Valeurs par d�faut
        setEntite(GlobalConstants.Entite.MAISON_JEUX);
        setSite(GlobalConstants.SiteMaisonJeux.ESPACEJEUX);
	}

	public String getEntite() {
		return hierarchieES.getEntite();
	}

	public void setEntite(String entite) {
		this.hierarchieES.setEntite(entite);
	}

	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}

	public void setSite(String site) {
		this.hierarchieES.setSiteOrigine(site);
	}
	
	
	
}
