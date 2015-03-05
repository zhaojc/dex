<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une adresse.
Description : Écran de saisie d'une nouvelle adresse.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/03/25 17:42:11 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/03/01 23:11:19 , Author: mdemers 
Création.

Revision: 1.3 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher 
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.3 $, $Date: 2002/03/25 17:42:11 $, $Author: abruno-boucher $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>


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


<title>Adresse</title>

</head>
<body
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

  <form action="/sujet/adresse/add"  >


<!-- POSITIONING TABLE -->
<TABLE align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <TR>
  	<TD align="center">

      <FORM name="frmCaracteristiques" action="#" method="post">

      <!-- CONTENT -->
      <TABLE width="614" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<TR>
    			<TD align="center">


      		  <TABLE width="600" cellpadding="5" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableCarved">
          		<tr>
          			<td colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
        		  </tr>
          		<TR>
                <TD align="center" colspan="4">

                <!-- SUBJECT TITLE -->
                <TABLE width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid;
                  border-color: #000000; border-width: 1px;">
              		<TR>
                    <TD align="center" class="tabSubject"><bean:message key='adresse_t'/></TD>
                  </TR>
                </TABLE>
                <!-- END TITLE -->

              </TD>
              </TR>

              <tr>
          			<td colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
        		  </tr>

          		<TR>
          			<td align="right" nowrap><bean:message key='v_ad_adresse_t'/></td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 240px" />
                   <%-- <myriap:text name='suivi' property="adresse1"
                     maxlength="10" style="HEIGHT: 20px; WIDTH: 240px" /> --%>
                 </td>
          			<td align="right" nowrap><bean:message key='v_ad_telephone_1_t'/></td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 120px"  />
                  <%-- <myriap:text name='suivi' property="telephone1"
                    maxlength="14" style="HEIGHT: 20px; WIDTH: 120px" /> --%>
                </td>
          		</TR>  

              <TR>
          			<td align="right" nowrap>&nbsp;</td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 240px" />
                   <%-- <myriap:text name='suivi' property="adresse2"
                     maxlength="40" style="HEIGHT: 20px; WIDTH: 240px" /> --%>
                 </td>
          			<td align="right" nowrap><bean:message key='v_ad_telephone_2_t'/></td>
          			<td><INPUT type="text" style="HEIGHT: 20px; WIDTH: 120px" />
                  <%-- <myriap:text name='suivi' property="telephone2"
                    maxlength="14"  style="HEIGHT: 20px; WIDTH: 120px" /> --%>
                </td>
          		</TR>

              <TR>
          			<td align="right" nowrap><bean:message key='i_pa_cle_t'/></td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 240px" />

                 </td>
          			<td align="right" nowrap><bean:message key='v_ad_telephone_3_t'/></td>
          			<td><INPUT type="text" style="HEIGHT: 20px; WIDTH: 120px" />
                </td>
          		</TR>

              <TR>
          			<td align="right" nowrap><bean:message key='l_pr_cle_t'/></td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 240px" />
                 </td>
          			<td align="right" nowrap><bean:message key='i_st_cle_t'/></td>
          			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 120px" />
                </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><bean:message key='l_vi_cle_t'/></td>
          			<td colspan="3"><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 210px" />
                  <INPUT type="button" value=" ... " />
                 </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><bean:message key='v_ad_code_postal_t'/></td>
          			<td colspan="3"><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 120px" />
                   <%-- <myriap:text name='suivi' property="codePostal"
                    maxlength="7"  style="HEIGHT: 20px; WIDTH: 120px" /> --%>
                 </td>
          		</TR>

              <TR>
          			<td align="right" nowrap><bean:message key='v_ad_commentaire_t'/></td>
          			<td colspan="3"><TEXTAREA name="commentaire" rows="3" cols="75"
          			    style="font-family: Verdana, Arial; font-size: 9pt;"></TEXTAREA>
                 </td>
          		</TR>

          		<TR>
          		  <TD align="center" colspan="4">

          		    <TABLE width="560" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
                 		<TR>
                			<TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></TD>
              		  </TR>

                 		<TR>
                 			<td align="right" nowrap><bean:message key='v_cree_par_t'/></td>
                 			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 120px" />
                          <%-- <myriap:text name='suivi' property="creePar"
                           maxlength="40" style="HEIGHT: 20px; WIDTH: 120px" /> --%>
                        </td>
                 			<td align="right" nowrap><bean:message key='v_tr_modifie_par_t'/></td>
                 			<td><INPUT type="text"  style="HEIGHT: 20px; WIDTH: 120px" />
                         <%-- <myriap:text name='suivi' property="modifiePar"
                           maxlength="40" style="HEIGHT: 20px; WIDTH: 120px" /> --%>
                       </td>
                 		</TR>


                 		<TR>
                 			<td align="right" nowrap><bean:message key='d_date_creation_t'/></td>
                 			<td><INPUT type="text"  size="10" />
                          <%-- <myriap:text name='suivi' property="dateCreation" maxlength="10"  size="10" /> --%>
                         <A HREF="javascript:openCalendar('document.forms(0).dateCreation',
                           document.forms(0).dateCreation.value);" onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" /></A>
                        </td>
                 			<td align="right" nowrap><bean:message key='d_ad_date_modification_t'/></td>
                 			<td><INPUT type="text"  size="10" />
                         <%-- <myriap:text name='suivi' property="dateModification" maxlength="10"  size="10" /> --%>
                         <A HREF="javascript:openCalendar('document.forms(0).dateModification',
                           document.forms(0).dateModification.value);" onmousedown="setXY(event.x, event.y);"><html:img page="/images/cal.gif" border="0" /></A>
                       </td>
                 		</TR>

                 		<TR>
                			<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></TD>
              		  </TR>

                  </TABLE>

          		  </TD>
          		</TR>

          		<TR>
          			<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></TD>
        		  </TR>

          	</TABLE>

    		  </TD>
    		</TR>

      	<TR>
        	<TD align="center">

    			  <!-- BOTTOM BUTTONS -->
    			  <TABLE width="600" cellpadding="3" cellspacing="0" border="0">
    			    <TR>
    			      <TD width="300">
   	          <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                    <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON"><A HREF="javascript:setForm();document.forms(0).submit();"
                        style="color: #000000; width: 60px; text-align: center"><bean:message key='cb_ok'/></A></DIV></TD>
                    </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                </TD>

                <TD width="300" align="right">
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
        </TR>
    	</TABLE>
      <!-- END CONTENT -->

    </TD>
  </TR>
</TABLE>
<!-- END POSITIONING TABLE -->

</FORM>

</body>
</html>
