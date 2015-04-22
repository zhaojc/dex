<%-- --------------------------------------------------------------------------
Use case    : Recherche de soci�t�
Description : Page principale dans laquelle est incorpor�e les diff�rentes
              composantes relatives � la recherche d'une soci�t�.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/30 13:20:20 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/04/30 13:20:20 $, $Author: fguerin $
Derniers commentaires � jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
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


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
function doReset() {
	soumettre('<%= request.getContextPath() + "/sujet/vehicule/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/sujet/vehicule/search/refresh.do"%>');
}

function doAdd() {
		post('<%= request.getContextPath() + "/vehicule/create.do?"%>');
}

function doSearch() {
	soumettre('<%= request.getContextPath() + "/sujet/vehicule/search.do"%>');
}

function doSelectVille() {
  message("Fonction non disponible pour le moment ...");
}

function doClose() {
	window.location='<cardex:writeObjectURL object="rechercheVehicule" objectProperty="sujet" page="/sujet/show.do" />';
}

function toucheRetour() {
//-- D�clenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       doSearch();
       return false;
    }
//-- On d�sactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de s�curit�
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
    //On v�rifie si la liste vient d'une s�lection dans la liste mod�le-marque. 
    //Si oui, on s�lectionne le mod�le dans la liste mod�le.
    if(document.forms(0).modeleMarque.value != ""){
       var cle = document.forms(0).modeleMarque.value;
       var pos = cle.indexOf("/");
       if (pos > -1){
       	  document.forms(0).modele.value = cle.substring(0,pos);
       	  document.forms(0).modeleMarque.value = "";
	  doConsulter();
       }
    }
}

function remplirListe(liste,valeur,texte){
//Ajout des nouvelles entr�es de la liste d�roulante retourn�e
//apr�s un changement d'une liste d�pendante.
	var oOption = document.createElement("OPTION");
	oOption.text = texte;
	oOption.value = valeur;
     if(liste == 'modele'){
	document.forms(0).modele.add(oOption); 
     }
}

function viderListe(liste){
//On vide la liste d'abord avant d'ins�rer les valeurs retourn�es
	var oOption = document.createElement("OPTION");
	oOption.text = "";
	oOption.value = "";
  if(liste == 'modele'){
	contenu = document.forms(0).modele;
	contenu.options.length = 0;    
	document.forms(0).modele.add(oOption); 
  }
}

function choisirModele(cle) {
//On place les valeurs choisies dans les listes de marques et mod�les.
   var pos = cle.indexOf("/");
   if (pos > -1){
   	document.forms(0).marque.value = cle.substring(pos+1,cle.length);
   	doRafraichir("modele",document.forms(0).marque.value);
   }
//alert(document.forms(0).modele.value + " - " + cle.substring(0,pos));
}

function doConsulter(){
    if(consulter.style.visibility == "visible"){
       consulter.style.visibility = "hidden";
       document.forms(0).loupe.src='<%=request.getContextPath()%>/images/magnify.gif';
    }else{
       consulter.style.visibility = "visible";
    }
}
</SCRIPT>

<TITLE><bean:message key='tabpage_vehicule'/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheRetour();">


<html:form action='/sujet/vehicule/search' >
        <html:hidden name='rechercheVehicule' property='sujet.cle' />
        <html:hidden name='rechercheVehicule' property='sujet.site' />
	
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.vehicules" />
	</tiles:insert>
	
	<tiles:insert page="/templates/vehicule/tpl_recherche_vehicule_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/sujet/vehicule/search.do" />
	</tiles:insert>	
	
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/sujet/tpl_liaison_vehicule_resultats.jsp" flush="true" />
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
	
</html:form>

<BR>

</BODY>
</HTML>