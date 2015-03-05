
<script language="JavaScript">
	
	function cookiesEnabled() {
	    document.cookie = "test=cookiesEnabled";
	    var pos = document.cookie.indexOf( "test=" );
	
	    if( pos == -1 ){
	        return false;
	    }
	    return true;
	}
	
	function javaEnabled()
	{
	    if( navigator.javaEnabled() == false){
	        return false;
	    }
	    else
	        return true;
	}
	
	function scriptFileSystemObject(){
	
		try{
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			return true;
	
		}catch(err){
			return false;
		}
	}
	
	function scriptShell(){
	
		try{
			var oShell= new ActiveXObject("WScript.Shell");
			return true;
	
		}catch(err){
			return false;
		}
	}                
	
	// Test si la configuration de l'utilisateur peut supporter l'application
	var erreur = false;
	var isCookiesActif = true;
	var isJavaActif = true;
	var isScriptFileSystemObjectActif = true;
	
	if ( cookiesEnabled() == false){
		erreur = true;
		isCookiesActif = false;
	}
	
	if ( javaEnabled() == false ){
		erreur = true;
		isJavaActif = false;
	}
	
	if ( scriptFileSystemObject() == false  || scriptShell() == false){
		erreur = true;
		isScriptFileSystemObjectActif = false;
	}

</script>


<div style="position: absolute;">
<input type="hidden" name="appName" /><BR>
<input type="hidden" name="appVersion" /><BR>
<input type="hidden" name="availWidth" /><BR>
<input type="hidden" name="availHeight" /><BR>
<input type="hidden" name="remoteAddr" value='<%=request.getRemoteAddr()%>'/><BR>
<input type="hidden" name="cookiesActif"/><BR>
<input type="hidden" name="javaActif"/><BR>
<input type="hidden" name="scriptFileSystemObjectActif"/><BR>
<input type="hidden" name="clipBoardActif"/><BR>
</div>
<script>
	document.forms(0).appName.value = navigator.appName;
	document.forms(0).appVersion.value = navigator.appVersion;
	document.forms(0).availWidth.value = window.screen.availWidth;
	document.forms(0).availHeight.value = window.screen.availHeight;
	
	document.forms(0).cookiesActif.value = isCookiesActif;
	document.forms(0).javaActif.value = isJavaActif;
	document.forms(0).scriptFileSystemObjectActif.value = isScriptFileSystemObjectActif;
	
	function initRequest(url) {
	    if (window.XMLHttpRequest) {
	        return new XMLHttpRequest();
	    } else if (window.ActiveXObject) {
	        xml = new ActiveXObject("Microsoft.XMLHTTP");
	        return xml;
	    }
	}	
	
	function inscrireLog(){
		var url = "<%=request.getContextPath()%>/InscrireLog";
		url += "?appName="+document.forms(0).appName.value;
		url += "&appVersion="+document.forms(0).appVersion.value;
		url += "&availWidth="+document.forms(0).availWidth.value;
		url += "&availHeight="+document.forms(0).availHeight.value;
		url += "&remoteAddr="+document.forms(0).remoteAddr.value;
		url += "&cookiesActif="+document.forms(0).cookiesActif.value;
		url += "&javaActif="+document.forms(0).javaActif.value;
		url += "&scriptFileSystemObjectActif="+document.forms(0).scriptFileSystemObjectActif.value;
		url += "&clipBoardActif="+document.forms(0).clipBoardActif.value;
	    var req = initRequest(url);
		req.open("GET", url, true);
	    req.send(null);
	}	
	
	/*
	* vider le clipboard pour ne pas que l'utilisateur puisse copier coller.
	*/
	var isActiverViderClipBoard = true;
	var isClearBoardDataActif = clipboardData.clearData();
	
	document.forms(0).clipBoardActif.value = isClearBoardDataActif;
	inscrireLog();
	
	function viderClipBoard(){
	
		if(clipboardData){
		
			if (isActiverViderClipBoard && isClearBoardDataActif != false){
				isClearBoardDataActif = clipboardData.clearData();
			}
		}
		setTimeout("viderClipBoard();", 100);
	}
	
	function desActiverViderClipBoard(){
		isActiverViderClipBoard = false;
	}
	
	function ActiverViderClipBoard(){
		isActiverViderClipBoard = true;
	}
	
	document.onfocusin=ActiverViderClipBoard;
	document.onfocusout=desActiverViderClipBoard;
	
	viderClipBoard();
	
</script>

