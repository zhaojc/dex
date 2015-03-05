<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "sociétés".
Description : Module qui affiche le contenu de l'onglet "sociétés", soit une
              liste de sociétés.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.7 $, $Date: 2002/04/11 18:20:50 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.7 $, $Date: 2002/04/11 18:20:50 $, $Author: mlibersan $
Derniers commentaires à jour.
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

<SCRIPT language='JavaScript' type='text/javascript'>
function doSauvegardeLienSociete(index,lien,lienSite) {
	var role = document.getElementById("societes["+index+"].role");
	var cleSujet = document.forms(0).cle.value;
	var siteSujet = document.forms(0).site.value;
//alert(index + '- ' + cleDossier + '- ' + siteDossier + ' ' + role.value);
//On passe ces valeurs en paramètre, car des erreurs de "submit" peuvent se produire si plus d'un dossier est ouvert.
	if((role.value != "") && (role.value != "0")){ //On s'assure qu'il y a un rôle
		post('<%= request.getContextPath() + "/sujet/link/update.do?lien="%>'+lien+'&lienSite='+lienSite+'&role='+role.value+'&cleSujet='+cleSujet+'&siteSujet='+siteSujet+'&<%=Globals.TOKEN_KEY+"="+token%>');
	}else{
		alert("<bean:message key='choix.role.update'/>");
	}
	//document.forms(0).action='<%= request.getContextPath() + "/sujet/link/update.do"%>';
	//document.forms(0).submit();
}
</SCRIPT>

