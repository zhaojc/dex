<%-- --------------------------------------------------------------------------
Use case    : Création d'une entrée de journal.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
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

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
function doRefresh() {
	unlockFields();
	soumettre('<%= request.getContextPath() + "/journal/create/refresh.do"%>');
}

function doOk() {
  //On affiche un message d'avertissement si le dossier ne contient pas
  //d'endroit ou de localisation.
    if((document.forms(0).endroit.value == "") || (document.forms(0).localisation.value == "")){
      if ( confirm('<bean:message key="cardex_endroit_localisation" />') ){
		unlockFields();
	    soumettre('<%= request.getContextPath() + "/journal/save.do"%>');
	  }else{
	     return false;
	  }
	}else{
		unlockFields();
	    soumettre('<%= request.getContextPath() + "/journal/save.do"%>');
	}
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

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >
  
<html:form action='/journal/save' >
	<jsp:include page="/templates/journal/tpl_onglet_recherche_journal_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>

	<tiles:insert page="/templates/journal/tpl_journal_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/journal/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>'/>
	</tiles:insert>	

	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
</html:form>

