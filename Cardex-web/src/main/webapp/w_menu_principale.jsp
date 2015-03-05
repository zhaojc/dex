<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Départ de tout les "use case".
Description : Écran de départ pour exécuter n'importe quelle tâche avec
              l'application (menu principal).
Author(s)   : $Author: mlibersan $, abruno-boucherSIUVI
Revision    : $Revision: 1.36 $, $Date: 2002/04/24 17:38:42 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.
 
$Revision: 1.36 $, $Date: 2002/04/24 17:38:42 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.presentation.util.LabelValueBean" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.struts.Globals" %>
<%
	//-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
	//-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
	//-- de session.
	String var_lang = "fr";
	try {
		var_lang = ((Locale) request.getSession().getAttribute(
				Globals.LOCALE_KEY)).getLanguage();
	} catch (Throwable e) {
	}
%>



<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<link rel='stylesheet' type='text/css' href='<%=request.getContextPath() + "/styles/lq_main_styles.css"%>'>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath() + "/styles/windowOpen.css"%>'>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath() + "/styles/lq_styles.css"%>'>
	
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/lq_scripts.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/windowOpen.js"></SCRIPT>

<style type="text/css" >
body{
	cursor: wait;
	}
</style>

<SCRIPT type="text/javascript">
	var rightClick = false;
	var toucheCTRL = false;

	function getOnContextMenu(){
		return rightClick;
	}

	function desactiveCTRL() {
	  // current pressed key
	  //var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();
	  
	  if (event.ctrlKey) {
		  //Si la toucche CTRL est permise, on n'autorise que les combinaisons CTRL-C, CTRL-V et CTRL-X.
	        if(toucheCTRL){
	           if(window.event.keyCode != 67 && window.event.keyCode != 86 && window.event.keyCode != 88){
	           	  event.returnValue = false;
	           }else{
	              event.returnValue = true;
	           }
	     	}else{
	     	   event.returnValue = false;
	     	}
      }else{
	    event.returnValue = true;
	  }
	}
	
	var userCardex = "<bean:write name='<%=AuthenticationSubject.class.getName()%>' property='user.code' />";

	function customOnLoad(){
		assignerUsagerTitre();
		history.go(+1);
		cursorDefault();
		background();
	}

	function assignerUsagerTitre(){
		var titre = document.title;

		if (userCardex != "null"){
			document.title=userCardex+"-"+titre;
		}
	}

	document.oncontextmenu=getOnContextMenu;
	document.onkeydown=desactiveCTRL;
		
	// Après que la page soit loader
	addEvent(window, 'load', customOnLoad);

function background(){
	  //S'il s'agit de l'environnement de formation, on affiche un fond différent pour le distinguer.
	  //alert("<%=request.getContextPath()%>" + "   <%=GlobalConstants.APPLICATION_PRODUCTION%>");
	 if("<%=request.getContextPath()%>" != "<%=GlobalConstants.APPLICATION_PRODUCTION%>" ){
		  		document.body.style.backgroundImage = 'url("<%=request.getContextPath()%>/images/arriere-planFormation.jpg")';
				document.body.style.backgroundPosition = 'top left';
				document.body.style.backgroundRepeat = 'repeat-x';
		  }
			 
	}


</SCRIPT>

<SCRIPT FOR = document EVENT = onreadystatechange>
	cursorDefault();
</SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">
  //document.onload = maximizeMain();
  addEvent(window, 'load', maximizeMain);
  
  function maximizeMain(){
    //-- Display units in pixels
    //-- Position relative to vbWindow
    window.moveTo(0, 0);
	window.resizeTo(screen.availWidth, screen.availHeight);
  }

	function mainWindowClose(){
		var message = "<bean:message key="message.quitter.cardex"/>";
		
		if (confirm(message)){
			windowClose();
		}
	}

	
	function fermerConnexion(sequence){
	    var url = "<%=request.getContextPath()%>/FermerConnexion?SEQUENCE="+sequence;
	    var req = initRequest(url);
	    req.open("GET", url, true);
	    
	    req.send();
	}

	var fermetureNormale = true;
	
	function entrerChoisirProfil(){
		var message = "<bean:message key="message.changement.profil"/>";
		
		if (confirm(message)){
			fermetureNormale = false;	
			windowHandlerClose();
			soumettre("<%=request.getContextPath()%>/entrerChoisirProfil.do");
		}
	}
	
	function doRafraichir(){
		fermetureNormale=false;
		soumettre('<%=request.getContextPath()%>/menu/refresh.do');
	}
		
	function doRechercheDirecteDossier(){
	    var numeroDossier = document.getElementById("numeroDossier");
		windowOpenLocation('<%=request.getContextPath()%>/menu/rechercheDossier.do?DOSSIER=' + numeroDossier.value);
	}

	function doRechercheDirecteSujet(){
	    var sujet = document.getElementById("sujet");
		windowOpenLocation('<%=request.getContextPath()%>/menu/rechercheSujet.do?SUJET=' + sujet.value);
	}

	function doRechercheDirecteVehicule(){
	    var immatriculation = document.getElementById("immatriculation");
		windowOpenLocation('<%=request.getContextPath()%>/menu/rechercheVehicule.do?VEHICULE=' + immatriculation.value);
	}

</SCRIPT>

<SCRIPT FOR=window EVENT=onunload>
	//window.open("logout.jsp","_self");
	if (fermetureNormale == true){
		windowHandlerClose();
	    var url = "<%=request.getContextPath()%>/SessionInvalidate";
	    var req = initRequest(url);
		req.open("GET", url, true);
	    req.send(null);
	}
</SCRIPT>


<jsp:include page='/scripts/messageUtilisateur.jsp' flush="true"/>
<jsp:include page='/scripts/activiteUtilisateur.jsp' flush="true"/>

<TITLE><bean:message key="titre.application.cardex"/></TITLE>
</HEAD>

<BODY link="#095B97"  vlink="#095B97" onload="background();afficherMessageUtilisateur();VerificationActiviteUtilisateur();">

<logic:present name='<%= AuthenticationSubject.class.getName() %>' >
<!-- Positioning table -->

<div id="divMessageUtilisateur" >
</div>
    <!-- LOGGED USER INFOS -->
<TABLE align="left" WIDTH="100%" cellPadding="4" cellSpacing="0" border="0" bgcolor="#FFFFFF" CLASS="tableOutline">
  <TR>
    <TD class="tabBackground" valign="middle">
     	<cardex:button urlSecurite="/menu/refresh.do" onclick="doRafraichir();" labelKey='RafraichirListe' style='width: 100px; text-align: center; background-color: #447db8; color:white' />
		<cardex:button urlSecurite="/entrerChoisirProfil.do" onclick="entrerChoisirProfil();" labelKey='choisir.profil' style='width: 100px; text-align: center; background-color: #447db8; color:white' />
		&nbsp;
        <b><cardex:afficherValeurListeTag name="<%= AuthenticationSubject.class.getName() %>" property="user.code" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' /></b>
		,&nbsp;
        <b><cardex:afficherValeurListeTag name="<%= AuthenticationSubject.class.getName() %>" property="user.site" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' /></b>
		,&nbsp;
        <b><cardex:afficherValeurListeTag name="<%= AuthenticationSubject.class.getName() %>" property="user.secteur" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' /></b>
		,&nbsp;
        <b><bean:write name='<%= AuthenticationSubject.class.getName() %>' property='user.code' />@<%=request.getServerName()%></b>&nbsp;
        <b><!-- %= request.getSession().getAttribute("instance") % --><%=request.getContextPath()%></b>&nbsp;
	    <b><dt:format locale="true"><dt:currentTime/></dt:format></b>
		&nbsp;
	  </TD>
  </TR>
</TABLE>
<!-- End logged user infos -->
<BR CLEAR="left">
<BR>&nbsp;
 
