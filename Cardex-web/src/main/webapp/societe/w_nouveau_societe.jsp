<%-- --------------------------------------------------------------------------
Use case    : R�sultat de soci�t�
Description : Page principale dans laquelle est incorpor�e les diff�rentes
              composantes relatives au r�sultat d'une soci�t�.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.11 $, $Date: 2002/04/22 17:37:15 $

History     : Voir ci-dessous.

Revision: 1.2 , Date: 2002/03/13 16:52:11 , Author: abruno-boucher
Cr�ation.

Revision: 1.8 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise � jour nouvel Assistant date-heure (incorpor� dans la page).

$Revision: 1.11 $, $Date: 2002/04/22 17:37:15 $, $Author: mlibersan $
Cr�ation.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%
   //-- L'appel suivant g�n�re une des cha�nes suivante de caract�res:  fr, en, sp, de, it, ...
   //-- et utilis� pour l'appel de fichiers localis�s.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>


<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/lq_consultationSociete_styles.css">


<SCRIPT language="JavaScript" type="text/javascript">

function doSoumettreRafraichir() {
	unlockFields();
	//-- Fonction d�clar�e dans lq_scripts.js
	soumettre('<%= request.getContextPath() + "/societe/refresh.do"%>');
}

function doOk() {
	unlockFields();
  //-- Fonction d�clar�e dans lq_scripts.js
  soumettre('<%= request.getContextPath() + "/societe/save.do"%>');
}

function doClose() {
	windowClose();
}

</SCRIPT>

<!-- En mode AJOUT il ne DOIT PAS y avoir de titre -->
<!-- pour des fins de contr�le par l'enveloppe Vb  -->
<TITLE></TITLE>

</HEAD>
<BODY   link="#095B97" 
  leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<html:form action='/societe/save' >
	<jsp:include page="/templates/societe/tpl_onglet_societe_entete.jsp" flush="true" />
	
	<tiles:insert page="/templates/societe/tpl_societe_formulaire.jsp" flush="true">
		<tiles:put name="soumettreSauvegarde" value="/societe/save.do" />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.AJOUT%>' />
	</tiles:insert>	
	
	<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
	<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
    <jsp:include page='/commun/aide.jsp' flush="true"/>
</html:form>

</BODY>
</HTML>
