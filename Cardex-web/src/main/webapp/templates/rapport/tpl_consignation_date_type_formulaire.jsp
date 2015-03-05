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

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

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
	  if(document.forms(0).site.value != ""){
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

<bean:define id="site" name='<%=form%>' property="site" type="String"/>
<bean:define id="nature" name='<%=form%>' property="nature" type="String"/>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:400; height:20;" style="z-index: 1; position: absolute; left: 240px; top: 150px; visibility= 'hidden'" id="consulterIntervenant">
	<select size='8' onchange="choisirIntervenant(this.value);" style="HEIGHT: 110px; WIDTH: 430px; background-color:beige">
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.SECTEUR_INTERVENANT_PAR_SITE%>'
		valeurDiscriminant='<%=site%>'/>
	</select>          
</DIV>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:220; height:20;" style="z-index: 1; position: absolute; left: 270px; top: 110px; visibility= 'hidden'" id="consulter">
	<html:select size='8' name='<%=form%>' property="typeCategorie" onchange="choisirCategorie(this.value);"
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
        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
        <myriap:select name='<%=form%>' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 200px' onchange='document.forms(0).genre.selectedIndex=0;document.forms(0).nature.selectedIndex=0;document.forms(0).type.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;document.forms(0).categorie.selectedIndex=0;doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 200px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
    </TR>

	<TR>
      <td ALIGN="right"><b><bean:message key="i_tc_cle_t2" />&nbsp;</b></td>
      <td ALIGN="left" colspan="5">
       <myriap:select size="1" name='<%=form%>' property="typeConsignation" style="HEIGHT: 20px; WIDTH: 150px" tabindex="19" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
	     <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
			valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_CONSIGNATION%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
        </myriap:select>
      </td>
    </TR>
	<tr>
		<td align="right" ><b><bean:message key='du_t' /></b></td>
     	<td nowrap="nowrap" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
		<td align="right" ><b><bean:message key='au_t' /></b></td>
     	<td nowrap="nowrap" >
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>


