<%-- --------------------------------------------------------------------------
Use case    : Liaison d'une auto-exclusion.
Description : Menu déroulant défénissant l'affinité de l'auto-exclusion a lier.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/03/04 17:39:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.2, Date: 2002/02/14 17:12:06, Author: pcaron
Ajout des commentaires de la page JSP.

Revision: 1.4 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/03/04 17:39:51 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">
	function submit() {
		soumettre('<%= request.getContextPath() + "/dossier/dossier/link.do"%>');
	}
</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<title><bean:message key='tabpage_role'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td align="center">

			  <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td align="center" ><b><bean:message key='i_ro_cle_t'/></b></td>
      		</tr>

      		<tr>
      			<td align="center">

      			  <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="eeeeee" class="tableOutline">
            		<tr>
            			<td align="center"><br>

                    <html:form action='/dossier/dossier/link.do' >
                    	<template:insert template='/templates/tpl_lien_role.jsp' />
                    </html:form>

                  </td>
            		</tr>
        	    </table>

        	  </td>
      		</tr>
      	</table>

      </td>
		</tr>
	</table>
	<!-- END INTERFACE -->


</BODY>
</HTML>
