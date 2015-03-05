<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>

<tiles:useAttribute name="soumettreURLRechercher" id="soumettreURLRechercher" classname="String"/>
<tiles:useAttribute name="soumettreURLChoisir" id="soumettreURLChoisir" classname="String"/>


      <!-- CONTENT -->
      <TABLE width="614" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline"
      style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#e6ebf4', startColorstr='#FFFFFF', gradientType='1');">
    		<TR>
    			<TD align="center">

      		  <TABLE width="700" cellpadding="4" cellspacing="0" border="0" class="tableCarved">

              <tr>
          			<td colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></td>
        	</tr>
         	
			<bean:define id="adresseSaisie" name='rechercheValidationAdresse' property="adresseSaisie" type="com.lotoquebec.cardex.presentation.model.form.AdresseForm"/>
        	
        	<logic:equal name='rechercheValidationAdresse' property='adresseSaisie.afficherAdresse1' value="true">	  
			<TR>
          		<td align="right" nowrap><b><bean:message key='v_ad_adresse_t'/></b></td>
          		<td> 
	            	<bean:write name='rechercheValidationAdresse' property='adresseSaisie.rechercheValidationAdresse'/>
                </td>

        	<logic:equal name='rechercheValidationAdresse' property='adresseSaisie.afficherAdresse2' value="true">
          		<td colspan="2"> 
	            	<bean:write name='rechercheValidationAdresse' property='adresseSaisie.adresse2'/>
                </td>
			</logic:equal>
                
          	</TR>
			</logic:equal>
			
			<TR>
          		<td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
          		<td>
	            	<myriap:text name='rechercheValidationAdresse' property='adresseSaisie.numeroMunicipal' tabindex="1" maxlength='10' style='HEIGHT: 20px; WIDTH: 100px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
          		<td>
                    <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.typeRue' tabindex="2" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
		             	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache" />
                    </myriap:select>
            	</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='rue'/></b></td>
          		<td>
	            	<myriap:text name='rechercheValidationAdresse' property='adresseSaisie.nomRue' tabindex="3" maxlength='30' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
          		<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
          		<td>
                    <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.pointCardinal' tabindex="4" size='1' style='HEIGHT: 20px; WIDTH: 80px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache" />             
                    </myriap:select>          		
            	</td>
          	</TR> 
          	
			<TR>
          		<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
                    <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.unite' tabindex="5" size='1' style='HEIGHT: 20px; WIDTH: 100px'>
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache" />
                    </myriap:select>	            	
                </td>
          		<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
          		<td>
    	        	<myriap:text name='rechercheValidationAdresse' property='adresseSaisie.numeroUnite' tabindex="6" maxlength='10' style='HEIGHT: 20px; WIDTH: 120px' />
            	</td>
          	</TR>

			<TR>
          		<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
          		<td>
	            	<myriap:text name='rechercheValidationAdresse' property='adresseSaisie.adressePostal' tabindex="7" maxlength='40' style='HEIGHT: 20px; WIDTH: 240px' />
                </td>
          	</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td>
                        <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.pays' tabindex="8" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache" />
                        </myriap:select>
                    </td>
          		</TR>
              <TR>
          			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
          			<td>
		  				<bean:define id="pays" name='rechercheValidationAdresse' property='adresseSaisie.pays' type="String"/>
                        <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.province' tabindex="9" size='1' style='HEIGHT: 20px; WIDTH: 240px' onchange='doRefresh();' onclick="choixListeAutomatique()" onblur="choixListeAutomatique()" onkeypress="typeAhead(this, event);">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache" valeurDiscriminant='<%=pays%>'/>
                        </myriap:select>
                 	</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
          			<td>
  			  			<bean:define id="province" name='rechercheValidationAdresse' property='adresseSaisie.province' type="String"/> 
                        <myriap:select name='rechercheValidationAdresse' property='adresseSaisie.ville' tabindex="10" size='1' style='HEIGHT: 20px; WIDTH: 240px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
               				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache" valeurDiscriminant='<%=province%>'/>
                        </myriap:select>
                     </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
          			<td>
                       <myriap:text name='rechercheValidationAdresse' property='adresseSaisie.codePostal' tabindex="11" maxlength='10'  style='HEIGHT: 20px; WIDTH: 120px' />
                    </td>
          		</TR>


          		<TR>
          			<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></TD>
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
                	<cardex:button urlSecurite='<%=soumettreURLChoisir%>' labelKey='cb_choisir'  onclick='doChoisir();' style="color: #000000; width: 70px; text-align: center" />
                	<cardex:button soumettre='<%=soumettreURLRechercher %>' labelKey='cb_rechercher' style="color: #000000; width: 70px; text-align: center" />                	
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
<html:hidden name='rechercheValidationAdresse' property='adresseSaisie.cle' />
<html:hidden name='rechercheValidationAdresse' property='adresseSaisie.site' />
<html:hidden name='rechercheValidationAdresse' property='adresseSaisie.lien' />
<html:hidden name='rechercheValidationAdresse' property='adresseSaisie.lienSite' />
<TABLE align="left" width="772" cellPadding="0" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772">
      	<TABLE align="left" width="772" cellPadding="2" cellSpacing="0" border="1" >
	<TR>
		<TD class="listTableHeader" WIDTH="770" COLSPAN="4" HEIGHT="15"><b><bean:message key='choix.adresse'/></b></td>
	</TR>
	<logic:iterate id='element' name='rechercheValidationAdresse' property='listeResultat.resultatComplet' indexId="index">
	<tr>
		<td><html:radio name="rechercheValidationAdresse" property="choixAdresse" value='<%=String.valueOf(index)%>' /></td>
		<td>
		<table>
		<TR>
		    <td align="right" nowrap><b><bean:message key='numero_municipal'/></b></td>
			<td>
				<logic:equal name="element" property="numeroMunicipalVariable" value="true">
					<bean:write name='element' property='numeroMunicipalMin' />=<
					<myriap:text name="rechercheValidationAdresse" property='<%="listeResultat.resultatComplet["+index+"].numeroMunicipal"%>' size="3" />=<
					<bean:write name='element' property='numeroMunicipalMax' />
				</logic:equal>
				<logic:equal name="element" property="numeroMunicipalVariable" value="false">
					<bean:write name='element' property='numeroMunicipal' />
				</logic:equal>				
			</td>
			<td align="right" nowrap><b><bean:message key='i_tr_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='typeRueDescription' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='rue'/></b></td>
			<td>
				<bean:write name='element' property='nomRue' />
			</td>
			<td align="right" nowrap><b><bean:message key='i_ct_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='pointCardinalDescription' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='i_tu_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='uniteDescription' />
			</td>
			<td align="right" nowrap><b><bean:message key='numero_unite'/></b></td>
			<td>
				<bean:write name='element' property='prefixNumeroUnite' />
				<logic:equal name="element" property="numeroUniteVariable" value="true">
					<bean:write name='element' property='numeroUniteMin' />
					<bean:write name='element' property='sufixNumeroUnite' />=<
					<myriap:text name="element" property="numeroUnite" size="3" />=<
					<bean:write name='element' property='numeroUniteMax' />
					<bean:write name='element' property='sufixNumeroUnite' />
				</logic:equal>
				<logic:equal name="element" property="numeroUniteVariable" value="false">
					<bean:write name='element' property='numeroUnite' />
				</logic:equal>
				
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='adresse_postale'/></b></td>
			<td>
				<bean:write name='element' property='adressePostal' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='i_pa_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='paysDescription' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='provinceDescription' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='l_vi_cle_t'/></b></td>
			<td>
				<bean:write name='element' property='villeDescription' />
		    </td>
		</TR>
		<TR>
			<td align="right" nowrap><b><bean:message key='v_ad_code_postal_t'/></b></td>
			<td>
				<bean:write name='element' property='codePostal' />
		    </td>
		</TR>
		</table>
		</td>
	</tr>
	</logic:iterate>
	</TABLE>
	</TD></TR>
</TABLE>
<jsp:include page='/commun/aide.jsp' flush="true"/>
<cardex:RetourFocus name="rechercheValidationAdresse" />
