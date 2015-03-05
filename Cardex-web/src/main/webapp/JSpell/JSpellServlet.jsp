<%@page import="com.wallstreetwise.app.jspell.domain.net.JSpellHTMLTools,com.wallstreetwise.app.jspell.domain.JSpellException,java.io.*,java.util.*"%>
<%@page session="false"%>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page language="java" contentType="text/html; charset=iso-8859-15" %>
<% 	
	Locale guiLocale=Locale.FRENCH;
	String guiLanguage=request.getParameter("guiLanguage");
	String guiCountry=request.getParameter("guiCountry");
	if(guiLanguage!=null && guiCountry!=null)
		guiLocale=new Locale(guiLanguage,guiCountry);
%>
<html>
<head><title>
Orthographe
</title>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-15">
<%
	JSpellHTMLTools tools=(JSpellHTMLTools)JSpellHTMLTools.getJSpellTools();
	String styleSheetURL=tools.getStyleSheetURL(request);
	String dictionaryOverride=null; 
	boolean compatibilityMode=false; // use compatible method (links vs. buttons) for older browsers such as Netscape 4.75, etc.
%>
<!-- Stylesheets are only partially used due to a bug in Firefox. To change button styles you may need to edit the styles inline -->
<link rel="stylesheet" type="text/css" href="<%=styleSheetURL%>">
<script type="text/javascript" language="JavaScript1.1"><!--
var myOpener=opener;
var oy;

function spellCheckClosing()
{
	if(!(navigator.appName == 'Microsoft Internet Explorer' && navigator.userAgent.toLowerCase().indexOf("mac")>0))
		myOpener.spellCheckClosing();
}

//if(typeof(window.dialogArguments)!="undefined")
//{
//	myOpener=window.dialogArguments;
//}

