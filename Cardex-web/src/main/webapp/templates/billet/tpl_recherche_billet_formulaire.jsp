<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>
<jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>

<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="650" border="0" cellpadding="0" cellspacing="0" class="tableOutline" >
     <tr>
	<td align="center" CLASS="tabBackground" >

  <table width="640" border="0" cellpadding="2" cellspacing="0"
style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
		<tr>
	      <td colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
	    </tr>

		<TR>
			<td witdh="10">&nbsp;
			</td>
			<td nowrap align="left"><b><bean:message key='nom.billet' /><bean:message
				key='2.points' /></b></td>
			<td><cardex:AutoCompleter name='rechercheBillets' property="nom" maxlength="50" 
	                style="HEIGHT: 20px; WIDTH: 140px" classeControl='<%= GlobalConstants.AutoCompleterClass.NOM_BILLET_AUTO_COMPLETER %>'
	                height="150" width="150" nbrAmorce="2"/>
			</td>
			<TD colspan="2" rowspan="2" valign="top">
        		<TABLE cellpadding="2" cellspacing="2" border="0" class="tableOutline">
        		<tr>
        			<td align="right" width="100"><b><u><bean:message key='billets.crees' /></u><bean:message key='2.points' /></b>
        			<td>
        				<table>
        					<tr><td nowrap align="right" width="60"><b><bean:message key='du_t' /></b>
								</td>
			        			<td width="100" align="right" nowrap>
									<cardex:Date name='rechercheBillets' property='dateDebutCreation' calendrier="true" nomProchainChamp="dateFinCreation"/>
								</td>
							</tr>
        					<tr><td nowrap align="right"><b><bean:message key='date_creation_fin_t' /></b>
        						</td>
        						<td width="100" align="right" nowrap>
									<cardex:Date name='rechercheBillets' property='dateFinCreation' calendrier="true" nomProchainChamp="numeroDetaillantProvenance"/>
								</td>
							</tr>
						</table>
					</td>	
				</tr>
				</table>				
			</td>			
		</TR>
		<TR>
			<td witdh="10">&nbsp;
			</td>
			<td nowrap align="left"><b><bean:message
				key='numero.control' /><bean:message key='2.points' /></b></td>
			<td><myriap:text name='rechercheBillets'
				property='numeroControl' size="30" maxlength='30' style="HEIGHT: 20px; WIDTH: 140px" /></td>
			<td nowrap align="right">&nbsp;</b>
			</td>
			<td width="150">&nbsp;
			</td>				
		</TR>
		<TR>
			<td witdh="10">&nbsp;
			</td>
			<td nowrap align="left"><b><bean:message key='valeur.billet' /><bean:message
				key='2.points' /></b></td>
			<td><cardex:argent name='rechercheBillets' property='valeur' maxlength='9'  style="HEIGHT: 20px; WIDTH: 50px" />$</td>
		</TR>
		<TR> 
			<td witdh="10">&nbsp;
			</td>
			<td  nowrap align="left"><b><bean:message key='type.mise' /><bean:message
				key='2.points' /></b></td>
			<td>
				<myriap:select name='rechercheBillets' property='typeMise' size='1' style='HEIGHT: 20px; WIDTH: 140px' >
				   <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache" valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_MISE %>'/>
				</myriap:select>
			</td>
			<TD nowrap="nowrap" align="left"><b><bean:message key='detaillant.validation' /><bean:message
				key='2.points' /></b></TD>
			<td><cardex:nombre name='rechercheBillets'
				property='numeroDetaillantValidation' size="6" maxlength='6' style="HEIGHT: 20px; WIDTH: 70px"/>
			</td>			
		</TR>
		<TR>
			<td witdh="10">&nbsp;
			</td>
			<td  nowrap align="left"><b><bean:message key='type.loterie' /><bean:message
				key='2.points' /></b></td>
			<td>
	          <myriap:select size='1' name='rechercheBillets' property="typeLoterie" style="HEIGHT: 20px; WIDTH: 140px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
	          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_LOTERIE%>'/>
	          </myriap:select> 
			</td>
			<TD nowrap="nowrap" align="left"><b><bean:message key='detaillant.verification' /><bean:message
				key='2.points' /></b></TD>
			<td><cardex:nombre name='rechercheBillets'
				property='numeroDetaillantVerification' size="6" maxlength='6' style="HEIGHT: 20px; WIDTH: 70px"/>
			</td>			
		</TR>
		<TR>
			<td witdh="10">&nbsp;
			</td>
			<td nowrap align="left"><b><bean:message key='montant.lot' /><bean:message
				key='2.points' /></b></td>
			<td><cardex:argent name='rechercheBillets' property='montantLot' maxlength='12' />$
			</td>
			<TD nowrap="nowrap" align="left"><b><bean:message key='detaillant.fautif' /><bean:message
				key='2.points' /></b></TD>
			<td><cardex:nombre name='rechercheBillets'
				property='numeroDetaillantFautif' size="6" maxlength='6' style="HEIGHT: 20px; WIDTH: 70px"/>
			</td>			
		</TR>
		<tr>
			<td witdh="10">&nbsp;
			</td>
			<td nowrap align="left"><b><bean:message key='date.paiement' /><bean:message key='2.points' /></b>
			</td>
     		<td width="100" align="left">
				<cardex:Date name='rechercheBillets' property='datePaiement' calendrier="true" nomProchainChamp="nom"/>
			</td>
			<td colspan="2">&nbsp;</td>
		</tr>

	    <TR>
	      <TD ALIGN="center" HEIGHT="15" colspan="5"><html:img page="/images/0061CFpixel.gif" height="1" width="640" border="0" /></TD>
	    </TR>


	    <TR>
	      <TD ALIGN="center" valign="top" colspan="5">
	
	        <TABLE width="640" cellpadding="2" cellspacing="0" border="0">
	          <TR>
		        <TD ALIGN="center" VALIGN="bottom" width="225"><b><bean:message key='affichage_resultats'/></b>
		           <html:select size='1' name='rechercheBillets' property='listeResultat.plageResultats' >
		              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
		           </html:select>
				</TD>	          
	            <TD width="125" ALIGN="left">&nbsp;
	              <cardex:button soumettre="/billet/soumettreRecherche.do" labelKey='cb_rechercher' />
	            </TD>
	            <TD width="200" align="right">
	              <cardex:button urlSecurite="/billet/soumettreRecherche.do" labelKey='cb_clear' onclick="soumettreActionMethod('rechercheDefaut');" />
	            </TD>
	            <TD width="100" align="right">
	              <cardex:button labelKey='cb_fermer'  onclick='windowClose();' />&nbsp;
	           </TD>
	          </TR>
	         </TABLE>
	
	      </TD>
	    </TR>

	
	
	    <TR>
	      <TD ALIGN="center" colspan="5">
	
	      </TD>
	    </TR>
	
	    <TR>
	      <TD ALIGN="center" colspan="5"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></TD>
	    </TR>

	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->