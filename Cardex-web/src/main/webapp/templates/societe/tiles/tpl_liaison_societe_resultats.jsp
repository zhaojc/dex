
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteLiaisonTrieListe" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="sourceProperty" id="sourceProperty" />


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
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_NO_FICHE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheTrier.do"%>'/>
                    </TD>
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_SEVERITE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheTrier.do"%>'/>
                    </TD>
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_RAISON_SOCIALE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheTrier.do"%>'/>
                    </TD>
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_NOM%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheTrier.do"%>'/>
                    </TD>
                    <TD class="listTableHeader">
                    	<cardex:EnteteListeTriable name="rechercheSociete" property="listeResultat" key='<%=SocieteLiaisonTrieListe.CLE_CLASSE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheTrier.do"%>'/>
                    </TD>
                   </TR>

                  <logic:iterate id='element' name='rechercheSociete' property='listeResultat.resultatAffichage' >

                           <TR>
                               <TD class="listDetailOdd">
                                  <cardex:linkLiaisonSociete source='rechercheSociete' sourceProperty='<%=sourceProperty.toString()%>' societe='element' 
                                  page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/link/role/show.do"%>'>
                                    <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                                  </cardex:linkLiaisonSociete>
                               </TD>
	                           <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroFiche"/></TD>
				               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
				                 align="center" ><bean:write name="element" property="severiteDescription"/></TD>
	                           <TD class="listDetailOdd"><bean:write name='element' property='raisonEtre' /></TD>
	                           <TD class="listDetailOdd">
	                           	  <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                                    <bean:write name='element' property='nom' />
                                  </cardex:linkSociete>
	                           </TD>
	                           <TD class="listDetailOdd"><bean:write  name='element' property='classeDescription' />&nbsp;</TD>                               
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
    		<cardex:NavigationResultatListe name="rechercheSociete" property="listeResultat" 
	    			URLChangerPage='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/rechercheChangerPage.do"%>'/>
       </TD>
      </TR>
    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
