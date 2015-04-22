<%-- --------------------------------------------------------------------------
Use case    : Ajout/Modification d'une narration.
Description : Module d'affichage du formulaire d'une narration.
Date        : janvier 2006
Remarque    : Cette page remplace l'objet HTMLEditor qui servait auparavant
              aux narrations. En raison de la difficulté à personnaliser et
              surtout en raison d'incompatibilité et de lenteur du plugin
              Java qui était nécessaire à cet objet, la nouvelle page repose
              entièrement sur du code HTML.
--------------------------------------------------------------------------- --%>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
 
	<script type="text/javascript">

	function exCommand(exCommand){
	
		if (fenetreNarration.isContentEditable==true){ 
			document.execCommand(exCommand);
			NarrationKeyPress();
		}
		// Retiré, car l'utilisateur veut faire plusieurs commande d'affilées
		//document.getElementById("fenetreNarration").focus();
	}

	// Ensure that all document elements except the EDITOR are unselectable.
	function selectable(unselectable)
	{
		for (var i=0; i < document.all.length; i++) {
			document.all[i].unselectable = unselectable;
		};
		document.getElementById("fenetreNarration").unselectable = "off";
		document.getElementById("fenetreNarration").focus();
	}

	function selectionnerTout(){
		selectable("on");	
		document.execCommand('SelectAll');
		selectable("off");
	}

	function cacherAbsentBTNDictionnaire(){
		var dictionnaireBTN = document.getElementById("DictionnaireBTN");
		var grammaireBTN = document.getElementById("GrammaireBTN");
		var correcteurBTN = document.getElementById("CorrecteurBTN");
		
		try{
		  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
		  
		}catch(err){
			dictionnaireBTN.style.visibility="hidden";
			grammaireBTN.style.visibility="hidden";
			correcteurBTN.style.visibility="hidden";
		}
	}
	

function dictionnaire(){
	try{
	  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
	  if (oGestionnaireAntidote != null) {
	  	oGestionnaireAntidote.LanceOutil("IEFrame", "DictionnaireParDefaut");
	  }
	}catch(err){
		message("Le logiciel Antidote n'est pas install&eacute; sur votre poste.");
	}
}

function grammaire(){
	try{
	  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
	  if (oGestionnaireAntidote != null) {
		  oGestionnaireAntidote.LanceOutil("IEFrame", "GuideParDefaut");
	  }
	}catch(err){
		message("Le logiciel Antidote n'est pas install&eacute; sur votre poste.");
	}
}

function correcteur(){
	try{
	  var oGestionnaireAntidote = new ActiveXObject("AntidoteBarre.Application");
	  if (oGestionnaireAntidote != null) {
	      antidote = true; //Le correcteur est utilisé. On n'affiche pas de rappel à la sauvegarde.
		  oGestionnaireAntidote.LanceOutil("IEFrame", "Correcteur");
	  }
	}catch(err){
		message("Le logiciel Antidote n'est pas install&eacute; sur votre poste.");
	}
}

var narrationKeyPress = false;

function NarrationKeyPress(){
	narrationKeyPress = true;
}

function sauvegarderNarrationTemporaire(){
 
 	if (narrationKeyPress){
		narrationKeyPress = false;
		var cle = document.getElementsByName("cle")[0].value;
		var site = document.getElementsByName("site")[0].value;
		var lien = document.getElementsByName("lien")[0].value;
		var lienSite = document.getElementsByName("lienSite")[0].value;
		var premiereNarrationTemporaire = document.getElementsByName("premiereNarrationTemporaire")[0];
		
		document.getElementById("sauvegardeNarrationTemporaire").contentWindow.setNarrationTemporaire(cle, site, lien, lienSite, premiereNarrationTemporaire.value, fenetreNarration.innerHTML);
		
		if (premiereNarrationTemporaire.value == "true"){
			premiereNarrationTemporaire.value = "false";
		}
		
		document.getElementById("sauvegardeNarrationTemporaire").contentWindow.soumettre();
	}
	setTimeout("sauvegarderNarrationTemporaire();", 600000); // 1000 = 1 sec / 60000 = 1 minutes / 900000 = 15 minutes
}

var gabaritNarration = {};
gabaritNarration["1330"] = "kiosques.htm";
gabaritNarration["1331"] = "observation.htm";
gabaritNarration["1332"] = "profil_rfc.htm";
gabaritNarration["1333"] = "rapport_plainte.htm";
gabaritNarration["1334"] = "rapport_reclamation.htm";
gabaritNarration["1335"] = "reclamation_rfc.htm";
gabaritNarration["1336"] = "rapport_activite_quotidien.htm";
gabaritNarration["1337"] = "securite_LQC.htm";
gabaritNarration["1338"] = "securite_maison_jeux.htm";
gabaritNarration["1339"] = "tableau_2x3.htm";
gabaritNarration["1340"] = "tableau_2x6.htm";
gabaritNarration["1341"] = "tableau_3x3.htm";
gabaritNarration["1342"] = "tableau_3x6.htm";
gabaritNarration["1343"] = "tableau_5x5.htm";
gabaritNarration["1344"] = "vigilance_resume_analyse.htm";
gabaritNarration["1345"] = "vigilance_actions_proposees.htm";
gabaritNarration["1346"] = "vigilance_decision_comite.htm";
gabaritNarration["1347"] = "vigilance_actions_prises.htm";
gabaritNarration["1348"] = "vigilance_conclusion.htm";
gabaritNarration["1349"] = "vigilance_suivi.htm";
gabaritNarration["1350"] = "vigilance_demande_reintegration_client.htm";

