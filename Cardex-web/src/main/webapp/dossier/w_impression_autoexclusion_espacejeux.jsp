<%-- --------------------------------------------------------------------------
Use case    : Impression du formulaire d'autoexclusion, seulement pour Espacejeux (jeux en ligne).
Description : Écran d'impression d'une narration.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/05/01 19:33:53 $

History     : Voir ci-dessous.

$Revision: 1.6 $, $Date: 2002/05/01 19:33:53 $, $Author: mlibersan $
Création.

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

<DIV align="center" STYLE="overflow:none; width:75; height:120; background-color:transparent; z-index: 1; position: absolute; right: 100px; top: 1px;" >
     <!--  html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/ -->
        <FONT face="Arial" size="5" color="#cccccc"><B><I>Confidentiel</I></B></FONT> 
</DIV>

<!-- LOGO POSITIONING -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD height="30">
    <!--  html:img page="/images/lq_SCQ.jpg" border="0" alt="logo" / -->
        <FONT face="Arial" size="4"> <B><I>Espacejeux</I></B></FONT>
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
           Auto-Exclusion Program
        </logic:equal>
    </H3></i></TD>
  </TR>

  <TR>
    <TD width="625">
      <BR>
      <P align="justify">
   		<logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
            <b>Le programme d'autoexclusion est offert à toute personne qui désire ne pas jouer sur le site espacejeux.com. 
            En s'inscrivant au programme d'autoexclusion, une personne autorise Loto-Québec et ses filiales à l'empêcher de procéder 
            à l'ouverture d'un compte Espacejeux ou, selon le cas, à bloquer un compte déjà créé et ce, pour la période qu'elle choisit. 
            L'inscription au programme d'autoexclusion est irrévocable. Ni Loto-Québec ni l'une ou l'autre de ses filiales n'assument 
            quelque responsabilité que ce soit si une personne enfreint, d'une façon ou d'une autre, l'interdiction d'accès à laquelle 
            elle s'est volontairement engagée.
			</b><BR>
        </logic:equal>
        <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
            <b>The auto-exclusion program is available to anyone who does not wish to be able to play on the Escapcejeux.com website. 
            By subscribing to the auto-exclusion program the individual is allowing without any restriction Loto-Quebec as well 
            all of it's subsidiaries to prohibit him from opening an online account, as well as to close an existing one, if the case maybe. 
            The program is an irrevocable and voluntary one. Therefore, Loto-Quebec as well as all it's subsidiary will not be held accountable, 
            if the auto-excluded succeeds in overcoming all the hindrances that has been put in place to avoid him (her) to access the Espacejeu.com 
            website, regardless of the manner in which way the attempt in successful.
