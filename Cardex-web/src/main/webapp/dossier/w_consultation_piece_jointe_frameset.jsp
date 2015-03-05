<%-- --------------------------------------------------------------------------
Use case    : Page de consultation des pièces jointes.
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/03/26 16:36:47 $

History     : Voir ci-dessous.

$Revision: 1.2 $, $Date: 2002/03/26 16:36:47 $, $Author: abruno-boucher $
Création.

--------------------------------------------------------------------------- --%>

<html>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title></title>

</head>

<frameset framespacing="2" border="false" frameborder="0" rows="60,*">
  <frame name="piece_controls" scrolling="no" noresize target="controls" 
    src="<%= request.getContextPath() %>/dossier/w_consultation_piece_jointe_controls.jsp"
    marginwidth="0" marginheight="0" />
  <frame name="piece_content" scrolling="yes" noresize target="content" 
    src="<%= request.getContextPath() %>/dossier/w_consultation_piece_jointe.jsp" />
</frameset>

<noframes>
  <body>
  <p>Cette page utilise des cadres, mais votre navigateur ne les prend pas en charge.</p>
  </body>
</noframes>

</html>