<!-- ------------------------------ -->
<DIV id="DATA_SOCIETIES">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
      <cardex:securityDefineTag nameDefine="nomSecurite" nomFormulaire="societe" propertyFormulaire='nom' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NOM%>' URLTrier="/sujet/trier.do" />
        </TD>
	  </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="numeroFicheSecurite" nomFormulaire="societe" propertyFormulaire='numeroFiche' >
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_NO_FICHE%>' URLTrier="/sujet/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteSecurite" nomFormulaire="societe" propertyFormulaire='severite' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_SEVERITE%>' URLTrier="/sujet/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="severiteCasinoSecurite" nomFormulaire="societe" propertyFormulaire='severiteCasino' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_SEVERITE_CASINO%>' URLTrier="/sujet/trier.do" />
        </TD>
	   </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="raisonEtreSecurite" nomFormulaire="societe" propertyFormulaire='raisonEtre' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_RAISON_SOCIALE%>' URLTrier="/sujet/trier.do" />
        </TD>
   	  </cardex:securityDefineTag>
	  <cardex:securityDefineTag nameDefine="classeSecurite" nomFormulaire="societe" propertyFormulaire='classe' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_CLASSE%>' URLTrier="/sujet/trier.do" />
        </TD>
	  </cardex:securityDefineTag>
	  <cardex:securityDefineTag nameDefine="roleSecurite" nomFormulaire="societe" propertyFormulaire='role' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_ROLE%>' URLTrier="/sujet/trier.do" />
        </TD>
	  </cardex:securityDefineTag>
	  <cardex:securityDefineTag nameDefine="lienCreateurSecurite" nomFormulaire="societe" propertyFormulaire='lienCreateur' >        
        <TD class="listTableHeader">
        	<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_LIEN_CREATEUR%>' URLTrier="/sujet/trier.do" />
        </TD>
      </cardex:securityDefineTag>
	   <cardex:securityDefineTag nameDefine="lienDateCreationSecurite" nomFormulaire="societe" propertyFormulaire='lienDateCreation' >
		   <TD class="listTableHeader" >
				<cardex:EnteteListeTriable name="sujet" property="listeSocietes" key='<%=SocieteOngletTrieListe.CLE_DATE_LIAISON%>' URLTrier="/sujet/trier.do" />			   
		   </TD>
	   </cardex:securityDefineTag>
    </TR>
    <logic:greaterThan name="sujet" property="listeSocietes.size" value="0">
    	<TR>
       		<TD colspan="10">
   	      		<b><bean:message key='st_rowcount_t4'/>&nbsp;<bean:write name='sujet' property="listeSocietes.size"/></b>
       		</TD>
    	</TR>
    </logic:greaterThan>
    <TR>
        <TD class="listDetailOdd" colspan="8" nowrap>
			<cardex:button labelKey="cb_lier" style="width: 50px; text-align: center;" soumettre="/sujet/societe/search/show.do"/>
        </TD>
    </TR>    
    <logic:iterate id="element" name="sujet" property='societes' indexId="index">
        <logic:equal name='element' property='audit' value='true' > 
           <TR style="background-color='#EEEEEE'">
        </logic:equal>
        <logic:notEqual name='element' property='audit' value='true' > 
           <TR>
        </logic:notEqual>
           <TD class="listDetailOdd" nowrap>
		      <!-- On interdit la suppression du lien s'il s'agit d'un détaillant RDD -->
              <logic:equal name='element' property='permettreSuppressionLiaison' value='true' >
				  <cardex:linkLiaisonSociete onclick="return doConfirmLinkSuppression();"  source='sujet' societe='element' page='/sujet/societe/delete.do'>
				      <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
				  </cardex:linkLiaisonSociete>
			 </logic:equal>
           </TD>
          <logic:equal name="nomSecuriteHidden" value="false">
           <TD class="listDetailOdd">
                <cardex:linkSociete societe='element' page='/societe/showAcces.do'>
                    <bean:write name='element' property='nom' />
                </cardex:linkSociete>
           </TD>
          </logic:equal>
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
          <logic:equal name="classeSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write  name='element' property='classeDescription' />&nbsp;</TD>
          </logic:equal>
          <logic:equal name="roleSecuriteHidden" value="false">
	          <TD class="listDetailOdd">&nbsp;
                <logic:equal name='element' property='permettreSuppressionLiaison' value='true' >
	                <logic:notEqual name='element' property='audit' value='true' >
	                    <bean:define id="societeLien" name='sujet' property='<%="societes["+index+"].lien"%>'/>
                   		<bean:define id="societeLienSite" name='sujet' property='<%="societes["+index+"].lienSite"%>' type="String"/>
			            <myriap:select id='<%="societes["+index+"].role"%>' name='element' property='role' style='HEIGHT: 20px; WIDTH: 120px'>
						   <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' valeurDiscriminant='<%=societeLienSite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ROLE_LIAISON %>' actionSecurite='<%=GlobalConstants.ActionSecurite.TOUTES_ACTIONS %>'/>
			            </myriap:select>     &nbsp;
						<cardex:securityDefineTag nameDefine="societeSujetSauvegarder" urlSecurite="/sujet/link/update.do">
							<IMG onclick="doSauvegardeLienSociete(<%= index %>,<%=societeLien%>,<%=societeLienSite%>);" src="<%=request.getContextPath()%>/images/sauvegarde.gif" altKey="cb_ok" border="1" height="14" width="14" />
						</cardex:securityDefineTag>
					</logic:notEqual>
				</logic:equal>
                <logic:equal name='element' property='permettreSuppressionLiaison' value='false' >
			           <cardex:afficherValeurListeTag name='sujet' property='<%="societes["+index+"].role"%>' classe='<%= GlobalConstants.CleListe.ROLE %>' />
			    </logic:equal>
			  </TD>
	      </logic:equal>
          <logic:equal name="lienCreateurSecuriteHidden" value="false">
           <TD class="listDetailOdd"><bean:write name="element" property="lienCreateur"/></TD>
          </logic:equal>
		   <logic:equal name="lienDateCreationSecuriteHidden" value="false">
	           <TD class="listDetailOdd" align="center"><bean:write name='element' property='lienDateCreation' /></TD>
	       </logic:equal>
        </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_societies division -->