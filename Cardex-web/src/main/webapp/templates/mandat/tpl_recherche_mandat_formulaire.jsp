<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des mandats.
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

<SCRIPT language="JavaScript" type="text/javascript">
function doCrochetAppouve(){
      document.forms(0).nonApprouve.checked = false;
      //document.forms(0).nonApprouve.value = "false";
//alert("non:" + document.forms(0).nonApprouve.value + "- oui:" + document.forms(0).approuve.value);
}
function doCrochetNonAppouve(){
      document.forms(0).approuve.checked = false;
      //document.forms(0).approuve.value = "false";
//alert("non:" + document.forms(0).nonApprouve.value + " - oui:" + document.forms(0).approuve.value);
}
</SCRIPT>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="650" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");' >

		  <!-- START ORIGIN AND DATE -->
		  <table width="650" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
      		<tr>
		    <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
		  </tr>

		   <tr>
          			<td align="right"><b><bean:message key='i_en_cle_t'/></b></td>
          			<td>
   						<html:hidden  name="recherchePSUMandat" property="entite" />          				<bean:define id="entite" name='recherchePSUMandat' property="entite" type="String"/>
                        <myriap:select size='1' name='recherchePSUMandat' property='entite' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
                              <cardex:optionTag 
		                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
								actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
                         </myriap:select>
          			</td>
			      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
			      <TD ALIGN="left">
			      <bean:define id="siteOrigine" name='recherchePSUMandat' property='siteOrigine' type="String"/>
			       <myriap:select size='1' name='recherchePSUMandat' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
                      <cardex:optionTag 
                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
						valeurDiscriminant='<%=entite%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		  		  </myriap:select>
			      </TD>
            		</tr>
 
            		<tr>
            			<td align="right"><b><bean:message key='mandat_t2'/></b></td>
            			<td>
							<myriap:text name='recherchePSUMandat' property='numeroMandat' style='HEIGHT: 20px; WIDTH: 120px' />
						</td>
            			<td align="right"><b><bean:message key='v_sv_intervenant_t'/></b></td>
            			<td>
                             <myriap:select size='1' name='recherchePSUMandat' property='intervenant' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                                <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
									valeurDiscriminant='<%=siteOrigine%>'
									actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE %>'
									/>
                             </myriap:select>
                       </td>
            		</tr>
		<TR>
		        <td align="right" nowrap><b><bean:message key='du_t'/></b></td>
	      		<td nowrap>
		              <cardex:DateHeure name='recherchePSUMandat' property="dateDebut" calendrier="true" />
	      		</td>
        		<td align="right" nowrap><b><bean:message key='date_creation_fin_t'/></b></td>
	      		<td nowrap>
		              <cardex:DateHeure name='recherchePSUMandat' property="dateFin" calendrier="true" />
	      		</td>
		</TR>
		<TR>
		  <TD align="right">
			<b><bean:message key='approuve_t'/></b>
		  </TD>
		  <TD>
			  <myriap:checkbox name='recherchePSUMandat' property="approuve" onclick="doCrochetAppouve();" />
	          </TD>
		  <TD align="right">
			<b><bean:message key='non_approuve_t'/></b>
		  </TD>
		  <TD>
			  <myriap:checkbox name='recherchePSUMandat' property="nonApprouve" onclick="doCrochetNonAppouve();" />
		  </td>
		</TR>
	     <TR>
	      <TD ALIGN="center" HEIGHT="15" colspan="4"><html:img page="/images/0061CFpixel.gif" width="630" height="1" border="0" /></TD>
	    </TR>
    <TR>
      <TD ALIGN="center" valign="top" colspan="4">
       <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0">
		  <tr>
	        <TD ALIGN="left" ><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='recherchePSUMandat' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>&nbsp;
                <cardex:button labelKey='cb_rechercher' style='width: 75px; text-align: center;' soumettre="/mandat/search.do" />
			</TD>
			<TD align="center">
                <cardex:button windowOpenLocation="/mandat/create.do" labelKey='cb_ajouter' style='width: 75px; text-align: center;' />
			</TD>
			<TD align="right">
                <cardex:button labelKey='cb_fermer'  style='width: 75px; text-align: center;' onclick='doClose();' />&nbsp;
	        </TD>
	      </TR>
	    </TABLE>
       </TD>
    </TR>
    <TR>
      <TD ALIGN="center" colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
   </table>
	<!-- END POSITIONING TABLE -->


    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->
