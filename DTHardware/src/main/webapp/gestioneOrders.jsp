<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 07/07/2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <title>Gestione prodotti</title>
</head>
<script src="libraries/managerUtilities.js"></script>
<link href="libraries/fontawesome-free-5.15.3-web/css/all.css" rel="stylesheet" type="text/css">
<script defer src="libraries/fontawesome-free-5.15.3-web/js/all.js"></script>
<script src="libraries/Utilities.js"></script>
<link id="linkCssLibrary" rel="stylesheet" type="text/css" href="homepageStyle.css">
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="libraries/jquery-ui-1.12.1/jquery-ui.css">
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">
<body>
<main id="alfaContainer"  style="width: 80%; margin: 0 -10% 0 10%;position: center; background-color: white">
    <header class="row" id="intestazione">
    <span>
       <span class="cols-1"><i class="fas fa-phone" color="#8bc52e"></i> Numero di telefono: 0123456789</span>
		<span class="cols-8" ><i class="fas fa-envelope" color="#8bc52e"></i>E-mail: DTHardware@gmail.com</span>
      <span class="cols-1" id="accediRef"><a href="login.html" title="Accedi o crea un account">Accedi</a> oppure <a href="iscrizione.html" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

    <span class="row" id="searchAndCarrello" style="flex-wrap: nowrap; width: 92%">
    <span class="col-4" id="logoSpan"><a href="manager.html"><img src="image/logo_DT.png" width="100%" id="logoIcon"></a></span>
	</span>

    <div class="container-fluid">
        <h5>Gestione prodotti.</h5>

        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Totale</th>
                    <th>Data d'acquisto</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody id="tableGestioneOrders">
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <span class="col-6" style="display: grid; place-items: center"><button id="btnModifica" class="btn btn-light">Modifica</button></span>
        <span class="col-6" style="display: grid; place-items: center"><button id="btnElimina" class="btn btn-danger">Elimina</button></span>
    </div>

    <div id="modificaOption" class="container-fluid" style="margin-top: 5%">
        <input type="text" id="idModify" name="idModify" placeholder="id" style="margin-bottom: 2%" disabled><br>
        <textarea type="text" id="fatturaModify" name="fatturaModify" placeholder="Fattura" style="margin-bottom: 2%; height: 65%; width: 50%"></textarea><br>
        <input type="text" id="totaleModify" name="totaleModify" placeholder="Totale" style="margin-bottom: 2%"><br>
        <input type="text" id="dataAcquistoModify" name="dataAcquistoModify" placeholder="Data d'acquisto" style="margin-bottom: 2%"><br>
        <input type="text" id="usernameModify" name="usernameModify" placeholder="Username" style="margin-bottom: 2%" disabled><br>
        <button id="btnInvioOrderModify" class="btn btn-success">Invio</button><br>
    </div>

    <div id="eliminaOption" class="container-fluid" style="margin-top: 5%">
        <p>Sei sicuro di voler eliminare il seguente ordine: <mark id="toEliminateOrderId"></mark> <mark id="toEliminateOrder"></mark></p>
        <button id="btnInvioOrderDelete" class="btn btn-danger">Invio</button>
    </div>

    <script>
        $( function() {
            $( "#dialogEsito" ).dialog({autoOpen: false});
        } );
    </script>

    <div id="dialogEsito" title="Espressione non valida">
        <p id="esitoP"></p>
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
        var orders=<%= request.getAttribute("ordersJson")%>;
        showOrdersGestione(orders);
        $("#modificaOption").hide();
        $("#eliminaOption").hide();
        $("#btnModifica").click(function ()
        {
            $("#modificaOption").show();
            $("#eliminaOption").hide();
        });
        $("#btnElimina").click(function ()
        {
            $("#eliminaOption").show();
            $("#modificaOption").hide();
        });
        $("#btnInvioOrderModify").click(function ()
        {
            modifyOrder();
        });
        $("#btnInvioOrderDelete").click(function ()
        {
            deleteOrder();
        });
    </script>

    <script>
            $.ajax
            (
            {
                url : "IsLoggedServ",
                method : "post",
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
                else
            {
                window.location.href="homepage.html";
            }
            },
                error : function () {alert("error")}
            }
            )
    </script>

</main>
</body>
</html>
