<%-- --------------------------------------------------------------------------
Description : Commande de redirection pour forcer le rafraichissement après
                une action externe au navigateur, comme le téléchargement
                d'une photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/10 14:37:07 $

History     : Voir ci-dessous.

$Revision: 1.3 $, $Date: 2002/04/10 14:37:07 $, $Author: mlibersan $
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<script type="text/javascript">

post(''<%=request.getContextPath()%>/sujet/show.do?site=<bean:write name='sujet' property="site"/>&cle=<bean:write name='sujet' property="cle"/>&motPasse=<bean:write name='sujet' property="motPasse"/>&timesatmp=<%=System.currentTimeMillis()%>');

</script>