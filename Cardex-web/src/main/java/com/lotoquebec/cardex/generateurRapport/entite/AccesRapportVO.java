package com.lotoquebec.cardex.generateurRapport.entite;

import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.ActionAccesCleHardListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteOrigineTableValeurCle;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class AccesRapportVO extends RapportVO<Acces>{

	private String actionDescription;
	private String siteDescription;
	private String utilisateurDescription;
	
	
	public AccesRapportVO(Acces acces) {
		super();
		this.entite = acces;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	actionDescription = cache.obtenirLabel(subject, entite.getAction(), new ActionAccesCleHardListe(subject.getLocale().getLanguage()));
		siteDescription = cache.obtenirLabel(subject, entite.getSite(), new SiteOrigineTableValeurCle(subject, String.valueOf(entite.getSite())));
		utilisateurDescription = cache.obtenirLabel(subject, entite.getUtilisateur(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), entite.getSite()));
	}
	
	// get / set
	public String getActionDescription() {
		return actionDescription;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public String getUtilisateurDescription() {
		return utilisateurDescription;
	}

	public String getDateAcces() {
		return DateFormat.format(entite.getDateAcces(), DateFormat.DATE_FORMAT_AVEC_HEURE);
	}
	
	public String getCle(){
		return String.valueOf( entite.getCle() );
	}
}
