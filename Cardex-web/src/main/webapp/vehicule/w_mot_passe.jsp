<%-- --------------------------------------------------------------------------
Use case    : Réponse du système suite à une capture.
Description : Écran affichant la réponse du système suite à une soumission d'une
              capture de photo.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/04/30 15:30:20 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/18 20:46:28 , Author: abruno-boucher
Création HTML.

$Revision: 1.1 $, $Date: 2002/04/30 15:30:20 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<title><bean:message key='cb_proteger' /></title>
</head>


<SCRIPT language="JavaScript" type="text/javascript">

function doClose() {
	windowClose();
}

</SCRIPT>

<TITLE></TITLE>
</HEAD>
<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"  >
<html:form action='/vehicule/showAcces' >
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">


      <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    		  <td colspan="2" align="center" class="errorHeader" >
                     <bean:message key='cb_proteger' />
                  </td>
  		</tr>
    		<tr>
    			<td colspan="2">

    		  <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="#ECECEC" class="tableCarved">
                        <tr>
                          <td colspan="2" align="center" class="errorFont" >
                             <b><html:errors /></b>
                          </td>
                        </tr>
        		<tr>
        			<td width="195" align="right"><b><bean:message key='v_ve_immatriculation_t'/></b></td>
        			<td width="195" align="left">
        				<bean:write name='vehicule' property="immatriculation" />
                    </td>
      		  </tr>

      		  <tr>
        			<td width="195" align="right"><b><bean:message key='site_t2'/></b></td>
        			<td width="195" align="left">
        				<cardex:afficherValeurListeTag name='vehicule' property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
        			</td>
      		  </tr>

      		  <tr>
        			<td width="195" align="right"><b><bean:message key='password_t'/></b></td>
        			<td width="195" align="left">
                                  <html:password name='vehicule' property="confirmationMotPasse" value="" maxlength="20" style="HEIGHT: 22px; WIDTH: 140px" />&nbsp;
                                  <html:hidden name='vehicule' property="motPasse"  />
                                  <html:hidden name='vehicule' property="cle"  />
                                  <html:hidden name='vehicule' property="site"  />
                                </td>
      		  </tr>

          </table>

          </td>
    		</tr>

    		<tr>
    		  <td width="200" align="right">
                     <cardex:button soumettre="/vehicule/showAcces.do" labelKey='cb_ok2' style="width: 60px; text-align: center;" />
                  </td>

          <td width="200">
                <cardex:button labelKey='cb_fermer'  onclick='doClose();' style="width: 60px; text-align: center;" />
          </td>

  		</tr>
  	</table>


    </td>
  </tr>
</table>

</html:form>

</BODY>
</HTML>