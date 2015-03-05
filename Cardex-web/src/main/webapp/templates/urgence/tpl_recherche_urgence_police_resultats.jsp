<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'un service d'urgence.
Description : Module d'affichage représentant les résutlats de recherche d'un
              service d'urgence policier.
Author(s)   : $Author: mazzucr $
Revision    : $Date: 2014/03/14 $
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.UrgenceTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
<tr>
	    <TD width="600"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheUrgence' property='listeResultat.size'/></b></TD>
        <TD><cardex:button securityConstraint='cardex.recherche.urgences.imprimer' labelKey='Imprimer'  onclick='doPrint();' /></TD>
	    <TD ALIGN="right" VALIGN="middle">
	        <cardex:button securityConstraint='cardex.recherche.urgences.imprimer' labelKey='ImprimerTout'  onclick='doPrintAll();' />&nbsp;
	    </TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->
<logic:greaterThan name='rechercheUrgence' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="772">

	    <!-- Data table -->
		  <TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			    <cardex:securityDefineTag nameDefine="classeSecurite" nomFormulaire="urgence" propertyFormulaire='classeDescription' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_CLASSE%>' URLTrier="/urgence/rechercheTrier.do" />
				   </td>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="dossierSecurite" nomFormulaire="urgence" propertyFormulaire='numeroDossier' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_DOSSIER%>' URLTrier="/urgence/rechercheTrier.do" />
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="societeSecurite" nomFormulaire="urgence" propertyFormulaire='societe' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_SOCIETE%>' URLTrier="/urgence/rechercheTrier.do" />
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="contactSecurite" nomFormulaire="urgence" propertyFormulaire='nom' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_CONTACT%>' URLTrier="/urgence/rechercheTrier.do" />
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="districtSecurite" nomFormulaire="urgence" propertyFormulaire='tabpage_contact_prenom' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_CONTACT_PRENOM%>' URLTrier="/urgence/rechercheTrier.do" />
				   </TD>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="districtSecurite" nomFormulaire="urgence" propertyFormulaire='district' >
				   <TD class="listTableHeader">
					   <cardex:EnteteListeTriable name="rechercheUrgence" property="listeResultat" key='<%=UrgenceTrieListe.CLE_DISTRICT%>' URLTrier="/urgence/rechercheTrier.do" />
				   </TD>
				</cardex:securityDefineTag>
				
		    </TR>

			<logic:iterate id='element' name='rechercheUrgence' property='listeResultat.resultatAffichage' >
               <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                   <logic:equal name="classeSecuriteHidden" value="false">
	                   <TD class="listDetailOdd"><bean:write name="element" property="classeDescription"/>&nbsp;</TD>
	               </logic:equal>
	               <logic:equal name="dossierSecuriteHidden" value="false">
	               		<TD class="listDetailOdd">
			          		<cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/showAcces.do' 
			          				actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
			            		<bean:write name="element" property="dossier.numeroCardexTexte"/>
			          		</cardex:linkDossier>
			          	</TD>
	               </logic:equal>
	               <logic:equal name="societeSecuriteHidden" value="false">
	                   <TD class="listDetailOdd"><bean:write name="element" property="societe"/>&nbsp;</TD>
	               </logic:equal>
	               <logic:equal name="contactSecuriteHidden" value="false">
	                   <TD class="listDetailOdd"><bean:write name="element" property="contact"/>&nbsp;</TD>
	               </logic:equal>
	               <logic:equal name="contactSecuriteHidden" value="false">
	                   <TD class="listDetailOdd"><bean:write name="element" property="contactPrenom"/>&nbsp;</TD>
	               </logic:equal>
	               <logic:equal name="districtSecuriteHidden" value="false">
	                   <TD class="listDetailOdd"><bean:write name="element" property="district"/>&nbsp;</TD>
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
	       <TD ALIGN="right" VALIGN="middle">
	    		<cardex:NavigationResultatListe name="rechercheUrgence" property="listeResultat" URLChangerPage="/urgence/rechercheChangerPage.do" />
	       </TD>
	      </TR>
	    </TABLE>
<!-- End Number of records to display -->
</logic:greaterThan>