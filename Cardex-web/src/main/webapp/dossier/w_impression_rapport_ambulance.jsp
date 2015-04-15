<%-- --------------------------------------------------------------------------
Use case    : Impression du rapport des narrations avec "ambulance".
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.12 $, $Date: 2002/05/01 19:33:53 $

History     : Voir ci-dessous.

$Revision: 1.12 $, $Date: 2002/05/01 19:33:53 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/taglibs-datetime.tld'  prefix='dt' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.NarrationForm" %>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>

<title>Cardex 2002 Internet</title>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<H3> Rapport sur les interventions d'ambulance <br>
     Impression des dossiers </H3><br>
<b><bean:message key='en_date_t'/>&nbsp;<dt:format pattern="EEEE d MMMM yyyy" locale="true"><dt:currentTime/></dt:format> </b><br>   
<b><bean:message key='d_date_debut_ajout_t'/>&nbsp;<bean:write name="rapportDossierAmbulance" property="dateDebutDu" /></b>&nbsp;
<b><bean:message key='d_date_fin_ajout_t'/>&nbsp;<bean:write name="rapportDossierAmbulance" property="dateDebutAu" /></b>

<logic:iterate id="dossiers" name='rapportGlobal'>
<logic:present name="dossiers">

<!-- Table globale pour permettre un saut de page avant l'impression de chaque rapport -->
<TABLE style="page-break-before:always"><TR><TD>

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->
<FORM action="#">

<!-- Affichage du logo -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0" >
  <TR>
    <TD valign="top">
        <!-- html:img page="/images/lq_societe_casinos.jpg" border="0" alt="Société des casinos" / -->
    </TD>
    <TD align="right" >
      &nbsp;
    </TD>
  </TR>
  <TR>
    <TD colspan="2" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
</TABLE>

<!-- Description du dossier -->
<TABLE width="650" cellpadding="2" cellspacing="0" border="" rules="none">
  <TR>
    <TD width="300" valign="bottom" class="tabSubject">&nbsp;
	    <bean:define id="genre" name='dossiers' property='genre' type="String"/>
		<cardex:afficherValeurListeTag name='dossiers' property='nature' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
            valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
			valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
    </TD>
    <TD width="230" align="right" valign="bottom" class="errorHeader">
      <bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_numero_t'/>&nbsp;
    </TD>
    <TD width="120" valign="bottom">
    	<bean:write name='dossiers' property="numeroDossier"/>
    </TD>
  </TR>
  <TR>
    <TD width="300">&nbsp;</TD>
    <TD width="230">&nbsp;</TD>
    <TD width="120" valign="top">
        <bean:write name='dossiers' property="numeroCardexTexte"/>
    </TD>
  </TR>
</TABLE>

