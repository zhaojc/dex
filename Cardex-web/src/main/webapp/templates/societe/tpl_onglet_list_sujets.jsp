<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "societe".
Description : Module qui affiche le contenu de l'onglet "sujets", soit une
              liste de societe.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.11 $, $Date: 2002/04/04 20:40:15 $

History     : Voir ci-dessous.

$Revision: 1.11 $, $Date: 2002/04/04 20:40:15 $, $Author: mlibersan $
Création
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
     le droit de suppression dans l'onglet (permis seulement si le site est le même).
     On récupère également le nombre d'enregistrements liés.
-->
<%
   String sujetSite = "";
   String utilisateur = "";
   String token = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   	 utilisateur = sujetCardex.getCode();
     token = (String)request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
   }
   catch (Throwable e) {}

%>
<SCRIPT language='JavaScript' type='text/javascript'>

function printOngletSujet(){
	   var rapport = "<%= GlobalConstants.ChoixRapport.ONGLET_SUJET_SOCIETE %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doSauvegardeLienSujet(index,lien,lienSite) {
	var role = document.getElementById("sujets["+index+"].role");
	var cleSociete = document.forms(0).cle.value;
	var siteSociete = document.forms(0).site.value;
//alert(index + '- ' + cleDossier + '- ' + siteDossier + ' ' + role.value);
//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((role.value != "") && (role.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/societe/link/update.do?lien="%>'+lien+'&lienSite='+lienSite+'&role='+role.value+'&cleSociete='+cleSociete+'&siteSociete='+siteSociete+'&<%=Globals.TOKEN_KEY+"="+token%>');
	}else{
		alert("<bean:message key='choix.role.update'/>");
	}
	//document.forms(0).action='<%= request.getContextPath() + "/societe/link/update.do"%>';
	//document.forms(0).submit();
}
</SCRIPT>
 
