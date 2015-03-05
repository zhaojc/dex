<%-- -------------------------------------------------------------------------- 
Use case    : Consultation d'un sujet.
Description : Module d'affichage représentant le formulaire de consultation d'un
              sujet.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.20 $, $Date: 2002/04/19 17:56:25 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/14 19:58:57 , Author: abruno-boucher
Création du contenu RESULTAT SUJET.

Revision: 1.16 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.20 $, $Date: 2002/04/19 17:56:25 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<%-- urlSecurite = /sujet/update.do ou /sujet/save.do --%>

<!-- Récupération de l'entité à laquelle appartient l'utilisateur pour déterminer
     le droit de sauvegarder (permis seulement si l'entité de l'utilisateur est la même 
     que celle de la fiche sujet, indépendamment de la règle ClearTrust) -->
<%
   String sujetEntite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetEntite   = String.valueOf(sujetCardex.getEntite());
   }
   catch (Throwable e) {}

%>

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

<SCRIPT language="JavaScript">
function doTraits() {
//-- Calcul de l'âge et ajout de traits d'union dans la date, s'il y a lieu.
   naissance = document.forms(0).dateNaissance.value;
   if(naissance.length == 8){
      if("<%= var_lang %>" == "fr"){
 	naissance = naissance.substring(0,4)+"-"+naissance.substring(4,6)+"-"+naissance.substring(6,8);
      }else{
 	naissance = naissance.substring(0,2)+"/"+naissance.substring(2,4)+"/"+naissance.substring(4,8);
      }
      document.forms(0).dateNaissance.value = naissance;
   }
   document.forms(0).age.value = computeAge(naissance);
}
function doMajuscules(objet) {
//-- Correction des majuscules initiales (nom et prénom). Si le texte a été écrit
//-- en lettres majuscules, on convertit en minuscules.
   resultat = objet.value;
   verification = " -";
   //-- On met la majuscule initiale
   resultat = resultat.substring(0,1).toUpperCase()+resultat.substring(1,40).toLowerCase();
   //-- On retire un espace initial, s'il y a lieu.
   indexpoint = resultat.indexOf(" ");
   if (indexpoint == 0) {
	resultat = resultat.substring(1,2).toUpperCase()+resultat.substring(2,40).toLowerCase();
   }
   longueur = resultat.length
   //-- Après un espace blanc ou un trait d'union, on met également une lettre majuscule
   for(var i=0; i < longueur; i++){
      if(verification.indexOf(resultat.charAt(i)) != -1){
      //--alert(i + "  " + resultat.substring(i+1,i+2));
         resultat = resultat.substring(0,i+1)+resultat.substring(i+1,i+2).toUpperCase()+resultat.substring(i+2,longueur).toLowerCase();
      }
   }
   objet.value = resultat;
}

function subDateNaissance(annee){
	var txtDateNaissance = document.getElementsByName("dateNaissance")[0];

	if (annee == "" || IsNumeric(annee) == false){
		txtDateNaissance.value="";
		return;
	}

	var dateNaissance = new Date();
	var anneeActuelle = parseInt(dateNaissance.getFullYear())-parseInt(annee);
	dateNaissance.setFullYear(anneeActuelle);

	txtDateNaissance.value = formatDateNaissance(dateNaissance);
}

function formatDateNaissance(date){
	return date.getFullYear()+"-"+right("00"+(date.getMonth()+1),2)+"-"+right("00"+date.getDate(), 2);
}

function doCopier() {
//Fonction temporaire pour permettre la copie des données d'une société à l'autre, dans le but d'éliminer les doublons de sociétés.
   if(document.forms(0).sujetDestinataire.value != ""){
      if ( confirm('Voulez-vous vraiment copier les données du sujet ' + document.forms(0).numeroFiche.value + " au sujet " + document.forms(0).sujetDestinataire.value + "?") ){
      	soumettre("<%= request.getContextPath()%>/sujet/copier.do?cle=" + document.forms(0).cle.value + "&site=" + document.forms(0).site.value + "&sujetDestinataire=" + document.forms(0).sujetDestinataire.value);
      }
   }
}

