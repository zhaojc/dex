<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une narration.
Description : �cran de saisie de nouvelles narrations.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/19 20:00:14 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/21 15:35:42 , Author: abruno-boucher
Localisation Assistant date.

$Revision: 1.3 $, $Date: 2002/04/19 20:00:14 $, $Author: mlibersan $
Mise � jour nouvel Assistant date-heure (incorpor� dans la page).

$Revision: 1.3 $, $Date: 2002/04/19 20:00:14 $, $Author: mlibersan $
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
   //-- L'appel suivant g�n�re une des cha�nes de caract�re suivante:  fr, en, sp, de, it, ...
   //-- et utilis� pour l'appel de fichiers localis�s.
   String var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-15">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/permettreClipboard.js"></SCRIPT>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">
toucheCTRL = true;
function setForm() {
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() {
  unlockFields();
  setForm();
  soumettre('<%= request.getContextPath() + "/vehicule/narration/update.do"%>');
}

function doCancel() {
//--  if (document.forms(0).HTMLEditor.isChanged() == false){
  if (fenetreNarration.isContentEditable == false){
    window.location = '<%=request.getContextPath()%>/vehicule/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
  }else{
    if(fenetreNarration.innerHTML != document.forms(0).narrationAvecFormat.value){
       var isYes = confirm("<bean:message key='pfc_closequery_savechanges'/>");
       if (isYes){
         doOk();
       }else{
         window.location = '<%=request.getContextPath()%>/vehicule/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
       }
    }else{
       window.location = '<%=request.getContextPath()%>/vehicule/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
    }
  }
}

function doApprobation() {
  setForm();
  soumettre('<%= request.getContextPath() + "/vehicule/narration/approve.do"%>');
}

function doModification() {
  setForm();
  soumettre('<%= request.getContextPath() + "/vehicule/narration/modified.do"%>');
}

function doClose() {
  windowClose();
}

function doPrint() {
//--  alert("Fonction non disponible.");
  windowOpenLocation('<%= request.getContextPath() %>/narrations/w_impression_narration_frameset_vehicule.jsp');
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
	  fenetreNarration.focus();
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

<title></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheCTRLNarration();" >

  <html:form action="/vehicule/narration/update"  >
    <jsp:include page="/templates/narrations/tpl_onglet_narration_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    
    <tiles:insert page="/templates/narrations/tpl_narrations_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/vehicule/narration/update.do" />
		<tiles:put name="urlSecuriteApprobation" value="/vehicule/narration/approve.do" />
		<tiles:put name="urlSecuriteModification" value="/vehicule/narration/modified.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>
	
  </html:form>

</body>
</html>





