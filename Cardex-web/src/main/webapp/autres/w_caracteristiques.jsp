<%-- --------------------------------------------------------------------------
Use case    : Sélection des caractéristiques d'un individu.
Description : Sélection des caractéristiques d'un individu.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/03/04 16:56:58 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.3 $, $Date: 2002/03/04 16:56:58 $, $Author: mdemers $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>

<title>Caractéristiques</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

      <FORM name="frmCaracteristiques" action="#" method="post">

      <!-- CONTENT -->
      <table width="414" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    			<td colspan="2">

    		  <table width="400" cellpadding="5" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableCarved">
        		<tr>
        			<td align="center" colspan="3"><html:img border="0" height="1" page="/images/blank.gif"  width="1" /></td>
      		  </tr>

        		<tr>
        			<td align="center" colspan="3">

        			  <table width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
              		<tr>
              			<td align="right"><b><bean:message key='co_no_fiche_t'/></b></td>
              			<td width="150"><INPUT type="text" name="numeroFiche" maxlength="40" style="WIDTH: 140px; HEIGHT: 20px;"/></td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='v_su_nom_t'/></b></td>
              			<td width="150"><INPUT type="text" name="nom" maxlength="40" style="WIDTH: 140px; HEIGHT: 20px;"/></td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='v_su_prenom_t'/></b></td>
              			<td width="150"><INPUT type="text" name="prenom" maxlength="40" style="WIDTH: 140px; HEIGHT: 20px;"/></td>
              			<td>&nbsp;</td>
              		</tr>

              	</table>

        			</td>
      		  </tr>

        		<tr>
        			<td class="errorHeader"><bean:message key='st_carac_dispon_t'/></td>
        			<td>&nbsp;</td>
        			<td align="right" class="errorHeader"><bean:message key='st_carac_choisies_t'/></td>
      		  </tr>

      		  <tr>
        			<td><select name="caracteristiquesDisponibles" size="8" multiple="yes" style="WIDTH: 160px; HEIGHT: 130px;" >
        			    <option value="0">Gros</option>
        			    <option value="1">Grand</option>
        			    <option value="2">Gras</option>
        			    <option value="3">Gigolo</option>
        			  </select>
        			</td>

        			<!-- Les boutons suivants doivent être "disabled" par défaut -->
        			<td align="center"><INPUT type="button" name="select_all" value="&gt;&gt;"
        			  class="functionButton" onClick="selectAll();"  disabled /><br><br>
        			  <INPUT type="button" name="select_one" value="&gt;"
        			  class="functionButton" onClick="selectOne();"  disabled /><br><br>
        			  <INPUT type="button" name="deselect_one" value="&lt;"
        			  class="functionButton" onClick="deselectOne();"  disabled /><br><br>
        			  <INPUT type="button" name="deselect_all" value="&lt;&lt;"
        			  class="functionButton" onClick="deselectAll();"  disabled /></td>

        			<td align="right"><select name="caracteristiques" size="8" multiple="yes" style="WIDTH: 160px; HEIGHT: 130px;" >
        			    <option value="a">Propre</option>
        			    <option value="b">Deux membres supérieurs</option>
        			  </select>
        			</td>
      		  </tr>

          </table>

          </td>
    		</tr>

    		<tr>
    		  <td width="200">
        	  <!-- START BUTTONS SECTION -->
        	  <!-- VERY TWISTED BUTTON -->
            <TABLE cellpadding="0" cellspacing="0" border="0">
              <TR>
                <!-- Call to the following link is activated when the connect button is triggered.
                     See VBScripts  -->
                <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:performSelectAll(); document.forms(0).submit();"
                  name="btnSubmit" style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_ok'/></A></DIV></TD>
              </TR>
            </TABLE>
            <!-- END BUTTON -->
          </td>

          <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
          <td width="200" align="right">
            <!-- VERY TWISTED BUTTON -->
            <TABLE cellpadding="0" cellspacing="0" border="0">
              <TR>
                <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON">
                <A HREF="javascript:window.location='<cardex:writeObjectURL object="sujet" page="/sujet/w_consultation_sujet.jsp" />'" style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_annuler'/></A></DIV></TD>
              </TR>
            </TABLE>
          <!-- END BUTTON -->
        </td>

  		</tr>
  	</table>
    <!-- END CONTENT -->

    </FORM>

    </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>