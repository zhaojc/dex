package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.NumeroReperageAutoexclusionDossierGenerateurRapport_CDX_0010;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class NumeroReperageAutoexclusionDossierRapportForm_CDX_0010 extends CriteresRapportForm{

	private static final long serialVersionUID = -8715501993401811455L;
	private HierarchieES hierarchieES = new HierarchieES();
	
	public NumeroReperageAutoexclusionDossierRapportForm_CDX_0010() {
		super(new NumeroReperageAutoexclusionDossierGenerateurRapport_CDX_0010());
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
