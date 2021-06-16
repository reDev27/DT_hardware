<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 04/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Benvenuto</title>
</head>
<body>
<link>
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/homepageUtilities.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="homepageStyle.css">
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">



<main id="alfaContainer" style="width: 80%; margin-left: 10%; margin-right: -10%; position: center">
<div class="row" id="intestazione">
    <span class="col-xl-9">
        <img class="col" src="image/telephone.png">Numero di telefono: 0123456789
        <img class="col" src="image/email.png">E-mail: DTHardware@gmail.com
    </span>
    <span class="col-xl-3"><a href="index.jsp" title="Accedi o crea un account">Accedi</a> oppure <a href="index.jsp" title="Accedi o crea un account">crea un account</a></span>
</div>




<textarea id="txtSearch" rows="1" ></textarea>
<button id="btnCarrello" style="width: 10% ; height: 5%"></button>
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
    $(
        function()
        {
            $( "#selectable" ).selectable();
        }
    );
</script>

<<ol id="selectable">
    <li class="ui-state-default">1</li>
    <li class="ui-state-default">2</li>
    <li class="ui-state-default">3</li>
    <li class="ui-state-default">4</li>
    <li class="ui-state-default">5</li>
    <li class="ui-state-default">6</li>
    <li class="ui-state-default">7</li>
    <li class="ui-state-default">8</li>
    <li class="ui-state-default">9</li>
    <li class="ui-state-default">10</li>
</ol>
</main>
</body>
</html>