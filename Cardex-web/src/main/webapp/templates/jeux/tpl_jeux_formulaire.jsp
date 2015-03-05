<%-- --------------------------------------------------------------------------
Use case    : Sélection des caractéristiques d'un individu.
Description : Sélection des caractéristiques d'un individu.
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
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

    <html:hidden name='jeux' property='site' />
    <html:hidden name='jeux' property='lien' />
    <html:hidden name='jeux' property='lienSite' />

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

	        			  <table width="300" cellpadding="2" cellspacing="2" border="0" style="border-style: solid; border-color: #AAAAAA; border-width: 1px;">
			              		<tr>
			              			<td align="right" height="30"><b><bean:message key='v_do_numero_t'/></b></td>
			              			<td align="left">&nbsp;
			                          	<bean:write name='jeux' property="numeroDossier" />
			                        </td>
			              		</tr>
			
			              		<tr>
			              			<td align="right" height="20"><b><bean:message key='i_ca_cle_t'/></b></td>
			              			<td align="left">&nbsp;
			              				<bean:define id="type" name='jeux' property='type' type="String" />
			                          <cardex:afficherValeurListeTag name="jeux" property="categorie" valeurDiscriminant='<%=type%>' classe='<%=GlobalConstants.CleListe.CATEGORIE %>' />
			                        </td>
			              		</tr>
			              		
			              		<tr>			              		
			              		 	<TD align="right"><b><bean:message key='type.jeu'/> : </b></TD>
        							<TD ALIGN="left">
							          <myriap:select size='1' name='jeux' property="typeJeu" style="HEIGHT: 20px; WIDTH: 160px" onkeypress="typeAhead(this, event);" onchange="doRafraichir();" >
							          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>'/>
							          </myriap:select> 
							        </TD>
			              		</tr>
		              	 </table>

        			</td>
      		  </tr>
			<tr>
				<td colspan="3">
					<tiles:insert page="/commun/doubleListe.jsp" flush="true">
						<tiles:put name="formName" value="jeux" />
						<tiles:put name="property" value="doubleListe" />
						<tiles:put name="strGaucheCol" value="st_listejeux_t" />
						<tiles:put name="strDroiteCol" value="st_jeuxchoisis_t" />
						<tiles:put name="listeGaucheLargeur" value="200" />
						<tiles:put name="listeDroiteLargeur" value="200" />
						<tiles:put name="listeHauteur" value="10" />
						<tiles:put name="idSelectMultiboxes" value="jeuxChoisis" />
					</tiles:insert>
				</td>
			</tr>
          </table>

          </td>
    		</tr>

    		<tr>
    		  <td >
        	  <!-- START BUTTONS SECTION -->
                    <cardex:button urlSecurite="/dossier/jeux/update.do" labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
                  </td>

          <!-- Le bouton "Annuler" efface l'historique de la présente page. -->
          <td align="right">
            <cardex:button labelKey='cb_annuler'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
          </td>

  		</tr>
  	</table>
    <!-- END CONTENT -->
