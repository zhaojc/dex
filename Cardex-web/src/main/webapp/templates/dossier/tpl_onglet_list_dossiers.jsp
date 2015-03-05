<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "dossiers".
Description : Module qui affiche le contenu de l'onglet "dossiers", soit une
              liste de dossiers.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/24 17:38:44 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/04/24 17:38:44 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe" %>

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
function printOngletDossier(){
	   var rapport = "<%= GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_DOSSIER %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}
</SCRIPT>
<!-- ------------------------------ -->
<DIV id="DATA_FOLDERS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
       <TD class="listTableHeader"><cardex:button securityConstraint='cardex.dossier.base.imprimer' labelKey='cb_imprimer'  onclick='printOngletDossier()' style="width='45';" /></TD>
	   <cardex:securityDefineTag nameDefine="numeroCardexSecurite" nomFormulaire="dossier" propertyFormulaire='numeroCardex' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="numeroDossierSecurite" nomFormulaire="dossier" propertyFormulaire='numeroDossier' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="dossier" propertyFormulaire='severite' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_SEVERITE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="confidentialiteSecurite" nomFormulaire="dossier" propertyFormulaire='confidentialite' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="natureSecurite" nomFormulaire="dossier" propertyFormulaire='nature' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NATURE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="typeSecurite" nomFormulaire="dossier" propertyFormulaire='type' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_TYPE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="categorieSecurite" nomFormulaire="dossier" propertyFormulaire='categorie' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CATEGORIE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="dossier" propertyFormulaire='statut' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_STATUT%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="fondeSecurite" nomFormulaire="dossier" propertyFormulaire='fonde' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_FONDE%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="dateDebutSecurite" nomFormulaire="dossier" propertyFormulaire='dateDebut' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_DEBUT%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="dateFinSecurite" nomFormulaire="dossier" propertyFormulaire='dateFin' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_FIN%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="intervenantSecurite" nomFormulaire="dossier" propertyFormulaire='intervenant' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="dossier" propertyFormulaire='lienCreateur' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='dossier' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/dossier/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="dossier" propertyFormulaire='lienDateCreation' >
		   <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="dossier" property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/dossier/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
    </TR>
   <logic:greaterThan name="dossier" property="listeDossiers.size" value="0">
    <TR>
       <TD colspan="14">&nbsp;
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='dossier' property="listeDossiers.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
      <TR>
        <TD class="listDetailOdd" nowrap>&nbsp;
              <cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/dossier/dossier/search/show.do"/>
        </TD>
        <TD class="listDetailOdd" colspan="14">&nbsp;</TD>
      </TR>
	</logic:notEqual>    
    <logic:iterate id="element" name="dossier" property='dossiers'>
        <TR>
                <TD class="listDetailOdd" nowrap>&nbsp;
	            <!-- On interdit le visionnement du dossier s'il ne s'agit pas de la même entité -->
                <logic:equal name='dossier' property='entite' value='<%= sujetEntite %>' >
                    <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
	          		    <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
          	            <logic:equal name='dossier' property='site' value='<%= sujetSite %>' >
          	                <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='dossier' dossier='element' page='/dossier/dossier/delete.do'>
          	                    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
          	                </cardex:linkLiaisonDossier>
	                    </logic:equal>
	          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
	          	        <logic:notEqual name='dossier' property='site' value='<%= sujetSite %>' >
	          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
          	                  <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='dossier' dossier='element' page='/dossier/dossier/delete.do'>
          	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
          	                  </cardex:linkLiaisonDossier>
						   </logic:equal>
					    </logic:notEqual>
                    </logic:notEqual>
                </logic:equal>
				<!-- On permet cependant la suppression du lien s'il a été créé par l'utilisateur -->
                <logic:notEqual name='dossier' property='entite' value='<%= sujetEntite %>' >
                    <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
	          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
          	                  <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='dossier' dossier='element' page='/dossier/dossier/delete.do'>
          	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
          	                  </cardex:linkLiaisonDossier>
						   </logic:equal>
                    </logic:notEqual>
                </logic:notEqual>
                </TD>
			<logic:equal name="numeroCardexSecuriteHidden" value="false">                
               <TD class="listDetailOdd" nowrap>&nbsp;
                    <cardex:linkDossier dossier='element' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
                        <bean:write name="element" property="numeroCardexTexte"/>
                    </cardex:linkDossier>
               </TD>
           </logic:equal>
           <logic:equal name="numeroDossierSecuriteHidden" value="false">
               <TD class="listDetailOdd" >&nbsp;<bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
			 </logic:equal>                 
			 <logic:equal name="statutSecuriteHidden" value="false">
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
			 <logic:equal name="fondeSecuriteHidden" value="false">               
               <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="fondeDescription"/></TD>
			 </logic:equal>
			 <logic:equal name="dateDebutSecuriteHidden" value="false">               
               <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateDebutLeft[16]"/></TD>
			 </logic:equal>	
			 <logic:equal name="dateFinSecuriteHidden" value="false">               
               <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateFin10"/></TD>
			 </logic:equal>
			 <logic:equal name="intervenantSecuriteHidden" value="false">
               <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="intervenant"/></TD>
			 </logic:equal>
			 <logic:equal name="lienCreateurSecuriteHidden" value="false">
               <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="lienCreateur"/></TD>
			 </logic:equal>
			 <logic:equal name="lienDateCreationSecuriteHidden" value="false">
		       <TD class="listDetailOdd" align="center"><bean:write name='element' property='lienDateCreation' /></TD>
	         </logic:equal>
       </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_folders division -->
