<%-- --------------------------------------------------------------------------
Use case    : R�ponse positive du syst�me.
Description : Un appel de fen�tre de l'enveloppe VB est effectu�, afin
              d'afficher une reponse positive du syst�me.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/03/04 18:58:28 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/03/04 18:58:28 $, $Author: mdemers $
Derniers commentaires � jour.
--------------------------------------------------------------------------- --%>

<%@ page import="org.apache.struts.util.RequestUtils" %>

<HTML>
    <SCRIPT LANGUAGE='javascript'>
        window.location.replace('<%= RequestUtils.absoluteURL(request,"/autres/w_multimedia_reponse.jsp") %>')
    </SCRIPT>
</HTML>