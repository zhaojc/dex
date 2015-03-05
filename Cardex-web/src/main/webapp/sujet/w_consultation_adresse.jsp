<%-- --------------------------------------------------------------------------
Use case    : Sélection des caractéristiques d'un individu.
Description : Sélection des caractéristiques d'un individu.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/19 20:00:17 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.4 $, $Date: 2002/04/19 20:00:17 $, $Author: mlibersan $
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

function disbleBoutonValidationEtRecherche(){
	var pays = document.getElementsByName("pays")[0];
	
	if (pays.value != "702"){
		disableButton("validationAdresse");
		disableButton("rechercheValidationAdresse");
	}
}

addEvent(window, 'load', disbleBoutonValidationEtRecherche);

function doRefresh() {
	unlockFields();
    soumettre('<%= request.getContextPath() + "/sujet/adresse/show/refresh.do"%>');
}

function doCancel() {
    window.location = '<%=request.getContextPath()%>/sujet/show.do?site=<bean:write name="adresse" property="lienSite"/>&cle=<bean:write name="adresse" property="lien"/>';
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doRefresh();
	}
}

</SCRIPT>

<title>Adresse</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="750" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
    <tr>
       <td align="center">
		  <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />       
		         
          <html:form action="/sujet/adresse/update" >
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="adresse_t" />
				<tiles:put name='tableWith' content="750" />
			</tiles:insert>               
          	<tiles:insert page="/templates/adresses/tpl_formulaire.jsp" flush="true">
          		<tiles:put name="soumettreURLSauvegarde" value="/sujet/adresse/updateValidation.do" />
          		<tiles:put name="soumettreURLRechercherValidation" value="/sujet/adresse/rechercheAdresseDefault.do" />
          	</tiles:insert>            
          </html:form>
       </td>
   </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>