<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "particularités".
Description : Module qui affiche le contenu de l'onglet "particularités", soit une
              liste de particularités.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/22 17:37:17 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/22 17:37:17 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<HTML>

<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<jsp:include page='/commun/commun.jsp' flush="true"/>

<link rel="stylesheet" type="text/css" href='<%= request.getContextPath() %>/styles/lq_styles.css' />

<!-- ------------------------------ -->
<SCRIPT language="JavaScript" type="text/javascript">
  function viewPicture(url){
    if (url != ""){
		windowOpenLocation(theThing);
    }
  }
function zoomIn(nameImage) {
	var image = document.getElementsByName(nameImage)[0];
   	if(parseInt(image.style.zoom) <= 250){
		newZoom= parseInt(image.style.zoom)+10+'%'
		image.style.zoom =newZoom;
   	}
}
function zoomOut(nameImage) {
	var image = document.getElementsByName(nameImage)[0];
  	newZoom= parseInt(image.style.zoom)-10+'%'
  	image.style.zoom =newZoom;
}

function ajusterGrandeur(image){
	var height = image.height;
	var width = image.width;
	
	if (height > width)
		image.height = 205;
	else
		image.width = 235;
}

var compteur = 1; //sert à compter les pages à partir du début du défilement

  function doPrint(){
    //parent.frames('galery_content').focus();
    //parent.frames('galery_content').print();
//Impression de la page courante des résultats
	   var rapport = "<%= GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_GALERIE %>";
	   var url = "<%=request.getContextPath()%>/AffichagePDFListes?RAPPORT=" + rapport; 
	   //alert(url);  
	   window.open(url, 'rapport', 'left=0,top=0,width=' + document.body.clientWidth + ',height=' + document.body.clientHeight + ',menubar=no,toolbar=no,resizable=yes');
  }


  function doClose() {
	parent.window.location='w_recherche_galerie.jsp';
  }

  //-- Activation du défilement automatique.
  var isScrolling = false;
  var timeoutDelay = 0;

  function toggleScrolling(){
  var lancerDefilement = document.getElementsByName( "lancerDefilement" )[0];
  
    if (isScrolling) {
      clearTimeout(isScrolling);
      isScrolling = false;
      document.forms(0).activer_defilement.value = "<bean:message key='debut_defilement'/>";
      lancerDefilement.value = "false";
    }else{
      isScrolling = true;
      doLancerDefilement();
      //-- Le même bouton peut désactiver la navigation
      document.forms(0).activer_defilement.value = "<bean:message key='fin_defilement'/>";
      lancerDefilement.value = "true";
    }
  }

    //-- Exécution du submit
  var secondes = 0; //sert à compter les secondes écoulées

	function doLancerDefilement(){
	    timeoutDelay = (document.forms(0).frequence.value * 1000);
		isScrolling = setTimeout("doDefilement()",timeoutDelay);	
	}

	function doDefilement(){
	    var numeroPageCourante = document.getElementById( "listeResultat.numeroPageCourante" );
	    var nomTable = document.getElementById("nomTable");
	    nomTable.value = "listeResultat";
	    
		if (numeroPageCourante.value == dernier){ //si la dernière page est atteinte
	        numeroPageCourante.value = 1;
		}else{
		    numeroPageCourante.value = parseInt(numeroPageCourante.value) + 1;
		}
	    document.forms(0).submit();
	}


</SCRIPT>

</HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="imagetoolbar" content="no" />
<BODY leftmargin="5" rightmargin="0" topmargin="0" marginheight="5" marginwidth="5">

<!-- Formulaire déclaré uniquement pour automatiser la fermeture de la fenêtre -->
<!-- depuis la fenêtre de contrôle d'impression -->

<html:form action='/galerie/rechercheChangerPage.do' >
<bean:define id="nombrePageTotal" name="rechercheGalerie" property="listeResultat.nombrePageTotal" type="Integer"/>
<script>
	var dernier = <%=nombrePageTotal%>;
</script>


