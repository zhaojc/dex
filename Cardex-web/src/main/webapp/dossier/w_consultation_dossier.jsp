<%-- --------------------------------------------------------------------------
Use case    : Consultation de dossier.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la consultation d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.34 $, $Date: 2002/04/16 19:51:34 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.34 $, $Date: 2002/04/16 19:51:34 $, $Author: mlibersan $
Ajout des scripts de gestion de l'Assistant Date-Heure.
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

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
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/ScriptsOnglets.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/IEUploader.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

var societes = "0";

function doConfirmLinkSuppression() {
  return confirmation('<bean:message key="cardex_suppression" />');
}

function doConfirmLinkSuppressionPost(postHref) {

	if ( confirmation('<bean:message key="cardex_suppression" />') ){
		post( postHref );
		return true;
	}else{
		return false;
	}
}

function doSoumettreRafraichir() {
    unlockFields();
	soumettre('<%= request.getContextPath() + "/dossier/refresh.do"%>');
}

function doSoumettreRafraichirConsultation() {
    unlockFields();
	soumettre('<%= request.getContextPath() + "/dossier/rafraichirConsultation.do"%>');
}

function doRechercheSigle() {
//Recherche du sigle du site applicable
//S'il s'agit d'un dossier d'investigation, on vérifie si le site applicable correspond à celui saisi dans le champ Numéro de dossier.
//La fonction est exécutée seulement s'il est possible d'enregistrer le dossier

    if ((document.forms(0).genre.value == "<%= GlobalConstants.Genre.INVESTIGATION %>") && (document.forms(0).statut.value == "<%= GlobalConstants.Statut.DOSSIER_ACTIF %>")
          && document.forms(0).modifiable.value == "true"){
       var sigle = "";
       var url = "<%= request.getContextPath() %>/RechercheSigle";
       
        url += "?CLE="+document.forms(0).cle.value;
        url += "&SITE="+document.forms(0).site.value;

        var req = initRequest(url);
        
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
			        sigle = req.responseXML.firstChild.text;
			        if(sigle != "" && sigle != document.forms(0).numeroDossier.value){
						if ( !confirmation('<bean:message key="cardex.dossier.site.applicable" />' + " (" + sigle + "/" + document.forms(0).numeroDossier.value + ")." )){
							return false;
						}
			        } 
			    }
            }
        };
		req.open("GET", url, true);
        req.send(null);
    }
}

function doOk() {
  //S'il s'agit d'un dossier Accident/maladie, on affiche un message si le champ Référence 3 est vide et si une société n'a pas été liée.
   if(document.forms(0).type.value == "<%= GlobalConstants.Type.ACCIDENT_MALADIE %>"){
		if((document.forms(0).reference3.value == "") || (societes == "0")){
			if (!confirmation("<bean:message key='cardex_ambulance' />") ){
				return false;
			}
		}
   }
  //Si le statut est changé à Inactif, on affiche un message s'il n'y a aucune date de fin.
   if(document.forms(0).statut.value == "<%= GlobalConstants.Statut.DOSSIER_INACTIF %>"){
		if(document.forms(0).dateFin.value == ""){
			if (!confirmation("<bean:message key='cardex_date_fin_inactif' />") ){
				return false;
			}
		}
   }

   if(document.forms(0).nature.value == "<%= GlobalConstants.Genre.INVESTIGATION %>"){
		if(document.forms(0).statut.value == "<%= GlobalConstants.Statut.DOSSIER_ACTIF %>" && document.forms(0).dateFin.value == ""){
			if (!confirmation("<bean:message key='cardex_date_fin_inactif' />") ){
				return false;
			}
		}
   }

  //On affiche un message d'avertissement si le dossier ne contient pas
  //d'endroit ou de localisation.
   if((document.forms(0).endroit.value == "") || (document.forms(0).localisation.value == "")){
      if ( confirmation('<bean:message key="cardex_endroit_localisation" />') ){
		  //On vérifie si le mot de passe a été changé. Si oui, on prévient l'utilisateur.
		  if(document.forms(0).motPasse.value != document.forms(0).motPasseCourant.value){
		  //alert( document.forms(0).motPasse.value + " != " + document.forms(0).motPasseCourant.value);
			if ( confirmation('<bean:message key="cardex_mot_de_passe_different" />') ){
				unlockFields();
				detruireOnglet();
				soumettre('<%= request.getContextPath() + "/dossier/update.do"%>');
				return true;
			}else{
				return false;
			}
		  }else{
			unlockFields();
			detruireOnglet();
			soumettre('<%= request.getContextPath() + "/dossier/update.do"%>');
		  }
	  }else{
	     return false;
	  }
	}else{
		  //On vérifie si le mot de passe a été changé. Si oui, on prévient l'utilisateur.
		  if(document.forms(0).motPasse.value != document.forms(0).motPasseCourant.value){
		  //alert( document.forms(0).motPasse.value + " != " + document.forms(0).motPasseCourant.value);
			if ( confirmation('<bean:message key="cardex_mot_de_passe_different" />') ){
				unlockFields();
				detruireOnglet();
				soumettre('<%= request.getContextPath() + "/dossier/update.do"%>');
				return true;
			}else{
				return false;
			}
		  }else{
			unlockFields();
			detruireOnglet();
			soumettre('<%= request.getContextPath() + "/dossier/update.do"%>');
		  }
	}
}

