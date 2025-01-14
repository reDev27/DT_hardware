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
    <link rel="icon" href="image/logo_DT_solo_pc.png">
</head>
<link href="libraries/fontawesome-free-5.15.3-web/css/all.css" rel="stylesheet" type="text/css">
<script defer src="libraries/fontawesome-free-5.15.3-web/js/all.js"></script>
<script src="libraries/Utilities.js"></script>
<link rel="stylesheet" type="text/css" href="viewProductStyle.css">
<link id="linkCssLibrary" rel="stylesheet" type="text/css" href="homepageStyle.css">
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="libraries/jquery-ui-1.12.1/jquery-ui.css">
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">
<body>
<style>
    #btnSearch svg
    {
        color: #86d804;
    }
</style>

<main id="alfaContainer"  style="width: 80%; margin: 0 -10% 0 10%;position: center; background-color: white">
    <header class="row" id="intestazione">
    <span>
      <span class="cols-1"><i class="fas fa-phone" color="#8bc52e"></i> Numero di telefono: 0123456789</span>
		<span class="cols-8" ><i class="fas fa-envelope" color="#8bc52e"></i>E-mail: DTHardware@gmail.com</span>
      <span class="cols-1" id="accediRef"><a href="login.html" title="Accedi o crea un account">Accedi</a> oppure <a href="iscrizione.html" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

    <span class="row" id="searchAndCarrello" style="flex-wrap: nowrap; width: 92%">
   <span class="cols-4" id="logoSpan"><a href="homepage.html"><img src="image/logo_DT.png" width="100%" id="logoIcon" alt="Il nostro logo"></a></span>
   <span class="col-3" id="txtSearchSpan"><textarea id="txtSearch" placeholder="Cerca..." rows="1" class="cols-4" oninput="searchProducts()" style="resize: none"></textarea><div id="suggerimentiDiv" class="list-group" style="position: absolute"></div></span><span id="btnCercaSpan" class="col-1"><button id="btnSearch" style="width: 30px; height: 30px; padding: 0; margin-top: 25%" class="btn btn-light" onclick="searchRedirectUtility()"><i class="fas fa-search"></i></button></span>
    <span class="col-3" id="btnCarrelloSpan"><button id="btnCarrello" type="button" class="btn btn-success cols-4">Carrello</button></span>
</span>

<div class="container">
    <h5>Ordini</h5>
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
                    <td id="idTotaleProduct"><%= Math.round(order.getTotale() * 100.0) / 100.0%></td>
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
            <a class="col-12 text-center" href="InfoPersonali">Informazioni personali</a>
            <a class="col-12 text-center" href="ShowOrdini">Ordini</a>
            <a class="col-12 text-center" href="ShowChangeIndirizzi">Indirizzi</a>
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
        redirectToCarrello()
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
