<%-- --------------------------------------------------------------------------
Use case    : Nouvueau sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives au résultat d'un sujet.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.9 $, $Date: 2002/04/08 16:33:53 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.9 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.9 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

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

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function doSoumettreRafraichir() {
	//-- Fonction déclarée dans lq_scripts.js
	unlockFields();
	soumettre('<%= request.getContextPath() + "/sujet/new/rafraichir.do"%>');
}

function doOk() {
  //-- Fonction déclarée dans lq_scripts.js
  unlockFields();
  soumettre('<%= request.getContextPath() + "/sujet/save.do"%>');
}

function doClose() {
	windowClose();
}

function doPrint() {
  message("Fonction non disponible pour le moment ...");
}

function initRequest() {

    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
        
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function doVerificationNumeroFiche() {

    if (document.forms(0).nom.value != "" 
    && document.forms(0).prenom.value != ""
    && document.forms(0).dateNaissance.value != "") {

        var url = "<%= request.getContextPath() %>/VerificationSujetExistant";
        url += "?NOM="+document.forms(0).nom.value;
        url += "&PRENOM="+document.forms(0).prenom.value;
        url += "&DATE_NAISSANCE="+document.forms(0).dateNaissance.value;

        var req = initRequest();
        
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
			        var numeroFiche = req.responseXML.firstChild.text;
			        
			        if (numeroFiche != "" && numeroFiche != null){
			        	message("Ce sujet existe déjà avec le(s) numéro(s) de fiche : "+numeroFiche+".");
			        }
                }
            }
        };
		req.open("GET", url, true);
        req.send(null);
    }
}

</SCRIPT>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_resultatSujet_styles.css">

<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<html:form action='/sujet/save' >
	<jsp:include page="/templates/sujet/tpl_onglet_sujet_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>

	<tiles:insert page="/templates/sujet/tpl_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/sujet/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>	

	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
</html:form>

</BODY>
</HTML>
