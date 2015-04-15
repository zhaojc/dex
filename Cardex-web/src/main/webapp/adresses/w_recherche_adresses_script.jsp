<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.adresse.AdresseInvalide" %>

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

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/listeAJAXHelper.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function doClose() {
	windowClose();
}

function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       doSoumettreRecherche();
       return false;
    }
//-- On désactive toutes les commandes qui utilisent la touche CTRL
//-- pour des raisons de sécurité
  if (window.event.ctrlKey){
    return false;
  }
}

function doSoumettreRecherche() {
  	//-- Fonction déclarée dans lq_scripts.js
	soumettre("<%= request.getContextPath() + "/adresses/soumettreRecherche.do"%>");	
}

function doSoumettreRechercheAdressesInvalides(){
	windowOpenLocation( construireCriteresURL() );
}

function construireCriteresURL(){
	var url = "<%=request.getContextPath()%>/CritereRapportAffichagePDF?";
	url += "choixRapport=<%=GlobalConstants.ChoixRapport.ADRESSE_INVALIDE%>";
	url += construireParametre("<%=AdresseInvalide.ENTITE%>");
	url += construireParametre("<%=AdresseInvalide.SITE_ORIGINE%>");
	url += construireParametreRadio("<%=AdresseInvalide.ENTITE_RECHERCHE%>");
	url += construireParametre("<%=AdresseInvalide.DATE_CREATION_DU%>");
	url += construireParametre("<%=AdresseInvalide.DATE_CREATION_AU%>");
	
	return url;
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

