<%-- --------------------------------------------------------------------------
Use case    : Affichage d'une évaluation.
Description : Consultation et mise à jour d'une évaluation du Comité de vigilance.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.4 $, $Date: 2002/04/19 20:00:17 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.4 $, $Date: 2002/04/19 20:00:17 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>

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

	function doOk() {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
	    unlockFields();
	    soumettre('<%=request.getContextPath()%>/dossier/evaluation/update.do');
	}

	function doAjoutFrequence(indexMise) {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
	    document.forms(0).indexMise.value = indexMise;
		soumettre('<%=request.getContextPath()%>/evaluation/ajouterFrequenceConsultation.do');
	}
	
	function doRetraitFrequence(indexMise, indexFrequenceVisites) {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
	    document.forms(0).indexMise.value = indexMise;
	    document.forms(0).indexFrequenceVisites.value = indexFrequenceVisites;
		soumettre('<%=request.getContextPath()%>/evaluation/retirerFrequenceConsultation.do');
	}
	
	function doAjoutMiseEvaluation() {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
		soumettre('<%=request.getContextPath()%>/evaluation/ajouterMiseEvaluationConsultation.do');
	}
	
	function doRetraitMiseEvaluation(indexMise) {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
	    document.forms(0).indexMise.value = indexMise;
		soumettre('<%=request.getContextPath()%>/evaluation/retirerMiseEvaluationConsultation.do');
	}
</SCRIPT>

<title>Évaluation</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onload="document.body.style.cursor = 'default';">

<!-- POSITIONING TABLE -->
          <html:form action="/dossier/evaluation/update" >
	        <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
	        <jsp:include page='/commun/aide.jsp' flush="true"/>
          
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="titre.evaluation" />
				<tiles:put name='tableWith' content="706" />
			</tiles:insert>               
		    <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />       
          	<tiles:insert page="/templates/evaluation/tpl_evaluation_formulaire.jsp" flush="true">
          		<tiles:put name="soumettreURLSauvegarde" value="/dossier/evaluation/update.do" />
          	</tiles:insert>            
          </html:form>

<!-- END POSITIONING TABLE -->

</BODY>
</HTML>