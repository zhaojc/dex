<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<SCRIPT language="JavaScript" type="text/javascript">
  function doClose() {
	windowClose();
  }

</SCRIPT>
<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page="/templates/tpl_lien_role.jsp" flush="true" />
