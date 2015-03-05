<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Formulaire du mandat PSU.
Description : Écran de saisie d'une nouvelle consignation.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.9 $, $Date: 2002/05/01 20:25:19 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.9 $, $Date: 2002/05/01 20:25:19 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


   <html:hidden  name="PSUMandat" property="cle" />
   <html:hidden  name="PSUMandat" property="site" />
   <html:hidden  name="PSUMandat" property="referenceCle" />
   <html:hidden  name="PSUMandat" property="referenceSite" />
   <html:hidden  name="PSUMandat" property="genreFichier" />
   <html:hidden  name="PSUMandat" property="dateCreation" />
   <html:hidden  name="PSUMandat" property="modificateur" />
   <html:hidden  name="PSUMandat" property="dateModification" />
   <html:hidden  name="PSUMandat" property="createur" />
   <html:hidden  name="PSUMandat" property="dateApprobation" />
   <html:hidden  name="PSUMandat" property="approbateur" />
   <html:hidden  name="PSUMandat" property="statut" />
   <html:hidden  name="PSUMandat" property="numeroMandat" />
   

<!-- POSITIONING TABLE -->

<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
</HEAD>

<SCRIPT language="JavaScript" type="text/javascript">

function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xml = new ActiveXObject("Microsoft.XMLHTTP");
        return xml;
    }
}

function doVerificationConsignation() {
//Fonction qui valide si le type est approuvable et si oui si le numéro de série a été approuvé.
    if (document.forms(0).typeConsignation.value != "" && document.forms(0).numeroSerie.value != "") {
        document.forms(0).numeroSerie.value = document.forms(0).numeroSerie.value.toUpperCase();
        var url = "<%= request.getContextPath() %>/VerificationConsignation?TYPE=" + document.forms(0).typeConsignation.value + "&NUMERO_SERIE=" + document.forms(0).numeroSerie.value;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    parseMessages(req.responseXML);
                } else if (req.status == 204){
                    
                }
            }
        };
	req.open("GET", url, true);
        req.send(null);
    }
}

function parseMessages(responseXML) {
    var listes = responseXML.getElementsByTagName("listes")[0];
    if (listes.childNodes.length > 0) {
	var entree = listes.childNodes[0];
        var statut = entree.getElementsByTagName("approuvable")[0];
        if(statut.childNodes[0].nodeValue == "yes"){
           document.forms(0).approuvable.checked = true;
           document.forms(0).approuvable.value = "yes";
           var statutApprouve = entree.getElementsByTagName("approuve")[0];
           if((statutApprouve == null) || (statutApprouve.childNodes[0].nodeValue != "yes")){
              alert("<bean:message key='avertissement_t'/>");
              avertissement.style.visibility = "visible";
              document.forms(0).approuve.checked = false;
              document.forms(0).approuve.value = "no"
              document.forms(0).approbateur.value = "";
              document.forms(0).dateApprobation.value = "";
           }else{
              avertissement.style.visibility = "hidden";
              document.forms(0).approuve.checked = true;
              document.forms(0).approuve.value = "yes";
              var approuve_par = entree.getElementsByTagName("approbateur")[0];
              document.forms(0).approbateur.value = approuve_par.childNodes[0].nodeValue;
              var date_approbation = entree.getElementsByTagName("date")[0];
              document.forms(0).dateApprobation.value = date_approbation.childNodes[0].nodeValue;
           }
        }else{
           avertissement.style.visibility = "hidden";
           document.forms(0).approuvable.checked = false;
           document.forms(0).approuvable.value = "no";
           document.forms(0).approuve.checked = false;
           document.forms(0).approuve.value = "no"
           document.forms(0).approbateur.value = "";
           document.forms(0).dateApprobation.value = "";
        }
    }
}

</SCRIPT>

