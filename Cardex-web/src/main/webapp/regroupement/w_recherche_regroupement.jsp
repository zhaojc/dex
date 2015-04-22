<%-- --------------------------------------------------------------------------
Use case    : Production des rapports de regroupement
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.ConstantRegroupement" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.regroupement.RegroupementRapport" %>
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

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function doRefresh() {
	soumettre('<%=request.getContextPath()%>/regroupement/search/refresh.do');
}

function doSearch() {
	soumettre("<%= request.getContextPath() + "/regroupement/search/default.do"%>");
}

function doClose() {
	windowClose();
}

//Impression des rapports
function doPrint() {
    unlockFields();
	var choixRapport = document.getElementsByName("choixRapport")[0];

    if ((isPlusUnAn()) && (choixRapport.value != '<%= ConstantRegroupement.MATRICE_REGROUPEMENTS %>')){
    	if (confirmation("Votre requête pourrait prendre plusieurs minutes pour s'exécuter.\rDésirez-vous poursuivre l'exécution?")){
    		soumettre("<%= request.getContextPath() + "/regroupement/search/valider.do"%>");
    	}
    }else{
    	soumettre("<%= request.getContextPath() + "/regroupement/search/valider.do"%>");
    }
}
<% if ("fr".equals(var_lang)){ %>
function getDate(element){
	var strDate = document.getElementsByName(element)[0].value;
	var annee = parseInt(strDate.substring(0,4),10);
	var mois = parseInt(strDate.substring(5,7),10)-1;
	var jour = parseInt(strDate.substring(8,10),10);
	var date = new Date(annee, mois, jour);
	return date;
}
<%} else {%>
//var templateDateHeure = "__/__/____ __:__:__";
function getDate(element){
	var strDate = document.getElementsByName(element)[0].value;
	var annee = parseInt(strDate.substring(6,10),10);
	var mois = parseInt(strDate.substring(3,5),10)-1;
	var jour = parseInt(strDate.substring(0,2),10);
	var date = new Date(annee, mois, jour);
	return date;
}
<%}%>

function isPlusUnAn(){
	var dateDebut = getDate("dateCreationDu");
	var dateFin = getDate("dateCreationAu");
	var dateDebutPlus1An = new Date(dateDebut);
	dateDebutPlus1An.setYear(dateDebut.getYear()+1);
	return dateDebutPlus1An < dateFin;
}

function construireCriteresURL(choixRapport){
	var url = "<%=request.getContextPath()%>/CritereRapportAffichagePDF?";
	url += "choixRapport="+choixRapport;
	url += "&tableValeurRapport=<%=GlobalConstants.TableValeur.RAPPORT_REGROUPEMENT%>";
	url += construireParametre("<%=RegroupementRapport.ENTITE%>");
	url += construireParametre("<%=RegroupementRapport.SITE%>");
	url += construireParametre("<%=RegroupementRapport.GENRE%>");
	url += construireParametre("<%=RegroupementRapport.NATURE%>");
	url += construireParametre("<%=RegroupementRapport.TYPE%>");
	url += construireParametre("<%=RegroupementRapport.CATEGORIE%>");
	url += construireParametre("<%=RegroupementRapport.INTERVENANT%>");
	url += construireParametre("<%=RegroupementRapport.SECTEUR%>");
	url += construireParametre("<%=RegroupementRapport.DATE_CREATION_DU%>");
	url += construireParametre("<%=RegroupementRapport.DATE_CREATION_AU%>");
	url += construireParametre("<%=RegroupementRapport.ENDROIT%>");
	url += construireParametre("<%=RegroupementRapport.REGROUPEMENT%>");
	
	return url;
}

function doTraits(objet) {
//-- Insertion de traits d'union dans les dates, s'il y a lieu.
   dateSaisie = objet.value;
   if(dateSaisie.length == 8){
      if("fr" == "fr"){
 	dateSaisie = dateSaisie.substring(0,4)+"-"+dateSaisie.substring(4,6)+"-"+dateSaisie.substring(6,8);
      }else{
        dateSaisie = dateSaisie.substring(0,2)+"/"+dateSaisie.substring(2,4)+"/"+dateSaisie.substring(4,8);
      }
    objet.value = dateSaisie;
   }
//-- On désactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de sécurité
  if (window.event.ctrlKey){
    return false;
  }
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
    if (window.event.keyCode == 13){
       doSearch();
       return false;
    }
}

</SCRIPT>
<title>Regroupement</title>
</head>

<body  link="#095B97" 
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheRetour();">

<html:form action="/regroupement/search/default.do">
	<bean:define id="form" name="rechercheRegroupement" type="com.lotoquebec.cardex.presentation.model.form.CriteresRechercheRegroupementForm"/>

	<jsp:include page="/templates/regroupement/tpl_onglet_recherche_regroupement_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<jsp:include page="/templates/regroupement/tpl_recherche_regroupement_formulaire.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
     <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
	
	<tiles:insert page="/templates/commun/tpl_rapport.jsp" flush="true">
	  	<tiles:put name="form" value='<%=form%>'/>
  	</tiles:insert>
  	
</html:form>

</BODY>
</HTML>
