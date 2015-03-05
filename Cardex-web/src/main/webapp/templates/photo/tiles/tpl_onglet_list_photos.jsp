
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>


<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

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

</SCRIPT>

<DIV id="DATA_PHOTOS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
      <TR>
          <TD class="listDetailOdd">
              <TABLE width="766" cellPadding="0" cellSpacing="0" border="0">
              <logic:equal name='sujet' property='photos.empty' value="true" >
                    <TR>
                        <TD class="listDetailOdd" rowspan="3" nowrap>
                            <cardex:linkPhotoUpload source='sujet' 
                            page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/photo/create.do"%>'>
                                <html:img page="/images/add_photo.gif" altKey="cb_lier" border="0" height="16" width="16" />
                            </cardex:linkPhotoUpload>
                            <cardex:linkFileUpload source='sujet' 
                            page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/image/create.do"%>'>
                                <html:img page="/images/add_scan.gif" altKey="cb_lier" border="0" height="14" width="14" />
                            </cardex:linkFileUpload>
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                        <TD>
                            &nbsp;
                        </TD>
                    </TR>
              </logic:equal>
              <logic:iterate id="element" name="sujet" property='photos'>

                    <TR>
                        <TD class="listDetailOdd" rowspan="3" nowrap>
                            <cardex:linkPhotoUpload source='sujet' 
                            page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/photo/create.do"%>'>
                                <html:img page="/images/add_photo.gif" altKey="cb_lier" border="0" height="16" width="16" />
                            </cardex:linkPhotoUpload>
                            <cardex:linkFileUpload source='sujet' 
                            page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/image/create.do"%>'>
                                <html:img page="/images/add_scan.gif" altKey="cb_lier" border="0" height="14" width="14" />
                            </cardex:linkFileUpload>
                        </TD>
                  <logic:iterate id="subelement" name="element" >
                        <TD>
			    <DIV align="center" STYLE="overflow:auto; width:225; height:175;" >
         			<IMG src="<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />" name="a<bean:write name='subelement' property='cle' />" border="0" CLASS="tableOutline" alt="" onload="this.style.zoom='100%'; this.style.height='160'" >
         		    </DIV>
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
			    <cardex:linkObject object='subelement' page='/sujet/photo/show.do'>
				  <html:img page="/images/magnify.gif" altKey="cb_consulter" border="0"  />
			    </cardex:linkObject>
					<cardex:securityDefineTag nameDefine="sujetPhotosZoom" securityConstraint="cardex.sujet.photos.zoom" />
					<% if ("true".equals(request.getAttribute("sujetPhotosZoomEnabled"))) { %>
                       <b><A HREF="javascript: viewPicture('<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='subelement' property='lienElement' />&SITE=<bean:write name='subelement' property='lienSiteElement' />&EXTENSION=<bean:write name='subelement' property='extension' />&GrandeImage=true');" ><bean:write name='subelement' property='lienElement' /></A></b>
                    <% }else{ %>
                         <b><bean:write name='subelement' property='lienElement' /></b>
                    <% } %>
                    
                            &nbsp;<bean:write name='subelement' property='site' />&nbsp;<bean:write name='subelement' property='dateCreation' />
                             <IMG src="<%=request.getContextPath()%>/images/moins.bmp" name="moins" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/moinsClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/moins.bmp'" onclick="zoomOut(a<bean:write name='subelement' property='cle' />);" >
			     			<IMG src="<%=request.getContextPath()%>/images/plus.bmp" name="plus" width="12" height="12" onmouseover="this.src='<%=request.getContextPath()%>/images/plusClick.bmp'" onmouseout="this.src='<%=request.getContextPath()%>/images/plus.bmp'" onclick="zoomIn(a<bean:write name='subelement' property='cle' />);" >
			  <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
			  <logic:equal name='subelement' property='site' value='<%= sujetSite %>' >
                            <cardex:linkObject onclick="return doConfirmLinkSuppression();" object='subelement' 
                            page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/photo/delete.do"%>'>
                                <html:img page="/images/trash.gif" altKey="cb_suprimer" border="0"  />
                            </cardex:linkObject>
	                  </logic:equal>

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
