<%-- --------------------------------------------------------------------------
Fermeture de session avec invalidation de la session pour libérer la mémoire.
--------------------------------------------------------------------------- --%>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_consultationDossier_styles.css"%>'>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>
<% 
  request.getSession().invalidate();
%>

<TITLE></TITLE>
</HEAD>
<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="windowClose();" >
</body>
</HTML>