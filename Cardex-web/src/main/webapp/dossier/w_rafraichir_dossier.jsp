<%-- --------------------------------------------------------------------------
Description : Commande de redirection pour forcer le rafraichissement après
                une action externe au navigateur, comme le téléchargement
                d'une photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/10 14:36:57 $

History     : Voir ci-dessous.

$Revision: 1.3 $, $Date: 2002/04/10 14:36:57 $, $Author: mlibersan $
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<script type="text/javascript">

deleteFile('<bean:write name="photo" property="filePathDoubleSlash"/>');
deleteFile('<%=GlobalConstants.FICHER_IMAGE_TEMPORAIRE%>');

post('<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name='dossier' property="site"/>&cle=<bean:write name='dossier' property="cle"/>&motPasse=<bean:write name='dossier' property="motPasse"/>&timesatmp=<%=System.currentTimeMillis()%>');

</script>


