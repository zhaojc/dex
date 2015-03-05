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

	function doConsulterIntervenant(){
	  if(document.forms(0).siteOrigine.value != ""){
	    if(consulterIntervenant.style.visibility == "visible"){
	       consulterIntervenant.style.visibility = "hidden";
	       document.forms(0).loupe2.src='<%=request.getContextPath()%>/images/magnify.gif';
	    }else{
	       consulterIntervenant.style.visibility = "visible";
	    }
	  }
	}
	
	function choisirIntervenant(cle) {
	//On place les valeurs choisies dans le champ Intervenant.
	   	document.forms(0).intervenant.value = cle;
	   	doConsulterIntervenant();
	}
	
	function doConsulter(){
	  if(document.forms(0).nature.value != ""){
	    if(consulter.style.visibility == "visible"){
	       consulter.style.visibility = "hidden";
	       document.forms(0).loupe.src='<%=request.getContextPath()%>/images/magnify.gif';
	    }else{
	       consulter.style.visibility = "visible";
	    }
	  }
	}

	function choisirCategorie(cle) {
	//On place les valeurs choisies dans les listes de Types et Catégories.
	   var pos = cle.indexOf("/");
	   if (pos > -1){
	   	document.forms(0).type.value = cle.substring(pos+1,cle.length);
	   	doRafraichir("categorie",document.forms(0).type.value);
	   }
	//alert(document.forms(0).modele.value + " - " + cle.substring(0,pos));
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
	
	function doSousCategorieSeul(){
	           document.forms(0).rechercherSousCategorie.value = "<%=GlobalConstants.BooleanString.TRUE%>";
	           document.forms(0).rechercherTous.checked = false;
	           document.forms(0).rechercherTous.value = "<%=GlobalConstants.BooleanString.FALSE%>";
	}
	
	function doSousCategorieTous(){
	           document.forms(0).rechercherSousCategorie.value = "<%=GlobalConstants.BooleanString.FALSE%>";
	           document.forms(0).rechercherSousCategorie.checked = false;
	           document.forms(0).rechercherTous.value = "<%=GlobalConstants.BooleanString.TRUE%>";
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

<bean:define id="siteOrigine" name="nombreRechercheDossiersRapportForm_CDX_0229" property="siteOrigine" type="String"/>
<bean:define id="nature" name="nombreRechercheDossiersRapportForm_CDX_0229" property="nature" type="String"/>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:400; height:20;" style="z-index: 1; position: absolute; left: 240px; top: 150px; visibility= 'hidden'" id="consulterIntervenant">
	<select size='8' onchange="choisirIntervenant(this.value);" style="HEIGHT: 110px; WIDTH: 430px; background-color:beige">
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.SECTEUR_INTERVENANT_PAR_SITE%>'
		valeurDiscriminant='<%=siteOrigine%>'/>
	</select>          
</DIV>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:220; height:20;" style="z-index: 1; position: absolute; left: 270px; top: 110px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='nombreRechercheDossiersRapportForm_CDX_0229' property="typeCategorie" onchange="choisirCategorie(this.value);"
	  style="HEIGHT: 110px; WIDTH: 220px; background-color:beige"  >
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE_CATEGORIE %>' 
		valeurDiscriminant='<%=nature%>'
		actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	</html:select>          
</DIV>

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
        <bean:define id="entite" name="nombreRechercheDossiersRapportForm_CDX_0229" property="entite" type="String"/>
        <myriap:select name='nombreRechercheDossiersRapportForm_CDX_0229' property='entite' size='1' tabindex="1" style='HEIGHT: 20px; WIDTH: 200px' onchange='document.forms(0).genre.selectedIndex=0;document.forms(0).nature.selectedIndex=0;document.forms(0).type.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='no_dossier_t'/></b></TD>
      <TD ALIGN="left" nowrap>
        <myriap:select name='nombreRechercheDossiersRapportForm_CDX_0229' property='siteNumeroCardex' tabindex="11" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_ABREVIATION %>'
	         valeurTableValeur='<%=GlobalConstants.TableValeur.SITE %>'
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
        <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property='dateNumeroCardex' maxlength='10'  size='10' tabindex="12" onkeyup="doTraits(this,'sequenceNumeroCardex');changerChamp(this,'sequenceNumeroCardex');" />
        <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property='sequenceNumeroCardex' maxlength='4' size='4' style="WIDTH: 32px" tabindex="13" />
        <myriap:link href="javascript:openDate('document.forms(0).elements(4)', document.forms(0).elements(4).value);" onmousedown="setXY(event.x + 47, event.y);" >
	  <html:img page="/images/cal.gif"border="0" />
        </myriap:link>
      </TD> 
      <TD ALIGN="right" VALIGN="top"><b><bean:message key='i_cc_cle_t'/></b></TD>
      <TD ALIGN="left" >
        <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='confidentialite' style='HEIGHT: 20px; WIDTH: 50px' tabindex="22" >
		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
 		</myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='siteOrigine' style='HEIGHT: 20px; WIDTH: 200px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right" VALIGN="top" nowrap><html:img border="0" height="5" page="/images/blank.gif" width="1" /><br>
        <b><bean:message key='v_do_ancienne_reference_t'/></b></TD>
      <TD ALIGN="left" VALIGN="top"><myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="numeroDossier" maxlength="20" size="20" style="HEIGHT: 20px; WIDTH: 160px" onchange='this.value = this.value.toUpperCase();' tabindex="14" /></TD>
      <TD ALIGN="right"><b><bean:message key='i_se_cle_t'/></b></TD>
      <TD ALIGN="left" >
        <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='severite' style='HEIGHT: 20px; WIDTH: 50px' tabindex="23" >
	   		<cardex:severiteOptions/>
        </myriap:select>
      </TD>
      <TD ALIGN="center" > <html:img border="0" height="1" page="/images/blank.gif"  width="1" /><br>&nbsp;
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='l_si_cle_inclus_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='siteApplicable' style='HEIGHT: 20px; WIDTH: 200px' tabindex="3" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_APPLICABLE_TABLE_VALEUR %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='nombreRechercheDossiersRapportForm_CDX_0229' property="reference1" tabindex="15" maxlength="20" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE1_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='origine_t'/></b></TD>
      <TD ALIGN="left" >
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='origine' style='HEIGHT: 20px; WIDTH: 170px' tabindex="24" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
	   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
      <bean:define id="genre" name='nombreRechercheDossiersRapportForm_CDX_0229' property="genre" type="String"/>
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='genre' style='HEIGHT: 20px; WIDTH: 200px' tabindex="4" onchange='doSoumettreRafraichir();' >
	   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
				valeurDiscriminant='<%=entite%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='nombreRechercheDossiersRapportForm_CDX_0229' property="reference2" tabindex="16" maxlength="20" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE2_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
      <TD ALIGN="left" >
      	<bean:define id="siteEndroit" name='nombreRechercheDossiersRapportForm_CDX_0229' property='siteOrigine' type="String"/>
        <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='endroit' style='HEIGHT: 20px; WIDTH: 170px' tabindex="25" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=siteEndroit%>'/>
	 	</myriap:select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='nature' style='HEIGHT: 20px; WIDTH: 200px' tabindex="5" onchange="doSoumettreRafraichir();" >
	         <cardex:optionTag
	         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
				valeurDiscriminant='<%=genre%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='v_do_reference3_t'/></b></TD>
      <TD ALIGN="left" >
            <cardex:AutoCompleter name='nombreRechercheDossiersRapportForm_CDX_0229' property="reference3" tabindex="17" maxlength="30" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.REFERENCE3_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
      <TD ALIGN="left" >
      	<bean:define id="siteLocalisation" name='nombreRechercheDossiersRapportForm_CDX_0229' property='siteOrigine' type="String"/>
        <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='localisation' style='HEIGHT: 20px; WIDTH: 170px' tabindex="26" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=siteLocalisation%>'/>
	    </myriap:select>
      </TD>
    </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
	    <bean:define id="nature" name='nombreRechercheDossiersRapportForm_CDX_0229' property='nature' type="String"/>
        <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='type' style='HEIGHT: 20px; WIDTH: 200px' tabindex="6" onchange="doSoumettreRafraichir();" >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
			valeurDiscriminant='<%=nature%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
        <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
        <TD>
            <cardex:AutoCompleter name='nombreRechercheDossiersRapportForm_CDX_0229' property="descriptif" tabindex="18" maxlength="80" 
            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
        </TD>
      <TD ALIGN="right" nowrap><b><bean:message key='enregistrement_numerique'/></b></TD>
      <TD ALIGN="left" nowrap>
			<html:radio tabindex="27" name='nombreRechercheDossiersRapportForm_CDX_0229' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
			<html:radio tabindex="28" name='nombreRechercheDossiersRapportForm_CDX_0229' property="enregistrementNumerique" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
      </TD>
   </TR>

    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" nowrap>
      	 <bean:define id="type" name='nombreRechercheDossiersRapportForm_CDX_0229' property='type' type="String"/>
         <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='categorie' tabindex="7" style='HEIGHT: 20px; WIDTH: 200px' >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		 </myriap:select>
         <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Liste des catégories" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
         
	    <TD align="right"><b><bean:message key='type.jeu'/> : </b></TD>
        <TD ALIGN="left">
        <bean:define id="entite" name="nombreRechercheDossiersRapportForm_CDX_0229" property="entite" type="String"/>
          <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property="typeJeu" style="HEIGHT: 20px; WIDTH: 160px" tabindex="20" onkeypress="typeAhead(this, event);" onchange="doSoumettreRafraichir();" onfocus="resetIncrementalSearch();" >
          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>' valeurDiscriminant='<%=entite%>'/>
          </myriap:select> 
        </TD>
	     
	     
         <TD ALIGN="right" nowrap><b><bean:message key='no_seq_t'/></b></TD>
      	 <TD ALIGN="left" >
             <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="referenceVideo" tabindex="29" maxlength="40" size="1" style="HEIGHT: 20px; WIDTH: 170px" />
         </TD>
    </TR>

    <TR>
      <TD ALIGN="right" nowrap><b><bean:message key='sous.categories'/><bean:message key='2.points'/></b></TD>
      <TD ALIGN="left">
       	<html:radio name='nombreRechercheDossiersRapportForm_CDX_0229' property="rechercherSousCategorie" value="<%=GlobalConstants.BooleanString.FALSE%>" onclick="doSousCategorieSeul();" />&nbsp;
       	<b><bean:message key='sous_categories_tous'/><bean:message key='2.points'/></b><html:radio name='nombreRechercheDossiersRapportForm_CDX_0229' property="rechercherTous" value="<%=GlobalConstants.BooleanString.FALSE%>" onclick="doSousCategorieTous();"/>
      </TD>
        <TD align="right"><b><bean:message key='tabpage_jeu'/> : </b></TD>
        <TD ALIGN="left">
          <bean:define id="typeJeu" name="nombreRechercheDossiersRapportForm_CDX_0229" property="typeJeu" type="String"/>
          <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property="jeu" style="HEIGHT: 20px; WIDTH: 160px" tabindex="20" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.JEUX%>' valeurDiscriminant='<%=entite+","+typeJeu%>'/>
          </myriap:select> 
        </TD>
      <TD ALIGN="right" ><b><bean:message key='enregistrement_conserve'/></b></TD>
      <TD ALIGN="left" nowrap>
		<html:radio name='nombreRechercheDossiersRapportForm_CDX_0229' property="enregistrementConserve" value="<%= GlobalConstants.SQL.TRUE %>" /><bean:message key='oui_t'/>
		<html:radio name='nombreRechercheDossiersRapportForm_CDX_0229' property="enregistrementConserve" value="<%= GlobalConstants.SQL.FALSE_FIXE %>"/><bean:message key='non_t'/>
      </TD>
    </TR>

	<TR>
      <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='statut' style='HEIGHT: 20px; WIDTH: 200px' tabindex="8" >
	   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.DOSSIER %>'/>
        </myriap:select>&nbsp;
      </TD>
      	<TD ALIGN="right" VALIGN="top" nowrap><html:img border="0" height="5" page="/images/blank.gif" width="1" /><br>
    	    <b><bean:message key='v_fiche_sujet_t'/></b></TD>
	    <TD ALIGN="left" VALIGN="top"><myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="numeroFicheSujet" size="20" maxlength="20" style="HEIGHT: 20px; WIDTH: 160px" tabindex="21" /></TD>
        <TD ALIGN="right" ><b><bean:message key='classe.societe'/><bean:message key='2.points'/></b></TD>
        <TD ALIGN="left"><myriap:select size='1' name='nombreRechercheDossiersRapportForm_CDX_0229' property='classe' style='HEIGHT: 20px; WIDTH: 170px' tabindex="32" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			<cardex:optionTag classe='<%= GlobalConstants.CleListe.CLASSE %>'/>
          </myriap:select>
        </TD>
	</TR>

	<TR>
      <TD ALIGN="right">
		  <b><bean:message key='c_do_fonde_t'/></b></TD>
	      <TD ALIGN="left" colspan="5">
	          <myriap:select name='nombreRechercheDossiersRapportForm_CDX_0229' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 200px" tabindex="9" >
	              <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
	          </myriap:select>
	      </TD>
    </TR>

	<TR>
      <td ALIGN="right"><b><bean:message key="v_sv_intervenant_t" />&nbsp;</b></td>
      <td ALIGN="left" colspan="5">
       <bean:define id="siteOrigine" name="nombreRechercheDossiersRapportForm_CDX_0229" property="siteOrigine" type="String"/>
       <myriap:select size="1" name="nombreRechercheDossiersRapportForm_CDX_0229" property="intervenant" style="HEIGHT: 20px; WIDTH: 450px" tabindex="10" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
				valeurDiscriminant='<%=siteOrigine%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
        </myriap:select>
        <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe2" TITLE="Liste des groupes et des intervenants" border="1" height="14" width="14" onclick="doConsulterIntervenant();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'">
      </td>
    </TR>
    <TR>
      <TD ALIGN="center" colspan="6" nowrap valign="top">
        <TABLE width="620" cellpadding="1" cellspacing="0" border="0" >
         <TR><TD>
	     <TABLE width="400" cellpadding="1" cellspacing="0" border="0" class="tableOutline">
          <TR>
            <TD colspan="4"><b><bean:message key='periode_val_t' /></b></TD>
          </TR>

          <TR>
            <TD nowrap ALIGN="right" width="120">&nbsp;&nbsp;<b><bean:message key='date_deb_per_t'/></b>&nbsp;</TD>
            <TD nowrap>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateDebutDu" size="10" maxlength="10" tabindex="33" onkeyup="doTraits(document.forms(0).dateDebutDu,'dateDebutAu');changerChamp(this,'dateDebutAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateDebutDu',document.forms(0).dateDebutDu.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD> 
            <TD nowrap>&nbsp;<b><bean:message key='au_t'/></b></TD>
            <TD nowrap>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateDebutAu" size="10" maxlength="10" tabindex="34" onkeyup="doTraits(document.forms(0).dateDebutAu,'dateFinDu');changerChamp(this,'dateFinDu');"/>
              <myriap:link href="javascript:openDate('document.forms(0).dateDebutAu', document.forms(0).dateDebutAu.value);" onmousedown="setXY(event.x, event.y);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
          <TR>
            <TD ALIGN="right" nowrap>&nbsp;&nbsp;<b><bean:message key='date_fin_per_t'/></b>&nbsp;</TD>
            <TD>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateFinDu" size="10" maxlength="10" tabindex="35" onkeyup="doTraits(document.forms(0).dateFinDu,'dateFinAu');changerChamp(this,'dateFinAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateFinDu', document.forms(0).dateFinDu.value);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
            <TD>&nbsp;<b><bean:message key='au_t'/></b></TD>
            <TD nowrap>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateFinAu" size="10" maxlength="10" tabindex="36" onkeyup="doTraits(document.forms(0).dateFinAu,'dateFinDu');changerChamp(this,'dateFinDu');"/>
              <myriap:link href="javascript:openDate('document.forms(0).dateFinAu', document.forms(0).dateFinAu.value);" onmousedown="setXY(event.x, event.y);" onmousedown="setXY(event.x, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
		    <TD ALIGN="left">&nbsp;</TD>
          </TR>
        </TABLE>
	</TD>
	<TD ALIGN="left" nowrap valign="top">
        <TABLE width="220" cellpadding="1" cellspacing="0" border="0" class="tableOutline">
          <TR>
            <TD colspan="2" nowrap><b><bean:message key='nouveaux_dossiers_t' /></b></TD>
          </TR>

          <TR>
            <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='du_t'/></b>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateCreationDu" size="10" maxlength="10" tabindex="37" onkeyup="doTraits(document.forms(0).dateCreationDu,'dateCreationAu');changerChamp(this,'dateCreationAu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateCreationDu', document.forms(0).dateCreationDu.value);"  onmousedown="setXY(event.x - 40, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
          <TR>
            <TD ALIGN="center" nowrap>&nbsp;<b><bean:message key='au_t'/></b>
              <myriap:text name='nombreRechercheDossiersRapportForm_CDX_0229' property="dateCreationAu" size="10" maxlength="10" tabindex="38" onkeyup="doTraits(document.forms(0).dateCreationAu,'dateCreationDu');changerChamp(this,'dateCreationDu');" />
              <myriap:link href="javascript:openDate('document.forms(0).dateCreationAu', document.forms(0).dateCreationAu.value);" onmousedown="setXY(event.x - 40, event.y);">
                <html:img page="/images/cal.gif"border="0" />
              </myriap:link>
            </TD>
          </TR>
        </TABLE>

      </TD>
    </TR>
	


