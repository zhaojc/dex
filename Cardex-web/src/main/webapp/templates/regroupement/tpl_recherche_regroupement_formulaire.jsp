<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="650" border="0" cellpadding="0" cellspacing="0" class="tableOutline" >
     <tr>
	<td align="center" CLASS="tabBackground" >

  <table width="640" border="0" cellpadding="5" cellspacing="0"
style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
		<tr>
	      <td colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
	    </tr>

	    <tr>
	<td width="630" align="left" colspan="2">
	  <!-- START TAB CONTENT -->
	  	<bean:define id="entite" name="rechercheRegroupement" property="entite" type="String"/>
	    <table width="620" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
      		</tr>

      		<tr>
      	 	<TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        		<b><bean:message key='i_en_cle_t'/></b>
        	</TD>
      		<TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
		        <bean:define id="entite" name='rechercheRegroupement' property="entite" type="String"/>
		        <myriap:select name='rechercheRegroupement' tabindex="1" property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
			   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		        </myriap:select>      		  
      		</TD>
	        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
	        <TD ALIGN="left">
	       <myriap:select size='1' name='rechercheRegroupement' property='site' style='HEIGHT: 20px; WIDTH: 200px' tabindex="7" onchange='doRefresh();' >
		      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
				valeurDiscriminant='<%=entite%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	        </myriap:select>        
			</TD>
      		<td>&nbsp;</td>
      	</tr>
 
      	<tr>
    	   <td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
    	   <td>
	    	   <bean:define id="secteur" name='rechercheRegroupement' property="secteur" type="String"/>
                  <myriap:select name='rechercheRegroupement' tabindex="2" property="secteur" size="1" style="HEIGHT: 20px; WIDTH: 160px" onchange='doRefresh();' >
                       <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                  </myriap:select>
           
            </td>
			 <TD ALIGN="right"><b><bean:message key='regroupement_t'/></b></TD>
	         <TD ALIGN="left">
			     <myriap:select size='1' name='rechercheRegroupement' property='regroupement' tabindex="5" style='HEIGHT: 20px; WIDTH: 200px;' >
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.RegroupementCleMultiListeCache" valeurDiscriminant='<%=secteur%>'/>
		         </myriap:select>
      		 </TD>
	  	    <td>&nbsp;</td>
      		</tr>

		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
	         <TD ALIGN="left">
		      <bean:define id="genre" name='rechercheRegroupement' property="genre" type="String"/>
		       <myriap:select  size='1' name='rechercheRegroupement' property='genre' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
			   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     	valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
						valeurDiscriminant='<%=entite%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		        </myriap:select>	         
	         </TD>
		    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
		    <TD ALIGN="left" >
		    	<bean:define id="secteur" name='rechercheRegroupement' property='secteur' type="String" />
		       <myriap:select size='1' name='rechercheRegroupement' tabindex="8" property='intervenant' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		        </myriap:select>&nbsp;		    
		    </TD>
	  	    <td>&nbsp;</td>

		</TR>
		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         <bean:define id="nature" name='rechercheRegroupement' property="nature" type="String"/>
		       <myriap:select size='1' name='rechercheRegroupement' property='nature' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
			         <cardex:optionTag
			         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
						valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
						valeurDiscriminant='<%=genre%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		        </myriap:select>
	         </TD>
            	<TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
      			<td>
                <bean:define id="site" name='rechercheRegroupement' property='site' type="String"/>
			        <myriap:select size='1' name='rechercheRegroupement' property='endroit' style='HEIGHT: 20px; WIDTH: 200px' tabindex="4" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				 	</myriap:select>
      			</td>
      			<td>&nbsp;</td>
		</TR>
      		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
	         <TD ALIGN="left">
				<bean:define id="type" name='rechercheRegroupement' property="type" type="String"/>
		        <myriap:select size='1' name='rechercheRegroupement' property='type' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' >
					<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
					valeurDiscriminant='<%=nature%>'
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		        </myriap:select>				
				</TD>
				
      			<td align="right" nowrap><b><bean:message key='remphor.entre.le'/><bean:message key='2.points'/></b></td>
      			<td nowrap>
      				<cardex:Date name='rechercheRegroupement' tabindex="11" property="dateCreationDu" calendrier="true" nomProchainChamp="dateCreationAu"/>
      			</td>
			<td>&nbsp;</td>
      		</TR>
      		<tr>
		 <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
	         <TD ALIGN="left">
		         <myriap:select size='1' name='rechercheRegroupement' property='categorie' tabindex="5" style='HEIGHT: 20px; WIDTH: 160px' >
					<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
						valeurDiscriminant='<%=type%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
				 </myriap:select>	         
      		 </TD>
		 <td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
  			<td nowrap>
  			  <cardex:Date name='rechercheRegroupement' tabindex="12" property="dateCreationAu" calendrier="true" />
  			</td>

			<td>&nbsp;</td>
      		</tr>
      		<tr>
      			<td align="center" colspan="5"><html:img page="/images/pixelnoir.gif" width="500" height="2" border="0" />
      			</td>
      		</tr>
      		<tr>
      			<td align="center" colspan="4"><b><bean:message key='choix_rapport_t'/></b>
                    <html:select name='rechercheRegroupement' property="choixRapport" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 250px">
			           	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
			           		valeurTableValeur='<%=GlobalConstants.TableValeur.RAPPORT_REGROUPEMENT%>' />
                    </html:select>
                	<cardex:button urlSecurite="/CritereRapportAffichagePDF" labelKey='cb_imprimer'  onclick='doPrint();' />
                </td>
		  	<td>&nbsp;              		
		  	</td>
      		</tr>
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
      		</tr>
      	</table>
      </td>
		</tr>

		<TR>
      <TD colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="2">

        <TABLE width="650" cellpadding="2" cellspacing="0" border="0">
          <TR>
            <TD ALIGN="left">
              <cardex:button labelKey='cb_clear' soumettre="/regroupement/search/default.do" />
            </TD>
            <TD ALIGN="left">&nbsp;
            </TD>
            <TD align="right">
              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>


    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="2"><html:img page="/images/0061CFpixel.gif" height="1" width="640" border="0" /></TD>
    </TR>


    <TR>
      <TD ALIGN="center" colspan="2">

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></TD>
    </TR>

	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->
