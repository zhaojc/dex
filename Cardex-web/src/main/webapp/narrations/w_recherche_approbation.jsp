<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des narrations.
Description : Écran 800 X 600 de recherche.
              Le visionnement des narration s'effectue égalemement dans
              la même fenêtre.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/11 15:05:19 $

History     : Voir ci-dessous.

$Revision: 1.4 $, $Date: 2002/04/11 15:05:19 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">
  //document.oncontextmenu = function(){return false}

</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
  //--  Déclaration d'une variable globale afin de permettre la fermeture automatique
  //--  de la fenêtre d'assistant date lorsque la page se ferme.
  var vWinCal;
</SCRIPT>

<SCRIPT FOR=window EVENT=onunload>
  //-- L'évènement onUnload est appelé lors d'un submit, d'un refresh
  //-- ou la fermeture de la fenêtre du navigateur.  Valide seulement pour I.E.

  //-- alert("L'évènement unload a été appelé.");
  //-- Si l'assistant date est encore ouvert, le fermer.;
  if (vWinCal) { vWinCal.close(); }
</SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/approbation/search/refresh.do"%>');
}

function doSearch() {
	soumettre("<%= request.getContextPath() + "/approbation/search.do"%>");
}

function doClose() {
	window.close();
}

function doTraits(objet,nomProchainChamp) {
	//-- Insertion de traits d'union dans les dates, s'il y a lieu.
	   dateSaisie = objet.value;
	   if(dateSaisie.length == 8 && dateSaisie.indexOf("-") == '-1'){
	      if("<%= var_lang %>" == "fr"){
	 	dateSaisie = dateSaisie.substring(0,4)+"-"+dateSaisie.substring(4,6)+"-"+dateSaisie.substring(6,8);
	      }else{
	        if(document.forms(0).elements(3).value == ""){ //-- On ne formatte pas en anglais le numéro de dossier
		    dateSaisie = dateSaisie.substring(0,2)+"/"+dateSaisie.substring(2,4)+"/"+dateSaisie.substring(4,8);
		}
	      }
	    objet.value = dateSaisie;
	    var prochainChamp = document.getElementsByName( nomProchainChamp )[0];
			prochainChamp.focus();
	   }
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

</SCRIPT>
<title><bean:message key='approbations_t'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" 
    onKeyPress="return toucheRetour();">

<html:form action='/approbation/search' >
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    
    <tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.approbations" />
	</tiles:insert>
    
    <jsp:include page='/commun/aide.jsp' flush="true"/>
    <jsp:include page="/templates/narrations/tpl_recherche_approbations_formulaire.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/narrations/tpl_recherche_approbations_resultats.jsp" flush="true" />
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>

</BODY>
</HTML>
