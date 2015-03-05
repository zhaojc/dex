<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche d'un dossier pour fin d'impression.
Description : Module d'affichage représentant les résultats d'une recherche de
              dossier pour fin d'impression.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/03/28 21:57:45 $

History     : Voir ci-dessous.

$Revision: 1.1 $, $Date: 2002/03/28 21:57:45 $, $Author: abruno-boucher $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() %>/styles/lq_styles.css'>

<TITLE><bean:message key="titre.application.cardex"/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<FORM action="#">

<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD width="30"><html:img page="/images/pixelnoir.gif" width="26" height="2" border="0" /></TD>
    <TD width="140" class="tabSubject">
	    <bean:define id="genre" name='rechercheDossier' property='genre' />
		<cardex:afficherValeurListeTag  name='rechercheDossier' property='nature' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
             valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
			 valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
    </TD>
    <TD width="480"><html:img page="/images/pixelnoir.gif" width="480" height="2" border="0" /></TD>
  </TR>
  
  <TR>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="10" border="0" /></TD>
  </TR>
</TABLE>


<!-- Number of records to display -->
<TABLE align="left" width="650" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheDossier' property='listeResultat.size'/></b></TD>
    <TD align="right"><b><bean:write name='rechercheDossier' property="listeResultat.numeroPageCourant"/>/<bean:write name='rechercheDossier' property="listeResultat.nombrePageTotal"/>&nbsp;page(s)</b>
    </TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left"><!-- Search Kind & nature list -->

<logic:greaterThan name='rechercheDossier' property='listeResultat.size' value="0" >
	<TABLE align="left" width="650" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="650">

	    <!-- Data table -->
		  <TABLE align="left" width="650" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader" ><bean:message key='v_do_ancienne_reference'/></TD>
			   <TD class="listTableHeader" ><bean:message key='v_do_numero_dossier'/></TD>
			   <TD class="listTableHeader" align="center" ><bean:message key='ref_1_t'/></TD>
			   <TD class="listTableHeader" align="center" ><bean:message key='ref_2_t'/></TD>
			   <TD class="listTableHeader" align="center" ><bean:message key='v_do_reference3'/></TD>
			   <TD class="listTableHeader" ><bean:message key='i_sev_t'/></TD>
			   <TD class="listTableHeader" ><bean:message key='i_cc_commentaire_t'/></TD>
			   <TD class="listTableHeader" ><bean:message key='i_type_t'/></TD>
			   <TD class="listTableHeader" ><bean:message key='tabpage_categorie'/></TD>
			   <TD class="listTableHeader" ><bean:message key='tabpage_periode'/></TD>
			   <TD class="listTableHeader" ><bean:message key='tabpage_statut'/></TD>
			   <TD class="listTableHeader" ><bean:message key='d_is_date_debut_t'/></TD>
			   <TD class="listTableHeader" ><bean:message key='d_is_date_fin_t'/></TD>
			   <TD class="listTableHeader" ><bean:message key='v_do_assigne_a'/></TD>
		    </TR>

        <logic:iterate id='element' name='rechercheDossier' property='listeResultat.resultatAffichage' >
               <TR>
                   <TD class="footnotes" nowrap width="15"><bean:write name="element" property="numeroDossier"/>&nbsp;</TD>
                   <TD class="footnotes" nowrap ><bean:write name="element" property="numeroCardex"/></TD>
                   <TD class="footnotes" >&nbsp;<bean:write name="element" property="reference1"/></TD>
                   <TD class="footnotes" >&nbsp;<bean:write name="element" property="reference2"/></TD>
                   <TD class="footnotes" >&nbsp;<bean:write name="element" property="reference3"/></TD>
	               <TD class="severity<bean:write name="element" property="severiteDescription"/>"
	                 align="center" ><bean:write name="element" property="severiteDescription"/></TD>
                   <TD class="footnotes"><bean:write name="element" property="confidentialiteDescription"/></TD>
                   <TD class="footnotes"><bean:write name="element" property="typeDescription"/></TD>
                   <TD class="footnotes"><bean:write name="element" property="categorieDescription"/></TD>
                   <TD class="footnotes" >&nbsp;<bean:write name="element" property="periodeDescription"/></TD>
                   <TD class="footnotes"><bean:write name="element" property="statutDescription"/></TD>
                   <TD class="footnotes" nowrap><bean:write name="element" property="dateDebut16"/></TD>
                   <TD class="footnotes" nowrap><bean:write name="element" property="dateFin10"/></TD>
				   <TD class="footnotes" nowrap ><bean:write name="element" property="intervenant"/></TD>
              </TR>
        </logic:iterate>
		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
	<!-- End Search Kind & nature list -->
    
</logic:greaterThan>

</FORM>

</BODY>
</HTML>