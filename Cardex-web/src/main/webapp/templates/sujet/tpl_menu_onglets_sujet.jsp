<%-- --------------------------------------------------------------------------
Use case    : Navigation par les onglets d'un sujet.
Description : Module qui affiche les onglets d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/09 20:43:44 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/22 21:21:24 , Author: abruno-boucher
Repositionnement des hyperliens sur 2 lignes.

$Revision: 1.5 $, $Date: 2002/04/09 20:43:44 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<!-- Sub tab navigation -->
<!-- Outline table -->
<div id="menuOnglets" style="visibility: hidden;">
<table style="margin: 0px;" cellpadding="0" cellspacing="0" width="900">
	<tr>
	  <td id="onglet0Centre0" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreHaut.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td id="onglet0Gauche0" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheHaut.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.sujet.narrations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_NARRATION');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_NARRATION" onclick="setOnglet('#TAB_NARRATION');ongletClick(0,0);">&nbsp;
						<bean:message key='tabpage_commentaires' />
						<logic:equal name='sujet' property='narrations.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.sujet.dossiers.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_FOLDERS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_FOLDERS" onclick="setOnglet('#TAB_FOLDERS');ongletClick(1,0);">&nbsp;
						<bean:message key='tabpage_dossier'/>
						<logic:equal name='sujet' property='dossiers.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.sujet.societes.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_SOCIETIES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_SOCIETIES" onclick="setOnglet('#TAB_SOCIETIES');ongletClick(2,0);">&nbsp;
						<bean:message key='tabpage_societe' />
						<logic:equal name='sujet' property='societes.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.sujet.relations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_INDIVIDUALS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_INDIVIDUALS" onclick="setOnglet('#TAB_INDIVIDUALS');ongletClick(3,0);">&nbsp;
						<bean:message key='tabpage_alias' />
						<logic:equal name='sujet' property='sujets.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet3Droit0" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	</tr>

	<tr>
	  <td id="onglet0Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td id="onglet0Gauche1" style="padding: 0px; width: 7px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletGaucheBas.gif);" nowrap="nowrap">
			<img src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="7" height="21" />
		  </td>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.sujet.adresses.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_ADDRESS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_ADDRESS" onclick="setOnglet('#TAB_ADDRESS');ongletClick(0,1);">&nbsp;
						<bean:message key='tabpage_adress' />
						<logic:equal name='sujet' property='adresses.empty' value='false'>
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
			<myriap:link
				securityConstraint='cardex.sujet.caracteristiques.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_CARACTERISTICS');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_CARACTERISTICS" onclick="setOnglet('#TAB_CARACTERISTICS');ongletClick(1,1);">&nbsp;
					<bean:message key='tabpage_carac' />
					<logic:equal name='sujet' property='caracteristiques.empty' value='false'>
						&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
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
			<myriap:link securityConstraint='cardex.sujet.photos.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_PHOTOS');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_PHOTOS" onclick="setOnglet('#TAB_PHOTOS');ongletClick(2,1);">&nbsp;
					<bean:message key='tabpage_photo' />
					<logic:equal name='sujet' property='photos.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.sujet.vehicules.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_VEHICLES');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_VEHICLES" onclick="setOnglet('#TAB_VEHICLES');ongletClick(3,1);">&nbsp; 
					<bean:message key='tabpage_vehicule' />
					<logic:equal name='sujet' property='vehicules.empty' value='false'>
						&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
					</logic:equal>
				</DIV>
			</myriap:link>			
		  </td>
		  <td id="onglet3Droit1" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	</tr>
	
</table>

</div>
<!-- End subtab navigation -->
