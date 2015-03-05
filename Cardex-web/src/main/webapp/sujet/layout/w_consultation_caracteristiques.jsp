
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />
<tiles:useAttribute name="formName" id="formName" />

<bean:define id="cleForm" name='<%=formName.toString()%>' property="cle"/>
<bean:define id="siteForm" name='<%=formName.toString()%>' property="site"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>
<SCRIPT language="JavaScript" type="text/javascript">

function doOk() {
  //-- Fonction déclarée dans lq_scripts.js
  performSelectAll();
  soumettre('<%= request.getContextPath() +preContexteApplicatif.toString()+contexteApplicatif.toString()+"/caracteristiques/update.do"%>');
}

function doCancel() {
    post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()+"?cle="+cleForm.toString()+"&site="+siteForm.toString()%>');
}

addEvent( window, 'load', initSelectOptions );

</SCRIPT>

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
    <tr>
       <td align="center">
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="l_cr_cle_t" />
				<tiles:put name='tableWith' content="505" />
			</tiles:insert>          
            <jsp:include page="/templates/caracteristiques/tpl_caracteristiques_formulaire.jsp" flush="true" />
       </td>
   </tr>
</table>
<!-- END POSITIONING TABLE -->
