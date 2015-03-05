// Dual_List.js: Gestion des "dual lists" dans Catégories de compte.
//------------------------------
// created 9 juin 2003
// update 12 juin 2003 by L.Lamontagne -> add fonction (selectAllList)


// Compare two options within a list by VALUES
function compareOptionValues(a, b) 
{ 
  
  // Radix 36: for alphanumeric values
  var sA = parseInt( a.value, 36 );  
  var sB = parseInt( b.value, 36 );  
  return sA - sB;
}


// Compare two options within a list by TEXT
function compareOptionText(a, b) 
{ 
  // Radix 36: for alphanumeric values

  var sA = parseInt( a.text, 36 );  
  var sB = parseInt( b.text, 36 );  
  return sA - sB;
}

// Sélectionne tout les élements d'une liste donné
function selectAllList(sourceList) 
{
	for(var i = 0; i < sourceList.options.length; i++) 
	{
	   if (sourceList.options[i] != null)
	   {
	     sourceList.options[i].selected = true;
	   }
	} 
    return true;
}
 
// désélectionne tout les élements d'une liste donné
function unSelectAllList(sourceList) 
{
	for(var i = 0; i < sourceList.options.length; i++) 
	{
	   if (sourceList.options[i] != null)
	   {
	     sourceList.options[i].selected = false;
	   }
	} 
    return true;
}


// Dual list move function
function moveDualList( srcList, destList, moveAll ) 
{
  // Do nothing if nothing is selected
  if (  ( srcList.selectedIndex == -1 ) && ( moveAll == false )   )
  {
    return;
  }
  newDestList = new Array( destList.options.length );

  // Initalise la destination avec les item s'y trouvant deja  
  var len = 0;
  for( len = 0; len < destList.options.length; len++ ) 
  {
    if ( destList.options[ len ] != null )
   {
      newDestList[ len ] = new Option( destList.options[ len ].text, destList.options[ len ].value, destList.options[ len ].defaultSelected, destList.options[ len ].selected );
    }
  }

	 
  for( var i = 0; i < srcList.options.length; i++ ) 
  { 
    if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
    {
       // Statements to perform if option is selected
       // Incorporate into new list
       newDestList[ len ] = new Option( srcList.options[i].text, srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected );
       len++;
    }
  }

  // Populate the destination with the items from the new array
  var k = 0;
  for ( var j = newDestList.length-1; j >= 0 ; j-- ) 
  {
    if ( newDestList[ j ] != null )
    {
      destList.options[ k++ ] = newDestList[ j ];
    }
  }

  // Erase source list selected elements
  for( var i = srcList.options.length - 1; i >= 0; i-- ) 
  { 
    if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
    {
       srcList.options[i] = null;
    }
  }
  
  //Fix 1501 -Trier les liste Select lors du déplacement. David Bernier
  // ce tri peut être lent, il faudrait trouver une meilleure façon de faire les choses
  Trier(srcList) //Tri la liste de départ.
  Trier(destList) //Trie la liste d'arrivé.
} 

//Fix 1501 -Trier les liste Select lors du déplacement. David Bernier
function Trier(liste) {
	listeMotsTrier = new Array( liste.options.length );
	listeTrier = new Array( liste.options.length );

	// Collecte les informations (text) de la liste
	var len = 0;
	for( len = 0; len < liste.options.length; len++ ) 
	{
		if ( liste.options[ len ] != null )
		{
			//Ajout des mots dans un Array afin de les trier
			listeMotsTrier[ len ] = liste.options[ len ].text + "|" + liste.options[ len ].value;
		}
	}
	
	//Trier la liste de mot.
	listeMotsTrier.sort(function(x,y) { 
      var a = remplacerAccents(x.substring(0, x.indexOf("|"))); 
      var b = remplacerAccents(y.substring(0, y.indexOf("|")));
      if(a < b) return -1;
      if(a == b) return 0;
      if(a > b) return 1;
    });
	
	//Reconstruire un nouveau Array avec la liste trier.
	var len2 = 0;
	for( len2 = 0; len2 < liste.options.length; len2++ ) 
	{
		if ( liste.options[ len2 ] != null )
		{
			x = listeMotsTrier[ len2 ];
			y = x.indexOf("|");
			laValeur = x.substring(y+1);
			leTexte = x.substring(0,y);
			
			listeTrier[ len2 ] = new Option( leTexte,laValeur,false,false);
			

		}
	}
	  
	//Reconstruire la liste avec le nouveau Array trié.
	for ( var j = 0; j <= listeTrier.length; j++) 
	{
		if ( listeTrier[ j ] != null )
		{
			liste.options[ j ] = listeTrier[ j ];
			
		}
	}
  
}

function remplacerAccents(s) {
	var accents = "ÀÂÄÉÈÊËÎÏÔÖÙÜÛÇ";
	var sansAccents = "AAAEEEEIIOOUUUC";
	
	var newString = String(s).toUpperCase();
	for(i = 0; i < s.length; i++) {
		pos = accents.indexOf(s.charAt(i));
		if(pos > -1) {
			newString = newString.replace(accents.charAt(pos), sansAccents.charAt(pos));
		}
	}
	return newString;
}