<TABLE border="0" cellpadding="0" cellspacing="0" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");' class="tableOutline">
  <tr>
  	<td colspan="2" >
    		  <TABLE width="650" cellpadding="3" cellspacing="0" border="0" class="tableOutline" >
        		<TR>
        			<td align="right" nowrap><b><bean:message key='mandat_t2'/></b></td>
        			<td nowrap>
                        <bean:write name='PSUMandat' property='numeroMandat' />
                        <html:hidden name='PSUMandat' property='numeroMandat' />
                    </td>
        			<td align="right" nowrap><b><bean:message key='du_t'/></b></td>
	      			<td nowrap>
		                  <cardex:DateHeure name='PSUMandat' property="dateDebut" calendrier="true" />
	      			</td>
        			<td align="right" nowrap><b><bean:message key='date_creation_fin_t'/></b></td>
	      			<td nowrap>
		                  <cardex:DateHeure name='PSUMandat' property="dateFin" calendrier="true" />
	      			</td>
        			<td align="right" nowrap><b><bean:message key='c_ac_action_t2'/></b></td>
        			<td>
                             <myriap:select name='PSUMandat' tabindex="1" property="typeAction" size="1" style="HEIGHT: 20px; WIDTH: 120px" >
                               <cardex:optionTag classe='<%= GlobalConstants.CleListe.TYPE_ACTION %>'/>
                            </myriap:select>
                    </td>
                 </TR>
        		<TR>
        		    <td align="right" nowrap><b><bean:message key='description_t'/></b></td>
        			<td colspan="5">
                            <myriap:text name='PSUMandat' property='description' style='HEIGHT: 20px; WIDTH: 410px' maxlength='250' tabindex="2" />
                    </td>
        			<td align="right" nowrap><b><bean:message key='i_st_cle_t'/></b></td>
        			<td>
						<cardex:afficherValeurListeTag name='PSUMandat' property="statut" classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
					</td>
        		</TR>
			  </TABLE>
			 </td>
			 </tr>
			 <tr>
			 <td align="center">
	    <table width="335" cellpadding="0" cellspacing="2" border="0" class="tableOutline">
	    <!-- Début Dossier -->
	        <TR>
	          <TD colspan="2" class="tabTitleSmallPSU">&nbsp;<bean:message key='tabpage_dossier'/>
				</TD>
      		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_en_cle_t'/></b></td>
        			<td>
        				<bean:define id="entite" name='PSUMandat' property="entite" type="String"/>
                           <myriap:select name='PSUMandat' property="entite" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange='doRefresh();' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="3" >
                              <cardex:optionTag 
		                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
								actionSecurite='<%=actionSecurite%>'/>
                          </myriap:select>
                    </td>
                </TR>
                <TR>
        			<td align="right" nowrap><b><bean:message key='site_t2'/></b></td>
        			<td>
                             <myriap:select name='PSUMandat' property="siteCible" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange='doRefresh();' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="4">
                                <cardex:optionTag 
			                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
									valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
									valeurDiscriminant='<%=entite%>'
									actionSecurite='<%=actionSecurite%>' />
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>	
        			<td align="right" nowrap><b><bean:message key='i_ge_cle_t'/></b></td>
        			<td>
        				<bean:define id="genre" name='PSUMandat' property="genre" type="String"/>
                            <myriap:select name='PSUMandat' property="genre" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange='doRefresh();' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="5">
                                <cardex:optionTag 
			                     	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     				valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
									valeurDiscriminant='<%=entite%>'
									actionSecurite='<%=actionSecurite%>' />
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_na_cle_t'/></b></td>
        			<td>
        				<bean:define id="nature" name='PSUMandat' property="nature" type="String"/>
                            <myriap:select name='PSUMandat' property="nature" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange='doRefresh();'onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="6">
			                     <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
			                     	valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
									valeurDiscriminant='<%=genre%>'
									actionSecurite='<%=actionSecurite%>' />
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_ty_cle_t'/></b></td>
        			<td>
        					<bean:define id="type" name='PSUMandat' property="type" type="String"/>
                            <myriap:select name='PSUMandat' property="type" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange='doRefresh();' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="7">
		 		        		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
									valeurDiscriminant='<%=nature%>'
									actionSecurite='<%=actionSecurite%>' />                               
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_ca_cle_t'/></b></td>
        			<td>
                            <myriap:select name='PSUMandat' property="categorie" size="1" style="HEIGHT: 20px; WIDTH: 120px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="8">
		 		        		<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE %>' 
									valeurDiscriminant='<%=type%>'
									actionSecurite='<%=actionSecurite%>' />                               
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='c_do_fonde_t'/></b></td>
        			<td>
                            <myriap:select name='PSUMandat' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 120px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="9">
                               <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='v_sv_intervenant_t'/></b></td>
        			<td>
        				<bean:define id="site" name='PSUMandat' property='siteCible' type="String"/>
                            <myriap:select name='PSUMandat' property="intervenant" size="1" style="HEIGHT: 20px; WIDTH: 200px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="10">
                               <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
									valeurDiscriminant='<%=site%>'
									actionSecurite='<%=actionSecurite%>'/>
                            </myriap:select>
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='v_do_numero_t'/></b></td>
        			<td>
                        <myriap:text name='PSUMandat' property='numeroDossier' size='40px' tabindex="11" />
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='no_dossier_t'/></b></td>
        			<td>
                        <myriap:text name='PSUMandat' property='numeroCardex' size='40px' tabindex="12" />
                    </td>
        		</TR>
        	</TABLE>
             </td>
             <td>
			<TABLE width="325" cellpadding="0" cellspacing="2" border="0" class="tableOutline">
			 <TR>
			  <TD>             
				<TABLE width="320" cellpadding="0" cellspacing="2" border="0" class="tableOutline">
				    <tr>
				      <td colspan="2" class="tabTitleSmallPSU">&nbsp;
					      <bean:message key="tabpage_sujet" />
				      </TD>
					</TR>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_su_numero_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='ficheSujet' size='40px' tabindex="13" />
	                    </td>
	        		</TR>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_su_nom_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='sujetNom' size='40px' tabindex="14" />
	                    </td>
	        		</TR>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_su_prenom_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='sujetPrenom' size='40px' tabindex="15" />
	                    </td>
	        		</TR>
				</TABLE>
		       </TD>
			  </TR>
        	  <TR>
        	   <TD>
			    <TABLE width="320" cellpadding="0" cellspacing="2" border="0" class="tableOutline">
			        <TR>
			          <TD colspan="2" class="tabTitleSmallPSU">&nbsp;
			              <bean:message key='tabpage_societe'/>
			          </TD>
		      		</tr>
	        		<TR>
	        			<td align="right" nowrap width="140"><b><bean:message key='v_no_fiche_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='ficheSociete' size='40px' tabindex="16" />
	                    </td>
	        		</TR>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_su_nom_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='societeNom' size='40px' tabindex="17" />
	                    </td>
	        		</TR>
		      	</TABLE>
               </TD>
              </TR>
        	  <TR>
        	   <td>
			    <TABLE width="320" cellpadding="1" cellspacing="2" border="0" class="tableOutline">
			        <TR>
			          <TD colspan="2" class="tabTitleSmallPSU">&nbsp;
			              <bean:message key='tabpage_vehicule'/>
			          </TD>
		      		</tr>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_ve_immatriculation_t'/></b></td>
	        			<td>
	                        <myriap:text name='PSUMandat' property='immatriculation' size='40px' tabindex="18" onkeydown="return isNumericImmatriculationTag(event.keyCode);" onkeyup="toUpper(this);" />
	                    </td>
	        		</TR>
	        		<TR>
	        			<td align="right" nowrap><b><bean:message key='v_ve_province_t'/></b></td>
	        			<td>
                            <myriap:select name='PSUMandat' property="province" size="1" style="HEIGHT: 20px; WIDTH: 120px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="19" >
                               <cardex:optionTag classe='<%= GlobalConstants.CleListe.PROVINCE_SANS_REQUIS %>'/>
                            </myriap:select>
	                    </td>
	        		</TR>
		      	</TABLE>
               </TD>
              </TR>
             </TABLE>
             </TD>
             </TR>
        	  <TR>
        	   <td colspan="2">
			    <TABLE width="670" cellpadding="3" cellspacing="2" border="0" class="tableOutline">
			        <TR>
			          <TD colspan="2" class="tabTitleSmallPSU">&nbsp;
			              <bean:message key='v_commentaire_t'/>
			          </TD>
		      		</tr>
	        		<TR>
	        		  <TD align="right" width="150">
	        		      <b><bean:message key='mots_cles'/></b>
	        		  </TD>
	        		  <TD>
	                        <myriap:text name='PSUMandat' property='motCle1' size='40px' tabindex="20" />&nbsp;
	                        <myriap:text name='PSUMandat' property='motCle2' size='40px' tabindex="21" />&nbsp;
	                        <myriap:text name='PSUMandat' property='motCle3' size='40px' tabindex="22" />
	                  </TD>
	        		</TR>
		      	</TABLE>
               </TD>
              </TR>
             <TR>
             <TD colspan="2">
			  <table width="670" cellpadding="0" cellspacing="2" border="0" class="tableOutline">
               <TR>
                <TD nowrap align="center">
					<TABLE width="340" cellpadding="0" cellspacing="2" border="0" class="tableOutline">                  
		                <TR>
		        			<td align="right" nowrap><b><bean:message key='tabpage_adress'/>&nbsp;<bean:message key='a_t'/></b></td>
		                    <td>
			                        <myriap:text name='PSUMandat' property='destinataireA' style='HEIGHT: 20px; WIDTH: 240px' tabindex="23" />
		                    </td>
		        		</TR>
		                <TR>
		        			<td align="right" nowrap><b><bean:message key='cc_t'/></b></td>
		                    <td>
			                        <myriap:text name='PSUMandat' property='destinataireCC' style='HEIGHT: 20px; WIDTH: 240px' tabindex="24" />
		                    </td>
		        		</TR>
		                <TR>
		        			<td align="right" nowrap><b><bean:message key='cci_t'/></b></td>
		                    <td>
			                        <myriap:text name='PSUMandat' property='destinataireCCI' style='HEIGHT: 20px; WIDTH: 240px' tabindex="25" />
		                    </td>
		        		</TR>
        		      </TABLE>
                   </td>
                  <TD>
					<TABLE width="300" cellpadding="2" cellspacing="2" border="0" class="tableOutline">                  
		                <TR>
		        			<td nowrap><b><bean:message key='message_envoi_t'/></b>
		        			</td>
		        		</TR>
		        		<TR>
		                    <td>
		            			<html:textarea name="PSUMandat" property="message" rows='4' cols='45' style='font-family: Verdana, Arial; font-size: 8pt;' />
		                    </td>
		        		</TR>
        		      </TABLE>
					</TD> 
				  </TR>                 
	                <TR>
	                    <td colspan="2">
	                        <bean:message key='v_cree_par_t'/>&nbsp;
		                    <cardex:afficherValeurListeTag name="PSUMandat" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
	                    	<bean:write name="PSUMandat" property="dateCreation"/>
	                    </td>
	        		</TR>
                </TABLE>
              </TD>
              </TR>
    	      <TR>
    	        <TD align="center" colspan="2">
    			  <!-- BOTTOM BUTTONS -->
    			  <TABLE width="600" cellpadding="3" cellspacing="0" border="0">
    			    <TR>
    			      <TD width="150">
                                <logic:equal name='PSUMandat' property='modifiable' value='true' >
                                    <cardex:button urlSecurite='<%=urlSecuriteSauvegarde %>' labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 80px; text-align: center" />
                                </logic:equal>
                              </TD>

				<TD width="150">
				  <logic:equal name='PSUMandat' property='modifiable' value='true' >
				     <logic:equal name='PSUMandat' property='approbateur' value=' ' >
				        <logic:notEqual name='PSUMandat' property='numeroMandat' value=' ' >
					        <cardex:button urlSecurite="/mandat/approbation.do" labelKey='Approbation'  onclick='doApprobation();' style="color: #000000; width: 80px; text-align: center" />
					    </logic:notEqual>
				      </logic:equal>
				  </logic:equal>
				  <logic:equal name='PSUMandat' property='modifiable' value='false' >
					 <cardex:button urlSecurite="/mandat/permettreModification.do" labelKey='cb_modification'  onclick='doPermettreModification();' style="color: #000000; width: 130px; text-align: center" />
				  </logic:equal>
			     </TD>

				<TD width="150" align="right">
				    <cardex:button labelKey='cb_fermer'  onclick='doClose();' style="color: #000000; width: 70px; text-align: center" />
				</TD>

			      </TR>
			 </TABLE>
            <!-- END BOTTOM BUTTONS -->
 
    		</TD>
    	   </TR>
         </TABLE>
         
<SCRIPT language="JavaScript" type="text/javascript">
//Affichage du message de validation, s'il y a lieu
message = "<%= request.getSession().getAttribute("message") %>";
if (message != "") {
	alert(message);
}
</SCRIPT>

<!-- END POSITIONING TABLE -->
