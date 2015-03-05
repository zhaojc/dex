<%-- --------------------------------------------------------------------------
Use case    : Sélection des caractéristiques d'un individu.
Description : Sélection des caractéristiques d'un individu.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/25 15:57:39 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.6 $, $Date: 2002/04/25 15:57:39 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

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
  soumettre('<%= request.getContextPath() + "/sujet/caracteristiques/update.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/sujet/show.do?site=<bean:write name="caracteristiques" property="lienSite"/>&cle=<bean:write name="caracteristiques" property="lien"/>';
}

</SCRIPT>

<title>Caractéristiques</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
    <tr>
       <td align="center">
          <html:form action="/sujet/caracteristiques/update" >
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="l_cr_cle_t" />
				<tiles:put name='tableWith' content="505" />
			</tiles:insert>           
            <jsp:include page="/templates/caracteristiques/tpl_caracteristiques_formulaire.jsp" flush="true" />
          </html:form>
       </td>
   </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>