<%-- --------------------------------------------------------------------------
Use case    : Recherche de dossier.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un dossier.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.29 $, $Date: 2002/04/08 16:33:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.13 , $Date: 2002/02/21 23:45:05 , Author: abruno-boucher
Ajout des scripts de gestion de l'Assistant Date-Heure.

$Revision: 1.29 $, $Date: 2002/04/08 16:33:51 $, $Author: mdemers $
Modification Assistant Date-Heure pour Assistant Date.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration" %>


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
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
	soumettre('<%= request.getContextPath() + "/dossier/search/refresh.do"%>');
}

function doDelete() {
    var msg1 = "<bean:message key='epuration'/>";
    var msg2 = "<bean:message key='epuration2'/>";
    var msg3 = "<bean:message key='epuration3'/>";

    var isYes = confirmation(msg1 + "\n" + msg2 + "\n" + msg3);
    if (isYes){
		soumettre('<%= request.getContextPath() + "/dossier/delete.do"%>');
    }
}

function doSousCategorieSeul(){
           document.forms(0).rechercherSousCategorie.value = "<%=GlobalConstants.BooleanString.TRUE%>";
           document.forms(0).rechercherTous.checked = false;
           document.forms(0).rechercherTous.value = "<%=GlobalConstants.BooleanString.FALSE%>";
}

function doSousCategorieTous(){
           document.forms(0).rechercherSousCategorie.value = "<%=GlobalConstants.BooleanString.FALSE%>";
           document.forms(0).rechercherSousCategorie.checked = false;
           document.forms(0).rechercherTous.value = "<%=GlobalConstants.BooleanString.TRUE%>";
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
	//Si un rapport a été sélectionné, on le met à blanc avant la recherche, sinon le rapport sera exécuté avec une erreur de sécurité. 
	vider("choixRapport");
	soumettre('<%= request.getContextPath() + "/dossier/search.do"%>');
}

function doClose() {
	window.close();
}

function doPrint() {
//Impression des résultats
	var rapport = document.forms(0).choixRapport.value;
    //alert(url); 
    if(rapport != ""){
	    var tableValeurRapport = "<%=GlobalConstants.TableValeur.RAPPORT_LISTE_RECHERCHE_DOSSIER%>";
	    var genre = document.forms(0).genre.value;
	    var siteOrigine = document.forms(0).siteOrigine.value;
	    var dateDebut = document.forms(0).dateDebutDu.value;
	    var dateFin = document.forms(0).dateDebutAu.value;	
	
		var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   	window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');

		//Pour empêcher le rapport de s'exécuter au rafraîchissement de la page, on met une valeur nulle dans la liste des rapports.
	    document.forms(0).choixRapport.value = "";
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

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
//alert(window.event.keyCode + " - " + window.event.button);
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
function toucheAide(valeur) {
//-- Affichage de l'aide
    message(valeur);
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
    var url = "";
    //alert(liste + cle);  
    if (cle == "") {
        viderListe(liste);
    } else {
       if(liste == "groupesIntervenants"){ //Le paramètre LANGUE sert pour le statut (ici à NON pour avoir toute la liste).
           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=NON&CLE=" + cle;
        }else{
           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
        }
//alert(url);   
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
    //On vérifie si la liste vient d'une sélection dans la liste Type-Categorie. 
    //Si oui, on sélectionne la catégorie dans la liste Catégorie.
    if(document.forms(0).typeCategorie.value != ""){
       var cle = document.forms(0).typeCategorie.value;
       var pos = cle.indexOf("/");
       if (pos > -1){
       	  document.forms(0).categorie.value = cle.substring(0,pos);
       	  document.forms(0).typeCategorie.value = "";
	  doConsulter();
       }
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
     if(liste == 'typeCategorie'){
	document.forms(0).typeCategorie.add(oOption); 
     }
     if(liste == 'groupesIntervenants'){
	document.forms(0).groupesIntervenants.add(oOption); 
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
	document.forms(0).typeCategorie.add(oOption); 
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

</SCRIPT>

<TITLE><bean:message key='tabpage_dossier'/></TITLE>

</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
    onKeyDown="return toucheRetour();" >
<bean:define id="form" name="rechercheDossier" type="com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm"/>

<html:form action='/dossier/search' >
	<jsp:include page="/templates/dossier/tpl_onglet_recherche_dossier_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
	<jsp:include page='/commun/aide.jsp' flush="true"/>
	
    <tiles:insert page="/templates/dossier/tpl_recherche_dossier_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value="/dossier/search.do" />
		<tiles:put name="urlSecuritePartage" value="/dossier/recherche/partage.do" />
		<tiles:put name="urlSecuriteRafraichir" value="/dossier/search/reset/default.do" />
	</tiles:insert>
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/dossier/tpl_recherche_dossier_resultats.jsp" flush="true" />
     <INPUT type="hidden" name="LANG" value="<%= var_lang %>" />
<BR>

	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />

</html:form>

</BODY>
</HTML>

