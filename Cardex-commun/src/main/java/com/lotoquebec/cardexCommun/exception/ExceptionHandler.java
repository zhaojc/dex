package com.lotoquebec.cardexCommun.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.InformationAcces;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.CourrielAction;

public class ExceptionHandler implements UncaughtExceptionHandler{

	private final Logger log = (Logger)LoggerCardex.getLogger((this.getClass()));
	private static ExceptionHandler exceptionHandler = null;
	
	private ExceptionHandler() {
	}

	public static ExceptionHandler getInstance(){
		
		if (exceptionHandler == null)
			exceptionHandler = new ExceptionHandler();
		return exceptionHandler;
	}
	
	public void traiterException(HttpServletRequest request, Throwable throwable){
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		traiterException( subject, throwable );
	}
	
	public void traiterException(HttpServletRequest request, String message, Throwable throwable){
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		traiterException( subject, message, throwable );
	}	
	
	public void traiterException(CardexAuthenticationSubject subject, Throwable throwable){
		traiterException(subject, "", throwable);
	}
	
	public void traiterException(CardexAuthenticationSubject subject, String message, Throwable throwable){
		throwable.printStackTrace();
		LoggerCardex.severe(log, message, throwable);
		
		StringBuilder stringBuilder = new StringBuilder();
	    Writer writer = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(writer);
	    throwable.printStackTrace(printWriter);

	    stringBuilder.append( InformationAcces.getInstance().informationSubject(subject) );
	    stringBuilder.append( "</br></br>" );
	    stringBuilder.append(message);
	    
		String html = writer.toString().replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll("\n", "</BR>");
		html = html.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		stringBuilder.append( "<FONT FACE='Arial, Helvetica'>" );
		stringBuilder.append( html );
		stringBuilder.append( "</FONT>" );
		
		try {
			String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION);
			CourrielAction.envoyerCourrielDestinataire(subject, objectMessage, stringBuilder.toString(), GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION, "A");
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			LoggerCardex.severe(log, message, e);
		} catch (DAOException e) {
			e.printStackTrace();
			LoggerCardex.severe(log, message, e);
		}
		
	}

	public void uncaughtException(Thread thread, Throwable throwable) {
		handle(throwable);
	}
	
	public void handle(Throwable throwable) {     
		  try {     
				CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
				traiterException(subject, throwable);
		  } catch (Throwable t) {       
				  // don't let the exception get thrown out, will cause infinite looping!     
		  }   
	}
	  
	public static void registerExceptionHandler() {     
		Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler.getInstance());     
		System.setProperty("sun.awt.exception.handler", ExceptionHandler.class.getName());   
	} 
}
