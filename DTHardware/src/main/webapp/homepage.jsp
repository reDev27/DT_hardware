<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 04/06/2021
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="${pageContext.request.contextPath}/libraries/jQuery_current.js"></script>
<head>
    <title>Title</title>
</head>
<img>
<p id="nTelefono">Numero di telefono: 0123456789</p>
<p id="email">E-mail: DTHardware@gmail.com</p>
<!--<p id="accessoRegistrazione">accedi o crea un account</p>-->
<pre id="accediRegistrati">
<p><a href="index.jsp" title="Accedi o crea un account">Accedi</a> oppure <a href="index.jsp" title="Accedi o crea un account">crea un account</a>
</p>
</pre>
<img src=""></img>
<textarea id="txtSearch"></textarea>
<button id="btnCarrello"></button>
<br>
<h3 id="names"></h3>
<script>
    $(document).ready
    (
        function()
        {
            $.get("getProductServ", function (resp)
                {
                    alert(resp[0].marca);
                }
            );
        }
    );
</script>

</body>
</html>