<TABLE width="100%" height="100%" align="left" cellPadding="0" cellSpacing="0" border="0">
  <TR>
    <TD valign="top" width="100">&nbsp;
    </TD>

    <TD valign="top">

	<TABLE width="210" cellPadding="5" cellSpacing="0" border="0" CLASS="tableOutline">
		<TR>
		  <TD align="center" VALIGN="top" CLASS="tabTitleBig" >
	         <bean:message key='menu' />
	      </TD>
		</TR>
	</TABLE>
    <TABLE width="210" align="left" cellPadding="2" cellSpacing="0" border="0" CLASS="tableCarved">
				 <TR>
				   <TD>&nbsp;
				   </TD>
				 </TR>
				 <cardex:securityDefineTag nameDefine="btnRechercheSujet" urlSecurite="/sujet/search/default.do">
                  <TR>
                    <TD align="center">
                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton01Background');"
                       onmouseover="changeBackgroundColor('menuButton01Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton01Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD >
                          <cardex:linkRechercheSujet page='/sujet/search/default.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message  key='tabpage_sujet'/></DIV>
                          </cardex:linkRechercheSujet>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
                     </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheSociete" urlSecurite="/societe/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton02Background');"
                       onmouseover="changeBackgroundColor('menuButton02Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton02Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>

                         <TD >
                          <cardex:linkRechercheSociete page='/societe/search/default.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message  key='tabpage_societe'/></DIV>
                          </cardex:linkRechercheSociete>
                         </TD>

                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>
                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheVehicule" urlSecurite="/vehicule/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton03Background');"
                       onmouseover="changeBackgroundColor('menuButton03Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton03Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD >
                          <cardex:linkRechercheVehicule page='/vehicule/search/default.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message  key='tabpage_vehicule'/></DIV>
                          </cardex:linkRechercheVehicule>
                        </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheNarration" urlSecurite="/narration/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton04Background');"
                       onmouseover="changeBackgroundColor('menuButton04Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton04Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD >
                          <cardex:linkRechercheNarration page='/narration/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='tabpage_commentaires'/></DIV>
                          </cardex:linkRechercheNarration>
                        </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheSuivi" urlSecurite="/suivi/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton05Background');"
                       onmouseover="changeBackgroundColor('menuButton05Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton05Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD >
                          <cardex:linkRechercheSuivi page='/suivi/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='tabpage_suivis'/></DIV>
                          </cardex:linkRechercheSuivi>
                        </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>
                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheUrgence" urlSecurite="/urgence/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton06Background');"
                       onmouseover="changeBackgroundColor('menuButton06Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton06Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD >
                          <cardex:linkRechercheUrgence page='/urgence/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='tabpage_urgence'/></DIV>
                          </cardex:linkRechercheUrgence>
                        </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>
                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheApprobation" urlSecurite="/approbation/search/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton07Background');"
                       onmouseover="changeBackgroundColor('menuButton07Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton07Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD>
                          <cardex:linkRechercheApprobation page='/approbation/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='approbations_t'/></DIV>
                          </cardex:linkRechercheApprobation>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheJournal" urlSecurite="/journal/search/default.do">
                  <TR>
                    <TD align="center">

                      <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton08Background');"
                       onmouseover="changeBackgroundColor('menuButton08Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton08Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD>
                          <cardex:linkRechercheApprobation page='/journal/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='journal'/></DIV>
                          </cardex:linkRechercheApprobation>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheConsignation" urlSecurite="/consignation/search/default.do">
                  <TR>
                    <TD align="center">

                      <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton09Background');"
                       onmouseover="changeBackgroundColor('menuButton09Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton09Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD>
                          <cardex:linkRechercheApprobation page='/consignation/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='consignation_t'/></DIV>
                          </cardex:linkRechercheApprobation>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheBillet" urlSecurite="/billet/recherche/defaut.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton10Background');"
                       onmouseover="changeBackgroundColor('menuButton10Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton10Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>

                         <TD >
                          <cardex:linkRechercheSociete page='/billet/recherche/defaut.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message  key='billets'/></DIV>
                          </cardex:linkRechercheSociete>
                         </TD>

                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>
                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRechercheAdresse" urlSecurite="/adresses/recherche/default.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton11Background');"
                       onmouseover="changeBackgroundColor('menuButton11Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton11Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>

                         <TD >
                          <cardex:linkOpenWindowObject page='/adresses/recherche/default.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message  key='tabpage_adress'/></DIV>
                          </cardex:linkOpenWindowObject>
                         </TD>

                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>
                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnRapports" urlSecurite="/rapport/defaut.do">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton12Background');"
                       onmouseover="changeBackgroundColor('menuButton12Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton12Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>

                         <TD >
                          <cardex:linkOpenWindowObject page='/rapport/defaut.do' >
                            <DIV id="MENU_BUTTON_TEXT"><bean:message key='rapports'/></DIV>
                          </cardex:linkOpenWindowObject>
                         </TD>

                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>
                    </TD>
                  </TR>
				</cardex:securityDefineTag>				
				<cardex:securityDefineTag nameDefine="btnRechercheMandat" urlSecurite="/mandat/search/default.do">
                  <TR>
                    <TD align="center">

                      <!-- Navigation button -->
                     <DIV id="MENU_NAV"
                       onmouseout="changeBackgroundColor('menuButton13Background');"
                       onmouseover="changeBackgroundColor('menuButton13Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton13Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD>
                          <cardex:linkRechercheApprobation page='/mandat/search/default.do' >
                             <DIV id="MENU_BUTTON_TEXT"><bean:message key='mandat_entete'/></DIV>
                          </cardex:linkRechercheApprobation>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
					 </DIV>

                    </TD>
                  </TR>
				</cardex:securityDefineTag>
				<cardex:securityDefineTag nameDefine="btnPilotage" securityConstraint="cardex.pilotage.enter">
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV" style="cursor: hand;"
                       onmouseout="changeBackgroundColor('menuButton14Background');"
                       onmouseover="changeBackgroundColor('menuButton14Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton14Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD>
							<DIV id="MENU_BUTTON_TEXT" onclick="windowOpenLocation('/pilotage/enter.do');"><bean:message key='Pilotage'/></DIV>
                         </TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                       
                     </TABLE>
					</DIV>
                    </TD>
                  </TR>
				</cardex:securityDefineTag>
                  <TR>
                    <TD align="center">

                     <!-- Navigation button -->
                     <DIV id="MENU_NAV" style="cursor: default;"
                       onmouseout="changeBackgroundColor('menuButton15Background');"
                       onmouseover="changeBackgroundColor('menuButton15Background');">
                     <TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton15Background">
                       <TR>
                         <TD ><html:img page="/images/l_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_up_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_up_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_center_btn.gif" border="0" /></TD>
                         <TD ><A HREF="#" onclick="mainWindowClose();"><DIV id="MENU_BUTTON_TEXT"><bean:message key='cb_fermer'/></DIV></A></TD>
                         <TD ><html:img page="/images/r_center_btn.gif" border="0" /></TD>
                       </TR>

                       <TR>
                         <TD ><html:img page="/images/l_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/center_down_btn.gif" border="0" /></TD>
                         <TD ><html:img page="/images/r_down_btn.gif" border="0" /></TD>
                       </TR>
                     </TABLE>
                       </DIV>

                    </TD>
                  </TR>

                  <TR>
                    <TD >&nbsp;</TD >
                  </TR>

          </TABLE>

                 </TD>
                 <TD align="center" valign="top">

