<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
</HEAD>
<%-- --------------------------------------------------------------------------
Use case    : Capture de photo.
Description : Écran utilisé pour associer une pièce jointe (photo ou document)
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/23 21:08:58 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/23 21:08:58 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Récupération du site de l'utilisateur pour placer l'information dans les outils d'annotation -->
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<html:hidden name='photo' property='typeMultimedia' />
<html:hidden name='photo' property='extension' />
<html:hidden name='photo' property='lien' />
<html:hidden name='photo' property='lienSite' />
<html:hidden name='photo' property='cle' />
<html:hidden name='photo' property='site' />
<html:hidden name='photo' property='lienMultimedia' />
<html:hidden name='photo' property='lienSiteMultimedia' />
<html:hidden name='photo' property='lienElement' />
<html:hidden name='photo' property='lienSiteElement' />
<input type="hidden" name='fileToDelete' value="" />

<SCRIPT LANGUAGE="VBScript" defer>

Sub PreviewImage()
    On Error resume next
    Dim element1, element2
    ' Désactivation du bouton d'upload, tant qu'on est pas sûr du téléchargement
    document.links("Upload").href = "#"
    document.links("Upload").style.color = "#808080"

    ' Prévisualisation du fichier:
    document.forms(0).Preview.FileName = document.forms(0).sourceFile.value
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        Exit Sub
    End If
    
    ' Conversion et sauvegarde en format JPG:
    document.forms(0).Preview.SaveFileType = 8
    document.forms(0).Preview.SaveFileName = "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"
    document.forms(0).Preview.SaveFile
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        Exit Sub
    End If
    'On limite la taille de l'image à 4 Mo
	If document.forms(0).Preview.IFilesize > 4000000 Then
		MsgBox "<bean:message key='erreur_fichier' />",16,"Cardex"
		Exit Sub
    End If
    document.forms(0).annotation.disabled = false
    document.forms(0).appliquer.disabled = false
    document.links("Upload").style.color = "#000000"

	document.links("Upload").href = "javascript:soumettrePhoto();"
End sub

  Sub Connection()
    On Error Resume Next
    document.forms(0).Twain.OpenDSM
    If document.forms(0).Twain.DataSourceCount > 1 Then
       document.forms(0).Twain.SelectSource
    End If
    On Error Resume Next
    document.forms(0).Twain.StartSession
    document.forms(0).Twain.Acquire
    document.forms(0).Twain.SaveFile "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"
    document.forms(0).Preview.hDIB = 0
    document.forms(0).Preview.hDIB = document.forms(0).Twain.hDIB
    document.forms(0).Twain.CloseSession

    'On limite la taille de l'image à 4 Mo
    document.forms(0).Preview.FileName = "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"

    If document.forms(0).Preview.IFilesize > 4000000 Then
		MsgBox "<bean:message key='erreur_fichier' />",16,"Cardex"
		Exit Sub
    End If

    document.forms(0).annotation.disabled = false
    document.forms(0).appliquer.disabled = false
    document.links("Upload").style.color = "#000000"
    ' document.links("Upload").href = "javascript:callVbPhotoUpload();"
	document.links("Upload").href = "javascript:soumettrePhoto();"
  End sub

