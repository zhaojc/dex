<%-- --------------------------------------------------------------------------
Use case    : Recherche des services d'urgence.
Description : Module d'affichage représentant le formulaire de recherche des
              services d'urgence ambulanciers.
Author(s)   : $Author: mazzucr $
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean'%>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html'%>
<%@ taglib uri='/WEB-INF/cardex-html.tld' prefix='cardex'%>
<%@ taglib uri='/WEB-INF/myriap-html.tld' prefix='myriap'%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles'%>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants"%>

<tiles:useAttribute name="urlSecuriteRecherche"
	id="urlSecuriteRecherche" classname="String" />

<TABLE width="870" cellpadding="0" cellspacing="0" border="0"
	class="tableOutline">
	<TR>
		<TD CLASS="tabBackground">
		<TABLE width="870" cellpadding="2" cellspacing="0" border="0">
			<TR>
				<TD ALIGN="middle" COLSPAN="5"><html:img
					page="/images/blank.gif" width="766" height="1" border="0" /></TD>
			</TR>
			<TR>
				<td width="10">&nbsp;</td>
				<td align="right" nowrap><b><bean:message key='societe_t' /></b></td>
				<td colspan="3">
			          <bean:define id="classe" 
			                       name="rechercheUrgence" 
			                       property="classe" 
			                       type="String"/>
			          <myriap:select size='1' 
			                         name='rechercheUrgence' 
			                         property="lienSociete" 
			                         tabindex="1" 
			                         style="HEIGHT: 20px; WIDTH: 280px" 
			                         onkeypress="typeAhead(this, event);" 
			                         onfocus="resetIncrementalSearch();" >
			          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.SOCIETE_PAR_CLASSE%>' 
			          	valeurDiscriminant='<%=classe%>'/>
			          </myriap:select> 
				</td>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='l_vi_cle_t'/>&nbsp;</b></TD>
	            <TD ALIGN="left" >
	                <myriap:text name='rechercheUrgence' 
	                             property="ville"
	                             onchange='this.value=this.value.toUpperCase();' 
	                             tabindex="2" 
	                             maxlength="50" 
	                             style="HEIGHT: 20px; WIDTH: 150px" />
	            </TD>
				<TD ALIGN="right" nowrap><b><bean:message key='contact'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left">
	                <myriap:text name='rechercheUrgence' 
	                             property="contact"
	                             onchange='this.value=this.value.toUpperCase();' 
	                             tabindex="3" 
	                             maxlength="50" 
	                             style="HEIGHT: 20px; WIDTH: 250px" />
	            </TD>
			</TR>
			<TR>
				<td width="10">&nbsp;
				</td>
	            <TD ALIGN="right"><b><bean:message key='motif'/><bean:message key='2.points'/></b></TD>
	            <TD ALIGN="left" >
			          <myriap:select size='1' 
			                         name='rechercheUrgence' 
			                         property="motif" 
			                         tabindex="4" 
			                         style="HEIGHT: 20px; WIDTH: 150px" 
			                         onkeypress="typeAhead(this, event);" 
			                         onfocus="resetIncrementalSearch();" >
		                  	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
							valeurTableValeur='<%=GlobalConstants.TableValeur.MOTIF%>' />
			          </myriap:select> 
	            </TD>
	            <TD ALIGN="right"><b><bean:message key='i_st_cle_t'/></b></TD>
	            <TD ALIGN="left" >
	                   <myriap:select name='rechercheUrgence' 
	                                  property="statut" 
	                                  tabindex="5" 
	                                  size="1" 
	                                  style="HEIGHT: 20px; WIDTH: 250px">
	                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.STATUT %>' 
	                      valeurDiscriminant='<%=GlobalConstants.ListeCache.Statut.URGENCE %>'/>
	                   </myriap:select>
	            </TD>
			</TR>
			<TR>
				<TD WIDTH="970" ALIGN="center" COLSPAN="6" HEIGHT="15"><html:img
					page="/images/0061CFpixel.gif" width="100%" height="1" border="0" /></TD>
			</TR>
			<TR>
				<TD WIDTH="970" ALIGN="center" COLSPAN="7"><!-- e"Sort by" options listing -->
				<TABLE width="970" align="left" cellPadding="2" cellSpacing="0"
					border="0" class="tabTitleSmallGrayed">
					<tr>
						<TD ALIGN="left"><b><bean:message
							key='affichage_resultats' /></b> <html:select size='1'
							name='rechercheUrgence' property='listeResultat.plageResultats'>
							<cardex:optionTag
								classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
						</html:select></TD>
						<TD valign="top"><cardex:button labelKey='cb_rechercher'
							soumettre="/urgence/search.do" /></TD>
						<TD></TD>
						<TD</TD>
						<TD align="right"><cardex:button labelKey='cb_clear'
							soumettre="/urgence/search/reset/default.do" /> <cardex:button
							labelKey='cb_fermer' onclick='doClose();' /></TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="middle" COLSPAN="7"><html:img
					page="/images/blank.gif" width="1" height="1" border="0" /></TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>