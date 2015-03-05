<%-- --------------------------------------------------------------------------
Use case    : Recherche de sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/19 20:00:12 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/04/19 20:00:12 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

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

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
function doReset() {
	soumettre('<%= request.getContextPath() + "/societe/sujet/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/societe/sujet/search/refresh.do"%>');
}

function doAdd() {
	var roleSujet = document.getElementById("roleLiaison");
    if((roleSujet.value != "") && (roleSujet.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/sujet/new.do?role="%>'+roleSujet.value);
	}else{
		alert("<bean:message key='choix.role'/>");
	}
}

function doSearch() {
	soumettre('<%= request.getContextPath() + "/societe/sujet/search.do"%>');
}

function doSelectVille() {
  alert("Fonction non disponible pour le moment ...");
}

function doClose() {
	window.location='<cardex:writeObjectURL object="rechercheSujet" objectProperty="societe" page="/societe/show.do" />';
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

function doTraits() {
//-- Calcul de l'âge et ajout de traits d'union dans la date, s'il y a lieu.
   naissance = document.forms(0).dateNaissance.value;
   if(naissance.length == 8){
      if("<%= var_lang %>" == "fr"){
 	naissance = naissance.substring(0,4)+"-"+naissance.substring(4,6)+"-"+naissance.substring(6,8);
      }else{
 	naissance = naissance.substring(0,2)+"/"+naissance.substring(2,4)+"/"+naissance.substring(4,8);
      }
      document.forms(0).dateNaissance.value = naissance;
   }
//alert(document.forms(0).dateNaissance.value.substring(5,7));
}

function doRafraichir(liste, cle) {
	var listeSource = "pays";
	var url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
	doRafraichirListe(listeSource, "province", url);
}

</SCRIPT>

<TITLE><bean:message key='tabpage_sujet'/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
      onKeyDown="return toucheRetour();">


<html:form action='/sujet/search' >
        <html:hidden name='rechercheSujet' property='societe.cle' />
        <html:hidden name='rechercheSujet' property='societe.site' />
	
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.sujets" />
	</tiles:insert>
	
	<tiles:insert page="/templates/sujet/tpl_recherche_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/societe/sujet/search.do" />
	</tiles:insert>	
	
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/societe/tpl_liaison_sujet_resultats.jsp" flush="true" />
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
	
</html:form>

<BR>

</BODY>
</HTML>