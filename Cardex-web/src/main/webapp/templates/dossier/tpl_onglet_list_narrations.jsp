<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "narrations".
Description : Module qui affiche le contenu de l'onglet "narrations", soit une
              liste de narrations.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.14 $, $Date: 2002/04/04 20:40:32 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.14 $, $Date: 2002/04/04 20:40:32 $, $Author: mlibersan $
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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationOngletTrieListe" %>

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

<!-- ---------------------------- -->
<DIV id="DATA_NARRATION">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" align="center"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_SITE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_DATE_CREATION%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_MONTANT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_COMMENTAIRE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_SEQUENCE%>' URLTrier="/dossier/trier.do" />
        </TD>        
    </TR>
    <logic:greaterThan name="dossier" property="listeNarrations.size" value="0">
    <TR>
       <TD colspan="10">&nbsp;
   	      <b><bean:message key='st_rowcount_t3'/>&nbsp;<bean:write name='dossier' property="listeNarrations.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>    

 <!-- Ce test est nécessaire car même si l'onglet est invisible, le contenu s'affiche puisqu'il
      s'agit de l'onglet par défaut.
 -->
<cardex:securityDefineTag nameDefine="narrationsOnglet" securityConstraint="cardex.dossier.narrations.onglet">

    <TR>
        <TD class="listDetailOdd" nowrap width="50">
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_ajouter" soumettre="/dossier/narration/create.do"/>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" colspan="8">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='narrations'>
    <logic:present name="element">
    <TR>
        <TD class="listDetailOdd" nowrap align="center">
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" width="36">
          <cardex:linkObject object='element' page='/dossier/narration/show.do'>
              <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
          </cardex:linkObject>
          <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
              <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
                  <logic:equal name='element' property='site' value='<%= sujetSite %>' >
			    <cardex:linkObject onclick="return doConfirmLinkSuppression();" object='element' page='/dossier/narration/delete.do'>
				<html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
			    </cardex:linkObject>
	          </logic:equal>
          </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="siteDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="dateCreation"/>
        </TD>
        <TD class="listDetailOdd" >
          <bean:write name="element" property="createurDescription"/>        
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="montant"/>
        </TD>
        <TD class="listDetailOdd" nowrap align='center'>
          <bean:write name="element" property="confidentialiteNarrationDescription"/>                
        </TD>
        <TD class="listDetailOdd">
          <html:textarea name="element" property="narrationSansFormat" rows='3' cols='30' style='font-family: Verdana, Arial; font-size: 8pt;' />
        </TD>
        <TD class="listDetailOdd" nowrap align='center'>
          <bean:write name="element" property="reference"/>
        </TD>
    </TR>
    </logic:present>
    </logic:iterate>

</cardex:securityDefineTag>

    </TABLE>
</DIV>
<!-- End data_narration division -->