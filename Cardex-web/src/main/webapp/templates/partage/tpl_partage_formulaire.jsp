<%-- --------------------------------------------------------------------------
Use case    : Sélection des intervenants qui partagent le dossier.
Description : Sélection des intervenants, peu importe l'entité et le site.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/04/25 15:42:57 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.2 $, $Date: 2002/04/25 15:42:57 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

    <html:hidden name='partage' property='site' />
    <html:hidden name='partage' property='lien' />
    <html:hidden name='partage' property='lienSite' />
    <html:hidden name='partage' property='numeroDossier' />
    <html:hidden name='partage' property='type' />
    <html:hidden name='partage' property='categorie' />
    

      <!-- CONTENT -->
<table width="820" cellpadding="7" cellspacing="0" border="1"
    style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#999999', startColorstr='#ffffff', gradientType='0');" >
	<tr>
		<td colspan="2">
			
		<table width="800" cellpadding="5" cellspacing="0" border="0"
			bgcolor="#ECECEC" class="tableCarved">
			<tr>
				<td align="center" colspan="2">

				<table cellpadding="0" cellspacing="3" border="0"
					style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
					<tr>
						<td align="center" height="30"><b><bean:message key='v_do_numero_t' /></b>
						<bean:write name='partage' property="numeroDossier"/>
						</td>
						<td align="center" height="30"><b><bean:message key='i_ca_cle_t' /></b>
              				<bean:define id="type" name='partage' property='type' type="String" />
                          	<cardex:afficherValeurListeTag name="partage" property="categorie" valeurDiscriminant='<%=type%>' classe='<%=GlobalConstants.CleListe.CATEGORIE %>' />						
						</td>
					</tr>

					<tr>
					 <td align="center" height="30"><b><bean:message key='i_en_cle_t'/></b>
					 	<bean:define id="entite" name='partage' property="entite" type="String"/>
				        <myriap:select name='partage' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='document.forms(0).siteOrigine.selectedIndex=0;doSoumettreRafraichir();' >
		        	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
								actionSecurite='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
				        </myriap:select>
				      </td>
			      <TD ALIGN="center"><b><bean:message key='i_si_cle_t2'/></b>
			       		<myriap:select size='1' name='partage' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px' tabindex="2" onchange='doSoumettreRafraichir();' >
                                <cardex:optionTag 
			                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
									valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
									valeurDiscriminant='<%=entite%>'
									actionSecurite='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />							
			        	</myriap:select>
			      </TD>
			      </tr>
      		  <tr>
        			<td align="right"><b><bean:message key='genre.partage'/><bean:message key='2.points'/></b></td>
        			<td align="left">
					<logic:equal name='partage' property="genrePartage" value='OUV' >
                      <myriap:select name='partage' property="genrePartage" size="1" style="HEIGHT: 20px; WIDTH: 100px"  >
                        <option value='RES'><bean:message key='restreint'/></option>
                        <option value='OUV' selected='selected'><bean:message key='ouvert'/></option>
                      </myriap:select>
                     </logic:equal>
					<logic:notEqual name='partage' property="genrePartage" value='OUV' >
                      <myriap:select name='partage' property="genrePartage" size="1" style="HEIGHT: 20px; WIDTH: 100px"  >
                        <option value='RES' selected='selected'><bean:message key='restreint'/></option>
                        <option value='OUV'><bean:message key='ouvert'/></option>
                      </myriap:select>
                     </logic:notEqual>
        			</td>
      		  </tr>
				</table>

				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<table width="750" cellpadding="5" cellspacing="0" border="0"
					bgcolor="#ECECEC" class="tableCarved">

					<tr>
						<td align="center" class="errorHeader"><bean:message
							key='liste_intervenants_t' /></td>
						<td>&nbsp;</td>
						<td align="center" class="errorHeader"><bean:message
							key='intervenants_choisis' /></td>
					</tr>

					<tr>
						<td width="370" align="center" >
						<bean:define id="collectionIntervenantsActifParSite" name="partage" property="intervenantsActifParSite" type="java.util.Collection"/>
						<bean:define id="collectionIntervenants" name="partage" property="intervenants" type="java.util.Collection"/>
						<myriap:select name='partage'
							property="intervenantsChoisis" size="1"
							style="HEIGHT: 160px; WIDTH: 350px" multiple='true'>
							<myriap:switch selected='false'
								collection='collectionIntervenantsActifParSite' labelProperty='label' property='value'/>
						</myriap:select></td>

						<!-- Les boutons suivants doivent être "disabled" par défaut -->
						<td align="center"><INPUT type="button" name="select_all"
							value="&gt;&gt;" class="functionButton" onClick="selectAll();"
							disabled /><br>
						<br>
						<INPUT type="button" name="select_one" value="&gt;"
							class="functionButton" onClick="selectOne();" disabled /><br>
						<br>
						<INPUT type="button" name="deselect_one" value="&lt;"
							class="functionButton" onClick="deselectOne();" disabled /><br>
						<br>
						<INPUT type="button" name="deselect_all" value="&lt;&lt;"
							class="functionButton" onClick="deselectAll();" disabled /></td>

						<td align="center">
							<myriap:select name='partage' property="intervenantsChoisis" size="1" style="HEIGHT: 160px; WIDTH: 350px" multiple='true'>
								<myriap:switch selected='true' collection='collectionIntervenants' labelProperty='label' property='value'/>
						    </myriap:select>
						</td>
					</tr>

				</table>

				</td>
			</tr>

			<tr>
				<td>
			<tr>
				<td><!-- START BUTTONS SECTION --> &nbsp;<cardex:button 
					urlSecurite="/dossier/partage/update.do" labelKey='cb_ok'
					onclick='doOk();'
					style="color: #000000; width: 70px; text-align: center" />
				</td>

				<!-- Le bouton "Annuler" efface l'historique de la présente page. -->
				<td align="right"><cardex:button
					labelKey='cb_annuler' onclick='doCancel();'
					style="color: #000000; width: 60px; text-align: center" />&nbsp;
				</td>

			</tr>
		</table>
		</td>

	</tr>
</table>
<!-- END CONTENT -->
