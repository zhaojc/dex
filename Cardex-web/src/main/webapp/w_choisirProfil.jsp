<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<SCRIPT type="text/javascript">
function doLogout(){
	if (confirmation("Voulez-vous quitter l'application Cardex?")){
		windowClose();
	}
}

function doChoisir(code) {
	document.forms(0).choixProfil.value = code;
	document.forms(0).action='<%= request.getContextPath() + "/choisirProfil.do"%>';
	document.forms(0).submit();
}

function doFermer() {
	var win=window.open("","_self");
	win.close();
}
setTimeout("doFermer();", 1800000); // 1000 = 1 sec / 60000 = 1 minutes / 900000 = 15 minutes / 1800000 = 30 minutes

</SCRIPT>

<html:hidden name='profils' property="choixProfil" />

<table width="600" cellpadding="5" cellspacing="0" border="1" class="tableOutline" align = "center"
style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');">
	<TR>
		<TH colspan=1 align="center" height=55><B><I><U><FONT SIZE="+2" FACE="Helvetica" COLOR="#375392">Système Cardex</FONT></U></I></B>
	  	</TH>
	</TR>
   	<TR>
      	<TD>
	      	<i><b><FONT FACE="Helvetica" COLOR="#000000">En accédant au Cardex, vous vous engagez à assurer l'intégrité et à respecter le caractère hautement confidentiel 
	      	de la documentation, des informations et des données obtenues dans le cadre de sa consultation. En vertu du préjudice que 
	      	constitue l'utilisation et/ou la divulgation non autorisées des documents, des informations et des données contenus au Cardex 
	      	pour Loto-Québec, la Société pourrait prendre tout recours jugé approprié envers l'utilisateur fautif.
	      	</FONT></b></i>
	  	</TD>
   	</TR>      
</table>
<td></td>
<table width="1210" border="0" cellpadding="0" cellspacing="10" class="tableOutline" 
style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
     <tr>
	  <td align="center">
		<table>
			<tr>
			  <td align="center">
	    		<TABLE align="left" width="1200" cellPadding="2" cellSpacing="0" border="1" >
	    		  <tr>
					   <TD class="listTableHeader">&nbsp;</TD>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='profil'/>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='v_su_nom'/>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='v_su_prenom'/>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='l_si_cle_t'/></TD>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='tabpage_statut'/></TD>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='v_secteur'/></TD>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='autorite'/></TD>
					   <TD class="listTableHeader" nowrap="nowrap"><bean:message key='tabpage_confidentiel'/></TD>
				   </tr>
	           	  <logic:iterate id='element' name='profils' property='listeProfils' indexId="index">
                   <TR onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
					   	<TD class="listDetailOdd" align="center" valign="middle"><IMG src="<%=request.getContextPath()%>/images/lancer.jpg" height="20" width="40" name="cb_consulter" border="1" onclick="doChoisir('<bean:write name="element" property="code"/>');" alt="<bean:message key='choix'/>">
						</TD>
	                   	<TD class="listDetailOdd" nowrap="nowrap"><bean:write name="element" property="code"/></TD>
	                   	<TD class="listDetailOdd" nowrap="nowrap"><bean:write name="element" property="nom"/></TD>
	                   	<TD class="listDetailOdd" nowrap="nowrap"><bean:write name="element" property="prenom"/></TD>
			       		<TD class="listDetailOdd"  nowrap="nowrap"><bean:write name="element" property="siteDescription"/></TD>
			       		<TD class="listDetailOdd"  nowrap="nowrap"><bean:write name="element" property="statutDescription"/></TD>
				        <TD class="listDetailOdd"><bean:write name="element" property="secteurDescription"/>&nbsp;(<bean:write name="element" property="sousSecteurDescription"/>)</TD>
				        <TD class="listDetailOdd" align='center' nowrap="nowrap"><bean:write name="element" property="autoriteDescription"/></TD>
				       <TD class="listDetailOdd" align='center' nowrap="nowrap"><bean:write name="element" property="confidentialiteDescription"/></TD>
                   </TR>
                 </logic:iterate>
                </table>
               </td>
            </tr>
			<tr>
			    <TD align="right" height="30" colspan="2">
   	   				<cardex:button labelKey='cb_fermer' onclick='doLogout();' />
   				</TD>
			    
			</tr>
			
		</table>
	  </td>
	</tr>
</table>

