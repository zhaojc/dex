<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
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
	<tr>
   	 	<TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
    		<b><bean:message key='i_en_cle_t'/></b>
       	</TD>
   		<TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
	        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
	        <myriap:select name='<%=form%>' tabindex="1" property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
		   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>      		  
     		</TD>
        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
        <TD ALIGN="left">
       	<myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 200px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>        
		</TD>
    </tr>

	<TR>
	    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
	    <td nowrap="nowrap" colspan="3" width="590" >
	    	<bean:define id="site" name='<%=form%>' property='site' type="String" />
	       <myriap:select size='1' name='<%=form%>' tabindex="3" property='intervenant' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SITE%>' valeurDiscriminant='<%=site%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>&nbsp;		    
	    </TD>
	</TR>
	 		
	<tr>
		<td align="right" width="90" ><b><bean:message key='du_t' /></b></td>
     	<td nowrap="nowrap" colspan="6" width="590" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
	</tr>
	
	<tr>
		<td align="right" width="90" ><b><bean:message key='date_creation_fin_t' /></b></td>
     	<td nowrap="nowrap" colspan="7" width="590">
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>

	<tr>
		<td align="right" width="90" ><b><bean:message key='i_tc_cle_t' /></b></td>
     	<td nowrap="nowrap" colspan="7" width="590">
	       <myriap:select size='1' name='<%=form%>' tabindex="3" property='activite' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
             	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_ACTIVITE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	       </myriap:select>&nbsp;		    
		</td>
	</tr>
	