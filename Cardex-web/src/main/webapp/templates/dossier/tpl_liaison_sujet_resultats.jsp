<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'un sujet.
Description : Module d'affichage représentant les résutlats de recherche d'un
              sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/03/19 23:32:24 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/03/19 23:32:24 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetTrieListe" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheSujet' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->
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
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_NO_FICHE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="severiteAutreSecurite" nomFormulaire="sujet" propertyFormulaire='severiteAutres' >
				   <TD class="listTableHeader" nowrap="nowrap">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE_AUTRES%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="sujet" propertyFormulaire='severite' >
				   <TD class="listTableHeader" nowrap="nowrap">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="sujet" propertyFormulaire='severiteCasino' >
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
                    </TD>
			   </cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="sujet" propertyFormulaire='nom' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_NOM%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="prenomSecurite" nomFormulaire="sujet" propertyFormulaire='prenom' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_PRENOM%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="aliasSecurite" nomFormulaire="sujet" propertyFormulaire='alias' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SURNOM%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="sexeSecurite" nomFormulaire="sujet" propertyFormulaire='sexe' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_SEXE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="ethnieSecurite" nomFormulaire="sujet" propertyFormulaire='ethnie' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_ETHNIE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="raceSecurite" nomFormulaire="sujet" propertyFormulaire='race' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_RACE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="langueSecurite" nomFormulaire="sujet" propertyFormulaire='langue' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_LANGUE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>			   
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateNaissanceSecurite" nomFormulaire="sujet" propertyFormulaire='dateNaissance' >
				   <TD class="listTableHeader" nowrap="nowrap">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_DATE_NAISSANCE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>			   
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="typeAgeSecurite" nomFormulaire="sujet" propertyFormulaire='typeAge' >
				   <TD class="listTableHeader">
						<cardex:EnteteListeTriable name="rechercheSujet" property="listeResultat" key='<%=SujetTrieListe.CLE_TYPE_AGE%>' URLTrier="/dossier/sujet/rechercheTrier.do"/>		   
				   </TD>
			   </cardex:securityDefineTag>
		    </TR>

	        <logic:iterate id='element' name='rechercheSujet' property='listeResultat.resultatAffichage' >
               <TR>
                   <TD class="listDetailOdd">
                      <cardex:linkLiaisonSujet source='rechercheSujet' sourceProperty='dossier' sujet='element' page='/dossier/sujet/link/role/show.do'>
                        <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                      </cardex:linkLiaisonSujet>
                 </TD>
	               <logic:equal name="numeroFicheSecuriteHidden" value="false">
		               <TD class="listDetailOdd" nowrap>
		                  <cardex:linkSujet sujet='element' page='/sujet/showAcces.do'>
							<bean:write name="element" property="numeroFiche"/>
		                  </cardex:linkSujet>
		               </TD>
	                </logic:equal>
	                <logic:equal name="severiteAutreSecuriteHidden" value="false">
			           <TD class="severity<bean:write name="element" property="severiteDescriptionAutres"/>"
			              align="center" >&nbsp;<bean:write name="element" property="severiteDescriptionAutres"/></TD>
		           </logic:equal>
				   <logic:equal name="severiteSecuriteHidden" value="false">		                 
		               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
		                 align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
		           </logic:equal>
	               <logic:equal name="severiteCasinoSecuriteHidden" value="false">
	                  <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
	                    align="center" ><bean:write name="element" property="severiteCasinoDescription"/>&nbsp;</TD>
	               </logic:equal>
				   <logic:equal name="nomSecuriteHidden" value="false">		                 
		               <TD class="listDetailOdd" nowrap="nowrap"><bean:write name='element' property='nom' /></TD>
		           </logic:equal>
				   <logic:equal name="prenomSecuriteHidden" value="false">		                 
		               <TD class="listDetailOdd"><bean:write name='element' property='prenom' /></TD>
		           </logic:equal>
				   <logic:equal name="aliasSecuriteHidden" value="false">		                 
		               <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='alias' /></TD>
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
		               <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateNaissance"/></TD>
		           </logic:equal>
				<logic:equal name="typeAgeSecuriteHidden" value="false">
					<TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="typeAgeDescription"/></TD>
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
	        <TD>&nbsp;</TD>
	       <TD ALIGN="right" VALIGN="middle">
	    		<cardex:NavigationResultatListe name="rechercheSujet" property="listeResultat" URLChangerPage="/dossier/sujet/rechercheChangerPage.do" />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

