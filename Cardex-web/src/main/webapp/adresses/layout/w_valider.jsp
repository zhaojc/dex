<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="actionSuffixe" id="actionSuffixe" classname="String"/>
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" classname="String"/>
<tiles:useAttribute name="closeAction" id="closeAction" classname="String"/>
<tiles:useAttribute name="formName" id="formName" classname="String"/>

<bean:define id="cleForm" name='<%=formName%>' property="cle"/>
<bean:define id="siteForm" name='<%=formName%>' property="site"/>


<SCRIPT language="JavaScript" type="text/javascript">

function doRefresh() {
	unlockFields();
    soumettre('<%= request.getContextPath()+contexteApplicatif+ "/adresse/rafraichirValider"+actionSuffixe+".do"%>');
}

function doCancel() {
	unlockFields();
    post('<%=request.getContextPath()+contexteApplicatif+closeAction+"?cle="+cleForm+"&site="+siteForm%>');
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doRefresh();
	}
}

</SCRIPT>

<html:form action='<%=contexteApplicatif+ "/adresse/choisirValider"+actionSuffixe+".do"%>' >
	<tiles:insert page="/templates/adresses/tpl_validation_formulaire.jsp" flush="true">
		<tiles:put name="soumettreURLValider" value='<%=contexteApplicatif+ "/adresse/valider"+actionSuffixe+".do"%>' />
		<tiles:put name="soumettreURLChoisir" value='<%=contexteApplicatif+ "/adresse/choisirValider"+actionSuffixe+".do"%>' />
	</tiles:insert>	
</html:form>