<TABLE width="500" cellPadding="5" cellSpacing="0" border="0" CLASS="tableOutline">
	<TR>
	  <TD align="center" VALIGN="top" CLASS="tabTitleBig" >
         <bean:message key='dossiers' />
      </TD>
	</TR>
</TABLE>
<TABLE width="500" cellPadding="10" cellSpacing="0" border="0" CLASS="tableCarved">
  <TR>
    <!-- We use a blank pixel in the first row to force alignment -->
    <TD align="left" VALIGN="top">
<%
	Collection genres = (Collection) request
					.getSession()
					.getAttribute(GlobalConstants.Menu.GENRES_RECHERCHE);
%>    
   		<cardex:securityDefineTag nameDefine="AccesRechercheDossier" urlSecurite="/dossier/search/default.do">
<%
	if (genres != null) {
					Map natures = (Map) request
							.getSession()
							.getAttribute(
									GlobalConstants.Menu.NATURES_RECHERCHE);
					Iterator itGenre = genres.iterator();

					while (itGenre.hasNext()) {
						LabelValueBean genre = (LabelValueBean) itGenre
								.next();
						if (genre.getValue() != null
								&& genre.getValue().trim().length() > 0) {
%>
    <html:img page="/images/blank.gif" width="220" height="1" border="0" /><BR>

	   <!-- SEARCH TAB -->
	   <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
	   
                <TR>
                        <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
                        <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
                          <%=genre.getLabel()%>
                        </TD>
                        <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
                </TR>
                <TR>
                        <TD CLASS="folderFrame"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
                        <TD CLASS="mainCellDetail" valign="middle">
                          <UL compact>&nbsp;
<%
	Collection naturesFilteredByGenre = (Collection) natures
									.get(genre.getValue());
							Iterator itNature = naturesFilteredByGenre
									.iterator();
							while (itNature.hasNext()) {
								LabelValueBean nature = (LabelValueBean) itNature
										.next();
								if (nature.getValue() != null
										&& nature.getValue().trim()
												.length() > 0) {
%>
                            <LI><cardex:linkRechercheDossier genre='<%= genre.getValue() %>' nature='<%= nature.getValue() %>' page='/dossier/search/default.do' ><%=nature.getLabel()%></cardex:linkRechercheDossier></LI>
<%
	}//if
							}//while
%>
                          </UL>
                        </TD>
                        <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
                        <TD CLASS="folderFrame"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>

                </TR>
                <TR>
                        <TD COLSPAN="5" CLASS="folderFrame"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>

                
          </TABLE>
          <!-- End search tah -->
          <br><br>
<%
	}//if
					}//while
				}//if
