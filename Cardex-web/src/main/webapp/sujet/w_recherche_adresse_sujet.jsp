<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>

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
    	soumettre('<%= request.getContextPath() + "/sujet/adresse/choixRechercheAdresse.do"%>');
    }
}

function isChoixValide(){
	var choix = document.getElementsByName("choixAdresse")[0];
	return choix != undefined;
}

function doCancel() {
	unlockFields();
    soumettre('<%=request.getContextPath()+"/sujet/adresse/retourRechercheAdresse.do"%>');
}

function choixListeAutomatique(){

	if (insertionCaractereListeAutomatique){
		doRefresh();
	}
}

</SCRIPT>

<title>Recherche adresse</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
    <tr>
       <td align="center">
		  <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />       
		         
          <html:form action="/sujet/adresse/rechercheAdresse" >
            <tiles:insert page="/templates/adresses/tpl_recherche_validation_adresse_formulaire.jsp" flush="true">
				<tiles:put name="soumettreRechercher" value="/sujet/adresse/rechercheAdresse.do" />
				<tiles:put name="soumettreChoix" value="/sujet/adresse/choixRechercheAdresse.do" />
			</tiles:insert>	
			<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
          </html:form>
       </td>
   </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>