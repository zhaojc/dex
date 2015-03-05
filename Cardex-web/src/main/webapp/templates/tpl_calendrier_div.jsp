<%-- --------------------------------------------------------------------------
Use case    : Affichage dynamique d'un calendrier.
Description : D�claration de division n�cessaire pour le peuplement et le
              positionnement dynamique d'un calendrier.
Author(s)   : $Author: abruno-boucher $, abruno-boucher
Revision    : $Revision: 1.1 $, $Date: 2002/03/22 23:23:21 $

History     : Voir ci-dessous.

$Revision: 1.1 $, $Date: 2002/03/22 23:23:21 $, $Author: abruno-boucher $
Cr�ation.
--------------------------------------------------------------------------- --%>


<!-- D�claration de division pour affichage dynamique du calendrier -->
<!-- DOIT N�CESSAIREMENT �TRE INCLUS � L'INT�RIEUR D'UNE BALISE <FORM> -->
  <STYLE type="text/css">
    #CALENDAR_DIV { 
      position: absolute; 
      top: 0px; 
      left: 0px; 
      width: 180px;
      height: 180px;
      clip: rect(0px 180px 180px 0px);
      overflow: hidden;
      background-color: #ffffff;
      z-index: 2; 
      visibility: hidden; 
    }
  </STYLE>
  <DIV id="CALENDAR_DIV" onmouseout="hideCalendar();" 
    onmouseover="if (divToHide){ clearTimeout(divToHide); divToHide = false; }"></DIV>
  <input type="hidden" name="time" value="" />
  <iframe id="selectBlockerCalendar" style="position:absolute;visibility: hidden;" frameBorder="0" scrolling="no" src='<%= request.getContextPath() + "/vide.html"%>' height="180" width="180"></iframe>
<!-- Fin calendrier division -->
