<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une inscription.
Description : Ajout d'une inscription reliée à un contrat d'auto-exclusion
              ou autre.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/04/18 21:17:41 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/21 22:02:56 , Author: abruno-boucher
Création.

$Revision: 1.7 $, $Date: 2002/04/18 21:17:41 $, $Author: mlibersan $
Détection de la locale pour l'appel du fichier Assistant date.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="java.util.Date" %>

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

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/selector_engine.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

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
	document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);

	//On s'assure qu'une aide immédiate a été sélectionnée avant de sauvegarder,
	//s'il s'agit d'une autoeclusion.
  if(document.forms(0).categorie.value == "<%= GlobalConstants.Categorie.AUTOEXCLUSION %>"){	
    if(valeurRadioCheck("aideimmediate")==""){
        var msg = "<bean:message key='cardex_aide_immediate'/>";
       	message(msg);
    }else{
   	    unlockFields();
        performSelectAll();
        document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);
        soumettre('<%= request.getContextPath() + "/dossier/inscription/add.do"%>');
    }
  }else{
    //Pour les autres types de contrats, la date de début ne doit pas dépasser la date du jour.
	var now = new Date(); 
	var mois = now.getMonth() + 1;
	if(mois < 10){
		mois = "0"+mois;
	}
	now = now.getYear()+"-"+mois+"-"+now.getDate();
	//alert(now + " - " + document.forms(0).dateDebut.value);
	if(document.forms(0).dateDebut.value > now){
          message("<bean:message key='cardex_required_date_deb'/>");
	}else{
		unlockFields();
	    performSelectAll();
	    document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);
	    soumettre('<%= request.getContextPath() + "/dossier/inscription/add.do"%>');
	}
  }

}

function valeurRadioCheck(nomRadio){
	 var i ,n;
	 var tabInput = document.getElementsByTagName("input");
	 n = tabInput.length;
	 
	 for (i=0; i<n; i++){

		 if (tabInput[i].name.toLowerCase()==nomRadio 
		 && tabInput[i].checked==true){
			return tabInput[i].value;
		 }
	 }
	 return "";
}

function doRefresh() {
	unlockFields();
	soumettre('<%= request.getContextPath() + "/dossier/inscription/refresh.do"%>');
}

function doCancel() {
    post('<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="inscription" property="lienSite"/>&cle=<bean:write name="inscription" property="lien"/>');
}

function doTraits(objet) {
//-- Insertion de traits d'union dans les dates, s'il y a lieu.
   dateSaisie = objet.value;
   if(dateSaisie.length == 8){
      if("<%= var_lang %>" == "fr"){
 	dateSaisie = dateSaisie.substring(0,4)+"-"+dateSaisie.substring(4,6)+"-"+dateSaisie.substring(6,8);
      }else{
        dateSaisie = dateSaisie.substring(0,2)+"/"+dateSaisie.substring(2,4)+"/"+dateSaisie.substring(4,8);
      }
    objet.value = dateSaisie;
   }
}

</SCRIPT>
<title>Inscription</title>
</head>

<body  link="#095B97"
    leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" onLoad="initSelectOptions();disableListes();">

<!-- POSITIONING TABLE -->
<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
    <td align="center">
          <html:form action="/dossier/inscription/add">
            <jsp:include page="/templates/tpl_erreur.jsp" flush="true" />
            <jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
            <jsp:include page='/commun/aide.jsp' flush="true"/>
            
			<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
				<tiles:put name='keyTitre' content="inscription" />
				<tiles:put name='tableWith' content="500" />
			</tiles:insert>            
            
    
		    <tiles:insert page="/templates/inscriptions/tpl_inscription_formulaire.jsp" flush="true">
				<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS%>' />
			</tiles:insert>	
            
          </html:form>
    </td>
  </tr>
</table>
<!-- END POSITIONING TABLE -->


</BODY>
</HTML>