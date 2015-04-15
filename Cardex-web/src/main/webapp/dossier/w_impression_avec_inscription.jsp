<%-- --------------------------------------------------------------------------
Use case    : Impression du formulaire pour les dossiers avec inscription.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.10 $, $Date: 2002/05/01 19:33:53 $


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
<%@ page import="com.lotoquebec.cardex.presentation.model.form.NarrationForm" %>


<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>

<title><bean:message key="titre.application.cardex"/></title>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5"
oncontextmenu="return false;" >

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->
<FORM action="#">

<DIV align="center" STYLE="overflow:none; width:100; height:100; background-color:transparent; z-index: 1; position: absolute; right: 100px; top: 1px;" >
     <html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/>
</DIV>

<!-- LOGO POSITIONING -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD valign="top">
        <html:img page="/images/lq-logo.gif" border="0" alt="Loto-Québec" />
	</TD>
    <TD align="right" >&nbsp;</TD>
  </TR>

  <TR>
    <TD height="15" colspan="2"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
</TABLE>
<!-- END LOGO POSITIONING -->
<!-- PRESENTATION TEXT -->
<TABLE width="652" cellpadding="2" cellspacing="0" border="0">
  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3" class="tabSubject">
       	<bean:define id="genre" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="genre" type="String"/> 
       	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="nature" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
		valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
		valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
    </TD>
    <TD>&nbsp;</TD>
  </TR>

  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    <TD>&nbsp;</TD>
  </TR>

  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3">
      <P align="justify">
      <logic:notEqual name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
        <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' value='<%=String.valueOf(GlobalConstants.Nature.ACCES_INTERDIT)%>' >
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    La personne inscrite sur ce document n'a plus accès aux casinos d'État opérés par la Société des casinos du Québec (SCQ) (casinos) et par la Société des établissements de jeux du Québec (SEJQ) (salons de jeux).
		  </logic:equal>
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		    Access to the State casinos operated by the Société des casinos du Québec (SCQ) (casinos) and by the Société des établissements de jeux du Québec (SEJQ) (salon de jeux) is forbidden to the person identified on this document.
		  </logic:equal>
	    </logic:equal>
	  </logic:notEqual>
      <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
        <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' value='<%=String.valueOf(GlobalConstants.Nature.ACCES_INTERDIT_LOTERIE)%>' >
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    La personne inscrite sur ce document n'a plus accès aux édifices corporatifs de Loto-Québec.
		  </logic:equal>
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		    Access to the corporate buildings of Loto-Québec is forbidden to the person identified on this document.
		  </logic:equal>
	    </logic:equal>
	  </logic:equal>
	  
      <logic:notEqual name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
        <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' value='<%=String.valueOf(GlobalConstants.Nature.AVIS_DE_GUET)%>' >
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    La personne inscrite sur ce document doit faire l'objet d'une surveillance accrue dans les  casinos d'État opérés par la Société des casinos du Québec (SCQ) (casinos) et par la Société des établissements du Québec (SEJQ) (salons de jeux). 
		  </logic:equal>
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		    The person identified on this document must be put under monitoring within the State casinos operated by the Société des casinos du Québec (SCQ) (casinos) and by the Société des établissement de jeux du Québec (SEJQ) (salon de jeux). 
		  </logic:equal>
	    </logic:equal>
	  </logic:notEqual>
      <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
        <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' value='<%=String.valueOf(GlobalConstants.Nature.AVIS_DE_GUET_LOTERIE)%>' >
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    La personne inscrite sur ce document doit faire l'objet d'une surveillance accrue dans les édifices corporatifs de Loto-Québec. 
		  </logic:equal>
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		    The person identified on this document must be put under monitoring within the corporate buildings of Loto-Québec. 
		  </logic:equal>
	    </logic:equal>
	  </logic:equal>
      </P>
    </TD>
    <TD>&nbsp;</TD>
  </TR>


  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="30" border="0" /></TD>
    <TD>&nbsp;</TD>
  </TR>


  <TR>
    <TD width="10">&nbsp;</TD>
    <TD width="92"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='signatures_t'/></b></TD>
    <TD width="270" valign="bottom"><html:img page="/images/pixelnoir.gif" width="256" height="1" border="0" /></TD>
    <TD width="270" align="right" ><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='approuve_le'/>&nbsp;_____/_____/_____</b></TD>
    <TD width="10">&nbsp;</TD>
  </TR>

  <TR>
    <TD width="10">&nbsp;</TD>
    <TD width="92">&nbsp;</TD>
    <TD width="270" valign="bottom"><b>&nbsp;<bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='directeur_t'/></b></TD>
    <TD width="270" align="right" >&nbsp;</TD>
    <TD width="10">&nbsp;</TD>
  </TR>

  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="35" border="0" /></TD>
    <TD>&nbsp;</TD>
  </TR>

  <TR>
    <TD width="10">&nbsp;</TD>
    <TD width="92"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='signatures_t'/></b></TD>
    <TD width="270" valign="bottom"><html:img page="/images/pixelnoir.gif" width="256" height="1" border="0" /></TD>
    <TD width="270" align="right" valign="bottom"><b>&nbsp;<bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_sv_date_creation_t'/>&nbsp;_____/_____/_____</b></TD>
    <TD width="10">&nbsp;</TD>
  </TR>

  <TR>
    <TD width="10">&nbsp;</TD>
    <TD width="92">&nbsp;</TD>
    <TD width="270" valign="bottom"><b>&nbsp;<bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_sv_demandeur'/>&nbsp;(<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="intervenant" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />)
    </b></TD>
    <TD width="270" align="right" >&nbsp;</TD>
    <TD width="10">&nbsp;</TD>
  </TR>

  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="15" border="0" /></TD>
    <TD>&nbsp;</TD>
  </TR>

  <TR>
    <TD>&nbsp;</TD>
    <TD class="errorHeader"><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_si_cle_inclus_t'/></TD>
      <logic:notEqual name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
	    <TD colspan="2">
	     <logic:present name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' >
	     <bean:write name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='stringSitesChoisis'/>
	    </logic:present>
	    </TD>
	  </logic:notEqual>
        <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='siteOrigine' value='8' >
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    <TD colspan="2">
				tous les édifices corporatifs de Loto-Québec, incluant Loto-Québec, Complexe multifonctionnel Loto-Québec (CMLQ), Technologies Nter, Casiloc, Société du Jeu virtuel du Québec inc., Contrôle de la qualité, T.V.A. (Tirages), Pierre-de-Coubertin, Édifice Pierre-Dupuis, Bureaux de région (Laval, Québec).
		    </TD>
		  </logic:equal>
		  <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		    <TD colspan="2">
		    	All Loto-Québec Corporate Buildings, including Loto-Québec, Complexe multifonctionnel Loto-Québec (CMLQ), Technologies Nter, Casiloc, Société du Jeu virtuel du Québec inc., Contrôle de la qualité, T.V.A. (Tirages), Pierre-de-Coubertin, Édifice Pierre-Dupuis, Bureaux de région (Laval, Québec).
		    </TD>
		  </logic:equal>
	    </logic:equal>
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
      <TABLE width="590" cellpadding="2" cellspacing="0" border="0">
       <TR>
          <TD width="305" valign="top">

            <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                style="border-style: solid; border-width: 1px; border-color: #000000;">

             <TR>
                <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
             </TR>

             <TR>
                <TD width="125" align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_numero_dossier_t'/></b></TD>
                <TD width="175"><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroCardexTexte' /></TD>
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
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_pe_cle_t'/></b></TD>
                <TD><cardex:afficherValeurListeTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PeriodeCleListeCache" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='periode'/></TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ty_cle_t'/></b></TD>
                <TD>
                	<bean:define id="nature" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='nature' type="String" />
                	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="type" classe='<%=GlobalConstants.CleListe.TYPE %>' valeurDiscriminant='<%=nature%>' />
                </TD>
             </TR>

             <TR>
                <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ca_cle_t'/></b></TD>
                <TD>
                	<bean:define id="type" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='type' type="String" />
                	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="categorie" classe='<%=GlobalConstants.CleListe.CATEGORIE%>' valeurDiscriminant='<%=type%>' />
                </TD>
             </TR>

             <TR>
                <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
             </TR>
            </TABLE>

            <BR>

      <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>' >
            <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                  style="border-style: solid; border-width: 1px; border-color: #000000;">

              <TR>
                 <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>

              <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>'>
              <TR>
                <TD width="150" align="right" class="errorHeader"><u><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t' /></u></TD>
                <TD width="150"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='nom' /></TD>
              </TR>

              <TR>
                <TD align="right" class="errorHeader"><u><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t' /></u></TD>
                <TD><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='prenom' /></TD>
              </TR>

               <TR>
                  <TD width="150" align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_numero_t'/></b></TD>
                  <TD width="150"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='numeroFiche' /></TD>
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
                  <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_nt_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='ethnie'/>
                  </TD>
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
                    <TD align="right">
                       <logic:notEqual name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='telephone1' value=''>
                           <b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone1' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                       </logic:notEqual> 
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="telephone1"/></TD>
                  </TR>
                  <TR>
                    <TD align="right">
                       <logic:notEqual name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='telephone2' value=''>
                    	   <b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone2' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                       </logic:notEqual>
                    <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="telephone2"/></TD>
                  </TR>
                  <TR>
                    <TD align="right">
                    	<logic:notEqual name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='telephone3' value=''>
                    		<b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone3' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                        </logic:notEqual>
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

          <TD width="285" align="right" valign="top">
          <TABLE>
              <logic:present name='<%=GlobalConstants.Impression.PHOTO_KEY%>' >
              <TR>
                  <!-- Les balises de paragraphes sont obligatoires -->
                  <P>
                  <TR>
                  <TD>
			<%-- Prévisualisation avec ImagXpress.ImagXpress (Pegasus ImagXpress Control v3.0)
			<OBJECT ID="Preview" WIDTH="320" HEIGHT="240"
				 CLASSID="CLSID:AAB6F9A1-C408-11D1-BC6D-00C0D1572A7B">
				    <PARAM NAME="_ExtentX" VALUE="5080">
				    <PARAM NAME="_ExtentY" VALUE="5080">
				    <PARAM NAME="Persistence" VALUE="0">
				    <PARAM NAME="AutoSize" VALUE="4">
				    <PARAM NAME="BackColor" VALUE="16777215">
				    <PARAM NAME="FileName" VALUE="<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='url' />">
			</OBJECT> --%>
                        <DIV align="center" STYLE="overflow:auto; width:320; height:240;" >
         			<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienElement' />&SITE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienSiteElement' />&EXTENSION=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='230'" >
         		</DIV>
                  </TD>
                  </TR>
              </logic:present>
              <logic:notPresent name='<%=GlobalConstants.Impression.PHOTO_KEY%>' >
              <TR>
                  <!-- Les balises de paragraphes sont obligatoires -->
                  <P>
                  <TR>
                  <TD>
                    <%-- html:img page="/images/no_picture.jpg" width="200" height="200" border="1" styleClass="tableOutline" alt="" / --%>
                  </TD>
                  </TR>
              </logic:notPresent>
          </TABLE>

            <BR>
              <!-- CARACTERISTICS -->
              <TABLE width="277" cellpadding="2" cellspacing="0" border="0">

                 <TR>
                    <TD width="27">&nbsp;</TD>
                    <TD width="250"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='caracteristiques_t'/></b></TD>
                 </TR>
              <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>' >
                 <TR>
                    <TD width="27">&nbsp;</TD>
                    <TD width="250">
					    <logic:iterate id="element" name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='caracteristiques.doubleListe.droiteColLabel'>
					       <%=element %>, &nbsp;
					    </logic:iterate>                          
                    </TD>
                 </TR>
              </logic:present>
              </TABLE>
              <!-- END CARACTERISTICS -->

            </TD>
          </TR>
      </TABLE>
      <!-- END DATA SECTION -->

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
</TABLE>
<!-- END POSITIONING TABLE -->

<BR>

<!-- Narrations -->
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0">
   <logic:present name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrations' >
    <TR>
      <TD width="652" colspan="6" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_commentaires' /></TD>
    </TR>

    <TR>
      <TD width="652" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>

    <logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrations'>
    <TR>
      <TD width="652" colspan="6">
          <TABLE>
            <TR>
              <TD class="pictureBackground"><FONT size="+2px">
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %></FONT>
              </TD>
            </TR>
          </TABLE>
      </TD>
    </TR>
    <TR>
      <TD width="652" height="15" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>
    </logic:iterate>
   </logic:present>
  </TABLE>
</P>
<TABLE width="652">
  <TR>
    <TD align='right'><b>CDX_0222&nbsp;-&nbsp;<bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroDossier' /></b></TD>
  </TR>
</TABLE>

</FORM>

</BODY>
</HTML>
