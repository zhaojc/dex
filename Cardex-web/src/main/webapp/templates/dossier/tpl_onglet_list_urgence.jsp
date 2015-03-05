<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "urgence".
Description : Module qui affiche le contenu de l'onglet "urgence" (services d'urgence)
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/03/13 22:43:46 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/03/13 22:43:46 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.UrgenceOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String var_lang = "fr";
   String sujetSite = "";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<SCRIPT language="JavaScript" type="text/javascript">
function doAjouter() {
   if(document.forms(0).choixClasse.value != ""){
	  soumettre("<%= request.getContextPath()%>/dossier/urgence/ajouter.do?choixClasse=" + document.forms(0).choixClasse.value);
   }
}

</SCRIPT>

<!-- ------------------------------ -->
<DIV id="DATA_URGENCE">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listDetailOdd" nowrap colspan="5">
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
	        	<b><bean:message key='i_cl_cle_t' /></b>
				
				<myriap:select size='1' name="dossier" property="choixClasse" style='HEIGHT: 20px; WIDTH: 190px' >
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.CLASSE_URGENCE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.AJOUT%>'/>
				</myriap:select>
                
				<cardex:button urlSecurite="/dossier/urgence/ajouter.do" labelKey='cb_ajouter' style="width: 80px; text-align: center;" onclick='doAjouter();' />
            </logic:notEqual>
        </TD>
    </TR>
    <TR>
       <TD class="listTableHeader">&nbsp;</TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="dossier" property="listeUrgence" key='<%=UrgenceOngletTrieListe.CLE_CLASSE%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="dossier" property="listeUrgence" key='<%=UrgenceOngletTrieListe.CLE_NOM%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name="dossier" property="listeUrgence" key='<%=UrgenceOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name="dossier" property="listeUrgence" key='<%=UrgenceOngletTrieListe.CLE_DATE_CREATION%>' URLTrier="/dossier/trier.do" />
	   </TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='urgence'>
    <TR>
        <TD class="listDetailOdd" nowrap>&nbsp;
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
       		<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
       	        <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		      		<cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='element' page='/dossier/urgence/supprimer.do'>
			  			<html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		      		</cardex:linkObject>
				</logic:equal>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd">
          <cardex:linkObject object='element' page='/dossier/urgence/modifier.do'>
              <bean:write name="element" property="classeDescription"/>
          </cardex:linkObject>
        </TD>        
        <TD class="listDetailOdd"><bean:write name='element' property='societe' /></TD>
        <TD class="listDetailOdd"><bean:write name='element' property='createur' /></TD>
        <TD class="listDetailOdd"><bean:write name='element' property='dateCreation' /></TD>
     </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_games division -->
