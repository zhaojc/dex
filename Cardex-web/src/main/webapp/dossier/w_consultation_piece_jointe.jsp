<%-- --------------------------------------------------------------------------
Use case    : Affichage d'une pièce jointe.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">

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

function dimensionner(objet) {
//--Réajustement de l'image pour l'impression sur une feuille 8,5x11
	if (objet.width > 650){
	   objet.width = "650";
	}
	if (objet.height > 900){
	   objet.height = "900";
	}
}

</SCRIPT>


<title>Pièce jointe</title>
</head>

<body  link="#095B97"
    leftmargin="0" rightmargin="0" topmargin="5" marginheight="5" marginwidth="0" >

<!-- POSITIONING TABLE -->

<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">
  	    <DIV align="center" id="photoImprimer" STYLE="overflow:none; width:670; height:920; z-index: 1; position: absolute; left: 10px; top: 10px; " >
			<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='photo' property='lienElement' />&SITE=<bean:write name='photo' property='lienSiteElement' />&EXTENSION=<bean:write name='photo' property='extension' />&GrandeImage=true" name="photo" onload="this.style.zoom='100%'; dimensionner(photo); " GALLERYIMG="no">        
	    </DIV>   
	</td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->

</BODY>
</HTML>