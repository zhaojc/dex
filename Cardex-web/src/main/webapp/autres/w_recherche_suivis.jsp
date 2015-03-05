<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des suivis.
Description : Écran 800 X 600 des suivis.
              Le résultat et la saisies de commentaires
              s'effectuent dans la même fenêtre.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/03/26 22:09:58 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.7 $, $Date: 2002/03/26 22:09:58 $, $Author: abruno-boucher $
Création HTML.
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


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


<title><bean:message key='Recherche_de_suivis'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<form
	NAME = "frmFileUpload"
	ACTION = "<%= request.getContextPath() %>/suivis/search/show.do"
	METHOD = "POST" >

<template:insert template='/templates/tpl_onglet_entete.jsp'>
  <template:put name='keyTitre' content="<bean:message key='tabpage_suivis'/>" direct='true'/>
</template:insert>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="772" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground">

	<!-- POSITIONING TABLE -->
  <table width="770" cellpadding="5" cellspacing="0" border="0">
		<tr>
      <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
    </tr>

		<tr>
			<td width="21"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
			<td width="149">
			  <!-- EMITER -->
			  <table width="139" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
      			<td><INPUT type="radio" name="emission" value="emisParMoi" /><b><bean:message key='emis_par_moi_t'/></b></td>
      		</tr>

      		<tr>
      			<td><INPUT type="radio" name="emission" value="emisPourMoi" /><b><bean:message key='emis_pour_moi_t'/></b></td>
      		</tr>

      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END EMITER -->
			</td>

			<td ><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>

			<td width="590" rowspan="2">

			  <!-- START ORIGIN AND DATE -->
			  <table width="530" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
            <td colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
          </tr>

           <tr>
      		  <td width="530" align="center" colspan="7">

      		    <!-- ORIGIN -->
      		    <table width="496" cellpadding="2" cellspacing="0" border="0" >
            		<tr>
            			<td align="right"><b><bean:message key='po_assigne_t'/></b></td>
            			<td><SELECT name="secteurAssigne" size="1" style="HEIGHT: 20px; WIDTH: 120px">
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
            			<td align="right"><b><bean:message key='po_origine_t'/></b></td>
            			<td><SELECT name="secteurOrigine" size="1" style="HEIGHT: 20px; WIDTH: 120px">
            			    <OPTION value=""></OPTION>
            			    <OPTION value="" selected>Option 1</OPTION>
            			    <OPTION value="">Option 2</OPTION>
            			  </SELECT>
            			</td>
            			<td align="right"><b><bean:message key='v_sv_demandeur_t'/></b></td>
            			<td><SELECT name="demandeur" size="1" style="HEIGHT: 20px; WIDTH: 120px">
            			    <OPTION value=""></OPTION>
            			    <OPTION value="" selected>Option 1</OPTION>
            			    <OPTION value="">Option 2</OPTION>
            			  </SELECT></td>
            			<td>&nbsp;</td>
            		</tr>

            	</table>
      	      <!-- END ORIGIN -->
        	  </td>
        	</tr>

        	<tr>
      			<td  width="530" height="15" colspan="7"><html:img page="/images/0061CFpixel.gif" width="536" height="1" border="0" /></td>
      		</tr>

      		<tr>
      		  <!-- DATE -->
      			<td width="70" align="right"><b><bean:message key='date_creation_debut_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="dateEmissionDebut" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateEmissionDebut',
                document.forms(0).dateEmissionDebut.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>
      			<td width="70" align="right" ><b><bean:message key='prevu_du_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="datePrevuDebut" />
      			  <A HREF="javascript:openCalendar('document.forms(0).datePrevuDebut',
                document.forms(0).datePrevuDebut.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>
      			<td width="70" align="right"><b><bean:message key='complete_entre_le_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="dateCompleteDebut" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateCompleteDebut',
                document.forms(0).dateCompleteDebut.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>

      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td width="70" align="right"><b><bean:message key='au_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="dateEmissionFin" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateEmissionFin',
                document.forms(0).dateEmissionFin.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>

      			<td width="70" align="right"><b><bean:message key='au_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="datePrevuFin" />
      			  <A HREF="javascript:openCalendar('document.forms(0).datePrevuFin',
                document.forms(0).datePrevuFin.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>

      			<td width="70" align="right"><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td width="100" nowrap><INPUT type="text" size="10" maxlength="10" name="dateCompleteFin" />
      			  <A HREF="javascript:openCalendar('document.forms(0).dateCompleteFin',
                document.forms(0).dateCompleteFin.value);" ><html:img page="/images/cal.gif" border="0" />
      			</td>
      			<td>&nbsp;</td>
      		</tr>
      		<!-- END DATE -->
      		<tr>
      			<td colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END ORIGIN AND DATE -->
      </td>
		</tr>

		<tr>
			<td width="21"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
			<td width="149">
			  <!-- APPROBATOR -->
			  <table width="139" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
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
			<td>&nbsp;</td>
		</tr>

		<TR>
      <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="4">

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
                  <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#" onclick="	window.close();" style="color: #000000;"><bean:message key='cb_fermer' />*</A></DIV></TD>
                </TR>
              </TABLE>
              <!-- END BUTTON -->
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="4"><html:img page="/images/0061CFpixel.gif" width="766" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" colspan="4">


      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

	</table>
	<!-- END POSITIONING TABLE -->


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
