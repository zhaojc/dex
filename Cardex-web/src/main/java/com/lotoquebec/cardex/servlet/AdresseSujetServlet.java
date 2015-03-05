package com.lotoquebec.cardex.servlet;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.AdresseOngletTrieListe;
import com.lotoquebec.cardex.presentation.taglib.html.AffichageAdresse;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.servlet.TableDynamiqueServlet;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Ce servlet sert à afficher les adresses d'un sujet à partir
 * d'un script ajax 
 */
public class AdresseSujetServlet extends TableDynamiqueServlet {
	
    protected List obtenirListeForm(String sCle, String sSite) throws BusinessResourceException, BusinessException, ValueObjectMapperException{
    	ListeResultat listAdresse = new ListeResultat();
        SujetBusinessDelegate delegate = new SujetBusinessDelegate();
        
        Sujet sujet = constuireSujet(sCle, sSite);
        
        Collection liensAdresse = delegate.findLiensAdresse(subject, sujet);
        Iterator iter = liensAdresse.iterator();

        while (iter.hasNext()) {
            Adresse     linkAdresse = (Adresse) iter.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    subject.getLocale());
            listAdresse.add( linkAdresseForm );
        }
        listAdresse.assignerTrierDefault(AdresseOngletTrieListe.CLE_DATE_CREATION, true, new AdresseOngletTrieListe());
        
        return listAdresse.getResultatComplet();
    }	
	
    protected String construireRanger(ListeCache cache, Object o) throws BusinessResourceException {
		String html = "";
		AdresseHtmlForm adresseForm = (AdresseHtmlForm) o;
		html += construireTD( cache.obtenirLabel(subject, adresseForm.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION)) );
		html += construireTD( AffichageAdresse.construireAffichageAdresse(adresseForm, "1", subject) );
		html += construireTD( cache.obtenirLabel(subject, adresseForm.getVille(), new VilleCleMultiExterneListeCache(subject, adresseForm.getProvince())) );
		html += construireTD( adresseForm.getCodePostal() );
		html += construireTD( adresseForm.getTelephone1() );
		html += construireTD( cache.obtenirLabel(subject, adresseForm.getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.ADRESSE)) );
		return html;
	}


    protected String construireEntete(MessageResources messageResources) {
		String html = "";
		html += "<TR>";
		html += construireTDEntete( messageResources, "tabpage_site");
		html += construireTDEntete( messageResources, "adresse_t");
		html += construireTDEntete( messageResources, "tabpage_ville");
		html += construireTDEntete( messageResources, "codepostal_t");
		html += construireTDEntete( messageResources, "telephone1_t2");
		html += construireTDEntete( messageResources, "tabpage_statut");
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