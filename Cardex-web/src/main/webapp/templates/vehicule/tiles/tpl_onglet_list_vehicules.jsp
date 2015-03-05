
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
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

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<!-- ------------------------------ -->
<DIV id="DATA_VEHICLES">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_CLE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </td>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_IMMATRICULATION%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MARQUE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_MODELE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_PROVINCE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>
	   <TD class="listTableHeader">
		   <cardex:EnteteListeTriable name="sujet" property="listeVehicules" key='<%=VehiculeOngletTrieListe.CLE_ANNEE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>'/>
	   </TD>           
    </TR>
    <TR>
        <TD class="listDetailOdd" colspan="7" nowrap>
			<cardex:button labelKey="cb_lier" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/vehicule/search/show.do"%>'/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="sujet" property='vehicules'>
        <TR>
           <TD class="listDetailOdd" nowrap>
					<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
					<logic:equal name='element' property='site' value='<%= sujetSite %>' >
						<cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='sujet' vehicule='element'  
						page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/vehicule/delete.do"%>'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonVehicule>
				  	</logic:equal>
          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
          	        <logic:notEqual name='element' property='site' value='<%= sujetSite %>' >
          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
						<cardex:linkLiaisonVehicule onclick="return doConfirmLinkSuppression();"  source='sujet' vehicule='element'  
						page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/vehicule/delete.do"%>'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonVehicule>
					   </logic:equal>
				    </logic:notEqual>
           </TD>
           <TD class="listDetailOdd">
	            <cardex:linkVehicule vehicule='element' page='/vehicule/showAcces.do'>
					<bean:write name="element" property="cle"/>
	            </cardex:linkVehicule>
           </TD>
           <TD class="listDetailOdd" nowrap><bean:write name="element" property="immatriculation"/>&nbsp;</TD>
           <TD class="listDetailOdd"><bean:write name="element" property="marqueDescription"/>&nbsp;</TD>
           <TD class="listDetailOdd"><bean:write name="element" property="modeleDescription"/>&nbsp;</TD>
           <TD class="listDetailOdd"><bean:write name="element" property="province"/>&nbsp;</TD>
           <TD class="listDetailOdd"><bean:write name="element" property="annee"/>&nbsp;</TD>      
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_vehicles division -->