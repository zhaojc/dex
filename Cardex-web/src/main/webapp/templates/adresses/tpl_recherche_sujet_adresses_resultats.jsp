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
                    <TD class="listTableHeaderVert">
	                    <cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_no_fiche_t2" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="i_sev_t" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_su_nom_t2" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_su_prenom_t2" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="v_su_surnom_t2" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
    					<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="tabpage_sexe" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="tabpage_nationalite" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="tabpage_race" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="i_la_cle_t" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>
                    <TD class="listTableHeaderVert">
                    	<cardex:EnteteListeTriable name="rechercheAdresses" property="listeResultat" key="d_su_date_naissance_t" URLTrier="/adresses/rechercheSujet/rechercheChangerPage.do"/>
                    </TD>                    
                   </TR>

       		     <logic:iterate id='element' name='rechercheAdresses' property='listeResultat.resultatAffichage' >
                       <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                           <TD class="listDetailOdd" nowrap>
                              <cardex:linkSujet sujet='element' page='/sujet/showAcces.do'>
	                           <bean:write name="element" property="numeroFiche"/>
                              </cardex:linkSujet>
	                       </TD>
                           <TD class="severity<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEVERITE %>' name='element' property='severite'/>" align="center" >
                           		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEVERITE %>' name='element' property='severite'/>&nbsp;
                           	</TD>
                           <TD class="listDetailOdd"><bean:write name='element' property='nom' /></TD>
                           <TD class="listDetailOdd"><bean:write name='element' property='prenom' /></TD>
                           <TD class="listDetailOdd">&nbsp;<bean:write name='element' property='alias' /></TD>
                           <TD class="listDetailOdd">&nbsp;<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='element' property='sexe'/></TD>
                           <TD class="listDetailOdd">&nbsp;<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='element' property='ethnie'/></TD>
                           <TD class="listDetailOdd">&nbsp;<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.RACE %>' name='element' property='race'/></TD>
                           <TD class="listDetailOdd">&nbsp;<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LANGUE %>' name='element' property='langue'/></TD>
                           <TD class="listDetailOdd" nowrap>&nbsp;<bean:write name="element" property="dateNaissance"/></TD>
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
    <TD><cardex:button securityConstraint='cardex.recherche.sujet.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' /></TD>
   <TD ALIGN="right" VALIGN="middle">
		<cardex:NavigationResultatListe name="rechercheAdresses" property="listeResultat" URLChangerPage="/adresses/rechercheSujet/rechercheChangerPage.do"/>
   </TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>