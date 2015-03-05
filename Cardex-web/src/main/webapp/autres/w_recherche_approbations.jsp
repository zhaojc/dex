<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des narrations.
Description : Écran 800 X 600 de recherche.
              Le visionnement des narration s'effectue égalemement dans
              la même fenêtre.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/03/25 17:38:06 $

History     : Voir ci-dessous.

Revision: 1.2 $, Date: 2002/03/05 17:36:28 , Author: abruno-boucher 
Création.

Revision: 1.5 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher 
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.5 $, $Date: 2002/03/25 17:38:06 $, $Author: abruno-boucher $
Commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">
  //document.oncontextmenu = function(){return false}

</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<title><bean:message key='recherche_narrations'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<form
	NAME = "frmFileUpload"
	ACTION = "<%= request.getContextPath() %>/approbations/search/show.do"
	METHOD = "POST" >

<template:insert template='/templates/tpl_onglet_entete.jsp'>
  <template:put name='keyTitre' content="<bean:message key='tabpage_commentaires'/>" direct='true'/>
</template:insert>
<template:insert template='/templates/tpl_calendrier_div.jsp' />
<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="772" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground">

  <table width="770" border="0" cellpadding="5" cellspacing="0">
		<tr>
      <td colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
    </tr>

		<tr>
			<td width="250" align="right" >
			  <!-- APPROBATOR -->
			  <table width="200" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
      			<td><INPUT type="radio" name="approbation" value="approuveParMoi" /><b><bean:message key='approuvés_par_moi_t'/></b></td>
      		</tr>

      		<tr>
      			<td><INPUT type="radio" name="approbation" value="approuve" /><b><bean:message key='approuve_t'/></b></td>
      		</tr>

      		<tr>
      			<td><INPUT type="radio" name="approbation" value="nonApprouve" /><b><bean:message key='non_approuve_t'/></b></td>
      		</tr>

      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END APPROBATOR -->
			</td>

			<td width="520" align="left">

			  <!-- START TAB CONTENT -->
			  <table width="450" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
      			<td align="right"><b><bean:message key='v_secteur_assigne_t'/></b></td>
      			<td><SELECT name="secteur" size="1" style="HEIGHT: 20px; WIDTH: 120px">
      			    <OPTION value=""></OPTION>
      			    <OPTION value="" selected>Option 1</OPTION>
      			    <OPTION value="">Option 2</OPTION>
      			  </SELECT>
      			</td>
      			<td align="right"><b><bean:message key='v_sv_intervenant_t'/></b></td>
      			<td><SELECT name="intervenant" size="1" style="HEIGHT: 20px; WIDTH: 120px">
      			    <OPTION value=""></OPTION>
      			    <OPTION value="" selected>Option 1</OPTION>
      			    <OPTION value="">Option 2</OPTION>
      			  </SELECT></td>
      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td align="right" nowrap><b><bean:message key='d_date_debut_ajout_t'/></b></td>
      			<td nowrap><INPUT type="text" size="10" maxlength="10" name="dateCreationDebut" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateCreationDebut',
                document.forms(0).dateCreationDebut.value);" onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" />
      			</td>
      			<td align="right" nowrap><b><bean:message key='approuve_entre_le_t'/></b></td>
      			<td nowrap><INPUT type="text" size="10" maxlength="10" name="dateApprobationDebut" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateApprobationDebut',
                document.forms(0).dateApprobationDebut.value);"  onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" />
      			</td>

      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td nowrap><INPUT type="text" size="10" maxlength="10" name="dateCreationFin" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateCreationFin',
                document.forms(0).dateCreationFin.value);"  onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" />
      			</td>

      			<td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td nowrap><INPUT type="text" size="10" maxlength="10" name="dateApprobationFin" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateApprobationFin',
                document.forms(0).dateApprobation.value);"  onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" />
      			</td>
      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      </td>
		</tr>

		<TR>
      <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="2">

        <TABLE width="760" cellpadding="2" cellspacing="0" border="0">
          <TR>
            <TD width="360" ALIGN="right">
              <!-- VERY TWISTED BUTTON -->
              <TABLE cellpadding="0" cellspacing="0" border="0" class="pictureBackground">
                <TR>
                  <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:document.forms(0).submit()"
                    style="color: #000000;"><bean:message key='cb_rechercher' />*</A></DIV></TD>
                </TR>
              </TABLE>
              <!-- END BUTTON -->
            </TD>
            <TD width="200" ALIGN="left">
              <!-- VERY TWISTED BUTTON -->
              <TABLE cellpadding="0" cellspacing="0" border="0" class="pictureBackground">
                <TR>
                  <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:document.forms(0).action='<%= request.getContextPath()%>/narrations/new.do';
                    document.forms(0).submit()" style="color: #000000;"><bean:message key='cb_approbation'/></A></DIV></TD>
                </TR>
              </TABLE>
              <!-- END BUTTON -->
            </TD>

            <TD width="200" align="right">
              <!-- VERY TWISTED BUTTON -->
              <TABLE cellpadding="0" cellspacing="0" border="0" class="pictureBackground">
                <TR>
                  <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#" onclick="window.close();" style="color: #000000;"><bean:message key='cb_fermer' />*</A></DIV></TD>
                </TR>
              </TABLE>
              <!-- END BUTTON -->
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>


    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="2"><html:img page="/images/0061CFpixel.gif" width="766" height="1" border="0" /></TD>
    </TR>


    <TR>
      <TD ALIGN="center" colspan="2">

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->

	<template:insert template='/templates/tpl_erreur.jsp' />
	<%-- <template:insert template='/templates/narrations/tpl_recherche_narrations_resultats.jsp' /> --%>
  <tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
  
</form>

</BODY>
</HTML>
