<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "véhicules".
Description : Module qui affiche le contenu de l'onglet "véhicules", soit une
              liste de véhicules.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.8 $, $Date: 2002/04/25 17:41:34 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.8 $, $Date: 2002/04/25 17:41:34 $, $Author: mlibersan $
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

<SCRIPT language='JavaScript' type='text/javascript'>
function doAddVehicule() {
	post('<%= request.getContextPath() + "/dossier/vehicule/new.do"%>');
}
</SCRIPT>

<!-- ------------------------------ -->
<DIV id="DATA_VEHICLES">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <cardex:securityDefineTag nameDefine="dossierOngletVehicuels" securityConstraint="cardex.dossier.onglet.vehicules.liste.resultat">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
	<cardex:securityDefineTag nameDefine="cleSecurite" nomFormulaire="vehicule" propertyFormulaire='cle' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_CLE%>' URLTrier="/dossier/trier.do"/>
	   </td>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="immatriculationSecurite" nomFormulaire="vehicule" propertyFormulaire='immatriculation' >	   
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_IMMATRICULATION%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="marqueSecurite" nomFormulaire="vehicule" propertyFormulaire='marque' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MARQUE%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="modeleSecurite" nomFormulaire="vehicule" propertyFormulaire='modele' >
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MODELE%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="provinceSecurite" nomFormulaire="vehicule" propertyFormulaire='province' >	   
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_PROVINCE%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="anneeSecurite" nomFormulaire="vehicule" propertyFormulaire='annee' >	   
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_ANNEE%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>
	<cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="vehicule" propertyFormulaire='lienCreateur' >	   
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/dossier/trier.do"/>
	   </TD>
	</cardex:securityDefineTag>	   
	<cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="vehicule" propertyFormulaire='lienDateCreation' >
	   <TD class="listTableHeader" >
			<cardex:EnteteListeTriable name="dossier" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/dossier/trier.do" />			   
	   </TD>
	</cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="dossier" property="listeVehicules.size" value="0">
    <TR>
       <TD colspan="10">&nbsp;
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='dossier' property="listeVehicules.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="8" nowrap>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/dossier/vehicule/search/show.do"/>
            </logic:notEqual>
            &nbsp;
            <cardex:securityDefineTag nameDefine="ajoutVehicule" urlSecurite="/dossier/vehicule/new.do">
	            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
					<cardex:button labelKey="cb_ajouter" onclick="doAddVehicule();" urlSecurite="/dossier/vehicule/new.do"/>
	            </logic:notEqual>
			</cardex:securityDefineTag>
        </TD>
    </TR>        
    <logic:iterate id="element" name="dossier" property='vehicules'>
        <TR>
           <TD class="listDetailOdd" nowrap>
                <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
        	   <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
	          	   <logic:equal name='dossier' property='site' value='<%= sujetSite %>' >
					  <cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='dossier' vehicule='element' page='/dossier/vehicule/delete.do'>
					      <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
					  </cardex:linkLiaisonVehicule>
				   </logic:equal>
	      			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
	      	        <logic:notEqual name='dossier' property='site' value='<%= sujetSite %>' >
	      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
					  <cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='dossier' vehicule='element' page='/dossier/vehicule/delete.do'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonVehicule>
					   </logic:equal>
				    </logic:notEqual>
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
    </cardex:securityDefineTag>
    </TABLE>
</DIV>
<!-- End data_vehicles division -->