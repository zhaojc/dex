<%-- --------------------------------------------------------------------------
Use case    : Page de consultation des photos.
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/03/26 16:36:47 $

History     : Voir ci-dessous.

$Revision: 1.2 $, $Date: 2002/03/26 16:36:47 $, $Author: abruno-boucher $
Création.

--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title><bean:message key='tabpage_galery'/></title>

<jsp:include page='/commun/commun.jsp' flush="true"/>

</head>
<frameset framespacing="2" border="false" frameborder="0" rows="60,*">
  <frame name="piece_controls" scrolling="no" noresize target="controls" 
    src="<%= request.getContextPath() %>/galerie/w_consultation_photo_controls.jsp"
    marginwidth="0" marginheight="0" />
  <frame name="piece_content" scrolling="yes" noresize target="content" 
    src="<%= request.getContextPath() %>/galerie/w_consultation_photo.jsp" />
</frameset>
<noframes>
  <body>
  <p>Cette page utilise des cadres, mais votre navigateur ne les prend pas en charge.</p>
  </body>
</noframes>

</html>
