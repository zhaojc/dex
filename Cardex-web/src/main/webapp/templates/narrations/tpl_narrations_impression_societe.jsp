<%-- --------------------------------------------------------------------------
Use case    : Impression d'une narration.
Description : Module d'impression du formulaire d'une narration.
Author(s)   : $Author: mlibersan $, abruno-boucher, mdemers
Revision    : $Revision: 1.5 $, $Date: 2002/04/26 11:55:53 $

History     : Voir ci-dessous.

Revision: 1.7 , Date: 2002/02/25 15:36:13 , Author: abruno-boucher
Ajustement mise-en-page.

$Revision: 1.5 $, $Date: 2002/04/26 11:55:53 $, $Author: mlibersan $
Derniers commentaires à jour.
--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld'  prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>
<%@ page import="com.lotoquebec.cardexCommun.GlobalConstants" %>
<%@ page import="com.lotoquebec.cardex.presentation.model.form.NarrationForm" %>


   <html:hidden  name="narration" property="narrationAvecFormat" />
   <html:hidden  name="narration" property="narrationSansFormat" />
   <html:hidden  name="narration" property="confidentialiteApprobateur" />
   <html:hidden  name="narration" property="confidentialiteCreateur" />
   <html:hidden  name="narration" property="autoriteApprobateur" />
   <html:hidden  name="narration" property="autoriteCreateur" />
   <html:hidden  name="narration" property="autoriteNarration" />

 <TABLE width="652" cellpadding="2" cellspacing="0" border="0" class="pictureBackground" style="border-style: double; border-color: #000000;">
  <TR>
    <TD width="302" valign="bottom">&nbsp;<SPAN class="tabSubject"><u><bean:message key='v_commentaire_t'/></u></SPAN></TD>
    <TD width="230" align="right" valign="bottom" class="errorHeader">
      <bean:message key='v_so_nom_t'/>&nbsp;
    </TD>
    <TD width="120" valign="bottom">
    	<bean:write name='narration' property="reference1"/>
    </TD>
  </TR>
 </TABLE>
 <br>

<TABLE width="650" cellpadding="0" cellspacing="0" border="0" class="tableOutline">
  <TR>
    <TD>
    <table width="650" cellpadding="5" cellspacing="0" border="0" class="pictureBackground">
        <!-- First row for alignment purposes -->
        <tr>
            <td width="109"><html:img page="/images/blank.gif" width="99" height="1" border="0" /></td>
            <td width="95"><html:img page="/images/blank.gif" width="85" height="1" border="0" /></td>
            <td width="120"><html:img page="/images/blank.gif" width="110" height="1" border="0" /></td>
            <td width="95"><html:img page="/images/blank.gif" width="85" height="1" border="0" /></td>
            <td width="120"><html:img page="/images/blank.gif" width="110" height="1" border="0" /></td>
            <td width="95"><html:img page="/images/blank.gif" width="85" height="1" border="0" /></td>
            <td width="16"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></td>
        </tr>

        <tr>
            <td align="right" class="lbl4Printing"><bean:message key='r_co_montant_t'/></td>
            <td>
            	<bean:write name='narration' property="montant"/>
            </td>
            <td align="right" class="lbl4Printing"><bean:message key='i_cc_cle_t'/></td>
            <td>
				<cardex:afficherValeurListeTag name="narration" property="confidentialiteNarration" classe='<%=GlobalConstants.CleListe.TABLE_VALEUR %>' 
					valeurTableValeur='<%=GlobalConstants.TableValeur.CONFIDENTIALITE%>' 
					actionSecurite='<%=GlobalConstants.ActionSecurite.SELECTION%>'/>
            </td>
            <td align="right" class="lbl4Printing"><bean:message key='v_co_rapporte_par_t'/></td>
            <td>
				<cardex:afficherValeurListeTag name="narration" property="rapporteur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
            </td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td align="right" colspan="2" class="lbl4Printing"><bean:message key='v_co_temps_t'/></td>
            <td colspan="3" nowrap>
            	<bean:write name='narration' property="tempsConsacre"/>
               <SPAN style="font-size: 6pt;"><bean:message key='total_minutes_t'/></SPAN>
            </td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td align="center" colspan="7"><html:img page="/images/0061CFpixel.gif" width="630" height="1" border="0" /></td>
        </tr>

        <tr>
            <td align="center" colspan="7">
            <table width="630" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td width="630" class="pictureBackground"><FONT size="+2px"><%= ((NarrationForm)pageContext.findAttribute("narration")).getNarrationAvecFormat() %></FONT>
                  </td>
                </tr>
            </table>
            </td>
        </tr>

        <tr>
            <td align="center" colspan="7"><html:img page="/images/0061CFpixel.gif" width="630" height="1" border="0" /></td>
        </tr>

            <tr>
                <td align="right" class="lbl4Printing"><bean:message key='v_cree_par_t'/></td>
                <td>
                  <cardex:afficherValeurListeTag name="narration" property="createur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                  
                </td>
                <td align="right" class="lbl4Printing"><bean:message key='v_tr_modifie_par_t2'/></td>
                <td>
                  <cardex:afficherValeurListeTag name="narration" property="modificateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </td>
                <td align="right" class="lbl4Printing"><bean:message key='v_modifie_par_t'/></td>
                <td>
                  <cardex:afficherValeurListeTag name="narration" property="approbateur" classe='<%=GlobalConstants.CleListe.INTERVENANT %>' />
                </td>
                <td>&nbsp;</td>
            </tr>

            <tr>
                <td align="right" class="lbl4Printing" nowrap><bean:message key='date_creation_t'/></td>
                <td>
                	<bean:write name='narration' property="dateCreation"/>
                </td>
                <td align="right" class="lbl4Printing" nowrap><bean:message key='date_modification_t'/></td>
                <td>
                	<bean:write name='narration' property="dateModification"/>
                </td>
                <td align="right" class="lbl4Printing" nowrap><bean:message key='d_date_modification_t'/></td>
                <td>
                	<bean:write name='narration' property="dateApprobation"/>
                </td>
                <td>&nbsp;</td>
            </tr>

          <TR>
            <TD colspan="7"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
          </TR>

        </table>

    </TD>
  </TR>
</TABLE>

<P>&nbsp;</P>
<P>&nbsp;</P>

<P>
  <TABLE width="650" cellpadding="2" cellspacing="0" border="0">
    <TR>
        <td width="280"><html:img page="/images/pixelnoir.gif" width="276" height="1" border="0" /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
        <td width="150" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
    </TR>
    <TR>
        <td width="280" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='signature_redacteur_t' /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='no_employe_t' /></td>
        <td width="150" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_t' /></td>
    </TR>
  </TABLE>
</P>
&nbsp;
<P>
  <TABLE width="650" cellpadding="2" cellspacing="0" border="0">
    <TR>
        <td width="280"><html:img page="/images/pixelnoir.gif" width="276" height="1" border="0" /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
        <td width="150" align="center"><html:img page="/images/pixelnoir.gif" width="146" height="1" border="0" /></td>
    </TR>
    <TR>
        <td width="280" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='signature_responsable_t' /></td>
        <td width="80">&nbsp;</td>
        <td width="220" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='no_employe_t' /></td>
        <td width="150" align="center"><bean:message  locale='<%=GlobalConstants.Impression.LOCALE_KEY%>' key='date_t' /></td>
    </TR>
  </TABLE>
<TABLE width="650">
  <TR>
    <TD align='right'><b>CDX_0213</b></TD>
  </TR>
</TABLE>
</P>