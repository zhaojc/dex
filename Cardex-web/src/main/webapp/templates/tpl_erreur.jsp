<%-- --------------------------------------------------------------------------
Use case    : Affichage d'une erreur.
Description : Écran affichant une erreur système ayant survenue.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/03/28 16:28:04 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/03/28 16:28:04 $, $Author: abruno-boucher $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ page import="org.apache.struts.Globals" %>

<!-- ERROR SECTION.  THIS SECTION MAY BE EMPTY -->
<logic:messagesPresent message="true">  
	<TABLE width="778" cellpadding="2" cellspacing="0" border="0" class="darkOrangeOutline"
		style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#45b358', startColorstr='#FFFFFF', gradientType='0');" >
		<TR>
			<TD class="errorHeader"><span style="width: 100%; font-size: 14px; font-family: Arial; color: #333333; filter:shadow(color=#999999,strength=4);">
              <bean:message key='pour.votre.information'/>
              </span>
      		</TD>
    	</TR>

	    <TR>
	      <TD><html:img page="/images/993300pixel.gif" width="760" height="1" border="0" /></TD>
	    <TR>		
		<html:messages id="message" message="true">
	    <TR>
			<TD class="errorHeader">
				<li><H5><bean:write name="message"/></H5></li>
			</TD>
		</TR>
		</html:messages> 
	</TABLE>
 
	<SCRIPT FOR = document EVENT = onreadystatechange>
	//Pour afficher clairement le message à l'écran
	if (document.readyState=="complete"){
	    document.forms(0).setActive();
		//oShell= new ActiveXObject("WScript.Shell");
		//oShell.SendKeys("{TAB}{DOWN}{DOWN}{DOWN}");
		//oShell.SendKeys("{PGDN}");
		//alert(document.readyState);
	}
	</SCRIPT>
</logic:messagesPresent>

<!-- ERROR SECTION.  THIS SECTION MAY BE EMPTY -->
<logic:present name='<%= Globals.ERROR_KEY %>' >
  <TABLE width="778" cellpadding="2" cellspacing="0" border="0" class="darkOrangeOutline"
  style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#6984A9', startColorstr='#FFFFFF', gradientType='0');" >
    <TR>
      <TD class="errorHeader"><span style="width: 100%; font-size: 14px; font-family: Arial; color: #333333; filter:shadow(color=#999999,strength=4);">
              <bean:message key='Erreur'/>
              </span>
      </TD>
    <TR>

    <TR>
      <TD><html:img page="/images/993300pixel.gif" width="760" height="1" border="0" /></TD>
    <TR>
    <TD class="errorFont">
      <span style="width: 100%; font-size: 12px; font-family: Arial; color: #993300;">
      <H5><html:errors/></H5>
      </span>
    </TD>

  </TABLE>
<SCRIPT FOR = document EVENT = onreadystatechange>
//Pour afficher clairement le message à l'écran
if (document.readyState=="complete"){
    document.forms(0).setActive();
	//oShell= new ActiveXObject("WScript.Shell");
	//oShell.SendKeys("{TAB}{DOWN}{DOWN}{DOWN}");
	//oShell.SendKeys("{PGDN}");
	//alert(document.readyState);
}
</SCRIPT>
</logic:present>

<!-- END ERROR SECTION -->
