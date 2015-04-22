<%-- --------------------------------------------------------------------------
Use case    : Recherche de société
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'une société.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/22 17:37:04 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.4 $, $Date: 2002/04/22 17:37:04 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>
<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettre('<%= request.getContextPath() + "/dossier/societe/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/dossier/societe/search/refresh.do"%>');
}

function doAdd() {
	var roleSociete = document.getElementById("roleLiaison");
    if((roleSociete.value != "") && (roleSociete.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/societe/create.do?role="%>'+roleSociete.value);
	}else{
		message("<bean:message key='choix.role'/>");
	}
}

function doSearch() {
	soumettre('<%= request.getContextPath() + "/dossier/societe/search.do"%>');
}

function doSelectVille() {
  message("Fonction non disponible pour le moment ...");
}

function doClose() {
	window.location='<cardex:writeObjectURL object="rechercheSociete" objectProperty="dossier" page="/dossier/show.do" />';
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

<html:form action='/dossier/societe/search' >
        <html:hidden name='rechercheSociete' property='dossier.cle' />
        <html:hidden name='rechercheSociete' property='dossier.site' />
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.societes" />
	</tiles:insert>
	
	<tiles:insert page="/templates/societe/tpl_recherche_societe_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/dossier/societe/search.do" />
	</tiles:insert>
	
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/dossier/tpl_liaison_societe_resultats.jsp" flush="true" />
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />	
	
</html:form>

<BR>

</BODY>
</HTML>