<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
 
<!-- Consignations --> 
<logic:iterate id="consignation" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='consignations' indexId='index' >
</logic:iterate>
<logic:present name="consignation" >
<P>
  <TABLE width="650" cellpadding="2" cellspacing="0" rules="none" >
<logic:iterate id="consignation" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='consignations' indexId='index' >
<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="5" class="errorHeader" height="30">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='consignation.rapport'/></TD>
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
      <TD width="150" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ty_cle_t'/></b></TD>
      <TD width="150">
          <cardex:afficherValeurListeTag name="consignation" property="typeConsignation" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_CONSIGNATION%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
      </TD>
      <TD width="150" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_creation_t'/></b></TD>
      <TD width="180">
          <bean:write name="consignation" property="dateCreation"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='description_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="description"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='no_serie_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="numeroSerie"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='denomination_t'/></b></TD>
      <TD>
		  <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.DENOMINATION %>' name="consignation" property="denomination"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='devise_t'/></b></TD>
      <TD>
          <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.DEVISE %>' name="consignation" property="devise"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='quantite_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="quantite"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='r_co_montant_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="montant"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='dimension_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="dimension"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='poids_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="poids"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='prix_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="prix"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='fournisseur_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="fournisseur"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ma_cle_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="marque"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_md_cle_t'/></b></TD>
      <TD>
          <bean:write name="consignation" property="modele"/>
      </TD>
    </TR>
  <TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_co_rapporte_par_t'/></b></TD>
      <TD colspan="3">
          <cardex:afficherValeurListeTag name="consignation" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
      </TD>
    </TR>
  <TR>
    <TD colspan="5" height="5"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
</logic:iterate>

  </TABLE>

</logic:present>
