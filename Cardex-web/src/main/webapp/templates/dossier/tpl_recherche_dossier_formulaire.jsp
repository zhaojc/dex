<%-- --------------------------------------------------------------------------
Use case    : Recherche d'un dossier.
Description : Module d'affichage représentant le formulaire d'une recherche de
              dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.54 $, $Date: 2002/02/25 19:12:36

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

Revision: 1.23 $, Date: 2002/02/15 22:30:19 , Author: abruno-boucher
Ajout de l'Assistant Date-Heure.

$Revision: 1.54 $, $Date: 2002/04/25 19:49:24 $, $Author: mlibersan $
Correction au VERY_TWISTED_BUTTON, compatibilité I.E. 5.
--------------------------------------------------------------------------- --%>
  
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@page import="com.lotoquebec.cardexCommun.util.StringUtils"%>


<tiles:useAttribute name="urlSecuriteRecherche" id="urlSecuriteRecherche" classname="String"/>
<tiles:useAttribute name="urlSecuritePartage" id="urlSecuritePartage" classname="String"/>
<tiles:useAttribute name="urlSecuriteRafraichir" id="urlSecuriteRafraichir" classname="String"/>


<!-- Kind & nature search fields -->
<!-- Following table is used to produce an outline -->


<TABLE cellpadding="1" cellspacing="0" border="0">

  <TR>
    <TD CLASS="tabTitle">

    <TABLE  width="980" cellpadding="2" cellspacing="0" border="0" 
    style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#cbd8e9', startColorstr='#FFFFFF', gradientType='0');">
      <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR><!-- First row uses transparent pixel to force good alignment -->
    <TR>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_en_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <bean:define id="entite" name='rechercheDossier' property="entite" type="String"/>
        <myriap:select name='rechercheDossier' property='entite' size='1' tabindex="1" style='HEIGHT: 20px; WIDTH: 200px' onchange='document.forms(0).genre.selectedIndex=0;document.forms(0).nature.selectedIndex=0;document.forms(0).type.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='no_dossier_t'/></b></TD>
      <TD ALIGN="left" nowrap>
        <myriap:select name='rechercheDossier' property='siteNumeroCardex' tabindex="11" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_ABREVIATION %>'
	         valeurTableValeur='<%=GlobalConstants.TableValeur.SITE %>'
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
        <myriap:text name='rechercheDossier' property='dateNumeroCardex' maxlength='10'  size='10' tabindex="12" onkeyup="doTraits(this,'sequenceNumeroCardex');changerChamp(this,'sequenceNumeroCardex');" />
        <myriap:text name='rechercheDossier' property='sequenceNumeroCardex' maxlength='4' size='4' style="WIDTH: 32px" tabindex="13" />
        <myriap:link href="javascript:openDate('document.forms(0).elements(4)', document.forms(0).elements(4).value);" onmousedown="setXY(event.x + 47, event.y);" >
	  <html:img page="/images/cal.gif"border="0" />
        </myriap:link>
      </TD> 
      <TD ALIGN="right" VALIGN="top"><b><bean:message key='i_cc_cle_t'/></b></TD>
      <TD ALIGN="left" >
        <myriap:select size='1' name='rechercheDossier' property='confidentialite' style='HEIGHT: 20px; WIDTH: 50px' tabindex="22" >
		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
 		</myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rechercheDossier' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 200px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right" VALIGN="top" nowrap><html:img border="0" height="5" page="/images/blank.gif" width="1" /><br>
        <b><bean:message key='v_do_ancienne_reference_t'/></b></TD>
      <TD ALIGN="left" VALIGN="top"><myriap:text name='rechercheDossier' property="numeroDossier" maxlength="20" size="20" style="HEIGHT: 20px; WIDTH: 160px" onchange='this.value = this.value.toUpperCase();' tabindex="14" /></TD>
      <TD ALIGN="right"><b><bean:message key='i_se_cle_t'/></b></TD>
      <TD ALIGN="left" >
        <myriap:select size='1' name='rechercheDossier' property='severite' style='HEIGHT: 20px; WIDTH: 50px' tabindex="23" >
	   		<cardex:severiteOptions/>
        </myriap:select>
      </TD>
      <TD ALIGN="center" > <html:img border="0" height="1" page="/images/blank.gif"  width="1" /><br>&nbsp;
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='l_si_cle_inclus_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rechercheDossier' property='siteApplicable' style='HEIGHT: 20px; WIDTH: 200px' tabindex="3" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_APPLICABLE_TABLE_VALEUR %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='rechercheDossier' property="reference1" tabindex="15" maxlength="20" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE1_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='origine_t'/></b></TD>
      <TD ALIGN="left" >
       <myriap:select size='1' name='rechercheDossier' property='origine' style='HEIGHT: 20px; WIDTH: 170px' tabindex="24" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
	   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
      <bean:define id="genre" name='rechercheDossier' property="genre" type="String"/>
       <myriap:select size='1' name='rechercheDossier' property='genre' style='HEIGHT: 20px; WIDTH: 200px' tabindex="4" onchange='doSoumettreRafraichir();' >
	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
				valeurDiscriminant='<%=entite%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='rechercheDossier' property="reference2" tabindex="16" maxlength="20" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE2_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
      <TD ALIGN="left" >
      	<bean:define id="siteEndroit" name='rechercheDossier' property='siteOrigine' type="String"/>
        <myriap:select size='1' name='rechercheDossier' property='endroit' style='HEIGHT: 20px; WIDTH: 170px' tabindex="25" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=siteEndroit%>'/>
	 	</myriap:select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rechercheDossier' property='nature' style='HEIGHT: 20px; WIDTH: 200px' tabindex="5" onchange="doSoumettreRafraichir();" >
	         <cardex:optionTag
	         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
				valeurDiscriminant='<%=genre%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference3_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='rechercheDossier' property="reference3" tabindex="17" maxlength="30" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE3_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
      <TD ALIGN="left" >
      	<bean:define id="siteLocalisation" name='rechercheDossier' property='siteOrigine' type="String"/>
        <myriap:select size='1' name='rechercheDossier' property='localisation' style='HEIGHT: 20px; WIDTH: 170px' tabindex="26" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=siteLocalisation%>'/>
	    </myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
	    <bean:define id="nature" name='rechercheDossier' property='nature' type="String"/>
        <myriap:select size='1' name='rechercheDossier' property='type' style='HEIGHT: 20px; WIDTH: 200px' tabindex="6" onchange="doRafraichir('categorie',this.value);" >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
			valeurDiscriminant='<%=nature%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
        <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
        <TD>
            <cardex:AutoCompleter name='rechercheDossier' property="descriptif" tabindex="18" maxlength="80" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
        </TD>
      <TD ALIGN="right" nowrap><b><bean:message key='enregistrement_numerique'/></b></TD>
      <TD ALIGN="left" nowrap>
			<html:radio tabindex="27" name='rechercheDossier' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
			<html:radio tabindex="28" name='rechercheDossier' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
      </TD>
   </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" nowrap>
      	 <bean:define id="type" name='rechercheDossier' property='type' type="String"/>
         <myriap:select size='1' name='rechercheDossier' property='categorie' tabindex="7" style='HEIGHT: 20px; WIDTH: 200px' >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		 </myriap:select>
         <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Liste des catégories" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
      </TD>

      	<TD ALIGN="right" VALIGN="top"><html:img border="0" height="5" page="/images/blank.gif" width="1" /><br>
    	    <b><bean:message key='v_fiche_sujet_t'/></b></TD>
	    <TD ALIGN="left" VALIGN="top"><myriap:text name='rechercheDossier' property="numeroFicheSujet" size="20" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px" tabindex="19" /></TD>

         <TD ALIGN="right" nowrap><b><bean:message key='no_seq_t'/></b></TD>
      	 <TD ALIGN="left" >
             <myriap:text name='rechercheDossier' property="referenceVideo" tabindex="29" maxlength="40" size="1" style="HEIGHT: 20px; WIDTH: 170px" />
         </TD>
    </TR>

    <TR>
      <TD ALIGN="right" nowrap><b><bean:message key='sous.categories'/><bean:message key='2.points'/></b></TD>
      <TD ALIGN="left">
       	<html:radio name='rechercheDossier' property="rechercherSousCategorie" value="<%=GlobalConstants.BooleanString.FALSE%>" onclick="doSousCategorieSeul();" />&nbsp;
       	<b><bean:message key='sous_categories_tous'/><bean:message key='2.points'/></b><html:radio name='rechercheDossier' property="rechercherTous" value="<%=GlobalConstants.BooleanString.FALSE%>" onclick="doSousCategorieTous();"/>
      </TD>

        <TD align="right"><b><bean:message key='type.jeu'/> : </b></TD>
        <TD ALIGN="left">
        <bean:define id="entite" name="rechercheDossier" property="entite" type="String"/>
          <myriap:select size='1' name='rechercheDossier' property="typeJeu" style="HEIGHT: 20px; WIDTH: 160px" tabindex="20" onkeypress="typeAhead(this, event);" onchange="doSoumettreRafraichir();" onfocus="resetIncrementalSearch();" >
          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>' valeurDiscriminant='<%=entite%>'/>
          </myriap:select> 
        </TD>


      <TD ALIGN="right" ><b><bean:message key='enregistrement_conserve'/></b></TD>
      <TD ALIGN="left" nowrap>
		<html:radio tabindex="30" name='rechercheDossier' property="enregistrementConserve" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
		<html:radio tabindex="31" name='rechercheDossier' property="enregistrementConserve" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
      </TD>
    </TR>
	<TR>
      <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rechercheDossier' property='statut' style='HEIGHT: 20px; WIDTH: 200px' tabindex="8" >
	   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
        </myriap:select>&nbsp;
      </TD>

        <TD align="right"><b><bean:message key='tabpage_jeu'/> : </b></TD>
        <TD ALIGN="left">
          <bean:define id="typeJeu" name="rechercheDossier" property="typeJeu" type="String"/>
          <myriap:select size='1' name='rechercheDossier' property="jeu" style="HEIGHT: 20px; WIDTH: 160px" tabindex="21" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.JEUX%>' valeurDiscriminant='<%=entite+","+typeJeu%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
          </myriap:select> 
        </TD>
      
      
        <TD ALIGN="right" ><b><bean:message key='classe.societe'/><bean:message key='2.points'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='rechercheDossier' property='classe' style='HEIGHT: 20px; WIDTH: 170px' tabindex="32" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			<cardex:optionTag classe='<%= GlobalConstants.CleListe.CLASSE %>'/>
          </myriap:select>
        </TD>
	</TR>
	
	<TR>
      <TD ALIGN="right">
		  <b><bean:message key='c_do_fonde_t'/></b></TD>
	      <TD ALIGN="left" colspan="5">
	          <myriap:select name='rechercheDossier' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 200px" tabindex="9" >
	              <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
	          </myriap:select>
	      </TD>
    </TR>
    	
	<TR>
      <td ALIGN="right"><b><bean:message key="v_sv_intervenant_t" />&nbsp;</b></td>
      <td ALIGN="left" colspan="5">
       <bean:define id="siteOrigine" name="rechercheDossier" property="siteOrigine" type="String"/>
       <myriap:select size="1" name="rechercheDossier" property="intervenant" style="HEIGHT: 20px; WIDTH: 450px" tabindex="10" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
				valeurDiscriminant='<%=siteOrigine%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
        </myriap:select>
        <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe2" TITLE="Liste des groupes et des intervenants" border="1" height="14" width="14" onclick="doConsulterIntervenant();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'">
      </td>

    </TR>
    <TR>
      <TD ALIGN="center" colspan="6" nowrap valign="top">
        <TABLE width="620" cellpadding="1" cellspacing="0" border="0" >
         <TR><TD>
	     <TABLE width="400" cellpadding="1" cellspacing="0" border="0" class="tableOutline">
          <TR>
            <TD colspan="4"><b><bean:message key='periode_val_t' /></b></TD>
          </TR>

          <TR>
            <TD nowrap ALIGN="right" width="120">&nbsp;&nbsp;<b><bean:message key='date_deb_per_t'/></b>&nbsp;</TD>
            <TD nowrap>
              <myriap:text name='rechercheDossier' property="dateDebutDu" size="10" maxlength="10" tabindex="33" onkeyup="doTraits(document.forms(0).dateDebutDu,'dateDebutAu');changerChamp(this,'dateDebutAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateDebutDu',document.forms(0).dateDebutDu.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD> 
            <TD nowrap>&nbsp;<b><bean:message key='au_t'/></b></TD>
            <TD nowrap>
              <myriap:text name='rechercheDossier' property="dateDebutAu" size="10" maxlength="10" tabindex="34" onkeyup="doTraits(document.forms(0).dateDebutAu,'dateFinDu');changerChamp(this,'dateFinDu');"/>
              <myriap:link href="javascript:openDate('document.forms(0).dateDebutAu', document.forms(0).dateDebutAu.value);" onmousedown="setXY(event.x, event.y);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
          <TR>
            <TD ALIGN="right" nowrap>&nbsp;&nbsp;<b><bean:message key='date_fin_per_t'/></b>&nbsp;</TD>
            <TD>
              <myriap:text name='rechercheDossier' property="dateFinDu" size="10" maxlength="10" tabindex="35" onkeyup="doTraits(document.forms(0).dateFinDu,'dateFinAu');changerChamp(this,'dateFinAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateFinDu', document.forms(0).dateFinDu.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
            <TD>&nbsp;<b><bean:message key='au_t'/></b></TD>
            <TD nowrap>
              <myriap:text name='rechercheDossier' property="dateFinAu" size="10" maxlength="10" tabindex="36" onkeyup="doTraits(document.forms(0).dateFinAu,'dateFinDu');changerChamp(this,'dateFinDu');"/>
              <myriap:link href="javascript:openDate('document.forms(0).dateFinAu', document.forms(0).dateFinAu.value);" onmousedown="setXY(event.x, event.y);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
		    <TD ALIGN="left">&nbsp;</TD>
          </TR>
        </TABLE>
	</TD>
	<TD ALIGN="left" nowrap valign="top">
        <TABLE width="220" cellpadding="1" cellspacing="0" border="0" class="tableOutline">
          <TR>
            <TD colspan="2" nowrap><b><bean:message key='nouveaux_dossiers_t' /></b></TD>
          </TR>

          <TR>
            <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='du_t'/></b>
              <myriap:text name='rechercheDossier' property="dateCreationDu" size="10" maxlength="10" tabindex="37" onkeyup="doTraits(document.forms(0).dateCreationDu,'dateCreationAu');changerChamp(this,'dateCreationAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateCreationDu', document.forms(0).dateCreationDu.value);"  onmousedown="setXY(event.x - 40, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
          <TR>
            <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='au_t'/></b>
              <myriap:text name='rechercheDossier' property="dateCreationAu" size="10" maxlength="10" tabindex="38" onkeyup="doTraits(document.forms(0).dateCreationAu,'dateCreationDu');changerChamp(this,'dateCreationDu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateCreationAu', document.forms(0).dateCreationAu.value);" onmousedown="setXY(event.x - 40, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
        </TABLE>

      </TD>
    </TR>
	</TABLE>

      </TD>
    </TR>
    <TR>
      <TD WIDTH="980" ALIGN="center" COLSPAN="6" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="970" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD WIDTH="980" ALIGN="center" COLSPAN="6">

      <!-- eSort by options for Kind & nature listing -->
      <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0">
		<tr>
	        <TD ALIGN="left" ><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheDossier' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>
			</TD>
		
            <TD ALIGN="right">
               <cardex:button urlSecurite='<%=urlSecuriteRecherche%>' accessKey="r" labelKey='cb_rechercher' onclick='doSearch();' />&nbsp;
               <cardex:button labelKey='tabpage_partage' windowOpenLocation='<%=urlSecuritePartage%>' />
            </TD>
            <TD ALIGN="left">
              <%
             //-- Le code qui suit vérifie si un genre par défaut a été ouvert dans la session.
             //-- (assigné lors de la première ouverture de l'écran de recherche de dossiers).
			 //-- Si l'écran de liaison d'un dossier est ouvert sans cette initialisation, le bouton
			 //-- Ajouter provoque une erreur. On ne l'affiche donc que si le genre est initialisé.
	   		 try{
	                if(StringUtils.isNotEmpty(nature)){
	              %>
	              <cardex:securityDefineTag nameDefine="sectionAjoutNature" valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE%>' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeur='<%=nature%>' actionSecurite='<%=GlobalConstants.ActionSecurite.AJOUT%>' >
	                   <cardex:button labelKey='cb_ajouter' soumettre="/dossier/new.do" />
                   </cardex:securityDefineTag>
		      <%    }
		        }
			 catch (Throwable e) {}
		     %>
	            </TD>
		    <TD width="100" ALIGN="right">
		      <cardex:button urlSecurite="/dossier/delete.do" labelKey='cb_epuration' onclick='doDelete();' />
	      </TD>
	      <TD align="right">
	          <cardex:button labelKey='cb_clear' soumettre='<%=urlSecuriteRafraichir%>' />
	          <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
	     </TD>	
		
	   </TR>

      </TABLE>
      <!-- End Sort by -->

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    </TABLE>
    <!-- End Kind & nature search fields -->

  </TD>
  </TR>
</TABLE>
<!-- End outline table -->


<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:220; height:20;" style="z-index: 1; position: absolute; left: 330px; top: 205px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='rechercheDossier' property="typeCategorie" onchange="choisirCategorie(this.value);"
	  style="HEIGHT: 110px; WIDTH: 220px; background-color:beige"  >
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE_CATEGORIE %>' 
		valeurDiscriminant='<%=nature%>'
		actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	</html:select>          
</DIV>
<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:400; height:20;" style="z-index: 1; position: absolute; left: 240px; top: 150px; visibility= 'hidden'" id="consulterIntervenant">
	<html:select size='8' name='rechercheDossier' property="groupesIntervenants" onchange="choisirIntervenant(this.value);"
	  style="HEIGHT: 110px; WIDTH: 430px; background-color:beige"  >
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.SECTEUR_INTERVENANT_PAR_SITE%>'
		valeurDiscriminant='<%=siteOrigine%>'/>
	</html:select>          
</DIV>