function right(e) {
	var warning1="THIS CODE DISPLAYS THE COPYRIGHT INFORMATION FOR THE JSPELL SPELL CHECKER.";
	var warning2="THE COPYRIGHT INFORMATION IS DISPLAYED WHEN THE RIGHT MOUSE BUTTON IS PRESSED.";
	var warning3="THE LICENSE FOR JSPELL DOES NOT PERMIT REMOVING OR DISABLING THE CODE";
	var warning4="CONTAINED WITHIN THIS SCRIPT BLOCK. (If you have a special requirement";
	var warning5="or if this code is causing problems on your web site then please contact us for support.)";

	if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2)) return false;
	else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3)) {
		alert('Correcteur orthographique - JSpell Spell Checker\n\nCopyright 1996-2007 The Solution Cafe Inc. All Rights Reserved.\n');
	window.event.cancelBubble=true;
		return false;
  }
  return true;
}
document.onmousedown=right;
if (document.layers) window.captureEvents(Event.MOUSEDOWN);
window.onmousedown=right;
//--></script>
<%
	tools.setCache(response);
	String directEdit=tools.getDirectEdit(request);
	String ieMode=tools.getIEMode(request);
	boolean drawPanel=true;
	boolean confirmAfterLearn=Boolean.valueOf(request.getParameter("confirmAfterLearn")).booleanValue();
	boolean confirmAfterReplace=Boolean.valueOf(request.getParameter("confirmAfterReplace")).booleanValue();
	boolean runTextChecker=request.getParameter("checkPanel")==null;
	boolean errorPanel=request.getParameter("errorPanel")!=null;
	boolean hidePreviewPanel=Boolean.valueOf(request.getParameter("hidePreviewPanel")).booleanValue();
	String enableLearn=request.getParameter("enableLearn");
	String supplementalDictionary=tools.getSupplementalDictionary(request);
	String language=tools.getLanguage(request);
	String imagePath=tools.getImagePath(request);
	String errorString=null;
	String errorDiagString="";

	if(runTextChecker && !errorPanel) {
		tools.indexSetup(config,dictionaryOverride);
	} 
	if(!tools.indexReady) {	
		errorPanel=true;
		errorDiagString="JSpell Has Not Been Properly Configured";
	}
	try {
		if(runTextChecker && !errorPanel)
			errorString=tools.getErrors(request,directEdit,ieMode,confirmAfterLearn,confirmAfterReplace,supplementalDictionary,language);
	} catch (JSpellException jse)
	{
		// Catch dictionary initialization errors.
		errorPanel=true;
		errorDiagString=jse.getMessage();
	}
	if(runTextChecker && !errorPanel) {
		if(errorString==null) {
			drawPanel=false;
			%>  
</head>
<body bgcolor="#dddddd">
<table width="100%" height=100% cellspacing=0 cellpadding=0 border="0">
	<tr>
		<td align=center valign=middle>
			<font face="Verdana, Helvetica, Arial, san-serif" size=4><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("spellcheckcomplete")%></font><br>
			<font face="Verdana, Helvetica, Arial, san-serif" size=2>(<%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("noerrorsfound")%>)</font><p>
			<form>
				<input type=button class="noerrorbutton" name="closeButton" value="<%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("ok")%>" onclick="spellCheckClosing(); parent.close();"><br>
			</form>
		</td>
	</tr>
</table>
<script type="text/javascript"><!--
	document.forms[0].closeButton.focus();
//--></script>
</body>
<%
		} 
		else 	{
		out.println(errorString);
		%> 
<SCRIPT type="text/javascript"><!--
var cfd;
var blocked=false;
function s(z,Z) {
	for(i=0;i<z.length;i++) {
		if(z[i]==Z)
			return true;
	}
	return false;
}
function setCookie(name, value, expires, path, domain, secure) {
	var curCookie = name + "=" + escape(value) +
		((expires) ? "; expires=" + expires.toGMTString() : "") +
		((path) ? "; path=" + path : "") +
		((domain) ? "; domain=" + domain : "") +
		((secure) ? "; secure" : "");
	document.cookie = curCookie;
}
function delCookie (NameOfCookie) {
  document.cookie = NameOfCookie + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
}
function fixDate(date) {
	var b = new Date(0);
	var s1 = b.getTime();
	if (s1 > 0)
		date.setTime(date.getTime() - s1);
}
function re() {
	var result=null;
	while(p2!=ec) {
		result=er[p2++];
		if(!s(po,result.s4) && learned.indexOf(result.s4+",")==-1 && !result.replaced) {
			if(directEdit=="NO") {
				if(cfd!=null && cfd!=result.fd)
					parent.myOpener.updateForm(cfd,y9[cfd]);
			}
			cfd=result.fd;
			return result;
		}
		else
			result=null;
	}
	if(result!=null) {
		if(directEdit=="NO") {
			if(cfd!=null && cfd!=result.fd)
				parent.myOpener.updateForm(cfd,y9[cfd]);
		}
		cfd=result.fd;
	}
	return result;
}
function runChecker() {
	oy=re();
	if(oy!=null) {
		for(i=0;i<10;i++) {
			if(i<oy.c) {
				// assigning option fails in Opera
				bv.pp.options[i].value=oy.b[i].text;
				bv.pp.options[i].text=oy.b[i].text;
			} else {
				bv.pp.options[i].value="";
				bv.pp.options[i].text="";
			}
		}
		if(oy.c>0) {
			bv.pp.options[0].selected=true;
			bv.replacement.value=oy.b[0].text;
		} else {
			bv.replacement.value=oy.s4;
		}
		bv.notFound.value=oy.s4;
		if(oy.c>0 && oy.b[0].text.charAt(0)=='*')
		{
			bv.replacement.disabled=true;
			blocked=true;
				bv.ignore.disabled=true;
				bv.ignoreAll.disabled=true;
<%
				if(enableLearn!=null && enableLearn.equals("YES")) {
%>
					bv.learn.disabled=true;
<% } %>

		} else
		{
			if(bv.replacement.disabled)
			{
				blocked=false;
				bv.replacement.disabled=false;
					bv.ignore.disabled=false;
					bv.ignoreAll.disabled=false;
<%
				if(enableLearn!=null && enableLearn.equals("YES")) {
%>
						bv.learn.disabled=false;
<% } %>
			}
		}
		showErrorInContext(oy);
		if(oy.c>0) {
			bv.pp.options[0].selected=true;
			bv.replacement.value="";
			bv.replacement.value=oy.b[0].text;
		} else {
			bv.replacement.value="";
			bv.replacement.value=oy.s4;
		}
		bv.notFound.value="";
		bv.notFound.value=oy.s4;
	} else {
		f();
	}
}
function qy(hg) {
	nw="09,z23at2a2"+er[hg].s4+"97.12ag1z2g";
	var whichError=er[hg];
	ui=y9[cfd].substring(0,whichError.ho);
	ui+=nw;
	ui+=y9[cfd].substring(whichError.ho+whichError.s4.length,y9[cfd].length);
	return ui;
}

function blt() {
	var h="";
	h+="<HTML><HEAD><TITLE><\/TITLE>";
	h+="<STYLE type=\"text/css\">";
	h+="body {background:#dddddd; margin:0px; padding:0px;}";
	h+="p {margin-top:0;margin-bottom:0;}";
	h+="<\/STYLE>";
	h+="<\/HEAD>";
	h+="<BODY>";
	h+="<TABLE height=100% width=100% cellpadding=0 cellspacing=0>";
	h+="<TR><TD align=center valign=center><font face=\"Verdana, Arial, Helvetica, sans-serif\" size=2><b>Correction Preview<\/b><\/font><\/TD><\/TR>";
	h+="<\/TABLE>";
	h+="<\/BODY><\/HTML>";

	parent.frames["heading"].document.open();
	parent.frames["heading"].document.write(h);
	parent.frames["heading"].document.close();
}

function pbj() {
	var h="";
	h+="<HTML><HEAD><TITLE><\/TITLE>";
	h+="<STYLE type=\"text/css\">";
	h+="body {background:#dddddd; margin:2px; padding:0px;}";
	h+="p {margin-top:0;margin-bottom:0;}";
	h+="<\/STYLE>";
	h+="<\/HEAD>";
	h+="<BODY>";
	var pt=qy(p2-1);
	var start=0;
	if(er[p2-1].ho>150) {
		start=pt.lastIndexOf(" ",er[p2-1].ho-150);
		if(start==-1)
			start=0;
	}
	var end=pt.length;
	if(er[p2-1].ho+170<pt.length) {
		end=pt.indexOf(" ",er[p2-1].ho+170);
		if(end==-1)
			end=pt.length;
	}
	var drawEllipse=true;
	if(end==pt.length)
		drawEllipse=false;
	pt=pt.substring(start,end);
	pt=r3(pt,"<","&lt;");
	pt=r3(pt,">","&gt;");
	pt=r3(pt,"09,z23at2a2","<font color=blue><strong>");
	pt=r3(pt,"97.12ag1z2g","<\/strong><\/font>");
	pt=r3(pt,"\r","<BR>");
	h+="<font face=\"Verdana, Arial, Helvetica, sans-serif\" size=2>";
	if(start!=0)
		h+="... ";
	h+=pt;
	if(drawEllipse)
		h+=" ...";
	h+="<\/font>";
	h+="<\/BODY>";
	h+="<\/HTML>";
	parent.frames["preview"].document.open();
	parent.frames["preview"].document.write(h);
	parent.frames["preview"].document.close();
}

function showErrorInContext(wa) {
   if(directEdit=="NO") {
      pbj();
   } else {
      mcd(wa);
   }
}
function mcd(wa) {
   var ho=wa.ho;
   var lineCount=0;
   for(var i=y9[cfd].indexOf("\n",0);i!=-1 && i<ho;i=y9[cfd].indexOf("\n",i+1))
      lineCount++;
   var s4=wa.s4;
   if(ieMode=="NO")
   {
		var ez=eval("myOpener."+myOpener.getSpellCheckItem(cfd));
		ez.focus();
		ez.setSelectionRange(ho-lineCount,ho-lineCount+s4.length);
	} else
	{
		var ez=eval("myOpener."+myOpener.getSpellCheckItem(cfd)).createTextRange();
		ez.move("character",ho-lineCount);
		ez.moveEnd("character",s4.length);
		ez.select();
	}
}
function r3(string,text,by) {
	var strLength = string.length, txtLength = text.length;
	if ((strLength == 0) || (txtLength == 0)) return string;
	var i = string.indexOf(text);
	if ((!i) && (text != string.substring(0,txtLength))) return string;
	if (i == -1) return string;
	var newstr = string.substring(0,i) + by;
	if (i+txtLength < strLength)
		newstr += r3(string.substring(i+txtLength,strLength),text,by);
	return newstr;
}
function confirmReplacement() {
	if(confirmAfterReplace) {
		return confirm("You are replacing: '"+er[p2-1].s4+
			"' with a word that is not from the suggestions.\n"+
			"Your replacement HAS NOT been validated.  If you choose to\n"+
			"continue and you are not sure of the proper spelling of your\n"+
			"replacement then you should rerun the spell check to verify\n"+
			"your replacement.\n\nChoose Ok to continue the replacement or Cancel.");
	} else {
		return true;
	}
}
function u8() {
	var nw=bv.replacement.value;
	var validreplacement=s(v,nw);
	if(!validreplacement) {
		if(!confirmReplacement())
			return;
	}
	trunc(p2-1,nw);
	if(directEdit=="YES")
		myOpener.updateForm(cfd,y9[cfd]);
	runChecker();
}
function tru() {
	var nw=bv.replacement.value;
	var validreplacement=s(v,nw);
	if(!validreplacement) {
		if(!confirmReplacement())
			return;
	}
	for(var i=p2-1;i<ec;i++) {
		if(er[i].s4==er[p2-1].s4 && er[i].fd==er[p2-1].fd)
			trunc(i,nw);
	}
	if(directEdit=="YES")
		myOpener.updateForm(cfd,y9[cfd]);
	runChecker();
}
function trunc(hg, nw) {
	var whichError=er[hg];
	ui=y9[cfd].substring(0,whichError.ho);
	ui+=nw;
	ui+=y9[cfd].substring(whichError.ho+whichError.s4.length,y9[cfd].length);
	y9[cfd]=ui;
	whichError.replaced=true;
	var ro=(nw.length-whichError.s4.length);
	for(var i=hg+1;i<ec && er[i].fd==er[hg].fd;i++) {
		er[i].ho+=ro;
	}
}
function f() {
	if(directEdit=="NO"){
		parent.myOpener.updateForm(cfd,y9[cfd]);
		spellCheckClosing();
		parent.close();
	} else {
		if(ieMode=="NO")
		{
			var ez=eval("myOpener."+myOpener.getSpellCheckItem(cfd));
			ez.setSelectionRange(0,0);
		} else
		{
		   var ez=eval("myOpener."+myOpener.getSpellCheckItem(cfd)).createTextRange();
			ez.moveStart("textedit",1);
			ez.moveEnd("textedit",1);
			ez.select();
		}
		spellCheckClosing();
		self.close();
	}
}

function blockedMessage()
{
	alert("This operation is not permitted for this word.");
}

function cx() {
	if(!blocked)
		runChecker();
	else
		blockedMessage();
}
function vb() {
	if(!blocked)
	{
		po[po.length]=er[p2-1].s4;
		runChecker();
	} else
		blockedMessage();
}
function uu() {
	if(bv.pp.selectedIndex>=er[p2-1].c) {
		bv.pp.selectedIndex=er[p2-1].c-1;
	}
	if(bv.pp.selectedIndex!=-1)
		bv.replacement.value=bv.pp.options[bv.pp.selectedIndex].text;
        showErrorInContext(oy);
}
function autoUpdate() {
	if(bv.pp.selectedIndex!=-1) {
		uu();
		u8();
	}
}
function i12() {
	var now = new Date();
	fixDate(now);
	now.setTime(now.getTime() + 10 * 365 * 24 * 60 * 60 * 1000);
	learned+=er[p2-1].s4+",";
	setCookie("learned",learned,now);
	runChecker();
}
function learnword() {
	if(!blocked)
	{
		// really need to open learn dialog in place... and redraw correction GUI after learn is complete
	
		// open learn words dialog with prompt to learn word globally, personally at server or via cookie
		// based on user permissions, var globalupdate, var serverdictid (null means use cookie)
		// get current window location, height, width and open the window next to it if it remains on screen, adjust
		// location so that the window stays on screen, disable elements on original window while learn window is 
		// open.

		if(confirmAfterLearn){
			if(confirm("You are adding '"+er[p2-1].s4+"' to your personal dictionary! Choose Ok to continue or Cancel to abort.")) {
				i12();
			}
		} else {
			i12();
		}
	} else
		blockedMessage();
}
//--></SCRIPT>
<%
		}
	}
	if(errorPanel) {
%>
</head>
<body bgcolor=#dddddd>
<table width=100% height=100% cellspacing=0 cellpadding=0 border="0">
	<tr>
		<td align=center valign=middle>
			<font face="Verdana, Helvetica, Arial, san-serif" size=4 color=red>ERROR</font><p>
			<font face="Verdana, Helvetica, Arial, san-serif" size=2>The spell checker is unavailable. If you continue to receive this message, please contact your server administrator.</font><p>
			<font face="Verdana, Helvetica, Arial, san-serif" size=3 color=red><%=errorDiagString%></font><p>
			<form>
				<input type=button name="closeButton" value="Ok" onclick="spellCheckClosing(); parent.close();"><br>
			</form>
		</td>
	</tr>
</table>
</body>
<%
	} else {
		if((runTextChecker && directEdit.equals("YES") && drawPanel) || !runTextChecker) {
			%> 
</head>
<body bgcolor="#dddddd">
<form action="" name="results" method="POST">
<table width="100%" height="100%" cellspacing=0 cellpadding=0 border="0">
	<tr valign=middle>
		<td align=center valign=center>
			<table align=center border="0" cellspacing=0 cellpadding=0>
				<tr valign=middle>
					<td>&nbsp;</td>
					<td align=right>
						<table cellspacing=2 cellpadding=0 border="0">
<tr><td><font size=1 face="Verdana, Helvetica, Arial, sans-serif"><b><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("notfound")%></b></font></td></tr>
<tr><td>&nbsp;<input type=text readonly name="notFound" size=12 style="width:160px; font: Verdana, Arial, sans-serif; background-color:#dddddd;"></td></tr>
<tr><td><font size=1 face="Verdana, Helvetica, Arial, sans-serif"><b><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("replacewith")%></b></font></td></tr>
<tr><td>&nbsp;<input type=text name="replacement" size=12 style="width:160px; font: Verdana, Arial, sans-serif"></td></tr>
<tr><td><font size=1 face="Verdana, Helvetica, Arial, sans-serif"><b><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("suggestions")%></b></font></td></tr>
<%
		if(directEdit.equals("YES")) 	{
%><tr><td>&nbsp;<SELECT name="pp" SIZE="4" style="width:160px; font: Verdana, Arial, sans-serif" onclick='showErrorInContext(oy);' ondblclick='autoUpdate();'  onchange='uu();'>
<%		} else { 
%><tr><td>&nbsp;<SELECT name="pp" SIZE="4" style="width:160px; font: Verdana, Arial, sans-serif" onclick='parent.showErrorInContext(oy);' ondblclick='parent.autoUpdate();'  onchange='parent.uu();'>
<%		}
%>
<OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
<OPTION></OPTION> <OPTION></OPTION> <OPTION></OPTION> <OPTION></OPTION> <OPTION></OPTION>
<OPTION></OPTION> <OPTION></OPTION> <OPTION></OPTION> <OPTION></OPTION>
							</SELECT></td></tr>
						</table>
					</td>
					<td>&nbsp;</td>
					<td align=left>

<%				if(compatibilityMode==false)
{
%>
<button name=replace style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.u8(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("replace")%></button><br>
<button name=replaceAll style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.tru(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("replaceall")%></button><br>
<button name=ignore style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.cx(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("ignore")%></button><br>
<button name=ignoreAll style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.vb(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("ignoreall")%></button><br>
<%
				if(enableLearn!=null && enableLearn.equals("YES")) {
%>
<button name=learn style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.learnword(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("learn")%></button>
<%				}
%>
<p>
<button name=finish style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.f(); return false;" class="finishbutton"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("finish")%></button><br>
<% }
else
{
%>
<a href="#" name=replace style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.u8(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("replace")%></a><br>
<a href="#" name=replaceAll style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.tru(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("replaceall")%></a><br>
<a href="#" name=ignore style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.cx(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("ignore")%></a><br>
<a href="#" name=ignoreAll style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.vb(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("ignoreall")%></a><br>
<%
				if(enableLearn!=null && enableLearn.equals("YES")) {
%>
<a href="#" name=learn style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.learnword(); return false;"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("learn")%></a>
<%				}
%>
<p>
<a href="#" name=finish style="background:#cccccc;width:95px;font: 8pt verdana,arial,sans-serif" onclick="parent.f(); return false;" class="finishbutton"><%=java.util.ResourceBundle.getBundle("com/wallstreetwise/app/jspell/gui/i18n",guiLocale).getString("finish")%></a><br>
<% } %>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<SCRIPT type="text/javascript"><!--
document.open();
document.write(" ");
document.close();
	<%
			if(directEdit.equals("YES")){
				%>
var bv=document.forms["results"];
runChecker();
				<%
			} else {
				%>
parent.bv=document.forms["results"];
parent.runChecker();
parent.blt();
				<%
			}
		%>
//--></SCRIPT>
<%
		} else {
			if(directEdit.equals("NO") && drawPanel) { 
				%> 
</head>
<%
	String proxy=tools.getProxy(request);
	String blankURL=tools.getBlankURL(request);
	if(!hidePreviewPanel) {
%>
<FRAMESET cols="60%,40%" FRAMEBORDER=1 BORDER=1 FRAMESPACING=0 >
   <FRAME name="checkPanel" src="<%=proxy%>?guiLanguage=<%=guiLanguage%>&guiCountry=<%=guiCountry%>&confirmAfterLearn=<%=confirmAfterLearn%>&confirmAfterReplace=<%=confirmAfterReplace%>&checkPanel=511&enableLearn=<%=enableLearn%>&imagePath=<%=java.net.URLEncoder.encode(imagePath)%>&styleSheetURL=<%=java.net.URLEncoder.encode(styleSheetURL)%>&supplementalDictionary=<%=java.net.URLEncoder.encode(supplementalDictionary)%>" SCROLLING=NO NORESIZE>
   <FRAMESET rows="18%,*">
   <FRAME name="heading" src="<%=blankURL%>" SCROLLING=NO NORESIZE>
   <FRAME name="preview" src="<%=blankURL%>" SCROLLING=NO NORESIZE>
   </FRAMESET>
</FRAMESET>
<%
	} else {
%>
<FRAMESET cols="100%,*" FRAMEBORDER=1 BORDER=1 FRAMESPACING=0 >
   <FRAME name="checkPanel" src="<%=proxy%>?guiLanguage=<%=guiLanguage%>&guiCountry=<%=guiCountry%>&confirmAfterLearn=<%=confirmAfterLearn%>&confirmAfterReplace=<%=confirmAfterReplace%>&checkPanel=511&enableLearn=<%=enableLearn%>&imagePath=<%=java.net.URLEncoder.encode(imagePath)%>&styleSheetURL=<%=java.net.URLEncoder.encode(styleSheetURL)%>&supplementalDictionary=<%=java.net.URLEncoder.encode(supplementalDictionary)%>" SCROLLING=NO NORESIZE>
   <FRAMESET rows="18%,*">
   <FRAME name="heading" src="<%=blankURL%>" SCROLLING=NO NORESIZE>
   <FRAME name="preview" src="<%=blankURL%>" SCROLLING=NO NORESIZE>
   </FRAMESET>
</FRAMESET>
<%
	}
			}
		}
	}
%>
<script  type="text/javascript" language="JavaScript1.1"><!--
for (var i=0; i<document.images.length; i++) document.images[i].onmousedown=right;
for (var i=0; i<document.links.length; i++) document.links[i].onmousedown=right;
//--></script>
</html>