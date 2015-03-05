<%-- --------------------------------------------------------------------------
Use case    : Résultat de liaison d'un dossier.
Description : Module d'affichage représentant les résultats d'une liaison d'un
              dossier.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/03/21 20:53:28 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/03/21 20:53:28 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierLiaisonTrieListe" %>

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
			   <TD class="listTableHeader">&nbsp;</td>
			   <cardex:securityDefineTag nameDefine="numeroCardexSecurite" nomFormulaire="dossier" propertyFormulaire='numeroCardex' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="numeroDossierSecurite" nomFormulaire="dossier" propertyFormulaire='numeroDossier' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="dossier" propertyFormulaire='severite' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_SEVERITE%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="confidentialiteSecurite" nomFormulaire="dossier" propertyFormulaire='confidentialite' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="natureSecurite" nomFormulaire="dossier" propertyFormulaire='nature' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NATURE%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="typeSecurite" nomFormulaire="dossier" propertyFormulaire='type' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_TYPE%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="categorieSecurite" nomFormulaire="dossier" propertyFormulaire='categorie' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_CATEGORIE%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="dossier" propertyFormulaire='statut' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_STATUT%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateDebutSecurite" nomFormulaire="dossier" propertyFormulaire='dateDebut' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_DATE_DEBUT%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="dateFinSecurite" nomFormulaire="dossier" propertyFormulaire='dateFin' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_DATE_FIN%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
			   <cardex:securityDefineTag nameDefine="intervenantSecurite" nomFormulaire="dossier" propertyFormulaire='intervenant' >
				   <TD class="listTableHeader">
				   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_INTERVENANT%>' URLTrier="/societe/dossier/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
		    </TR>

	      <logic:iterate id='element' name='rechercheDossier' property='listeResultat.resultatAffichage' >
	             <TR>
	                 <TD class="listDetailOdd">
	                    <cardex:linkLiaisonDossier source='rechercheDossier' sourceProperty='societe' dossier='element' page='/societe/dossier/link/role/show.do'>
	                      <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
	                    </cardex:linkLiaisonDossier>
	                 </TD>
	                 <logic:equal name="numeroCardexSecuriteHidden" value="false">
	                  <TD class="listDetailOdd" nowrap>
	                     <cardex:linkDossier dossier='element' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
							<bean:write name="element" property="numeroCardexTexte"/>
	                     </cardex:linkDossier>
	                  </TD>
					</logic:equal>
					<logic:equal name="numeroDossierSecuriteHidden" value="false">
	                  <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
					</logic:equal>
					<logic:equal name="severiteSecuriteHidden" value="false">
	                  <TD class="severity<bean:write name="element" property="severiteDescription"/>"
	                    align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
					</logic:equal>
					<logic:equal name="confidentialiteSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="confidentialiteDescription"/></TD>
					</logic:equal>
					<logic:equal name="natureSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="natureDescription"/></TD>
					</logic:equal>
					<logic:equal name="typeSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="typeDescription"/></TD>
					</logic:equal>              
					<logic:equal name="categorieSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="categorieDescription"/></TD>
					</logic:equal>                 
					<logic:equal name="statutSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="statutDescription"/></TD>
					</logic:equal>				 
					<logic:equal name="dateDebutSecuriteHidden" value="false">
	                  <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateDebut"/></TD>
					</logic:equal>	
					<logic:equal name="dateFinSecuriteHidden" value="false">
	                  <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateFin"/></TD>
					</logic:equal>
					<logic:equal name="intervenantSecuriteHidden" value="false">
	                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="intervenant"/></TD>
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
	    		<cardex:NavigationResultatListe name="rechercheDossier" property="listeResultat" URLChangerPage="/societe/dossier/rechercheChangerPage.do" />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
