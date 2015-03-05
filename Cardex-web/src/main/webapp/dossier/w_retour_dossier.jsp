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
<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT type="text/javascript">
	post('<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name='dossier' property="site"/>&cle=<bean:write name='dossier' property="cle"/>&motPasse=<bean:write name='dossier' property="motPasse"/>&timesatmp=<%=System.currentTimeMillis()%>');
</SCRIPT>



