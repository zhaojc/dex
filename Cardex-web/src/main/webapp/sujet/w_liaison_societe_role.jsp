<%-- --------------------------------------------------------------------------
Use case    : Liaison d'une société.
Description : Menu déroulant définissant le rôle d'association.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/24 12:31:54 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.2, Date: 2002/02/14 17:12:06, Author: pcaron
Ajout des commentaires de la page JSP.

Revision: 1.4 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.3 $, $Date: 2002/04/24 12:31:54 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript">

</script>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>

<title><bean:message key='tabpage_role'/></title>
</head>
<SCRIPT language="JavaScript" type="text/javascript">
  function doClose() {
	parent.window.location='<cardex:writeObjectURL object="rechercheSociete" page="/sujet/societe/search/refresh.do" />';
  }
	function soumettreForm() {
		soumettre('<%= request.getContextPath() + "/sujet/societe/link.do"%>');
	}
</SCRIPT>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >
                    <html:form action='/sujet/societe/link.do' >
                    	<jsp:include page="/templates/tpl_lien_role.jsp" flush="true" />
                    </html:form>
</BODY>
</HTML>
