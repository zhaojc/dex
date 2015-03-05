<SCRIPT language="JavaScript" type="text/javascript">

function assignerValeur(nomChamp, td){
	var nomDiv = construireNomDiv(nomChamp);
	var nomFrame = construireNomFrame(nomChamp);
	var div = document.getElementById(nomDiv);
	var frame = document.getElementById(nomFrame);
	var inputText = document.getElementsByName(nomChamp)[0];

	inputText.value = td.firstChild.firstChild.data;

	div.innerHTML = "";
	div.style.display = "none";
	frame.style.visibility = "hidden";
}

function cacherDiv(nomDiv){
	var div = document.getElementById(nomDiv);
	div.style.display = "none";
}

function cacherFrame(nomFrame){
	var frame = document.getElementById(nomFrame);
	frame.style.visibility = "hidden";
}

function afficherDiv(nomDiv){
	var div = document.getElementById(nomDiv);
	div.style.display = "";
}

function autoCompleter(nomChamp, nbrAmorce, classeControl){
	var champValeur = window.event.srcElement.value;
	champValeur = addslashes(champValeur);
	
	if (champValeur.length >= nbrAmorce)
		return setTimeout("lancerAutoCompleter('"+nomChamp+"','"+champValeur+"','"+nbrAmorce+"','"+classeControl+"');", 1000); // 1000 = 1 sec / 60000 = 1 minutes / 900000 = 15 minutes
	else{
		cacherDiv( construireNomDiv( nomChamp ) );
	    cacherFrame( construireNomFrame( nomChamp ) );
	}
	return null;
}

function addslashes(ch) {
	ch = ch.replace(/\\/g,"\\\\")
	ch = ch.replace(/\'/g,"\\'")
	ch = ch.replace(/\"/g,"\\\"")
	return ch
}

function lancerAutoCompleter(nomChamp, texteOriginal, nbrAmorce, classeControl){
	var champValeur = document.getElementsByName(nomChamp)[0].value;

	if (champValeur == texteOriginal)
		callAjaxAutoCompleter(nomChamp, champValeur, classeControl);
}

function construireURLAutoCompleter(nomChamp, valeur, classeControl){
	return "<%=request.getContextPath()%>/AutoCompleterServlet?NOM_CHAMP="+nomChamp+"&VALEUR="+valeur+"&CLASSE_CONTROL="+classeControl;
}

function construireNomDiv(nomChamp){
	return "menuDiv"+nomChamp;
}

function construireNomFrame(nomChamp){
	return "saisieAuto"+nomChamp;
}

function callAjaxAutoCompleter(nomChamp, valeur, classeControl){
	var url = construireURLAutoCompleter(nomChamp, valeur, classeControl);
    var req = initRequest(url);

    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            var nomDiv = construireNomDiv(nomChamp);            
            var nomFrame = construireNomFrame(nomChamp);	
            if (req.status == 200) {
            	
            	if (req.responseText.length == 0){
                	cacherDiv( nomDiv ); 
                	cacherFrame(nomFrame );
            	}else{
	                assignerDivAutoCompleter(nomDiv, nomFrame, req.responseText);
                }
            } else if (req.status == 204){
                cacherDiv( nomDiv );
                cacherFrame(nomFrame );
            }
        }
    };
	req.open("GET", url, true);
    req.send(null);
}	

function assignerDivAutoCompleter(nomdiv, nomFrame, tableContenu){
	var div = document.getElementById(nomdiv);
	var frame = document.getElementById(nomFrame);
	div.innerHTML = tableContenu;	
	div.style.display = "";
	frame.style.visibility = "visible";
}

// Creer une activité si la narration est en cours de création
function FermerListeCacheString() {
	var url = "<%=request.getContextPath()%>/FermerListeCacheString";
    var req = initRequest(url);
    
	req.open("GET", url, true);
    req.send(null);
}

function onFocusOut(ctTimeOut){
	clearTimeout(ctTimeOut);
	FermerListeCacheString();
	/* On ne peut pas fermer la liste sur cet évènement.
	Sinon, on ne peut pas sélectionner.
	var nomDiv = construireNomDiv(nomChamp);   
	var nomFrame = construireNomFrame(nomChamp);
	
	var div = document.getElementById(nomDiv);
	var frame = document.getElementById(nomFrame);
	
	div.innerHTML = "";
	div.style.display = "none";
	frame.style.visibility = "hidden";*/
}


</script>