<%-- --------------------------------------------------------------------------
Use case    : Navigation par les onglets d'un dossier.
Description : Module qui affiche les onglets d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/04/24 17:38:44 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.7 $, $Date: 2002/04/24 17:38:44 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Sub tab navigation -->
<!-- Outline table -->
<div id="menuOnglets" style="visibility: hidden;">
<table>
<tr><td valign="top">
   <table style="margin: 0px;" cellpadding="0" cellspacing="0" width="900">
	<tr>
	  <td id="onglet0Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreHaut.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td id="onglet0Gauche0" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheHaut.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			
			<myriap:link securityConstraint='cardex.dossier.sujets.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_INDIVIDUALS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_INDIVIDUALS" onclick="setOnglet('#TAB_INDIVIDUALS');ongletClick(0,0);">&nbsp; 
						<bean:message key='tabpage_sujet' />
						<logic:equal name='dossier' property='sujets.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>

		  </td>
		  <td id="onglet0Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitHaut.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet1Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<logic:equal name='dossier' property='inscription' value='true'>
				<myriap:link
					securityConstraint='cardex.dossier.inscriptions.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_INSCRIPTIONS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_INSCRIPTIONS" onclick="setOnglet('#TAB_INSCRIPTIONS');ongletClick(1,0);">&nbsp; 
						<bean:message key='tabpage_inscription' />
						<logic:equal name='dossier' property='inscriptions.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
				</myriap:link>
			</logic:equal>
			<logic:equal name='dossier' property='inscription' value='false'>
				<myriap:link href="#"
					onmouseover="window.status=''; return true">
					<DIV style="color: #e6ebf4;" id="TAB_INSCRIPTIONS">&nbsp; 
						<bean:message key='tabpage_inscription' />
						<logic:equal name='dossier' property='inscriptions.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
				</myriap:link>
			</logic:equal>
		  </td>
		  <td id="onglet1Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet2Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.photos.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_PHOTOS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_PHOTOS" onclick="setOnglet('#TAB_PHOTOS');ongletClick(2,0);">&nbsp; 
						<bean:message key='tabpage_photo' />
						<logic:equal name='dossier' property='photos.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet2Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet3Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.suivis.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_FOLLOW_UP');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_FOLLOW_UP" onclick="setOnglet('#TAB_FOLLOW_UP');ongletClick(3,0);">&nbsp; 
						<bean:message key='tabpage_suivis' />
						<logic:equal name='dossier' property='suivis.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet3Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet4Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
					securityConstraint='cardex.dossier.narrations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_NARRATION');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_NARRATION" onclick="setOnglet('#TAB_NARRATION');ongletClick(4,0);">&nbsp; <bean:message key='tabpage_commentaires' />
						<logic:equal name='dossier' property='narrations.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
				</myriap:link>
		  </td>
		  <td id="onglet4Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet5Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
					securityConstraint='cardex.dossier.billets.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_BILLET');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_BILLET" onclick="setOnglet('#TAB_BILLET');ongletClick(5,0);">&nbsp; <bean:message key='billets' />
						<logic:equal name='dossier' property='billets.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
				</myriap:link>
		  </td>
		  <td id="onglet5Droit0" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet6Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.pieces.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_JOINED_PC');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_JOINED_PC" onclick="setOnglet('#TAB_JOINED_PC');ongletClick(6,0)">&nbsp; 
						<bean:message key='tabpage_documents' />
						<logic:equal name='dossier' property='piecesJointes.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet6Droit0" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>

	</tr>
  </table>
  </td>
 </tr>
 <tr>
   <td valign="top">
	<table style="margin: 0px;" cellpadding="0" cellspacing="0" width="900">
	 <tr>
	  <td id="onglet0Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td id="onglet0Gauche1" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheBas.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.dossiers.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_FOLDERS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_FOLDERS" onclick="setOnglet('#TAB_FOLDERS');ongletClick(0,1);">&nbsp; 
						<bean:message key='tabpage_dossier' />
						<logic:equal name='dossier' property='dossiers.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet0Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet1Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.jeux.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_GAMES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_GAMES" onclick="setOnglet('#TAB_GAMES');ongletClick(1,1);">&nbsp; 
						<bean:message key='tabpage_jeu' />
						<logic:equal name='dossier' property='jeux.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9 height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet1Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet2Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.vehicules.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_VEHICLES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_VEHICLES" onclick="setOnglet('#TAB_VEHICLES');ongletClick(2,1);">&nbsp; 
						<bean:message key='tabpage_vehicule' />
						<logic:equal name='dossier' property='vehicules.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet2Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet3Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.societes.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_SOCIETIES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_SOCIETIES" onclick="setOnglet('#TAB_SOCIETIES');ongletClick(3,1);">&nbsp; 
						<bean:message key='tabpage_societe' />
						<logic:equal name='dossier' property='societes.empty' value='false'>
						   <SCRIPT language="JavaScript" type="text/javascript">
						       //La variable "societes" sert à valider la présence d'une société au moment de la sauvegarde d'un dossier Accident/Maladie.
						       societes = "1";
						   </SCRIPT>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>						
		  </td>
		  <td id="onglet3Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet4Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
					securityConstraint='cardex.dossier.consignation.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_CONSIGNATION');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_CONSIGNATION" onclick="setOnglet('#TAB_CONSIGNATION');ongletClick(4,1);">&nbsp; 
						<bean:message key='consignation_t' />
						<logic:equal name='dossier' property='consignations.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet4Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet5Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
					securityConstraint='cardex.dossier.souscategories.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_SOUS_CATEGORIES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_SOUS_CATEGORIES" onclick="setOnglet('#TAB_SOUS_CATEGORIES');ongletClick(5,1);">&nbsp; 
						<bean:message key='sous.categories' />
						<logic:equal name='dossier' property='listeSousCategories.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet5Droit1" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</tr>
		</table>
	  </td>

	  <td id="onglet6Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
					securityConstraint='cardex.dossier.partage.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_PARTAGE');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_PARTAGE" onclick="setOnglet('#TAB_PARTAGE');ongletClick(6,1);">&nbsp; 
						<bean:message key='tabpage_partage' />
						<logic:equal name='dossier' property='partage.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet6Droit1" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	</tr>
  </table>
  </td>
 </tr>
 <tr>
   <td valign="top">
	<table style="margin: 0px;" cellpadding="0" cellspacing="0" width="250">
	 <tr>
	  <td id="onglet0Centre2" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="200" height="21">
		<tr>
		  <td id="onglet0Gauche2" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheBas.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="250" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.urgence.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_URGENCE');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_URGENCE" onclick="setOnglet('#TAB_URGENCE');ongletClick(0,2);">&nbsp;
						<bean:message key='urgence' />
						<logic:equal name='dossier' property='urgence.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>			
		  </td>
 		<logic:equal name="dossier" property="categorie" value="<%=String.valueOf(GlobalConstants.Categorie.COMITE_VIGILANCE)%>">
		  <td id="onglet0Droit2" style="margin: 0px; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif); background-color: rgb(255, 255, 255);" width="29">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="29" height="1" />
		  </td>
		</logic:equal>
		<logic:notEqual name="dossier" property="categorie" value="<%=String.valueOf(GlobalConstants.Categorie.COMITE_VIGILANCE)%>">
		  <td id="onglet0Droit2" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
	 	</logic:notEqual>
		</table>
	  </td>
 	<!-- On n'affiche cet onglet que pour les dossiers du comité de vigilance -->
 	<logic:equal name="dossier" property="categorie" value="<%=String.valueOf(GlobalConstants.Categorie.COMITE_VIGILANCE)%>">
	  <td id="onglet1Centre2" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="250" height="21">
		<tr>
		  <td align="center" valign="middle" width="250" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.dossier.evaluations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_EVALUATIONS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_EVALUATIONS" onclick="setOnglet('#TAB_EVALUATIONS');ongletClick(1,2);">&nbsp;
						<bean:message key='titre.evaluation' />
						<logic:equal name='dossier' property='evaluations.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>			
		  </td>
		  <td id="onglet1Droit2" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		  </tr>
		</table>
		</td>
	</logic:equal> 
 	</tr>
   </table>
  </td>
 </tr>
</table>
</div>
<!-- End subtab navigation -->

