/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.exception.DossierBusinessRuleException;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.integration.dao.DossierDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.StringHelper;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux dossiers.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.15 $, $Date: 2002/04/23 21:09:29 $
 */
public class DossierBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    
    /**
     * Retourne un DossierBusinessRuleException initialis� avec
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
        DossierBusinessRuleException exc =
            new DossierBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

    /**
     * Valide les r�gles d'affaires applicable � un dossier.
     *
     * @param businessObject Le dossier
     * @throws BusinessRuleException
     * @throws BusinessException 
     * @throws DAOException
     *
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof Dossier) {
            Dossier dossier = (Dossier) businessObject;

            try {
            	assignerDonneeValidation(subject, dossier);
            	assignerFormatDescriptif(dossier);
            	
                checkDateDebutRule(dossier);
                checkDateDebutSuperieurDateFinRule(dossier);
                //Inutile de valider les dossiers en confidentialit� 8 qui vont �tre �pur�s
                if(dossier.getConfidentialite() != GlobalConstants.Confidentialite.HUIT){
	                checkNarrationsNonApprouveeDossierInactifRule(dossier);
	            	checkSuivisNonApprouveeDossierInactifRule(dossier);
	            	checkFondeDossierInactifRule(dossier);
	            	checkSousCategorieNonApprouveeDossierInactifRule(dossier);
	            	checkDossierAvecSujetProvisoirInactif(dossier);
	            	checkDossierAvecInscriptionActive(dossier);
	                checkMotDePasseRule(dossier);
	                checkDossierAvecInscriptionRule(dossier);
	                checkDossierAvecJeux(dossier);
	                checkNarrationConclusionDossierInactifRule(dossier);
	                checkDateFinDossierVigilanceInactifRule(dossier);
	                checkAutoexclusionEspacejeux(dossier);
                }
            } catch (DAOException e) {
				e.printStackTrace();
				throw new AssertionError("Probl�me DAO dans DossierBusinessRuleSet");
			}
            
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Dossier.class.getName()
                                               + "'");
        }
    }

    private void assignerFormatDescriptif(Dossier dossier){
    	String descriptif = dossier.getDescriptif();
    	descriptif = descriptif.toUpperCase().trim();
    	descriptif = StringHelper.retirerEspacesBlancs( descriptif );
    	dossier.setDescriptif( descriptif );
    }
    
    private void assignerDonneeValidation(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException{
    	DossierDAO dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
    	// SUIVI
    	Collection linkedSuivis = dossierDAO.findLiensSuivi(subject, dossier);
    	Iterator it = linkedSuivis.iterator();
    	
        while (it.hasNext()){
        	dossier.addSuivi((Suivi)it.next());
        }    	
        
        // JEUX
    	Jeux jeux = dossierDAO.findLiensJeux(subject, dossier);
    	Iterator iterJeux = jeux.getJeuxChoisis().iterator();
    	
        while (iterJeux.hasNext()){
        	dossier.addJeu((String)iterJeux.next());
        }       
    }
    
    /**
     * Dates de d�but sup�rieure ou �gale � 1993-01-01.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkMotDePasseRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkMotDePasseRule()");

        if (dossier.getMotPasse().trim().equals(dossier.getConfirmationMotPasse().trim())) {
        }else {
            throw createException(DossierBusinessRuleException.MOT_DE_PASSE_INVALID);
        }
    }

    /**
     * Dates de d�but sup�rieure ou �gale � 1993-01-01.
     * et non sup�rieur � la date du jour, sauf pour les autoexclusions.
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkDateDebutRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkDateDebutRule()");

        Date date = dossier.getDateDebut();
        if (date == null) {
            return;
        }

        Date now = new Date(System.currentTimeMillis());
        GregorianCalendar gc = new GregorianCalendar(1993, 0, 1);
        Date old = gc.getTime();

        if (date.before(old)) {
            throw createException(DossierBusinessRuleException.DATE_DEBUT_INVALIDE);
        }
        if ((dossier.getCategorie() != Long.parseLong(GlobalConstants.Categorie.AUTOEXCLUSION)) && (date.after(now))){
            throw createException(DossierBusinessRuleException.DATE_DEBUT_INVALIDE);
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
    private void checkDateDebutSuperieurDateFinRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

        if (dossier.getDateDebut() != null && dossier.getDateFin() != null) {
            if (dossier.getDateFin().before(dossier.getDateDebut())) {
                throw createException(DossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            }
        }
    }

    /**
     * Le statut ne peut �tre mis inactif si des narrations
     * n'ont pas encore �t� approuv�s.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des narrations
     * n'ont pas encore �t� approuv�s.
     */
    private void checkNarrationsNonApprouveeDossierInactifRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkNarrationsNonApprouveeDossierInactifRule()");

        String statut = Long.toString(dossier.getStatut());
        
        if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {
            // Dossier inactif, toutes les narrations doivent �tre approuv�es.

            Collection linkedNarrations = dossier.getNarrations();
            Iterator   it = linkedNarrations.iterator();
            while (it.hasNext()) {
                Narration linkedNarration = (Narration) it.next();
                Timestamp dateApprobation = linkedNarration.getDateApprobation();
                if (dateApprobation == null ) {
                    // Narration non approuv�e
                    throw createException(DossierBusinessRuleException.NARRATIONS_NON_APPROUVE_DOSSIER_INACTIF);
                }
            }
        }
    }

    /**
     * Le statut ne peut �tre mis inactif si une narration
     * de conclusion n'a pas �t� cr��e dans un dossier de vigilance.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des narrations
     * n'ont pas encore �t� approuv�s.
     */
    private void checkNarrationConclusionDossierInactifRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkNarrationConclusionDossierInactifRule()");

        String statut = Long.toString(dossier.getStatut());
        Boolean trouve = false;
        
        if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF) && dossier.getCategorie() == GlobalConstants.Categorie.COMITE_VIGILANCE) {
            // Dossier de vigilance inactif, une conclusion doit �tre pr�sente.
            Collection linkedNarrations = dossier.getNarrations();
            Iterator   it = linkedNarrations.iterator();
            while (it.hasNext()) {
                Narration linkedNarration = (Narration) it.next();
                if(linkedNarration.getGabaritUtilise() != 0){
	                Long gabarit = linkedNarration.getGabaritUtilise();
	                if (gabarit == GlobalConstants.GabaritNarration.VIGILANCE_5_FERMETURE_DOSSIER) {
	                	trouve = true;
	                }
                }
            }
            if (!trouve){
                throw createException(DossierBusinessRuleException.MANQUE_CONCLUSION_EVALUATION_DOSSIER_INACTIF);
            }
        }
    }

    /**
     * Le statut ne peut �tre mis inactif si une date
     * de fin n'a pas �t� inscrite dans un dossier de vigilance.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des narrations
     * n'ont pas encore �t� approuv�s.
     */
    private void checkDateFinDossierVigilanceInactifRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkDateFinDossierVigilanceInactifRule()");

        String statut = Long.toString(dossier.getStatut());
        Boolean trouve = false;
        
        if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF) && dossier.getCategorie() == Long.valueOf(GlobalConstants.Categorie.COMITE_VIGILANCE)) {
            if (dossier.getDateFin() == null){
                throw createException(DossierBusinessRuleException.MANQUE_DATE_FIN_EVALUATION_DOSSIER_INACTIF);
            }
        }
    }

    /**
     * Le statut ne peut �tre mis inactif si des suivis
     * n'ont pas encore �t� approuv�s.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des suivis
     * n'ont pas encore �t� approuv�s.
     * @throws DAOException
     */
    private void checkSuivisNonApprouveeDossierInactifRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkSuivisNonApprouveeDossierInactifRule()");

        String statut = Long.toString(dossier.getStatut());
        if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {

            // Dossier inactif, tous les suivis doivent �tre approuv�s.
        	Collection linkedSuivis = dossier.getSuivis();
            Iterator   it = linkedSuivis.iterator();
            while (it.hasNext()) {
                Suivi linkedSuivi = (Suivi) it.next();
                String approbateur = linkedSuivi.getApprobateur();
                if (approbateur == null || approbateur.equals("")) {
                    // Suivi non approuv�
                    throw createException(DossierBusinessRuleException.SUIVIS_NON_APPROUVE_DOSSIER_INACTIF);
                }
            }
        }
    }
    
    /**
     * Un dossier inactif ne peut pas avoir de sous-cat�gories non approuv�es.
     * @param dossier
     * @throws BusinessRuleException
     * @throws BusinessException 
     */
    private void checkSousCategorieNonApprouveeDossierInactifRule(Dossier dossier)
    throws BusinessException {
    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
		log.debug("checkSousCategorieNonApprouveeDossierInactifRule()");
		
		String statut = Long.toString(dossier.getStatut());
		if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {
		    // Dossier inactif, toutes les sous-cat�gories doivent �tre approuv�s.
		    Set<SousCategorieVO> linkedSousCategorie = dossier.getSousCategories();
		    Iterator it = linkedSousCategorie.iterator();
		    
		    while (it.hasNext()) {
		    	SousCategorieVO linkedSousCategorieVO = (SousCategorieVO) it.next();
		    	
		        if (linkedSousCategorieVO.isApprouve() == false) {
		            // Sous cat�gorie non approuv�
	            	businessRuleExceptionHandle.add("cardex_non_approuve" );
	            	throw businessRuleExceptionHandle.getBusinessException();					
		        }
		    }
		}
	}    

    
    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins une inscription.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des suivis
     * n'ont pas encore �t� approuv�s.
     */
    private void checkDossierAvecInscriptionRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkDossierAvecInscriptionRule()");
        //Un dossier avec inscription doit �tre li�
        //� au moins une inscription.
        if (!dossier.isNouveau() && dossier.isInscription()) {
          if (dossier.getInscriptions().size() == 0){
            throw createException(DossierBusinessRuleException.DOSSIER_SANS_INSCRIPTIONS);
          }
          if (dossier.getSujets().size() == 0){
            throw createException(DossierBusinessRuleException.DOSSIER_SANS_SUJETS);
          }
        }
    }
    
    /**
     * Un dossier inactif ne peut pas avoir de sujet avec un statut "Provisoire"
     * @param dossier
     * @throws BusinessRuleException
     * @throws BusinessException 
     */
    private void checkDossierAvecSujetProvisoirInactif(Dossier dossier)
    throws BusinessRuleException, BusinessException {
		log.debug("checkDossierAvecSujetProvisoirInactif()");
		
		String statut = Long.toString(dossier.getStatut());
		if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {
			Iterator iter = dossier.getSujets().iterator();
			
			while (iter.hasNext()) {
				Sujet sujet = (Sujet) iter.next();
				
				if (sujet.getStatut() == Long.valueOf(GlobalConstants.Statut.SUJET_PROVISOIRE).longValue()){
					throw (new BusinessRuleExceptionHandle("cardex_sujet_provisoire")).getBusinessException();
				}
			}
		}
	}
    
    /**
     * Un dossier inactif ne peut pas avoir une inscription active.
     * @param dossier
     * @throws BusinessRuleException
     * @throws BusinessException 
     */
    private void checkDossierAvecInscriptionActive(Dossier dossier)
    throws BusinessRuleException, BusinessException {
		log.debug("checkDossierAvecInscriptionActive()");
		
		String statut = Long.toString(dossier.getStatut());
		if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {
			Iterator iter = dossier.getInscriptions().iterator();
			
			while (iter.hasNext()) {
				Inscription inscription = (Inscription) iter.next();
				
				if (inscription.getStatut() == Long.valueOf(GlobalConstants.Statut.INSCRIPTION_ACTIF).longValue()){
					throw (new BusinessRuleExceptionHandle("cardex_inscription_active")).getBusinessException();
				}
			}
		}
	}
    
    private void checkDossierAvecJeux(Dossier dossier) throws BusinessException {
    	log.debug("checkDossierAvecJeux()");
		//Un dossier d'autoexclusion doit avoir des jeux.
    	
		if (!dossier.isNouveau() 
		&& GlobalConstants.Categorie.AUTOEXCLUSION.equals(String.valueOf(dossier.getCategorie()))) {
		  
			if (dossier.getJeux().size() == 0){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("onglet_jeux_autoexclusion");
            	throw businessRuleExceptionHandle.getBusinessException();					
			}
		}
    }    
    
    /**
     * Un dossier d'autoexclusion d'Espacejeux doit �tre de la cat�gorie Jeux en ligne.
     * @param dossier
     * @throws BusinessRuleException
     * @throws BusinessException 
     */
    private void checkAutoexclusionEspacejeux(Dossier dossier)
    throws BusinessRuleException, BusinessException {
		log.debug("checkAutoexclusionEspacejeux()");
		
		String site = Long.toString(dossier.getSiteOrigine());
		String categorie = Long.toString(dossier.getCategorie());
		if (site.equals(GlobalConstants.SiteMaisonJeux.ESPACEJEUX)) {
			if(categorie.equals(GlobalConstants.Categorie.AUTOEXCLUSION)){
				//throw createException(DossierBusinessRuleException.AUTOEXCLUSION_ESPACEJEUX);
				throw (new BusinessRuleExceptionHandle("autoexclusion_espacejeux")).getBusinessException();
			}
		}
	}

    /**
     * Le statut ne peut �tre mis inactif si le champ Fond� est �
     * Ind�termin� ou � suivre
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si des narrations
     * n'ont pas encore �t� approuv�s.
     */
    private void checkFondeDossierInactifRule(Dossier dossier)
            throws BusinessRuleException {
        log.debug("checkFondeDossierInactifRule()");

        String statut = Long.toString(dossier.getStatut());
        
        if (statut.equals(GlobalConstants.Statut.DOSSIER_INACTIF)) {
            // Dossier inactif, le champ Fond� ne doit pas �tre Ind�termin� ou � suivre.
        	if(dossier.getFonde() == Long.valueOf(GlobalConstants.Fonde.INDETERMINE) || dossier.getFonde() == Long.valueOf(GlobalConstants.Fonde.A_SUIVRE)){
               throw createException(DossierBusinessRuleException.FONDE_DOSSIER_INACTIF);
            }
        }
    }
    
}
