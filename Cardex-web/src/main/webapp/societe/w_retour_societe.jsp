<%-- --------------------------------------------------------------------------
Description : Commande de redirection pour forcer le rafraichissement après
                une action externe au navigateur
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.3 $, $Date: 2002/04/10 14:37:03 $

History     : Voir ci-dessous.

$Revision: 1.3 $, $Date: 2002/04/10 14:37:03 $, $Author: mlibersan $
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<script type="text/javascript">

post('<%=request.getContextPath()%>/societe/show.do?site=<bean:write name='societe' property="site"/>&cle=<bean:write name='societe' property="cle"/>&motPasse=<bean:write name='societe' property="motPasse"/>&timesatmp=<%=System.currentTimeMillis()%>');

</script>