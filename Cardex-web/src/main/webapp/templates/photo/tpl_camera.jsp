<%-- --------------------------------------------------------------------------
Description : Écran utilisé pour visualiser la transmission des caméras
              de surveillance.
	      Appelé par w_camera.jsp
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>


<%-- Le contrôle de capture Pegasus utilise le clipboard, il faut désactiver le mécanisme de filtrage. --%>
<input type="hidden" name="clipboard" value="required">

<SCRIPT LANGUAGE="VBScript">
<!--
  Sub Connection()
    If document.forms(0).Capture.NumDevices < 1 Then
      Alert "[[Aucun digitaliseur ou caméra n'est installé sur ce système.]]"
      Exit Sub
    End If
    On Error resume next
    If document.forms(0).Capture.DeviceIndex < 0 Then
      document.forms(0).Capture.Connect 0
        If Err.Number <> 0 Then
          Alert Err.Description
          Err.Clear
          Exit Sub
        End If
        document.forms(0).Capture.Preview = true
     	document.forms(0).connect.value = "<bean:message key='deconnecter_t' />"
     	document.forms(0).captureNow.disabled = false
     	document.forms(0).config_device.disabled = false
    Else
        document.forms(0).Capture.Disconnect
        document.forms(0).connect.value = "<bean:message key='connecter_t' />"
        document.forms(0).captureNow.disabled = true
     	document.forms(0).config_device.disabled = true
    End If
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
    End If
  End sub

  Sub CaptureImage()
    On Error resume next
    document.forms(0).Capture.GrabFrame
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        exit sub
    End If
    document.forms(0).Capture.FrameFile = "c:\temp\capture\temporaire.jpg"
    document.forms(0).Capture.SaveFrame
    document.forms(0).Preview.hDIB = document.forms(0).Capture.hDIB

    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
        exit sub
    End If
   End sub


  Sub ConfigDevice()
    On Error resume next
    document.forms(0).Capture.ShowVideoSourceDlg
    ' document.forms(0).Capture.ShowVideoFormatDlg
    If Err.Number <> 0 Then
        Alert Err.Description
        Err.Clear
    End If
  End Sub

-->
</SCRIPT>
    <table width="680" cellpadding="5" cellspacing="0" border="0" bgcolor="#EEEEEE" class="tableOutline">
		<tr>
			<td width="680" align="center">

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
  			<IMG src="<%= request.getContextPath() %>/images/blank.gif" width="10" height="0" border="0" />

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

			</td>
		</tr>

		<tr>
			<td width="680" align="center">
			  <!-- START BUTTONS SECTION -->
			  <table width="450" cellpadding="5" cellspacing="0" border="0" bgcolor="#D0D0D0" class="greenOutline">
      		<tr>
      			<td>

      			  <!-- DEVICE BUTTONS -->
      			  <table width="170" height="75" border="0" cellpadding="10" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>
            			<td>
            			<input type="button" name="connect" value="<bean:message key='connecter_t' />" onClick="Connection()"><BR><BR>
            			<input type="button" name="config_device" value="<bean:message key='configurer_t' />" onClick="ConfigDevice()" disabled>
            			</td>
            		</tr>
        	    </table>
          	  <!-- END DEVICE -->
      			</td>

      			<!-- CAPTURE BUTTONS -->
      			<td align="center">
      			<table width="140" height="75" border="0" cellpadding="10" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		    <tr>
            		    <td align="center">
            			  <input type="button" name="captureNow" value="<bean:message key='capturer_t' />" onClick="CaptureImage()" disabled>
            		    </td>
            		    </tr>
                        </table>
      			</td>

        		<td align="right">

        		  <!-- UPLOAD BUTTONS -->
      			  <table width="275" height="75" border="0" cellpadding="5" cellspacing="0" bgcolor="#D0D0D0" class="greenOutline">
            		<tr>
            			<td align="right" valign="bottom">

          			  <!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                      <TR>
                  </TABLE>
                  <!-- END BUTTON -->
          			</td>

          		</tr>
          		<tr>

          			<td align="right" valign="top">
          			<!-- VERY TWISTED BUTTON -->
                  <TABLE cellpadding="0" cellspacing="0" border="0">
                    <TR>
                      <TD bgcolor="#D0D0D0"><DIV id="VERY_TWISTED_BUTTON">
                        <A HREF="#" onclick="doCancel();" style="color:#000000;"><bean:message key='cb_fermer' /></A></DIV></TD>
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


