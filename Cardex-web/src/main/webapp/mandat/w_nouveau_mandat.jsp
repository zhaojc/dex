<%-- --------------------------------------------------------------------------
Use case    : Création d'un nouveau mandat.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
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

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
function doRefresh() {
	unlockFields();
	soumettre('<%= request.getContextPath() + "/mandat/create/refresh.do"%>');
}

function doOk() {
	unlockFields();
    soumettre('<%= request.getContextPath() + "/mandat/save.do"%>');
}

function doClose() {
    windowClose();
}

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"  >
  
<html:form action='/journal/save' >
	<jsp:include page="/templates/mandat/tpl_onglet_mandat_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/mandat/tpl_mandat_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/mandat/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>	
	
	<jsp:include page="/templates/mandat/tpl_erreur.jsp" flush="true" />
</html:form>

