/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller.rapport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.FabriqueBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.rapport.AmbulanceDossierRapportVO;
import com.lotoquebec.cardex.presentation.controller.DossierAction;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.CaracteristiquesForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.AmbulanceDossierRapportForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;


public class AmbulanceRapportAction extends RapportAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

   /**
	 * Produire des rapports
	 * pour la cour d'appel dans une cause concernant le droit d'acc�s � l'information.
	 * Rapports produits pour �tre �ventuellement d�pos�s en cour par les avocats
	 * de Loto-Qu�bec, � la demande du juge.
	 * La principe consiste � rechercher toutes les narrations qui contiennent le
	 * mot "ambulance" et � produire le rapport complet du dossier attach�.
	 * Ce rapport doit �tre le m�me que celui produit par le bouton Imprimer
	 * de la fiche dossier affich�e � l'�cran. Comme on ne veut pas imprimer manuellement
	 * chacun des centaines de dossiers, ce programme va g�n�rer un 
	 * rapport global � l'�cran pr�t pour l'impression. La page utilis�e est 
	 * w_impression_sans_inscription2.jsp_contentieux qu'il faut renommer
	 * w_impression_sans_inscription2.jsp avant d'ex�cuter le rapport.
	 * Un bouton mis en commentaire dans la page tpl_recherche_dossier_formulaire.jsp
	 * permet de lancer l'ex�cution du rapport.
	 */
    public ActionForward imprimer(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.debug("Production du rapport d'ambulance");
		ActionMessages errors = new ActionMessages();

		try {
			// On recherche d'abord les narrations concern�es.
			AmbulanceDossierRapportForm ambulanceDossierRapportForm = (AmbulanceDossierRapportForm) form;
			AmbulanceDossierRapportVO ambulanceDossierRapportVO = new AmbulanceDossierRapportVO();
			ValueObjectMapper.convert(ambulanceDossierRapportForm, ambulanceDossierRapportVO);
			List<Dossier> results = FabriqueBusinessDelegate.getDossierBusinessDelegate().rapportAmbulance(subject, ambulanceDossierRapportVO);
			Collection<DossierForm> currentList = new ArrayList<DossierForm>();
			Iterator<Dossier> it = results.iterator();
			// Il faut ensuite b�tir les rapports � partir des dossiers
			// auquels les narrations sont rattach�es.
			DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();

			while (it.hasNext()) {
				Dossier dossier = it.next();
				dossier = dossierDelegate.find(subject, dossier);

				if (dossier.getConfidentialite() != GlobalConstants.Confidentialite.HUIT) {
					DossierForm dossierForm = new DossierForm();
					ValueObjectMapper.convertDossier(dossier, dossierForm,
							subject.getLocale());
					(new DossierAction()).rechercheLiensDossier(subject, dossier, dossierForm,
							dossierDelegate);
					Iterator iterSujet = dossierForm.getSujets().iterator();
					
					while (iterSujet.hasNext()){
						SujetForm linkSujetForm = (SujetForm) iterSujet.next();
						Sujet linkSujet = new SujetVO();
						ValueObjectMapper.convertSujetHtmlForm(linkSujetForm, linkSujet, subject.getLocale());
						
				        // Recherche des adresses du sujet:
				        Collection adresses = FabriqueBusinessDelegate.getSujetBusinessDelegate().findLiensAdresse(subject, linkSujet);
				        log.debug("Adresses li�es (" + adresses.size() + ") :");
						if (adresses.size() > 0){
				        	Iterator it2 = adresses.iterator();
				        //while (it.hasNext()) { //On ne prend que la premi�re adresse retourn�e
				            Adresse     linkAdresse = (Adresse) it2.next();
				            AdresseForm linkAdresseForm = new AdresseForm();
				            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
				                    subject.getLocale());
				            log.debug(linkAdresse.toString());
				            linkAdresseForm.assignerValeurDeListe( subject );
				            linkSujetForm.addAdresse(linkAdresseForm);
				        //}
						}
						
				        // Recherche des caract�ristiques du sujet:
				        Caracteristiques liensCaracteristiques = FabriqueBusinessDelegate.getSujetBusinessDelegate().findLiensCaracteristique(subject, linkSujet);
				        log.debug("Caracteristiques li�es (" + liensCaracteristiques.getCaracteristiquesChoisis().size() + ") :");
				        CaracteristiquesForm linkCaracteristiquesForm = new CaracteristiquesForm();
				        ValueObjectMapper.convertCaracteristiques(subject, liensCaracteristiques,linkCaracteristiquesForm,subject.getLocale());
				        linkSujetForm.setCaracteristiques(linkCaracteristiquesForm);
					}
					currentList.add(dossierForm);
				}
			}

			request.getSession().setAttribute(
					GlobalConstants.Impression.LOCALE_KEY, subject.getLocale());
			request.getSession().setAttribute("rapportGlobal", currentList);
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		} 
	}
  
}

