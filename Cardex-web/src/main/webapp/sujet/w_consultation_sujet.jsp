<%-- --------------------------------------------------------------------------
Use case    : Résultat de sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives au résultat d'un sujet.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.11 $, $Date: 2002/04/08 16:33:53 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.11 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.11 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %> 
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

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

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_resultatSujet_styles.css">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/ScriptsOnglets.jsp' flush="true"/>

<SCRIPT LANGUAGE="JavaScript">

function doConfirmLinkSuppression() {
	unlockFields(); 
	return confirmation('<bean:message key="cardex_suppression" />');
}

function doSoumettreRafraichir() {
	//-- Fonction déclarée dans lq_scripts.js
	unlockFields(); 
	soumettre('<%= request.getContextPath() + "/sujet/rafraichir.do"%>');
}

function doSoumettreRafraichirConsultation() {
    unlockFields();
	soumettre('<%= request.getContextPath() + "/sujet/rafraichirConsultation.do"%>');
}

function doOk() {
	unlockFields();
	detruireOnglet();
  //-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + "/sujet/update.do"%>');
}

function doClose() {
	detruireOnglet();
	//window.close();
	soumettre('<%= request.getContextPath() + "/sujet/retour.do"%>');
}

function doPrint() {
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;

	   var url = "<%=request.getContextPath()+"/RapportAffichage?rapportFormClass="+GlobalConstants.RapportForm.SUJET_RAPPORT_FORM_CDX_0002%>&cle="+cle+"&site="+site;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doExporter(){
//Sert à exporter des données dans un fichier texte pour les besoins de la Sûreté du Québec.
 var url = '<%= request.getContextPath() + "/sujet/printFiche.do"%>';
 window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=yes,toolbar=no,resizable=yes');
}

function getOnglet(){
//On affiche le dernier onglet choisi par l'utilisateur. Par défaut, lors de la
//première ouverture du dossier, on active l'onglet Narration.
   var cookies = document.cookie;
//alert(cookies);   
   if (!cookies){ // no cookies
      return "<bean:write name="sujet" property="ongletDefaut" />";
   }
   
   var search = "sujet=";
   var iLen = cookies.length;
   if (iLen > 0) 
   {
      // find where cookie begins
      var begin = cookies.indexOf(search);
      if (begin != -1)
      {          
         begin += search.length;
         // search for the end of the value
         var end = cookies.indexOf(";", begin);
         if (end == -1)
         {
            // delimiter not found, so eat rest of cookie string
            end = iLen;
         }

         return unescape(cookies.substring(begin, end));
      }
   }

   return "<bean:write name="sujet" property="ongletDefaut" />"; // cookie non trouvé
}

  
function setOnglet(onglet){
//Fontions pour conserver l'onglet activé par l'utilisateur lors du retour du dossier.
//On fixe une expiration automatique à 10 minutes
   var c = new Date();
   c.setTime (c.getTime() + (1000 * 600)); 
  document.cookie = "sujet=" + escape(onglet) + "; expires="+c.toGMTString();
   //alert(document.cookie);       
}

function detruireOnglet(){
//Suppression du cookie à la fermeture de la fenêtre
   var c = new Date();
   c.setTime (c.getTime() - 1); 
   // document.cookie = "onglet" + document.forms(0).cle.value + "=; expires=Thu, 01-Jan-70 00:00:01 GMT";
   document.cookie = "sujet=1; expires="+c.toGMTString();
//alert(document.cookie);
}

function doVerificationNumeroFiche() {
}

function doSelectionnerPhotoGalerie(cle, site){
	soumettreParams('<%= request.getContextPath() + "/sujet/selectionnerPhotoGalerie.do"%>',cle, site);
}

function doAuditAcces() {
//Impression de l'audit des accès
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var url = "<%=request.getContextPath()+"/RapportAffichage?rapportFormClass="+GlobalConstants.RapportForm.SUJET_AUDIT_ACCES_RAPPORT_FORM_CDX_0211%>&cle="+cle+"&site="+site;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doAuditChangement() {
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var url = "<%=request.getContextPath()+"/RapportAffichage?rapportFormClass="+GlobalConstants.RapportForm.SUJET_AUDIT_CHANGEMENTS_SUJETS_CDX_0185%>&cle="+cle+"&site="+site;	    
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doConfidentialite8(){
//Avertissement à l'utilisateur pour ajouter des précisions
  if (document.forms(0).confidentialite.value == "<%= GlobalConstants.Confidentialite.HUIT %>" ) {
  	 message("Ne pas oublier d'inscrire la raison (erreur ou doublon) de la mise en confidentialité 8 dans Référence (effacer le contenu du champ si une inscription y apparait et la remplacer par la raison).");
  }
}

</SCRIPT>

<TITLE><bean:message key='gb_sujet'/></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
  onLoad="onLoadToggleDivisionVisibility(getOnglet());"  >
      
<html:form action='/sujet/update' enctype="multipart/form-data" >
    <input type="hidden" id="param1" name="param1" value="0" />
    <input type="hidden" id="param2" name="param2" value="0" />

	<jsp:include page="/templates/sujet/tpl_onglet_sujet_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/sujet/tpl_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/sujet/update.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />

        <!-- ------------------------------ -->
        <!-- BEGINNING OF DYNAMIC DIVISIONS -->

            <jsp:include page="/templates/sujet/tpl_menu_onglets_sujet.jsp" flush="true" />
      <DIV id="DIVISIONS_GROUP">
            <jsp:include page="/templates/sujet/tpl_onglet_list_narrations.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_dossiers.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_societes.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_sujets.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_addresses.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_caracteristiques.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_photos.jsp" flush="true" />
            <jsp:include page="/templates/sujet/tpl_onglet_list_vehicules.jsp" flush="true" />
      </DIV>
      <!-- END DIVISIONS_GROUP -->
</html:form>
<SCRIPT type="text/javascript">
setShow("menuOnglets");
</SCRIPT>
</BODY>
</HTML>
