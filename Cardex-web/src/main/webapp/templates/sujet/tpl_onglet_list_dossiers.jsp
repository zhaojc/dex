<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "dossiers".
Description : Module qui affiche le contenu de l'onglet "dossiers", soit une
              liste de dossiers.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/04 20:40:19 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/04 20:40:19 $, $Author: mlibersan $
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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération de l'entité à laquelle appartient l'utilisateur pour déterminer
     ses droits d'accès aux dossiers associés. -->
<!-- On récupère également le total des dossiers liés -->     
<%
   String sujetEntite = "";
   String sujetSite = "";
   String utilisateur = "";
   String loto_quebec = GlobalConstants.Entite.LOTO_QUEBEC;
   String token = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetEntite = String.valueOf(sujetCardex.getEntite());
     sujetSite   = String.valueOf(sujetCardex.getSite());
     utilisateur = sujetCardex.getCode();
     token = (String)request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
   }
   catch (Throwable e) {}

%>
<SCRIPT>
function printOngletDossier(){
	   var rapport = "<%= GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_SUJET %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doSauvegardeLienDossier(index,lien,lienSite) {
	var role = document.getElementById("dossiers["+index+"].role");
	var cleSujet = document.forms(0).cle.value;
	var siteSujet = document.forms(0).site.value;
//alert(index + '- ' + cleDossier + '- ' + siteDossier + ' ' + role.value);
//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((role.value != "") && (role.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/sujet/link/update.do?lien="%>'+lien+'&lienSite='+lienSite+'&role='+role.value+'&cleSujet='+cleSujet+'&siteSujet='+siteSujet+'&<%=Globals.TOKEN_KEY+"="+token%>');
	}
	//document.forms(0).action='<%= request.getContextPath() + "/sujet/link/update.do"%>';
	//document.forms(0).submit();
}

</SCRIPT>
<!-- ------------------------------ -->
<DIV id="DATA_FOLDERS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;<cardex:button securityConstraint='cardex.dossier.base.imprimer' labelKey='cb_imprimer'  onclick='printOngletDossier()' /></TD>
        
        <cardex:securityDefineTag nameDefine="numeroCardexSecurite" nomFormulaire="dossier" propertyFormulaire='numeroCardex' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="numeroDossierSecurite" nomFormulaire="dossier" propertyFormulaire='numeroDossier' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NUMERO_DOSSIER%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="roleSecurite" nomFormulaire="dossier" propertyFormulaire='role' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_ROLE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="dossier" propertyFormulaire='severite' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_SEVERITE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="confidentialiteSecurite" nomFormulaire="dossier" propertyFormulaire='confidentialite' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="natureSecurite" nomFormulaire="dossier" propertyFormulaire='nature' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_NATURE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="typeSecurite" nomFormulaire="dossier" propertyFormulaire='type' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_TYPE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="categorieSecurite" nomFormulaire="dossier" propertyFormulaire='categorie' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_CATEGORIE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="statutSecurite" nomFormulaire="dossier" propertyFormulaire='statut' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_STATUT%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="fondeSecurite" nomFormulaire="dossier" propertyFormulaire='fonde' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_FONDE%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>	   
	   <cardex:securityDefineTag nameDefine="dateDebutSecurite" nomFormulaire="dossier" propertyFormulaire='dateDebut' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_DEBUT%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="dateFinSecurite" nomFormulaire="dossier" propertyFormulaire='dateFin' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_FIN%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="intervenantSecurite" nomFormulaire="dossier" propertyFormulaire='intervenant' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="dossier" propertyFormulaire='lienCreateur' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name='sujet' property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/sujet/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="dossier" propertyFormulaire='lienDateCreation' >
		   <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="sujet" property="listeDossiers" key='<%=DossierOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/sujet/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="sujet" property="listeDossiers.size" value="0">
    <TR>
       <TD colspan="16">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='sujet' property="listeDossiers.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>

    <TR>
        <TD class="listDetailOdd" nowrap align="center">
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/sujet/dossier/search/show.do"/>
			<!-- L'ajout du journal n'est permis qu'aux enquêteurs de Loto-Québec -->
			<% if (sujetEntite.equals(loto_quebec)) { %>
				&nbsp;
				<cardex:button urlSecurite="/sujet/journal/create.do" labelKey="journal" windowOpenLocation="/sujet/journal/create.do"/>
			<% } %>
			
        </TD>
        <TD class="listDetailOdd" colspan="16">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="sujet" property='dossiers' indexId="index">
            <TR>
                <TD class="listDetailOdd" nowrap align="center">&nbsp; 
          <!-- On interdit toute action sur un dossier lié s'il ne s'agit pas de la même entité -->
                <logic:equal name='sujet' property='entite' value='<%= sujetEntite %>' >
          			<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
                    <logic:equal name='sujet' property='site' value='<%= sujetSite %>' >
                        <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='/sujet/dossier/delete.do'>
                            <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
                        </cardex:linkLiaisonDossier>
                    </logic:equal>
          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
          	        <logic:notEqual name='sujet' property='site' value='<%= sujetSite %>' >
          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
                        <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='/sujet/dossier/delete.do'>
      	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
      	                  </cardex:linkLiaisonDossier>
					   </logic:equal>
				    </logic:notEqual>
                </logic:equal>
      			<!-- On permet la suppression si le créateur du lien est l'utilisateur -->
      	        <logic:notEqual name='sujet' property='entite' value='<%= sujetEntite %>' >
      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
                    <cardex:linkLiaisonDossier onclick="return doConfirmLinkSuppression();" source='sujet' dossier='element' page='/sujet/dossier/delete.do'>
  	                     <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
  	                  </cardex:linkLiaisonDossier>
				   </logic:equal>
			    </logic:notEqual>
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
	          <TD class="listDetailOdd" nowrap>
                    <bean:define id="sujetLien" name='sujet' property='<%="dossiers["+index+"].lien"%>'/>
                  	<bean:define id="sujetLienSite" name='sujet' property='<%="dossiers["+index+"].lienSite"%>' type="String"/>
		           <myriap:select id='<%="dossiers["+index+"].role"%>' name='element' property='role' style='HEIGHT: 20px; WIDTH: 120px'>
				   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=sujetLienSite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
		           </myriap:select>
		           &nbsp;
					<cardex:securityDefineTag nameDefine="sujetDossierSauvegarder" urlSecurite="/sujet/link/update.do">
						<IMG onclick="doSauvegardeLienDossier(<%= index %>,<%=sujetLien%>,<%=sujetLienSite%>);" src="<%=request.getContextPath()%>/images/sauvegarde.gif" altKey="cb_ok" border="1" height="14" width="14" />
					</cardex:securityDefineTag>
			  </TD>
         </logic:equal>
		 <logic:equal name="severiteSecuriteHidden" value="false">
               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
                 align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
		 </logic:equal>
         <logic:equal name="confidentialiteSecuriteHidden" value="false">
               <TD class="listDetailOdd" align="center" ><bean:write name="element" property="confidentialiteDescription"/></TD>
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
               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateFin10"/>&nbsp;</TD>
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
