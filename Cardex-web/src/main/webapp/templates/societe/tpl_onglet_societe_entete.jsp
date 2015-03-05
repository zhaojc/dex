<%-- --------------------------------------------------------------------------
Use case    : Toute interaction avec les types d'intéraction.
Description : Module qui affiche l'onglet désignant le type utilisé (dosssier,
              sujet, véhicule, société).
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.2 $, $Date: 2002/03/05 23:15:30 $

History     : Voir ci-dessous.

$Revision: 1.2 $, $Date: 2002/03/05 23:15:30 $, $Author: abruno-boucher $
Création.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>

<!-- Tab identifier -->
<TABLE width="770" cellPadding="0" cellSpacing="0" border="0">
  <TR>
    <TD>

      <TABLE cellpadding="0" cellspacing="0" border="0">
        <TR>
          <TD><html:img border="0" height="20" page="/images/l_up_corner.gif" width="5" /></TD>
          <TD CLASS="tabTitle" nowrap>&nbsp; <bean:message key='tabpage_societe' /> &nbsp;</TD>
          <TD><html:img align="top" border="0" height="20" page="/images/r_up_corner.gif" width="5" /></TD>
        </TR>
      </TABLE>

    </TD>
    <TD CLASS="tabSubject">  </TD>
  </TR>
</TABLE>
<!-- End tab identifier -->