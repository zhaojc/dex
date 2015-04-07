package com.lotoquebec.cardexCommun.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class InformationAcces {

	private static final Logger log = (Logger)LoggerCardex.getLogger(InformationAcces.class);
	private static InformationAcces informationLogon = null;
	private static SimpleDateFormat simpleDateFormat = null;
	
	private InformationAcces() {
	}

	public static InformationAcces getInstance(){
		
		if (informationLogon == null){
			informationLogon = new InformationAcces();
			log.getHandlers()[0].setFormatter( new SimpleFormatter() );
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
		}
		return informationLogon;
	}
	
	/*
	 * TS=2012-01-10 16:33:38.942 EST;DEVV=LQ;DEVP=CARDEX;MSGID=1;DEVIP=10.8.79.115;DEVHN=Z500WXPG15551;CAT=1;VERS=1.0;FUNC=LOGON;
	 * PUSER=AMAITRE;EUSER=ACAGENCCHA;SITE=5;SECTEUR=230;SIP=10.2.43.6;
	 * DECRAN=1680x1020;FURETEUR=Microsoft Internet Explorer;VERSIONPOSTE=4.0 (compatible: MSIE 7.0: Windows NT 5.1;;PPOSTE=Cookies-JavaScript-ScriptFileSystemObject-Presse-papier-;RC=succes
	 */
	public synchronized void inscrireLogon(CardexAuthenticationSubject subject, HttpServletRequest request){
		StringBuilder msg = new StringBuilder();
		CardexUser cardexUser = (CardexUser)subject.getUser();
		
		cardexUser.setFureteur( request.getParameter("appName") );
		cardexUser.setVersionPoste( StringUtils.left(request.getParameter("appVersion"), 41) );
		cardexUser.setDimentionEcran( request.getParameter("availWidth") + "x" + request.getParameter("availHeight") );
		cardexUser.setAdresseIP( request.getParameter("remoteAddr") );
		cardexUser.setCookiesActif( Boolean.parseBoolean( request.getParameter("cookiesActif") ) );
		cardexUser.setJavaActif( Boolean.parseBoolean( request.getParameter("javaActif") ) );
		cardexUser.setScriptFileSystemObjectActif( Boolean.parseBoolean( request.getParameter("scriptFileSystemObjectActif") ) );
		cardexUser.setClipBoardActif(Boolean.parseBoolean( request.getParameter("clipBoardActif") ) );
		
		msg.append( informationCommune(cardexUser) );
		
		msg.append("FUNC=LOGON;");
		
		msg.append("DECRAN=");
		msg.append(cardexUser.getDimentionEcran());
		msg.append(";");
		
		msg.append("FURETEUR=");
		msg.append(cardexUser.getFureteur());
		msg.append(";");
		
		msg.append("VERSIONPOSTE=");
		msg.append(StringUtils.replace(cardexUser.getVersionPoste(),";",":"));		
		msg.append(";");
		
		StringBuilder problemePoste = new StringBuilder();
		
		if (cardexUser.getCookiesActif() != null && cardexUser.getCookiesActif() == false)
			problemePoste.append("Cookies-");

		if (cardexUser.getJavaActif() != null && cardexUser.getJavaActif() == false)
			problemePoste.append("JavaScript-");
		
		if (cardexUser.getScriptFileSystemObjectActif() != null && cardexUser.getScriptFileSystemObjectActif() == false)
			problemePoste.append("ScriptFileSystemObject-");
		
		if (cardexUser.getClipBoardActif() != null && cardexUser.getClipBoardActif() == false)
			problemePoste.append("Presse-papier");		
		
		msg.append("PPOSTE=");
		msg.append(problemePoste.toString());
		msg.append(";");
		
		msg.append("RC=succes;");
		
		log.fine(msg.toString());
	}
	
	/*
	 * TS=2012-01-10 16:33:38.942 EST;DEVV=LQ;DEVP=CARDEX;MSGID=1;DEVIP=10.8.79.115;DEVHN=Z500WXPG15551;CAT=1;VERS=1.0;
	 * PUSER=AMAITRE;EUSER=ACAGENCCHA;SITE=5;SECTEUR=230;SIP=10.2.43.6;
	 */
	private String informationCommune(CardexUser cardexUser){
		StringBuilder msg = new StringBuilder();
		
		msg.append("TS=");
		msg.append(simpleDateFormat.format(new Date()));
		msg.append(";");

		msg.append("DEVV=LQ;");
		msg.append("DEVP=CARDEX;");
				

		try {
			InetAddress host = InetAddress.getLocalHost();
			
			msg.append("DEVIP=");
			msg.append(host.getHostAddress());
			msg.append(";");
					
			msg.append("DEVHN=");
			msg.append(host.getHostName());
			msg.append(";");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		msg.append("CAT=1;");
		msg.append("VERS=1.0;");

		msg.append("PUSER=");
		msg.append(cardexUser.getCodeParent());
		msg.append(";");
		
		msg.append("EUSER=");
		msg.append(cardexUser.getCode());
		msg.append(";");
		
		msg.append("SITE=");
		msg.append(cardexUser.getSite());
		msg.append(";");
		
		msg.append("SECTEUR=");
		msg.append(cardexUser.getSecteur());
		msg.append(";");
		
		msg.append("SIP=");
		msg.append(cardexUser.getAdresseIP());
		msg.append(";");	
		
		return msg.toString();
	}
	
	/*
	 * TS=2012-01-10 16:34:40.005 EST;DEVV=LQ;DEVP=CARDEX;MSGID=2;DEVIP=10.8.79.115;DEVHN=Z500WXPG15551;CAT=1;VERS=1.0;FUNC=LOGOFF;
	 * PUSER=AMAITRE;EUSER=ACAGENCCHA;SITE=5;SECTEUR=230;SIP=10.2.43.6;RC=succes
	 */
	public synchronized String informationSubject(CardexAuthenticationSubject subject){
		StringBuilder msg = new StringBuilder();
		
		if (subject == null){
			msg.append("Aucun subject ");
			return msg.toString();
		}
		
		CardexUser user = (CardexUser)subject.getUser();
		msg.append(user.getCode());
		msg.append(" (");
		msg.append(user.getCodeParent());
		msg.append(") - Site : ");
		msg.append(user.getSite());
		msg.append(" - Secteur : ");
		msg.append(user.getSecteur());
		msg.append(" - IP: ");
		msg.append(user.getAdresseIP());
		
		return msg.toString();
	}
	
	public synchronized void inscrireLogout(CardexAuthenticationSubject subject){
		StringBuilder msg = new StringBuilder();
		
		CardexUser cardexUser = (CardexUser)subject.getUser();
		
		msg.append( informationCommune(cardexUser) );
		
		msg.append("FUNC=LOGOUT;");
		
		msg.append("RC=succes;");
		
		log.fine(msg.toString());
	}

}
