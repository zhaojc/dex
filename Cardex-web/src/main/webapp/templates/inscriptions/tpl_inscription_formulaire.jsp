<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une inscription.
Description : Ajout d'une inscription reliée à un contrat d'auto-exclusion
              ou autre.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/25 15:42:54 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/21 22:02:56 , Author: abruno-boucher
Création.

$Revision: 1.6 $, $Date: 2002/04/25 15:42:54 $, $Author: mlibersan $
Détection de la locale pour l'appel du fichier Assistant date.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<html:hidden name='inscription' property='lien' />
<html:hidden name='inscription' property='lienSite' />
<html:hidden name='inscription' property='dateRencontreInitiale' />
<html:hidden name='inscription' property='intervenantRencontreInitiale' />
<html:hidden name='inscription' property='dateRencontreFinale' />
<html:hidden name='inscription' property='intervenantRencontreFinale' />

<bean:define id="inscription" name="inscription" type="com.lotoquebec.cardex.presentation.model.form.InscriptionForm" />

<script type="text/javascript">
	function basculerPeriodeASuivre(){
		var periode = document.getElementsByName("periode")[0];
		if(periode.value == <%=GlobalConstants.Periode.A_SUIVRE_CONSEILLER%> ){ 
		    document.forms(0).aideInitiale.checked = true;
			document.forms(0).aideInitiale.disabled = true;
			document.forms(0).aideInitiale.value = "true";
		}else{
			document.forms(0).aideInitiale.checked = false;
			document.forms(0).aideInitiale.disabled = true;
			document.forms(0).aideInitiale.value = "false";
		}
	}
<%
	boolean disableListes = inscription.isTousSitesApplicables();
%>
	function disableListes(){
		
		if (<%=disableListes%>){
	      document.forms(0).select_all.disabled = true;
	      document.forms(0).select_one.disabled = true;
	      document.forms(0).deselect_all.disabled = true;
	      document.forms(0).deselect_one.disabled = true;
	      document.forms(0).sitesChoisis(0).disabled = true;	
	      document.forms(0).sitesChoisis(1).disabled = true;
		}
	}
</script>

