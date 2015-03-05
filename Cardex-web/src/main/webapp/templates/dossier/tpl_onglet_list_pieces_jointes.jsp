<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
    String sujetSite = "";
    String token = "";

    try
    {
        AuthenticationSubject sujet = (AuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
        CardexUser sujetCardex = (CardexUser) sujet.getUser();
        sujetSite = String.valueOf(sujetCardex.getSite());
        token = (String) request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
    }
    catch (Throwable e)
    {
    }
%>

<SCRIPT language="JavaScript" type="text/javascript">
    function viewPicture(theThing){
        if (theThing != ""){
            view_window = window.open( theThing, "view_window","toolbar=0,scrollbars=yes,resizable=1,width=650,height=520,top=" + ((screen.height - 520)/2) + ",left=" + ((screen.width -650)/2) );
            view_window.focus();
        }
    }
   function zoomIn(image) {
      if(parseInt(image.style.zoom) <= 250){
  	newZoom= parseInt(image.style.zoom)+10+'%'
	image.style.zoom =newZoom;
      }
   }
   function zoomOut(image) {
      newZoom= parseInt(image.style.zoom)-10+'%'
     image.style.zoom =newZoom;
   }

   function voir() {
	soumettre('<%=request.getContextPath() + "/dossier/pieceJointe/show.do"%>');
   }

   function ajouterPiece(){
      if (document.getElementsByName("ajoutPieceJointe.filePath")[0].value != "" && document.getElementsByName("ajoutPieceJointe.description")[0].value != "") {
      	document.forms(0).ajoutPiece.src = '<%=request.getContextPath()%>/images/fermer.gif';
		soumettre('<%=request.getContextPath() + "/dossier/pieceJointe/ajouterPieceJointe.do"%>');
	  }
   }

	function doSauvegardeLienPieceJointe(index, lienMultimedia, lienSiteMultimedia, lien, lienSite) {
	var confidentialite = document.getElementById("piecesJointes["+index+"].confidentialite");
	
	//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((confidentialite.value != "") && (confidentialite.value != "0")){ //On s'assure qu'il y a une confidentialité
		post('<%=request.getContextPath() + "/dossier/pieceJointe/update.do?lienMultimedia="%>'+lienMultimedia+'&lienSiteMultimedia='+lienSiteMultimedia+'&lien='+lien+'&lienSite='+lienSite+'&confidentialite='+confidentialite.value+'&<%=Globals.TOKEN_KEY + "=" + token%>');
		}
	}
	
	function assignerExtensionAjoutPiece(extension){
		var filePath = document.getElementsByName("ajoutPieceJointe.filePath")[0];
		filePath.value = extension;
	
		var fileDescription = document.getElementsByName("ajoutPieceJointe.description")[0];
		fileDescription.value = right(extension,50);
	}

</SCRIPT>

