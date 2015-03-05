<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<tiles:useAttribute name="form" id="form" scope="request" classname="String"/>

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

<bean:define id="site" name='<%=form%>' property="site" type="String"/>
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
      <td nowrap="nowrap">
        <bean:define id="entite" name='<%=form%>' property="entite" type="String"/>
        <myriap:select name='<%=form%>' property='entite' size='1' style='HEIGHT: 20px; WIDTH: 170px' tabindex="1" onchange="document.forms(0).nature.value='';doSoumettreRafraichir();" >
	   		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
				valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      <TD ALIGN="right"><b><bean:message key='i_si_cle_t2'/>&nbsp;</b></TD>
      <TD ALIGN="left">
       <myriap:select size='1' name='<%=form%>' property='site' style='HEIGHT: 20px; WIDTH: 170px' tabindex="2" >
	      <cardex:optionTag classe='<%=GlobalConstants.CleListe.SITE_ORIGINE %>' 
			valeurDiscriminant='<%=entite%>'
			actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
        </myriap:select>
      </TD>
      
	</TR>
	
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/>&nbsp;</b></TD>
	      <td nowrap="nowrap">
	      <bean:define id="genre" name='<%=form%>' property="genre" type="String"/>
	        <myriap:select size='1' name='<%=form%>' property='genre' style='HEIGHT: 20px; WIDTH: 170px' tabindex="3" onchange="document.forms(0).type.value='';document.forms(0).categorie.value='';doSoumettreRafraichir();">
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
					valeurDiscriminant='<%=entite%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	      <TD ALIGN="right">
		  <b><bean:message key='origine_t'/>&nbsp;</b></TD>
	      <TD ALIGN="left">
		      <myriap:select size='1' name='<%=form%>' property='origine' style='HEIGHT: 20px; WIDTH: 170px' tabindex="22" onkeypress="typeAhead(this, event);" >
			  	  <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
		      </myriap:select>
	      </TD>
	</TR>
		
	<TR>
	      <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/>&nbsp;</b></TD>
	      <td nowrap="nowrap">
	       <bean:define id="nature" name='<%=form%>' property="nature" type="String"/>
	       <myriap:select size='1' name='<%=form%>' property='nature' style='HEIGHT: 20px; WIDTH: 170px' tabindex="5" onchange="document.forms(0).categorie.value='';doSoumettreRafraichir();">
		   		   <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
	                valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'
					valeurDiscriminant='<%=genre%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>
	      </TD>
	      <TD ALIGN="right"><b><bean:message key='i_or_cle_t'/></b></TD>
	      <TD ALIGN="left" >
	        <myriap:select size='1' name='<%=form%>' property='endroit' style='HEIGHT: 20px; WIDTH: 170px' tabindex="23" onkeypress="typeAhead(this, event);" >
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
		 	</myriap:select>
	      </TD>
	</TR>
	
	<TR>
         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
         <TD ALIGN="left">
			<bean:define id="type" name='<%=form%>' property="type" type="String"/>
	        <myriap:select size='1' name='<%=form%>' property='type' tabindex="4" style='HEIGHT: 20px; WIDTH: 170px' onchange='doSoumettreRafraichir();' >
				<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
				valeurDiscriminant='<%=nature%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	        </myriap:select>				
		</TD>
      <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
      <TD ALIGN="left" >
        <myriap:select size='1' name='<%=form%>' property='localisation' style='HEIGHT: 20px; WIDTH: 170px' tabindex="24" onkeypress="typeAhead(this, event);" >
				<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
	    </myriap:select>
      </TD>
    </TR>
	<tr>
	 	<TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
         <TD ALIGN="left">
         <myriap:select size='1' name='<%=form%>' property='categorie' tabindex="5" style='HEIGHT: 20px; WIDTH: 170px' >
			<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE%>'
				valeurDiscriminant='<%=type%>'
				actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
		 </myriap:select>	         
   		 </TD>
   		 
	      <TD ALIGN="right">
		  <b><bean:message key='c_do_fonde_t'/>&nbsp;</b></TD>
	      <TD ALIGN="left">
	          <myriap:select name='<%=form%>' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 170px" tabindex="4" >
	              <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
	          </myriap:select>
	      </TD>	      
   		 
	</tr>	

   	<tr>
  	   <td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
   	   <td>
	   	   <bean:define id="secteur" name='<%=form%>' property="secteur" type="String"/>
           <myriap:select name='<%=form%>' tabindex="2" property="secteur" size="1" style="HEIGHT: 20px; WIDTH: 170px" onchange='doSoumettreRafraichir();'  >
           	<cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
           </myriap:select>
       </td>
        <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
        <TD>
            <cardex:AutoCompleter name='<%=form%>' property="descriptif" tabindex="17" maxlength="80" 
            style="HEIGHT: 20px; WIDTH: 170px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
            height="150" width="150" nbrAmorce="2"/>
        </TD>
	</tr>

	<TR>
	     <td ALIGN="right"><b><bean:message key="v_sv_intervenant_t" />&nbsp;</b></td>
	     <td ALIGN="left" colspan="3">
	       <myriap:select size='1' name='<%=form%>' tabindex="4" property='intervenant' style='HEIGHT: 20px; WIDTH: 500px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
		   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	       </myriap:select>&nbsp;
	       <img src="<%=request.getContextPath()%>/images/magnify.gif" id="loupe2" TITLE="Liste des groupes et des intervenants" border="1" height="14" width="14" onclick="doConsulterIntervenant();" onmousedown="this.src='<%=request.getContextPath()%>/images/magnify2.gif'">
	     </td>
	</TR>

	<tr>
		<td align="right" ><b><bean:message key='v_do_ancienne_reference_t' /></b></td>
     	<td nowrap="nowrap" >
     		<myriap:text name='<%=form%>' property="numeroDossier" maxlength="20" size="20" style="HEIGHT: 20px; WIDTH: 160px"  tabindex="5" />
		</td>
		<td align="right" ><b><bean:message key='reference_t' /><bean:message key='2.points' /></b></td>
     	<td nowrap="nowrap" >
     		<myriap:text name='<%=form%>' property="reference" maxlength="20" size="20" style="HEIGHT: 20px; WIDTH: 170px"  tabindex="5" />
		</td>
	</tr>

	<tr>
		<td align="right" ><b><bean:message key='du_t' /></b></td>
     	<td nowrap="nowrap" >
			<cardex:Date name='<%=form%>' property='dateDebutDu' calendrier="true" nomProchainChamp="dateDebutAu"/>
		</td>
		<td align="right" ><b><bean:message key='date_creation_fin_t' /></b></td>
     	<td nowrap="nowrap" >
			<cardex:Date name='<%=form%>' property='dateDebutAu' calendrier="true" />
		</td>
	</tr>


	