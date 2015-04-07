/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.exception.InscriptionBusinessRuleException;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux dossiers.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/04/04 20:41:06 $
 */
public class InscriptionBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	

    /**
     * Retourne un InscriptionBusinessRuleException initialis� avec
     * l'identificateur de r�gle.
     *
     *
     * @param ruleId
     *
     * @return
     *
     * @see
     */
    protected BusinessRuleException createException(int ruleId) {
        InscriptionBusinessRuleException exc =
            new InscriptionBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

    /**
     * Valide les r�gles d'affaires applicable � un dossier.
     *
     * @param businessObject Le dossier
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet dossier ne sont pas respect�es.
     * @throws BusinessException
     * @throws 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof Inscription) {
            Inscription inscription = (Inscription) businessObject;

			Dossier critere = new DossierVO();
			critere.setCle( inscription.getLien() );
			critere.setSite( inscription.getLienSite() );
			try{
				Dossier dossier = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, critere);
				
	            checkDateDebutRule(inscription);
	            checkDateDebutSuperieurDateFinRule(inscription);
	            validerPeriodeInscriptionAutoExclusion(subject, inscription, dossier);
	            assignerTousSiteApplicable(subject, inscription, dossier);
				
			} catch (DAOException e) {
				e.printStackTrace();
				throw new AssertionError("Probl�me dans validerPeriodeInscriptionAutoExclusion");
			}		
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Inscription.class.getName()
                                               + "'");
        }
    }


    /**
     * On assigne "tous les sites applicables" si c'est un dossier d'"acc�s interdit"
     * ou un "autoexclusion" avec tous les sites de s�lectionn�s.
     * @param subject
     * @param inscription
     * @param dossier
     */
    private void assignerTousSiteApplicable(CardexAuthenticationSubject subject, Inscription inscription, Dossier dossier) {
    	ListeCache listeCache = ListeCache.getInstance();
    	
    	if (isCategorieDansNature( subject, dossier.getCategorie(), GlobalConstants.Nature.ACCES_INTERDIT )
    	|| isCategorieDansNature( subject, dossier.getCategorie(), GlobalConstants.Nature.AVIS_DE_GUET )){
    		inscription.setTousSitesApplicables(true);
    		return;
    	}
		
    	if (GlobalConstants.Categorie.AUTOEXCLUSION.equals( String.valueOf( dossier.getCategorie() ) )){

			try {
				int nbSiteApplicableMax = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.ActionSecurite.MODIFICATION)).size();

				if (inscription.getSitesChoisis().size() >= nbSiteApplicableMax)
	    			inscription.setTousSitesApplicables(true);
			} catch (BusinessResourceException e) {
				e.printStackTrace();
				throw new AssertionError("Probl�me dans assignerTousSiteApplicable");
			}
    	}
	}
    
    private boolean isCategorieDansNature(CardexAuthenticationSubject subject, long categorie, long cleNature){
    	ListeCache listeCache = ListeCache.getInstance();
    	LabelValueBean labelValueBeanCategorie = new LabelValueBean("", String.valueOf(categorie));
    	
    	try {
			Collection listeType = listeCache.obtenirListe(subject, new TypeCleMultiListeCache(subject, cleNature));
			Iterator iterType = listeType.iterator();
			
			while (iterType.hasNext()) {
				LabelValueBean labelValueBeanType = (LabelValueBean) iterType.next();
				
				if (StringUtils.isNotEmpty(labelValueBeanType.getValue())){
					Collection listeCategorie = listeCache.obtenirListe(subject, new CategorieCleMultiListeCache(subject, labelValueBeanType.getValue()));
					
					if (listeCategorie.contains(labelValueBeanCategorie))
						return true;
				}
			}
			
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new AssertionError("Probl�me dans isCategorieDansAccesInterdit");
		}
    	return false;
    }

	/**
     * Dates de d�but sup�rieure ou �gale � 1993-01-01.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkDateDebutRule(Inscription inscription)
            throws BusinessRuleException {
        log.debug("checkDateDebutRule()");

        Date date = inscription.getDateDebut();
        if (date == null) {
            return;
        }

        //Date now = new Date(System.currentTimeMillis());
        GregorianCalendar gc = new GregorianCalendar(1993, 0, 1);
        Date old = gc.getTime();
        if (date.before(old)) {
            throw createException(InscriptionBusinessRuleException.DATE_DEBUT_INVALIDE);
        }
    }

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si les dates de d�but sont
     * inf�rieures ou �gales aux dates de fin.
     */
    private void checkDateDebutSuperieurDateFinRule(Inscription inscription)
            throws BusinessRuleException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

        if (inscription.getDateDebut() != null && inscription.getDateFin() != null) {
            if (inscription.getDateFin().before(inscription.getDateDebut())) {
                throw createException(InscriptionBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            }
        }
    }

    /**
     * La p�riode doit �tre � "fixe" ou "� suivre" dans le cas d'un dossier d'autoexclusion.
     * 2008-10-17 : m�me chose pour les aides initiale et imm�diate
     * @param subject
     * @param inscription
     * @param dossier 
     * @throws BusinessException
     */
    private void validerPeriodeInscriptionAutoExclusion(CardexAuthenticationSubject subject, Inscription inscription, Dossier dossier)
            throws BusinessException {
        log.debug("validerPeriodeInscriptionAutoExclusion()");
    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();

		if (inscription.getLien() != 0  && inscription.getLienSite() != 0
				&& GlobalConstants.Periode.A_SUIVRE_CONSEILLER.equals( String.valueOf( inscription.getPeriode()  ) ) == false
		&& GlobalConstants.Periode.A_SUIVRE.equals( String.valueOf( inscription.getPeriode()  ) ) == false
		&& GlobalConstants.Periode.FIXE.equals( String.valueOf( inscription.getPeriode()  )) == false ){

			if (GlobalConstants.Categorie.AUTOEXCLUSION.equals( String.valueOf( dossier.getCategorie() ) )){
            	businessRuleExceptionHandle.add("cardex_autoexclusion_periode" );
            	throw businessRuleExceptionHandle.getBusinessException();					
			}
      }
    }

}
