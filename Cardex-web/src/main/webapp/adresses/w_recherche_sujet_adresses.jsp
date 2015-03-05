<%-- --------------------------------------------------------------------------
Use case    : Recherche de sujet
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la recherche d'un sujet.
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.12 $, $Date: 2002/04/08 16:33:53 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.12 $, $Date: 2002/04/08 16:33:53 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>
<jsp:include page="w_recherche_adresses_script.jsp" flush="true" />

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
	  //-- Fonction déclarée dans lq_scripts.js
		soumettre('<%= request.getContextPath() + "/adresses/rechercheSujet/rafraichir.do"%>');
	}	
	
	function doPrint() {
		//-- Fonction déclarée dans lq_scripts.js
		//Impression de la page courante des résultats
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_ADRESSE_SUJETS %>"; 
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport;
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
	}
	
	function choixListeAutomatique(){
	
		if (insertionCaractereListeAutomatique){
			doSoumettreRafraichir();
		}
	}	
	
</SCRIPT>

<TITLE><bean:message key='tabpage_adress'/></TITLE>
</HEAD>
<BODY link="#095B97"
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
  onKeyDown="return toucheRetour();">

<html:form action='/adresses/soumettreRecherche'>
	<jsp:include page='/commun/aide.jsp' flush="true"/>
	
	<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
		<tiles:put name='keyTitre' content="recherche.adresses" />
	</tiles:insert>		
	<tiles:insert page="/templates/adresses/tpl_recherche_adresses_formulaire.jsp" flush="true"/>
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/adresses/tpl_recherche_sujet_adresses_resultats.jsp" flush="true" />
	
	<tiles:insert page="/scripts/scriptsSequenceSQL.jsp" flush="true" />
</html:form>

<BR>

</BODY>
</HTML>