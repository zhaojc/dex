<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.Globals" %>


<tiles:useAttribute name="soumettreURLSauvegarde" id="soumettreURLSauvegarde" classname="String"/>

<%
   //-- L'appel suivant génère une des chaînes suivante de caractères:  fr, en, sp, de, it, ...
   //-- et utilisé pour l'appel de fichiers localisés.  Gestion d'erreur en cas de timeout
   //-- de session.
   String var_lang = "fr";
   try{
     var_lang = ((Locale)request.getSession().getAttribute(Globals.LOCALE_KEY)).getLanguage();
   }
   catch (Throwable e) {}

%>

<jsp:include page='/scripts/sectionEscamotable.jsp' flush="true"/>
<jsp:include page='/scripts/autoCompleter.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
  var delai = 300 * 1000; //5 minutes
		
	function doCancel() {
	    window.location = '<%=request.getContextPath()%>/dossier/show.do?site=<bean:write name="evaluation" property="lienSite"/>&cle=<bean:write name="evaluation" property="lien"/>';
	}
	
	// Selectionner les types de jeux
	function selectMultiboxesjeuxChoisis(){
	
		try{
			var i = 0;
			while(true){
				eval("selectMultiboxesjeuxChoisis"+i+++"();");
			}
		} catch(e){}
	
	}

	function doSauvegardeImmediate() {
	    selectMultiboxesjeuxChoisis();
	    selectMultiboxesetatsChoisis();
	    selectMultiboxesproposChoisis();
	    unlockFields();
	    setTimeout("doVerificationSauvegarde()",delai);
	    soumettre('<%=request.getContextPath()%>/dossier/evaluation/sauvegarde.do');
	}

	function doVerificationSauvegarde() {
		alert("N'oubliez pas de sauvegarder régulièrement votre évaluation.");			
		setTimeout("doVerificationSauvegarde()",delai);
	}
  
  setTimeout("doVerificationSauvegarde()",delai);
  
</SCRIPT>

<input type="hidden" name="indexMise" value="">
<input type="hidden" name="indexFrequenceVisites" value="">

