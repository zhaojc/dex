
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
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

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />

<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doReset() {
	soumettreActionMethod("resetSearchDefault");	
}

function doSoumettreRafraichir() {
	soumettreActionMethod("refreshRechercheSujet");	
}

function doAdd() {
    windowOpenLocation("<%= request.getContextPath() + "/sujet/new.do"%>");
}

function doSearch() {
	soumettreActionMethod("search");
}

function doSelectVille() {
  message("Fonction non disponible pour le moment ...");
}

function doClose() {
	post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()%>');
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

function doTraits() {
//-- Calcul de l'âge et ajout de traits d'union dans la date, s'il y a lieu.
   naissance = document.forms(0).dateNaissance.value;
   if(naissance.length == 8){
      if("<%= var_lang %>" == "fr"){
 	naissance = naissance.substring(0,4)+"-"+naissance.substring(4,6)+"-"+naissance.substring(6,8);
      }else{
 	naissance = naissance.substring(0,2)+"/"+naissance.substring(2,4)+"/"+naissance.substring(4,8);
      }
      document.forms(0).dateNaissance.value = naissance;
   }
//alert(document.forms(0).dateNaissance.value.substring(5,7));
}

function doRafraichir(liste, cle) {
	var listeSource = "pays";
	var url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
	doRafraichirListe(listeSource, "province", url);
}

addEvent( window, 'keyDown', toucheRetour );

</SCRIPT>


    <html:hidden name='rechercheSujet' property='dossier.cle' />
    <html:hidden name='rechercheSujet' property='dossier.site' />

	<tiles:insert page="/templates/sujet/tpl_recherche_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteRecherche" value='<%=contexteApplicatif.toString()+"/sujet/search.do"%>' />
	</tiles:insert>

	<tiles:insert page="/templates/sujet/tiles/tpl_liaison_sujet_resultats.jsp" flush="true">
		<tiles:put name="preContexteApplicatif" value='<%=contexteApplicatif.toString()%>' direct="true"/>
		<tiles:put name="contexteApplicatif" value="/sujet"/>
		<tiles:put name="sourceProperty" value="sujet"/>
	</tiles:insert>
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />	
	