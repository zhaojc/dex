<%-- --------------------------------------------------------------------------
Use case    : Audit des changements d'adresses.
Description : Page principale dans laquelle est incorporée les différentes
              composantes relatives à la consultation d'un dossier.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.34 $, $Date: 2002/04/16 19:51:34 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.34 $, $Date: 2002/04/16 19:51:34 $, $Author: mlibersan $
Ajout des scripts de gestion de l'Assistant Date-Heure.
--------------------------------------------------------------------------- --%>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>
<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<HTML>
<HEAD>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<TITLE><bean:message key='audit.changement'/></TITLE>
</HEAD>

<BODY link="#095B97" leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" >
<TABLE cellpadding="1" cellspacing="0" border="0" >
<TR> 
    <TD align="center">
       <TABLE cellpadding="2" cellspacing="0" border="1">
         <TR>
            <TD width="770" align="center" height="40" valign="middle" class="tabTitleBig">
                <bean:message key='audit.changement'/> - <bean:message key='adresse_t'/> 
				<html:img page="/images/blank.gif" width="150" height="1" border="0" />
                <cardex:button securityConstraint='' labelKey='cb_imprimer' onclick='window.print();' />
            </TD>
         </TR>
         <TR>
            <TD width="770" align="left" height="25" valign="middle" class="tabTitleSmall">
				CDX_0180 Audit des changements des adresses
				<html:img page="/images/blank.gif" width="300" height="1" border="0" />
				Confidentiel
			</TD>
		</TR>
       </TABLE>
    </TD>
