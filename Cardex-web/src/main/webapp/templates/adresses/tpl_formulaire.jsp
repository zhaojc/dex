
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<SCRIPT LANGUAGE="JavaScript">
function doAuditChangement() {
	   var rapport = "<%= GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_ADRESSES %>";
	   var userCardex = '<bean:write name="<%= AuthenticationSubject.class.getName() %>" property="user.code" />';
	   var url = "<%=request.getContextPath()%>/AffichagePDFAudits?RAPPORT=" + rapport + "&UTILISATEUR=" + userCardex; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}
</SCRIPT>
<tiles:useAttribute name="soumettreURLValider" id="soumettreURLValider" classname="String"/>
<tiles:useAttribute name="soumettreURLRechercherValidation" id="soumettreURLRechercherValidation" classname="String"/>

      <!-- CONTENT -->
      <TABLE width="750" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline"
      style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='0');">
    		<TR>
    			<TD align="center">

      		  <TABLE width="750" cellpadding="4" cellspacing="0" border="0" class="tableCarved">

              <tr>
          			<td colspan="4"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></td>
        	</tr>
        	
        	<logic:equal name='adresse' property='afficherAdresse1' value="true">	  
			<TR>
          		<td align="right" nowrap><b><bean:message key='v_ad_adresse_t'/></b></td>
          		<td> 
	            	<bean:write name='adresse' property='adresse'/>
                </td>

        	<logic:equal name='adresse' property='afficherAdresse2' value="true">
          		<td colspan="2"> 
	            	<bean:write name='adresse' property='adresse2'/>
                </td>
			</logic:equal>
                
          	</TR>
			</logic:equal>

	<logic:equal name='adresse' property='indicateurRdd' value="true">			
			<TR>
          		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
          		<td>
          			<bean:write name='adresse' property='numeroMunicipal'/>&nbsp;
                </td>
          		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
          		<td>
          			<cardex:afficherValeurListeTag name='adresse' property='typeRue' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache"/>&nbsp;
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
          		<td>
          			<bean:write name='adresse' property='nomRue'/>&nbsp;
                </td>
          		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
          		<td>
          			<cardex:afficherValeurListeTag name='adresse' property='pointCardinal' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache"/>&nbsp;
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
          			<cardex:afficherValeurListeTag name='adresse' property='unite' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache"/>&nbsp;
                </td>
          		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
          		<td>
          			<bean:write name='adresse' property='numeroUnite'/>&nbsp;
            	</td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
          		<td>
	            	<bean:write name='adresse' property='adressePostal'/>&nbsp;
                </td>
                <td colspan="2" rowspan="8"><b>
                	<bean:message key='telephones'/></b> 
                	<TABLE cellpadding="2" cellspacing="0" border="1">
                	    <tr>
                	 	    <td>
				  				<cardex:afficherValeurListeTag name='adresse' property='typeUtilTelephone1' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>
			                    &nbsp;
			        	        <bean:write name='adresse' property='telephone1' />
			        	    </td>                
	                	</tr>
	                	<tr>
				  			<td>
				  				<cardex:afficherValeurListeTag name='adresse' property='periodeTelephone1' classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>'/>
			                </td>                
	                	</tr>
	                	<tr>
				  			<td>
				  				<cardex:afficherValeurListeTag name='adresse' property='typeUtilTelephone2' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>
			                    &nbsp;
			        	        <bean:write name='adresse' property='telephone2' />
		            	    </td>
	                	</tr>
	                	<tr>
				  			<td>
				  				<cardex:afficherValeurListeTag name='adresse' property='periodeTelephone2' classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>'/>
			                </td>                
	                	</tr>
	                	<tr>
				  			<td>
				  				<cardex:afficherValeurListeTag name='adresse' property='typeUtilTelephone3' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>
			                    &nbsp;
			        	        <bean:write name='adresse' property='telephone3' />
		            	    </td>                 	
	                	</tr>
	                	<tr>
				  			<td>
				  				<cardex:afficherValeurListeTag name='adresse' property='periodeTelephone3' classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>'/>
			                </td>                
	                	</tr>
                	</TABLE>
                </td>
          	</TR>
          	
              <TR>
          			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td colspan="3">
          				<cardex:afficherValeurListeTag name='adresse' property='pays' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache"/>&nbsp;
                    </td>
          		</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
          			<td colspan="3">
		  				<bean:define id="pays" name='adresse' property='pays' type="String"/>
                        <cardex:afficherValeurListeTag name='adresse' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>&nbsp;
                 	</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
          			<td colspan="3">
  			  			<bean:define id="province" name='adresse' property='province' type="String"/> 
						<cardex:afficherValeurListeTag name='adresse' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>&nbsp;
					</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
          			<td colspan="3">
                       <bean:write name='adresse' property='codePostal'/>&nbsp;
                    </td>
          		</TR>
          		<TR>
          			<td align="right" nowrap><b><bean:message key='adresse_electronique_1'/></b></td>
          			<td colspan="3">
                       <bean:write name='adresse' property='adresseElectronique1'/>&nbsp;
                    </td>
          		</TR>
          		
          		<TR>
          			<td align="right" nowrap><b><bean:message key='adresse_electronique_2'/></b></td>
          			<td colspan="3">
                       <bean:write  name='adresse' property='adresseElectronique2'/>&nbsp;
                    </td>
          		</TR>
          		<TR>
	          		<TD align="right"><b><bean:message key='i_st_cle_t'/></b></TD>
					<TD>	
          			   <cardex:afficherValeurListeTag name='adresse' property='statut' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache" valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.ADRESSE%>'/>&nbsp;
	                </TD>
	            </TR>                    
	</logic:equal>
	<logic:equal name='adresse' property='indicateurRdd' value="false">
			<TR>
          		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
          		<td>
	            	<myriap:text name='adresse' property='numeroMunicipal' tabindex="1" maxlength='10' style='HEIGHT: 20px; WIDTH: 100px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
          		<td>
                    <myriap:select name='adresse' property='typeRue' tabindex="2" size='1' style='HEIGHT: 20px; WIDTH: 80px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		             	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache" />
                    </myriap:select>
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
          		<td>
	            	<myriap:text name='adresse' property='nomRue' tabindex="3" maxlength='30' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
          		<td>
                    <myriap:select name='adresse' property='pointCardinal' tabindex="4" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache" />             
                    </myriap:select>          		
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
                    <myriap:select name='adresse' property='unite' tabindex="5" size='1' style='HEIGHT: 20px; WIDTH: 100px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache" />
                    </myriap:select>	            	
                </td>
          		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
          		<td>
    	        	<myriap:text name='adresse' property='numeroUnite' tabindex="6" maxlength='10' style='HEIGHT: 20px; WIDTH: 120px' />
            	</td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
          		<td>
	            	<myriap:text name='adresse' property='adressePostal' tabindex="7" maxlength='40' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
                <td colspan="2" rowspan="9" valign="top"><b>
                	<bean:message key='telephones'/></b>
                	<TABLE cellpadding="0" cellspacing="0" border="1">
                	<tr><td>
	                	<TABLE cellpadding="3" cellspacing="0">
	                	<tr>
				  			<td align="right">
			                    <myriap:select name='adresse' property='typeUtilTelephone1' tabindex="8" size='1' style='HEIGHT: 20px; WIDTH: 140px'>
									<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache" />
			                    </myriap:select>		  			
							</td>
							<td>
			        	        <myriap:text name='adresse' property='telephone1' tabindex="9" maxlength='14'  style='HEIGHT: 20px; WIDTH: 120px' />
			        	    </td>                
	                	</tr>
	                	<tr>
				  			<td align="right">
				  				<b><bean:message key='disponibilite'/><bean:message key='2.points'/></b>
				  			</td>
				  			<td>
			                    <myriap:select name='adresse' property='periodeTelephone1' tabindex="10" size='1' style='HEIGHT: 20px; WIDTH: 120px'>
									<cardex:optionTag classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>' />
			                    </myriap:select>		
			        	    </td>                
	                	</tr>
	                	<tr>
	                	    <td colspan="2">
								<html:img page="/../../images/0061CFpixel.gif" height="1" border="0" width="100%" />
							</td>
						</TR>
	                	<tr>
				  			<td align="right">
			                    <myriap:select name='adresse' property='typeUtilTelephone2' tabindex="11" size='1' style='HEIGHT: 20px; WIDTH: 140px'>
									<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache" />
			                    </myriap:select>		  			
			                </td>
			                <TD>
		        	          <myriap:text name='adresse' property='telephone2' tabindex="12" maxlength='14'  style='HEIGHT: 20px; WIDTH: 120px' />
		            	    </td>
	                	</tr>
	                	<tr>
				  			<td align="right">
				  				<b><bean:message key='disponibilite'/><bean:message key='2.points'/></b>
				  			</td>
				  			<td>
			                    <myriap:select name='adresse' property='periodeTelephone2' tabindex="13" size='1' style='HEIGHT: 20px; WIDTH: 120px'>
									<cardex:optionTag classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>' />
			                    </myriap:select>		
			        	    </td>                
	                	</tr>
	                	<tr>
	                	    <td colspan="2">
								<html:img page="/../../images/0061CFpixel.gif" height="1" border="0" width="100%" />
							</td>
						</TR>
						<tr>
				  			<td align="right">
			                    <myriap:select name='adresse' property='typeUtilTelephone3' tabindex="14" size='1' style='HEIGHT: 20px; WIDTH: 140px'>
									<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache" />
			                    </myriap:select>		  			
							</TD>
	  						<TD>
		        	          <myriap:text name='adresse' property='telephone3' tabindex="15" maxlength='14'  style='HEIGHT: 20px; WIDTH: 120px' />
		            	    </td>                 	
	                	</tr>
	                	<tr>
				  			<td align="right">
				  				<b><bean:message key='disponibilite'/><bean:message key='2.points'/></b>
				  			</td>
				  			<td>
			                    <myriap:select name='adresse' property='periodeTelephone3' tabindex="16" size='1' style='HEIGHT: 20px; WIDTH: 120px'>
									<cardex:optionTag classe="<%=GlobalConstants.CleListe.TABLE_VALEUR %>" 
									   valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE_TELEPHONE%>' />
			                    </myriap:select>		
			        	    </td>                
	                	</tr>
	                	</TABLE>
                	</td></tr>
                	</TABLE>
                </td>
          	</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td>
                        <myriap:select name='adresse' property='pays' tabindex="17" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache" />
                        </myriap:select>
                    </td>
          		</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
          			<td>
		  				<bean:define id="pays" name='adresse' property='pays' type="String"/>
                        <myriap:select name='adresse' property='province' tabindex="18" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache" valeurDiscriminant='<%=pays%>'/>
                        </myriap:select>
                 	</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
          			<td>
  			  			<bean:define id="province" name='adresse' property='province' type="String"/> 
                        <myriap:select name='adresse' property='ville' tabindex="19" size='1' style='HEIGHT: 20px; WIDTH: 240px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
               				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache" valeurDiscriminant='<%=province%>'/>
                        </myriap:select>
                     </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
          			<td>
                       <myriap:text name='adresse' property='codePostal' tabindex="20" maxlength='10'  style='HEIGHT: 20px; WIDTH: 120px' />
                    </td>
          		</TR>
          		<TR>
          			<td align="right" nowrap><b><bean:message key='adresse_electronique_1'/></b></td>
          			<td>
                       <myriap:text name='adresse' property='adresseElectronique1' tabindex="21" maxlength='100'  style='HEIGHT: 20px; WIDTH: 240px' />
                    </td>
          		</TR>
          		
          		<TR>
          			<td align="right" nowrap><b><bean:message key='adresse_electronique_2'/></b></td>
          			<td>
                       <myriap:text name='adresse' property='adresseElectronique2' tabindex="22" maxlength='100'  style='HEIGHT: 20px; WIDTH: 240px' />
                    </td>
          		</TR>
          		<TR>
	          		<TD align="right"><b><bean:message key='i_st_cle_t'/></b></TD>
					<TD>	
					    <myriap:select name='adresse' property='statut' tabindex="23" size='1' style='HEIGHT: 20px; WIDTH: 240px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
               				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache" valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.ADRESSE%>'/>
                        </myriap:select>
	                </TD>
	            </TR>                    
	</logic:equal>
          		
              <TR>
          			<td align="right" nowrap><b><bean:message key='v_ad_commentaire_t'/></b></td>
          			<td colspan="3">
                       <myriap:textarea name='adresse' property='commentaire' tabindex="24" rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 80) {this.value = this.value.substring(0,79);}' />
                    </td>
          		</TR>

          		<TR>
          		  <TD align="center" colspan="4">

          		    <TABLE width="700" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
                 		<TR>
                			<TD colspan="2"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></TD>
              		  </TR>

                 		<TR>
                 			<td align="right" nowrap><bean:message key='v_cree_par_t'/></td>
                 			<td>
							    <cardex:optionsRenderer name="adresse" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>
                	        </td>
                 			<td align="right" nowrap><bean:message key='v_tr_modifie_par_t2'/></td>
                 			<td>
							    <cardex:optionsRenderer name="adresse" property="modificateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>
    	                   </td>
                 		</TR>


                 		<TR>
                 			<td align="right" nowrap><bean:message key='d_date_creation_t'/></td>
                 			<td>
                            	<bean:write name='adresse' property='dateCreation'/>
                            </td>
                 			<td align="right" nowrap><bean:message key='d_ad_date_modification_t'/></td>
                 			<td>
                            	<bean:write name='adresse' property='dateModification'/>                                           
	                       </td>
                 		</TR>

                 		<TR>
                			<TD colspan="4"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></TD>
              		  </TR>

                  </TABLE>

          		  </TD>
          		</TR>

          	  <TR>
          			<TD colspan="4"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></TD>
       		  </TR>
       		  <TR>
        		<TD colspan="4">
				     <logic:equal name='adresse' property='indicateurRdd' value='true' >
				     	<STRONG><bean:message key='indicateur.rdd'/></STRONG>
				     </logic:equal>
				</TD>
       		  </TR>

          	</TABLE>

    		  </TD>
    		</TR>

      	<TR>
        	<TD align="center">

			<!-- BOTTOM BUTTONS -->
			<TABLE width="750" cellpadding="3" cellspacing="0" border="0">
			  <TR>
			    <TD width="375">
                	<cardex:button soumettre='<%=soumettreURLValider%>' labelKey='cb_ok' style="color: #000000; width: 70px; text-align: center" />
                	<cardex:button soumettre='<%=soumettreURLRechercherValidation%>' id="rechercheValidationAdresse" labelKey='cb_rechercher' style="color: #000000; width: 70px; text-align: center" />
                </TD>

                <TD width="375" align="right">
			        <cardex:button securityConstraint="cardex.adresse.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
                    <cardex:button labelKey='cb_fermer'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
                </TD>
              </TR>
            </TABLE>
            <!-- END BOTTOM BUTTONS -->

          </TD>
        </TR>
    	</TABLE>
      <!-- END CONTENT -->
<html:hidden name='adresse' property='cle' />
<html:hidden name='adresse' property='site' />
<html:hidden name='adresse' property='lien' />
<html:hidden name='adresse' property='lienSite' />
<html:hidden name='adresse' property='pays' />
<html:hidden name='adresse' property='province' />
	    <jsp:include page='/commun/aide.jsp' flush="true"/>

<cardex:RetourFocus name="adresse" />
