package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.StatistiqueVigilanceDetailleGenerateurRapport_CDX_0236;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class StatistiqueVigilanceDetailleForm_CDX_0236 extends CriteresRapportForm{
	
	private static final long serialVersionUID = -1079083400464250512L;
	private HierarchieES hierarchieES = new HierarchieES();

	public StatistiqueVigilanceDetailleForm_CDX_0236() {
		super(new StatistiqueVigilanceDetailleGenerateurRapport_CDX_0236());
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
		this.hierarchieES.setEntite(entite);
	}

	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}

	public void setSite(String site) {
		this.hierarchieES.setSiteOrigine(site);
	}

}
