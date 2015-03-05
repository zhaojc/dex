<%-- --------------------------------------------------------------------------
Use case    : Ajout d'un suivi.
Description : Écran de saisie d'un nouveau suivi.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/03/04 17:39:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/03/04 17:39:51 $, $Author: mdemers $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

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
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<title><bean:message key='Suivi'/></title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

  <form action="/dossier/suivi/add"  >


<!-- POSITIONING TABLE -->
<TABLE align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

      <FORM name="frmCaracteristiques" action="#" method="post">

      <!-- CONTENT -->
      <TABLE width="614" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    			<td align="center">


    		  <TABLE width="600" cellpadding="5" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableCarved">
        		<tr>
        			<td colspan="6"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
      		  </tr>
        		<TR>
              <TD align="center" colspan="6">

              <!-- SUBJECT TITLE -->
              <TABLE width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid;
                border-color: #000000; border-width: 1px;">
            		<TR>
                  <TD align="center" class="tabSubject"><bean:message key='Suivi'/></TD>
                </TR>
              </TABLE>
              <!-- END TITLE -->

            </TD>
            </TR>

            <tr>
        			<td colspan="6"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
      		  </tr>

        		<TR>
        			<td align="right" nowrap><bean:message key='i_tc_cle_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />

               </td>
        			<td align="right" nowrap><bean:message key='i_cc_cle_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />

              </td>
        			<td align="right" nowrap><bean:message key='i_st_cle_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 100px" />

              </td>
        		</TR>

 
            <!-- TEXT INPUT HERE -->
        		<TR>
        			<td align="center" colspan="6">
        			  <TEXTAREA name="suivi" rows="15" cols="92" style="font-family: Verdana, Arial; font-size: 9pt;"></TEXTAREA>
              </td>
            </TR>
            <!-- END INPUT TEXT -->


        		<TR>
        			<td align="right" nowrap><bean:message key='l_sv_po_assigne_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />
                <%-- <myriap:select name='narration' property="secteurDemandeur" size="1" style="HEIGHT: 20px; WIDTH: 110px">
                    <html:options collection='<%= GlobalConstants.ItemList.SECTEUR %>' labelProperty='label' property='value' />
                </myriap:select> --%>
              </td>
        			<td align="right" nowrap><bean:message key='v_sv_intervenant_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />

              </td>
        			<td align="right" nowrap><bean:message key='d_sv_date_prevue_t'/></td>
        			<td><INPUT type="text" size="10" />
                 <%-- <myriap:text name='suivi' property="datePrevue" maxlength="10"  size="10" /> --%>
                 <A HREF="javascript:openCalendar('document.forms(0).datePrevue',
                   document.forms(0).datePrevue.value);" ><html:img page="/images/cal.gif" border="0" /></A>
              </td>
        		</TR>

            <TR>
        			<td  colspan="5" align="right" nowrap><bean:message key='date_completee_t'/></td>
        			<td><INPUT type="text" size="10" />
                <%-- <myriap:text name='suivi' property="dateCompletee" maxlength="10"  size="10" /> --%>
                <A HREF="javascript:openCalendar('document.forms(0).dateCompletee',
                   document.forms(0).dateCompletee.value);" ><html:img page="/images/cal.gif" border="0" /></A>
              </td>
        		</TR>

        		<TR>
        			<td align="right">&nbsp;</td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />
                <%-- <myriap:text name='suivi' property="secteurDemandeur" maxlength="40" style="HEIGHT: 20px; WIDTH: 110px" /> --%>
              </td>
        			<td align="right" nowrap><bean:message key='v_sv_demandeur_t'/></td>
        			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 110px" />
                <%-- <myriap:text name='suivi' property="demandeur" maxlength="40" style="HEIGHT: 20px; WIDTH: 110px" /> --%>
              </td>
        			<td align="right" nowrap><bean:message key='d_sv_date_creation_t'/></td>
        			<td><INPUT type="text" size="10" />
                <%-- <myriap:text name='suivi' property="demandeLe" maxlength="10"  size="10" /> --%>
                <A HREF="javascript:openCalendar('document.forms(0).dateCompletee',
                   document.forms(0).dateCompletee.value);" ><html:img page="/images/cal.gif" border="0" /></A>
              </td>
        		</TR>
          </TABLE>

    		  </TD>
    		</TR>

    	<TR>
    	<TD align="center">
    			  <!-- BOTTOM BUTTONS -->
    			  <TABLE width="600" cellpadding="3" cellspacing="0" border="0">
    			    <TR>
    			      <TD width="150">
   	          <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                  <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:setForm();document.forms(0).submit();"
                        style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_ok'/></A></DIV></TD>
                  </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                </TD>

                <TD width="150" align="right">
        			    <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                  <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#"
                         style="color: #000000; width: 60px; text-align: center"><bean:message key='Complété'/></A></DIV></TD>
                  </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                </TD>

                <TD width="150">
        			    <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                  <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#"
                        style="color: #000000; width: 60px; text-align: center"><bean:message key='Approbation'/></A></DIV></TD>
                  </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                </TD>

                <TD width="150" align="right">
        			    <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                  <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="#" onclick="windowClose();"
                        style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_fermer'/></A></DIV></TD>
                  </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                </TD>

              </TR>
            </TABLE>
            <!-- END BOTTOM BUTTONS -->

  	</TD>
  </tr>
  	</TABLE>
    <!-- END CONTENT -->



    </td>
  </tr>
</TABLE>
<!-- END POSITIONING TABLE -->
  </form>

</body>
</html>
