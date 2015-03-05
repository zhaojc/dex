<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="actionSuffixe" id="actionSuffixe" classname="String"/>
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" classname="String"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_styles.css" />

<STYLE type="text/css">
  .functionButton {
    width: 40px;
    font-weight: bold;
    text-align: center;
  }
</STYLE>
<SCRIPT language="JavaScript" type="text/javascript">
	
	function doChoisir() {
		
		if (isChoixValide()){
			unlockFields();
	    	soumettre('<%= request.getContextPath()+contexteApplicatif+"/rechercherValiderAdresse/choisir"+actionSuffixe+".do"%>');
	    }
	}
	
	function doSoumettreRafraichir() {
	  //-- Fonction déclarée dans lq_scripts.js
		soumettre('<%= request.getContextPath()+contexteApplicatif+ "/rechercherValiderAdresse/rafraichir"+actionSuffixe+".do"%>');
	}	
	
	function isChoixValide(){
		var choix = document.getElementsByName("choixAdresse")[0];
		return choix != undefined;
	}
	
	function doCancel() {
		unlockFields();
	    soumettre('<%= request.getContextPath()+contexteApplicatif+ "/rechercherValiderAdresse/retourRechercher"+actionSuffixe+".do"%>');
	}
	
	function choixListeAutomatique(){
	
		if (insertionCaractereListeAutomatique){
			doRefresh();
		}
	}

</SCRIPT>

<tiles:insert page="/templates/adresses/tpl_rechercherValider_formulaire.jsp" flush="true">
	<tiles:put name="soumettreURLRechercher" value='<%= contexteApplicatif + "/rechercherValiderAdresse/rechercher"+actionSuffixe+".do"%>' />
	<tiles:put name="soumettreURLChoisir" value='<%= contexteApplicatif + "/rechercherValiderAdresse/choisir"+actionSuffixe+".do"%>' />
</tiles:insert>	
