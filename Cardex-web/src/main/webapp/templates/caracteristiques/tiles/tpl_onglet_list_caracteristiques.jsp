<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<!-- ------------------------------ -->
<DIV id="DATA_CARACTERISTICS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><bean:message key='tabpage_carac'/></TD>
    </TR>
    <logic:iterate id="element" name="sujet" property='caracteristiques.doubleListe.droiteColLabel'>
            <TR>
                <TD class="listDetailOdd"><%=element %></TD>
            </TR>
    </logic:iterate>        
    
    <TR>
        <TD class="listDetailOdd" nowrap>
			<cardex:button labelKey="cb_ajouter" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/caracteristiques/show.do"%>'/>
        </TD>
        <TD class="listDetailOdd" colspan="11">&nbsp;</TD>
    </TR>
    </TABLE>
</DIV>
<!-- End data_caracteristics division -->
