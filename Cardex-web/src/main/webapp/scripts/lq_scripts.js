
function openLocation(url){
    //-- Place your code here
	  window.open(url,"","toolbar=0,location=0,directories=0,menuBar=0,scrollbars=yes,"
	    + "resizable=1,dependent=yes,width=800,height=600");
}

//-- Utiliser avec le script date_picker_fr.js
function openCalendar(elementName, elementValue){
  doCalendarPositioning();
  show_calendar(elementName, elementValue);
  document.all['CALENDAR_DIV'].style.visibility = "visible";
  document.all['selectBlockerCalendar'].style.visibility = "visible";
}

//-- Utiliser avec le script date_picker_fr.js
function openDate(elementName, elementValue){
   doCalendarPositioning();
   show_calendar(elementName, elementValue);
   document.all['CALENDAR_DIV'].style.visibility = "visible";
   document.all['selectBlockerCalendar'].style.visibility = "visible";   
}	

//-- Utiliser avec le script date_time_picker_fr.js
function openDateTime(elementName, elementValue){
   doCalendarPositioning();
   show_date_time(elementName, elementValue);
   document.all['CALENDAR_DIV'].style.visibility = "visible";
	document.all['selectBlockerCalendar'].style.visibility = "visible";   
}

//-- Utiliser avec le script twin_date_time_picker_fr.js
function generateTwinDateTime(element1Name, element2Name, element1Value){
  doCalendarPositioning();
  show_date_time4twin_fields(element1Name, element2Name, element1Value);
  document.all['CALENDAR_DIV'].style.visibility = "visible";
  document.all['selectBlockerCalendar'].style.visibility = "visible";  
}


/**************************************************************/
//-- Positionnement du calendrier par rapport à un évènement click
//-- de la souris.
var xPos = 0;
var yPos = 0;

function setXY(someX, someY){
  //-- Affichage à gauche du click de souris
  //-- Valeurs par défaut pouvant être ajustées
  //-- sur l'appel de la méthode
  xPos = someX - 170;
  yPos = someY - 15;
}

function doCalendarPositioning(){
  document.all['CALENDAR_DIV'].style.left = xPos;
  document.all['CALENDAR_DIV'].style.top = yPos;
  document.all['selectBlockerCalendar'].style.left = xPos;
  document.all['selectBlockerCalendar'].style.top = yPos;
}

//-- Peuplement dynamique du calendrier
function drawCalendar(strHTML){
  document.all['CALENDAR_DIV'].innerHTML = strHTML;
}



/**************************************************************/
//-- Fermeture du calendrier sur l'évènement onmouseout.
var divToHide = false;
var timeoutDelay = 100;

function hideCalendar(){
  //-- Programmation d'un court délai avant la fermeture du calendrier,
  //-- en millisecondes
  divToHide = setTimeout("doHideAfterWait()",timeoutDelay);
}

//-- Exécution de la fermeture
function doHideAfterWait(){
  if (divToHide){
    document.all['CALENDAR_DIV'].style.visibility = "hidden";
    document.all['selectBlockerCalendar'].style.visibility = "hidden";
    clearTimeout(divToHide);
    divToHide = false;
  }
}



/**************************************************************/
//-- Fonction calculant le nombre de mois entre 2 dates valides.
//-- Retourne un nombre de mois si les dates sont valides,
//-- une chaîne de caractère vide si non-valides.
function monthsUntil(startDate, endDate){
  var stringStartDate = startDate.substr(0,10);
  var stringEndDate = endDate.substr(0,10);
  var isValidFormat = new Boolean(false);
  var aYear;
  var aMonth;
  var aDay;

  var monthsStart = 0;
  var monthsEnd = 0;

  isValidFormat = validateDateFormat(stringStartDate) && validateDateFormat(stringEndDate);

  if (!(isValidFormat)){ return ""; }

  //-- Récupération des valeurs de date selon le format
  //-- YYYY-MM-DD ou MM/DD/YYYY
  if (stringStartDate.indexOf("/") != -1){
    aYear = stringStartDate.substr(6,4);
    aMonth = stringStartDate.substr(0,2);
  }else{
    aYear = stringStartDate.substr(0,4);
    aMonth = stringStartDate.substr(5,2);
  }
  monthsStart = (aYear * 12) + (aMonth -1);

  //-- Récupération des valeurs de date selon le format
  //-- YYYY-MM-DD ou MM/DD/YYYY
  if (stringStartDate.indexOf("/") != -1){
    aYear = stringEndDate.substr(6,4);
    aMonth = stringEndDate.substr(0,2);
  }else{
    aYear = stringEndDate.substr(0,4);
    aMonth = stringEndDate.substr(5,2);
  }
  monthsEnd = (aYear * 12) + (aMonth -1);

  return (monthsEnd - monthsStart);
}

