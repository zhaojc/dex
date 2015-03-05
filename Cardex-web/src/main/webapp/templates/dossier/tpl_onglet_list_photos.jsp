<%-- --------------------------------------------------------------------------
Use case    : S�lection de onglet "particularit�s".
Description : Module qui affiche le contenu de l'onglet "particularit�s", soit une
              liste de particularit�s.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.14 $, $Date: 2002/04/10 14:37:09 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Premi�re �tape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.14 $, $Date: 2002/04/10 14:37:09 $, $Author: mlibersan $
Derniers commentaires � jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<!-- R�cup�ration du site auquel appartient l'utilisateur pour d�terminer
     le droit de suppression dans l'onglet (permis seulement si le site est le m�me -->
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
    function viewPhoto(theThing){
        if (theThing != ""){
//--            view_window = window.open( theThing, "view_window","toolbar=0,scrollbars=yes,resizable=1,width=650,height=520,top=" + ((screen.height - 520)/2) + ",left=" + ((screen.width -650)/2) );
            view_window = window.open( theThing, "view_window","toolbar=0,scrollbars=yes,resizable=1,width=750,height=620,top=10,left=10" );
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
      	document.forms(0).ajoutPhoto.src = '<%=request.getContextPath()%>/images/fermer.gif';
      	unlockFields();
	  	soumettre('<%= request.getContextPath() + "/dossier/photo/ajouterPhoto.do"%>');
	  }
   }

</SCRIPT>

<DIV id="DATA_PHOTOS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
      <TR>
          <TD class="listDetailOdd">
              <TABLE width="766" cellPadding="0" cellSpacing="0" border="0">
<html:hidden name='dossier' property='ajoutPhoto.typeMultimedia' />
<html:hidden name='dossier' property='ajoutPhoto.extension' />
<html:hidden name='dossier' property='ajoutPhoto.lien' />
<html:hidden name='dossier' property='ajoutPhoto.lienSite' />
<html:hidden name='dossier' property='ajoutPhoto.cle' />
<html:hidden name='dossier' property='ajoutPhoto.site' />
<html:hidden name='dossier' property='ajoutPhoto.lienMultimedia' />
<html:hidden name='dossier' property='ajoutPhoto.lienSiteMultimedia' />
<html:hidden name='dossier' property='ajoutPhoto.lienElement' />
<html:hidden name='dossier' property='ajoutPhoto.lienSiteElement' />
<html:hidden name='dossier' property='ajoutPhoto.filePath'  />              
		<logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
		  <cardex:securityDefineTag nameDefine="sectionPhotos" urlSecurite="/dossier/photo/create.do">				
			<TR>
				<TD colspan="4">
				  <TABLE width="600" border="0">
                    <TR>
                        <TD class="listDetailOdd" nowrap align="right">
                        	<b><bean:message key='cb_ajouter' /><bean:message key='2.points' /></b>
                        </TD>
                        <TD witdh="400">
    					    <html:file name="dossier" property="ajoutPhoto.sourceFile" size="90" onchange="assignerExtensionAjout(this.value);" style="HEIGHT: 20px; WIDTH: 450px" />
						</TD>
						<TD rowspan="2" align="left">
		    					      <img src="<%=request.getContextPath()%>/images/ouvrir.gif" id="ajoutPhoto" TITLE="Ajouter la photo" border="1" height="30" width="30" onclick='ajouter();' />    					    
    					</TD>
    				</TR>
    				<TR>
    					<TD class="listDetailOdd" nowrap align="right">
    					    <b><bean:message key='description_t' /></b>&nbsp;
    					</TD>
    					<TD witdh="400">
    					    <myriap:text name='dossier' property="ajoutPhoto.description" maxlength="50" style="HEIGHT: 20px; WIDTH: 370px" />
    					</TD>
    					<TD>&nbsp;
    					</TD>
					</TR>
				</TABLE>
			  </TD>	
		   </TR>
	     </cardex:securityDefineTag>
		</logic:notEqual> 
              <logic:equal name='dossier' property='photos.empty' value="false" >
              		 <tr>
						<td align="center" colspan="4"><html:img page="/images/pixelnoir.gif" width="850" height="2" border="0" />
						</td>
					</tr>
              </logic:equal>
           <TR>

                 <logic:iterate id="element" name="dossier" property='photos'>

                  <logic:iterate id="subelement" name="element" >
                        <TD>
			    <cardex:linkObject object='subelement' page='/dossier/pieceJointe/show.do'>
				  <html:img page="/images/magnify.gif" altKey="cb_consulter" border="0"  />
			    </cardex:linkObject>
			    	<cardex:securityDefineTag nameDefine="dossierPhotosZoom" securityConstraint="cardex.dossier.photos.zoom" />
                          <% if ("true".equals(request.getAttribute("dossierPhotosZoomEnabled"))) { %> 
                                   <b><A HREF="javascript: viewPhoto('<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />&GrandeImage=true');" ><bean:write name='subelement' property='lienElement' /></A></b>
                          <% }else{ %>
                                     <b><bean:write name='subelement' property='lienElement' /></b>
                          <% } %>
                            &nbsp;<bean:write name='subelement' property='site' />&nbsp;<bean:write name='subelement' property='intervenant' />&nbsp;<bean:write name='subelement' property='dateCreation' />
                             <IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut(a<bean:write name='subelement' property='cle' />);" />
			     			 <IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn(a<bean:write name='subelement' property='cle' />);" />
                            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<!-- On interdit la suppression du lien s'il ne s'agit pas du m�me site -->
				<logic:equal name='subelement' property='site' value='<%= sujetSite %>' >
				      <cardex:securityDefineTag nameDefine="dossierPhotosSupprimer" urlSecurite="/dossier/photo/delete.do">
					      	<cardex:linkObject onclick="return doConfirmLinkSuppression();" object="subelement" page='/dossier/photo/delete.do'>
					      		<html:img page="/images/trash.gif" altKey="cb_supprimer" border="0" />
					      	</cardex:linkObject>
				      </cardex:securityDefineTag>
				</logic:equal>
                            </logic:notEqual>
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
                            <DIV align="center" STYLE="overflow:auto; width:225; height:175;" >
			        <IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" name="a<bean:write name='subelement' property='cle' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='160'">
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
