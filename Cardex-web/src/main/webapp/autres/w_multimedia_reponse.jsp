<%-- --------------------------------------------------------------------------
Use case    : Réponse du système suite à une capture.
Description : Écran affichant la réponse du système suite à une soumission d'une
              capture de photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.11 $, $Date: 2002/03/18 21:47:38 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.11 $, $Date: 2002/03/18 21:47:38 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">
  //document.oncontextmenu = function(){return false}

</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<title><bean:message key='tabpage_type_multimedia'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<!-- START POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td align="center">

			  <!-- START INTERFACE -->
			  <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="greenOutline">
      		<tr>
      			<td align="center" class="errorHeader">[[La sauvegarde du fichier a été effectuée avec succès!]]</td>
      		</tr>

      		<tr>
      			<td>
      			  <!-- UPLOAD SECTION -->
      			  <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="eeeeee" class="greenOutline">
            		<tr>
            			<td width="195" align="center">

            			  <!-- VERY TWISTED BUTTON -->
                    <TABLE cellpadding="0" cellspacing="0" border="0">
                      <TR>
                        <!-- Call to the following link is activated when the connect button is triggered.
                             See VBScripts  -->
                        <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:history.go(-2);"
                          name="btnUpload" style="width: 60px; text-align: center; color: #000000;"><bean:message key='cb_ok'/></A></DIV></TD>
                      </TR>
                    </TABLE>
                    <!-- END BUTTON -->

            			</td>
            			<td width="195" align="center">

            			<!-- VERY TWISTED BUTTON -->
                    <TABLE cellpadding="0" cellspacing="0" border="0">
                      <TR>
                        <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON">
                          <A HREF="#" onclick="windowClose();" style="width: 60px; text-align: center; color: #000000;"><bean:message key='cb_fermer'/></A></DIV></TD>
                      </TR>
                    </TABLE>
                    <!-- END BUTTON -->

                    </td>
              		</tr>
          	    </table>
            	  <!-- END UPLOAD SECTION -->

      </td>
		</tr>
	</table>
	<!-- END INTERFACE -->

                  </td>
                </tr>
          </table>
	<!-- END POSITIONING TABLE -->
</BODY>
</HTML>