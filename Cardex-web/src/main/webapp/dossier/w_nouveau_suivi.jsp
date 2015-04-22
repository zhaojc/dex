<%-- --------------------------------------------------------------------------
Use case    : Ajout d'un suivi.
Description : Écran de saisie d'un nouveau suivi.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/18 21:17:41 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/18 21:17:41 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%
   //-- L'appel suivant génère une des chaînes de caractère suivante:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.
   String var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

var hasChanged = false;

function doRefresh() {
	unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/create/refresh.do"%>');
}

function doOk() {
	unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/add.do"%>');
}

function doAjouter() {
	unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/ajouter.do"%>');
}

function doCancel() {
  if (document.forms(0).hasChanged == false ){
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="suivi" property="lienSite"/>&cle=<bean:write name="suivi" property="lien"/>';
  }else{
    var isYes = confirmation("<bean:message key='pfc_closequery_savechanges'/>");
    if (isYes){
      doOk();
    }else{
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="suivi" property="lienSite"/>&cle=<bean:write name="suivi" property="lien"/>';
    }
  }
}

function doComplete() {
	unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/complete.do"%>');
}

function doClose() {
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="suivi" property="lienSite"/>&cle=<bean:write name="suivi" property="lien"/>';
}

</SCRIPT>

<title><bean:message key='Suivi'/></title>

</head>
<body
  leftmargin="0" rightmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<input type="hidden" name="clipboard" value="required">
  <html:form action="/dossier/suivi/add"  >
<input type="hidden" name="clipboard" value="required">
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
    
    <jsp:include page="/templates/tpl_erreur_centre.jsp" flush="true" />
    
    
    <tiles:insert page="/templates/suivis/tpl_suivi_formulaire.jsp" flush="true">
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>

    
  </html:form>

</body>
</html>





