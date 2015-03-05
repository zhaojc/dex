<%-- --------------------------------------------------------------------------
Use case    : �cran de recherche des narrations.
Description : �cran 800 X 600 de recherche.
              Le visionnement des narration s'effectue �galemement dans
              la m�me fen�tre.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/08 16:33:50 $

History     : Voir ci-dessous.

$Revision: 1.5 $, $Date: 2002/04/08 16:33:50 $, $Author: mdemers $
Cr�ation.

$Revision: 1.5 $, $Date: 2002/04/08 16:33:50 $, $Author: mdemers $
Mise � jour nouvel Assistant date-heure (incorpor� dans la page).
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

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

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettre('<%= request.getContextPath() + "/narration/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/narration/search/refresh.do"%>');
}

function doClose() {
	window.close();
}

function doPrintRapport() {
//Impression du rapport d'activit�s quotidien
	   var dateDebut = document.forms(0).dateCreationDebut.value;
	   var dateFin = document.forms(0).dateCreationFin.value;
	   var site = document.getElementsByName("site")[0].value;
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichageRapportActivites?DATE_DEBUT=" + dateDebut + "&DATE_FIN=" + dateFin + "&UTILISATEUR=" + userCardex + "&SITE=" + site;
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
       document.forms(0).submit();
       return false;
    }
//-- On d�sactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de s�curit�
  if (window.event.ctrlKey){
    return false;
  }
}

</SCRIPT>
<title><bean:message key='recherche_narrations'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" 
    onKeyPress="return toucheRetour();">

<html:form action='/narration/search' >
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.narrations" />
	</tiles:insert>    
    <jsp:include page="/templates/narrations/tpl_recherche_narrations_formulaire.jsp" flush="true" />
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/narrations/tpl_recherche_narrations_resultats.jsp" flush="true" />
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>

</BODY>
</HTML>
