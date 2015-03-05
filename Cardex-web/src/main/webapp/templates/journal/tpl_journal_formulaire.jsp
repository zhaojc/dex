<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>

<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<tiles:useAttribute name="actionSecurite" id="actionSecurite" classname="String"/>

<SCRIPT language="JavaScript" type="text/javascript">
function doClickDateFin(){
	if (document.forms(0).dateFin.value == null ||document.forms(0).dateFin.value.length == 0 ) {
		document.forms(0).dateFin.value = document.forms(0).dateDebut.value;
	}
}
</SCRIPT>

<%-- Le clipboard doit être actif dans les narrations. --%>
<input type="hidden" name="clipboard" value="required">

<bean:define id="entite" name='journal' property="entite" type="String"/>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="675"border="0" cellpadding="0" cellspacing="0" class="tableOutline" >
	<tr>
		<td align="center" CLASS="tabBackground" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");' >

  <table width="648" border="0" cellpadding="5" cellspacing="0">
		<tr>
      <td colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
    </tr>

    <tr>
	<td width="125" align="right" >
	</td>
	<td width="645" align="left">
	  <!-- START TAB CONTENT -->
	    <table width="600" cellpadding="2" cellspacing="0" border="0" class="tableOutline">
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
      		</tr>

      		<tr>
      	 	<TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        		<b><bean:message key='v_do_numero_dossier_t'/></b>
        	</TD>
      		<TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
      			<bean:write name='journal' property="numeroDossier.site"/><bean:write name='journal' property="numeroDossier.date"/><bean:write name='journal' property="numeroDossier.sequence"/>
      		</TD>
	        <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/></b></TD>
	        <TD ALIGN="left">
				<cardex:afficherValeurListeTag name="journal" property="site" classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' actionSecurite='<%=actionSecurite%>' />
			</TD>
      		<td>&nbsp;</td>
      		</tr>

      		<tr>
		    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
		    <TD ALIGN="left" colspan="3">
		       <cardex:afficherValeurListeTag name="journal" property="intervenant" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
		    </TD>
	  	    <td>&nbsp;</td>
      		</tr>
	        <TR>
	    	  <TD ALIGN="center" HEIGHT="15" colspan="5"><html:img page="/images/0061CFpixel.gif" height="1" width="595" border="0" /></TD>
	        </TR>

      		<tr>
	         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="nature" name='journal' property='nature' type="String"/>
	         	<bean:define id="type" name='journal' property='type' type="String"/>
	             <myriap:select size='1' name='journal' property='type' style='HEIGHT: 20px; WIDTH: 160px' onchange='doRefresh();' tabindex="1" >
 		        		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
							valeurDiscriminant='<%=nature%>' 
							actionSecurite='<%=actionSecurite%>' />
	             </myriap:select>
	         </TD>
            		<TD ALIGN="right"><b><bean:message key='v_do_ancienne_reference_t'/></b></TD>
      			<td>
		      		<myriap:text name='journal' property="numeroIncident" maxlength="40" style="HEIGHT: 20px; WIDTH: 140px"  tabindex="7" />
      			</td>
      			<td>&nbsp;</td>
      		</tr>

      		<tr>
		 <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
	         <TD ALIGN="left">
		     	<myriap:select size='1' name='journal' property='categorie' tabindex="2" style='HEIGHT: 20px; WIDTH: 160px' >
  	       		    <cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE %>' 
						valeurDiscriminant='<%=type%>'
						actionSecurite='<%=actionSecurite%>' />
	             </myriap:select>
      		 </TD>
        	<TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
  			<td>
    			<myriap:text name='journal' property="numeroEmploye" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  tabindex="8"/>
  			</td>
			<td>&nbsp;</td>
      		</tr>

      		<TR>
      			<TD ALIGN="right" ><b><bean:message key='i_or_cle_t'/></b></TD>
      			<TD>
                <bean:define id="site" name='journal' property='site' type="String"/>
			        <myriap:select size='1' name='journal' property='endroit' style='HEIGHT: 20px; WIDTH: 160px' tabindex="3" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
							<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				 	</myriap:select>
      			</TD>
	        	<TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
	  			<td>
	    			<myriap:text name='journal' property="reference2" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  tabindex="9"/>
	  			</td>
      			<td>&nbsp;</td>
      		</TR>
      		<tr>
	            <TD ALIGN="right"><b><bean:message key='origine_t'/></b></TD>
		        <TD ALIGN="left" >
				    <myriap:select name='journal' property="origine" tabindex="4" size="1" style="HEIGHT: 20px; WIDTH: 160px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
				       <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=actionSecurite%>'/>
				    </myriap:select>&nbsp;
		        </TD>
	        	<TD ALIGN="right"><b><bean:message key='v_do_reference3_t'/></b></TD>
	  			<td>
	    			<myriap:text name='journal' property="reference3" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  tabindex="10"/>
	  			</td>
            		<TD ALIGN="left">&nbsp;</TD>
      		</tr>
		<tr>
			<TD ALIGN="right" nowrap><b><bean:message key='v_do_ref_video_t'/></b></TD>
			<TD ALIGN="left" >
				<myriap:text name='journal' property="referenceVideo" size="1" maxlength="40" style="HEIGHT: 20px; WIDTH: 160px"  tabindex="5"/>
			</TD>
	        <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
	        <TD>
		        <myriap:select size='1' name='journal' property='localisation' style='HEIGHT: 20px; WIDTH: 140px' tabindex="11" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
		 		</myriap:select>
	        </TD>
           	<TD ALIGN="left">&nbsp;</TD>
   		</tr>
		<tr>
	    	<TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
	    	<TD ALIGN="left" >
	            <cardex:AutoCompleter name='journal' property="descriptif" tabindex="6" maxlength="80" 
	            style="HEIGHT: 20px; WIDTH: 140px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
	            height="150" width="150" nbrAmorce="2"/>
	     	</TD>
   			<td align="right" nowrap><b><bean:message key='v_co_temps_t'/></b></td>
   			<td>
	     		<myriap:text name='journal' property="duree" maxlength="4"  size="4"  tabindex="12"/>
		        <bean:message key='total_minutes_t'/>
   			</td>
         	<TD ALIGN="left">&nbsp;</TD>
   		</tr>
		<tr>
      			<td align="right" nowrap><b><bean:message key='d_do_date_debut_t'/></b></td>
      			<td nowrap>
	                  <cardex:DateHeure name='journal' property="dateDebut" calendrier="true" />
      			</td>
		 	<td align="right" nowrap><b><bean:message key='d_do_date_fin_t'/></b></td>
      			<td nowrap>
	                  <cardex:DateHeure name='journal' property="dateFin" calendrier="true" onClick="doClickDateFin();"/>
      			</td>

           		<TD ALIGN="left">&nbsp;</TD>
      		</tr>
		<tr>
      			<td align="right" colspan="5">&nbsp;</td>
      		</tr>
      		<tr>
      			<td  colspan="5"><b><bean:message key='v_ad_commentaire_t'/></b>
      			</td>
      		</tr>
      		<tr>
      			<td  colspan="5" align="center">      		
		            <html:textarea name="journal" property="description" rows='5' cols='90' style='font-family: Verdana, Arial; font-size: 8pt;'  tabindex="13"/>
                        </td>
      		</tr>
      		<tr>
      			<td colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
      		</tr>
      	</table>
      </td>
		</tr>

		<TR>
      <TD colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></TD>
    </TR>

    <TR>
      <TD ALIGN="center" valign="top" colspan="2">

        <TABLE width="645" cellpadding="2" cellspacing="0" border="0">
          <TR>
            <TD width="440" ALIGN="left">
                <logic:equal name='journal' property='modifiable' value='true' >
                  <cardex:button urlSecurite='<%=urlSecuriteSauvegarde %>' labelKey='cb_ok' style="width: 70px; text-align: center;" onclick='doOk();' />
                </logic:equal>
                <logic:equal name='journal' property='modifiable' value='false' >
                  <cardex:button labelKey='cb_ok' style="width: 70px; text-align: center;" disabled='true' />
                </logic:equal>
            </TD>
            <TD width="200" align="right">
              <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>


    <TR>
      <TD ALIGN="center" HEIGHT="15" colspan="2"><html:img page="/images/0061CFpixel.gif" height="1" width="645" border="0" /></TD>
    </TR>


    <TR>
      <TD ALIGN="center" colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></TD>
    </TR>

	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->

<html:hidden name='journal' property="cle" />
<html:hidden name='journal' property="site" />
<html:hidden name='journal' property="intervenant" />
<html:hidden name='journal' property="modifiable" />
<html:hidden name='journal' property="cleNarration" />
<html:hidden name='journal' property="siteNarration" />
<html:hidden name='journal' property="dateCreation" />
<html:hidden name='journal' property="numeroDossier.site" />
<html:hidden name='journal' property="numeroDossier.date" />
<html:hidden name='journal' property="numeroDossier.sequence" />

<%-- 
<logic:present name='journal' property="entiteCardexLiaison">
	<html:hidden name='journal' property="entiteCardexLiaison.cle" />
	<html:hidden name='journal' property="entiteCardexLiaison.site" />
</logic:present>
--%>
