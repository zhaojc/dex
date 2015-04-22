<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>


<tiles:useAttribute name="soumettreURL" id="soumettreURL" classname="String"/>

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

<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript" src='<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js'></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doDetaillantValidation(){
//Saisie automatique du détaillant validation si le champ est vide. On copie le détaillant Provenance, s'il y en a un d'inscrit
	if ((document.forms(0).numeroDetaillantValidation.value == null ||document.forms(0).numeroDetaillantValidation.value.length == 0) 
		&& (document.forms(0).numeroDetaillantProvenance.value != null && document.forms(0).numeroDetaillantProvenance.value.length > 0 )){
		document.forms(0).numeroDetaillantValidation.value=document.forms(0).numeroDetaillantProvenance.value;
		soumettreActionMethod('rechercheValidation');
	}
}

function doDetaillantVerification(){
//Saisie automatique du détaillant vérification si le champ est vide. On copie le détaillant Provenance, s'il y en a un d'inscrit
	if ((document.forms(0).numeroDetaillantVerification.value == null ||document.forms(0).numeroDetaillantVerification.value.length == 0 ) 
		&& (document.forms(0).numeroDetaillantProvenance.value != null && document.forms(0).numeroDetaillantProvenance.value.length > 0 )){
		document.forms(0).numeroDetaillantVerification.value=document.forms(0).numeroDetaillantProvenance.value;
		soumettreActionMethod('rechercheVerification');	
	}
}

function doViderProvenance(){
//On retire les valeurs si l'utilisateur annule le détaillant saisi.
	document.forms(0).cleSocieteProvenance.value = "";
	document.forms(0).siteSocieteProvenance.value = "";
	document.forms(0).dateAchat.value = "";
}

function doTrouverProvenance(){
//Recherche automatique
	if (document.forms(0).numeroDetaillantProvenance.value != "" && document.forms(0).numeroDetaillantProvenance.value.length != 0 ){
		soumettreActionMethod('rechercheProvenance');
	}
}

function doViderValidation(){
	document.forms(0).cleSocieteValidation.value = "";
	document.forms(0).siteSocieteValidation.value = "";
	document.forms(0).dateValidation.value = "";
}

function doTrouverValidation(){
	if (document.forms(0).numeroDetaillantValidation.value != "" && document.forms(0).numeroDetaillantValidation.value.length != 0 ){
		soumettreActionMethod('rechercheValidation');
	}
}

function doViderVerification(){
	document.forms(0).cleSocieteVerification.value = "";
	document.forms(0).siteSocieteVerification.value = "";
	document.forms(0).dateVerification.value = "";
}

function doTrouverVerification(){
	if (document.forms(0).numeroDetaillantVerification.value != "" && document.forms(0).numeroDetaillantVerification.value.length != 0 ){
		soumettreActionMethod('rechercheVerification');
	}
}

function doViderFautif(){
	document.forms(0).cleSocieteFautif.value = "";
	document.forms(0).siteSocieteFautif.value = "";
}

function doTrouverFautif(){
	if (document.forms(0).numeroDetaillantFautif.value != "" && document.forms(0).numeroDetaillantFautif.value.length != 0 ){
		soumettreActionMethod('rechercheFautif');
	}
}

function validerChamps(){
//Sert à afficher un message si des champs d'identification ont été laissés vides.
//On ne force cependant pas de saisie dans les champs.
//On ne vérifie pas le numéro de contrôle à ce stade puisqu'il est obligatoire et que la validation se fera plus loin.
   if((document.forms(0).nom.value == "") || (document.forms(0).valeur.value == "") || (document.forms(0).montantLot.value == "") || (document.forms(0).typeLoterie.value == "")  || (document.forms(0).typeMise.value == "")){
      //alert("<bean:message key='identification.billet.vide' />");
      if ( confirmation("<bean:message key='identification.billet.vide' />")){
      	 soumettreForm();
      	 return true;
	  }else{
	     return false;
	  }
   }else{
      	soumettreForm();
      	return true;
   }
}   