%>    
		</cardex:securityDefineTag>
    </TD>
    <TD align="right" VALIGN="top">
    	<cardex:securityDefineTag nameDefine="AccesGalerie" urlSecurite="/galerie/search/default.do">
<%
	genres = (Collection) request.getSession()
						.getAttribute(
								GlobalConstants.Menu.GENRES_GALERIE);

				if (genres != null) {
					Map natures = (Map) request
							.getSession()
							.getAttribute(
									GlobalConstants.Menu.NATURES_GALERIE);
					Iterator itGenre = genres.iterator();

					while (itGenre.hasNext()) {
						LabelValueBean genre = (LabelValueBean) itGenre
								.next();
						if (genre.getValue() != null
								&& genre.getValue().trim().length() > 0) {
%>            
		<html:img page="/images/blank.gif" width="220" height="1" border="0" /><BR>
            <!--GALLERY -->
            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
                <TR>
                        <TD ALIGN="left" COLSPAN="2" CLASS="folderTitleGallery"><html:img page="/images/l_up_corner_green.gif" width="5" height="24" border="0" /></TD>
                        <TD CLASS="folderTitleGallery"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
                          <bean:message key='tabpage_galery' />&nbsp;<%=genre.getLabel()%>
                        </TD>
                        <TD ALIGN="right" COLSPAN="2" CLASS="folderTitleGallery"><html:img page="/images/r_up_corner_green.gif" width="5" height="24" border="0" /></TD>
                </TR>
                <TR>
                        <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
                        <TD CLASS="mainCellDetail" valign="middle">
                          <UL compact>&nbsp;

<%
	Collection naturesFilteredByGenre = (Collection) natures
									.get(genre.getValue());
							Iterator itNature = naturesFilteredByGenre
									.iterator();
							while (itNature.hasNext()) {
								LabelValueBean nature = (LabelValueBean) itNature
										.next();
								if (nature.getValue() != null
										&& nature.getValue().trim()
												.length() > 0) {
%>
                            <LI><cardex:linkGalerie genre='<%= genre.getValue() %>' nature='<%= nature.getValue() %>' page='/galerie/search/default.do' ><%=nature.getLabel()%></cardex:linkGalerie></LI>
<%
	}//if
							}//while
%>

                          </UL>
                        </TD>
                        <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
                        <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>

                </TR>
                <TR>
                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>
               
          </TABLE>
          <!-- End gallery -->
          <br><br>
<%
	}//if
					}//while
				}//if
