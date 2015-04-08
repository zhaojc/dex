
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>


<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<SCRIPT LANGUAGE="JavaScript">

function doConfirmLinkSuppression() {
  return confirm('<bean:message key="cardex_suppression" />');
}

function doSoumettreRafraichir() {
 	unlockFields();
	//-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + preContexteApplicatif.toString() + contexteApplicatif.toString() + "/nouveau/sujet/refresh.do"%>');
}

function doOk() {
   unlockFields();
	detruireOnglet();
  //-- Fonction déclarée dans lq_scripts.js
	soumettre('<%= request.getContextPath() + preContexteApplicatif.toString() + contexteApplicatif.toString() + "/nouveau/sujet/update.do"%>');
}

function doClose() {
	detruireOnglet();
	windowClose();
}

function doPrint() {
	soumettre('<%= request.getContextPath() + "/sujet/printFiche.do"%>');
}

function getOnglet(){
//On affiche le dernier onglet choisi par l'utilisateur. Par défaut, lors de la
//première ouverture du dossier, on active l'onglet Narration.
   var cookies = document.cookie;
//alert(cookies);   
   if (!cookies){ // no cookies
      return "<bean:write name="sujet" property="ongletDefaut" />";
   }
   
   var search = "sujet=";
   var iLen = cookies.length;
   if (iLen > 0) 
   {
      // find where cookie begins
      var begin = cookies.indexOf(search);
      if (begin != -1)
      {          
         begin += search.length;
         // search for the end of the value
         var end = cookies.indexOf(";", begin);
         if (end == -1)
         {
            // delimiter not found, so eat rest of cookie string
            end = iLen;
         }

         return unescape(cookies.substring(begin, end));
      }
   }

   return "<bean:write name="sujet" property="ongletDefaut" />"; // cookie non trouvé
}

  
function setOnglet(onglet){
//Fontions pour conserver l'onglet activé par l'utilisateur lors du retour du dossier.
//On fixe une expiration automatique à 10 minutes
   var c = new Date();
   c.setTime (c.getTime() + (1000 * 600)); 
  document.cookie = "sujet=" + escape(onglet) + "; expires="+c.toGMTString();
   //alert(document.cookie);       
}

function detruireOnglet(){
//Suppression du cookie à la fermeture de la fenêtre
   var c = new Date();
   c.setTime (c.getTime() - 1); 
   // document.cookie = "onglet" + document.forms(0).cle.value + "=; expires=Thu, 01-Jan-70 00:00:01 GMT";
   document.cookie = "sujet=1; expires="+c.toGMTString();
//alert(document.cookie);
}

function doVerificationNumeroFiche() {
}

function doToggleDivisionVisibility(){
	toggleDivisionVisibility(getOnglet());
}

addEvent( window, 'load', doToggleDivisionVisibility );

</SCRIPT>

	<tiles:insert page="/templates/sujet/tpl_sujet_formulaire.jsp" flush="true">
		<tiles:put name="urlSecuriteSauvegarde" value='<%=preContexteApplicatif.toString() + contexteApplicatif.toString() + "/nouveau/sujet/update.do"%>' />
		<tiles:put name="actionSecurite" value='<%=GlobalConstants.ActionSecurite.MODIFICATION%>' />
	</tiles:insert>	


    <!-- ------------------------------ -->
    <!-- BEGINNING OF DYNAMIC DIVISIONS -->

        <jsp:include page="/templates/sujet/tpl_menu_onglets_sujet.jsp" flush="true" />
  <DIV id="DIVISIONS_GROUP">
	<tiles:insert page="/templates/narrations/tiles/tpl_onglet_list_narrations.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>
	<tiles:insert page="/templates/dossier/tiles/tpl_onglet_list_dossiers.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>  	
	<tiles:insert page="/templates/societe/tiles/tpl_onglet_list_societes.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>  	        

	<tiles:insert page="/templates/sujet/tiles/tpl_onglet_list_sujets.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>

	<tiles:insert page="/templates/adresses/tiles/tpl_onglet_list_addresses.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
	  	<tiles:put name="form" value="sujet"/>
  	</tiles:insert>        

	<tiles:insert page="/templates/caracteristiques/tiles/tpl_onglet_list_caracteristiques.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>

	<tiles:insert page="/templates/photo/tiles/tpl_onglet_list_photos.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>        
        
	<tiles:insert page="/templates/vehicule/tiles/tpl_onglet_list_vehicules.jsp" flush="true">
	  	<tiles:put name="preContexteApplicatif" value="<%=contexteApplicatif%>"/>
	  	<tiles:put name="contexteApplicatif" value="/sujet"/>
  	</tiles:insert>        
  </DIV>
  <!-- END DIVISIONS_GROUP -->
  
<SCRIPT type="text/javascript">
setShow("menuOnglets");
</SCRIPT>
