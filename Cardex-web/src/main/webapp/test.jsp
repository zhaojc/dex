<%-- Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
$Id: index.jsp,v 1.5 2005/08/29 22:10:20 gmurray71 Exp $ --%>

<html>
<head>
<style type="text/css">

.popupItem {
  background: #FFFAFA;
  color: #000000;
  text-decoration: none;
  font-size: 1.2em;
}

.popupItem:hover {
  background: #7A8AFF;
  color: #FFFAFA;
}

.popupRow {
  background: #FFFAFA;
}

</style>

<script type="text/javascript">

var isIE;
var completeTable;
var completeField;
var autorow;
var req;


function getElementY(element){
	var targetTop = 0;
	if (element.offsetParent) {
		while (element.offsetParent) {
			targetTop += element.offsetTop;
            element = element.offsetParent;
		}
	} else if (element.y) {
		targetTop += element.y;
    }
	return targetTop;
}

function init() {
    completeField = document.getElementById("complete-field");
    var menu = document.getElementById("auto-row");
    autorow = document.getElementById("menu-popup");
    autorow.style.top = getElementY(menu) + "px";
    completeTable = document.getElementById("completeTable");
    completeTable.setAttribute("bordercolor", "white");
}

function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        xml = new ActiveXObject("Microsoft.XMLHTTP");
        return xml;
    }
}


function doCompletion() {
    if (completeField.value == "") {
        clearTable();
    } else {
        var url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=nature&LANGUE=fr&CLE=72";
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                //alert(req.responseText + " - " + req.responseText.length);
                    parseMessages(req.responseXML);
                } else if (req.status == 204){
                    clearTable();
                }
            }
        };
	req.open("GET", url, true);
        req.send(null);
    }
}

function parseMessages(responseXML) {
    //clearTable();
var listes = responseXML.getElementsByTagName("listes")[0];
//alert(listes);	
   if (listes.childNodes.length > 0) {
//        completeTable.setAttribute("bordercolor", "black");
//        completeTable.setAttribute("border", "1");
//    } else {
//        //clearTable();
//    }
    preparerListe();
    for (loop = 0; loop < listes.childNodes.length; loop++) {
	//var employee = employees.childNodes[loop];
        //var firstName = employee.getElementsByTagName("firstName")[0];
        //var lastName = employee.getElementsByTagName("lastName")[0];
        //var employeeId = employee.getElementsByTagName("id")[0];
        //appendEmployee(firstName.childNodes[0].nodeValue,lastName.childNodes[0].nodeValue, employeeId.childNodes[0].nodeValue);
	var entree = listes.childNodes[loop];
        var texte = entree.getElementsByTagName("texte")[0];
        var valeur = entree.getElementsByTagName("valeur")[0];
        remplirListe(valeur.childNodes[0].nodeValue, texte.childNodes[0].nodeValue);
        //remplirListe(employeeId.childNodes[0].nodeValue, lastName.childNodes[0].nodeValue + ", " + firstName.childNodes[0].nodeValue);
    }
  }
}

function clearTable() {
    if (completeTable) {
      completeTable.setAttribute("bordercolor", "white");
      completeTable.setAttribute("border", "0");
      completeTable.style.visible = false;
      for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
        completeTable.removeChild(completeTable.childNodes[loop]);
      }
    }
}

function appendEmployee(firstName,lastName,employeeId) {
    var firstNameCell;
    var lastNameCell;
    var row;
    var nameCell;
    if (isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#FFFAFA");

    
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "autocomplete");
    linkElement.appendChild(document.createTextNode(firstName + " " + lastName));
    nameCell.appendChild(linkElement);
}

function remplirListe(cle,valeur){
var oOption = document.createElement("OPTION");
//oSelect = nom de la liste
//alert(valeur + " - " + cle);
oOption.text = valeur;
oOption.value = cle;
document.forms(0).oSelect.add(oOption); 
}

function preparerListe(){
//On vide la liste d'abord avant d'insérer les valeurs retournées
	theSel = document.forms(0).oSelect;
	theSel.options.length = 0;    
	var oOption = document.createElement("OPTION");
	oOption.text = "";
	oOption.value = "";
	document.forms(0).oSelect.add(oOption); 
}
</script>

 <title>Auto-Completion using Asynchronous JavaScript and XML (AJAX)</title>
</head>
 <body onload="init();" >
 
 <h1>Test avec le servlet RafraichirListe</h1>
 <hr/>
 <p>
  
  <form name="autofillform" action="autocomplete" method="get">
   <input type="hidden" name="action" value="lookupbyname"/>
   <table border="0" cellpadding="5" cellspacing="0">
    <tr>
     <td><b>Employee Name:</b></td>
     <td>
      <input type="text" size="20" autocomplete="off" id="complete-field" name="id" onkeyup="doCompletion();">
     </td>
    </tr>
    <tr><td>
    <SELECT ID="oSelect" style="width:'100'">
      <OPTION value="0">
    </SELECT>
    <tr><td id="auto-row" colspan="2">&nbsp;<td/></tr>
   </table>
  </form>
 <div style="position: absolute; top:170px;left:140px" id="menu-popup">
 <table id="completeTable" border="1" bordercolor="black" cellpadding="0" cellspacing="0" />
 </div>

 </body>
</html>
