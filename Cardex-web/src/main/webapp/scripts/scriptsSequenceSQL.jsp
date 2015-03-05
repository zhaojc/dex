<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>

<SCRIPT FOR=window EVENT=onunload>

	var sequence = document.getElementsByName("sequence")[0];
	fermerConnexion(sequence.value);
	
</SCRIPT>

<html:hidden property="sequence"/>
