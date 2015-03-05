<SCRIPT language="JavaScript" type="text/javascript">
// aideAjax.js
//Fontion d'aide en ligne. Une aide est affich�e � l'�cran en fonction de
//l'emplacement du curseur.

var dernierChamps = null;
 
document.onhelp = new Function("aideAjax();return false;");

function doAide(valeur,cle) {
    var url = "<%=request.getContextPath()%>/AfficherAide?VALEUR=" + valeur + "&CLE=" + cle;
    var req = initRequestAide(url);
	aide.innerHTML = "";
    req.onreadystatechange = function() {
    //alert(req.readyState);
        if (req.readyState == 4) {
            if (req.status == 200) {
                if(req.responseText == ""){
                   aide.innerHTML = "Aucune aide n'a �t� trouv�e pour cet �l�ment.";
                }else{
                   aide.innerHTML = req.responseText;
                }
            } else if (req.status == 204){
                aide.innerHTML = "Aucune aide n'a �t� trouv�e pour cet �l�ment.";
            }
        }
    };
	req.open("GET", url, true);
    req.send(null);
}

function aideAjax(){
	var aide = document.getElementById("aide");
	var selectBlockerAide = document.getElementById("selectBlockerAide");
	
	if (dernierChamps != null){
		var element = document.getElementsByName(dernierChamps)[0];
	    //On v�rifie le type de champ
	    if (element.type != "text"){
		    if(element.value != ""){
		    //alert(element.value);
		       //On v�rifie si la valeur est num�rique
		       doAide(dernierChamps,element.value);
		    }else{
		    //alert(element.name);
		       doAide(dernierChamps,"0");
		    }
		}else{
		   doAide(dernierChamps,"0");
		}
	}

	if(aide.style.visibility == "visible"){
	   aide.style.visibility = "hidden";
	   selectBlockerAide.style.visibility = "hidden";
	}else{
	   aide.style.visibility = "visible";
	   aide.focus();
	   selectBlockerAide.style.visibility = "visible";
	}
}

function initRequestAide(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xml = new ActiveXObject("Microsoft.XMLHTTP");
        return xml;
    }
}

document.onactivate=function(){
 if (document.activeElement != null && document.activeElement.name != null 
 && document.activeElement.name != '' && document.activeElement.name != 'undefined'){
  dernierChamps = document.activeElement.name;
 }
}
 
</script>
