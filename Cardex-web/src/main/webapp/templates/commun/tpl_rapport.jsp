<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="org.apache.struts.Globals" %>

<tiles:useAttribute name="form" id="form" classname="com.lotoquebec.cardex.presentation.model.RapportAffichagePDFForm" scope="session"/>

<SCRIPT language="JavaScript" type="text/javascript">

function ouvrirRapport(){
	var choixRapport = document.getElementsByName("choixRapport")[0];
	var url = construireCriteresURL(choixRapport.value);
	//Pour empêcher le rapport de s'exécuter à chaque action, on met une valeur nulle dans la variable.
	choixRapport.value = "";
	windowOpenLocation(url);
}


<logic:notPresent name='<%= Globals.ERROR_KEY %>' >
<logic:notEqual name="form" property="choixRapport" value="">
	ouvrirRapport();
</logic:notEqual>	
</logic:notPresent>

</SCRIPT>


