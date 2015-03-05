
function windowOpenLocation(url){
	var windowHandler = window.open(url,"","toolbar=0,location=0,directories=0,menuBar=0,scrollbars=yes,"
	    + "resizable=yes,dependent=yes,status=yes");
    windowHandler.moveTo(0, 0);
	windowHandler.resizeTo(screen.availWidth, screen.availHeight);	    	    
    windowHandler.focus();
	addWindowHandler( windowHandler );
}

function addWindowHandler(windowHandler){
	window.parent.opener.addWindowHandler( windowHandler );
}

function activerViderClipBoard(){
	window.parent.opener.activerViderClipBoard();
}

function desActiverViderClipBoard(){
	window.parent.opener.desActiverViderClipBoard();
}

function fermerConnexion(sequence){
	try{
		window.parent.opener.fermerConnexion(sequence);
	}catch(e){}
}