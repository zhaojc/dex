

// Retourne la requête URL qui est appeler par AJAX, bref, le servlet 
function construireURL(patch, servlet, langue, listeSource){
	var sourceValue = document.getElementsByName(listeSource)[0].value;
	return patch + "/" + servlet + "?LANGUE=" + langue + "&CLE="+ sourceValue;
}

function getSelect(name){
	return document.getElementsByName(name)[0];
}

// prend la liste source lance l'url et popule la liste à populer
function doRafraichirListe(listeSource, listeAPopuler, url) {
	var sourceValue = getSelect(listeSource).value;

    if (sourceValue == "") {
        viderListe(listeAPopuler);
    } else {
        var req = initRequest(url);

        req.onreadystatechange = function() {
            if (req.readyState == 4) {

                if (req.status == 200) {
                    parseMessages(req.responseXML, listeAPopuler);
                } else if (req.status == 204){
                    viderListe(listeAPopuler);
                }
            }
        };
		req.open("GET", url, true);
        req.send(null);
    }
}
// boucle et popule la liste avec les données de retour du servlet
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
}

//Ajout des nouvelles entrées de la liste déroulante retournée
//après un changement d'une liste dépendante.
function remplirListe(liste,valeur,texte){
	var oOption = document.createElement("OPTION");
	oOption.text = texte;
	oOption.value = valeur;
	var element = document.getElementsByName(liste)[0];
	element.add( oOption );
}

//On vide la liste d'abord avant d'insérer les valeurs retournées
function viderListe(liste){
//On vide la liste d'abord avant d'insérer les valeurs retournées
	var oOption = document.createElement("OPTION");
	oOption.text = "";
	oOption.value = "";
	
	if (liste != ''){
		var contenu = document.getElementsByName(liste)[0];
		
		if (contenu != null){
			contenu.options.length = 0;    
			contenu.add(oOption);
		}
	}
}
