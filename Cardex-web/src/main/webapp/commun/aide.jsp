<jsp:include page='/scripts/aideAjax.jsp' flush="true"/>
<iframe id="selectBlockerAide" style="position:absolute;visibility: hidden;height:250;width:350;left: 140px; top: 110px;" frameBorder="0" scrolling="no" src='<%= request.getContextPath() + "/vide.html"%>'></iframe>
<DIV id="aide" contentEditable=false ALIGN=center 	
	STYLE="overflow:scroll; height:250; visibility= 'hidden';
     width:350;background-color: #fafaf5; font:normal normal normal 12pt Arial;
     border:'0.05cm groove darkgray'; scrollbar-base-color:lightgray;
	 z-index: 1; position: absolute; left: 140px; top: 110px;" 
	onfocusout="this.style.visibility = 'hidden';document.all['selectBlockerAide'].style.visibility = 'hidden'">
</DIV>
<SCRIPT language="JavaScript" type="text/javascript">
doAide(document.forms(0).name,"0");
</SCRIPT>