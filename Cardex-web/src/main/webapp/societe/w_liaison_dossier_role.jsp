<%-- --------------------------------------------------------------------------
Use case    : Liaison d'une auto-exclusion.
Description : Menu d�roulant d�f�nissant l'affinit� de l'auto-exclusion a lier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/04/24 12:31:46 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.2, Date: 2002/02/14 17:12:06, Author: pcaron
Ajout des commentaires de la page JSP.

Revision: 1.4 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.7 $, $Date: 2002/04/24 12:31:46 $, $Author: mlibersan $
Derniers commentaires � jour.
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
	parent.window.location='<cardex:writeObjectURL object="rechercheDossier" page="/societe/dossier/search/refresh.do" />';
  }

	function soumettreForm() {
		soumettre('<%= request.getContextPath() + "/societe/dossier/link.do"%>');
	}
	
</SCRIPT>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

                    <html:form action='/societe/dossier/link.do' >
                    	<jsp:include page="/templates/tpl_lien_role.jsp" flush="true" />
                    </html:form>
</BODY>
</HTML>
