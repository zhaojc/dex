<%-- --------------------------------------------------------------------------
Use case    : Recherche de sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un sujet.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.12 $, $Date: 2002/04/08 16:33:53 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.12 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
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


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>
<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
  //-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + "/sujet/search/refresh.do"%>');
}

function doSearch() {
  //-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + "/sujet/search.do"%>');
}

function doDelete() {
    var msg1 = "<bean:message key='epuration'/>";
    var msg2 = "<bean:message key='epuration2'/>";
    var msg3 = "<bean:message key='epuration3'/>";
    var isYes = confirm(msg1 + "\n" + msg2 + "\n" + msg3);
    if (isYes){
        soumettre('<%= request.getContextPath() + "/sujet/delete.do"%>');
    }
}

function doSelectVille() {
  alert("Fonction non disponible pour le moment ...");
} 

function doClose() {
	window.close();
}

function doPrint() {
//Impression de la page courante des résultats
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}
 
function doPrintAll() {
//Impression des résultats complets
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS_COMPLET %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       validation();
       return false;
    }
//-- On désactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de sécurité
  if (window.event.ctrlKey){
    return false;
  }
}

function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xml = new ActiveXObject("Microsoft.XMLHTTP");
        return xml;
    }
}

function doRafraichir(liste,cle) {
    if (cle == "") {
        viderListe(liste);
    } else {
        var url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    parseMessages(req.responseXML, liste);
                } else if (req.status == 204){
                    viderListe(liste);
                }
            }
        };
	req.open("GET", url, true);
        req.send(null);
    }
}

function parseMessages(responseXML, liste) {
    var listes = responseXML.getElementsByTagName("listes")[0];
    if (listes.childNodes.length > 0) {
      viderListe(liste);
      for (loop = 0; loop < listes.childNodes.length; loop++) {
	var entree = listes.childNodes[loop];
        var texte = entree.getElementsByTagName("texte")[0];
        var valeur = entree.getElementsByTagName("valeur")[0];
        remplirListe(liste,valeur.childNodes[0].nodeValue, texte.childNodes[0].nodeValue);
      }
    } else {
        viderListe(liste);
    }
}

function remplirListe(liste,valeur,texte){
//Ajout des nouvelles entrées de la liste déroulante retournée
//après un changement d'une liste dépendante.
	var oOption = document.createElement("OPTION");
	oOption.text = texte;
	oOption.value = valeur;
    if(liste == 'province'){
	document.forms(0).province.add(oOption); 
    }
    if(liste == 'ville'){
	document.forms(0).ville.add(oOption); 
    }
    if(liste == 'siteOrigine'){
	document.forms(0).siteOrigine.add(oOption); 
    }
   
}

function doTraits() {
//-- Calcul de l'âge et ajout de traits d'union dans la date, s'il y a lieu.
   naissance = document.forms(0).dateNaissance.value;
   if(naissance.length == 8){
	 	naissance = naissance.substring(0,4)+"-"+naissance.substring(4,6)+"-"+naissance.substring(6,8);
        document.forms(0).dateNaissance.value = naissance;
   }
//alert(document.forms(0).dateNaissance.value.substring(5,7));
}

function doAdd() {
    soumettre("<%= request.getContextPath() + "/sujet/new.do"%>");
}

</SCRIPT>

<TITLE><bean:message key='tabpage_sujet'/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
  onKeyDown="return toucheRetour();" >

<html:form action='/sujet/search' >
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.sujets" />
	</tiles:insert>
	
	<tiles:insert page="/templates/sujet/tpl_recherche_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/sujet/search.do" />
	</tiles:insert>		
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/sujet/tpl_recherche_sujet_resultats.jsp" flush="true" />
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>

<BR>

</BODY>
</HTML>