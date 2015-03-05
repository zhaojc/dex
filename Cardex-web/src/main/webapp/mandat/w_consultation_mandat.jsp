<%-- --------------------------------------------------------------------------
Use case    : Consultation d'un mandat PSU
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
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

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function doRefresh() {
	unlockFields();
	soumettre('<%=request.getContextPath()%>/mandat/refresh.do');
}

function doOk() {
	unlockFields();
	soumettre('<%=request.getContextPath()%>/mandat/update.do');
}

function doApprobation() {
	unlockFields();
	soumettre('<%=request.getContextPath()%>/mandat/approbation.do');
}

function doPermettreModification() {
	unlockFields();
	soumettre('<%=request.getContextPath()%>/mandat/permettreModification.do');
}

function doClose() {
	windowClose();
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
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       doSearch();
       return false;
    }
}

</SCRIPT>
<title>Approbations</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyPress="return toucheRetour();" oncontextmenu="return false;" >

<html:form action="/mandat/update.do">

	<jsp:include page="/templates/mandat/tpl_onglet_mandat_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/mandat/tpl_mandat_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/mandat/update.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	
	
	<jsp:include page="/templates/mandat/tpl_erreur.jsp" flush="true" />
    <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>
</html:form>

</BODY>
</HTML>
