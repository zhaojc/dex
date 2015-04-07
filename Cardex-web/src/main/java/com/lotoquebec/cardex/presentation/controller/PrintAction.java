/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.VehiculeBusinessDelegate;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.presentation.controller.util.tri.SousCategorieComparator;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardex.presentation.model.form.CaracteristiquesForm;
import com.lotoquebec.cardex.presentation.model.form.ConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.InscriptionForm;
import com.lotoquebec.cardex.presentation.model.form.JeuxForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.ParticularitesForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SousCategorieForm;
import com.lotoquebec.cardex.presentation.model.form.SuiviForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.UrgenceForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.CodeLangue;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/04/26 16:13:01 $
 */
public class PrintAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton Imprimer 
     * de l'�cran de consultation d'un dossier sans inscription. 
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward choosePrintDossier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Choix d'impression d'un dossier");
        ActionErrors errors = new ActionErrors();
        DossierForm         dossierForm = (DossierForm) form;
        Dossier             dossier = new DossierVO();

        GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER);
        
        try {
	        log.debug("Dossier � imprimer  : " + dossierForm.toString());
	        ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
	        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
	        delegate.validerImpressionDossier(subject, dossier);
	        
	        if (dossierForm.isInscription()) {
	           	return mapping.findForward("choixFormulaire");
	        }else {
                log.debug("Impression d'un dossier sans inscription");
                request.getSession().removeAttribute(GlobalConstants.Impression.DOSSIER_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.SUJET_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.INSCRIPTION_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.ADRESSE_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY);
				request.getSession().removeAttribute(GlobalConstants.Impression.CONSIGNATION_KEY);

                request.getSession().setAttribute(GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

                /// prendre le formulaire et la locale dans le PrintDossierForm
                populatePrintDossierForm(subject.getLocale(),subject, dossier, dossierForm);
                request.getSession().setAttribute(GlobalConstants.Impression.DOSSIER_KEY,dossierForm);
            
                if (!dossierForm.getSujets().isEmpty()) {
                	SujetForm sujetForm = (SujetForm)dossierForm.getSujets().iterator().next();
                	request.getSession().setAttribute(GlobalConstants.Impression.SUJET_KEY,sujetForm);
                	
                	if (!sujetForm.getAdresses().isEmpty()) {
                		AdresseForm adresseForm = (AdresseForm)sujetForm.getAdresses().iterator().next();
                		request.getSession().setAttribute(GlobalConstants.Impression.ADRESSE_KEY,adresseForm);
                	}

                	if (!sujetForm.getPhotos().isEmpty()) {
                		Collection photosSujet = (Collection)sujetForm.getPhotos().iterator().next();
                		
                		if (!photosSujet.isEmpty()) {
                			PhotoForm photoSujetForm = (PhotoForm)photosSujet.iterator().next();
                			request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY,photoSujetForm);
                		}
                	}
                }
                
                if (!dossierForm.getPhotos().isEmpty()) {
                	Collection photos = (Collection)dossierForm.getPhotos().iterator().next();
                	
                	if (!photos.isEmpty()) {
                		PhotoForm photoForm = (PhotoForm)photos.iterator().next();
                		request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_KEY,photoForm);
                	}
                }
                
                if (!dossierForm.getConsignations().isEmpty()) {
                	ConsignationForm consignationForm = (ConsignationForm)dossierForm.getConsignations().iterator().next();
                	request.getSession().setAttribute(GlobalConstants.Impression.CONSIGNATION_KEY,consignationForm);
                }
                unirSocieteParRolePourImpression(dossierForm);
                retirerNarrationRAQImpression(dossierForm);
	        }
          } catch (BusinessResourceException bre) {
              handleBusinessResourceException(bre, errors, request);
              return mapping.findForward("error");
          } catch (BusinessException be) {
              handleBusinessException(be, errors, request);
              return (new ActionForward(mapping.getInput()));
          } catch (ValueObjectMapperException vome) {
              handleValueObjectMapperException(vome, errors, request);
              return mapping.findForward("error");
          }
          return mapping.findForward("sansInscriptions");
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur l'ic�ne historique 
     * de l'�cran de consultation d'un dossier sans inscription. 
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward choosePrintDossierAudit(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Impression historique d'un dossier");
        ActionErrors errors = new ActionErrors();
        DossierForm         dossierForm = (DossierForm) form;
        Dossier             dossier = new DossierVO();

        GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_HISTORIQUE);
        
        try {
	        log.debug("Dossier � imprimer  : " + dossierForm.toString());
	        ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
	        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
	        delegate.validerImpressionDossier(subject, dossier);
	        
	        if (dossierForm.isInscription()) { 
	           	return mapping.findForward("choixFormulaire");
	        }else {
                log.debug("Impression d'un dossier sans inscription");
                request.getSession().removeAttribute(GlobalConstants.Impression.DOSSIER_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.SUJET_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.INSCRIPTION_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.ADRESSE_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY);
				request.getSession().removeAttribute(GlobalConstants.Impression.CONSIGNATION_KEY);

                request.getSession().setAttribute(GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

                /// prendre le formulaire et la locale dans le PrintDossierForm
                populatePrintDossierFormAudit(subject.getLocale(),subject, dossier, dossierForm);
                request.getSession().setAttribute(GlobalConstants.Impression.DOSSIER_KEY,dossierForm);
            
                if (!dossierForm.getSujets().isEmpty()) {
                	SujetForm sujetForm = (SujetForm)dossierForm.getSujets().iterator().next();
                	request.getSession().setAttribute(GlobalConstants.Impression.SUJET_KEY,sujetForm);
                	
                	if (!sujetForm.getAdresses().isEmpty()) {
                		AdresseForm adresseForm = (AdresseForm)sujetForm.getAdresses().iterator().next();
                		request.getSession().setAttribute(GlobalConstants.Impression.ADRESSE_KEY,adresseForm);
                	}

                	if (!sujetForm.getPhotos().isEmpty()) {
                		Collection photosSujet = (Collection)sujetForm.getPhotos().iterator().next();
                		
                		if (!photosSujet.isEmpty()) {
                			PhotoForm photoSujetForm = (PhotoForm)photosSujet.iterator().next();
                			request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY,photoSujetForm);
                		}
                	}
                }
                
                if (!dossierForm.getPhotos().isEmpty()) {
                	Collection photos = (Collection)dossierForm.getPhotos().iterator().next();
                	
                	if (!photos.isEmpty()) {
                		PhotoForm photoForm = (PhotoForm)photos.iterator().next();
                		request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_KEY,photoForm);
                	}
                }
                
                if (!dossierForm.getConsignations().isEmpty()) {
                	ConsignationForm consignationForm = (ConsignationForm)dossierForm.getConsignations().iterator().next();
                	request.getSession().setAttribute(GlobalConstants.Impression.CONSIGNATION_KEY,consignationForm);
                }
                unirSocieteParRolePourImpression(dossierForm);
                retirerNarrationRAQImpression(dossierForm);
	        }
          } catch (BusinessResourceException bre) {
              handleBusinessResourceException(bre, errors, request);
              return mapping.findForward("error");
          } catch (BusinessException be) {
              handleBusinessException(be, errors, request);
              return (new ActionForward(mapping.getInput()));
          } catch (ValueObjectMapperException vome) {
              handleValueObjectMapperException(vome, errors, request);
              return mapping.findForward("error");
          }
          return mapping.findForward("sansInscriptions");
    }

    private void unirSocieteParRolePourImpression(DossierForm dossierForm){
    	List<SocieteForm> societeFormList = new ArrayList<SocieteForm>();
    	Iterator iter = dossierForm.getListeSocietes().getResultatComplet().iterator();
    	
    	while (iter.hasNext()) {
			SocieteForm societeForm = (SocieteForm) iter.next();
			societeForm.getRoles().add(societeForm.getRole());
			int indexSocietePresente = societeFormList.indexOf(societeForm);
			
			if (indexSocietePresente > -1){
				
				if (societeFormList.get(indexSocietePresente).getRoles().contains( societeForm.getRole() ) == false)
					societeFormList.get(indexSocietePresente).getRoles().add( societeForm.getRole() );
			}else
				societeFormList.add(societeForm);
    	}
    	dossierForm.getListeSocietes().setResultatComplet( societeFormList );
    }
    
    /*
     * Il ne faut pas voir les narrations de type RAQ dans l'impression
     * de dossier.
     */
    private void retirerNarrationRAQImpression(DossierForm dossierForm) {
    	Iterator iter = dossierForm.getNarrations().iterator();
    	
    	while (iter.hasNext()) {
			NarrationForm narrationForm = (NarrationForm) iter.next();
			
			if (narrationForm.isRapportActiviteQuotidienne())
				iter.remove();
		}
	}

	/**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton Imprimer 
     * de l'�cran de consultation d'un dossier avec inscription. 
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward printDossier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Impression d'un dossier avec inscription");
        ActionMessages errors = new ActionMessages();
        Locale localeImpression = null;

        try {
            DossierForm         dossierForm = (DossierForm) form;
            Dossier             dossier = new DossierVO();

            request.getSession().removeAttribute(GlobalConstants.Impression.DOSSIER_KEY);
            request.getSession().removeAttribute(GlobalConstants.Impression.SUJET_KEY);
            request.getSession().removeAttribute(GlobalConstants.Impression.INSCRIPTION_KEY);
            request.getSession().removeAttribute(GlobalConstants.Impression.ADRESSE_KEY);
            request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_KEY);
            request.getSession().removeAttribute(GlobalConstants.Impression.SUIVI_KEY);

            if (dossierForm.getLangue() != null && dossierForm.getLangue().trim().length() > 0) {
              localeImpression = CodeLangue.valueOf(Integer.parseInt(dossierForm.getLangue()));
            }else {
              localeImpression = subject.getLocale();
            }
            request.getSession().setAttribute(GlobalConstants.Impression.LOCALE_KEY, localeImpression);

            /// prendre le formulaire et la locale dans le PrintDossierForm

            log.debug("Locale d'impression : " + localeImpression.toString());
            log.debug("Dossier � imprimer  : " + dossierForm.toString());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,localeImpression);
            populatePrintDossierForm(localeImpression,subject, dossier, dossierForm);
            request.getSession().setAttribute(GlobalConstants.Impression.DOSSIER_KEY,dossierForm);
            if (!dossierForm.getSujets().isEmpty()) {
              SujetForm sujetForm = (SujetForm)dossierForm.getSujets().iterator().next();
              request.getSession().setAttribute(GlobalConstants.Impression.SUJET_KEY,sujetForm);
              if (!sujetForm.getAdresses().isEmpty()) {
                AdresseForm adresseForm = (AdresseForm)sujetForm.getAdresses().iterator().next();
                request.getSession().setAttribute(GlobalConstants.Impression.ADRESSE_KEY,adresseForm);
              }
              if (!sujetForm.getPhotos().isEmpty()) {
                Collection photos = (Collection)sujetForm.getPhotos().iterator().next();
                if (!photos.isEmpty()) {
                  PhotoForm photoForm = (PhotoForm)photos.iterator().next();
                  request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_KEY,photoForm);
                }
              }

            }
            if (!dossierForm.getInscriptions().isEmpty()) {
                InscriptionForm inscriptionForm = (InscriptionForm)dossierForm.getInscriptions().iterator().next();
                inscriptionForm.assignerListeSitesChoisis(subject);
                request.getSession().setAttribute(GlobalConstants.Impression.INSCRIPTION_KEY,inscriptionForm);
            }

            if (!dossierForm.getSuivis().isEmpty()) {
                SuiviForm suiviForm = (SuiviForm)dossierForm.getSuivis().iterator().next();
                request.getSession().setAttribute(GlobalConstants.Impression.SUIVI_KEY,suiviForm);
            }

            /* Ne pas retirer de l'information
            if (dossierForm.getDateDebut() != null && dossierForm.getDateDebut().length() > 10) {
              dossierForm.setDateDebut(dossierForm.getDateDebut().substring(0,10));
            }

            if (dossierForm.getDateFin() != null && dossierForm.getDateFin().length() > 10) {
              dossierForm.setDateFin(dossierForm.getDateFin().substring(0,10));
            }*/

            return mapping.findForward(obtenirForward(dossierForm));
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }

    }
    
    /**
     * Imprimer le dossier avec les photo (jpg, gif) en pi�ces jointes.
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward imprimerDossierAvecPhotoDansPiecesJointes(
	CardexAuthenticationSubject subject, ActionMapping mapping,
	ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException {
        log.debug("Impression d'un dossier avec les photos des pi�ces jointes");
		ActionErrors errors = new ActionErrors();
		DossierForm dossierForm = (DossierForm) form;

		GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_PIECES_JOINTES);
		
		try {
			log.debug("Impression d'un dossier sans inscription");
			request.getSession().removeAttribute(
					GlobalConstants.Impression.DOSSIER_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.SUJET_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.INSCRIPTION_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.ADRESSE_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.PHOTO_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.PHOTO_SUJET_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.CONSIGNATION_KEY);

			Dossier dossier = new DossierVO();

			request.getSession().setAttribute(
					GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

			// / prendre le formulaire et la locale dans le PrintDossierForm

			log.debug("Dossier � imprimer  : " + dossierForm.toString());
			ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
					subject.getLocale());

			populatePrintDossierForm(subject.getLocale(), subject, dossier,
					dossierForm);
			request.getSession().setAttribute(
					GlobalConstants.Impression.DOSSIER_KEY, dossierForm);
			if (!dossierForm.getSujets().isEmpty()) {
				SujetForm sujetForm = (SujetForm) dossierForm.getSujets()
						.iterator().next();
				request.getSession().setAttribute(
						GlobalConstants.Impression.SUJET_KEY, sujetForm);
				if (!sujetForm.getAdresses().isEmpty()) {
					AdresseForm adresseForm = (AdresseForm) sujetForm
							.getAdresses().iterator().next();
					request.getSession()
							.setAttribute(
									GlobalConstants.Impression.ADRESSE_KEY,
									adresseForm);
				}

				if (!sujetForm.getPhotos().isEmpty()) {
					Collection photosSujet = (Collection) sujetForm.getPhotos()
							.iterator().next();
					if (!photosSujet.isEmpty()) {
						PhotoForm photoSujetForm = (PhotoForm) photosSujet
								.iterator().next();
						request.getSession().setAttribute(
								GlobalConstants.Impression.PHOTO_SUJET_KEY,
								photoSujetForm);
					}
				}

			}
			if (!dossierForm.getPhotos().isEmpty()) {
				Collection photos = (Collection) dossierForm.getPhotos()
						.iterator().next();
				if (!photos.isEmpty()) {
					PhotoForm photoForm = (PhotoForm) photos.iterator().next();
					request.getSession().setAttribute(
							GlobalConstants.Impression.PHOTO_KEY, photoForm);
				}
			}
			if (!dossierForm.getConsignations().isEmpty()) {
				ConsignationForm consignationForm = (ConsignationForm) dossierForm
						.getConsignations().iterator().next();
				request.getSession().setAttribute(
						GlobalConstants.Impression.CONSIGNATION_KEY,
						consignationForm);
			}
			unirSocieteParRolePourImpression(dossierForm);
			retirerNarrationRAQImpression(dossierForm);
			
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, errors, request);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}    
    
    /**
     * Imprimer le dossier historique avec les photo (jpg, gif) en pi�ces jointes.
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward imprimerDossierAvecPhotoDansPiecesJointesAudit(
	CardexAuthenticationSubject subject, ActionMapping mapping,
	ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException {
        log.debug("Impression historique d'un dossier avec les photos des pi�ces jointes");
		ActionErrors errors = new ActionErrors();
		DossierForm dossierForm = (DossierForm) form;

		GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_PIECES_JOINTES_HISTORIQUE);
		
		try {
			log.debug("Impression historique d'un dossier sans inscription");
			request.getSession().removeAttribute(
					GlobalConstants.Impression.DOSSIER_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.SUJET_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.INSCRIPTION_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.ADRESSE_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.PHOTO_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.PHOTO_SUJET_KEY);
			request.getSession().removeAttribute(
					GlobalConstants.Impression.CONSIGNATION_KEY);

			Dossier dossier = new DossierVO();

			request.getSession().setAttribute(
					GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

			// / prendre le formulaire et la locale dans le PrintDossierForm

			log.debug("Dossier � imprimer  : " + dossierForm.toString());
			ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
					subject.getLocale());

			populatePrintDossierFormAudit(subject.getLocale(), subject, dossier,
					dossierForm);
			request.getSession().setAttribute(
					GlobalConstants.Impression.DOSSIER_KEY, dossierForm);
			if (!dossierForm.getSujets().isEmpty()) {
				SujetForm sujetForm = (SujetForm) dossierForm.getSujets()
						.iterator().next();
				request.getSession().setAttribute(
						GlobalConstants.Impression.SUJET_KEY, sujetForm);
				if (!sujetForm.getAdresses().isEmpty()) {
					AdresseForm adresseForm = (AdresseForm) sujetForm
							.getAdresses().iterator().next();
					request.getSession()
							.setAttribute(
									GlobalConstants.Impression.ADRESSE_KEY,
									adresseForm);
				}

				if (!sujetForm.getPhotos().isEmpty()) {
					Collection photosSujet = (Collection) sujetForm.getPhotos()
							.iterator().next();
					if (!photosSujet.isEmpty()) {
						PhotoForm photoSujetForm = (PhotoForm) photosSujet
								.iterator().next();
						request.getSession().setAttribute(
								GlobalConstants.Impression.PHOTO_SUJET_KEY,
								photoSujetForm);
					}
				}

			}
			if (!dossierForm.getPhotos().isEmpty()) {
				Collection photos = (Collection) dossierForm.getPhotos()
						.iterator().next();
				if (!photos.isEmpty()) {
					PhotoForm photoForm = (PhotoForm) photos.iterator().next();
					request.getSession().setAttribute(
							GlobalConstants.Impression.PHOTO_KEY, photoForm);
				}
			}
			if (!dossierForm.getConsignations().isEmpty()) {
				ConsignationForm consignationForm = (ConsignationForm) dossierForm
						.getConsignations().iterator().next();
				request.getSession().setAttribute(
						GlobalConstants.Impression.CONSIGNATION_KEY,
						consignationForm);
			}
			unirSocieteParRolePourImpression(dossierForm);
			retirerNarrationRAQImpression(dossierForm);
			
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, errors, request);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}    
    
     private String obtenirForward(DossierForm dossierForm){
    	String formulaireKey = dossierForm.getFormulaire();
    	
    	if (GlobalConstants.ChoixImpressionContrat.CONTRAT_AUTOEXCLUSION.equals( formulaireKey )){
    		
            if (!dossierForm.getInscriptions().isEmpty()) {
                InscriptionForm inscriptionForm = (InscriptionForm)dossierForm.getInscriptions().iterator().next();
                //Les contrats bonifi�s ont un contrat diff�rent.
                if (GlobalConstants.Periode.A_SUIVRE.equals( inscriptionForm.getPeriode() )
                && (GlobalConstants.SiteMaisonJeux.MONTREAL.equals( dossierForm.getSite())
                	|| GlobalConstants.SiteMaisonJeux.CENTRE_EVALUATION.equals( dossierForm.getSite())
                	|| GlobalConstants.SiteMaisonJeux.MAISON_JEAN_LAPOINTE.equals( dossierForm.getSite()))){
                    // A_SUIVRE => Contrat bonifi�
                	// SITE DOSSIER = MONTR�AL ou Centre d'�valuation ou Maison Jean-Lapointe
                	return GlobalConstants.ChoixImpressionContrat.CONTRAT_AUTOEXCLUSION_MONTREAL;
                }
                //Pour les jeux en ligne, le contrat est diff�rent.
                if(GlobalConstants.Categorie.AE_ESPACEJEUX.equals( dossierForm.getCategorie())){
                	return GlobalConstants.ChoixImpressionContrat.CONTRAT_AUTOEXCLUSION_ESPACEJEUX;
                }
            }
    	}
    	
    	return formulaireKey;
    }
    
    /**
     * <p>
     * Popule les informations imprimables d'un dossier obtenu dans la base de
     * donn�e dans le DossierForm sp�cifi�s.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populatePrintDossierForm(Locale localeImpression,CardexAuthenticationSubject subject,
                                           Dossier criteria,
                                           DossierForm dossierForm) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException {
        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        Dossier  dossier = delegate.find(subject, criteria);

        log.debug("Dossier trouv�: " + dossier.toString());
        dossierForm.resetOnglets();
        ValueObjectMapper.convertDossier(dossier, dossierForm, localeImpression);

        Collection linked;
        Iterator it;

        // Recherche des liens jeux
        Jeux liensJeux = delegate.findLiensJeux(subject,
                dossier);
        log.debug("Jeux li�s (" + liensJeux.getJeuxChoisis().size() + ") :");
        JeuxForm linkJeuxForm = new JeuxForm();
        ValueObjectMapper.convertJeux(subject, liensJeux,linkJeuxForm,localeImpression);
        log.debug(linkJeuxForm.toString());
        dossierForm.setJeux(linkJeuxForm);

        // Recherche des Sujets:
        linked = delegate.findLiensSujet(subject, dossier);
        log.debug("Sujets li�s (" + linked.size() + ") :");
        it = linked.iterator();
        while (it.hasNext()) {
            Sujet linkSujet = (Sujet) it.next();
            log.debug(linkSujet.getNumeroFiche());
            dossierForm.addSujet(getPrintSujetForm(localeImpression,subject, linkSujet));
        }

        // Recherche des Soci�t�s:
        linked = delegate.findLiensSociete(subject, dossier);
        it = linked.iterator();
        log.debug("Societes li�es (" + linked.size() + ") :");
        while (it.hasNext()) {
            Societe linkSociete = (Societe) it.next();
            dossierForm.addSociete(getPrintSocieteForm(localeImpression,subject, linkSociete));
        }

        // Recherche des V�hicules:
        linked = delegate.findLiensVehicule(subject, dossier);
        log.debug("V�hicules li�s (" + linked.size() + ") :");
        it = linked.iterator();
        while (it.hasNext()) {
            Vehicule linkVehicule = (Vehicule) it.next();
            dossierForm.addVehicule(getPrintVehiculeForm(localeImpression,subject, linkVehicule));
        }

        // Recherche des photos:
        Collection liensPhoto = delegate.findLiensPhoto(subject, dossier);
        log.debug("Photos li�es (" + liensPhoto.size() + ") :");
        it = liensPhoto.iterator();
        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3; i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm,
                        localeImpression);
                sublist.add(linkPhotoForm);
                log.debug(linkPhoto.toString());
              }
            }
            dossierForm.addPhoto(sublist);
        }

        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarrationRapport(subject,
                dossier);
        it = liensNarration.iterator();
        log.debug("Narration li�s (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarration(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        // Recherche des liens suivis
        Collection liensSuivi = delegate.findLiensSuivi(subject,
                dossier);
        it = liensSuivi.iterator();
        log.debug("Suivis li�s (" + liensSuivi.size() + ") :");
        while (it.hasNext()) {
            Suivi     linkSuivi = (Suivi) it.next();
            SuiviForm linkSuiviForm = new SuiviForm();

            ValueObjectMapper.convertSuivi(linkSuivi, linkSuiviForm,
                    localeImpression);
            dossierForm.addSuivi(linkSuiviForm);
            log.debug(linkSuivi.toString());
        }

		// Recherche des liens consignation
		Collection liensConsignation = delegate.findLiensConsignation(subject,
				dossier);
		it = liensConsignation.iterator();
		log.debug("Consignations li�es (" + liensConsignation.size() + ") :");
		while (it.hasNext()) {
			Consignation     linkConsignation = (Consignation) it.next();
			ConsignationForm linkConsignationForm = new ConsignationForm();

			ValueObjectMapper.convertConsignation(linkConsignation, linkConsignationForm,
					localeImpression);
			dossierForm.addConsignation(linkConsignationForm);
			log.debug(linkConsignation.toString());
		}

		// Recherche des liens billet
		Collection liensBillet = delegate.trouverLiensBillet(subject,dossier);
		it = liensBillet.iterator();
		log.debug("billets li�s (" + liensBillet.size() + ") :");
		while (it.hasNext()) {
			BilletVO     linkBillet = (BilletVO) it.next();
			BilletForm linkBilletForm = new BilletForm();

			ValueObjectMapper.convert(linkBillet, linkBilletForm, subject.getLocale());
			dossierForm.addBillet(linkBilletForm);
			log.debug(linkBillet.toString());
		}

		// Recherche des liens urgence
		Collection liensUrgence = delegate.findLiensUrgence(subject,dossier);
		it = liensUrgence.iterator();
		log.debug("urgences li�es (" + liensUrgence.size() + ") :");
		while (it.hasNext()) {
			UrgenceVO     linkUrgence = (UrgenceVO) it.next();
			UrgenceForm linkUrgenceForm = new UrgenceForm();

			ValueObjectMapper.convertUrgence(linkUrgence, linkUrgenceForm, subject.getLocale());
			dossierForm.addUrgence(linkUrgenceForm);
			log.debug(linkUrgence.toString());
		}

		// Recherche des inscriptions:
        Collection liensInscription = delegate.findLiensInscription(subject,
                dossier);
        it = liensInscription.iterator();
        log.debug("Inscriptions li�es (" + liensInscription.size() + ") :");
        while (it.hasNext()) {
            Inscription     linkInscription = (Inscription) it.next();
            InscriptionForm linkInscriptionForm = new InscriptionForm();
            ValueObjectMapper.convertInscription(linkInscription, linkInscriptionForm,
                    localeImpression);
            log.debug(linkInscription.toString());
            dossierForm.addInscription(linkInscriptionForm);
        }

        // Recherche des liens pieces jointes
        Collection liensPieceJointe = delegate.findLiensPieceJointe(subject,
                dossier);
        it = liensPieceJointe.iterator();

        log.debug("PieceJointes li�s (" + liensPieceJointe.size() + ") :");

        while (it.hasNext()) {
            Photo     linkPieceJointe = (Photo) it.next();
            
            PhotoForm linkPieceJointeForm = new PhotoForm();
            ValueObjectMapper.convertPhoto(linkPieceJointe, linkPieceJointeForm,
                    subject.getLocale());
        	dossierForm.addPieceJointe(linkPieceJointeForm);
        }//while
        
        // Recherche des liens SousCat�gories
        SousCategoriesVO sousCategoriesVO = delegate.findLiensSousCategories(subject,
                dossier);
        it = sousCategoriesVO.getSousCategories().iterator();
        log.debug("SousCat�gories li�s (" + sousCategoriesVO.getSousCategories().size() + ") :");
        List listeSousCategorie = new ArrayList();
        
        while (it.hasNext()) {
            SousCategorieVO sousCategorieVO = (SousCategorieVO) it.next();
            SousCategorieForm sousCategorie = new SousCategorieForm(sousCategorieVO);
            sousCategorie.assignerValeurDeListe( subject );
            listeSousCategorie.add(sousCategorie);
        }
        Collections.sort(listeSousCategorie, new SousCategorieComparator());
        dossierForm.setListeSousCategories( listeSousCategorie );
        
/*
 *         Collection liensPhoto = delegate.findLiensPhoto(subject, dossier);
        log.debug("Photos li�es (" + liensPhoto.size() + ") :");
        it = liensPhoto.iterator();
        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3; i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm,
                        localeImpression);
                sublist.add(linkPhotoForm);
                log.debug(linkPhoto.toString());
              }
            }
            dossierForm.addPhoto(sublist);
        }        
 */
        
        
    }

    /**
     * Popule les informations imprimables d'un sujet pour l'impression d'un
     * dossier, incluant les adresses et les caract�ristiques.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    SujetForm getPrintSujetForm(Locale localeImpression, CardexAuthenticationSubject subject, Sujet linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        SujetBusinessDelegate delegate = new SujetBusinessDelegate();
        //On commence d'abord par v�rifier s'il y a des donn�es historiques dans l'audit des changements
        //en fonction de la date de liaison au dossier.
        Sujet sujet = delegate.find(subject, linked);
        SujetForm sujetForm = new SujetForm();
        sujet.setRole(linked.getRole());
        ValueObjectMapper.convertSujet(sujet, sujetForm, localeImpression);

        Iterator it;

        // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhoto(subject,
                sujet);
        it = liensPhoto.iterator();
        log.debug("Photos li�s (" + liensPhoto.size() + ") :");

        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3;i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm, localeImpression);
                //Pour l'impression de la photo des sujets sur les rapports, on doit prendre celle qui est s�lectionn�e.
                if(linkPhotoForm.isSelectionner() || sujet.getLienDateCreation() != null){
                	sublist.add(linkPhotoForm);
                }
                log.debug(linkPhoto.toString());
              }//if
            }//for
            	sujetForm.addPhoto(sublist);
        }//while

        // Recherche des soci�t�s du sujet:
        Collection societes = delegate.findLiensSociete(subject, sujet);
        log.debug("Societes li�es (" + societes.size() + ") :");
		if (societes.size() > 0){
        	it = societes.iterator();
        	while (it.hasNext()) { 
	            Societe     linkSociete = (Societe) it.next();
	            SocieteForm linkSocieteForm = new SocieteForm();
	            ValueObjectMapper.convertSociete(linkSociete, linkSocieteForm,
	                    localeImpression);
	            log.debug(linkSociete.toString());
	            linkSocieteForm.assignerValeurDeListe(subject);
	            sujetForm.addSociete(linkSocieteForm);
        	}
		}
        
		// Recherche des adresses du sujet:
        Collection adresses = delegate.findLiensAdresse(subject, sujet);
        log.debug("Adresses li�es (" + adresses.size() + ") :");
		if (adresses.size() > 0){
        	it = adresses.iterator();
        //while (it.hasNext()) { //On ne prend que la premi�re adresse retourn�e
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    localeImpression);
            log.debug(linkAdresse.toString());
            sujetForm.addAdresse(linkAdresseForm);
        //}
		}
        // Recherche des caract�ristiques du sujet:
        Caracteristiques liensCaracteristiques =
            delegate.findLiensCaracteristique(subject, sujet);
        log.debug("Caracteristiques li�es (" + liensCaracteristiques.getCaracteristiquesChoisis().size() + ") :");
        CaracteristiquesForm linkCaracteristiquesForm = new CaracteristiquesForm();
        ValueObjectMapper.convertCaracteristiques(subject, liensCaracteristiques,linkCaracteristiquesForm,localeImpression);
        sujetForm.setCaracteristiques(linkCaracteristiquesForm);

        return sujetForm;
    }

    /**
     * Popule les informations imprimables d'une soci�t� pour l'impression d'un
     * dossier.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    SocieteForm getPrintSocieteForm(Locale localeImpression, CardexAuthenticationSubject subject, Societe linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
        //Societe societe = delegate.find(subject, linked);
        SocieteForm societeForm = new SocieteForm();
        //societe.setLienDateCreation(linked.getLienDateCreation());
        ValueObjectMapper.convertSociete(linked, societeForm, localeImpression);
        log.debug(societeForm.toString());
       // Recherche des adresses de la soci�t�:
        Iterator it;
        Collection adresses = delegate.findLiensAdresse(subject, linked);
        log.debug("Adresses li�es (" + adresses.size() + ") :");
		if (adresses.size() > 0){
        	it = adresses.iterator();
        //while (it.hasNext()) { //On ne prend que la premi�re adresse retourn�e
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    localeImpression);
            log.debug(linkAdresse.toString());
            societeForm.addAdresse(linkAdresseForm);
        //}
		}
 
        return societeForm;
    }

    VehiculeForm getPrintVehiculeForm(Locale localeImpression,CardexAuthenticationSubject subject, Vehicule linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
        Vehicule vehicule = delegate.find(subject, linked);
        VehiculeForm vehiculeForm = new VehiculeForm();
        ValueObjectMapper.convertVehicule(vehicule, vehiculeForm, localeImpression);
        log.debug(vehiculeForm.toString());
        //Recherche des particularit�s
        Particularites liensParticularites = delegate.findLiensParticularite(subject, vehicule);
        log.debug("Caracteristiques li�es (" + liensParticularites.getParticularitesChoisis().size() + ") :");
        ParticularitesForm linkParticularitesForm = new ParticularitesForm();
        ValueObjectMapper.convertParticularites(subject, liensParticularites,linkParticularitesForm,localeImpression);
        vehiculeForm.setParticularites(linkParticularitesForm);

        return vehiculeForm;
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton 
     * Rapport uniformis� dans la consultation de dossiers. Ce rapport est
     * un format normalis� pour les enqu�tes majeures et repose sur des gabarits 
     * pr�-d�finis dans les narrations.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward imprimerRapport(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Impression du dossier normalis� pour les enqu�tes majeures");
        ActionMessages errors = new ActionMessages();
        DossierForm         dossierForm = (DossierForm) form;
        
        GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_UNIFORMISE);

            try {
                log.debug("Impression d'un dossier d'enqu�te majeure");
                request.getSession().removeAttribute(GlobalConstants.Impression.DOSSIER_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.SUJET_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.INSCRIPTION_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.ADRESSE_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY);
				request.getSession().removeAttribute(GlobalConstants.Impression.CONSIGNATION_KEY);

                Dossier             dossier = new DossierVO();

                request.getSession().setAttribute(GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

                /// prendre le formulaire et la locale dans le PrintDossierForm

                log.debug("Dossier � imprimer  : " + dossierForm.toString());
                ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());

                populatePrintDossierForm(subject.getLocale(),subject, dossier, dossierForm);
                populatePrintDossierUniformeForm(subject.getLocale(),subject, dossier, dossierForm);
            request.getSession().setAttribute(GlobalConstants.Impression.DOSSIER_KEY,dossierForm);
            if (!dossierForm.getSujets().isEmpty()) {
              SujetForm sujetForm = (SujetForm)dossierForm.getSujets().iterator().next();
              request.getSession().setAttribute(GlobalConstants.Impression.SUJET_KEY,sujetForm);
              if (!sujetForm.getAdresses().isEmpty()) {
                AdresseForm adresseForm = (AdresseForm)sujetForm.getAdresses().iterator().next();
                request.getSession().setAttribute(GlobalConstants.Impression.ADRESSE_KEY,adresseForm);
              }

              if (!sujetForm.getPhotos().isEmpty()) {
                Collection photosSujet = (Collection)sujetForm.getPhotos().iterator().next();
                if (!photosSujet.isEmpty()) {
                  PhotoForm photoSujetForm = (PhotoForm)photosSujet.iterator().next();
                  request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY,photoSujetForm);
                }
              }

            }
        if (!dossierForm.getPhotos().isEmpty()) {
                Collection photos = (Collection)dossierForm.getPhotos().iterator().next();
                if (!photos.isEmpty()) {
                  PhotoForm photoForm = (PhotoForm)photos.iterator().next();
                  request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_KEY,photoForm);
                }
            }

			if (!dossierForm.getConsignations().isEmpty()) {
				  ConsignationForm consignationForm = (ConsignationForm)dossierForm.getConsignations().iterator().next();
				  request.getSession().setAttribute(GlobalConstants.Impression.CONSIGNATION_KEY,consignationForm);
			}
              } catch (BusinessResourceException bre) {
                  handleBusinessResourceException(bre, errors, request);
                  return mapping.findForward("error");
              } catch (BusinessException be) {
                  handleBusinessException(be, errors, request);
                  return (new ActionForward(mapping.getInput()));
              } catch (ValueObjectMapperException vome) {
                  handleValueObjectMapperException(vome, errors, request);
                  return mapping.findForward("error");
              }
          return mapping.findForward("success");
	}

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'icone historique pour le rapport
     * uniformis� dans la consultation de dossiers. Ce rapport est
     * un format normalis� pour les enqu�tes majeures et repose sur des gabarits 
     * pr�-d�finis dans les narrations.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward imprimerRapportAudit(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Impression du dossier normalis� et historique pour les enqu�tes majeures");
        ActionMessages errors = new ActionMessages();
        DossierForm         dossierForm = (DossierForm) form;

        GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_UNIFORMISE_HISTORIQUE);
        
            try {
                log.debug("Impression d'un dossier historique d'enqu�te majeure");
                request.getSession().removeAttribute(GlobalConstants.Impression.DOSSIER_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.SUJET_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.INSCRIPTION_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.ADRESSE_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_KEY);
                request.getSession().removeAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY);
				request.getSession().removeAttribute(GlobalConstants.Impression.CONSIGNATION_KEY);

                Dossier             dossier = new DossierVO();

                request.getSession().setAttribute(GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());

                /// prendre le formulaire et la locale dans le PrintDossierForm

                log.debug("Dossier � imprimer  : " + dossierForm.toString());
                ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());

                populatePrintDossierFormAudit(subject.getLocale(),subject, dossier, dossierForm);
                populatePrintDossierUniformeForm(subject.getLocale(),subject, dossier, dossierForm);
            request.getSession().setAttribute(GlobalConstants.Impression.DOSSIER_KEY,dossierForm);
            if (!dossierForm.getSujets().isEmpty()) {
              SujetForm sujetForm = (SujetForm)dossierForm.getSujets().iterator().next();
              request.getSession().setAttribute(GlobalConstants.Impression.SUJET_KEY,sujetForm);
              if (!sujetForm.getAdresses().isEmpty()) {
                AdresseForm adresseForm = (AdresseForm)sujetForm.getAdresses().iterator().next();
                request.getSession().setAttribute(GlobalConstants.Impression.ADRESSE_KEY,adresseForm);
              }

              if (!sujetForm.getPhotos().isEmpty()) {
                Collection photosSujet = (Collection)sujetForm.getPhotos().iterator().next();
                if (!photosSujet.isEmpty()) {
                  PhotoForm photoSujetForm = (PhotoForm)photosSujet.iterator().next();
                  request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_SUJET_KEY,photoSujetForm);
                }
              }

            }
        if (!dossierForm.getPhotos().isEmpty()) {
                Collection photos = (Collection)dossierForm.getPhotos().iterator().next();
                if (!photos.isEmpty()) {
                  PhotoForm photoForm = (PhotoForm)photos.iterator().next();
                  request.getSession().setAttribute(GlobalConstants.Impression.PHOTO_KEY,photoForm);
                }
            }

			if (!dossierForm.getConsignations().isEmpty()) {
				  ConsignationForm consignationForm = (ConsignationForm)dossierForm.getConsignations().iterator().next();
				  request.getSession().setAttribute(GlobalConstants.Impression.CONSIGNATION_KEY,consignationForm);
			}
              } catch (BusinessResourceException bre) {
                  handleBusinessResourceException(bre, errors, request);
                  return mapping.findForward("error");
              } catch (BusinessException be) {
                  handleBusinessException(be, errors, request);
                  return (new ActionForward(mapping.getInput()));
              } catch (ValueObjectMapperException vome) {
                  handleValueObjectMapperException(vome, errors, request);
                  return mapping.findForward("error");
              }
          return mapping.findForward("success");
	}

    /**
     * <p>
     * Popule les informations historiques imprimables d'un dossier obtenu dans la base de
     * donn�e dans le DossierForm sp�cifi�s.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populatePrintDossierFormAudit(Locale localeImpression,CardexAuthenticationSubject subject,
                                           Dossier criteria,
                                           DossierForm dossierForm) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException {
        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        Dossier  dossier = delegate.find(subject, criteria);

        log.debug("Dossier trouv�: " + dossier.toString());
        dossierForm.resetOnglets();
        ValueObjectMapper.convertDossier(dossier, dossierForm, localeImpression);

        Collection linked;
        Iterator it;

        // Recherche des liens jeux
        Jeux liensJeux = delegate.findLiensJeux(subject,
                dossier);
        log.debug("Jeux li�s (" + liensJeux.getJeuxChoisis().size() + ") :");
        JeuxForm linkJeuxForm = new JeuxForm();
        ValueObjectMapper.convertJeux(subject, liensJeux,linkJeuxForm,localeImpression);
        log.debug(linkJeuxForm.toString());
        dossierForm.setJeux(linkJeuxForm);

        // Recherche des Sujets:
        linked = delegate.findLiensSujet(subject, dossier);
        log.debug("Sujets li�s (" + linked.size() + ") :");
        it = linked.iterator();
        while (it.hasNext()) {
            Sujet linkSujet = (Sujet) it.next();
            log.debug(linkSujet.getNumeroFiche());
            dossierForm.addSujet(getPrintSujetFormAudit(localeImpression,subject, linkSujet));
        }

        // Recherche des Soci�t�s:
        linked = delegate.findLiensSociete(subject, dossier);
        it = linked.iterator();
        log.debug("Societes li�es (" + linked.size() + ") :");
        while (it.hasNext()) {
            Societe linkSociete = (Societe) it.next();
            dossierForm.addSociete(getPrintSocieteFormAudit(localeImpression,subject, linkSociete));
        }

        // Recherche des V�hicules:
        linked = delegate.findLiensVehicule(subject, dossier);
        log.debug("V�hicules li�s (" + linked.size() + ") :");
        it = linked.iterator();
        while (it.hasNext()) {
            Vehicule linkVehicule = (Vehicule) it.next();
            dossierForm.addVehicule(getPrintVehiculeFormAudit(localeImpression,subject, linkVehicule));
        }

        // Recherche des photos:
        Collection liensPhoto = delegate.findLiensPhoto(subject, dossier);
        log.debug("Photos li�es (" + liensPhoto.size() + ") :");
        it = liensPhoto.iterator();
        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3; i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm,
                        localeImpression);
                sublist.add(linkPhotoForm);
                log.debug(linkPhoto.toString());
              }
            }
            dossierForm.addPhoto(sublist);
        }

        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarrationRapport(subject,
                dossier);
        it = liensNarration.iterator();
        log.debug("Narration li�s (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarration(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        // Recherche des liens suivis
        Collection liensSuivi = delegate.findLiensSuivi(subject,
                dossier);
        it = liensSuivi.iterator();
        log.debug("Suivis li�s (" + liensSuivi.size() + ") :");
        while (it.hasNext()) {
            Suivi     linkSuivi = (Suivi) it.next();
            SuiviForm linkSuiviForm = new SuiviForm();

            ValueObjectMapper.convertSuivi(linkSuivi, linkSuiviForm,
                    localeImpression);
            dossierForm.addSuivi(linkSuiviForm);
            log.debug(linkSuivi.toString());
        }

		// Recherche des liens consignation
		Collection liensConsignation = delegate.findLiensConsignation(subject,
				dossier);
		it = liensConsignation.iterator();
		log.debug("Consignations li�es (" + liensConsignation.size() + ") :");
		while (it.hasNext()) {
			Consignation     linkConsignation = (Consignation) it.next();
			ConsignationForm linkConsignationForm = new ConsignationForm();

			ValueObjectMapper.convertConsignation(linkConsignation, linkConsignationForm,
					localeImpression);
			dossierForm.addConsignation(linkConsignationForm);
			log.debug(linkConsignation.toString());
		}

		// Recherche des liens billet
		Collection liensBillet = delegate.trouverLiensBillet(subject,dossier);
		it = liensBillet.iterator();
		log.debug("billets li�s (" + liensBillet.size() + ") :");
		while (it.hasNext()) {
			BilletVO     linkBillet = (BilletVO) it.next();
			BilletForm linkBilletForm = new BilletForm();

			ValueObjectMapper.convert(linkBillet, linkBilletForm, subject.getLocale());
			dossierForm.addBillet(linkBilletForm);
			log.debug(linkBillet.toString());
		}

        // Recherche des inscriptions:
        Collection liensInscription = delegate.findLiensInscription(subject,
                dossier);
        it = liensInscription.iterator();
        log.debug("Inscriptions li�es (" + liensInscription.size() + ") :");
        while (it.hasNext()) {
            Inscription     linkInscription = (Inscription) it.next();
            InscriptionForm linkInscriptionForm = new InscriptionForm();
            ValueObjectMapper.convertInscription(linkInscription, linkInscriptionForm,
                    localeImpression);
            log.debug(linkInscription.toString());
            dossierForm.addInscription(linkInscriptionForm);
        }

        // Recherche des liens pieces jointes
        Collection liensPieceJointe = delegate.findLiensPieceJointe(subject,
                dossier);
        it = liensPieceJointe.iterator();

        log.debug("PieceJointes li�s (" + liensPieceJointe.size() + ") :");

        while (it.hasNext()) {
            Photo     linkPieceJointe = (Photo) it.next();
            
            PhotoForm linkPieceJointeForm = new PhotoForm();
            ValueObjectMapper.convertPhoto(linkPieceJointe, linkPieceJointeForm,
                    subject.getLocale());
        	dossierForm.addPieceJointe(linkPieceJointeForm);
        }//while
        
        // Recherche des liens SousCat�gories
        SousCategoriesVO sousCategoriesVO = delegate.findLiensSousCategories(subject,
                dossier);
        it = sousCategoriesVO.getSousCategories().iterator();
        log.debug("SousCat�gories li�s (" + sousCategoriesVO.getSousCategories().size() + ") :");
        List listeSousCategorie = new ArrayList();
        
        while (it.hasNext()) {
            SousCategorieVO sousCategorieVO = (SousCategorieVO) it.next();
            SousCategorieForm sousCategorie = new SousCategorieForm(sousCategorieVO);

            ValueObjectMapper.convert(sousCategorieVO, sousCategorie);
            sousCategorie.assignerValeurDeListe( subject );
            listeSousCategorie.add(sousCategorie);
        }
        Collections.sort(listeSousCategorie, new SousCategorieComparator());
        dossierForm.setListeSousCategories( listeSousCategorie );
        
    }
    /**
     * Popule les informations historiques imprimables d'un sujet pour l'impression d'un
     * dossier, incluant les adresses et les caract�ristiques.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    SujetForm getPrintSujetFormAudit(Locale localeImpression, CardexAuthenticationSubject subject, Sujet linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        SujetBusinessDelegate delegate = new SujetBusinessDelegate();
        //On commence d'abord par v�rifier s'il y a des donn�es historiques dans l'audit des changements
        //en fonction de la date de liaison au dossier.
        Sujet sujet = delegate.findAudit(subject, linked);
        if(sujet.getCle() == 0){//S'il n'y a pas de donn�es historiques, on prend les donn�es actuelles.
        	sujet = delegate.find(subject, linked);
        }
        SujetForm sujetForm = new SujetForm();
        sujet.setRole(linked.getRole());
        sujet.setLienDateCreation(linked.getLienDateCreation());
        ValueObjectMapper.convertSujet(sujet, sujetForm, localeImpression);

        Iterator it;

        // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhotoAudit(subject, sujet);
        it = liensPhoto.iterator();
        log.debug("Photos li�s (" + liensPhoto.size() + ") :");
        Collection sublist = new ArrayList();

              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm, localeImpression);
                	sublist.add(linkPhotoForm);
                log.debug(linkPhoto.toString());
              }else{
            	  //S'il n'y a pas de photo historique, on prend l'actuelle
            	  liensPhoto = delegate.findLiensPhoto(subject, sujet);
                  it = liensPhoto.iterator();
                  log.debug("Photos li�s (" + liensPhoto.size() + ") :");

                  while (it.hasNext()) {
                      for (int i=0; i<3;i++) {
                        if (it.hasNext()) {
                          Photo     linkPhoto = (Photo) it.next();
                          PhotoForm linkPhotoForm = new PhotoForm();
                          ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm, localeImpression);
                          //Pour l'impression de la photo des sujets sur les rapports, on doit prendre celle qui est s�lectionn�e.
                          if(linkPhotoForm.isSelectionner() || sujet.getLienDateCreation() != null){
                          	sublist.add(linkPhotoForm);
                          }
                          log.debug(linkPhoto.toString());
                        }//if
                      }//for
                      	sujetForm.addPhoto(sublist);
                  }//while
            	  
              }
            	sujetForm.addPhoto(sublist);

            	// Recherche des adresses du sujet:
        Collection adresses = delegate.findLiensAdresseAudit(subject, sujet);
        log.debug("Adresses li�es (" + adresses.size() + ") :");
		if (adresses.size() > 0){
        	it = adresses.iterator();
        //On ne prend que la premi�re adresse retourn�e
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    localeImpression);
            log.debug(linkAdresse.toString());
            sujetForm.addAdresse(linkAdresseForm);
		}else{
			//S'il n'y a pas d'adresses ant�rieures, on prend l'adresse actuelle.
			adresses = delegate.findLiensAdresse(subject, sujet);
			if (adresses.size() > 0){
	        	it = adresses.iterator();
	        //On ne prend que la premi�re adresse retourn�e
	            Adresse     linkAdresse = (Adresse) it.next();
	            AdresseForm linkAdresseForm = new AdresseForm();
	            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
	                    localeImpression);
	            log.debug(linkAdresse.toString());
	            sujetForm.addAdresse(linkAdresseForm);
			}
		}
        // Recherche des caract�ristiques du sujet:
        Caracteristiques liensCaracteristiques =
            delegate.findLiensCaracteristique(subject, sujet);
        log.debug("Caracteristiques li�es (" + liensCaracteristiques.getCaracteristiquesChoisis().size() + ") :");
        CaracteristiquesForm linkCaracteristiquesForm = new CaracteristiquesForm();
        ValueObjectMapper.convertCaracteristiques(subject, liensCaracteristiques,linkCaracteristiquesForm,localeImpression);
        sujetForm.setCaracteristiques(linkCaracteristiquesForm);

        return sujetForm;
    }

    /**
     * Popule les informations historiques imprimables d'une soci�t� pour l'impression d'un
     * dossier.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    SocieteForm getPrintSocieteFormAudit(Locale localeImpression, CardexAuthenticationSubject subject, Societe linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
        Societe societe = delegate.findAudit(subject, linked);
        if(societe.getCle() == 0){//S'il n'y a pas de donn�es historiques, on prend les donn�es actuelles.
        	societe = delegate.find(subject, linked);
        }
        SocieteForm societeForm = new SocieteForm();
        societe.setLienDateCreation(linked.getLienDateCreation());
        ValueObjectMapper.convertSociete(societe, societeForm, localeImpression);
        societeForm.setRole(String.valueOf(linked.getRole()));

        log.debug(societeForm.toString());
       // Recherche des adresses de la soci�t�:
        Iterator it;
        Collection adresses = delegate.findLiensAdresseAudit(subject, societe);
        log.debug("Adresses li�es (" + adresses.size() + ") :");
		if (adresses.size() > 0){
        	it = adresses.iterator();
        //On ne prend que la premi�re adresse retourn�e
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    localeImpression);
            log.debug(linkAdresse.toString());
            societeForm.addAdresse(linkAdresseForm);
		}else{
			//S'il n'y a pas d'adresses ant�rieures, on prend l'adresse actuelle.
			adresses = delegate.findLiensAdresse(subject, societe);
			if (adresses.size() > 0){
	        	it = adresses.iterator();
	        //On ne prend que la premi�re adresse retourn�e
	            Adresse     linkAdresse = (Adresse) it.next();
	            AdresseForm linkAdresseForm = new AdresseForm();
	            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
	                    localeImpression);
	            log.debug(linkAdresse.toString());
	            societeForm.addAdresse(linkAdresseForm);
			}
		}
 
        return societeForm;
    }

    VehiculeForm getPrintVehiculeFormAudit(Locale localeImpression,CardexAuthenticationSubject subject, Vehicule linked) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException
    {
        VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
        Vehicule vehicule = delegate.findAudit(subject, linked);
        if(vehicule.getCle() == 0){//S'il n'y a pas de donn�es historiques, on prend les donn�es actuelles.
        	vehicule = delegate.find(subject, linked);
        }
        VehiculeForm vehiculeForm = new VehiculeForm();
        ValueObjectMapper.convertVehicule(vehicule, vehiculeForm, localeImpression);
        vehicule.setLienDateCreation(linked.getLienDateCreation());
        log.debug(vehiculeForm.toString());
        //Recherche des particularit�s
        Particularites liensParticularites = delegate.findLiensParticularite(subject, vehicule);
        log.debug("Caracteristiques li�es (" + liensParticularites.getParticularitesChoisis().size() + ") :");
        ParticularitesForm linkParticularitesForm = new ParticularitesForm();
        ValueObjectMapper.convertParticularites(subject, liensParticularites,linkParticularitesForm,localeImpression);
        vehiculeForm.setParticularites(linkParticularitesForm);

        return vehiculeForm;
    }

    /**
     * <p>
     * Recherche les narrations retenues pour l'impression du rapport uniformis�
     * ainsi que les pi�ces jointes.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populatePrintDossierUniformeForm(Locale localeImpression,CardexAuthenticationSubject subject,
                                           Dossier dossier,
                                           DossierForm dossierForm) throws BusinessResourceException,
                                         BusinessException,
                                         ValueObjectMapperException {

        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        log.debug("Dossier uniforme: " + dossier.toString());
        //dossierForm.resetOnglets();
        //ValueObjectMapper.convertDossier(dossier, dossierForm, localeImpression);

        Collection linked;
        Iterator it;

        // Recherche des liens narration. On effectue un traitement pour chaque section du rapport.
        Collection liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "%titre%");
        it = liensNarration.iterator();
        log.debug("Narration Titre (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationTitre(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Identification%");
        it = liensNarration.iterator();
        log.debug("Narration Identification (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationIdentification(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Table%");
        it = liensNarration.iterator();
        log.debug("Narration Table (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationTable(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Introduction%");
        it = liensNarration.iterator();
        log.debug("Narration Introduction (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationIntroduction(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Enqu�te%");
        it = liensNarration.iterator();
        log.debug("Narration Enquete (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationEnquete(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        
        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Constat%");
        it = liensNarration.iterator();
        log.debug("Narration Constat (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationConstats(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Conclusion%");
        it = liensNarration.iterator();
        log.debug("Narration Conclusion (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationConclusion(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Recommandations%");
        it = liensNarration.iterator();
        log.debug("Narration Recommandation (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationRecommandations(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        liensNarration = delegate.findLiensNarrationRapportUniforme(subject,
                dossier, "Autres%");
        it = liensNarration.iterator();
        log.debug("Narration Autres (" + liensNarration.size() + ") :");
        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    localeImpression);
            dossierForm.addNarrationAutres1(linkNarrationForm);
            log.debug(linkNarration.toString());
        }

        // Recherche des pi�ces jointes:
        /*Collection liensPieceJointe = delegate.findLiensPieceJointe(subject,
                dossier);
        it = liensPieceJointe.iterator();

        log.debug("PieceJointes li�es (" + liensPieceJointe.size() + ") :");

        while (it.hasNext()) {
            Photo     linkPieceJointe = (Photo) it.next();
            PhotoForm linkPieceJointeForm = new PhotoForm();
            ValueObjectMapper.convertPhoto(linkPieceJointe, linkPieceJointeForm,
                    subject.getLocale());
            dossierForm.addPieceJointe(linkPieceJointeForm);
        }*///while

		// Recherche des liens consignation
/*		Collection liensConsignation = delegate.findLiensConsignation(subject,
				dossier);
		it = liensConsignation.iterator();
		log.debug("Consignations li�es (" + liensConsignation.size() + ") :");
		while (it.hasNext()) {
			Consignation     linkConsignation = (Consignation) it.next();
			ConsignationForm linkConsignationForm = new ConsignationForm();

			ValueObjectMapper.convertConsignation(linkConsignation, linkConsignationForm,
					localeImpression);
			dossierForm.addConsignation(linkConsignationForm);
			log.debug(linkConsignation.toString());
		}
*/	}
}
