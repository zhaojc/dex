<%-- --------------------------------------------------------------------------
Use case    : Affichage d'une photo.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>

<title>Photos</title>
</head>

<body  link="#095B97"leftmargin="0" rightmargin="0" topmargin="5" marginheight="5" marginwidth="0" 
    oncontextmenu="return false;" >

<!-- POSITIONING TABLE -->

<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">
  	    <DIV align="center" id="photoImprimer" STYLE="overflow:auto; width:750; height:950; z-index: 1; position: absolute; left: 10px; top: 10px; " >
 		<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='photo' property='lienElement' />&SITE=<bean:write name='photo' property='lienSiteElement' />&EXTENSION=<bean:write name='photo' property='extension' />&GrandeImage=true" name="photo" onload="this.style.zoom='100%'; " GALLERYIMG="no">        
	    </DIV>
	</td>
  </tr>
</table>

<!-- END POSITIONING TABLE -->

</BODY>
</HTML>