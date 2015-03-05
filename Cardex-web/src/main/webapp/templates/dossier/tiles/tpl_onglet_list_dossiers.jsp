<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<!-- Récupération de l'entité à laquelle appartient l'utilisateur pour déterminer
     ses droits d'accès aux dossiers associés. -->
<!-- On récupère également le total des dossiers liés -->     
<%
   String sujetEntite = "";
   String sujetSite = "";
   String utilisateur = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetEntite = String.valueOf(sujetCardex.getEntite());
     sujetSite   = String.valueOf(sujetCardex.getSite());
     utilisateur = sujetCardex.getCode();
   }
   catch (Throwable e) {}

%>
<SCRIPT>
//-- Sert à réduire l'information pour l'impression complète sur une page
//-- Après l'impression, on remet l'affichage normal
function printPage(){
   var coll = document.all.namedItem("DATA_FOLDERS");
   if (coll!=null){
      coll.style.zoom = '95%';
      window.print();
      coll.style.zoom = '100%';
   }
}
</SCRIPT>
<!-- ------------------------------ -->
<DIV id="DATA_FOLDERS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><cardex:button securityConstraint='cardex.dossier.base.imprimer' labelKey='cb_imprimer'  onclick='printPage()' /></TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_CARDEX%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_ROLE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_SEVERITE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NATURE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_TYPE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CATEGORIE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_STATUT%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_DEBUT%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_FIN%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_INTERVENANT%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
    </TR>
    <logic:greaterThan name="sujet" property="listeDossiers.size" value="0">
    <TR>
       <TD colspan="12">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='sujet' property="listeDossiers.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" nowrap>
			<cardex:button labelKey="cb_lier" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/search/show.do"%>'/>
        </TD>
        <TD class="listDetailOdd" colspan="11">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="sujet" property='dossiers'>
            <TR>
                <TD class="listDetailOdd" nowrap>&nbsp; 
          <!-- On interdit toute action sur un dossier lié s'il ne s'agit pas de la même entité -->
                <logic:equal name='element' property='entite' value='<%= sujetEntite %>' >
          			<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
                    <logic:equal name='element' property='site' value='<%= sujetSite %>' >
                        <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/delete.do"%>'>
                            <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
                        </cardex:linkLiaisonDossier>
                    </logic:equal>
          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
          	        <logic:notEqual name='element' property='site' value='<%= sujetSite %>' >
          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
                        <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/delete.do"%>'>
      	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
      	                  </cardex:linkLiaisonDossier>
					   </logic:equal>
				    </logic:notEqual>
                </logic:equal>
      			<!-- On permet la suppression si le créateur du lien est l'utilisateur -->
      	        <logic:notEqual name='element' property='entite' value='<%= sujetEntite %>' >
      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
                    <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/delete.do"%>'>
  	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
  	                  </cardex:linkLiaisonDossier>
				   </logic:equal>
			    </logic:notEqual>
                </TD>
               <TD class="listDetailOdd" nowrap>
                    <cardex:linkDossier dossier='element' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
						<bean:write name="element" property="numeroCardexTexte"/>
                    </cardex:linkDossier>
               </TD>
               <TD class="listDetailOdd" ><bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
               <TD class="listDetailOdd"><bean:write name="element" property="roleDescription"/></TD>               
               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
                 align="center" ><bean:write name="element" property="severiteDescription"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="confidentialiteDescription"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="natureDescription"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="typeDescription"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="categorieDescription"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="statutDescription"/></TD>
               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateDebutLeft[10]"/></TD>
               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateFin10"/></TD>
               <TD class="listDetailOdd"><bean:write name="element" property="intervenant"/></TD>                
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_folders division -->
