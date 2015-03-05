<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "inscriptions".
Description : Module qui affiche le contenu de l'onglet "inscriptions", soit une
              liste d'inscriptions.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/04/04 20:40:32 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.7 $, $Date: 2002/04/04 20:40:32 $, $Author: mlibersan $
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
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.InscriptionOngletTrieListe" %>

<!-- Récupération de l'entité à laquelle appartient l'utilisateur pour déterminer
     ses droits d'accès aux dossiers associés. -->
<!-- On récupère également le total des dossiers liés -->     
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>


<!-- ------------------------------ -->
<DIV id="DATA_INSCRIPTIONS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_DATE_INSCRIPTION%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_SITE_CHOISIS%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_DUREE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_DATE_DEBUT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_DATE_FIN%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_PERIODE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_STATUT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_RENCONTRE_INITIALE%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name='dossier' property="listeInscriptions" key='<%=InscriptionOngletTrieListe.CLE_RENCONTRE_FINALE%>' URLTrier="/dossier/trier.do" />
        </TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_ajouter" soumettre="/dossier/inscription/create.do"/>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" colspan="11">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='inscriptions' indexId="index">
    <TR>
        <TD class="listDetailOdd" valign='top'>
		  <!-- Aux utilisateurs autorisés, la consultation de l'inscription est permise
		       si le dossier et l'inscription sont actifs et s'il s'agit d'une autoexclusion -->
          <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
            <logic:notEqual name='element' property='statut' value='<%=GlobalConstants.Statut.INSCRIPTION_INACTIF%>' >
               <logic:equal name='dossier' property='categorie' value='<%=GlobalConstants.Categorie.AUTOEXCLUSION%>'>
	                <cardex:linkObject object='element' page='/dossier/inscription/consulter.do'>
	                    <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
	                </cardex:linkObject>
               </logic:equal>
               <logic:equal name='dossier' property='categorie' value='<%=GlobalConstants.Categorie.AE_ESPACEJEUX%>'>
	                <cardex:linkObject object='element' page='/dossier/inscription/consulter.do'>
	                    <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
	                </cardex:linkObject>
               </logic:equal>
             </logic:notEqual>
             <!-- Si l'inscription est inactive, on permet de l'ouvrir s'il s'agit d'un contrat bonifié pour permettre 
             	  d'inscrire les dates de rencontre qui peuvent avoir lieu après la fin du contrat -->
            <logic:equal name='element' property='statut' value='<%=GlobalConstants.Statut.INSCRIPTION_INACTIF%>' >
               <logic:notEqual name='element' property='periode' value='<%=GlobalConstants.Periode.FIXE%>'>
	                <cardex:linkObject object='element' page='/dossier/inscription/consulter.do'>
	                    <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
	                </cardex:linkObject>
               </logic:notEqual>
             </logic:equal>
            <logic:equal name='element' property='statut' value='<%=GlobalConstants.Statut.INSCRIPTION_INACTIF%>' >
       		<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
       	        <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		      <cardex:linkObject onclick="return doConfirmLinkSuppression();" object='element' page='/dossier/inscription/delete.do'>
			  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		      </cardex:linkObject>
                </logic:equal>
            </logic:equal>
          </logic:notEqual>
        </TD>

        
        <TD class="listDetailOdd" nowrap valign='top'>
          <bean:write name="element" property="dateInscription"/>
        </TD>
        <TD class="listDetailOdd" nowrap valign='top'>
          <logic:iterate id="element2" name="element" property='sitesChoisisDescription'>
			<bean:write name="element2" /> <br>
          </logic:iterate>
        </TD>
        <TD class="listDetailOdd" nowrap valign='top' align='center'>
          <bean:write name="element" property="duree"/>
        </TD>
        <TD class="listDetailOdd" nowrap valign='top'>
          <bean:write name="element" property="dateDebut"/>
        </TD>
        <TD class="listDetailOdd" nowrap valign='top'>
          <bean:write name="element" property="dateFin"/>
        </TD>
        <TD class="listDetailOdd" valign='top'>
          <bean:write name="element" property="periodeDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap valign='top'>
          <bean:write name="element" property="statutDescription"/>
        </TD>
        <TD class="listDetailOdd" valign='top'>
          <bean:write name="element" property="createurDescription"/>
        </TD>
        <TD class="listDetailOdd" valign='top'>
          <bean:write name="element" property="dateRencontreInitiale"/>
        </TD>
        <TD class="listDetailOdd" valign='top'>
          <bean:write name="element" property="dateRencontreFinale"/>
        </TD>
    </TR>
    </logic:iterate>
    </TABLE>

</DIV>
<!-- End data_inscriptions division -->