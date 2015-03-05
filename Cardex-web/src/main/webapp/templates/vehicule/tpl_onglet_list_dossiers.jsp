<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "dossiers".
Description : Module qui affiche le contenu de l'onglet "dossiers", soit une
              liste de dossiers.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/16 14:32:55 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/04/16 14:32:55 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération de l'entité à laquelle appartient l'utilisateur pour déterminer
     ses droits d'accès aux dossiers associés. -->
<!-- On récupère également le total des dossiers liés -->     
<%
         String sujetEntite = "";
         String sujetSite = "";
         String utilisateur = "";
         String token = "";
         try
         {
             AuthenticationSubject sujet = (AuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
             CardexUser sujetCardex = (CardexUser) sujet.getUser();
             sujetEntite = String.valueOf(sujetCardex.getEntite());
             sujetSite = String.valueOf(sujetCardex.getSite());
             utilisateur = sujetCardex.getCode();
             token = (String) request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
         }
         catch (Throwable e)
         {
         }
     %>

<SCRIPT language='JavaScript' type='text/javascript'>
function doSauvegardeLienDossier(index,lien,lienSite) {
	var role = document.getElementById("dossiers["+index+"].role");
	var cleVehicule = document.forms(0).cle.value;
	var siteVehicule = document.forms(0).site.value;
//alert(index + '- ' + cleDossier + '- ' + siteDossier + ' ' + role.value);
//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((role.value != "") && (role.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%=request.getContextPath() + "/vehicule/link/update.do?lien="%>'+lien+'&lienSite='+lienSite+'&role='+role.value+'&cleVehicule='+cleVehicule+'&siteVehicule='+siteVehicule+'&<%=Globals.TOKEN_KEY + "=" + token%>');
	}
	//document.forms(0).action='<%=request.getContextPath() + "/vehicule/link/update.do"%>';
	//document.forms(0).submit();
}
</SCRIPT>

