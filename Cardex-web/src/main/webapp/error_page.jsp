<%-- --------------------------------------------------------------------------
Use case    : Accès à la base de donnée.
Description : Écran qui affiche une erreur relative à un problème relié à la
              connection avec la base de donnée (ou avec un DAO).
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/03/25 20:43:17 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.10 $, $Date: 2002/03/25 20:43:17 $, $Author: mlibersan $
Nouvelle mise en page.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page isErrorPage="true" %>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css">

<TITLE>Erreur système/System error</TITLE>

</HEAD>
<BODY   link="#095B97"  vlink="#095B97" alink="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">


<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">


      <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="darkOrangeOutline">
    		<tr>
    			<td colspan="2">

    		  <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="#ECECEC" class="tableCarved">
        		<TR>
              <TD width="50" align="center"><html:img page="/images/stop.gif" border="0" /></TD>
              <TD class="errorHeader"><P align="justify">
               Une erreur est survenue lors de la soumission de votre requête. Veuillez contacter le support technique en indiquant l'erreur affichée ci-dessous.
              </P></TD>
              <TD>&nbsp;</TD>
            <TR>

            <TR>
              <TD width="50" >&nbsp;</TD>
              <TD class="errorHeader"><P align="justify">
               An error occured while processing your request. Please contact your technical support with the following error.
             </P></TD>
             <TD>&nbsp;</TD>
            <TR>

            <TR>
              <TD colspan="3">
                <html:errors/><%= request.getSession().getAttribute("message") %>
              </TD>
            <TR>

          </table>

          </td>
    		</tr>

    		<tr>
    		  <td width="400" align="center">
            <!-- VERY TWISTED BUTTON -->
            <TABLE cellpadding="0" cellspacing="0" border="0">
              <TR>
                <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON">
                <A HREF="#" onclick="windowClose();" style="color: #000000; width: 60px; text-align: center">Fermer/Close</A></DIV></TD>
              </TR>
            </TABLE>
          <!-- END BUTTON -->
        </td>

  		</tr>
  	</table>


    </td>
  </tr>
</table>
<!-- END 550 HEIGHT TABLE -->
	<!-- END INTERFACE -->

</BODY>
</HTML>


