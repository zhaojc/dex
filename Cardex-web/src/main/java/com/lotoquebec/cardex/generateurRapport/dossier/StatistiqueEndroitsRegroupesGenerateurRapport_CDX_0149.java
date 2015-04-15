package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class StatistiqueEndroitsRegroupesGenerateurRapport_CDX_0149 extends CritereGenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATISTIQUE_ENDROITS_REGROUPES);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/statistiqueEndroitsRegroupes");
	}
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new StatistiqueDossierRapportVO();
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException,BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		StatistiqueDossierRapportVO statistiqueDossierRapportVO = (StatistiqueDossierRapportVO) rapportVO;
		ResultSet resultSet = delegate.rapportEndroitsRegroupes(statistiqueDossierRapportVO,connection);
		return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		StatistiqueDossierRapportVO statistiqueDossierRapportVO = (StatistiqueDossierRapportVO) rapportVO;
		CardexUser cardexUser = (CardexUser) subject.getUser();
        ListeCache listeCache = ListeCache.getInstance();
        String descriptionCategorie="";
        String descriptionType="";
        String descriptionSite ="";
        String descriptionGenre = "";
        String descriptionNature = "";
        
        try
        {
            descriptionSite = listeCache.obtenirLabel(subject, statistiqueDossierRapportVO.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
            if(descriptionSite==""){
                descriptionSite="Tous";
            }
            
            descriptionGenre= listeCache.obtenirLabel(subject, String.valueOf(statistiqueDossierRapportVO.getGenre()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER));
            if(descriptionGenre==""){
                descriptionGenre="Tous";
            }
            
            descriptionNature = listeCache.obtenirLabel(subject, String.valueOf(statistiqueDossierRapportVO.getNature()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, "", GlobalConstants.ActionSecurite.CONSULTER_DOSSIER));
            if(descriptionNature==""){
                descriptionNature="Toutes";
            }

            descriptionType = listeCache.obtenirLabel(subject, statistiqueDossierRapportVO.getType(), new TypeCleMultiListeCache(subject, statistiqueDossierRapportVO.getNature()));
            if(descriptionType==""){
                descriptionType="Tous";
            }

            descriptionCategorie = listeCache.obtenirLabel(subject, statistiqueDossierRapportVO.getCategorie(), new CategorieCleMultiListeCache(subject, statistiqueDossierRapportVO.getType()));
            if(descriptionCategorie==""){
                descriptionCategorie="Toutes";
            }

            parameters.put("DateDebut", StringUtils.substring(statistiqueDossierRapportVO.getDateDebutDu().toString(), 0, 10));
            parameters.put("DateFin", StringUtils.substring(statistiqueDossierRapportVO.getDateDebutAu().toString(), 0, 10));
            parameters.put("UTILISATEUR", cardexUser.getCode());
            parameters.put("SITE_INTERROGE", descriptionSite);
            parameters.put("GENRE_INTERROGE", descriptionGenre);
            parameters.put("NATURE_INTERROGEE", descriptionNature);
            parameters.put("TYPE_INTERROGE", descriptionType);
            parameters.put("CATEGORIE_INTERROGEE", descriptionCategorie);
        }
        catch (BusinessResourceException e) {
            e.printStackTrace();
        }
        return parameters;
	}
}
