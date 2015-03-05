<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des consignations.
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
<%@ page import="com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration" %>
<%@ page import="com.lotoquebec.cardexCommun.presentation.taglib.cardex.SecurityDefineTag" %>

<SCRIPT language="JavaScript" type="text/javascript">
function doCrochetAppouve(){
      document.forms(0).nonApprouve.checked = false;
      document.forms(0).nonApprouve.value = "no";
//alert("non:" + document.forms(0).nonApprouve.value + "- oui:" + document.forms(0).approuve.value);
}
function doCrochetNonAppouve(){
      document.forms(0).approuve.checked = false;
      document.forms(0).approuve.value = "no";
//alert("non:" + document.forms(0).nonApprouve.value + " - oui:" + document.forms(0).approuve.value);
}
</SCRIPT>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="772" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
		<td align="center" CLASS="tabBackground" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");' >

		  <!-- START ORIGIN AND DATE -->
		  <table width="770" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
      		<tr>
		    <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
		  </tr>

		   <tr>
          			<td align="right"><b><bean:message key='i_en_cle_t'/></b></td>
          			<td>
          				<bean:define id="entite" name='rechercheConsignation' property="entite" type="String"/>
                         <myriap:select size='1' name='rechercheConsignation' property='entite' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
                  	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
								actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                          </myriap:select>
          			</td>
			      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
			      <TD ALIGN="left">
			      <bean:define id="siteOrigine" name='rechercheConsignation' property='siteOrigine' type="String"/>
			        <myriap:select size='1' name='rechercheConsignation' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
		   		         <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
								valeurDiscriminant='<%=entite%>'
								actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
						</myriap:select>
			      </TD>
            		</tr>

            		<tr> 
            			<td align="right" nowrap><b><bean:message key='i_tc_cle_t2'/></b></td>
            			<td>
                                   <myriap:select size='1' name='rechercheConsignation' property='typeConsignation' style='HEIGHT: 20px; WIDTH: 160px' >
                                       <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
											valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_CONSIGNATION%>'
											actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
                                    </myriap:select>
            			</td>
	       			    <td align="right"><b><bean:message key='devise_t'/></b></td>
	       			    <td>
	                       <myriap:select size='1' name='rechercheConsignation' property='devise' style='HEIGHT: 20px; WIDTH: 160px' >
	                           <cardex:optionTag classe='<%= GlobalConstants.CleListe.DEVISE %>'/>
	                        </myriap:select>
	                   </td>
					</TR>
            		<tr>
				      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
				      <TD ALIGN="left">
				      <bean:define id="genre" name='rechercheConsignation' property="genre" type="String"/>
				       <myriap:select size='1' name='rechercheConsignation' property='genre' style='HEIGHT: 20px; WIDTH: 160px' tabindex="4" onchange='doSoumettreRafraichir();' >
			   	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     			valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>' 
								valeurDiscriminant='<%=entite%>' 
								actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
				        </myriap:select>
				      </TD>
	       			<td align="right"><b><bean:message key='denomination_t'/></b></td>
	       			<td>
	                       <myriap:select size='1' name='rechercheConsignation' property='denomination' style='HEIGHT: 20px; WIDTH: 160px' >
	                           <cardex:optionTag classe='<%= GlobalConstants.CleListe.DENOMINATION %>'/>
	                        </myriap:select>
	               </td>
            		</tr>
					<TR>
				      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
				      <TD ALIGN="left">
				      	<bean:define id="nature" name='rechercheConsignation' property="nature" type="String"/>
				       <myriap:select size='1' name='rechercheConsignation' property='nature' style='HEIGHT: 20px; WIDTH: 160px' tabindex="5" onchange="viderListe('categorie');doSoumettreRafraichir();" >
				         <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
							valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
							valeurDiscriminant='<%=genre%>'
							actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />					        
				        </myriap:select>
				      </TD>
					  <td nowrap align="right"><b><bean:message key='no_serie_t'/></b></td>
					  <td>
						    <myriap:text name='rechercheConsignation' property='numeroSerie' style='HEIGHT: 20px; WIDTH: 160px' />
					  </td>
					</TR>
					<TR>	
				        <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
				        <TD ALIGN="left">
				        	<bean:define id="type" name='rechercheConsignation' property="type" type="String"/>
				       	 	<myriap:select size='1' name='rechercheConsignation' property='type' style='HEIGHT: 20px; WIDTH: 160px' tabindex="6" onchange="doSoumettreRafraichir();" >
								<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
									valeurDiscriminant='<%=nature%>'
									actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />					    	    
				        	</myriap:select>
				      	</TD>
            			<td align="right"><b><bean:message key='i_ma_cle_t'/></b></td>
            			<td>
            			   <cardex:AutoCompleter name='rechercheConsignation' property="marque" maxlength="50" 
					            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.MARQUE_CONSIGNATION_AUTO_COMPLETER %>'
					            height="150" width="150" nbrAmorce="2"/>
            			</td>
            		</tr>
					<tr>
					      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
					      <TD ALIGN="left" nowrap>
					         <myriap:select size='1' name='rechercheConsignation' property='categorie' tabindex="7" style='HEIGHT: 20px; WIDTH: 160px' >
								<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
									valeurDiscriminant='<%=type%>'
									actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>					  	      
					         </myriap:select>
					      </TD>
			       			<td align="right"><b><bean:message key='i_md_cle_t'/></b></td>
			       			<td>
	            			   <cardex:AutoCompleter name='rechercheConsignation' property="modele" maxlength="50" 
						            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.MODELE_CONSIGNATION_AUTO_COMPLETER %>'
						            height="150" width="150" nbrAmorce="2"/>
			               </td>
					</tr>
            		<tr>
            			<td align="right"><b><bean:message key='v_sv_intervenant_t'/></b></td>
            			<td>
                                <myriap:select size='1' name='rechercheConsignation' property='intervenant' style='HEIGHT: 20px; WIDTH: 300px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
                                  <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
									valeurDiscriminant='<%=siteOrigine%>'
									actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE %>'
									/>
                                </myriap:select>
                        </td>
            			<td align="right"><b><bean:message key='fournisseur_t'/></b></td>
            			<td>
	            			   <cardex:AutoCompleter name='rechercheConsignation' property="fournisseur" maxlength="50" 
						            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.FOURNISSEUR_CONSIGNATION_AUTO_COMPLETER %>'
						            height="150" width="150" nbrAmorce="2"/>
            			</td>
       		</tr>

      		<tr>
			    <TD align="right">
					<b><bean:message key='description_t'/></b>
			    </TD>
			    <TD>
					  <myriap:text name='rechercheConsignation' property='description' style='HEIGHT: 20px; WIDTH: 300px' />
		        </TD>
				<td align="right"><b><bean:message key='d_date_debut_ajout_t'/></b></td>
				<td nowrap>
				    <myriap:text name='rechercheConsignation' property='dateDebut' size='10' maxlength='10' onkeyup="doTraits(document.forms(0).dateDebut,'dateFin');changerChamp(this,'dateFin');" />
				     <myriap:link href="javascript:openCalendar('document.forms(0).dateDebut',document.forms(0).dateDebut.value);" onmousedown='setXY(event.x,event.y);' >
					<html:img page="/images/cal.gif" border="0" />
				     </myriap:link>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
				<td align="right"><b><bean:message key='et_le_t'/></b>
				</td>
				<td>
                     <myriap:text name='rechercheConsignation' property='dateFin' size='10' maxlength='10' onkeyup="doTraits(document.forms(0).dateFin,'dateDebut');changerChamp(this,'dateDebut');" />
       			     <myriap:link href="javascript:openCalendar('document.forms(0).dateFin',document.forms(0).dateFin.value);" onmousedown='setXY(event.x,event.y);' >
                                <html:img page="/images/cal.gif" border="0" />
                     </myriap:link>
				</td>
		</TR>
		<TR>
		  <TD align="right">
			<b><bean:message key='approuve_t'/></b>
		  </TD>
		  <TD>
			  <myriap:checkbox name='rechercheConsignation' property="approuve" onclick="doCrochetAppouve();" />
	      </TD>
		  <TD align="right">
			<b><bean:message key='non_approuve_t'/></b>
		  </TD>
		  <TD align="left">
			  <myriap:checkbox name='rechercheConsignation' property="nonApprouve" onclick="doCrochetNonAppouve();" />
		  </td>
		</TR>

    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="4"><html:img page="/images/0061CFpixel.gif" width="766" height="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" colspan="4">

      <!-- e"Sort by" options for Kind & nature listing -->
      <TABLE width="700 align="left" cellPadding="2" cellSpacing="0" border="0">
		<tr>
        <TD ALIGN="center" VALIGN="bottom" ><b><bean:message key='affichage_resultats'/></b>
           <html:select size='1' name='rechercheConsignation' property='listeResultat.plageResultats' >
              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
           </html:select>
		</TD>
		<TD ALIGN="left">
		   <cardex:button labelKey='cb_rechercher' soumettre="/consignation/search.do" />
		</TD>
		<TD ALIGN="right">
              <cardex:button labelKey='cb_clear' style='width: 120px; text-align: center;' soumettre="/consignation/search/reset/default.do" />
			  &nbsp;
              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
		</TD>
	   </TR>

      </TABLE>
      <!-- End Sort by -->

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