/**************************************************************/
//-- Fonction calculant le nombre d'années entre aujourd'hui et une autre date.
//-- Retourne un nombre d'année si la date est valide,
//-- une chaîne de caractère vide si non-valide.
function computeAge(birthDate){
  var totalYears = 0;
  var stringDate = birthDate.substr(0,10);
  var isValidFormat = new Boolean(false);
  var thisYear;
  var thisMonth;
  var thisDay;
  isValidFormat = validateDateFormat(stringDate);

  if (!(isValidFormat)){ return ""; }

 var todayDate = new Date();

 var aDayValue = 1000 * 60 * 60 * 24;                      //ms, sc, mm, hh
 var aYearValue = (365.25 * aDayValue);
  //-- Récupération des valeurs de date selon le format
  //-- YYYY-MM-DD ou MM/DD/YYYY
  if (stringDate.indexOf("/") != -1){
    var thisYear = stringDate.substr(6,4);
    var thisMonth = stringDate.substr(0,2);
    var thisDay = stringDate.substr(3,2);
  }else{
    var thisYear = stringDate.substr(0,4);
    var thisMonth = stringDate.substr(5,2);
    var thisDay = stringDate.substr(8,2);
  }
  //alert(thisYear + ":" + thisMonth + ":" + thisDay + "." )
  var birthDateValue = new Date (thisYear, thisMonth -1, thisDay);
  var timeDiff = todayDate - birthDateValue;
  totalYears = Math.floor(timeDiff / aYearValue);

  return totalYears;
}

/**************************************************************/
//-- Vérification du format de la date (YYYY-MM-DD ou MM/DD/YYYY)
function validateDateFormat(longYearFormat){
  var datePattern;

  if (longYearFormat.length < 10) {
    return false
  }

  if (longYearFormat.indexOf("-") != -1){
    //Masque de saisie, Doit être au format "yyyy-mm-dd"
  	datePattern = /^(\d+)\-(\d+)\-(\d+)$/;
  	if ((datePattern.test(longYearFormat) != true) || (longYearFormat.length < 10)){
  	  //alert("Date de format invalide : "+ longYearFormat);
  	  return false;
  	}
  }else if (longYearFormat.indexOf("/") != -1){
      //Masque de saisie, Doit être au format "mm/dd/yyyy"
    	datePattern = /^(\d+)\/(\d+)\/(\d+)$/;
    	if ((datePattern.test(longYearFormat) != true) || (longYearFormat.length < 10)){
    	  //alert("Invalid Date Format : "+ longYearFormat);
    	  return false;
    	}
  }else {
    return false;
  }

  return true;
}

/**************************************************************/
//-- Fonction de suppression de l'attribut "disabled" avant un submit
function unlockFields(){
   var i;
   for (i = 0; i < document.forms(0).elements.length; i++){
     document.forms(0).elements(i).disabled = false;
   }
}


/**************************************************************/
//-- Fonction de changement de couleurs des boutons de navigation
function changeBackgroundColor(styleName){
  //document.all[styleName].style.backgroundColor = #33cc99;
  //alert(styleName);
  var ts;
  var i;
  for (i = 0; i < document.styleSheets[0].rules.length; i++){
      s = document.styleSheets[0].rules[i].selectorText;

      if ( s.indexOf(styleName) != -1) {
        if(document.styleSheets[0].rules[i].style.backgroundColor == "#cff0e6"){
          document.styleSheets[0].rules[i].style.backgroundColor = "ffffff";
        } else {
          document.styleSheets[0].rules[i].style.backgroundColor = "#cff0e6";
        }
      }
  }

}

