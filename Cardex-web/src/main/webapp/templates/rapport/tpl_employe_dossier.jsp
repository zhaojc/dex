<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@page import="com.lotoquebec.cardexCommun.util.StringUtils"%>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>

<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
		soumettreActionMethod("rafraichir");
	}

	
	function doRafraichir(liste,cle) {
	    var url = "";
	    //alert(liste + cle);  
	    if (cle == "") {
	        viderListe(liste);
	    } else {
	       if(liste == "groupesIntervenants"){ //Le paramètre LANGUE sert pour le statut (ici à NON pour avoir toute la liste).
	           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=NON&CLE=" + cle;
	        }else{
	           url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&LANGUE=<%= var_lang %>&CLE=" + cle;
	        }
	//alert(url);   
	        var req = initRequest(url);
	        req.onreadystatechange = function() {
	            if (req.readyState == 4) {
	                if (req.status == 200) {
	                    parseMessages(req.responseXML, liste);
	                } else if (req.status == 204){
	                    viderListe(liste);
	                }
	            }
	        };
		req.open("GET", url, true);
	        req.send(null);
	    }
	}
	
	function parseMessages(responseXML, liste) {
	    var listes = responseXML.getElementsByTagName("listes")[0];
	    if (listes.childNodes.length > 0) {
	      viderListe(liste);
	      for (loop = 0; loop < listes.childNodes.length; loop++) {
		var entree = listes.childNodes[loop];
	        var texte = entree.getElementsByTagName("texte")[0];
	        var valeur = entree.getElementsByTagName("valeur")[0];
	        remplirListe(liste,valeur.childNodes[0].nodeValue, texte.childNodes[0].nodeValue);
	      }
	    } else {
	        viderListe(liste);
	    }
	    //On vérifie si la liste vient d'une sélection dans la liste Type-Categorie. 
	    //Si oui, on sélectionne la catégorie dans la liste Catégorie.
	    if(document.forms(0).typeCategorie.value != ""){
	       var cle = document.forms(0).typeCategorie.value;
	       var pos = cle.indexOf("/");
	       if (pos > -1){
	       	  document.forms(0).categorie.value = cle.substring(0,pos);
	       	  document.forms(0).typeCategorie.value = "";
		  doConsulter();
	       }
	    }
	}
	
	function remplirListe(liste,valeur,texte){
	//Ajout des nouvelles entrées de la liste déroulante retournée
	//après un changement d'une liste dépendante.
		var oOption = document.createElement("OPTION");
		oOption.text = texte;
		oOption.value = valeur;
	     if(liste == 'categorie'){
			document.forms(0).categorie.add(oOption); 
	     }
	     if(liste == 'type'){
		document.forms(0).type.add(oOption); 
	     }
	     if(liste == 'typeCategorie'){
		document.forms(0).typeCategorie.add(oOption); 
	     }
	     if(liste == 'groupesIntervenants'){
		document.forms(0).groupesIntervenants.add(oOption); 
	     }
	     
	}
	
	function viderListe(liste){
	//On vide la liste d'abord avant d'insérer les valeurs retournées
		var oOption = document.createElement("OPTION");
		oOption.text = "";
		oOption.value = "";
	  if(liste == 'categorie'){
		contenu = document.forms(0).categorie;
		contenu.options.length = 0;    
		document.forms(0).categorie.add(oOption); 
	  }
	  if(liste == 'type'){
		contenu = document.forms(0).type;
		contenu.options.length = 0;    
		document.forms(0).type.add(oOption); 
	  }
	  if(liste == 'typeCategorie'){
		contenu = document.forms(0).typeCategorie;
		contenu.options.length = 0;    
		document.forms(0).typeCategorie.add(oOption); 
	  }
	  if(liste == 'groupesIntervenants'){
		contenu = document.forms(0).groupesIntervenants;
		contenu.options.length = 0;    
		document.forms(0).groupesIntervenants.add(oOption); 
	  }
	}
	
	function initRequest(url) {
	    if (window.XMLHttpRequest) {
	        return new XMLHttpRequest();
	    } else if (window.ActiveXObject) {
	        xml = new ActiveXObject("Microsoft.XMLHTTP");
	        return xml;
	    }
	}
	
	function doTraits(objet,nomProchainChamp) {
		//-- Insertion de traits d'union dans les dates, s'il y a lieu.
		   dateSaisie = objet.value;
		   if(dateSaisie.length == 8 && dateSaisie.indexOf("-") == '-1'){
		      if("<%= var_lang %>" == "fr"){
		 	dateSaisie = dateSaisie.substring(0,4)+"-"+dateSaisie.substring(4,6)+"-"+dateSaisie.substring(6,8);
		      }else{
		        if(document.forms(0).elements(3).value == ""){ //-- On ne formatte pas en anglais le numéro de dossier
			    dateSaisie = dateSaisie.substring(0,2)+"/"+dateSaisie.substring(2,4)+"/"+dateSaisie.substring(4,8);
			}
		      }
		    objet.value = dateSaisie;
		    var prochainChamp = document.getElementsByName( nomProchainChamp )[0];
				prochainChamp.focus();
		   }
	}
		
