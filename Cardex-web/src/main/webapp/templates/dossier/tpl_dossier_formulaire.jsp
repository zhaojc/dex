<%-- --------------------------------------------------------------------------
Use case    : Consultation d'un dossier.
Description : Module d'affichage représentant le formulaire de consultation d'un
              dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.53 $, $Date: 2002/05/01 19:34:03 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.28 , Date: 2002/03/04 21:57:13 , Author: abruno-boucher
Ajustement du format date-heure.

$Revision: 1.53 $, $Date: 2002/05/01 19:34:03 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.securite.UIComponentState" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<!-- Following table is used to produce an outline -->
<TABLE cellpadding="1" cellspacing="0" border="0" >
<TR>
    <TD CLASS="tabTitle">
    <bean:define id="entite" name='dossier' property="entite" type="String"/>
        <TABLE  width="950" cellpadding="2" cellspacing="0" border="0" 
              style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');">

        <!-- First row uses transparent pixel to force good alignment -->
        <TR>
            <TD ALIGN="center" COLSPAN="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='no_dossier_t'/></b></TD>
            <TD ALIGN="left" ><b>
            	<bean:write name='dossier' property="numeroCardexTexte"/></b>
            </TD>

                <!-- Le titre DOIT ÊTRE peuplé dynamiquement pour l'enveloppe Vb -->
                <SCRIPT language="JavaScript">
                  document.title = "<bean:write name='dossier' property='numeroCardex.site'/><bean:write name='dossier' property='numeroCardex.date'/><bean:write name='dossier' property='numeroCardex.sequence'/>";
                </SCRIPT>
            <TD ALIGN="right"><b><bean:message key='d_do_date_debut_t'/></b></TD>
            <TD ALIGN="left" ><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
                <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                    <TD nowrap>
                        <logic:equal name='dossier' property='inscription' value='false' >
                        	<cardex:DateHeure name='dossier' property="dateDebut" calendrier="true" nomProchainChamp="dateFin"/>
                        </logic:equal>
                        <logic:equal name='dossier' property='inscription' value='true' >
                          <bean:write name='dossier' property="dateDebut"/>&nbsp;
                          <html:hidden name='dossier' property="dateDebut" />
                        </logic:equal>
                   </TD>
                </TR>
                <TR>
                    <TD ALIGN="center" CLASS="timeFormat"><bean:message key="date.heure.format"/></TD>
                </TR>
                </TABLE>

            </TD>
            <TD ALIGN="center" VALIGN="top" colspan="2">
                <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                    <TD ALIGN="right" VALIGN="top" colspan="2"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><cardex:SecuriteLibelle nomFormulaire="dossier" propertyFormulaire="motPasse" key='password'/>
                        <myriap:password name='dossier' property="motPasse" maxlength="20" style="HEIGHT: 22px; WIDTH: 80px" />&nbsp;
                    </TD>
                </TR>
                <TR>
                    <TD ALIGN="right" VALIGN="top" colspan="2"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><cardex:SecuriteLibelle nomFormulaire="dossier" propertyFormulaire="confirmationMotPasse" key='password_confirm'/>
                        <myriap:password name='dossier' property="confirmationMotPasse" maxlength="20" style="HEIGHT: 22px; WIDTH: 80px" />&nbsp;
                    </TD>
                </TR>
                </TABLE>
            </TD>
            <TD>&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
            <TD ALIGN="left">
              	   <bean:define id="genre" name='dossier' property="genre" type="String"/>
                  <myriap:select defaultState='<%=UIComponentState.DISABLED.toString()%>' name='dossier' property="genre" size="1" style="HEIGHT: 20px; WIDTH: 170px" onchange='doSoumettreRafraichir();' >
                      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     	valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>' 
						valeurDiscriminant='<%=entite%>'
						actionSecurite='<%=actionSecurite%>' />
                  </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><b><bean:message key='d_do_date_fin_t'/></b></TD>
            <TD ALIGN="left" VALIGN="top">
              <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                   <TD nowrap>
                        <logic:equal name='dossier' property='inscription' value='false' >
                        	<cardex:DateHeure name='dossier' property="dateFin" calendrier="true" onClick="doClickDateFin();"/>
                        </logic:equal>
                        <logic:equal name='dossier' property='inscription' value='true' >
                          <bean:write name='dossier' property="dateFin"/>&nbsp;
                          <html:hidden name='dossier' property="dateFin" />
                        </logic:equal>
                   </TD>
                </TR>
                <TR>
                    <TD ALIGN="center" CLASS="timeFormat"><bean:message key="date.heure.format"/></TD>
                </TR>
                </TABLE>
            </TD>
            <TD ALIGN="left" VALIGN="top">
            
            	<logic:equal name='dossier' property='inscription' value='false' >
            		<myriap:text name='dossier' property="mois" maxlength="4" size="4" onfocus="document.forms(0).mois.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);blur();" />
            	</logic:equal>
            	<logic:equal name='dossier' property='inscription' value='true' >
                	<bean:write name='dossier' property="mois"/>
                </logic:equal>
              <b><bean:message key='v_do_duree_t'/></b>

            </TD>
            <TD align="right" colspan="2" nowrap>&nbsp;
            </TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
            <TD ALIGN="left">
              	   <bean:define id="nature" name='dossier' property="nature" type="String"/>
                  <myriap:select defaultState='<%=UIComponentState.DISABLED.toString()%>' name='dossier' property="nature" size="1" style="HEIGHT: 20px; WIDTH: 170px" onchange="doSoumettreRafraichir();" >
                     <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     	valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
						valeurDiscriminant='<%=genre%>'
						actionSecurite='<%=actionSecurite%>' />
                  </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
            <TD ALIGN="left" >
	            <cardex:AutoCompleter name='dossier' property="reference1" tabindex="8" maxlength="20" 
	            style="HEIGHT: 20px; WIDTH: 120px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE1_DOSSIER_AUTO_COMPLETER %>'
	            height="150" width="150" nbrAmorce="2"/>                
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_cc_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <myriap:select name='dossier' property="confidentialite" tabindex="16" size="1" style="HEIGHT: 20px; WIDTH: 140px" onchange="doConfidentialite8();">
                   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
					actionSecurite='<%=actionSecurite%>' />
                </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
            <TD ALIGN="left">
	            <bean:define id="type" name='dossier' property="type" type="String"/>
		        <myriap:select name='dossier' property="type" tabindex="1" size="1" style="HEIGHT: 20px; WIDTH: 170px" onchange="doSoumettreRafraichir();"  >
					<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
					valeurDiscriminant='<%=nature%>'
					actionSecurite='<%=actionSecurite%>' />
		        </myriap:select>
            </TD>
            <TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
            <TD ALIGN="left" >
	            <cardex:AutoCompleter name='dossier' property="reference2" tabindex="9" maxlength="30" 
	            style="HEIGHT: 20px; WIDTH: 120px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE2_DOSSIER_AUTO_COMPLETER %>'
	            height="150" width="150" nbrAmorce="2"/>                   
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_se_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <myriap:select name='dossier' property="severite" tabindex="15" size="1" style="HEIGHT: 20px; WIDTH: 140px">
                   <cardex:severiteOptions/>
                </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
            <TD ALIGN="left" nowrap>
		         <myriap:select name='dossier' property="categorie" tabindex="2" size="1" style="HEIGHT: 20px; WIDTH: 170px">
					<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
						valeurDiscriminant='<%=type%>'
						actionSecurite='<%=actionSecurite%>'/>
				 </myriap:select>
                
                <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Consultation" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
                
            </TD>
            <TD ALIGN="right"><b><bean:message key='v_do_reference3_t'/></b></TD>
            <TD ALIGN="left" >
                <cardex:AutoCompleter name='dossier' property="reference3" tabindex="10" maxlength="30" 
		            style="HEIGHT: 20px; WIDTH: 120px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE3_DOSSIER_AUTO_COMPLETER %>'
		            height="150" width="150" nbrAmorce="2"/>                   
            </TD>
            <TD ALIGN="right"><b><bean:message key='origine_t'/></b></TD>
            <TD ALIGN="left" >
                <!-- protection du champ origine -->
                <logic:equal name='dossier' property='new' value='false' >
                   <myriap:select name='dossier' property="origine" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=actionSecurite%>'/>
                   </myriap:select>&nbsp;
                </logic:equal>
                <!-- pas de règle de sécurité s'il s'agit d'un nouveau dossier -->
                <logic:equal name='dossier' property='new' value='true' >
                   <myriap:select name='dossier' property="origine" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=actionSecurite%>'/>
                   </myriap:select>&nbsp;
                </logic:equal>
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='v_do_ancienne_reference_t'/></b></TD>
            <TD ALIGN="left">
                <myriap:text name='dossier' property="numeroDossier" tabindex="3" maxlength="20" style="HEIGHT: 20px; WIDTH: 170px" onchange='this.value = this.value.toUpperCase();' />
                <logic:equal name='dossier' property='new' value='false' >
	                <logic:notEqual name='dossier' property="statut" value='<%= GlobalConstants.Statut.DOSSIER_INACTIF %>'>
	                	<div onclick="lierDossier();" style="position: absolute;"><html:img page="/images/link.gif" altKey="cb_lier" width="14" height="14" border="1" /></div>
	                </logic:notEqual>
                </logic:equal>
                <logic:equal name='dossier' property='new' value='true' >
	                <logic:notEqual name='dossier' property="categorie" value=''>
	                <logic:notEqual name='dossier' property="origine" value=''>
	                <logic:notEqual name='dossier' property="confidentialite" value=''>
	                	<div onclick="lierDossier();" style="position: absolute;"><html:img page="/images/link.gif" altKey="cb_lier" width="14" height="14" border="1" /></div>
	                </logic:notEqual>
	                </logic:notEqual>
	                </logic:notEqual>
                </logic:equal>
            </TD>
            <TD ALIGN="right"><b><bean:message key='enregistrement_numerique'/></b></TD>
            <TD ALIGN="left" >
				<html:radio name='dossier' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
				<html:radio name='dossier' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <bean:define id="site" name='dossier' property='siteOrigine' type="String"/>
			        <myriap:select size='1' name='dossier' property='endroit' style='HEIGHT: 20px; WIDTH: 140px' tabindex="17" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				 	</myriap:select>
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
            <TD ALIGN="left">
                <myriap:select name='dossier' property="statut" tabindex="4" size="1" style="HEIGHT: 20px; WIDTH: 170px">
                    <cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
                </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="right"><b><bean:message key='no_seq_t'/></b></TD>
            <TD ALIGN="left" >
                <myriap:text name='dossier' property="referenceVideo" tabindex="12" maxlength="40" size="1" style="HEIGHT: 20px; WIDTH: 120px" />
            </TD>
            <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
            <TD ALIGN="left" >
                <myriap:select name='dossier' property="localisation" tabindex="18" size="1" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
                </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='c_do_fonde_t'/></b></TD>
            <TD ALIGN="left">
                <myriap:select name='dossier' property="fonde" tabindex="5" size="1" style="HEIGHT: 20px; WIDTH: 170px">
                    <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
                </myriap:select>&nbsp;
            </TD>
            <TD ALIGN="right"><b><bean:message key='enregistrement_conserve'/></b></TD>
            <TD ALIGN="left" >
				<html:radio name='dossier' property="enregistrementConserve" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
				<html:radio name='dossier' property="enregistrementConserve" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
            </TD>
            <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
            <TD ALIGN="left" >
                <%-- <myriap:text name='dossier' property="descriptif" tabindex="19" maxlength="40" style="HEIGHT: 20px; WIDTH: 140px" />&nbsp; --%>
                <cardex:AutoCompleter name='dossier' property="descriptif" tabindex="19" maxlength="80" 
                style="HEIGHT: 20px; WIDTH: 140px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
                height="150" width="150" nbrAmorce="2"/>
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>
        <TR>
            <TD ALIGN="right" nowrap><b><bean:message key='v_intervenant_assigne_t'/></b></TD>
            <TD ALIGN="left" colspan="6" nowrap>
            	<!-- S'il s'agit d'un nouveau dossier, on affiche seulement les intervenants actifs -->
                <myriap:select name='dossier' property="intervenant" tabindex="13" size="1" style="HEIGHT: 20px; WIDTH: 400px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		              <logic:equal name='dossier' property='new' value='true' >
						   <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_ACTIF_PAR_SITE%>' valeurDiscriminant='<%=site%>'/>
					  </logic:equal>
		              <logic:equal name='dossier' property='new' value='false' >
			   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE%>' 
							valeurDiscriminant='<%=site%>'
							actionSecurite='<%=actionSecurite%>'/>
		              </logic:equal>
		              
                </myriap:select>
                  <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe2" TITLE="Liste des groupes et des intervenants actifs" border="1" height="14" width="14" onclick="doConsulterIntervenant();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
                
            </TD>
        </TR>

		<TR>
			<TD colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" />
			</TD>
		</TR>
			
    <logic:equal name='dossier' property='new' value='false' >
      <TR>
      	<TD colspan="7"  ALIGN="center">
	      <TABLE cellspacing="3" >
	        <TR>
			    <TD nowrap>
				    	<b><bean:message key='i_si_cle_t2'/></b>
						<cardex:afficherValeurListeTag name="dossier" property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=actionSecurite%>' />
						&nbsp;
						<b><bean:message key='d_date_creation_t'/></b>
						<bean:write name="dossier" property="dateCreation" />
			    <TD align="right" nowrap>
						<b><bean:message key='v_cree_par_t'/></b>
				</TD>
				<TD>
						<cardex:afficherValeurListeTag name="dossier" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' actionSecurite='<%=actionSecurite%>' />
				</TD>
	        </TR>
	      </TABLE>
	     </TD>
	    </TR>
	</logic:equal>

		<TR>
            <TD COLSPAN="7">
              <TABLE>
                <TR><TD align="center" WIDTH="950" HEIGHT="20">
	                <logic:equal name='dossier' property='modifiable' value='true' >
	                  <cardex:button urlSecurite='<%=urlSecuriteSauvegarde%>' labelKey='cb_ok_dossier' style="width: 120px; text-align: center;" onclick='doOk();' />
	                </logic:equal>
	               </TD>
	            </TR>
	          </TABLE>
            </TD>
        </TR>
     </TABLE>
    </TD>