/**************************************************************/
//-- Fonction d'alternance d'affichage des onglets

function onLoadToggleDivisionVisibility(tabId){
	var exactTabId = tabId.substring(1, tabId.length);
	var div = document.getElementById(exactTabId);
	
	if (div != null){
		div.click();
	}
}

function toggleDivisionVisibility(tabName){

    var i;
    var s;
    var divName = "DATA_" + tabName.substring(5,tabName.length);
    var tempDivName;

    //-- Navigateur par défaut: IE 5.0 ou 5.5
    isIE5 = false;
    isIE5_5up = false;

    if (navigator.appVersion.indexOf("MSIE 5") != -1){
      isIE5 = true;
    }

    if (navigator.appVersion.indexOf("MSIE 5.5") != -1){
        isIE5_5up = true;
        isIE5 = false;
    }

    if (navigator.appVersion.indexOf("MSIE 6") != -1){
        isIE5_5up = true;
        isIE5 = false;
    }

    for (i = 0; i < document.styleSheets[0].rules.length; i++){
      s = document.styleSheets[0].rules[i].selectorText;
      
      if ( s.indexOf("#TAB_") != -1) {
    	  document.styleSheets[0].rules[i].style.color = "#000000";
    	  if ( s.indexOf(tabName) != -1) {
        	// SÉLECTION
        	//document.styleSheets[0].rules[i].style.backgroundColor = "#447db8";
          //document.styleSheets[0].rules[i].style.color = "#ffffff"
          //-- Préparation de la visibilité de la division
          if (isIE5_5up){
            document.all[divName].style.top = 0;
            document.all[divName].style.left = 0;
          }
          document.all[divName].style.visibility = "visible";
        }else {
          
          //document.styleSheets[0].rules[i].style.backgroundColor = "#eeece0" //"#E6EBF4" //"#cbd8e9";
          //document.styleSheets[0].rules[i].style.color = "#000000" //"#095b97";
        }
      }else {

        if ( s.indexOf("#DATA_") != -1) {
          tempDivName = s.substring(1,s.length);
          
          if (tempDivName != divName){
           	 document.all[tempDivName].style.visibility = "hidden";
          }
        }
      }
    }

  }

/**************************************************************/
//-- Fonction pour déterminer le chemin d'accès des gabarits
function getChemin(serveur, port,contexte){
  chemin = "";
  
  if(port == "443"){
     chemin = "https://" + serveur + contexte + "/applets/templates/";
  }
  if(port == "80"){
     chemin = "http://" + serveur + contexte + "/applets/templates/";
  }
  
  if(port == "9080"){
     chemin = "http://" + serveur + ":9080" + contexte + "/applets/templates/";
  }
  

  if(port == "9081"){
	     chemin = "http://" + serveur + ":9081" + contexte + "/applets/templates/";
	  }  

  if(port == "9082"){
	     chemin = "http://" + serveur + ":9082" + contexte + "/applets/templates/";
	  }  

  if(port == "9083"){
	     chemin = "http://" + serveur + ":9083" + contexte + "/applets/templates/";
	  }  
  
return chemin;
}

/**************************************************************/
//-- Remplacement des apostrophes pour éviter l'affichage &#8217;
//-- Cette conversion se produit lors de la copie de Word à Cardex.
function remplaceApostrophes(narration){
	out = "’"; // replace this
	add = "'"; // with this
	temp = "" + narration;

	while (temp.indexOf(out)>-1) {
	    pos= temp.indexOf(out);
	    temp = "" + (temp.substring(0, pos) + add + 
	    temp.substring((pos + out.length), temp.length));
	}
	return temp;
}

//-------------------------------------------------
//Cette fonction permet de retrouver une entrée dans une liste déroulante
//en frappant plusieurs lettres successives. Elle est surtout utile pour les 
//longues listes, comme les villes. Sans cette fonction, chaque lettre frappée
//déroule la liste à la première occurence de la lettre.

//variable pour garder la construction de la string

var incrementalString = "";

//**************************************************

//fonction pour réinitialiser la recherche de string

//**************************************************

function resetIncrementalSearch() {
   incrementalString = "";
}
var iTimeOut = 0;
function resetIncrementalSearchTimeOut(i) {

	if (i == iTimeOut){
	   incrementalString = "";
	   iTimeOut = 0;
   }
}

