<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="keyTitre" id="keyTitre" classname="String"/>
<tiles:useAttribute name="tableWith" id="tableWith" classname="String" ignore="true"/>

<%
	if (tableWith == null || tableWith.length() == 0)
		tableWith = "770";
 %>
<!-- Tab identifier -->
<TABLE width="<%=tableWith%>" cellPadding="0" cellSpacing="0" border="0">
  <TR>
    <TD>

      <TABLE cellpadding="0" cellspacing="0" border="0">
        <TR>
          <TD><html:img border="0" height="20" page="/images/l_up_corner.gif" width="5" /></TD>
          <TD CLASS="tabTitle" nowrap>&nbsp; <bean:message key='<%=keyTitre%>'/> &nbsp;</TD>
          <TD><html:img align="top" border="0" height="20" page="/images/r_up_corner.gif" width="5" /></TD>
        </TR>
      </TABLE>

    </TD>
    <TD CLASS="tabSubject">&nbsp;</TD>
  </TR>
</TABLE>
<!-- End tab identifier -->