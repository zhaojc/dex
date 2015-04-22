<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "suivis".
Description : Module qui affiche le contenu de l'onglet "suivis", soit une
              liste de suivis.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/05/01 20:25:24 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.10 $, $Date: 2002/05/01 20:25:24 $, $Author: mlibersan $
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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SuiviOngletTrieListe" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<SCRIPT language="JavaScript" type="text/javascript">
function ajouterSuivi(){
//S'il s'agit d'un suivi de l'entité Investigation, on s'assure que les sites du sujet et du dossier correspondent.
//Le but est d'éviter qu'un suivi INV soit créé dans un dossier INF et vice-versa.
   if(document.forms(0).site.value == "<%= GlobalConstants.Sites.INVESTIGATION %>" || 
   		document.forms(0).site.value == "<%= GlobalConstants.Sites.INVESTIGATION_FACTUREE %>"){
   		if( '<%= sujetSite %>' == document.forms(0).site.value){
			soumettre('<%= request.getContextPath() + "/dossier/suivi/create.do"%>');
		}else{
			message("<bean:message key='cardex.erreur.site.suivi'/>");
		}
	}else{
		soumettre('<%= request.getContextPath() + "/dossier/suivi/create.do"%>');
	}
}
</SCRIPT>

<!-- ------------------------------ -->

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
</HEAD>
<DIV id="DATA_FOLLOW_UP">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" align="center"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_SITE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_ACTIVITE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_STATUT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_DEMANDEUR%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
		    <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_APPROBATEUR%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_SUIVI%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader" nowrap align="center">
            <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_DATE_DEMANDE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader" nowrap align="center">
            <cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_DATE_PREVUE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader" nowrap align="center">
        	<cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_DATE_COMPLETE%>' URLTrier="/dossier/trier.do" />
        </TD>        
        <TD class="listTableHeader" align="center">
        	<cardex:EnteteListeTriable name="dossier" property="listeSuivis" key='<%=SuiviOngletTrieListe.CLE_DATE_APPROBATION%>' URLTrier="/dossier/trier.do" />
        </TD>        
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_ajouter" onclick="ajouterSuivi();"/>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" colspan="13">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='suivis'>
    <TR>
        <TD class="listDetailOdd" nowrap align="center">&nbsp;
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>&nbsp;
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
              <cardex:linkObject object='element' page='/dossier/suivi/show.do'>
                  <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
              </cardex:linkObject>
       		<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
       	        <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		      <cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='element' page='/dossier/suivi/delete.do'>
			  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		      </cardex:linkObject>
		</logic:equal>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="siteDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="activiteDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd">
        	<bean:write name="element" property="statutDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="confidentialiteSuiviDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="demandeurDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="intervenantDescription"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="approbateurDescription"/>&nbsp;
        </TD>
		<logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
		    <TD class="listDetailOdd" nowrap>
	          <html:textarea name="element" property="suivi" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;' disabled='true' />
	        </TD>
		</logic:notEqual>
		<logic:equal name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
		    <TD class="listDetailOdd" nowrap>
	          <html:textarea name="element" property="suivi" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;' disabled='false' />
	        </TD>
		</logic:equal>
        <TD class="listDetailOdd" width="75" nowrap="nowrap">
          <bean:write name="element" property="dateCreation"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" width="75" nowrap="nowrap">
          <bean:write name="element" property="datePrevue"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" width="75" nowrap="nowrap">
          <bean:write name="element" property="dateCompletee"/>&nbsp;
        </TD>
        <TD class="listDetailOdd" width="75" nowrap="nowrap">
          <bean:write name="element" property="dateApprobation"/>&nbsp;
        </TD>
    </TR>
    </logic:iterate>

    </TABLE>
</DIV>
<!-- End data_follow_up division -->