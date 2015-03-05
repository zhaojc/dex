<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche d'un dossier.
Description : Module d'affichage représentant les résultats d'une recherche de
              dossier.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.28 $, $Date: 2002/03/28 21:57:05 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.28 $, $Date: 2002/03/28 21:57:05 $, $Author: abruno-boucher $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<jsp:include page='/scripts/sectionEscamotable.jsp' flush="true"/>

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheDossier' property='listeResultat.size'/></b></TD>
</TR>
</TABLE><!-- End Number of records to display -->
<br clear="left"><!-- Search Kind & nature list -->

<logic:greaterThan name='rechercheDossier' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
		  <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader">&nbsp;</TD>
			   <cardex:securityDefineTag nameDefine="numeroCardexSecurite" nomFormulaire="dossier" propertyFormulaire='numeroCardex' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="numeroDossierSecurite" nomFormulaire="dossier" propertyFormulaire='numeroDossier' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="reference1Securite" nomFormulaire="dossier" propertyFormulaire='reference1' >
				   <TD class="listTableHeader" nowrap>
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_REFERENCE1%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="reference2Securite" nomFormulaire="dossier" propertyFormulaire='reference2' >
				   <TD class="listTableHeader" nowrap>
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_REFERENCE2%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="reference3Securite" nomFormulaire="dossier" propertyFormulaire='reference3' >
				   <TD class="listTableHeader" nowrap>
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_REFERENCE3%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="dossier" propertyFormulaire='severite' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_SEVERITE%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="confidentialiteSecurite" nomFormulaire="dossier" propertyFormulaire='confidentialite' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="typeSecurite" nomFormulaire="dossier" propertyFormulaire='type' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_TYPE%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="categorieSecurite" nomFormulaire="dossier" propertyFormulaire='categorie' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_CATEGORIE%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="periodeSecurite" nomFormulaire="dossier" propertyFormulaire='periode' >
				   <TD class="listTableHeader" >
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_PERIODE%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="dossier" propertyFormulaire='statut' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_STATUT%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateDebutSecurite" nomFormulaire="dossier" propertyFormulaire='dateDebut' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_DATE_DEBUT%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateFinSecurite" nomFormulaire="dossier" propertyFormulaire='dateFin' >
				   <TD class="listTableHeader" nowrap>
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_DATE_FIN%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="intervenantSecurite" nomFormulaire="dossier" propertyFormulaire='intervenant' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/partage/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
		    </TR>

            <logic:iterate id='element' name='rechercheDossier' property='listeResultat.resultatComplet' indexId="index">
          	<cardex:SectionEscamotableEnteteTR sufixIdCible='<%=String.valueOf(index)%>' onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
				<logic:equal name="numeroCardexSecuriteHidden" value="false">          		 
	          		 <TD class="listDetailOdd">
	                    <cardex:linkDossierPartage dossier='element' page='/dossier/showAcces.do'>
	                      <bean:write name="element" property="numeroCardexTexte"/>
	                    </cardex:linkDossierPartage>
	                 </TD>
                 </logic:equal>
                 <logic:equal name="numeroDossierSecuriteHidden" value="false">
                 	<TD class="listDetailOdd" ><bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
                 </logic:equal>
                 <logic:equal name="reference1SecuriteHidden" value="false">
                 <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="reference1"/></TD>
                 </logic:equal>
                 <logic:equal name="reference2SecuriteHidden" value="false">
              	 	<TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="reference2"/></TD>
				 </logic:equal>
                 <logic:equal name="reference3SecuriteHidden" value="false">
                 	<TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="reference3"/></TD>
				 </logic:equal>
                 <logic:equal name="severiteSecuriteHidden" value="false">
                 	<TD class="severity<bean:write name="element" property="severiteDescription"/>"
                   	align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
				 </logic:equal>
                 <logic:equal name="confidentialiteSecuriteHidden" value="false">
                	<TD class="listDetailOdd" align="center" ><bean:write name="element" property="confidentialiteDescription"/></TD>
				 </logic:equal>
                 <logic:equal name="typeSecuriteHidden" value="false">
                 	<TD class="listDetailOdd"><bean:write name="element" property="typeDescription"/></TD>
			     </logic:equal>              
   				 <logic:equal name="categorieSecuriteHidden" value="false">
                 	<TD class="listDetailOdd"><bean:write name="element" property="categorieDescription"/></TD>
				 </logic:equal>              
   				 <logic:equal name="periodeSecuriteHidden" value="false">
                 	<TD class="listDetailOdd">&nbsp;<bean:write name="element" property="periodeDescription"/></TD>
				 </logic:equal>                 
				 <logic:equal name="statutSecuriteHidden" value="false">
                 	<TD class="listDetailOdd"><bean:write name="element" property="statutDescription"/></TD>
				 </logic:equal>				 
				 <logic:equal name="dateDebutSecuriteHidden" value="false">
                 	<TD class="listDetailOdd" nowrap><bean:write name="element" property="dateDebut16"/></TD>
				 </logic:equal>	
				 <logic:equal name="dateFinSecuriteHidden" value="false">
                 	<TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateFin10"/></TD>
				 </logic:equal>
				 <logic:equal name="intervenantSecuriteHidden" value="false">
                 	<TD class="listDetailOdd"><bean:write name="element" property="intervenant"/></TD>
				 </logic:equal>
            </cardex:SectionEscamotableEnteteTR>
              <bean:define id="cle" name="element" property="cle" type="String" />
              <bean:define id="site" name="element" property="site" type="String"/>
              <cardex:SectionEscamotableContenuTR sufixId='<%=String.valueOf(index)%>' colSpan="15">
              	<table width="100%"><tr><td>
				    <TABLE align="left" width="100%" cellPadding="2" cellSpacing="0" border="1" >
						<cardex:SectionEscamotableEnteteTR sufixIdCible='<%=String.valueOf(index)+"SC"%>' onMouseOver="this.className='listDetailEven'" 
						onMouseOut="this.className='listDetailOdd'" httpServletAjax="SousCategorieServlet" cle='<%=cle%>' site='<%=site%>' securityConstraint="cardex.dossier.souscategories.onglet">
		                   <TD class="listDetailOdd" width="100%"><b><bean:message key="sous.categories" /></b></TD>
			            </cardex:SectionEscamotableEnteteTR> 
		                <cardex:SectionEscamotableContenuTR sufixId='<%=String.valueOf(index)+"SC"%>' colSpan="2" securityConstraint="cardex.dossier.souscategories.onglet">
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

</logic:greaterThan>
