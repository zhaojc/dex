<%-- --------------------------------------------------------------------------
Use case    : Affichage de la liste des accèes d'un véhicule.
Description : Écran affichant la liste des accès enregistrés pour un véhicule donné.
Author(s)   : $Author: François Guérin
Revision    : $Revision: 1.6 $, $Date: 2002/07/31 21:41:22 $

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doClose() {
	window.location='<cardex:writeObjectURL object="vehicule" page="/vehicule/w_consultation_vehicule.jsp" />';
}

function doRefresh() {
	soumettre('<%= request.getContextPath() + "/vehicule/acces/refresh.do"%>');
}
</SCRIPT>


<title>Accès</title>
</head>

<body  leftmargin="5" rightmargin="0" topmargin="0" marginheight="0" marginwidth="0" >
<html:form action='/vehicule/update' >
<br><!-- Number of records to display -->
      <table width="600" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    		  <td colspan="3" align="center" class="errorHeader" >
                     <bean:message key='historique_actions' />
                  </td>
  		</tr>
    		<tr>
    		<td width="100">&nbsp;
		</TD>
		<TD>
    		  <table width="400" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="#ECECEC" class="tableCarved">
        		  <tr>
        			<td width="195" align="right"><b><bean:message key='v_ve_immatriculation_t'/></b></td>
        			<td width="195" align="left">
        				<bean:write name='vehicule' property="immatriculation" />
                    </td>
	      		  </tr>

      			  <tr>
        			<td width="195" align="right"><b><bean:message key='createur_id_t'/></b></td>
        			<td width="195" align="left">
        				<cardex:afficherValeurListeTag name='vehicule' property='createur' classe='<%= GlobalConstants.CleListe.INTERVENANT%>' />
        			</td>
	      		  </tr>
      			  <tr>
        			<td width="195" align="right"><b><bean:message key='d_date_creation_t'/></b></td>
        			<td width="195" align="left">
        				<bean:write name='vehicule' property="dateCreation" />
        			</td>
	      		  </tr>
                   </table>
		</TD>
    		<TD width="100">&nbsp;
		</TD>
		</TR>
		<TR>
    		  <td colspan="3">&nbsp;
                  </td>
		</TR>
</table>

<TABLE align="left" width="600" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="460"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='<%= GlobalConstants.RechercheList.ACCES %>' property='size'/></b></TD>
    <TD width="140" align="right">&nbsp;
    </TD>
  </TR>

</TABLE><!-- End Number of records to display -->
<br clear="left"><!-- Search Kind & nature list -->


   <jsp:include page="/templates/autres/tpl_liste_acces_vehicules.jsp" flush="true" />

</html:form>

</BODY>
</HTML>