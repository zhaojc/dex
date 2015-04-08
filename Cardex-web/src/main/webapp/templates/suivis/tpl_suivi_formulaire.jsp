<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Ajout d'un suivi.
Description : Écran de saisie d'un nouveau suivi.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.9 $, $Date: 2002/05/01 20:25:19 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.9 $, $Date: 2002/05/01 20:25:19 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="java.util.Locale" %>

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

<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<SCRIPT language="JavaScript" type="text/javascript" src='<%= request.getContextPath() %>/scripts/date_time_picker_<%= var_lang %>.js'></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/twin_date_time_picker_<%= var_lang %>.js"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">

function doValeursDefaut(valeur) {
//En fonction de certains types de suivi, on met des valeurs par défaut automatiquement.
//Seulement pour le site Loto-Québec.
	var calendrier = document.getElementById('calDatePrevue');
	if(((valeur == '<%= GlobalConstants.TypeSuivi.SUIVI_24_HEURES %>') || 
		(valeur == '<%= GlobalConstants.TypeSuivi.DELAI_21_JOURS %>') ||
		(valeur == '<%= GlobalConstants.TypeSuivi.DELAI_30_JOURS %>')) && 
	    (document.forms(0).lienSite.value == '<%= GlobalConstants.Sites.LOTO_QUEBEC %>')){
		document.forms(0).statut.value = '<%= GlobalConstants.StatutSuivi.EN_COURS %>';
		document.forms(0).intervenant.value = document.forms(0).demandeur.value;
		//Calcul de la date prévue.
		var currentTime = new Date();
		if(valeur == '<%= GlobalConstants.TypeSuivi.SUIVI_24_HEURES %>'){
		    currentTime.setDate(currentTime.getDate() + 1);
			document.forms(0).suivi.value = "24 heures " + document.forms(0).suivi.value;
			//doAfficherDatePrevue("<%= GlobalConstants.TypeSuivi.UN_JOUR %>");
		} 
		//R11-0713 : on retire le calcul des jours ouvrables pour les suivis 21 et 30 jours
		if(valeur == '<%= GlobalConstants.TypeSuivi.DELAI_21_JOURS %>'){
		    currentTime.setDate(currentTime.getDate() + 21);
			document.forms(0).suivi.value = "21 jours " + document.forms(0).suivi.value;
			//doAfficherDatePrevue("<%= GlobalConstants.TypeSuivi.VINGT_ET_UN_JOURS %>");
		} 
		if(valeur == '<%= GlobalConstants.TypeSuivi.DELAI_30_JOURS %>'){
		    currentTime.setDate(currentTime.getDate() + 30);
			document.forms(0).suivi.value = "30 jours " + document.forms(0).suivi.value;
			//doAfficherDatePrevue("<%= GlobalConstants.TypeSuivi.TRENTE_JOURS %>");
		} 
		
		var mois = currentTime.getMonth() + 1;
		if(mois<10){
			mois = "0" + mois;
		}
		var jour = currentTime.getDate();
		if(jour<10){
			jour = "0" + jour;
		}
		var annee = currentTime.getFullYear();
		var heures=currentTime.getHours();
		if(heures<10){
			heures = "0" + heures;
		}
		var minutes=currentTime.getMinutes();
		if(minutes<10){
			minutes = "0" + minutes;
		}
		var secondes=currentTime.getSeconds();
		if(secondes<10){
			secondes = "0" + secondes;
		}
		document.forms(0).datePrevue.value = annee + "-" + mois + "-" + jour + " " + heures + ":" + minutes + ":" + secondes;
		//On protège la date prévue selon le type d'activité
		document.forms(0).datePrevue.disabled = true;
		calendrier.style.visibility = 'hidden';
	}else{
		document.forms(0).datePrevue.disabled = false;
   		calendrier.style.visibility = 'visible';
	}
 }

function doAfficherDatePrevue(JoursAjoute) {
//Calcul des intervalles de dates en tenant compte des jours ouvrables.
	var url = "<%=request.getContextPath()%>/CalculerDateOuvrableServlet?JoursAjoute="+JoursAjoute;
    var req = initRequest(url);
    
    req.onreadystatechange = function() {
    	
    	if (req.readyState == 4) {
    		
    		if (req.status == 200) {
            	document.forms(0).datePrevue.value = req.responseText;
            }
        }
    };
	req.open("GET", url, true);
    req.send(null);
}