Sub doAnnoter()
	Dim ToolFont, texte
	'Chargement de l'image pour annotation
	document.forms(0).imageAnnotation.Resize 520, 440
	document.forms(0).imageAnnotation.hDib = document.forms(0).Preview.CopyDib
	document.forms(0).Notate.ToolbarSetToolTip 4099, "Dessiner une ellipse"
	document.forms(0).Notate.ToolbarSetToolTip 4103, "Dessiner au crayon"
	document.forms(0).Notate.ToolbarSetToolTip 4102, "Dessiner une ligne"
	document.forms(0).Notate.ToolbarSetToolTip 4096, "Pointeur pour menu (bouton droit de la souris)"
	document.forms(0).Notate.ToolbarSetToolTip 4100, "Dessiner un polygone"
	document.forms(0).Notate.ToolbarSetToolTip 4098, "Dessiner un rectangle"
	document.forms(0).Notate.ToolbarSetToolTip 4104, "Site"
	document.forms(0).Notate.ToolbarSetToolTip 4097, "Écrire une annotation"
	document.forms(0).Notate.ToolbarSetToolTip 4101, "Dessiner un polygone irrégulier"
	document.forms(0).Notate.ToolbarSetToolTip 4106, "Outil bouton"
	document.forms(0).Notate.ToolbarSetToolTip 4105, "Logo"
	document.forms(0).Notate.ToolbarSetToolTip 4107, "Règle"        
   	document.forms(0).Notate.SetClientWindow document.forms(0).imageAnnotation.Hwnd 'Connect NotateXpress
	LayerNum = document.forms(0).Notate.CreateLayer			'Start a new workspace
	document.forms(0).Notate.ToolbarSetVisible LayerNum, True	'Turn on the pallete window
	'Chargement du site dans l'outil Estampe et des fontes pour les textes
        Select Case <%= sujetSite %>
	  Case "5"
	     texte = "Casino de Charlevoix "
	  Case "6"
	     texte = "Casino du Lac-Leamy "
	  Case "7"
	     texte = "Casino de Montréal "
	  Case "9"
	     texte = "Société des loteries vidéo "
	  Case Else
	     texte = "Loto-Québec "
	End Select
	'Ajout de la date courante à l'estampe.
	document.forms(0).Notate.SetText LayerNum, 4104,  texte & Now()
	'Changement à blanc de la couleur de l'estampe
	document.forms(0).Notate.SetTextColor LayerNum, 4104, RGB(255,255,255)
	'Copie du logo
	document.forms(0).Notate.SetToolDefaultAttribute 4105, 17, document.forms(0).Estampe.CopyDIB
	set ToolFont = CreateObject("StdFont")
	ToolFont.name = "Arial"
        ToolFont.Size = 24
        ToolFont.Italic = False
        document.forms(0).Notate.SetFont LayerNum, 4097, ToolFont
	ToolFont.name = "Times New Roman"
        ToolFont.Size = 14
        document.forms(0).Notate.SetFont LayerNum, 4104, ToolFont
        'On change la propriété de certains outils à "transparent" et à bordure jaune
        document.forms(0).Notate.SetToolDefaultAttribute 4099, 16, 1 'Ellipse
        document.forms(0).Notate.SetToolDefaultAttribute 4098, 16, 1 'Rectangle
        document.forms(0).Notate.SetToolDefaultAttribute 4100, 16, 1 'Polygone
        document.forms(0).Notate.SetPenColor LayerNum, 4099, RGB(255,0,0)
        document.forms(0).Notate.SetPenColor LayerNum, 4098, RGB(255,0,0)
        document.forms(0).Notate.SetPenColor LayerNum, 4100, RGB(255,0,0)
        document.forms(0).Notate.SetPenWidth LayerNum, 4099, 5
        document.forms(0).Notate.SetPenWidth LayerNum, 4098, 5
        document.forms(0).Notate.SetPenWidth LayerNum, 4100, 5
        document.forms(0).Notate.EnableContextMenu = True
End sub

Sub doAppliquer()
      On Error resume next
    document.forms(0).Notate.Brand 24
    document.forms(0).Preview.hDib = document.forms(0).imageAnnotation.CopyDib
    document.forms(0).imageAnnotation.Resize 1, 1
    document.forms(0).Preview.SaveFileType = 8
    document.forms(0).Preview.SaveFileName = "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"
    document.forms(0).Preview.SaveFile
    document.forms(0).Notate.ToolbarSetVisible LayerNum, False	'Turn off the pallete window
    document.forms(0).Notate.DeleteLayer LayerNum
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        Exit Sub
    End If
    'On limite la taille de l'image à 4 Mo
    If document.forms(0).Preview.IFilesize > 4000000 Then
	MsgBox "<bean:message key='erreur_fichier' />",16,"Cardex"
	Exit Sub
    End If
	
End sub	
  
</SCRIPT>

