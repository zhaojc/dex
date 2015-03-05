<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<TD class="listDetailOdd" nowrap>
  <bean:write name="element" property="numeroDossier"/>&nbsp;
</TD>
<TD class="listDetailOdd" nowrap>
  <bean:write name="element" property="narrationSansFormat"/>
</TD>
<TD class="listDetailOdd" nowrap>
  <cardex:afficherValeurListeTag name="element" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
</TD>
<TD class="listDetailOdd" nowrap>
  <bean:write name="element" property="dateCreation"/>
</TD>
<TD class="listDetailOdd" nowrap>
  <cardex:afficherValeurListeTag name="element" property="approbateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
</TD>
<TD class="listDetailOdd">
  <bean:write name="element" property="dateApprobation"/>
</TD>
