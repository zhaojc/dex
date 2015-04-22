<%-- --------------------------------------------------------------------------
Use case    : Recherche d'un sujet.
Description : Module d'affichage représentant le formulaire de recherche d'un
              sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.19 $, $Date: 2002/04/30 12:18:06 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.19 $, $Date: 2002/04/30 12:18:06 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>
 
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="urlSecuriteRecherche" id="urlSecuriteRecherche" classname="String"/>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/listeAJAXHelper.js"></SCRIPT>

<SCRIPT language="JavaScript">

function validation(){
    var ok = true;
    if(document.forms(0).dateNaissance.value != ""){
	doTraits();
	if(document.forms(0).dateNaissance.value.length != 10){
	   ok = false;
	}else{
	//La date de naissance ne doit contenir que des caractères numériques, à part les traits d'union
	   nombre = "1234567890";
	   for (var i = 0; i < document.forms(0).dateNaissance.value.length; i++) {
		   var lettre = document.forms(0).dateNaissance.value.charAt(i);
		   var pos = nombre.indexOf(lettre);
		   //alert(lettre + " " + i);
		if(pos == -1){
		  //On s'assure que les traits d'union sont à la bonne place
		  if((i == 4) || (i == 7)){
		     if("-".indexOf(lettre) == -1){
			ok = false;
		     }
		  }else{  //ni un chiffre ni un trait d'union
		        ok = false;
		  }
		}
           } //for
	}
   }
   if(ok){
	  doSearch();
	  return 0;
   }else{
	  message("La date de naissance est invalide");
   }
}

</SCRIPT>


