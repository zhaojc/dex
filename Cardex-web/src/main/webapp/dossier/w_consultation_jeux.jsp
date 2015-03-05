<%-- --------------------------------------------------------------------------
Use case    : R�ponse du syst�me suite � une capture.
Description : �cran affichant la r�ponse du syst�me suite � une soumission d'une
              capture de photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/18 21:17:41 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , $Date: 2002/02/20 23:29:43 , Author: abruno-boucher
Cr�ation HTML.

$Revision: 1.4 $, $Date: 2002/04/18 21:17:41 $, $Author: mlibersan $
Ajout javascript pour s�lections dynamiques.
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
     soumettre('<%= request.getContextPath() + "/dossier/jeux/update.do"%>');
}

function doRafraichir() {
    selectMultiboxes();
    unlockFields();
     soumettre('<%= request.getContextPath() + "/dossier/jeux/rafraichir.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="jeux" property="lienSite"/>&cle=<bean:write name="jeux" property="lien"/>';
}

</SCRIPT>


<title>Jeux</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

          <html:form action="/dossier/jeux/update" >
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="tabpage_jeu" />
				<tiles:put name='tableWith' content="505" />
			</tiles:insert>           
            <jsp:include page="/templates/jeux/tpl_jeux_formulaire.jsp" flush="true" />
          </html:form>
        </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>