<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<SCRIPT language="JavaScript" type="text/javascript">

	function doRefresh() {
		soumettreActionMethod("rafraichir");
	}

</SCRIPT>


<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

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
	        <myriap:select name='<%=form%>' tabindex="1" property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
		   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>      		  
     		</TD>
        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
        <TD ALIGN="left">
       	<myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 200px' tabindex="7" onchange='doRefresh();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>        
		</TD>
    </tr>
   	<tr>
  	   <td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
   	   <td>
	   	   <bean:define id="secteur" name='<%=form%>' property="secteur" type="String"/>
           <myriap:select name='<%=form%>' tabindex="2" property="secteur" size="1" style="HEIGHT: 20px; WIDTH: 160px" onchange='doRefresh();' >
           	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
           </myriap:select>
       </td>
		 <TD ALIGN="right"><b><bean:message key='regroupement_t'/></b></TD>
       <TD ALIGN="left">
		<myriap:select size='1' name='<%=form%>' property='regroupement' tabindex="5" style='HEIGHT: 20px; WIDTH: 200px;' >
			<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.RegroupementCleMultiListeCache" valeurDiscriminant='<%=secteur%>'/>
	   	</myriap:select>
       </TD>
	</tr>
	<TR>
		<TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
	    <TD ALIGN="left">
	      <bean:define id="genre" name='<%=form%>' property="genre" type="String"/>
	       <myriap:select  size='1' name='<%=form%>' property='genre' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                    	valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>	         
         </TD>
	    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
	    <TD ALIGN="left" >
	    	<bean:define id="secteur" name='<%=form%>' property='secteur' type="String" />
	       <myriap:select size='1' name='<%=form%>' tabindex="8" property='intervenant' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>&nbsp;		    
	    </TD>
	</TR>
	<TR>
         <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
         <TD ALIGN="left">
         <bean:define id="nature" name='<%=form%>' property="nature" type="String"/>
	       <myriap:select size='1' name='<%=form%>' property='nature' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
		         <cardex:optionTag
		         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
					valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
					valeurDiscriminant='<%=genre%>'
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
         </TD>
           	<TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
     			<td>
               <bean:define id="site" name='<%=form%>' property='site' type="String"/>
		        <myriap:select size='1' name='<%=form%>' property='endroit' style='HEIGHT: 20px; WIDTH: 200px' tabindex="4" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
			 	</myriap:select>
     			</td>
	</TR>
	<TR>
         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
         <TD ALIGN="left">
			<bean:define id="type" name='<%=form%>' property="type" type="String"/>
	        <myriap:select size='1' name='<%=form%>' property='type' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
				<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
				valeurDiscriminant='<%=nature%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>				
			</TD>
			
     			<td align="right" nowrap><b><bean:message key='remphor.entre.le'/><bean:message key='2.points'/></b></td>
     			<td nowrap>
     				<cardex:Date name='<%=form%>' tabindex="11" property="dateDebutDu" calendrier="true" nomProchainChamp="dateDebutAu"/>
     			</td>
			</TR>
     		<tr>
	 	<TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
         <TD ALIGN="left">
	         <myriap:select size='1' name='<%=form%>' property='categorie' tabindex="5" style='HEIGHT: 20px; WIDTH: 160px' >
				<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
					valeurDiscriminant='<%=type%>'
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
			 </myriap:select>	         
     		 </TD>
	 	<td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
			<td nowrap>
			  <cardex:Date name='<%=form%>' tabindex="12" property="dateDebutAu" calendrier="true" />
			</td>
	</tr>
     		
