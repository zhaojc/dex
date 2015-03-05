<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>

<html>

<jsp:include page='/commun/commun.jsp' flush="true"/>

<script type="text/javascript">
	function setNarrationTemporaire(cle, site, lien, lienSite, premiereNarrationTemporaire, narrationTemporaire) {
		document.forms(0).cle.value = cle;
		document.forms(0).site.value = site;
		document.forms(0).lien.value = lien;
		document.forms(0).lienSite.value = lienSite;
		document.forms(0).premiereNarrationTemporaire.value = premiereNarrationTemporaire;
		document.forms(0).narrationTemporaire.value = narrationTemporaire;
	}
	function soumettre(){
		document.forms(0).submit();
	}
</script>
<body>
<html:form action="/narration/sauvegardeNarrationTemporaire"  >
	<html:text property="cle"/>
	<html:text property="site"/>
	<html:text property="lien"/>
	<html:text property="lienSite"/>
	<html:text property="premiereNarrationTemporaire"/>
	
	<html:textarea property="narrationTemporaire"/>
	
</html:form>
</body>
</html>