<BR>
<TABLE width="650" cellpadding="2" cellspacing="0" border="" rules="none">
  <TR>
    <TD colspan="3"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>

  <TR>
    <TD width="420" valign="top">
      <!-- NATURE -->
      <TABLE width="420" cellpadding="2" cellspacing="0" border="0">
        <TR>
          <TD colspan="5"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD colspan="3" class="errorHeader"><u><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='nature_t2' /></u></TD>
          <TD>&nbsp;</TD>
        </TR>

        <TR>
          <TD width="8">&nbsp;</TD>
          <TD width="90" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ty_cle_t'/></b></TD>
          <TD class="footnotes">
          	<bean:define id="nature" name='dossiers' property='nature' type="String" />
			<cardex:afficherValeurListeTag name="dossiers" property="type" classe='<%=GlobalConstants.CleListe.TYPE %>' valeurDiscriminant='<%=nature%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
          </TD>
          <TD width="90" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_debut_t'/></b></TD>
          <TD nowrap="nowrap">
		         <bean:write name='dossiers' property="dateDebut"/>
          </TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ca_cle_t'/></b></TD>
          <TD class="footnotes">
          	<bean:define id="type" name='dossiers' property='type' type="String" />
			<cardex:afficherValeurListeTag name="dossiers" property="categorie" classe='<%=GlobalConstants.CleListe.CATEGORIE %>' valeurDiscriminant='<%=type%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
          </TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_fin_t'/></b></TD>
          <TD>
			  <bean:write name='dossiers' property="dateFin"/>
		  </TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_st_cle_t'/></b></TD>
          <TD class="footnotes">
                <cardex:afficherValeurListeTag name='dossiers' property='statut' classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
          </TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference1_t'/></b></TD>
          <TD>
          	<bean:write name='dossiers' property="reference1"/>
          </TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_se_cle_t'/></b></TD>
          <TD class="footnotes">
                <cardex:afficherValeurListeTag name='dossiers' property='severite' classe='<%= GlobalConstants.CleListe.SEVERITE %>' />
          </TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference2_t'/></b></TD>
          <TD>
          	<bean:write name='dossiers' property="reference2"/>
          </TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_ref_video_t'/> : </b></TD>
          <TD class="footnotes">
                <cardex:afficherValeurListeTag name='dossiers' property='referenceVideo' classe='<%= GlobalConstants.CleListe.REFERENCE_VIDEO %>' />
          </TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference3_t'/></b></TD>
          <TD>
          	<bean:write name='dossiers' property="reference3"/>
          </TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference_video_t'/></b></TD>
          <TD class="footnotes">
                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.REFERENCE_VIDEO %>' name='dossiers' property='typeVideo'/>
          </TD>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_sv_intervenant_t'/></b></TD>
          <TD class="footnotes">
                <cardex:afficherValeurListeTag name="dossiers" property="intervenant" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
          </TD>
        </TR>

      </TABLE>
      <!-- END NATURE -->
    </TD>

    <TD width="1"><html:img page="/images/pixelnoir.gif" width="1" height="160" border="0" /></TD>

    <TD width="229" valign="top">
      <!-- SITE -->
      <TABLE width="229" cellpadding="2" cellspacing="0" border="0">
        <TR>
          <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>

        <TR>
          <TD colspan="2" class="errorHeader">&nbsp;<u><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='lieu_t2'/></u></TD>
          <TD>&nbsp;</TD>
        </TR>

        <TR>
          <TD width="100" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_si_cle_t'/> : </b></TD>
          <TD width="140" class="footnotes">
          		<bean:define id="entite" name='dossiers' property="entite" type="String"/>
                <cardex:afficherValeurListeTag name="dossiers" property="siteOrigine" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' valeurDiscriminant='<%=entite%>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
                </TD>
          <TD width="9">&nbsp;</TD>
        </TR>

        <TR>
          <TD width="100" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_or_cle_t'/></b></TD>
          <TD width="140" class="footnotes">
                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ENDROIT %>' name='dossiers' property='endroit'/>
          </TD>
          <TD width="9">&nbsp;</TD>
        </TR>

        <TR>
          <TD width="100" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_cr_cle_t'/></b></TD>
          <TD width="140" class="footnotes">
                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LOCALISATION %>' name='dossiers' property='localisation'/>
          </TD>
          <TD width="9">&nbsp;</TD>
        </TR>

        <TR>
          <TD width="100" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_lieu_t'/></b></TD>
          <TD width="140">
          	<bean:write name='dossiers' property="descriptif"/>
		  </TD>
          <TD width="9">&nbsp;</TD>
        </TR>

        <TR>
          <TD width="100" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='origine_t'/></b></TD>
          <TD width="140" class="footnotes">
                <cardex:afficherValeurListeTag name='dossier' property='origine' 
                classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' 
                valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' />
          </TD>
          <TD width="9">&nbsp;</TD>
        </TR>

      </TABLE>
    </TD>
  </TR>

  <TR>
    <TD colspan="3">
	<html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" />
    </TD>
  </TR>
</TABLE>

<BR>

