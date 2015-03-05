<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Billets -->
<logic:iterate id="billet" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='billets' indexId='index'>
</logic:iterate>
<logic:present name="billet" >
  <P>
  <TABLE width="650" cellpadding="2" cellspacing="0" rules="none" >
<logic:iterate id="billet" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='billets' indexId='index'>
<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="5" class="errorHeader" height="30">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='billet.rapport'/></TD>
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
      <TD width="150" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='nom.billet'/><bean:message key='2.points' /></b></TD>
      <TD width="150">
          <bean:write name="billet" property="nom"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='valeur.billet'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="billet" property="valeur"/>&nbsp;$
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD width="160" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='type.loterie'/><bean:message key='2.points' /></b></TD>
      <TD width="170">
          <cardex:afficherValeurListeTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.JEUX%>' name="billet" property="typeLoterie"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='montant.lot'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="billet" property="montantLot"/>&nbsp;$
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='type.mise'/><bean:message key='2.points' /></b></TD>
      <TD>
		<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='<%="billets["+index+"].typeMise"%>' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_MISE %>'/>
      </TD>
      <TD align="left"><b><bean:message key='participation.extra' /><bean:message key='2.points' /></b>
      </TD>
      <TD>
      	<logic:equal name='billet' property='extra' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='billet' property='extra' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
	</TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='numero.control'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="billet" property="numeroControl"/>
      </TD>
      <TD align="left"><b><bean:message key='extra.gagnant' /><bean:message key='2.points' /></b>
      </TD>
      <TD>
      	<logic:equal name='billet' property='extraGagnant' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='billet' property='extraGagnant' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date.paiement'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="billet" property="datePaiement"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='montant.extra'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="billet" property="montantExtra"/>&nbsp;$
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='formule.groupe'/><bean:message key='2.points' /></b></TD>
      <TD>
      	<logic:equal name='billet' property='formuleGroupe' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='billet' property='formuleGroupe' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='participation.tirage'/><bean:message key='2.points' /></b></TD>
      <TD>
      	<logic:equal name='billet' property='participationTirage' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='billet' property='participationTirage' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
    </TR>
    <TR>
      <TD colspan="5" align="center">
      	<html:img page="/images/pixelgris.gif" width="648" height="1" border="0" />
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left" width="150"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='provenance.billet'/><bean:message key='2.points' /></b></TD>
      <TD colspan="3">
          <bean:write name="billet" property="provenanceBillet"/>&nbsp;<bean:write name="billet" property="dateAchat"/>
      </TD>
    </TR>
    <TR>
	  <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='detaillant.validation'/><bean:message key='2.points' /></b></TD>
      <TD colspan="3">
          <bean:write name="billet" property="validationBillet"/>&nbsp;<bean:write name="billet" property="dateValidation"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='detaillant.verification'/><bean:message key='2.points' /></b></TD>
      <TD colspan="3">
          <bean:write name="billet" property="verificationBillet"/>&nbsp;<bean:write name="billet" property="dateVerification"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='detaillant.fautif'/><bean:message key='2.points' /></b></TD>
      <TD colspan="3">
          <bean:write name="billet" property="detaillantFautif"/>
      </TD>
    </TR>
  <TR>
    <TD colspan="5" height="5"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
</logic:iterate>

  </TABLE>

</logic:present>
