<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<SCRIPT language="JavaScript" type="text/javascript">

	function doSoumettreRafraichir() {
		soumettreActionMethod("rafraichir");
	}

	function doConsulterIntervenant(){
	  if(document.forms(0).site.value != ""){
	    if(consulterIntervenant.style.visibility == "visible"){
	       consulterIntervenant.style.visibility = "hidden";
	       document.forms(0).loupe2.src='<%=request.getContextPath()%>/images/magnify.gif';
	    }else{
	       consulterIntervenant.style.visibility = "visible";
	    }
	  }
	}
	
	function choisirIntervenant(cle) {
	//On place les valeurs choisies dans le champ Intervenant.
	   	document.forms(0).intervenant.value = cle;
	   	doConsulterIntervenant();
	}
	
</SCRIPT>
<bean:define id="site" name="rapportDossierActifIntervenant_CDX_0102" property="site" type="String"/>

<DIV align="center" STYLE="overflow:none; border='0.05cm none gray'; width:400; height:20;" style="z-index: 1; position: absolute; left: 240px; top: 150px; visibility= 'hidden'" id="consulterIntervenant">
	<select size='8' onchange="choisirIntervenant(this.value);" style="HEIGHT: 110px; WIDTH: 430px; background-color:beige">
  	  <cardex:optionTag classe='<%=GlobalConstants.CleListe.SECTEUR_INTERVENANT_PAR_SITE%>'
		valeurDiscriminant='<%=site%>'/>
	</select>          
</DIV>

<TABLE width="680" cellpadding="3" cellspacing="0" border="0"
	bgcolor="#ECECEC" class="tableOutline"
	style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#ACC8CC', startColorstr='#FFFFFF', gradientType='1');">
	<TR>
		<TD colspan="4"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br></TD>
    </TR> 
	<TR>
      <TD ALIGN="right"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <b><bean:message key='i_en_cle_t'/>&nbsp;</b></TD>
      <TD ALIGN="left"><html:img page="/images/blank.gif" width="1" height="1" border="0" /><br>
        <bean:define id="entite" name='rapportDossierActifIntervenant_CDX_0102' property="entite" type="String"/>
        <myriap:select name='rapportDossierActifIntervenant_CDX_0102' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 150px' tabindex="1" onchange='document.forms(0).genre.selectedIndex=0;doSoumettreRafraichir();' >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='rapportDossierActifIntervenant_CDX_0102' property='site' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" onchange='doSoumettreRafraichir();' >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
	      <TD ALIGN="left">
	      <bean:define id="genre" name='rapportDossierActifIntervenant_CDX_0102' property="genre" type="String"/>
	       <myriap:select size='1' name='rapportDossierActifIntervenant_CDX_0102' property='genre' style='HEIGHT: 20px; WIDTH: 150px' tabindex="3" >
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	      <TD>&nbsp;</TD>
	      <TD>&nbsp;</TD>
	</TR>
	
	<TR>
	     <td ALIGN="right"><b><bean:message key="v_sv_intervenant_t" />&nbsp;</b></td>
	     <td ALIGN="left" colspan="3">
	      <myriap:select size="1" name="rapportDossierActifIntervenant_CDX_0102" property="intervenant" style="HEIGHT: 20px; WIDTH: 500px" tabindex="4" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.INTERVENANT_PAR_SITE %>' 
				valeurDiscriminant='<%=site%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>'/>
	       </myriap:select>
	       <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe2" TITLE="Liste des groupes et des intervenants" border="1" height="14" width="14" onclick="doConsulterIntervenant();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'">
	     </td>
	</TR>


