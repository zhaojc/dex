<%-- --------------------------------------------------------------------------
Use case    : Impression d'un sujet (utilisé par la galerie).
Description : Les boutons ne s'impriment pas sur la page.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/19 17:56:43 $

History     : Voir ci-dessous.

Revision: 1.1 , Date: 2002/03/26 21:09:17 , Author: abruno-boucher
Création.

$Revision: 1.5 $, $Date: 2002/04/19 17:56:43 $, $Author: mlibersan $
Commentaires à jour.

--------------------------------------------------------------------------- --%>
 
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.SujetForm" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.PhotoForm" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META http-equiv="imagetoolbar" content="no"> 
<LINK rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/styles/lq_styles.css'>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">

function zoomIn(image) {
      if(parseInt(image.style.zoom) <= 250){
  	newZoom= parseInt(image.style.zoom)+10+'%'
	image.style.zoom =newZoom;
      }
}
function zoomOut(image) {
      newZoom= parseInt(image.style.zoom)-10+'%'
     image.style.zoom =newZoom;
}

function doPrint() {
   var cle = document.forms(0).cle.value;
   var site = document.forms(0).site.value;

   var url = "<%=request.getContextPath()+"/RapportAffichage?rapportFormClass="+GlobalConstants.RapportForm.GALERIE_SUJET_RAPPORT_FORM_CDX_0004%>&cle="+cle+"&site="+site;
   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');	   
}

function doClose() {
  	onUnload = false;
	window.parent.close();
}

</SCRIPT>

<TITLE><bean:message key="titre.application.cardex"/></TITLE>

</HEAD>
<BODY leftmargin="5" rightmargin="0" topmargin="5" marginheight="5" marginwidth="5" 
oncontextmenu="return false;" >

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->
<FORM action="#">

<DIV align="center" STYLE="overflow:none; width:100; height:100; background-color:transparent; z-index: 1; position: absolute; right: 100px; top: 1px;" >
     <html:img page="/images/filigrane.gif" border="0" style="background-color:transparent;"/>
</DIV>

<!-- INDIVIDUAL DESCRIPTION -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD width="30"><html:img page="/images/pixelnoir.gif" width="26" height="2" border="0" /></TD>
    <TD width="80" class="tabSubject"><bean:message key='gb_sujet'/></TD>
    <TD width="540"><html:img page="/images/pixelnoir.gif" width="540" height="2" border="0" /></TD>
  </TR>

  <TR>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="10" border="0" /></TD>
  </TR>
</TABLE>


