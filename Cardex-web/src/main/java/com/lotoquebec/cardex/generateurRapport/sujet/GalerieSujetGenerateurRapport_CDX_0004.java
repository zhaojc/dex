package com.lotoquebec.cardex.generateurRapport.sujet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

public class GalerieSujetGenerateurRapport_CDX_0004 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.base.imprimer");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		Sujet sujet = (Sujet) vo;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportImpressionFiche(sujet.getCle(), sujet.getSite(), "CARDEX_RAPPORT.SP_IMPRESSION_SUJET", connection);
        return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SUJET_GALERIE_CDX_0004);
	}

	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Sujet sujet = (Sujet) vo;
		CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
        InputStream photo = recherchePhoto(subject, sujet);
        parameters.put("PHOTO",photo);
		parameters.put("CONFIDENTIALITE", Long.toString(privilege.getNiveauConfidentialite()));
		return parameters;
	}
	
	private InputStream recherchePhoto(CardexAuthenticationSubject subject, Sujet sujet){
		SujetBusinessDelegate delegateSujet = new SujetBusinessDelegate();
		PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
		
		try {
			List<PhotoVO> liensPhoto = delegateSujet.findLiensPhoto(subject, sujet);
			
			for(Photo photo:liensPhoto){
			    
			    if(photo.isSelectionner()){
			    	FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, photo.getLienElement(), photo.getLienSiteElement(), false);
			    	
			    	if(fichierMultimediaVO.getImageByte() != null){
			    		return new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
			    	}
			    }
			}
			return null;
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
