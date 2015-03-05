<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'une société.
Description : Module d'affichage représentant les résutlats de recherche d'une
              société.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/03/28 23:35:13 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/03/28 23:35:13 $, $Author: abruno-boucher $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteTrieListe" %>

<br>
<!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="600"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheSociete' property='listeResultat.size'/></b></TD>
	<TD><cardex:button securityConstraint='cardex.recherche.societes.imprimer' labelKey='Imprimer'  onclick='doPrint();' /></TD>
	<TD ALIGN="right" VALIGN="middle">
	    <cardex:button securityConstraint='cardex.recherche.societes.imprimer' labelKey='ImprimerTout'  onclick='doPrintAll();' />&nbsp;
	</TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->

<logic:greaterThan name='rechercheSociete' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
           <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
                 <TR>
                   <TR>
                   		<cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="societe" propertyFormulaire='nom' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_NOM%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="classeSecurite" nomFormulaire="societe" propertyFormulaire='classe' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_CLASSE%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="societe" propertyFormulaire='numeroFiche' > 
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_NO_FICHE%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="societe" propertyFormulaire='severite' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_SEVERITE%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="societe" propertyFormulaire='severiteCasino' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="raisonEtreSecurite" nomFormulaire="societe" propertyFormulaire='raisonEtre' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_RAISON_SOCIALE%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="referenceNomSecurite" nomFormulaire="societe" propertyFormulaire='referenceNom' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_REFERENCE_NOM%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
					   <cardex:securityDefineTag nameDefine="referencePrenomSecurite" nomFormulaire="societe" propertyFormulaire='referencePrenom' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteTrieListe.CLE_REFERENCE_PRENOM%>' URLTrier="/societe/rechercheTrier.do"/>
	                    </TD>
					   </cardex:securityDefineTag>
                   </TR>

                  <logic:iterate id='element' name='rechercheSociete' property='listeResultat.resultatAffichage' >

                   <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                   	<logic:equal name="nomSecuriteHidden" value="false">
                       <TD class="listDetailOdd" width="250">
                          <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                            <bean:write name='element' property='nom' />
                          </cardex:linkSociete>
                       </TD>
	                 </logic:equal>
	                 <logic:equal name="classeSecuriteHidden" value="false">
                       <TD class="listDetailOdd"><bean:write  name='element' property='classeDescription' />&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="numeroFicheSecuriteHidden" value="false">
                       <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroFiche"/>&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="severiteSecuriteHidden" value="false">
	                   <TD class="severity<bean:write name="element" property="severiteDescription"/>"
	                     align="center" ><bean:write name="element" property="severiteDescription"/>&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="severiteCasinoSecuriteHidden" value="false">
	                   <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
	                     align="center" ><bean:write name="element" property="severiteCasinoDescription"/>&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="raisonEtreSecuriteHidden" value="false">
                       <TD class="listDetailOdd" width="150"><bean:write name='element' property='raisonEtre' />&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="referenceNomSecuriteHidden" value="false">
                       <TD class="listDetailOdd"><bean:write  name='element' property='referenceNom' />&nbsp;</TD>
	                 </logic:equal>
	                 <logic:equal name="referencePrenomSecuriteHidden" value="false">
                       <TD class="listDetailOdd"><bean:write  name='element' property='referencePrenom' />&nbsp;</TD>
	                 </logic:equal>
                  </TR>
                  </logic:iterate>

		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
	<!-- End Search Kind & nature list -->
    <br clear="left">

<!-- Number of records to display -->
	    <TABLE align="left" width="770" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
	     <TR>
	       <TD ALIGN="right" VALIGN="middle">
	    		<cardex:NavigationResultatListe name="rechercheSociete" property="listeResultat" URLChangerPage="/societe/rechercheChangerPage.do" />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>