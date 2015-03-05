<%-- --------------------------------------------------------------------------
Use case    : Exportation des données sur un sujet
Description : Utilisé par la Sûreté du Québec.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/19 17:56:43 $

History     : Voir ci-dessous.

Revision: 1.1 , Date: 2002/03/26 21:09:17 , Author: abruno-boucher
Création.

$Revision: 1.5 $, $Date: 2002/04/19 17:56:43 $, $Author: mlibersan $
Commentaires à jour.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.SujetForm" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.PhotoForm" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>

<HTML>
<HEAD>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="imagetoolbar" content="no" />

</HEAD>
<BODY>

<!-- Formatage des informations séparées par un point-virgule sur une seule ligne. L'utilisateur peut ensuite sauvegarder la page dans un fichier. -->
<bean:write name="sujet" property="numeroFiche"/>;<bean:write name="sujet" property="nom"/>;<bean:write name="sujet" property="prenom"/>;<bean:write name="sujet" property="dateNaissance"/>;<cardex:afficherValeurListeTag classe='<%= GlobalConstants.CleListe.SEXE %>' name='sujet' property="sexe"/>;<bean:write name="sujet" property="numeroAssuranceSociale" />;<bean:write name="sujet" property="numeroPermisConduire" />;
<logic:iterate id="adresse" name="sujet" property='adresses'><cardex:affichageAdresse name="adresse" numeroLigne="1" langueKey="<%=GlobalConstants.Impression.LOCALE_KEY%>" />;<cardex:affichageAdresse name="adresse" numeroLigne="2" langueKey="<%=GlobalConstants.Impression.LOCALE_KEY%>" />;<bean:write name="adresse" property="villeDescription"/>;<bean:write name="adresse" property="provinceDescription"/>;<cardex:afficherValeurListeTag name='adresse' property="pays" classe='<%= GlobalConstants.CleListe.PAYS %>' />;<bean:write name='adresse' property='codePostal' />;<bean:write name='adresse' property='telephone1' />;</logic:iterate>
<logic:iterate id="societe" name="sujet" property='societes'><bean:write name="societe" property="nom"/>;</logic:iterate>
<logic:iterate id="dossier" name="sujet" property='dossiers'><bean:write name="dossier" property="numeroCardex.site"/>-<bean:write name="dossier" property="numeroCardex.date"/>-<bean:write name="dossier" property="numeroCardex.sequence"/>;</logic:iterate>  	

</BODY>
</HTML>
