<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "suivis".
Description : Module qui affiche le contenu de l'onglet "suivis", soit une
              liste de suivis.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/05/01 20:25:24 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.10 $, $Date: 2002/05/01 20:25:24 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ConsignationOngletTrieListe" %>

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

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
</HEAD>
<DIV id="DATA_CONSIGNATION">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" align="center"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_TYPE%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_QUANTITE%>' URLTrier="/dossier/trier.do"/>        
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_NUMERO_SERIE%>' URLTrier="/dossier/trier.do"/>        
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_PRIX_UNITAIRE%>' URLTrier="/dossier/trier.do"/>        
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_DEVISE%>' URLTrier="/dossier/trier.do"/>        
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_DESCRIPTION%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeConsignations" key='<%=ConsignationOngletTrieListe.CLE_DATE_CREATION%>' URLTrier="/dossier/trier.do"/>
        </TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_ajouter" soumettre="/dossier/consignation/create.do"/>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" colspan="11">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='consignations'>
    <TR>
        <TD class="listDetailOdd" nowrap align="center">
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <cardex:linkObject object='element' page='/dossier/consignation/show.do'>
              <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
          </cardex:linkObject>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
       		<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
       	        <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		      <cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='element' page='/dossier/consignation/delete.do'>
			  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		      </cardex:linkObject>
		</logic:equal>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="typeConsignationDescription"/>
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="quantite"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="numeroSerie"/>
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="prix"/>
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="deviseDescription"/>        
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="description"/>
        </TD>
        <TD class="listDetailOdd"><bean:write name="element" property="dateCreation10"/></TD>
    </TR>
    </logic:iterate>

    </TABLE>
</DIV>
<!-- End data_follow_up division -->