%>
		</cardex:securityDefineTag>
    </TD>
  </TR>


 </TABLE>
 <!-- End folder section -->
       </TD>
       <TD valign="top">
			<TABLE>
				<TR>
					<TD>
					<TABLE width="400" cellPadding="5" cellSpacing="0" border="0"
						CLASS="tableOutline">
						<TR>
							<TD align="center" VALIGN="top" CLASS="tabTitleBig"><bean:message
								key='recherche.directe' /></TD>
						</TR>
					</TABLE>
					<TABLE width="400" align="left" cellPadding="5" cellSpacing="0"
						border="0" CLASS="tableCarved">
						<TR>
							<TD ALIGN="right"><b><bean:message
								key='v_do_numero_dossier' /><bean:message key='2.points' /></b></TD>
							<TD><input type='text' name='numeroDossier' value=''
								tabindex='' maxlength='17'
								onblur="this.value = this.value.toUpperCase();" /> <img
								src="<%=request.getContextPath()%>/images/magnify.gif"
								id="loupe" TITLE="Recherche de dossiers" border="1" height="14"
								width="14" onclick="doRechercheDirecteDossier();" /></TD>
						</TR>
						<TR>
							<TD ALIGN="right"><b><bean:message key='numero.fiche' /><bean:message
								key='2.points' /></b></TD>
							<TD><input type='text' name='sujet' value='' tabindex=''
								maxlength='10' onblur="this.value = this.value.toUpperCase();" />
							<img src="<%=request.getContextPath()%>/images/magnify.gif"
								id="loupe" TITLE="Recherche de sujets" border="1" height="14"
								width="14" onclick="doRechercheDirecteSujet();" /></TD>
						</TR>
						<TR>
							<TD ALIGN="right"><b><bean:message
								key='v_ve_immatriculation_t' /></b></TD>
							<TD><input type='text' name='immatriculation' value=''
								tabindex='' maxlength='10'
								onblur="this.value = this.value.toUpperCase();" /> <img
								src="<%=request.getContextPath()%>/images/magnify.gif"
								id="loupe" TITLE="Recherche de véhicules" border="1" height="14"
								width="14" onclick="doRechercheDirecteVehicule();" /></TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD height="100">&nbsp;
					</TD>
				</TR>
				<TR>
					<TD align="center">
					<TABLE width="210" cellPadding="5" cellSpacing="0" border="0"
						CLASS="tableOutline">
						<TR>
							<TD align="center" VALIGN="top" CLASS="tabTitleBig"><bean:message
								key='documentation' /></TD>
						</TR>
					</TABLE>

					<TABLE width="210" cellPadding="5" cellSpacing="0" border="0"
						CLASS="tableOutline">
						<TR>
							<TD align="center"><!-- Navigation button -->
							<DIV id="MENU_NAV" style="cursor: default;"
								onmouseout="changeBackgroundColor('menuButton16Background');"
								onmouseover="changeBackgroundColor('menuButton16Background');">
							<TABLE cellPadding="0" cellSpacing="0" border="0"
								CLASS="menuButton16Background">
								<TR>
									<TD><html:img page="/images/l_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/r_up_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_center_btn.gif" border="0" /></TD>
									<TD><a href="#" onclick="windowOpenLocation('\\\\le500\\DFS\\InterLQ\\index-Cardex\\Guide Cardex\\Guide Cardex.pdf');">
										    <DIV id="MENU_BUTTON_TEXT"><bean:message key='Guide' /></DIV>
										</a>
									</TD>
									<TD><html:img page="/images/r_center_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_down_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_down_btn.gif"
										border="0" /></TD>
									<TD><html:img page="/images/r_down_btn.gif" border="0" /></TD>
								</TR>
							</TABLE>
							</DIV>

							</TD>
						</TR>
						<TR>
							<TD align="center"><!-- Navigation button -->
							<DIV id="MENU_NAV" style="cursor: default;"
								onmouseout="changeBackgroundColor('menuButton17Background');"
								onmouseover="changeBackgroundColor('menuButton17Background');">
							<TABLE cellPadding="0" cellSpacing="0" border="0"
								CLASS="menuButton17Background">
								<TR>
									<TD><html:img page="/images/l_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/r_up_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_center_btn.gif" border="0" /></TD>
									<TD><a href="#" onclick="windowOpenLocation('\\\\le500\\DFS\\InterLQ\\index-Cardex\\Index_v2.0.xlsm');">
										    <DIV id="MENU_BUTTON_TEXT"><bean:message key='index' /></DIV>
										</a>
									</TD>
									<TD><html:img page="/images/r_center_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_down_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_down_btn.gif"
										border="0" /></TD>
									<TD><html:img page="/images/r_down_btn.gif" border="0" /></TD>
								</TR>
							</TABLE>
							</DIV>

							</TD>
						</TR>
						<TR>
							<TD align="center"><!-- Navigation button -->
							<DIV id="MENU_NAV" style="cursor: default;"
								onmouseout="changeBackgroundColor('menuButton18Background');"
								onmouseover="changeBackgroundColor('menuButton18Background');">
							<TABLE cellPadding="0" cellSpacing="0" border="0" CLASS="menuButton18Background">
								<TR>
									<TD><html:img page="/images/l_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_up_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/r_up_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_center_btn.gif" border="0" /></TD>
									<TD><a href="#" onclick="windowOpenLocation('\\\\le500\\DFS\\InterLQ\\index-Cardex\\Écrits officiels SC');">
										    <DIV id="MENU_BUTTON_TEXT"><bean:message key='MenuPrincipal.Bouton.Documentation.EcritsOfficielsSC' /></DIV>
										</a>
									</TD>
									<TD><html:img page="/images/r_center_btn.gif" border="0" /></TD>
								</TR>

								<TR>
									<TD><html:img page="/images/l_down_btn.gif" border="0" /></TD>
									<TD><html:img page="/images/center_down_btn.gif"
										border="0" /></TD>
									<TD><html:img page="/images/r_down_btn.gif" border="0" /></TD>
								</TR>
							</TABLE>
							</DIV>

							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
			</TABLE>

			<!-- Language variable to be retreived from the Vb envelope -->
 <!-- Please leave uppercases. -->
 <FORM name="aForm" action="#" method="post">
   <INPUT type="hidden" name="LANG" value="<%=var_lang%>" />
   <jsp:include page="/commun/configuration.jsp" />
 </FORM>

 </TD>
   </TR>
 </TABLE>
 <!-- End positionning table -->

<BR>
<script type="text/javascript">
	function affMasquer(){
		if (document.all.version.style.color == "blue"){
			document.all.version.style.color = "white";
		}else{
			document.all.version.style.color = "blue";
		}
	}

</script>

<div id="version" onclick="affMasquer()" style="position: absolute; left: 0; top: 0; width: 150; height: 20; color: white;">
Version: XXXX-XX-XX 
</div>

</BODY>
</HTML>
</logic:present>