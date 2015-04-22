<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- --------------------------------------------------------------------------
Use case    : Ajout d'une consignation.
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

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.action.Action" %>

<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

   <html:hidden  name="consignation" property="cle" />
   <html:hidden  name="consignation" property="site" />
   <html:hidden  name="consignation" property="lien" />
   <html:hidden  name="consignation" property="lienSite" />
   <html:hidden  name="consignation" property="lienGenre" />
   <html:hidden  name="consignation" property="reference1" />
   <html:hidden  name="consignation" property="reference2" />
   <html:hidden  name="consignation" property="dateCreation" />
   <html:hidden  name="consignation" property="modificateur" />
   <html:hidden  name="consignation" property="dateModification" />
   <html:hidden  name="consignation" property="createur" />
   <html:hidden  name="consignation" property="dateApprobation" />
   <html:hidden  name="consignation" property="approbateur" />

<!-- POSITIONING TABLE -->

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
</HEAD>

<SCRIPT language="JavaScript" type="text/javascript">

function calculerMontant() {
   if(((document.forms(0).quantite.value != "0")   || (document.forms(0).quantite.value != null))
       && (document.forms(0).prix.value > " ")){
       //-- On remplace le séparateur décimal par un point si c'est une virgule.
       	    prix = document.forms(0).prix.value;
       	       prix = prix.replace(",",".");
       	       //On retire également le séparateur de milliers pour le calcul.
       	       prix = prix.replace(/ /gi,"");
               document.forms(0).montant.value=eval(document.forms(0).quantite.value*prix);
           //-- On remet ensuite la virgule pour la sauvegarde
	       document.forms(0).prix.value = prix.replace(".",",");
	       //-- Même chose pour le résultat
	       document.forms(0).montant.value = document.forms(0).montant.value.replace(".",",");
   }
}

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
        if(statut.childNodes[0].nodeValue == "true"){
           document.forms(0).approuvable.checked = true;
           document.forms(0).approuvable.value = "true";
           var statutApprouve = entree.getElementsByTagName("approuve")[0];
           if((statutApprouve == null) || (statutApprouve.childNodes[0].nodeValue != "true")){
              message("<bean:message key='avertissement_t'/>");
              avertissement.style.visibility = "visible";
              document.forms(0).approuve.checked = false;
              document.forms(0).approuve.value = "false"
              document.forms(0).approbateur.value = "";
              document.forms(0).dateApprobation.value = "";
           }else{
              avertissement.style.visibility = "hidden";
              document.forms(0).approuve.checked = true;
              document.forms(0).approuve.value = "true";
              var approuve_par = entree.getElementsByTagName("approbateur")[0];
              document.forms(0).approbateur.value = approuve_par.childNodes[0].nodeValue;
              var date_approbation = entree.getElementsByTagName("date")[0];
              document.forms(0).dateApprobation.value = date_approbation.childNodes[0].nodeValue;
           }
        }else{
           avertissement.style.visibility = "hidden";
           document.forms(0).approuvable.checked = false;
           document.forms(0).approuvable.value = "false";
           document.forms(0).approuve.checked = false;
           document.forms(0).approuve.value = "false"
           document.forms(0).approbateur.value = "";
           document.forms(0).dateApprobation.value = "";
        }
    }
}

</SCRIPT>