<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD width="30">&nbsp;</TD>
    <TD valign="top">

      <!-- DATA SECTION -->
      <TABLE width="590" cellpadding="2" cellspacing="0" border="0">
       <TR>
          <TD width="305" valign="top">

            <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                  style="border-style: solid; border-width: 1px; border-color: #000000;">

              <TR>
                 <TD width="125"><html:img page="/images/blank.gif" width="121" height="1" border="0" /></TD>
                 <TD width="175"><html:img page="/images/blank.gif" width="171" height="1" border="0" /></TD>
              </TR>

              <TR>
                 <TD width="125" align="right"><b><bean:message key='v_su_numero_t'/></b></TD>
                 <TD width="175">
                    <cardex:linkSujet sujet='sujet' page='/sujet/show.do'>
                       <bean:write name="sujet" property="numeroFiche"/>
                    </cardex:linkSujet>
                 </TD>
              </TR>

              <TR>
                 <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>

              <TR>
                <TD width="125" align="right" class="errorHeader"><u><bean:message key='v_su_nom_t' /></u></TD>
                <TD width="175"><bean:write name="sujet" property="nom"/></TD>
              </TR>


              <TR>
                <TD align="right" class="errorHeader"><u><bean:message key='v_su_prenom_t' /></u></TD>
                <TD><bean:write name="sujet" property="prenom"/></TD>
              </TR>

              <TR>
                <TD align="right" class="errorHeader"><u><bean:message key='v_su_surnom_t' /></u></TD>
                <TD><bean:write name="sujet" property="alias"/></TD>
              </TR>

              <TR>
                  <TD align="right"><b><bean:message key='date_naissance_t'/></b></TD>
                  <TD><bean:write name="sujet" property="dateNaissance"/></TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message key='i_sx_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='sujet' property="sexe"/>
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message key='i_ls_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.LANGUE %>' name='sujet' property="langue"/>
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message key='i_nt_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.ETHNIE %>' name='sujet' property="ethnie"/>
                  </TD>
               </TR>

               <TR>
                  <TD align="right"><b><bean:message key='i_ra_cle_t'/></b></TD>
                  <TD>
                  		<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.RACE %>' name='sujet' property="race"/>
                  </TD>
               </TR>

              <TR>
                 <TD width="300" colspan="2"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
              </TR>
            </TABLE>

            <BR>
            <!-- CARACTERISTICS -->
              <TABLE width="300" cellpadding="2" cellspacing="0" border="0"
                  style="border-style: solid; border-width: 1px; border-color: #000000;">
                 <!-- Following row for alignment purposes only -->
                 <TR>
                    <TD width="25"><html:img page="/images/blank.gif" width="21" height="1" border="0" /></TD>
                    <TD width="100"><html:img page="/images/blank.gif" width="96" height="1" border="0" /></TD>
                    <TD width="175"><html:img page="/images/blank.gif" width="171" height="1" border="0" /></TD>
                 </TR>

                 <TR>
                    <TD width="125" align="right" colspan="2"><b><bean:message key='caracteristiques_t'/></b></TD>
                    <TD width="175">&nbsp;</TD>
                 </TR>
                 <TR>
                    <TD width="25">&nbsp;</TD>
                    <TD width="275" colspan="2">
                      <UL>
					    <logic:iterate id="element" name='sujet' property='caracteristiques.doubleListe.droiteColLabel'>
					       <LI class="listDetailOdd"><%=element%></LI>
					    </logic:iterate>                      
                      </UL>
                    </TD>
                 </TR>
              </TABLE>
              <!-- END CARACTERISTICS -->
              <BR>
		<TABLE width="300" cellpadding="2" cellspacing="0" border="0"
		       style="border-style: solid; border-width: 1px; border-color: #000000;">
		  <TR>
		    <TD align="left" valign="top">&nbsp;<b><bean:message locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='tabpage_jeu'/> : </b></TD>
		    <TD colspan="3">
		      <logic:iterate id="element" name='sujet' property='dossiers'>
		      <logic:present name="element" >
		         <logic:equal name="element" property="statut" value='<%=String.valueOf(GlobalConstants.Statut.DOSSIER_ACTIF)%>'>
		         	<logic:present name="element" property="jeux" >
					    <logic:iterate id="subelement" name="element" property='jeux.doubleListe.droiteColLabel'>
							<%=subelement%>, &nbsp;
					    </logic:iterate>
					</logic:present>   
				 </logic:equal>
		      </logic:present>
		      </logic:iterate>
		    </TD>
		  </TR>
		</TABLE>

          </TD>

          <TD width="285" align="center" valign="top">
            <%
                SujetForm sujet = (SujetForm) pageContext.findAttribute("sujet");
                Collection photos = sujet.getToutesPhotos();

                if (photos.size() > 0)
                {
                    Iterator iter = photos.iterator();
                    PhotoForm photoPremiere = (PhotoForm) iter.next();
            %>
            <SCRIPT>
	           	var imageArraySRC = new Array();          
            	imageArraySRC[0] = "<%=request.getContextPath()%>/AffichageLoupe?CLE=<%=photoPremiere.getLienElement()%>&SITE=<%=photoPremiere.getLienSiteElement()%>&EXTENSION=<%=photoPremiere.getExtension()%>";
            <%for (int i = 1; iter.hasNext(); i++)
                {
                    PhotoForm photo = (PhotoForm) iter.next();%>
            	imageArraySRC[<%=i%>] = "<%=request.getContextPath()%>/AffichageLoupe?CLE=<%=photo.getLienElement()%>&SITE=<%=photo.getLienSiteElement()%>&EXTENSION=<%=photo.getExtension()%>";
            <%}%>
				var position = 0;
				
				function chargerImage(newPos, imageBtn, cible){

					if (0 <= newPos && newPos <= imageArraySRC.length-1){
						document.getElementsByName("photo")[0].src = imageArraySRC[newPos];
						document.getElementsByName("photo2")[0].src = imageArraySRC[newPos];
						position = newPos;
					}
				}

            </SCRIPT>
            
		    <DIV align="center" STYLE="overflow:auto; width:320; height:240;" >
			<IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<%=photoPremiere.getLienElement()%>&SITE=<%=photoPremiere.getLienSiteElement()%>&EXTENSION=<%=photoPremiere.getExtension()%>" border="0" CLASS="tableOutline" alt="" name="photo" onload="this.style.zoom='100%'; this.style.height='230'" onclick="photoAgrandie.style.visibility = 'visible'" onmouseenter="this.style.cursor='hand'"; onmouseleave="this.style.cursor='default'"; >
		    </DIV>
				    <DIV align="center" STYLE="overflow:none; width:500; height:700;" style="z-index: 1; position: absolute; left: 50px; top: 50px; visibility= 'hidden'" id="photoAgrandie">
				       <TABLE>
			              <TR>
			              	  <TD>
				        		<IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<%=photoPremiere.getLienElement()%>&SITE=<%=photoPremiere.getLienSiteElement()%>&EXTENSION=<%=photoPremiere.getExtension()%>" name="photo2" onload="this.style.zoom='100%'; this.style.height='690'"; onclick="photoAgrandie.style.visibility = 'hidden'"  >
	  						  </TD>
	  				      </TR>
	  			       </TABLE>
				    </DIV>
	        <%
	            if (photos.size() > 1)
	                {
	        %>
			<IMG src="<%=request.getContextPath()%>/images/Precedent.gif" name="btnPrecedent" onclick="chargerImage(position-1);" onmouseover="this.src='<%=request.getContextPath()%>/images/Precedent_Click.gif'" onmouseout="this.src='<%=request.getContextPath()%>/images/Precedent.gif'">
	        <IMG src="<%=request.getContextPath()%>/images/Suivant.gif" name="btnSuivant" onclick="chargerImage(position+1);" onmouseover="this.src='<%=request.getContextPath()%>/images/Suivant_Click.gif'" onmouseout="this.src='<%=request.getContextPath()%>/images/Suivant.gif'">
            <%
                }
                }
                else
                {
            %>
                <html:img page="/images/no_picture.jpg" width="275" height="275" border="1" alt="portrait" />
            <%
                }
            %>
          </TD>
        </TR>
        </TABLE>
      <!-- END DATA SECTION -->

    </TD>
    <TD width="30">&nbsp;</TD>
  </TR>
