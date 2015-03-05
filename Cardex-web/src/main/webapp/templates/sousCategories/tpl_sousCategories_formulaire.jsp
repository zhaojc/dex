
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- CONTENT -->
<table width="700" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline"
style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#999999', startColorstr='#ffffff', gradientType='0');" >
<TR>
  <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
  <TD ALIGN="left">
	  <bean:define id="entite" name='dossier' property="entite" type="String"/>
	  <bean:define id="genre" name='sousCategories' property="genre" type="String"/>
	  <myriap:select name='sousCategories' property="genre" size="1" style="HEIGHT: 20px; WIDTH: 140px" onchange='doSoumettreRafraichir();' >
   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
            valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
			valeurDiscriminant='<%=entite%>' 
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />	     
	  </myriap:select>&nbsp;
  </TD>
  <td width="500">&nbsp;</td>  
</TR> 
<TR>
  <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
  <TD ALIGN="left">
       <myriap:select size='1' name='sousCategories' property='nature' tabindex="4" style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
			valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
			valeurDiscriminant='<%=genre%>' 
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />	  	   	
       </myriap:select>
  </TD>
  <td width="500">&nbsp;</td>
</TR>
<tr>
	<td width="200" colspan="3">
		<tiles:insert page="/commun/doubleListe.jsp" flush="true">
			<tiles:put name="formName" value="sousCategories" />
			<tiles:put name="property" value="doubleListe" />
			<tiles:put name="strGaucheCol" value="sous.categories.disponible" />
			<tiles:put name="strDroiteCol" value="sous.categories.choisis" />
			<tiles:put name="listeGaucheLargeur" value="300" />
			<tiles:put name="listeDroiteLargeur" value="300" />
			<tiles:put name="listeHauteur" value="10" />
			<tiles:put name="idSelectMultiboxes" value="SousCategories" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
    <!-- START BUTTONS SECTION -->
    <cardex:button labelKey='cb_ok' urlSecurite="/dossier/sousCategories/update.do"  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
    </td>
    <td width="500">&nbsp;</td>
    <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
    <td align="right">
    <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
    </td>
</tr>
</table>
 <!-- END CONTENT -->
