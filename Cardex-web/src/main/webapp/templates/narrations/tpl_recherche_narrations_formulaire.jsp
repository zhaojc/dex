<%-- --------------------------------------------------------------------------
Use case    : Écran de recherche des narrations.
Description : Écran 800 X 600 de recherche.
              Le visionnement des narration s'effectue égalemement dans
              la même fenêtre.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/22 17:42:36 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/04/22 17:42:36 $, $Author: mlibersan $
Création.

$Revision: 1.6 $, $Date: 2002/04/22 17:42:36 $, $Author: mlibersan $
Mise à jour nouvel Assistant date-heure (incorporé dans la page).
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.action.Action" %>

        <!-- This table for outline purposes only -->
        <table width="770" border="0" cellpadding="0" cellspacing="0" class="tableOutline">
	<tr>
	    <td align="center" CLASS="tabBackground">

            <table width="770" border="0" cellpadding="5" cellspacing="0"
            style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#cbd8e9', gradientType='0');">
            <tr>
            <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
        </tr>

        <tr>
                <td align="center">

                  <!-- START BUTTONS SECTION -->
                  <table width="760" cellpadding="2" cellspacing="0" border="0" class="tableOutline"
            			style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#D0D0D0', gradientType='0');">
                  
        <tr>
                <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
        </tr>

      		<tr>
      	 	<TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        		<b><bean:message key='i_en_cle_t'/></b>
        	</TD>
      		<TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
      		  <bean:define id="entite" name='rechercheNarration' property="entite" type="String"/>
      		  <myriap:select name='rechercheNarration' tabindex="1" property='entite' size='1' style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
		   	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
      		  </myriap:select>
      		</TD>
	        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
	        <TD ALIGN="left">
		    <myriap:select tabindex="7" size='1' name='rechercheNarration' property='site' style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
	            <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
					valeurDiscriminant='<%=entite%>'
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
            </myriap:select>
		    </TD>
		</tr>
 
      		<tr>
        	   <td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
        	   <td>
                       <myriap:select name='rechercheNarration' tabindex="2" property="secteur" size="1" style="HEIGHT: 20px; WIDTH: 200px" onchange='doSoumettreRafraichir();' >
                               <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                       </myriap:select>
                    </td>
		    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
		    <TD ALIGN="left" >
		    	<bean:define id="secteur" name='rechercheNarration' property='secteur' type="String" />
		       <myriap:select size='1' name='rechercheNarration' tabindex="8" property='intervenant' style='HEIGHT: 20px; WIDTH: 350px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		        </myriap:select>&nbsp;
		    </TD>
      		</tr>

      		<tr>
      			<TD ALIGN="right" ><b><bean:message key='i_or_cle_t'/></b></TD>
      			<TD>
                   <bean:define id="site" name='rechercheNarration' property='site' type="String"/>
			        <myriap:select size='1' name='rechercheNarration' property='endroit' style='HEIGHT: 20px; WIDTH: 200px' tabindex="3" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				 	</myriap:select>
      			</TD>
		        <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
		        <TD>
	                <myriap:select name='rechercheNarration' property="localisation" tabindex="9" size="1" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
	                </myriap:select>&nbsp;
   		        </TD>
      		</tr>
		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
	         <TD ALIGN="left">
	             <bean:define id="genre" name='rechercheNarration' property="genre" type="String"/>
	             <myriap:select size='1' name='rechercheNarration' property='genre' tabindex="4" style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
       	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     	valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
						valeurDiscriminant='<%=entite%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	             </myriap:select>
	         </TD>
		      <TD ALIGN="right"><b><bean:message key='origine_t'/></b></td>
		      <td nowrap>
		      <myriap:select size='1' name='rechercheNarration'  tabindex="13" property='origine' style='HEIGHT: 20px; WIDTH: 140px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			     <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
			  </myriap:select>
		      </TD>
		</TR>
		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="nature" name='rechercheNarration' property="nature" type="String"/>
	             <myriap:select size='1' name='rechercheNarration' property='nature' tabindex="4" style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
       		         <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
						valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
						valeurDiscriminant='<%=genre%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	             </myriap:select>
	         </TD>
                <td align="right" nowrap><b><bean:message key='d_date_debut_ajout_t'/></b></td>
                <td nowrap>

                  <myriap:text name='rechercheNarration' property="dateCreationDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateCreationDebut,'dateCreationFin');changerChamp(this,'dateCreationFin');" />
                  <myriap:link href="javascript:openCalendar('document.forms(0).dateCreationDebut',document.forms(0).dateCreationDebut.value);"  onmousedown="setXY(event.x, event.y);">
                    <html:img page="/images/cal.gif" border="0" />
                  </myriap:link>
                </td>
		</TR>
      	<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="type" name='rechercheNarration' property="type" type="String"/>
	             <myriap:select size='1' name='rechercheNarration' property='type' tabindex="4" style='HEIGHT: 20px; WIDTH: 140px' onchange='doSoumettreRafraichir();' >
 		        	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
						valeurDiscriminant='<%=nature%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	             </myriap:select>
                <td align="right" nowrap><b><bean:message key='et_le_t'/></b></td>
                <td nowrap>

                  <myriap:text name='rechercheNarration' property="dateCreationFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateCreationFin,'dateCreationDebut');changerChamp(this,'dateCreationDebut');" />
                  <myriap:link href="javascript:openCalendar('document.forms(0).dateCreationFin',document.forms(0).dateCreationFin.value);" onmousedown="setXY(event.x, event.y);" >
                    <html:img page="/images/cal.gif" border="0" />
                  </myriap:link>
                </td>
      	</TR>
      	<tr>
		 	 <TD ALIGN="right" valign="top"><html:img page="/images/blank.gif" width="1" height="2" border="0" /><br><b><bean:message key='i_ca_cle_t'/></b></TD>
	         <TD ALIGN="left" valign="top">
			     <myriap:select size='1' name='rechercheNarration' property='categorie' tabindex="5" style='HEIGHT: 20px; WIDTH: 140px' >
	  	       		 <cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE %>' 
						valeurDiscriminant='<%=type%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		         </myriap:select>
      		 </TD>
		 	 <TD ALIGN="right" valign="top"><b><bean:message key='c_do_fonde_t'/></b></TD>
			 <TD colspan="2">			 
			     <myriap:select size='1' name='rechercheNarration' property='fonde' tabindex="5" style='HEIGHT: 20px; WIDTH: 140px' >
	  	       		 <cardex:optionTag classe='<%=GlobalConstants.CleListe.FONDE %>' 
						valeurDiscriminant='<%=type%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		         </myriap:select>
			</TD>
		</TR>
		<tr>
		  <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
		</tr>
		<TR>			
		  <td colspan="4" nowrap>
			<table><tr><td valign="middle" align="center" width="330">
                <table width="300" cellpadding="2" cellspacing="2" border="0"  class="tableOutline"
                	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#D0D0D0', gradientType='0');">
		              <tr>
		                <td align="right">
		                   <b><bean:message key='mots_cles'/></b>
		                </td>
		                <td>
		                  <myriap:text name='rechercheNarration' property="motCle1" maxlength="60" style="HEIGHT: 20px; WIDTH: 200px" />
		                </td>
		              </tr>
		              <tr>
		                	<td>&nbsp;
		                	</td>
		                	<td>
		                	  <myriap:text name='rechercheNarration' property="motCle2" maxlength="60" style="HEIGHT: 20px; WIDTH: 200px" />
		                	</td>
		              </tr>
		              <tr>
		                <td>&nbsp;
		                </td>
		                <td>
		                  <myriap:text name='rechercheNarration' property="motCle3" maxlength="60" style="HEIGHT: 20px; WIDTH: 200px" />
		                </td>
		              </tr>
		              <tr>
		                <td colspan="2" align="center">
			               <b><bean:message key='mots_cles2'/></b>
			          	</td>
			          </tr>
       	      </table>
       	    </td>
       	    <td valign="middle" align="center" width="210">
              <table width="180" cellpadding="2" cellspacing="0" border="0" class="tableOutline"
                 		style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#D0D0D0', gradientType='0');">
                     <tr>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                     </tr>

                     <tr>
                             <td align="right">
                               <b><bean:message key='choix_tous'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='typeRecherche' value='<%=GlobalConstants.TypeRecherche.TOUS%>' />
                              </td>
                     </tr>

                     <tr>
                             <td align='right'>
                               <b><bean:message key='choix_un'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='typeRecherche' value='<%=GlobalConstants.TypeRecherche.ANY%>' />
                            </td>
                     </tr>

                     <tr>
                             <td align='right'>
                               <b><bean:message key='choix_derives'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='typeRecherche' value='<%=GlobalConstants.TypeRecherche.DERIVES%>' />
                             </td>
                     </tr>

                     <tr>
                             <td align='right'>
                               <b><bean:message key='choix_lettre'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='typeRecherche' value='<%=GlobalConstants.TypeRecherche.LETTRES%>' />
                             </td>
                     </tr>

                     <tr>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                     </tr>
             </table>
           </td>
           <td valign="middle" align="center" width="210">
             <table width="180" cellpadding="2" cellspacing="0" border="0" class="tableOutline"
                 		style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ffffff', startColorstr='#D0D0D0', gradientType='0');">
                     <tr>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" />
                               <b><bean:message key='tri.par.date'/><bean:message key='2.points'/></b>
                             </td>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                     </tr>
                     <tr>
                             <td align="right">
                               <b><bean:message key='tri.par.date.debut.dossier'/><bean:message key='2.points'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='ordreAffichage' value='<%=GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_DEBUT_DOSSIER%>' />
                              </td>
                     </tr>
                     <tr>
                             <td align='right'>
                               <b><bean:message key='tri.par.date.creation.narration'/><bean:message key='2.points'/></b>
                             </td>
                             <td>
                               <myriap:radio name='rechercheNarration' property='ordreAffichage' value='<%=GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_CREATION_NARRATION%>' />
                            </td>
                     </tr>
                     <tr>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                             <td><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
                     </tr>
                 </table>	
	         </td>
	        </TR>
	       </TABLE>
	     </td>
	    </tr>

        <tr>
                <td colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
        </tr>
      </table>
      </td>
    </tr>
    <TR>
      <TD ALIGN="center" valign="top" colspan="4">

     <TABLE align="left" cellPadding="2" cellSpacing="2" border="0" class="tabTitleSmallGrayedCarvedOut" width="770">
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheNarration' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>&nbsp;
              <cardex:button labelKey='cb_rechercher' soumettre="/narration/search.do" />
            </TD>
            <TD width="310" ALIGN="left">
              <cardex:button urlSecurite="/AffichageRapportActivites" labelKey='bouton.rapport.activites.quotidien' style='width: 150px; text-align: center;' onclick='doPrintRapport();' />
              &nbsp;&nbsp;
              <cardex:button labelKey='cb_clear' style='width: 120px; text-align: center;' soumettre="/narration/search/reset/default.do" />
            </TD>

            <TD width="80" align="right">
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
