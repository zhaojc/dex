<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>


<tiles:useAttribute name="soumettreURL" id="soumettreURL" classname="String"/>

<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
function afficher(valeur){
alert(valeur);
}

function doOk() {
  	  unlockFields();
  	  //alert('<%= request.getContextPath()%>' + '<%=soumettreURL%>'); 
      //soumettre('<%= request.getContextPath()%>' + '<%=soumettreURL%>');    
      soumettre('<%= request.getContextPath() + "/dossier/urgence/add.do"%>');
      
}


function doCancel() {
    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="urgence" property="lienSite"/>&cle=<bean:write name="urgence" property="lien"/>';
}

</SCRIPT> 

      <!-- CONTENT -->
<TABLE width="720" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style='background-image: url("<%=request.getContextPath()%>/images/background_journal.jpg");'>
	<tr>
		<td><html:img page="/images/blank.gif" width="1"
			height="1" border="0" /></td>
	</TR>
	<TR>
		<td align="left">
		<TABLE width="710" cellpadding="2" cellspacing="2" border="0">
		    <tr>
				<td width="10">&nbsp;
				</td>
				<td align="right" nowrap><b><bean:message key='i_cl_cle_t' /></b></td>
				<td colspan="3"><cardex:afficherValeurListeTag name="urgence" property="classe" classe='<%=GlobalConstants.CleListe.CLASSE %>' />
				</td>
				
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
				<td align="right" nowrap><b><bean:message key='societe_t' /></b>
				</td>
				<td colspan="3">
			    	<bean:define id="classe" name="urgence" property="classe" type="String"/>
			        	<myriap:select size='1' 
			        	               name='urgence' 
			        	               property="lienSociete" 
			        	               tabindex="1"
			        	               style="HEIGHT: 20px; WIDTH: 280px" 
			        	               onkeypress="typeAhead(this, event);" 
			        	               onfocus="resetIncrementalSearch();" >
			          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.SOCIETE_PAR_CLASSE%>' valeurDiscriminant='<%=classe%>'/>
			          </myriap:select> 
				</td>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='l_vi_cle_t'/>&nbsp;</b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="ville" tabindex="2" maxlength="50" style="HEIGHT: 20px; WIDTH: 150px" />
	            </TD>
				<TD ALIGN="right" nowrap><b><bean:message key='contact'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left">
	                <myriap:text name='urgence' property="contact" tabindex="3" maxlength="50" style="HEIGHT: 20px; WIDTH: 250px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='motif'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
			          <myriap:select size='1' name='urgence' property="motif" tabindex="4" style="HEIGHT: 20px; WIDTH: 150px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		                  	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
							valeurTableValeur='<%=GlobalConstants.TableValeur.MOTIF%>' />
			          </myriap:select> 
	            </TD>
	            <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
	            <TD ALIGN="left" >
	                   <myriap:select name='urgence' property="statut" tabindex="5" size="1" style="HEIGHT: 20px; WIDTH: 250px">
	                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.URGENCE %>'/>
	                   </myriap:select>
	            </TD>
			</TR>
		<tr>
			<td align="center" colspan="5"><html:img
				page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
		</TR>
		<TR>
			<TD align="center"  colspan="5" ><!-- BOTTOM BUTTONS -->
			   <TABLE width="710" cellpadding="3" cellspacing="0" border="0">
				<TR>
					<TD align="left">
						<cardex:button urlSecurite='<%=soumettreURL%>' labelKey='cb_ok' soumettre='<%=soumettreURL%>' style="color: #000000; width: 70px; text-align: center" />
					</TD>
					<TD align="right">
						<cardex:button urlSecurite='<%=soumettreURL%>' labelKey='cb_annuler' onclick="doCancel();" style="color: #000000; width: 60px; text-align: center" />
					</TD>
				</TR>
			  </TABLE>
			</TD>
		</TR>
	 </TABLE>
	</TD>
  </TR>
</TABLE>


<html:hidden property="lien"/>
<html:hidden property="lienSite"/>
<html:hidden property="classe"/>
<html:hidden property="lienSiteSociete"/>