function afficherExtraGagnant() { 
//On n'affiche la section de l'Extra gagnant que s'il y a une participation à l'Extra.

	if(document.getElementById("extra").checked == true){
		document.getElementById("extraGagnant").disabled = false;
	}else{
		document.getElementById("extraGagnant").checked = false;
		document.getElementById("extraGagnant").value = "false";
		document.getElementById("extraGagnant").disabled = true;
	} 
} 

</SCRIPT> 

      <!-- CONTENT -->
<TABLE width="720" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style='background-image: url("<%=request.getContextPath()%>/images/background_journal.jpg");'>
	<tr>
		<td><html:img page="/images/blank.gif" width="1"
			height="1" border="0" /></td>
	</TR>
	<TR>
		<td align="left">
		<TABLE width="710" cellpadding="2" cellspacing="2" border="0">
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap width="130"><b><bean:message key='nom.billet' /><bean:message
					key='2.points' /></b></td>
				<td width="180"><cardex:AutoCompleter name='billet' property="nom" maxlength="50" 
		                style="HEIGHT: 20px; WIDTH: 180px" classeControl='<%= GlobalConstants.AutoCompleterClass.NOM_BILLET_AUTO_COMPLETER %>'
		                height="150" width="150" nbrAmorce="2"/>
				</td>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap witdh="150"><b><bean:message key='valeur.billet' /><bean:message
					key='2.points' /></b></td>
				<td><cardex:argent name='billet' 
					property='valeur' maxlength='9'  style="HEIGHT: 20px; WIDTH: 50px" /> $</td>
			</TR>
			<TR>
				<td witdh="10">&nbsp;
				</td>
				<td align=left nowrap><b><bean:message key='type.loterie' /><bean:message
					key='2.points' /></b>
				</td>
				<td>
			          <myriap:select size='1' name='billet' property="typeLoterie" style="HEIGHT: 20px; WIDTH: 180px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_LOTERIE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
			          </myriap:select> 
				</td>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap><b><bean:message key='montant.lot' /><bean:message
					key='2.points' /></b></td>
				<td><cardex:argent name='billet'
					property='montantLot' maxlength='12'  style="HEIGHT: 20px; WIDTH: 103px" /> $</td>				
			</TR>
			<TR>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap><b><bean:message key='type.mise' /><bean:message
					key='2.points' /></b></td>
				<td>
					<myriap:select name='billet' property='typeMise' size='1' style='HEIGHT: 20px; WIDTH: 180px' >
					   <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_MISE %>'/>
					</myriap:select>
				</td>
			    <td witdh="10">&nbsp;</td>
				<td align="left" nowrap ><b><bean:message
					key='participation.extra' /><bean:message key='2.points' /></b></td>
				<TD width="145"><b><bean:message key='oui_t' /><bean:message key='2.points' /></b>
						<html:radio	name='billet' property='extra' value='true' onclick="afficherExtraGagnant();" />
					<b><bean:message key='non_t' /><bean:message key='2.points' /></b>
						<html:radio name='billet' property='extra' value='false' onclick="afficherExtraGagnant();" />
				</TD>

			</TR>
			<TR>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap><b><bean:message
					key='numero.control' /><bean:message key='2.points' /></b></td>
				<td><myriap:text name='billet'
					property='numeroControl' size="30" maxlength='30'  style="HEIGHT: 20px; WIDTH: 180px" /></td>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap >
						<b><bean:message key='extra.gagnant' /><bean:message key='2.points' /></b>
				</td>
				<TD width="145">
						<b><bean:message key='oui_t' /><bean:message key='2.points' /></b>
								<html:radio	name='billet' property='extraGagnant' value='true' onclick="document.getElementById('extraGagnant').value = 'true';"/>
						<b><bean:message key='non_t' /><bean:message key='2.points' /></b>
								<html:radio name='billet' property='extraGagnant' value='false' />
				</TD>				
			</DIV>
			</TR>

			<TR>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap><b><bean:message key='date.paiement' /><bean:message key='2.points' /></b>
				</td>
	     		<td width="100" align="left">
					<cardex:Date name='billet' property='datePaiement' calendrier="true" nomProchainChamp="nom"/>
				</td>
				<td witdh="10">&nbsp;</td>
				<td align="left" nowrap><b><bean:message key='montant.extra' /><bean:message
					key='2.points' /></b></td>
				<td><cardex:argent name='billet'
					property='montantExtra' maxlength='12'  style="HEIGHT: 20px; WIDTH: 103px" /> $</td>				
			</TR>
			
			<TR>
				<td witdh="10">&nbsp;
				</td>
				<td align="left" nowrap ><b><bean:message
					key='formule.groupe' /><bean:message key='2.points' /></b></td>
				<TD width="145"><b><bean:message key='oui_t' /><bean:message key='2.points' /></b>
						<html:radio	name='billet' property='formuleGroupe' value='true' />
					<b><bean:message key='non_t' /><bean:message key='2.points' /></b>
						<html:radio name='billet' property='formuleGroupe' value='false' />
				</TD>				
				<td witdh="10">&nbsp;</td>
				<td align="left" nowrap ><b><bean:message
					key='participation.tirage' /><bean:message key='2.points' /></b></td>
				<TD width="145"><b><bean:message key='oui_t' /><bean:message key='2.points' /></b>
						<html:radio	name='billet' property='participationTirage' value='true' />
					<b><bean:message key='non_t' /><bean:message key='2.points' /></b>
						<html:radio name='billet' property='participationTirage' value='false' />
				</TD>				
			</TR>
		  </TABLE>
		 </TD>
	</TR>
	</TR>
	<tr>
		<td align="left">
		<TABLE width="710" cellpadding="2" cellspacing="2" border="0">
			<tr>
				<td>
				<TABLE width="335" cellpadding="2" cellspacing="2" border="0" class="tableOutline">
					<TR>
						<TD colspan="2"><b><u><bean:message key='provenance.billet' /></u></b></TD>
					</TR>
					<TR>
						<TD align="left"><b><bean:message key='numero.detaillant' /><bean:message
							key='2.points' /></b></TD>
						<td><cardex:nombre name='billet'
							property='numeroDetaillantProvenance' size="6" maxlength='6' onchange="doViderProvenance();"
							onfocusout="doTrouverProvenance();"/>
						</td>
					</TR>
					<TR>
						<TD align="left" colspan="2"><b><bean:message key='detaillant' />
							<bean:message key='2.points' /></b>
							<bean:write name='billet' property='nomDetaillantProvenance' />
						</td>
					</TR>
					<tr>
						<td colspan="2" align="center"><html:img
							page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
					</TR>
					<TR>
						<TD align="left" width="175"><b><bean:message key='date.heure.achat' /><bean:message
							key='2.points' /></b></TD>
						<td> 
							<cardex:DateHeure name='billet' property='dateAchat' calendrier="true" nomProchainChamp="nomDetaillantProvenance" />
						</td>
					</TR>
				</TABLE>
				</td>
				<td>
				<TABLE width="335" cellpadding="2" cellspacing="2" border="0" class="tableOutline">
					<TR>
						<TD colspan="2"><b><u><bean:message key='validation.billet' /></u></b></TD>
					</TR>
					<TR>
						<TD align="left"><b><bean:message key='numero.detaillant' /><bean:message
							key='2.points' /></b></TD>
						<td><cardex:nombre name='billet'
							property='numeroDetaillantValidation' size="6" maxlength='6' onfocus="doDetaillantValidation();" 
							onchange="doViderValidation();" onfocusout="doTrouverValidation();"/>
					</TR>
					<TR>
						<TD align="left" colspan="2"><b><bean:message key='detaillant' /><bean:message key='2.points' /></b>
							<bean:write name='billet' property='nomDetaillantValidation' />
						</td>
					</TR>
					<tr>
						<td colspan="2" align="center"><html:img
							page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
					</TR>
					<TR>
						<TD align="left" width="165"><b><bean:message key='date.heure.validation' /><bean:message key='2.points' /></b></TD>
						<td>
							<cardex:DateHeure name='billet' property='dateValidation' calendrier="true" nomProchainChamp="nomDetaillantValidation" />
						</td>
					</TR>
				</TABLE>
				</td>
			</tr>
		</TABLE>
		</TD>
	</TR>
	<tr>
		<td align="left">
		<TABLE width="710" cellpadding="2" cellspacing="2" border="0">
			<tr>
				<td>
				<TABLE width="335" cellpadding="2" cellspacing="2" border="0" class="tableOutline">
					<TR>
						<TD colspan="2"><b><u><bean:message key='verification.billet' /></u></b></TD>
					</TR>
					<TR>
						<TD align="left"><b><bean:message key='numero.detaillant' /><bean:message
							key='2.points' /></b></TD>
						<td><cardex:nombre name='billet'
							property='numeroDetaillantVerification' size="6" maxlength='6' onfocus="doDetaillantVerification();"
							onchange="doViderVerification();" onfocusout="doTrouverVerification();"/>
					</TR>
					<TR>
						<TD align="left" colspan="2"><b><bean:message key='detaillant' />
							<bean:message key='2.points' /></b>
							<bean:write name='billet' property='nomDetaillantVerification' />
						</td>
					</TR>
					<tr>
						<td colspan="2" align="center"><html:img
							page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
					</TR>
					<TR>
						<TD align="left" width="175"><b><bean:message key='date.heure.verification' /><bean:message
							key='2.points' /></b></TD>
						<td> 
							<cardex:DateHeure name='billet' property='dateVerification' calendrier="true" nomProchainChamp="nomDetaillantVerification" />
						</td>
					</TR>
				</TABLE>
				</td>
				<td valign="top">
				<TABLE width="335" cellpadding="2" cellspacing="2" border="0" class="tableOutline">
					<TR>
						<TD colspan="2"><b><u><bean:message key='detaillant.fautif' /></u></b></TD>
					</TR>
					<TR>
						<TD align="left" width="165"><b><bean:message key='numero.detaillant' /><bean:message
							key='2.points' /></b></TD>
						<td><cardex:nombre name='billet'
							property='numeroDetaillantFautif' size="6" maxlength='6' onchange="doViderFautif();" 
							onfocusout="doTrouverFautif();"/>
					</TR>
					<TR>
						<TD align="left" colspan="2"><b><bean:message key='detaillant' /><bean:message key='2.points' /></b>
							<bean:write name='billet' property='nomDetaillantFautif' />
						</td>
					</TR>
				</TABLE>
				</td>
			</tr>
		</TABLE>
		</TD>
	</TR>
	<tr>
		<td align="center"><html:img
			page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
	</TR>
	<TR>
		<TD align="center" ><!-- BOTTOM BUTTONS -->
		<TABLE width="710" cellpadding="3" cellspacing="0" border="0">
			<TR>
				<TD align="left">
					<cardex:button urlSecurite='<%=soumettreURL%>' labelKey='cb_ok' onclick="validerChamps();" style="color: #000000; width: 70px; text-align: center" />
				</TD>
				<TD align="right">
					<cardex:button urlSecurite='<%=soumettreURL%>' labelKey='cb_annuler' onclick="soumettreActionMethod('retour');" style="color: #000000; width: 60px; text-align: center" />
				</TD>
			</TR>
		</TABLE>
		<!-- END BOTTOM BUTTONS --></TD>
	</TR>
</TABLE>

<SCRIPT>
  afficherExtraGagnant();
</SCRIPT>

<!-- Lien entité -->
<html:hidden property="lien"/>
<html:hidden property="lienSite"/>
<html:hidden property="lienGenre"/>

<!-- Lien société provenance -->
<html:hidden property="cleSocieteProvenance"/>
<html:hidden property="siteSocieteProvenance"/>

<!-- Lien société validation -->
<html:hidden property="cleSocieteValidation"/>
<html:hidden property="siteSocieteValidation"/>

<!-- Lien société verification -->
<html:hidden property="cleSocieteVerification"/>
<html:hidden property="siteSocieteVerification"/>

<!-- Lien société fautif -->
<html:hidden property="cleSocieteFautif"/>
<html:hidden property="siteSocieteFautif"/>
