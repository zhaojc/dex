<%-- --------------------------------------------------------------------------
Use case    : Consultation ou ajout d'une société.
Description : Module d'affichage représentant le formulaire de consultation d'une
              société.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.13 $, $Date: 2002/02/25 19:12:36

History     : Voir ci-dessous.

Revision: 1.2 , Date: 2002/03/15 21:00:10 , Author: mdemers
Création.

Revision: 1.9 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise à jour nouvel Assistant date-heure (incorporé dans la page). 

$Revision: 1.13 $, $Date: 2002/04/30 12:17:59 $, $Author: mlibersan $
Commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<tiles:useAttribute name="soumettreSauvegarde" id="soumettreSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de sauvegarder (permis seulement si le site est le même 
     indépendamment de la règle ClearTrust) -->
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>
<SCRIPT language="JavaScript" type="text/javascript">
function doCopier() {
//Fonction temporaire pour permettre la copie des données d'une société à l'autre, dans le but d'éliminer les doublons de sociétés.
   if(document.forms(0).detaillant.value != ""){
      if ( confirmation('Voulez-vous vraiment copier les données de la société ' + document.forms(0).numeroFiche.value + " à la société " + document.forms(0).detaillant.value + "?") ){
      	soumettre("<%= request.getContextPath()%>/societe/copier.do?cle=" + document.forms(0).cle.value + "&site=" + document.forms(0).site.value + "&detaillant=" + document.forms(0).detaillant.value);
      }
   }
}
</SCRIPT>

