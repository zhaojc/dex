<%-- --------------------------------------------------------------------------
Use case    : Impression d'un sujet (utilisé par la galerie).
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/04/10 16:30:46 $

History     : Voir ci-dessous.

$Revision: 1.2 $, $Date: 2002/04/10 16:30:46 $, $Author: mdemers $
Création.

--------------------------------------------------------------------------- --%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta http-equiv="imagetoolbar" content="no" />
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title></title>

</head>

<frameset framespacing="0" border="false" frameborder="0" rows="*,70">
  <frame name="print_content" scrolling="yes" noresize target="content"
    src="<%= request.getContextPath() %>/sujet/w_impression_sujet_fiche.jsp"
  marginwidth="0" marginheight="0" />
  <frame name="print_controls" scrolling="no" noresize target="controls"
    src="<%= request.getContextPath() %>/autres/w_impression_controls.jsp" />
</frameset>

<noframes>
  <body>
  <p>Cette page utilise des cadres, mais votre navigateur ne les prend pas en charge.</p>
  </body>
</noframes>

</html>
