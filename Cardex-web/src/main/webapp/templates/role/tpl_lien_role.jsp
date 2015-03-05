

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<table align="center" height="550" border="0" cellpadding="5" cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td align="center">

			  <table width="400" cellpadding="5" cellspacing="0" border="0"  bgcolor="#D0D0D0" class="tableOutline">
                            <tr>
                                    <td colspan="2" align="center" class="errorHeader" ><H4><bean:message key='tabpage_role'/></H4></td>
                            </tr>

                            <tr>
                                    <td colspan="2" align="center">

                                      <table width="390" height="90" border="0" cellpadding="5" cellspacing="0" bgcolor="eeeeee" class="tableCarved" >
                                        <tr>
                                          <td align="center"><br>
                                            <!-- TYPE OF AFFINITY SECTION -->
                                            <TABLE width="240" cellpadding="5" cellspacing="0" border="0" >
                                                <TR>
                                                <TD width="120" align="right"><b><bean:message key='i_ro_cle_t'/></b></TD>
                                                <TD width="120" align="left">
                                                    <html:hidden name='lien' property='cleSource' />
                                                    <html:hidden name='lien' property='cleDestination' />
                                                    <html:hidden name='lien' property='siteSource' />
                                                    <html:hidden name='lien' property='siteDestination' />
                                                    <html:hidden name='lien' property='typeLien' />
                									<bean:define id="siteRole" name='lien' property='siteString' type="String"/>
                									<logic:equal name="lien" property="typeLien" value="<%=GlobalConstants.LienRole.RELATION %>">
	                                                    <myriap:select name='lien' property="role" >
	                                                     	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=siteRole %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON_RELATION %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
	                                                    </myriap:select>&nbsp;
	                                                </logic:equal>
                									<logic:notEqual name="lien" property="typeLien" value="<%=GlobalConstants.LienRole.RELATION %>">
	                                                    <myriap:select name='lien' property="role" >
	                                                     	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=siteRole %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
	                                                    </myriap:select>&nbsp;
	                                                </logic:notEqual>
                                                </TD>
                                              </TR>
                                              <TR>
                                                <TD align="center" colspan="2">&nbsp;</TD>
                                              </TR>
                                            </TABLE>
                                            <!-- END AFFINITY -->

                                          </td>
                                        </tr>
                                      </table>
                              </td>
                            </tr>
                            <tr>
                                <TD align="right" ><INPUT type="button" value="&nbsp;&nbsp;&nbsp;Ok&nbsp;&nbsp;&nbsp;" onclick="soumettreForm();"/></TD>
                                <TD align="left"><INPUT type="button" value="Annuler" onClick="doClose();"/></TD>
                            </tr>
                      </table>
                    </td>
		</tr>
</table>
	<!-- END INTERFACE -->














