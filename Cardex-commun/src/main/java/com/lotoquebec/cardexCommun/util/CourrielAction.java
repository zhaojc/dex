package com.lotoquebec.cardexCommun.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.InterventionCodeCourrielCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.TypeInterventionCle;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.text.DateFormat;

/**
 * Cette classe traite l'envoi automatique des courriers électroniques
 * aux intervenants concernés par un mouvement de personnel.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.1 $, $Date: 2004/06/15 22:06:38 $
 */
public class CourrielAction{

	private static Logger log = Logger.getLogger(CourrielAction.class.getName());

	public static void envoiCourriel(CardexAuthenticationSubject subject, String texte, String destinataire, String objet){
		envoiCourriel(subject, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL, texte, destinataire, objet);
	}
	
	public static void envoiCourriel(CardexAuthenticationSubject subject, String codeExpediteur, String texte, String destinataire, String objet){
		   ListeCache listeCache = ListeCache.getInstance();

	       String to = destinataire;

	       try {
	    	   String from = listeCache.obtenirLabel(subject, codeExpediteur, new InterventionCodeCourrielCle());
	    	   
	    	   log.fine("Envoi du courrier à : " + to);

	           // set the SMTP host property value
	           Properties properties = System.getProperties();
	           properties.put("mail.smtp.host", "relais1.loto-quebec.com");

	           // create a JavaMail session
	           Session session = Session.getInstance(properties, null);

	           // create a new MIME message 
	           MimeMessage message = new MimeMessage(session);

	           // set the from address
	           Address fromAddress = new InternetAddress(from);
	           message.setFrom(fromAddress);

	           // set the to address
	           if (to != null) {
	               Address[] toAddress = InternetAddress.parse(to);
	               message.setRecipients(Message.RecipientType.TO, toAddress);
	           }
	           else
	               throw new MessagingException("No \"To\" address specified");

	           // set the subject
	           message.setSubject(objet, "iso-8859-1");
	           
	           message.setContent(texte, "text/html;charset=\"UTF-8\"");
	           
	       	   Transport.send(message);
	       }
	       catch (AddressException e) {
	    	  log.warning(e.toString());
	       }
	       catch (SendFailedException e) {
	    	  log.warning(e.toString());
		   }catch (MessagingException e) {
	          log.warning(e.toString());
	       } catch (BusinessResourceException e) {
	    	   log.warning(e.toString());
		   }
	   }
	
	public static void envoiCourrielEtFichier(CardexAuthenticationSubject subject, String texte, String destinataire, String objet, String emplacementFichier){
		   ListeCache listeCache = ListeCache.getInstance();

	       String to = destinataire;

	       try {
	    	   String from = listeCache.obtenirLabel(subject, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL, new InterventionCodeCourrielCle());
	    	   
	    	   log.fine("Envoi du courrier à : " + to);

	           // set the SMTP host property value
	           Properties properties = System.getProperties();
	           properties.put("mail.smtp.host", "relais1.loto-quebec.com");

	           // create a JavaMail session
	           Session session = Session.getInstance(properties, null);

	           // create a new MIME message 
	           MimeMessage message = new MimeMessage(session);

	           // set the from address
	           Address fromAddress = new InternetAddress(from);
	           message.setFrom(fromAddress);

	           // set the to address
	           if (to != null) {
	               Address[] toAddress = InternetAddress.parse(to);
	               message.setRecipients(Message.RecipientType.TO, toAddress);
	           }
	           else
	               throw new MessagingException("No \"To\" address specified");

	           // set the subject
	           message.setSubject(objet, "iso-8859-1");

	           MimeBodyPart mimeBodyPartTexte = new MimeBodyPart();
	           //mimeBodyPartTexte.setText(texte);
	           mimeBodyPartTexte.setContent(texte, "text/html");
	           
	           MimeBodyPart bodyPartFichier = new MimeBodyPart();
	           Multipart multipart = new MimeMultipart();
	           multipart.addBodyPart(mimeBodyPartTexte);
	           
	           // Part two is attachment
		       FileDataSource fds = new FileDataSource(emplacementFichier);   	
		       bodyPartFichier.setDataHandler(new DataHandler(fds));
		       bodyPartFichier.setFileName(fds.getName());
	           multipart.addBodyPart(bodyPartFichier);
	           
	           // Put parts in message
	           message.setContent(multipart);
	           
	       	   Transport.send(message);
	       }catch (AddressException e) {
	    	  log.warning(e.toString());
	       }catch (SendFailedException e) {
	    	  log.warning(e.toString());
		   }catch (MessagingException e) {
	          log.warning(e.toString());
	       } catch (BusinessResourceException e) {
	    	   log.warning(e.toString());
		   }
	   }
	
	/**
	 * Recherche les destinataires et envoie un courriel
	 * @throws DAOException 
	 *//*
    public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final String message, final String type, String snSite) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(connection);
    	String sql = obtenirSQLRechercheDestinataires(type, snSite);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>  " + message;
				envoiCourriel(subject, texte, destinataire, type);
			}
    	};
    	
		template.query(sql, callbackHandler, false);    	
	}    */
	
