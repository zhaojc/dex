package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class HebdomadaireEspacejeuxDossierRapportForm_CDX_0144 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	
	
	public HebdomadaireEspacejeuxDossierRapportForm_CDX_0144() {
		super();
	}
	
	@Override
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        // Valeurs par défaut
        setEntite(GlobalConstants.Entite.MAISON_JEUX);
        setSite(GlobalConstants.SiteMaisonJeux.ESPACEJEUX);
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144();
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
