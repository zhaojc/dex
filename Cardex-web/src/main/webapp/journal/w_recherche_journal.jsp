<%-- --------------------------------------------------------------------------
Use case    : Recherche des entrées du journal de surveillance
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
 
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
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src='<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js'></SCRIPT>


<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettre('<%=request.getContextPath()%>/journal/search/reset/default.do');
}

function doSoumettreRafraichir() {
	unlockFields();
	soumettre('<%=request.getContextPath()%>/journal/search/refresh.do');
}

function doSearch() {
	unlockFields();
	soumettre("<%= request.getContextPath() + "/journal/search.do"%>");
}

function doClose() {
	window.close();
}

function doAdd() {
    windowOpenLocation("<%= request.getContextPath() + "/journal/create.do"%>");
}
//Accès à l'écran de production des rapports de regroupement
function doRegroupement() {
    windowOpenLocation("<%= request.getContextPath() + "/regroupement/search/default.do"%>");
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
<title>Recherche Journal</title>
</head>

<body  link="#095B97" id="journal"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheRetour();">

<html:form action="/journal/search.do">
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
    
    <tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.journal" />
	</tiles:insert>
	<jsp:include page="/templates/journal/tpl_recherche_journal_formulaire.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/journal/tpl_recherche_journal_resultats.jsp" flush="true" />
     <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>
<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />

</html:form>

</BODY>
</HTML>
