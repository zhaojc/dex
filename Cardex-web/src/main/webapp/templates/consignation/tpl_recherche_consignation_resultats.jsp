<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche d'un dossier.
Description : Module d'affichage représentant les résultats d'une recherche de
              dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/30 12:17:57 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.4 $, $Date: 2002/04/30 12:17:57 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ConsignationTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->

<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheConsignation' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left">
<logic:greaterThan name='rechercheConsignation' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

    <TABLE width="772" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_TYPE%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_DATE_CREATION%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_DESCRIPTION%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_DENOMINATION%>' URLTrier="/consignation/rechercheTrier.do"/>        
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_NUMERO_SERIE%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_QUANTITE%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_MONTANT%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_MARQUE%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_MODELE%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
	        <cardex:EnteteListeTriable name='rechercheConsignation' property="listeResultat" key='<%=ConsignationTrieListe.CLE_FOURNISSEUR%>' URLTrier="/consignation/rechercheTrier.do"/>
        </TD>
    </TR>
	<logic:iterate id='element' name='rechercheConsignation' property='listeResultat.resultatAffichage' >
    <TR>
        <TD class="listDetailOdd" nowrap>
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
          <logic:notEqual name="element" property="approuve" value='true'>
              &nbsp;
          </logic:notEqual>
        </TD>
        <TD class="listDetailOdd">
          <cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
			<bean:write name="element" property="dossier.numeroCardexTexte"/>
          </cardex:linkDossier>
        </TD>
        <TD class="listDetailOdd"><bean:write name="element" property="typeConsignationDescription"/></TD>
        <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateCreation10"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="description"/></TD>
        <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="denominationDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="numeroSerie"/></TD>
        <TD class="listDetailOdd" align="right">&nbsp;<bean:write name="element" property="quantite"/></TD>
        <TD class="listDetailOdd" align="right">&nbsp;<bean:write name="element" property="montant"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="marque"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="modele"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="fournisseur"/></TD>
    </TR>
    </logic:iterate>
    </TABLE>
    
    <TABLE align="left" width="770" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
     <TR>
        <TD><cardex:button securityConstraint='cardex.recherche.consignation.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' /></TD>
       <TD ALIGN="right" VALIGN="middle">
    		<cardex:NavigationResultatListe name="rechercheConsignation" property="listeResultat" URLChangerPage="/consignation/rechercheChangerPage.do" />
       </TD>
      </TR>
    </TABLE>
<!-- End Number of records to display -->
</logic:greaterThan>
