
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetEntite = "";
   String utilisateur = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
   	 utilisateur = sujetCardex.getCode();
     sujetEntite   = String.valueOf(sujetCardex.getEntite());
   }
   catch (Throwable e) {}

%>

<!-- Le preContexteApplicatif est le contexte avant le nouveau contexte applicatif -->
<tiles:useAttribute name="preContexteApplicatif" id="preContexteApplicatif" />
<tiles:useAttribute name="contexteApplicatif" id="contexteApplicatif" />

<!-- ------------------------------ -->
<DIV id="DATA_INDIVIDUALS">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NO_FICHE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE_AUTRES%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEVERITE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_NOM%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
	   		<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_PRENOM%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_SEXE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ETHNIE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_RACE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_LANGUE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>
	   <TD class="listTableHeader">
			<cardex:EnteteListeTriable name="sujet" property="listeSujets" key='<%=SujetOngletTrieListe.CLE_ROLE%>' URLTrier='<%=preContexteApplicatif.toString()+"/trier.do"%>' />
	   </TD>            
    </TR>
    <logic:greaterThan name="sujet" property="listeSujets.size" value="0">
    	<TR>
       		<TD colspan="12">
   	      		<b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='sujet' property="listeSujets.size"/></b>
       		</TD>
    	</TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="11" nowrap>
		<cardex:button labelKey="cb_lier" soumettre='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/sujet/search/show.do" %>'/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="sujet" property='sujets'>
            <TR>
                <TD class="listDetailOdd" nowrap>
		  <!-- On interdit la suppression du lien s'il ne s'agit pas de la même entité -->
				  <logic:equal name='element' property='entite' value='<%= sujetEntite %>' >
	                    <cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='sujet' sujet='element'  
	                    page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/sujet/delete.do"%>'>
	                        <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
	                    </cardex:linkLiaisonSujet>
                  </logic:equal>
          			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
          	        <logic:notEqual name='element' property='entite' value='<%= sujetEntite %>' >
          	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
	                    <cardex:linkLiaisonSujet onclick="return doConfirmLinkSuppression();" source='sujet' sujet='element' 
	                    page='<%=preContexteApplicatif.toString()+contexteApplicatif.toString()+"/sujet/delete.do"%>'>
						  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
					      </cardex:linkLiaisonSujet>
					   </logic:equal>
				    </logic:notEqual>
                </TD>
                <TD class="listDetailOdd" nowrap>
                    <cardex:linkSujet sujet='element' page='/sujet/showAcces.do'>
                        <bean:write name="element" property="numeroFiche"/>
                    </cardex:linkSujet>
                </TD>
                <TD class="severity<bean:write name="element" property="severiteDescriptionAutres"/>"
                    align="center" >&nbsp;<bean:write name="element" property="severiteDescriptionAutres"/></TD>
                <TD class="severity<bean:write name="element" property="severiteDescription"/>"
                    align="center" >&nbsp;<bean:write name="element" property="severiteDescription"/></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='nom' /></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='prenom' /></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='sexeDescription' /></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='ethnieDescription' /></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='raceDescription' /></TD>
                <TD class="listDetailOdd"><bean:write name='element' property='langueDescription' /></TD>
                <TD class="listDetailOdd"><bean:write name="element" property="roleDescription"/></TD>    
            </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_individuals division -->
