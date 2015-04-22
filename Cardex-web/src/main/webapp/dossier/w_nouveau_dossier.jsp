<%-- --------------------------------------------------------------------------
Use case    : Cr�ation de dossier.
Description : Page principale dans laquelle est incorpor�e les diff�rentes
              composantes relatives � la cr�ation d'un dossier.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.23 $, $Date: 2002/04/08 16:33:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.23 $, $Date: 2002/04/08 16:33:51 $, $Author: mdemers $
Ajout des scripts de gestion de l'Assistant Date-Heure.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
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

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
function doSoumettreRafraichir() {
	unlockFields();
	soumettre('<%= request.getContextPath() + "/dossier/new/refresh.do"%>');
}

function doOk() {
  //S'il s'agit d'un dossier Accident/maladie, on affiche un message si le champ R�f�rence 3 est vide et si une soci�t� n'a pas �t� li�e.
   if(document.forms(0).type.value == "<%= GlobalConstants.Type.ACCIDENT_MALADIE %>"){
		if((document.forms(0).reference3.value == "")){
			if (!confirmation("<bean:message key='cardex_ambulance' />") ){
				return false;
			}
		}
   }
  //On affiche un message d'avertissement si le dossier ne contient pas
  //d'endroit ou de localisation.
    if((document.forms(0).endroit.value == "") || (document.forms(0).localisation.value == "")){
      if ( confirmation('<bean:message key="cardex_endroit_localisation" />') ){
		unlockFields();
	    soumettre('<%= request.getContextPath() + "/dossier/save.do"%>');
	  }else{
	     return false;
	  }
	}else{
		unlockFields();
	    soumettre('<%= request.getContextPath() + "/dossier/save.do"%>');
	}
}

function doClose() {
    windowClose();
}

function doPrint() {
  message("Fonction non disponible pour le moment ...");
}

function doAudit() {
  message("Fonction non disponible pour le moment ...");
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
       if(liste == "groupesIntervenants"){ //Le param�tre LANGUE sert pour le statut (ici � NON pour avoir toute la liste).
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
    //On v�rifie si la liste vient d'une s�lection dans la liste Type-Categorie. 
    //Si oui, on s�lectionne la cat�gorie dans la liste Cat�gorie.
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
//Ajout des nouvelles entr�es de la liste d�roulante retourn�e
//apr�s un changement d'une liste d�pendante.
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
//On vide la liste d'abord avant d'ins�rer les valeurs retourn�es
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
//On place les valeurs choisies dans les listes de Types et Cat�gories.
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

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >
<html:form action='/dossier/save' >
	<jsp:include page="/templates/dossier/tpl_onglet_dossier_entete.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/dossier/tpl_dossier_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value="/dossier/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
</html:form>

