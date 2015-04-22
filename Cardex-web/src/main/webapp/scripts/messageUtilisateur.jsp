<script>
var debutTableBase = "<TABLE border=1 width='100%'><TR><TD align='center' style='color:#FFFFFF;background-color:#447db8;font-weight:bold;'><FONT size='+2'>";
var debutTableBleu = "<TABLE border=1 width='100%'><TR><TD align='center' style='color:#FFFFFF;background-color:#0061CF;font-weight:bold;'><FONT size='+2'>";
var debutTableRouge = "<TABLE border=1 width='100%'><TR><TD align='center' style='color:#FFFFFF;background-color:#FF0000;font-weight:bold;'><FONT size='+2'>";
var finTable = "</FONT></TD></TR></TABLE>";

function construireTableRouge(texte){
	return debutTableRouge+texte+finTable; 
}

function construireTableBleu(texte){
	return debutTableBleu+texte+finTable; 
}

function construireTableBase(texte){
	return debutTableBase+texte+finTable; 
}

function doAfficherMessageUtilisateur() {
	var url = "<%=request.getContextPath()%>/AfficherMessageUtilisateur";
    var req = initRequest(url);
    viderMessageUtilisateur();
    
    req.onreadystatechange = function() {
    	
    	if (req.readyState == 4) {
    		
    		if (req.status == 200) {
            	parseMessageUtilisateur(req.responseXML);
            }
        }
    };
	req.open("GET", url, true);
    req.send(null);
}

function parseMessageUtilisateur(xml){
	var divMessageUtilisateur = document.getElementById("divMessageUtilisateur");
	var html = "";
    var listes = xml.getElementsByTagName("messages")[0];
    var listeAlerte1 = new Array();
	
    if (listes != null && listes.childNodes.length > 0) {
    	
	    for (var loop = 0; loop < listes.childNodes.length; loop++) {
	    	var message = listes.childNodes[loop];
    	    var niveau = message.attributes.getNamedItem("niveau").text;
	        var texte = message.childNodes[0].text;
	        
	        if (niveau == 1){
	        	listeAlerte1.push(texte);
	        	html += construireTableRouge(texte);
	    	}else if (niveau == 2){
	    		html += construireTableRouge(texte);
	    	}else if (niveau == 3){
	        	html += construireTableBleu(texte);
	        }
    	}
    }
    if(html == ""){
    	divMessageUtilisateur.innerHTML = construireTableBase("Bienvenue au système Cardex");
    }else{
	    divMessageUtilisateur.innerHTML = html;
	}
    afficherMessagePopUp(listeAlerte1);
}

function afficherMessagePopUp(listeAlerte1){
	
	for (var loop = 0; loop < listeAlerte1.length; loop++) {
		message(listeAlerte1[loop]);
	}
}

function viderMessageUtilisateur(){
	var divMessageUtilisateur = document.getElementById("divMessageUtilisateur");
	divMessageUtilisateur.innerHTML = construireTableBase("Bienvenue au système Cardex");
}

function afficherMessageUtilisateur(){
	doAfficherMessageUtilisateur();
	setTimeout("afficherMessageUtilisateur();", 900000); // 1000 = 1 sec / 60000 = 1 minutes / 900000 = 15 minutes
}
</script>