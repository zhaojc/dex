var windowHandlerArray = new Array();

function windowOpenLocation(url){
    //-- Place your code here
	var windowHandler = window.open(url,"","toolbar=0,location=0,directories=0,menuBar=0,scrollbars=yes,"
	    + "resizable=yes,dependent=yes,status=yes");
    windowHandler.moveTo(0, 0);
	windowHandler.resizeTo(screen.availWidth, screen.availHeight);	    
    windowHandler.focus();
    windowHandlerArray.push( windowHandler );
}


function windowHandlerClose(){

	for (var i=0;i<windowHandlerArray.length;i=i+1){

		if (windowHandlerArray[i].closed == false){
			windowHandlerArray[i].close();
		}
	}
	windowHandlerArray = new Array();
}

function addWindowHandler(windowHandler){
	windowHandlerArray.push(windowHandler);
}

