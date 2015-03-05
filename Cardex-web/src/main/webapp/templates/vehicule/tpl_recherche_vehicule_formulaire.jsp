<%-- --------------------------------------------------------------------------
Use case    : Recherche d'un véhicule.
Description : Module d'affichage représentant le formulaire de recherche d'un
              véhicule.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/03/22 17:20:37 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.5 $, $Date: 2002/03/22 17:20:37 $, $Author: fguerin $
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
 
<!-- Kind & nature search fields -->
<!-- Following table is used to produce an outline -->
<TABLE width="870" cellpadding="0" cellspacing="0" border="0" class="tableOutline">
  <TR>
    <TD CLASS="tabBackground">
    <TABLE  width="870" cellpadding="2" cellspacing="0" border="0">
      <!-- First row uses transparent pixel to force good alignment -->
      <TR>
      	<TD ALIGN="middle" COLSPAN="5"><html:img page="/images/blank.gif" width="766" height="1" border="0" /></TD>
      </TR>
      <TR>
        <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
          <b><bean:message key='i_en_cle_t'/></b></TD>
        <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
           <bean:define id="entite" name='rechercheVehicule' property="entite" type="String"/>
           <myriap:select name='rechercheVehicule' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' tabindex="1" onchange='doSoumettreRafraichir();'>
      	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
           </myriap:select>
        </TD>
		<TD ALIGN="right" width="130"><b><bean:message key='co_no_fiche_t' /></b></TD>
		<TD ALIGN="left"><myriap:text
			name='rechercheVehicule' property="numeroFiche"
			style="HEIGHT: 20px; WIDTH: 120px"
			tabindex="6" /></TD>
        <TD ALIGN="right"><b><bean:message key='i_cc_cle_t'/></b></TD>
        <TD ALIGN="left"><html:select size='1' name='rechercheVehicule' property="confidentialite"
          style="HEIGHT: 20px; WIDTH: 80px" tabindex="8" >
               	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE %>' />
          		</html:select></TD>
      </TR>
      <TR>
      	<TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      	<TD ALIGN="left">
       		<myriap:select size='1' name='rechercheVehicule' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px' tabindex="2" onchange='doSoumettreRafraichir();' >
		      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
		        valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>'  
				valeurDiscriminant='<%=entite%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        	</myriap:select>
      	</TD>
        <TD ALIGN="right"><b><bean:message key='l_pr_cle_t'/></b></TD>
        <TD ALIGN="left"><html:select name='rechercheVehicule' property='cleProvince' style="HEIGHT: 20px; WIDTH: 160px" tabindex="7" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" onchange="" >
	        <cardex:optionTag classe='<%= GlobalConstants.CleListe.PROVINCE_SANS_REQUIS %>' />
          	</html:select>
        </TD>
        <TD ALIGN="right"><b><bean:message key='i_pt_cle_1_t' /></b></TD>
        <TD ALIGN="left"><html:select name='rechercheVehicule' property='particularite1' style="HEIGHT: 20px; WIDTH: 160px" tabindex="9" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
        	<cardex:optionTag classe='<%= GlobalConstants.CleListe.PARTICULARITE%>'/>
          	</html:select></TD>
      </TR>
      <TR> 
        <TD ALIGN="right"><b><bean:message key='i_ma_cle_t'/></b></TD>
        <TD ALIGN="left">
        	<bean:define id="marque" name='rechercheVehicule' property="marque" type="String"/>
        	<myriap:select size='1' name='rechercheVehicule' property="marque"
          		style="HEIGHT: 20px; WIDTH: 160px" onclick="doRafraichir('modele',this.value);" tabindex="3" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
            	<cardex:optionTag classe='<%= GlobalConstants.CleListe.MARQUE %>'/>
          	</myriap:select>
        </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD ALIGN="left"><html:select name='rechercheVehicule' property='particularite2' style="HEIGHT: 20px; WIDTH: 160px" tabindex="10" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			<cardex:optionTag classe='<%= GlobalConstants.CleListe.PARTICULARITE%>'/>
          	</html:select></TD>
        <TD>&nbsp;</TD>
      </TR>
      <TR> 
        <TD ALIGN="right"><b><bean:message key='i_md_cle_t'/></b></TD>
        <TD ALIGN="left">
        	<html:select size='1' name='rechercheVehicule' property="modele" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();"
			  style="HEIGHT: 20px; WIDTH: 160px" tabindex="4" >
			  <cardex:optionTag classe='<%= GlobalConstants.CleListe.MODELE %>' valeurDiscriminant='<%=marque %>'/>
		  	</html:select>
          	<img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Consultation" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
        </TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
	    <TD ALIGN="left"><html:select name='rechercheVehicule' property='particularite3' style="HEIGHT: 20px; WIDTH: 160px" tabindex="11" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
	        <cardex:optionTag classe='<%= GlobalConstants.CleListe.PARTICULARITE%>'/>
          	</html:select></TD>
      </TR>
      <TR>
        <TD ALIGN="right"><b><bean:message key='v_ve_immatriculation_t'/></b></TD>
        <TD ALIGN="left"><myriap:text name='rechercheVehicule' property="immatriculation"  style="HEIGHT: 20px; WIDTH: 160px" tabindex="5" onkeydown="return isNumericImmatriculationTag(event.keyCode);" onkeyup="toUpper(this);keyShiftUP(event.keyCode);"/></TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
        <TD ALIGN="left"><html:select name='rechercheVehicule' property='particularite4' style="HEIGHT: 20px; WIDTH: 160px" tabindex="12" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
        	<cardex:optionTag classe='<%= GlobalConstants.CleListe.PARTICULARITE%>'/>
          	</html:select></TD>
      </TR>
      <TR>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
		<TD>&nbsp;</TD>
        <TD ALIGN="left"><html:select name='rechercheVehicule' property='particularite5' style="HEIGHT: 20px; WIDTH: 160px" tabindex="13" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
        	<cardex:optionTag classe='<%= GlobalConstants.CleListe.PARTICULARITE%>'/>
          	</html:select></TD>
      </TR>
      <TR>
        <TD WIDTH="970" ALIGN="center" COLSPAN="6" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="100%" height="1" border="0" /></TD>
      </TR>
      <TR>
        <TD WIDTH="970" ALIGN="center" COLSPAN="7">
        <!-- e"Sort by" options listing -->
      <TABLE width="970" align="left" cellPadding="2" cellSpacing="0" border="0" class="tabTitleSmallGrayed">
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheVehicule' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>
			</TD>
	        <TD valign="top" >
	            <cardex:button accessKey='r' labelKey='cb_rechercher' urlSecurite='<%=urlSecuriteRecherche%>' onclick="doSearch();" />
	        </TD>
	        <TD>&nbsp;
	            <cardex:securityDefineTag nameDefine="ajoutVehicule" urlSecurite="/vehicule/create.do">
				    <!-- On ne permet l'ajout qu'à partir de l'écran de liaison -->
				    <logic:present name="rechercheVehicule" property="entiteCardexLiaison" >
			            <cardex:button onclick="doAdd();" urlSecurite="/vehicule/create.do" labelKey='cb_ajouter' />
					</logic:present>
				</cardex:securityDefineTag>
	        </TD>
	        <TD width="100" ALIGN="right">
		    <cardex:button urlSecurite="/vehicule/delete.do" labelKey='cb_epuration' onclick='doDelete();' />
	        </TD>
	        <TD align="right">
	          <cardex:button labelKey='cb_clear' soumettre="/vehicule/search/reset/default.do" />
	          <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
	        </TD>
	      </TR>
      </TABLE>
        <!-- End Sort by -->
        </TD>
      </TR>
      <TR>
        <TD ALIGN="middle" COLSPAN="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
      </TR>
    </TABLE>
    <!-- End Kind & nature search fields -->
    </TD>
  </TR>
</TABLE>
<!-- End outline table -->
<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:180; height:20;" style="z-index: 1; position: absolute; left: 260px; top: 140px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='rechercheVehicule' property="modeleMarque" onchange="choisirModele(this.value);" style="HEIGHT: 110px; WIDTH: 170px; background-color:beige" tabindex="4" >
	  <cardex:optionTag classe='<%= GlobalConstants.CleListe.MODELE_MARQUE %>'/>
	</html:select>          
</DIV>