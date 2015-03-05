<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
		soumettreActionMethod("rafraichir");
	}

</SCRIPT>

<TABLE width="480" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
	<TR>
		<TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br></TD>
    </TR> 
	<TR>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_ca_cle_t'/></b></TD>
      <TD ALIGN="left">
                <select name='categorie' size="1" style="HEIGHT: 20px; WIDTH: 190px"  >
                  <option value='0'></option>
                  <option value='<%=GlobalConstants.Categorie.INFRACTION_1%>'><%=GlobalConstants.ClientMystereInfractions.FORMATION%></option>
                  <option value='<%=GlobalConstants.Categorie.INFRACTION_2%>'><%=GlobalConstants.ClientMystereInfractions.AVIS_FORMEL%></option>
                  <option value='<%=GlobalConstants.Categorie.INFRACTION_3%>'><%=GlobalConstants.ClientMystereInfractions.SUSPENSION_15%></option>
                  <option value='<%=GlobalConstants.Categorie.INFRACTION_4%>'><%=GlobalConstants.ClientMystereInfractions.SUSPENSION_30%></option>
                  <option value='<%=GlobalConstants.Categorie.INFRACTION_5%>'><%=GlobalConstants.ClientMystereInfractions.RETRAIT_PERMIS%></option>
                </select>

	  </TD>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='district'/><bean:message key='2.points'/></b></TD>
      <TD ALIGN="left">
                <select name='district' size="1" style="HEIGHT: 20px; WIDTH: 50px"  >
                  <option value='0'></option>
                  <option value='<%=GlobalConstants.ClientMystereDistricts.DISTRICT_21%>'><%=GlobalConstants.ClientMystereDistricts.DISTRICT_21%></option>
                  <option value='<%=GlobalConstants.ClientMystereDistricts.DISTRICT_22%>'><%=GlobalConstants.ClientMystereDistricts.DISTRICT_22%></option>
                  <option value='<%=GlobalConstants.ClientMystereDistricts.DISTRICT_23%>'><%=GlobalConstants.ClientMystereDistricts.DISTRICT_23%></option>
                </select>
	  </TD>
	</TR>