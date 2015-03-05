
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld' prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierLiaisonTrieListe" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="sourceProperty" id="sourceProperty" />

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheDossier' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left"><!-- Search Kind & nature list -->

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">
		<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	    <!-- Data table -->
		  <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader">&nbsp;</TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NUMERO_CARDEX%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_SEVERITE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_CONFIDENTIALITE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_NATURE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_TYPE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_CATEGORIE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_STATUT%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_DATE_DEBUT%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_DATE_FIN%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name='rechercheDossier' property="listeResultat" key='<%=DossierLiaisonTrieListe.CLE_INTERVENANT%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/dossier/rechercheTrier.do"%>'/>
			   </TD>
		    </TR>

                    <logic:iterate id='element' name='rechercheDossier' property='listeResultat.resultatAffichage' >
                           <TR>
                               <TD class="listDetailOdd" nowrap >
                                  <cardex:linkLiaisonDossier source='rechercheDossier' sourceProperty='<%=sourceProperty.toString()%>' dossier='element' 
                                  page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/link/role/show.do"%>'>
                                    <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                                  </cardex:linkLiaisonDossier>
                               </TD>
                               <TD class="listDetailOdd" nowrap>
                                  <cardex:linkDossier dossier='element' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
                                    <bean:write name="element" property="numeroCardexTexte"/>
                                  </cardex:linkDossier>
                               </TD>
                               <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroDossier"/></TD>
                               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
                                 align="center" ><bean:write name="element" property="severiteDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="confidentialiteDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="natureDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="typeDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="categorieDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="statutDescription"/></TD>
                               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateDebut"/></TD>
                               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateFin"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="intervenant"/></TD>
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
	    		<cardex:NavigationResultatListe name="rechercheDossier" property="listeResultat" 
	    			URLChangerPage='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheChangerPage.do"%>'/>
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

