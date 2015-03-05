<%-- --------------------------------------------------------------------------
Use case    : Liaison de dossier.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la liaison d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/22 17:37:22 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/04/22 17:37:22 $, $Author: mlibersan $
Ajout des scripts de gestion de l'Assistant Date-Heure.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.CriteresRechercheDossierHtmlForm" %>
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

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>
<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettre('<%= request.getContextPath() + "/vehicule/dossier/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/vehicule/dossier/search/refresh.do"%>');
}

function doAdd() {
<%
String nature = ((CriteresRechercheDossierHtmlForm)request.getSession().getAttribute("rechercheDossier")).getNature();
%>
    windowOpenLocation("<%= request.getContextPath() + "/dossier/new.do?nature="+ nature%>");
}


function doSearch() {
//-- On retire les traits d'union dans le numéro Cardex, s'il y a lieu, car ils ne sont pas considérés dans la recherche
        numero = document.forms(0).elements(3).value;  //adressage par elements car numeroCardex.date n'est pas reconnu.
	if(numero.length > 8){
	   while(numero.indexOf("-") > 0) { 
	        tiret = numero.indexOf("-");
 		numero = numero.substring(0,tiret)+numero.substring(tiret+1,numero.length);
 	   }
        }
	document.forms(0).elements(3).value = numero;
	soumettre('<%= request.getContextPath() + "/vehicule/dossier/search.do"%>');
}

function doClose() {
	window.location='<cardex:writeObjectURL object="rechercheDossier" objectProperty="vehicule" page="/vehicule/show.do" />';
}

function doTraits(objet) {
//-- Insertion de traits d'union dans les dates, s'il y a lieu.
   dateSaisie = objet.value;
   if(dateSaisie.length == 8){
      if("<%= var_lang %>" == "fr"){
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
     if(liste == 'categorie'){
	document.forms(0).categorie.add(oOption); 
     }
     if(liste == 'type'){
	document.forms(0).type.add(oOption); 
     }
     
}

function viderListe(liste){
//On vide la liste d'abord avant d'insérer les valeurs retournées
	var oOption = document.createElement("OPTION");
	oOption.text = "";
	oOption.value = "";
  if(liste == 'categorie'){
	contenu = document.forms(0).categorie;
	contenu.options.length = 0;    
	document.forms(0).categorie.add(oOption); 
  }
  if(liste == 'type'){
	contenu = document.forms(0).type;
	contenu.options.length = 0;    
	document.forms(0).type.add(oOption); 
  }
  if(liste == 'typeCategorie'){
	contenu = document.forms(0).typeCategorie;
	contenu.options.length = 0;    
	document.forms(0).type.add(oOption); 
  }
  if(liste == 'groupesIntervenants'){
	contenu = document.forms(0).groupesIntervenants;
	contenu.options.length = 0;    
	document.forms(0).groupesIntervenants.add(oOption); 
  }
}
function choisirCategorie(cle) {
//On place les valeurs choisies dans les listes de Types et Catégories.
   var pos = cle.indexOf("/");
   if (pos > -1){
   	document.forms(0).type.value = cle.substring(pos+1,cle.length);
   	doRafraichir("categorie",document.forms(0).type.value);
   }
//alert(document.forms(0).modele.value + " - " + cle.substring(0,pos));
}

function choisirIntervenant(cle) {
//On place les valeurs choisies dans le champ Intervenant.
   	document.forms(0).intervenant.value = cle;
   	doConsulterIntervenant();
//alert(document.forms(0).modele.value + " - " + cle.substring(0,pos));
}

function doConsulter(){
  if(document.forms(0).nature.value != ""){
    if(consulter.style.visibility == "visible"){
       consulter.style.visibility = "hidden";
       document.forms(0).loupe.src='<%=request.getContextPath()%>/images/magnify.gif';
    }else{
       consulter.style.visibility = "visible";
    }
  }
}
function doConsulterIntervenant(){
  if(document.forms(0).siteOrigine.value != ""){
    if(consulterIntervenant.style.visibility == "visible"){
       consulterIntervenant.style.visibility = "hidden";
       document.forms(0).loupe2.src='<%=request.getContextPath()%>/images/magnify.gif';
    }else{
       consulterIntervenant.style.visibility = "visible";
    }
  }
}

function doPrintRapport() {
//Impression d'un rapport personnalisé
	//soumettre('<%= request.getContextPath() + "/dossier/printRapport.do"%>');
	//On s'assure qu'il y a un site d'origine, sinon les résultats sont erronés.
	if(document.forms(0).siteOrigine.value == ""){
	   alert("<bean:message key='cardex_required_site'/>");
	}else{
	   var rapport = document.forms(0).choixRapport.value;
	   var siteOrigine = document.forms(0).siteOrigine.value;
	   var dateDebut = document.forms(0).dateDebutDu.value;
	   var dateFin = document.forms(0).dateDebutAu.value;	
	   var genre = document.forms(0).genre.value;
	   var userCardex = document.forms(0).intervenant.value;
	   var url = "<%=request.getContextPath()%>/AffichagePDF?RAPPORT=" + rapport + "&SITE=" + siteOrigine + "&GENRE=" + genre + "&UTILISATEUR=" + userCardex + "&DATE_DEBUT=" + dateDebut + "&DATE_FIN=" + dateFin;
	   //alert(url);  
	   //alert(rapport + " - " + url); 
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
	}
}

function doPrintRapportDossier() {
//-- Fonction pour un rapport demandé par le service du contentieux
    //post('<cardex:writeObjectURL object="rechercheDossier" page="/rapportAmbulance.do" />;');
	soumettre('<%= request.getContextPath() + "/rapportAmbulance.do"%>');
}

</SCRIPT>

<TITLE></TITLE>
</HEAD>
<BODY link="#095B97"  
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
        onKeyDown="return toucheRetour();">


<html:form action='/vehicule/dossier/search.do' >
	<jsp:include page='/templates/dossier/tpl_onglet_recherche_dossier_entete.jsp' flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />	
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/dossier/tpl_recherche_dossier_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/vehicule/dossier/search.do" />
		<tiles:put name="urlSecuritePartage" value="/vehicule/dossier/recherche/partage.do" />
		<tiles:put name="urlSecuriteRafraichir" value="/vehicule/dossier/search/reset/default.do" />				
	</tiles:insert>
	
	<jsp:include page='/templates/tpl_erreur.jsp' flush="true" />
	<jsp:include page='/templates/vehicule/tpl_liaison_dossier_resultats.jsp' flush="true" />
        <html:hidden name='rechercheDossier' property='vehicule.cle' />
        <html:hidden name='rechercheDossier' property='vehicule.site' />
        
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />        
        
</html:form>

<BR>

</BODY>
</HTML>
