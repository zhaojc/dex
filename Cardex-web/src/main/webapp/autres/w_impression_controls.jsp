<%-- --------------------------------------------------------------------------
Use case    : Bouton de contrôle pour Impression.
Description : Les boutons ne s'impriment pas sur la page
Author(s)   : $Author: mlibersan $, abruno-boucher
Revision    : $Revision: 1.5 $, $Date: 2002/04/18 21:17:47 $

History     : Voir ci-dessous.

$Revision: 1.5 $, $Date: 2002/04/18 21:17:47 $, $Author: mlibersan $
Création.

--------------------------------------------------------------------------- --%>

<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>

<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<?import namespace="ie" implementation="#default">


<html XMLNS:IE>
<head>
<META HTTP-EQUIV="expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel='stylesheet' type='text/css' href='<%= request.getContextPath() + "/styles/lq_styles.css"%>'>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<SCRIPT language="JavaScript" type="text/javascript">
  function doPrint(){
    parent.frames('print_content').focus();
    parent.frames('print_content').print();
  }

  function doClose() {
  	onUnload = false;
	window.parent.close();
  }

function mail(){
  //var Outlook  = new ActiveXObject( "Outlook.Application" );
  //var MailItem = Outlook.CreateItem(0);
  //var objFSO = new ActiveXObject("Scripting.FileSystemObject");
  //var identifiant = parent.frames('print_content').document.forms(0).cle.value;
  //var fichier = "\\\\denon\\Courriels\\" + identifiant + ".htm"; 
  //alert(fichier);
  //2 = pour écriture
  //var objTextFileO = objFSO.OpenTextFile (fichier, 2, true);
  //objTextFileO.write(parent.frames('print_content').document.body.innerHTML);
  //objTextFileO.close();
  //MailItem.Subject = "Rapport";
  //MailItem.To = "";
  //MailItem.CC = "";
  //MailItem.BodyFormat = 2;
  //MailItem.HTMLBody = parent.frames('print_content').document.body.innerHTML;
  //MailItem.HTMLBody = "http://" + fichier;
  //MailItem.Body = "http://denon.le500.loto-quebec.com:83//" + identifiant + ".htm";
  //MailItem.display();
}


</SCRIPT>

<title></title>

</head>
<body leftmargin="5" rightmargin="0" topmargin="0" marginheight="5" marginwidth="5">

<!-- object id=factory style="display:none"
classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" viewastext codebase="ScriptX.cab#Version=5,0,4,185">
</object -->

<TABLE width="650" cellpadding="0" cellspacing="0" border="0" bgcolor="#eeeeee" class="tableOutline">
  <TR>
    <TD class="pictureBackground">
    <table width="650" cellpadding="5" cellspacing="0" border="0">
        <TR>
           <TD><html:img page="/images/blank.gif" width="640" height="1" border="0" /></TD>
        </TR>

        <tr>
            <td align="center">
              <!-- BOTTOM BUTTONS -->
              <TABLE width="300" cellpadding="5" cellspacing="0" border="0">
                <TR>
                  <TD width="150" align="right">
                    <cardex:button securityConstraint='cardex.fenetres.base.imprimer' labelKey='cb_imprimer'  onclick='doPrint();' />
                  </TD>

                  <TD width="150" align="center">&nbsp;
                    &nbsp;
                  </TD>

                  <TD width="150">
                    <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
                  </TD>

                </TR>
              </TABLE>
              <!-- END BOTTOM BUTTONS -->
            </td>
          </tr>

           <TR>
            <TD><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
          </TR>

        </table>

    </TD>
  </TR>
</TABLE>
