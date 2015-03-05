<%-- --------------------------------------------------------------------------
Use case    : Impression du formulaire d'autoexclusion.
Description : �cran d'impression d'une narration.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/05/01 19:33:53 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/05/01 19:33:53 $, $Author: mlibersan $
Cr�ation.

--------------------------------------------------------------------------- --%>

<%--  --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>


<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META http-equiv="imagetoolbar" content="no" >

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/lq_scripts.js"></SCRIPT>

<title><bean:message key="titre.application.cardex"/></title>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="0" marginheight="0" marginwidth="5" bottommargin="0">
<FORM action="#">

<DIV align="center" STYLE="overflow:none; width:100; height:100; background-color:transparent; z-index: 1; position: absolute; right: 100px; top: 1px;" >
     <!--  html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/ -->
        <FONT face="Arial" size="5" color="#cccccc"><B><I>Confidentiel</I></B></FONT> 
</DIV>

<!-- LOGO POSITIONING -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
     <TD height="30">
   <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="imprimerLogoSCQ" value='true'>
	    <!--  html:img page="/images/lq_SCQ.jpg" border="0" alt="logo" / -->
        <FONT face="Arial" size="4"> <B><I>Soci�t� des casinos du Qu�bec inc.</I></B></FONT>
    </logic:equal>
    <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="imprimerLogoSCQ" value='false'>
    	<!-- html:img page="/images/ludoplex.jpg" border="0" alt="logo" / -->
        <FONT face="Arial" size="4"> <B><I>Soci�t� des loteries vid�o du Qu�bec inc.</I></B></FONT>
    </logic:equal>    
    </TD>
  </TR>
       <TR>
          <TD align="left" class="errorHeader" colspan="2" >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
                 <table cellpadding="0" cellspacing="0" width="650" border="0" style="border-style: solid;
  border-width: 2px; border-color: #000000;">
                 <tr>
                 	<TD class="errorHeader" align="center">Je veux �tre imm�diatement mis en contact avec une ressource d'aide :&nbsp;
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideImmediate' value="true">
							OUI
						</logic:equal>
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideImmediate' value="false">
							NON
						</logic:equal>						
					</td>
                 </tr>
                 </table>
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
                 <table cellpadding="0" cellspacing="0" width="650" border="0" style="border-style: solid;
  border-width: 2px; border-color: #000000;">
                 <tr>
                 	<TD class="errorHeader" align="center">I want to be put in contact with a support resource now:
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideImmediate' value="true">
							YES
						</logic:equal>
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideImmediate' value="false">
							NO
						</logic:equal>						
					</td>
                 </tr>
               </table>
            </logic:equal>
          </TD>
        </TR>
</TABLE>
<!-- END LOGO POSITIONING -->

<br>
<!-- PRESENTATION TEXT -->
<TABLE width="652" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD align="center"><i><H3>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
           Programme d'autoexclusion
        </logic:equal>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
           Self-Exclusion Program
        </logic:equal>
    </H3></i></TD>
  </TR>

  <TR>
    <TD width="625">
     <P align="justify">
    <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="imprimerLogoSCQ" value='true'>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
            <b>La Soci�t� des casinos du Qu�bec inc. (la � Soci�t� �) offre le programme d'autoexclusion � tout client qui
            d�sire cesser de fr�quenter les casinos de la Soci�t�.<BR>
            En s'inscrivant au programme d'autoexclusion, le client autorise la Soci�t� � l'exclure de ses casinos,
            incluant les aires de jeux, bars, casse-cro�te, restaurants, salles de spectacles et boutiques � l'int�rieur
            desdits casinos et ce, pour la p�riode de son choix. L'inscription au programme d'autoexclusion est
            irr�vocable.<BR>
            La Soci�t� n'assume aucune responsabilit� si le client enfreint son interdiction d�acc�s � laquelle il s'est
            volontairement engag�.</b><BR>
        </logic:equal>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
            <b>The Soci�t� des casinos du Qu�bec inc. (the �Soci�t�) offers the self-exclusion program to any client who
            wishes to stop frequenting the casinos of the Soci�t�.<BR>
            By registering in the self-exclusion program, the client authorises the Soci�t� to expel him/her from its
            casinos, including play areas, bars, snack bars, restaurants, entertainment facilities and boutiques in said
            casinos, during the period chosen by the client. Registration in self-exclusion program is irrevocable.<BR>
            The Soci�t� assumes no responsibility should the client contravene the ban to which he/she voluntarily
            committed. </b><BR>
        </logic:equal>
     </logic:equal>
    <logic:equal name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property="imprimerLogoSCQ" value='false'>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
            <b>La Soci�t� des loteries vid�o du Qu�bec inc. (la � Soci�t� �) offre le programme d'autoexclusion � tout client qui
            d�sire cesser de fr�quenter les Salons de jeux de la Soci�t�.<BR>
            En s'inscrivant au programme d'autoexclusion, le client autorise la Soci�t� � l'exclure de ses Salons de jeux,
            incluant les aires de jeux, bars, casse-cro�te, restaurants, salles de spectacles et boutiques � l'int�rieur
            desdits Salons de jeux et ce, pour la p�riode de son choix. L'inscription au programme d'autoexclusion est
            irr�vocable.<BR>
            La Soci�t� n'assume aucune responsabilit� si le client enfreint son interdiction d�acc�s � laquelle il s'est
            volontairement engag�.</b><BR>
        </logic:equal>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
            <b>The Soci�t� des loteries vid�o du Qu�bec inc. (the �Soci�t�) offers the self-exclusion program to any client who
            wishes to stop frequenting the Salons de jeux of the Soci�t�.<BR>
            By registering in the self-exclusion program, the client authorises the Soci�t� to expel him/her from its
            Salons de jeux, including play areas, bars, snack bars, restaurants, entertainment facilities and boutiques in said
            Salons de jeux, during the period chosen by the client. Registration in self-exclusion program is irrevocable.<BR>
            The Soci�t� assumes no responsibility should the client contravene the ban to which he/she voluntarily
            committed. </b><BR>
        </logic:equal>
     </logic:equal>
     </P>
 	</TD>
  </TR>
  <TR>
    <TD colspan="3" height="5">&nbsp;</TD>
  </TR>
  <TR>
    <TD colspan="3" height="5"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
  </TR>

