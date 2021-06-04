<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 01/06/2021
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="${pageContext.request.contextPath}/libraries/jQuery_current.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/getProductServ" method="get">
    <input type="submit" id="btn01" value="test">
</form>

<script>
    $("#btn01").click
    (
        function()
        {
            alert("stai mandando una request al server");
        }
    );
</script>
</body>
</html>