<!-- Kind & nature search fields -->
<!-- Following table is used to produce an outline -->
<TABLE cellpadding="0" cellspacing="0" border="0" class="tableOutline">
  <TR>
		<TD>

		<TABLE cellpadding="2" cellspacing="0" border="0"
			style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#e6ebf4', startColorstr='#FFFFFF', gradientType='1');">
			<TR>
				<TD ALIGN="right"><html:img page="/images/blank.gif" width="1"
					height="1" border="0" /><br>
				<b><bean:message key='i_en_cle_t' /></b></TD>
        		<bean:define id="entite" name='rechercheSujet' property="entite" type="String"/>
				<TD ALIGN="left"><html:img page="/images/blank.gif" width="1"
					height="1" border="0" /><br>
				<myriap:select 
					name='rechercheSujet' property='entite' size='1' onchange='doSoumettreRafraichir();'
					style='HEIGHT: 20px; WIDTH: 160px' tabindex="1">
			   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
				</myriap:select></TD>
				<TD ALIGN="right" width="130"><b><bean:message key='co_no_fiche_t' /></b></TD>
				<TD ALIGN="left"><myriap:text
					name='rechercheSujet' property="numeroFiche"
					style="HEIGHT: 20px; WIDTH: 120px"
					onchange='document.forms(0).numeroFiche.value = document.forms(0).numeroFiche.value.toUpperCase();'
					tabindex="12" /></TD>
				<TD ALIGN="right"><b><bean:message key='i_cc_cle_t' /></b></TD>
				<TD ALIGN="left"> <myriap:select size='1'
					name='rechercheSujet' property='confidentialite'
					style='HEIGHT: 20px; WIDTH: 170px' tabindex="22">
					<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE %>' />
				</myriap:select></TD>
				<TD width="110">&nbsp;</TD>
			</TR>

			<TR>
		        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
			    <TD ALIGN="left">
			       <myriap:select size='1' name='rechercheSujet' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px' tabindex="2"  onchange='doSoumettreRafraichir();'>
				      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				        valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>'  
						valeurDiscriminant='<%=entite%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
			        </myriap:select>
			    </TD>
				<TD ALIGN="right"><b><bean:message key='i_sx_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property='sexe'
					style='HEIGHT: 20px; WIDTH: 120px' tabindex="13">
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.SEXE %>'/>
				</myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='age_t' /></b></TD>
				<TD colspan="2" ALIGN="left">
				
				<TABLE cellpadding="0" cellspacing="0">
					<TR> 
						<TD align="left" width="50"><myriap:text
							name='rechercheSujet'
							property='age' maxlength='3' style="HEIGHT: 20px; WIDTH: 25px"
							tabindex="23" /></TD>
						<TD align="right"><b><bean:message key='age.reel'/></b><html:checkbox name='rechercheSujet'
							property='ageReel' /> &nbsp;</TD>
						<TD align="right"><b><bean:message key='age.reel.plus.moins5'/></b><html:checkbox name='rechercheSujet'
							property='ageReelPlusMoins' /> &nbsp;</TD>
						<TD align="right"><b><bean:message key='age.estime.plus.moins5'/></b><html:checkbox name='rechercheSujet'
							property='ageEstime' />&nbsp;</TD>
						<TD align="right"><b><bean:message key='age.inconnu'/></b><html:checkbox name='rechercheSujet'
							property='ageInconnu' /></TD>
					</TR>
				</TABLE> 
				</TD>
			</TR>

			<TR>
				<TD ALIGN="right"><b><bean:message key='v_su_nom_textuel' /></b></TD>
				<TD ALIGN="left">
				    <cardex:AutoCompleter name='rechercheSujet' property="nom" tabindex="3" maxlength="30" 
            		style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.NOM_SUJET_AUTO_COMPLETER %>'
            		height="150" width="150" nbrAmorce="2"/>
				</TD>
				<TD ALIGN="right"><b><bean:message key='i_nt_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property="ethnie"
					style="HEIGHT: 20px; WIDTH: 120px" tabindex="14"
					onkeypress="typeAhead(this, event);"
					onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.ETHNIE %>'/>
				</myriap:select></TD>
				<TD ALIGN="right" nowrap><b><bean:message key='date_naissance_t' /></b></TD>
				<TD ALIGN="left">
					<cardex:Date name='rechercheSujet' property="dateNaissance" nomProchainChamp="caracteristique1" tabindex="24"/>
				</TD>
				<TD>&nbsp;</TD>
			</TR>

			<TR>
				<TD ALIGN="right" width="130"><b><bean:message key='v_su_nom_phonetique' /></b></TD>
				<TD ALIGN="left"><myriap:text
					name='rechercheSujet' property='nomPhonetique'
					style='HEIGHT: 20px; WIDTH: 160px' tabindex="4" /></TD>
				<TD ALIGN="right"><b><bean:message key='i_ro_cle_t' /></b></TD>
				<TD ALIGN="left">
		          <bean:define id="site" name='rechercheSujet' property="siteOrigine" type="String"/>
		          <myriap:select size='1' name='rechercheSujet' property="role"
		          style="HEIGHT: 20px; WIDTH: 160px" tabindex="15" >
		          	  <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=site %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
		          </myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='l_cr_cle_1_t' /></b></TD>
				<TD ALIGN="left" nowrap><myriap:select size='1' name='rechercheSujet' property='caracteristique1' style='HEIGHT: 20px; WIDTH: 170px' tabindex="25">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.CARACTERISTIQUE %>'/>
				</myriap:select></TD>
				<TD rowspan="4" valign="top" align="left">
				<TABLE cellpadding="0" cellspacing="0" border="0">
					<TR>
						<TD>&nbsp;</TD>
						<TD align="center"><b><bean:message key='et' /></b></TD>
						<TD align="center"><b><bean:message key='ou' /></b></TD>
					</TR>
					<TR>
						<TD><html:img page="/images/jonction.gif" border="0" height="22" />
						</TD>
						<TD><html:radio name='rechercheSujet' property='or1'
							value='<%=GlobalConstants.Operateur.ET%>' /></TD>
						<TD><html:radio name='rechercheSujet' property='or1'
							value='<%=GlobalConstants.Operateur.OU%>' /></TD>
					</TR>
					<TR>
						<TD><html:img page="/images/jonction.gif" border="0" height="22" />
						</TD>
						<TD><html:radio name='rechercheSujet' property='or2'
							value='<%=GlobalConstants.Operateur.ET%>' /></TD>
						<TD><html:radio name='rechercheSujet' property='or2'
							value='<%=GlobalConstants.Operateur.OU%>' /></TD>
					</TR>
					<TR>
						<TD><html:img page="/images/jonction.gif" border="0" height="22" />
						<TD><html:radio name='rechercheSujet' property='or3'
							value='<%=GlobalConstants.Operateur.ET%>' /></TD>
						<TD><html:radio name='rechercheSujet' property='or3'
							value='<%=GlobalConstants.Operateur.OU%>' /></TD>
					</TR>
				</TABLE>
				</TD>

			</TR>

			<TR>
				<TD ALIGN="right"><b><bean:message key='v_su_prenom_phonetique' /></b></TD>
				<TD ALIGN="left"><myriap:text
					name='rechercheSujet' property="prenomPhonetique"
					style="HEIGHT: 20px; WIDTH: 160px" tabindex="5" /></TD>
				<TD ALIGN="right"><b><bean:message key='i_ls_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property="langue"
					style="HEIGHT: 20px; WIDTH: 120px" tabindex="16">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.LANGUE %>'/>
				</myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='l_cr_cle_1_t' /></b></TD>
				<TD ALIGN="left" colspan="2" nowrap><myriap:select size='1'
					name='rechercheSujet' property='caracteristique2'
					style='HEIGHT: 20px; WIDTH: 170px' tabindex="26">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.CARACTERISTIQUE %>'/>
				</myriap:select></TD>
			</TR>


			<TR>
				<TD ALIGN="right"><b><bean:message key='v_su_surnom_phonetique' /></b></TD>
				<TD ALIGN="left"><myriap:text name='rechercheSujet'
					property="alias" style="HEIGHT: 20px; WIDTH: 160px" tabindex="6" maxlength="50" /></TD>			
				<TD ALIGN="right"><b><bean:message key='i_ra_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property="race"
					style="HEIGHT: 20px; WIDTH: 120px" tabindex="17">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.RACE %>'/>
				</myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='l_cr_cle_1_t' /></b></TD>
				<TD ALIGN="left" colspan="2" nowrap><myriap:select size='1'
					name='rechercheSujet' property='caracteristique3'
					style='HEIGHT: 20px; WIDTH: 170px' tabindex="27">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.CARACTERISTIQUE %>'/>
				</myriap:select></TD>
			</TR>

			<TR>
				<TD ALIGN="right"><b><bean:message key='i_pa_cle_t' /></b></TD>
				<TD ALIGN="left">
				<bean:define id="pays" name='rechercheSujet' property="pays" type="String"/>
				<myriap:select size='1'
					name='rechercheSujet' property="pays"
					style="HEIGHT: 20px; WIDTH: 160px"
					onchange="viderListe('ville');doRafraichir('province',this.value);"
					tabindex="7" onkeypress="typeAhead(this, event);"
					onfocus="resetIncrementalSearch();">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.PAYS %>'/>
				</myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='i_st_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property="statut"
					style="HEIGHT: 20px; WIDTH: 120px" tabindex="18">
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SUJET %>'/>
				</myriap:select></TD>
				<TD ALIGN="right"><b><bean:message key='l_cr_cle_1_t' /></b></TD>
				<TD ALIGN="left" colspan="2" nowrap><myriap:select size='1'
					name='rechercheSujet' property='caracteristique4'
					style='HEIGHT: 20px; WIDTH: 170px' tabindex="28">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.CARACTERISTIQUE %>'/>
				</myriap:select></TD>
			</TR>

			<TR>
				<TD ALIGN="right"><b><bean:message key='l_pr_cle_t' /></b></TD>
				<TD ALIGN="left">
					<bean:define id="province" name='rechercheSujet' property="province" type="String"/>
					<myriap:select size='1'
					name='rechercheSujet' property="province"
					style="HEIGHT: 20px; WIDTH: 160px"
					onchange="doSoumettreRafraichir();" tabindex="8"
					onkeypress="typeAhead(this, event);"
					onfocus="resetIncrementalSearch();">
					<cardex:optionTag classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays%>'/>
				</myriap:select></TD>
				<TD ALIGN="right" colspan="2">
				<b><bean:message key='i_se_cle_t' /></b>&nbsp;
					<myriap:select size='1'
					name='rechercheSujet' property="severiteAutres"
					style="HEIGHT: 20px; WIDTH: 40px" tabindex="19">
					<cardex:severiteOptions />
					</myriap:select>
				</TD>
				<TD ALIGN="right"><b><bean:message key='passeport_t' /></b></TD>
				<TD ALIGN="left" colspan="2"><myriap:text
					name='rechercheSujet' property="passeport"
					style="HEIGHT: 20px; WIDTH: 170px"
					onchange='this.value=this.value.toUpperCase();' tabindex="29" /></TD>
			</TR>

			<TR>
				<TD ALIGN="right"><b><bean:message key='l_vi_cle_t' /></b></TD>
				<TD ALIGN="left"><myriap:select size='1'
					name='rechercheSujet' property="ville"
					style="HEIGHT: 20px; WIDTH: 160px" tabindex="9"
					onkeypress="typeAhead(this, event);"
					onfocus="resetIncrementalSearch();">
					 <cardex:optionTag classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province%>'/>
				</myriap:select> 
				</TD>
				<TD ALIGN="right" colspan="2">
					<cardex:SecuriteLibelle nomFormulaire="rechercheSujet" propertyFormulaire="severite" key='i_se_cle_inv'/>
					<myriap:select size='1'
					name='rechercheSujet' property="severite"
					style="HEIGHT: 20px; WIDTH: 40px" tabindex="20">
					<cardex:severiteOptions />
					</myriap:select>
				</TD>
				<TD ALIGN="right" nowrap><b><bean:message key='v_su_assurance_maladie_t' /></b></TD>
				<TD ALIGN="left" colspan="2"><myriap:text
					name='rechercheSujet' property="numeroAssuranceMaladie"
					style="HEIGHT: 20px; WIDTH: 170px"
					tabindex="30" /></TD>
			</TR>
			<TR>
				<TD ALIGN="right" nowrap><b><bean:message key='v_su_reference_2_t' /></b></TD>
				<TD ALIGN="left"><myriap:text
					name='rechercheSujet' property="numeroClient"
					style="HEIGHT: 20px; WIDTH: 160px" tabindex="10" /></TD>
				<TD ALIGN="right" colspan="2">
					<cardex:SecuriteLibelle nomFormulaire="rechercheSujet" propertyFormulaire="severiteCasino" key='i_se_cle_inv_casino'/>
					<myriap:select size='1'
					name='rechercheSujet' property="severiteCasino"
					style="HEIGHT: 20px; WIDTH: 40px" tabindex="21">
					<cardex:severiteOptions />
					</myriap:select>
				</TD>
	            <TD ALIGN="right"><b><bean:message key='v_su_permis_conduire_t'/></b></TD>
	            <TD ALIGN="left" colspan="2">
	            	<myriap:text name='rechercheSujet' property="numeroPermisConduire" tabindex="31" maxlength="20" style="HEIGHT: 20px; WIDTH: 170px" /> 
	            </TD>
			</TR>

			<TR>
				<TD ALIGN="right" nowrap><b><bean:message key='v_su_reference_1_t' /></b></TD>
				<TD ALIGN="left"><myriap:text
					name='rechercheSujet' property="reference"
					style="HEIGHT: 20px; WIDTH: 160px"
					onchange='this.value=this.value.toUpperCase();' tabindex="11" /></TD>
				<TD colspan="5">&nbsp;</TD>
			</TR>
			<TR>
				<TD ALIGN="center" COLSPAN="7" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="100%" height="1" border="0" /></TD>
			</TR>
			<TR>
				<TD WIDTH="1000" ALIGN="center" COLSPAN="7">

				<TABLE width="100%" cellPadding="2" cellSpacing="0" border="0">
					<tr>
						<TD ALIGN="left"><b><bean:message key='affichage_resultats' /></b>
						<html:select size='1' name='rechercheSujet'
							property='listeResultat.plageResultats'>
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
						</html:select></TD>
						<TD><cardex:button accessKey='r' urlSecurite='<%=urlSecuriteRecherche%>'
							labelKey='cb_rechercher' onclick='validation();' /> <!--input type="submit" value='rechercher' name='soumettre' / -->
						</TD>
						<TD>&nbsp;
						<!-- On ne permet l'ajout qu'à partir de l'écran de liaison -->
				            <cardex:securityDefineTag nameDefine="ajoutSujet" securityConstraint="cardex.sujet.new">
							    <logic:present name="rechercheSujet" property="entiteCardexLiaison" >
							        <b><bean:message key='i_ro_cle_t' /></b>&nbsp;
							        <bean:define id="siteLiaison" name='rechercheSujet' property="lienSite" type="String"/>
							        <logic:equal name="rechercheSujet" property="typeLien" value="<%=GlobalConstants.LienRole.RELATION %>">
								    	<myriap:select name="rechercheSujet" property="roleLiaison" size="1" style="height:20px; WIDTH: 150px; "  >
											<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=siteLiaison %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON_RELATION %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
										</myriap:select>
									</logic:equal>          
							        <logic:notEqual name="rechercheSujet" property="typeLien" value="<%=GlobalConstants.LienRole.RELATION %>" >
								    	<myriap:select name="rechercheSujet" property="roleLiaison" size="1" style="height:20px; WIDTH: 150px; "  >
											<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=siteLiaison %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
										</myriap:select>
									</logic:notEqual>          
							    	&nbsp;
							        <cardex:button onclick="doAdd();" urlSecurite="/sujet/new.do" labelKey='cb_ajouter' />
								</logic:present>
								<!-- Sauf pour le journal des enquêteurs de Loto-Québec -->
							    <logic:notPresent name="rechercheSujet" property="entiteCardexLiaison" >
							    	<cardex:securityDefineTag nameDefine="sujetJournalCreate" urlSecurite="/sujet/journal/create">
							         	<cardex:button onclick="doAdd();" urlSecurite="/sujet/new.do" labelKey='cb_ajouter' />
							         </cardex:securityDefineTag>
								</logic:notPresent>							
							</cardex:securityDefineTag>
						</TD>
						<TD width="100"><cardex:button
							urlSecurite="/sujet/delete.do"
							labelKey='cb_epuration' onclick='doDelete();' /></TD>
						<TD align="right">
						    <!-- On retire le bouton Effacer critères dans les écrans de liaison, car il ramène à l'écran de recherche au lieu de liaison -->
						    <logic:notPresent name="rechercheSujet" property="entiteCardexLiaison" >
						    	<cardex:button labelKey='cb_clear' soumettre="/sujet/search/reset/default.do" style='width: 120px; text-align: center;' />
						    </logic:notPresent> 
							<cardex:button labelKey='cb_fermer' onclick='doClose();' />
						</TD>
					</TR>

				</TABLE>

				</TD>






			</TR>

			<TR>
				<TD ALIGN="middle" COLSPAN="7"><html:img page="/images/blank.gif"
					width="1" height="1" border="0" /></TD>
			</TR>
		</TABLE>
    <!-- End Kind & nature search fields -->

    </TD>
  </TR>
</TABLE>
<!-- End outline table -->
