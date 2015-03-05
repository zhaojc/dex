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

<script type="text/javascript">
	function basculerPeriodeASuivre(nomRadio){
		var periode = document.getElementsByName("periode")[0];

		if (valeurRadioCheck(nomRadio) == "true"){
			periode.value = <%=GlobalConstants.Periode.A_SUIVRE%>;
			periode.disabled = true;
		}else{
			periode.disabled = false;
		}
	}

function doPrint() {
	   var fiche = "IS";
	   var cle = document.forms(0).cle.value;
	   var site = document.forms(0).site.value;
	   var url = "<%=request.getContextPath()%>/AffichagePDFFiches?FICHE=" + fiche + "&SITE=" + site + "&CLE=" + cle;
	   var rapport = "<%= GlobalConstants.ChoixRapport.IMPRESSION_INSCRIPTION %>";
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
}

</script>
		
      <!-- CONTENT -->
      <table width="414" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    			<td colspan="2" align="center" valign="top">

    		  <table width="400" cellpadding="2" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableCarved">
        		<tr>
        			<td align="center" colspan="3"><html:img border="0" height="1" page="/images/blank.gif"  width="1" /></td>
      		  </tr>

        		<tr>
        			<td align="center" colspan="3">

        			  <table width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #000000; border-width: 1px;">
              		<tr>
              			<td width="135" align="right"><b><bean:message key='i_en_cle_t'/></b></td>
              			<td width="150">
                          		<cardex:afficherValeurListeTag name='inscription' property='entite' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' actionSecurite='<%=GlobalConstants.ActionSecurite.MODIFICATION%>'/>
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              	</table>

        			</td>
      		  </tr>

      		  <tr>
        			<td align="center" colspan="3">

        			  <table width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_is_date_creation_t3'/></b></td>
              			<td width="150">
                        	<bean:write name='inscription' property="dateInscription" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='v_is_duree_t2' /></b></td>
              			<td width="150">
                          	<bean:write name='inscription' property="duree" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_date_debut_t'/></b></td>
              			<td width="150">
                             <bean:write name='inscription' property="dateDebut" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td width="135" align="right"><b><bean:message key='d_date_fin_t'/></b></td>
              			<td width="150">
                              <bean:write name='inscription' property="dateFin" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              		  <td width="135" align="right"><b><bean:message key='i_pe_cle_t'/></b></td>
              			<td width="150">
                          <cardex:afficherValeurListeTag name='inscription' property="periode" classe='<%=GlobalConstants.CleListe.PERIODE%>'/>
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
              		<tr>
              			<td width="135" align="right"><b><bean:message key='aide_initiale_t'/></b></td>
              			<td width="150">
              				<logic:equal name='inscription' property="aideInitiale" value="true">
              					<bean:message key="oui_t"/>
              				</logic:equal>
              				<logic:equal name='inscription' property="aideInitiale" value="false">
              					<bean:message key="non_t"/>
              				</logic:equal>
                        </td>
              			<td>&nbsp;</td>
              		</tr>
              		<tr>
              			<td width="135" align="right"><b><bean:message key='aide_immediate_t'/></b></td>
              			<td width="150">
              				<logic:equal name='inscription' property="aideImmediate" value="true">
              					<bean:message key="oui_t"/>
              				</logic:equal>
              				<logic:equal name='inscription' property="aideImmediate" value="false">
              					<bean:message key="non_t"/>
              				</logic:equal>
                        </td>
              			<td>&nbsp;</td>
              		</tr>
              	</table>

        			</td>
      		  </tr>

        		<tr>
        			<td align="center" width="100" class="errorHeader">&nbsp;</td>
        			<td align="center" width="100">&nbsp;</td>
        			<td align="center" width="100" class="errorHeader"><bean:message key='st_selectionsite_t'/></td>
      		  </tr>

      		  <tr>
        			<td align="center" colspan="2">
        			  <bean:define id="collectionSiteApplicable" name="inscription" property="siteApplicable" type="java.util.Collection"/>
      		  		  <bean:define id="collectionSiteEspaceJeux" name="inscription" property="siteEspaceJeux" type="java.util.Collection"/>
        			  <table width="180" cellpadding="2" cellspacing="0" border="0" bgcolor="#ECECEC" >
        			    <tr>
        			        <td>
        			           <logic:equal name='inscription' property='aideInitiale' value='true' >
        			        	        <b><bean:message key='aide_initiale_t'/></b>
        			           </logic:equal>
        			    	</td>
        			    </tr>
        			    <tr>
        			    	<TD align="center">
		                        <logic:equal name='inscription' property='aideInitiale' value='true' >
		                          <cardex:DateHeure name='inscription' property="dateRencontreInitiale" calendrier="true" />	                        	
		                        </logic:equal>
        			    	</TD>
        			    </tr>
        			    <tr>
        			    	<TD align="center">
		                        <logic:equal name='inscription' property='aideInitiale' value='true' >
							        <cardex:optionsRenderer name="inscription" property="intervenantRencontreInitiale" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>					                
								</logic:equal>
							</TD>
						</tr>
        			    <tr>
        			        <td>
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE_CONSEILLER %>" >
        			        	        <b><bean:message key='rencontre_finale'/></b>
        			           </logic:equal>
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE %>" >
        			        	        <b><bean:message key='rencontre_finale'/></b>
        			           </logic:equal>
        			    	</td>
        			    </tr>
        			    <tr>
        			    	<TD align="center">
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE_CONSEILLER %>" >
		                          <cardex:DateHeure name='inscription' property="dateRencontreFinale" calendrier="true" />
		                        </logic:equal>
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE %>" >
		                          <cardex:DateHeure name='inscription' property="dateRencontreFinale" calendrier="true" />
		                        </logic:equal>
		                    </TD>
	                    <TR>
				            <TD ALIGN="center" >
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE_CONSEILLER %>" >
							        <cardex:optionsRenderer name="inscription" property="intervenantRencontreFinale" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>					                
		                        </logic:equal>
		                        <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE %>" >
							        <cardex:optionsRenderer name="inscription" property="intervenantRencontreFinale" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>					                
		                        </logic:equal>
				            </TD>
				        </TR>
        			    	
        			    </table>
        			</td>
               <logic:notEqual name='dossier' property='categorie' value='<%=GlobalConstants.Categorie.AE_ESPACEJEUX%>'>
        			<td align="center" width="100">
                          <myriap:select name='inscription' property="sitesChoisis" size="1" style="HEIGHT: 160px; WIDTH: 130px" multiple='true' disabled='true'>
                             <myriap:switch  selected='true' collection='collectionSiteApplicable' labelProperty='label' property='value' />
                          </myriap:select>&nbsp;
        			</td>
               </logic:notEqual>
               <!-- Le site EspaceJeux ne fait pas partie des sites applicables, sauf pour les contrats d'Espacejeux. On sélectionne
                    donc une autre liste pour afficher le site -->
               <logic:equal name='dossier' property='categorie' value='<%=GlobalConstants.Categorie.AE_ESPACEJEUX%>'>
        			<td align="center" width="100">
                          <myriap:select name='inscription' property="sitesChoisis" size="1" style="HEIGHT: 160px; WIDTH: 130px" multiple='true' disabled='true'>
                             <myriap:switch  selected='true' collection='collectionSiteEspaceJeux' labelProperty='label' property='value' />
                          </myriap:select>&nbsp;
        			</td>
               </logic:equal>
      		  </tr>

          </table>

          </td>
		</tr>

		<tr>
		  <td width="100">&nbsp;
    	  <!-- START BUTTONS SECTION -->
            <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE %>" >
	            <cardex:button urlSecurite="/dossier/inscription/update.do" labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
			</logic:equal>
            <logic:equal name='inscription' property='periode' value="<%= GlobalConstants.Periode.A_SUIVRE_CONSEILLER %>" >
	            <cardex:button urlSecurite="/dossier/inscription/update.do" labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
			</logic:equal>
          </td>

          <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
          <td width="300" align="right">
		 	<cardex:button labelKey='cb_imprimer' securityConstraint='cardex.dossier.inscription.imprimer' style='width: 60px; text-align: center;' onclick='doPrint();' urlSecurite="/AffichagePDFFiches" />&nbsp;
            <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
          </td>

  		</tr>
  	</table>
	<html:hidden name='inscription' property="cle" />
	<html:hidden name='inscription' property="site" />

    <!-- END CONTENT -->