	public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final String objetMessage, final String message, final String type, String snSite) throws DAOException {
		envoyerCourrielDestinataire(subject, connection, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL, objetMessage, message, type, snSite);
	}
	
    public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final String codeExpediteur, final String objetMessage, final String message, final String type, String snSite) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(connection);
    	String sql = obtenirSQLRechercheDestinataires(type, snSite);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>  " + message;
				envoiCourriel(subject, codeExpediteur, texte, destinataire, objetMessage);
			}
    	};
    	
		template.query(sql, callbackHandler, false);    	
	}     

    public static void envoyerCourrielEtFichierDestinataire(final CardexAuthenticationSubject subject, Connection connection, final String objetMessage, final String message, final String type, String snSite, final String emplacementFichier) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(connection);
    	String sql = obtenirSQLRechercheDestinataires(type, snSite);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>  " + message;
				envoiCourrielEtFichier(subject, texte, destinataire, objetMessage, emplacementFichier);
			}
    	};
    	
		template.query(sql, callbackHandler, false);    	
	} 
    
    public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, final String objetMessage, final String message, final String type, String snSite) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(subject);
    	String sql = obtenirSQLRechercheDestinataires(type, snSite);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>  " + message;
				envoiCourriel(subject, texte, destinataire, objetMessage);
			}
    	};
    	
		template.query(sql, callbackHandler);    	
	}    
    
    
    /**
     * Recherche un groupe de destinataire et envoie les courriels.
     * @param subject
     * @param connection
     * @param messageDuCourriel
     * @param type
     * @param siteConcerne
     * @throws DAOException
     */
    public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final MessageDuCourriel messageDuCourriel, final String type, final String siteConcerne) throws DAOException {
    	envoyerCourrielDestinataire(subject, connection, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL, messageDuCourriel, siteConcerne, siteConcerne); 
	}
    
    public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final String codeExpediteur, final MessageDuCourriel messageDuCourriel, final String type, final String siteConcerne) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(connection);
		String sql = obtenirSQLRechercheGroupeDestinataires(type, siteConcerne);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>  " + messageDuCourriel.construireMessage(type);
				try {
					envoiCourriel(subject, codeExpediteur, texte, destinataire, messageDuCourriel.construireObjectMessage(subject));
				} catch (BusinessResourceException e) {
					e.printStackTrace();
					throw new RuntimeException("Erreur lors de la recherche de destinataire");
				}
			}
    	};
    	
		template.query(sql, callbackHandler, false);
	}    
/*
    public static void rechercheGroupeDestinataires(final CardexAuthenticationSubject subject, final String type, final String siteConcerne) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(subject);
    	String sql = obtenirSQLRechercheGroupeDestinataires(type, siteConcerne);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>";
				envoiCourriel(subject, texte, destinataire, type);
			}
    	};
    	
		template.query(sql, callbackHandler, true);
	}

    public static void rechercheGroupeDestinataires(final CardexAuthenticationSubject subject, Connection connection, final String type, final String siteConcerne) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(connection);
    	String sql = obtenirSQLRechercheGroupeDestinataires(type, siteConcerne);
    	
    	RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String destinataire = rs.getString("V_IV_COURRIEL");
				String texte = rs.getString("V_IV_TEXTE_MESSAGE");
				texte = texte + "<br>";
				envoiCourriel(subject, texte, destinataire, type);
			}
    	};
    	
		template.query(sql, callbackHandler, false);
	}*/

    public static String constuireObjectMessage(CardexAuthenticationSubject subject, String typeMouvement, String nom, String prenom) throws BusinessResourceException {
    	String objectMessage = constuireObjectMessage(subject, typeMouvement);
    	
		if (GlobalConstants.TypesIntervention.Fin.equals(typeMouvement) == false)
			objectMessage += " " + nom + ", "+ prenom;
		return objectMessage;
	}

    public static String constuireObjectMessage(CardexAuthenticationSubject subject, String typeMouvement) throws BusinessResourceException {
    	ListeCache listeCache = ListeCache.getInstance();
    	return listeCache.obtenirLabel(subject, typeMouvement, new TypeInterventionCle(subject));
	}

    public static String constuireObjectMessageAvecDate(CardexAuthenticationSubject subject, String typeMouvement) throws BusinessResourceException {
		String dateDuJour = DateFormat.format(new Date());
    	ListeCache listeCache = ListeCache.getInstance();
    	return listeCache.obtenirLabel(subject, typeMouvement, new TypeInterventionCle(subject))+" ("+dateDuJour+")";
	}

    
    private static String obtenirSQLRechercheDestinataires(String type, String snSite){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT V_IV_COURRIEL,V_IV_TEXTE_MESSAGE,C_IV_ENVOI_MESSAGE ");
		sql.append("FROM IV_INTERVENTION ");
		sql.append("WHERE V_TI_CODE = '" + type + "' ");
	
		if (StringUtils.isNotEmpty(snSite)) {
			sql.append(" and V_SN_SITE ='" + snSite + "' ");
		}
		sql.append(" AND C_IV_ENVOI_MESSAGE = '1' ");
		return sql.toString();
    }
    
	// On recherche tous les intervenants qui doivent être avisés
	// en fonction du site d'où origine le mouvement.
	// La table GI_GROUPE_INTERVENTION contient les appariements
	// entre les sites d'origine (concerné) et les intervenants (site
	// d'intervention).
    private static String obtenirSQLRechercheGroupeDestinataires(String type, String siteConcerne){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT V_IV_COURRIEL,V_IV_TEXTE_MESSAGE,C_IV_ENVOI_MESSAGE,V_GI_SITE_CONCERNE ");
		sql.append("FROM IV_INTERVENTION, GI_GROUPE_INTERVENTION ");
		sql.append("WHERE V_TI_CODE = '" + type + "' ");
		sql.append(" and V_GI_SITE_INTERVENTION = V_SN_SITE and V_GI_SITE_CONCERNE = '" + siteConcerne + "' ");
		sql.append(" AND C_IV_ENVOI_MESSAGE = '1' ");    
		return sql.toString();
    }
}