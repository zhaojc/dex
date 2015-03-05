<%-- --------------------------------------------------------------------------
Use case    : Sélection des particularités d'un véhicule
Description : Sélection des particularités d'un véhicule
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/04/25 17:41:32 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.2 $, $Date: 2002/04/25 17:41:32 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

    <html:hidden name='particularites' property='site' />
    <html:hidden name='particularites' property='lien' />
    <html:hidden name='particularites' property='lienSite' />

      <!-- CONTENT -->
      <table width="414" cellpadding="7" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
    			<td colspan="2">

    		  <table width="400" cellpadding="5" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableCarved">
        		<tr>
        			<td align="center" colspan="3"><html:img border="0" height="1" page="/images/blank.gif"  width="1" /></td>
      		  </tr>

        		<tr>
        			<td align="center" colspan="3">

        			  <table width="300" cellpadding="2" cellspacing="0" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
              		<tr>
              			<td align="right"><b><bean:message key='l_dossier_no_t'/></b></td>
              			<td width="150">
                          	<bean:write name='particularites' property="cle" />
                          	<html:hidden name='particularites' property="cle" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='v_ve_immatriculation_t'/></b></td>
              			<td width="150">
              				<bean:write name='particularites' property="immatriculation" />
              				<html:hidden name='particularites' property="immatriculation" />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              		<tr>
              			<td align="right"><b><bean:message key='i_md_cle_t'/></b></td>
              			<td width="150">
              				<bean:define id="marque" name="particularites" property="marque" type="String"/>
	              			<cardex:afficherValeurListeTag name="particularites" property="modele" classe='<%=GlobalConstants.CleListe.MODELE%>' valeurDiscriminant='<%=marque%>' />
                        </td>
              			<td>&nbsp;</td>
              		</tr>

              	</table>

        			</td>
      		  </tr>
			<tr>
				<td colspan="3">
					<tiles:insert page="/commun/doubleListe.jsp" flush="true">
						<tiles:put name="formName" value="particularites" />
						<tiles:put name="property" value="doubleListe" />
						<tiles:put name="strGaucheCol" value="particularites_disponibles_t" />
						<tiles:put name="strDroiteCol" value="particularites_choisies_t" />
						<tiles:put name="listeGaucheLargeur" value="200" />
						<tiles:put name="listeDroiteLargeur" value="200" />
						<tiles:put name="listeHauteur" value="10" />
						<tiles:put name="idSelectMultiboxes" value="particularitesChoisis" />
					</tiles:insert>
				</td>
			</tr>

          </table>

          </td>
    		</tr>

    		<tr>
    		  <td width="220">
                 <cardex:button urlSecurite="/sujet/caracteristiques/update.do" labelKey='cb_ok_audit'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
              </td>

          <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
          <td width="180" align="right">
            <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
          </td>

  		</tr>
  	</table>
    <!-- END CONTENT -->