<!-- ------------------------------ -->
<DIV id="DATA_FOLDERS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
	    <cardex:securityDefineTag nameDefine="numeroCardexSecurite" nomFormulaire="dossier" propertyFormulaire='numeroCardex' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="numeroDossierSecurite" nomFormulaire="dossier" propertyFormulaire='numeroDossier' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="roleSecurite" nomFormulaire="dossier" propertyFormulaire='role' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_ROLE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="dossier" propertyFormulaire='severite' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_SEVERITE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="confidentialiteSecurite" nomFormulaire="dossier" propertyFormulaire='confidentialite' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="natureSecurite" nomFormulaire="dossier" propertyFormulaire='nature' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NATURE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="typeSecurite" nomFormulaire="dossier" propertyFormulaire='type' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_TYPE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="categorieSecurite" nomFormulaire="dossier" propertyFormulaire='categorie' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CATEGORIE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="dossier" propertyFormulaire='statut' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_STATUT%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="fondeSecurite" nomFormulaire="dossier" propertyFormulaire='fonde' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_FONDE%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>	   
	   <cardex:securityDefineTag nameDefine="dateDebutSecurite" nomFormulaire="dossier" propertyFormulaire='dateDebut' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_DEBUT%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="dateFinSecurite" nomFormulaire="dossier" propertyFormulaire='dateFin' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_FIN%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="intervenantSecurite" nomFormulaire="dossier" propertyFormulaire='intervenant' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="dossier" propertyFormulaire='lienCreateur' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='vehicule' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/vehicule/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="dossier" propertyFormulaire='lienDateCreation' >
		    <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="vehicule" property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/vehicule/trier.do" />			   
		    </TD>
 	   </cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="vehicule" property="listeDossiers.size" value="0">
	    <TR>
	       <TD colspan="16">
	   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='vehicule' property="listeDossiers.size"/></b>
	       </TD>
	    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="16" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/vehicule/dossier/search/show.do"/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="vehicule" property='dossiers' indexId="index">
    <TR>
                <TD class="listDetailOdd" nowrap>
			<logic:equal name='vehicule' property='entite' value='<%= sujetEntite %>' >
			    <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
			    <logic:equal name='vehicule' property='site' value='<%= sujetSite %>' >
				    <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='vehicule' dossier='element' page='/vehicule/dossier/delete.do'>
					<html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
				    </cardex:linkLiaisonDossier>
			    </logic:equal>
      			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
      	        <logic:notEqual name='vehicule' property='site' value='<%= sujetSite %>' >
      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
				    <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='vehicule' dossier='element' page='/vehicule/dossier/delete.do'>
  	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
  	                  </cardex:linkLiaisonDossier>
				   </logic:equal>
			    </logic:notEqual>
			</logic:equal>
      			<!-- On permet la suppression si le créateur du lien est l'utilisateur -->
      	        <logic:notEqual name='vehicule' property='entite' value='<%= sujetEntite %>' >
      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
				    <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='vehicule' dossier='element' page='/vehicule/dossier/delete.do'>
  	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
  	                  </cardex:linkLiaisonDossier>
				   </logic:equal>
			    </logic:notEqual>&nbsp;
                </TD>
         <logic:equal name="numeroCardexSecuriteHidden" value="false">
               <TD class="listDetailOdd" nowrap>
				    <cardex:linkDossier dossier='element' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
						<bean:write name="element" property="numeroCardexTexte"/>
				    </cardex:linkDossier>
               </TD>
         </logic:equal>
         <logic:equal name="numeroDossierSecuriteHidden" value="false">               
               <TD class="listDetailOdd" ><bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
		 </logic:equal>
			<logic:equal name="roleSecuriteHidden" value="false">
				<TD class="listDetailOdd" nowrap><bean:define id="vehiculeLien"
					name='vehicule' property='<%="dossiers["+index+"].lien"%>' /> <bean:define
					id="vehiculeLienSite" name='vehicule'
					property='<%="dossiers["+index+"].lienSite"%>' type="String" /> <myriap:select
					id='<%="dossiers["+index+"].role"%>' name='element' property='role'
					style='HEIGHT: 20px; WIDTH: 120px'>
					<cardex:optionTag
						classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
						valeurDiscriminant='<%=vehiculeLienSite %>'
						valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>' />
				</myriap:select> &nbsp; <cardex:securityDefineTag
					nameDefine="vehiculeDossierSauvegarder"
					urlSecurite="/vehicule/link/update.do">
					<IMG
						onclick="doSauvegardeLienDossier(<%=index%>,<%=vehiculeLien%>,<%=vehiculeLienSite%>);"
						src="<%=request.getContextPath()%>/images/sauvegarde.gif"
						altKey="cb_ok" border="1" height="14" width="14" />
				</cardex:securityDefineTag></TD>
			</logic:equal>
			<logic:equal name="severiteSecuriteHidden" value="false">
               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
                 align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
		 </logic:equal>
         <logic:equal name="confidentialiteSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="confidentialiteDescription"/></TD>
		 </logic:equal>
	     <logic:equal name="natureSecuriteHidden" value="false">               
               <TD class="listDetailOdd"><bean:write name="element" property="natureDescription"/></TD>
		 </logic:equal>
         <logic:equal name="typeSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="typeDescription"/></TD>
	     </logic:equal>              
		 <logic:equal name="categorieSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="categorieDescription"/></TD>
		 </logic:equal>                 
		 <logic:equal name="statutSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="statutDescription"/></TD>
         </logic:equal>
		 <logic:equal name="fondeSecuriteHidden" value="false">               
              <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="fondeDescription"/></TD>
		 </logic:equal>         
		 <logic:equal name="dateDebutSecuriteHidden" value="false">
               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateDebutLeft[10]"/></TD>
         </logic:equal>
         <logic:equal name="dateFinSecuriteHidden" value="false">
               <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateFin10"/></TD>
         </logic:equal>
         <logic:equal name="intervenantSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="intervenant"/></TD>
		 </logic:equal>
		 <logic:equal name="lienCreateurSecuriteHidden" value="false">
               <TD class="listDetailOdd"><bean:write name="element" property="lienCreateur"/></TD>
         </logic:equal>
	   <logic:equal name="lienDateCreationSecuriteHidden" value="false">
           <TD class="listDetailOdd" align="center"><bean:write name='element' property='lienDateCreation' /></TD>
       </logic:equal>
    </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_folders division -->
