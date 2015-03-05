package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class ReconnaissancePlaqueGenerateurRapport_CDX_0075 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/reconnaissancePlaque");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new StatistiqueDossierRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		StatistiqueDossierRapportVO reconnaissancePlaqueVO = (StatistiqueDossierRapportVO) rapportVO;
		ListeCache cache = ListeCache.getInstance();
		String siteDescription = cache.obtenirLabel(subject, reconnaissancePlaqueVO.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.Entite.MAISON_JEUX, GlobalConstants.ActionSecurite.SELECTION));
		String natureDescription = cache.obtenirLabel(subject, reconnaissancePlaqueVO.getNature(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, GlobalConstants.Genre.SUJETS_INTERET, GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
		//Le système AutoVu de reconnaissance de plaques supporte mal les accents. On les retire donc ici. Les autres l'ont déjà été dans la procédure SQL. 
		if(natureDescription.equals("Accès interdits")){
			natureDescription = "Acces interdits";
		}
		if(siteDescription.equals("Casino Montréal")){
			siteDescription = "Casino Montreal";
		}
		String titre = natureDescription + " " + siteDescription;

		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.produireRapportReconnaissance(titre, reconnaissancePlaqueVO);
       	return new JRResultSetDataSource(resultSet);
	}


	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_RECONNAISSANCE_PLAQUES);
	}

}
