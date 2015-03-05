<%-- --------------------------------------------------------------------------
Use case    : Ajout/Modification d'une narration.
Description : Module d'affichage du formulaire de'une narration.
Author(s)   : $Author: mdemers $, abruno-boucher, mdemers
Revision    : $Revision: 1.27 $, $Date: 2002/05/01 17:42:34 $

History     : Voir ci-dessous.

Revision: 1.7 , Date: 2002/02/25 15:36:13 , Author: abruno-boucher
Ajustement mise-en-page.

$Revision: 1.27 $, $Date: 2002/05/01 17:42:34 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.presentation.taglib.cardex.SecurityDefineTag" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

<jsp:include page='/JSpell/jspell.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
var language;
var guiLanguage;
var guiCountry;

function getSpellCheckArray() {
	guiLanguage="en";
	guiCountry="US";
	language = document.forms(0).language.options[document.forms(0).language.selectedIndex].value;
	if(language=="English (US)"){
		guiLanguage="en";
		guiCountry="US";
	}
	if(language=="French (FR)"){
		guiLanguage="fr";
		guiCountry="FR";
	}
    var fieldsToCheck=new Array();
   // make sure to enclose form/field object reference in quotes!
   document.forms(0).texteSaisi.value=fenetreNarration.innerHTML;
   fieldsToCheck[fieldsToCheck.length]='document.forms(0).texteSaisi';
   return fieldsToCheck;
}

/*function verification(){
//Appel au dictionnaire Word pour la correction orthographique.
//Word doit être installé.  La mise en page est perdue après la correction.
  //var texte = document.forms(0).HTMLEditor.getText();
  var texte = fenetreNarration.innerHTML;
  var Word, Doc, Corrected;
  var wdDialogToolsSpellingAndGrammar = 828;
  var wdDoNotSaveChanges = 0;
  Word = new ActiveXObject("Word.Application");
  if (Word != null) {
     Word.Visible=false;
     Doc = Word.Documents.Add();
     Word.Selection.Text = texte;
     Word.Dialogs(wdDialogToolsSpellingAndGrammar).Show();
     if (Word.Selection.Text.length != 1)
        Corrected = Word.Selection.Text;
     else
        Corrected = texte;
     Doc.Close(wdDoNotSaveChanges);
     Word.Quit();
     //Si des corrections ont été apportées, on les applique.
     if(Corrected != texte){
        //document.forms(0).HTMLEditor.setText(Corrected);
        fenetreNarration.innerHTML = Corrected;
     }
  }
}
*/

function doSequence(gabarit){
  //Assignation du numéro de séquence selon le gabarit choisi
    if(gabarit == "pagetitre.htm"){
       document.forms(0).reference.value = "Page titre 1";
    }
    if(gabarit == "identification_MTB.htm" || gabarit == "identification_CHA.htm" || gabarit == "identification_HUL.htm" || gabarit == "identification_LQC.htm" || gabarit == "identification_MTL.htm" || gabarit == "identification_SEJ.htm" || gabarit == "identification_SSJQ.htm"){
       document.forms(0).reference.value = "Identification 1";
    }
    if(gabarit == "tablematieres.htm"){
       document.forms(0).reference.value = "Table des matières 1";
    }
    if(gabarit == "introduction.htm"){
       document.forms(0).reference.value = "Introduction 1";
    }
    if(gabarit == "enquete.htm"){
       document.forms(0).reference.value = "Enquête 1";
    }
    if(gabarit == "constat.htm"){
       document.forms(0).reference.value = "Constat 1";
    }
    if(gabarit == "conclusion.htm"){
       document.forms(0).reference.value = "Conclusion 1";
    }
    if(gabarit == "recommandation.htm"){
       document.forms(0).reference.value = "Recommandations 1";
    }
}    

function affichageExclamation(){
   if(document.forms(0).tempsConsacre.value != ""){
   		document.forms(0).exclamation.style.visibility = "hidden";
   }else{
    	document.forms(0).exclamation.style.visibility = "visible";
   }
}

function doAuditChangement() {
	   var rapport = "<%=GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_NARRATIONS%>";
	   var userCardex = '<bean:write name="<%=AuthenticationSubject.class.getName()%>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&UTILISATEUR=" + userCardex; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function chargerFichierNarrationTemporaire(){
	var narrationTemporaire = document.getElementsByName("narrationTemporaire")[0].value;
	fenetreNarration.innerHTML = narrationTemporaire;
	
	fenetreNarration.focus();

	// Pour une raison que je ne peux expliquer, si je ne fais pas le ENTER
	// la narration récupérée ne s'affiche pas...	
	var oShell= new ActiveXObject("WScript.Shell");
	oShell.SendKeys("{ENTER}");
	oShell.Quit;	
}

