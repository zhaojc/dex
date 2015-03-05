<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Urgence -->
<logic:iterate id="urgence" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='urgence' indexId='index'>
</logic:iterate>
<logic:present name="urgence" >
  <P>
  <TABLE width="650" cellpadding="2" cellspacing="0" rules="none" >
<logic:iterate id="urgence" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='urgence' indexId='index'>
<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="5" class="errorHeader" height="30">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='urgence.rapport'/></TD>
  </TR>
  <TR>
    <TD colspan="5" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
    <TR>
      <TD width="20" valign="top" class="tabSubject"><b><bean:write name="index1"/></b>
      <TD width="150" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_cl_cle_t'/></b></TD>
      <TD width="150">
          <cardex:afficherValeurListeTag name="urgence" property="classe" classe='<%=GlobalConstants.CleListe.CLASSE %>' />
      </TD>
      <TD width="160" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='societe_t'/></b></TD>
      <TD width="170">
          <bean:write name='urgence' property='societe' />
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='district'/><bean:message key='2.points' /></b></TD>
      <TD>
		<bean:write name="urgence" property="district"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='unite'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="unite"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='client.contact'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="contact"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message key='repondant'/><bean:message key='2.points'/></b>
      </TD>
      <TD>
      	<logic:equal name='urgence' property='repondant' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='urgence' property='repondant' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
	</TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_telephone_t'/></b></TD>
      <TD>
          <bean:write name="urgence" property="telephone"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message key='telecopieur' /><bean:message key='2.points' /></b>
      </TD>
      <TD>
			<bean:write name="urgence" property="telecopieur"/>
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='courriel_t2'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="courriel"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='numero.evenement'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="evenement"/>&nbsp;
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_st_cle_t'/></b></TD>
      <TD>
             <cardex:afficherValeurListeTag name='urgence' property='statut' classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.URGENCE %>'/>
      </TD>
      <TD align="left"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='motif'/><bean:message key='2.points' /></b></TD>
      <TD>
			<cardex:afficherValeurListeTag name='urgence' property='motif' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.MOTIF %>'/>      </TD>
    </TR>
  <TR>
    <TD colspan="5" height="5"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
</logic:iterate>

  </TABLE>

</logic:present>
