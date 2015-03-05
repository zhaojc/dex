
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

<SCRIPT language="JavaScript" type="text/javascript" runat=server>

function setForm() {
	document.forms(0).narrationAvecFormat.value = fenetreNarration.innerHTML;
	document.forms(0).narrationSansFormat.value = remplaceApostrophes(fenetreNarration.innerText);
}

function doOk() {
  setForm();
  unlockFields();
  soumettre('<%= request.getContextPath() + contexteApplicatif.toString() + preContexteApplicatif.toString() + "/narration/add.do"%>');
}

function doCancel() {
  if (fenetreNarration.innerHTML == document.forms(0).narrationAvecFormat.value ){
    window.location = '<%=request.getContextPath()+ contexteApplicatif.toString() + preContexteApplicatif.toString()%>/show.do?site=<bean:write name="narration" property="sujet.site"/>&cle=<bean:write name="narration" property="sujet.cle"/>';
  }else{
    var isYes = confirm("<bean:message key='pfc_closequery_savechanges'/>");
    if (isYes){
      doOk();
    }else{
      window.location = '<%=request.getContextPath()+ contexteApplicatif.toString() + preContexteApplicatif.toString()%>/show.do?site=<bean:write name="narration" property="sujet.site"/>&cle=<bean:write name="narration" property="sujet.cle"/>';
    }
  }
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

	addEvent(window, 'load', atteindreFin);

</SCRIPT>

    <tiles:insert page="/templates/narrations/tiles/tpl_narrations_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"/narration/add.do"%>' />
		<tiles:put name="urlSecuriteApprobation" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"narration/approve.do"%>' />
		<tiles:put name="urlSecuriteModification" value='<%=contexteApplicatif.toString()+preContexteApplicatif.toString()+"/narration/modified.do"%>' />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>

