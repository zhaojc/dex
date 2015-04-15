package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EndroitCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.rapport.Discriminant;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


public abstract class RegroupementGenerateurRapport extends CritereGenererRapport {

	public final static String ENTITE = "entite";
	public final static String SITE = "site";
	public final static String GENRE = "genre";
	public final static String NATURE = "nature";
	public final static String TYPE = "type";
	public final static String CATEGORIE = "categorie";
	public final static String INTERVENANT = "intervenant";
	public final static String SECTEUR = "secteur";
	public final static String DATE_CREATION_DU = "dateCreationDu";
	public final static String DATE_CREATION_AU = "dateCreationAu";
	public final static String ENDROIT = "endroit";
	public final static String REGROUPEMENT = "regroupement";
	public final static String TEMPS_NON_COMPTABILISE = "temps_non_comptabilise";
	
	PresentationRegroupementRapport presentationRegroupementRapport = new PresentationRegroupementRapport();
	
	protected Discriminant discriminantRegroupement = new Discriminant(){
		public boolean comparer(Object temoin, Object element) {
			ResultatPresentationRegroupement rTemoin = (ResultatPresentationRegroupement) temoin;
			ResultatPresentationRegroupement rElement = (ResultatPresentationRegroupement) element;
			return rTemoin.getCle() == rElement.getCle();
		}
	};
	
	protected Discriminant discriminantIntervenant = new Discriminant(){
		public boolean comparer(Object temoin, Object element) {
			ResultatPresentationRegroupement rTemoin = (ResultatPresentationRegroupement) temoin;
			ResultatPresentationRegroupement rElement = (ResultatPresentationRegroupement) element;
			return rTemoin.getCleIntervenant() == rElement.getCleIntervenant();
		}
	};	
	
	protected Discriminant discriminantEndroit = new Discriminant(){
		public boolean comparer(Object temoin, Object element) {
			ResultatPresentationRegroupement rTemoin = (ResultatPresentationRegroupement) temoin;
			ResultatPresentationRegroupement rElement = (ResultatPresentationRegroupement) element;
			return rTemoin.getEndroit().equals(rElement.getEndroit());
		}
	};	

	protected Discriminant discriminantAnneeMois = new Discriminant(){
		public boolean comparer(Object temoin, Object element) {
			ResultatPresentationRegroupement rTemoin = (ResultatPresentationRegroupement) temoin;
			ResultatPresentationRegroupement rElement = (ResultatPresentationRegroupement) element;
			return rTemoin.getMoisNombre().equals(rElement.getMoisNombre());
		}
	};	
	
	// Obtenir le fichier .jasper
	protected abstract InputStream obtenirGabarit();
	
	// Appel des composants concept pour la production du rapport
	protected abstract ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException; 
	
	// Trier les regroupements
	protected abstract void trier(PresentationRegroupementRapport regroupementRapportVO);
	
	// Ajout des sous-�l�ments de la collection
	protected abstract void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO);
	
	// Construire le contenu du rapport
	protected abstract JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO);
	
	// Assigner la valeur de temps nom comptabilis� � la premi�re colonne.
	protected abstract void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise);
	
	// Converti les donn�es du VO vers Regroupement
	protected abstract void convertirResultat(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement);
	
	// Ajouter le temps non comptabilis�
	protected abstract void ajouterTempsNonComptabilise(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO);
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException{
		RegroupementRapportVO criteresRechercheRegroupementVO = (RegroupementRapportVO) rapportVO;
		ResultatRegroupementVO regroupement = produireRapport(subject, criteresRechercheRegroupementVO);
		
		convertirResultat(subject, presentationRegroupementRapport, regroupement);
		
		trier(presentationRegroupementRapport);
		ajouterSousCollection(presentationRegroupementRapport);

		ajouterTempsNonComptabilise(subject, presentationRegroupementRapport);
	
		presentationRegroupementRapport.calculerPourcentageListeResultatPresentationRegroupement();

		return construireDataSource(presentationRegroupementRapport);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		RegroupementRapportVO criteresRechercheRegroupementVO = (RegroupementRapportVO) rapportVO;
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		String secteur = "";
		String endroit = "";
		String intervenant = "";
		String categorie = "";
		try {
			site = listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
			secteur = listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getSecteur(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SECTEUR, "", GlobalConstants.ActionSecurite.RECHERCHE));
			endroit = listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getEndroit(), new EndroitCleListeCache(subject));
			intervenant = listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getIntervenant(), new IntervenantCle(subject));
			categorie = listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getType(), new TypeCleMultiListeCache(subject, criteresRechercheRegroupementVO.getNature() ));
			categorie = categorie + " / " + listeCache.obtenirLabel(subject, criteresRechercheRegroupementVO.getCategorie(), new CategorieCleMultiListeCache(subject, criteresRechercheRegroupementVO.getType()));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		
		parameters.put("SiteOrigine", site);
		parameters.put("Secteur", secteur);
		parameters.put("Endroit", endroit);
		parameters.put("Intervenant", intervenant);
		parameters.put("Categorie", categorie);
		parameters.put("DateDebut", StringUtils.substring(criteresRechercheRegroupementVO.getDateDebutDu().toString(),0,10));
		parameters.put("DateFin", StringUtils.substring(criteresRechercheRegroupementVO.getDateDebutAu().toString(),0,10));

		parameters.put("TotalTotalHeures", presentationRegroupementRapport.getTotalRechercheRegroupement().getTotalHeuresFormate());
		parameters.put("TotalQuota", Math.round(presentationRegroupementRapport.getTotalRechercheRegroupement().getQuota())+" %");
		return parameters;
	}
	
}
