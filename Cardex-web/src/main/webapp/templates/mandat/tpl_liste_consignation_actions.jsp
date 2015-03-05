<%-- --------------------------------------------------------------------------
Use case    : Affichage de la liste des consignation des actions pour un mandat.
Author(s)   : $Author: François Guérin

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<logic:greaterThan name='consignationActionResultats' property='size' value="0" >
	<TABLE align="left" width="600" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="600">

	    <!-- Data table -->
		  <TABLE align="left" width="600" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <logic:equal name='PSUMandat' property='typeAction' value='<%= GlobalConstants.TypeAction.LIAISON %>' >
				   <TD class="listTableHeader"><bean:message key='onglet_t'/></TD>
				   <TD class="listTableHeader"><bean:message key='reference_t'/></TD>
			   </logic:equal>			   
			   <logic:equal name='PSUMandat' property='typeAction' value='<%= GlobalConstants.TypeAction.SUPPRESSION %>' >
				   <TD class="listTableHeader"><bean:message key='onglet_t'/></TD>
				   <TD class="listTableHeader"><bean:message key='reference_t'/></TD>
			   </logic:equal>			   
			   <TD class="listTableHeader"><bean:message key='v_co_cree_par_t'/></TD>
			   <TD class="listTableHeader"><bean:message key='c_ac_action_t'/></TD>
			   <TD class="listTableHeader" align="center"><bean:message key='d_ac_date_acces_t'/></TD>
		    </TR>

            <logic:iterate id='element' name='consignationAction' >
                   <TR>
				   <logic:equal name='element' property='typeAction' value='<%= GlobalConstants.TypeAction.LIAISON %>' >
					   <TD class="listDetailOdd">&nbsp;
					        <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.GENRE_FICHIER %>' name="element" property="genreFichierAction"/>
					   </TD>
					   <TD class="listDetailOdd">&nbsp;
					   		<bean:write name="element" property="referenceAction"/>
					   </TD>
				   </logic:equal>			   
				   <logic:equal name='element' property='typeAction' value='<%= GlobalConstants.TypeAction.SUPPRESSION %>' >
					   <TD class="listDetailOdd">&nbsp;
					   		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.GENRE_FICHIER %>' name="element" property="genreFichierAction"/>
					   </TD>
					   <TD class="listDetailOdd">&nbsp;
					   		<bean:write name="element" property="referenceAction"/>
					   </TD>
				   </logic:equal>			   
            		<TD class="listDetailOdd" nowrap>
			          <cardex:afficherValeurListeTag name="element" property="intervenant" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
			       </TD>
			       <TD class="listDetailOdd" nowrap>
			          <cardex:afficherValeurListeTag name='element' property="typeAction" classe='<%= GlobalConstants.CleListe.TYPE_ACTION %>' />
			       </TD>
                   <TD class="listDetailOdd" align="center" nowrap><bean:write name="element" property="dateConsignation"/>
                   </TD>
                  </TR>
            </logic:iterate>
		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
</logic:greaterThan>

