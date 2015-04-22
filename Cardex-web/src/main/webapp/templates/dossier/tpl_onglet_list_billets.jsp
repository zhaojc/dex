<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardexCommun.authentication.AuthenticationSubject" %>
<%@ page import="com.lotoquebec.cardexCommun.user.CardexUser" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.util.trierListeColumns.BilletOngletTrieListe" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>


<!-- Récupération du site auquel appartient l'utilisateur pour déterminer
     le droit de suppression dans l'onglet (permis seulement si le site est le même -->
<%
   String var_lang = "fr";
   String sujetSite = "";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
     AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
     CardexUser sujetCardex = (CardexUser)sujet.getUser();
     sujetSite   = String.valueOf(sujetCardex.getSite());
   }
   catch (Throwable e) {}

%>

<SCRIPT language="JavaScript" type="text/javascript" src="<%= request.getContextPath() %>/scripts/date_picker_<%= var_lang %>.js"></SCRIPT>

<SCRIPT language="JavaScript" type="text/javascript">
function doDatePaiement() {
   if(document.forms(0).paiement.value != ""){
      if ( confirmation("<bean:message key='inscrire.date.paiement' />") ){
      	soumettre("<%= request.getContextPath()%>/dossier/inscrire/datePaiement.do?cle=" + document.forms(0).cle.value + "&site=" + document.forms(0).site.value + "&datePaiement=" + document.forms(0).paiement.value);
      }
   }
}

</SCRIPT>


<!-- ------------------------------ -->

<HEAD>
<DIV id="DATA_BILLET">
    <TABLE width="900" cellPadding="3" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader" colspan="2" align="center">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_NUMERO_CONTROL%>' URLTrier="/dossier/trier.do"/>        
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_NOM_BILLET%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_MONTANT_LOT%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_PROVENANCE_BILLET%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_VALIDATION_BILLET%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_VERIFICATION_BILLET%>' URLTrier="/dossier/trier.do"/>
        </TD>
        <TD class="listTableHeader">
	        <cardex:EnteteListeTriable name='dossier' property="listeBillets" key='<%=BilletOngletTrieListe.CLE_PAIEMENT_BILLET%>' URLTrier="/dossier/trier.do"/>
        </TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap colspan="13">
            <logic:equal name='dossier' property='afficherBoutonAjouterBillet' value='true' >
				<cardex:button labelKey="cb_ajouter" soumettre="/dossier/billet/creer.do"/>
            </logic:equal>
            <logic:equal name='dossier' property='afficherBoutonAjouterBillet' value='false' >
				&nbsp;<b><bean:message key='date.paiement' /><bean:message key='2.points' /></b>
				<input type='text' name='paiement' onkeyup="doTraitsDateTAG(this, event.keyCode, '');" onkeydown="return isNumericDateTag(event.keyCode);" onfocusout="formatDate(this);" onchange="" onblur="" onClick="" value='' tabindex='' size='9' maxlength='10' /><a href="javascript:openDate('document.forms(0).paiement', document.forms(0).paiement.value);" onmousedown="setXY(event.x+120, event.y+400);"> <img src='/cardex/images/cal.gif' border='0'/></a>&nbsp;
				<cardex:button urlSecurite="/dossier/inscrire/datePaiement.do" labelKey='cb_ok' style="width: 80px; text-align: center;" onclick='doDatePaiement();' />
            </logic:equal>
        </TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='billets'>
    <TR>
        <TD class="listDetailOdd" nowrap>&nbsp;
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
       		<!-- On interdit la suppression du lien s'il ne s'agit pas du même site -->
       	        <logic:equal name='element' property='site' value='<%= sujetSite %>' >
		      <cardex:linkObject onclick="return doConfirmLinkSuppression();"  object='element' page='/dossier/billet/supprimer.do'>
			  <html:img page="/images/trash.gif" altKey="cb_supprimer" border="1" height="14" width="14" />
		      </cardex:linkObject>
		</logic:equal>
            </logic:notEqual>
        </TD>
        <TD class="listDetailOdd" nowrap>&nbsp;
          <cardex:linkObject object='element' page='/dossier/billet/consulter.do'>
              <bean:write name="element" property="numeroControl"/>
          </cardex:linkObject>        
        </TD>
        <TD class="listDetailOdd" align="center">&nbsp;
        	<bean:write name="element" property="nom"/>
        </TD>
        <TD class="listDetailOdd" nowrap align="center">&nbsp;
          <bean:write name="element" property="montantLot"/>&nbsp;$
        </TD>
        <TD class="listDetailOdd" align="center">&nbsp;
          <bean:write name="element" property="provenanceBillet"/>
        </TD>
        <TD class="listDetailOdd" align="center">&nbsp;
          <bean:write name="element" property="validationBillet"/>        
        </TD>
        <TD class="listDetailOdd" align="center">&nbsp;
          <bean:write name="element" property="verificationBillet"/>        
        </TD>
        <TD class="listDetailOdd" align="center">&nbsp;
          <bean:write name="element" property="datePaiement"/>        
        </TD>
    </TR>
    </logic:iterate>

    </TABLE>
</DIV>
<!-- End data_follow_up division -->
