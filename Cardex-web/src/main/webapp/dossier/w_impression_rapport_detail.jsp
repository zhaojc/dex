<%-- --------------------------------------------------------------------------
Use case    : Impression du rapport uniformisé
Author(s)   : François Guérin
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
<SCRIPT language="JavaScript" type="text/javascript">
function dimensionner(objet) {
//--Réajustement de l'image pour l'impression sur une feuille 8,5x11
alert(objet.width + " - " + objet.height);
	if (objet.width > 650){
	   objet.width = "650";
	}
	if (objet.height > 900){
	   objet.height = "900";
	}
}
</SCRIPT>

<title><bean:message key="titre.application.cardex"/></title>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5">

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->
<FORM action="#">
<%-- Le clipboard doit être actif dans les narrations. --%>
<!-- input type="hidden" name="clipboard" value="required" -->

<!-- Page titre -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsTitre' >
<logic:present name="narration" >
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" >
    <TR>
      <TD width="652" align='center'>
        	 	<html:img page="/images/logo_LQ.jpg" border="0" width='250' height='350' />
      </TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle'>
          <P><BR>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
  </TABLE>
</logic:present>
</logic:iterate>

<!-- Identification -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsIdentification' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%' height="700" align='center' valign='middle'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Table des matières -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsTable' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD  width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Introduction -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsIntroduction' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always; style='page-break-after:always''>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Section Enquête -->

<!-- Sujets -->
<logic:iterate id="sujet" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='sujets' indexId='index'>
<logic:present name="sujet">
<% if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='sujets_impliqués_t'/></i>
<BR>

<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
<BR>  
<i><bean:write name="index1"/></i>
<BR>
	     <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_nom_t' /></i>
		  <bean:write name='sujet' property="nom"/>
<BR>
	     <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_prenom_t' /></i>
		  <bean:write name='sujet' property="prenom"/>
<BR>	     
	     <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_su_surnom_t' /></i>
		  <bean:write name='sujet' property="alias"/>
<BR>	     
	   <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_sx_cle_t'/></i>
		  <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='sujet' property="sexe"/>
<BR>
	     <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_nt_cle_t'/></i>
		  <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='sujet' property="ethnie"/>
<BR>	     
	     <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ra_cle_t'/></i>
		  <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.RACE %>' name='sujet' property="race"/>
<BR>		
	     <i>DDN : </i>
		  <bean:write name='sujet' property="dateNaissance"/>
<BR>
	     <i>NAS : </i>
		  <bean:write name='sujet' property="numeroAssuranceSociale"/>
<BR>	     
	     <i># de permis de conduire : </i>
		  <bean:write name='sujet' property="numeroPermisConduire"/>

    <html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" />
<BR>
            <logic:iterate id="element" name="sujet" property="adresses">
		<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_adresse_t' /></i>
                <cardex:affichageAdresse name='element' numeroLigne="1" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
                <cardex:affichageAdresse name='element' numeroLigne="2" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
                
                <html:img page="/images/blank.gif" width="1" height="1" border="0" />
<BR>                
                <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_vi_cle_t' /></i>
            	<bean:define id="pays" name='element' property="pays" type="String"/>
            	<bean:define id="province" name='element' property="province" type="String"/>
               	<cardex:afficherValeurListeTag name='element' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>                
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_pr_cle_t' /></i>
	            <cardex:afficherValeurListeTag name='element' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>
                                    
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_pa_cle_t' /></i>
                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.PAYS %>' name='element' property="pays"/>
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t' /></i>
                <bean:write name='element' property="codePostal"/>
<BR>              
              <i># de tél. :</i>
                <bean:write name='element' property="telephone1"/>
<BR>
              <i># de client/employé : </i>
                <bean:write name='sujet' property="numeroClientEmploye"/>
<br>              

  </logic:iterate>
	    <logic:iterate id="element" name="sujet" property="photos" length="1" >
	    <logic:present name="element" >
	         <logic:iterate id="subelement" name="element" length="1" >
			<DIV align="center" STYLE="overflow:hidden; width:215; height:165;" >
	              	     <IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='160'">
	  		</DIV>
	  	 </logic:iterate>
	    </logic:present>
	    </logic:iterate>
          <html:img page="/images/pixelnoir.gif" width="650" height="1" border="0" />

  </logic:present>

  </logic:iterate>

<P>

<!-- Véhicules -->
<logic:iterate id="vehicule" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='vehicules' indexId='index'>
<logic:present name="vehicule">
<html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" />
<BR>
<% if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
  <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_vehicule' /></i>
<% } %>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
<br>
   <bean:write name="index1"/>
<br>   
          <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_ma_cle_t'/></i>
          <bean:define id="marque" name='vehicule' property="marque" type="String"/>
            <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.MARQUE %>' name="vehicule" property='marque'/>
<BR>
	<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_md_cle_t' /></i>
            <cardex:afficherValeurListeTag name='vehicule' property="modele" classe='<%= GlobalConstants.CleListe.MODELE %>' valeurDiscriminant='<%=marque %>'/>
<BR>
	<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='c_ve_annee_t' /></i>
          <bean:write name='vehicule' property='annee' /> 
<BR>	
	<i># de plaque d'immatriculation : </i>
            <bean:write name='vehicule' property='immatriculation' />
