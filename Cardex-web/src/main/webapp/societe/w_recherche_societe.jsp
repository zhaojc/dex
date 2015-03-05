<%-- --------------------------------------------------------------------------
Use case    : Recherche de société.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'une societe.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.10 $, $Date: 2002/04/11 19:21:49 $

History     : Voir ci-dessous.

Revision: 1.4 , Date: 2002/03/06 21:53:37 , $Author: abruno-boucher
Création.

$Revision: 1.10 $, $Date: 2002/04/11 19:21:49 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
	//-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + "/societe/search/refresh.do"%>');
}

function doAdd() {
  windowOpenLocation("<%= request.getContextPath() + "/societe/create.do"%>");
}

function doDelete() {
    var msg1 = "<bean:message key='epuration'/>";
    var msg2 = "<bean:message key='epuration2'/>";
    var msg3 = "<bean:message key='epuration3'/>";
    var isYes = confirm(msg1 + "\n" + msg2 + "\n" + msg3);
    if (isYes){
        soumettre('<%= request.getContextPath() + "/societe/delete.do"%>');
    }
}

function doClose() {
	window.close();
}

function doSearch() {
	soumettre('<%= request.getContextPath() + "/societe/search.do"%>');
}

function doPrint() {
//Impression de la page courante des résultats
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doPrintAll() {
//Impression des résultats complets
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES_COMPLET %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       doSearch();
       return false;
    }
//-- On désactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de sécurité
  if (window.event.ctrlKey){
    return false;
  }
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doSoumettreRafraichir();
	}
}

</SCRIPT>


<TITLE><bean:message key='tabpage_societe'/></TITLE>

</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheRetour();">


<html:form action='/societe/search' >
  <jsp:include page='/commun/aide.jsp' flush="true"/>

	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.societes" />
	</tiles:insert>
  
	<tiles:insert page="/templates/societe/tpl_recherche_societe_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/societe/search.do" />
	</tiles:insert>  
  
  <tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
  
  <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
  <jsp:include page="/templates/societe/tpl_recherche_societe_resultats.jsp" flush="true" />
  
  <tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>
<BR>

</BODY>
</HTML>