</SCRIPT>

<bean:define id="siteOrigine" name="rapportEmployeDossierRapportForm_CDX_0042" property="site" type="String"/>
<bean:define id="nature" name="rapportEmployeDossierRapportForm_CDX_0042" property="nature" type="String"/>

<TABLE width="680" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
	<TR>
		<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br></TD>
    </TR> 
	<TR>
	  <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_en_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <bean:define id="entite" name="rapportEmployeDossierRapportForm_CDX_0042" property="entite" type="String"/>
        <myriap:select name='rapportEmployeDossierRapportForm_CDX_0042' property='entite' size='1'  tabindex="1" style='HEIGHT: 20px; WIDTH: 200px' onchange='document.forms(0).site.selectedIndex=0;document.forms(0).genre.selectedIndex=0;document.forms(0).nature.selectedIndex=0;document.forms(0).type.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='site' style='HEIGHT: 20px; WIDTH: 200px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" colspan="3">
      <bean:define id="genre" name='rapportEmployeDossierRapportForm_CDX_0042' property="genre" type="String"/>
       <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='genre' style='HEIGHT: 20px; WIDTH: 200px' tabindex="3" onchange='doSoumettreRafraichir();' >
	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
				valeurDiscriminant='<%=entite%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" colspan="3">
       <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='nature' style='HEIGHT: 20px; WIDTH: 200px' tabindex="4" onchange="doSoumettreRafraichir();" >
	         <cardex:optionTag
	         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
				valeurDiscriminant='<%=genre%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" colspan="3">
	    <bean:define id="nature" name='rapportEmployeDossierRapportForm_CDX_0042' property='nature' type="String"/>
        <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='type' style='HEIGHT: 20px; WIDTH: 200px' tabindex="5" onchange="doSoumettreRafraichir();" >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
			valeurDiscriminant='<%=nature%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
   </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" nowrap colspan="3">
      	 <bean:define id="type" name='rapportEmployeDossierRapportForm_CDX_0042' property='type' type="String"/>
         <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='categorie' tabindex="6" style='HEIGHT: 20px; WIDTH: 200px' >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		 </myriap:select>
      </TD>
	</TR>
	<TR>
      <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" colspan="3">
       <myriap:select size='1' name='rapportEmployeDossierRapportForm_CDX_0042' property='statut' style='HEIGHT: 20px; WIDTH: 200px' tabindex="7" >
	   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
        </myriap:select>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="right">
	  <b><bean:message key='c_do_fonde_t'/></b></TD>
      <TD ALIGN="left" colspan="3">
          <myriap:select name='rapportEmployeDossierRapportForm_CDX_0042' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 200px" tabindex="8" >
              <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
          </myriap:select>
      </TD>
    </TR>
    <TR>
       <TD ALIGN="right"><b><bean:message key='i_ro_cle_t' /></b></TD>
       <TD ALIGN="left" colspan="3">
          <bean:define id="site" name="rapportEmployeDossierRapportForm_CDX_0042" property="site" type="String"/>
          <myriap:select name='rapportEmployeDossierRapportForm_CDX_0042' property="role" size="1" style="HEIGHT: 20px; WIDTH: 200px" tabindex="9" >
	 			<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=site%>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
          </myriap:select>
       </TD>
	</TR>
      <TR>
        <TD nowrap ALIGN="right"><b><bean:message key='date_deb_per_t'/></b></TD>
        <TD nowrap>
          <myriap:text name='rapportEmployeDossierRapportForm_CDX_0042' property="dateDebutDu" size="10" maxlength="10" tabindex="10" onkeyup="doTraits(document.forms(0).dateDebutDu,'dateDebutAu');changerChamp(this,'dateDebutAu');" />
          <myriap:link href="javascript:openDate('document.forms(0).dateDebutDu',document.forms(0).dateDebutDu.value);" onmousedown="setXY(event.x, event.y);">
            <html:img page="/images/cal.gif"border="0" />
          </myriap:link>
        </TD> 
        <TD nowrap ALIGN="right"><b><bean:message key='au_t'/></b></TD>
        <TD nowrap>
          <myriap:text name='rapportEmployeDossierRapportForm_CDX_0042' property="dateDebutAu" size="10" maxlength="10" tabindex="11" onkeyup="doTraits(document.forms(0).dateDebutAu,'dateFinDu');changerChamp(this,'dateFinDu');"/>
          <myriap:link href="javascript:openDate('document.forms(0).dateDebutAu', document.forms(0).dateDebutAu.value);" onmousedown="setXY(event.x, event.y);" onmousedown="setXY(event.x, event.y);">
            <html:img page="/images/cal.gif"border="0" />
          </myriap:link>
        </TD>
      </TR>

