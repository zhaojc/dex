package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.CumulatifEspacejeuxDossierGenerateurRapport_CDX_0143;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class CumulatifEspacejeuxDossierRapportForm_CDX_0143 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	
	public CumulatifEspacejeuxDossierRapportForm_CDX_0143() {
		super();
	}
	
	@Override
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        
        // Valeurs par d�faut
        setEntite(GlobalConstants.Entite.MAISON_JEUX);
        setSite(GlobalConstants.SiteMaisonJeux.ESPACEJEUX);
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new CumulatifEspacejeuxDossierGenerateurRapport_CDX_0143();
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
