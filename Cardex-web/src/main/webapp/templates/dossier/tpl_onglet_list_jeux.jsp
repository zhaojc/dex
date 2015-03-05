<%-- --------------------------------------------------------------------------
Use case    : Sélection de onglet "jeux".
Description : Module qui affiche le contenu de l'onglet "jeux", soit une
              liste de jeux.
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.6 $, $Date: 2002/03/13 22:43:46 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.6 $, $Date: 2002/03/13 22:43:46 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- ------------------------------ -->
<DIV id="DATA_GAMES">
    <TABLE width="900" cellPadding="2" cellSpacing="0" border="0" BGCOLOR="#ffffff" CLASS="tableOutline">
    <TR>
        <TD class="listTableHeader"><bean:message key='tabpage_jeu'/></TD>
    </TR>
    <TR>
        <TD class="listDetailOdd" nowrap>
            <logic:notEqual name='dossier' property='statut' value='<%=GlobalConstants.Statut.DOSSIER_INACTIF%>' >
				<cardex:button labelKey="cb_ajouter" soumettre="/dossier/jeux/show.do"/>
            </logic:notEqual>
        </TD>
    </TR>
    <logic:iterate id="element" name="dossier" property='jeux.doubleListe.droiteColLabel'>
    <TR>
        <TD class="listDetailOdd"><%=element %></TD>
    </TR>
    </logic:iterate>
    </TABLE>
</DIV>
<!-- End data_games division -->