function chargerTableValeurGabarit(gabarit) {
//-- Chargement d'un gabarit de saisie de données
//-- dans la fenêtre de narration
	if(gabarit != ""){
	  var xmlhttp = new ActiveXObject("MSXML2.XMLHTTP"); 
	  serveur = "<%= request.getServerName() %>";
	  port = "<%= request.getServerPort() %>";
	  contexte = "<%= request.getContextPath() %>";
	  chemin = getChemin(serveur,port,contexte);
	  xmlhttp.open("GET", chemin + gabaritNarration[gabarit], 0); 
	  xmlhttp.send(""); 
	  fenetreNarration.insertAdjacentHTML("beforeEnd", xmlhttp.responseText); 
	  xmlhttp = null; 
	  fenetreNarration.focus();
	}
}

sauvegarderNarrationTemporaire();

	</script>
	
    <DIV id=boutons>
      <TABLE border="1" cellpadding="2" cellspacing="0" width="100%">
      	<TR>
      		<td></td>
       		<td nowrap align ='left'><b><bean:message key='narration.police'/></b></td>
      		<td nowrap align ='left'><b><bean:message key='narration.taille'/></b></td>
      		<td></td>
       		<td nowrap align ='left'><b><bean:message key='narration.gabarit.standard'/></b></td>
      		<td nowrap align ='left'><b><bean:message key='narration.gabarit.uniformise'/></b></td>
      	</tr>
       <TR><TD nowrap align="center">
		<button name="Selection" TITLE="Tout sélectionner" onmousedown="selectionnerTout();">
			<IMG src="<%=request.getContextPath()%>/images/selection_all.gif" />
		</button>
		&nbsp;       
	  	<button name="undo" TITLE="Annuler" onmousedown="exCommand('Undo');">
			<IMG src="<%=request.getContextPath()%>/images/Undo.gif" >
		</button>
	  	<button name="redo" TITLE="Refaire" onmousedown="exCommand('Redo');">
			<IMG src="<%=request.getContextPath()%>/images/Redo.gif" >
		</button>
		&nbsp;       
		
		<BUTTON name="gras" TITLE="Gras"  onmousedown='exCommand("Bold");'>
			<IMG src="<%=request.getContextPath()%>/images/Bold.gif">
		</BUTTON>
		<BUTTON name="italique" TITLE="Italique" onmousedown='exCommand("Italic");'>
			<IMG src="<%=request.getContextPath()%>/images/Italic.gif">
		</BUTTON>
		<BUTTON name="souligne" TITLE="Soulignement" onmousedown='exCommand("Underline");'>
			<IMG src="<%=request.getContextPath()%>/images/Underline.gif">
		</BUTTON>
		&nbsp;       
		<BUTTON name="couper" TITLE="Couper"  onmousedown='exCommand("Cut");'>
			<IMG src="<%=request.getContextPath()%>/images/Cut.gif">
		</BUTTON>
		<BUTTON name="copier" TITLE="Copier" onmousedown='exCommand("Copy");'>
			<IMG src="<%=request.getContextPath()%>/images/Copy.gif">
		</BUTTON>
		<BUTTON name="coller" TITLE="Coller" onmousedown='exCommand("Paste");'>
			<IMG src="<%=request.getContextPath()%>/images/Paste.gif">
		</BUTTON>		
		&nbsp;
		<BUTTON name="indent" TITLE="Indentation" onmousedown='exCommand("Indent");'>
			<IMG src="<%=request.getContextPath()%>/images/Indent.gif">
		</BUTTON>
		<BUTTON name="retrait" TITLE="Retrait" onmousedown='exCommand("Outdent");'>
			<IMG src="<%=request.getContextPath()%>/images/Outdent.gif">
		</BUTTON>
		<BUTTON name="liste" TITLE="Numérotation" onmousedown='exCommand("InsertOrderedList");'>
			<IMG src="<%=request.getContextPath()%>/images/Ordered.gif">
		</BUTTON>
		<BUTTON name="puces" TITLE="Puces" onmousedown='exCommand("InsertUnorderedList");'>
			<IMG src="<%=request.getContextPath()%>/images/Unordered.gif">
		</BUTTON>		
		&nbsp;&nbsp;
   		<button id="DictionnaireBTN" TITLE="Dictionnaire" style="width=24; height=24;" onclick="dictionnaire();">
				<IMG src="<%=request.getContextPath()%>/images/Dictionnaire.gif" >
			</button>
    	  	<button id="GrammaireBTN" TITLE="Grammaire" style="width=24; height=24;" onclick="grammaire();">
				<IMG src="<%=request.getContextPath()%>/images/Grammaire.gif" >
			</button>
   		  	<button id="CorrecteurBTN" TITLE="Correcteur" style="width=24; height=24;" onclick="correcteur();">
				<IMG src="<%=request.getContextPath()%>/images/Correcteur.gif" >
			</button>&nbsp;

	</TD>
	<TD  align="right">
	<SELECT style="WIDTH:70px" onchange='document.execCommand("FontName",false,this.value);fenetreNarration.focus();'>
	   <option value="" selected="selected"></option>
	   <option value="Arial">Arial</option>
	   <option value="Courier New">Courier</option>
	   <option value="Times">Times</option>
	</SELECT>
	</TD>
	<TD>
	<SELECT style="WIDTH:40px" onchange='document.execCommand("FontSize",false,this.selectedIndex);fenetreNarration.focus();'>
	   <option value="" selected="selected"></option>
	   <option value="1">8 pts</option>
	   <option value="2">10 pts</option>
	   <option value="3">12 pts</option>
	   <option value="4">14 pts</option>
	   <option value="5">18 pts</option>
	   <option value="6">24 pts</option>
	   <option value="7">36 pts</option>
	</SELECT>
	</TD>
	<TD nowrap>
		<BUTTON name="gauche" TITLE="Alignement à gauche" onmousedown='exCommand("JustifyLeft");'>
			<IMG src="<%=request.getContextPath()%>/images/Left.gif">
		</BUTTON>
		<BUTTON name="centre" TITLE="Alignement au centre" onmousedown='exCommand("JustifyCenter");'>
			<IMG src="<%=request.getContextPath()%>/images/Center.gif">
		</BUTTON>
		<BUTTON name="droit" TITLE="Alignement à droite" onmousedown='exCommand("JustifyRight");'>
			<IMG src="<%=request.getContextPath()%>/images/Right.gif">
		</BUTTON>
		<BUTTON name="justifie" TITLE="Justification" onmousedown='exCommand("JustifyFull");'>
			<IMG src="<%=request.getContextPath()%>/images/Justify.gif">
		</BUTTON>
		&nbsp;
		<BUTTON name="ligne" TITLE="Ligne horizontale" onmousedown='exCommand("InsertHorizontalRule");'>
			<IMG src="<%=request.getContextPath()%>/images/HR.gif">
		</BUTTON>
 
	</TD> 
	<TD align="center">
		<bean:define id="vigilance_RP0002" name='narration' property="gabaritFiltrerOptionPredicates"/>
	    <myriap:select name='narration' property="gabaritUtilise" style="HEIGHT:20px;WIDTH:200px" onchange='chargerTableValeurGabarit(this.value);fenetreNarration.focus();'>
	       <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR%>' 
		       valeurTableValeur='<%=GlobalConstants.TableValeur.GABARIT_NARRATION%>'  
		       actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS%>'
		       filtrerOptionPredicatesList='vigilance_RP0002'/>
	    </myriap:select>&nbsp;

	</td>
     <TD nowrap>
    <cardex:securityDefineTag nameDefine="sectionGabarit" urlSecurite="/dossier/rapport.do">

	<SELECT style="WIDTH:160px" onchange='lireGabarit(this.value);doSequence(this.value);this.selectedIndex=0;fenetreNarration.focus();'>
	   <option value="" selected="selected"></option>
	   <option value="pagetitre.htm">Page titre</option>
	   <option value="identification_CHA.htm">Identification Charlevoix</option>
	   <option value="identification_HUL.htm">Identification Lac-Leamy</option>
	   <option value="identification_LQC.htm">Identification Loto-Québec</option>
	   <option value="identification_MTL.htm">Identification Montréal</option>
	   <option value="identification_MTB.htm">Identification Mont-Tremblant</option>
	   <option value="identification_SEJ.htm">Identification Établissements de jeux</option>
	   <option value="identification_SSJQ.htm">Identification Salons de jeux</option>
	   <option value="tablematieres.htm">Table des matières</option>
	   <option value="introduction.htm">Introduction</option>
	   <option value="enquete.htm">Enquête</option>
	   <option value="constat.htm">Constat</option>
	   <option value="conclusion.htm">Conclusion</option>
	   <option value="recommandation.htm">Recommandations</option>
	</SELECT>
   </cardex:securityDefineTag>
       </TD>
       </TR>
      </TABLE>
    </DIV>


<DIV id=fenetreNarration contentEditable=true ALIGN=left STYLE="height:520;
     width:100%;background-color:white; font:normal normal normal 12pt Times; padding:2;
     border:0.05cm groove darkgray; scrollbar-base-color:lightgray;
     overflow:auto; lineHeight:4mm" onkeypress="NarrationKeyPress();">
</DIV>

<html:textarea name="narration" property="narrationTemporaire" style="position:absolute;visibility: hidden;"></html:textarea>

<iframe name="sauvegardeNarrationTemporaire" id="sauvegardeNarrationTemporaire" style="position:absolute;visibility: hidden;" frameBorder="0" scrolling="no" src="<%=request.getContextPath()%>/templates/narrations/sauvegardeNarrationTemporaire.jsp"></iframe>
<html:hidden name="narration" property="premiereNarrationTemporaire"/>

