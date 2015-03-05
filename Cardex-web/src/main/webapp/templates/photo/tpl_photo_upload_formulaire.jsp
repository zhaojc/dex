<%-- --------------------------------------------------------------------------
Use case    : Capture de photo.
Description : Écran utilisé afin de faire de la capture d'image avec une caméra
              numérique.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.20 $, $Date: 2002/04/19 17:56:31 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.20 $, $Date: 2002/04/19 17:56:31 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

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

<%-- Le contrôle de capture Pegasus utilise le clipboard, il faut désactiver le mécanisme de filtrage. --%>
<input type="hidden" name="clipboard" value="required">

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

<SCRIPT LANGUAGE="VBScript">

  Sub Connection()
    On Error Resume Next
    document.forms(0).Twain.OpenDSM
    If document.forms(0).Twain.DataSourceCount > 1 Then
       document.forms(0).Twain.SelectSource
    End If
    On Error Resume Next
    document.forms(0).Twain.StartSession
    document.forms(0).annotation.disabled = false
    document.forms(0).appliquer.disabled = false
    document.links("btnUpload").style.color = "#000000"
'    document.links("btnUpload").onClick = "soumettre();"
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
    document.links("btnUpload").style.color = "#000000"
    ' document.links("btnUpload").href = "javascript:callVbPhotoUpload();"
  End sub

  Sub CaptureImage()
    On Error resume next
    document.forms(0).Capture.GrabFrame
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        exit sub
    End If
    document.forms(0).Capture.FrameFile = "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"
    document.forms(0).Capture.SaveFrame
    document.forms(0).Preview.hDIB = document.forms(0).Capture.hDib

    'On limite la taille de l'image à 4 Mo
    document.forms(0).Preview.FileName = "C:\Program Files\Loto-Québec\Cardex\cardex01.jpg"

    If document.forms(0).Preview.IFilesize > 4000000 Then
		MsgBox "<bean:message key='erreur_fichier' />",16,"Cardex"
		Exit Sub
    End If

    document.forms(0).annotation.disabled = false
    document.forms(0).appliquer.disabled = false
    document.links("btnUpload").style.color = "#000000"
    ' document.links("btnUpload").href = "javascript:callVbPhotoUpload();"

    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        exit sub
    End If
   End sub

  Sub ConfigDevice()
    On Error resume next
    If document.forms(0).Capture.DeviceIndex < 0 Then
      document.forms(0).connect.disabled = true
      document.forms(0).captureNow.disabled = false
      document.forms(0).deconnecte.disabled = false
      document.forms(0).Capture.Connect 0
      If Err.Number <> 0 Then
         Alert Err.Description
         Err.Clear
         Exit Sub
      End If
    End If
    document.forms(0).Capture.Preview = true
    document.forms(0).Capture.ShowVideoSourceDlg
    ' document.forms(0).Capture.ShowVideoFormatDlg
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        exit sub
    End If
  End Sub

  Sub Deconnexion()
      document.forms(0).Capture.Disconnect
      document.forms(0).connect.disabled = false
      document.forms(0).captureNow.disabled = true
      document.forms(0).deconnecte.disabled = true
      document.forms(0).annotation.disabled = true
      document.forms(0).appliquer.disabled = true

  End Sub
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

	<table width="800" cellpadding="5" cellspacing="0" border="0" bgcolor="#EEEEEE" class="tableOutline">
		<tr>
		    <td width="800" align="center">

                      <TABLE><TR><TD>
                    <%-- Capture avec Pegasus.CapturePRO (Pegasus CapturePRO Control v2.0) --%>
  			<OBJECT ID="Capture" WIDTH="320" HEIGHT="240"
  			 CLASSID="CLSID:CC34CEB3-5C10-11D1-A40F-00A024229C83">
                            <PARAM NAME="_ExtentX" VALUE="5080">
                            <PARAM NAME="_ExtentY" VALUE="5080">
                            <PARAM NAME="_cx" VALUE="5080">
                            <PARAM NAME="_cy" VALUE="5080">
  			</OBJECT>
			<Script Language="VBScript">
			'Déverrouillage de l'objet CapturePro pour la capture à partir du port USB.
			'Selon le serveur le paramètre de déverrouillage est différent
			   Dim serveur
			   serveur = "<%= request.getServerName() %>"
			   'Par défaut, on met le serveur de production
			   document.forms(0).Capture.IntegratorWeb "https://cardex.loto-quebec.com?9F8539224D4E0DA01E0AC621BB52DD40"
			   If serveur = "localhost" then 'Environnement de développement
				document.forms(0).Capture.IntegratorWeb "localhost?260D1DDE4263A52305378F620D135BAE"
			   End If
			   If serveur = "denon.le500.loto-quebec.com" then 'Test et formation
				document.forms(0).Capture.IntegratorWeb "denon.le500.loto-quebec.com?EDBF036C11BE14B1C1C3BC6CD518F754"
			   End If
			   If serveur = "cardex.loto-quebec.ti" then 'Test et formation
				document.forms(0).Capture.IntegratorWeb "https://cardex.loto-quebec.ti?E46094DBCAE9D0CA08E38F3A11CD023F"
			   End If
			   If serveur = "cardex.loto-quebec.ta" then 'Test d'acceptation
				document.forms(0).Capture.IntegratorWeb "https://cardex.loto-quebec.ta?CED86FBCF96FE2E1BB0F197E651468ED"
			   End If
			   If serveur = "services4.loto-quebec.com" then 'Production Externe
				document.forms(0).Capture.IntegratorWeb "https://services4.loto-quebec.com?B18F35C36F7C74DC53B344406A89C6C5"
			   End If
			   If serveur = "services4.loto-quebec.ta" then 'Test d'acceptation Externe
				document.forms(0).Capture.IntegratorWeb "https://services4.loto-quebec.ta?36F665B0184DD098C742A0FC04C5B3F6"
			   End If
			</Script>
		    </td>
		    <td>
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
	    <PARAM NAME="AutoSize" VALUE="2">
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

    <DIV ID="annotation" STYLE="overflow:auto; width:520; height:440; z-index: 1; position: absolute; left: 15px; top: 100px; visibility='visible'" >
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
	    <DIV STYLE="visibility="hidden'">
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
          </DIV>
              </TD></TR></TABLE>
			</td>
		</tr>
                <tr>
                <td width="770" align="center">
                   <bean:message key='description_t' />
                   <myriap:text name='photo' property='description' size='50' maxlength='50'  style='font-family: Verdana, Arial; font-size: 9pt;' />
       				<html:hidden name='photo' property='filePath'  />
       
					<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/IEUploader.js"></SCRIPT>
                </td>
                </tr>

		<tr>
			<td width="770" align="center">
			  <!-- START BUTTONS SECTION -->
			  <table width="720" cellpadding="5" cellspacing="0" border="0" bgcolor="#D0D0D0" class="greenOutline">
      		<tr>
      			<td>

      			  <!-- DEVICE BUTTONS -->
      			  <table width="275" height="75" border="0" cellpadding="10" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>
            			<td>
            			<input type="button" name="connect" value="<bean:message key='numeriseur_t' />" onClick="Connection();assignerNumerisateur();photoLoad=true;" style="width='75'"><BR><BR>
            			<input type="button" name="config_device" value="<bean:message key='configurer_t' />" onClick="ConfigDevice()" style="width='75'" >&nbsp;&nbsp;
            			<input type="button" name="deconnecte" value="<bean:message key='deconnecter_t' />" onClick="Deconnexion()" disabled style="width='75'">
            			</td>
            		</tr>
        	    </table>
          	  <!-- END DEVICE -->
      			</td>

      			<!-- CAPTURE BUTTONS -->
      			<td align="center">
      			<TABLE width="140" height="75" border="0" cellpadding="10" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		    <tr>
            		    <td align="center">
	    					<DIV id="cardex01Div" style="visibility: hidden;position: absolute;left: 0;right: 0">
	    						<html:file name="photo" property="cardex01"/>
	    					</DIV>
            			  <input type="button" name="captureNow" value="<bean:message key='capturer_t' />" onClick="CaptureImage();assignerNumerisateur();photoLoad=true;" disabled>
            		    </td>
            		    </tr>
                        </table>
      			</td>

        		<td align="right">

        		  <!-- UPLOAD BUTTONS -->
	  <table width="275" height="75" border="0" cellpadding="5" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>

          			  <!-- VERY TWISTED BUTTON -->
                           <TD align="right"><input type="button" name="annotation" value="<bean:message key='annoter_t' />" style="width='75'" onClick="doAnnoter();" disabled>
                       	      &nbsp;
                       	   </TD>
                         <!-- Call to the following link is activated when the connect button is triggered.
                           See VBScripts  -->
                           <TD bgcolor="#D0D0D0" align="right"><DIV id="VERY_TWISTED_BUTTON" style="width='75'"><A HREF="#" name="btnUpload" onclick="soumettrePhoto();" 
                               style="color:#808080"><bean:message key='associer_t' /></A></DIV></TD>
                       </TR>
                       <TR>
			<TD align="right"><input type="button" name="appliquer" value="<bean:message key='appliquer_t' />" style="width='75'" onClick="doAppliquer();" disabled>
			    &nbsp;
                         </TD>
                      <TD bgcolor="#D0D0D0" align="right"><DIV id="VERY_TWISTED_BUTTON" style="width='75'">
                        <A HREF="javascript:doCancel();" style="color:#000000; "><bean:message key='annuler_t' /></A></DIV></TD>
                    </TR>
                  </TABLE>
                  <!-- END BUTTON -->
                  </td>
            		</tr>
        	    </table>
          	  <!-- END UPLOAD -->

              </td>
        		</tr>
        	</table>
      	<!-- END BUTTON SECTION -->
      </td>
		</tr>
	</table>
	<!-- END INTERFACE -->
	    <jsp:include page='/commun/aide.jsp' flush="true"/>


