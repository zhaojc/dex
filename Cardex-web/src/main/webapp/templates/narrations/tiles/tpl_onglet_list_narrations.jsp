

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationOngletTrieListe" %>

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

<!-- ---------------------------- -->
<DIV id="DATA_NARRATION">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" align="center"><html:img page="/images/check.gif"  border="1" height="14" width="14" /></TD>
        <TD class="listTableHeader">&nbsp;</TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_SITE%>' URLTrier="/dossier/trier.do" />
	   </TD>        
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_DATE_CREATION%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_INTERVENANT%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_MONTANT%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_CONFIDENTIALITE%>' URLTrier="/dossier/trier.do" />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name='sujet' property="listeNarrations" key='<%=NarrationOngletTrieListe.CLE_COMMENTAIRE%>' URLTrier="/dossier/trier.do" />
	   </TD>	   
    </TR>
    <logic:greaterThan name="sujet" property="listeNarrations.size" value="0">
	    <TR>
	       <TD colspan="9">
	   	      <b><bean:message key='st_rowcount_t3'/>&nbsp;<bean:write name='sujet' property="listeNarrations.size"/></b>
	       </TD>
	    </TR>
    </logic:greaterThan>
 
<cardex:securityDefineTag nameDefine="sectionNarration" securityConstraint="cardex.sujet.narrations.onglet">

    <TR>
        <TD class="listDetailOdd" nowrap width="50">
			<cardex:button labelKey="cb_ajouter" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/narration/create.do"%>'/>
        </TD>
        <TD class="listDetailOdd" colspan="8">&nbsp;</TD>
    </TR>
    <logic:iterate id="element" name="sujet" property='narrations'>
    <TR>
        <TD class="listDetailOdd" nowrap align="center">
          <logic:equal name="element" property="approuve" value='true'>
              <html:img page="/images/check.gif"  border="0" height="14" width="14" />
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" width="35">
          <cardex:linkObject object='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/narration/show.do"%>'>
              <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
          </cardex:linkObject>
          <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
          <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		<cardex:linkObject onclick="return doConfirmLinkSuppression();" object='element' page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/narration/delete.do"%>'>
		    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		</cardex:linkObject>
          </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="siteDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="dateCreation"/>
        </TD>
        <TD class="listDetailOdd">
        	<bean:write name="element" property="createurDescription"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
          <bean:write name="element" property="montant"/>
        </TD>
        <TD class="listDetailOdd" nowrap align="center">
        	<bean:write name="element" property="confidentialiteNarrationDescription"/>
        </TD>
        <TD class="listDetailOdd" width="75">
          <myriap:textarea name="element" property="narrationSansFormat" rows='3' cols='20' style='font-family: Verdana, Arial; font-size: 8pt;' />
        </TD>
    </TR>
    </logic:iterate>
  
</cardex:securityDefineTag>
    
    </TABLE>
</DIV>
<!-- End data_narration division -->