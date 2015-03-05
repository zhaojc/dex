<%-- --------------------------------------------------------------------------
Use case    : Recherche de dossier.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un dossier.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
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
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doSelect(numeroUnique) {
	soumettre('<%= request.getContextPath() + "/dossier/dossier/lierDossierNumeroCardex.do?"+GlobalConstants.Dossier.NUMERO_UNIQUE+"="%>'+numeroUnique);
}

function doRetour() {
	soumettre('<%= request.getContextPath() + "/dossier/showAcces.do"%>');
}

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<html:form action='/dossier/dossier/lierDossierNumeroCardex' >
	<jsp:include page="/templates/dossier/tpl_onglet_list_choix_dossiers.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
    <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>

</html:form>

</BODY>
</HTML>

