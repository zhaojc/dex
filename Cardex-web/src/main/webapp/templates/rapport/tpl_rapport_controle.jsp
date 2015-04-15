<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

<tiles:insert attribute="critere" />

<TR>
	<TD WIDTH="100%" ALIGN="center" COLSPAN="4" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="100%" height="1" border="0" /></TD>
</TR>
<tr>
	<td colspan="3" nowrap="nowrap">
		<cardex:button accessKey="i" labelKey='Imprimer' onclick="soumettreActionMethod('imprimer');" />
		<html:img page="/images/blank.gif" width="75" height="1"/>
		<cardex:button accessKey="e" labelKey='Imprimer.excel' onclick="soumettreActionMethod('imprimerExcel');" />
	</td>
	<td align="right">
		<cardex:button labelKey='cb_fermer'  onclick='windowClose();' />
	</td>
</tr>
</table>

<html:hidden name='<%=form%>' property="lancerRapport" value="false"/>

<SCRIPT language="JavaScript" type="text/javascript">

	function ouvrirRapport(){
		var url = "<%=request.getContextPath()%>/CritereRapportAffichagePDF?rapportForm=<%=form%>";
		windowOpenLocation(url);
	}
	
	function ouvrirRapportExcel(){
		var url = "<%=request.getContextPath()%>/RapportAffichageExcel?rapportForm=<%=form%>";
		windowOpenLocation(url);
	}	
	
	<logic:notPresent name='<%= Globals.ERROR_KEY %>' >
	<logic:equal name="<%=form%>" property="lancerRapport" value="true">
	<logic:equal name="<%=form%>" property="typeSortieServlet" value='<%=GlobalConstants.TypeSortieServlet.PDF%>'>
		ouvrirRapport();
	</logic:equal>
	<logic:equal name="<%=form%>" property="typeSortieServlet" value='<%=GlobalConstants.TypeSortieServlet.EXCEL%>'>
		ouvrirRapportExcel();
	</logic:equal>	
	</logic:equal>	
	</logic:notPresent>

</SCRIPT>