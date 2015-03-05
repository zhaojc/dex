<%-- --------------------------------------------------------------------------
Use case    : Résultats de recherche d'un journal.
Description : Module d'affichage représentant les résutlats de recherche d'un
              journal.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.17 $, $Date: 2002/04/04 20:40:19 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.17 $, $Date: 2002/04/04 20:40:19 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.JournalTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<br><!-- Number of records to display -->
<TABLE align="left" width="950" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="950"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheJournal' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
<br clear="left">
<!-- Result list -->
<logic:greaterThan name='rechercheJournal' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

	<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="950">

	    <!-- Data table -->
		  <TABLE align="left" width="950" cellPadding="1" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
				   <cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_DESCRIPTION%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
					<cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_INTERVENANT%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_DATE_DEBUT%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_TYPE%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_CATEGORIE%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
			   <TD class="listTableHeader">
			   		<cardex:EnteteListeTriable name="rechercheJournal" property="listeResultat" key='<%=JournalTrieListe.CLE_ENDROIT%>' URLTrier="/journal/rechercheTrier.do"/>
			   </TD>
		    </TR>
                    <logic:iterate id='element' name='rechercheJournal' property='listeResultat.resultatAffichage' >
                           <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
                               <TD class="listDetailOdd">
                                  <cardex:linkJournal dossier='element' page='/journal/show.do'>
                                    <bean:write name="element" property="numeroDossier"/>
                                  </cardex:linkJournal>
                               </TD>
                               <TD class="listDetailOdd" width="200"><html:textarea name="element" property="description" rows='3' cols='30' style='font-family: Verdana, Arial; font-size: 8pt;' /></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="intervenant"/></TD>
                               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateDebut"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="typeDescription"/></TD>
                               <TD class="listDetailOdd"><bean:write name="element" property="categorieDescription"/></TD>
                               <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="endroitDescription"/></TD>
                          </TR>
                    </logic:iterate>

		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
	<!-- End Search Kind & nature list -->
    <br clear="left">

    <!-- Number of records to display -->
	    <TABLE align="left" width="950" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
	     <TR>
	        <TD>&nbsp;</TD>
	       <TD ALIGN="right" VALIGN="middle">
	    		<cardex:NavigationResultatListe name="rechercheJournal" property="listeResultat" URLChangerPage="/journal/rechercheChangerPage.do"/>
	       </TD>
	      </TR>
	    </TABLE>    
	<!-- End Number of records to display -->
</logic:greaterThan>
