<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- ------------------------------ -->
<DIV id="DATA_CARACTERISTICS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" colspan="2"><bean:message key='tabpage_carac'/></TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
			<cardex:button labelKey="cb_ajouter" soumettre="/sujet/caracteristiques/show.do"/>
        </TD>
        <TD class="listDetailOdd">&nbsp;</TD>
    </TR>    
    <logic:iterate id="element" name="sujet" property='caracteristiques.doubleListe.droiteColLabel'>
            <TR>
                <TD class="listDetailOdd"><%=element %></TD>
            </TR>
    </logic:iterate>    
    </TABLE>
</DIV>
<!-- End data_caracteristics division -->
