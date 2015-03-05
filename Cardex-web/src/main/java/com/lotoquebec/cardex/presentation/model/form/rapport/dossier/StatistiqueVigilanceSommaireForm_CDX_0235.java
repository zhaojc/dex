package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.StatistiqueVigilanceSommaireGenerateurRapport_CDX_0235;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class StatistiqueVigilanceSommaireForm_CDX_0235 extends CriteresRapportForm{
	
	private HierarchieES hierarchieES = new HierarchieES();

	public StatistiqueVigilanceSommaireForm_CDX_0235() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new StatistiqueVigilanceSommaireGenerateurRapport_CDX_0235();
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
		this.hierarchieES.setEntite(entite);
	}

	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}

	public void setSite(String site) {
		this.hierarchieES.setSiteOrigine(site);
	}

}
