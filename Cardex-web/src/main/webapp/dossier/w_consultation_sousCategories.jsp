<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>
<SCRIPT language="JavaScript" type="text/javascript">

function doOk() {
    selectMultiboxes();
    unlockFields();
    soumettre('<%= request.getContextPath() + "/dossier/sousCategories/update.do"%>');
}

function doSoumettreRafraichir() {
    selectMultiboxes();
    unlockFields();
    soumettre('<%= request.getContextPath() + "/dossier/sousCategories/rafraichir.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="sousCategories" property="lienSite"/>&cle=<bean:write name="sousCategories" property="lien"/>';
}

</SCRIPT>


<title><bean:message key='sous.categories'/></title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">
      <html:form action="/dossier/sousCategories/update" >
		<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
			<tiles:put name='keyTitre' content="sous.categories" />
			<tiles:put name='tableWith' content="700" />
		</tiles:insert> 
      
        <jsp:include page="/templates/sousCategories/tpl_sousCategories_formulaire.jsp" flush="true" />
      </html:form>
    </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>