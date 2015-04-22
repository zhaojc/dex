<%-- --------------------------------------------------------------------------
Use case    : Téléchargement d'image.
Description : Écran utilisé afin de faire de la capture d'image avec une caméra
              numérique.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/30 12:18:03 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/30 12:18:03 $, $Author: mlibersan $
Ajout des definitions taglib manquant.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- meta http-equiv="Page-Exit" content="progid:DXImageTransform.Microsoft.gradientWipe(duration=2)" -->

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="VBScript">
   Dim LayerNum 'Variable globale pour l'objet d'annotation (Notate)
</SCRIPT>

<title>Pièces jointes</title>
</head>
<SCRIPT>

function doCancel() {
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="photo" property="lienSite"/>&cle=<bean:write name="photo" property="lien"/>';
}

var photoLoad = false;

function soumettrePhoto(){

	if (photoLoad){
		soumettre('<%=request.getContextPath()%>/dossier/pieceJointe/upload.do');
	}
}

function soumettreDocument(){

	var oas = new ActiveXObject("Scripting.FileSystemObject");
	var file = oas.getFile( document.getElementsByName("sourceFile")[0].value );

	if (file.size > 10000000)
		message("<bean:message key='erreur_fichier_document' />");
	else
		soumettre('<%=request.getContextPath()%>/dossier/pieceJointe/upload.do');
}

</SCRIPT>

<body  link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >

  <html:form action='/dossier/pieceJointe/upload.do' enctype="multipart/form-data" >
  		<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
  		
  		<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
			<tiles:put name='keyTitre' content="tabpage_documents" />
		</tiles:insert>
		 
        <jsp:include page="/templates/photo/tpl_document_upload_formulaire.jsp" flush="true" />
  </html:form>

</BODY>
</HTML>
