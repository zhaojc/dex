<SCRIPT language="JavaScript" type="text/javascript">
// uncomment these three lines if using IIS and/or adjust the URLS to point to the
// IIS directory where you've located these files. And comment out the next three
// lines.

// var spellCheckURL="http://localhost/jspell_proxy.asp"; // change to point to the JSpell Spell Check Servlet
// var styleSheetURL="http://localhost/jspell.css"; 
// var imagePath="http://localhost/images"; // relative URL to JSpell button images directory
var spellCheckURL="<%=request.getContextPath()%>/JSpell/JSpellServlet.jsp"; // change to point to the JSpell Spell Check Servlet
var styleSheetURL="<%=request.getContextPath()%>/JSpell/jspell.css"; 
var jspellpopupurl="<%=request.getContextPath()%>/JSpell/jspellpopup.html"; // specify location of jspellpopup.html
var imagePath="<%=request.getContextPath()%>/images";

//var blankURL="about:blank";
var blankURL="<%=request.getContextPath()%>/JSpell/blank.html"; // uncomment if using SSL

var ww;	// holds reference to popup
var disableLearn=false; // set to true, to remove the Learn words capability
var forceUpperCase=false; // force suggestions and spell checker to use upper case
var ignoreIrregularCaps=false;	// ignore lower case sentence beginnings, etc.
var ignoreFirstCaps=false;	// ignore if first character in a field is lowercase
var ignoreNumbers=true; // ignore words with embedded numbers
var ignoreUpper=true; // ignore words in upper case
var ignoreDouble=false; // ignore repeated words
var confirmAfterLearn=false; // show warning before user 'learns' a word
var confirmAfterReplace=true; // show warning when replacing using a word not in the suggestions list.
var supplementalDictionary=""; // optional supplemental word list kept at server.
var language="";
var hidePreviewPanel=true; // You can use this to hide the preview panel when running in directEdit mode
var directmode=false; // is highlighting done in original text control or is there a preview panel 
var ieMode=false; // specify type of highlighting

function getSpellCheckItem(jspell_n) {
	var fieldsToCheck=getSpellCheckArray();
	return fieldsToCheck[jspell_n];
}
function updateForm(jspell_m,newvalue) {
 //eval(getSpellCheckItemValue(jspell_m)+"=newvalue"); 
 fenetreNarration.innerHTML = newvalue;
 }
function getSpellCheckItemValue(jspell_j) { return getSpellCheckItem(jspell_j)+".value"; }
function getSpellCheckItemValueValue(jspell_k) { return eval(getSpellCheckItemValue(jspell_k)); }

function jspellcheck() {
	var width=520; var height=200;
	if (navigator.appName == 'Microsoft Internet Explorer' && navigator.userAgent.toLowerCase().indexOf("mac")==-1 && navigator.userAgent.toLowerCase().indexOf("opera")==-1 && hidePreviewPanel) {	
		directmode=true; width=290;
		ieMode=true;
	} 

	if (navigator.userAgent.toLowerCase().indexOf("gecko/")>0 && hidePreviewPanel)
	{
		directmode=true; width=290;
	}

	if(hidePreviewPanel==true)
		width=290;

	var w = 1024, h = 768;
	if (document.all || document.layers)
	{
		w=eval("scre"+"en.availWidth"); h=eval("scre"+"en.availHeight");
	}

	var leftPos = (w/2-width/2), topPos = (h/2-height/2);
	
	ww=window.open(jspellpopupurl, "checker", "width="+width+",height="+height+",top="+topPos+",left="+leftPos+",toolbar=no,status=no,menubar=no,directories=no,resizable=yes");
	ww.focus();
}

function spellCheckClosing() {
	// place code here that will execute when the spell check is complete

	// e.g., message("Spell Check Complete");

	// NOTE: this code will NOT be called if the client is using a Macintosh 
	// and Internet Explorer due to a bug in Mac IE. Also, this code will not
	// get called if the user closes the spell check dialog without clicking
	// the JSpell generated buttons. To add this functionality you must add
	// onunload calls to 'myOpener.spellCheckClosing()' in all of the body
	// tags of JSpell.jsp. However, this will NOT function on a Macintosh with
	// IE due to a Macintosh IE bug.
}
</script>