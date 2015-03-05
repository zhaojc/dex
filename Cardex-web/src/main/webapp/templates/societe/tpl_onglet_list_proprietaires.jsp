<%-- --------------------------------------------------------------------------
Use case    : Liste des propriétaires. La liste est construite à partir des sujets associés
			  avec un rôle "propriétaire" de la société. L'onglet permet de suivre
			  l'historique des propriétaires dans le temps.
Description : Module qui affiche le contenu de l'onglet "sujets" avec un rôle "propriétaire".
Author(s)   : $Author: guerinf
Revision    : $Revision: 1.11 $, $Date: 2009/11/20 20:40:15 $

History     : Voir ci-dessous.

$Revision: 1.11 $, $Date: 2002/04/04 20:40:15 $, $Author: mlibersan $
Création
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même).
     On récupère également le nombre d'enregistrements liés.
-->
<%
   String sujetSite = "";
   String utilisateur = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   	 utilisateur = sujetCardex.getCode();
   }
   catch (Throwable e) {}

%>
<SCRIPT>
//-- Sert à réduire l'information pour l'impression complète sur une page
//-- Après l'impression, on remet l'affichage normal
function printPage(){
   var coll = document.all.namedItem("DATA_PROPRIETAIRES");
   if (coll!=null){
      coll.style.zoom = '90%';
      window.print();
      coll.style.zoom = '100%';
   }
}
</SCRIPT>

<!-- ------------------------------ -->
<DIV id="DATA_PROPRIETAIRES">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
       <TD class="listTableHeader">&nbsp;<!-- cardex:button securityConstraint='cardex.fenetres.proprietaires.imprimer' labelKey='cb_imprimer'  onclick='printPage()' / --></TD>
      <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="sujet" propertyFormulaire='numeroFiche' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_NO_FICHE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="sujet" propertyFormulaire='severite' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_SEVERITE%>' URLTrier="/societe/trier.do" />
		   </TD>
   	  </cardex:securityDefineTag>
      <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="sujet" propertyFormulaire='nom' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_NOM%>' URLTrier="/societe/trier.do" />
		   </TD>
   	  </cardex:securityDefineTag>
      <cardex:securityDefineTag nameDefine="prenomSecurite" nomFormulaire="sujet" propertyFormulaire='prenom' >
		   <TD class="listTableHeader">
		   		<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_PRENOM%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="sexeSecurite" nomFormulaire="sujet" propertyFormulaire='sexe' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_SEXE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="ethnieSecurite" nomFormulaire="sujet" propertyFormulaire='ethnie' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_ETHNIE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="raceSecurite" nomFormulaire="sujet" propertyFormulaire='race' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_RACE%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="langueSecurite" nomFormulaire="sujet" propertyFormulaire='langue' >
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_LANGUE%>' URLTrier="/societe/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="sujet" propertyFormulaire='lienDateCreation' >		   
		   <TD class="listTableHeader">
				<cardex:EnteteListeTriable name="societe" property="listeProprietaires" key='<%=SujetOngletTrieListe.CLE_DATE_CREATION%>' URLTrier="/societe/trier.do" />			   
		   </TD> 
		</cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="societe" property="listeProprietaires.size" value="0">
    <TR>
       <TD colspan="11">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='societe' property="listeProprietaires.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <logic:iterate id="element" name="societe" property='proprietaires'>
     <TR>
     	<TD>&nbsp;</TD>
       <logic:equal name="numeroFicheSecuriteHidden" value="false">
         <TD class="listDetailOdd" nowrap>
             <cardex:linkSujet sujet='element' page='/sujet/showAcces.do'>
                 <bean:write name="element" property="numeroFiche"/>
             </cardex:linkSujet>
         </TD>
	   </logic:equal>
	   <logic:equal name="severiteSecuriteHidden" value="false">	
        <TD class="severity<bean:write name="element" property="severiteDescription"/>"
          align="center" ><bean:write name="element" property="severiteDescription"/></TD>
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
	   <logic:equal name="lienDateCreationSecuriteHidden" value="false">
         <TD class="listDetailOdd"><bean:write name="element" property="lienDateCreation"/></TD>
	  </logic:equal>
     </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_individuals division -->
