<%-- --------------------------------------------------------------------------
Use case    : Recherche d'une société.
Description : Module d'affichage représentant le formulaire d'une recherche d'une
              société.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.10 $, $Date: 2002/02/25 19:12:36

History     : Voir ci-dessous.

Revision: 1.3 , Date: 2002/03/06 19:24:15 , Author: abruno-boucher
Création.

$Revision: 1.10 $, $Date: 2002/04/22 17:37:02 $, $Author: mlibersan $
Création.
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
 
<!-- KIND & NATURE SEARCH FIELDS -->
<!-- Following table is used to produce an outline -->
<TABLE width="850" cellpadding="0" cellspacing="0" border="0" class="tableOutline">
  <TR>
    <TD CLASS="tabBackground">

    <TABLE  width="850" cellpadding="2" cellspacing="0" border="0">
      <!-- First row uses transparent pixel to force good alignment -->
      <TR>
        <TD ALIGN="middle" COLSPAN="7"><html:img page="/images/blank.gif" width="766" height="1" border="0" /></TD>
      </TR>

      <TR>
        <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
          <b><bean:message key='i_en_cle_t'/></b></TD>
          <bean:define id="entite" name='rechercheSociete' property="entite" type="String"/>
        <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
           <myriap:select name='rechercheSociete' property='entite' onchange='doSoumettreRafraichir();' size='1' style='HEIGHT: 20px; WIDTH: 160px' tabindex="1" >
   	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
           </myriap:select>
        </TD>
        <TD ALIGN="right" nowrap><b><bean:message key='co_no_fiche_t'/></b></TD>
        <TD ALIGN="left"><myriap:text name='rechercheSociete' 
                                      property="numeroFiche" 
                                      style="HEIGHT: 20px; WIDTH: 160px" 
                                      onchange='this.value = this.value.toUpperCase();' 
                                      tabindex="9" />
        </TD>
        <TD ALIGN="right"><b><bean:message key='i_cc_cle_t'/></b>&nbsp;
          <myriap:select size='1' name='rechercheSociete' property='confidentialite' style='HEIGHT: 20px; WIDTH: 70px' tabindex="16" >
  	        <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
          </myriap:select></TD>
        <TD>&nbsp;</TD>
      </TR>
 
      <TR>
        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
	    <TD ALIGN="left">
	       <myriap:select size='1' name='rechercheSociete' property='siteOrigine' onchange='doSoumettreRafraichir();' style='HEIGHT: 20px; WIDTH: 160px' tabindex="2" >
		      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
		        valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>'  
				valeurDiscriminant='<%=entite%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>
	    </TD>
        <TD ALIGN="right"><b><bean:message key='i_ro_cle_t'/></b></TD>
        <TD ALIGN="left">
        	<bean:define id="site" name='rechercheSociete' property="siteOrigine" type="String"/>
          		<myriap:select size='1' 
          		               name='rechercheSociete' 
          		               property="role"
          					   style="HEIGHT: 20px; WIDTH: 160px" 
          					   tabindex="10" >
          	  <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=site %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
          </myriap:select></TD>
        <TD colspan="2" rowspan="4" valign="top" align="right">
        <TABLE cellpadding="2" cellspacing="2" border="0" class="tableOutline">
        <tr><td>
        	<b><bean:message key='sujet_t'/></b>
        	<table cellpadding="2" cellspacing="2" border="0">
		      <TR>
		        <TD ALIGN="right" ><b><bean:message key='v_su_nom_t'/></b></TD>
		        <TD><myriap:text name='rechercheSociete' property="nomSujet" style="HEIGHT: 20px; WIDTH: 160px" tabindex="17" /></TD>
			  </tr>
			  <TR>
		        <TD ALIGN="right" ><b><bean:message key='v_su_prenom_t'/></b></TD>
		        <TD><myriap:text name='rechercheSociete' property="prenomSujet" style="HEIGHT: 20px; WIDTH: 160px" tabindex="18" /></TD>
		      </TR>        	
        	</table>
        </td></tr></TABLE>
        </TD>
        <TD>&nbsp;</TD>
      </TR>
      <TR>
        <TD ALIGN="right" nowrap><b><bean:message key='v_so_raison_sociale_t2'/></b></TD>
        <TD ALIGN="left"><myriap:text name='rechercheSociete' property="raisonEtre" style="HEIGHT: 20px; WIDTH: 160px" tabindex="3" /></TD>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_1_t'/>  </b></TD>
        <TD ALIGN="left"><myriap:text name='rechercheSociete' property="reference1" style="HEIGHT: 20px; WIDTH: 160px" tabindex="11" /></TD>

        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>        	      
      <TR>
        <TD ALIGN="right"><b><bean:message key='v_su_nom_textuel'/></b></TD>
        <TD ALIGN="left">
		    <cardex:AutoCompleter name='rechercheSociete' property="nom" tabindex="4" maxlength="50" 
	   		style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.NOM_SOCIETE_AUTO_COMPLETER %>'
   			height="150" width="150" nbrAmorce="2"/>
   		</TD>
        <TD ALIGN="right" ><b><bean:message key='i_cl_cle_t'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='rechercheSociete' property='classe' style='HEIGHT: 20px; WIDTH: 160px' tabindex="12" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			<cardex:optionTag classe='<%= GlobalConstants.CleListe.CLASSE %>'/>
          </myriap:select>
        </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='v_su_nom_phonetique'/></b></TD>
        <TD ALIGN="left"><myriap:text
					name='rechercheSociete' 
					property='nomPhonetique'
					tabindex="5"
					style='HEIGHT: 20px; WIDTH: 160px'/>
        </TD>
        <TD ALIGN="right"><b><bean:message key='i_ls_cle_t'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='rechercheSociete' property="langue"
          style="HEIGHT: 20px; WIDTH: 160px" tabindex="13" >
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.LANGUE %>'/>
          </myriap:select></TD>
        <TD ALIGN="right" ><b>&nbsp;</b></TD>
        <TD ALIGN="left">&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='i_pa_cle_t'/></b></TD>
        <TD ALIGN="left">
          <bean:define id="pays" name='rechercheSociete' property="pays" type="String"/>
          <myriap:select size='1' name='rechercheSociete' property="pays" style="HEIGHT: 20px; WIDTH: 160px"  onchange='doSoumettreRafraichir();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" tabindex="6" onkeypress="typeAhead(this, event);">
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.PAYS %>'/>
          </myriap:select>
        </TD>
        <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='rechercheSociete' property="statut"
          style="HEIGHT: 20px; WIDTH: 160px" tabindex="14" >
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SOCIETE %>'/>
          </myriap:select></TD>
        <TD ALIGN="right" colspan="2"><b><bean:message key='i_se_cle_inv_t'/></b>&nbsp;
        	<myriap:select size='1' name='rechercheSociete' property="severite"
          style="HEIGHT: 20px; WIDTH: 40px" tabindex="18" >
	           <cardex:severiteOptions />
          </myriap:select></TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='l_pr_cle_t'/></b></TD>
        <TD ALIGN="left">
          <bean:define id="province" name='rechercheSociete' property="province" type="String"/>
          <myriap:select size='1' name='rechercheSociete' property="province" style="HEIGHT: 20px; WIDTH: 160px" onchange='doSoumettreRafraichir();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" tabindex="7" onkeypress="typeAhead(this, event);">
        	<cardex:optionTag classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays%>'/>
          </myriap:select>
        </TD>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_2_t'/></b></TD>
        <TD>
	        <myriap:text name='rechercheSociete' property='reference2' tabindex="15" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px; " />
	    </TD>
        <TD ALIGN="right" colspan="2"><b><bean:message key='i_se_cle_inv_casino_t'/></b>&nbsp;
        <myriap:select size='1' name='rechercheSociete' property="severiteCasino"
          style="HEIGHT: 20px; WIDTH: 40px" tabindex="19" >
	           <cardex:severiteOptions />
          </myriap:select></TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='l_vi_cle_t'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='rechercheSociete' property="ville"
          style="HEIGHT: 20px; WIDTH: 160px" tabindex="8" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province%>'/>
          </myriap:select>
        </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD WIDTH="850" ALIGN="center" COLSPAN="7" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="850" height="1" border="0" /></TD>
      </TR>
	  <TR>
        <TD WIDTH="850" ALIGN="center" COLSPAN="7">
	        
	      <TABLE width="100%" cellPadding="2" cellSpacing="0" border="0" class="tabTitleSmallGrayed">

      <TR>
        <TD COLSPAN="3" >
           <b><bean:message key='affichage_resultats'/></b>
           <html:select size='1' name='rechercheSociete' property='listeResultat.plageResultats' >
              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
           </html:select>
        </TD>
        <TD>
           <cardex:button accessKey='r' urlSecurite='<%=urlSecuriteRecherche%>' labelKey='cb_rechercher' onclick='doSearch();' />
        </TD>
        <TD>&nbsp;
		    <!-- On ne permet l'ajout qu'à partir de l'écran de liaison -->
		    <logic:present name="rechercheSociete" property="entiteCardexLiaison" >
		       <cardex:securityDefineTag nameDefine="ajoutSociete" urlSecurite="/societe/create.do">
		          <logic:equal name="rechercheSociete" property="lienRoleRequis" value="true" >
			        <b><bean:message key='i_ro_cle_t' /></b>&nbsp;
			        <bean:define id="siteLiaison" name='rechercheSociete' property="lienSite" type="String"/>
			    	<myriap:select name="rechercheSociete" property="roleLiaison" size="1" style="height:20px; WIDTH: 150px; "  >
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=siteLiaison %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
					</myriap:select>          
			    	&nbsp;
			      </logic:equal>
		          <logic:equal name="rechercheSociete" property="lienRoleRequis" value="false" >
			         <html:hidden name='rechercheSociete' property="roleLiaison" />
			      </logic:equal>
			      <cardex:button onclick="doAdd();" urlSecurite="/societe/create.do" labelKey='cb_ajouter' />
			   </cardex:securityDefineTag>
			</logic:present>
            <cardex:button labelKey='cb_epuration' onclick='doDelete();' urlSecurite="/societe/delete.do" />
        </TD>
        <TD ALIGN="right" COLSPAN="2">
          <cardex:button labelKey='cb_clear' soumettre="/societe/search/reset/default.do" />
          <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
        </TD>
      </TR>
	  </Table>
        </TD>
      </TR>
	  
      <TR>
        <TD ALIGN="middle" COLSPAN="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
      </TR>
    </TABLE>
    <!-- END KIND & NATURE SEARCH FIELDS -->

    </TD>
  </TR>
</TABLE>
<!-- END OUTLINE TABLE -->
<html:hidden name='rechercheSociete' property="lienRoleRequis" />