</TR>
</TABLE>
     <TABLE width="950" cellpadding="5" cellspacing="0" border="0" style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');" CLASS="tableOutline">
		 <TR>
		   <td align="left">&nbsp;
		<logic:equal name='dossier' property='new' value='false' >
	    <!--  La section suivante est invisible si l'utilisateur n'a pas le droit d'imprimer les rapports -->
	      <cardex:securityDefineTag nameDefine="sectionRapport" classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.RAPPORT_DOSSIER%>'>
		<!-- Impression des résultats de recherche et navigation -->
			  <b><bean:message key='choix_rapport_t'/></b>
		           <html:select name='dossier' property="choixRapport" size="1" style="HEIGHT: 20px; WIDTH: 255px"  >
		           	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
		           		valeurTableValeur='<%=GlobalConstants.TableValeur.RAPPORT_DOSSIER %>' />
		           </html:select>&nbsp;
		           <cardex:button labelKey='Imprimer'  onclick='doPrint();' />
	      </cardex:securityDefineTag>
		</logic:equal>
			  </td>
	          <TD width="300" align="right">
	              <logic:equal name='dossier' property='new' value='false' >
				    <cardex:button securityConstraint="cardex.acces.selectAccesDossier" labelKey='cb_createur' style="width: 100px; text-align: center;" onclick='doAuditAcces();' />&nbsp;
				    <cardex:button securityConstraint="cardex.dossier.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
	              </logic:equal>
	              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
	          </TD>
		</TR>
	 </TABLE>