</TABLE>
<!-- END PRESENTATION TEXT -->

<br>
<!-- DATA FORM -->
<TABLE width="650" cellpadding="2" cellspacing="0" border="0">
  <TR>
    <TD>

      <!-- PICTURE SECTION -->
      <TABLE width="590" cellpadding="0" cellspacing="0" border="0">
       <TR>
          <TD align="center" valign="top" class="tabSubject" colspan="2">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Demande d'inscription au programme d'autoexclusion, � l'intention du Directeur g�n�ral
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              Request of Registration to the Self-Exclusion Program to the Attention of the General Manager
            </logic:equal>
          </TD>
          <TD align="right" rowspan="6">
          <TABLE>
              <logic:present name='<%=GlobalConstants.Impression.PHOTO_KEY%>' >
              <TR>
                  <!-- Les balises de paragraphes sont obligatoires -->
                  <P>
                  <TD>
                        <DIV align="center" STYLE="overflow:hidden; width:300; height:240;" >
         			<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienElement' />&SITE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienSiteElement' />&EXTENSION=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='230'" >
         		</DIV>
                  </TD>
                  </P>
              </TR>
              </logic:present>
              <logic:notPresent name='<%=GlobalConstants.Impression.PHOTO_KEY%>' >
              <TR>
                  <!-- Les balises de paragraphes sont obligatoires -->
                  <P>
                  <TD>
                    <%-- html:img page="/images/no_picture.jpg" width="200" height="200" border="1" width="200" height="200" border="0" styleClass="tableOutline" alt="" / --%>
                  </TD>
                  </P>
              </TR>
              </logic:notPresent>
          </TABLE>
          </TD>
        </TR>

        <TR>
          <TD colspan="2" class="errorHeader" valign="top">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Je demande d'�tre inscrit au programme d'autoexclusion
              pour une dur�e de : <bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='mois' /> mois (maximum 60 mois).
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I request to be registered in the self-exclusion program
              for a period of <bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='mois' /> months (maximum 60 months).
            </logic:equal>
          </TD>
        </TR>
        <TR>
          <TD align="right" class="errorHeader" nowrap >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              <i>La p�riode d'autoexclusion est en vigueur du : </i>
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              <i>The self-exclusion period is in force from:</i>
            </logic:equal>
          </TD>
          <TD class="errorHeader" nowrap><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='dateDebutLeft[10]' /></TD>
        </TR>

        <TR>
          <TD align="right" class="errorHeader" >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              <i>au : </i>
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              <i>to: </i>
            </logic:equal>
          </TD>
          <TD class="errorHeader" nowrap><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='dateFinLeft[10]' /></TD>
        </TR>
        <TR>
          <TD align="left" class="errorHeader" colspan="2" >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
                 <table cellpadding="0" cellspacing="0" width="375">
                 <tr>
	                <td colspan="6" class="errorHeader">- Je voudrais �tre mis en contact avec un conseiller</td>
                 </tr>
                 <tr>
                 	<TD class="errorHeader" width="145">� l'autoexclusion :</TD>
	                <td width="35"><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="true" onclick="return false"/></td>
					<td width="35">
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="true">
							<input type="checkbox" name="isDemandeAideGeneralFalse" onclick="return false"/>
						</logic:equal>
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="false">
							<input type="checkbox" name="isDemandeAideGeneralFalse" checked="checked" onclick="return false"/>
						</logic:equal>					
					</td>
	                <td width="35"><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideInitiale' value="true" onclick="return false"/></td>
	                <td><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='periodeASuivre' value="true" onclick="return false"/></td>
					<td width="90">&nbsp;</td>
                 </tr>
                 <tr>
	                <td>&nbsp;</td>
	                <td class="errorHeader">OUI</td>
					<td class="errorHeader">NON</td>
	                <td class="errorHeader">D�but</td>
					<td class="errorHeader">Fin</td>
					<TD>&nbsp;</TD>
                 </tr>                 
                 </table>
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
                 <table cellpadding="0" cellspacing="0" width="375">
                 <tr>
	                <td colspan="6" class="errorHeader">- I would like to be put in contact with a</td>
                 </tr>
                 <tr>
                 	<TD class="errorHeader" width="80">consellor:</TD>
	                <td width="20"><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="true" onclick="return false"/></td>
					<td width="20">
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="true">
							<input type="checkbox" name="demandeAideGeneralFalse" onclick="return false"/>
						</logic:equal>
						<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="false">
							<input type="checkbox" name="demandeAideGeneralFalse" checked="checked" onclick="return false"/>
						</logic:equal>
					</td>
	                <td width="20"><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='aideInitiale' value="true" onclick="return false"/></td>
	                <td><html:checkbox name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='periodeASuivre' value="true" onclick="return false"/></td>
					<td width="65">&nbsp;</td>
                 </tr>
                 <tr>
	                <td>&nbsp;</td>
	                <td class="errorHeader">YES</td>
					<td class="errorHeader">NO</td>
	                <td class="errorHeader">Start</td>
					<td class="errorHeader">End</td>
					<TD>&nbsp;</TD>
                 </tr>                 
                 </table>    
            </logic:equal>        
          </TD>
        </TR>
        <logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='periodeASuivre' value="true">
	        <TR>
	          <TD align="left" class="errorHeader" colspan="2" >
	            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
	                 - Je veux �tre contact� par : 
	              <logic:present name='<%=GlobalConstants.Impression.ADRESSE_KEY%>'>
	                   <bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='commentaire' />
	              </logic:present>
	            </logic:equal>
	            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
	                 - I want to be contacted by: 
	              <logic:present name='<%=GlobalConstants.Impression.ADRESSE_KEY%>'>
	                 <bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='commentaire' />
	              </logic:present>
	            </logic:equal>
	          </TD>
	        </TR>
        </logic:equal>
        
      </TABLE>
      <!-- END PICTURE SECTION -->

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>

  <TR><TD>
      <!-- PERSONAL INFOS -->
      <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>' >
        <TABLE width="665" cellpadding="2" cellspacing="0" border="0"style="border-style: solid;
  border-width: 2px; border-color: #000000;">
          <TR>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t'/></b></TD>
            <TD width="160"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='nom' /></TD>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t'/></b></TD>
            <TD width="160"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='prenom' /></TD>
          </TR>
          <logic:present name='<%=GlobalConstants.Impression.ADRESSE_KEY%>'>
          <TR>
            <TD align="right" valign="top"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_adresse_t'/></b></TD>
            <TD><cardex:affichageAdresse name="<%=GlobalConstants.Impression.ADRESSE_KEY%>" numeroLigne="1" langueKey="<%=GlobalConstants.Impression.LOCALE_KEY%>" /> 
            	<cardex:affichageAdresse name="<%=GlobalConstants.Impression.ADRESSE_KEY%>" numeroLigne="2" langueKey="<%=GlobalConstants.Impression.LOCALE_KEY%>" /> 
            </TD>
            <TD align="right" valign="top"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_pr_cle_t'/></b></TD>
            <TD valign="top">
               	<bean:define id="pays" name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="pays" type="String"/>
            	<bean:define id="province" name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="province" type="String"/>
           		<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>
            </TD>
          </TR>

          <TR>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_vi_cle_t'/></b></TD>
            <TD>
            	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>
            </TD>
            
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t'/></b></TD>
            <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='codePostal' /></TD>
          </TR>
          </logic:present>
          <TR>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_naissance_t'/></b></TD>
            <TD><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='dateNaissance' /></TD>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_sx_cle_t'/></b></TD>
            <TD>
            	<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='sexe'/>
            </TD>
          </TR>

          <TR>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='origine_ethnique_t'/></b></TD>
            <TD>
            	<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='ethnie'/>
            </TD>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ls_cle_t'/></b></TD>
            <TD>
            	<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LANGUE %>' name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='langue'/>
            </TD>
          </TR>
          <logic:present name='<%=GlobalConstants.Impression.ADRESSE_KEY%>'>
          <TR>
            <TD align="right"><b><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='typeUtilTelephone1' classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache"/>&nbsp;:</b></TD>
            <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='telephone1' /></TD>
            <TD colspan="2">&nbsp;</TD>
          </TR>
	  </logic:present>
          <TR>
            <TD align="right" valign="top"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_jeu'/>&nbsp;: </b></TD>
            <TD colspan="3">
                <logic:iterate id="element" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='jeux.doubleListe.droiteColLabel'>
					<%=element %>, &nbsp;
			    </logic:iterate>                   
            </TD>
          </TR>
        </TABLE>
        </logic:present>
      <!-- END PERSONAL INFOS -->
    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
  <TR><TD>
      <!-- FOONOTES -->
      <TABLE width="665" cellpadding="2" cellspacing="0" border="0" style="border-style: solid;
  border-width: 2px; border-color: #000000;">
        <TR>
          <TD width="20" align="right" valign="top"><html:img page="/images/blank.gif" width="10" height="10" border="1" /></TD>
          <TD width="570" >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Je suis inscrit au programme � Casino Privil�ges �, num�ro de membre
              ____________________
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I am registered in the "Casino Privil�ges" program, my card number is
              ____________________
            </logic:equal>

          </TD>
        </TR>
        <TR>
          <TD width="20" align="right" valign="top"><html:img page="/images/blank.gif" width="10" height="10" border="0" /></TD>
          <TD width="590" >
          <P align="justify">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Note : l'inscription au programme d'autoexclusion entra�ne l'annulation de l'inscription au
              programme �&nbsp;Casino Privil�ges&nbsp;�. En cons�quence, j'accepte que mon inscription au programme �&nbsp;Casino
              Privil�ges&nbsp;� soit r�sili�e. De plus, j'autorise la Soci�t� � me remettre les dollars privil�ges (machines � sous) 
              que j'ai accumul�s. Je reconnais que mon solde est de _______________ dollars privil�ges en
              date du ______________________________ pour lesquels la Soci�t� me remet _______________dollars
              repr�sentant la somme totale due.
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
			  Note : Registration in the self-exclusion program cancels registration in the "Casino Privil�ges" 
			  program. Consequently, I accept that my registration in the "Casino Privil�ges" program be canceled. 
			  Moreover, I hereby authorize the Soci�t� to pay me the privil�ges dollars (slot machines) I have accumulated. I 
			  acknowledge that as of __________________, the balance in my account equals _______________ 
			  privil�ges dollars, and that the Soci�t� has given me ________________dollars, which represents 
			  the total amount I am due.
            </logic:equal>
          </P>

          </TD>
        </TR>


        <TR>
          <TD width="20" align="right" valign="top"><html:img page="/images/blank.gif" width="10" height="10" border="1" /></TD>
          <TD width="590" ><P align="justify">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Je ne suis pas inscrit au programme �&nbsp;Casino Privil�ges&nbsp;�. J'accepte que mes nom, pr�nom et adresse
              postale soient enregistr�s dans la base de donn�es de la Soci�t� afin d'emp�cher tout envoi de
              documentation annon�ant les activit�s des �tablissements de jeu.
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I am not registered in the "Casino Privil�ges" program. I accept that my complete name and address
              be entered in the Soci�t�'s database in order to prevent the Soci�t� from sending me documentation
              announcing the activities taking place in its gaming establishments.
            </logic:equal>
        </P>
        </TD>
        </TR>
      </TABLE>
