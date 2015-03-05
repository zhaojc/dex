function spellCheck() {
	spellcheck();
}

function spellcheck() {
	var directmode=false; var width=450; var height=200;
	if (navigator.appName == 'Microsoft Internet Explorer' && navigator.userAgent.toLowerCase().indexOf("opera")==-1) {	
		directmode=true; width=220;
	}

	var w = 640, h = 480;
	if (document.all || document.layers)
		w = screen.availWidth; h = screen.availHeight;

	var leftPos = (w/2-width/2), topPos = (h/2-height/2);
	
	// need to check if window is already open.
	
		ww=window.open("", "checker", "width="+width+",height="+height+",top="+topPos+",left="+leftPos+",toolbar=no,status=no,menubar=no,directories=no,resizable=yes");
		var spellCheckArray=getSpellCheckArray();
		var h="";
		h+='<HTML><HEAD><TITLE>Orthographe</TITLE></HEAD><BODY onLoad="document.forms[0].submit();">';
		h+='<FORM ACTION="'+spellCheckURL+'" METHOD="POST" NAME="jspell" target="checker">';
		h+='<INPUT TYPE=hidden NAME="imagePath" VALUE="'+imagePath+'">';
		h+='<INPUT TYPE=hidden NAME="fieldCount" VALUE="'+spellCheckArray.length+'">';
		
		for(var i=0;i<spellCheckArray.length;i++)
		{
			h+='<INPUT TYPE=hidden NAME="field_'+i+'" VALUE="fieldvalue_'+i+'">';
			h+='<INPUT TYPE=hidden NAME="fieldvalue_'+i+'" VALUE="">';
		}

		if (directmode)
			h+='<INPUT type=hidden NAME="directEdit" VALUE="YES">';
		else 
			h+='<INPUT type=hidden NAME="directEdit" VALUE="NO">';

		if (disableLearn) // defaults to enabled, must explicitly disable learning words.
			h+='<INPUT type=hidden NAME="enableLearn" VALUE="NO">';
		else 
			h+='<INPUT type=hidden NAME="enableLearn" VALUE="YES">';

		if (forceUpperCase) // defaults to false
			h+='<INPUT type=hidden NAME="forceUpperCase" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="forceUpperCase" VALUE="False">';

		if (ignoreIrregularCaps)
			h+='<INPUT type=hidden NAME="ignoreIrregularCaps" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="ignoreIrregularCaps" VALUE="False">';

		if(ignoreFirstCaps)
			h+='<INPUT type=hidden NAME="ignoreFirstCaps" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="ignoreFirstCaps" VALUE="False">';

		if(ignoreUpper)
			h+='<INPUT type=hidden NAME="ignoreUpper" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="ignoreUpper" VALUE="False">';

		if(ignoreNumbers)
			h+='<INPUT type=hidden NAME="ignoreNumbers" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="ignoreNumbers" VALUE="False">';

		if(ignoreDouble)
			h+='<INPUT type=hidden NAME="ignoreDouble" VALUE="True">';
		else
			h+='<INPUT type=hidden NAME="ignoreDouble" VALUE="False">';

		h+='</FORM>';
		h+='<SCR'+'IPT>';
		for(var i=0;i<spellCheckArray.length;i++)
		{
			if(navigator.userAgent.toLowerCase().indexOf("opera")==-1) 	{
				h+='document.forms["jspell"].fieldvalue_'+i+'.value=opener.getSpellCheckItemValueValue('+i+'); ';
			} else {
				h+='document.forms["jspell"].fieldvalue_'+i+'.value=unescape("'+escape(eval(getSpellCheckItemValue(i)))+'"); ';
			}
		}
		h+='</SCR'+'IPT></BODY></HTML>';

		ww.document.open(); 	ww.document.write(h); 	ww.document.close();
	ww.focus(); 
}
function updateForm(jspell_m,newvalue) { eval(getSpellCheckItemValue(jspell_m)+"=newvalue"); finVerification();}
function getSpellCheckItemValue(jspell_j) { return getSpellCheckItem(jspell_j)+".value"; }
function getSpellCheckItemValueValue(jspell_k) { return eval(getSpellCheckItemValue(jspell_k)); }
