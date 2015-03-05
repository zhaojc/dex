<%-- --------------------------------------------------------------------------
Use case    : Résultat d'une recherche d'un dossier.
Description : Module d'affichage représentant les résultats d'une recherche de
              dossier.
Author(s)   : $Author: fguerin $, abruno-boucher
Revision    : $Revision: 1.9 $, $Date: 2002/04/22 20:12:22 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.9 $, $Date: 2002/04/22 20:12:22 $, $Author: fguerin $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<SCRIPT language='JavaScript' type='text/javascript'>
function doAudit(id, dossier, cle, site){
//Cette fonction a pour but d'enregistrer un audit de consultation
//des narrations à partir de la recherche. Lorsque l'utilisateur clique
//sur la narration dans les résultats, le servlet inscrit une entrée
//dans la table d'audit. Pour éviter d'effectuer un enregistrement chaque fois
//que la narration est cliquée (par exemple, la barre de défilement), on change
//la hauteur de 1 pixel au premier accès. Par la suite, on vérifie la valeur; si c'est la
//valeur modifiée, l'enregistrement n'est pas effectué.
var narration = document.getElementById(id);
//alert(dossier + " - " + cle + " - " + site);
//alert(narration.style.height);
   if(narration.style.height == "120px"){
	  narration.style.height = "121px";
       var url = "<%= request.getContextPath() %>/AuditRechercheNarration";
        url += "?CLE="+cle;
        url += "&SITE="+site;
        url += "&DOSSIER="+dossier;

        var req = initRequest();
        
        req.onreadystatechange = function() {
        };
		req.open("GET", url, true);
        req.send(null);
   }
}

function initRequest() {

    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
        
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}


</SCRIPT>

<br><!-- Number of records to display -->

<TABLE align="left" width="772" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
 <TR>
    <TD width="772"><b><bean:message key='st_rowcount_t2'/>&nbsp;<bean:write name='rechercheNarration' property='listeResultat.size'/></b></TD>
  </TR>
</TABLE><!-- End Number of records to display -->
<br clear="left">
<logic:greaterThan name='rechercheNarration' property='listeResultat.size' value="0" >
	<jsp:include page="/templates/commun/tpl_tri_commun.jsp" flush="true" />

    <TABLE width="772" cellPadding="2" cellSpacing="0" border="" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_NUMERO_CARDEX%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_COMMENTAIRE%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_CREATEUR%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_TYPE%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_CATEGORIE%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_DATE_CREATION%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
        <TD class="listTableHeader" align="center">
            <cardex:EnteteListeTriable name='rechercheNarration' property="listeResultat" key='<%=NarrationTrieListe.CLE_DATE_APPROBATION%>' URLTrier="/narration/rechercheTrier.do"/>
        </TD>
    </TR>
	<logic:iterate id='element' name='rechercheNarration' property='listeResultat.resultatAffichage' indexId="index">
    <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
        <TD class="listDetailOdd">&nbsp;
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd">
          <cardex:linkDossier dossier='element' dossierProperty='dossier' page='/dossier/showAcces.do' actionSecurite='<%=GlobalConstants.ActionSecurite.CONSULTER_DOSSIER%>'>
          <bean:write name="element" property="dossier.numeroCardexTexte"/>
          </cardex:linkDossier>
        </TD>
        <TD class="listDetailOdd" >
        		   <bean:define id="noDossier" name="element" property="dossier.numeroCardex"/>
                   <bean:define id="cleNarration" name='element' property="cle"/>
		           <bean:define id="siteNarration" name='element' property="site"/>
           <!-- html:textarea styleId="a" name="element" property="narrationSansFormat" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;'  / -->
			<DIV STYLE="overflow:auto; word-wrap: break-word; width:280; height:120; border='0.05cm groove gray'" id="<%=index%>" onfocus="doAudit(<%=index%>,'<%=noDossier%>', <%=cleNarration%>, <%=siteNarration%>);">
					<bean:write filter="false" name="element" property="narrationSansFormat" />
    		</DIV>
        </TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="createurDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dossier.typeDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dossier.categorieDescription"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dateCreation"/></TD>
        <TD class="listDetailOdd">&nbsp;<bean:write name="element" property="dateApprobation"/></TD>
    </TR>
    </logic:iterate>
    </TABLE>
    <TABLE align="left" width="770" cellPadding="5" cellSpacing="0" bgcolor="#ffffff" border="0">
     <TR>
        <TD>&nbsp;</TD>
       <TD ALIGN="right" VALIGN="middle">
    		<cardex:NavigationResultatListe name="rechercheNarration" property="listeResultat" URLChangerPage="/narration/rechercheChangerPage.do"/>
       </TD>
      </TR>
    </TABLE>
<!-- End Number of records to display -->
</logic:greaterThan>
