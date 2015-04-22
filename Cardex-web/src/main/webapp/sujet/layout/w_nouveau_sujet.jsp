
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT LANGUAGE="JavaScript">

function doOk() {
	//-- Fonction déclarée dans lq_scripts.js
	unlockFields();
	soumettre('<%= request.getContextPath() + preContexteApplicatif.toString() + contexteApplicatif.toString() + "/nouveau/sujet/save.do"%>');
}

function doClose() {
	windowClose();
}

function doPrint() {
	alert("Fonction non disponible pour le moment ...");
}

function initRequest() {

    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
        
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function doVerificationNumeroFiche() {

    if (document.forms(0).nom.value != "" 
    && document.forms(0).prenom.value != ""
    && document.forms(0).dateNaissance.value != "") {

        var url = "<%= request.getContextPath() %>/VerificationSujetExistant";
        url += "?NOM="+document.forms(0).nom.value;
        url += "&PRENOM="+document.forms(0).prenom.value;
        url += "&DATE_NAISSANCE="+document.forms(0).dateNaissance.value;

        var req = initRequest();
        
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
			        var numeroFiche = req.responseXML.firstChild.text;
			        
			        if (numeroFiche != "" && numeroFiche != null){
			        	message("Ce sujet existe déjà avec le(s) numéro(s) de fiche : '"+numeroFiche+"'.");
			        }
                }
            }
        };
		req.open("GET", url, true);
        req.send(null);
    }
}

</SCRIPT>

<tiles:insert page="/templates/sujet/tpl_sujet_formulaire.jsp" flush="true">
	<tiles:put name="urlSecuriteSauvegarde" value='<%=preContexteApplicatif.toString() + contexteApplicatif.toString() + "/nouveau/sujet/save.do"%>' />
	<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
</tiles:insert>