</b><BR>
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
  <TR><TD>
 
      <!-- PICTURE SECTION -->
      <TABLE width="665" cellpadding="0" cellspacing="0" border="0">
       <TR>
          <TD align="center" valign="top" class="tabSubject" colspan="2">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Demande d'inscription au programme d'autoexclusion
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I hereby request to be register in the auto-exclusion program
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
         			<IMG src="<%= request.getContextPath() %>/AffichageLoupe'CLE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienElement' />&SITE=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='lienSiteElement' />&EXTENSION=<bean:write name='<%=GlobalConstants.Impression.PHOTO_KEY%>' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='230'" >
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
            <BR>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              Je demande d'être inscrit au programme d'autoexclusion pour une durée de : (1 mois (minimum) à 5 ans (maximum)).
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I request the be register in the auto-exclusion program for a period of (1 month (minimum) to 5 years (Maximum)).
            </logic:equal>
          </TD>
        </TR>
        <TR>
          <TD align="right" class="errorHeader" nowrap >
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              <i>La période d'autoexclusion est en vigueur du : </i>
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              <i>The self-exclusion period is in effect from:</i>
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
    
      </TABLE>
      <!-- END PICTURE SECTION -->

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
  <TR>
    <TD>
      <!-- PERSONAL INFOS -->
      <BR>
      <logic:present name='<%=GlobalConstants.Impression.SUJET_KEY%>' >
        <TABLE width="665" cellpadding="2" cellspacing="0" border="0"style="border-style: solid;
  border-width: 2px; border-color: #000000;">
          <TR>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t'/></b></TD>
            <TD width="160"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='nom' /></TD>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t'/></b></TD>
            <TD width="160"><bean:write name='<%=GlobalConstants.Impression.SUJET_KEY%>' property='prenom' /></TD>
          </TR>
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
            	<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property="ville" classe='<%= GlobalConstants.CleListe.VILLE%>' valeurDiscriminant='<%=province%>'/>
            </TD>
            <TD align="right"><b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t'/></b></TD>
            <TD><bean:write name='<%=GlobalConstants.Impression.ADRESSE_KEY%>' property='codePostal' /></TD>
          </TR>
		  </logic:present>
        </TABLE>
        </logic:present>
      <!-- END PERSONAL INFOS -->
    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
  <TR><TD>
      <!-- FOONOTES -->
      <TABLE width="665" cellpadding="5" cellspacing="0" border="0" style="border-style: solid;
  border-width: 2px; border-color: #000000;">

        <TR>
          <TD width="20" align="right" valign="top"><html:img page="/images/blank.gif" width="10" height="10" border="1" /></TD>
          <TD width="590" ><P align="justify">
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
              J'accepte que mon nom, prénom, adresse postale et adresse courriel soient enregistrés dans la base de données 
              d'Espacejeux afin d'empêcher tout envoi de documentation relative aux activités de jeu en ligne.
            </logic:equal>
            <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
              I hereby: Surname, Name, living at address and EMail, allow Escpacejeux to add my coordinates to their database. 
              My coordinates being in such database will therefore stop all possibilities' of correspondence will be sent 
              to my attention regarding online games.
            </logic:equal>
        </P>
        </TD>
        </TR>
      </TABLE>
</TD></tr>
          <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
	  <tr>                 	
		<TD class="errorHeader" align="left">J'ai lu et accepté les conditions de cet engagement.
		<BR><BR>
			Cocher la case appropriée :&nbsp;
				<html:img page="/images/blank.gif" width="10" height="10" border="1" />
					OUI
				<html:img page="/images/blank.gif" width="10" height="10" border="1" />
					NON
			</td>
               </tr>
          </logic:equal>
          <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
               <tr>
               	<TD class="errorHeader" align="left">I have read and accepted the conditions of this agreement.
               	<BR><BR>
				Select the appropriate option:&nbsp;
				<html:img page="/images/blank.gif" width="10" height="10" border="1" />
					YES
				<html:img page="/images/blank.gif" width="10" height="10" border="1" />
					NO
			</td>
               </tr>
          </logic:equal>
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
<BR>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       Signature du témoin :
		       <html:img page="/images/pixelnoir.gif" width="205" height="1" border="0" />
		    </logic:equal>
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
		       Witness's signature:
		       <html:img page="/images/pixelnoir.gif" width="215" height="1" border="0" />
		    </logic:equal>
	    </TD>
	    <TD width="155" align="left" nowrap="nowrap">
		    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
		       No. employé :
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
<BR>
			<TABLE cellpadding="0" cellspacing="0">
			<tr>
				<td width="356">&nbsp;
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
				       Signature de la personne autorisée :
					   <html:img page="/images/pixelnoir.gif" width="140" height="1" border="0" />
				    </logic:equal>
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='en' >
				       Authorized person's signature:
					   <html:img page="/images/pixelnoir.gif" width="170" height="1" border="0" />
				    </logic:equal>
				</TD>
			    <TD width="176" align="left" nowrap="nowrap">&nbsp;
				    <logic:equal name='<%=GlobalConstants.Impression.LOCALE_KEY%>' property='language' value='fr' >
				       No. employé :
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
							<td>
								<cardex:afficherValeurListeTag name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='intervenant' classe='<%=GlobalConstants.CleListe.INTERVENANT %>'/>
							</td>
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
    <td>****</td>
    <TD align='right'><b><bean:write name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='numeroDossier' /></b></TD>
  </TR>
</TABLE>

<!-- END SIGNATURE -->

</FORM>

</BODY>
</HTML>