</TD></tr>
<tr><td>
      <TABLE width="665" height="50" cellpadding="2" cellspacing="0" border="0" style="border-style: solid;
  border-width: 2px; border-color: #000000;">
	  <TR>
	    <TD colspan="2" align="left">
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
			&nbsp;&nbsp;<b>Sites : </b>
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
			&nbsp;&nbsp;<b>Sites: </b>
		    </logic:equal>
		    
		    <logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='tousCasinoEtLudoplex' value="true">
	   		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
			    Tous les casinos et salons de jeux pr�sents et futurs.
			    </logic:equal>
			    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
			    Both present and future casinos and gaming halls.
			    </logic:equal>
			</logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='tousCasinoEtLudoplex' value="false">			
				<bean:write name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='stringSitesChoisis'/>
			</logic:equal>			
			<br><br>
   		    
   		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		    &nbsp;&nbsp;<b>Remarques :</b><html:img page="/images/pixelnoir.gif" width="570" height="1" border="0" />
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
			&nbsp;&nbsp;<b>Remarks: </b><html:img page="/images/pixelnoir.gif" width="580" height="1" border="0" />
		    </logic:equal>
	    </TD>
	  </TR>
      </TABLE>
    </TD>
    <!-- END FOOTNOTES -->

    <TD width="30">&nbsp;</TD>
</tr>
<!-- END DATA FORM -->

<tr><td>
	<!-- SIGNATURE -->
	<TABLE width="665" cellpadding="5" cellspacing="0" border="0" style="border-style: solid;
	  border-width: 2px; border-color: #000000;">
	  <TR>
	    <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	  </TR>
	
	  <TR>
	
	    <TD align="left" nowrap colspan="2">&nbsp;
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       Signature du client :
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Patron's signature:
		    </logic:equal>
			<html:img page="/images/pixelnoir.gif" width="380" height="1" border="0" />
		</TD>
	    <TD width="40" align="right">
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       Date :
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Date:&nbsp;
		    </logic:equal>
	    </TD>
	    <TD width="90" valign="bottom" align="right"><html:img page="/images/pixelnoir.gif" width="90" height="1" border="0" /></TD>
	  </TR>

	  <TR>
	    <TD align="left" nowrap>&nbsp;
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       Signature du t�moin :
		       <html:img page="/images/pixelnoir.gif" width="205" height="1" border="0" />
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Witness's signature:
		       <html:img page="/images/pixelnoir.gif" width="215" height="1" border="0" />
		    </logic:equal>
	    </TD>
	    <TD width="155" align="left" nowrap="nowrap">
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       No. employ� :
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Employee nb.
		    </logic:equal>
			<html:img page="/images/pixelnoir.gif" width="70" height="1" border="0" />
		</TD>
	    <TD width="40" align="right">
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       Date :
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Date:&nbsp;
		    </logic:equal>
	    </TD>
	    <TD width="90" valign="bottom"><html:img page="/images/pixelnoir.gif" width="90" height="1" border="0" /></TD>
	  </TR>	

	  <TR>
		<TD align="left" colspan="4">
			<TABLE cellpadding="0" cellspacing="0">
			<tr>
				<td width="356">&nbsp;
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
				       Signature de la personne autoris�e :
					   <html:img page="/images/pixelnoir.gif" width="140" height="1" border="0" />
				    </logic:equal>
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
				       Authorized person's signature:
					   <html:img page="/images/pixelnoir.gif" width="170" height="1" border="0" />
				    </logic:equal>
				</TD>
			    <TD width="176" align="left" nowrap="nowrap">&nbsp;
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
				       No. employ� :
				    </logic:equal>
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
				       Employee nb.&nbsp;
				    </logic:equal>
					<html:img page="/images/pixelnoir.gif" width="70" height="1" border="0" />
				</TD>
			    <TD width="43" align="left" nowrap="nowrap">
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
				       Date :
				    </logic:equal>
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
				       Date:&nbsp;
				    </logic:equal>
			    </TD>
			    <TD width="90" valign="bottom" align="right"><html:img page="/images/pixelnoir.gif" width="90" height="1" border="0" /></TD>
			</tr>
			<tr>
				<td colspan="4">
					<table cellpadding="0" cellspacing="0">
					<tr>
					    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
							<td width="200">&nbsp;</td>
					    </logic:equal>
					    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
							<td width="170">&nbsp;</td>
					    </logic:equal>
								<td><cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='intervenant' classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</TD>
	  </TR>
	  <TR>
	    <TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	  </TR>
	</TABLE>
	</td>
</TR>
</TABLE>

<TABLE width="630">
  <TR>
    <td>
    	<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="true">
    		**
    	</logic:equal>
    	<logic:equal name='<%=GlobalConstants.Impression.INSCRIPTION_KEY%>' property='demandeAideGeneral' value="false">
    		*
    	</logic:equal>
    </td>
    <TD align='right'><b><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroDossier' /></b></TD>
  </TR>
</TABLE>

<!-- END SIGNATURE -->

</FORM>

</BODY>
</HTML>
