<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'une société.
Description : Module d'affichage représentant les résutlats de recherche d'une
              société.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/03/28 23:35:13 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/03/28 23:35:13 $, $Author: abruno-boucher $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br>
<!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheAdresses' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->

<logic:greaterThan name='rechercheAdresses' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
	
	<TABLE align="left" width="770" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="100%">

	    <!-- Data table -->
           <TABLE align="left" width="100%" cellPadding="2" cellSpacing="0" border="1" >
                 <TR>
                   <TR>
                    <TD class="listTableHeaderVert">
	                    <cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_so_nom" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_no_fiche_t2" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="i_sev_t" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_so_raison_sociale_t3" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="tabpage_classe" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
    					<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_so_reference_nom_t2" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_so_reference_prenom_t2" URLTrier="/adresses/rechercheSociete/rechercheChangerPage.do"/>
                    </TD>
                   </TR>

       		     <logic:iterate id='element' name='rechercheAdresses' property='listeResultat.resultatAffichage' >
                   <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                       <TD class="listDetailOdd">
                          <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                       		<bean:write name='element' property='nom' />
                          </cardex:linkSociete>
                       </TD>

                       <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroFiche"/>&nbsp;</TD>
			           <TD class="listDetailOdd" nowrap>
			             <cardex:afficherValeurListeTag name='element' property="severite" classe='<%= GlobalConstants.CleListe.SEVERITE %>'/>&nbsp;
			           </TD>
                       <TD class="listDetailOdd"><bean:write name='element' property='raisonEtre' />&nbsp;</TD>

                       <TD class="listDetailOdd" >
                       	<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.CLASSE %>' name='element' property='classe'/>&nbsp;
                       </TD>

                       <TD class="listDetailOdd"><bean:write  name='element' property='referenceNom' />&nbsp;</TD>
                       <TD class="listDetailOdd"><bean:write  name='element' property='referencePrenom' />&nbsp;</TD>
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
    <TD><cardex:button securityConstraint='cardex.recherche.societes.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' /></TD>
   <TD ALIGN="right" VALIGN="middle">
		<cardex:NavigationResultatListe name="rechercheAdresses" property="listeResultat" URLChangerPage="/adresses/rechercheSociete/rechercheChangerPage.do" />
   </TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>