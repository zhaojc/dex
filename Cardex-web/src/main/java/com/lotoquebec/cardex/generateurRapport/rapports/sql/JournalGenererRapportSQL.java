package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.JournalRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.sql.GenererRapportSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public abstract class JournalGenererRapportSQL extends GenererRapportSQL {

	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new JournalRapportVO();
	}
	
	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		JournalRapportVO journalRapportVO = (JournalRapportVO) rapportVO;
		
		//On ne traite ici que les rapports sommaires ou d�taill�s.  Les rapports
		//par cat�gories sont ex�cut�s ailleurs.
		// Recherche normale avec les autres crit�res.  En fonction des
		// tests effectu�s et des v�rifications des crit�res, on ajoute
		// le code SQL n�cessaire � une variable.
		preparerSQL.getSQL().append(" SELECT S.V_SI_SITE, GS.V_GS_NOM  as \"GROUPE_SECURITE\", trin.v_tr_description AS \"GROUPE\", i.v_in_nom||', '||i.v_in_prenom as \"INTERVENANT\", ");
		preparerSQL.getSQL().append(" TRTY.V_TR_DESCRIPTION as \"TYPE\", TRCA.V_TR_DESCRIPTION as \"CATEGORIE\", C.V_CO_TEMPS AS \"TEMPS\", ");
		preparerSQL.getSQL().append(" DO.V_DO_REFERENCE2||' - '||DO.V_DO_LIEU AS \"DESCRIPTIF\", TROR.V_TR_DESCRIPTION AS \"ORIGINE\" "); 
		preparerSQL.getSQL().append(" FROM DO_DOSSIER DO, CA_CATEGORIE CA, CO_COMMENTAIRE2 C, TY_TYPE TY, NA_NATURE NA, GE_GENRE GE, ");
		preparerSQL.getSQL().append(" TR_TRADUCTION TRTY, TR_TRADUCTION TRCA, SI_SITE S, in_intervenant i, tr_traduction trin, ");
		preparerSQL.getSQL().append(" TR_TRADUCTION TROR, GS_GROUPE_SECURITE GS, IGS_INTERVENANT_GROUPE_SEC IGS ");                    
		//On ne tient pas compte des dossiers plac�s en confidentialit� 8 (dossiers en attente de suppression).
		preparerSQL.getSQL().append(" WHERE DO.I_CC_CLE <> ?");
		preparerSQL.addParametre(GlobalConstants.Confidentialite.HUIT);
		preparerSQL.getSQL().append(" AND CA.I_CA_CLE = DO.I_CA_CLE  ");
		preparerSQL.getSQL().append(" AND i.NAME =  c.v_co_cree_par " );
		preparerSQL.getSQL().append(" AND trin.l_tr_cle = i.l_in_secteur " );
		preparerSQL.getSQL().append(" AND trin.I_LA_CLE = 1 " );
		preparerSQL.getSQL().append(" AND DO.L_DO_CLE = C.L_CO_REFERENCE AND DO.L_SI_CLE = C.L_CO_REF_SITE  " );
		preparerSQL.getSQL().append(" AND C.C_CO_REF_GENRE = '" + GlobalConstants.GenreFichier.DOSSIER + "'");
		preparerSQL.getSQL().append(" AND CA.I_TY_CLE = TRTY.L_TR_CLE ");
		preparerSQL.getSQL().append(" AND TRTY.I_LA_CLE = 1 " );
		preparerSQL.getSQL().append(" AND DO.L_DO_ORIGINE = TROR.L_TR_CLE AND TROR.I_LA_CLE = 1 ");
		preparerSQL.getSQL().append(" AND I.NAME = IGS.V_NAME ");
		preparerSQL.getSQL().append(" AND IGS.L_GS_CLE = GS.L_GS_CLE ");
		preparerSQL.getSQL().append(" AND DO.I_CA_CLE = TRCA.L_TR_CLE ");
		preparerSQL.getSQL().append(" AND TRCA.I_LA_CLE = 1 ");
		preparerSQL.getSQL().append(" AND CA.I_TY_CLE = TY.I_TY_CLE ");
		preparerSQL.getSQL().append(" AND TY.I_NA_CLE = NA.I_NA_CLE ");
		preparerSQL.getSQL().append(" AND NA.I_GE_CLE = GE.I_GE_CLE ");
		preparerSQL.getSQL().append(" AND C.V_CO_TEMPS is not null " );
		
		if (journalRapportVO.getEntite() != 0 && journalRapportVO.getSite() == 0) {
	        preparerSQL.getSQL().append(" AND S.L_SI_CLE = DO.L_SI_CLE");
	        preparerSQL.getSQL().append(" AND S.I_EN_CLE = ? " );
	        preparerSQL.addParametre(journalRapportVO.getEntite());
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "I.NAME", journalRapportVO.getIntervenant());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.V_DO_ANCIENNE_REFERENCE", journalRapportVO.getNumeroDossier().toUpperCase());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.V_DO_REFERENCE1", journalRapportVO.getReference().toUpperCase());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_CA_CLE", journalRapportVO.getCategorie());
		
		if (journalRapportVO.getType() != 0 && journalRapportVO.getCategorie() == 0) {
	        preparerSQL.getSQL().append(" AND TY.I_TY_CLE = ? " );
	        preparerSQL.addParametre(journalRapportVO.getType());
		}
		if (journalRapportVO.getNature() != 0 && journalRapportVO.getType() == 0) {
            preparerSQL.getSQL().append(" AND NA.I_NA_CLE = ? ");
            preparerSQL.addParametre(journalRapportVO.getNature());
		}
		if (journalRapportVO.getGenre() != 0 && journalRapportVO.getNature() == 0) {
            preparerSQL.getSQL().append(" AND GE.I_GE_CLE = ? " );
            preparerSQL.addParametre(journalRapportVO.getGenre());
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "I.L_IN_SECTEUR", journalRapportVO.getSecteur());
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "DO.V_DO_LIEU", journalRapportVO.getDescriptif());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_DO_FONDE", journalRapportVO.getFonde());
		
		if (journalRapportVO.getSite() != 0) {
            preparerSQL.getSQL().append(" AND C.L_SI_CLE = S.L_SI_CLE AND C.L_SI_CLE = ? ");
            preparerSQL.addParametre(journalRapportVO.getSite());
		}
		// On tient compte de l'heure dans la recherche
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "C.D_CO_DATE_CREATION", ">=", journalRapportVO.getDateDebutDu());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "C.D_CO_DATE_CREATION", "<", journalRapportVO.getDateDebutAu());
        if(journalRapportVO.getDateDebutAu()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.L_DO_ORIGINE", journalRapportVO.getOrigine());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_OR_CLE", journalRapportVO.getEndroit());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_CR_CLE", journalRapportVO.getLocalisation());

		return preparerSQL;
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		JournalRapportVO journalRapportVO = (JournalRapportVO) rapportVO;
		parameters.put("NO_DOSSIER", journalRapportVO.getNumeroDossier());
		parameters.put("REFERENCE", journalRapportVO.getReference());
		return parameters;
	}
	
}