</SCRIPT>
<!-- Following table is used to produce an outline -->
<TABLE cellpadding="1" cellspacing="0" border="0" >
<TR>
    <TD CLASS="tabTitle">
        <TABLE  width="900" cellpadding="2" cellspacing="0" border="0" 
              style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');">
        <!-- First row uses transparent pixel to force good alignment -->
        <TR>
            <TD ALIGN="center" COLSPAN="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='v_no_fiche_t'/></b></TD>
            <TD ALIGN="left"><b>
	        	<bean:write name='sujet' property="numeroFiche" /></b>
	        	<html:hidden name='sujet' property="numeroFiche" />
	        </TD>
            <TD ALIGN="right"><b><bean:message key='i_sx_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <myriap:select name='sujet' property="sexe" size="1" tabindex="8" style="HEIGHT: 20px; WIDTH: 120px">
                   <cardex:optionTag classe='<%= GlobalConstants.CleListe.SEXE %>'/>
                </myriap:select>
            </TD>
            <TD ALIGN="right" >&nbsp;
              <cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="motPasse" key='password'/>
            </TD>
            <TD ALIGN="left">
              <myriap:password name='sujet' property="motPasse" style="HEIGHT: 22px; WIDTH: 120px" maxlength="20" tabindex="16"/>
            </TD>
            <TD>&nbsp;</TD>
        </TR>
 
        <TR>
            <TD ALIGN="right"><b><bean:message key='v_su_nom_t'/></b></TD>
            <TD ALIGN="left">
         	 <logic:equal name='sujet' property='indicateurRdd' value='true' >
		     	<bean:write name="sujet" property="nom"/>
		     </logic:equal>
   			 <logic:equal name='sujet' property='indicateurRdd' value='false' >
	            <myriap:text name='sujet' property="nom" tabindex="1" size="1" maxlength="30" style="HEIGHT: 20px; WIDTH: 120px" onchange='doMajuscules(document.forms(0).nom)' onblur="doVerificationNumeroFiche();" />
			 </logic:equal>
            </TD>
            <TD ALIGN="right" VALIGN="top"><b><bean:message key='i_ls_cle_t'/></b></TD>
            <TD ALIGN="left" VALIGN="top"><html:select name='sujet' property="langue" tabindex="9" size="1" style="HEIGHT: 20px; WIDTH: 120px">
                   <cardex:optionTag classe='<%= GlobalConstants.CleListe.LANGUE %>'/>
                </html:select>
            </TD>
            <TD ALIGN="right">&nbsp;
               <html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="confirmationMotPasse" key='password_confirm'/>
            </TD>
            <TD ALIGN="left">
               <myriap:password name='sujet' property="confirmationMotPasse" style="HEIGHT: 22px; WIDTH: 120px" maxlength="20" tabindex="17"/>
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='v_su_prenom_t'/></b></TD>
            <TD ALIGN="left">
	         	 <logic:equal name='sujet' property='indicateurRdd' value='true' >
			     	<bean:write name="sujet" property="prenom"/>
			     </logic:equal>
	   			 <logic:equal name='sujet' property='indicateurRdd' value='false' >
		            <myriap:text name='sujet' property="prenom" tabindex="2" maxlength="30" style="HEIGHT: 20px; WIDTH: 120px" onchange='doMajuscules(document.forms(0).prenom)' onblur="doVerificationNumeroFiche();" />
				 </logic:equal>
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_nt_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <html:select name='sujet' property="ethnie" size="1" tabindex="10" style="HEIGHT: 20px; WIDTH: 120px"  onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
                   <cardex:optionTag classe='<%= GlobalConstants.CleListe.ETHNIE %>'/>
                </html:select>
              </TD>
            <TD ALIGN="right"><b><bean:message key='i_cc_cle_t'/></b>&nbsp;</TD>
            <TD ALIGN="left" >
                <html:select name='sujet' property="confidentialite" tabindex="18" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange="doConfidentialite8();">
                  	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
					actionSecurite='<%=actionSecurite%>' />
                </html:select>
              </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='v_su_surnom_t'/></b></TD>
            <TD ALIGN="left"><myriap:text name='sujet' property="alias" tabindex="3" maxlength="50" style="HEIGHT: 20px; WIDTH: 120px" />
              </TD>
            <TD ALIGN="right"><b><bean:message key='i_ra_cle_t'/></b></TD>
            <TD ALIGN="left" >
              <html:select name='sujet' property="race" tabindex="11" size="1" style="HEIGHT: 20px; WIDTH: 120px">
                    <cardex:optionTag classe='<%= GlobalConstants.CleListe.RACE %>'/>
                </html:select>
              </TD>
			<TD ALIGN="right"><b><bean:message key='i_se_cle_t' /></b>&nbsp;
			</TD>
			<TD ALIGN="left">
					<myriap:select size='1'
					name='sujet' property="severiteAutres"
					style="HEIGHT: 20px; WIDTH: 120px" tabindex="19">
						<cardex:severiteOptions />
					</myriap:select>
			</TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right" nowrap><b><bean:message key='date_naissance_t'/></b></TD><!-- ici -->
            <TD ALIGN="left">
            	<bean:define id="isAgeActif" name='sujet' property="ageActif" type="Boolean"/>
                <cardex:Date name='sujet' property="dateNaissance" nomProchainChamp="typeAge" calendrier='true' actif='<%=String.valueOf(isAgeActif)%>' tabindex="4" onChange="doVerificationNumeroFiche();" onBlur="doVerificationNumeroFiche();document.forms(0).age.value = computeAge(document.forms(0).dateNaissance.value);"/>
                <myriap:text name='sujet' property="age" size="3" maxlength="3" style="WIDTH: 29px" onfocus="document.forms(0).age.value = computeAge(document.forms(0).dateNaissance.value);" onkeyup="subDateNaissance(this.value);" disabled='<%=isAgeActif.booleanValue()==false%>'/>
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
            <TD ALIGN="left" >
                   <myriap:select name='sujet' property="statut" tabindex="12" size="1" style="HEIGHT: 20px; WIDTH: 120px">
                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SUJET %>'/>
                   </myriap:select>
            </TD>
            <TD align="right">
            	<cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="severite" key='i_se_cle_inv'/>
            </TD>
			<TD ALIGN="left" >
					<myriap:select size='1'
						name='sujet' property="severite"
						style="HEIGHT: 20px; WIDTH: 120px" tabindex="20">
							<cardex:severiteOptions />
						</myriap:select>
		    </TD>					
			<TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='type.age'/><bean:message key="2.points" /></b></TD>
            <TD ALIGN="left">
                <myriap:select name='sujet' property="typeAge" size="1" tabindex="5" style="HEIGHT: 20px; WIDTH: 120px" onchange="doSoumettreRafraichir();" > 
                   <cardex:optionTag classe='<%= GlobalConstants.CleListe.TYPE_AGE %>'/>
                </myriap:select>
            </TD>        
            <TD ALIGN="right">
            	<cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="numeroAssuranceSociale" key='c_su_assurance_sociale'/>
            </TD>
            <TD ALIGN="left" ><myriap:text name='sujet' property="numeroAssuranceSociale" tabindex="13" maxlength="11" style="HEIGHT: 20px; WIDTH: 120px" />
            </TD>
            <TD align="right">
            	<cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="severiteCasino" key='i_se_cle_inv_casino'/>
            </TD>
			<TD ALIGN="left" >
					<myriap:select size='1'
						name='sujet' property="severiteCasino"
						style="HEIGHT: 20px; WIDTH: 120px" tabindex="21">
							<cardex:severiteOptions />
					</myriap:select>
		    </TD>					
			<TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='v_su_reference_1_t'/></b></TD>
            <TD ALIGN="left"><myriap:text name='sujet' property="reference1" tabindex="6" maxlength="50" style="HEIGHT: 20px; WIDTH: 120px" /></TD>
            
            <TD ALIGN="right" colspan="2">
            	<cardex:SecuriteLibelle nomFormulaire="sujet" propertyFormulaire="NASCanadien" key='nas.canadien'/>
            	<myriap:checkbox name='sujet' property="NASCanadien" tabindex="14" />&nbsp;&nbsp;&nbsp;&nbsp;
            </TD>
			<TD ALIGN="right" nowrap><b><bean:message key='v_su_assurance_maladie_t' /></b>&nbsp;</TD>
			<TD ALIGN="left"><myriap:text name='sujet' property="numeroAssuranceMaladie" tabindex="20" 
				maxlength="25" style="HEIGHT: 20px; WIDTH: 120px"/>
			</TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='v_su_reference_2_t'/></b></TD>
            <TD ALIGN="left"><myriap:text name='sujet' property="numeroClientEmploye" tabindex="7" maxlength="40" style="HEIGHT: 20px; WIDTH: 120px" /></TD>
            <TD ALIGN="right" nowrap><b><bean:message key='v_su_permis_conduire_t'/></b></TD>
            <TD ALIGN="left" ><myriap:text name='sujet' property="numeroPermisConduire" tabindex="15" maxlength="20" style="HEIGHT: 20px; WIDTH: 120px" />
            </TD>
            <TD ALIGN="right"><b><bean:message key='passeport_t'/></b>&nbsp;</TD>
            <TD ALIGN="left" ><myriap:text name='sujet' property="passeport" tabindex="22" maxlength="20" style="HEIGHT: 20px; WIDTH: 120px" onchange='this.value = this.value.toUpperCase();'/></TD>
            <TD>&nbsp;</TD>
        </TR>

		<TR>
			<TD colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" />
			</TD>
		</TR>

    <logic:equal name='sujet' property='new' value='false' >
      <TR>
      	<TD colspan="7"  ALIGN="center">
	      <TABLE cellspacing="3" >
	        <TR>
			    <TD nowrap>
				    	<b><bean:message key='i_si_cle_t2'/></b>
						<cardex:afficherValeurListeTag name="sujet" property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=actionSecurite%>' />
						&nbsp;
						<b><bean:message key='d_date_creation_t'/></b>
						<bean:write name="sujet" property="dateCreation" />
			    <TD align="right" nowrap>
						<b><bean:message key='v_cree_par_t'/></b>
				</TD>
				<TD>
						<cardex:afficherValeurListeTag name="sujet" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' actionSecurite='<%=actionSecurite%>' />
				</TD>
	        </TR>
	      </TABLE>
	     </TD>
	  </TR>
	  <TR>
	  	<TD colspan="7" align="center">
    			<cardex:securityDefineTag nameDefine="copieSujet" urlSecurite="/sujet/copier.do"><b>
                  Numéro de sujet destinataire :</b>&nbsp; <input type="text" name="sujetDestinataire" width="20" onblur="this.value = this.value.toUpperCase();" >&nbsp;
                  <input type="button" value="Copier" style="width: 60px; text-align: center;" onclick="doCopier();">
                </cardex:securityDefineTag>
        </TD>
      </TR>
	  	
	</logic:equal>

		<tiles:insert page="tiles/tpl_control_sujet_formulaire.jsp" flush="true">
			<tiles:put name="sujetEntite" value='<%=sujetEntite%>' />
			<tiles:put name="urlSecuriteSauvegarde" value='<%=urlSecuriteSauvegarde %>' />
		</tiles:insert>
        
        </TABLE>
        <!-- End entry fields -->
    </TD>
</TR>
</TABLE>
<!-- End Outline table -->
<html:hidden name='sujet' property='cle' />
<html:hidden name='sujet' property='site' />
<html:hidden name='sujet' property="createur" />
<html:hidden name='sujet' property="dateCreation" />
<html:hidden name='sujet' property="indicateurRdd" />

<logic:present name='sujet' property="entiteCardexLiaison">
	<html:hidden name='sujet' property="entiteCardexLiaison.cle" />
	<html:hidden name='sujet' property="entiteCardexLiaison.site" />
	<html:hidden name='sujet' property="role" />
</logic:present>

<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