<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />
<TABLE width="750" cellpadding="0" cellspacing="0" border="0" bgcolor="#eeeeee" class="tableOutline">
        <TR>
           <TD><html:img page="/images/blank.gif" width="740" height="1" border="0" /></TD>
        </TR>

        <tr>
            <td align="center">
              <!-- TOP BUTTONS -->
              <TABLE width="740" cellpadding="2" cellspacing="0" border="1">
                <TR>
                  <TD width="170">
					<cardex:NavigationResultatListe name="rechercheGalerie" property="listeResultat" URLChangerPage="/galerie/rechercheChangerPage.do" />
                  </TD>
                  <TD width="150" align="center">
                    <INPUT type="button" name="activer_defilement" value="<bean:message key='debut_defilement'/>" onclick="toggleScrolling();" /></TD>
                  <TD width="200" align="right"><bean:message key='Intervalle'/>
                   <html:hidden name="rechercheGalerie" property="lancerDefilement" />
                   <html:select name="rechercheGalerie"  property="frequence">
                   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.DEFILEMENT%>'/>
                   </html:select>
                   <logic:equal name="rechercheGalerie" property="lancerDefilement" value="true">
						<script>toggleScrolling();</script>
					</logic:equal>                   
                   <bean:message key='secondes'/>
                  </TD>

                  <TD width="65" align="right">
                    <cardex:button securityConstraint='cardex.fenetres.galeries.imprimer' style="width: 60px; text-align: center;" labelKey='cb_imprimer' onclick='doPrint();' />
                  </TD>

                  <TD width="65" align="right">
                    <cardex:button labelKey='cb_fermer' style="width: 60px; text-align: center;" onclick='doClose();' />
                  </TD>

                </TR>
              </TABLE>
              <!-- END TOP BUTTONS -->
            </td>
          </tr>

           <TR>
            <TD><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
          </TR>

        </table>

<html:hidden name="rechercheGalerie" property="sujetAttache"/>
<html:hidden name="rechercheGalerie" property="dossierAttache"/>

<!-- GALERY VISUALIZATION -->
<br clear="left"><!-- Search Kind & nature list -->

<TABLE width="750" cellPadding="3" cellSpacing="0" border="0">
<logic:iterate id='element' name='rechercheGalerie' property='listeResultat.resultatAffichage' indexId="index" >

	<% if (index.intValue() == 0 || index.intValue() == 3)  { %>
	<TR>
	<% } %>
		<TD>
			<DIV align="center" STYLE="overflow:auto; width:240; height:210;" >
				<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />" name='img<%=index%>' border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; ajusterGrandeur(this)" >
			</DIV>
			<div align="center">
			<TABLE cellPadding="0" cellSpacing="0" border="0">
			<TR><TD nowrap="nowrap">
				<logic:equal name="element" property="attachSujet" value='true' >
					<cardex:linkImpression object='element' objectProperty='sujet' page='/sujet/print.do'>
						<b><bean:write name='element' property='reference' /></b>&nbsp;
					</cardex:linkImpression>
				</logic:equal>
				<logic:equal name="element" property="attachDossier" value='true' >
					<cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/show.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_GALERIE%>'>
						<b><bean:write name='element' property='reference' /></b>&nbsp;
					</cardex:linkDossier>
				</logic:equal>
			</TD>
			<TD colspan="2">
				<logic:notEmpty name="element" property="sujetInteretDossiers">
					<table cellPadding="0" cellSpacing="0" border="0">
					<logic:iterate id='sujetInteretDossier' name="element" property="sujetInteretDossiers" >
						<logic:notEmpty name='sujetInteretDossier' property='numeroDossier'>
							<tr><td>
							<cardex:linkDossier dossier='sujetInteretDossier' page='/dossier/show.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_GALERIE%>'>
								<b><bean:write name='sujetInteretDossier' property='numeroDossier' /></b>&nbsp;
							</cardex:linkDossier>
							</td></tr>
						</logic:notEmpty>
					</logic:iterate>
					</table>
				</logic:notEmpty>
			</TD>
	     	</tr>
	     	<tr>
	     	     <td>
					<b><bean:write name='element' property='sansHeureDateCreation' /></b>
				</TD>
				<TD class="severity<cardex:afficherValeurListeTag name='element' property="severite" classe='<%= GlobalConstants.CleListe.SEVERITE %>' />"
				align="left" width="5" height="12" nowrap>
				<cardex:afficherValeurListeTag name='element' property="severite" classe='<%= GlobalConstants.CleListe.SEVERITE %>' />
				</TD>
				<TD nowrap>
					<cardex:linkObject object='element' page='/galerie/photo/show.do'>
						<html:img page="/images/magnify.gif" altKey="cb_consulter" border="0"  />
					</cardex:linkObject>
					<IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut('img<%=index%>');" >
					<IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn('img<%=index%>');" >
				</TD>
			</TR>
			</TABLE>
			</div>
		</TD>
	<% if (index.intValue() == 2 || index.intValue() == 5)  { %>
	</TR>
	<% } %>
	
</logic:iterate>
</TABLE>
 
</html:form>
<!-- END GALERY VISUALIZATION -->
