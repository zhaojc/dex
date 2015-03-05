package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.Collections;
import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.util.comparator.DateDebutDossierComparator;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Les dossiers de vente aux mineurs, sauf dossier catégorie Échantillon ou 
 * Visite non complété ou Gestion nouveau détaillant, doivent être ajouté 
 * dans un ordre cohérent.  Par exemple, Infraction 1, suivi de infraction 2, 
 * suivi de conformité 3.  Les ordres valides sont:
 * - Un nouveau dossier Échantillon ou Visite non complétée ou Gestion nouveau détaillant
 * - Ancien dossier Echantillon ou Visite non completée ou Gestion nouveau 
 * détaillant et suivi de Infraction 1 ou conformité 1
 * - Il faut permettre la rotation des détaillants.  Ancien dossier conformité 1 
 * ou conformité 2 ou conformité 3 ou conformité 4 ou conformité 5 ou infraction 5 
 * et suivi de Infraction 1 ou conformité 1 
 * - Ancien dossier Infraction 1 suivi de Infraction 2 ou conformité 2
 * - Ancien dossier Infraction 2 suivi de Infraction 3 ou conformité 3
 * - Ancien dossier Infraction 3 suivi de Infraction 4 ou conformité 4
 * - Ancien dossier Infraction 4 suivi de Infraction 5 ou conformité 5
 * Le reste des possibilités sont non valide
 * Il faut permetre a la société d'avoir d'autre type de dossier, mais l'ordre des Vente aux mineurs doit être préservé
 * 
 * @author levassc
 *
 */
public abstract class OrdreDossierClientMystereValidateurRV0004 implements Validateur<Dossier> {

	protected CardexAuthenticationSubject subject;
	protected Dossier origineDossier;
	protected Dossier suivantDossier;
	protected Dossier precedentDossier;
	
	protected abstract void assignerDossiers(List<Dossier> dossiers) throws DAOException;
	
	public OrdreDossierClientMystereValidateurRV0004(CardexAuthenticationSubject subject, Dossier dossierOrigine) {
		super();
		this.subject = subject;
		this.origineDossier = dossierOrigine;
	}
	
	public boolean isValide(Dossier t) throws BusinessException, DAOException {
		
        if(origineDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)){
    		Societe societe = obtenirSocieteRDD();
    		
    		if (societe != null){
	    		List<Dossier> dossiers = (List<Dossier>) FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensDossier(subject, societe);
	    		assignerDossiers(dossiers);
	
				if (societe != null && isCategorieValide() == false)
	                throw new BusinessException( getMessage() );
    		}
        }
     
        return true;
	}

	protected Societe obtenirSocieteRDD() throws DAOException{
		List<Societe> societes = (List<Societe>) FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSociete(subject, origineDossier);
		
		if (societes.size() == 1)
			return societes.get(0);
		return null;
	}
	
	private boolean isCategorieValide(){
		
		// si un des deux dossiers est null, c'est une combinaison valide
		if (precedentDossier == null || suivantDossier == null)
			return true;
		
		long categoriePrecedentDossier = precedentDossier.getCategorie();
		long categorieSuivantDossier = suivantDossier.getCategorie();
		
		if (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.ECHANTILLON
		|| categorieSuivantDossier == GlobalConstants.CategorieClientMystere.RETRAIT_PROCESSUS
		|| categorieSuivantDossier == GlobalConstants.CategorieClientMystere.GESTION_DETAILLANT)
			return true;
		
		if ((categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.ECHANTILLON
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.RETRAIT_PROCESSUS
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.GESTION_DETAILLANT
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_1
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_2
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_3
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_4
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_5
		 || categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5
		 )
		&& (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1
		 || categorieSuivantDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_1))
			return true;
		
        if (categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1
        && (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2
         || categorieSuivantDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_2))
            return true;
        
        if (categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2
        && (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3
         || categorieSuivantDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_3))
            return true;
				
        if (categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3
        && (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 
         || categorieSuivantDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_4))
            return true;
				
        if (categoriePrecedentDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4
        && (categorieSuivantDossier == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 
         || categorieSuivantDossier == GlobalConstants.CategorieClientMystere.CONFORME_VISITE_5))
            return true;

		return false;
	}
	
	protected Dossier obtenirPrecedentDossier(List<Dossier> dossiers) throws DAOException {
		Dossier precedentDossier = null;
		
		Collections.sort(dossiers, new DateDebutDossierComparator());
		
		for(Dossier dossier:dossiers){
			
			if (dossier.equals(origineDossier))
				break;
			
			// ne prendre que les dossiers de vente aux mineurs
			if (isDossierAccepte(dossier))
				precedentDossier = dossier;
		}
		return precedentDossier;
	}
	
	protected Dossier obtenirSuivantDossier(List<Dossier> dossiers) throws DAOException {
		boolean prendreSuivant = false;
		Collections.sort(dossiers, new DateDebutDossierComparator());
		
		for(Dossier dossier:dossiers){

			if (prendreSuivant && isDossierAccepte(dossier))
				return dossier;
			
			if (dossier.equals(origineDossier))
				prendreSuivant = true;
		}
		return null;
	}

	// ne prendre que les dossiers de vente aux mineurs
	protected boolean isDossierAccepte(Dossier dossier){
		return dossier.getConfidentialite() != GlobalConstants.Confidentialite.HUIT 
		&& dossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
		&& dossier.getType() == Long.valueOf(GlobalConstants.Type.VENTE_MINEUR);
	}
	
	public BusinessMessage getMessage() throws BusinessResourceException {
		ListeCache cache = ListeCache.getInstance();
        String categorieDescription1 = cache.obtenirLabel(subject, precedentDossier.getCategorie(), new CategorieCleMultiListeCache(subject, GlobalConstants.Type.VENTE_MINEUR));
        String categorieDescription2 = cache.obtenirLabel(subject, suivantDossier.getCategorie(), new CategorieCleMultiListeCache(subject, GlobalConstants.Type.VENTE_MINEUR));
		return new BusinessMessage("ME0004", categorieDescription1, categorieDescription2); //La catégorie du dossier «{0}» n'est pas cohérente avec la dernière visite «{1}» pour ce détaillant.
	}

}
