<%-- --------------------------------------------------------------------------
Use case    : Navigation par les onglets d'une société.
Description : Module qui affiche les onglets d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.5 $, $Date: 2002/04/11 19:21:51 $

History     : Voir ci-dessous.

$Revision: 1.5 $, $Date: 2002/04/11 19:21:51 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>


<BR>
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
					securityConstraint='cardex.societe.narrations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_NARRATION');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_NARRATION" onclick="ongletClick(0,0);">&nbsp;
						<bean:message key='tabpage_commentaires' />
						<logic:equal name='societe' property='narrations.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.societe.dossiers.onglet'
				href="javascript:toggleDivisionVisibility('#TAB_FOLDERS');"
				onmouseover="window.status=''; return true">
				<DIV id="TAB_FOLDERS" onclick="ongletClick(1,0);">&nbsp;
					<bean:message key='tabpage_dossier' />
					<logic:equal name='societe' property='dossiers.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.societe.sujets.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_INDIVIDUALS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_INDIVIDUALS" onclick="ongletClick(2,0);">&nbsp;
						<bean:message key='tabpage_sujet' />
						<logic:equal name='societe' property='sujets.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.societe.relations.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_SOCIETIES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_SOCIETIES" onclick="ongletClick(3,0);">&nbsp;
						<bean:message key='tabpage_alias' />
						<logic:equal name='societe' property='societes.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.societe.adresses.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_ADDRESS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_ADDRESS" onclick="ongletClick(0,1);">&nbsp;
						<bean:message key='tabpage_adress' />
						<logic:equal name='societe' property='adresses.empty' value='false'>
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
				<myriap:link securityConstraint='cardex.societe.photos.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_PHOTOS');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_PHOTOS" onclick="ongletClick(1,1);">&nbsp; 
						<bean:message key='tabpage_photo'/>
						<logic:equal name='societe' property='photos.empty' value='false'>
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
			<myriap:link securityConstraint='cardex.societe.vehicules.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_VEHICLES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_VEHICLES" onclick="ongletClick(2,1);">&nbsp; 
						<bean:message key='tabpage_vehicule' />
						<logic:equal name='societe' property='vehicules.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet2Droit1" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBas.gif);" nowrap="nowrap">
			<img id="dtabs_b0i6aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	  <td id="onglet3Centre1" style="border-style: ridge; border-width: 0px; margin: 0px; cursor: default; background-image: url(<%=request.getContextPath()%>/images/OngletCentreBas.gif); background-repeat: repeat-x; background-color: rgb(255, 255, 255);">
		<table cellpadding="0" cellspacing="0" width="100%" height="21">
		<tr>
		  <td align="center" valign="middle" width="100%" nowrap="nowrap">
			<myriap:link securityConstraint='cardex.societe.proprietaires.onglet'
					href="javascript:toggleDivisionVisibility('#TAB_PROPRIETAIRES');"
					onmouseover="window.status=''; return true">
					<DIV id="TAB_PROPRIETAIRES" onclick="ongletClick(3,1);">&nbsp; 
						<bean:message key='historique' />
						<logic:equal name='societe' property='proprietaires.empty' value='false'>
							&nbsp; <img border="0" src="<%=request.getContextPath()%>/images/FlecheBas.GIF" width="9" height="10">
						</logic:equal>
					</DIV>
			</myriap:link>
		  </td>
		  <td id="onglet3Droit1" style="padding: 0px; width: 21px; height: 21px; background-repeat: no-repeat; background-image: url(<%=request.getContextPath()%>/images/OngletDroitBasFin.gif);" nowrap="nowrap">
			<img id="dtabs_b0i7aimgblank" src="<%=request.getContextPath()%>/images/blank.gif" style="display: block;" width="21" height="21" />
		  </td>
		</tr>
		</table>
	  </td>
	</tr>
</table>

</div>
<!-- End subtab navigation -->
