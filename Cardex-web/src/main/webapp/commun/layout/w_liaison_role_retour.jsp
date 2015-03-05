<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<SCRIPT language="JavaScript" type="text/javascript">
  function doClose() {
	post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()%>');
  }

</SCRIPT>

<jsp:include page="/templates/role/tpl_lien_role.jsp" flush="true" />
