<script>

function VerificationActiviteUtilisateur() {
	var url = "<%=request.getContextPath()%>/VerificationActiviteUtilisateur";
    var req = initRequest(url);
    
    req.onreadystatechange = function() {
    	
    	if (req.readyState == 4) {
    		
    		if (req.status == 200) {
            	
            	if (req.responseText == "false")
            		windowClose();
            }else
            	windowClose();
        }
    };
	req.open("GET", url, true);
    req.send(null);

    setTimeout("VerificationActiviteUtilisateur();", 900000); // 1000 = 1 sec / 60000 = 1 minutes / 900000 = 15 minutes
}

</script>