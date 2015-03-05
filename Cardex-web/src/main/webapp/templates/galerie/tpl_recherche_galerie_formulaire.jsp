<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Recherche d'un dossier de la galerie.
Description : Module d'affichage représentant le formulaire de recherche d'un
              dossier de la galerie.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/05/01 20:25:22 $

History     : Voir ci-dessous.

Revision: 1.1 , Date: 2002/03/27 21:27:41 , Author: abruno-boucher
Création.

Revision: 1.3 , Date: 2002/03/27 21:36:58 , Author: abruno-boucher
Ajustement position du calendrier et libellés

$Revision: 1.10 $, $Date: 2002/05/01 20:25:22 $, $Author: mlibersan $
Dernier commentaire à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<bean:define id="entite" name="rechercheGalerie" property="entite" type="String"/>
<TABLE width="1200" cellpadding="2" cellspacing="2" border="0" class="tableOutline">
<TR><TD CLASS="tabBackground">
	<TABLE cellpadding="2" cellspacing="2" border="0"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#e6ebf4', startColorstr='#FFFFFF', gradientType='0');">
	  <TR>
	    <TD CLASS="tabBackground" rowspan="2" valign="top">
			<TABLE cellpadding="2" cellspacing="1" border="0" class="tableOutline" width="100%" 
			style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#e6ebf4', startColorstr='#FFFFFF', gradientType='0');">
			<TR><TD colspan="2"><u><b><bean:message key="gb_sujet" /></b></u></TD></TR>
			<TR>
		        <TD ALIGN="right" nowrap><b><bean:message key='v_fiche_sujet_t'/></b></TD>
		      	<TD ALIGN="left" >
		      		<myriap:text name='rechercheGalerie' property="numeroSujet" size="20" maxlength="20" style="HEIGHT: 20px; WIDTH: 120px"  onchange='this.value = this.value.toUpperCase();' />
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='v_su_nom_textuel'/></b></TD>
			    <TD ALIGN="left" >
		          <myriap:text name='rechercheGalerie' property='nomOrdinaire' style="HEIGHT: 20px; WIDTH: 120px" /> 
		        </TD>
			</TR> 
			<TR>
		        <TD align="right"><b><bean:message key='v_su_nom_phonetique'/></b></TD>
		      	<TD ALIGN="left" >
		           <myriap:text name='rechercheGalerie' property='nom' style='HEIGHT: 20px; WIDTH: 120px' /> 
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='v_su_prenom_phonetique'/></b></TD>
		      	<TD ALIGN="left" >
		          <myriap:text name='rechercheGalerie' property='prenom' style='HEIGHT: 20px; WIDTH: 120px' /> 
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='v_su_surnom_phonetique'/></b></TD>
		      	<TD ALIGN="left" >
		          <myriap:text name='rechercheGalerie' property='alias' style='HEIGHT: 20px; WIDTH: 120px' /> 
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='i_ra_cle_t'/></b></TD>
			    <TD ALIGN="left" >
		          <myriap:select size='1' name='rechercheGalerie' property="race" style="HEIGHT: 20px; WIDTH: 160px" >
		          	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RaceCleListeCache" />
		          </myriap:select>
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='i_ls_cle_t'/></b></TD>
		        <TD ALIGN="left" >
		          <myriap:select size='1' name='rechercheGalerie' property="langue" style="HEIGHT: 20px; WIDTH: 160px" >
		          	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache"/>
		          </myriap:select>
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='i_sx_cle_t'/></b></TD>
				<TD ALIGN="left" >
		          <myriap:select size='1' name='rechercheGalerie' property='sexe' style='HEIGHT: 20px; WIDTH: 160px' >
		  	        <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SexeCleListeCache" />
		          </myriap:select>
		        </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='i_nt_cle_t'/></b></TD>
				<TD ALIGN="left" >
		          <myriap:select  size='1' name='rechercheGalerie' property="ethnie" style="HEIGHT: 20px; WIDTH: 160px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		          	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EthnieCleListeCache" />
		          </myriap:select> 
		        </TD>
			</TR>
			<TR>
		        <TD ALIGN="right"><b><bean:message key='galerie.age'/></b></TD>
				
				<TD colspan="2" ALIGN="left">
				<TABLE cellpadding="0" cellspacing="0">
					<TR> 
						<TD align="left" width="50">
							<myriap:text name='rechercheGalerie' 
							             property='age' 
							             maxlength='3' 
							             style="HEIGHT: 20px; WIDTH: 25px"
							             tabindex="21" />
						</TD>
						<TD align="right"><b><bean:message key='age.reel'/></b>
							<html:checkbox name='rechercheGalerie'
							               property='ageReel' /> &nbsp;
						</TD>
						<TD align="right"><b><bean:message key='age.reel.plus.moins5'/></b>
							<html:checkbox name='rechercheGalerie'
							               property='ageReelPlusMoins' /> &nbsp;
						</TD>
						<TD align="right"><b><bean:message key='age.estime.plus.moins5'/></b>
							<html:checkbox name='rechercheGalerie'
							               property='ageEstime' /> &nbsp;
						</TD>
						<TD align="right"><b><bean:message key='age.inconnu'/></b>
							<html:checkbox name='rechercheGalerie'
							               property='ageInconnu' />
						</TD>
					</TR>
				</TABLE> 
				</TD>
				
			</TR>
			<TR>
				<TD colspan="2" valign="top">
				<Table cellpadding="0" cellspacing="0" border="0">
				<TR>
			        <TD align="right" height="22" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;<b><bean:message key='l_cr_cle_1_t'/></b>&nbsp;</TD>
			        <TD ALIGN="left" valign="bottom">
			          <myriap:select size='1' name='rechercheGalerie' property='caracteristique1' style='HEIGHT: 20px; WIDTH: 160px' >
			          	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache"/>
			          </myriap:select>
			        </TD>
			        <TD rowspan="4" valign="top">
			        	<TABLE cellpadding="0" cellspacing="0" border="0">
						<TR>			        	
							<TD>&nbsp;</TD>
					        <TD align="center"><b><bean:message key='et'/></b></TD>
					        <TD align="center"><b><bean:message key='ou'/></b></TD>
						</TR>
						<TR>
					        <TD>
								<html:img page="/images/jonction.gif" border="0" height="22" />
							</TD>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique1et2' value='<%=GlobalConstants.Operateur.ET%>'/>		
							</TD>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique1et2' value='<%=GlobalConstants.Operateur.OU%>'/>
					        </TD>
						</TR>
						<TR>
					        <TD>
								<html:img page="/images/jonction.gif" border="0" height="22"/>
							</TD>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique2et3' value='<%=GlobalConstants.Operateur.ET%>'/>		
							</TD>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique2et3' value='<%=GlobalConstants.Operateur.OU%>'/>
					        </TD>
						</TR>
						<TR>
					        <TD>
								<html:img page="/images/jonction.gif" border="0" height="22"/>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique3et4' value='<%=GlobalConstants.Operateur.ET%>'/>		
							</TD>
							<TD>
								<html:radio name='rechercheGalerie' property='caracteristique3et4' value='<%=GlobalConstants.Operateur.OU%>'/>
					        </TD>
						</TR>												
			        	</TABLE>
			        </TD>		
				</TR>
				<TR>
			        <TD align="right" height="23" valign="bottom"><b><bean:message key='l_cr_cle_1_t'/></b>&nbsp;</TD>
			        <TD ALIGN="left" valign="bottom">
			          <myriap:select size='1' name='rechercheGalerie' property='caracteristique2' style='HEIGHT: 20px; WIDTH: 160px' >
				          <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache"/>
			          </myriap:select>
			        </TD>
			        <TD></TD>
				</TR>
				<TR>
			        <TD align="right" height="23" valign="bottom"><b><bean:message key='l_cr_cle_1_t'/></b>&nbsp;</TD>
			        <TD ALIGN="left" valign="bottom">
			          <myriap:select size='1' name='rechercheGalerie' property='caracteristique3' style='HEIGHT: 20px; WIDTH: 160px' >
				          <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache"/>
			          </myriap:select>
			        </TD>	
			        <TD></TD>			        	
				</TR>
				<TR>
			        <TD align="right" height="23" valign="bottom"><b><bean:message key='l_cr_cle_1_t'/></b>&nbsp;</TD>
			        <TD ALIGN="left" valign="bottom">
			          <myriap:select size='1' name='rechercheGalerie' property='caracteristique4' style='HEIGHT: 20px; WIDTH: 160px' >
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache"/>			          
			          </myriap:select>
			        </TD>	
			        <TD>&nbsp;
			        </TD>
				</TR>				
				</Table>
				</TD>
			</TR>
			</TABLE>
		</TD>
	    <TD CLASS="tabBackground" valign="top" align="center">
			<TABLE cellpadding="2" cellspacing="1" border="0" class="tableOutline" width="100%"
			style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#e6ebf4', startColorstr='#FFFFFF', gradientType='0');">
			<TR><TD><u><b><bean:message key="tabpage_dossier" /></b></u></TD></TR>
			<TR>
		        <TD ALIGN="right"><b><bean:message key='l_si_cle_inclus_t2'/></b></TD>
		        <TD ALIGN="left">
		        	<bean:define id="siteApplicable" name='rechercheGalerie' property='siteApplicable' type="String"/>
		          <myriap:select size='1' name='rechercheGalerie' property='siteApplicable' style='HEIGHT: 20px; WIDTH: 140px' onchange="doRefresh();">
		  	        <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle" valeurDiscriminant='<%=entite%>'/>
		          </myriap:select>
		        </TD>
			    <TD align="right"><b><bean:message key='i_se_cle_t'/></b></TD>
		        <TD ALIGN="left">
		          <myriap:select size='1' name='rechercheGalerie' property="severite" style="HEIGHT: 20px; WIDTH: 100px" >
			           <cardex:severiteOptions/>
		          </myriap:select>
		        </TD>
			</TR>
			<TR>
		        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
		        <TD ALIGN="left">
	        	  <bean:define id="siteOrigine" name='rechercheGalerie' property='siteOrigine' type="String"/>
		          <myriap:select size='1' name='rechercheGalerie' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 140px' onchange='doRefresh();' >
  	                 <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
						valeurDiscriminant='<%=entite%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
				  </myriap:select>
		        </TD>
		        <TD ALIGN="right" nowrap>&nbsp;<b><bean:message key='d_date_debut_ajout_t'/></b></TD>
		        <TD ALIGN="left">
		          <myriap:text name='rechercheGalerie' property="dateAjoutDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateAjoutDebut,'dateAjoutFin');changerChamp(this,'dateAjoutFin');"/>
		          <myriap:link href="javascript:openDate('document.forms(0).dateAjoutDebut', document.forms(0).dateAjoutDebut.value);" onmousedown="setXY(event.x - 40, event.y);">
		             <html:img page="/images/cal.gif" border="0" />
		          </myriap:link>
		        </TD>
			</TR>		
			<TR>
		        <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
		        <TD ALIGN="left">
		        	<bean:define id="genre" name='rechercheGalerie' property='genre' type="String"/>
		        	<bean:define id="nature" name='rechercheGalerie' property='nature' type="String"/>
		          <myriap:select size='1' name='rechercheGalerie' property='nature' style='HEIGHT: 20px; WIDTH: 140px' onchange='doRefresh();' >
			         <cardex:optionTag
			         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
						valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
						valeurDiscriminant='<%=genre%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_GALERIE%>'/>		  	        
		          </myriap:select>
		        </TD>
		        <TD ALIGN="right" nowrap>&nbsp;<b><bean:message key='d_date_fin_ajout_t'/></b></TD>
		        <TD ALIGN="left">
		          <myriap:text name='rechercheGalerie' property="dateAjoutFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateAjoutFin,'dateValideDebut');changerChamp(this,'dateValideDebut');"/>
		          <myriap:link href="javascript:openDate('document.forms(0).dateAjoutFin', document.forms(0).dateAjoutFin.value);" onmousedown="setXY(event.x - 40, event.y);">
		             <html:img page="/images/cal.gif" border="0" />
		          </myriap:link>
				</TD>
			</TR>
			<TR>
		        <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
				<TD ALIGN="left" nowrap valign="middle">
					<bean:define id="type" name='rechercheGalerie' property='type' type="String"/>
			        <myriap:select size='1' name='rechercheGalerie' property='type' style='HEIGHT: 20px; WIDTH: 140px' onchange="doRefresh();" >
						<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
						valeurDiscriminant='<%=nature%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_GALERIE%>' />
			        </myriap:select>
		        </TD>
		        <TD nowrap ALIGN="right"><b><bean:message key='d_date_debut_valid_t'/></b></TD>
				<TD ALIGN="left">
		          <myriap:text name='rechercheGalerie' property="dateValideDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateValideDebut,'dateValideFin');changerChamp(this,'dateValideFin');"/>
		          <myriap:link href="javascript:openDate('document.forms(0).dateValideDebut',document.forms(0).dateValideDebut.value);" onmousedown="setXY(event.x - 40, event.y);">
		            <html:img page="/images/cal.gif"border="0" />
		          </myriap:link>
		        </TD>
	        </TR>
			<TR>
		        <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
				<TD ALIGN="left">
		          <myriap:select size='1' name='rechercheGalerie' property='categorie' style='HEIGHT: 20px; WIDTH: 140px' onchange="doReperage();">
		    	      <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache" valeurDiscriminant='<%=type%>'/>
		          </myriap:select>
			        <DIV style="position: absolute; visibility= 'hidden'" id="nombre">
			          <img src="<%=request.getContextPath()%>/images/PlusGrandOuEgal.GIF" id="operateur" TITLE="Nombre de repérages plus grand ou égal à" border="0" height="18" width="30" /><myriap:text name='rechercheGalerie' property="nombreReperages" size="1" maxlength="2"  />
			        </DIV>		          
		        </TD>
		        <TD align="right" nowrap>&nbsp;<b><bean:message key='au_t'/></b></TD>
		        <TD ALIGN="left">
		          <myriap:text name='rechercheGalerie' property="dateValideFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateValideFin,'dateTermineDebut');changerChamp(this,'dateTermineDebut');"/>
		           <myriap:link href="javascript:openDate('document.forms(0).dateValideFin', document.forms(0).dateValideFin.value);" onmousedown="setXY(event.x - 40, event.y);">
		              <html:img page="/images/cal.gif" border="0" />
		           </myriap:link>
		        </TD>
	        </TR>
			<TR>
		        <TD align="right"><b><bean:message key='v_do_ancienne_reference_t'/></b></TD>
				<TD ALIGN="left">
		           <myriap:text name='rechercheGalerie' property="numeroDossier" style="HEIGHT: 20px; WIDTH: 140px" onchange='this.value = this.value.toUpperCase();' />
		        </TD>
				<TD ALIGN="right" nowrap><b><bean:message key='date_fin_per_t'/></b></TD>
				<TD ALIGN="left">
					<myriap:text name='rechercheGalerie' property="dateTermineDebut" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateTermineDebut,'dateTermineFin');changerChamp(this,'dateTermineFin');"/>
					<myriap:link href="javascript:openDate('document.forms(0).dateTermineDebut', document.forms(0).dateTermineDebut.value);" onmousedown="setXY(event.x - 40, event.y);">
				      <html:img page="/images/cal.gif" border="0" />
					</myriap:link>
				</TD>
	        </TR>
			<TR>
	            <TD ALIGN="right"><b><bean:message key='enregistrement_numerique'/></b></TD>
	            <TD ALIGN="left" >
					<html:radio name='rechercheGalerie' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
					<html:radio name='rechercheGalerie' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
	            </TD>
				<TD align="right"><b><bean:message key='au_t'/></b></TD>
				<TD ALIGN="left">
					<myriap:text name='rechercheGalerie' property="dateTermineFin" size="10" maxlength="10" onkeyup="doTraits(document.forms(0).dateTermineFin,'periode');changerChamp(this,'periode');"/>
					<myriap:link  href="javascript:openDate('document.forms(0).dateTermineFin', document.forms(0).dateTermineFin.value);" onmousedown="setXY(event.x - 40, event.y);">
						<html:img page="/images/cal.gif" border="0" />
					</myriap:link>
				</TD>
	        </TR>
	        <TR>
		        <TD align="right"><b><bean:message key='i_or_cle_t'/></b></TD>
				<TD ALIGN="left">
			      	<bean:define id="site" name='rechercheGalerie' property='siteOrigine' type="String"/>
			        <myriap:select size='1' name='rechercheGalerie' property='endroit' style='HEIGHT: 20px; WIDTH: 140px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				 	</myriap:select>
		        </TD>        
				<TD align="right"><b><bean:message key='i_pe_cle_t'/></b></TD>
				<TD>
					<myriap:select size='1' name='rechercheGalerie' property="periode" style="HEIGHT: 20px; WIDTH: 100px" >
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PeriodeCleListeCache"/>
					</myriap:select>
				</TD>
	        </TR>
	        <TR>
	            <TD ALIGN="right"><b><bean:message key='origine_t'/></b></TD>
			    <TD ALIGN="left" >
					<myriap:select size='1' name='rechercheGalerie' property='origine' style='HEIGHT: 20px; WIDTH: 140px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_GALERIE%>'/> 
					</myriap:select>
				</TD>
				<TD ALIGN="right"><b><bean:message key='c_do_fonde_t'/></b></TD>
				<TD ALIGN="left">
					<myriap:select name='rechercheGalerie' property="fonde" style="HEIGHT: 20px; WIDTH: 100px">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.FondeCleListeCache"/>
					</myriap:select>
				</TD>
	        </TR>
	        <TR>
				<TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></td>
				<TD ALIGN="left">
			        <myriap:select size='1' name='rechercheGalerie' property='localisation' style='HEIGHT: 20px; WIDTH: 140px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
			 		</myriap:select>
				</TD>
	          <TD align="right"><b><bean:message key='i_st_cle_t'/></b></TD>
	          <TD ALIGN="left">
		          <myriap:select size='1' name='rechercheGalerie' property="statut" style="HEIGHT: 20px; WIDTH: 100px" >
		          	<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache" valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER%>'/>
		          </myriap:select>
		      </TD>
	        </TR>
	        <TR>
	          <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
	            <TD ALIGN="left">
	                <cardex:AutoCompleter name='rechercheGalerie' property="descriptif" maxlength="80" 
	                style="HEIGHT: 20px; WIDTH: 140px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
	                height="150" width="150" nbrAmorce="2"/>
	            </TD>
	                
		      <TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
		      <TD ALIGN="left" >
		            <cardex:AutoCompleter name='rechercheGalerie' property="reference1" tabindex="14" maxlength="20" 
		            style="HEIGHT: 20px; WIDTH: 100px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE1_DOSSIER_AUTO_COMPLETER %>'
		            height="150" width="150" nbrAmorce="2"/>
		      </TD>
	        </TR>        
			<TR>
		        <TD align="right"><b><bean:message key='type.jeu'/> : </b></TD>
		        <TD ALIGN="left">
		        <bean:define id="entite" name="rechercheGalerie" property="entite" type="String"/>
		          <myriap:select size='1' name='rechercheGalerie' property="typeJeu" style="HEIGHT: 20px; WIDTH: 140px" tabindex="20" onkeypress="typeAhead(this, event);" onchange="doRefresh();" onfocus="resetIncrementalSearch();" >
		          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>' valeurDiscriminant='<%=entite%>'/>
		          </myriap:select>  
		        </TD>
			
		      <TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
		      <TD ALIGN="left" >
		            <cardex:AutoCompleter name='rechercheGalerie' property="reference2" tabindex="14" maxlength="20" 
		            style="HEIGHT: 20px; WIDTH: 100px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE2_DOSSIER_AUTO_COMPLETER %>'
		            height="150" width="150" nbrAmorce="2"/>
		      </TD>
			</TR>
			<TR>
		        <TD align="right"><b><bean:message key='tabpage_jeu'/> : </b></TD>
		        <TD ALIGN="left" colspan="3">
		          <bean:define id="typeJeu" name="rechercheGalerie" property="typeJeu" type="String"/>
		          <myriap:select size='1' name='rechercheGalerie' property="jeu" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.JEUX%>' valeurDiscriminant='<%=entite+","+typeJeu%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_GALERIE%>'/>
		          </myriap:select> 
		        </TD>
			</TR>
			<TR>
			    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
			    <TD ALIGN="left" colspan="3">
				    <myriap:select size='1' name='rechercheGalerie' property='intervenant' style='HEIGHT: 20px; WIDTH: 390px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantActifParSiteCle" valeurDiscriminant='<%=site%>'/> 
				    </myriap:select>&nbsp;
			    </TD>
			</TR>	        
			</TABLE>
		</TD>
	  </TR>
	  <TR>
		<TD CLASS="tabBackground" >
			<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
			<TR><TD valign="bottom" align="center">
		  		<TABLE cellpadding="2" cellspacing="2" border="0" class="tableOutline">
				<TR><TD>
					<b><bean:message key='tri'/><bean:message key='2.points'/></b>
					<myriap:select size='1' name='rechercheGalerie' property='ordreTri' style='HEIGHT: 20px; WIDTH: 180px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe.TriGalerieCleRessourceHardListe" /> 
					</myriap:select>
				</TD><TD>
					<TABLE cellpadding="2" cellspacing="0" border="0">
					<TR>
						<TD><b><bean:message key='asc'/></b></TD>
						<TD><b><bean:message key='desc'/></b></TD>
					</TR>
					<TR>
						<TD><html:radio name='rechercheGalerie' property='sensTri' value='<%=GlobalConstants.SensTri.ASC%>'/></TD>
						<TD><html:radio name='rechercheGalerie' property='sensTri' value='<%=GlobalConstants.SensTri.DESC%>'/></TD>
					</TR>
			  		</TABLE>
				</TD></TR>
		  		</TABLE>
			</TD>
			<TD valign="bottom" align="right">
		  		<TABLE cellpadding="2" cellspacing="2" border="0" class="tableOutline">
				<TR>
					<TD><b><bean:message key='i_inclus_sujet_t'/></b></TD>
					<TD><myriap:checkbox name='rechercheGalerie' property='sujetAttache' value="true" />*</TD>			
				</TR>
				<TR>
					<TD><b><bean:message key='i_inclus_dossier_t'/></b></TD>
					<TD><myriap:checkbox name='rechercheGalerie' property='dossierAttache' value="true" />*</TD>			
				</TR>
		  		</TABLE>
			</TD></TR>
			</TABLE>
		</TD>
	  </TR>

      <TR>
        <TD WIDTH="1200" ALIGN="center" COLSPAN="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
      </TR>
      
	    <TR>
	      <TD WIDTH="1200" ALIGN="center" COLSPAN="2" HEIGHT="15"><img src="<%=request.getContextPath()%>/images/0061CFpixel.gif" height="1" width="1200" border="0"></TD>
	    </TR>

      <TR>
        <TD ALIGN="right" valign="top" COLSPAN="2" >
          <cardex:button labelKey='cb_rechercher' soumettre="/galerie/search.do" />
           <cardex:button labelKey='cb_clear' soumettre="/galerie/search/reset/default.do" style='width: 120px; text-align: center;' />
           <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
        </TD>
      </TR>
    </TABLE>
    </TD>
  </TR>
    <!-- End Kind & nature search fields -->
</TABLE>
<!-- End outline table -->
