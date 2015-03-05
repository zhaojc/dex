<%-- --------------------------------------------------------------------------
Use case    : �cran de recherche des consignations.
Description : �cran 800 X 600 des consignations.
              Le r�sultat et la saisies de commentaires
              s'effectuent dans la m�me fen�tre.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/08 16:33:55 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.4 $, $Date: 2002/04/08 16:33:55 $, $Author: mdemers $
Cr�ation HTML.
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">
  //document.oncontextmenu = function(){return false}

</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
  //--  D�claration d'une variable globale afin de permettre la fermeture automatique
  //--  de la fen�tre d'assistant date lorsque la page se ferme.
  var vWinCal;
</SCRIPT>

<SCRIPT FOR=window EVENT=onunload>
  //-- L'�v�nement onUnload est appel� lors d'un submit, d'un refresh
  //-- ou la fermeture de la fen�tre du navigateur.  Valide seulement pour I.E.

  //-- alert("L'�v�nement unload a �t� appel�.");
  //-- Si l'assistant date est encore ouvert, le fermer.;
  if (vWinCal) { vWinCal.close(); }
</SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/consignation/search/refresh.do"%>');
}

function doClose() {
	window.close();
}

function doPrint() {
  //windowOpenLocation('<%= request.getContextPath() %>/consignation/w_impression_consignation_resultats_frameset.jsp');
//Impression de la page courante des r�sultats
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_CONSIGNATIONS%>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
  
}

function doTraits(objet,nomProchainChamp) {
	//-- Insertion de traits d'union dans les dates, s'il y a lieu.
	   dateSaisie = objet.value;
	   if(dateSaisie.length == 8 && dateSaisie.indexOf("-") == '-1'){
	      if("<%= var_lang %>" == "fr"){
	 	dateSaisie = dateSaisie.substring(0,4)+"-"+dateSaisie.substring(4,6)+"-"+dateSaisie.substring(6,8);
	      }else{
	        if(document.forms(0).elements(3).value == ""){ //-- On ne formatte pas en anglais le num�ro de dossier
		    dateSaisie = dateSaisie.substring(0,2)+"/"+dateSaisie.substring(2,4)+"/"+dateSaisie.substring(4,8);
		}
	      }
	    objet.value = dateSaisie;
	    var prochainChamp = document.getElementsByName( nomProchainChamp )[0];
			prochainChamp.focus();
	   }
}

function toucheRetour() {
//-- D�clenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
  	//Si un rapport a �t� s�lectionn�, on le met � blanc avant la recherche, sinon le rapport sera ex�cut� avec une erreur de s�curit�. 
	  vider("choixRapport");
	  soumettre('<%= request.getContextPath() + "/consignation/search.do"%>');
      return false;
    }
//-- On d�sactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de s�curit�
  if (window.event.ctrlKey){
    //window.event.keyCode = 0;
    return false;
  }
}

function viderListe(liste){
//On vide la liste d'abord avant d'ins�rer les valeurs retourn�es
	var oOption = document.createElement("OPTION");
	oOption.text = "";
	oOption.value = "";
  if(liste == 'categorie'){
  	document.forms(0).type.value = ""; 
	document.forms(0).categorie.add(oOption); 
  }
  if(liste == 'type'){
	document.forms(0).type.add(oOption); 
  }
}



</SCRIPT>
<title><bean:message key='recherche_consignation_t'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
      onKeyDown="return toucheRetour();">
<bean:define id="form" name="rechercheConsignation" type="com.lotoquebec.cardex.presentation.model.form.CriteresRechercheConsignationForm"/>

<html:form action='/suivi/search' >
        <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
	    <jsp:include page='/commun/aide.jsp' flush="true"/>
		<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
			<tiles:put name='keyTitre' content="recherche.consignations" />
		</tiles:insert>	    
        <jsp:include page="/templates/consignation/tpl_recherche_consignation_formulaire.jsp" flush="true" />
	    <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	    <jsp:include page="/templates/consignation/tpl_recherche_consignation_resultats.jsp" flush="true" />
	    <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>
	<tiles:insert page="/templates/commun/tpl_rapport.jsp" flush="true">
	  	<tiles:put name="form" value='<%=form%>'/>
  	</tiles:insert>
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />

</html:form>

</BODY>
</HTML>
