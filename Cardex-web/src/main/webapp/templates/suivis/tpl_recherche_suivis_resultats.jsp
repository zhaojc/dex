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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SuiviTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="950" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="950"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheSuivi' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left">
<logic:greaterThan name='rechercheSuivi' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
    <TABLE width="950" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_ACTIVITE%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_DEMANDEUR%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_INTERVENANT%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_SUIVI%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader" align="center">
            <cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_DATE_PREVUE%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="rechercheSuivi" property="listeResultat" key='<%=SuiviTrieListe.CLE_DATE_COMPLETE%>' URLTrier="/suivi/rechercheTrier.do" />
        </TD>
    </TR>
    <logic:iterate id='element' name='rechercheSuivi' property='listeResultat.resultatAffichage' >
	<TR>
        <TD class="listDetailOdd" nowrap>&nbsp;
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
            <bean:write name="element" property="dossier.numeroCardexTexte"/>
          </cardex:linkDossier>
        </TD>
        <TD class="listDetailOdd" width="100">&nbsp;<bean:write name="element" property="activiteDescription"/></TD>
        <TD class="listDetailOdd" width="200">&nbsp;<bean:write name="element" property="demandeurDescription"/></TD>
        <TD class="listDetailOdd" width="200">&nbsp;<bean:write name="element" property="intervenantDescription"/></TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<html:textarea name="element" property="suivi" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;' /></TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="datePrevue"/></TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateCompletee"/></TD>
    </TR>
    </logic:iterate>
    </TABLE>
    <TABLE align="left" width="950" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
     <TR>
        <TD><cardex:button securityConstraint='cardex.recherche.suivis.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' /></TD>
       <TD ALIGN="right" VALIGN="middle">
    		<cardex:NavigationResultatListe name="rechercheSuivi" property="listeResultat" URLChangerPage="/suivi/rechercheChangerPage.do" />
       </TD>
      </TR>
    </TABLE>
    
<!-- End Number of records to display -->
</logic:greaterThan>
