<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "sociétés".
Description : Module qui affiche le contenu de l'onglet "sociétés", soit une
              liste de sociétés.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.7 $, $Date: 2002/04/04 20:40:15 $

History     : Voir ci-dessous.

$Revision: 1.7 $, $Date: 2002/04/04 20:40:15 $, $Author: mlibersan $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe" %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="org.apache.struts.Globals" %>

<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String sujetSite = "";
   String utilisateur = "";
   String token = "";
   try{
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     utilisateur = sujetCardex.getCode();
     sujetSite   = String.valueOf(sujetCardex.getSite());
     token = (String)request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
   }
   catch (Throwable e) {}

%>

<!-- ------------------------------ -->
<DIV id="DATA_SOCIETIES">
    <TABLE width="772" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>    
       <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="societe" propertyFormulaire='numeroFiche' >
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NO_FICHE%>' URLTrier="/societe/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="societe" propertyFormulaire='severite' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_SEVERITE%>' URLTrier="/societe/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="societe" propertyFormulaire='severiteCasino' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/societe/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="raisonEtreSecurite" nomFormulaire="societe" propertyFormulaire='raisonEtre' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_RAISON_SOCIALE%>' URLTrier="/societe/trier.do" />
        </TD>
   	  </cardex:securityDefineTag>
      <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="societe" propertyFormulaire='nom' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NOM%>' URLTrier="/societe/trier.do" />
        </TD>
	  </cardex:securityDefineTag>
	  <cardex:securityDefineTag nameDefine="classeSecurite" nomFormulaire="societe" propertyFormulaire='classe' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_CLASSE%>' URLTrier="/societe/trier.do" />
        </TD>
	  </cardex:securityDefineTag>
	  <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="societe" propertyFormulaire='lienCreateur' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/societe/trier.do" />
        </TD>        
      </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="societe" propertyFormulaire='lienDateCreation' >
		   <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="societe" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/societe/trier.do" />
		   </TD>
	   </cardex:securityDefineTag>
    </TR> 
    <logic:greaterThan name="societe" property="listeSocietes.size" value="0">
    <TR>
       <TD colspan="10">
   	      <b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='societe' property="listeSocietes.size"/></b>
       </TD>
    </TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="7" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/societe/societe/search/show.do"/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="societe" property='societes' indexId="index">
        <TR>
           <TD class="listDetailOdd" nowrap>
		      <!-- On interdit la suppression du lien s'il s'agit d'un détaillant RDD lié à un détaillant RDD -->
              <logic:equal name='element' property='permettreSuppressionLiaison' value='true' >
					<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
					<logic:equal name='societe' property='site' value='<%= sujetSite %>' >
						<cardex:linkLiaisonSociete onclick="return doConfirmLinkSuppression();"  source='societe' societe='element' page='/societe/societe/delete.do'>
						    <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
						</cardex:linkLiaisonSociete>
					</logic:equal>
	      			<!-- On permet cependant la suppression si le créateur du lien est l'utilisateur -->
	      	        <logic:notEqual name='societe' property='site' value='<%= sujetSite %>' >
	      	           <logic:equal name='element' property='lienCreateur' value='<%= utilisateur %>' >
						<cardex:linkLiaisonSociete onclick="return doConfirmLinkSuppression();"  source='societe' societe='element' page='/societe/societe/delete.do'>
	                         <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
	                      </cardex:linkLiaisonSociete>
					   </logic:equal>
				    </logic:notEqual>
			  </logic:equal>
           </TD>
          <logic:equal name="numeroFicheSecuriteHidden" value="false">
           <TD class="listDetailOdd" nowrap><bean:write name="element" property="numeroFiche"/>&nbsp;</TD>
          </logic:equal>
          <logic:equal name="severiteSecuriteHidden" value="false">           
           <TD class="severity<bean:write name="element" property="severiteDescription"/>"
             align="center" ><bean:write name="element" property="severiteDescription"/></TD>
          </logic:equal>
          <logic:equal name="severiteCasinoSecuriteHidden" value="false">           
           <TD class="severity<bean:write name="element" property="severiteCasinoDescription"/>"
             align="center" ><bean:write name="element" property="severiteCasinoDescription"/></TD>
          </logic:equal>
          <logic:equal name="raisonEtreSecuriteHidden" value="false">             
           <TD class="listDetailOdd"><bean:write name='element' property='raisonEtre' />&nbsp;</TD>
          </logic:equal>
          <logic:equal name="nomSecuriteHidden" value="false">           
           <TD class="listDetailOdd">
                <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                    <bean:write name='element' property='nom' />
                </cardex:linkSociete>
           </TD>
          </logic:equal>
          <logic:equal name="classeSecuriteHidden" value="false">           
           <TD class="listDetailOdd"><bean:write  name='element' property='classeDescription' />&nbsp;</TD>
          </logic:equal>
          <logic:equal name="lienCreateurSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="lienCreateur"/></TD>
		  </logic:equal>           
		     <logic:equal name="lienDateCreationSecuriteHidden" value="false">
	             <TD class="listDetailOdd" ><bean:write name='element' property='lienDateCreation' /></TD>
	         </logic:equal>
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_societies division -->