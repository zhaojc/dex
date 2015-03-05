<%-- --------------------------------------------------------------------------
Use case    : Nouveau v�hicule
Description : Page principale dans laquelle sont incorpor�es les diff�rentes
              composantes relatives � l'ajout d'un v�hicule.
Author(s)   : $Author: fguerin $, fguerin
Revision    : $Revision: 1.6 $, $Date: 2002/04/30 13:20:18 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: fguerin
Ajustement des commentaires de la page JSP.

Revision: 1.3 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise � jour nouvel Assistant date-heure (incorpor� dans la page).

$Revision: 1.6 $, $Date: 2002/04/30 13:20:18 $, $Author: fguerin $
Derniers commentaires � jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
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

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function doSoumettreRafraichir() {
	//-- Fonction d�clar�e dans lq_scripts.js
	unlockFields();
	soumettre('<%= request.getContextPath() + "/vehicule/create/refresh.do"%>');
}

function doClose() {
	windowClose();
}

function initRequest(url) {
//alert(url);
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
    doVerificationVehicule();
}

function doVerificationVehicule() {

    if (document.forms(0).immatriculation.value != "" 
    && document.forms(0).modele.value != "") {
        var url = "<%= request.getContextPath() %>/VerificationVehiculeExistant";
        url += "?IMMATRICULATION="+document.forms(0).immatriculation.value;
        url += "&MODELE="+document.forms(0).modele.value;

        var req = initRequest(url);
        
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
			        var vehicule = req.responseXML.firstChild.text;
			        
			        if (vehicule != "" && vehicule != null){
			        	alert("Ce v�hicule existe d�j� : "+vehicule+".");
			        }
                }
            }
        };
		req.open("GET", url, true);
        req.send(null);
    }
}
</SCRIPT>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css">

<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<html:form action='/vehicule/save' >
	<jsp:include page='/templates/vehicule/tpl_onglet_vehicule_entete.jsp'flush='true' />
	<jsp:include page='/templates/tpl_calendrier_div.jsp' flush='true' />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert page="/templates/vehicule/tpl_vehicule_formulaire.jsp" flush="true">
		<tiles:put name="urlSoumettreSauvegarde" value="/vehicule/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>
	
	<jsp:include page='/templates/tpl_erreur.jsp' flush='true' />
</html:form>

</BODY>
</HTML>
