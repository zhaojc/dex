<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>


<TABLE cellpadding="1" cellspacing="0" border="0">

  <TR>
    <TD CLASS="tabTitle">

    <TABLE  width="850" cellpadding="2" cellspacing="0" border="0" 
    style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#cbd8e9', startColorstr='#FFFFFF', gradientType='0');">
      <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR><!-- First row uses transparent pixel to force good alignment -->
     <TR>
      <TD WIDTH="850" ALIGN="center" COLSPAN="6">

      <!-- eSort by options for Kind & nature listing -->
      <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0">
		<tr>
		  <TD align="right">
	          <cardex:button labelKey='cb_fermer'  onclick='window.close();' />
	     </TD>	
		
	   </TR>

      </TABLE>
      <!-- End Sort by -->

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    </TABLE>
    <!-- End Kind & nature search fields -->

  </TD>
  </TR>
</TABLE>
<!-- End outline table -->
