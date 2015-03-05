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

<TITLE>Erreur de sécurité/Security error</TITLE>

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
              <TD width="50" align="center" valign="middle"><html:img page="/images/warning.png" border="0" /></TD>
              <TD class="errorHeader">
               Vous n'êtes pas autorisé à accéder à cet élément.
              </TD>
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
	                <A HREF="#" onclick="windowClose();" style="color: #000000; width: 60px; text-align: center">Fermer</A></DIV></TD>
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


