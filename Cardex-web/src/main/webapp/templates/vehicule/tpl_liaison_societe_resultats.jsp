<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche de société.
Description : Module d'affichage représentant les résutlats de recherche d'un
              sujet.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/04/09 21:13:08 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.1 $, $Date: 2002/04/09 21:13:08 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteLiaisonTrieListe" %>

<br>
<!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheSociete' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->

<logic:greaterThan name='rechercheSociete' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
           <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
                   <TR>
                    <TD class="listTableHeader">&nbsp;</TD>
	               <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="societe" propertyFormulaire='nom' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_NOM%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
	                    </TD>
				   </cardex:securityDefineTag>
                    <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="societe" propertyFormulaire='numeroFiche' >
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_NO_FICHE%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
	                    </TD>
				   </cardex:securityDefineTag>
				   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="societe" propertyFormulaire='severite' >	                    
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_SEVERITE%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
	                    </TD>
				   </cardex:securityDefineTag>
				   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="societe" propertyFormulaire='severiteCasino' >
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
                    </TD>
				   </cardex:securityDefineTag>
				   <cardex:securityDefineTag nameDefine="raisonEtreSecurite" nomFormulaire="societe" propertyFormulaire='raisonEtre' >	                    
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_RAISON_SOCIALE%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
	                    </TD>
	               </cardex:securityDefineTag>
				   <cardex:securityDefineTag nameDefine="classeSecurite" nomFormulaire="societe" propertyFormulaire='classe' >	                    
	                    <TD class="listTableHeader">
	                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_CLASSE%>' URLTrier="/vehicule/societe/rechercheTrier.do"/>
	                    </TD>
                    </cardex:securityDefineTag>
                   </TR>

                  <logic:iterate id='element' name='rechercheSociete' property='listeResultat.resultatAffichage' >
                     <TR>
                         <TD class="listDetailOdd">
                            <cardex:linkLiaisonSociete source='rechercheSociete' sourceProperty='vehicule' societe='element' page='/vehicule/societe/link/role/show.do'>
                              <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                            </cardex:linkLiaisonSociete>
                         </TD>
	                     <logic:equal name="nomSecuriteHidden" value="false">
	                      	<TD class="listDetailOdd">
	                            <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
	                              <bean:write name='element' property='nom' />
	                            </cardex:linkSociete>
	                      	</TD>
		                 </logic:equal>
                         <logic:equal name="numeroFicheSecuriteHidden" value="false">
	                      	<TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="numeroFiche"/></TD>
		                 </logic:equal>
		                 <logic:equal name="severiteSecuriteHidden" value="false">	                      	
	                        <TD class="severity<bean:write name="element" property="severiteDescription"/>"
	                           align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
		                 </logic:equal>
		                 <logic:equal name="severiteCasinoSecuriteHidden" value="false">
		                   <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
		                     align="center" ><bean:write name="element" property="severiteCasinoDescription"/>&nbsp;</TD>
		                 </logic:equal>
		                 <logic:equal name="raisonEtreSecuriteHidden" value="false">	                           
	                      	<TD class="listDetailOdd">&nbsp;<bean:write name='element' property='raisonEtre' /></TD>
	                     </logic:equal>
		                 <logic:equal name="classeSecuriteHidden" value="false">	                      	
	                      	<TD class="listDetailOdd">&nbsp;<bean:write  name='element' property='classeDescription' />&nbsp;</TD>
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
    		<cardex:NavigationResultatListe name="rechercheSociete" property="listeResultat" URLChangerPage="/vehicule/societe/rechercheChangerPage.do" />
       </TD>
      </TR>
    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
