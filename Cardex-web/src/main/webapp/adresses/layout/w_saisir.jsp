
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<tiles:useAttribute name="actionSuffixe" id="actionSuffixe" classname="String"/>
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" classname="String"/>
<tiles:useAttribute name="closeAction" id="closeAction" classname="String"/>
<tiles:useAttribute name="formName" id="formName" classname="String"/>

<bean:define id="cleForm" name='<%=formName%>' property="cle" type="String"/>
<bean:define id="siteForm" name='<%=formName%>' property="site" type="String"/>


<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>
<SCRIPT language="JavaScript" type="text/javascript">

function doRefresh() {
	unlockFields();
    soumettre('<%= request.getContextPath()+contexteApplicatif+ "/adresse/rafraichir"+actionSuffixe+".do"%>');
}

function doCancel() {
	post('<%=request.getContextPath()+contexteApplicatif+closeAction+"?cle="+cleForm+"&site="+siteForm%>');
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doRefresh();
	}
}

</SCRIPT>

<tiles:insert page="/templates/adresses/tpl_formulaire.jsp" flush="true">
	<tiles:put name="soumettreURLValider" value='<%=contexteApplicatif+ "/adresse/initialiserValider"+actionSuffixe+".do"%>' />
	<tiles:put name="soumettreURLRechercherValidation" value='<%=contexteApplicatif+ "/rechercherValiderAdresse/initialiserRechercher"+actionSuffixe+".do"%>'/>
</tiles:insert>

