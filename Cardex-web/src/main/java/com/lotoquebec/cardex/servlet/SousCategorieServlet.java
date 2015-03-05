package com.lotoquebec.cardex.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.presentation.controller.util.tri.SousCategorieComparator;
import com.lotoquebec.cardex.presentation.model.form.SousCategorieForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.servlet.TableDynamiqueServlet;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Ce servlet sert à afficher les adresses d'un sujet à partir
 * d'un script ajax 
 */
public class SousCategorieServlet extends TableDynamiqueServlet {
	
    protected List obtenirListeForm(String sCle, String sSite) throws BusinessResourceException, BusinessException, ValueObjectMapperException{
        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        
        Dossier dossier = constuireDossier(sCle, sSite);
        SousCategoriesVO sousCategoriesVO = delegate.findLiensSousCategories(subject,
                dossier);
        Iterator iter = sousCategoriesVO.getSousCategories().iterator();
        List listeSousCategorie = new ArrayList();
        
        while (iter.hasNext()) {
            SousCategorieVO sousCategorieVO = (SousCategorieVO) iter.next();
            SousCategorieForm sousCategorie = new SousCategorieForm(sousCategorieVO);
            sousCategorie.assignerValeurDeListe( subject );
            listeSousCategorie.add(sousCategorie);
        }
        Collections.sort(listeSousCategorie, new SousCategorieComparator());
        
        return listeSousCategorie;
    }	
	
    protected String construireRanger(ListeCache cache, Object o) throws BusinessResourceException {
		String html = "";
		SousCategorieForm sousCategorie = (SousCategorieForm) o;
		html += construireTD( sousCategorie.getDescription() );
		return html;
	}


    protected String construireEntete(MessageResources messageResources) {
		String html = "";
		html += "<TR>";
		html += construireTDEntete( messageResources, "sous.categories");
    	html += "</TR>";
		return html;
	}

	private Dossier constuireDossier(String sCle, String sSite) {
		Dossier dossier = new DossierVO();
        long cle = Long.valueOf( sCle ).longValue();
        long site = Long.valueOf( sSite ).longValue();
        dossier.setCle( cle );
        dossier.setSite( site);
        
		return dossier;
	}
}