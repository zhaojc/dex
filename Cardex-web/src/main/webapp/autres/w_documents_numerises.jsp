<%-- --------------------------------------------------------------------------
Use case    : Téléchargement de fichiers.
Description : Envoi au serveur un fichier sélectionné par l'utilisateur.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.9 $, $Date: 2002/03/04 17:39:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.9 $, $Date: 2002/03/04 17:39:51 $, $Author: mdemers $
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

<title>Pièce[s]-jointe[s]</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<!-- "/servlet/UploadServlet" -->
<form
	NAME = "frmFileUpload"
	ACTION = "<%= request.getContextPath() %>/dossier/photo/upload.do"
	METHOD = "POST"
	ENCTYPE = "multipart/form-data" >

  <table width="610" height="450" border="0" cellpadding="5" cellspacing="0" bgcolor="#EEEEEE" class="tableOutline">
		<tr>
			<td align="center">

			  <!-- START BUTTONS SECTION -->
			  <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="greenOutline">
      		<tr>
      			<td>

      			  <!-- DEVICE BUTTONS -->
      			  <table width="230" height="90" cellpadding="5" cellspacing="0" border="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>
            			<td><INPUT type="file" name="cardex02" /></td>
            		</tr>
        	    </table>
          	  <!-- END DEVICE -->

      			</td>
      			<td>
      			  <!-- UPLOAD SECTION -->
      			  <table width="150" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>
            			<td align="right" valign="bottom">

            			  <!-- VERY TWISTED BUTTON -->
                    <TABLE cellpadding="0" cellspacing="0" border="0">
                      <TR>
                        <!-- Call to the following link is activated when the connect button is triggered.
                             See VBScripts  -->
                        <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:document.frmFileUpload.submit();"
                          name="btnUpload" style="color: #000000;"> <bean:message key='cb_ok'/></A></DIV></TD>
                      </TR>
                    </TABLE>
                    <!-- END BUTTON -->

            			</td>


            		</tr>
            		<tr>

            			<td align="right" valign="top">
            			<!-- VERY TWISTED BUTTON -->
                    <TABLE cellpadding="0" cellspacing="0" border="0">
                      <TR>
                        <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#" onclick="windowClose();"
                          style="color: #000000;"> <bean:message key='cb_annuler'/></A></DIV></TD>
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

</form>

</BODY>
</HTML>
