package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.VehiculeBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.rapport.PDFImpressionRapport;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lq.std.conf.impl.AppConfig;

/**
 * Mouvements de personnel
 * @author levassc
 *
 */
public class CDX00_00016_VerificationMultimedia implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00016_VerificationMultimedia.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.info("Entr�e flux CDX00_00016");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.info("Vérification des �l�ments multimédia");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			envoyerConfirmation(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00016");
	}

	private void envoyerConfirmation(CardexAuthenticationSubject subject, Connection connection) throws FileNotFoundException, JRException, BusinessResourceException, DAOException, IOException{
		String nomRapport = obtenirNomRapport();
		//log.info("Choix nom rapport : '"+nomRapport+"'");
		log.info("Envoi du courriel");
		produireRapportVerificationMultimedia(nomRapport, connection);
		String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Coherence);
		CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.Coherence, "A", nomRapport);
	}

	private String obtenirNomRapport(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_COHERENCE);
		String dateRapport = dateFormat.format(new Date());
		return rapportTemporaire+"V�rification des �l�ments multim�dias ("+dateRapport+").pdf";
	}

	private void produireRapportVerificationMultimedia(String nomRapport, Connection connection) throws JRException, FileNotFoundException, DAOException, IOException {
		log.info("produireRapportVerificationMultimedia d�but "+nomRapport);
		
		Map parameters = new HashMap();
		List liste = new ArrayList();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateRapport = dateFormat.format(new Date());
		InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.VERIFICATION_ELEMENTS_MULTIMEDIA);
		try{
			log.info("produireRapportVerificationMultimedia");
			ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().rapportVerificationMultimedia(connection);
			//Pour chaque lien trouv�, on v�rifie la pr�sence de l'�l�ment multimedia.
			while(resultSet.next()){
				Photo photoVO = new PhotoVO();
				InputStream photo = null;
				photoVO.setLienMultimedia(resultSet.getLong("L_EM_CLE"));
				photoVO.setLienSiteMultimedia(resultSet.getLong("L_EM_SI_CLE"));
				photoVO.setGenreFichier(resultSet.getString("C_LMM_REF_GENRE"));
				photoVO.setExtension(resultSet.getString("V_MM_EXTENSION"));
				photoVO.setTypeMultimedia(resultSet.getLong("I_TM_CLE"));
				//On commence par v�rifier la petite photo
				if(GlobalConstants.Image.EXTENTION_IMAGE_COMPRESSION_ACCEPTE.indexOf(photoVO.getExtension()) > -1){
					FichierMultimediaVO fichierMultimediaVO = FabriqueCardexDAO.getInstance().getPhotoDAO().fichierPresent(subject, photoVO, true);
					if(fichierMultimediaVO.getImageByte() == null || fichierMultimediaVO.getImageByte().length == 0){
						Map donnees = new HashMap();
						String fichier = String.valueOf(photoVO.getLienMultimedia())+fichierMultimediaVO.getExtention();
						donnees.put("Element", fichier);
						//Recherche de la fiche o� l'�l�ment est associ�.
						long cle = resultSet.getLong("L_LMM_REFERENCE");
						long site = resultSet.getLong("L_LMM_REF_SITE");
						String genre = resultSet.getString("C_LMM_REF_GENRE");
						genre = StringUtils.trim(genre);
						if(genre.equals(GlobalConstants.GenreFichier.DOSSIER)){
							DossierBusinessDelegate dossierBusinessDelegate = new DossierBusinessDelegate();
							Dossier dossier = new DossierVO();
							dossier.setCle(cle);
							dossier.setSite(site);
							dossier = dossierBusinessDelegate.find(subject, dossier);
							String noCardex = StringUtils.substring(dossier.getNumeroCardex(),0,3) + "-" +
											  StringUtils.substring(dossier.getNumeroCardex(),3,11) + "-" +
											  StringUtils.substring(dossier.getNumeroCardex(),11,15);
							donnees.put("Fiche", "Dossier " + noCardex);
						}
						if(genre.equals(GlobalConstants.GenreFichier.SUJET)){
							SujetBusinessDelegate sujetBusinessDelegate = new SujetBusinessDelegate();
							Sujet sujet = new SujetVO();
							sujet.setCle(cle);
							sujet.setSite(site);
							sujet = sujetBusinessDelegate.find(subject, sujet);
							donnees.put("Fiche", "Sujet " + sujet.getNumeroFiche());
						}
						if(genre.equals(GlobalConstants.GenreFichier.SOCIETE)){
							SocieteBusinessDelegate societeBusinessDelegate = new SocieteBusinessDelegate();
							Societe societe = new SocieteVO();
							societe.setCle(cle);
							societe.setSite(site);
							societe = societeBusinessDelegate.find(subject, societe);
							if(StringUtils.isNotEmpty(societe.getNumeroFiche())){
								donnees.put("Fiche", "Soci�t� " + societe.getNumeroFiche());
							}else{
								donnees.put("Fiche", "Soci�t� " + societe.getNom());
							}
						}
						if(genre.equals(GlobalConstants.GenreFichier.VEHICULE)){
							VehiculeBusinessDelegate vehiculeBusinessDelegate = new VehiculeBusinessDelegate();
							Vehicule vehicule = new VehiculeVO();
							vehicule.setCle(cle);
							vehicule.setSite(site);
							vehicule = vehiculeBusinessDelegate.find(subject, vehicule);
							donnees.put("Fiche", "V�hicule " + vehicule.getImmatriculation());
						}
						liste.add(donnees);
					}
				}
				//Ensuite l'�l�ment multim�dia original associ�
				FichierMultimediaVO fichierMultimediaVO = FabriqueCardexDAO.getInstance().getPhotoDAO().fichierPresent(subject, photoVO, false);
				if(fichierMultimediaVO.getImageByte() == null || fichierMultimediaVO.getImageByte().length == 0){
					Map donnees = new HashMap();
					String fichier = String.valueOf(photoVO.getLienMultimedia())+fichierMultimediaVO.getExtention();
					donnees.put("Element", fichier);
					//Recherche de la fiche o� l'�l�ment est associ�.
					long cle = resultSet.getLong("L_LMM_REFERENCE");
					long site = resultSet.getLong("L_LMM_REF_SITE");
					String genre = resultSet.getString("C_LMM_REF_GENRE");
					genre = StringUtils.trim(genre);
					if(genre.equals(GlobalConstants.GenreFichier.DOSSIER)){
						DossierBusinessDelegate dossierBusinessDelegate = new DossierBusinessDelegate();
						Dossier dossier = new DossierVO();
						dossier.setCle(cle);
						dossier.setSite(site);
						dossier = dossierBusinessDelegate.find(subject, dossier);
						String noCardex = StringUtils.substring(dossier.getNumeroCardex(),0,3) + "-" +
						StringUtils.substring(dossier.getNumeroCardex(),3,11) + "-" +
					    StringUtils.substring(dossier.getNumeroCardex(),11,15);
						donnees.put("Fiche", "Dossier " + noCardex);
					}
					if(genre.equals(GlobalConstants.GenreFichier.SUJET)){
						SujetBusinessDelegate sujetBusinessDelegate = new SujetBusinessDelegate();
						Sujet sujet = new SujetVO();
						sujet.setCle(cle);
						sujet.setSite(site);
						sujet = sujetBusinessDelegate.find(subject, sujet);
						donnees.put("Fiche", "Sujet " + sujet.getNumeroFiche());
					}
					if(genre.equals(GlobalConstants.GenreFichier.SOCIETE)){
						SocieteBusinessDelegate societeBusinessDelegate = new SocieteBusinessDelegate();
						Societe societe = new SocieteVO();
						societe.setCle(cle);
						societe.setSite(site);
						societe = societeBusinessDelegate.find(subject, societe);
						if(StringUtils.isNotEmpty(societe.getNumeroFiche())){
							donnees.put("Fiche", "Soci�t� " + societe.getNumeroFiche());
						}else{
							donnees.put("Fiche", "Soci�t� " + societe.getNom());
						}
					}
					if(genre.equals(GlobalConstants.GenreFichier.VEHICULE)){
						VehiculeBusinessDelegate vehiculeBusinessDelegate = new VehiculeBusinessDelegate();
						Vehicule vehicule = new VehiculeVO();
						vehicule.setCle(cle);
						vehicule.setSite(site);
						vehicule = vehiculeBusinessDelegate.find(subject, vehicule);
						donnees.put("Fiche", "V�hicule " + vehicule.getImmatriculation());
					}
					liste.add(donnees);
				}
			}//while
	      } catch (BusinessResourceException bre) {
	          bre.printStackTrace();
	      } catch (BusinessException be) {
	          be.printStackTrace();
	      } catch (SQLException se) {
				//System.out.println(se.getMessage());
				se.printStackTrace();
	      }
  		JRDataSource dataSource = new JRMapCollectionDataSource(liste);
		// log.info(context.getRealPath("/rapports/"));
		parameters.put("SUBREPORT_DIR", "/rapports/");
		parameters.put("REPORT_CONNECTION", connection);
		parameters.put("UTILISATEUR", "Diff�r� Cardex");
        parameters.put("DATE_DEBUT",dateRapport);
		JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, dataSource);

		// Sauvegarde dans un fichier
		log.info("produireRapportVerificationMultimedia (Sauvegarde dans un fichier)");
		(new PDFImpressionRapport()).impression(nomRapport, print);
		log.info("produireRapportVerificationMultimedia Fin");

	}
	
}
