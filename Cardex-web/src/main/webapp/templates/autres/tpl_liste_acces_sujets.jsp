<%-- --------------------------------------------------------------------------
Use case    : Affichage des listes d'accès.
Author(s)   : $Author: François Guérin

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<logic:greaterThan name='<%= GlobalConstants.RechercheList.ACCES %>' property='size' value="0" >
	<TABLE align="left" width="600" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
	 <TR>
	    <TD width="600">

	    <!-- Data table -->
		  <TABLE align="left" width="600" cellPadding="2" cellSpacing="0" border="1" >
			 <TR>
			   <TD class="listTableHeader"><bean:message key='v_co_cree_par_t'/></OPTION>
			   <TD class="listTableHeader"><bean:message key='d_ac_date_acces_t'/></TD>
			   <TD class="listTableHeader"><bean:message key='l_si_cle_t'/></TD>
			   <TD class="listTableHeader" align="center"><bean:message key='c_ac_action_t'/></TD>
		    </TR>

                    <logic:iterate id='element' name='<%= GlobalConstants.RechercheList.ACCES_CURRENT_LIST %>' >
                           <TR>
                               <TD class="listDetailOdd" nowrap>
			          <cardex:afficherValeurListeTag name="element" property="utilisateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
			       </TD>
                               <TD class="listDetailOdd" nowrap><bean:write name="element" property="dateAcces"/></TD>
			       <TD class="listDetailOdd" nowrap>
			          <cardex:afficherValeurListeTag name="element" property="site" classe='<%=GlobalConstants.CleListe.SITE_APPLICABLE_TABLE_VALEUR %>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
			       </TD>
                               <TD class="listDetailOdd" align="center" nowrap>
				   <logic:equal name='element' property='action' value='S'>
				     <bean:message key='legende1'/>
				   </logic:equal>
				   <logic:equal name='element' property='action' value='U'>
				     <bean:message key='legende2'/>
				   </logic:equal>
				   <logic:equal name='element' property='action' value='I'>
				     <bean:message key='legende3'/>
				   </logic:equal>
				   <logic:equal name='element' property='action' value='D'>
				     <bean:message key='legende4'/>
				   </logic:equal>
                               </TD>
                          </TR>
                    </logic:iterate>
		</TABLE><!-- End data table -->

		</TD>
	  </TR>
	</TABLE>
	<!-- End Search Kind & nature list -->
    <br clear="left">
<TABLE align="left" width="600" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0" >
 <TR>
    <TD ALIGN="right" VALIGN="center">
      <b><bean:write name='<%=GlobalConstants.RechercheList.ACCES_CURRENT_PAGE%>'/>/<bean:write name='<%=GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES%>'/>&nbsp;page(s)&nbsp;&nbsp;&nbsp;</b>
      <logic:equal name='<%=GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS%>' value='true'>
        <cardex:linkObject object='sujet' page='/acces/searchSujet/begin.do'>
          <b>|&lt;&lt;</b>
        </cardex:linkObject>
        ,
        <cardex:linkObject object='sujet' page='/acces/searchSujet/previous.do'>
          <b>&lt;&lt;</b>
        </cardex:linkObject>
      </logic:equal>
      <logic:equal name='<%=GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS%>' value='false'>
          <b style='color:#999999;' >|&lt;&lt;</b>
          ,
          <b style='color:#999999;' >&lt;&lt;</b>
      </logic:equal>
      ,
      <logic:equal name='<%=GlobalConstants.RechercheList.ACCES_HAS_NEXT%>' value='true'>
        <cardex:linkObject object='sujet' page='/acces/searchSujet/next.do'>
          <b>&gt;&gt;</b>
        </cardex:linkObject>
        ,
        <cardex:linkObject object='sujet' page='/acces/searchSujet/end.do'>
          <b>&gt;&gt;|</b>
        </cardex:linkObject>
      </logic:equal>
      <logic:equal name='<%=GlobalConstants.RechercheList.ACCES_HAS_NEXT%>' value='false'>
        <b style='color:#999999;' >&gt;&gt;</b>
        ,
        <b style='color:#999999;' >&gt;&gt;|</b>
      </logic:equal>
   </TD>
  </TR>
</TABLE>
<!-- End Number of records to display -->
</logic:greaterThan>

