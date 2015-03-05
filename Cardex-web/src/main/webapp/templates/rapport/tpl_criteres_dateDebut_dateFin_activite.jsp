<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="org.apache.struts.Globals" %>

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

<TABLE width="680" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
	<TR>
		<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br></TD>
    </TR> 
	<TR>
	<tr>
		<td align="right" width="190" ><b><bean:message key='du_t' /></b></td>
     	<td nowrap="nowrap" colspan="3" width="490" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
	</tr>
	<tr>
		<td align="right" width="190" ><b><bean:message key='date_creation_fin_t' /></b></td>
     	<td nowrap="nowrap" colspan="3" width="490">
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>
	<tr>
		<td align="right" width="190" ><b><bean:message key='i_tc_cle_t' /></b></td>
     	<td nowrap="nowrap" colspan="7" width="490">
     	   <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
	       <myriap:select size='1' name='<%=form%>' tabindex="3" property='activite' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
             	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_ACTIVITE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	       </myriap:select>&nbsp;		    
		</td>
	</tr>

