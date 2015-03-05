
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeTrieListe" %>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="sourceProperty" id="sourceProperty" />

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
			   <TD class="listTableHeader">&nbsp;</OPTION>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_CLE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </td>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_IMMATRICULATION%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_MARQUE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_MODELE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_PROVINCE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </TD>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheVehicule" property="listeResultat" key='<%=VehiculeTrieListe.CLE_ANNEE%>' URLTrier='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheTrier.do"%>'/>
			   </TD>
		    </TR>

			<logic:iterate id='element' name='rechercheVehicule' property='listeResultat.resultatAffichage' >

                   <TR>
                       <TD class="listDetailOdd">
                          <cardex:linkLiaisonVehicule source='rechercheVehicule' source='<%=sourceProperty.toString()%>' vehicule='element' 
                          page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/vehicule/link/role/show.do"%>'>
                            <html:img page="/images/link.gif" altKey="cb_lier" border="0" height="14" width="14" />
                          </cardex:linkLiaisonVehicule>
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
	    		<cardex:NavigationResultatListe name="rechercheVehicule" property="listeResultat" 
	    			URLChangerPage='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/dossier/rechercheChangerPage.do"%>' />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->

</logic:greaterThan>
