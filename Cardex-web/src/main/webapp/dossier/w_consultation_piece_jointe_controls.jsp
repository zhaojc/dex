<%-- --------------------------------------------------------------------------
Use case    : Bouton de contrôle pour visualiser les photos de la galerie.
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/30 12:18:13 $

History     : Voir ci-dessous.

$Revision: 1.8 $, $Date: 2002/04/30 12:18:13 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>



<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/styles/lq_styles.css'>

<SCRIPT language="JavaScript" type="text/javascript">
  function doPrint(){
    parent.frames('piece_content').focus();
    parent.frames('piece_content').print();
  }


  function doClose() {
  	parent.window.location='<cardex:writeObjectURL object="dossier" page="/dossier/show.do" />';
  }

</SCRIPT>

<TITLE></TITLE>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="0" marginheight="5" marginwidth="5">

<FORM action="#" method="post">
<TABLE width="700" cellpadding="0" cellspacing="0" border="0" bgcolor="#eeeeee" class="tableOutline">
  <TR>
    <TD class="pictureBackground">
    <table width="700" cellpadding="5" cellspacing="0" border="0">
        <TR>
           <TD colspan="2"><html:img page="/images/blank.gif" width="700" height="1" border="0" /></TD>
        </TR>

	<TR>
	  <TD width="250" align="right">
	    <cardex:button securityConstraint='cardex.fenetres.galeries.imprimer' style="width: 60px; text-align: center;" labelKey='cb_imprimer' onclick='doPrint();' />
	  </TD>

	  <TD width="65" align="right">
	    <cardex:button labelKey='cb_fermer' style="width: 60px; text-align: center;" onclick='doClose();' />
	  </TD>

	</TR>
           <TR>
            <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
          </TR>

        </table>

    </TD>
  </TR>
</TABLE>
</FORM>

</BODY>
</HTML>
