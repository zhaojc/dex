package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.NombreReperageAutoexclusionDossierGenerateurRapport_CDX_0011;
import com.lotoquebec.cardex.generateurRapport.dossier.NumeroReperageAccesInterditsGenerateurRapport_CDX_0012;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class NumeroReperageAccesInterditsDossierRapportForm_CDX_0012 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	
	public NumeroReperageAccesInterditsDossierRapportForm_CDX_0012() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	public String getEntite() {
		return hierarchieES.getEntite();
	}
	public void setEntite(String entite) {
		hierarchieES.setEntite(entite);
	}
	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}
	public void setSite(String siteOrigine) {
		hierarchieES.setSiteOrigine(siteOrigine);
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new NumeroReperageAccesInterditsGenerateurRapport_CDX_0012();
	}
	
}
