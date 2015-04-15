<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="sujetEntite" id="sujetEntite" classname="String"/>
<tiles:useAttribute name="urlSecuriteSauvegarde" id="urlSecuriteSauvegarde" classname="String"/>
<%-- urlSecurite = /sujet/update.do ou /sujet/save.do --%>

        <TR>
            <TD COLSPAN="7">
              <TABLE>
                <TR>
                   <TD nowrap>&nbsp;
				     <logic:equal name='sujet' property='indicateurRdd' value='true' >
				     	<STRONG><bean:message key='indicateur.rdd'/></STRONG>
				     </logic:equal>
				   </TD>
                   <TD align="center" width="100%" HEIGHT="20">
	                  <cardex:button urlSecurite='<%=urlSecuriteSauvegarde%>' labelKey='cb_ok_dossier' style="width: 120px; text-align: center;" onclick='doOk();' />
	               </TD>
	            </TR>
	          </TABLE>
            </TD>
         </TR>
     </TABLE>
     <TABLE width="900" cellpadding="3" cellspacing="0" border="0" style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#EEECE0', startColorstr='#FFFFFF', gradientType='0');" CLASS="tableOutline">
         <TR>   
            <TD colspan="3" valign="middle">
 				<logic:equal name='sujet' property='new' value='false' >
		 	         <cardex:button labelKey='cb_imprimer' securityConstraint='cardex.sujet.base.imprimer' style='width: 60px; text-align: center;' onclick='doPrint();' />&nbsp;
			         <cardex:button labelKey='cb_exporter' style='width: 60px; text-align: center;' onclick='doExporter();' urlSecurite="/sujet/printFiche.do" />
			    </logic:equal>
            </TD>
            <TD ALIGN="right" COLSPAN="4" valign="middle">
                <TABLE cellpadding="2" cellspacing="2" border="0">
                <TR>
                    <TD width="300" align="right">
		               <logic:equal name='sujet' property='new' value='false' >
					     <cardex:button securityConstraint="cardex.acces.selectAccesSujet" labelKey='cb_createur' style="width: 100px; text-align: center;" onclick='doAuditAcces();' />&nbsp;
					     <cardex:button securityConstraint="cardex.sujet.audit" labelKey='audit.changement' style="width: 130px; text-align: center;" onclick='doAuditChangement();' />&nbsp;
                         <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                        </logic:equal>
                        <logic:equal name='sujet' property='new' value='true' >
                            	<cardex:button labelKey='cb_fermer' onclick='doClose();' />
                        </logic:equal>
                    </TD>
                </TR>
                </TABLE>
            </TD>
        </TR>

