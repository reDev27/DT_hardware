<%@ page import="Model.Cliente" %>
<%@ page import="Model.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.DAO.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 01/07/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<%
    Cliente cliente= (Cliente) session.getAttribute("cliente");
    ArrayList<Order> orders= (ArrayList<Order>) session.getAttribute("orders");
%>
<head>
    <title><%= cliente.getUsername()%></title>
</head>
<script src="libraries/Utilities.js"></script>
<link rel="stylesheet" type="text/css" href="viewProductStyle.css">
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="libraries/jquery-ui-1.12.1/jquery-ui.css">
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">
<body>
<main id="alfaContainer"  style="width: 80%; margin: 0 -10% 0 10%;position: center; background-color: white">
    <header class="row" id="intestazione">
    <span>
      <img class="cols-1" src="image/telephone.png"> <span class="cols-1">Numero di telefono: 0123456789</span>
      <img class="cols-1" src="image/email.png" style="margin-left: 2%"> <span class="cols-8" >E-mail: DTHardware@gmail.com</span>
      <span class="cols-1" id="accediRef"><a href="login.html" title="Accedi o crea un account">Accedi</a> oppure <a href="iscrizione.html" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

    <span class="row" id="searchAndCarrello">
   <span class="cols-4" id="logoSpan"><a href="homepage.html"><img src="image/logo.png" width="100%" id="logoIcon" alt="Il nostro logo"></a></span>
   <span class="cols-4" id="txtSearchSpan"><textarea id="txtSearch" placeholder="Cerca..." rows="1" class="cols-4 "></textarea></span>
   <span class="cols-4" id="btnCarrelloSpan"><button type="button" class="btn btn-success cols-4" id="btnCarrello">Carrello</button></span>
</span>

<div class="container">
    <h5>Ordini</h5>
    <!--<div class="row">

        <span id="ordineSpan"></span>
    </div>-->
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Identificativo</th>
            <th>Totale</th>
            <th>Data d'acquisto</th>
        </tr>
        </thead>
        <tbody id="bodyTable">
        <%
            int i=0;
            for(Order order:orders)
            {
        %>
                <tr onclick='showDettagliOrdine(ordersJson,<%= i%>)'>
                    <td id="idProduct<%= i%>"><%= order.getId()%></td>
                    <td id="idTotaleProduct"><%= order.getTotale()%></td>
                    <td id="idDataAcquisto"><%= DateUtil.getStringFromCalendar(order.getDataAcquisto())%></td>
                </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>

<div id="dettagliOrdineDiv" style="padding-left: 2%">

</div>

    <div id="pieDiPagina" class="row jumbotron">
        <div class="col-4">
          <span id="infoSpan" class="row">
            <h5 class="col-12">Informazioni</h5>
            <a class="col-12" href="terminiECondizioni.html">Termini e condizioni</a>
            <a class="col-12" href="metodiDiPagamento.html">Metodi di pagamento</a>
            <a class="col-12" href="politicheReso.html">Politiche di reso</a>
          </span>
        </div>
        <div class="col-4">
          <span id="accountSpan" class="row">
            <h5 class="col-12 text-center">Il tuo account</h5>
            <a class="col-12 text-center" href="homepage.html">Informazioni personali</a>
            <a class="col-12 text-center" href="homepage.html">Ordini</a>
            <a class="col-12 text-center" href="homepage.html">Indirizzi</a>
          </span>
        </div>
        <div class="col-4">
          <span id="contattaciSpan" class="row">
            <h5 class="col-12 text-end">Contattaci</h5>
            <a class="col-12 text-end" href="homepage.html">Via pippo 5</a>
            <a class="col-12 text-end" href="homepage.html">0123456789</a>
            <a class="col-12 text-end" href="homepage.html">ciao@hello.com</a>
          </span>
        </div>
    </div>

    <script>
        $( function() {
            $( "#dialogEsito" ).dialog({autoOpen: false});
        } );
    </script>

    <div id="dialogEsito" title="Espressione non valida">
        <p id="esitoP"></p>
    </div>

    <script>
        var ordersJson=<%= session.getAttribute("ordersJson")%>;

        $(document).ready
        (
            $.ajax
            (
                {
                    url : "IsLoggedServ",
                    method : "get",
                    dataType : "json",
                    success : function (data)
                    {
                        if(data.L)
                        {
                            document.getElementById("accediRef").innerHTML = "<a href=\"#accediRef\" id='refLogout' title=\"Log-out\">Esci</a>";
                            $("#refLogout").on("click",
                                function ()
                                {
                                    $.ajax
                                    (
                                        {
                                            url : "AuthServ",
                                            method: "get",
                                        }
                                    )
                                    window.location.href="homepage.html";
                                }
                            )
                        }
                    },
                    error : function () {alert("error")}
                }
            )
        )
    </script>
</main>
</body>
</html>