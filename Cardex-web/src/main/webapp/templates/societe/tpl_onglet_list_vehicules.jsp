<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "véhicules".
Description : Module qui affiche le contenu de l'onglet "véhicules", soit une
              liste de véhicules.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/22 14:11:37 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/04/22 14:11:37 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeOngletTrieListe" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   String utilisateur = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     utilisateur = sujetCardex.getCode();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>


<!-- ------------------------------ -->
<DIV id="DATA_VEHICLES">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
	<cardex:securityDefineTag nameDefine="cleSecurite" nomFormulaire="vehicule" propertyFormulaire='cle' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_CLE%>' URLTrier="/societe/trier.do"/>
	   </td>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="immatriculationSecurite" nomFormulaire="vehicule" propertyFormulaire='immatriculation' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_IMMATRICULATION%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="marqueSecurite" nomFormulaire="vehicule" propertyFormulaire='marque' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MARQUE%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="modeleSecurite" nomFormulaire="vehicule" propertyFormulaire='modele' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MODELE%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="provinceSecurite" nomFormulaire="vehicule" propertyFormulaire='province' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_PROVINCE%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="anneeSecurite" nomFormulaire="vehicule" propertyFormulaire='annee' >	   
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_ANNEE%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="vehicule" propertyFormulaire='lienCreateur' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/societe/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="vehicule" propertyFormulaire='lienDateCreation' >
	  	<TD class="listTableHeader" >
			<cardex:EnteteListeTriable name="societe" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/societe/trier.do" />
		</TD>
	</cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="societe" property="listeVehicules.size" value="0">
    <TR>
       <TD colspan="10">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='societe' property="listeVehicules.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="8" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/societe/vehicule/search/show.do"/>
        </TD>
    </TR>
    <logic:iterate id="element" name="societe" property='vehicules'>
        <TR>
           <TD class="listDetailOdd" nowrap>
					<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
					<logic:equal name='societe' property='site' value='<%= sujetSite %>' >
						<cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='societe' vehicule='element' page='/societe/vehicule/delete.do'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonVehicule>
				  	</logic:equal>
          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
          	        <logic:notEqual name='societe' property='site' value='<%= sujetSite %>' >
          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
						<cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='societe' vehicule='element' page='/societe/vehicule/delete.do'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonVehicule>
					   </logic:equal>
				    </logic:notEqual>
           </TD>
		<logic:equal name="cleSecuriteHidden" value="false">
           <TD class="listDetailOdd">
	            <cardex:linkVehicule vehicule='element' page='/vehicule/showAcces.do'>
					<bean:write name="element" property="cle"/>
					<bean:write name="element" property="site"/>
	            </cardex:linkVehicule>
           </TD>
        </logic:equal>
        <logic:equal name="immatriculationSecuriteHidden" value="false">
           <TD class="listDetailOdd" nowrap><bean:write name="element" property="immatriculation"/>&nbsp;</TD>
        </logic:equal>
        <logic:equal name="marqueSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="marqueDescription"/>&nbsp;</TD>
        </logic:equal>
        <logic:equal name="modeleSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="modeleDescription"/>&nbsp;</TD>
        </logic:equal>
        <logic:equal name="provinceSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="province"/>&nbsp;</TD>
        </logic:equal>
        <logic:equal name="anneeSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="annee"/>&nbsp;</TD>
       </logic:equal>
	   <logic:equal name="lienCreateurSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="lienCreateur"/></TD>  
       </logic:equal>
       <logic:equal name="lienDateCreationSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name='element' property='lienDateCreation' /></TD>
        </logic:equal>
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_vehicles division -->