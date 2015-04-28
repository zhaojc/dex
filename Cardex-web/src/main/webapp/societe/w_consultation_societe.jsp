<%-- --------------------------------------------------------------------------
Use case    : Résultat de société
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives au résultat d'une société.
Author(s)   : $Author: mdemers $, abruno-boucher, mdemers
Revision    : $Revision: 1.10 $, $Date: 2002/04/08 16:33:48 $

History     : Voir ci-dessous.

Revision: 1.3 , Date: 2002/03/07 16:40:29 , $Author: abruno-boucher
Création.

Revision: 1.7 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.10 $, $Date: 2002/04/08 16:33:48 $, $Author: mdemers $
Commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration" %>
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


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/IEUploader.js"></SCRIPT>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_consultationSociete_styles.css">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<jsp:include page='/scripts/ScriptsOnglets.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doConfirmLinkSuppression() {
  return confirmation('<bean:message key="cardex_suppression" />');
}

function doSoumettreRafraichir() {
	//-- Fonction déclarée dans lq_scripts.js
	unlockFields();
	soumettre('<%= request.getContextPath() + "/societe/refresh.do"%>');
}

function doSoumettreRafraichirConsultation() {
	unlockFields();
	soumettre('<%= request.getContextPath() + "/societe/rafraichirConsultation.do"%>');
}

function doOk() {
  unlockFields();
  //-- Fonction déclarée dans lq_scripts.js
  soumettre('<%= request.getContextPath() + "/societe/update.do"%>');
}

function doClose() {
	soumettre('<%= request.getContextPath() + "/societe/retour.do"%>');
}

function doAuditAcces() {
//Impression de l'audit des accès
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var url = "<%=request.getContextPath()+"/RapportAffichage?rapportFormClass="+GlobalConstants.RapportForm.SOCIETE_AUDIT_ACCES_RAPPORT_FORM_CDX_0212%>&cle="+cle+"&site="+site; 
	   	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doAuditChangement() {
//Impression de l'audit des changements
	   var rapport = "<%= RapportsConfiguration.AUDIT_CHANGEMENTS_SOCIETES %>";
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&UTILISATEUR=" + userCardex; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doPrint() {
	   var fiche = "SO";
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichagePDFFiches?FICHE=" + fiche + "&SITE=" + site + "&UTILISATEUR=" + userCardex + "&CLE=" + cle;
	   var rapport = "<%= GlobalConstants.ChoixRapport.IMPRESSION_SOCIETE %>";
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=yes,toolbar=no,resizable=yes');
	
}

function doConfidentialite8(){
//Avertissement à l'utilisateur pour ajouter des précisions
  if (document.forms(0).confidentialite.value == "<%= GlobalConstants.Confidentialite.HUIT %>" ) {
  	 message("Ne pas oublier d'inscrire la raison (erreur ou doublon) de la mise en confidentialité 8 dans Raison d'être (effacer le contenu du champ si une inscription y apparait et la remplacer par la raison).");
  }
}

</SCRIPT>

<!-- Le titre est peuplé dynamiquement dans
     la page tpl_societe_formulaire  -->
<TITLE><bean:message key='tabpage_societe'/></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
  onLoad="onLoadToggleDivisionVisibility('<bean:write name="societe" property="ongletDefaut" />');" >

<html:form action='/societe/update' enctype="multipart/form-data" >
    <jsp:include page="/templates/societe/tpl_onglet_societe_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/societe/tpl_societe_formulaire.jsp" flush="true">
		<tiles:put name="soumettreSauvegarde" value="/societe/update.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
        <!-- ------------------------------ -->
        <!-- BEGINNING OF DYNAMIC DIVISIONS -->
            <jsp:include page="/templates/societe/tpl_menu_onglets_societe.jsp" flush="true" />
      <DIV id="DIVISIONS_GROUP">
            <jsp:include page="/templates/societe/tpl_onglet_list_narrations.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_dossiers.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_sujets.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_societes.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_addresses.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_photos.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_vehicules.jsp" flush="true" />
            <jsp:include page="/templates/societe/tpl_onglet_list_proprietaires.jsp" flush="true" />
      </DIV>
      <!-- END DIVISIONS_GROUP -->
</html:form>

<SCRIPT type="text/javascript">
setShow("menuOnglets");
</SCRIPT>

</BODY>
</HTML>
