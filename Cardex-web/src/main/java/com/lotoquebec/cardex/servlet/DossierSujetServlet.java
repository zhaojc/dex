package com.lotoquebec.cardex.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.FondeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.NatureCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.servlet.TableDynamiqueServlet;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Ce servlet sert à afficher les dossiers liers au sujet à partir
 * d'un script ajax
 */
public class DossierSujetServlet extends TableDynamiqueServlet {

    protected List obtenirListeForm(String sCle, String sSite) throws BusinessResourceException, BusinessException, ValueObjectMapperException{
    	List listDossier = new ArrayList();
        SujetBusinessDelegate delegate = new SujetBusinessDelegate();
        
        Sujet sujet = constuireSujet(sCle, sSite);
        
        Collection liensDossier = delegate.findLiensDossier(subject, sujet);
        Iterator iter = liensDossier.iterator();

        while (iter.hasNext()) {
            Dossier     linkDossier = (Dossier) iter.next();
            DossierForm linkDossierForm = new DossierForm();
            ValueObjectMapper.convertDossier(linkDossier, linkDossierForm,
                    subject.getLocale());
            linkDossierForm.assignerValeurDeListe(subject);
        	listDossier.add( linkDossierForm );
        }
        
        return listDossier;
    }	
	
    protected String construireRanger(ListeCache cache, Object o) throws BusinessResourceException {
		String html = "";
		DossierHtmlForm dossierForm = (DossierHtmlForm) o;
		html += construireTD( "&nbsp;" + dossierForm.getNumeroDossier() );
		html += construireTD( dossierForm.getNumeroCardex().getAffichage() );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getRole(), new RoleCleListeCache(subject)) );
		html += construireTD( "&nbsp;" + cache.obtenirLabel(subject, dossierForm.getSeverite(), new SeveriteCleListeCache(subject)) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getConfidentialite(), new ConfidentialiteCleListeCache(subject)) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getNature(), new NatureCleMultiListeCache(subject, dossierForm.getGenre())) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getType(), new TypeCleMultiListeCache(subject, dossierForm.getNature())) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getCategorie(), new CategorieCleMultiListeCache(subject, dossierForm.getType())) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER)) );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getFonde(), new FondeCleListeCache(subject)) );
		html += construireTD( dossierForm.getDateDebutLeft(10) );
		html += construireTD( "&nbsp;" + dossierForm.getDateFin10() );
		html += construireTD( cache.obtenirLabel(subject, dossierForm.getIntervenant(), new IntervenantCle(subject)) );

		return html;
	}


    protected String construireEntete(MessageResources messageResources) {
		String html = "";
		html += "<TR>";
		html += construireTDEntete( messageResources, "v_do_ancienne_reference");
		html += construireTDEntete( messageResources, "v_do_numero_dossier");
		html += construireTDEntete( messageResources, DossierOngletTrieListe.CLE_ROLE);
		html += construireTDEntete( messageResources, "i_sev_t");
		html += construireTDEntete( messageResources, "i_cc_commentaire_t");
		html += construireTDEntete( messageResources, "i_nature_t");
		html += construireTDEntete( messageResources, "i_type_t");
		html += construireTDEntete( messageResources, "tabpage_categorie");
		html += construireTDEntete( messageResources, "tabpage_statut");
		html += construireTDEntete( messageResources, "tabpage_fonde");
		html += construireTDEntete( messageResources, "d_is_date_debut_t");
		html += construireTDEntete( messageResources, "d_is_date_fin_t");
		html += construireTDEntete( messageResources, "v_do_assigne_a");
		html += "</TR>";
    	
		return html;
	}
    
	private Sujet constuireSujet(String sCle, String sSite) {
		Sujet sujet = new SujetVO();
        long cle = Long.valueOf( sCle ).longValue();
        long site = Long.valueOf( sSite ).longValue();
        sujet.setCle( cle );
        sujet.setSite( site);
        
		return sujet;
	}
}