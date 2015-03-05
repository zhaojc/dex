<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>


<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/listeAJAXHelper.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
		soumettreActionMethod("rafraichir");
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
	   var pos = cle.indexOf("/");
	   if (pos > -1){
	   	document.forms(0).type.value = cle.substring(pos+1,cle.length);
	   	doRafraichir("categorie",document.forms(0).type.value);
	   }
	}

	function doRafraichir(liste,cle) {
  
	    if (cle == "") {
	        viderListe(liste);
	    } else {
	        var url = "<%= request.getContextPath() %>/RafraichirListe?LISTE=" + liste + "&CLE=" + cle;
   
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

</SCRIPT>

<bean:define id="genre" name='rapportDossierContratsAutoexclusion_CDX_0060' property="genre" type="String"/>
<bean:define id="nature" name='rapportDossierContratsAutoexclusion_CDX_0060' property='nature' type="String"/>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:220; height:20;" style="z-index: 1; position: absolute; left: 270px; top: 110px; visibility= 'hidden'" id="consulter">
	<select size='8' onchange="choisirCategorie(this.value);" name="typeCategorie"
	  style="HEIGHT: 110px; WIDTH: 220px; background-color:beige"  >
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE_CATEGORIE %>' 
		valeurDiscriminant='<%=nature%>'
		actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	</select>          
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
        <bean:define id="entite" name='rapportDossierContratsAutoexclusion_CDX_0060' property="entite" type="String"/>
        <myriap:select name='rapportDossierContratsAutoexclusion_CDX_0060' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 170px' tabindex="1" onchange='doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right" nowrap="nowrap"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rapportDossierContratsAutoexclusion_CDX_0060' property='site' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
	      <TD ALIGN="left">
	      
	       <myriap:select size='1' name='rapportDossierContratsAutoexclusion_CDX_0060' property='genre' style='HEIGHT: 20px; WIDTH: 170px' tabindex="3" onchange='doSoumettreRafraichir();'>
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
		<td align="right" width="90" ><b><bean:message key='du_t' /></b></td>
     	<td nowrap="nowrap" width="590" >
			<cardex:Date name='rapportDossierContratsAutoexclusion_CDX_0060' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
	</TR>
	
	<TR>
	  <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rapportDossierContratsAutoexclusion_CDX_0060' property='nature' style='HEIGHT: 20px; WIDTH: 170px' tabindex="5" onchange="doSoumettreRafraichir();" >
	         <cardex:optionTag
	         	classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
				valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
				valeurDiscriminant='<%=genre%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
        </myriap:select>
      </TD>
		<td align="right" width="90" ><b><bean:message key='au_t' /></b></td>
	    	<td nowrap="nowrap" colspan="3" width="590">
			<cardex:Date name='rapportDossierContratsAutoexclusion_CDX_0060' property='dateDebutAu' calendrier="true" />
		</td>
      
	</TR>
	
	
    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left">
	    
        <myriap:select size='1' name='rapportDossierContratsAutoexclusion_CDX_0060' property='type' style='HEIGHT: 20px; WIDTH: 170px' tabindex="6" onchange="doSoumettreRafraichir();" >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
			valeurDiscriminant='<%=nature%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
        </myriap:select>
      </TD>
    </TR>


    <TR>
      <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left" nowrap>
      	 <bean:define id="type" name='rapportDossierContratsAutoexclusion_CDX_0060' property='type' type="String"/>
         <myriap:select size='1' name='rapportDossierContratsAutoexclusion_CDX_0060' property='categorie' tabindex="7" style='HEIGHT: 20px; WIDTH: 170px' >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		 </myriap:select>
         <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe" TITLE="Liste des catégories" border="1" height="14" width="14" onclick="doConsulter();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'" />
		</TD>
	</TR>

	