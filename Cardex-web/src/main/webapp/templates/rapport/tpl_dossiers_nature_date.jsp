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

<TABLE width="680" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
	<TR>
		<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br></TD>
    </TR> 
	<TR>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_en_cle_t'/>&nbsp;</b></TD>
      <td nowrap="nowrap" colspan="3">
        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
        <myriap:select name='<%=form%>' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 170px' tabindex="1" onchange='doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
	      <td nowrap="nowrap" colspan="3">
	      <bean:define id="genre" name='<%=form%>' property="genre" type="String"/>
	        <myriap:select size='1' name='<%=form%>' property='genre' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" onchange='doSoumettreRafraichir();'>
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
	      <td nowrap="nowrap" colspan="3">
	      
	       <myriap:select size='1' name='<%=form%>' property='nature' style='HEIGHT: 20px; WIDTH: 170px' tabindex="3">
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'
					valeurDiscriminant='<%=genre%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>

	<tr>
		<td align="right" ><b><bean:message key='du_t' />&nbsp;</b></td>
     	<td nowrap="nowrap" colspan="3" width="590" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
	</tr>
	<tr>
		<td align="right" ><b><bean:message key='date_creation_fin_t' />&nbsp;</b></td>
     	<td nowrap="nowrap" colspan="3" width="590">
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>
