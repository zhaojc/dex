<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une narration.
Description : Écran de saisie de nouvelles narrations.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.18 $, $Date: 2002/04/19 20:00:14 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/21 15:35:42 , Author: abruno-boucher
Localisation Assistant date.

$Revision: 1.18 $, $Date: 2002/04/19 20:00:14 $, $Author: mlibersan $
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.18 $, $Date: 2002/04/19 20:00:14 $, $Author: mlibersan $
Derniers commentaires à jour.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %> 
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
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
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
<!-- meta http-equiv="Page-Exit" content="progid:DXImageTransform.Microsoft.gradientWipe(duration=1)" -->

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/permettreClipboard.js"></SCRIPT>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">
	toucheCTRL = true;
	antidote = false; //variable globale pour savoir si Antidote a été utilisé. Sinon, on affiche un message à la sauvegarde de la narration.


<logic:equal name='narration' property='modifiable' value='false' >
	antidote = true; // Le cas où l'utilisateur a accès à la section du haut, sans avoir la section de la narration.
	// Pas de section edition de narration, pas d'antidote.
</logic:equal>

function setForm() {
	//--document.forms(0).HTMLEditor.setContentType("text/html");
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() { 
  //Vérification si Antidote a été utilisé. Sinon, on affiche un rappel.
	try{
	  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
	  if((!antidote) && (oGestionnaireAntidote != null)){
			vbMsgAntidote("<bean:message key='rappel_antidote'/>","Antidote");
	  }else{
	      //Antidote a été utilisé, on sauvegarde.
		  setForm();
		  unlockFields();
		  soumettre('<%= request.getContextPath() + "/dossier/narration/update.do"%>');
	  }
	}catch(err){
	  //Antidote n'est pas installé. On procède à la sauvegarde.
		  setForm();
		  unlockFields();
		  soumettre('<%= request.getContextPath() + "/dossier/narration/update.do"%>');
	}
}

var fenetreNarrationSauvegardable = true;

function doCancel() {
//  if (document.forms(0).modifiable.value == "false"){
  if (fenetreNarrationSauvegardable == false){
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
  }else{
    if ((fenetreNarration.innerHTML != document.forms(0).narrationAvecFormat.value)) {
	    //On utilise une boîte de dialogue VbScript pour avoir trois boutons : Oui, Non et Annuler
	    //Ces trois boutons ne sont pas possibles en Javascript
	    //Le bouton Annuler sert désormais à demeurer dans la narration. Auparavant, le bouton ramenait le dossier,
	    //ce qui pouvait causer des pertes de données si l'utilisateur avait cliqué le bouton Retour par erreur.
	    vbMsg("<bean:message key='pfc_closequery_savechanges'/>","Confirmation");
    }else{
	 if (document.forms(0).tempsConsacre.value == "") {
	       var isYes = confirm("<bean:message key='pfc_closequery_savechanges'/>");
	       if (isYes){
		 		doOk();
	       }else{
		 		window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
	       }
         }else{
	     	window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
	 }
    }
  }
}

function doRetour(){
	  window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
}

function doApprobation() {
  unlockFields();
  setForm();
  soumettre('<%= request.getContextPath() + "/dossier/narration/approve.do"%>');
}

function doModification() {
  unlockFields();
  setForm();
  soumettre('<%= request.getContextPath() + "/dossier/narration/modified.do"%>');
}

function doClose() {
  windowClose();
}

function doPrint() {
  //-- alert("Fonction non disponible pour le moment ...");
  windowOpenLocation('<%= request.getContextPath() %>/narrations/w_impression_narration_frameset.jsp');
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
	  atteindreFin();
	}
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

function atteindreFin() {
	fenetreNarration.focus();
	fenetreNarration.click();
	var oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{PGDN}{PGDN}{PGDN}");
	fenetreNarration.focus();
	fenetreNarration.click();
	cursorDefault();
}

function bloqueBackspace() {

//On désactive également la touche "Backspace" en dehors d'un champ texte pour éviter
//par mégarde de perdre la narration (cette touche rappelle la page précédente en mode web).
	if(window.event.keyCode == 8 && window.document.activeElement.type != "text" 
	   && window.document.activeElement.id != "fenetreNarration"){
   		//alert(window.document.activeElement.type + " - " + window.document.activeElement.name + " - " + window.document.activeElement.id);
   		event.returnValue=false;
	}
}

function doAjoutSujet(){
  unlockFields();
  setForm();
  soumettre('<%= request.getContextPath() + "/dossier/narration/ajoutSujet.do"%>');
}

function verificationCTRL(){
//Si la fenêtre est approuvée ou non modifiable, on interdit la touche CTRL.
//alert(document.forms(0).dateApprobation.value + " - " + fenetreNarration.isContentEditable);
     if (document.forms(0).dateApprobation.value != "" || !fenetreNarration.isContentEditable){
		toucheCTRL = false;
	 }	
}

function lancerOutil(){
  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
   oGestionnaireAntidote.LanceOutil("IEFrame", "Correcteur");
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

Function vbMsgAntidote(isTxt,isCaption)
isChoice = MsgBox(isTxt,3,isCaption)
If isChoice = 6 Then 'Oui
	selectionnerTout()
	antidote = true
	lancerOutil()
End If
If isChoice = 7 Then 'Non
    setForm()
    unlockFields()
	antidote = true
    doOk()
End If
End Function

</Script>
<STYLE>
	.time    {behavior: url(#default#time2);}
</STYLE>

<title></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
  onload="verificationCTRL();" onkeydown="bloqueBackspace();toucheCTRLNarration();">

  <html:form action="/dossier/narration/update"  >
    <jsp:include page="/templates/narrations/tpl_onglet_narration_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page="w_consultation_narration_dossier_formulaire.jsp" flush="true" />
  </html:form>

</body>
</html>





