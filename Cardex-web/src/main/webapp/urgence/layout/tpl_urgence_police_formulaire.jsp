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
   //-- L'appel suivant g�n�re une des cha�nes suivante de caract�res:  fr, en, sp, de, it, ...
   //-- et utilis� pour l'appel de fichiers localis�s.  Gestion d'erreur en cas de timeout
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
<TABLE width="900" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style='background-image: url("<%=request.getContextPath()%>/images/background_journal.jpg");'>
	<tr>
		<td><html:img page="/images/blank.gif" width="1"
			height="1" border="0" /></td>
	</TR>
	<TR>
		<td align="left">
		<TABLE width="900" cellpadding="2" cellspacing="2" border="0">
		    <tr>
				<td width="10">&nbsp;</td>
				<td align="right" nowrap><b><bean:message key='i_cl_cle_t' /></b></td>
				<td><cardex:afficherValeurListeTag name="urgence" property="classe" classe='<%=GlobalConstants.CleListe.CLASSE %>' /></td>
	            <TD ALIGN="right"><b><bean:message key='nom'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="contact" tabindex="6" maxlength="60" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
				<td align="right" nowrap><b><bean:message key='societe_t' /></b>
				</td>
				<td>
			          <bean:define id="classe" name="urgence" property="classe" type="String"/>
			          <myriap:select size='1' name='urgence' property="lienSociete" tabindex="1" style="HEIGHT: 20px; WIDTH: 280px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.SOCIETE_PAR_CLASSE%>' valeurDiscriminant='<%=classe%>'/>
			          </myriap:select> 
				</td>
	            <TD ALIGN="right"><b><bean:message key='prenom'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="contactPrenom" tabindex="7" maxlength="30" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='unite'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="unite" tabindex="2" maxlength="50" style="HEIGHT: 20px; WIDTH: 215px" />
	            </TD>
	            <TD ALIGN="right"><b><bean:message key='grade'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="fonctionGrade" tabindex="8" maxlength="50" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='district.urgence'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="district" tabindex="4" maxlength="50" style="HEIGHT: 20px; WIDTH: 215px" />
	            </TD>
	            <TD ALIGN="right"><b><bean:message key='matricule'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" colspan="3">
	                <myriap:text name='urgence' property="matricule" tabindex="9" maxlength="30" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='numero.evenement'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="evenement" tabindex="5" maxlength="50" style="HEIGHT: 20px; WIDTH: 215px" />
	            </TD>
	            <TD ALIGN="right"><b><bean:message key='v_ad_telephone_t'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="telephone" tabindex="10" maxlength="20" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
				<td COLSPAN=4 ALIGN="right"><b><bean:message key='poste_telephonique'/></b></td>
				<td ALIGNE="left"><cardex:nombre name="urgence" property="poste" tabindex="11" /></td>
			</TR>
			<TR>
	            <TD td COLSPAN=4 ALIGN="right"><b><bean:message key='telecopieur'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='urgence' property="telecopieur" tabindex="12" maxlength="20" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
			<TR>
	            <TD td COLSPAN=4 ALIGN="right"><b><bean:message key='courriel_t2'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" colspan="3">
	                <myriap:text name='urgence' property="courriel" tabindex="13" maxlength="50" style="HEIGHT: 20px; WIDTH: 180px" />
	            </TD>
			</TR>
		<tr>
			<td align="center" colspan="5"><html:img
				page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
		</TR>
		<TR>
			<TD align="center"  colspan="5" ><!-- BOTTOM BUTTONS -->
			   <TABLE width="800" cellpadding="3" cellspacing="0" border="0">
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
