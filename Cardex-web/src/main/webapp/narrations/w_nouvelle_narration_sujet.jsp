<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une narration.
Description : Écran de saisie de nouvelles narrations.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/18 21:17:54 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.4 , Date: 2002/02/21 23:02:15 , Author: abruno-boucher
Localisation Assistant date.

$Revision: 1.8 $, $Date: 2002/04/18 21:17:54 $, $Author: mlibersan $
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.8 $, $Date: 2002/04/18 21:17:54 $, $Author: mlibersan $
Derniers commentaires à jour.

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
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
<!-- meta http-equiv="Page-Exit" content="progid:DXImageTransform.Microsoft.gradientWipe(duration=1)" -->

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/permettreClipboard.js"></SCRIPT>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript" runat=server>

function setForm() {
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() {
  setForm();
  unlockFields();
  soumettre('<%= request.getContextPath() + "/sujet/narration/add.do"%>');
}

function doCancel() {
  if (fenetreNarration.innerHTML == document.forms(0).narrationAvecFormat.value ){
    window.location = '<%=request.getContextPath()%>/sujet/show.do?site=<bean:write name="narration" property="sujet.site"/>&cle=<bean:write name="narration" property="sujet.cle"/>';
  }else{
    //On utilise une boîte de dialogue VbScript pour avoir trois boutons : Oui, Non et Annuler
    //Ces trois boutons ne sont pas possibles en Javascript
    //Le bouton Annuler sert désormais à demeurer dans la narration. Auparavant, le bouton ramenait le dossier,
    //ce qui pouvait causer des pertes de données si l'utilisateur avait cliqué le bouton Retour par erreur.
    vbMsg("<bean:message key='pfc_closequery_savechanges'/>","Confirmation");
  }
}

function doRetour(){
     window.location = '<%=request.getContextPath()%>/sujet/show.do?site=<bean:write name="narration" property="sujet.site"/>&cle=<bean:write name="narration" property="sujet.cle"/>';
}

function doApprobation() {
  message("Fonction non disponible pour le moment ...");
}

function doModification() {
  message("Fonction non disponible pour le moment ...");
}

function doClose() {
  windowClose();
}

function doPrint() {
  message("Fonction non disponible pour le moment ...");
}

function lireGabarit(gabarit) {
//-- Chargement d'un gabarit de saisie de données
//-- dans la fenêtre de narration
	if(gabarit != ""){
	  var xmlhttp = new ActiveXObject("MSXML2.XMLHTTP"); 
	  serveur = "<%= request.getServerName() %>";
	  port = "<%= request.getServerPort() %>";
	  contexte = "<%= request.getContextPath() %>";
	  chemin = getChemin(serveur,port,contexte);
	  xmlhttp.open("GET", chemin + gabarit, 0); 
	  xmlhttp.send(""); 
	  //alert(xmlhttp.responseText);
	  fenetreNarration.insertAdjacentHTML("beforeEnd", xmlhttp.responseText); 
	  xmlhttp = null; 
	  fenetreNarration.focus();
	}
}

function atteindreFin() {
	fenetreNarration.focus();
	fenetreNarration.click();
	var oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{PGDN}{PGDN}{PGDN}");
	fenetreNarration.focus();
	fenetreNarration.click();
	cursorDefault();
}

function tabulation() {
	fenetreNarration.focus();
	fenetreNarration.click();
	var oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{TAB}{TAB}");
	fenetreNarration.insertAdjacentHTML("beforeEnd", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	fenetreNarration.focus();
	fenetreNarration.click();
}

function toucheCTRLNarration() {
//-- On désactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de sécurité, sauf le copier coller pour les narrations modifiables.
  if (window.event.ctrlKey){
     if (document.forms(0).approuvable.value == "true" || fenetreNarration.isContentEditable){
        if(window.event.keyCode == 68){ //On permet CTRL-D pour insérer la date du jour dans la narration
        	insererDate();
        } 
        if(window.event.keyCode != 67 && window.event.keyCode != 86 && window.event.keyCode != 88){
           //window.event.keyCode = 0;
           event.returnValue = false;
        }
     }else{
           //window.event.keyCode = 0;
	   event.returnValue = false;
     }
  }
}

function insererDate(){
   //Insertion de la date courante dans le texte
   var currentDate = new Date();
   var day = currentDate.getDate();
   if(day < 10){
   	  day = "0" + day;
   }
   var month = currentDate.getMonth() + 1;
   if(month < 10){
   	  month = "0" + month;
   }
   var year = currentDate.getFullYear();
   document.selection.createRange().text=year + "-" + month + "-" + day; 
   
}
</SCRIPT>

<Script Language=VBScript>

Function vbMsg(isTxt,isCaption)

isChoice = MsgBox(isTxt,3,isCaption)
If isChoice = 6 Then 'Oui
	doOk()
End If
If isChoice = 7 Then 'Non
	doRetour()
End If
End Function

</Script>

<title><bean:message key='v_commentaire_t'/></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
   onLoad="atteindreFin();" onKeyDown="return toucheCTRLNarration();">

  <html:form action="/sujet/narration/add"  >
    <html:hidden  name="narration" property="sujet.cle" />
    <html:hidden  name="narration" property="sujet.site" />
    <jsp:include page="/templates/narrations/tpl_onglet_narration_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    
    <tiles:insert page="/templates/narrations/tpl_narrations_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/sujet/narration/add.do" />
		<tiles:put name="urlSecuriteApprobation" value="/sujet/narration/approve.do" />
		<tiles:put name="urlSecuriteModification" value="/sujet/narration/modified.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>

  </html:form>

</body>
</html>
