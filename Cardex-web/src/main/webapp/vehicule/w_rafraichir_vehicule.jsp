<%-- --------------------------------------------------------------------------
Description : Commande de redirection pour forcer le rafraichissement apr�s
                une action externe au navigateur, comme le t�l�chargement
                d'une photo.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/03/27 01:35:43 $

History     : Voir ci-dessous.

$Revision: 1.2 $, $Date: 2002/03/27 01:35:43 $, $Author: mdemers $
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<script type="text/javascript">

deleteFile('<bean:write name="photo" property="filePathDoubleSlash"/>');
deleteFile('<%=GlobalConstants.FICHER_IMAGE_TEMPORAIRE%>');

post('<%=request.getContextPath()%>/vehicule/show.do?site=<bean:write name='vehicule' property="site"/>&cle=<bean:write name='vehicule' property="cle"/>&motPasse=<bean:write name='vehicule' property="motPasse"/>&timesatmp=<%=System.currentTimeMillis()%>');

</script>