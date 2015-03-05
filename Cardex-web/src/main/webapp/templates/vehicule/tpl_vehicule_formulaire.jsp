<%-- --------------------------------------------------------------------------
Use case    : Consultation d'un véhicule.
Description : Module d'affichage représentant le formulaire de consultation d'un
              véhicule.
Author(s)   : $Author: fguerin $, fguerin
Revision    : $Revision: 1.8 $, $Date: 2002/04/30 13:20:24 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron 
Ajustement des commentaires de la page JSP.

Revision: 1.2 , Date: 2002/02/14 19:58:57 , Author: abruno-boucher
Création du contenu RESULTAT VEHICULE.

Revision: 1.6 , Date: 2002/03/25 16:12:33 , Author: abruno-boucher
Mise à jour nouvel Assistant date-heure (incorporé dans la page).

$Revision: 1.8 $, $Date: 2002/04/30 13:20:24 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de sauvegarder (permis seulement si le site est le même 
     indépendamment de la règle ClearTrust) -->
     
<tiles:useAttribute name="urlSoumettreSauvegarde" id="urlSoumettreSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<!-- Following table is used to produce an outline -->
<TABLE cellpadding="1" cellspacing="0" border="0" >
<TR>
    <TD CLASS="tabTitle">
        <TABLE  width="800" cellpadding="2" cellspacing="0" border="0" CLASS="tabBackgroundEdit">
        <!-- First row uses transparent pixel to force good alignment -->
        <TR>
            <TD ALIGN="center" COLSPAN="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>
        <TR>
            <TD ALIGN="right"><b><bean:message key='co_no_fiche_t'/></b></TD>
            <TD ALIGN="left">
            	<bean:write name='vehicule' property="cle"/>
            	<bean:write name='vehicule' property="site"/> 
            </TD>
            <TD ALIGN="right"><b><bean:message key='c_ve_annee_t'/></b></TD>
            <TD ALIGN="left" ><myriap:text name='vehicule' property="annee" size="4" maxlength="4" style="HEIGHT: 20px; WIDTH: 40px" tabindex="5" />
            </TD>
            <TD ALIGN="left"><b><bean:message key='d_expiration_t'/></b></TD>
            <TD ALIGN="right" VALIGN="top" colspan="2">
                <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                    <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><cardex:SecuriteLibelle nomFormulaire="vehicule" propertyFormulaire="motPasse" key='password'/>&nbsp;</TD>
                    <TD VALIGN="top"><myriap:password name='vehicule' property="motPasse" style="HEIGHT: 22px; WIDTH: 120px" maxlength="20"/>&nbsp;</TD>
                </TR>
                </TABLE>
            </TD>
            <TD>&nbsp;</TD>
        </TR>
 
        <TR>
            <TD ALIGN="right"><b><bean:message key='i_ma_cle_t'/></b></TD>
            <TD ALIGN="left"><myriap:select name='vehicule' property="marque" size="1" style="HEIGHT: 20px; WIDTH: 120px" onchange="doRafraichir('modele',this.value);" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" tabindex="1" >
                   <cardex:optionTag classe='<%= GlobalConstants.CleListe.MARQUE %>'/>
                </myriap:select>
            <TD ALIGN="right"><b><bean:message key='v_ve_vignette_t'/></b></TD>
            <TD ALIGN="left" VALIGN="top"><myriap:text name='vehicule' property="vignette" maxlength="15" style="HEIGHT: 20px; WIDTH: 120px" tabindex="6" />
            </TD>
            <TD ALIGN="left" VALIGN="top" nowrap>
              <myriap:text name='vehicule' property="dateExpirationVignette" maxlength="10" style="HEIGHT: 20px; WIDTH: 60px" tabindex="7" />
              <html:link href="javascript:openDate('document.forms(0).dateExpirationVignette',document.forms(0).dateExpirationVignette.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif" border="0" />
              </html:link>
            <TD ALIGN="right" VALIGN="top" colspan="2">
                <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                    <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><cardex:SecuriteLibelle nomFormulaire="vehicule" propertyFormulaire="confirmationMotPasse" key='password_confirm'/>&nbsp;</TD>
                    <TD VALIGN="top"><myriap:password name='vehicule' property="confirmationMotPasse" style="HEIGHT: 22px; WIDTH: 120px" maxlength="20"/>&nbsp;</TD>
                </TR>
                </TABLE>
            </TD>
            <TD ALIGN="left">&nbsp;</TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='i_md_cle_t'/></b></TD>
            <TD ALIGN="left" nowrap>
            	<bean:define id="marque" name='vehicule' property="marque" type="String"/>
            	<myriap:select name='vehicule' property="modele" size="1" style="HEIGHT: 20px; WIDTH: 120px" tabindex="2" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" onblur="doVerificationVehicule();" >
                   <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.ModeleCleMultiListeCache" valeurDiscriminant='<%=marque%>'/>
                </myriap:select>
          	<img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Consultation" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
            </TD>
            <TD ALIGN="right"><b><bean:message key='v_ve_numero_serie_t'/></b></TD>
            <TD ALIGN="left" ><myriap:text name='vehicule' property="numeroSerie" size="25" maxlength="25" style="HEIGHT: 20px; WIDTH: 120px" tabindex="8" />
            </TD>
            <TD ALIGN="left"><b><bean:message key='d_expiration_t'/></b></TD>
            <TD ALIGN="right" colspan="2">&nbsp;
            </TD>
        </TR>

        <TR>
            <TD ALIGN="right"><b><bean:message key='v_ve_immatriculation_t'/></b></TD>
            <TD ALIGN="left"><myriap:text name='vehicule' property="immatriculation" maxlength="15" style="HEIGHT: 20px; WIDTH: 120px" tabindex="3" onkeydown="return isNumericImmatriculationTag(event.keyCode);" onkeyup="toUpper(this);keyShiftUP(event.keyCode);" onblur="doVerificationVehicule();" />
              </TD>
            <TD ALIGN="right"><b><bean:message key='v_ve_assureur_t'/></b></TD>
            <TD ALIGN="left" ><myriap:text name='vehicule' property="assurance" maxlength="40" style="HEIGHT: 20px; WIDTH: 120px" tabindex="9" /></TD>
            <TD ALIGN="left" VALIGN="top" nowrap>
              <myriap:text name='vehicule' property="dateExpirationAssurance" maxlength="10" style="HEIGHT: 20px; WIDTH: 60px" tabindex="10" />
              <html:link href="javascript:openDate('document.forms(0).dateExpirationAssurance',document.forms(0).dateExpirationAssurance.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif" border="0" />
              </html:link>
	    <TD ALIGN="right" colspan="2">&nbsp;
	    </TD>
        </TR>
        <TR>
	    <TD align="right" nowrap><b><bean:message key='l_pr_cle_t'/></b></TD>
	    <TD ALIGN="left">
             <html:select name='vehicule' property="cleProvince" size="1" style="HEIGHT: 20px; WIDTH: 120px" tabindex="4" >
                  <cardex:optionTag classe='<%= GlobalConstants.CleListe.PROVINCE_SANS_REQUIS %>' />
             </html:select>
        </TD>
            <TD ALIGN="right"><b><bean:message key='v_ve_police_t'/></b></TD>
            <TD ALIGN="left" ><myriap:text name='vehicule' property="police" maxlength="25" style="HEIGHT: 20px; WIDTH: 120px" tabindex="11" /></TD>
            <TD ALIGN="left">&nbsp;</TD>
            <TD ALIGN="right" VALIGN="top" colspan="2">
                <TABLE cellpadding="0" cellspacing="0" border="0">
                <TR>
                    <TD ALIGN="right" VALIGN="top"><html:img page="/images/blank.gif" width="1" height="5" border="0" /><BR><b><bean:message key='i_cc_cle_t'/>&nbsp;</b></TD>
                    <TD VALIGN="top">
                    	<html:select name='vehicule' property="confidentialite" size="1" style="HEIGHT: 20px; WIDTH: 120px" tabindex="12" onchange="doConfidentialite8();" >
                            <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
								valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
								actionSecurite='<%=actionSecurite%>' />
                		</html:select>&nbsp;
                </TD>
                </TR>
                </TABLE>
            </TD>
        </TR>
        <TR>
   			<td align="right" nowrap><b><bean:message key='v_ad_commentaire_t'/></b></td>
   			<td colspan="5">
				<myriap:text name='vehicule' property="commentaire" maxlength="250" style="HEIGHT: 20px; WIDTH: 550px" tabindex="21" />
			</td>
		</TR>
    <logic:equal name='vehicule' property='new' value='false' >
      <TR>
      	<TD colspan="7"  ALIGN="center">
	      <TABLE cellspacing="3" >
	        <TR>
			    <TD nowrap>
				    	<b><bean:message key='i_si_cle_t2'/></b>
						<cardex:afficherValeurListeTag name="vehicule" property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=actionSecurite%>' />
						&nbsp;
						<b><bean:message key='d_date_creation_t'/></b>
						<bean:write name="vehicule" property="dateCreation" />
			    <TD align="right" nowrap>
						<b><bean:message key='v_cree_par_t'/></b>
				</TD>
				<TD>
						<cardex:afficherValeurListeTag name="vehicule" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' actionSecurite='<%=actionSecurite%>' />
				</TD>
	        </TR>
	      </TABLE>
	     </TD>
	    </TR>
	</logic:equal>
        <TR>
            <TD COLSPAN="7">
              <TABLE>
                <TR>
                   <TD align="center" WIDTH="800" HEIGHT="20">
	                    <cardex:button soumettre='<%=urlSoumettreSauvegarde%>' labelKey='cb_ok_dossier' style='width: 120px; text-align: center;'/>
	               </TD>
	            </TR>
	          </TABLE>
            </TD>
	  <TR>
		<TR>
			<TD colspan="7" align="center"><html:img page="/images/0061CFpixel.gif" width="780" height="1" border="0" />
			</TD>
		</TR>
     </TABLE>
     <TABLE width="800" cellpadding="3" cellspacing="2" border="0" CLASS="tabBackgroundEdit">
            <TD ALIGN="right">
                <TABLE cellpadding="5" cellspacing="0" border="0" >
                <TR>
                    <TD>
		               <logic:equal name='vehicule' property='new' value='false' >
					     <cardex:button securityConstraint="cardex.acces.selectAccesVehicule" labelKey='cb_createur' style="width: 100px; text-align: center;" onclick='doAuditAcces();' />&nbsp;
					     <cardex:button securityConstraint="cardex.vehicule.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
                         <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                        </logic:equal>
                        <logic:equal name='vehicule' property='new' value='true' >
                            	<cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                        </logic:equal>
                    </TD>
                </TR>
                </TABLE>
            </TD>
        </TR>
        </TABLE>
        <!-- End entry fields -->
    </TD>
</TR>
</TABLE>
<!-- End Outline table -->
<html:hidden name='vehicule' property='cle' />
<html:hidden name='vehicule' property='site' />

<%--
<html:hidden name='vehicule' property='marque' />
<html:hidden name='vehicule' property='modele' />

--%>

<logic:present name='vehicule' property="entiteCardexLiaison">
	<html:hidden name='vehicule' property="entiteCardexLiaison.cle" />
	<html:hidden name='vehicule' property="entiteCardexLiaison.site" />
</logic:present>


<BR>
<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:180; height:20;" style="z-index: 1; position: absolute; left: 230px; top: 110px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='vehicule' property="modeleMarque" onchange="choisirModele(this.value);"
	  style="HEIGHT: 110px; WIDTH: 170px; background-color:beige" tabindex="4" >
	  <cardex:optionTag classe='<%= GlobalConstants.CleListe.MODELE_MARQUE %>'/>
	</html:select>          
</DIV>
<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />