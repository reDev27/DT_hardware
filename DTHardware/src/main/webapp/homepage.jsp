<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 04/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link>
<script src="${pageContext.request.contextPath}/libraries/jQuery_current.js"></script>
<script src="${pageContext.request.contextPath}/libraries/homepageUtilities.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
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
<textarea id="txtSearch" rows="1" ></textarea>
<button id="btnCarrello"></button>
<br>
<div id="categoriesList">
    <table id="tableCategories">

    </table>
</div>
<div id="productList">

</div>
<script>
    var categorie;
    $.ajax
    (
        {
            type: "GET",
            url: "${pageContext.request.contextPath}/getCategoriesServ",
            dataType: "json",
            //data
            success: function (data)
            {
                buildTableCategories(data);
            },
            error: function (){alert("error")}
        }
    )

</script>
</body>
</html>