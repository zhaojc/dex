<%-- --------------------------------------------------------------------------
Use case    : Recherche de service d'urgence
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un service d'urgence.
Author(s)   : $Author: mazzucr $ 
Revision    : $Revision: 1 $, $Date: 2014/03/13 $

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="org.apache.struts.Globals" %>
<%
    //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
    //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
    //-- de session.
    String var_lang = "fr";
    try
    {
        var_lang = ((Locale) request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
    }
    catch (Throwable e)
    {
    }
%>


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css'
	href='<%=request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true" />

<SCRIPT language="JavaScript" type="text/javascript">
function doReset() {
	soumettre('<%=request.getContextPath() + "/urgence/search/reset/default.do"%>');
}

function doSoumettreRafraichir() {
	soumettre('<%=request.getContextPath() + "/urgence/search/refresh.do"%>');
}

function doClose() {
	window.close();
}

function doPrint() {
//Impression de la page courante des résultats
	   var rapport = "<%=GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE%>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doPrintAll() {
//Impression des résultats complets
	   var rapport = "<%=GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE_COMPLET%>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}



function toucheRetour() {
//-- Déclenchement de la recherche sur la touche retour
  if (window.event.keyCode == 13){
       soumettre("<%=request.getContextPath()%>/urgence/search.do");
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
        var url = "<%=request.getContextPath()%>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%=var_lang%>&CLE=" + cle;
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
    //On vérifie si la liste vient d'une sélection dans la liste modèle-marque. 
    //Si oui, on sélectionne le modèle dans la liste modèle.
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
//Ajout des nouvelles entrées de la liste déroulante retournée
//après un changement d'une liste dépendante.
	var oOption = document.createElement("OPTION");
	oOption.text = texte;
	oOption.value = valeur;
     if(liste == 'modele'){
	document.forms(0).modele.add(oOption); 
     }
}

function viderListe(liste){
//On vide la liste d'abord avant d'insérer les valeurs retournées
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
//On place les valeurs choisies dans les listes de marques et modèles.
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

function doSoumettreRafraichirClasse() {
   if(document.forms(0).classe.value != ""){
      soumettre('<%=request.getContextPath() + "/urgence/refreshUrgence.do"%>');
   }
}

</SCRIPT>

<TITLE><bean:message key='tabpage_urgence' /></TITLE>
</HEAD>
<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5"
	marginheight="5" marginwidth="5" onKeyDown="return toucheRetour();">

<html:form action='/urgence/search'>
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.urgences" />
	</tiles:insert>

	<TABLE width="974" cellpadding="0" cellspacing="0" border="0"
		class="tableOutline">
		<TR>
			<TD CLASS="tabBackground">
				<TABLE width="974" cellpadding="2" cellspacing="0" border="0">
					<TR>
						<TD ALIGN="middle" COLSPAN="5">
							<html:img page="/images/blank.gif" width="874" height="1" border="0" />
						</TD>
					</TR>
					<tr>
						<TD ALIGN="middle" COLSPAN="2">
							<html:img page="/images/blank.gif" width="68" height="1" border="0" />
						</TD>				
						<TD class="listDetailOdd"><b><bean:message key='i_cl_cle_t'/></b> 
							<myriap:select name='rechercheUrgence'	property="classe" tabindex="1" size="1"	style="HEIGHT: 20px; WIDTH: 150px" onchange="doSoumettreRafraichirClasse();">
							<cardex:optionTag
								classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>'
								valeurTableValeur='<%=GlobalConstants.TableValeur.CLASSE_URGENCE %>' />
							</myriap:select>
						</TD>
					</tr>
					<tr>
						<TD ALIGN="middle" COLSPAN="2">
							<html:img page="/images/blank.gif" width="68" height="1" border="0" />
						</TD>				
					</tr>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
	<logic:equal name="rechercheUrgence" property='classe'	value='<%=String.valueOf(GlobalConstants.Classes.AMBULANCE)%>'>
		<tiles:insert page="/templates/urgence/tpl_recherche_urgence_ambulance_form.jsp" flush="true">
			<tiles:put name="urlSecuriteRecherche" value="/urgence/search.do" />
		</tiles:insert>
	</logic:equal>

	<logic:equal name="rechercheUrgence" property='classe'	value='<%=String.valueOf(GlobalConstants.Classes.POLICE)%>'>
		<tiles:insert page="/templates/urgence/tpl_recherche_urgence_police_form.jsp" flush="true">
			<tiles:put name="urlSecuriteRecherche" value="/urgence/search.do" />
		</tiles:insert>
	</logic:equal>

	<logic:equal name="rechercheUrgence" property='classe' value='<%=String.valueOf(GlobalConstants.Classes.POMPIER)%>'>
		<tiles:insert page="/templates/urgence/tpl_recherche_urgence_pompier_form.jsp" flush="true">
			<tiles:put name="urlSecuriteRecherche" value="/urgence/search.do" />
		</tiles:insert>
	</logic:equal>

	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
	<jsp:include page='/commun/aide.jsp' flush="true" />
	<jsp:include page='/templates/tpl_erreur.jsp' flush='true' />

	<logic:equal name="rechercheUrgence" property='classe' value='<%=String.valueOf(GlobalConstants.Classes.AMBULANCE)%>'>
		<jsp:include page='/templates/urgence/tpl_recherche_urgence_ambulance_resultats.jsp' flush='true' />
	</logic:equal>

	<logic:equal name="rechercheUrgence" property='classe' value='<%=String.valueOf(GlobalConstants.Classes.POLICE)%>'>
		<jsp:include page='/templates/urgence/tpl_recherche_urgence_police_resultats.jsp' flush='true' />
	</logic:equal>

	<logic:equal name="rechercheUrgence" property='classe' value='<%=String.valueOf(GlobalConstants.Classes.POMPIER)%>'>
		<jsp:include page='/templates/urgence/tpl_recherche_urgence_pompier_resultats.jsp' flush='true' />
	</logic:equal>

	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />

</html:form>

</BODY>
</HTML>