<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des narrations.
Description : Écran 800 X 600 de recherche.
              Le visionnement des narration s'effectue égalemement dans
              la même fenêtre.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/22 20:12:22 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/04/22 20:12:22 $, $Author: fguerin $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="772" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground">

  <table width="770" border="0" cellpadding="5" cellspacing="0">
		<tr>
      <td colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
    </tr>

		<tr>
			<td width="250" align="right" >
			  <!-- APPROBATOR -->
                          <table width="200" cellpadding="2" cellspacing="3" border="0"  bgcolor="#D0D0D0" class="tableOutline">
                              <tr>
                                      <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                                      <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                              </tr>

                              <tr align='right'>
                                      <td>
                                        <b><bean:message key='approuvés_par_moi_t'/></b>
                                      </td>
                                      <td align="left"> 
                                        <myriap:radio name='rechercheNarration' property='statutApprobation' value='<%=GlobalConstants.StatutApprobation.APPROUVE_PAR_MOI%>' />
                                       </td>
                              </tr>

                              <tr>
                                      <td align='right'>
                                        <b><bean:message key='approuve_t'/></b>
                                      </td>
                                      <td>
                                        <myriap:radio name='rechercheNarration' property='statutApprobation' value='<%=GlobalConstants.StatutApprobation.APPROUVE%>' />
                                     </td>
                              </tr>

                              <tr>
                                      <td align='right'>
                                        <b><bean:message key='non_approuve_t'/></b>
                                      </td>
                                      <td>
                                        <myriap:radio name='rechercheNarration' property='statutApprobation' value='<%=GlobalConstants.StatutApprobation.NON_APPROUVE%>' />
                                      </td>
                              </tr>

                              <tr>
                                      <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                                      <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                              </tr>
                      </table>
      	<!-- END APPROBATOR -->
			</td>

			<td width="520" align="left">

			  <!-- START TAB CONTENT -->
			  <table width="450" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
      			<td align="right"><b><bean:message key='v_secteur_assigne_t'/></b></td>
      			<td colspan="2">
                    <myriap:select size='1' name='rechercheNarration' property='secteur' style='HEIGHT: 20px; WIDTH: 200px' onchange='doSoumettreRafraichir();' >
                        <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                     </myriap:select>
      			</td>
      			<td colspan="2">&nbsp;</td>
      		</tr>
      			<td align="right"><b><bean:message key='v_sv_intervenant_t'/></b></td>
      			<td colspan="3">
      				<bean:define id="secteur" name='rechercheNarration' property='secteur' type="String" />
                    <myriap:select size='1' name='rechercheNarration' property='intervenant' style='HEIGHT: 20px; WIDTH: 400px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                        <cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                     </myriap:select>
                 </td>
      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td align="right" nowrap><b><bean:message key='d_date_debut_ajout_t'/></b></td>
      			<td nowrap>
                          <myriap:text name='rechercheNarration' property="dateCreationDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateCreationDebut,'dateCreationFin');changerChamp(this,'dateCreationFin');" />
                          <myriap:link href="javascript:openCalendar('document.forms(0).dateCreationDebut',document.forms(0).dateCreationDebut.value);" onmousedown="setXY(event.x, event.y);" >
                            <html:img page="/images/cal.gif" border="0" />
                          </myriap:link>
      			</td>
      			<td align="right" nowrap><b><bean:message key='approuve_entre_le_t'/></b></td>
      			<td nowrap>
                          <myriap:text name='rechercheNarration' property="dateApprobationDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateApprobationDebut,'dateApprobationFin');changerChamp(this,'dateApprobationFin');" />
                          <myriap:link href="javascript:openCalendar('document.forms(0).dateApprobationDebut',document.forms(0).dateApprobationDebut.value);"  onmousedown="setXY(event.x, event.y);" >
                            <html:img page="/images/cal.gif" border="0" />
                          </myriap:link>
      			</td>

      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td nowrap>
                          <myriap:text name='rechercheNarration' property="dateCreationFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateCreationFin,'dateCreationDebut');changerChamp(this,'dateCreationDebut');" />
                          <myriap:link href="javascript:openCalendar('document.forms(0).dateCreationFin',document.forms(0).dateCreationFin.value);"  onmousedown="setXY(event.x, event.y);" >
                            <html:img page="/images/cal.gif" border="0" />
                          </myriap:link>
      			</td>

      			<td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td nowrap>
                          <myriap:text name='rechercheNarration' property="dateApprobationFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateApprobationFin,'dateApprobationDebut');changerChamp(this,'dateApprobationDebut');" />
                          <myriap:link href="javascript:openCalendar('document.forms(0).dateApprobationFin',document.forms(0).dateApprobationFin.value);"  onmousedown="setXY(event.x, event.y);"  >
                            <html:img page="/images/cal.gif" border="0" />
                          </myriap:link>
      			</td>
      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      </td>
		</tr>

		<TR>
      <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="2"><html:img page="/images/0061CFpixel.gif" width="766" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="2">

     <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0" class="tabTitleSmallGrayed">
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheNarration' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>&nbsp;
              <cardex:button labelKey='cb_rechercher' soumettre="/approbation/search.do" />
            </TD>
            <TD width="200" ALIGN="left">
              <cardex:button labelKey='cb_clear' style='width: 120px; text-align: center;' soumettre="/approbation/search/reset/default.do" />
            </TD>
            <TD width="200" align="right">
              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>

	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->