<input type="hidden" name="saveAs" value="cardex01.jpg">
    <DIV ID="annotation" STYLE="overflow:auto; width:520; height:440; z-index: 1; position: absolute; left: 15px; top: 75px; visibility='visible'" >
	<OBJECT ID="imageAnnotation" WIDTH=1 HEIGHT=1
	 CLASSID="CLSID:6D3CF4F3-C2F3-46E7-A126-3E53102A6B91">
	    <PARAM NAME="_ExtentX" VALUE="5080">
	    <PARAM NAME="_ExtentY" VALUE="5080">
	    <PARAM NAME="ErrStr" VALUE="69B50355D2547332A7A28F525098BDAF">
	    <PARAM NAME="ErrCode" VALUE="1823482295">
	    <PARAM NAME="ErrInfo" VALUE="1022167355">
	    <PARAM NAME="Persistence" VALUE="1">
	    <PARAM NAME="_cx" VALUE="1864468">
	    <PARAM NAME="_cy" VALUE="18">
	    <PARAM NAME="AutoSize" VALUE="1">
	    <PARAM NAME="BorderType" Value="2">
	</OBJECT>
	<Script Language="VBScript">
	'Déverrouillage de l'objet Pegasus d'affichage de l'image pour annotation.
	'Selon le serveur le paramètre de déverrouillage est différent
	   Dim serveur
	   serveur = "<%= request.getServerName() %>"
	   'Par défaut, on met le serveur de production
	   document.forms(0).imageAnnotation.IntegratorWeb "https://cardex.loto-quebec.com?9F8539224D4E0DA01E0AC621BB52DD40"
	   If serveur = "localhost" then 'Environnement de développement
		document.forms(0).imageAnnotation.IntegratorWeb "localhost?260D1DDE4263A52305378F620D135BAE"
	   End If
	   If serveur = "denon.le500.loto-quebec.com" then 'Test et formation
		document.forms(0).imageAnnotation.IntegratorWeb "denon.le500.loto-quebec.com?EDBF036C11BE14B1C1C3BC6CD518F754"
	   End If
	   If serveur = "cardex.loto-quebec.ti" then 'Test et formation
		document.forms(0).imageAnnotation.IntegratorWeb "https://cardex.loto-quebec.ti?E46094DBCAE9D0CA08E38F3A11CD023F"
	   End If
	   If serveur = "cardex.loto-quebec.ta" then 'Test d'acceptation
		document.forms(0).imageAnnotation.IntegratorWeb "https://cardex.loto-quebec.ta?CED86FBCF96FE2E1BB0F197E651468ED"
	   End If

	   If serveur = "services4.loto-quebec.com" then 'Production Externe
		document.forms(0).imageAnnotation.IntegratorWeb "https://services4.loto-quebec.com?B18F35C36F7C74DC53B344406A89C6C5"
	   End If
	   If serveur = "services4.loto-quebec.ta" then 'Test d'acceptation Externe
		document.forms(0).imageAnnotation.IntegratorWeb "https://services4.loto-quebec.ta?36F665B0184DD098C742A0FC04C5B3F6"
	   End If

	</Script>
    </DIV>

