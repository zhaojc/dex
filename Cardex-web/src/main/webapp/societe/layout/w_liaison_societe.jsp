<%-- --------------------------------------------------------------------------
Use case    : Recherche de société
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'une société.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/22 17:37:21 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/04/22 17:37:21 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>
<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
function doReset() {
	soumettreActionMethod("resetSearchDefault");
}

function doSoumettreRafraichir() {
	soumettreActionMethod("refreshRechercheSociete");	
}

function doAdd() {
    windowOpenLocation("<%= request.getContextPath()+"/societe/create.do"%>");
}

function doSearch() {
	soumettreActionMethod("search");
}

function doSelectVille() {
  message("Fonction non disponible pour le moment ...");
}

function doClose() {
	post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()%>');
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       doSearch();
       return false;
    }
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doSoumettreRafraichir();
	}
}

addEvent( window, 'keyDown', toucheRetour );

</SCRIPT>


<tiles:insert page="/templates/societe/tpl_recherche_societe_formulaire.jsp" flush="true">
	<tiles:put name="urlSecuriteRecherche" value='<%=contexteApplicatif.toString()+"/societe/search.do"%>' />
</tiles:insert>


<tiles:insert page="/templates/societe/tiles/tpl_liaison_societe_resultats.jsp" flush="true">
	<tiles:put name="preContexteApplicatif" value='<%=contexteApplicatif.toString()%>' direct="true"/>
	<tiles:put name="contexteApplicatif" value="/sujet"/>
	<tiles:put name="sourceProperty" value="sujet"/>
</tiles:insert>

<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