<!-- Sujets -->
<logic:iterate id="sujet" name='dossiers' property='sujets' indexId='index'>
<logic:present name="sujet">
<P>
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
<% if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="3" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='sujets_impliqués_t'/></TD>
  </TR>
  <TR>
    <TD colspan="3" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
  <TR>
    <TD align="center" colspan="3">
      <TABLE width="640" cellpadding="2" cellspacing="0" border="0">
        <TR><TD>
          <TABLE width="375">
             <TR>
		  <TD width="140" valign="top" class="tabSubject"><bean:write name="index1"/></TD>
		  <TD>&nbsp;
	     </TR>
	     <TR>
		  <TD width="140" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='co_no_fiche_t'/></b></TD>
		  <TD><bean:write name='sujet' property="numeroFiche"/></TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_sx_cle_t'/></b></TD>
		  <TD>
		  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='sujet' property="sexe"/>
		  </TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_naissance_t'/></b></TD>
		  <TD><bean:write name='sujet' property="dateNaissance"/></TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t' /></b></TD>
		  <TD><bean:write name='sujet' property="prenom"/></TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t' /></b></TD>
		  <TD><bean:write name='sujet' property="nom"/></TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_nt_cle_t'/></b></TD>
		  <TD>
		  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='sujet' property="ethnie"/>
		  </TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='age_t'/></b></TD>
		  <TD><bean:write name='sujet' property="age"/></TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ro_cle_t'/></b></TD>
		  <TD>
		  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ROLE %>' name='sujet' property="role"/>
		  </TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ls_cle_t'/></b></TD>
		  <TD>
		  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LANGUE %>' name='sujet' property="langue"/>
		  </TD>
	     </TR>
	     <TR>
		  <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ro_cle_t'/></b></TD>
		  <TD>
		  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ROLE %>' name='sujet' property="role"/>
		  </TD>
	     </TR>
	   </TABLE>
	   </TD><TD>
	    <logic:iterate id="element" name="sujet" property="photos" length="1" >
	    <logic:present name="element" >
	         <logic:iterate id="subelement" name="element" length="1" >
			<DIV align="center" STYLE="overflow:hidden; width:215; height:165;" >
	              	     <!-- IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='160'" -->
	  		</DIV>
	  	 </logic:iterate>
	    </logic:present>
	    </logic:iterate>
	   </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>

  <TR>
    <TD colspan="3">
    <html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>

  <TR>
    <TD width="30">&nbsp;</TD>
    <TD width="590" valign="top">
      <TABLE width="590" cellpadding="0" cellspacing="0" border="0">
       <TR>
          <TD width="305" valign="top">

            <TABLE width="300" cellpadding="2" cellspacing="0" border="0">
            <logic:iterate id="element" name="sujet" property="adresses">
            <logic:present name="element">
              <TR>
                <TD width="90" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_adresse_t' /></b></TD>
                <TD width="210"><bean:write name='element' property="adresse"/></TD>
              </TR>
              <TR>
                <TD colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>
              <TR>
                <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_vi_cle_t' /></b></TD>
                <TD>
	            	<bean:define id="pays" name='element' property="pays" type="String"/>
	            	<bean:define id="province" name='element' property="province" type="String"/>
	               	<cardex:afficherValeurListeTag name='element' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>           
                </TD>
              </TR>
              <TR>
                <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_pr_cle_t' /></b></TD>
                <TD>
	            	<cardex:afficherValeurListeTag name='element' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>
                </TD>
              </TR>
              <TR>
                <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_pa_cle_t' /></b></TD>
                <TD>
	                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.PAYS %>' name='element' property="pays"/>
                </TD>
              </TR>
              <TR>
                <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t' /></b></TD>
                <TD><bean:write name='element' property="codePostal"/></TD>
              </TR>
              <TR>
                <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone1' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                <TD><bean:write name='element' property="telephone1"/></TD>
              </TR>
              <TR>
                <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone2' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
                <TD><bean:write name='element' property="telephone2"/></TD>
              </TR>
              <TR>
                 <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>
            </logic:present>
            </logic:iterate>
            </TABLE>
          </TD>

          <TD width="1"><html:img page="/images/pixelnoir.gif" width="1" height="125" border="0" /></TD>

          <!-- Caractéristiques du sujet -->
          <TD width="284" align="right" valign="top">
              <TABLE width="277" cellpadding="2" cellspacing="0" border="0">

                 <TR>
                    <TD width="27">&nbsp;</TD>
                    <TD width="250"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='caracteristiques_t'/></b></TD>
                 </TR>
                 <TR>
                    <TD width="27">&nbsp;</TD>
                    <TD width="250">
                      <UL>
                      	<logic:present name='sujet' property='caracteristiques.doubleListe.droiteColLabel'>
	                        <logic:iterate id="element" name='sujet' property='caracteristiques.doubleListe.droiteColLabel'>
						       <LI class="listDetailOdd"><%=element %></LI>
						    </logic:iterate>
						</logic:present>                      
                      </UL>
                    </TD>
                 </TR>
              </TABLE>

            </TD>
          </TR>
      </TABLE>

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>

  <TR>
    <TD colspan="3"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
  </logic:present>
  </logic:iterate>

