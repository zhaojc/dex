<%-- --------------------------------------------------------------------------
Use case    : Impression d'une narration.
Description : Écran d'impression d'une narration.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/08 16:33:50 $

History     : Voir ci-dessous.

$Revision: 1.4 $, $Date: 2002/04/08 16:33:50 $, $Author: mdemers $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>


<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<title><bean:message key="titre.application.cardex"/></title>

</head>
<body leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<DIV align="center" STYLE="overflow:none; width:100; height:100; background-color:transparent; z-index: 1; position: absolute; right: 300px; top: 1px;" >
     <html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/>
</DIV>

  <html:form action="/sujet/narration/update"  >
    <html:hidden  name="narration" property="sujet.cle" />
    <html:hidden  name="narration" property="sujet.site" />
    <jsp:include page="/templates/narrations/tpl_narrations_impression_sujet.jsp" flush="true" />
  </html:form>

</body>
</html>
