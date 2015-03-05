<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des suivis.
Description : Écran 800 X 600 des suivis.
              Le résultat et la saisies de commentaires
              s'effectuent dans la même fenêtre.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/12 14:58:45 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

$Revision: 1.5 $, $Date: 2002/04/12 14:58:45 $, $Author: mlibersan $
Création HTML. 
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="840" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground">
		
<bean:define id="entite" name='rechercheSuivi' property="entite" type="String"/>

	<!-- POSITIONING TABLE -->
  <table width="840" cellpadding="5" cellspacing="0" border="0">
		<tr>
      <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
    </tr>

		<tr>
			<td width="21"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
			<td width="149">
			  <!-- EMITER -->
			  <table width="139" cellpadding="2" cellspacing="3" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
                        <td align="right"><b><bean:message key='emis_par_moi_t'/></b></td>
                        <td><myriap:radio name='rechercheSuivi' property='statutSuivi' value='<%=GlobalConstants.StatutEmissionSuivi.EMIS_PAR_MOI%>' />
                        </td>
      		</tr>

      		<tr>
                      <td align="right"><b><bean:message key='emis_pour_moi_t'/></b></td>
                      <td><myriap:radio name='rechercheSuivi' property='statutSuivi' value='<%=GlobalConstants.StatutEmissionSuivi.EMIS_POUR_MOI%>' />
                      </td>
      		</tr>

      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END EMITER -->
			</td>
 
			<td ><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>

			<td width="690" rowspan="2">

			  <!-- START ORIGIN AND DATE -->
			  <table width="630" cellpadding="2" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
            <td colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
          </tr>

           <tr>
      		  <td width="630" align="center" colspan="7">

      		    <!-- ORIGIN -->
      		    <table width="596" cellpadding="2" cellspacing="0" border="0" >
            		<tr>
            			<td align="left"><b><bean:message key='po_assigne_t'/></b></td>
            			<td align="left"><b><bean:message key='v_sv_intervenant_t'/></b></td>
            			<td>&nbsp;</td>
            		</tr>
            		<tr>
            			<td align="left">&nbsp;&nbsp;
                            <myriap:select size='1' name='rechercheSuivi' property='secteurAssigne' style='HEIGHT: 20px; WIDTH: 200px' onchange='doSoumettreRafraichir();' >
                                <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                             </myriap:select>
            			</td>
            			<td>&nbsp;&nbsp;
            				<bean:define id="secteurAssigne" name='rechercheSuivi' property='secteurAssigne' type="String" />
                            <myriap:select size='1' name='rechercheSuivi' property='intervenant' style='HEIGHT: 20px; WIDTH: 350px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                                <cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteurAssigne%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                             </myriap:select>
                         </td>
            			<td>&nbsp;</td>
            		</tr>

            		<tr>
            			<td align="left" nowrap><b><bean:message key='po_origine_t2'/></b></td>
            			<td align="left"><b><bean:message key='v_sv_demandeur_t'/></b></td>
            			<td>&nbsp;</td>
            		</tr>
            		<tr>
            			<td>&nbsp;&nbsp;
                         <myriap:select size='1' name='rechercheSuivi' property='secteurOrigine' style='HEIGHT: 20px; WIDTH: 200px' onchange='doSoumettreRafraichir();' >
                             <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR %>' />
                          </myriap:select>
            			</td>
            			<td>&nbsp;&nbsp;
            			<bean:define id="secteurOrigine" name='rechercheSuivi' property='secteurOrigine' type="String" />
                         <myriap:select size='1' name='rechercheSuivi' property='demandeur' style='HEIGHT: 20px; WIDTH: 350px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                             <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR %>' valeurDiscriminant='<%=secteurOrigine%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                          </myriap:select>
                        </td>
            			<td>&nbsp;</td>
            		</tr>

            	</table>
      	      <!-- END ORIGIN -->
        	  </td>
        	</tr>

        	<tr>
      			<td  width="630" height="15" colspan="7"><html:img page="/images/0061CFpixel.gif" width="630" height="1" border="0" /></td>
      		</tr>

      		<tr>
      		  <!-- DATE -->
      			<td width="70" align="right"><b><bean:message key='date_creation_debut_t'/></b></td>
      			<td width="100" nowrap>
      					<cardex:Date name='rechercheSuivi' property='dateEmisDebut' calendrier="true" nomProchainChamp="dateEmisFin"/>
      			</td>
      			<td width="70" align="right" ><b><bean:message key='prevu_du_t'/></b></td>
      			<td width="100" nowrap>
      					<cardex:Date name='rechercheSuivi' property='datePrevueDebut' calendrier="true" nomProchainChamp="datePrevueFin"/>
      			</td>
      			<td width="150" align="right"><b><bean:message key='complete_entre_le_t'/></b></td>
      			<td width="100" nowrap>
      					<cardex:Date name='rechercheSuivi' property='dateCompleteeDebut' calendrier="true" nomProchainChamp="dateCompleteeFin"/>
      			</td>

      			<td>&nbsp;</td>
      		</tr>

      		<tr>
      			<td width="70" align="right"><b><bean:message key='au_t'/></b></td>
      			<td width="100" nowrap>
						<cardex:Date name='rechercheSuivi' property='dateEmisFin' calendrier="true" nomProchainChamp="datePrevueDebut"/>      			
      			</td>

      			<td width="70" align="right"><b><bean:message key='au_t'/></b></td>
      			<td width="100" nowrap>
						<cardex:Date name='rechercheSuivi' property='datePrevueFin' calendrier="true" nomProchainChamp="dateCompleteeDebut"/>
      			</td>

      			<td width="150" align="right"><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      			<td width="100" nowrap>
						<cardex:Date name='rechercheSuivi' property='dateCompleteeFin' calendrier="true" nomProchainChamp="entite"/>      			
      			</td>
      			<td>&nbsp;</td>
      		</tr>
      		<!-- END DATE -->
      		<tr>
      			<td  width="630" height="15" colspan="7"><html:img page="/images/0061CFpixel.gif" width="630" height="1" border="0" /></td>
      		</tr>
      		<TR>
       			<td align="right" nowrap><b><bean:message key='i_tc_cle_t'/></b></td>
       			<td colspan="6">
                     <myriap:select name='rechercheSuivi' property="activite" tabindex="14" size="1" style="HEIGHT: 20px; WIDTH: 200px" onchange="doValeursDefaut(this.value);" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
      					<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_ACTIVITE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
   					 </myriap:select>&nbsp;
                </td>
			</TR>      		
      		<tr>
      			<td colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END ORIGIN AND DATE -->
      </td>
		</tr>

		<tr>
			<td width="21"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
			<td width="149">
			  <!-- APPROBATOR -->
			  <table width="139" cellpadding="2" cellspacing="3" border="0"  bgcolor="#D0D0D0" class="tableOutline">
      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>

      		<tr>
      			<td align="right"><b><bean:message key='approuve_t'/></b></td>
                        <td><myriap:radio name='rechercheSuivi' property='statutApprobation' value='<%=GlobalConstants.StatutApprobation.APPROUVE%>' />
                        </td>
      		</tr>

      		<tr>
                      <td align="right"><b><bean:message key='non_approuve_t'/></b></td>
                      <td><myriap:radio name='rechercheSuivi' property='statutApprobation' value='<%=GlobalConstants.StatutApprobation.NON_APPROUVE%>' />
                      </td>
      		</tr>

      		<tr>
      			<td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
      		</tr>
      	</table>
      	<!-- END APPROBATOR -->
			</td>
			<td>&nbsp;</td>
		</tr>

	<TR>
      <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="4"><html:img page="/images/0061CFpixel.gif" width="836" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="4">

     <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0" class="tabTitleSmallGrayed">
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheSuivi' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>
            <TD width="150">
              <cardex:button labelKey='cb_rechercher' soumettre="/suivi/search.do" />
            </TD>
            <TD width="150" ALIGN="left">
              <cardex:button labelKey='cb_clear' style='width: 120px; text-align: center;' soumettre="/suivi/search/reset/default.do" />
            </TD>

            <TD width="200" align="right">
              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>

	</table>
	<!-- END POSITIONING TABLE -->


    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->
