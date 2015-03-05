<%-- --------------------------------------------------------------------------
Description : Commande de redirection pour forcer le rafraichissement après
                une action externe au navigateur, comme le téléchargement
                d'une photo.
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />
<tiles:useAttribute name="formNameRetour" id="formNameRetour" />

<bean:define id="cleForm" name='<%=formNameRetour.toString()%>' property="cle"/>
<bean:define id="siteForm" name='<%=formNameRetour.toString()%>' property="site"/>
 
<jsp:include page="/commun/commun.jsp" flush="true" />

<script type="text/javascript">

deleteFile('<bean:write name="photo" property="filePathDoubleSlash"/>');
deleteFile('<%=GlobalConstants.FICHER_IMAGE_TEMPORAIRE%>');

post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()+"?cle="+cleForm.toString()+"&site="+siteForm.toString()%>');

</script>