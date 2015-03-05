<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "dossiers".
Description : Module qui affiche une liste de dossier à lier
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<html:hidden  name="dossier" property="cle" />
<html:hidden  name="dossier" property="site" />

<TABLE width="900" cellPadding="2" cellSpacing="0" border="1" BGCOLOR="#ffffff" CLASS="tableOutline">
<TR>
    <TD class="listTableHeader" colspan="5" align="center" valign="middle"><FONT style="font-size:18;font-weight:bold;"><bean:message key="choisir_dossier_associer"/></FONT></TD>
</TR>
<TR>
    <TD class="listTableHeader"><bean:message key='v_do_numero_dossier'/></TD>    
    <TD class="listTableHeader"><bean:message key='v_do_anc_ref_entete_t'/></TD>
    <TD class="listTableHeader"><bean:message key='i_genre_t'/></TD>
    <TD class="listTableHeader"><bean:message key='i_nature_t'/></TD>
    <TD class="listTableHeader"><bean:message key='i_type_t'/></TD>
</TR>
<logic:iterate id="element" name="dossier" property='listeDossierALier'>
<TR>
    <TD class="listDetailOdd" nowrap>
    	<A href="#" onclick="doSelect('<bean:write name="element" property="cle"/>-<bean:write name="element" property="site"/>');">
        <bean:write name="element" property="numeroCardexTexte"/>
        </A>
    </TD>
    <TD class="listDetailOdd" nowrap>
        <bean:write name="element" property="numeroDossier"/>
    </TD>
    <TD class="listDetailOdd" nowrap>
    	<bean:define id="entite" name='element' property="entite" type="String"/>
    	<bean:define id="genre" name='element' property="genre" type="String"/>
		<cardex:afficherValeurListeTag name="element" property="genre" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
        	valeurTableValeur='<%=GlobalConstants.TableValeur.GENRE %>' 
			valeurDiscriminant='<%=entite%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />                
    </TD>
    <TD class="listDetailOdd" nowrap>
    <bean:define id="nature" name='element' property="nature" type="String"/>
		<cardex:afficherValeurListeTag name="element" property="nature" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
		valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>' 
		valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />                
    </TD>                
    <TD class="listDetailOdd" nowrap>
   		<cardex:afficherValeurListeTag name="element" property="type" classe='<%=GlobalConstants.CleListe.TYPE%>' 
		valeurDiscriminant='<%=nature%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE%>' />
    </TD>
</TR>
</logic:iterate>
</TABLE>

<TABLE width="900" cellPadding="2" cellSpacing="0" border="0" >
<TR>
    <TD align="right">
		<cardex:button labelKey='retour'  onclick='doRetour();' style="color: #000000; width: 60px; text-align: center" />
    </TD>
</TR>
</TABLE>