
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />
<tiles:useAttribute name="formNameRetour" id="formNameRetour" />

<bean:define id="cleForm" name='<%=formNameRetour.toString()%>' property="cle"/>
<bean:define id="siteForm" name='<%=formNameRetour.toString()%>' property="site"/>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="imagetoolbar" content="no" />

<SCRIPT language="VBScript">
   Dim LayerNum 'Variable globale pour l'objet d'annotation (Notate)
</SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

function doCancel() {
    post('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()+closeAction.toString()+"?cle="+cleForm.toString()+"&site="+siteForm.toString()%>');
}

var photoLoad = false;

function soumettrePhoto(){

	if (photoLoad){
		soumettre('<%=request.getContextPath()+preContexteApplicatif.toString()+contexteApplicatif.toString()%>/image/upload.do');
	}
}

</SCRIPT>

<input type="hidden" name='mustDeleteFile' value="true" />
<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
			
<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
	<tiles:put name='keyTitre' content="tabpage_photo" />
</tiles:insert>
			
<jsp:include page="/templates/photo/tpl_image_upload_formulaire.jsp" flush="true" />
