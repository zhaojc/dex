var fso = new ActiveXObject("Scripting.FileSystemObject");
var repProgramFiles = "C:\\Program Files\\";
var repLotoQuebec = "Loto-Québec\\";
var repCardex = "Cardex\\";
var nomDossierChamp = repProgramFiles+repLotoQuebec+repCardex;
//var nomFichierEXE = nomDossierChamp+"IEUploader.exe";
var assignerUploaderFait = false;

if (fso.FolderExists(nomDossierChamp) == false){
	creerNomRepertoire(repProgramFiles);
	creerNomRepertoire(repProgramFiles+repLotoQuebec);	
	creerNomRepertoire(nomDossierChamp);
}

function creerNomRepertoire(repertoire){

	if (fso.FolderExists(repertoire) == false){
		fso.CreateFolder(repertoire);
	}
}

/*function isUploaderExists(){
	return fso.FileExists(nomFichierEXE);
}*/

/*if (fso.FileExists(nomFichierEXE) == false){
//  fso.DeleteFile(nomFichierEXE, true);
	fichier = fso.CreateTextFile(nomFichierEXE, true);
	 
	for (var i=0;i<fileArray.length;i++){
		fichier.write( String.fromCharCode( fileArray[i] ) );
	}
	 
	fichier.close();
}*/
 
function previsualiserEnable(){
	var Previsualiser = document.getElementsByName("Previsualiser")[0];
	Previsualiser.disabled = false;
}

function assignerExtension(extension){
	
	var filePath = document.getElementsByName("filePath")[0];
	filePath.value = extension;

	var fileDescription = document.getElementsByName("description")[0];
	fileDescription.value = right(extension,50);
}

//Appelé lors de l'ajout d'une photo à partir de l'onglet Photo
function assignerExtensionAjout(extension){
	
	var filePath = document.getElementsByName("ajoutPhoto.filePath")[0];
	filePath.value = extension;

	var fileDescription = document.getElementsByName("ajoutPhoto.description")[0];
	fileDescription.value = right(extension,50);
}

//Appelé lors de l'ajout d'une pièce jointe dans l'onglet Pièces jointes
function assignerExtensionAjoutPiece(extension){
	
	var filePath = document.getElementsByName("ajoutPieceJointe.filePath")[0];
	filePath.value = extension;

	var fileDescription = document.getElementsByName("ajoutPieceJointe.description")[0];
	fileDescription.value = right(extension,50);
}

function setShowDiv(){
	var hideDiv = document.getElementById("cardex01Div");
	hideDiv.style.visibility = "visible";
}

function setFocus(){
	var sourceFile = document.getElementsByName("cardex01")[0];
	sourceFile.focus();
}

function setHideDiv(){
	var hideDiv = document.getElementById("cardex01Div");
	hideDiv.style.visibility = "hidden";
}

function assignerNumerisateur(){
	assignerUploader();
}

function assignerUploader(){
	
	if (assignerUploaderFait == false){
		assignerUploaderFait = true;
		setShowDiv();
		setFocus();
	
		//var ws=new ActiveXObject("WScript.shell");
		//ws.run ('"'+nomFichierEXE+'"'+" --c:\\temp\\capture\\cardex01.jpg");
		ecrireShell("C:/Program Files/Loto-Québec/Cardex/cardex01.jpg");
		
		setTimeout("setHideDiv()",500);
	}
}

function ecrireShell(texte){
	var wsh = new ActiveXObject("WScript.Shell");

	for(var i=0;i<texte.length;i++){
		wsh.SendKeys("{" + texte.charAt(i) + "}");
	}
}
