<!-- --------------------------------------------------------------------------
Use case    : Capture de photo.
Description : Écran utilisé afin de faire de la capture d'image avec une caméra
              numérique.
--------------------------------------------------------------------------- -->

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<meta http-equiv="imagetoolbar" content="no" />

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />
<tiles:useAttribute name="closeAction" id="closeAction" />
<tiles:useAttribute name="formNameRetour" id="formNameRetour" />

<bean:define id="cleForm" name='<%=formNameRetour.toString()%>' property="cle"/>
<bean:define id="siteForm" name='<%=formNameRetour.toString()%>' property="site"/>

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

function addEnctype(){
	document.forms(0).encoding="multipart/form-data";
}

//addEvent( window, 'load', addEnctype );

</SCRIPT>
<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />

<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
	<tiles:put name='keyTitre' content="capturer_t" />
</tiles:insert>

<jsp:include page="/templates/photo/tpl_photo_upload_formulaire.jsp" flush="true" />

