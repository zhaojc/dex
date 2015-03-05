<%-- --------------------------------------------------------------------------
Use case    : Réponse du système suite à une capture.
Description : Écran affichant la réponse du système suite à une soumission d'une
              capture de photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/03/12 21:41:22 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , $Date: 2002/02/20 23:29:43 , Author: abruno-boucher
Création HTML.

$Revision: 1.6 $, $Date: 2002/03/12 21:41:22 $, $Author: mlibersan $
Ajout javascript pour sélections dynamiques.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

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
    performSelectAll();
    soumettre('<%= request.getContextPath() + "/dossier/jeux/update.do"%>');
}

function doCancel() {
    history.go(-1);
}

</SCRIPT>


<title>Jeux</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">
          <html:form action="/dossier/jeux/update" >
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="tabpage_jeu" />
				<tiles:put name='tableWith' content="505" />
			</tiles:insert>           
            <template:insert template='/templates/jeux/tpl_jeux_formulaire.jsp'/>
          </html:form>
        </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>