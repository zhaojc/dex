

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- ------------------------------ -->
<DIV id="DATA_SOUS_CATEGORIES">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
    	<TD class="listTableHeader" align="center"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader"><bean:message key='sous.categories'/></TD>
        <TD class="listTableHeader"><bean:message key='liePar'/></TD>
        <TD class="listTableHeader"><bean:message key='lieLe'/></TD>
    </TR>
    <bean:define id="nature" name="dossier" property="nature" type="String"/>
    <TR>
        <TD class="listDetailOdd" nowrap colspan="2">
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
	            <logic:equal name='dossier' property='permettreModificationApprobation' value='false' >
					<cardex:button labelKey="cb_ajouter" soumettre="/dossier/sousCategories/show.do"/>
				</logic:equal>
	            <logic:equal name='dossier' property='permettreModificationApprobation' value='true' >
					<cardex:button labelKey="cb_ajouter" disabled="true" />
				</logic:equal>
	
	            <logic:greaterThan name='dossier' property='listeSousCategoriesSize' value="0">
	            <logic:equal name='dossier' property='modifiable' value='true' >
	                &nbsp;
		            <logic:equal name='dossier' property='permettreModificationApprobation' value='false' >
		              <cardex:button labelKey='cb_approbation' style="width: 70px; text-align: center;" soumettre="/dossier/approuverSousCategories.do" />
		            </logic:equal>
		            <logic:equal name='dossier' property='permettreModificationApprobation' value='true' >
		              <cardex:button labelKey='cb_approbation' style="width: 70px; text-align: center;" disabled='true' />
		            </logic:equal>
		            &nbsp;
		            <logic:equal name='dossier' property='permettreModificationApprobation' value='true' >
		              <cardex:button labelKey='cb_modification' style='width: 110px' soumettre="/dossier/modifierApprouverSousCategories.do"  />
		            </logic:equal>
		            <logic:equal name='dossier' property='permettreModificationApprobation' value='false' >
		              <INPUT type='button' value="<bean:message key='cb_modification' />"  style='width: 110px' disabled />
		            </logic:equal>
		        </logic:equal>
	            </logic:greaterThan>
            </logic:notEqual>
            
        </TD>
    </TR>    
    <logic:iterate id="element" name="dossier" property='listeSousCategories' indexId="index">
    <TR>
    	<TD class="listDetailOdd">
    	  <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>    
        <TD class="listDetailOdd"><bean:write name="element" property="description" /></TD>
        <TD class="listDetailOdd"><bean:write name="element" property="creerPar" /></TD>
        <TD class="listDetailOdd"><bean:write name="element" property="dateCreation" /></TD>
    </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_sous_categories division -->
