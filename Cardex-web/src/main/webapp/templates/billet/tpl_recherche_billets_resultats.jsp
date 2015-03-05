
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.BilletDossierTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<SCRIPT>
	function doSoumettreRafraichir() {
		soumettreActionMethod('rafraichirRecherche');
	}
</SCRIPT>

<br>
<!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheBillets' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->

<logic:greaterThan name='rechercheBillets' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
	<TABLE align="left" width="770" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="100%">

	    <!-- Data table -->
           <TABLE align="left" width="100%" cellPadding="2" cellSpacing="0" border="1" >
                   <TR>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_TYPE%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_CATEGORIE%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_STATUT%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_DATE_DEBUT%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_DATE_FIN%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_INTERVENANT%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>                    
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_NOM_BILLET%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader" nowrap>
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_NUMERO_CONTROLE%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader" nowrap>
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_MONTANT_LOT%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader">
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_DATE_PAIEMENT%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
					   <TD class="listTableHeader" nowrap>
					   		<cardex:EnteteListeTriable name='rechercheBillets' property="listeResultat" key='<%=BilletDossierTrieListe.CLE_DATE_CREATION%>' URLTrier="/billet/rechercheTrier.do"/>
					   </TD>
                   </TR>

       		     <logic:iterate id='element' name='rechercheBillets' property='listeResultat.resultatAffichage' >
   	     		   <bean:define id="dossier" name="element" property="dossierForm"/>
                   <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                     <TD class="listDetailOdd">
	          		    <cardex:linkDossier dossier='dossier' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
	                      <bean:write name="element" property="dossierForm.numeroCardexTexte"/>
	                    </cardex:linkDossier>
	                 </TD>
	                 <TD class="listDetailOdd"><bean:write name="element" property="dossierForm.typeDescription"/></TD>
	                 <TD class="listDetailOdd"><bean:write name="element" property="dossierForm.categorieDescription"/></TD>
	                 <TD class="listDetailOdd"><bean:write name="element" property="dossierForm.statutDescription"/></TD>
	                 <TD class="listDetailOdd" nowrap><bean:write name="element" property="dossierForm.dateDebut16"/></TD>
	                 <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dossierForm.dateFin10"/></TD>
	                 <TD class="listDetailOdd"><bean:write name="element" property="dossierForm.intervenant"/></TD>
	                 <TD class="listDetailOdd" ><bean:write name="element" property="nom"/>&nbsp;</TD>
	                 <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="numeroControl"/></TD>
	                 <TD class="listDetailOdd" align="center">&nbsp;<bean:write name="element" property="montantLot"/>&nbsp;$</TD>
	                 <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="datePaiement"/></TD>
	                 <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="dateCreation"/></TD>
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
		<cardex:NavigationResultatListe name="rechercheBillets" property="listeResultat" URLChangerPage="/billet/rechercheChangerPage.do" />
   </TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>