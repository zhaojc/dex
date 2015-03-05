<!-- --------------------------------------------------------------------------
Description : Écran utilisé pour visualiser la transmission des caméras
              de surveillance.
--------------------------------------------------------------------------- -->
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="VBScript">
   Dim LayerNum 'Variable globale pour l'objet d'annotation (Notate)
</SCRIPT>

<title>Capture</title>
</head>
<SCRIPT language="JavaScript" type="text/javascript">

function doCancel() {
	window.close();
}
</SCRIPT>

<body  link="#095B97" leftmargin="2" rightmargin="0" topmargin="2" marginheight="2" marginwidth="2">
  <html:form action='/dossier/photo/upload.do' enctype="multipart/form-data" >
	  	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		  <tiles:put name='keyTitre' content="camera_t" />
		</tiles:insert>
  
          <jsp:include page="/templates/photo/tpl_camera.jsp" flush="true" />
  </html:form>

</BODY>
</HTML>
