<%-- --------------------------------------------------------------------------
Use case    : Navigation par les onglets d'un véhicule.
Description : Module qui affiche les onglets d'un véhicule.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/11 18:49:06 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/22 21:21:24 , Author: abruno-boucher
Repositionnement des hyperliens sur 2 lignes.

$Revision: 1.5 $, $Date: 2002/04/11 18:49:06 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<!-- Sub tab navigation -->
<!-- Outline table -->
<div id="menuOnglets" style="visibility: hidden;">
<table style="margin: 0px;" cellpadding="0" cellspacing="0" width="775">
	<tr>
	  <td id="onglet0Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreHaut.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td id="onglet0Gauche0" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheHaut.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link
				securityConstraint='cardex.vehicule.narrations.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_NARRATION');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_NARRATION" onclick="ongletClick(0,0);">&nbsp;
					<bean:message key='tabpage_commentaires' />
					<logic:equal name='vehicule' property='narrations.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.vehicule.dossiers.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_FOLDERS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_FOLDERS" onclick="ongletClick(1,0);">&nbsp;
						<bean:message key='tabpage_dossier' />
						<logic:equal name='vehicule' property='dossiers.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
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
			<myriap:link securityConstraint='cardex.vehicule.sujets.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_INDIVIDUALS');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_INDIVIDUALS" onclick="ongletClick(2,0);">&nbsp;
					<bean:message key='tabpage_sujet' />
					<logic:equal name='vehicule' property='sujets.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.vehicule.societes.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_SOCIETIES');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_SOCIETIES" onclick="ongletClick(3,0);">&nbsp;
					<bean:message key='tabpage_societe' />
					<logic:equal name='vehicule' property='societes.empty' value='false'>
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
				securityConstraint='cardex.vehicule.particularites.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_PARTICULARITES');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_PARTICULARITES" onclick="ongletClick(4,0);">&nbsp;
					<bean:message key='tabpage_particularite' />
					<logic:equal name='vehicule' property='particularites.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.vehicule.photos.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_PHOTOS');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_PHOTOS" onclick="ongletClick(5,0);">&nbsp; 
					<bean:message key='tabpage_photo' />
					<logic:equal name='vehicule' property='photos.empty' value='false'>
						&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
					</logic:equal>
				</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet5Droit0" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	</tr>
</table>

</div>
<!-- End subtab navigation -->
