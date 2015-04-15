
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<!-- Kind & nature search fields -->
<!-- Following table is used to produce an outline -->
<TABLE width="770" cellpadding="1" cellspacing="0" border="0">

  <TR>
    <TD CLASS="tabTitle">

    <TABLE width="100%" cellpadding="2" cellspacing="0" border="0" CLASS="tabBackground" style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#acc8c8', startColorstr='#FFFFFF', gradientType='0');" bgcolor="#acc8c8">
      <TR>
      <TD ALIGN="center" COLSPAN="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR><!-- First row uses transparent pixel to force good alignment -->
    <TR>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_en_cle_t'/></b></TD>
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
      	<bean:define id="entite" name='rechercheAdresses' property="entite" type="String"/>
        <myriap:select name='rechercheAdresses' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' tabindex="1">
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select> 
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rechercheAdresses' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px'  tabindex="2" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>		  
      </TD>
    </TR>

    <TR>
      <TD WIDTH="100%" ALIGN="center" COLSPAN="4" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" height="1" border="0" width="100%"/></TD>
    </TR>
    
	<TR>
  		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
  		<td>
        	<myriap:text name='rechercheAdresses' property='numeroMunicipal' tabindex="3" maxlength='40' style='HEIGHT: 20px; WIDTH: 100px' />
        </td>
  		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
  		<td>
            <myriap:select name='rechercheAdresses' property='typeRue' tabindex="4" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
	             <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache" />
            </myriap:select>
    	</td>
  	</TR>
  	
	<TR>
  		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
  		<td>
        	<myriap:text name='rechercheAdresses' property='nomRue' tabindex="5" maxlength='40' style='HEIGHT: 20px; WIDTH: 240px' />
        </td>
  		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
  		<td>
            <myriap:select name='rechercheAdresses' property='pointCardinal' tabindex="6" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache" />             
            </myriap:select>          		
    	</td>
  	</TR>
  	
	<TR>
  		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
  		<td>
            <myriap:select name='rechercheAdresses' property='unite' tabindex="7" size='1' style='HEIGHT: 20px; WIDTH: 100px'>
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache" />
            </myriap:select>	            	
            </td>
      		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
      		<td>
	        	<myriap:text name='rechercheAdresses' property='numeroUnite' tabindex="8" maxlength='14' style='HEIGHT: 20px; WIDTH: 120px' />
        	</td>
      	</TR>

		<TR>
      		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
      		<td>
            	<myriap:text name='rechercheAdresses' property='adressePostal' tabindex="9" maxlength='200' style='HEIGHT: 20px; WIDTH: 240px' />
            </td>
  			<td align="right" nowrap><b><bean:message key='v_ad_telephone_t'/></b></td>
  			<td>
              <myriap:text name='rechercheAdresses' property='telephone' tabindex="13" maxlength='14'  style='HEIGHT: 20px; WIDTH: 120px' />
            </td>
      	</TR>          	

          <TR>
  			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
  			<td>
                <myriap:select name='rechercheAdresses' property='pays' tabindex="10" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doSoumettreRafraichir();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache" />
                </myriap:select>
            </td>
  			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
  			<td>
               <myriap:text name='rechercheAdresses' property='codePostal' tabindex="14" maxlength='10'  style='HEIGHT: 20px; WIDTH: 120px' />
            </td>
  		</TR>

      <TR>
  			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
  			<td>
  				<bean:define id="pays" name='rechercheAdresses' property='pays' type="String"/> 
                <myriap:select name='rechercheAdresses' property='province' tabindex="11" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doSoumettreRafraichir();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache" valeurDiscriminant='<%=pays%>'/>
                </myriap:select>
         	</td>
         	<TD colspan="2" rowspan="2" >
         	<TABLE cellpadding="2" cellspacing="0" class="tableOutline" width="100%" height="100%">
         	<TR>
	         	<TD width="200" align="right"><b><bean:message key='recherche_sujets'/></b></TD>
	         	<TD><myriap:radio name='rechercheAdresses' property="entiteRecherche" value='<%=GlobalConstants.ChoixRechercheAdresse.SUJET%>' tabindex="15"/></TD>
         	</TR>         	
         	<TR>
	         	<TD align="right"><b><bean:message key='recherche_societes'/></b></TD>
	         	<TD><myriap:radio name='rechercheAdresses' property="entiteRecherche" value='<%=GlobalConstants.ChoixRechercheAdresse.SOCIETE%>' tabindex="16"/></TD>
         	</TR>         	
         	</TABLE>
         	</TD>
  		</TR>

  		<TR>
  			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
  			<td>
  				<bean:define id="province" name='rechercheAdresses' property='province' type="String"/> 
	              <myriap:select name='rechercheAdresses' property='ville' tabindex="12" size='1' style='HEIGHT: 20px; WIDTH: 240px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache" valeurDiscriminant='<%=province%>'/>
	              </myriap:select>
             </td>
  		</TR>

  		<TR>
  			<td align="right" nowrap><b><bean:message key='courriel_t'/></b></td>
  			<td>
  				<myriap:text name='rechercheAdresses' property='adresseElectronique' maxlength='100' style='HEIGHT: 20px; WIDTH: 240px' />
            </td>
  		</TR>
    <cardex:securityDefineTag nameDefine="adresseInvalide" securityConstraint="cardex.cb_rapport.adresse.invalide">
	<TR>
		<TD WIDTH="100%" align="right" COLSPAN="4">
    <TABLE width="220" cellpadding="1" cellspacing="0" border="0" class="tableOutline">
      <TR>
        <TD colspan="2" nowrap><b><bean:message key='adresses.crees' /></b></TD>
      </TR>
      <TR>
        <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='du_t'/></b>
          <myriap:text name='rechercheAdresses' property="dateCreationDu" size="10" maxlength="10" tabindex="31" 
          onkeyup="doTraits(document.forms(0).dateCreationDu);changerChamp(this,'dateCreationAu');"/>
          <myriap:link href="javascript:openDate('document.forms(0).dateCreationDu', document.forms(0).dateCreationDu.value);"  onmousedown="setXY(event.x - 40, event.y);">
            <html:img page="/images/cal.gif"border="0" />
          </myriap:link>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='au_t'/></b>
          <myriap:text name='rechercheAdresses' property="dateCreationAu" size="10" maxlength="10" tabindex="32" onkeyup="doTraits(document.forms(0).dateCreationAu);changerChamp(this,'dateCreationDu');" />
          <myriap:link href="javascript:openDate('document.forms(0).dateCreationAu', document.forms(0).dateCreationAu.value);" onmousedown="setXY(event.x - 40, event.y);">
            <html:img page="/images/cal.gif"border="0" />
          </myriap:link>
        </TD>
      </TR>
    </TABLE>
	</TD></TR>
    </cardex:securityDefineTag>
	<TR>
      <TD WIDTH="100%" ALIGN="center" COLSPAN="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD WIDTH="100%" ALIGN="center" COLSPAN="4" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" height="1" border="0" width="100%" /></TD>
    </TR>    
    <TR>
      <TD COLSPAN="4" >
     <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0" >
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
          <html:select size='1' name='rechercheAdresses' property='listeResultat.plageResultats' >
              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
           </html:select>&nbsp;
          <cardex:button labelKey='cb_rechercher' soumettre='/adresses/soumettreRecherche.do' />&nbsp;&nbsp;&nbsp;
          <logic:equal name="adresseInvalideHidden" value="false">
          	<cardex:button urlSecurite="/CritereRapportAffichagePDF" labelKey='cb_rapport.adresse.invalide' onclick='doSoumettreRechercheAdressesInvalides();' />
          </logic:equal>
          </TD>
          <TD align="right">
	          <cardex:button soumettre="/adresses/recherche/default.do" labelKey='cb_clear' />&nbsp;&nbsp;&nbsp;
    	      <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
    	      
          </TD>
        </TR>
      </TABLE>
      </TD>
     <TD>&nbsp;</TD>
    </TR>

    </TABLE>
    <!-- End Kind & nature search fields -->
  </TD>
  </TR>
</TABLE>
<!-- End outline table -->
<jsp:include page="/templates/tpl_calendrier_div.jsp" flush="true" />
<cardex:RetourFocus name="rechercheAdresses" />