<BR>
	<i>Province d'immatriculation :</i>
        <cardex:afficherValeurListeTag name='vehicule' property="cleProvince" classe='<%= GlobalConstants.CleListe.PROVINCE_SANS_REQUIS %>' />

<BR>	
<html:img page="/images/blank.gif" width="1" height="1" border="0" />
</P>
</logic:present>
</logic:iterate>
<!-- Sociétés -->
<logic:iterate id="societe" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='societes' indexId='index'>
<logic:present name="societe" >

<%
	if (String.valueOf(pageContext.findAttribute("index")).trim().equals("0")) {
%>
<html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" />
<BR>
<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_societe'/></i>
<% } %>
<BR>
  <%
  // Calcul de l'index basé à 1 (l'énumération est basée à zéro)
  Integer index0 = (Integer) pageContext.findAttribute("index");
  pageContext.setAttribute("index1", new Integer( index0.intValue() + 1 ));
  %>
<br>
	<bean:write name="index1"/>
<BR>          
          <i># de fiche : </i>
         <bean:write name='societe' property="numeroFiche" />
<BR>         
	<i>Nom du commerce : </i>
          <bean:write name='societe' property='nom' />
<BR>	
	<i>Nom du responsable : </i>
          <bean:write name='societe' property='referenceNom' />,&nbsp;<bean:write name='societe' property='referencePrenom' />
<BR>          
            <logic:iterate id="element" name="societe" property="adresses">
	    <logic:present name="element" >
<html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" />
		<i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_adresse_t' /></i>
                <cardex:affichageAdresse name='element' numeroLigne="1" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
                <cardex:affichageAdresse name='element' numeroLigne="2" langueKey='<%=GlobalConstants.Impression.LOCALE_KEY%>'/>
                <html:img page="/images/blank.gif" width="1" height="1" border="0" />
<BR>                
                <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_vi_cle_t' /></i>
            	<bean:define id="pays" name='element' property="pays" type="String"/>
            	<bean:define id="province" name='element' property="province" type="String"/>
               	<cardex:afficherValeurListeTag name='element' property="ville" classe='<%= GlobalConstants.CleListe.VILLE %>' valeurDiscriminant='<%=province %>'/>                
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='l_pr_cle_t' /></i>
	            <cardex:afficherValeurListeTag name='element' property="province" classe='<%= GlobalConstants.CleListe.PROVINCE %>' valeurDiscriminant='<%=pays %>'/>
                
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='i_pa_cle_t' /></i>
                <cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.PAYS %>' name='element' property="pays"/>
<BR>              
              <i><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='v_ad_code_postal_t' /></i>
                <bean:write name='element' property="codePostal"/>
<BR>              
              <i># de tél. :</i>
                <bean:write name='element' property="telephone1"/>
<BR>
	</logic:present>
	</logic:iterate>

<html:img page="/images/pixelnoir.gif" width="650" height="2" border="0" />

</logic:present>
</logic:iterate>

<!-- Photos -->
<logic:iterate id="element" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='photos'>
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
            	     <IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='150'" >
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
        
    </TR>
    <TR>
        <TD colspan="3"><P>&nbsp;</P></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Enquête -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsEnquete' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Fin section Enquête -->

<!-- Constats -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsConstats' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Conclusion -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsConclusion' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Recommandations -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsRecommandations' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' style='page-break-before:always'>
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Autres -->
<logic:iterate id="narration" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='narrationsAutres1' >
<logic:present name="narration" >
<P>
  <TABLE width="652" cellpadding="2" cellspacing="0" border="0" align='center' >
    <TR>
      <TD width="652"><html:img page="/images/pixelnoir.gif" width="648" height="2" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" align='center' valign='middle' >
          <P>
          <TABLE>
            <TR>
              <TD width='100%'>
                <%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %>
              </TD>
            </TR>
          </TABLE>
          
      </TD>
    </TR>
    <TR>
      <TD width="652"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD width="652" height="15" ><html:img page="/images/pixelnoir.gif" width="648" height="1" border="0" /></TD>
    </TR>

  </TABLE>

</logic:present>
</logic:iterate>

<!-- Pièces jointes -->
<logic:iterate id="element" name='<%=GlobalConstants.Impression.DOSSIER_KEY%>' property='piecesJointes' >
<logic:present name="element" >
  <TABLE width="650" cellpadding="2" cellspacing="0" border="0" align='center'  >
    <TR>
      <TD width="650" align='center' valign='middle' >
		  <logic:equal name='element' property='extension' value="JPG" >                        
		  	    <DIV align="center" id="photoImprimer" STYLE="overflow:none; width:650; height:850;" >
				    <IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />" onload="this.style.zoom='100%'; if(this.height > '850'){this.height = '850';}; if(this.width > '650'){this.width = '650';}" GALLERYIMG="no">        
			    </DIV>   
	    	  </logic:equal>
      </TD>
    </TR>
    <TR>
      <TD width="650"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>

  </TABLE>
</logic:present>
</logic:iterate>

        <jsp:include page="/templates/dossier/tpl_impression_consignation.jsp" flush="true" />

<TABLE width="650">
  <TR>
    <TD align='right'><b>CDX_0231</b></TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</HTML>