//**************************************************************************************

//fonction qui permet de faire une recherche incrémental (type ahead) dans une drop-down

//**************************************************************************************
var insertionCaractereListeAutomatique = false;

function typeAhead(selectBox, event) {

	var unicode=event.charCode? event.charCode : event.keyCode
	var actualkey=String.fromCharCode(unicode);
	insertionCaractereListeAutomatique = true;
	
	incrementalString = incrementalString + actualkey.toLowerCase();
	for (i=0; i<selectBox.length; i++) {
	  if (selectBox.options[i].text.substring(0,incrementalString.length).toLowerCase() == incrementalString) {
			selectBox.selectedIndex = i;
			try{ // Il est possible qu'il n'y ait pas de onchange.
				selectBox.onchange();
			}catch(err){}
			break;
	  }
	} //for
	event.cancelBubble = true;
	event.returnValue = false;
	iTimeOut++;
	window.setTimeout("resetIncrementalSearchTimeOut("+iTimeOut+")", 2000);
} //typeAhead()

//-------------------------------------------------
//------ cette méthode est utilisé par les processus AJAX -------
// Retourne l'active X de AJAX
function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xml = new ActiveXObject("Microsoft.XMLHTTP");
        return xml;
    }
}

//-------------------------------------------------
//------ méthode utilisé par le tag EnteteListeTriable -------
function trierSoumettre(actionSoumettre, nomTable, sortBy, sortDescendant) {
	document.getElementById( "nomTable" ).value = nomTable;
	document.getElementById( "trierPart" ).value = sortBy;
	document.getElementById( "trierAscendant" ).value = sortDescendant;
	soumettre(actionSoumettre);
}

function containArray(key, array){

	for(var i=0;i<array.length;i++){
		if (array[i] == key)
			return true;
	}
	return false;
}

function hand(){
	document.body.style.cursor='pointer';
}

function auto(){
	document.body.style.cursor='auto';
}


//-------------------------------------------------
// Soumettre
    
var requeteEnvoye = false;

function soumettre(action){
	if (requeteEnvoye == false){
		requeteEnvoye = true;
		cursorWait();
		document.forms(0).action=action;
		document.forms(0).submit();	
	}
}

function post(url){
	if (requeteEnvoye == false){
		requeteEnvoye = true;
		window.parent.location = url;
	}
}

function soumettreParams(action, param1, param2){
	document.getElementById("param1").value = param1;
	document.getElementById("param2").value = param2;
	soumettre(action);
}

function windowClose(){
	//window.close();
	var win=window.open("","_self");
	win.close();
}

function setShow(divName){
	var cardex01Div = document.getElementById(divName);
	cardex01Div.style.visibility = "visible";
}

function setHide(divName){
	var cardex01Div = document.getElementById(divName);
	cardex01Div.style.visibility = "hidden";
}

function setFocus(elementName){
	var element = document.getElementsByName(elementName)[0];
	element.focus();
}

function deleteFile(nom){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	
	if (fso.FileExists(nom)){
		fso.DeleteFile(nom, true);
	}
}

function cursorWait(){
	document.body.style.cursor = "wait";
}

function cursorDefault(){
	document.body.style.cursor = "default";
}

// Ajouter un evenement à un object html
function addEvent(obj, evType, fn){
	
	if (obj.addEventListener){
		obj.addEventListener(evType, fn, false);
		return true;
	
	} else if (obj.attachEvent){
		var r = obj.attachEvent("on"+evType, fn);
		return r;
	} else {
		return false;
	}
}

//  check for valid numeric strings
function IsNumeric(strString){
   var strValidChars = "0123456789";
   var strChar;
   var blnResult = true;

   if (strString.length == 0) return false;

   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++){
      strChar = strString.charAt(i);
      
      if (strValidChars.indexOf(strChar) == -1){
         blnResult = false;
      }
   }
   return blnResult;
}

