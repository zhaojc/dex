<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
	    document.forms(0).nature.value = "";
		soumettreActionMethod("rafraichir");
	}

</SCRIPT>

<html:hidden name='<%=form%>' property='entite' />
<html:hidden name='<%=form%>' property='genre' />

<TABLE width="680" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
        <myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" >
 	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
	</TR>
	<TR>
      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
      <td nowrap="nowrap">
         <bean:define id="genre" name='<%=form%>' property="genre" type="String"/>
         <myriap:select size='1' name='<%=form%>' property='nature' style='HEIGHT: 20px; WIDTH: 170px' tabindex="3">
	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'
				valeurDiscriminant='<%=genre%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
         </myriap:select>
      </TD>
	</TR>

