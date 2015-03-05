<%-- --------------------------------------------------------------------------
Use case    : Impression du formulaire pour les dossiers avec inscription.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/05/01 19:33:53 $

History     : Voir ci-dessous.

$Revision: 1.10 $, $Date: 2002/05/01 19:33:53 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>

<title><bean:message key="titre.application.cardex"/></title>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->
<FORM action="#">

<DIV align="center" STYLE="overflow:none; width:100; height:100; background-color:transparent; z-index: 1; position: absolute; right: 100px; top: 1px;" >
     <html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/>
</DIV>

<!-- LOGO POSITIONING -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD valign="top"><html:img page="/images/lq-logo.gif" border="0" alt="logo" />
    </TD>
  </TR>

  <TR>
    <TD height="15" ><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
</TABLE>
<!-- END LOGO POSITIONING -->

<!-- PRESENTATION TEXT -->
<TABLE width="652" cellpadding="2" cellspacing="0" border="0">
  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3" class="tabSubject">
    	<bean:define id="genre" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='genre' type="String"/>
    	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
		valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
    	valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
    </TD>
    <TD>&nbsp;</TD>
  </TR>

  <TR>
    <TD colspan="5" height="15"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
  </TR>

</TABLE>
<!-- END PRESENTATION TEXT -->


<BR>
<!-- POSITIONING TABLE -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD width="30">&nbsp;</TD>
    <TD colspan="2" valign="top">

      <!-- DATA SECTION -->
      <TABLE width="645" cellpadding="2" cellspacing="0" border="0">
       <TR>
          <TD width="305" valign="top">

            <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                style="border-style: solid; border-width: 1px; border-color: #000000;">

             <TR>
                <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
             </TR>

             <TR>
                <TD width="125" align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_numero_dossier_t'/></b></TD>
                <TD width="175"><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroCardex' /></TD>
             </TR>

             <TR>
                <TD width="125" align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference1_t'/></b></TD>
                <TD width="175"><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='reference1' /></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_ancienne_reference_t'/></b></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroDossier' /></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_is_duree_t'/> : </b></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='mois' /></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_debut_t'/></b></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='dateDebut' /></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_fin_t'/></b></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='dateFin' /></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ty_cle_t'/></b></TD>
                <TD><bean:define id="nature" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' type="String"/>
                	<cardex:afficherValeurListeTag  name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='type' classe='<%=GlobalConstants.CleListe.TYPE %>' valeurDiscriminant='<%=nature%>'/>
                </TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ca_cle_t'/></b></TD>
                <TD><bean:define id="type" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='type' type="String"/>
                	<cardex:afficherValeurListeTag  name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='categorie' classe='<%=GlobalConstants.CleListe.CATEGORIE %>' valeurDiscriminant='<%=type%>'/>
                </TD>
             </TR>

             <TR>
                <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
             </TR>
            </TABLE>
	  </TD>
	  <TD>

      <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>' >
            <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                  style="border-style: solid; border-width: 1px; border-color: #000000;">

              <TR>
                 <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>

              <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>'>
              <TR>
                <TD width="125" align="right" class="errorHeader"><u><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t' /></u></TD>
                <TD width="175"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='nom' /></TD>
              </TR>

              <TR>
                <TD align="right" class="errorHeader"><u><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t' /></u></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='prenom' /></TD>
              </TR>

              <TR>
                <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="15" border="0" /></TD>
              </TR>

               <TR>
                  <TD width="125" align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_numero_t'/></b></TD>
                  <TD width="175"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='numeroFiche' /></TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_sx_cle_t'/></b></TD>
                  <TD>
                  	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.SUJET_KEY%>' property="sexe" classe='<%= GlobalConstants.CleListe.SEXE %>' />
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ra_cle_t'/></b></TD>
                  <TD>
	                  <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.RACE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='race'/>
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_naissance_t'/></b></TD>
                  <TD><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='dateNaissance' /></TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ls_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LANGUE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='langue'/>
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='c_su_assurance_sociale_t'/></b></TD>
                  <TD><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='numeroAssuranceSociale' /></TD>
               </TR>
	           <TR>   
	              <TD ALIGN="right"><b><bean:message key='v_su_permis_conduire_t'/></b></TD>
	              <TD ALIGN="left" ><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property="numeroPermisConduire" /></TD>
			  </TR>
              <TR>
                <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="15" border="0" /></TD>
              </TR>
                <logic:present name='<%=GlobalConstants.Impression.ADRESSE_KEY%>'>
                  <TR>
                    <TD align="right" valign="top"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_adresse_t' /></b></TD>
                    <TD width="210">
	                    <cardex:affichageAdresse name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' numeroLigne="1" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
	                    <cardex:affichageAdresse name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' numeroLigne="2" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
                    </TD>
                  </TR>
                  <TR>
                    <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_vi_cle_t' /></b></TD>
                    <TD>
		            	<bean:define id="pays" name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="pays" type="String"/>
		            	<bean:define id="province" name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="province" type="String"/>
		               	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>           
                    </TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_pr_cle_t' /></b></TD>
                    <TD>
	            		<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>
                    </TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_pa_cle_t' /></b></TD>
                    <TD>
                    	<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.PAYS %>' name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="pays"/>	
                    </TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t' /></b></TD>
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="codePostal"/></TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone1' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="telephone1"/></TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone2' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="telephone2"/></TD>
                  </TR>
                  <TR>
                    <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone3' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="telephone3"/></TD>
                  </TR>
                </logic:present>
                  <TR>
                     <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  </TR>
              </logic:present>
            </TABLE>
        </logic:present>

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
</TABLE>
<!-- END POSITIONING TABLE -->


<BR>
<!-- Sous-catégories -->
 <TABLE width="650" cellpadding="0" cellspacing="0" border="0">
 <TR>
     <TD class="errorHeader"><bean:message key='sous.categories'/></TD>
 </TR>
 <TR>
    <TD height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
 <logic:iterate id="element" name="dossier" property='listeSousCategories' indexId="index">
 <TR>
     <TD><bean:write name="element" property="description" /></TD>
 </TR>
 </logic:iterate>
 </TABLE>
 <br>
 
<!-- Suivis -->
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0">
    <TR>
      <TD width="652" colspan="6" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_suivis' /></TD>
    </TR>

    <TR>
      <TD width="652" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>

    <logic:iterate id="suivi" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='suivis'>
    <TR>
      <TD width="652" colspan="6">
         <TABLE>
           <TR>
			<td align="right" nowrap ><bean:message key='v_sv_intervenant_t'/></td>
			<td width="150">
		     <cardex:afficherValeurListeTag  name='suivi' property='intervenant' classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
            </td>
        	<td align="right" nowrap><bean:message key='d_sv_date_prevue_t2'/></td>
        	<td nowrap>
                   <bean:write name='suivi' property='datePrevue'  />
                </td>
            </TR>
            <TR>
        	<td  align="right" nowrap><bean:message key='date_completee_t'/></td>
        	<td nowrap>
                	<bean:write name='suivi' property='dateCompletee' />
                </td>
                <TD colspan="2" width="350" style='font-family: Verdana, Arial; font-size: 9pt; border:"0.02cm groove black"' >
          		<bean:write name="suivi" property="suivi" />
                </TD>
            </TR>
         </TABLE>
      </TD>
    </TR>
    <TR>
      <TD width="652" colspan="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>
    </logic:iterate>

  </TABLE>
<TABLE width="652">
  <TR>
    <TD align='right'><b>CDX_0223</b></TD>
  </TR>
</TABLE>
</P>

</FORM>

</BODY>
</HTML>