//valider une valeur monétaire
function validerArgent(input){
   var strValidChars = "-1234567890,.";
   var strChar = "";
   var strValide = "";
   var strString = input.value;
   var virgulePresente = false;
   var nombreApresVirgule = 0;
   var valideChar = 0;
   var dernierePositionVirgulePossible = input.getAttribute('maxlength') - 2; 
   
   if (strString.length == 0) return;

   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length; i++){
	   strChar = strString.charAt(i);
	   if (strValidChars.indexOf(strChar) > -1){
		   valideChar++;
		   
		   // On pousse la virgule oubligatoire
		   if (virgulePresente == false
		   && (strString.charAt(i) != "," || strString.charAt(i) == ".")
		   && valideChar == dernierePositionVirgulePossible){		   
			   virgulePresente = true;
			   strValide +=  ",";
		   }
		   
		   if (virgulePresente == true){
			   
			   if (nombreApresVirgule == 2)
				   continue;
			   nombreApresVirgule++;
		   }
		   
		   if(strString.charAt(i) == ",")
			   virgulePresente = true;
		   
		   if(strString.charAt(i) == "."){
        	  strValide = strValide + ","; //On remplace un point par une virgule décimale.
        	  virgulePresente = true;
          }else{
        	  strValide = strValide + strChar;
          }
      }
   }
   input.value = strValide;
}

// Retirer le Click droit
var rightClick = false;


function getOnContextMenu(){
	return rightClick;
}

// Retirer l'usage de la touche CTRL
var toucheCTRL = false;
function desactiveCTRL() {

  if (event.ctrlKey) {
    event.returnValue = toucheCTRL;
  }
}

function right(str, n)
/***
        IN: str - the string we are RIGHTing
            n - the number of characters we want to return

        RETVAL: n characters from the right side of the string
***/
{
        if (n <= 0)     // Invalid bound, return blank string
           return "";
        else if (n > String(str).length)   // Invalid bound, return
           return str;                     // entire string
        else { // Valid bound, return appropriate substring
           var iLen = String(str).length;
           return String(str).substring(iLen, iLen - n);
        }
}

//Permet la tabulation automatique d'un champ au suivant.
function changerChamp(o, nomProchainChamp) {
	var key = window.event.keyCode;
	//alert(key);
    var filter = [0,8,9,16,17,18,35,36,37,38,39,40,46];
	if (o.value.length == o.maxLength && !contientElement(filter,key)){
		var prochainChamp = document.getElementsByName( nomProchainChamp )[0];
		prochainChamp.focus();
	}
}
//On filtre les actions du clavier qui ne sont pas des caractères.
//Sinon, on ne peut pas utiliser les flèches de curseur par exemple.
function contientElement(filtre, cle) {
    var trouve = false;
    var index = 0;
    while(!trouve && index < filtre.length){
        if(filtre[index] == cle){
            trouve = true;
        }else{
            index++;
        }
    }
    //alert(trouve + " - " + filtre);
        return trouve;
}
/*
 * Disable un button par ID
 */
function disableButton(id){
	var button = document.getElementById(id);
	
	if (button != null)
		button.disabled = true;
}

/*
 * Construire les paramètres le la requête post
 */
function construireParametre(parametre){
	var valeur = document.getElementsByName(parametre)[0].value;
	return "&"+parametre+"="+valeur;
}

function construireParametreRadio(parametre){
	var radio = document.getElementsByName(parametre);
	
    for (var i = 0; i < radio.length; i++ ) {
        
		if (radio[i].checked == true) {
            return "&"+parametre+"="+radio[i].value;
		}
    }
	return "";
}

function isNavigationDateTag(keyCode){
   return keyCode == 35 || keyCode == 36  // home fin
   || keyCode == 37 || keyCode == 39; // flèche arrière flèche avant
}

function isNumericTag(keyCode){
	   return ((keyCode >= 48 && keyCode <= 57) // 0-1--2-3-4-5-6-7-8-9 Haut clavier
	   || (keyCode >= 96 && keyCode <= 105)) // 0-1-2-3-4-5-6-7-8-9 Key pad
	   || keyCode == 8 //Effacer
	   || keyCode == 9 //TAB
	   || keyCode == 46 // DEL
	   || keyCode == 35 || keyCode == 36  // home fin
	   || keyCode == 37 || keyCode == 39; // flèche arrière flèche avant
}

