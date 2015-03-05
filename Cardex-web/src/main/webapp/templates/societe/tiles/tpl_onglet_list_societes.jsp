
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   String utilisateur = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     utilisateur = sujetCardex.getCode();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<!-- ------------------------------ -->
<DIV id="DATA_SOCIETIES">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NO_FICHE%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_SEVERITE%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_RAISON_SOCIALE%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NOM%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_CLASSE%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_ROLE%>' URLTrier='<%=preContexteApplicatif.toString()+"/societe/trier.do"%>' />
        </TD>
    </TR>
    <logic:greaterThan name="sujet" property="listeSocietes.size" value="0">
    <TR>
       <TD colspan="8">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='sujet' property="listeSocietes.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="7" nowrap>
			<cardex:button labelKey="cb_lier" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/search/show.do"%>'/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="sujet" property='societes'>
        <TR>
           <TD class="listDetailOdd" nowrap>
	          	<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
	          	<logic:equal name='element' property='site' value='<%= sujetSite %>' >
					<cardex:linkLiaisonSociete onclick="return doConfirmLinkSuppression();"  source='sujet' societe='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/delete.do"%>'>
					    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
					</cardex:linkLiaisonSociete>
                </logic:equal>
      			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
      	        <logic:notEqual name='element' property='site' value='<%= sujetSite %>' >
      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
					<cardex:linkLiaisonSociete onclick="return doConfirmLinkSuppression();"  source='sujet' societe='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/societe/delete.do"%>'>
                         <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
                      </cardex:linkLiaisonSociete>
				   </logic:equal>
			    </logic:notEqual>
           </TD>
           <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroFiche"/>&nbsp;</TD>
           <TD class="severity<bean:write name="element" property="severiteDescription"/>"
             align="center" ><bean:write name="element" property="severiteDescription"/></TD>
           <TD class="listDetailOdd"><bean:write name='element' property='raisonEtre' />&nbsp;</TD>
           <TD class="listDetailOdd">
                <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                    <bean:write name='element' property='nom' />
                </cardex:linkSociete>
           </TD>
           <TD class="listDetailOdd"><bean:write  name='element' property='classeDescription' />&nbsp;</TD>
           <TD class="listDetailOdd"><bean:write  name='element' property='roleDescription' />&nbsp;</TD>           
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_societies division -->