<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "sujets".
Description : Module qui affiche le contenu de l'onglet "sujets", soit une
              liste de sujets.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/16 14:32:55 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/04/16 14:32:55 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   String utilisateur = "";
   String token = "";
   try{
	   AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
   	   CardexUser sujetCardex = (CardexUser)sujet.getUser();
   	   utilisateur = sujetCardex.getCode();
       sujetSite   = String.valueOf(sujetCardex.getSite());
       token = (String)request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
   }
   catch (Throwable e) {}

%>

<SCRIPT language='JavaScript' type='text/javascript'>
function doSauvegardeLienSujet(index,lien,lienSite) {
	var role = document.getElementById("sujets["+index+"].role");
	var cleVehicule = document.forms(0).cle.value;
	var siteVehicule = document.forms(0).site.value;
//alert(index + '- ' + cleDossier + '- ' + siteDossier + ' ' + role.value);
//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((role.value != "") && (role.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/vehicule/link/update.do?lien="%>'+lien+'&lienSite='+lienSite+'&role='+role.value+'&cleVehicule='+cleVehicule+'&siteVehicule='+siteVehicule+'&<%=Globals.TOKEN_KEY+"="+token%>');
	}else{
		alert("<bean:message key='choix.role.update'/>");
	}
	//document.forms(0).action='<%= request.getContextPath() + "/vehicule/link/update.do"%>';
	//document.forms(0).submit();
}
</SCRIPT>


<!-- ------------------------------ -->
<DIV id="DATA_INDIVIDUALS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
		<cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="sujet" propertyFormulaire='numeroFiche' >        
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NO_FICHE%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteAutresSecurite" nomFormulaire="sujet" propertyFormulaire='severiteAutres' >	   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE_AUTRES%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="sujet" propertyFormulaire='severite' >	   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="sujet" propertyFormulaire='severiteCasino' >	   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="sujet" propertyFormulaire='nom' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NOM%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="prenomSecurite" nomFormulaire="sujet" propertyFormulaire='prenom' >		   
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_PRENOM%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="sexeSecurite" nomFormulaire="sujet" propertyFormulaire='sexe' >		   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEXE%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="ethnieSecurite" nomFormulaire="sujet" propertyFormulaire='ethnie' >		   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ETHNIE%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="raceSecurite" nomFormulaire="sujet" propertyFormulaire='race' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_RACE%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="langueSecurite" nomFormulaire="sujet" propertyFormulaire='langue' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_LANGUE%>' URLTrier="/vehicule/trier.do"/>			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="roleSecurite" nomFormulaire="sujet" propertyFormulaire='role' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ROLE%>' URLTrier="/vehicule/trier.do"/>			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="sujet" propertyFormulaire='lienCreateur' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/vehicule/trier.do"/>
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="sujet" propertyFormulaire='lienDateCreation' >
		    <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="vehicule" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/vehicule/trier.do" />			   
		    </TD>
 	   </cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="vehicule" property="listeSujets.size" value="0">
    <TR>
       <TD colspan="14">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='vehicule' property="listeSujets.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="12" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/vehicule/sujet/search/show.do"/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="vehicule" property='sujets' indexId="index">
       <TR>
           <TD class="listDetailOdd" nowrap>
			   <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
			   <logic:equal name='vehicule' property='site' value='<%= sujetSite %>' >
			                   <cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='vehicule' sujet='element' page='/vehicule/sujet/delete.do'>
			                      <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
			                   </cardex:linkLiaisonSujet>
			               </logic:equal>
			     			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
			     	        <logic:notEqual name='vehicule' property='site' value='<%= sujetSite %>' >
			     	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
			                   <cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='vehicule' sujet='element' page='/vehicule/sujet/delete.do'>
				  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
			      </cardex:linkLiaisonSujet>
			   </logic:equal>
			   </logic:notEqual>
           </TD>
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
           <TD class="listDetailOdd"><bean:write name='element' property='nom' /></TD>
	   </logic:equal>
	   <logic:equal name="prenomSecuriteHidden" value="false">           
           <TD class="listDetailOdd"><bean:write name='element' property='prenom' /></TD>
       </logic:equal>
	   <logic:equal name="sexeSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='sexeDescription' /></TD>
       </logic:equal>
   	   <logic:equal name="ethnieSecuriteHidden" value="false">           
           <TD class="listDetailOdd"><bean:write name='element' property='ethnieDescription' /></TD>
       </logic:equal>
	   <logic:equal name="raceSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='raceDescription' /></TD>
       </logic:equal>
	   <logic:equal name="langueSecuriteHidden" value="false">           
           <TD class="listDetailOdd"><bean:write name='element' property='langueDescription' /></TD>
       </logic:equal>
	   <logic:equal name="roleSecuriteHidden" value="false">	          
	          <TD class="listDetailOdd">
	               <bean:define id="sujetLien" name='vehicule' property='<%="sujets["+index+"].lien"%>'/>
		           <bean:define id="sujetLienSite" name='vehicule' property='<%="sujets["+index+"].lienSite"%>' type="String"/>
		           <myriap:select id='<%="sujets["+index+"].role"%>' name='element' property='role' style='HEIGHT: 20px; WIDTH: 120px'>
          	  		   <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=sujetLienSite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
		           </myriap:select>
		           &nbsp;
					<cardex:securityDefineTag nameDefine="vehiculeSujetSauvegarder" urlSecurite="/vehicule/link/update.do">
						<IMG onclick="doSauvegardeLienSujet(<%= index %>,<%=sujetLien%>,<%=sujetLienSite%>);" src="<%=request.getContextPath()%>/images/sauvegarde.gif" altKey="cb_ok" border="1" height="14" width="14" />
					</cardex:securityDefineTag>
			  </TD>
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
<!-- End data_individuals division -->