</TABLE>
<!-- END INDIVIDUAL DESCRIPTION -->


<BR>
<!-- FILES RELATED TO INDIVIDUAL -->
<TABLE width="650" cellpadding="0" cellspacing="0" border="0">
  <TR>
    <TD width="30"><html:img page="/images/pixelnoir.gif" width="26" height="2" border="0" /></TD>
    <TD width="80" class="errorHeader"><bean:message key='gb_dossier'/></TD>
    <TD width="540"><html:img page="/images/pixelnoir.gif" width="540" height="2" border="0" /></TD>
  </TR>

  <TR>
    <TD colspan="3"><html:img page="/images/blank.gif" width="1" height="5" border="0" /></TD>
  </TR>
</TABLE>

<TABLE width="700" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff"
  >
  <TR>
  <%-- manque lien pour ouvrir le dossier --%>
      <TD class="listTableHeader" nowrap><bean:message key='v_do_ancienne_reference'/></TD>
      <TD class="listTableHeader" nowrap><bean:message key='v_do_numero_dossier'/></TD>
      <TD class="listTableHeader"><bean:message key='i_sev_t'/></TD>
      <TD class="listTableHeader"><bean:message key='i_cc_commentaire_t'/></TD>
      <TD class="listTableHeader"><bean:message key='i_nature_t'/></TD>
      <TD class="listTableHeader"><bean:message key='i_type_t'/></TD>
      <TD class="listTableHeader"><bean:message key='tabpage_categorie'/></TD>
      <TD class="listTableHeader"><bean:message key='tabpage_statut'/></TD>
      <TD class="listTableHeader"><bean:message key='tabpage_role'/></TD>
      <TD class="listTableHeader" nowrap><bean:message key='d_is_date_debut_t'/></TD>
      <TD class="listTableHeader" nowrap><bean:message key='d_is_date_fin_t'/></TD>
      <TD class="listTableHeader"><bean:message key='v_do_assigne_a'/></TD>
  </TR>
	<logic:iterate id="element" name="sujet" property='dossiers' indexId="index">
		<TR>
			<TD class="footnotes"><FONT size="1pt">&nbsp;<bean:write name="element" property="numeroDossier" /></FONT></TD>
			<TD class="footnotes" nowrap><FONT size="1pt"> <cardex:linkDossier
				dossier='element' page='/dossier/show.do'
				actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
				<bean:write name="element" property="numeroCardex.site" />-<bean:write
					name="element" property="numeroCardex.date" />-<bean:write
					name="element" property="numeroCardex.sequence" />
			</cardex:linkDossier></FONT></TD>
			<TD
				class="severity<cardex:afficherValeurListeTag name='element' property='severite' classe='<%= GlobalConstants.CleListe.SEVERITE %>' />"
				align="center">&nbsp; <cardex:afficherValeurListeTag
				name='element' property='severite'
				classe='<%= GlobalConstants.CleListe.SEVERITE %>' /></TD>
			<TD class="footnotes" align="center"><cardex:afficherValeurListeTag
				name="element" property="confidentialite"
				classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE %>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>' /></TD>
			<TD class="footnotes"><FONT size="+1pt"> <bean:define
				id="genre" name='element' property='genre' type="String" /> <cardex:afficherValeurListeTag
				name='element' property='nature'
				classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'
				valeurDiscriminant='<%=genre%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_GALERIE %>' />
			</FONT></TD>
			<TD class="footnotes"><FONT size="+1pt"> <bean:define
				id="nature" name='element' property='nature' type="String" /> <cardex:afficherValeurListeTag
				name='element' property='type'
				classe='<%=GlobalConstants.CleListe.TYPE %>'
				valeurDiscriminant='<%=nature%>' /> </FONT></TD>
			<TD class="footnotes"><FONT size="+1pt"> <bean:define
				id="type" name='element' property='type' type="String" /> <cardex:afficherValeurListeTag
				name='element' property='categorie'
				classe='<%=GlobalConstants.CleListe.CATEGORIE %>'
				valeurDiscriminant='<%=type%>' /> </FONT></TD>
			<TD class="footnotes"><FONT size="+1pt"> <cardex:afficherValeurListeTag
				name='element' property='statut'
				classe='<%= GlobalConstants.CleListe.STATUT %>'
				valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>' />
			</FONT></TD>
			<TD class="listDetailOdd">
			<FONT size="+1pt">
				<cardex:afficherValeurListeTag
				name='element' property='role'
				classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'				
				 /> </FONT>
			</TD>

			<TD class="footnotes" nowrap><FONT size="+1pt"><bean:write
				name="element" property="dateDebut" /></FONT></TD>
			<TD class="footnotes" nowrap><FONT size="+1pt">&nbsp;<bean:write
				name="element" property="dateFin" /></FONT></TD>
			<TD class="footnotes"><FONT size="+1pt"><bean:write
				name="element" property="intervenant" /></FONT></TD>
		</TR>
	</logic:iterate>
</TABLE>

<html:hidden name='sujet' property='cle' />
<html:hidden name='sujet' property='site' />
</FORM>
<table width="650" bgcolor="#eeeeee" class="tableOutline">
  <TR>
       <TD>
	     <cardex:button securityConstraint='cardex.sujet.base.imprimer' labelKey='cb_imprimer' style='width: 60px; text-align: center;' onclick='doPrint();' />&nbsp;&nbsp;
       </TD>
      <TD width="250" colspan="2" align="right">
           <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
      </TD>
  </TR>
</TABLE>

</BODY>
</HTML>
