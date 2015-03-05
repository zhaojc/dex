  isLeftSelectEmpty = true;
  isRightSelectEmpty = true;
  var leftSelectName = 0;
  var rightSelectName = 0;

  //-- La fonction suivante v�rifie si les listes d�roulantes
  //-- contiennent des donn�es et active les boutons correspondants.
  //-- La premi�re liste est celle de gauche en HTML.
  //-- Nous parcourons la liste des �l�ments au lieu des noms pr�d�finis
  //-- du formulaire afin de rendre les fonctions d'activation et de
  //-- d�sactivation des boutons le plus g�n�rique possible.
  function initSelectOptions() {
    var i;
    var aType = "";

    for( i = 0; i < document.forms(0).elements.length; i++ ) {
      aType = document.forms(0).elements(i).type;
      //-- Identification des listes d�roulantes
        if (aType.indexOf("select-multiple") != -1){
        //-- affectation de la premi�re liste
            if (leftSelectName == 0) {
              leftSelectName = i; //document.forms(0).elements(i).name;

              if (document.forms(0).elements(i).length > 0) { isLeftSelectEmpty = false; }
            } else {                                 //-- affectation de la deuxi�me liste
              rightSelectName = i; //document.forms(0).elements(i).name;
              if (document.forms(0).elements(i).length > 0) { isRightSelectEmpty = false; }
            }
        }
    }

    //-- Activation des boutons
    if (!isLeftSelectEmpty) {
      document.forms(0).select_all.disabled = false;
      document.forms(0).select_one.disabled = false;
    }

    if (!isRightSelectEmpty) {
      document.forms(0).deselect_all.disabled = false;
      document.forms(0).deselect_one.disabled = false;
    }
  }



  function selectAll() {

    var i = document.forms(0).elements(rightSelectName).length;
    var j = 0;
    document.forms(0).elements(rightSelectName).length = document.forms(0).elements(leftSelectName).length +
                                                        document.forms(0).elements(rightSelectName).length;

    for (i = i; i < document.forms(0).elements(rightSelectName).length; i++){
      document.forms(0).elements(rightSelectName).options(i).value = document.forms(0).elements(leftSelectName).options(j).value;
      document.forms(0).elements(rightSelectName).options(i).text = document.forms(0).elements(leftSelectName).options(j).text;
      j++;
    }
    document.forms(0).elements(leftSelectName).length = 0;
    document.forms(0).deselect_all.disabled = false;
    document.forms(0).deselect_one.disabled = false;
    document.forms(0).select_all.disabled = true;
    document.forms(0).select_one.disabled = true;

  }

  function selectOne() {
    var i;
    var j = 0;
    var k = 0;
    vectorValue = new Array(document.forms(0).elements(leftSelectName).length);
    vectorText = new Array(document.forms(0).elements(leftSelectName).length);
    for (i = 0; i < document.forms(0).elements(leftSelectName).length; i++ ){
      if (document.forms(0).elements(leftSelectName).options(i).selected == true){
        j = document.forms(0).elements(rightSelectName).length + 1;
        document.forms(0).elements(rightSelectName).length = j;
        document.forms(0).elements(rightSelectName).options(j - 1).value = document.forms(0).elements(leftSelectName).options(i).value;
        document.forms(0).elements(rightSelectName).options(j - 1).text = document.forms(0).elements(leftSelectName).options(i).text;
      } else {
        //-- Nous transf�rons les items non-s�lectionn�s dans des tableaux temporaires.
        vectorValue[k] = document.forms(0).elements(leftSelectName).options(i).value;
        vectorText[k] = document.forms(0).elements(leftSelectName).options(i).text;
        k++;
      }
    }
    document.forms(0).deselect_all.disabled = false;
    document.forms(0).deselect_one.disabled = false;

    //-- Reconstruction de la liste d'�l�ments non-s�lectionn�s.
    document.forms(0).elements(leftSelectName).length = 0;

    document.forms(0).elements(leftSelectName).length = k ;
    for (i = 0; i < k; i++ ) {
      document.forms(0).elements(leftSelectName).options(i).value = vectorValue[i];
      document.forms(0).elements(leftSelectName).options(i).text = vectorText[i];
    }

    //-- D�sactivation des boutons si la liste disponible est vide
    if (document.forms(0).elements(leftSelectName).length == 0) {
      document.forms(0).select_all.disabled = true;
      document.forms(0).select_one.disabled = true;
    }
  }

  function deselectOne() {
    var i;
    var j = 0;
    var k = 0;
    vectorValue = new Array(document.forms(0).elements(rightSelectName).length);
    vectorText = new Array(document.forms(0).elements(rightSelectName).length);
    for (i = 0; i < document.forms(0).elements(rightSelectName).length; i++ ){
      if (document.forms(0).elements(rightSelectName).options(i).selected == true){
        j = document.forms(0).elements(leftSelectName).length + 1;
        document.forms(0).elements(leftSelectName).length = j;
        document.forms(0).elements(leftSelectName).options(j - 1).value = document.forms(0).elements(rightSelectName).options(i).value;
        document.forms(0).elements(leftSelectName).options(j - 1).text = document.forms(0).elements(rightSelectName).options(i).text;
      } else {
        //-- Nous transf�rons les items non-s�lectionn�s dans des tableaux temporaires.
        vectorValue[k] = document.forms(0).elements(rightSelectName).options(i).value;
        vectorText[k] = document.forms(0).elements(rightSelectName).options(i).text;
        k++;
      }
    }
    document.forms(0).select_all.disabled = false;
    document.forms(0).select_one.disabled = false;

    //-- Reconstruction de la liste d'�l�ments non-s�lectionn�s.
    document.forms(0).elements(rightSelectName).length = 0;

    document.forms(0).elements(rightSelectName).length = k ;
    for (i = 0; i < k; i++ ) {
      document.forms(0).elements(rightSelectName).options(i).value = vectorValue[i];
      document.forms(0).elements(rightSelectName).options(i).text = vectorText[i];
    }

    //-- D�sactivation des boutons si la liste disponible est vide
    if (document.forms(0).elements(rightSelectName).length == 0) {
      document.forms(0).deselect_all.disabled = true;
      document.forms(0).deselect_one.disabled = true;
    }
  }


  function deselectAll() {
    var i = document.forms(0).elements(leftSelectName).length;
    var j = 0;
    document.forms(0).elements(leftSelectName).length = document.forms(0).elements(leftSelectName).length +
                                                        document.forms(0).elements(rightSelectName).length;

    for (i = i; i < document.forms(0).elements(leftSelectName).length; i++){
      document.forms(0).elements(leftSelectName).options(i).value = document.forms(0).elements(rightSelectName).options(j).value;
      document.forms(0).elements(leftSelectName).options(i).text = document.forms(0).elements(rightSelectName).options(j).text;
      j++;
    }
    document.forms(0).elements(rightSelectName).length = 0;
    document.forms(0).select_all.disabled = false;
    document.forms(0).select_one.disabled = false;
    document.forms(0).deselect_all.disabled = true;
    document.forms(0).deselect_one.disabled = true;
  }


  //-- D�commenter la fonction suivante et l'appeler depuis le bouton Ok
  //-- pour visualiser les items s�lectionn�s.
  
/*  function showSelectOptions() {
    var aMsg = "";
    performSelectAll();
    for (var i=0; i < document.forms(0).elements(rightSelectName).length; i++){
      if (document.forms(0).elements(rightSelectName).options(i).selected){
        aMsg = aMsg + document.forms(0).elements(rightSelectName).options(i).text +  " = "
               + document.forms(0).elements(rightSelectName).options(i).value + "\n" ;
      }
    }
    aMsg = aMsg + "Nombre d'items choisis : " + document.forms(0).elements(rightSelectName).length;
    alert(aMsg);
  }
*/  


  //-- S�lection de tous les items de la liste avant d'envoyer le r�sultat au serveur.
  function performSelectAll () {
    var i;

    for ( i = 0; i < document.forms(0).elements(rightSelectName).length; i++ ){
      document.forms(0).elements(rightSelectName).options(i).selected = true;
    }
  }