<!-- ------------------------------ -->
<DIV id="DATA_INDIVIDUALS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
       <TD class="listTableHeader"><cardex:button securityConstraint='cardex.sujet.base.imprimer' labelKey='cb_imprimer'  onclick='printOngletSujet()' /></TD>
       <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="sujet" propertyFormulaire='numeroFiche' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NO_FICHE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteAutreSecurite" nomFormulaire="sujet" propertyFormulaire='severiteAutres' >		   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE_AUTRES%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="sujet" propertyFormulaire='severite' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="sujet" propertyFormulaire='severiteCasino' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="sujet" propertyFormulaire='nom' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NOM%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="prenomSecurite" nomFormulaire="sujet" propertyFormulaire='prenom' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_PRENOM%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="anneeNaissanceSecurite" nomFormulaire="sujet" propertyFormulaire='anneeNaissance' >
		   <TD class="listTableHeader" width="60">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ANNEE_NAISSANCE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="sexeSecurite" nomFormulaire="sujet" propertyFormulaire='sexe' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEXE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="ethnieSecurite" nomFormulaire="sujet" propertyFormulaire='ethnie' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ETHNIE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="raceSecurite" nomFormulaire="sujet" propertyFormulaire='race' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_RACE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="langueSecurite" nomFormulaire="sujet" propertyFormulaire='langue' >		   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_LANGUE%>' URLTrier="/societe/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="roleSecurite" nomFormulaire="sujet" propertyFormulaire='role' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ROLE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="sujet" propertyFormulaire='lienCreateur' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/societe/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="sujet" propertyFormulaire='lienDateCreation' >
		   <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/societe/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="dateFinEnqueteSecurite" nomFormulaire="sujet" propertyFormulaire='dateFinEnquete' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_DATE_FIN_ENQUETE%>' URLTrier="/societe/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="societe" property="listeSujets.size" value="0">
    <TR>
       <TD colspan="17">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='societe' property="listeSujets.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>

    <TR>
        <TD class="listDetailOdd" colspan="17" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/societe/sujet/search/show.do"/>
        </TD>
    </TR>
    <logic:iterate id="element" name="societe" property='sujets' indexId="index">
        <logic:equal name='element' property='audit' value='true' > 
           <TR style="background-color='#EEEEEE'">
        </logic:equal>
        <logic:notEqual name='element' property='audit' value='true' > 
           <TR>
        </logic:notEqual>
           <TD class="listDetailOdd" nowrap>&nbsp;
		      <!-- On interdit la suppression du lien s'il s'agit d'un détaillant RDD -->
              <logic:equal name='element' property='permettreSuppressionLiaison' value='true' >
				<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
				<logic:equal name='societe' property='site' value='<%= sujetSite %>' >
	    	           <logic:notEqual name='element' property='audit' value='true' > 
				               	<cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='societe' sujet='element' page='/societe/sujet/delete.do'>
				                   	<html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
				               	</cardex:linkLiaisonSujet>
				        </logic:notEqual>
				</logic:equal>
	   			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
	   	        <logic:notEqual name='societe' property='site' value='<%= sujetSite %>' >
	     	         <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
	     	             <logic:notEqual name='element' property='audit' value='true' > 
			               	<cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='societe' sujet='element' page='/societe/sujet/delete.do'>
							  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
					      </cardex:linkLiaisonSujet>
						</logic:notEqual>
			 		 </logic:equal>
				 </logic:notEqual>
			  </logic:equal>
           </TD>
		<logic:equal name="numeroFicheSecuriteHidden" value="false">
           <TD class="listDetailOdd" nowrap>&nbsp;
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
               align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/>&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="severiteCasinoSecuriteHidden" value="false">
           <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
               align="center" >&nbsp;<bean:write name="element" property="severiteCasinoDescription"/>&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="nomSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='nom' />&nbsp;</TD>
	   </logic:equal>
	   <logic:equal name="prenomSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='prenom' />&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="anneeNaissanceSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='anneeNaissance' />&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="sexeSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='sexeDescription' />&nbsp;</TD>
       </logic:equal>
   	   <logic:equal name="ethnieSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='ethnieDescription' />&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="raceSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='raceDescription' />&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="langueSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='langueDescription' />&nbsp;</TD>
       </logic:equal>
       <logic:equal name="roleSecuriteHidden" value="false">
         <TD class="listDetailOdd" nowrap>&nbsp;
         <logic:notEqual name='element' property='audit' value='true' >
         		<logic:equal name='element' property='permettreSuppressionLiaison' value='true' >
	               <bean:define id="sujetLien" name='societe' property='<%="sujets["+index+"].lien"%>'/>
		           <bean:define id="sujetLienSite" name='societe' property='<%="sujets["+index+"].lienSite"%>' type="String"/>
		           <myriap:select id='<%="sujets["+index+"].role"%>' name='element' property='role' style='HEIGHT: 20px; WIDTH: 120px'>
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=sujetLienSite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
		           </myriap:select>
		           &nbsp;
				   <cardex:securityDefineTag nameDefine="societeSujetSauvegarder" urlSecurite="/societe/link/update.do">
						<IMG onclick="doSauvegardeLienSujet(<%= index %>,<%=sujetLien%>,<%=sujetLienSite%>);" src="<%=request.getContextPath()%>/images/sauvegarde.gif" altKey="cb_ok" border="1" height="14" width="14" />
				   </cardex:securityDefineTag>
			   </logic:equal>
	           <logic:equal name='element' property='permettreSuppressionLiaison' value='false' >
	          		<cardex:afficherValeurListeTag name='societe' property='<%="sujets["+index+"].role"%>' classe='<%= GlobalConstants.CleListe.ROLE %>' />
	   		   </logic:equal>
   		 </logic:notEqual>			
	     </TD>
       </logic:equal>
	   <logic:equal name="lienCreateurSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="lienCreateur"/>&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="lienDateCreationSecuriteHidden" value="false">
           <TD class="listDetailOdd" align="center">&nbsp;<bean:write name='element' property='lienDateCreation' /></TD>
       </logic:equal>
	   <logic:equal name="dateFinEnqueteSecuriteHidden" value="false">
           <TD class="listDetailOdd" align="center"><bean:write name="element" property="dateFinEnquete"/>&nbsp;</TD>
       </logic:equal>
       </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_individuals division -->
