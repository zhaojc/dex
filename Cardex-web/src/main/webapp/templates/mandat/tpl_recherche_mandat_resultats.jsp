<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche de mandats.
Description : Module d'affichage représentant les résultats d'une recherche de
              mandats.
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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.PSUMandatTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->

<TABLE align="left" width="650" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="650"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='recherchePSUMandat' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left">
<logic:greaterThan name='recherchePSUMandat' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

    <TABLE width="650" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_NUMERO_MANDAT%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_STATUT%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_DESCRIPTION%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" width="75">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_DATE_DEBUT%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" width="75">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_DATE_FIN%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='recherchePSUMandat' property="listeResultat" key='<%=PSUMandatTrieListe.CLE_TOTAL%>' URLTrier="/mandat/rechercheTrier.do"/>
        </TD>
    </TR>
    <logic:iterate id='element' name='recherchePSUMandat' property='listeResultat.resultatAffichage' >
    <TR>
        <TD class="listDetailOdd" nowrap width="16">
          <logic:notEqual name="element" property="approbateur" value=' '>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" width="40" nowrap align="center">
          <cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='element' page='/mandat/delete.do'>
			  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		  </cardex:linkObject>
		  <bean:define id="mandat" name="element" property="numeroMandat" type="String"/>
		  <IMG onclick="doListeActions('<%= mandat %>');" src="<%=request.getContextPath()%>/images/new.gif" altKey="liste" border="1" height="14" width="14" />
        </TD>
        <TD class="listDetailOdd" nowrap>
           <html:hidden  name="element" property="entite" />
           <html:hidden  name="element" property="siteCible" />
           <html:hidden  name="element" property="genre" />
           <html:hidden  name="element" property="nature" />
           <html:hidden  name="element" property="type" />
           <html:hidden  name="element" property="typeAction" />
          <cardex:linkPSUMandat mandat='element' page='/mandat/show.do'>
              <bean:write name="element" property="numeroMandat"/>
          </cardex:linkPSUMandat>
        </TD>
        <TD class="listDetailOdd" ><bean:write name="element" property="statutDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="description"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dateDebut10"/></TD>        
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dateFin10"/></TD>
        <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="total"/></TD>
    </TR>
    </logic:iterate>
    </TABLE>
    <TABLE align="left" width="590" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
     <TR>
        <TD><cardex:button securityConstraint='cardex.recherche.mandat.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' /></TD>
       <TD ALIGN="right" VALIGN="middle">
    		<cardex:NavigationResultatListe name="recherchePSUMandat" property="listeResultat" URLChangerPage="/mandat/rechercheChangerPage.do"/>
       </TD>
      </TR>
    </TABLE>
    
<!-- End Number of records to display -->
</logic:greaterThan>
