<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute id="formName" name="formName" classname="String"/>
<tiles:useAttribute id="property" name="property" classname="String"/>
<tiles:useAttribute id="strGaucheCol" name="strGaucheCol" classname="String"/>
<tiles:useAttribute id="strDroiteCol" name="strDroiteCol" classname="String"/>
<tiles:useAttribute id="listeGaucheLargeur" name="listeGaucheLargeur" classname="String"/>
<tiles:useAttribute id="listeDroiteLargeur" name="listeDroiteLargeur" classname="String"/>
<tiles:useAttribute id="listeHauteur" name="listeHauteur" classname="String"/>
<tiles:useAttribute id="idSelectMultiboxes" name="idSelectMultiboxes" classname="String" ignore="true"/>

<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/dual_list.js"></script>

<script language="JavaScript">
	function selectMultiboxes() {
    	selectMultiboxes<%=idSelectMultiboxes%>();    	
    }
	function selectMultiboxes<%=idSelectMultiboxes%>() {	
    	selectAllList(document.getElementsByName('<%=property.toString()+".gaucheColSTR"%>')[0]);
    	selectAllList(document.getElementsByName('<%=property.toString()+".droiteColSTR"%>')[0]);
    }
</script>

<table cellpadding="0" cellspacing="0" width="100%">
<tr> 
	<td width="<%= listeGaucheLargeur %>"><b><bean:message key="<%= strGaucheCol.toString() %>" /><bean:message key="2.points" /></b></td>
	<td align="center">&nbsp;</td>
	<td width="<%= listeDroiteLargeur %>"><b><bean:message key="<%= strDroiteCol.toString() %>" /><bean:message key="2.points" /></b></td>
</tr>

<tr> 
   	<%-- Liste de gauche --%>
	<td rowspan="2">
		<bean:define id="lesElementsG" name="<%= formName.toString() %>" property='<%= property.toString() + ".gaucheCols" %>' type="java.util.List" />
		<html:select name="<%= formName.toString() %>" property='<%= property.toString() + ".gaucheColSTR" %>' value="" multiple="true" size="<%= listeHauteur.toString() %>" style='<%= "width:"+listeGaucheLargeur %>' styleId="lstGauche">
            <html:options collection="lesElementsG" property="value" labelProperty="label" />
		</html:select>
	</td>
	
    <%-- Bouton Ajouter --%>
	<% String s = "javascript:moveDualList(document.getElementsByName('" + property.toString() + ".gaucheColSTR')[0], document.getElementsByName('" + property.toString() + ".droiteColSTR')[0], false)"; %>
	<td valign="top" align="center">
		<html:button value="Ajouter >" property="AjouterAff" style="width: 75px" disabled="false" onclick="<%= s %>" styleId="btnAjouter" />
    </td>
    
    <%-- Liste de droite --%>
    <td rowspan="2">
		<bean:define id="lesElementsD" name="<%= formName.toString() %>" property='<%= property.toString() + ".droiteCols" %>' type="java.util.List" />
		<html:select name="<%= formName.toString()%>" property='<%=property.toString()+".droiteColSTR" %>' value="" multiple="true" size='<%= listeHauteur.toString() %>' style='<%= "width:"+listeDroiteLargeur %>' styleId="lstDroite">
            <html:options collection="lesElementsD" property="value" labelProperty="label" />
		</html:select>
	</td>
</tr>

<tr>
	<%--Bouton Supprimer --%>  
	<% s = "javascript:moveDualList(document.getElementsByName('" + property.toString() + ".droiteColSTR')[0], document.getElementsByName('" + property.toString() + ".gaucheColSTR')[0], false)"; %>
    <td valign="bottom" align="center">
    	<html:button value="< Supprimer" property="Supprimer" style="width: 75px" disabled="false" onclick="<%= s %>" styleId="btnSupprimer" />
    </td>
  </tr>
</table>