function doAuditChangement() {
	   var rapport = "<%= RapportsConfiguration.AUDIT_CHANGEMENTS_SUIVIS %>";
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&UTILISATEUR=" + userCardex; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

function doPrint() {
	   var fiche = "SV";
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var url = "<%=request.getContextPath()%>/AffichagePDFFiches?FICHE=" + fiche + "&SITE=" + site + "&CLE=" + cle;
	   var rapport = "<%= GlobalConstants.ChoixRapport.IMPRESSION_INSCRIPTION %>";
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

</SCRIPT>

   <html:hidden  name="suivi" property="cle" />
   <html:hidden  name="suivi" property="site" />
   <html:hidden  name="suivi" property="lien" />
   <html:hidden  name="suivi" property="lienSite" />
   <html:hidden  name="suivi" property="confidentialiteApprobateur" />
   <html:hidden  name="suivi" property="confidentialiteCreateur" />
   <html:hidden  name="suivi" property="niveauHierarchiqueApprobateur" />
   <html:hidden  name="suivi" property="niveauHierarchiqueCreateur" />
   <html:hidden  name="suivi" property="niveauHierarchiqueSuivi" />
   <html:hidden  name="suivi" property="reference1" />
   <html:hidden  name="suivi" property="reference2" />
   <html:hidden  name="suivi" property="createur" />
   <html:hidden  name="suivi" property="modificateur" />
   <html:hidden  name="suivi" property="dateModification" />
   <html:hidden  name="suivi" property="approbateur" />
   <html:hidden  name="suivi" property="dateApprobation" />

<bean:define id="entite" name='suivi' property="entite" type="String"/>

<!-- POSITIONING TABLE -->
<TABLE align="center" height="500" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">

		<tiles:insert template='/templates/tpl_onglet_entete.jsp'>
			<tiles:put name='keyTitre' content="Suivi" />
			<tiles:put name='tableWith' content="771" />
		</tiles:insert> 
  

      <!-- CONTENT -->
      <TABLE width="640" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    			<td align="center">

    		  <TABLE width="600" cellpadding="5" cellspacing="0" border="0" class="tableCarved"
    		      style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');">
        		<tr>
        			<td colspan="6"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
      		  </tr>

              <tr>
        			<td colspan="6"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
      		  </tr>

        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_tc_cle_t'/></b></td>
        			<td>
                         <logic:equal name='suivi' property='modifiable' value='true' >
                             <myriap:select name='suivi' property="activite" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 170px" onchange="doValeursDefaut(this.value);" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
             						<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_ACTIVITE %>' actionSecurite='<%=actionSecurite%>'/>
          					 </myriap:select>&nbsp;
                         </logic:equal>
                         <logic:equal name='suivi' property='modifiable' value='false' >
                         	<cardex:afficherValeurListeTag name='suivi' property="activite" classe='<%= GlobalConstants.CleListe.TYPE_ACTIVITE%>'/>
                         </logic:equal>
                    </td>
        			<td align="right" nowrap><b><bean:message key='i_cc_cle_t'/></b></td>
        			<td>
                         <logic:equal name='suivi' property='modifiable' value='true' >
                            <myriap:select name='suivi' property="confidentialiteSuivi" size="1" style="HEIGHT: 20px; WIDTH: 60px">
                                <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
								actionSecurite='<%=actionSecurite%>' />
                             </myriap:select>
                         </logic:equal>
                         <logic:equal name='suivi' property='modifiable' value='false' >
                         	<cardex:afficherValeurListeTag name='suivi' property="confidentialiteSuivi" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' actionSecurite='<%=actionSecurite%>' />
                         </logic:equal>
                    </td>
        			<td align="right" nowrap><b><bean:message key='i_st_cle_t'/></b></td>
        			<td>
                                  <logic:equal name='suivi' property='modifiable' value='true' >
                                      <myriap:select name='suivi' property="statut" size="1" style="HEIGHT: 20px; WIDTH: 100px">
                                        <cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SUIVI %>'/>
                                      </myriap:select>
                                  </logic:equal>
                                  <logic:equal name='suivi' property='modifiable' value='false' >
                                  	<cardex:afficherValeurListeTag name='suivi' property="statut" classe='<%=GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SUIVI%>'/>
                                  </logic:equal>

                              </td>
        		</TR>


            <!-- TEXT INPUT HERE -->
        		<TR>
        			<td align="center" colspan="6">

                        <logic:equal name='suivi' property='modifiable' value='true' >
                          <myriap:textarea name='suivi' property='suivi' rows="15" cols="120" style="font-family: Verdana, Arial; font-size: 9pt;" onkeypress='if (this.value.length > 2000) {this.value = this.value.substring(0,2000);}' ></myriap:textarea>
                        </logic:equal>
                        <logic:equal name='suivi' property='modifiable' value='false' >
                          <myriap:textarea name='suivi' property='suivi' rows="15" cols="120" style="font-family: Verdana, Arial; font-size: 9pt;" disabled='true' ></myriap:textarea>
                        </logic:equal>
              </td>
            </TR>
            <!-- END INPUT TEXT -->


        		<TR>
        			<td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
        			<td>
                      <logic:equal name='suivi' property='modifiable' value='true' >
                        <myriap:select name='suivi' property="secteurAssigne" size="1" style="HEIGHT: 20px; WIDTH: 160px" onchange='doRefresh();' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		   			         <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>'
		   			         	valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR %>' 
								actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />                            
                        </myriap:select>
                      </logic:equal>
                      <logic:equal name='suivi' property='modifiable' value='false' >
                      	<cardex:afficherValeurListeTag name='suivi' property="secteurAssigne" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>'
		   			         	valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                      </logic:equal>
                    </td>
        			<td align="right" nowrap><b><bean:message key='v_sv_intervenant_t'/></b></td>
        			<td colspan="3">
      				  <bean:define id="secteurAssigne" name='suivi' property="secteurAssigne" type="String"/>
                      <logic:equal name='suivi' property='modifiable' value='true' >
                         <myriap:select size='1' name='suivi' property='intervenant' style='HEIGHT: 20px; WIDTH: 330px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                             <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR %>' actionSecurite='<%=actionSecurite%>' valeurDiscriminant='<%=secteurAssigne%>'/>
                         </myriap:select>
                      </logic:equal>
                      <logic:equal name='suivi' property='modifiable' value='false' >
                      	<cardex:afficherValeurListeTag name='suivi' property='intervenant' classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR %>' actionSecurite='<%=actionSecurite%>' valeurDiscriminant='<%=secteurAssigne%>' />
                      </logic:equal>
                    </td>
        		</TR>

            <TR>
        			<td align="right" nowrap><b><bean:message key='d_sv_date_creation_t'/></b></td>
        			<td>
        				<bean:write name='suivi' property='dateCreation' />
                    </td>
        			<td align="right" nowrap><b><bean:message key='date_completee_t'/></b></td>
        			<td nowrap>
                     <logic:equal name='suivi' property='modifiable' value='true' >
                         <logic:equal name='suivi' property='permettreComplete' value='true' >
                         	<cardex:DateHeure name='suivi' property='dateCompletee' calendrier="true" nomProchainChamp="" />
                         </logic:equal>
                         <logic:equal name='suivi' property='permettreComplete' value='false' >
                         	<bean:write name='suivi' property='dateCompletee' />
                         	<html:hidden name="suivi" property="dateCompletee" />
                         </logic:equal>
                     </logic:equal>
                     <logic:equal name='suivi' property='modifiable' value='false' >
                     	<bean:write name='suivi' property='dateCompletee' />
                     	<html:hidden name="suivi" property="dateCompletee" />
                     </logic:equal>
                   </td>
        			<td align="right" nowrap><b><bean:message key='d_sv_date_prevue_t2'/></b></td>
        			<td nowrap>
                          <logic:equal name='suivi' property='modifiable' value='true' >
                             <cardex:DateHeure name='suivi' property='datePrevue' id="calDatePrevue" calendrier="true" nomProchainChamp="dateCompletee" />
                          </logic:equal>
                          <logic:equal name='suivi' property='modifiable' value='false' >
                          	<bean:write name='suivi' property='datePrevue'/>
                          	<html:hidden name="suivi" property="datePrevue" />
                          </logic:equal>
                    </td>
        		</TR>

        		<TR>
        			<td align="right">&nbsp;</td>
        			<td>
                        <b><cardex:afficherValeurListeTag name='suivi' property="secteurOrigine" classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=actionSecurite%>'/></b>
                      </td>
        			<td align="right" nowrap><b><bean:message key='v_sv_demandeur_t'/></b></td>
        			<td colspan="3">
        				<cardex:afficherValeurListeTag name='suivi' property='demandeur' classe='<%= GlobalConstants.CleListe.INTERVENANT%>'/>
                     </td>
        		</TR>
          </TABLE>

    		  </TD>
    		</TR>

    	<TR>
    	<TD align="center">
    			  <!-- BOTTOM BUTTONS -->
    			  <TABLE width="100%" cellpadding="3" cellspacing="0" border="0">
    			    <TR>
    			      <TD width="150">
                                <logic:equal name='suivi' property='modifiable' value='true' >
                                    <cardex:button labelKey='cb_ok' urlSecurite="/dossier/suivi/update"  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
                    		        &nbsp;
                    		        <cardex:button labelKey='cb_ajouter' urlSecurite="/dossier/suivi/ajouter.do" onclick='doAjouter();' style="color: #000000; width: 60px; text-align: center" />               
                                </logic:equal>
                                <logic:equal name='suivi' property='modifiable' value='false' >
                                    <cardex:button labelKey='cb_ok' urlSecurite="/dossier/suivi/update" onclick='doOk();' style="color: #000000; width: 70px; text-align: center" disabled='true' />
                    		    	&nbsp;
                    		    	<cardex:button labelKey='cb_ajouter' urlSecurite="/dossier/suivi/ajouter.do" onclick='doAjouter();' style="color: #000000; width: 60px; text-align: center" disabled='true' />               
                                </logic:equal>
                              </TD>

                <TD width="150" align="right">
                    <logic:equal name='suivi' property='modifiable' value='true' >
                        <logic:equal name='suivi' property='permettreComplete' value='true' >
                            <cardex:button labelKey='Complété' urlSecurite="/dossier/suivi/complete.do" onclick='doComplete();' style="color: #000000; width: 60px; text-align: center" />
                        </logic:equal>
                        <logic:equal name='suivi' property='permettreComplete' value='false' >
                            <cardex:button labelKey='Complété' urlSecurite="/dossier/suivi/complete.do" onclick='doComplete();' style="color: #000000; width: 60px; text-align: center" disabled='true' />
                        </logic:equal>

                    </logic:equal>
                    <logic:equal name='suivi' property='modifiable' value='false' >
                        <cardex:button labelKey='Complété' urlSecurite="/dossier/suivi/complete.do" onclick='doComplete();' style="color: #000000; width: 60px; text-align: center" disabled='true' />
                    </logic:equal>
                </TD>

                <TD width="150">
                    <logic:equal name='suivi' property='approuvable' value='true' >
                        <cardex:button labelKey='Approbation' soumettre="/dossier/suivi/approve.do" style="color: #000000; width: 70px; text-align: center" />
                    </logic:equal>
                    <logic:equal name='suivi' property='approuvable' value='false' >
                        <cardex:button labelKey='Approbation' soumettre="/dossier/suivi/approve.do" style="color: #000000; width: 70px; text-align: center" disabled='true'/>
                    </logic:equal>
                </TD>

                <TD width="300" align="right">
                    <cardex:button labelKey='cb_imprimer' securityConstraint='cardex.dossier.suivi.imprimer' style='width: 60px; text-align: center;' onclick='doPrint();' urlSecurite="/AffichagePDFFiches" />&nbsp;
                    <logic:equal name='suivi' property='modifiable' value='false' >
			     		<cardex:button securityConstraint="cardex.suivi.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
    				</logic:equal>
                    <cardex:button labelKey='cb_fermer'  onclick='doClose();' style="color: #000000; width: 60px; text-align: center" />
                </TD>

              </TR>
            </TABLE>
            <!-- END BOTTOM BUTTONS -->

  	</TD>
  </tr>
  	</TABLE>
    <!-- END CONTENT -->



    </td>
  </tr>
</TABLE>
<!-- END POSITIONING TABLE -->

<html:hidden name="suivi" property="secteurOrigine" />
<html:hidden name="suivi" property="demandeur" />
<html:hidden name="suivi" property="secteurAssigne" />
<html:hidden name="suivi" property="dateCreation" />
<html:hidden name="suivi" property="reference3" />