<table width="670" cellpadding="5" cellspacing="0" border="0" bgcolor="#EEEEEE" class="tableOutline">
    <tr>
    <td width="670" align="center">

        <IMG src="<%= request.getContextPath() %>/images/blank.gif" width="10" height="0" border="0" />

	<%-- Capture avec TwainPRO pour connecter aux numériseurs --%>
	<OBJECT ID="Twain" WIDTH="320" HEIGHT="240"
	CLASSID="CLSID:9EB329EF-4119-41F7-AE5F-804E287CEBD8">
	    <PARAM NAME="_ExtentX" VALUE="847">
	    <PARAM NAME="_ExtentY" VALUE="847">
	    <PARAM NAME="ErrStr" VALUE="7F5D917EBF713CB662A8C91351F9AE3C">
	    <PARAM NAME="ErrCode" VALUE="708000241">
	    <PARAM NAME="ErrInfo" VALUE="869568359">
	    <PARAM NAME="_cx" VALUE="5080">
	    <PARAM NAME="_cy" VALUE="5080">
	</OBJECT>
	<Script Language="VBScript">
	'Déverrouillage de l'objet Twain pour le lien au numériseur.
	'Selon le serveur le paramètre de déverrouillage est différent
	   Dim serveur
	   serveur = "<%= request.getServerName() %>"
	   'Par défaut, on met le serveur de production
	   document.forms(0).Twain.IntegratorWeb "https://cardex.loto-quebec.com?9F8539224D4E0DA01E0AC621BB52DD40"
	   If serveur = "localhost" then 'Environnement de développement
		document.forms(0).Twain.IntegratorWeb "localhost?260D1DDE4263A52305378F620D135BAE"
	   End If
	   If serveur = "denon.le500.loto-quebec.com" then 'Test et formation
		document.forms(0).Twain.IntegratorWeb "denon.le500.loto-quebec.com?EDBF036C11BE14B1C1C3BC6CD518F754"
	   End If
	   If serveur = "cardex.loto-quebec.ti" then 'Test et formation
		document.forms(0).Twain.IntegratorWeb "https://cardex.loto-quebec.ti?E46094DBCAE9D0CA08E38F3A11CD023F"
	   End If
	   If serveur = "cardex.loto-quebec.ta" then 'Test d'acceptation
		document.forms(0).Twain.IntegratorWeb "https://cardex.loto-quebec.ta?CED86FBCF96FE2E1BB0F197E651468ED"
	   End If

	   If serveur = "services4.loto-quebec.com" then 'Production Externe
		document.forms(0).Twain.IntegratorWeb "https://services4.loto-quebec.com?B18F35C36F7C74DC53B344406A89C6C5"
	   End If
	   If serveur = "services4.loto-quebec.ta" then 'Test d'acceptation Externe
		document.forms(0).Twain.IntegratorWeb "https://services4.loto-quebec.ta?36F665B0184DD098C742A0FC04C5B3F6"
	   End If			   
	</Script>

	<OBJECT ID="Preview" WIDTH=320 HEIGHT=240
	 CLASSID="CLSID:6D3CF4F3-C2F3-46E7-A126-3E53102A6B91">
	    <PARAM NAME="_ExtentX" VALUE="5080">
	    <PARAM NAME="_ExtentY" VALUE="5080">
	    <PARAM NAME="ErrStr" VALUE="69B50355D2547332A7A28F525098BDAF">
	    <PARAM NAME="ErrCode" VALUE="1823482295">
	    <PARAM NAME="ErrInfo" VALUE="1022167355">
	    <PARAM NAME="Persistence" VALUE="1">
	    <PARAM NAME="_cx" VALUE="1864468">
	    <PARAM NAME="_cy" VALUE="18">
	    <PARAM NAME="AutoSize" VALUE="4">
	</OBJECT>
	<Script Language="VBScript">
	'Déverrouillage de l'objet Pegasus d'affichage de l'image.
	'Selon le serveur le paramètre de déverrouillage est différent
	   Dim serveur
	   serveur = "<%= request.getServerName() %>"
	   'Par défaut, on met le serveur de production
	   document.forms(0).Preview.IntegratorWeb "https://cardex.loto-quebec.com?9F8539224D4E0DA01E0AC621BB52DD40"
	   If serveur = "localhost" then 'Environnement de développement
		document.forms(0).Preview.IntegratorWeb "localhost?260D1DDE4263A52305378F620D135BAE"
	   End If
	   If serveur = "denon.le500.loto-quebec.com" then 'Test et formation
		document.forms(0).Preview.IntegratorWeb "denon.le500.loto-quebec.com?EDBF036C11BE14B1C1C3BC6CD518F754"
	   End If
	   If serveur = "cardex.loto-quebec.ti" then 'Test et formation
		document.forms(0).Preview.IntegratorWeb "https://cardex.loto-quebec.ti?E46094DBCAE9D0CA08E38F3A11CD023F"
	   End If
	   If serveur = "cardex.loto-quebec.ta" then 'Test d'acceptation
		document.forms(0).Preview.IntegratorWeb "https://cardex.loto-quebec.ta?CED86FBCF96FE2E1BB0F197E651468ED"
	   End If

	   If serveur = "services4.loto-quebec.com" then 'Production Externe
		document.forms(0).Preview.IntegratorWeb "https://services4.loto-quebec.com?B18F35C36F7C74DC53B344406A89C6C5"
	   End If
	   If serveur = "services4.loto-quebec.ta" then 'Test d'acceptation Externe
		document.forms(0).Preview.IntegratorWeb "https://services4.loto-quebec.ta?36F665B0184DD098C742A0FC04C5B3F6"
	   End If			   
	</Script>

    <DIV STYLE="overflow:auto; width:320; height:240; z-index: 1; position: absolute; left: 10px; top: 10px; visibility='hidden'" >
	<OBJECT ID="Estampe" WIDTH=320 HEIGHT=240
	 CLASSID="CLSID:6D3CF4F3-C2F3-46E7-A126-3E53102A6B91">
	    <PARAM NAME="_ExtentX" VALUE="5080">
	    <PARAM NAME="_ExtentY" VALUE="5080">
	    <PARAM NAME="ErrStr" VALUE="69B50355D2547332A7A28F525098BDAF">
	    <PARAM NAME="ErrCode" VALUE="1823482295">
	    <PARAM NAME="ErrInfo" VALUE="1022167355">
	    <PARAM NAME="Persistence" VALUE="1">
	    <PARAM NAME="_cx" VALUE="1864468">
	    <PARAM NAME="_cy" VALUE="18">
	    <PARAM NAME="AutoSize" VALUE="5">
	</OBJECT>
	<Script Language="VBScript">
	'Déverrouillage de l'objet Pegasus d'affichage du logo.
	'Selon le serveur le paramètre de déverrouillage est différent
        'Et chargement du logo pour l'outil image
	   Dim serveur, chemin
	   serveur = "<%= request.getServerName() %>"
	   If serveur = "localhost" then 'Environnement de développement
		document.forms(0).Estampe.IntegratorWeb "localhost?260D1DDE4263A52305378F620D135BAE"
		chemin = "http://" & serveur & ":9080"
	   End If
	   If serveur = "denon.le500.loto-quebec.com" then 'Test et formation
		document.forms(0).Estampe.IntegratorWeb "denon.le500.loto-quebec.com?EDBF036C11BE14B1C1C3BC6CD518F754"
		chemin = "http://" & serveur
	   End If
	   If serveur = "cardex.loto-quebec.com" then 'Production
	   	document.forms(0).Estampe.IntegratorWeb "https://cardex.loto-quebec.com?9F8539224D4E0DA01E0AC621BB52DD40"
		chemin = "https://" & serveur
	   End If
	   If serveur = "cardex.loto-quebec.ti" then 'Test et formation
		document.forms(0).Estampe.IntegratorWeb "https://cardex.loto-quebec.ti?E46094DBCAE9D0CA08E38F3A11CD023F"
		chemin = "https://" & serveur
	   End If
	   If serveur = "cardex.loto-quebec.ta" then 'Test d'acceptation
		document.forms(0).Estampe.IntegratorWeb "https://cardex.loto-quebec.ta?CED86FBCF96FE2E1BB0F197E651468ED"
		chemin = "https://" & serveur
	   End If
	   If serveur = "services4.loto-quebec.com" then 'Production Externe
		document.forms(0).Estampe.IntegratorWeb "https://services4.loto-quebec.com?B18F35C36F7C74DC53B344406A89C6C5"
		chemin = "https://" & serveur
	   End If
	   If serveur = "services4.loto-quebec.ta" then 'Test d'acceptation Externe
		document.forms(0).Estampe.IntegratorWeb "https://services4.loto-quebec.ta?36F665B0184DD098C742A0FC04C5B3F6"
		chemin = "https://" & serveur
	   End If			   
	     document.forms(0).Estampe.FileName = chemin & "<%= request.getContextPath() %>/images/lq-logo.gif"

	</Script>
    </DIV>

