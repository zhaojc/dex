<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />


<SCRIPT language="JavaScript" type="text/javascript">
toucheCTRL = true;

function setForm() {
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() {
  unlockFields();
  setForm();
  soumettre('<%= request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() + "/narration/update.do"%>');
}

function doCancel() {
  if (fenetreNarration.isContentEditable == false){
    window.location = '<%=request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() %>/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
  }else{
    if(fenetreNarration.innerHTML != document.forms(0).narrationAvecFormat.value){
       var isYes = confirm("<bean:message key='pfc_closequery_savechanges'/>");
       if (isYes){
         doOk();
       }else{
         window.location = '<%=request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() %>/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
       }
    }else{
       window.location = '<%=request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() %>/show.do?site=<bean:write name="narration" property="lienSite"/>&cle=<bean:write name="narration" property="lien"/>';
    }
  }
}

function doApprobation() {
  setForm();
  soumettre('<%= request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() + "/narration/approve.do"%>');
}

function doModification() {
  setForm();
  soumettre('<%= request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() + "/narration/modified.do"%>');
}

function doClose() {
  windowClose();
}

function doPrint() {
//--alert("Fonction non disponible.");
  windowOpenLocation('<%= request.getContextPath() %>/narrations/w_impression_narration_frameset_sujet.jsp');
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

function tabulation() {
    if(fenetreNarration.isContentEditable){
	fenetreNarration.focus();
	fenetreNarration.click();
	var oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{TAB}{TAB}");
	fenetreNarration.insertAdjacentHTML("beforeEnd", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	fenetreNarration.focus();
	fenetreNarration.click();
    }
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

	addEvent(window, 'keyDown', toucheCTRLNarration);
	
</SCRIPT>

    <tiles:insert page="/templates/narrations/tpl_narrations_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"/narration/update.do"%>' />
		<tiles:put name="urlSecuriteApprobation" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"/narration/approve.do"%>' />
		<tiles:put name="urlSecuriteModification" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"/narration/modified.do"%>' />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>





