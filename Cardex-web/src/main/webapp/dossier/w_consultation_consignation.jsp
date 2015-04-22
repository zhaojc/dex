<%-- --------------------------------------------------------------------------
Use case    : Consultation d'une consignation
Description : Écran de consultation d'une consignation
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
<%@ page import="org.apache.struts.util.RequestUtils" %>
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
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

var hasChanged = false;

function doRefresh() {
  unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/consignation/refresh.do"%>');
}

function doOk() {
// S'il s'agit d'un champ approuvable, la dénomination et la devise sont obligatoires.
//alert(document.forms(0).approuvable.checked);
  if(document.forms(0).approuvable.checked == true){
     if((document.forms(0).denomination.value == "") || (document.forms(0).devise.value == "")){
         message("<bean:message key='cardex_consignation_denomination'/>");
     }else{
       unlockFields();
	   soumettre('<%= request.getContextPath() + "/dossier/consignation/update.do"%>');
	 }
  }else{
  	  unlockFields();
      soumettre('<%= request.getContextPath() + "/dossier/consignation/update.do"%>');
  } 
}

function doCancel() {
  if (document.forms(0).hasChanged == false ){
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="consignation" property="lienSite"/>&cle=<bean:write name="consignation" property="lien"/>';
  }else{
    var isYes = confirmation("<bean:message key='pfc_closequery_savechanges'/>");
    if (isYes){
      doOk();
    }else{
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="consignation" property="lienSite"/>&cle=<bean:write name="consignation" property="lien"/>';
    }
  }
}

function doApprobation() {
  unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/consignation/approve.do"%>');
}

function doComplete() {
 unlockFields();
  soumettre('<%= request.getContextPath() + "/dossier/consignation/complete.do"%>');
}

function doClose() {
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="consignation" property="lienSite"/>&cle=<bean:write name="consignation" property="lien"/>';
}

</SCRIPT>

<title></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

  <html:form action="/dossier/consignation/update"  >
    <jsp:include page="/templates/consignation/tpl_onglet_consignation_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
    
    <tiles:insert page="/templates/consignation/tpl_consignation_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/dossier/consignation/update.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	
    
    <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
    
  </html:form>

</body>
</html>
