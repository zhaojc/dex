<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.EvaluationOngletTrieListe" %>
 
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

<!-- ------------------------------ -->
<DIV id="DATA_EVALUATIONS">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader">&nbsp;</TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="dossier" property="listeEvaluations" key='<%=EvaluationOngletTrieListe.CLE_PERIODE_DU%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="dossier" property="listeEvaluations" key='<%=EvaluationOngletTrieListe.CLE_PERIODE_AU%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="dossier" property="listeEvaluations" key='<%=EvaluationOngletTrieListe.CLE_DATE_EVALUATION%>' URLTrier="/dossier/trier.do" />
        </TD>
        <TD class="listTableHeader">
            <cardex:EnteteListeTriable name="dossier" property="listeEvaluations" key='<%=EvaluationOngletTrieListe.CLE_CREATEUR%>' URLTrier="/dossier/trier.do" />
        </TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
			<cardex:button labelKey="cb_ajouter" soumettre="/dossier/evaluation/create.do"/>
        </TD>
        <TD class="listDetailOdd" colspan="8">&nbsp;</TD>
    </TR>    
    <logic:iterate id="element" name="dossier" property='evaluations'>
    <TR>
        <TD class="listDetailOdd">
          <cardex:linkObject object='element' page='/dossier/evaluation/show.do'>
              <html:img page="/images/magnify.gif" altKey="cb_consulter" border="1" height="14" width="14" />
          </cardex:linkObject>
          <!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
              <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		  <cardex:linkObject onclick="return doConfirmLinkSuppression();" object='element' page='/dossier/evaluation/delete.do'>
		      <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		  </cardex:linkObject>
              </logic:equal>
        </TD>
        <TD class="listDetailOdd" nowrap>
   	        <bean:write name="element" property="dateDebutEval"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="dateFinEval" />
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="dateEvaluation"/>
        </TD>
        <TD class="listDetailOdd" nowrap>
	        <bean:write name="element" property="createurDescription"/>
        </TD>
    </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_folders division -->
