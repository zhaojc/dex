<%!
    String doEscape ( String s )
    {
        if ( s == null )
        {
            return null;
        }

        StringBuffer sb = new StringBuffer ( );
        int n = s.length ( );
        for ( int i = 0; i < n; i++ )
        {
            char c = s.charAt ( i );
            switch ( c )
            {
                case '<': sb.append ( "&lt;" ); break;
                case '>': sb.append ( "&gt;" ); break;
                case '&': sb.append ( "&amp;" ); break;
                case '"': sb.append ( "&quot;" ); break;
                // Be carefull with this one (non-breaking white space)
                case ' ': sb.append ( "&nbsp;" ); break;         
                default:  sb.append ( c ); break;
            }
        }
        
        return sb.toString(); 
    }
%>

<%
    /***************************************************************************
     * ct_logon.jsp
     *
     * Copyright (c) 2001 RSA Security, Inc.
     * All rights reserved.
     **************************************************************************/
      
    /***************************************************************************
     * if CTAuthMode isn't defined, we'll default to BASIC.
     *
     * CTAuthMode should be one of the following:
     * - BASIC
     * - NT
     * - SECURID
     * - CUSTOM
     **************************************************************************/
    
    String CTAuthMode = request.getParameter ( "CTAuthMode" );
    String CTOrigUrl = request.getParameter ( "CT_ORIG_URL" );
    if (CTOrigUrl == null) 
    {
        CTOrigUrl = request.getParameter ("ct_orig_uri");
    }

    if ( CTAuthMode == null || !( CTAuthMode.length () > 0 ) )
    {
        CTAuthMode = "BASIC";
    }
    else
    {
        CTAuthMode = doEscape ( CTAuthMode );
        CTAuthMode = CTAuthMode.toUpperCase ( );
    }

    CTOrigUrl = doEscape ( CTOrigUrl );
    
    // Get the post back URL, which is same as this URL
    String PostBackURL = request.getRequestURI ();

    /***************************************************************************
     * Process logon if the request method is "POST". The post information is
     * copied to the "ct-iis-form-query" header and passed back to the server.
     * The presence of this header indicates that this request is a 
     * authentication request.
     **************************************************************************/
    if ( request.getMethod ().equalsIgnoreCase ( "POST" ) )
    {
        String CTUser = request.getParameter ( "user" );
        String CTPassword = request.getParameter ( "password" );
        CTAuthMode = request.getParameter ( "auth_mode" );
        CTOrigUrl = request.getParameter ( "orig_url" );
        
        if(CTUser != null && CTUser.equals("") == false)
        {
            CTUser = java.net.URLEncoder.encode(CTUser);
        }
        
        if(CTPassword != null && CTPassword.equals("") == false)
        {
            CTPassword = java.net.URLEncoder.encode(CTPassword);
        }
        
        StringBuffer QueryString = new StringBuffer ();
        QueryString.append ( "user=" );
        QueryString.append ( CTUser );
        QueryString.append ( "&password=" );
        QueryString.append ( CTPassword );
        QueryString.append ( "&auth_mode=" );
        QueryString.append ( CTAuthMode );
        QueryString.append ( "&orig_url=" );
        QueryString.append ( CTOrigUrl );
        

        // Add the new header, that indicates "authentication request"
        response.addHeader ( "ct-iis-form-query", QueryString.toString () );

        // Issue redirect
        response.sendRedirect ( "/cleartrust/ct_logon.jsp" );
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

  <HEAD>
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
    <TITLE>ClearTrust <%=CTAuthMode%> Login Form</TITLE>
        <style type="text/css">
        <!--
        BODY { margin: 5px; background-color: #FFFFFF; font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 11px; color: #000000}
        TABLE,TH,P, BLOCKQUOTE, UL, LI, INPUT, SELECT, TEXTAREA {font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 11px; color: #000000}
        .body_sm { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 10px; color: #4C4949}
        .rule { background-color: #6699CC}
        .rule2 { background-color: #4C4949}
        .pg_hdr { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;     font-size: 24px; color: #000000}
        .pg_body { background-color: #FFFFEE}
        .hdline { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 12px; color: #3A6688; font-weight: bold}
        .hdline_error { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 12px; color: #FF0000; font-weight: bold}
        .prmt { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 11px; color: #4C4949; font-weight: bold}
        A:link, A:active, A:visited { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif; font-size: 11px; color: #6699CC; text-decoration: none;} 
        A:hover { font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;     font-size: 11px; color: #333333}
        -->
        </style>
<script language="JavaScript">
	var versionEXE = true;
	var cacherMenu = false;
	
	function fullwin(){
		//Check ancienne version
		if (cacherMenu){
			activerPage();
		} else {
			var callVbWindowLink = document.getElementById("callVbWindowLink");
			callVbWindowLink.click();
		}
	}                

	function afficherNouvelleFenetreSansMenu(){

		if(self.opener == undefined){
			var fen = window.open("<%= request.getContextPath()%>/cleartrust/ct_logon.jsp", "_blank", "toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes");
			fen.moveTo(0,0);
			fen.resizeTo(screen.availWidth, screen.availHeight);
			self.moveTo(0,0);
			self.resizeTo(0,0); 
			opener=self;
			window.open('','_self','');			
	     	window.close();
		}else{
			activerPage();
		}
	}

	function activerPage(){
		var user = document.getElementsByName("user")[0];
		var pass = document.getElementsByName("password")[0];
		var chargement = document.getElementById("chargementId");
		user.disabled = false;
		pass.disabled = false;
		user.focus();
		chargement.style.visibility = "hidden";	
	}

    function doSubmit() {
    alert("<%=PostBackURL%>%>");
        document.authForm.method="post";
        document.authForm.action="<%=PostBackURL%>%>";
        document.authForm.submit();
    }
	
	function openVersionHTML(){
		var fen = window.open("https://cardex.loto-quebec.com/cardex/enter.do", "_blank", "toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes");
		fen.moveTo(0,0);
		fen.resizeTo(screen.availWidth, screen.availHeight);
	}
	
	function afficherMessageVersionEXE(){
		var chargement = document.getElementById("chargementId");
		var html = "";
		html += "<table border=1><tr><td><b>";
		html += "Vous avez sélectionné une version périmée de Cardex.<br>";
		html += "SVP aviser votre support Cardex pour corriger la situation.<br><br>";
		//html += "<a href='#' onclick='openVersionHTML();'>Appuyer ici</a> (vous serez dirigé vers la version courante)";
		html += "</b></td></tr></table>";
		chargement.innerHTML = html;
	}
	
	window.setTimeout("afficherMessageVersionEXE()", 3000);	
                
    // IUploader
	var repProgramFiles = "C:\\Program Files\\";
	var repLotoQuebec = "Loto-Québec\\";
	var repCardex = "Cardex\\";
	var nomDossierChamp = repProgramFiles+repLotoQuebec+repCardex;
	//var nomFichierEXE = nomDossierChamp+"IEUploader.exe";
    
    var fso = new ActiveXObject("Scripting.FileSystemObject");

	if (fso.FolderExists(nomDossierChamp) == false){
		creerNomRepertoire(repProgramFiles);
		creerNomRepertoire(repProgramFiles+repLotoQuebec);	
		creerNomRepertoire(nomDossierChamp);
	}

/*    
	function isUploaderExists(){

		if (scriptFileSystemObject() == false)
			return false;
		    

		if (fso.FolderExists(nomDossierChamp) == false){
			creerNomRepertoire(repProgramFiles);
			creerNomRepertoire(repProgramFiles+repLotoQuebec);	
			creerNomRepertoire(nomDossierChamp);
		}
	
		return fso.FileExists(nomFichierEXE);
	}    */
         /*
    var repTemp = "C:\\temp\\";
    var repCapture = "capture\\";         
         
    if (fso.FolderExists(repTemp+repCapture) == false){
		creerNomRepertoire(repTemp);
		creerNomRepertoire(repTemp+repCapture);	
	}*/
                
	function creerNomRepertoire(repertoire){
	
		if (fso.FolderExists(repertoire) == false){
			fso.CreateFolder(repertoire);
		}
	}
	                
</script>  
<SCRIPT FOR = document EVENT = onreadystatechange>
//Pour placer et activer le curseur dans le premier champ.
//Le focus seulement ne semble pas suffire.  C''est pourquoi
//on force le déplacement du curseur avec la touche TAB.
/*if (document.readyState=="complete"
&& self.opener != undefined){
	document.forms(0).user.focus();
	document.forms(0).user.click();
	oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{TAB}+{TAB}");
}*/
</SCRIPT>

  </HEAD>


<BODY link="#095B97"  vlink="#095B97" onload="fullwin();">
<div id="chargementId" style="visibility: visible;position: absolute;z-index: 1;height:800;width:800;left: 140px; top: 0px;background-color: #FFFFFF;">
	<h1>Chargement Cardex...</h1>
</div>

<h3 style="color: red;">




<noscript>
	La configuration de votre poste ne supporte pas 'JavaScript'<br>
	SVP contacter le support technique.
</noscript>
</h3>
<IMG USEMAP="#main" SRC="<%=request.getContextPath()%>/images/logo_lq_logon.jpg" ALIGN=TOP BORDER="0" ALT="Cardex" width="125" height="175">

<%
    /************************************************************************** 
     * When defining logon forms specify "CTAuthMode=<auth_type>"
     * and "CTLoginErrorMsg=<This is my error message>" as part of the query
     * string.
     *
     * Example:
     * cleartrust.agent.login_error_user_location_basic=
     * /cleartrust/ct_logon.jsp?CTAuthMode=BASIC&CTLoginErrorMsg=Invalid%20User
     **************************************************************************/
    String CTLoginErrorMsg = request.getParameter ( "CTLoginErrorMsg" );
  
    /* Escape special characters to prevent cross site scripting */
    if ( CTLoginErrorMsg != null )
    {
       CTLoginErrorMsg = doEscape ( CTLoginErrorMsg  );
    }
%>

<FORM NAME="authForm" ACTION="<%=PostBackURL%>" METHOD=POST ACCEPT-CHARSET="UTF-8">
<INPUT TYPE="hidden" NAME="auth_mode" VALUE="<%=CTAuthMode%>">
<INPUT TYPE="hidden" NAME="orig_url" VALUE="<%=CTOrigUrl%>">

<script type="text/javascript" language="Javascript">
// Test si la configuration de l'utilisateur peut supporter l'application

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


if ( isCookiesActif == false){
	document.write("La configuration de votre poste ne supporte pas les 'cookies'.<br>");
}

if ( isJavaActif == false ){
	document.write("La configuration de votre poste ne supporte pas 'JAVA'.<br>");
}

if ( isScriptFileSystemObjectActif == false){
	document.write("La configuration de votre poste ne supporte pas la fonction :<BR>'Contrôles d''initialisation et de script ActiveX non marqué comme sécurisés'.<br>");
}

if ( erreur ){
	document.write("Votre poste n'a pas été configuré correctement pour Cardex.<br>");
	document.write("SVP contacter le support technique pour compléter l'installation.<br>");
}

</script>

<TABLE align="center" border="1" rules=NONE cellpadding="3" >
<tr>
  <TH colspan=1 align="center" height=55><B><I><U><FONT SIZE="+2" FACE="Helvetica" COLOR="#375392">Connexion au système Cardex</FONT></U></I></B>
  </TH>
</tr>
  <tr> 
    <td valign="top"> 
      <TABLE width = "380" align="center" border="1" rules=NONE cellpadding="5">
        <%if ( CTLoginErrorMsg != null && CTLoginErrorMsg.length () > 0) {%>
             <tr> 
              <td colspan="2" class="hdline_error"><img src="<%=request.getContextPath()%>/images/icn_important.gif" width="20" height="20" alt="">
                <%=CTLoginErrorMsg%></td>
            </tr>
        <% } %>
        <tr> 
          <td colspan="2"> 
            <p> 
              <%if ( CTAuthMode.equals ( "SECURID" ) ) {%>
                  This resource requires an RSA SecurID log in. Please identify yourself by entering your user ID and your PASSCODE.            
            <%}%>
            </p>
          </td>
        </tr>
        <tr> 
          <td nowrap align="right"><FONT SIZE="+0.5" FACE="Helvetica" COLOR="#375392"><b>Code d'utilisateur :</b></FONT></td>
          <td> 
            <input type="text" name="user" size="20" tabindex= "1" onchange='this.value = this.value.toUpperCase();' disabled="disabled" >
          </td>
        </tr>
        <%if ( CTAuthMode.equals ( "SECURID" ) ) {%>
            <tr> 
              <td class="prmt" nowrap>PASSCODE:</td>
              <td width="100%"> 
                <input type="password" name="password" size="20">
              </td>
            </tr>
        <%     }else{%>
            <tr> 
              <td nowrap align="right"><FONT SIZE="+0.5" FACE="Helvetica" COLOR="#375392"><b>Mot de passe :</b></FONT></td>
              <td> 
                <input type="password" name="password" tabindex= "2" size="20" disabled="disabled">
              </td>
            </tr>    
        <%}%>

        <tr> 
          <td valign="top" colspan="2" align="center">
            <INPUT TYPE="submit" >
          </td>
	</tr>        
        <tr>
          <td valign="top"  colspan="2" align="center">
		      <a href="https://cardex.loto-quebec.com:19443/cardex/cleartrust/ct_change.jsp"><FONT SIZE="+0.5" FACE="Helvetica" COLOR="#375392"><u>Changement du mot de passe</u></FONT></a>
	      </td>
        </tr>
      </table>
    </td>
  </tr>
</TABLE>
<br>
<table width="400" cellpadding="5" cellspacing="0" border="1" class="tableOutline" align = "center"
	   style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');">
   <TR>
      <TD>
	      <i><b>En accédant au Cardex, vous vous engagez à assurer l'intégrité et à respecter le caractère hautement confidentiel 
	      de la documentation, des informations et des données obtenues dans le cadre de sa consultation. En vertu du préjudice que 
	      constitue l'utilisation et/ou la divulgation non autorisées des documents, des informations et des données contenus au Cardex 
	      pour Loto-Québec, la Société pourrait prendre tout recours jugé approprié envers l'utilisateur fautif.
	      </b></i>
	  </TD>
   </TR>      
</table>
<%-- Check ancienne version --%>
<SCRIPT language="JavaScript" type="text/javascript">
function callVbWindow(str1, str2, str3, str4){
	afficherNouvelleFenetreSansMenu();
}
function callVbClose(){
}
</SCRIPT>
<div style="visibility: hidden; position: absolute;left: 50;top: 50">
	<a id="callVbWindowLink" href="javascript:callVbWindow('<%=request.getContextPath()%>/cleartrust/vide.html', ' ','IMPRESSION','');">callVbWindowLink</a>
	<a id="callVbCloseLink" href="javascript:callVbClose();" onclick="">callVbCloseLink</a>	
</div>
<%-- fin Check ancienne version --%>
</form>
</body>
</html>