</TABLE>
</P>

<P>
<!-- Sociétés -->
<logic:iterate id="societe" name='dossiers' property='societes' indexId='index'>
<logic:present name="societe" >
<P>
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="3" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_societe'/></TD>
  </TR>
  <TR>
    <TD colspan="3" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
  <TR>
    <TD align="center" colspan="3">
      <TABLE width="640" cellpadding="2" cellspacing="0" border="0">
        <TR>
          <TD width="20" valign="top" rowspan="6" class="tabSubject"><bean:write name="index1"/></TD>
          <TD align="right"><b><bean:message key='co_no_fiche_t'/></b></TD>
          <TD align="left"><bean:write name='societe' property="numeroFiche" /></TD>
          <TD>&nbsp;</TD>
        </TR>
        <TR>
          <TD width="120" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_so_raison_sociale_t'/></b></TD>
          <TD width="500" colspan="2"><bean:write name='societe' property='raisonEtre' /></TD>
        </TR>
        <TR>
          <TD width="120" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_so_nom_t' /></b></TD>
          <TD width="500" colspan="3"><bean:write name='societe' property='nom' /></TD>
        </TR>
        <TR>
          <TD width="120" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_so_date_fondation_t' /></b></TD>
          <TD width="170"><bean:write name='societe' property='dateDeFondation' /></TD>
          <TD width="100"></TD>
          <TD width="230"></TD>
        </TR>
        <TR>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_so_reference_nom_t' /></b></TD>
          <TD><bean:write name='societe' property='referenceNom' /></TD>
          <TD><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference1_t'/></TD>
          <TD><bean:write name='societe' property='reference1' /></TD>
        </TR>
        <TR>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_so_reference_prenom_t' /></b></TD>
          <TD><bean:write name='societe' property='referencePrenom' /></TD>
          <TD><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_do_reference2_t'/></b></TD>
          <TD><bean:write name='societe' property='reference2' /></TD>
        </TR>
        <TR>
          <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>
      </TABLE>
    </TD>
  </TR>

  <TR>
    <TD colspan="3"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>

</TABLE>
</P>
</logic:present>
</logic:iterate>