<OBJECT id="Notate" classid="clsid:BD1A7D5D-F3FB-4238-A072-6948D1150CD2" ></OBJECT>

    </td>
    </tr>
    <tr>
    <td width="670" align="center">
       <bean:message key='description_t' />
       <myriap:text name='photo' property='description' size='50' maxlength='50'  style='font-family: Verdana, Arial; font-size: 9pt;' />
       <html:hidden name='photo' property='filePath'  />       
		<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/IEUploader.js"></SCRIPT>
		       
       <SCRIPT type="text/javascript">

		function buttonEnable(nomButton){
			var button = document.getElementsByName(nomButton)[0];
			button.disabled = false;
		}

		function buttonDisable(nomButton){
			var button = document.getElementsByName(nomButton)[0];
			button.disabled = true;
		}
				
		function evaluerFichier(nomFichier){
			var pointPosition = nomFichier.lastIndexOf(".")+1;
			var extentions = nomFichier.substr(pointPosition).toUpperCase();

			if ("<%=GlobalConstants.Image.EXTENTION_IMAGE%>".indexOf(extentions) > -1){
				buttonEnable("Previsualiser");
				buttonDisable("associerFichier");
			}else{
				buttonDisable("Previsualiser");			
				buttonEnable("associerFichier");			
			}	
		}
		
       </SCRIPT>       
    </td>
    </tr>
    <tr>
    <td width="670" align="center" valign="top">
        <!-- Boutons -->
        <table width="720" cellpadding="5" cellspacing="0" border="0" bgcolor="#D0D0D0" class="greenOutline">
            <tr>
	    <td align="center" valign="top">
                <table width="400" height="75" border="0" cellpadding="5" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
		    <TR><TD>
                   <input type="button" name="connect" value="<bean:message key='numeriseur_t' />" onClick="Connection();photoLoad=true;assignerNumerisateur();" style="width='75'">
                </TD>
                <TD align="right">
					<html:file name="photo" property="sourceFile" onchange="assignerExtension(this.value);evaluerFichier(this.value);"/>
					<DIV id="cardex01Div" style="visibility: hidden;position: absolute;left: 0;right: 0">
						<html:file name="photo" property="cardex01"/>
					</DIV>    			   
		        </TD>
		        <TD>&nbsp;
		        </TD>
		    </TR>
		    <TR>
		        <TD>&nbsp;
		        </TD>
		        <TD bgcolor="#D0D0D0" align="right">
                     <input type="button" name="Previsualiser" value="<bean:message key='previsualiser_t' />" onClick="PreviewImage();photoLoad=true;" disabled="disabled" >
                </TD>
		        <TD align="center">
			         <input type="button" name="associerFichier" value="<bean:message key='associer_fichier' />" style='width: 80; text-align: center;' onclick='soumettreDocument();' disabled="disabled" >
                </TD>
		    </TR>
		</table>
            </td>
	    <td align="right" valign="top">
                <table width="250" height="75" border="0" cellpadding="5" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
		    <TR>
                         <TD align="center"><input type="button" name="annotation" value="<bean:message key='annoter_t' />" style="width='110'" onClick="doAnnoter();" disabled>
                       	</TD>
                        <TD>  
                           <DIV id="VERY_TWISTED_BUTTON" STYLE="width:110"><A HREF="#" name="Upload" 
                                    style="color:#808080;"><bean:message key='associer_image' /></A></DIV>
		        </TD>
		    </TR>
                    <tr>
			<TD align="center"><input type="button" name="appliquer" value="<bean:message key='appliquer_t' />" style="width='110'" onClick="doAppliquer();assignerUploader();" disabled>
                        </TD>
		        <TD align="center">
		             <input type="button" name="Upload" value="<bean:message key='annuler_t' />" style="width='110'" onClick="doCancel();">
		        </TD>
                    </tr>
		</TABLE>
	    </td>

            </tr>
        </table>

    </td>
    </tr>
</table>
	    <jsp:include page='/commun/aide.jsp' flush="true"/>

