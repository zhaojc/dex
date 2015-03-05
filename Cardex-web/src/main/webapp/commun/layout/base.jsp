<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>

<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<tiles:useAttribute name="titre" id="titre" classname="String"/>
<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>
<tiles:useAttribute name="action" id="action" classname="String"/>
<tiles:useAttribute name="actionMethod" id="actionMethod" classname="String"/>

<html:html >
<HEAD>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_resultatSujet_styles.css">

<style type="text/css" >
body{
	cursor: wait;
	}
</style>

<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/lq_scripts.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/windowOpenChilds.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT type="text/javascript">

	var userCardex = "<bean:write name='<%= AuthenticationSubject.class.getName() %>' property='user.code' />";

	
	function customOnLoad(){
		cursorDefault();
	}

	document.oncontextmenu=getOnContextMenu;
	document.onkeydown=desactiveCTRL;

	// Après que la page soit loader
	addEvent(window, 'load', customOnLoad);
	
	// Développement
	if (userCardex == "null"){
		rightClick = true;
		toucheCTRL = true;
	}

	function soumettreForm(){
		
		if ( validerSoumettre() )
			document.<%=form%>.submit();
	}
	
	function soumettreActionMethod(actionMethod){
		var method = document.getElementById("actionMethod");
	
		if ( validerSoumettre() ){
			method.value = actionMethod;
			document.<%=form%>.submit();
		}	
	}	
	
	function post(href){
		document.<%=form%>.action = href;
		document.<%=form%>.submit();
	}
	
	var soumettre1Fois = true;
	function validerSoumettre(){
	
		if (soumettre1Fois){
			cursorWait();
			soumettre1Fois = false;
			return true;
		}else{
			return false;
		}
	}

</SCRIPT>

<SCRIPT FOR = document EVENT = onreadystatechange>
	cursorDefault();
</SCRIPT>

<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>	 

<TITLE><bean:message key='<%=titre.toString()%>' /></TITLE>

</HEAD>
<BODY   link="#095B97"  vlink="#095B97" alink="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />

    <html:form action="<%=action.toString()%>" enctype="multipart/form-data">

      <input type="hidden" id="actionMethod" name="actionMethod" value='<%=actionMethod.toString() %>' />
      <input type="hidden" id="param1" name="param1" value="0" />
      <input type="hidden" id="param2" name="param2" value="0" />      

	  <tiles:insert attribute="header" />
	  <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
	  <jsp:include page='/commun/aide.jsp' flush="true"/>
	  <jsp:include page='/commun/commun.jsp' flush="true"/>
      
      <tiles:insert attribute="content"/>
	      
    </html:form>	

</BODY>
</html:html>
