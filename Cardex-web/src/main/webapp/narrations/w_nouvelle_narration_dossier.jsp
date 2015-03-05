<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une narration.
Description : �cran de saisie de nouvelles narrations.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.17 $, $Date: 2002/04/18 21:17:54 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.4 , Date: 2002/02/21 23:02:15 , Author: abruno-boucher
Localisation Assistant date.

$Revision: 1.17 $, $Date: 2002/04/18 21:17:54 $, $Author: mlibersan $
Mise � jour nouvel Assistant date-heure (incorpor� dans la page).

$Revision: 1.17 $, $Date: 2002/04/18 21:17:54 $, $Author: mlibersan $
Derniers commentaires � jour.

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
   //-- L'appel suivant g�n�re une des cha�nes suivante de caract�res:  fr, en, sp, de, it, ...
   //-- et utilis� pour l'appel de fichiers localis�s.  Gestion d'erreur en cas de timeout
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

<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/permettreClipboard.js"></SCRIPT>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">
toucheCTRL = true;
antidote = false; //variable globale pour savoir si Antidote a �t� utilis�. Sinon, on affiche un message � la sauvegarde de la narration.
function setForm() {
	//document.forms(0).narrationAvecFormat.value = document.forms(0).HTMLEditor.getText();
	//document.forms(0).narrationSansFormat.value = document.forms(0).HTMLEditor.getPlainText();
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() {
  //V�rification si Antidote a �t� utilis�. Sinon, on affiche un rappel.
	try{
	  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
	  if((!antidote) && (oGestionnaireAntidote != null)){
			vbMsgAntidote("<bean:message key='rappel_antidote'/>","Antidote");
	  }else{
	      //Antidote a �t� utilis�, on sauvegarde.
		  setForm();
		  unlockFields();
		  soumettre('<%= request.getContextPath() + "/dossier/narration/add.do"%>');
	  }
	}catch(err){
	  //Antidote n'est pas install�. On proc�de � la sauvegarde.
		  setForm();
		  unlockFields();
		  soumettre('<%= request.getContextPath() + "/dossier/narration/add.do"%>');
	}
}

function doCancel() {
//  if (document.forms(0).HTMLEditor.isChanged() == false ){
  if (fenetreNarration.innerHTML == document.forms(0).narrationAvecFormat.value) {
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="dossier.site"/>&cle=<bean:write name="narration" property="dossier.cle"/>';
  }else{
    //On utilise une bo�te de dialogue VbScript pour avoir trois boutons : Oui, Non et Annuler
    //Ces trois boutons ne sont pas possibles en Javascript
    //Le bouton Annuler sert d�sormais � demeurer dans la narration. Auparavant, le bouton ramenait le dossier,
    //ce qui pouvait causer des pertes de donn�es si l'utilisateur avait cliqu� le bouton Retour par erreur.
    vbMsg("<bean:message key='pfc_closequery_savechanges'/>","Confirmation");
  }
}

function doRetour(){
      window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="narration" property="dossier.site"/>&cle=<bean:write name="narration" property="dossier.cle"/>';
}

function doApprobation() {
  alert("Fonction non disponible pour le moment ...");
}

function doModification() {
  alert("Fonction non disponible pour le moment ...");
}

function doClose() {
  windowClose();
}

function doPrint() {
  alert("Fonction non disponible pour le moment ...");
}

function lireGabarit(gabarit) {
//-- Chargement d'un gabarit de saisie de donn�es
//-- dans la fen�tre de narration
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

function toucheCTRLNarration() {
//-- On d�sactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de s�curit�, sauf le copier coller pour les narrations modifiables.
  if (window.event.ctrlKey){
     if (document.forms(0).approuvable.value == "true" || fenetreNarration.isContentEditable){
        if(window.event.keyCode == 68){ //On permet CTRL-D pour ins�rer la date du jour dans la narration
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
//On d�sactive �galement la touche "Backspace" en dehors d'un champ texte pour �viter
//par m�garde de perdre la narration (cette touche rappelle la page pr�c�dente en mode web).
	if(window.event.keyCode == 8 && window.document.activeElement.type != "text" 
	   && window.document.activeElement.id != "fenetreNarration"){
   		//alert(window.document.activeElement.type + " - " + window.document.activeElement.name + " - " + window.document.activeElement.id);
   		event.returnValue=false;
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

function lancerOutil(){
  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
   oGestionnaireAntidote.LanceOutil("IEFrame", "Correcteur");
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

<title><bean:message key='v_commentaire_t'/></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onLoad="atteindreFin();" onKeyDown="return toucheCTRLNarration();">

  <html:form action="/dossier/narration/add"  >
    <html:hidden  name="narration" property="dossier.cle" />
    <html:hidden  name="narration" property="dossier.site" />
    <jsp:include page="/templates/narrations/tpl_onglet_narration_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>

    <tiles:insert page="/templates/narrations/tpl_narrations_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/dossier/narration/add.do" />
		<tiles:put name="urlSecuriteApprobation" value="/dossier/narration/approve.do" />
		<tiles:put name="urlSecuriteModification" value="/dossier/narration/modified.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>    
	<!-- jsp:include page="/templates/tpl_erreur.jsp" flush="true" / -->
  </html:form>

</body>
</html>
