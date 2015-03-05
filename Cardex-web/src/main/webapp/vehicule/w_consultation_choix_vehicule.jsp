<%-- --------------------------------------------------------------------------
Use case    : Recherche directe de v�hicule.
Description : cette page affiche les r�sultats retourn�s par la recherche directe
de v�hicules � partir du num�ro d'immatriculation. Comme ce num�ro n'est pas unique, 
l'utilisateur choisit dans cette page, le v�hicule voulu.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%
   //-- L'appel suivant g�n�re une des cha�nes suivante de caract�res:  fr, en, sp, de, it, ...
   //-- et utilis� pour l'appel de fichiers localis�s.  Gestion d'erreur en cas de timeout
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
	soumettre('<%= request.getContextPath() + "/vehicule/showAcces.do"%>');
}

function doRetour() {
	window.close();
}

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<html:form action='/vehicule/showAcces' >
	<jsp:include page="/templates/vehicule/tpl_choix_vehicule_resultats.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
    <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>

</html:form>

</BODY>
</HTML>