<!-- Véhicules -->
<logic:iterate id="vehicule" name='dossiers' property='vehicules' indexId='index'>
<logic:present name="vehicule">
<P>
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
<% if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="3" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_vehicule' /></TD>
  </TR>
  <TR>
    <TD colspan="3" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
  <TR>
    <TD align="center" colspan="3">
      <TABLE width="640" cellpadding="2" cellspacing="0" border="0">
        <TR>
          <TD width="20" valign="top" rowspan="6" class="tabSubject"><bean:write name="index1"/></TD>
          <TD width="120" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ma_cle_t'/></b></TD>
          <TD width="500" colspan="3">
          	<bean:define id="marque" name='vehicule' property="marque" type="String"/>
            <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.MARQUE %>' name="vehicule" property='marque'/>
          </TD>
        </TR>
        <TR>
          <TD width="120" align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_md_cle_t' /></b></TD>
          <TD width="170">
          	<cardex:afficherValeurListeTag name='vehicule' property="modele" classe='<%= GlobalConstants.CleListe.MODELE %>' valeurDiscriminant='<%=marque %>'/>
          </TD>
          <TD width="120"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ve_immatriculation_t'/></b></TD>
          <TD width="210">
            <bean:write name='vehicule' property='immatriculation' />
          </TD>
        </TR>
        <TR>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='c_ve_annee_t' /></b></TD>
          <TD colspan="3"><bean:write name='vehicule' property='annee' /></TD>
        </TR>
        <TR>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ve_assureur_t' /></b></TD>
          <TD colspan="3"><bean:write name='vehicule' property='assurance' /></TD>
        </TR>
        <TR>
          <TD align="right"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ve_police_t' /></b></TD>
          <TD><bean:write name='vehicule' property='police' /></TD>
          <TD><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_ve_expiration_police_t'/></b></TD>
          <TD><bean:write name='vehicule' property='dateExpirationVignette' /></TD>
        </TR>
        <TR>
          <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
  <TR>
    <TD colspan="3"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
</TABLE>
</P>
</logic:present>
</logic:iterate>

<!-- Photos -->
<logic:iterate id="element" name='dossiers' property='photos'>
<logic:present name="element" >
<P>
  <TABLE width="650" cellpadding="0" cellspacing="0" border="0">
    <TR>
      <TD class="errorHeader" colspan="3" >&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_photo' /></TD>
    </TR>

    <TR>
      <TD height="15"  colspan="3" ><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
    </TR>

    <TR>
        <!-- Les balises de paragraphes sont obligatoires -->
        <P>
        <logic:iterate id="subelement" name="element" >
        <TD>
		<DIV align="center" STYLE="overflow:hidden; width:215; height:165;" >
            	     <IMG src="<bean:write name='subelement' property='url' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='150'" >
		</DIV>
        </TD>
        </logic:iterate>
        <% Collection collection = (Collection)pageContext.findAttribute("element"); %>
        <% if (collection.size() == 1)  { %>
            <TD>&nbsp;</TD><TD>&nbsp;</TD>
        <% } if (collection.size() == 2)  { %>
            <TD>&nbsp;</TD>
        <% } %>
        </TR>
        <TR>
        </P>
    </TR>
    <TR>
        <TD colspan="3"><P>&nbsp;</P></TD>
    </TR>

  </TABLE>
</P>
</logic:present>
</logic:iterate>

<!-- Services d'urgence -->

<!-- Urgence -->
<logic:iterate id="urgence" name='dossiers' property='urgence' indexId='index'>
</logic:iterate>
<logic:present name="urgence" >
  <P>
  <TABLE width="650" cellpadding="2" cellspacing="0" rules="none" >
