<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CurrentTime</title>
</head>
<body>
<%= request.getAttribute("time") %>
<br/>
<%= request.getAttribute("nick") %>
<br/>
<%= request.getAttribute("name") %>
</body>
</html>
