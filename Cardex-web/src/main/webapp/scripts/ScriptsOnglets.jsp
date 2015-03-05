<script>
var ongletSelectionneX = 0;
var ongletSelectionneY = 0;

var ongletCentreBas = new Image();
ongletCentreBas.src = "<%=request.getContextPath()%>/images/OngletCentreBas.gif";
var ongletGaucheBas = new Image();
ongletGaucheBas.src = "<%=request.getContextPath()%>/images/OngletGaucheBas.gif";
var ongletDroitBas = new Image();
ongletDroitBas.src = "<%=request.getContextPath()%>/images/OngletDroitBas.gif";
var ongletDroitBasFin = new Image();
ongletDroitBasFin.src = "<%=request.getContextPath()%>/images/OngletDroitBasFin.gif";
var ongletCentreHaut = new Image();
ongletCentreHaut.src = "<%=request.getContextPath()%>/images/OngletCentreHaut.gif";
var ongletGaucheHaut = new Image();
ongletGaucheHaut.src = "<%=request.getContextPath()%>/images/OngletGaucheHaut.gif";
var ongletGaucheHautMilieux = new Image();
ongletGaucheHautMilieux.src = "<%=request.getContextPath()%>/images/OngletGaucheHautMilieux.gif";
var ongletDroitHautFin = new Image();
ongletDroitHautFin.src = "<%=request.getContextPath()%>/images/OngletDroitHautFin.gif";
var ongletDroitHaut = new Image();
ongletDroitHaut.src = "<%=request.getContextPath()%>/images/OngletDroitHaut.gif";

function ongletClick(iX, iY){
	var x = parseInt(iX);
	var y = parseInt(iY);

	if (x == ongletSelectionneX
	&& y == ongletSelectionneY)
		return;

	DeselectionnerOnglet(ongletSelectionneX, ongletSelectionneY);
	selectionnerOnglet(x, y);
	ongletSelectionneX = x;
	ongletSelectionneY = y;
}

function DeselectionnerOnglet(x, y){

	var ongletCentre = document.getElementById("onglet"+x+"Centre"+y);
	var ongletDroit = document.getElementById("onglet"+x+"Droit"+y);

	ongletCentre.style.backgroundImage = "url("+ongletCentreBas.src+")";

	// Gauche
	if (x == 0){
		var ongletGauche = document.getElementById("onglet"+x+"Gauche"+y);
		ongletGauche.style.backgroundImage = "url("+ongletGaucheBas.src+")";
	}else{
		var ongletGauche = document.getElementById("onglet"+(x-1)+"Droit"+y);
		ongletGauche.style.backgroundImage = "url("+ongletDroitBas.src+")";
	}

	//Droit
	if (isOngletDroit(x, y))
		ongletDroit.style.backgroundImage = "url("+ongletDroitBasFin.src+")";
	else
		ongletDroit.style.backgroundImage = "url("+ongletDroitBas.src+")";
}

function isOngletDroit(x, y){
	var ongletDroit = document.getElementById("onglet"+(x+1)+"Droit"+y);

	return ongletDroit == null;
}

function selectionnerOnglet(x, y){
	var ongletCentre = document.getElementById("onglet"+x+"Centre"+y);
	var ongletDroit = document.getElementById("onglet"+x+"Droit"+y);

	ongletCentre.style.backgroundImage = "url("+ongletCentreHaut.src+")";

	// Gauche
	if (x == 0){
		var ongletGauche = document.getElementById("onglet"+x+"Gauche"+y);
		ongletGauche.style.backgroundImage = "url("+ongletGaucheHaut.src+")";
	}else{
		var ongletGauche = document.getElementById("onglet"+(x-1)+"Droit"+y);
		ongletGauche.style.backgroundImage = "url("+ongletGaucheHautMilieux.src+")";
	}

	//Droit
	if (isOngletDroit(x, y))
		ongletDroit.style.backgroundImage = "url("+ongletDroitHautFin.src+")";
	else
		ongletDroit.style.backgroundImage = "url("+ongletDroitHaut.src+")";
}
</script>