<TABLE width="650" cellpadding="3" cellspacing="0" border="0" class="tableCarved"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#85a91c7', startColorstr='#ffffff', gradientType='1');">
	<tr>
		<td colspan="2">
			<cardex:button labelKey='cb_sauvergarde_immediate'  onclick='doSauvegardeImmediate();' style="color: #000000; width: 150px; text-align: center" />
		</td>
	</tr>
	<tr>
		<td colspan="2"><html:img page="/images/blank.gif" width="1"
			height="1" border="0" /></td>
	</TR>
	<TR>
		<td colspan="2" align="left">
		<TABLE width="640" cellpadding="2" cellspacing="4" border="0">
			<TR>
				<td align="right" nowrap ><b><bean:message key='periode.evaluation' /><bean:message
					key='2.points' /></b></td>
				<td><b><bean:message key='du'/><bean:message key='2.points' /></b>
					<cardex:DateHeure name='evaluation' property='dateDebutEval' calendrier="true" nomProchainChamp="dateFinEval" />
				</td>
				<td nowrap colspan= "2"><b><bean:message key='au'/><bean:message key='2.points' /></b>
					<cardex:DateHeure name='evaluation' property='dateFinEval' calendrier="true" nomProchainChamp="numeroClientBijou" />
				</td>
			</TR>
			<TR>
				<td align="right" nowrap ><b><bean:message key='numero.client.bijou' /><bean:message
					key='2.points' /></b></td>
				<td colspan="3">
					<cardex:nombre name='evaluation'
							property='numeroClientBijou' size="30" maxlength='30' style="HEIGHT: 20px; WIDTH: 180px"/>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="4"><html:img
					page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
			</TR>
			
			<tr>
				<td align=right nowrap><b><bean:message key='type.jeu' /><bean:message
					key='2.points' /></b>
				</td>
				<td colspan="3">
			          <myriap:select size='1' name='evaluation' property="typeJeu" style="HEIGHT: 20px; WIDTH: 180px" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			          	<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>'/>
			          </myriap:select>
			          &nbsp;
			          <cardex:button labelKey='cb_ajouter' onclick='doAjoutMiseEvaluation();'/> 
				</td>
			</TR>
			<tr>
				<td colspan="4">
				<logic:iterate id='element' name='evaluation' property='misesEvaluation' indexId="index">
				<table width="100%" border="1"><tr><td>
				<table width="100%">
	        		<cardex:SectionEscamotableEnteteTR name='evaluation' property='<%="misesEvaluation["+index+"]"%>' sufixIdCible='<%=String.valueOf(index)%>' onMouseOver="this.className='listDetailEven'" onMouseOut="this.className='listDetailOdd'">
	        			<td class="listDetailOdd">
	        				<b><cardex:afficherValeurListeTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR%>' valeurTableValeur='<%=GlobalConstants.TableValeur.TYPE_JEU%>'name='element' property="jeuxForm.typeJeu" /></b>
	        			</td>
	        			<td align="right">
	        				<IMG src="<%=request.getContextPath()%>/images/supprimer.png" border="0" height="30" width="30" onclick='doRetraitMiseEvaluation("<%=index %>");' alt="<bean:message key='cb_supprimer'/>">
	        			</td>
					</cardex:SectionEscamotableEnteteTR>
					<cardex:SectionEscamotableContenuTR name='evaluation' property='<%="misesEvaluation["+index+"]"%>' sufixId='<%=String.valueOf(index)%>' colSpan="3">
						<TABLE width="100%" cellpadding="2" cellspacing="4" border="0">
						<tr>
						    <td>&nbsp;</td>
							<td colspan="3">
								<tiles:insert page="/commun/doubleListe.jsp" flush="false">
									<tiles:put name="formName" value="evaluation" />
									<tiles:put name="property" value='<%="misesEvaluation["+index+"].jeuxForm.doubleListe" %>' />
									<tiles:put name="strGaucheCol" value="st_listejeux_t" />
									<tiles:put name="strDroiteCol" value="st_jeuxchoisis_t" />
									<tiles:put name="listeGaucheLargeur" value="200" />
									<tiles:put name="listeDroiteLargeur" value="200" />
									<tiles:put name="listeHauteur" value="10" />
									<tiles:put name="idSelectMultiboxes" value='<%="jeuxChoisis"+index%>' />
								</tiles:insert>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4"><html:img
								page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
						</TR>
						<TR>
							<td align=right nowrap><b><bean:message key='mise' /><bean:message
								key='2.points' /></b>
							</td>
							<td colspan="3">
								<table class="tableCarved" width="100%">
								  <tr>
								     <td align="right"><b><bean:message key='minimum' /><bean:message key='2.points' /></b>
								     </td>
								     <td>
										<cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].miseMinimum"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" /> $
									</td>
							        <td align="right"><b><bean:message key='moyenne' /><bean:message key='2.points' /></b>
							        </td>
							        <td>
								        <cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].miseMoyenne"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" /> $
									</td>
									<td align="right"><b><bean:message key='maximum' /><bean:message key='2.points' /></b>
									</td>
									<td>
										<cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].miseMaximum"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" /> $
									</td>
								  </tr>
								  <TR>
									<td align="right" nowrap><b><bean:message key='gains.pertes' /><bean:message key='2.points' /></b></td>
									<td colspan="3"> 
										<cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].gainPerte"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" />
										&nbsp;<bean:message key='indication.perte'/>
									</td>
									<td align="right" nowrap><b><bean:message key='total' /><bean:message key='2.points' /></b></td>
									<td colspan="2"> 
										<cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].miseTotal"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" /> $
									</td>
								</TR>
							  </table>
							</td>
						</TR>
						<TR>
							<td align="right" nowrap><b><bean:message key='temps.jeu' /><bean:message
								key='2.points' /></b></td>
							<td><b><bean:message key='minimum' /><bean:message key='2.points' /></b>
									<cardex:nombre name='evaluation' property='<%="misesEvaluation["+index+"].tempsJeuMinimum"%>' size="6" maxlength='6'/>&nbsp;<bean:message key='total_minutes_t'/>
							</td>
							<td align="right"><b><bean:message key='maximum' /><bean:message key='2.points' /></b>
							</td>
							<td>
								<cardex:nombre name='evaluation' property='<%="misesEvaluation["+index+"].tempsJeuMaximum"%>' size="4" maxlength='4'/>&nbsp;<bean:message key='total_minutes_t'/>&nbsp;
								<b><bean:message key='total' /><bean:message key='2.points' /></b>
								<cardex:nombre name='evaluation' property='<%="misesEvaluation["+index+"].tempsJeuTotal"%>' size="8" maxlength='8'/>
							</td>
						</TR>
						<tr>
							<td align="left" colspan="4"><html:img page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
						</TR>
						<TR>
							<td align="right" nowrap><b><bean:message key='frequence.visites' /><bean:message
								key='2.points' /></b></td>
							<td colspan="3">
								<table  border="1" width="100%">
								    <tr>
								        <td align="center"><b><bean:message key='c_ve_annee' /> - <bean:message key='mois' /></b></td>
								        <td align="center"><b><bean:message key='nombre.fois' /></b></td>
								        <td align="center"><b><bean:message key='gains.pertes' /></b><br>&nbsp;<bean:message key='valeurs.entieres'/></b></td>
								        <td align="center">
								        	<IMG src="<%=request.getContextPath()%>/images/ajouter.png" border="0" height="30" width="30" onclick='<%="doAjoutFrequence("+index+");"%>' alt="<bean:message key='cb_ajouter'/>">
										</td>
								    </tr>
								  <logic:iterate id='element' name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites"%>' indexId="indexFrequencesVisites">
								    <html:hidden name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].cle"%>'/>
					           		<html:hidden name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].site"%>'/>
								    <html:hidden name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].lien"%>'/>
								    <html:hidden name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].lien"%>'/>
								    <tr>
								    	<td align="center">
											<myriap:select id='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].annee"%>' name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].annee"%>' size="1" style="HEIGHT: 20px; WIDTH: 60px">
												<cardex:optionTag classe='<%= GlobalConstants.CleListe.ANNEE %>'/>	
											</myriap:select>&nbsp; 
											<myriap:select id='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].mois"%>' name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].mois"%>' size="1" style="HEIGHT: 20px; WIDTH: 40px">
												<cardex:optionTag classe='<%= GlobalConstants.CleListe.MOIS %>'/>	
											</myriap:select>&nbsp; 
										</td>
								    	<td align="center">
											<myriap:text name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].nombreVisites"%>' maxlength="4"  size="4" />
										</td>
								    	<td align="center">
								    		<cardex:argent name='evaluation' property='<%="misesEvaluation["+index+"].frequencesVisites["+indexFrequencesVisites+"].gainPerte"%>' maxlength='12'  style="HEIGHT: 20px; WIDTH: 65px" />
										</td>
										<td align="center">
											<IMG src="<%=request.getContextPath()%>/images/supprimer.png" border="0" height="30" width="30" onclick='<%="doRetraitFrequence("+index+","+indexFrequencesVisites+");"%>' alt="<bean:message key='cb_supprimer'/>">
										</td>
									</tr>
								 </logic:iterate>
								</table>
							</td>
						</TR>
						<tr>
							<td align="left" colspan="4"><html:img
								page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
						</TR>						
						</table>
					</cardex:SectionEscamotableContenuTR>
				</table>
				</td></tr></table>
				</logic:iterate>
				</td>
			</tr>
			
			<tr>
				<td align="left" colspan="4"><html:img
					page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
			</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='faits.connus'/><bean:message key='2.points' /></b></td>
          		<td colspan="3">
                      <myriap:textarea name='evaluation' property='faitsConnus' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
                </td>
          	</TR>
			<TR>
				<td align="right" nowrap><b><bean:message key='proximite.casino' /><bean:message
					key='2.points' /></b></td>
				<td colspan="3"> 
					<myriap:textarea name='evaluation' property='proximite' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
				</td>
			</TR>
			<TR>
				<td align="right" nowrap><b><bean:message key='gradation.mises' /><bean:message
					key='2.points' /></b></td>
				<td colspan="3"> 
					<myriap:textarea name='evaluation' property='gradation' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
				</td>
			</TR>
			<TR>
				<td align="right" nowrap><b><bean:message key='frequence.transactions' /><bean:message
					key='2.points' /></b></td>
				<td colspan="3"> 
					<myriap:textarea name='evaluation' property='transaction' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
				</td>
			</TR>
			<tr>
				<td align="left" colspan="4"><html:img
					page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
			</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='etat.esprit'/><bean:message key='2.points' /></b></td>
				<td colspan="3">
					<tiles:insert page="/commun/doubleListe.jsp" flush="true">
						<tiles:put name="formName" value="evaluation" />
						<tiles:put name="property" value="doubleListeEtat" />
						<tiles:put name="strGaucheCol" value="etats.disponibles" />
						<tiles:put name="strDroiteCol" value="etats.choisis" />
						<tiles:put name="listeGaucheLargeur" value="200" />
						<tiles:put name="listeDroiteLargeur" value="200" />
						<tiles:put name="listeHauteur" value="6" />
						<tiles:put name="idSelectMultiboxes" value="etatsChoisis" />
					</tiles:insert>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
          		<td colspan="3">
                      <myriap:textarea name='evaluation' property='commentaireEtat'  rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
                </td>
          	</TR>
			<tr>
				<td align="left" colspan="4"><html:img
					page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
			</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='propos.client'/><bean:message key='2.points' /></b></td>
				<td colspan="3">
					<tiles:insert page="/commun/doubleListe.jsp" flush="true">
						<tiles:put name="formName" value="evaluation" />
						<tiles:put name="property" value="doubleListePropos" />
						<tiles:put name="strGaucheCol" value="propos.disponibles" />
						<tiles:put name="strDroiteCol" value="propos.choisis" />
						<tiles:put name="listeGaucheLargeur" value="200" />
						<tiles:put name="listeDroiteLargeur" value="200" />
						<tiles:put name="listeHauteur" value="6" />
						<tiles:put name="idSelectMultiboxes" value="proposChoisis" />
					</tiles:insert>
				</td>
			</tr>
            <TR>
          		<td>&nbsp;</td>
          		<td colspan="3">
                      <myriap:textarea name='evaluation' property='commentairePropos' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
                </td>
          	</TR>
			<tr>
				<td align="left" colspan="4"><html:img
					page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
			</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='informations.supplementaires'/><bean:message key='2.points' /></b></td>
          		<td colspan="3">
                      <myriap:textarea name='evaluation' property='commentaireAutre' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
                </td>
          	</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='signes.non.verbaux'/><bean:message key='2.points' /></b></td>
          		<td colspan="3">
                      <myriap:textarea name='evaluation' property='commentaireSigne' rows='2' cols='80'  style='font-family: Verdana, Arial; font-size: 9pt;' onkeypress='if (this.value.length >= 250) {this.value = this.value.substring(0,249);}' />
                </td>
          	</TR>
            <TR>
          		<td align="right" nowrap><b><bean:message key='evaluation.effectuee.par'/><bean:message key='2.points' /></b></td>
          		<td colspan="3">
		          <myriap:select name='evaluation' property="signataire1" size="1" style="HEIGHT: 20px; WIDTH: 430px">
             			<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
             	  </myriap:select>
                </td>
             </tr>
             <tr>
		        <td>&nbsp;</td>
		        <td colspan="3">
		          <myriap:select name='evaluation' property="signataire2" size="1" style="HEIGHT: 20px; WIDTH: 430px">
             			<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
             	  </myriap:select>
             	</td>
	   		 </tr>
			 <tr>
		        <td>&nbsp;</td>
		        <td colspan="3">
				  <myriap:select name='evaluation' property="signataire3" size="1" style="HEIGHT: 20px; WIDTH: 430px">
                		<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                  </myriap:select>
                </td>
	   		 </tr>
			 <tr>
		        <td>&nbsp;</td>
		        <td colspan="3">
		          <myriap:select name='evaluation' property="signataire4" size="1" style="HEIGHT: 20px; WIDTH: 430px">
       					<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
				  </myriap:select>
				</td>
	   		 </tr>
			 <tr>
		        <td>&nbsp;</td>
		        <td colspan="3">
		          <myriap:select name='evaluation' property="signataire5" size="1" style="HEIGHT: 20px; WIDTH: 430px">
       					<cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
				  </myriap:select>
				</td>
          	</TR>
			<TR>
				<td align="right" nowrap><b><bean:message key='evaluation.effectuee.le'/><bean:message
					key='2.points' /></b></td>
				<td colspan="3"><cardex:DateHeure name='evaluation' property='dateEvaluation' calendrier="true" nomProchainChamp="createur" />
				</td>
			</TR>
		    <tr>
				<td align="right" nowrap><b><bean:message key='evaluation.effectuee.par'/><bean:message
					key='2.points' /></b></td>
		        <td colspan="3">
		            <bean:write name="evaluation" property="createur"/>
                </td>
            </tr>
		    <tr>
				<td align="right" nowrap><b><bean:message key='enregistre.le'/><bean:message
					key='2.points' /></b></td>
		        <td colspan="3">
		            <bean:write name="evaluation" property="dateCreation"/>
                </td>
            </tr>
	  </TABLE>
	</td>
  </TR>
  <tr>
		<td align="left" colspan="2"><html:img
			page="/images/0061CFpixel.gif" height="1" width="100%" border="0" /></td>
  </TR>
  <tr>
    <td>
    	<cardex:button labelKey='cb_ok' onclick='doOk();' style="color: #000000; width: 70px; text-align: center" />
    </td>
    <td align="right">
        <cardex:button labelKey='cb_fermer'  onclick='doCancel();' style="color: #000000; width: 60px; text-align: center" />
    </td>
  </tr>
</TABLE>  

<!-- Lien entité -->
<html:hidden property="lien"/>
<html:hidden property="lienSite"/>
<html:hidden property="index"/>

