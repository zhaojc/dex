package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class EnqueteReclamationDossierGenerateurRapport_CDX_0030 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersEnqueteReclamation");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new ActifIntervenantDossierRapportVO_CDX_0102();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
    	RapportBusinessDelegate delegate = new RapportBusinessDelegate();
    	List list = new ArrayList();
    	ResultSet resultSet = null;
    	try{
   		 	Calendar calendar = Calendar.getInstance();
   		 	SimpleDateFormat moisDateFormat = new SimpleDateFormat("MMMM", java.util.Locale.FRENCH);
   		 	//Pour ce rapport, il faut remonter au d�but de l'ann�e financi�re. Si le mois courant est inf�rieur � avril,
   		 	//la date de d�part sera avril de l'ann�e pr�c�dente. Sinon, avril de l'ann�e en cours.
   		 	//On v�rifie d'abord si un crit�re de date a �t� saisi.
   		 	Calendar dateDebutCal = Calendar.getInstance();
   		 	dateDebutCal.setTime(((CritereRapportVO)rapportVO).getDateDebutDu());
   		 	int annee = dateDebutCal.get(Calendar.YEAR);

    		if(3 > dateDebutCal.get(Calendar.MONTH))
    			calendar.set(annee-1, 3, 1);
    		else
    			calendar.set(annee, 3, 1);
    		
    		for(int i=0;i<=11;i++){
    			Map enquetesPAR = new HashMap();
	    		String anneeMois = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH);
	    		resultSet = delegate.rapportEnqueteReclamation(anneeMois,connection);
	    		resultSet.next();
	    		enquetesPAR.put("ANNEE", calendar.get(Calendar.YEAR)+"");
	    		enquetesPAR.put("MOIS", moisDateFormat.format(calendar.getTime()));
	    		enquetesPAR.put("TOTAL", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("PAR_MOINS_10000", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("PAR_PLUS_10000", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("NON_PAR", resultSet.getBigDecimal(2));
	      	    list.add(enquetesPAR);
    			calendar.add(Calendar.MONTH,1);
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
	        }
        }
  	  return new JRMapCollectionDataSource(list);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_PAR);
	}

}
