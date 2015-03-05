<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.ChangementMotPasseForm" %>
<%
request.getSession().setAttribute("changementMotPasse", new ChangementMotPasseForm());
%>

<html>
<head>
<title>Changement de mot de passe</title>
<META HTTP-EQUIV="Content-Type" Content="text-html; charset=Windows-1252">

<SCRIPT FOR = document EVENT = onreadystatechange>
//Pour placer et activer le curseur dans le premier champ.
//Le focus seulement ne semble pas suffire.  C''est pourquoi
//on force le déplacement du curseur avec la touche TAB.
if (document.readyState=="complete"){
	document.forms(0).code.focus();
	document.forms(0).code.click();
	oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{TAB}+{TAB}");
	//document.forms(0).code.value = '<%= request.getRemoteUser() %>';
}
</SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function verifier() {
//Validations des informations saisies.
    if(document.forms(0).code.value == ""){
       alert("Le code est obligatoire.");
    }else{
	    if(document.forms(0).ancienMotPasse.value == ""){
	       alert("L'ancien mot de passe est obligatoire.");
	    }else{
			if(document.forms(0).nouveauMotPasse.value != document.forms(0).confirmation.value){
			   alert("Le mot de passe et la confirmation doivent être identiques.");
			}else{
				if(document.forms(0).nouveauMotPasse.value.length < 8){
				   alert("Le mot de passe doit contenir au moins huit caractères.");
				}else{
				//Le mot de passe doit contenir au moins 1 caractère numérique
					nombre = "1234567890"
					for (var i = 0; i < document.forms(0).nouveauMotPasse.value.length; i++) {
					   var lettre = document.forms(0).nouveauMotPasse.value.charAt(i);
					   var pos = nombre.indexOf(lettre);
					   //alert(lettre + " " + pos);
					   if(pos != -1){
						  document.forms(0).submit();
						  return 0;
					   }
					}
		    		alert("Le mot de passe doit contenir au moins un caractère numérique.");
		    	}
		    }
	    }
	}
}

</SCRIPT>
</head>

<BODY link="#095B97"  vlink="#095B97"
  style='background-image: url("<%=request.getContextPath()%>/images/logo_lq_bkg.jpg");
  background-repeat: no-repeat; background-position: center center;' >

<table border="0" cellpadding="5" cellspacing="0">
  <tr>
	<TD align="center" valign="bottom" colspan="2">
       <font size="+3" face="Helvetica" color="#375392">
       <b><i>Changement du mot de passe</i></b>
       </font>
    </TD>
  </tr>
  <tr>
  <td>&nbsp;</td>
  <td align="center"><FONT size="+1" color="#FF6666">
<html:form action="changement" method="post" >
  <bean:write name='changementMotPasse' property="message" /></FONT>
<table width="500" align="center" border="1" rules=NONE cellpadding="5">
  <tr>
    <td align='right' ><font color="#FF2222">*</font><b>Code d'utilisateur :</b></td>
    <td><html:text name='changementMotPasse' property="code" tabindex="1" maxlength="20" style="HEIGHT: 20px; WIDTH: 120px" onchange='this.value = this.value.toUpperCase();' />&nbsp;</td>
  </tr>
  <tr>
    <td align='right'><font color="#FF2222">*</font><b>Ancien mot de passe :</b></td>
    <td><html:password name='changementMotPasse' property="ancienMotPasse" tabindex="2" maxlength="20" style="HEIGHT: 22px; WIDTH: 120px" />&nbsp;</td>
  </tr>
  <tr>
    <td align='right'><font color="#FF2222">*</font><b>Nouveau mot de passe :</b></td>		
    <td><html:password  name='changementMotPasse' property="nouveauMotPasse" tabindex="3" maxlength="20" style="HEIGHT: 22px; WIDTH: 120px" />&nbsp;</td>
  </tr>
  <tr>
    <td align='right'><font color="#FF2222">*</font><b>Confirmation du mot de passe :</b></td>
    <td><html:password name='changementMotPasse' property="confirmation" tabindex="4" maxlength="20" style="HEIGHT: 22px; WIDTH: 120px" />&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="center" >
    <font color="#FF2222">*</font>
    Tous les champs sont requis!
    </td>
  </tr>
  <tr>
    <td colspan=2 align="center"><input type="button" value='Soumettre' onclick='verifier();' >
	</td>
  </tr>
         
</table>
</td>
</tr>
</table>
</html:form>
</body>
</html>