<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

      <!-- CONTENT -->
      <table width="500" cellpadding="3" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
		<tr>
    			<td colspan="2" align="center">

    		  <table width="470" cellpadding="3" cellspacing="0" border="0" class="tableCarved"
    		  style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#D0D0D0', gradientType='0');>
        		<tr>
        			<td align="center" colspan="3"><html:img border="0" height="1" page="/images/blank.gif"  width="1" /></td>
      		  </tr>

        		<tr>
        			<td align="center" colspan="3">

        			  <table width="450" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #000000; border-width: 1px;">
              		<tr>
              			<td align="center" colspan="3"><b><bean:message key='i_en_cle_t'/>
              				<cardex:afficherValeurListeTag name='inscription' property='entite' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.AJOUT%>'/><b>
						</td>
              		</tr>

              	</table>

        			</td>
      		  </tr>

      		  <tr>
        			<td align="center" colspan="3">

        			  <table width="350" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_is_date_creation_t3'/></b></td>
              			<td width="150">
                                  <cardex:Date name='inscription' property="dateInscription" calendrier="true" nomProchainChamp="duree"/>
                                </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='v_is_duree_t2' /></b></td>
              			<td width="150">
                                  <myriap:text name='inscription' property="duree" maxlength="40" style="WIDTH: 140px; HEIGHT: 20px;" onfocus="document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);blur();" />
                                </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_date_debut_t'/></b></td>
              			<td width="150">
                         	<cardex:Date name='inscription' property="dateDebut" calendrier="true" nomProchainChamp="dateFin" onChange="document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);blur();"/>    
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_date_fin_t'/></b></td>
              			<td width="150">
              				<cardex:Date name='inscription' property="dateFin" calendrier="true" nomProchainChamp="periode" onChange="document.forms(0).duree.value = monthsUntil(document.forms(0).dateDebut.value, document.forms(0).dateFin.value);"/>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              		  <td width="135" align="right"><b><bean:message key='i_pe_cle_t'/></b></td>
              			<td width="190">
		                   <myriap:select name='inscription' property="periode" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 180px" styleClass="disabled" onchange="basculerPeriodeASuivre()">
		                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>'  valeurTableValeur='<%=GlobalConstants.TableValeur.PERIODE %>' actionSecurite='<%=actionSecurite%>'/>
		                   </myriap:select>&nbsp;
						</td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='i_st_cle_t'/></b></td>
              			<td width="150">
              				<cardex:afficherValeurListeTag name='inscription' property="statut" classe='<%=GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER%>'/>
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<!-- Les cases d'aide ne valent que pour les autoexclusions -->
		              <logic:equal name='dossier' property='categorie' value="<%= GlobalConstants.Categorie.AUTOEXCLUSION %>" >
		              <tr>
              			<td width="135" align="right"><b><bean:message key='aide_initiale_t'/></b></td>
              			<td width="150">
							<myriap:checkbox name='inscription' property="aideInitiale" disabled="true" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>
              		<tr>
              			<td width="135" align="right"><b><bean:message key='aide_immediate_t'/></b></td>
              			<td width="150">
              				<DIV style="position: absolute; visibility: hidden;">
								<html:radio name='inscription' property="aideImmediate" value="" />
              				</DIV>
							<html:radio name='inscription' property="aideImmediate" value="<%=GlobalConstants.BooleanString.TRUE%>" /><bean:message key='oui_t'/>
							<html:radio name='inscription' property="aideImmediate" value="<%=GlobalConstants.BooleanString.FALSE%>" /><bean:message key='non_t'/>
                        </td>
              			<td>&nbsp;</td>
              		</tr>
              	  </logic:equal>
              	</table>
		         <logic:notEqual name='dossier' property='categorie' value="<%= GlobalConstants.Categorie.AUTOEXCLUSION %>" >
		             <bean:write name='inscription' property="aideInitiale" />
		             <bean:write name='inscription' property="aideImmediate" />
		             <html:hidden name='inscription' property='aideInitiale' />
		             <html:hidden name='inscription' property='aideImmediate' />
		         </logic:notEqual>
        		</td>
      		  </tr>

        		<tr>
        			<td class="errorHeader"><bean:message key='st_availablesite_t'/></td>
        			<td>&nbsp;</td>
        			<td align="center" class="errorHeader"><bean:message key='st_selectionsite_t'/></td>
      		  </tr>
      		  <bean:define id="entite" name='dossier' property="entite" type="String"/>
      		  <bean:define id="collectionSiteApplicable" name="inscription" property="siteApplicable" type="java.util.Collection"/>
  		  <bean:define id="collectionSiteEspaceJeux" name="inscription" property="siteEspaceJeux" type="java.util.Collection"/>
			<logic:notEqual name='dossier' property='categorie' value="<%= GlobalConstants.Categorie.AE_ESPACEJEUX %>" >
      		  <tr>
        			<td>
                         <myriap:select name='inscription' property="sitesChoisis" size="1" style="HEIGHT: 160px; WIDTH: 180px" multiple='true' >
                            <myriap:switch  selected='false' collection='collectionSiteApplicable' labelProperty='label' property='value' />
                         </myriap:select>
        			</td>

        			<!-- Les boutons suivants doivent être "disabled" par défaut -->
        			<td align="center"><INPUT type="button" name="select_all" value="&gt;&gt;"
        			  class="functionButton" onClick="selectAll();"  disabled /><br><br>
        			  <INPUT type="button" name="select_one" value="&gt;"
        			  class="functionButton" onClick="selectOne();"  disabled /><br><br>
        			  <INPUT type="button" name="deselect_one" value="&lt;"
        			  class="functionButton" onClick="deselectOne();"  disabled /><br><br>
        			  <INPUT type="button" name="deselect_all" value="&lt;&lt;"
        			  class="functionButton" onClick="deselectAll();"  disabled /></td>


        			<td align="right">
                        <myriap:select name='inscription' property="sitesChoisis" size="1" style="HEIGHT: 160px; WIDTH: 180px" multiple='true' >
	 						<myriap:switch  selected='true' collection='collectionSiteApplicable' labelProperty='label' property='value' />
                        </myriap:select>&nbsp;
        			</td>
      		  </tr>
			</logic:notEqual>
			<!-- S'il s'agit d'un contrat d'Espacejeux, on force le site choisi à Espacejeux -->
			<logic:equal name='dossier' property='categorie' value="<%= GlobalConstants.Categorie.AE_ESPACEJEUX %>" >
      		  <tr>
        			<td align="right" colspan="3">
                         <myriap:select name='inscription' property="sitesChoisis" size="1" style="HEIGHT: 160px; WIDTH: 180px" multiple='true' >
                            <myriap:switch  selected='true' collection='collectionSiteEspaceJeux' labelProperty='label' property='value' />
                         </myriap:select>&nbsp;
        			</td>
      		  </tr>
			</logic:equal>
			
          </table>

          </td>
		</tr>

		<tr>
		  <td width="200">
    	  <!-- START BUTTONS SECTION -->
            <cardex:button urlSecurite="/dossier/inscription/add.do" labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
          </td>

          <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
          <td align="right">
            <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
          </td>

  		</tr>
  	</table>
<html:hidden name='dossier' property="categorie" />
  	
    <!-- END CONTENT -->
<script type="text/javascript">
	basculerPeriodeASuivre("aideinitiale");
</script>