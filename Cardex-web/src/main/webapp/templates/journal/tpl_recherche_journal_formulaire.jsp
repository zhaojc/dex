<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
 


<!-- THIS TABLE FOR OUTLINE PURPOSES ONLY -->
<table width="950" border="0" cellpadding="0" cellspacing="0" class="tableOutline" >
     <tr>
	<td align="center" CLASS="tabBackground" style='background-image: url("<%= request.getContextPath()%>/images/background_journal.jpg");' >

  <table width="940" border="0" cellpadding="0" cellspacing="5">
		<tr>
	      <td colspan="2"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
	    </tr>

		<td align="center" colspan="2">
	  <!-- START TAB CONTENT -->
	    <table width="930" cellpadding="2" cellspacing="5" border="0" class="tableOutline">
      	  <tr>
      			<td colspan="7"><html:img page="/images/blank.gif" height="1" width="1" border="0" /></td>
      	  </tr>

      	  <tr>
      	 	<TD ALIGN="right"><b><bean:message key='i_en_cle_t'/></b>
        	</TD>
      		<TD ALIGN="left">
      		  	<bean:define id="entite" name='rechercheJournal' property="entite" type="String"/>
      		  	<myriap:select name='rechercheJournal' tabindex="1" property='entite' size='1' style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
		   		  <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.ENTITE%>' 
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
      		  	</myriap:select>
      		</TD>
	        <TD ALIGN="right" nowrap><b><bean:message key='i_si_cle_t2'/></b></TD>
	        <TD ALIGN="left">
			    <myriap:select tabindex="7" size='1' name='rechercheJournal' property='site' style='HEIGHT: 20px; WIDTH: 140px'  onchange='doSoumettreRafraichir();'>
		            <cardex:optionTag classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>' 
						valeurTableValeur='<%=GlobalConstants.TableValeur.SITE%>' 
						valeurDiscriminant='<%=entite%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
	            </myriap:select>
	        </TD>
			<TD align="right"><b><bean:message key='v_do_ancienne_reference_t' /></b>
			</TD>
			<TD>
	     		<myriap:text name='rechercheJournal' property="numeroDossier" maxlength="20" size="20" style="HEIGHT: 20px; WIDTH: 160px"  tabindex="14" />
			</td>
	  	    <td>&nbsp;</td>
   		</tr>

   		<tr>
       	   <td align="right" nowrap><b><bean:message key='l_sv_po_assigne_t'/></b></td>
       	   <td> 
                 <myriap:select name='rechercheJournal' tabindex="2" property="secteur" size="1" style="HEIGHT: 20px; WIDTH: 160px" onchange='doSoumettreRafraichir();' >
                      <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR %>' valeurTableValeur='<%=GlobalConstants.TableValeur.SECTEUR%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
                 </myriap:select>
              </td>
		    <TD ALIGN="right"><b><bean:message key='v_sv_intervenant_t'/></b></TD>
		    <TD ALIGN="left" colspan="3">
		    	<bean:define id="secteur" name='rechercheJournal' property='secteur' type="String" />
		       <myriap:select size='1' name='rechercheJournal' tabindex="8" property='intervenant' style='HEIGHT: 20px; WIDTH: 455px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			   		<cardex:optionTag classe='<%= GlobalConstants.CleListe.INTERVENANT_PAR_SECTEUR%>' valeurDiscriminant='<%=secteur%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
		        </myriap:select>&nbsp;
		    </TD>
	  	    <td>&nbsp;</td>
   		</tr>

		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ge_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="genre" name='rechercheJournal' property="genre" type="String"/>
	            <cardex:afficherValeurListeTag name='rechercheJournal' property='genre' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>'
          		valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>'
          		actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
          	  </TD>
		      <TD ALIGN="right"><b><bean:message key='origine_t'/></b></td>
		      <td nowrap>
		      <myriap:select size='1' name='rechercheJournal'  tabindex="9" property='origine' style='HEIGHT: 20px; WIDTH: 140px' onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();" >
			     <cardex:optionTag classe='<%= GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'  valeurDiscriminant='<%=entite %>' valeurTableValeur='<%=GlobalConstants.TableValeur.ORIGINE %>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
			  </myriap:select>
		      </TD>
    		  <TD ALIGN="right" ><b><bean:message key='i_or_cle_t'/></b></TD>
     		  <TD>
                 <bean:define id="site" name='rechercheJournal' property='site' type="String"/>
		          <myriap:select size='1' name='rechercheJournal' property='endroit' style='HEIGHT: 20px; WIDTH: 160px' tabindex="15" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
						<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EndroitSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
			 	  </myriap:select>
     		  </TD>
      		  <td>&nbsp;</td>
		</TR>
		<TR>
	         <TD ALIGN="right"><b><bean:message key='i_na_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="nature" name='rechercheJournal' property="nature" type="String"/>
	            <cardex:afficherValeurListeTag name='rechercheJournal' property='nature' classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
                     	valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
						valeurDiscriminant='<%=genre%>'
						actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>'/>
	         </TD>
            	<TD ALIGN="right"><b><bean:message key='v_do_reference1_t'/></b></TD>
      			<td>
		      		<myriap:text name='rechercheJournal' property="numeroEmploye" tabindex="10" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  />
      			</td>
	        <TD ALIGN="right"><b><bean:message key='i_cr_cle_t'/></b></TD>
	        <TD>
       			<myriap:select size='1' name='rechercheJournal' property='localisation' style='HEIGHT: 20px; WIDTH: 160px' tabindex="16" onkeypress="typeAhead(this, event);" onfocus="resetIncrementalSearch();">
					<cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.LocalisationSiteCleMultiListeCache" valeurDiscriminant='<%=site%>'/>
				</myriap:select>
  		    </TD>
      		<td>&nbsp;</td>
		</TR>
      	<TR>
	         <TD ALIGN="right"><b><bean:message key='i_ty_cle_t'/></b></TD>
	         <TD ALIGN="left">
	         	<bean:define id="type" name='rechercheJournal' property="type" type="String"/>
	             <myriap:select size='1' name='rechercheJournal' property='type' tabindex="4" style='HEIGHT: 20px; WIDTH: 160px' onchange='doSoumettreRafraichir();' >
 		        		<cardex:optionTag classe='<%=GlobalConstants.CleListe.TYPE %>' 
							valeurDiscriminant='<%=nature%>'
							actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	             </myriap:select>
            <TD ALIGN="right"><b><bean:message key='v_do_reference2_t'/></b></TD>
      		<td>
		      	<myriap:text name='rechercheJournal' property="reference2" tabindex="11" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  />
      		</td>
	        <TD ALIGN="right">
              <b><bean:message key='c_do_fonde_t'/></b>
			</TD>
			<TD>
	          <myriap:select name='rechercheJournal' property="fonde" size="1" style="HEIGHT: 20px; WIDTH: 160px" tabindex="17" >
	              <cardex:optionTag classe='<%= GlobalConstants.CleListe.FONDE %>'/>
	          </myriap:select>
	        </TD>
		    <td>&nbsp;</td>
      	</TR>
      	<tr>
		    <TD ALIGN="right"><b><bean:message key='i_ca_cle_t'/></b></TD>
	        <TD ALIGN="left">
		        <myriap:select size='1' name='rechercheJournal' property='categorie' tabindex="5" style='HEIGHT: 20px; WIDTH: 160px' >
	  	       		<cardex:optionTag classe='<%=GlobalConstants.CleListe.CATEGORIE %>' 
							valeurDiscriminant='<%=type%>'
							actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
	            </myriap:select>
      		 </TD>
            	<TD ALIGN="right"><b><bean:message key='v_do_reference3_t'/></b></TD>
      			<td>
		      		<myriap:text name='rechercheJournal' property="reference3" tabindex="12" maxlength="20"  style="HEIGHT: 20px; WIDTH: 140px"  />
      			</td>
		   <TD ALIGN="right"><b><bean:message key='v_do_lieu_t'/></b></TD>
	       <TD ALIGN="left">
	            <cardex:AutoCompleter name='rechercheJournal' property="descriptif" tabindex="18" maxlength="80" 
	            style="HEIGHT: 20px; WIDTH: 160px" classeControl='<%= GlobalConstants.AutoCompleterClass.DESCRIPTIF_DOSSIER_AUTO_COMPLETER %>'
	            height="150" width="150" nbrAmorce="2"/>
      	  </TD>
			<td>&nbsp;</td>
   		</tr>
     	<tr>
	 		<td align="right" nowrap><b><bean:message key='d_date_debut_ajout_t'/></b></td>
    		<td nowrap>
	               <cardex:DateHeure name='rechercheJournal' property="dateCreationDu" calendrier="true" nomProchainChamp="dateCreationAu" tabindex="6" />      				
      		</td>
 		    <td align="right" nowrap><b><bean:message key='d_date_fin_ajout_t'/></b></td>
      		<td nowrap>
      				<cardex:DateHeure name='rechercheJournal' property="dateCreationAu" calendrier="true" tabindex="13" />
      		</td>
   			<td align="right" colspan="3">&nbsp;</td>
      	</tr>

      	</table>
      </td>
    </tr>


    <TR>
      <TD ALIGN="center" valign="top" colspan="2">

     <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0">
		  <tr>
	        <TD ALIGN="left"><b><bean:message key='affichage_resultats'/></b>
	           <html:select size='1' name='rechercheJournal' property='listeResultat.plageResultats' >
	              <cardex:optionTag classe="com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.MaximumCleHardListe" />
	           </html:select>&nbsp;
              <cardex:button accessKey="r" labelKey='cb_rechercher' urlSecurite="/journal/search.do" onclick='doSearch();' />
            </TD>
            <TD ALIGN="left">
              <cardex:button labelKey='cb_clear' soumettre="/journal/search/reset/default.do" />
            </TD>
            <TD width="50" ALIGN="left" colspan="2">&nbsp;
            </TD>
            <TD ALIGN="right">
               <cardex:button urlSecurite="/journal/create.do" labelKey='cb_ajouter'  onclick='doAdd();' />
            </TD>
            <TD align="right">
              <cardex:button labelKey='cb_fermer' onclick='doClose();' />
           </TD>
          </TR>
         </TABLE>

      </TD>
    </TR>
	</table>
	<!-- END TAB CONTENT -->

    </TD>
   </TR>
</table>
<!-- END OUTLINE TABLE -->
