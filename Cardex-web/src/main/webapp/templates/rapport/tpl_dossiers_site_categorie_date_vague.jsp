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
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
		<cardex:afficherValeurListeTag name='<%=form%>' property="entite" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
	      <td colspan="3">
	       <bean:define id="genre" name='<%=form%>' property="genre" type="String"/>
	       <myriap:select size='1' name='<%=form%>' property='genre' style='HEIGHT: 20px; WIDTH: 170px' tabindex="3" onchange="doSoumettreRafraichir();">
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
	      <td colspan="3">
	       <bean:define id="nature" name='<%=form%>' property="nature" type="String"/>
	       <myriap:select size='1' name='<%=form%>' property='nature' style='HEIGHT: 20px; WIDTH: 170px' tabindex="4" onchange="doSoumettreRafraichir();">
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'
					valeurDiscriminant='<%=genre%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/>&nbsp;</b></TD>
	      <td colspan="3">
	      
	        <myriap:select size='1' name='<%=form%>' property='type' style='HEIGHT: 20px; WIDTH: 120px' tabindex="5" onchange="doSoumettreRafraichir();">
				<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
				valeurDiscriminant='<%=nature%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/>&nbsp;</b></TD>
	      <td colspan="3">
	        <bean:define id="type" name='<%=form%>' property="type" type="String"/>
	        <myriap:select size='1' name='<%=form%>' property='categorie' style='HEIGHT: 20px; WIDTH: 120px' tabindex="5"  >
				<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE %>' 
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	</TR>

	<tr>
		<td align="right" ><b><bean:message key='du_t' /></b></td>
     	<td colspan="3" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
	</tr>
	
	<tr>
		<td align="right" ><b><bean:message key='date_creation_fin_t' /></b></td>
     	<td colspan="3" >
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>

	<tr>
		<td align="right" nowrap><b><bean:message key='numero.vague' /><bean:message key='2.points' /></b></td>
     	<td colspan="3">
			<myriap:text name='<%=form%>' property='vague' />
		</td>
	</tr>

