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
  soumettre('<%= request.getContextPath() + "/dossier/suivi/refresh.do"%>');
}

function doOk() {
  unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/update.do"%>');
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

function doAjouter() {
  unlockFields(); 
  soumettre('<%= request.getContextPath() + "/dossier/suivi/ajouter.do"%>');
}

function doComplete() {
  unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/suivi/complete.do"%>');
}

function doClose() {
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="suivi" property="lienSite"/>&cle=<bean:write name="suivi" property="lien"/>';
}

function doDatePrevue() {
//On protège la date prévue selon le type d'activité
	if(((document.forms(0).activite.value == '<%= GlobalConstants.TypeSuivi.SUIVI_24_HEURES %>') || 
		(document.forms(0).activite.value == '<%= GlobalConstants.TypeSuivi.DELAI_21_JOURS %>') ||
		(document.forms(0).activite.value == '<%= GlobalConstants.TypeSuivi.DELAI_30_JOURS %>')) && 
	    (document.forms(0).lienSite.value == '<%= GlobalConstants.Sites.LOTO_QUEBEC %>')){
		document.forms(0).datePrevue.disabled = true;
		var calendrier = document.getElementById('calDatePrevue');
   		calendrier.style.visibility = 'hidden';
	}
}

</SCRIPT>

<title><bean:message key='Suivi'/></title>

</head>
<body onload="doDatePrevue();"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

  <html:form action="/dossier/suivi/update"  >
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
    
    <jsp:include page="/templates/tpl_erreur_centre.jsp" flush="true" />
    
   	<tiles:insert page="/templates/suivis/tpl_suivi_formulaire.jsp" flush="true">
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>
    
    
  </html:form>

</body>
</html>
