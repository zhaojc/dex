<%-- --------------------------------------------------------------------------
Use case    : Impression du formulaire pour les dossiers avec inscription.
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/04/16 19:51:19 $

History     : Voir ci-dessous.

$Revision: 1.1 $, $Date: 2002/04/16 19:51:19 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title></title>

</head>

<frameset framespacing="0" border="false" frameborder="0" rows="*,70">
  <frame name="print_content" scrolling="yes" noresize target="content"
    src="<%= request.getContextPath() %>/dossier/w_impression_autoexclusion_template.jsp"
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