function doClose() {
	detruireOnglet();
	windowClose();
}

function doAuditAcces() {
//Impression de l'audit des accès
	   var rapport = "<%= RapportsConfiguration.AUDIT_ACCES_DOSSIERS %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doAuditChangement() {
    //window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
	   var rapport = "<%= RapportsConfiguration.AUDIT_CHANGEMENTS_DOSSIERS %>";
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   //var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&SITE=&GENRE=&UTILISATEUR=" + userCardex + "&DATE_DEBUT=&DATE_FIN=&CATEGORIE="; 
	   var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&UTILISATEUR=" + userCardex; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doPrint() {
  if(document.forms(0).inscription.value == "true" && document.forms(0).choixRapport.value == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER %>"){
  	//Impression d'un dossier avec inscription
  	url = '<%= request.getContextPath() + "/dossier/choose/print.do"%>';
    window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    //post('<%= request.getContextPath() + "/dossier/choose/print.do"%>');
  }else{	
	//Impression du rapport choisi
	   var rapport = document.forms(0).choixRapport.value;
	   var fiche = "DO";
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER %>"){
	     	url = '<%= request.getContextPath() + "/dossier/choose/print.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_HISTORIQUE %>"){
	     	url = '<%= request.getContextPath() + "/dossier/choose/print/audit.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_PIECES_JOINTES %>"){
	     	url = '<%= request.getContextPath() + "/dossier/choose/printAvecPhotoDansPiecesJointes.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_PIECES_JOINTES_HISTORIQUE %>"){
	     	url = '<%= request.getContextPath() + "/dossier/choose/printAvecPhotoDansPiecesJointes/audit.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_UNIFORMISE %>"){
	     	url = '<%= request.getContextPath() + "/dossier/rapport.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if(rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_UNIFORMISE_HISTORIQUE %>"){
	     	url = '<%= request.getContextPath() + "/dossier/rapport/audit.do"%>';
    		window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
	   if((rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_SOMMAIRE %>") || (rapport == "<%= GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_DETAILLE %>")){
		   url = "<%=request.getContextPath()%>/AffichagePDFFiches?FICHE=" + fiche + "&SITE=" + site + "&CLE=" + cle + "&RAPPORT=" + rapport;
		   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
    	}
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
    var url = "";
    if (cle == "") {
        viderListe(liste);
    } else {
       if(liste == "groupesIntervenants"){ //Le paramètre LANGUE sert pour le statut (ici à NON pour avoir toute la liste).
           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=NON&CLE=" + cle;
        }else{
           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
        }
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
	document.forms(0).type.add(oOption); 
  }
  if(liste == 'groupesIntervenants'){
	contenu = document.forms(0).groupesIntervenants;
	contenu.options.length = 0;    
	document.forms(0).groupesIntervenants.add(oOption); 
  }
}

function getOnglet(){
//On affiche le dernier onglet choisi par l'utilisateur. Par défaut, lors de la
//première ouverture du dossier, on active l'onglet Narration.
   var cookies = document.cookie;
//alert(cookies);   
   if (!cookies){ // no cookies
      return "<bean:write name="dossier" property="ongletDefaut" />";
   }
   
   var search = "dossier=";
   var iLen = cookies.length;
   if (iLen > 0) 
   {
      // find where cookie begins
      var begin = cookies.indexOf(search);
      if (begin != -1)
      {          
         begin += search.length;
         // search for the end of the value
         var end = cookies.indexOf(";", begin);
         if (end == -1)
         {
            // delimiter not found, so eat rest of cookie string
            end = iLen;
         }
//alert(unescape(cookies.substring(begin, end)));
         return unescape(cookies.substring(begin, end));
      }
   }

   return "<bean:write name="dossier" property="ongletDefaut" />"; // cookie non trouvé
}

  
function setOnglet(onglet){
//Fontions pour conserver l'onglet activé par l'utilisateur lors du retour du dossier.
//On fixe une expiration automatique à 10 minutes
   var c = new Date();
   c.setTime (c.getTime() + (1000 * 600)); 
  document.cookie = "dossier=" + escape(onglet) + "; expires="+c.toGMTString();
   //alert(document.cookie);       
}

function detruireOnglet(){
//Suppression du cookie à la fermeture de la fenêtre
   var c = new Date();
   c.setTime (c.getTime() - 1); 
   // document.cookie = "onglet" + document.forms(0).cle.value + "=; expires=Thu, 01-Jan-70 00:00:01 GMT";
   document.cookie = "dossier=1; expires="+c.toGMTString();
//alert(document.cookie);
}

function lierDossier(){
	var numeroDossier = document.getElementsByName("numeroDossier")[0];
	
	if (numeroDossier != null && numeroDossier.value != ""){
	    unlockFields();
	  	soumettre('<%= request.getContextPath() + "/dossier/dossier/linkNumeroDossier.do"%>');
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

function doClickDateFin(){
	if (document.forms(0).dateFin.value == null ||document.forms(0).dateFin.value.length == 0 ) {
		document.forms(0).dateFin.value = document.forms(0).dateDebut.value;
	}
}

function doConfidentialite8(){
//Avertissement à l'utilisateur pour ajouter des précisions
  if (document.forms(0).confidentialite.value == "<%= GlobalConstants.Confidentialite.HUIT %>" ) {
  	 message("Ne pas oublier d'inscrire la raison (erreur ou doublon) de la mise en confidentialité 8 dans le Numéro de dossier (effacer le contenu du champ si une inscription y apparait et la remplacer par la raison).");
  }
}

</SCRIPT>

<TITLE></TITLE>
</HEAD>

<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" 
    onLoad="onLoadToggleDivisionVisibility(getOnglet());doRechercheSigle();" >
<html:form action='/dossier/update' enctype="multipart/form-data">
    <jsp:include page="/templates/dossier/tpl_onglet_dossier_entete.jsp" flush="true" />
    <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
	<jsp:include page='/commun/aide.jsp' flush="true"/>
    
    <tiles:insert page="/templates/dossier/tpl_dossier_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/dossier/update.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	
    
    <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
    <jsp:include page="/templates/dossier/tpl_menu_onglets_dossier.jsp" flush="true" />
    <DIV id="DIVISIONS_GROUP">
    <!-- Data table related to selected tab -->
    <!-- Data header --> 
        <jsp:include page="/templates/dossier/tpl_onglet_list_sujets.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_inscriptions.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_photos.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_suivis.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_narrations.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_dossiers.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_jeux.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_vehicules.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_societes.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_billets.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_pieces_jointes.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_consignations.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_sousCategories.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_partage.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_urgence.jsp" flush="true" />
        <jsp:include page="/templates/dossier/tpl_onglet_list_evaluations.jsp" flush="true" />
    </DIV>
    <!-- END DIVISIONS_GROUP -->
    
</html:form>
<BR>
<SCRIPT type="text/javascript">
setShow("menuOnglets");
</SCRIPT>

</BODY>
</HTML>