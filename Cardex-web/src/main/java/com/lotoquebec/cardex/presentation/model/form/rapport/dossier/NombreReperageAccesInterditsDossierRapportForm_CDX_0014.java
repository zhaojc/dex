package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.NombrePlus5ReperageAutoexclusionGenerateurRapport_CDX_0011;
import com.lotoquebec.cardex.generateurRapport.dossier.NombreReperageAccesInterditsGenerateurRapport_CDX_0014;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class NombreReperageAccesInterditsDossierRapportForm_CDX_0014 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	
	public NombreReperageAccesInterditsDossierRapportForm_CDX_0014() {
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
		return new NombreReperageAccesInterditsGenerateurRapport_CDX_0014();
	}
	
}
