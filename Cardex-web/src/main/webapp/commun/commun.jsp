<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
	
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
	
<%
  //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
  //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
  //-- de session.
  String var_lang = "fr";
  try{
    var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
  }
  catch (Throwable e) {}

%>
	
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/lq_scripts.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/windowOpenChilds.js"></SCRIPT>

<style type="text/css" >
body{
	cursor: wait;
	}
</style>

<!-- Comme la touche CTRL-P ne peut pas être empêchée, le style suivant imprime une page blanche au lieu de l'information affichée  -->
<style media="print">
	body {
		display : none;
	}
</style>

 
<SCRIPT type="text/javascript">
	var rightClick = false;
	var toucheCTRL = true;

	function getOnContextMenu(){
		return rightClick;
	}

	function desactiveCTRL() {
	  // current pressed key
	  //var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();
	  if (event.ctrlKey) {
		  //Si la toucche CTRL est permise, on n'autorise que les combinaisons CTRL-C, CTRL-V et CTRL-X.
	        if(toucheCTRL){
	        
	           if(window.event.keyCode != 67 && window.event.keyCode != 86 && window.event.keyCode != 88){
	           	  event.returnValue = false;
	           	  
	           }else{
	              event.returnValue = true;
	           }
	     	}else{
	     	   event.returnValue = false;
	     	}
      }else{
	    event.returnValue = true;
	  }
	}
	
	//var userCardex = "<%=request.getHeader("ct-remote-user")%>";
	var userCardex = "<bean:write name='<%= AuthenticationSubject.class.getName() %>' property='user.code' />";
	
	function customOnLoad(){
		assignerUsagerTitre();
		history.go(+1);
		cursorDefault();
		background();
	}

	function assignerUsagerTitre(){
		var titre = document.title;

		if (userCardex != "null"){
			document.title=userCardex+"-"+titre;
		}
	}

	function desactiverDrag(){
		return false;
	}

	document.oncontextmenu=getOnContextMenu;
	document.onkeydown=desactiveCTRL;
		
	document.ondragstart=desactiverDrag;
		
	// Après que la page soit loader
	addEvent(window, 'load', customOnLoad);

	// Développement
<%
	java.net.InetAddress ia = java.net.InetAddress.getLocalHost();
	String host = ia.getHostName();
	if(host.equals("Z500W19935") || host.equals("Z500W19696") || host.equals("Z500W20037")){ //Poste de développement
%>	
		rightClick = true;
		toucheCTRL = true;
		isActiverViderClipBoard = false;
<%	}%>

	var isActiverViderClipBoard;
	
	if (isActiverViderClipBoard == null){
		addEvent(window, 'focusin', activerViderClipBoard);
	}

	addEvent(window, 'focusout', desActiverViderClipBoard);
	
function background(){
	  //S'il s'agit de l'environnement de formation, on affiche un fond différent pour le distinguer.
	  //alert("<%= request.getContextPath() %>" + "   <%= GlobalConstants.APPLICATION_PRODUCTION %>");
	 if("<%= request.getContextPath() %>" != "<%= GlobalConstants.APPLICATION_PRODUCTION %>" ){
		  		document.body.style.backgroundImage = 'url("<%= request.getContextPath()%>/images/arriere-planFormation.jpg")';
				document.body.style.backgroundPosition = 'top left';
				document.body.style.backgroundRepeat = 'repeat-x';
		  }
			 
}

</SCRIPT>




<SCRIPT FOR = document EVENT = onreadystatechange>
	cursorDefault();
</SCRIPT>

