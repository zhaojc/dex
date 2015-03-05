<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'un sujet.
Description : Module d'affichage représentant les résutlats de recherche d'un
              sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.17 $, $Date: 2002/04/04 20:40:19 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.17 $, $Date: 2002/04/04 20:40:19 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetTrieListe" %>

<jsp:include page='/scripts/sectionEscamotable.jsp' flush="true"/>


<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="600"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheSujet' property='listeResultat.size'/></b></TD>
	<TD><cardex:button securityConstraint='cardex.recherche.sujets.imprimer' labelKey='Imprimer'  onclick='doPrint();' /></TD>
	<TD ALIGN="right" VALIGN="middle">
	    <cardex:button securityConstraint='cardex.recherche.sujets.imprimer' labelKey='ImprimerTout'  onclick='doPrintAll();' />&nbsp;
	</TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->
<logic:greaterThan name='rechercheSujet' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
		  <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader">&nbsp;</TD>
			   <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="sujet" propertyFormulaire='numeroFiche' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_NO_FICHE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="severiteAutresSecurite" nomFormulaire="sujet" propertyFormulaire='severiteAutres' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE_AUTRES%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="sujet" propertyFormulaire='severite' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="sujet" propertyFormulaire='severiteCasino' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="sujet" propertyFormulaire='nom' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_NOM%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="prenomSecurite" nomFormulaire="sujet" propertyFormulaire='prenom' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_PRENOM%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="aliasSecurite" nomFormulaire="sujet" propertyFormulaire='alias' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SURNOM%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="sujet" propertyFormulaire='statut' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_STATUT%>' URLTrier="/sujet/rechercheTrier.do"/>			   
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="sexeSecurite" nomFormulaire="sujet" propertyFormulaire='sexe' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEXE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="ethnieSecurite" nomFormulaire="sujet" propertyFormulaire='ethnie' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_ETHNIE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="raceSecurite" nomFormulaire="sujet" propertyFormulaire='race' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_RACE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="langueSecurite" nomFormulaire="sujet" propertyFormulaire='langue' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_LANGUE%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateNaissanceSecurite" nomFormulaire="sujet" propertyFormulaire='dateNaissance' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_DATE_NAISSANCE%>' URLTrier="/sujet/rechercheTrier.do"/>		   
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="typeAgeSecurite" nomFormulaire="sujet" propertyFormulaire='typeAge' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_TYPE_AGE%>' URLTrier="/sujet/rechercheTrier.do"/>		   
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="reference1Securite" nomFormulaire="sujet" propertyFormulaire='reference1' >
				   <TD class="listTableHeader" nowrap>
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_REFERENCE1%>' URLTrier="/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
		    </TR>

        <logic:iterate id='element' name='rechercheSujet' property='listeResultat.resultatAffichage' indexId="index">
        	<cardex:SectionEscamotableEnteteTR sufixIdCible='<%=String.valueOf(index)%>' onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
				<logic:equal name="numeroFicheSecuriteHidden" value="false">
				    <TD class="listDetailOdd" nowrap>
				          <cardex:linkSujet sujet='element' page='/sujet/showAcces.do'>
				            <bean:write name="element" property="numeroFiche"/>
				          </cardex:linkSujet>                  
				    </TD>
				</logic:equal>
				<logic:equal name="severiteAutresSecuriteHidden" value="false">
				    <TD class="severity<bean:write name="element" property="severiteDescriptionAutres"/>"
				      align="center" >&nbsp;<bean:write name="element" property="severiteDescriptionAutres"/></TD>
				</logic:equal>
				<logic:equal name="severiteSecuriteHidden" value="false">		                 
				    <TD class="severity<bean:write name="element" property="severiteDescription"/>"
				      align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
				</logic:equal>
				<logic:equal name="severiteCasinoSecuriteHidden" value="false">		                 
				    <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
				      align="center" >&nbsp;<bean:write name="element" property="severiteCasinoDescription"/></TD>
				</logic:equal>
				<logic:equal name="nomSecuriteHidden" value="false">		                 
				    <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='nom' /></TD>
				</logic:equal>
				<logic:equal name="prenomSecuriteHidden" value="false">		                 
				    <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='prenom' /></TD>
				</logic:equal>
				<logic:equal name="aliasSecuriteHidden" value="false">		                 
				    <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='alias' /></TD>
				</logic:equal>
				<logic:equal name="statutSecuriteHidden" value="false">
					<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='statutDescription' /></TD>
				</logic:equal>
				<logic:equal name="sexeSecuriteHidden" value="false">		                 
					<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='sexeDescription' /></TD>
				</logic:equal>
				<logic:equal name="ethnieSecuriteHidden" value="false">		                 
					<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='ethnieDescription' /></TD>
				</logic:equal>
				<logic:equal name="raceSecuriteHidden" value="false">		                 
					<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='raceDescription' /></TD>
				</logic:equal>
				<logic:equal name="langueSecuriteHidden" value="false">		                 
					<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='langueDescription' /></TD>
				</logic:equal>
				<logic:equal name="dateNaissanceSecuriteHidden" value="false">		                 
					<TD class="listDetailOdd" nowrap>&nbsp;<bean:write name='element' property='dateNaissance'/></TD>
				</logic:equal>
				<logic:equal name="typeAgeSecuriteHidden" value="false">
					<TD class="listDetailOdd" nowrap>&nbsp;<bean:write name='element' property='typeAgeDescription'/></TD>
				</logic:equal>			        	
				<logic:equal name="reference1SecuriteHidden" value="false">		                 
				       <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='reference1'/></TD>
				</logic:equal>
              </cardex:SectionEscamotableEnteteTR>
              <bean:define id="cle" name="element" property="cle" type="String" />
              <bean:define id="site" name="element" property="site" type="String"/>
              <cardex:SectionEscamotableContenuTR sufixId='<%=String.valueOf(index)%>' colSpan="15">
              	<table width="100%"><tr><td>
				    <TABLE align="left" width="100%" cellPadding="2" cellSpacing="0" border="1" >
						<cardex:SectionEscamotableEnteteTR sufixIdCible='<%=String.valueOf(index)+"A"%>' onMouseOver="this.className='listDetailEven'" 
						onMouseOut="this.className='listDetailOdd'" httpServletAjax="AdresseSujetServlet" cle='<%=cle%>' site='<%=site%>' securityConstraint="cardex.sujet.adresses.consulter">
		                   <TD class="listDetailOdd" width="100%"><b><bean:message key="tabpage_adress" /></b></TD>
			            </cardex:SectionEscamotableEnteteTR> 
		                <cardex:SectionEscamotableContenuTR sufixId='<%=String.valueOf(index)+"A"%>' colSpan="2" securityConstraint="cardex.sujet.adresses.consulter">
			                <bean:message key="chargement"/> ...
		                </cardex:SectionEscamotableContenuTR>
	              	</TABLE>
				</td></tr><tr><td>
				    <TABLE align="left" width="100%" cellPadding="2" cellSpacing="0" border="1" >
						<cardex:SectionEscamotableEnteteTR sufixIdCible='<%=String.valueOf(index)+"D"%>' onMouseOver="this.className='listDetailEven'" 
						onMouseOut="this.className='listDetailOdd'" httpServletAjax="DossierSujetServlet" cle='<%=cle%>' site='<%=site%>' securityConstraint="cardex.sujet.dossiers.consulter">
		                   <TD class="listDetailOdd" width="100%"><b><bean:message key="tabpage_dossier" /></b></TD>
			            </cardex:SectionEscamotableEnteteTR>
		                <cardex:SectionEscamotableContenuTR sufixId='<%=String.valueOf(index)+"D"%>' colSpan="2" securityConstraint="cardex.sujet.dossiers.consulter">
			                <bean:message key="chargement"/> ...
		                </cardex:SectionEscamotableContenuTR>
	              	</TABLE>              	
				</td></tr></table>              	
              </cardex:SectionEscamotableContenuTR>
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
		   <TD align="right">
	    		<cardex:NavigationResultatListe name="rechercheSujet" property="listeResultat" URLChangerPage="/sujet/rechercheChangerPage.do"/>
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
