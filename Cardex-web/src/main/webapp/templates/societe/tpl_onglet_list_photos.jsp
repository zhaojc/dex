<%-- --------------------------------------------------------------------------
Use case    : Sélection de l'onglet "Photos".
Description : Module qui affiche le contenu de l'onglet Photos.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/04/10 14:36:55 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/04/10 14:36:55 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>
<SCRIPT language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/IEUploader.js"></SCRIPT>

<!-- ------------------------------ -->
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

   function ajouter(){
      if (document.getElementsByName("ajoutPhoto.filePath")[0].value != "" && document.getElementsByName("ajoutPhoto.description")[0].value != "") {
      	unlockFields();
      	document.forms(0).ajout.src = '<%=request.getContextPath()%>/images/fermer.gif';
	  	soumettre('<%= request.getContextPath() + "/societe/update.do"%>');
	  }
   }

</SCRIPT>

<DIV id="DATA_PHOTOS">
    <TABLE width="850" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
      <TR>
          <TD class="listDetailOdd" align="center">
              <TABLE width="820" cellPadding="0" cellSpacing="0" border="0">
<html:hidden name='societe' property='ajoutPhoto.typeMultimedia' />
<html:hidden name='societe' property='ajoutPhoto.extension' />
<html:hidden name='societe' property='ajoutPhoto.lien' />
<html:hidden name='societe' property='ajoutPhoto.lienSite' />
<html:hidden name='societe' property='ajoutPhoto.cle' />
<html:hidden name='societe' property='ajoutPhoto.site' />
<html:hidden name='societe' property='ajoutPhoto.lienMultimedia' />
<html:hidden name='societe' property='ajoutPhoto.lienSiteMultimedia' />
<html:hidden name='societe' property='ajoutPhoto.lienElement' />
<html:hidden name='societe' property='ajoutPhoto.lienSiteElement' />
<html:hidden name='societe' property='ajoutPhoto.filePath'  />
		<cardex:securityDefineTag nameDefine="sectionPhotos" urlSecurite="/societe/photo/create.do">				
			<TR>
				<TD colspan="4">
				  <TABLE width="600" border="0">
                    <TR>
                        <TD class="listDetailOdd" nowrap align="right">
                        	<b><bean:message key='cb_ajouter' /><bean:message key='2.points' /></b>
                        </TD>
                        <TD witdh="400">
    					    <html:file name="societe" property="ajoutPhoto.sourceFile" size="90" onchange="assignerExtensionAjout(this.value);" style="HEIGHT: 20px; WIDTH: 450px" />
						</TD>
						<TD rowspan="2" align="left">		

    					      <img src="<%=request.getContextPath()%>/images/ouvrir.gif" id="ajout" TITLE="Ajouter la photo" border="1" height="30" width="30" onclick='ajouter();' />
    					</TD>
    				</TR>
    				<TR>
    					<TD class="listDetailOdd" nowrap align="right">
    					    <b><bean:message key='description_t' /></b>&nbsp;
    					</TD>
    					<TD witdh="400">
    					    <myriap:text name='societe' property="ajoutPhoto.description" maxlength="50" style="HEIGHT: 20px; WIDTH: 370px" />
    					</TD>
    					<TD>&nbsp;
    					</TD>
					</TR>
				</TABLE>
			  </TD>	
		   </TR>
	   </cardex:securityDefineTag>    					    
              <logic:equal name='societe' property='photos.empty' value="false" >
              		 <tr>
						<td align="center" colspan="4"><html:img page="/images/pixelnoir.gif" width="850" height="2" border="0" />
						</td>
					</tr>
              </logic:equal>
			<TR>
              <logic:iterate id="element" name="societe" property='photos'>

                  <logic:iterate id="subelement" name="element" >
                        <TD>
			    	<cardex:securityDefineTag nameDefine="societePhotosZoom" securityConstraint="cardex.societe.photos.zoom" />
                          <% if ("true".equals(request.getAttribute("societePhotosZoomEnabled"))) { %>
                             <b><A HREF="javascript: viewPicture('<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />&GrandeImage=true');" ><bean:write name='subelement' property='lienElement' /></A></b>
                          <% }else{ %>
                               <b><bean:write name='subelement' property='lienElement' /></b>
                          <% } %>
                            &nbsp;<bean:write name='subelement' property='site' />&nbsp;<bean:write name='subelement' property='intervenant' />&nbsp;<bean:write name='subelement' property='dateCreation' />
                             <IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut(a<bean:write name='subelement' property='cle' />);" >
			     <IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn(a<bean:write name='subelement' property='cle' />);" >
			  <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
			  <logic:equal name='subelement' property='site' value='<%= sujetSite %>' >
                              <cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='subelement' page='/societe/photo/delete.do'>
                                  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="0"  />
                              </cardex:linkObject>
  			  </logic:equal>
                        </TD>
                  </logic:iterate>
                  <% Collection collection = (Collection)pageContext.findAttribute("element"); %>
                  <% if (collection.size() == 1)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } if (collection.size() == 2)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } %>
                    </TR>
                    <TR>
                  <logic:iterate id="subelement" name="element" >
                        <TD>
				<%-- Prévisualisation avec ImagXpress.ImagXpress (Pegasus ImagXpress Control v3.0) 
				<OBJECT ID="Preview" WIDTH="320" HEIGHT="240"
				 CLASSID="CLSID:AAB6F9A1-C408-11D1-BC6D-00C0D1572A7B">
				    <PARAM NAME="_ExtentX" VALUE="5080">
				    <PARAM NAME="_ExtentY" VALUE="5080">
				    <PARAM NAME="Persistence" VALUE="0">
				    <PARAM NAME="AutoSize" VALUE="4">
				    <PARAM NAME="BackColor" VALUE="16777215">
				    <PARAM NAME="FileName" VALUE="<bean:write name='subelement' property='url' />">
				</OBJECT>
				--%>
			   <DIV align="center" STYLE="overflow:auto; width:225; height:175;" >
         			<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" name="a<bean:write name='subelement' property='cle' />" name="a<bean:write name='subelement' property='cle' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='160'" >
         		   </DIV>
                        </TD>
                  </logic:iterate>
                  <% collection = (Collection)pageContext.findAttribute("element"); %>
                  <% if (collection.size() == 1)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } if (collection.size() == 2)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } %>
                    </TR>
                    <TR>
                        <TD colspan="3">&nbsp;</TD>
                    </TR>
                    <TR>
                  <logic:iterate id="subelement" name="element" >
                        <TD>
                        	<bean:write name='subelement' property='description' />
                        </TD>
                  </logic:iterate>
                  <% collection = (Collection)pageContext.findAttribute("element"); %>
                  <% if (collection.size() == 1)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } if (collection.size() == 2)  { %>
                        <TD>
                            &nbsp;
                        </TD>
                  <% } %>
                    </TR>
                    <TR>
                        <TD colspan="3">&nbsp;</TD>
                    </TR>
              </logic:iterate>
              </TABLE>
          </TD>
      </TR>
    </TABLE>
</DIV>
<!-- End data_photos division -->
