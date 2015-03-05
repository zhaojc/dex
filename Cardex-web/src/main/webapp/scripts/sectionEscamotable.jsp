<SCRIPT language="JavaScript" type="text/javascript">
//-------------------------------------------------
//------ méthodes utilisé par le tag SectionEscamotableEnteteTR -------
function basculerSectionEscamotable(sectionEscamotableValeur, sufixIdCible){
	var img = document.getElementById("sectionEscamotableImg"+sufixIdCible);
	var tr = document.getElementById("sectionEscamotableContenuTr"+sufixIdCible);
	var imgSrc = img.src.slice(img.src.length - 8);
	var sectionEscamotableValeurDocument;
	var valeurSectionEscamotableValeurDocument;
	
	if (sectionEscamotableValeur != ""){
		sectionEscamotableValeurDocument = document.getElementsByName(sectionEscamotableValeur)[0];
	}
	
	if (imgSrc == "plus.gif"){
		img.src = "<%=request.getContextPath()%>/images/moins.gif";
		tr.style.display = "";
		valeurSectionEscamotableValeurDocument="true";
	} else {
		img.src = "<%=request.getContextPath()%>/images/plus.gif";
		tr.style.display = "none";
		valeurSectionEscamotableValeurDocument="false";
	}
	
	if (sectionEscamotableValeurDocument != undefined){
		sectionEscamotableValeurDocument.value=valeurSectionEscamotableValeurDocument;
	}
}

var ajaxTrSufixIdArray = new Array();

function callAjaxTr(url, sufixIdCible){

	if (containArray(sufixIdCible, ajaxTrSufixIdArray) == false){
		ajaxTrSufixIdArray.push(sufixIdCible);
		
	    var req = initRequest(url);

	    req.onreadystatechange = function() {
	        if (req.readyState == 4) {

	            if (req.status == 200) {
	                remplirTD(sufixIdCible, req.responseText);
	            } else if (req.status == 204){
	                viderListe(listeAPopuler);
	            }
	        }
	    };
		req.open("GET", url, true);
	    req.send(null);
	}
}

function remplirTD(sufixIdCible, tableContenu){
	var td = document.getElementById("sectionEscamotableContenuTd"+sufixIdCible);
	td.innerHTML = tableContenu;
}
</SCRIPT>
