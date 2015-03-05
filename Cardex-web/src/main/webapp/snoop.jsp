<html>
<!--
  Copyright (c) 1999 The Apache Software Foundation.  All rights 
  reserved.
-->
<body bgcolor="white">
<h1> Request Information </h1>
<font size="4">

<%@ page session="false" %>

JSP Request Method: <%= request.getMethod() %>
<br>
Request URI: <%= request.getRequestURI() %>
<br>
Request URL: <%= request.getRequestURL() %>
<br>
Request ContextPath: <%= request.getContextPath() %>
<br>
Request Protocol: <%= request.getProtocol() %>
<br>
Servlet path: <%= request.getServletPath() %>
<br>
Path info: <%= request.getPathInfo() %>
<br>
Path translated: <%= request.getPathTranslated() %>
<br>
Query string: <%= request.getQueryString() %>
<br>
Content length: <%= request.getContentLength() %>
<br>
Content type: <%= request.getContentType() %>
<br>
Server name: <%= request.getServerName() %>
<br>
Server port: <%= request.getServerPort() %>
<br>
Remote user: <%= request.getRemoteUser() %>
<br>
User Principal: <%= request.getUserPrincipal() %>
<br>
Remote address: <%= request.getRemoteAddr() %>
<br>
Remote host: <%= request.getRemoteHost() %>
<br>
Authorization scheme: <%= request.getAuthType() %> 
<br>
The browser you are using is <%= request.getHeader("User-Agent") %>
<hr>
HTTP_HOST is: <%= request.getHeader("host") %>
<br>
SERVER_NAME is: <%= request.getHeader("SERVER_NAME") %>
<hr>
cookies is: <%= request.getCookies() %>
<br>
cookie is: <SCRIPT language='JavaScript' type='text/javascript'>document.write(document.cookie);</SCRIPT>
<br>
ct-remote-user is: <%= request.getHeader("ct-remote-user") %>
<br>
ct-auth-type is: <%= request.getHeader("ct-auth-type") %>
<br>
ct-web-svr-id is: <%= request.getHeader("ct-web-svr-id") %>
<hr>
</font>
</body>
</html>
