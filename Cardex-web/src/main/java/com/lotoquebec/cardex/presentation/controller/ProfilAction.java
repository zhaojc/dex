package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.form.IntervenantForm;
import com.lotoquebec.cardex.presentation.model.form.ProfilsForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.IntervenantVO;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;
import com.lotoquebec.cardexCommun.integration.dao.SecuriteDAO;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class ProfilAction extends AbstractAction {

	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	
	public ProfilAction() throws BusinessResourceException {
		super();
	}

	public ActionForward entrerChoisir(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.debug("S�lection d'un profil");

		try {
			CardexUser cardexUser = (CardexUser) subject.getUser();
	        ProfilsForm profilForm = (ProfilsForm) form;
	        profilForm.setChoixProfil("");
	    	SecuriteDAO securiteDAO = new SecuriteDAO();
	    	List<IntervenantVO> listeProfils = securiteDAO.obtenirListeProfils(cardexUser.getCode());
	    	profilForm.getListeProfils().clear();
	    	
	    	for(IntervenantVO intervenantVO:listeProfils){
	    		IntervenantForm intervenantForm = new IntervenantForm(); 
	    		ValueObjectMapper.convert(intervenantVO, intervenantForm);
	    		profilForm.getListeProfils().add(intervenantForm);
	    	}
	    	request.setAttribute("profils",profilForm);
	    	return mapping.findForward("success");
		} catch (DAOException e) { 
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}
    }
	
	public ActionForward choisir(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.debug("Choix d'un profil");

		try {
	        ProfilsForm profilForm = (ProfilsForm) form;
			CardexUser cardexUser = (CardexUser)subject.getUser();
			//On s'assure que le code retourn� fait partie de la liste des profils de l'utilisateur.
	    	SecuriteDAO securiteDAO = new SecuriteDAO();
	    	List<IntervenantVO> listeProfils = securiteDAO.obtenirListeProfils(cardexUser.getCode());
	    	for(IntervenantVO intervenantVO:listeProfils){
		    	IntervenantForm intervenantForm = new IntervenantForm(); 
		    	ValueObjectMapper.convert(intervenantVO, intervenantForm);
		    	if(intervenantForm.getCode().equals(profilForm.getChoixProfil())){
		    		//On v�rifie �galement que le code parent correspond au code de l'utilisateur authentifi�.
		    		if(intervenantForm.getCodeParent().equals(cardexUser.getCodeParent())){
		    			subject = FabriqueDAO.getInstance().getIntervenantDAO().find(intervenantVO.getCode());
						
				        assignerValeursAuLogon(subject, request);
				        
				    	return mapping.findForward("menu");
		    		}
				}
			}
			return mapping.findForward("error");
		} catch (DAOException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
        }
    }

	public static void assignerValeursAuLogon(CardexAuthenticationSubject subject, HttpServletRequest request){
		CardexUser cardexUser = (CardexUser)subject.getUser();
		
		// Il faut retirer cet attribue, sinon, il gardera les acc�s de son ancien acc�s.
		request.getSession().removeAttribute(GlobalConstants.Securite.SESSION_RACCOURCIT_GESTION_ACCES_SECURITE);
		
        //Traitement de l'affichage des dossiers et de la Galerie
        Map map = new HashMap();
        ListeCache listeCache = ListeCache.getInstance();

		try {
			Collection genres = listeCache.obtenirListe((CardexAuthenticationSubject)subject, new TableValeurCleSQLListeCache((CardexAuthenticationSubject)subject, GlobalConstants.TableValeur.GENRE, cardexUser.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
			
	        Iterator it = genres.iterator();
	        while(it.hasNext()){
	          LabelValueBean labelValueBean = (LabelValueBean)it.next();
	          
	          if (labelValueBean.getValue() != null && labelValueBean.getValue().trim().length() > 0) {
	            Collection natures = listeCache.obtenirListe((CardexAuthenticationSubject)subject, new TableValeurCleSQLListeCache((CardexAuthenticationSubject)subject, GlobalConstants.TableValeur.NATURE, labelValueBean.getValue(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
	            map.put(labelValueBean.getValue(),natures);
	          }
	        }
	        request.getSession().setAttribute(GlobalConstants.Menu.GENRES_RECHERCHE,genres);
	        request.getSession().setAttribute(GlobalConstants.Menu.NATURES_RECHERCHE,map);
	
	        // recherche galerie
	        map = new HashMap();
	        genres = listeCache.obtenirListe((CardexAuthenticationSubject)subject, new TableValeurCleSQLListeCache((CardexAuthenticationSubject)subject, GlobalConstants.TableValeur.GENRE, cardexUser.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_GALERIE));
	        it = genres.iterator();
	        while(it.hasNext()){
	          LabelValueBean labelValueBean = (LabelValueBean)it.next();
	          
	          if (labelValueBean.getValue() != null && labelValueBean.getValue().trim().length() > 0) {
	            Collection natures = listeCache.obtenirListe((CardexAuthenticationSubject)subject, new TableValeurCleSQLListeCache((CardexAuthenticationSubject)subject, GlobalConstants.TableValeur.NATURE, labelValueBean.getValue(), GlobalConstants.ActionSecurite.RECHERCHE_GALERIE));
	            map.put(labelValueBean.getValue(),natures);
	          }
	        }
	        request.getSession().setAttribute(GlobalConstants.Menu.GENRES_GALERIE,genres);
	        request.getSession().setAttribute(GlobalConstants.Menu.NATURES_GALERIE,map);
    
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
        
        request.getSession().setAttribute(AuthenticationSubject.class.getName(),subject);
        request.getSession().setAttribute(Globals.LOCALE_KEY, subject.getLocale());
	}
	
}