</SCRIPT>

   <html:hidden  name="narration" property="narrationAvecFormat" />
   <html:hidden  name="narration" property="narrationSansFormat" />
   <html:hidden  name="narration" property="confidentialiteApprobateur" />
   <html:hidden  name="narration" property="confidentialiteCreateur" />
   <html:hidden  name="narration" property="autoriteApprobateur" />
   <html:hidden  name="narration" property="autoriteCreateur" />
   <html:hidden  name="narration" property="autoriteNarration" />
   <html:hidden  name="narration" property="modifiable" />
   <html:hidden  name="narration" property="approuvable" />
   <html:hidden  name="narration" property="permettreModification" />
   <html:hidden  name="narration" property="lien" />
   <html:hidden  name="narration" property="lienSite" />
   <html:hidden  name="narration" property="cle" />
   <html:hidden  name="narration" property="site" />
   <html:hidden  name="narration" property="dateCreation" />
   <html:hidden  name="narration" property="approbateur" />
   <html:hidden  name="narration" property="dateApprobation" />
   <html:textarea property="narrationTemporaire" style="position:absolute;visibility:hidden;"/>

<%-- Le clipboard doit être actif dans les narrations. --%>
<input type="hidden" name="clipboard" value="required">
<input type="hidden" name="texteSaisi">

<jsp:include page="/templates/tpl_erreur.jsp" flush="true" />


