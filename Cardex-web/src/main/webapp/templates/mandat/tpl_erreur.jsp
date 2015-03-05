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
<logic:present name='<%= Globals.ERROR_KEY %>' >
  <TABLE  width="772" cellpadding="5" cellspacing="0" border="0" class="darkOrangeOutline">
    <TR>
      <TD class="errorHeader"><H5><bean:message key='Erreur'/></H5>
      </TD>
    <TR>

    <TR>
      <TD><html:img page="/images/993300pixel.gif" width="760" height="1" border="0" /></TD>
    <TR>
    <TD class="errorFont">
      <PRE>
      <html:errors/>
      </PRE>

    </TD>

  </TABLE>
<SCRIPT FOR = document EVENT = onreadystatechange>
//Pour afficher clairement le message à l'écran
if (document.readyState=="complete"){
	oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{DOWN}{DOWN}{DOWN}");
}
</SCRIPT>
</logic:present>

<!-- END ERROR SECTION -->