</TR>
<TR> 
    <TD align="center">
    <logic:iterate id='element' name='rechercheAdresses' property='listeResultatAudit.resultatAffichage' indexId="index">
        <TABLE  width="770" cellpadding="2" cellspacing="0" border="0">
        <TR>
           <TD ALIGN="center" COLSPAN="7">
	              <b><i><bean:message key='audit.date.changement'/><bean:message key='2.points'/>&nbsp;</i><bean:write name="element" property="dateChangement"/> - <i><bean:message key='audit.change.par'/><bean:message key='2.points'/>&nbsp;</i><bean:write name="element" property="changePar"/></b>
           </TD>
        </TR>
        <TR>
            <TD ALIGN="center" COLSPAN="7"><html:img page="/images/pixelnoir.gif" width="770" height="1" border="0" /></TD>
        </TR>
			<TR>
          		<td align="right" nowrap ><b><bean:message key='numero_municipal'/></b></td>
          		<td>
	            	<bean:write name='element' property='numeroMunicipal'/>
                </td>
          		<td align="right"><b><bean:message key='i_tr_cle_t'/></b></td>
				<td>
	                <cardex:afficherValeurListeTag name='element' property="typeRue" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache'/>
            	</td>
    	        <td colspan="4">&nbsp;</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap ><b><bean:message key='rue'/></b></td>
          		<td>
	            	<bean:write name='element' property='nomRue' />
                </td>
          		<td align="right"><b><bean:message key='i_ct_cle_t'/></b></td>
				<td>
					<cardex:afficherValeurListeTag name='element' property="pointCardinal" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache'/>
            	</td>
    	        <td colspan="4">&nbsp;</td>
          	</TR>
          	
			<TR>
          		<td align="right" nowrap ><b><bean:message key='i_tu_cle_t'/></b></td>
          		<td>
	                <cardex:afficherValeurListeTag name='element' property="unite" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache'/>
                </td>
          		<td align="right"><b><bean:message key='numero_unite'/></b></td>
			    <td>
    	        	<bean:write name='element' property='numeroUnite'/>
    	        </TD>
    	        <td colspan="4">&nbsp;</td>
          	</TR>

			<TR>
          		<td align="right" nowrap ><b><bean:message key='adresse_postale'/></b></td>
          		<td>
	            	<bean:write name='element' property='adressePostal'/>
                </td>
                <td colspan="5" rowspan="4" align="center"><b>
                	<bean:message key='telephones'/></b>
                	<TABLE cellpadding="0" cellspacing="0" border="1">
                	<tr><td align="right">
	                	<TABLE cellpadding="2" cellspacing="2">
	                	<tr>
				  			<td>
	                			<logic:notEqual name='element' property='telephone1' value="">
		                			<cardex:afficherValeurListeTag name='element' property="typeUtilTelephone1" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache'/>
				                    &nbsp;
				        	        <bean:message key='2.points'/>
				        	        <bean:write name='element' property='telephone1'/>
			        	        </logic:notEqual>
			        	    </td>                
	                	</tr>
	                	<tr>
				  			<td>
	                			<logic:notEqual name='element' property='telephone2' value="">
		                			<cardex:afficherValeurListeTag name='element' property="typeUtilTelephone2" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache'/>
				                    &nbsp;
				        	        <bean:message key='2.points'/>
			        	            <myriap:text name='element' property='telephone2'/>
			        	        </logic:notEqual>
		            	    </td>
	                	</tr>
	                	<tr> 
				  			<td>
	                			<logic:notEqual name='element' property='telephone3' value="">
		                			<cardex:afficherValeurListeTag name='element' property="typeUtilTelephone3" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache'/>
				                    &nbsp;
				        	        <bean:message key='2.points'/>
			        	            <myriap:text name='element' property='telephone3'/>
			        	        </logic:notEqual>
		            	    </td>                 	
	                	</tr>
	                	</TABLE>
                	</td></tr>
                	</TABLE>
                </td>
          	</TR>
              <TR>
          			<td align="right" nowrap ><b><bean:message key='i_pa_cle_t'/></b></td>
          			<td colspan="6">
               			<cardex:afficherValeurListeTag name='element' property="pays" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache'/>
                    </td>
          	  </TR>
              <TR>
          			<td align="right" nowrap ><b><bean:message key='l_pr_cle_t'/></b></td>
          			<td colspan="6">
               			<cardex:afficherValeurListeTag name='element' property="province" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache'/>
                 	</td>
          		</TR>

          		<TR>
          			<td align="right" nowrap ><b><bean:message key='l_vi_cle_t'/></b></td>
          			<td colspan="6">
  			  			<bean:define id="province" name='element' property='province' type="String"/> 
               			<cardex:afficherValeurListeTag name='element' property="ville" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache' valeurDiscriminant='<%=province%>'/>
                     </td>
          		</TR>

          		<TR>
          			<td align="right" nowrap ><b><bean:message key='v_ad_code_postal_t'/></b></td>
          			<td>
                       <bean:write name='element' property='codePostal' />
                    </td>
          			<td align="right"><b><bean:message key='i_st_cle_t'/></b></td>
          			 <td>
               			<cardex:afficherValeurListeTag name='element' property="statut" classe='com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.ADRESSE%>'/>
	                </td>
	                <td colspan="3">&nbsp;</td>                    
          		</TR>
          		<TR>
          			<td align="right" nowrap ><b><bean:message key='adresse_electronique_1'/></b></td>
          			<td colspan="6">
                       <bean:write name='element' property='adresseElectronique1'/>
                    </td>
          		</TR>
          		
          		<TR>
          			<td align="right" nowrap ><b><bean:message key='adresse_electronique_2'/></b></td>
          			<td colspan="6">
                       <bean:write name='element' property='adresseElectronique2'/>
                    </td>
          		</TR>

          		
              <TR>
          			<td align="right" nowrap ><b><bean:message key='v_ad_commentaire_t'/></b></td>
          			<td colspan="6">
                       <myriap:textarea name='adresse' property='commentaire' tabindex="21" rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' disabled='true' />
                    </td>
          	 </TR>

          	 <TR>
          	 <TD align="center" colspan="7">

          		    <TABLE width="600" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
                 		<TR>
                			<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1"  border="0" /></TD>
              		    </TR>

                 		<TR>
                 			<td align="right" nowrap><bean:message key='v_cree_par_t'/></td>
                 			<td>
							    <cardex:afficherValeurListeTag name="element" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>
                	        </td>
                 			<td align="right" nowrap><bean:message key='v_tr_modifie_par_t2'/></td>
                 			<td>
							    <cardex:afficherValeurListeTag name="element" property="modificateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>&nbsp;
    	                   </td>
                 		</TR>
                 		<TR>
                 			<td align="right" nowrap><bean:message key='d_date_creation_t'/></td>
                 			<td>
                            	<bean:write name='element' property='dateCreation'/>
                            </td>
                 			<td align="right" nowrap><bean:message key='d_ad_date_modification_t'/></td>
                 			<td>
                            	<bean:write name='element' property='dateModification'/>&nbsp;                                           
	                       </td>
                 		</TR>
		     		</TABLE>
		    </TD>
		</TR>
		<TR>
		    <TD ALIGN="center" COLSPAN="7" height="30px"><html:img page="/images/pixelnoir.gif" width="770" height="1" border="0" /></TD>
		</TR>
	</TABLE>
    </logic:iterate>
</TD>
</TR>
</TABLE>    

</BODY>
</HTML>