<TABLE width="1100" cellpadding="0" cellspacing="0" border="0" bgcolor="#eeeeee" class="tableOutline">
  <TR>
    <TD class="pictureBackground" width="1100">
    <table width="1100" cellpadding="2" cellspacing="0" border="0"
    style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='0');" bgcolor="#acc8c8">
        <tr>
            <td align="right"><b><bean:message key='r_co_montant_t'/></b></td>
            <td>
              <logic:equal name='narration' property='modifiable' value='true' >
                 <cardex:argent name='narration'
					property='montant' maxlength='11' style="HEIGHT: 20px; WIDTH: 65px" />$
              </logic:equal>
              <logic:equal name='narration' property='modifiable' value='false' >
              	<bean:write name='narration' property="montant" />
              </logic:equal>
            </td>
            <td colspan="2">&nbsp;</td>
            <td align="right"><b><bean:message key='i_cc_cle_t'/></b></td>
            <td>
              <logic:equal name='narration' property='modifiable' value='true' >
                <myriap:select name='narration' property="confidentialiteNarration" size="1" style="HEIGHT: 20px; WIDTH: 60px">
                 	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
                </myriap:select>&nbsp;
              </logic:equal>
              <logic:equal name='narration' property='modifiable' value='false' >
              	<cardex:afficherValeurListeTag name='narration' property="confidentialiteNarration" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.MODIFICATION%>'/>
              </logic:equal>
            </td> 
         <tr>
            <td align="right"><b><bean:message key='v_co_rapporte_par_t'/></b></td>
            <td colspan="5">
              <logic:equal name='narration' property='modifiable' value='true' >
                <myriap:select name='narration' property="rapporteur" size="1" style="HEIGHT: 20px; WIDTH: 530px">
                	<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </myriap:select>&nbsp;
              </logic:equal>
              <logic:equal name='narration' property='modifiable' value='false' >
                <cardex:afficherValeurListeTag name="narration" property="rapporteur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
              </logic:equal>
            </td>
        </tr>

        <tr>
            <td align="right"><b><bean:message key='v_co_temps_t'/></b></td>
            <td nowrap="nowrap" colspan="3">

              <SCRIPT language="JavaScript" type="text/javascript">
                function computeHoursMinutes() {
                    document.forms(0).tempsConsacreHeure.value=Math.floor((document.forms(0).tempsConsacre.value/60));
                    document.forms(0).tempsConsacreMinute.value=document.forms(0).tempsConsacre.value-(document.forms(0).tempsConsacreHeure.value*60);
                }
              </SCRIPT>

              <logic:equal name='narration' property='modifiable' value='true' >
                  <myriap:text name='narration' property="tempsConsacre" maxlength="4"  size="4" onchange='computeHoursMinutes();affichageExclamation();' />
                  <IMG SRC="<%=request.getContextPath()%>/images/exclamation.gif" NAME="exclamation" ALIGN=BOTTOM BORDER="0" ALT="N'oubliez pas d'inscrire un temps consacré." onload='affichageExclamation();'>
              </logic:equal>
              <logic:equal name='narration' property='modifiable' value='false' >
                	<bean:write name='narration' property="tempsConsacre" />
              </logic:equal>
            <bean:message key='total_minutes_t'/>
                &nbsp;
           		<myriap:text name='narration' property="tempsConsacreHeure" maxlength="2"  size="2" disabled='true' />
            	<bean:message key='heures_t'/>
            	&nbsp;
            	<myriap:text name='narration' property="tempsConsacreMinute" maxlength="2"  size="2" disabled='true' />
            	<bean:message key='minutes_t'/>
            </td>
            <td align='right' nowrap><b><bean:message key='no_seq_t'/></b></td>
            <td> <myriap:select name='narration' property="reference" size="1" style="HEIGHT: 20px; WIDTH: 115px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                	<cardex:optionTag classe='<%= GlobalConstants.CleListe.RAPPORT_SECTION %>'/>
                </myriap:select>&nbsp;

            </td>
        </tr>

        <tr>
            <td align="center" colspan="6" width="1100">
            <table width="1100" cellpadding="5" cellspacing="0" border="0" class="tableCarved">
                <tr>
                  <td align="center" width="1100">
		  <jsp:include page="/templates/narrations/tpl_fenetre_narration.jsp" flush="true" />
		  
                </td>
            </tr>
        </table>
        </td>
        </tr>
            <TR>
                <TD WIDTH="1100" ALIGN="center" HEIGHT="15" colspan="6"><html:img page="/images/0061CFpixel.gif" width="1100" height="1" border="0" /></TD>
            </TR>
            <TR>
            	<td colspan="5">
	            	<bean:define id="dossier" name='dossier'/>
	            	<b><bean:message key='no_dossier_t'/></b>
	            	   <bean:write name='dossier' property="numeroCardexTexte"/>
            	</td>
            	<td align="rignt">
			     		<cardex:button securityConstraint="cardex.narration.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
            	</td>
            </TR>
            <TR>
                <TD WIDTH="1100" ALIGN="center" HEIGHT="15" colspan="6"><html:img page="/images/0061CFpixel.gif" width="1100" height="1" border="0" /></TD>
            </TR>
        
            <tr>
                <td align="right"><b><bean:message key='v_cree_par_t'/></b></td>
                <td colspan="5">
				    <cardex:afficherValeurListeTag name="narration" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </td>
            </tr>
            <tr>
                <td align="right"><b><bean:message key='v_tr_modifie_par_t2'/></b></td>
                <td colspan="5">
		    		<cardex:afficherValeurListeTag name="narration" property="modificateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </td>
            </tr>
            <tr>
                <td align="right"><b><bean:message key='v_modifie_par_t'/></b></td>
                <td colspan="5">
		    		<cardex:afficherValeurListeTag name="narration" property="approbateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </td>
            </tr>
            <tr>
                <td align="right" nowrap><b><bean:message key='date_creation_t'/></b></td>
                <td>
                    <bean:write name="narration" property="dateCreation"/>
                </td>
                <td align="right" nowrap><b><bean:message key='date_modification_t'/></b></td>
                <td>
                    <bean:write name="narration" property="dateModification"/>
                </td>
                <td align="right" nowrap><b><bean:message key='d_date_modification_t'/></b></td>
                <td>
                    <bean:write name="narration" property="dateApprobation"/>
                </td>
            </tr>

            <TR>
                <TD WIDTH="1100" ALIGN="center" HEIGHT="15" colspan="6"><html:img page="/images/0061CFpixel.gif" width="1100" height="1" border="0" /></TD>
            </TR>

        <tr>
            <td align="center" colspan="6" width="1100">
              <!-- BOTTOM BUTTONS -->
              <TABLE width="700" cellpadding="0" cellspacing="0" border="0">
                <TR>
                  <TD>
                    <logic:equal name='narration' property='modifiable' value='true' >
                      <cardex:button urlSecurite="/dossier/narration/update.do" labelKey='cb_ok' style="width: 70px; text-align: center;"
                        onclick='doOk();' />
                    </logic:equal>
                    <logic:equal name='narration' property='modifiable' value='false' >
                    	<INPUT type='button' value="<bean:message key='cb_ok' />" style="width: 70px; text-align: center;" disabled />
                      <SCRIPT> fenetreNarration.contentEditable = false;
	                      fenetreNarrationSauvegardable	= false;
                      </SCRIPT>
                    </logic:equal>

                    <cardex:securityDefineTag nameDefine="droitSujetAjout" securityConstraint="cardex.dossier.narrations.sujets.ajouter" />
                    
                    <logic:equal name='<%="droitSujetAjout"+SecurityDefineTag.ENABLED%>' value='true' >
 	                    <logic:equal name='narration' property='approuve' value='false' >
		                    <logic:equal name='narration' property='droitModifierNarrationSansSauvegarder' value='true' >
		                      <SCRIPT> fenetreNarration.contentEditable = true;
		                      </SCRIPT>
		                    </logic:equal>
	                    </logic:equal>		                    
                    </logic:equal>
                </TD>

                <TD>&nbsp;
					<select name=language>
					 <option value="English (US)">English</option>
					 <option value="French (FR)" selected>Français</option>
					</select>
		    		<cardex:button labelKey='cb_spellcheck' style="width: 80px; text-align: center;" onclick="jspellcheck()" />
				</TD>
                <TD>
                    <logic:equal name='narration' property='approuvable' value='true' >
                        <cardex:button urlSecurite="/dossier/narration/approve.do" labelKey='cb_approbation' style="width: 70px" onclick='doApprobation();' />
                    </logic:equal>
                    <logic:equal name='narration' property='approuvable' value='false' >
                      <INPUT type='button' value="<bean:message key='cb_approbation' />" style="width: 70px" disabled >
                    </logic:equal>
                </TD>

                <TD>
                    <logic:equal name='narration' property='permettreModification' value='true' >
                      <cardex:button urlSecurite="/dossier/narration/modified.do" labelKey='cb_modification' style="width: 110px" onclick='doModification();' />
                    </logic:equal>
                    <logic:equal name='narration' property='permettreModification' value='false' >
                      <INPUT type='button' value="<bean:message key='cb_modification' />" style="width: 110px" disabled />
                    </logic:equal>
                </TD>
                <TD>
                	  <bean:define id="disableRechargerNarrationTemporaire" name='narration' property='disableRechargerNarrationTemporaire' type="Boolean"/>
	                  <cardex:button id="btnRechargerNarrationTemporaire" labelKey="recharger.narration.temporaire" style="width: 155px" onclick="chargerFichierNarrationTemporaire();" disabled="<%=disableRechargerNarrationTemporaire.booleanValue()%>"/>
                      <logic:equal name='narration' property='nouveau' value='false' >
                    	<cardex:button securityConstraint='cardex.fenetres.narrations.imprimer' labelKey='cb_imprimer' style="width: 50px" onclick='doPrint();' />
                      </logic:equal>
                      <cardex:button labelKey='cb_annuler' style="width: 60px; text-align: center;" onclick='doCancel();' />
                </TD>
                <TD>
	                <logic:notEqual name='dossier' property="statut" value='<%= GlobalConstants.Statut.DOSSIER_INACTIF %>'>                	
                      <logic:equal name='narration' property='droitBtnAjoutSujet' value='true' >
                      	&nbsp;&nbsp;12345
                    	<cardex:button urlSecurite="/dossier/narration/ajoutSujet.do" labelKey='cb_AjoutSujet'  onclick='doAjoutSujet();' />
                      </logic:equal>
                    </logic:notEqual>
                </TD>                
              </TR>
            </TABLE>
            <!-- END BOTTOM BUTTONS -->
            </td>
        </tr>
        <TR>
        <TD><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>

    </table>

    </TD>
  </TR>
</TABLE>


<SCRIPT language="JavaScript">
   //-- Copie de la narration dans la fenêtre d'édition
   fenetreNarration.innerHTML = document.forms(0).narrationAvecFormat.value;
   //Affichage du point d'exclamation
   //affichageExclamation();
   //fenetreNarration.insertAdjacentHTML("afterBegin","<SPAN STYLE='line-height:4mm'> " + document.forms(0).narrationAvecFormat.value + " </SPAN>");
   //alert(fenetreNarration.innerHTML);
   cacherAbsentBTNDictionnaire();
   
</SCRIPT>