/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Module de test uniquement. L'affichage ne fonctionne qu'en mode local. Sinon, ça prend un servlet.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.1 $, $Date: 2006/08/20 22:06:38 $
 */
public class RapportsJasperAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Test des rapports Jasper (rapport tabulaire sur les contrats
     * d'autoexclusion avec deux paramètres de date).
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward rapport(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws DAOException,IOException,
                              ServletException {
        log.fine("Module des rapports Jasper");

        ActionErrors errors = new ActionErrors();
        CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) form;
        CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = DAOConnection.getInstance().getConnection(subject);
        try {
        	String choixRapport = criteresRechercheDossierForm.getChoixRapport();
        	String procedure = "";
            RapportBusinessDelegate delegate = new RapportBusinessDelegate();
        	/*if (GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NUMERO";
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NOMBRE";
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REP_ACCES_INTERDITS";
            }*/
        	if (GlobalConstants.ChoixRapport.REPERAGES_AVIS_DE_GUET.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AVIS_DE_GUET;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AVIS_GUET";
            }
            
			InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
//			//On s'assure qu'il y a des date, sinon on prend les six derniers mois.
			//MAINTENANT FAIT DANS LA PROCEDURE ORACLE
//            String DATE_FORMAT_NOW = "yyyy-MM-dd";
//            Calendar dateDebut = Calendar.getInstance();
//            Calendar dateFin = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
//            dateDebut.add(Calendar.DATE,-130);
//			if(StringUtils.isEmpty(criteresRechercheDossierForm.getDateDebutDu())){
//				criteresRechercheDossierForm.setDateDebutDu(sdf.format(dateDebut.getTime()));
//			}
//			if(StringUtils.isEmpty(criteresRechercheDossierForm.getDateDebutAu())){
//				criteresRechercheDossierForm.setDateDebutAu(sdf.format(dateFin.getTime()));
//			}
			ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierForm, criteresRechercheDossier,subject.getLocale());
			//Utilisation d'un resultSet comme source de données
	         //resultSet = delegate.rapportReperage(criteresRechercheDossier, procedure);
	         JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
	         ServletContext context = request.getSession().getServletContext();  
	         //System.out.println(context.getRealPath("/rapports/"));
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         //parameters.put("UTILISATEUR",user.getCode());
	         parameters.put("UTILISATEUR"," ");
	         JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
		    //Affichage à l'écran
		    JasperViewer.viewReport(print,false);
		    //JasperViewer.viewReport(print);
		    return mapping.findForward("success");
	    //}catch (IOException se) {
        //    throw new DAOException(se);
        } /*catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
	    }*/catch (JRException se) {
            throw new DAOException(se);
  	    //}catch (FileNotFoundException se) {
        //    throw new DAOException(se);
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }
        finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
        }
	}
    
}

