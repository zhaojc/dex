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
<script>
<% if ("fr".equals(var_lang)){ %>
var templateDateHeure = "____-__-__ __:__:__";
var templateDate = "____-__-__";
<%} else {%>
var templateDateHeure = "__/__/____ __:__:__";
var templateDate = "__/__/____";
<%}%>

function formatDate(o) {
	o.value = obtenirResultatInsertion(o.value, templateDate, 8);
}

function formatDateHeure(o) {
	o.value = obtenirResultatInsertion(o.value, templateDateHeure, 14);
}

function obtenirResultatInsertion(valeur, template, maxSize){
	var retour = "";
	var iValeur = 0;
	
	if (valeur == "")
		return "";
	
	valeur = getNumeric(valeur);
	valeur = (valeur+"00000000000000").substring(0, maxSize);

	return formaterTemplate(valeur, template);
}

function formaterTemplate(valeur, template){
	var retour = "";
	var iValeur = 0;
	
		for (var iControl=0;iControl<template.length;iControl++){
		var v = valeur.charAt(iValeur);
		var vControl = template.charAt(iControl);

		if (vControl == "_"){
			retour += v;
			iValeur++;

		} else if (vControl != "_"){
			retour += vControl;
		}

		if (iValeur > valeur.length)
			break;
	}

	return retour;
}

function doTraitsDateTAG(objet, keyCode, nomProchainChamp) {
	//-- Insertion de traits d'union dans les dates, s'il y a lieu.
	
	var dateSaisie;
	var assignerProchainChamp;
	assignerProchainChamp = false; 
	dateSaisie = objet.value;
	
	dateSaisie = dateSaisie.replace(/-/g, "");
	dateSaisie = dateSaisie.replace(/\//g, "");
	dateSaisie = dateSaisie.replace(/:/g, "");
	dateSaisie = dateSaisie.replace(/ /g, "");

	if (isNavigationDateTag(keyCode))
		return;

	if(dateSaisie.length == 8 && objet.size == 9){
		objet.value = formaterTemplate(dateSaisie, templateDate);
		
		assignerProchainChamp = true;
	}else if(dateSaisie.length == 14 && objet.size == 19){
		objet.value = formaterTemplate(dateSaisie, templateDateHeure);
			
		assignerProchainChamp = true;
			
	}else if (dateSaisie.length == objet.size){// max du champ
		assignerProchainChamp = true;
	}
	
	if(assignerProchainChamp
	&& nomProchainChamp != ''){
		var prochainChamp = document.getElementsByName( nomProchainChamp )[0];

		if(prochainChamp.scrollWidth>0) 
			prochainChamp.focus();
	} 
}

   function isNumericKeyCode(keyCode){
       return ((keyCode >= 48 && keyCode <= 57)
       || (keyCode >= 96 && keyCode <= 105))
       || keyCode == 8 //Effacer
       || keyCode == 9 //TAB
       || keyCode == 109 || keyCode == 111
       || keyCode == 16 || keyCode == 186 || keyCode == 32
       || keyCode == 35 || keyCode == 36 || keyCode == 37
       || keyCode == 39;
   }

   function getNumeric(text){
   	var retour = "";

   	for (var i=0;i<text.length;i++){

   		if ( isNumeric(text.charAt(i)) )
   			retour += text.charAt(i);
   	}

   	return retour;
   }

function isNumeric(sText){
	var ValidChars = "0123456789";
	var IsNumber=true;
	var Char;

	for (i = 0; i < sText.length && IsNumber == true; i++) {
		Char = sText.charAt(i);

	  	if (ValidChars.indexOf(Char) == -1) {
			IsNumber = false;
		}
	}
	return IsNumber;
}
</script>