function isAlphabetiqueTag(keyCode){
	   return (keyCode >= 65 && keyCode <= 90) // A-Z
	   || keyCode == 8 //Effacer
	   || keyCode == 9 //TAB
	   || keyCode == 46 // DEL
	   || keyCode == 35 || keyCode == 36  // home fin
	   || keyCode == 37 || keyCode == 39; // flèche arrière flèche avant
}

function isArgentTag(keyCode){
	return ((keyCode >= 48 && keyCode <= 57) // 0-1--2-3-4-5-6-7-8-9 Haut clavier
	   || (keyCode >= 96 && keyCode <= 105)) // 0-1-2-3-4-5-6-7-8-9 Key pad
	   || keyCode == 8 //Effacer
	   || keyCode == 9 //TAB
	   || keyCode == 46 // DEL
	   || keyCode == 35 || keyCode == 36  // home fin
	   || keyCode == 37 || keyCode == 39  // flèche arrière flèche avant
	   || keyCode == 109 //Signe moins, pour entrer des nombres négatifs
	   || keyCode == 188 // virgule
	   || keyCode == 110 || keyCode == 190;  // point (sera transformé en virgule décimale à la sortie du champ)
	   //On accepte le point en raison des utilisateurs trop habitués à saisir un point ou qui utilisent le pavé numérique.
}

var isShift=false;

function keyShiftUP(keyCode){
    if(keyCode==16)	isShift=false;  // SHIFT
}

function isNumericImmatriculationTag(keyCode){
	if(keyCode==16)	isShift=true; // SHIFT
	return isShift==false && (isNumericTag(keyCode) || isAlphabetiqueTag(keyCode));
}

function isNumericDateTag(keyCode){
   return ((keyCode >= 48 && keyCode <= 57) // 0-1--2-3-4-5-6-7-8-9 Haut clavier
   || (keyCode >= 96 && keyCode <= 105)) // 0-1-2-3-4-5-6-7-8-9 Key pad
   || keyCode == 8 //Effacer
   || keyCode == 9 //TAB
   || keyCode == 46 // DEL
   || keyCode == 35 || keyCode == 36  // home fin
   || keyCode == 37 || keyCode == 39 // flèche arrière flèche avant
   || keyCode == 109 || keyCode == 111; // '-' '/'
}

function isNumericDateHeureTag(keyCode){
	return ((keyCode >= 48 && keyCode <= 57) // 0-1--2-3-4-5-6-7-8-9 Haut clavier
	   || (keyCode >= 96 && keyCode <= 105)) // 0-1-2-3-4-5-6-7-8-9 Key pad
	   || keyCode == 8 // Effacer
	   || keyCode == 9 // TAB
	   || keyCode == 46 // DEL
	   || keyCode == 32 // Espace
	   //|| keyCode == 16 // Shift
	   || keyCode == 186 // :
	   || keyCode == 35 || keyCode == 36  // home fin
	   || keyCode == 37 || keyCode == 39 // flèche arrière flèche avant
	   || keyCode == 109 || keyCode == 111; // '-' '/'
	}

function toUpper(o){
	o.value = o.value.toUpperCase();
}

function getInternetExplorerVersion()
	//Returns the version of Internet Explorer or a -1
	//(indicating the use of another browser).
	{
	var rv = -1; // Return value assumes failure.
	if (navigator.appName == 'Microsoft Internet Explorer')
	{
	 var ua = navigator.userAgent;
	 var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	 if (re.exec(ua) != null)
	   rv = parseFloat( RegExp.$1 );
	}
	return rv;
}

function vider(nomChamp){
	var champ = document.getElementsByName(nomChamp)[0];
	if (champ != undefined)
		champ.value = "";
}

function retourPostControleNavigation(actionSoumettre, nomTable, numeroPageSelectionne) {
	
	if (event.keyCode == 13){
		soumettreControleNavigation(actionSoumettre, nomTable, numeroPageSelectionne);
		event.keyCode=null;
		return false;
	}else
		return isNumericTag(event.keyCode);
}

function soumettreControleNavigation(actionSoumettre, nomTable, numeroPageSelectionne) {
	document.getElementById( "nomTable" ).value = nomTable;
	document.getElementById( nomTable+".numeroPageCourante" ).value = numeroPageSelectionne;
	soumettre(actionSoumettre);
}
