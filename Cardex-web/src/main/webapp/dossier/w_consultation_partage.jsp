<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />
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
    //showSelectOptions()
    unlockFields();
    performSelectAll();
    soumettre('<%= request.getContextPath() + "/dossier/partage/update.do"%>');
}

function doSoumettreRafraichir() {
    unlockFields();
    performSelectAll();
    soumettre('<%= request.getContextPath() + "/dossier/partage/rafraichir.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="partage" property="lienSite"/>&cle=<bean:write name="partage" property="lien"/>';
}

</SCRIPT>


<title><bean:message key='tabpage_partage'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">
      <html:form action="/dossier/partage/update" >
		<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
			<tiles:put name='keyTitre' content="tabpage_partage" />
			<tiles:put name='tableWith' content="815" />
		</tiles:insert>      
        <jsp:include page="/templates/partage/tpl_partage_formulaire.jsp" flush="true" />
      </html:form>
    </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>