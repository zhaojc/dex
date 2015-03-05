<%-- --------------------------------------------------------------------------
Use case    : Réponse négative du système.
Description : Un appel de fenêtre de l'enveloppe VB est effectué, afin
              d'afficher une reponse négative du système.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/03/04 17:39:51 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.4 $, $Date: 2002/03/04 17:39:51 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ page import="org.apache.struts.util.RequestUtils" %>

<HTML>
    <SCRIPT LANGUAGE='javascript'>
        window.location.replace('<%= RequestUtils.absoluteURL(request,"/autres/w_multimedia_reponse.jsp") %>')
    </SCRIPT>
</HTML>