<!-- End Outline table -->
<html:hidden name='dossier' property="hierarchie" />
<html:hidden name='dossier' property="inscription" />
<html:hidden name='dossier' property="cle" />
<html:hidden name='dossier' property="site" />
<html:hidden name='dossier' property="periode" />
<html:hidden name='dossier' property="motPasseCourant" />
<html:hidden name='dossier' property="duree" />
<html:hidden name='dossier' property="dateRapportee" />
<html:hidden name='dossier' property="classe" />
<html:hidden name='dossier' property="race" />
<html:hidden name='dossier' property="dateAssignation" />
<html:hidden name='dossier' property="dateEvenement" />
<html:hidden name='dossier' property="reference4" />
<html:hidden name='dossier' property="reference5" />
<html:hidden name='dossier' property="reference6" />
<html:hidden name='dossier' property="reference7" />
<html:hidden name='dossier' property="fondeDescription" />
<html:hidden name='dossier' property="numeroCardex.site" />
<html:hidden name='dossier' property="numeroCardex.date" />
<html:hidden name='dossier' property="numeroCardex.sequence" />
<html:hidden name='dossier' property="siteOrigine" />
<html:hidden name='dossier' property="modifiable" />

<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

<DIV align="center" STYLE="overflow:none; border='0.05cm solid gray'; background-color: #ffffff; width:285; height:20; z-index: 1; position: relative; top: -10px; visibility= 'hidden'" id="historique">
	<b><bean:message key='historiqueAudit'/></b>
</DIV>
<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:220; height:20;" style="z-index: 1; position: absolute; left: 340px; top: 150px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='dossier' property="typeCategorie" onchange="choisirCategorie(this.value);" style="HEIGHT: 110px; WIDTH: 220px; background-color:beige" tabindex="4" >
	   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE_CATEGORIE %>' 
		valeurDiscriminant='<%=nature%>'
		actionSecurite='<%=actionSecurite%>'/>
	</html:select>          
</DIV>
<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:400; height:20;" style="z-index: 1; position: absolute; left: 470px; top: 150px; visibility= 'hidden'" id="consulterIntervenant">
	<html:select size='8' name='dossier' property="groupesIntervenants" onchange="choisirIntervenant(this.value);"
	  style="HEIGHT: 110px; WIDTH: 430; background-color:beige" tabindex="4" >
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.SECTEUR_INTERVENANT_PAR_SITE%>'
		valeurDiscriminant='<%=site%>'/>	  
	</html:select>          
</DIV>
