<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
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

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />

<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettreActionMethod("resetSearchDefault");
}

function doSoumettreRafraichir() {
	soumettreActionMethod("refreshRechercheDossier");
}

function doAdd() {
    windowOpenLocation("<%= request.getContextPath()+"/dossier/new.do?nature="%>");
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
	soumettreActionMethod("search");
}

function doClose() {
	post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()%>');
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
}

addEvent( window, 'keyDown', toucheRetour );

</SCRIPT>
 
    <tiles:insert page="/templates/dossier/tpl_recherche_dossier_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value='<%=contexteApplicatif.toString()+"/dossier/search.do"%>' />
		<tiles:put name="urlSecuritePartage" value='<%=contexteApplicatif.toString()+"/dossier/recherche/partage.do"%>' />
		<tiles:put name="urlSecuriteRafraichir" value='<%=contexteApplicatif.toString()+"/dossier/dossier/search/reset/default.do"%>' />				
	</tiles:insert>

<tiles:insert page="/templates/dossier/tiles/tpl_liaison_dossier_resultats.jsp" flush="true">
	<tiles:put name="preContexteApplicatif" value='<%=contexteApplicatif.toString()%>' direct="true"/>
	<tiles:put name="contexteApplicatif" value="/sujet"/>
	<tiles:put name="sourceProperty" value="sujet"/>
</tiles:insert>

<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
