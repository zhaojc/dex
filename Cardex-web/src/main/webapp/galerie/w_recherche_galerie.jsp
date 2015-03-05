<%-- --------------------------------------------------------------------------
Use case    : Recherche de sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/26 18:55:27 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/04/26 18:55:27 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
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
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/styles/lq_styles.css'>
<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doRefresh() {
	soumettre('<%= request.getContextPath() + "/galerie/search/refresh.do"%>');
}

function doReperage(){
	if(document.forms(0).categorie.value == "<%= GlobalConstants.Categorie.REPERAGE_AUTOEXCLUSION %>"
	|| document.forms(0).categorie.value == "<%= GlobalConstants.Categorie.REPERAGE_ACCES_INTERDIT %>"
	|| document.forms(0).categorie.value == "<%= GlobalConstants.Categorie.REPERAGE_AVIS_GUET %>"){
		nombre.style.visibility = "visible";
	}else{
		nombre.style.visibility = "hidden";
	}
}

function doClose() {
	window.close();
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       document.forms(0).submit();
       return false;
    }
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

</SCRIPT>

<TITLE><bean:message key='tabpage_galery'/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
      onKeyPress="return toucheRetour();" onload="doReperage();">

<html:form action='/galerie/search' >
	<jsp:include page='/templates/tpl_calendrier_div.jsp' flush= 'true' />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.galerie" />
	</tiles:insert>    
	<jsp:include page='/templates/galerie/tpl_recherche_galerie_formulaire.jsp' flush= 'true' />
	<jsp:include page='/templates/tpl_erreur.jsp' flush= 'true' />
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>

<BR>

</BODY>
</HTML>