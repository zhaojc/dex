<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<%@ page import="com.lotoquebec.cardex.presentation.model.form.ValidationAdresseForm" %>

<tiles:useAttribute name="soumettreURLValider" id="soumettreURLValider" classname="String"/>
<tiles:useAttribute name="soumettreURLChoisir" id="soumettreURLChoisir" classname="String"/>

      <!-- CONTENT -->
      <TABLE width="614" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline"
      style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='0');">
    		<TR>
    			<TD align="center">

      		  <TABLE width="700" cellpadding="4" cellspacing="0" border="0" class="tableCarved">
              </TR>

              <tr>
          			<td colspan="4"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></td>
        	</tr>

			<TR>
          		<TD WIDTH="770" COLSPAN="4" HEIGHT="15"><b><bean:message key='choix.adresse'/></b></td>
          	</TR>
          	
          	<TR>
          		<TD WIDTH="770" COLSPAN="4" HEIGHT="15"><html:radio name="validationAdresse" property="choixAdresse" value='<%=ValidationAdresseForm.ADRESSE_SAISIE%>'></html:radio><b><bean:message key='choisir.adresse.saisie'/></b></td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
          		<td>
	            	<bean:write name='validationAdresse' property='adresseSaisie.numeroMunicipal' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.typeRueDescription' />
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.nomRue' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.pointCardinalDescription' />
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.uniteDescription' />
                </td>
          		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.numeroUnite' />
            	</td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
          		<td>
          			<bean:write name='validationAdresse' property='adresseSaisie.adressePostal' />
                </td>
          	</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td>
          				<bean:write name='validationAdresse' property='adresseSaisie.paysDescription' />
                    </td>
          		</TR>
             <TR>
        			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
        			<td>
  					<bean:write name='validationAdresse' property='adresseSaisie.provinceDescription' />
               	</td>
        		</TR>

        		<TR>
        			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
        			<td>
        				<bean:write name='validationAdresse' property='adresseSaisie.villeDescription' />
                   </td>
        		</TR>

        		<TR>
        			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
        			<td>
        				<bean:write name='validationAdresse' property='adresseSaisie.codePostal' />
                  </td>
        		</TR>
			<TR>
          		<TD WIDTH="770" ALIGN="center" COLSPAN="4" HEIGHT="15"><img src="<%=request.getContextPath()%>/images/0061CFpixel.gif" height="1" width="770" border="0"></td>
          	</TR>
          	
          	<TR>
          		<TD WIDTH="770" COLSPAN="4" HEIGHT="15"><html:radio name="validationAdresse" property="choixAdresse" value='<%=ValidationAdresseForm.ADRESSE_VALIDE%>'></html:radio><b><bean:message key='choisir.adresse.valide'/></b></td>
          	</TR>
          	
			<TR>
          		<TD WIDTH="770" COLSPAN="4"><b><bean:message key='resultat.validation.adresse'/><bean:message key='2.points'/></td>
          	</TR>
			<bean:define id="adresseValide" name='validationAdresse' property="adresseValide" type="com.lotoquebec.cardex.presentation.model.form.AdresseForm"/>
			<TR>
          		<TD WIDTH="770" COLSPAN="4"><font style="color: red;"><%=adresseValide.getMessage()%></font></td>
          	</TR>        	
        	
        	<logic:equal name='validationAdresse' property='adresseValide.afficherAdresse1' value="true">	  
			<TR>
          		<td align="right" nowrap><b><bean:message key='v_ad_adresse_t'/></b></td>
          		<td> 
	            	<bean:write name='validationAdresse' property='adresseValide.validationAdresse'/>
                </td>

        	<logic:equal name='validationAdresse' property='adresseValide.afficherAdresse2' value="true">
          		<td colspan="2"> 
	            	<bean:write name='validationAdresse' property='adresseValide.adresse2'/>
                </td>
			</logic:equal>
                
          	</TR>
			</logic:equal>
			
			<TR>
          		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
          		<td>
	            	<myriap:text name='validationAdresse' property='adresseValide.numeroMunicipal' tabindex="1" maxlength='10' style='HEIGHT: 20px; WIDTH: 100px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
          		<td>
                    <myriap:select name='validationAdresse' property='adresseValide.typeRue' tabindex="2" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
		             	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache" />
                    </myriap:select>
            	</td>
          	</TR>
           	
			<TR>
          		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
          		<td>
	            	<myriap:text name='validationAdresse' property='adresseValide.nomRue' tabindex="3" maxlength='30' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
          		<td>
                    <myriap:select name='validationAdresse' property='adresseValide.pointCardinal' tabindex="4" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache" />             
                    </myriap:select>          		
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
                    <myriap:select name='validationAdresse' property='adresseValide.unite' tabindex="5" size='1' style='HEIGHT: 20px; WIDTH: 100px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache" />
                    </myriap:select>	            	
                </td>
          		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
          		<td>
    	        	<myriap:text name='validationAdresse' property='adresseValide.numeroUnite' tabindex="6" maxlength='10' style='HEIGHT: 20px; WIDTH: 120px' />
            	</td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
          		<td>
	            	<myriap:text name='validationAdresse' property='adresseValide.adressePostal' tabindex="7" maxlength='40' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
          	</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td>
                        <myriap:select name='validationAdresse' property='adresseValide.pays' tabindex="8" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache" />
                        </myriap:select>
                    </td>
          		</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
          			<td>
		  				<bean:define id="pays" name='validationAdresse' property='adresseValide.pays' type="String"/>
                        <myriap:select name='validationAdresse' property='adresseValide.province' tabindex="9" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache" valeurDiscriminant='<%=pays%>'/>
                        </myriap:select>
                 	</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
          			<td>
  			  			<bean:define id="province" name='validationAdresse' property='adresseValide.province' type="String"/> 
                        <myriap:select name='validationAdresse' property='adresseValide.ville' tabindex="10" size='1' style='HEIGHT: 20px; WIDTH: 240px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
               				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache" valeurDiscriminant='<%=province%>'/>
                        </myriap:select>
                     </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
          			<td>
                       <myriap:text name='validationAdresse' property='adresseValide.codePostal' tabindex="11" maxlength='10'  style='HEIGHT: 20px; WIDTH: 120px' />
                    </td>
          		</TR>


          		<TR>
          			<TD colspan="4"><html:img page="/../../images/blank.gif" width="1" height="1"  border="0" /></TD>
        		  </TR>

          	</TABLE>

    		  </TD>
    		</TR>

      	<TR>
        	<TD align="center">

			<!-- BOTTOM BUTTONS -->
			<TABLE width="600" cellpadding="3" cellspacing="0" border="0">
			  <TR>
			    <TD width="300">
                	<cardex:button soumettre='<%=soumettreURLChoisir%>' labelKey='cb_choisir' style="color: #000000; width: 70px; text-align: center" />
                	<cardex:button soumettre='<%=soumettreURLValider%>' labelKey='cb_validation' style="color: #000000; width: 70px; text-align: center" />
                </TD>

                <TD width="300" align="right">
                    <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
                </TD>
              </TR>
            </TABLE>
            <!-- END BOTTOM BUTTONS -->

          </TD>
        </TR>
    	</TABLE>
      <!-- END CONTENT -->
<html:hidden name='validationAdresse' property='adresseValide.cle' />
<html:hidden name='validationAdresse' property='adresseValide.site' />
<html:hidden name='validationAdresse' property='adresseValide.lien' />
<html:hidden name='validationAdresse' property='adresseValide.lienSite' />
	    <jsp:include page='/commun/aide.jsp' flush="true"/>

<cardex:RetourFocus name="validationAdresse" />
