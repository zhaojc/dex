<%-- --------------------------------------------------------------------------
Use case    : Toute interaction avec les types d'intéraction.
Description : Module qui affiche l'onglet désignant le type utilisé (dosssier,
              sujet, véhicule, société).
Author(s)   : $Author: mdemers $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/02/21 23:38:20 $

History     : Voir ci-dessous.

Revision: 1.0, Date: 2002/02/14 17:12:06, Author: abruno-boucher
Première étape de la conversion initiale: sauvegarder le gabarit HTML en un
format JSP.

Revision: 1.1 , Date: 2002/02/14 17:18:14 , Author: pcaron
Ajustement des commentaires de la page JSP.

$Revision: 1.2 $, $Date: 2002/02/21 23:38:20 $, $Author: mdemers $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>

<!-- Tab identifier -->
<TABLE width="770" cellPadding="0" cellSpacing="0" border="0">
  <TR>
    <TD>

      <TABLE cellpadding="0" cellspacing="0" border="0">
        <TR>
          <TD><html:img border="0" height="20" page="/images/l_up_corner.gif" width="5" /></TD>
        <logic:equal name="rechercheDossier" property="genre" value="" >
          <TD CLASS="tabTitle" nowrap>&nbsp; <bean:message key='tabpage_dossier' /> &nbsp;
          </TD>
		</logic:equal>
        <logic:notEqual name="rechercheDossier" property="genre" value="" >
          <TD CLASS="tabTitle" nowrap><bean:message key="recherche"/>&nbsp; 
          	<bean:define id="genre" name='rechercheDossier' property="genre" type="String"/> 
          	<cardex:afficherValeurListeTag name="rechercheDossier" property="nature" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR_DISCRIMINANT_REQUIS %>'
			valeurTableValeur='<%=GlobalConstants.TableValeur.NATURE %>'  
			valeurDiscriminant='<%=genre%>' actionSecurite='<%=GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER%>' />
          &nbsp;</TD>
		</logic:notEqual>
          <TD><html:img align="top" border="0" height="20" page="/images/r_up_corner.gif" width="5" /></TD>
        </TR>
      </TABLE>

    </TD>
    <TD CLASS="tabSubject">  </TD>
  </TR>
</TABLE>
<!-- End tab identifier -->