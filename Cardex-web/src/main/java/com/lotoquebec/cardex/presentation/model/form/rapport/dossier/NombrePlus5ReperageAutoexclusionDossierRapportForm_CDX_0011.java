package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.NombrePlus5ReperageAutoexclusionGenerateurRapport_CDX_0011;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class NombrePlus5ReperageAutoexclusionDossierRapportForm_CDX_0011 extends CriteresRapportForm{

	private static final long serialVersionUID = -1965823062221893766L;
	private HierarchieES hierarchieES = new HierarchieES();
	
	public NombrePlus5ReperageAutoexclusionDossierRapportForm_CDX_0011() {
		super(new NombrePlus5ReperageAutoexclusionGenerateurRapport_CDX_0011());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
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

}
