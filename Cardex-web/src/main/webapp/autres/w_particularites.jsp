<%-- --------------------------------------------------------------------------
Use case    : Sélection des particularités d'un véhicule.
Description : Sélection des particularités d'un véhicule.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/03/04 16:56:58 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.2 $, $Date: 2002/03/04 16:56:58 $, $Author: mdemers $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>


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

<title><bean:message key='tabpage_particularite'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

      <FORM name="frmParticularites" action="#" method="post">

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

        			  <table width="250" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
              		<tr>
              			<td align="right"><b><bean:message key='l_dossier_no_t'/></b></td>
              			<td width="150"><INPUT type="text" name="numeroDossier" maxlength="40" style="WIDTH: 140px; HEIGHT: 20px;"/></td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='i_ma_cle_t'/></b></td>
              			<td width="150"><select name="marque" style="WIDTH: 140px; HEIGHT: 20px;" >
            			    <option value="0">Ford</option>
            			    <option value="1" selected>GM</option>
            			    <option value="2">Chrysler</option>
            			    <option value="3">Mercedez</option>
            			  </select></td>
            			  <td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='i_md_cle_t'/></b></td>
              			<td width="150"><select name="modele" style="WIDTH: 140px; HEIGHT: 20px;" >
            			    <option value="0">Cavalier</option>
            			    <option value="1" selected>Malibu</option>
            			    <option value="2">Roadster</option>
            			    <option value="3">Pico</option>
            			  </select></td>
            			  <td>&nbsp;</td>
              		</tr>

              	</table>

        			</td>
      		  </tr>

        		<tr>
        			<td class="errorHeader"><bean:message key='particularites_disponibles_t'/></td>
        			<td>&nbsp;</td>
        			<td align="right" class="errorHeader"><bean:message key='particularites_choisies_t'/></td>
      		  </tr>

      		  <tr>
        			<td><select name="particularitesDisponibles" size="8" multiple="yes" style="WIDTH: 160px; HEIGHT: 130px;" >
        			    <option value="0">Bleue</option>
        			    <option value="1">Verte</option>
        			    <option value="2">Rouge</option>
        			    <option value="3">Jaune</option>
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

        			<td align="right"><select name="particularites" size="8" multiple="yes" style="WIDTH: 160px; HEIGHT: 130px;" >
        			    <option value="a">Blanche</option>
        			    <option value="b">Grise</option>
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
                <A HREF="javascript:window.location.replace(history.go(-1));" style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_annuler'/></A></DIV></TD>
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