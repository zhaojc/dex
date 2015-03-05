<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.AdresseOngletTrieListe" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="form" id="form" />

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<!-- ------------------------------ -->
<DIV id="DATA_ADDRESS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_SITE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_ADRESSE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_VILLE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_CODE_POSTAL%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_TELEPHONE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='<%=form.toString()%>' property="listeAdresses" key='<%=AdresseOngletTrieListe.CLE_STATUT%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/trier.do"%>'/>
        </TD>
    </TR>
    <logic:iterate id="element" name='<%=form.toString()%>' property='adresses'>
    <TR>
        <TD class="listDetailOdd">
          <cardex:linkObject object='element' 
          page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/adresse/show.do"%>'>
              <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
          </cardex:linkObject>
          <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
              <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		  <cardex:linkObject onclick="return doConfirmLinkSuppression();" object='element' 
		  page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/adresse/delete.do"%>'>
		      <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		  </cardex:linkObject>
              </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>
   	        <bean:write name="element" property="siteDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="adresseTag1"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="villeDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="codePostal"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="telephone1"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="statutDescription"/>
        </TD>
    </TR>
    </logic:iterate>
    <TR>
        <TD class="listDetailOdd" nowrap>
			<cardex:button labelKey="cb_ajouter" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/adresse/create.do"%>'/>
        </TD>
        <TD class="listDetailOdd" colspan="8">&nbsp;</TD>
    </TR>
    </TABLE>
</DIV>
<!-- End data_folders division -->
