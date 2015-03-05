<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche d'un dossier.
Description : Module d'affichage représentant les résultats d'une recherche de
              dossier.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/22 20:12:22 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/04/22 20:12:22 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ApprobationTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheNarration' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left">
<logic:greaterThan name='rechercheNarration' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_COMMENTAIRE%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_CREATEUR%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_DATE_CREATION%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_APPROBATEUR%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>        
        <TD class="listTableHeader" align="center">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=ApprobationTrieListe.CLE_DATE_APPROBATION%>' URLTrier="/approbation/rechercheTrier.do"/>
        </TD>        
    </TR>
    <logic:iterate id='element' name='rechercheNarration' property='listeResultat.resultatAffichage' >
    <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
        <TD class="listDetailOdd">&nbsp;
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd">
          <cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
            <bean:write name="element" property="dossier.numeroCardexTexte"/>
          </cardex:linkDossier>
        </TD>
        <TD class="listDetailOdd" nowrap>
        	<html:textarea name="element" property="narrationSansFormat" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;' />
        </TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="createurDescription"/></TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateCreation"/></TD>
        <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="approbateurDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dateApprobation"/></TD>
    </TR>
    </logic:iterate>
    </TABLE>
    
    <TABLE align="left" width="770" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
     <TR>
        <TD>&nbsp;</TD>
       <TD ALIGN="right" VALIGN="middle">
    		<cardex:NavigationResultatListe name="rechercheNarration" property="listeResultat" URLChangerPage="/approbation/rechercheChangerPage.do" />
       </TD>
      </TR>
    </TABLE>
    
<!-- End Number of records to display -->
</logic:greaterThan>
