<%-- --------------------------------------------------------------------------
Use case    : S�lection des particularit�s d'un v�hicule.
Description : S�lection des particularit�s d'un v�hicule.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/04/19 20:00:16 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.2 $, $Date: 2002/04/19 20:00:16 $, $Author: mlibersan $
Cr�ation.
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
  //-- Fonction d�clar�e dans lq_scripts.js
    selectMultiboxes();
    unlockFields();
  soumettre('<%= request.getContextPath() + "/vehicule/particularites/update.do"%>');
}

function doOkAudit() {
    //Sauvegarde avec l'audit des changements
    selectMultiboxes();
    unlockFields();
  soumettre('<%= request.getContextPath() + "/vehicule/particularites/update/audit.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/vehicule/show.do?site=<bean:write name="particularites" property="lienSite"/>&cle=<bean:write name="particularites" property="lien"/>';
}

</SCRIPT>

<title>Caract�ristiques</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
    <tr>
       <td align="center">
          <html:form action="/vehicule/particularites/update" >
            <tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="i_pt_cle_t" />
				<tiles:put name='tableWith' content="505" />
			</tiles:insert> 
            <jsp:include page="/templates/particularites/tpl_particularites_formulaire.jsp" flush="true" />
          </html:form>
       </td>
   </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>