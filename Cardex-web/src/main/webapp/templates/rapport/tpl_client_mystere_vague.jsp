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
        <b><bean:message key='numero.vague'/><bean:message key='2.points'/></b></TD>
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
		<myriap:text name='<%=form%>' property="vague" style="HEIGHT: 20px; WIDTH: 120px" onchange='this.value = this.value.toUpperCase();' />
	  </TD>
	</TR>