<DIV id="DATA_JOINED_PC">
<html:hidden name='dossier' property='ajoutPieceJointe.typeMultimedia' />
<html:hidden name='dossier' property='ajoutPieceJointe.extension' />
<html:hidden name='dossier' property='ajoutPieceJointe.lien' />
<html:hidden name='dossier' property='ajoutPieceJointe.lienSite' />
<html:hidden name='dossier' property='ajoutPieceJointe.cle' />
<html:hidden name='dossier' property='ajoutPieceJointe.site' />
<html:hidden name='dossier' property='ajoutPieceJointe.lienMultimedia' />
<html:hidden name='dossier' property='ajoutPieceJointe.lienSiteMultimedia' />
<html:hidden name='dossier' property='ajoutPieceJointe.lienElement' />
<html:hidden name='dossier' property='ajoutPieceJointe.lienSiteElement' />
<html:hidden name='dossier' property='ajoutPieceJointe.filePath'  />
   <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
	  <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
		<cardex:securityDefineTag nameDefine="sectionPieces" urlSecurite="/dossier/pieceJointe/image/create.do">	
	 	 <TR>
			<TD colspan="2">
			  <TABLE width="600" border="0">
                   <TR>
                       <TD class="listDetailOdd" nowrap align="right">
                       		<b><bean:message key='cb_ajouter' /><bean:message key='2.points' /></b>
                       </TD>
                       <TD witdh="400">
   					    <html:file name="dossier" property="ajoutPieceJointe.sourceFile" size="90" onchange="assignerExtensionAjoutPiece(this.value);" style="HEIGHT: 20px; WIDTH: 450px" />
						</TD>
						<TD rowspan="2" align="left">		
	   					    		<img src="<%=request.getContextPath()%>/images/ouvrir.gif" id="ajoutPiece" TITLE="Ajouter la pièce jointe" border="1" height="30" width="30" onclick='ajouterPiece();' />
	   					</TD>
	   				</TR>
   				<TR>
   					<TD class="listDetailOdd" nowrap align="right">
   					    <b><bean:message key='description_t' /></b>&nbsp;
   					</TD>
   					<TD witdh="400">
   					    <myriap:text name='dossier' property="ajoutPieceJointe.description" maxlength="50" style="HEIGHT: 20px; WIDTH: 370px" />
   					</TD>
   					<TD>&nbsp;
   					</TD>
				</TR>
			</TABLE>
		  </TD>	
	   	</TR>
	  </cardex:securityDefineTag>    					    
	</logic:notEqual>
             <logic:equal name='dossier' property='piecesJointes.empty' value="false" >
             		 <tr>
					<td align="center" colspan="2"><html:img page="/images/pixelnoir.gif" width="850" height="2" border="0" />
					</td>
				</tr>
             </logic:equal>

              <logic:iterate id="element" name="dossier" property='piecesJointes' indexId="index">
				    <bean:define id="cle" name='element' property='lienElement' />
				    <bean:define id="site" name='element' property='lienSiteElement' />
				    <bean:define id="extension" name='element' property='extension' />
				    <bean:define id="lienMultimedia" name='element' property='lienMultimedia' />
				    <bean:define id="lienSiteMultimedia" name='element' property='lienSiteMultimedia' />
				    <bean:define id="confidentialite" name='element' property='confidentialite' />
				    <bean:define id="lien" name='element' property='lien' />
				    <bean:define id="lienSite" name='element' property='lienSite' />
              
                    <TR>
                        <TD valign='top' width="400">
                          <TABLE>
                            <TR><TD>
                            <cardex:securityDefineTag nameDefine="dossierPhotosZoom" securityConstraint="cardex.dossier.photos.zoom" />
				  	<%
				  	    if ("true".equals(request.getAttribute("dossierPhotosZoomEnabled")))
				  	        {
				  	%>
					   <b><A HREF="javascript: viewPhoto('<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />&GrandeImage=true');" ><bean:write name='element' property='lienElement' /></A></b>
					<%
					    }
					        else
					        {
					%>
					     <b><bean:write name='element' property='lienElement' /></b>
					<%
					    }
					%>
				    &nbsp;<bean:write name='element' property='site' />&nbsp;<bean:write name='element' property='intervenant' />&nbsp;<bean:write name='element' property='dateCreation' />
				    <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
					<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
					<logic:equal name='element' property='site' value='<%=sujetSite%>' >
					      <cardex:securityDefineTag nameDefine="dossierLiaisonSupprimer" urlSecurite="/dossier/photo/delete.do">
						      	<cardex:linkObject onclick="return doConfirmLinkSuppression();" object="element" page='/dossier/photo/delete.do'>
						      		<html:img page="/images/trash.gif" altKey="cb_supprimer" border="0" />
						      	</cardex:linkObject>
					      </cardex:securityDefineTag>					      
					</logic:equal>
				    </logic:notEqual>
				    </td></tr>
				    <tr><td>
				    <logic:equal name='element' property='extension' value="JPG" >
					    <cardex:linkObject object='element' page='/dossier/pieceJointe/show.do'>
						  <html:img page="/images/magnify.gif" altKey="cb_consulter" border="0"  />
					    </cardex:linkObject>
				           <IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut(a<bean:write name='element' property='cle' />);" >
				           <IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn(a<bean:write name='element' property='cle' />);" >
				    </logic:equal>
				    <logic:equal name='element' property='extension' value="BMP" >
					    <cardex:linkObject object='element' page='/dossier/pieceJointe/show.do'>
						  <html:img page="/images/magnify.gif" altKey="cb_consulter" border="0"  />
					    </cardex:linkObject>
				           <IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut(a<bean:write name='element' property='cle' />);" >
				           <IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn(a<bean:write name='element' property='cle' />);" >
				    </logic:equal>
                        	</TD>
	                    </TR>
	                    <TR>
	                        <TD><bean:write name='element' property='description' /></TD>
	                    </TR>
						<TR>
					        <TD ALIGN="left" VALIGN="top"><bean:message key='i_cc_cle_t'/>
			              	    <bean:define id="clePieceJointe" name='dossier' property='<%="piecesJointes["+index+"].cle"%>'/>
					            <bean:define id="sitePieceJointeLien" name='dossier' property='<%="piecesJointes["+index+"].site"%>' type="String"/>
					        	<myriap:select size='1' id='<%="piecesJointes["+index+"].confidentialite"%>' 
					        	               name='element' property="confidentialite" 
					        	               style='HEIGHT: 20px; WIDTH: 100px' tabindex="1" >
							   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
													  valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
													  actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
					 			</myriap:select>
					 			&nbsp;
								<!-- On interdit la mise à jour du lien s'il ne s'agit pas du même site -->
								<logic:equal name='element' property='site' value='<%=sujetSite%>' >
								      <cardex:securityDefineTag nameDefine="dossierLiaisonSauvegarder" urlSecurite="/dossier/pieceJointe/update.do">
							      		<IMG onclick="doSauvegardeLienPieceJointe(<%=index%>,<%=lienMultimedia%>,<%=lienSiteMultimedia%>,<%=lien%>,<%=lienSite%>);"
							      		     src="<%=request.getContextPath()%>/images/sauvegarde.gif" 
										     altKey="cb_ok" title="Sauvegarder la pièce jointe"
										     border="1" height="14" width="14" />
								      </cardex:securityDefineTag>					      
								</logic:equal>
					        </TD>
		   				</TR>
	                 </TABLE>
	               </TD>
                   <TD>
			  <logic:equal name='element' property='extension' value="JPG" >                        
				  <DIV STYLE="overflow:auto; width:480; height:270;" >
				      <IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />" name="a<bean:write name='element' property='cle' />" border="1" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='240'" >
				  </DIV>   
			  </logic:equal>
		    <%
		        String fileName = request.getContextPath() + "/AffichageLoupe?CLE=" + cle + "&SITE=" + site + "&EXTENSION=" + extension;
		    %>
		    <logic:equal name='element' property='extension' value="WMV" >
				<object id="MediaPlayer" width="301" height="345"
					standby="Chargement des composantes Windows Media Player..."
					type="application/x-mplayer2" style="border: thin solid">
						<param name="filename" value='<%=fileName.toString()%>' />
						<param name="height" value="384" />
						<param name="width" value="320" />
						<param name="autoStart" value="0" />
						<param name="autoPlay" value="1" />
						<param name="AnimationatStart" value="0" />
						<param name="TransparentAtStart" value="1" />
						<param name="ShowControls" value="1" />
						<param name="ShowStatusBar" value="0" />
						<PARAM name="ShowDisplay" VALUE="0">
						<param name="bgcolor" value="#000000" />
						<param name="volume" value="100%" />
						<param name="loop" value="0" />
						<param name="AutoSize" value="0">
						<param name="DisplaySize" value="0">
				</object>
		    </logic:equal>
		    <logic:equal name='element' property='extension' value="MOV" >
				<object id="MediaPlayer" width="301" height="345"
					standby="Chargement des composantes Windows Media Player..."
					type="application/x-mplayer2" style="border: thin solid">
						<param name="filename" value='<%=fileName.toString()%>' />
						<param name="height" value="384" />
						<param name="width" value="320" />
						<param name="autoStart" value="0" />
						<param name="autoPlay" value="1" />
						<param name="AnimationatStart" value="0" />
						<param name="TransparentAtStart" value="1" />
						<param name="ShowControls" value="1" />
						<param name="ShowStatusBar" value="0" />
						<PARAM name="ShowDisplay" VALUE="0">
						<param name="bgcolor" value="#000000" />
						<param name="volume" value="100%" />
						<param name="loop" value="0" />
						<param name="AutoSize" value="0">
						<param name="DisplaySize" value="0">
				</object>
		    </logic:equal>
		    <logic:equal name='element' property='extension' value="AVI" >
				<object id="MediaPlayer" width="301" height="345"
					standby="Chargement des composantes Windows Media Player..."
					type="application/x-mplayer2" style="border: thin solid">
						<param name="filename" value='<%=fileName.toString()%>' />
						<param name="height" value="384" />
						<param name="width" value="320" />
						<param name="autoStart" value="0" />
						<param name="autoPlay" value="1" />
						<param name="AnimationatStart" value="0" />
						<param name="TransparentAtStart" value="1" />
						<param name="ShowControls" value="1" />
						<param name="ShowStatusBar" value="0" />
						<PARAM name="ShowDisplay" VALUE="0">
						<param name="bgcolor" value="#000000" />
						<param name="volume" value="100%" />
						<param name="loop" value="0" />
						<param name="AutoSize" value="0">
						<param name="DisplaySize" value="0">
				</object>
		    </logic:equal>					  
			  <logic:equal name='element' property='extension' value="PNG" >                        
				  <DIV STYLE="overflow:auto; width:480; height:270;" >
				      <IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />" name="a<bean:write name='element' property='cle' />" border="1" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='240'" >
				  </DIV>   
			  </logic:equal>			  
			  <logic:equal name='element' property='extension' value="PDF" > 
			     <DIV STYLE="overflow:auto; width:100; height:100; " >
					<html:img page="/images/pdf.jpg" altKey="Document PDF" border="0" />
				    <!-- object CLASSID='clsid:CA8A9780-280D-11CF-A24D-444553540000' width=470 height=510 >
				    	<PARAM NAME="SRC" VALUE="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />">
			        </OBJECT -->
			     </DIV>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="DOC" >                        
			     <DIV align="center" STYLE="overflow:auto; width:70; height:60; border='0.05cm groove black'" >
			  		<html:img page="/images/Word.gif" altKey="Document Word" border="0" />               
			     </DIV>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="DOCX" >                        
			     <DIV align="center" STYLE="overflow:auto; width:70; height:60; border='0.05cm groove black'" >
			  		<html:img page="/images/Word.gif" altKey="Document Word" border="0" />               
			     </DIV>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="DOT" >                        
			     <DIV  STYLE="overflow:auto; width:480; height:480; border='0.05cm groove black'" contentEditable='false' >
			          <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=470 height=470 TYPE="application/msword" >
			          </OBJECT>
			     </DIV>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="GIF" >                        
			      <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=240 height=240 TYPE="image/gif" >
			      </OBJECT>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="TXT" >                        
			      <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=480 height=480 TYPE="text/plain" >
			      </OBJECT>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="XLS" >         
			  	<html:img page="/images/excel.gif" altKey="Document Excel" border="0" />               
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="XLSX" >         
			  	<html:img page="/images/excel.gif" altKey="Document Excel" border="0" />               
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="HTM" >                        
			      <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=480 height=480 TYPE="text/html" ></OBJECT>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="HTML" >                        
			      <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=480 height=480 TYPE="text/html" ></OBJECT>			  
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="BMP" >                        
			      <DIV STYLE="overflow:auto; width:480; height:270;" >
				  <IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  name="a<bean:write name='element' property='cle' />" border="1" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='240'" >
			      </DIV>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="TIF" >                        
			      <IMG src="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=480 height=480 >
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="RTF" >                        
			      <object data="<%=request.getContextPath()%>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />"  width=480 height=480 TYPE="application/msword" >
			      </OBJECT>
			  </logic:equal>
			  <logic:equal name='element' property='extension' value="WAV" >
			    	<object id="MediaPlayer" width="301" height="44"
						standby="Chargement des composantes Windows Media Player..."
						type="application/x-mplayer2" style="border: thin solid">
							<param name="filename" value='<%=fileName.toString()%>' />
							<param name="autoStart" value="0" />
							<param name="autoPlay" value="1" />
							<param name="AnimationatStart" value="0" />
							<param name="TransparentAtStart" value="1" />
							<param name="ShowControls" value="1" />
							<param name="ShowStatusBar" value="0" />
							<PARAM name="ShowDisplay" VALUE="0">
							<param name="bgcolor" value="#000000" />
							<param name="volume" value="100%" />
							<param name="loop" value="0" />
							<param name="AutoSize" value="0">
							<param name="DisplaySize" value="0">
					</object>
			  </logic:equal>
                    </TD>
	            </TR>
              </logic:iterate>
              </TABLE>
          </TD>
      </TR>
    </TABLE>
     
</DIV>
<!-- End data_DATA_JOINED_PC division -->
