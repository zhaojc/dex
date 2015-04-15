<%-- --------------------------------------------------------------------------
Use case    : Réponse du système suite à une capture.
Description : Écran affichant la réponse du système suite à une soumission d'une
              capture de photo.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/04/16 19:51:01 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/18 20:46:28 , Author: abruno-boucher
Création HTML.

$Revision: 1.1 $, $Date: 2002/04/16 19:51:01 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() + "/styles/lq_styles.css"%>' />

<jsp:include page='/commun/commun.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function doClose() {
	//soumettre('<%= request.getContextPath() + "/dossier/w_consultation_dossier.jsp"%>');
	windowClose();
}

function doImprimer() {
	var rapport = document.forms(0).formulaire.value;
	//Le rapport CONTRAT_INSCRIPTIONS n'a pas été converti, car Jasper ne supporte pas adéquatement les narrations formatées.
	if(rapport == "<%=GlobalConstants.ChoixImpressionContrat.CONTRAT_INSCRIPTIONS%>"){
		soumettre('<%= request.getContextPath() + "/dossier/print.do"%>');
	}else{
		if(document.forms(0).site.value == "<%=GlobalConstants.SiteMaisonJeux.ESPACEJEUX %>"){
			rapport = "<%=GlobalConstants.ChoixImpressionContrat.CONTRAT_AUTOEXCLUSION_ESPACEJEUX %>";
		}
		var langue  = document.forms(0).langue.value;
		var cleDossier = document.forms(0).cle.value;
		var cleSite = document.forms(0).site.value;
		var url = "<%=request.getContextPath()%>/TableValeurAffichageRapport?choixRapport=" + rapport + "&langue=" + langue + "&cle=" + cleDossier + "&site=" + cleSite;
	    windowOpenLocation(url);
	    //window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
	    doClose();
	}
}

</SCRIPT>

<title><bean:message key='choix_formulaire_t'/></title>
</head>

<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"  >
<html:form action='/dossier/print' >

<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
	<tiles:put name='keyTitre' content="choix_formulaire_t" />
	<tiles:put name='tableWith' content="403" />
</tiles:insert>

      <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
		  <td colspan="2">
    		  <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="#ECECEC" class="tableCarved">
      		  <tr>
        			<td width="125" align="right"><b><bean:message key='formulaire'/></b></td>
        			<td width="195" align="left">
                      <html:select name='dossier' property="formulaire" size="1" style="HEIGHT: 20px; WIDTH: 190px"  >
                        <option value='<%=GlobalConstants.ChoixImpressionContrat.CONTRAT_AUTOEXCLUSION%>'><bean:message key='contrat_autoexclusion'/></option>
                        <option value='<%=GlobalConstants.ChoixImpressionContrat.CONTRAT_INSCRIPTIONS%>'><bean:message key='autres2_t'/></option>
                        <option value='<%=GlobalConstants.ChoixImpressionContrat.CONTRAT_SUIVIS%>'><bean:message key='tabpage_suivis'/></option>
                      </html:select>
        			</td>
      		  </tr>

      		  <tr>
        			<td width="125" align="right"><b><bean:message key='i_ls_cle_t'/></b></td>
        			<td width="195" align="left">
						<myriap:select name='dossier' property='langue' size='1' style='HEIGHT: 20px; WIDTH: 190px' >
						   <cardex:optionTag classe='<%= GlobalConstants.CleListe.LANGUE_APPLICATION %>'/>
						</myriap:select>
                      <html:hidden name='dossier' property="motPasse"  />
                      <html:hidden name='dossier' property="cle"  />
                      <html:hidden name='dossier' property="site"  />
                    </td>
      		  </tr>
 
          </table>

          </td>
    		</tr>

    		<tr>
    		  <td width="200" align="right">
          			<cardex:button urlSecurite="/CritereRapportAffichagePDF" labelKey='cb_ok2' onclick='doImprimer();' style="width: 60px; text-align: center;" />
                  </td>

          <td width="200">
				<cardex:button securityConstraint='' labelKey='cb_fermer'  onclick='doClose();' style="width: 60px; text-align: center;" />
          </td>

  		</tr>
  	</table>


    </td>
  </tr>
</table>
</html:form>

</BODY>
</HTML>