<TABLE border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff">
  <tr>
  	<td align="center">


      <!-- CONTENT -->
      <TABLE width="650"  cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
    		<tr>
		   <td align="center">

    		  <TABLE width="620" cellpadding="3" cellspacing="0" border="0" bgcolor="#ECECEC" class="tableOutline" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");'>
        		<tr>
        			<td colspan="6"><html:img page="/images/blank.gif" width="1" height="1"  border="0" />
        			</td>
      		        </TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='i_ty_cle_t'/></b></td>
        			<td>
                                     <myriap:select name='consignation' property="typeConsignation" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange="doVerificationConsignation();" >
                                       <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
											valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_CONSIGNATION%>'
											actionSecurite='<%=actionSecurite%>'/>
                                    </myriap:select>
                                </td>
        			<td align="right" nowrap><b><bean:message key='denomination_t'/></b></td>
        			<td>
                             <myriap:select name='consignation' property="denomination" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange="document.forms(0).prix.value=document.forms(0).denomination[document.forms(0).denomination.selectedIndex].text;calculerMontant();">
                               <cardex:optionTag classe='<%= GlobalConstants.CleListe.DENOMINATION %>'/>
                            </myriap:select>
					</td>
        			<td align="right" nowrap><b><bean:message key='devise_t'/></b></td>
        			<td>
                        <myriap:select name='consignation' property="devise" size="1" style="HEIGHT: 20px; WIDTH: 120px">
                          <cardex:optionTag classe='<%= GlobalConstants.CleListe.DEVISE %>'/>
                       </myriap:select>
                    </td>
        		</TR>
        		<TR>
        		    <td align="right" nowrap><b><bean:message key='description_t'/></b></td>
        			<td colspan="5">
                            <myriap:text name='consignation' property='description' style='HEIGHT: 20px; WIDTH: 300px' maxlength='50' />
                    </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='no_serie_t'/></b></td>
        			<td>
                            <myriap:text name='consignation' property='numeroSerie' size='60px' onblur="doVerificationConsignation();" />
                    </td>
        			<td align="right" nowrap><b><bean:message key='dimension_t'/></b></td>
        			<td>
                                    <myriap:text name='consignation' property='dimension' size='60px' />
                                </td>
        			<td align="right" nowrap><b><bean:message key='poids_t'/></b></td>
        			<td>
                                    <myriap:text name='consignation' property='poids' size='40px' />
                                </td>
        		</TR>
        		<TR>
        			<td align="right" nowrap><b><bean:message key='quantite_t'/></b></td>
        			<td>
                                    <myriap:text name='consignation' property='quantite' size='20px' onfocusout="validerArgent(prix);" onblur="calculerMontant()" />
                                </td>
        			<td align="right" nowrap><b><bean:message key='prix_t'/></b></td>
        			<td>
        				<cardex:argent name='consignation' onfocusout="validerArgent(this);"
							property='prix' size="11" maxlength='11'  style="HEIGHT: 20px; WIDTH: 60px"  onblur="calculerMontant()" />$
                    </td>
        			<td align="right" nowrap><b><bean:message key='r_co_montant_t'/></b></td>
        			<td>
                        <cardex:argent name='consignation' property='montant' />
                    </td>
        		</TR>

        		<TR>
        			<td align="right" nowrap><b><bean:message key='fournisseur_t'/></b></td>
        			<td>
           			   <cardex:AutoCompleter name='consignation' property="fournisseur" maxlength="50" 
				            style="HEIGHT: 20px; WIDTH: 123px" classeControl='<%= GlobalConstants.AutoCompleterClass.FOURNISSEUR_CONSIGNATION_AUTO_COMPLETER %>'
				            height="150" width="150" nbrAmorce="2"/>                        
                    </td>
        			<td align="right" nowrap><b><bean:message key='i_ma_cle_t'/></b></td>
        			<td>
           			   <cardex:AutoCompleter name='consignation' property="marque" maxlength="50" 
				            style="HEIGHT: 20px; WIDTH: 123px" classeControl='<%= GlobalConstants.AutoCompleterClass.MARQUE_CONSIGNATION_AUTO_COMPLETER %>'
				            height="150" width="150" nbrAmorce="2"/>                        
                    </td>
        			<td align="right" nowrap><b><bean:message key='i_md_cle_t'/></b></td>
        			<td>
           			   <cardex:AutoCompleter name='consignation' property="modele" maxlength="50" 
				            style="HEIGHT: 20px; WIDTH: 123px" classeControl='<%= GlobalConstants.AutoCompleterClass.MODELE_CONSIGNATION_AUTO_COMPLETER %>'
				            height="150" width="150" nbrAmorce="2"/>                        
                    </td>
        		</TR>

        		<TR>
        			<td align="right" nowrap><b><bean:message key='v_ad_commentaire_t'/></b></td>
				<td  colspan="5" align="left">      		
				    <html:textarea name="consignation" property="commentaire" rows='2' cols='90' style='font-family: Verdana, Arial; font-size: 8pt;'  />
				</td>
        		</TR>
        		<TR>
        		  <TD colspan=6 align=center>
              			<b><bean:message key='approuvable_t'/></b>
                                  <myriap:checkbox name='consignation' property="approuvable" disabled="true" />
				  &nbsp;&nbsp;
              			<b><bean:message key='approuve_t'/></b>
                                  <myriap:checkbox name='consignation' property="approuve" disabled="true" />
                          </td>
                        </TR>

		      <tr>
		         <td align='center' colspan="6" >
			   <table cellpadding="3" border='1' rules='none' width="600">
			    <tr>
				<td align="right" nowrap><bean:message key='v_cree_par_t'/></td>
				<td>
				    <cardex:afficherValeurListeTag name="consignation" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
				</td>
				<td align="right" nowrap><bean:message key='v_tr_modifie_par_t2'/></td>
				<td>
				    <cardex:afficherValeurListeTag name="consignation" property="modificateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
				</td>
				<td align="right" nowrap><bean:message key='v_modifie_par_t'/></td>
				<td>
				    <cardex:afficherValeurListeTag name="consignation" property="approbateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
				</td>
			    </tr>

			    <tr>
				<td align="right" nowrap><bean:message key='date_t2'/></td>
				<td nowrap>
				    <bean:write name="consignation" property="dateCreation"/>
				</td>
				<td align="right" nowrap><bean:message key='date_t2'/></td>
				<td nowrap>
				    <bean:write name="consignation" property="dateModification"/>
				</td>
				<td align="right" nowrap><bean:message key='date_t2'/></td>
				<td nowrap>
				    <bean:write name="consignation" property="dateApprobation"/>
				</td>
			    </tr>
			  </table>
		      </td>
		    </tr>
        	    <tr>
        		<td colspan="6" align="center" ><html:img page="/images/0061CFpixel.gif" height="1" width="650" border="0" />
        		</td>
      		    </TR>
    	         <TR>
    	            <TD align="center" colspan="6">
    			  <!-- BOTTOM BUTTONS -->
    			  <TABLE width="600" cellpadding="3" cellspacing="0" border="0">
    			    <TR>
    			      <TD width="150">
                                <logic:equal name='consignation' property='modifiable' value='true' >
                                    <cardex:button urlSecurite='<%=urlSecuriteSauvegarde%>' labelKey='cb_ok'  onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
                                </logic:equal>
                              </TD>

				<TD width="150">
				  <logic:equal name='consignation' property='modifiable' value='true' >
				     <logic:equal name='consignation' property='approuvable' value='true' >
					 <cardex:button urlSecurite="/dossier/consignation/approve.do" labelKey='Approbation'  onclick='doApprobation();' style="color: #000000; width: 80px; text-align: center" />
				      </logic:equal>
				  </logic:equal>
			     </TD>

				<TD width="150" align="right">
				    <cardex:button labelKey='cb_fermer'  onclick='doClose();' style="color: #000000; width: 60px; text-align: center" />
				</TD>

			      </TR>
			 </TABLE>
            <!-- END BOTTOM BUTTONS -->
 
    		</TD>
    	   </TR>
         </TABLE>


  	</TD>
      </tr>
   </TABLE>
    <!-- END CONTENT -->
    </td>
  </tr>
  <tr>
    <td align='center'>
    <!-- Affichage du message selon le statut d'approbation -->
       <logic:equal name='consignation' property='approuvable' value='true' >
	 <logic:equal name='consignation' property='approuve' value='true' >
		<DIV align="center" STYLE="overflow:none; width:670; height:5;" style="visibility= 'hidden';border='0.05cm groove gray';background-color='#F0FFFF'" id="avertissement">    
			<H5><bean:message key='avertissement_t'/></H5>
		</DIV>
	 </logic:equal>
       </logic:equal>
       <logic:notEqual name='consignation' property='approuvable' value='true' >
		<DIV align="center" STYLE="overflow:none; width:670; height:5;" style="visibility= 'hidden';border='0.05cm groove gray';background-color='#F0FFFF'" id="avertissement">    
			<H5><bean:message key='avertissement_t'/></H5>
		</DIV>
       </logic:notEqual>
       <logic:equal name='consignation' property='approuvable' value='true' >
	 <logic:notEqual name='consignation' property='approuve' value='true' >
		<DIV align="center" STYLE="overflow:none; width:670; height:5;" style="visibility= 'visible';border='0.05cm groove gray';background-color='#F0FFFF'" id="avertissement">    
			<H5><bean:message key='avertissement_t'/></H5>
		</DIV>
	 </logic:notEqual>
       </logic:equal>
    </td>
  </tr>
</TABLE>
<!-- END POSITIONING TABLE -->