<logic:iterate id="urgence" name='dossiers' property='urgence' indexId='index'>
<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <TR>
    <TD colspan="5" class="errorHeader" height="30">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='urgence.rapport'/></TD>
  </TR>
  <TR>
    <TD colspan="5" height="15"><html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" /></TD>
  </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
    <TR>
      <TD width="20" valign="top" class="tabSubject"><b><bean:write name="index1"/></b>
      <TD width="150" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_cl_cle_t'/></b></TD>
      <TD width="150">
          <cardex:afficherValeurListeTag name="urgence" property="classe" classe='<%=GlobalConstants.CleListe.CLASSE %>' />
      </TD>
      <TD width="160" align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='societe_t'/></b></TD>
      <TD width="170">
          <bean:write name='urgence' property='societe' />
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='district'/><bean:message key='2.points' /></b></TD>
      <TD>
		<bean:write name="urgence" property="district"/>
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='unite'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="unite"/>
      </TD>
    </TR>
    <TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='client.contact'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="contact"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message key='repondant'/><bean:message key='2.points'/></b>
      </TD>
      <TD>
      	<logic:equal name='urgence' property='repondant' value='<%=GlobalConstants.BooleanString.TRUE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='oui_t'/>     		
      	</logic:equal>
      	<logic:equal name='urgence' property='repondant' value='<%=GlobalConstants.BooleanString.FALSE%>' >
			<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='non_t'/>     		
      	</logic:equal>
      </TD>
	</TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_telephone_t'/></b></TD>
      <TD>
          <bean:write name="urgence" property="telephone"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message key='telecopieur' /><bean:message key='2.points' /></b>
      </TD>
      <TD>
			<bean:write name="urgence" property="telecopieur"/>
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='courriel_t2'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="courriel"/>&nbsp;
      </TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='numero.evenement'/><bean:message key='2.points' /></b></TD>
      <TD>
          <bean:write name="urgence" property="evenement"/>&nbsp;
      </TD>
    </TR>
	<TR>
      <TD width="20">&nbsp;</TD>
      <TD align="left"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_st_cle_t'/></b></TD>
      <TD>
             <cardex:afficherValeurListeTag name='dossiers' property='<%="urgence["+index+"].statut"%>' classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.URGENCE %>'/>
      </TD>
      <TD align="left"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='motif'/><bean:message key='2.points' /></b></TD>
      <TD>
			<cardex:afficherValeurListeTag name='dossiers' property='<%="urgence["+index+"].motif"%>' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.MOTIF %>'/>      </TD>
    </TR>
  <TR>
    <TD colspan="5" height="5"><html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" /></TD>
  </TR>
</logic:iterate>

  </TABLE>

</logic:present>

<!-- Narrations -->
<logic:iterate id="narration" name='dossiers' property='narrations' indexId='index' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" >
<% if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
    <TR>
      <TD width="652" colspan="6" class="errorHeader">&nbsp;<bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_commentaires' /></TD>
    </TR>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
    <TR>
      <TD width="652" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>

    <TR>
      <TD width="163"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_co_rapporte_par_t'/></b></TD>
      <TD width="163">
          <cardex:afficherValeurListeTag name="narration" property="rapporteur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
      </TD>
      <TD width="163" align="right"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='d_date_creation_t'/></TD>
      <TD width="163">
          <bean:write name="narration" property="dateCreation"/>
      </TD>
    </TR>
    <TR>
      <TD width="652" colspan="6"><b><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_commentaire_t' /></b></TD>
    </TR>
    <TR>
      <TD width="652" colspan="6" >
          <P>
          <TABLE>
            <TR>
              <TD class="pictureBackground"><FONT size="+2px">
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %></FONT>
              </TD>
            </TR>
          </TABLE>
          </P>
      </TD>
    </TR>
    <TR>
      <TD width="652" colspan="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" colspan="6"><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>
</P>
</logic:present>
</logic:iterate>
&nbsp;
<P>
  <TABLE width="650" cellpadding="2" cellspacing="0" border="0">
    <TR>
        <td width="280"><html:img page="/images/pixelnoir.gif" width="276" height="1" border="0" /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
        <td width="150" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
    </TR>
    <TR>
        <td width="280" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='signature_responsable_t' /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='no_employe_t' /></td>
        <td width="150" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_t' /></td>
    </TR>
  </TABLE>
<TABLE width="652">
  <TR>
    <TD align='right'><b>CDX_0226</b></TD>
  </TR>
</TABLE>
</P>

</FORM>
</TD></TR></TABLE>
</logic:present>
</logic:iterate>
</BODY>
</HTML>
