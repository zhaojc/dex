<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'un véhicule.
Description : Module d'affichage représentant les résutlats de recherche d'un
              véhicule.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/22 14:11:39 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/04/22 14:11:39 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeTrieListe" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
	    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheVehicule' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->
<logic:greaterThan name='rechercheVehicule' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
		  <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader">&nbsp;</td>
			   <cardex:securityDefineTag nameDefine="cleSecurite" nomFormulaire="vehicule" propertyFormulaire='cle' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_CLE%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </td>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="immatriculationSecurite" nomFormulaire="vehicule" propertyFormulaire='immatriculation' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_IMMATRICULATION%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="marqueSecurite" nomFormulaire="vehicule" propertyFormulaire='marque' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_MARQUE%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="modeleSecurite" nomFormulaire="vehicule" propertyFormulaire='modele' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_MODELE%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="provinceSecurite" nomFormulaire="vehicule" propertyFormulaire='province' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_PROVINCE%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="anneeSecurite" nomFormulaire="vehicule" propertyFormulaire='annee' >				   
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_ANNEE%>' URLTrier="/dossier/vehicule/rechercheTrier.do"/>
				   </TD>
			   </cardex:securityDefineTag>
		    </TR>

			<logic:iterate id='element' name='rechercheVehicule' property='listeResultat.resultatAffichage' >

              <TR>
                  <TD class="listDetailOdd">
                     <cardex:linkLiaisonVehicule source='rechercheVehicule' source='dossier' vehicule='element' page='/dossier/vehicule/link.do'>
                       <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                     </cardex:linkLiaisonVehicule>
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
                  <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="immatriculation"/>&nbsp;</TD>
               </logic:equal>
               <logic:equal name="marqueSecuriteHidden" value="false">
                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="marqueDescription"/>&nbsp;</TD>
               </logic:equal>
               <logic:equal name="modeleSecuriteHidden" value="false">
                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="modeleDescription"/>&nbsp;</TD>
               </logic:equal>
               <logic:equal name="provinceSecuriteHidden" value="false">
                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="province"/>&nbsp;</TD>
               </logic:equal>
               <logic:equal name="anneeSecuriteHidden" value="false">                  
                  <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="annee"/>&nbsp;</TD>
               </logic:equal>                  
            </TR>
            </logic:iterate>
		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
	<!-- End Search Kind & nature list -->
    <br clear="left">

<!-- Number of records to display -->
	    <TABLE align="left" width="770" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
	     <TR>
	        <TD>&nbsp;</TD>
	       <TD ALIGN="right" VALIGN="middle">
	    		<cardex:NavigationResultatListe name="rechercheVehicule" property="listeResultat" URLChangerPage="/dossier/vehicule/rechercheChangerPage.do" />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
