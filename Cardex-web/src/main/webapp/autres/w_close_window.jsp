<%-- --------------------------------------------------------------------------
Use case    : Consultation de dossier.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la consultation d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/03/19 23:31:59 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.1 $, $Date: 2002/03/19 23:31:59 $, $Author: mlibersan $
Ajout des scripts de gestion de l'Assistant Date-Heure.
--------------------------------------------------------------------------- --%>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>
<SCRIPT type="text/javascript">
windowClose();
</SCRIPT>