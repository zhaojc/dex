package com.lotoquebec.cardex.generateurRapport.acces;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.ListOfArrayDataSource;

import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardex.business.delegate.AccesBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.rapport.AccesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.AccesForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SujetAuditGenerateurRapport_CDX_0211 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.audit");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new AccesRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		AccesBusinessDelegate delegate =  new AccesBusinessDelegate();
		ValueListIterator results;
        SujetVO sujet = new SujetVO();
		results = delegate.selectAccesSujet(subject, sujet);
		return new ListOfArrayDataSource( traitementAcces(subject, results) );
	}


	private List<Acces> traitementAcces(CardexAuthenticationSubject subject, ValueListIterator results) throws ValueObjectMapperException, IteratorException{
	  Collection list = results.getNextElements(5000);
	  Collection currentList = new ArrayList();
	  Iterator   it = list.iterator();
	  List liste = new ArrayList();
	  
	  try{
		  
		  while (it.hasNext()) {
		      Acces acces = (Acces)it.next();
		      AccesForm accesForm = new AccesForm();
		      ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
		      currentList.add(accesForm);
		  }
		  //On récupère les valeurs directement dans un Map avec la fonction BeanUtils.describe.
		  //Cela  évite de traiter les données champ par champ. Il faut cependant que les noms de champ utilisés dans
		  //le rapport soient identiques au "describe" des champs de la fonction.	  
		  Iterator iter = currentList.iterator();
		  
		  while (iter.hasNext()) {
			  AccesForm accesForm = (AccesForm) iter.next();
			  accesForm.assignerValeurDeListe(subject);
			Map acces = BeanUtils.describe(accesForm);
			liste.add(acces);
		  }
		} catch (BusinessResourceException bre) {
		    bre.printStackTrace();
		} catch (BusinessException be) {
		    be.printStackTrace();
		} catch (IllegalAccessException iae) {
		    throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
		    throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
		    throw new ValueObjectMapperException(nsme);
		}
	  return liste;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_ACCES_SUJETS);
	}

	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = new HashMap();
		AccesRapportVO rapportDossierVO =(AccesRapportVO) rapportVO;
        parameters.put("DATE_DEBUT",DateFormat.format(rapportDossierVO.getDateHeureDebutDu(), DateFormat.DATE_FORMAT_AVEC_HEURE));
        parameters.put("DATE_FIN",DateFormat.format(rapportDossierVO.getDateHeureDebutAu(), DateFormat.DATE_FORMAT_AVEC_HEURE));
        parameters.put("UTILISATEUR",subject.getPrincipal().getName());
        //On met un titre, car le CDX_0122 sert pour 3 rapports
        parameters.put("TITRE","Rapport sur les acc�s des superutilisateurs");
        
		parameters.put("NUMERO_DOSSIER",sujetForm.getNumeroFiche());
		parameters.put("CREATEUR",sujetForm.getCreateurDescription());
		parameters.put("DATE_CREATION", sujetForm.getDateCreation());

		parameters.put("REPORT_CONNECTION",connection);
        
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		try {
			site = listeCache.obtenirLabel(subject, String.valueOf(rapportDossierVO.getSite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		parameters.put("SITE_DESCRIPTION", site);
		return parameters;
	}
	
}