<!-- KIND & NATURE SEARCH FIELDS -->
<!-- Following table is used to produce an outline -->
<TABLE width="850" cellpadding="0" cellspacing="0" border="0" class="tableOutline">
  <TR>
    <TD class="tabBackgroundEdit">

    <TABLE  width="850" cellpadding="2" cellspacing="0" border="0">
      <!-- First row uses transparent pixel to force good alignment -->
      <TR>
        <TD width="125" align="right"><html:img page="/images/blank.gif" width="121" height="1" border="0" /></TD>
        <TD width="165" align="left"><html:img page="/images/blank.gif" width="160" height="1" border="0" /></TD>
        <TD width="105"><html:img page="/images/blank.gif" width="101" height="1" border="0" /></TD>
        <TD width="125"><html:img page="/images/blank.gif" width="121" height="1" border="0" /></TD>
        <TD width="215"><html:img page="/images/blank.gif" width="111" height="1" border="0" /></TD>
        <TD width="125"><html:img page="/images/blank.gif" width="121" height="1" border="0" /></TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD align="right"><b><bean:message key='co_no_fiche_t'/></b></TD>
        <TD align="left">
  			 <logic:equal name='societe' property='indicateurRdd' value='true' >
		     	<bean:write name="societe" property="numeroFiche"/>
		     </logic:equal>
   			 <logic:equal name='societe' property='indicateurRdd' value='false' >
	        	<myriap:text name='societe' property="numeroFiche" tabindex="1" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px" onchange='this.value = this.value.toUpperCase();'  />
	        </logic:equal>
        </TD>

        <!-- Le titre DOIT ÊTRE peuplé dynamiquement pour l'enveloppe Vb -->
        <SCRIPT language="JavaScript">
          document.title = "<bean:message key='tabpage_societe'/> <bean:write name='societe' property='numeroFiche'/>";
        </SCRIPT>

        <TD ALIGN="right" ><b><bean:message key='i_cl_cle_t'/></b></TD>
        <TD ALIGN="left" ><html:select size='1' name='societe' property='classe' tabindex="6" style='HEIGHT: 20px; WIDTH: 160px' >
  	        <cardex:optionTag classe='<%= GlobalConstants.CleListe.CLASSE %>'/>
          </html:select></TD>
        <TD align="right" colspan="2">
          <TABLE cellpadding="2" cellspacing="0" border="0">
            <TR>
                <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><cardex:SecuriteLibelle nomFormulaire="societe" propertyFormulaire="motPasse" key='password'/>&nbsp;</TD>
                <TD VALIGN="top"><myriap:password name='societe' property="motPasse" style="HEIGHT: 22px; WIDTH: 115px" maxlength="20"/>&nbsp;</TD>
            </TR>
          </TABLE>
        </TD>
        <TD>&nbsp;</TD>
      </TR>
 
      <TR>
        <TD ALIGN="right"><b><bean:message key='v_su_nom_t'/></b></TD>
        <TD ALIGN="left">
  			 <logic:equal name='societe' property='indicateurRdd' value='true' >
		     	<bean:write name="societe" property="nom"/>
		     </logic:equal>
   			 <logic:equal name='societe' property='indicateurRdd' value='false' >
		        <myriap:text name='societe' property='nom' tabindex="2" maxlength="50" style="HEIGHT: 20px; WIDTH: 160px" />
		     </logic:equal>
		</TD>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_1_t'/></b></TD>
        <TD>
         	 <logic:equal name='societe' property='indicateurRdd' value='true' >
		     	<bean:write name="societe" property="reference1"/>
		     </logic:equal>
   			 <logic:equal name='societe' property='indicateurRdd' value='false' >
		        <myriap:text name='societe' property='reference1' tabindex="9" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px; " />
			 </logic:equal>
	    </TD>
        <TD ALIGN="right" colspan="2">
          <TABLE cellpadding="2" cellspacing="0" border="0">
            <TR>
                <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><cardex:SecuriteLibelle nomFormulaire="societe" propertyFormulaire="confirmationMotPasse" key='password_confirm'/>&nbsp;</TD>
                <TD VALIGN="top"><myriap:password name='societe' property="confirmationMotPasse" style="HEIGHT: 22px; WIDTH: 115px" maxlength="20"/>&nbsp;</TD>
            </TR>
          </TABLE>
        </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_nom_t'/></b></TD>
        <TD ALIGN="left">
  			 <logic:equal name='societe' property='indicateurRdd' value='true' >
		     	<bean:write name="societe" property="referenceNom"/>
		     </logic:equal>
   			 <logic:equal name='societe' property='indicateurRdd' value='false' >
          		<myriap:text name='societe' property='referenceNom' tabindex="3" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px" />
			 </logic:equal>
        </TD>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_2_t'/></b></TD>
        <TD ALIGN="left">
	        <myriap:text name='societe' property='reference2' tabindex="9" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px; background-color: #FFB6C1;" />
	    </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right"><b><bean:message key='v_so_reference_prenom_t'/></b></TD>
        <TD ALIGN="left">
  			 <logic:equal name='societe' property='indicateurRdd' value='true' >
		     	<bean:write name="societe" property="referencePrenom"/>
		     </logic:equal>
   			 <logic:equal name='societe' property='indicateurRdd' value='false' >
 		        <myriap:text name='societe' property='referencePrenom' tabindex="4" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px" />
			 </logic:equal>
        </TD>
        <TD ALIGN="right"><b><bean:message key='i_ls_cle_t'/></b></TD>
        <TD ALIGN="left"><html:select size='1' name='societe' property="langue" tabindex="11"
          style="HEIGHT: 20px; WIDTH: 160px" >
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.LANGUE %>'/>
          </html:select></TD>
        <TD ALIGN="right" ><b><bean:message key='i_cc_cle_t'/></b></TD>
        <TD ALIGN="left">
          <html:select size='1' name='societe' property='confidentialite' tabindex="12" style='HEIGHT: 20px; WIDTH: 121px' onchange="doConfidentialite8();" >
  	      	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
				actionSecurite='<%=actionSecurite%>' />
          </html:select></TD>
        <TD>&nbsp;</TD>
      </TR>

      <TR>
        <TD ALIGN="right" nowrap><b><bean:message key='v_so_raison_sociale_t2'/></b></TD>
        <TD ALIGN="left"><myriap:text name='societe' property="raisonEtre" maxlength="50" tabindex="5" style="HEIGHT: 20px; WIDTH: 160px; background-color: #FFB6C1;" /></TD>
        <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
        <TD ALIGN="left"><html:select size='1' name='societe' property="statut" tabindex="10"
          style="HEIGHT: 20px; WIDTH: 160px" >
          	<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.SOCIETE %>'/>
          </html:select></TD>
        <TD ALIGN="right"><cardex:SecuriteLibelle nomFormulaire="societe" propertyFormulaire="severite" key='i_se_cle_inv'/></TD>
        <TD ALIGN="left"><myriap:select name='societe' property="severite" tabindex="13"
          style="HEIGHT: 20px; WIDTH: 121px" >
	           <cardex:severiteOptions  />
          </myriap:select>
        </TD>
        <TD>&nbsp;</TD>
      </TR>


      <TR>
        <TD ALIGN="right" ><b><bean:message key='d_so_date_fondation_t'/></b></TD>
        <TD ALIGN="left">
          <cardex:Date name='societe' property="dateDeFondation" nomProchainChamp="severite" calendrier="true" tabindex="7" />
        </TD>
        <TD>&nbsp;</TD>
        <TD>&nbsp;</TD>
        <TD ALIGN="right"><b><bean:message key='i_se_cle_inv_casino_t'/></b></TD>
        <TD ALIGN="left"><myriap:select name='societe' property="severiteCasino" tabindex="13"
          style="HEIGHT: 20px; WIDTH: 121px" >
	           <cardex:severiteOptions  />
          </myriap:select>
        </TD>
        <TD>&nbsp;</TD>
      </TR>
	  <TR>
	    <TD colspan="7" align="center">
	      <table cellpadding="0" cellspacing="7" border="0" class="tableOutline">
	      	<TR>
	      		<TD align="right"><STRONG><bean:message key='centre.regional'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<bean:write name="societe" property="centreRegional"/>
				     	&nbsp;
				     	<bean:write name="societe" property="centreRegionalDescription"/>
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
				     	<myriap:text name="societe" property="centreRegional" maxlength="7" style="HEIGHT: 20px; WIDTH: 60px"/>
				     	&nbsp;
				     </logic:equal>
				 </TD>
	      		<TD align="right"><STRONG><bean:message key='actif'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<myriap:checkbox name='societe' property="actif" disabled="true" />
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
				     	<myriap:checkbox name='societe' property="actif" />
				     </logic:equal>
				 </TD>
			</TR>
	      	<TR>
	      		<TD align="right"><STRONG><bean:message key='district'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<bean:write name="societe" property="district"/>
						&nbsp;
				     	<bean:write name="societe" property="districtDescription"/>
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
				     	<myriap:text name="societe" property="district" maxlength="7" style="HEIGHT: 20px; WIDTH: 60px"/>
				     	&nbsp;
				     </logic:equal>
				 </TD>
	      		<TD align="right"><STRONG><bean:message key='date.desactivation'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<bean:write name="societe" property="dateInactif"/>
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
						<cardex:Date name='societe' property="dateInactif" nomProchainChamp="codeCompte" calendrier="true" />
				     </logic:equal>
				 </TD>
			</TR>
	      	<TR>
	      		<TD align="right"><STRONG><bean:message key='code.compte'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<bean:write name="societe" property="codeCompte"/>
						&nbsp;
				     	<bean:write name="societe" property="codeCompteDescription"/>
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
				     	<myriap:text name="societe" property="codeCompte"  maxlength="4" style="HEIGHT: 20px; WIDTH: 60px" />
				     	&nbsp;
				     </logic:equal>
				 </TD>
	      		<TD align="right"><STRONG><bean:message key='raison.desactivation'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
	      			 <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<bean:write name="societe" property="raisonDesactivation"/>
				     </logic:equal>
	      			 <logic:equal name='societe' property='indicateurRdd' value='false' >
				     	<myriap:text name="societe" property="raisonDesactivation"  maxlength="60" style="HEIGHT: 20px; WIDTH: 50px"/>
				     </logic:equal>
				 </TD>
			</TR>
	      	<TR>
	      		<TD align="right"><STRONG><bean:message key='methode.echantillonnage'/><bean:message key='2.points'/></STRONG></TD>
	      		<TD>
			          <html:select size='1' name='societe' property='echantillonnage' tabindex="12" style='HEIGHT: 20px; WIDTH: 60px' >
			  	      	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
							valeurTableValeur='<%=GlobalConstants.TableValeur.METHODE_ECHANTILLONNAGE %>' 
							actionSecurite='<%=actionSecurite%>' />
			          </html:select></TD>
				 </TD>
	      		<TD colspan="2">&nbsp;
			    </TD>
			</TR>
	      </table>
	    </TD>
	  </TR>
  	  <TR>
		<TD colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" />
		</TD>
 	  </TR>
			
    <logic:equal name='societe' property='new' value='false' >
      <TR>
      	<TD colspan="7"  ALIGN="center">
	      <TABLE cellspacing="3" >
	        <TR>
			    <TD nowrap>
				    	<b><bean:message key='i_si_cle_t2'/></b>
						<cardex:afficherValeurListeTag name="societe" property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=actionSecurite%>' />
						&nbsp;
						<b><bean:message key='d_date_creation_t'/></b>
						<bean:write name="societe" property="dateCreation" />
			    </TD>
			    <TD align="right" nowrap>
						<b><bean:message key='v_cree_par_t'/></b>
				</TD>
				<TD>
						<cardex:afficherValeurListeTag name="societe" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' actionSecurite='<%=actionSecurite%>' />
				</TD>
	        </TR>
	      </TABLE>
	     </TD>
	    </TR>
	</logic:equal>

      <TR>
            <TD COLSPAN="7">
				     <logic:equal name='societe' property='indicateurRdd' value='true' >
				     	<STRONG><bean:message key='indicateur.rdd'/></STRONG>
				     </logic:equal>
		    </TD>
	  </TR>
	  <TR>
            <TD colspan="7" align="center" width="100%" HEIGHT="40">
	               <cardex:button soumettre='<%=soumettreSauvegarde%>' labelKey='cb_ok_dossier' style='width: 120px; text-align: center;'/>
	        </TD>
      </TR>
     </TABLE>
     <TABLE width="850" cellpadding="3" cellspacing="2" border="0" style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');" CLASS="tableOutline">
         <TR>   
          <TD>&nbsp;
	              <logic:equal name='societe' property='new' value='false' >
		              <cardex:button securityConstraint='cardex.societe.base.imprimer' style="width: 60px; text-align: center;" labelKey='cb_imprimer'  onclick='doPrint();' />&nbsp;
		    			<cardex:securityDefineTag nameDefine="copieSociete" urlSecurite="/societe/copier.do">
		                  Numéro de détaillant destinataire :&nbsp; <input type="text" name="detaillant" width="20" >&nbsp;
		                  <input type="button" value="Copier" style="width: 60px; text-align: center;" onclick="doCopier();">
		                </cardex:securityDefineTag>
                  </logic:equal>
          </TD>
          <TD ALIGN="right" COLSPAN="5">
	              <logic:equal name='societe' property='new' value='false' >
				     <cardex:button securityConstraint="cardex.acces.selectAccesSociete" labelKey='cb_createur' style="width: 100px; text-align: center;" onclick='doAuditAcces();' />&nbsp;
				     <cardex:button securityConstraint="cardex.societe.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
                     <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                  </logic:equal>
                  <logic:equal name='societe' property='new' value='true' >
                      	<cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                  </logic:equal>
          </TD>
      </TR>

    </TABLE>
    <!-- END KIND & NATURE SEARCH FIELDS -->

    </TD>
  </TR>
</TABLE>
<!-- END OUTLINE TABLE -->
<html:hidden name='societe' property="cle" />
<html:hidden name='societe' property="new" />
<html:hidden name='societe' property="site" />
<html:hidden name='societe' property="indicateurRdd" />
<html:hidden name='societe' property="actif" />

<logic:present name='societe' property="entiteCardexLiaison">
	<html:hidden name='societe' property="entiteCardexLiaison.cle" />
	<html:hidden name='societe' property="entiteCardexLiaison.site" />
	<html:hidden name='societe' property="role" />
</logic:present>

<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
