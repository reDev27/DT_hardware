<%@ page import="Model.Product" %>
<%@ page import="Model.DAO.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 18/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<% Product product=(Product) request.getAttribute("product");%>
<head>
    <meta charset="UTF-8">
    <title><%= product.getMarca() + " " + product.getModello()%></title>
</head>
<script src="libraries/Utilities.js"></script>
<link rel="stylesheet" type="text/css" href="viewProductStyle.css">
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="libraries/jquery-ui-1.12.1/jquery-ui.css">
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">
<body>
<style>
    #selectableTableProducts .ui-selecting
    {
        background: rgba(188, 255, 179, 0.89);
    }

    #selectableTableProducts .ui-selected
    {
        background: #188e08;
        color: #000000;
    }

    #prodottoSpan p
    {
        max-width: 80%;
        width: 80%;
        margin:auto;
        padding: .5%;
    }
    #prodottoSpan button
    {
        width: 80%;
        margin: auto;
    }

    #spinnerParag .ui-spinner-down
    {
        background-image: url("libraries/jquery-ui-1.12.1/images/ui-icon-down.png");
        width: 16px;
        height: 16px;
        background-repeat: no-repeat ;
        display: block;
        cursor: pointer;
    }

    #spinnerParag .ui-spinner-up
    {
        background-image: url("libraries/jquery-ui-1.12.1/images/ui-icon-up.png");
        width: 16px;
        height: 16px;
        background-repeat: no-repeat ;
        display: block;
        cursor: pointer;

    }

    #spinnerParag span
    {
        width: 80%;
    }
</style>
<main id="alfaContainer"  style="width: 80%; margin: 0 -10% 0 10%;position: center; background-color: white">
    <header class="row" id="intestazione">
    <span>
      <img class="cols-1" src="image/telephone.png"> <span class="cols-1">Numero di telefono: 0123456789</span>
      <img class="cols-1" src="image/email.png" style="margin-left: 2%"> <span class="cols-8" >E-mail: DTHardware@gmail.com</span>
      <span class="cols-1" id="accediRef"><a href="index.jsp" title="Accedi o crea un account">Accedi</a> oppure <a href="index.jsp" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

  <span class="row" id="searchAndCarrello">
    <span class="cols-4" id="logoSpan"><a href="homepage.html"><img src="image/logo.png" width="100%" id="logoIcon" alt="Il nostro logo"></a></span>
    <span class="cols-4" id="txtSearchSpan"><textarea id="txtSearch" placeholder="Cerca..." rows="1" class="cols-4"></textarea></span>
    <span class="cols-4" id="btnCarrelloSpan"><button id="btnCarrello" type="button" class="btn btn-success cols-4">Carrello</button></span>
  </span>


<div class="row" style="margin-top: 3%">
  <span class="col-3">
    <h4 style="margin-left: 5%">Categorie</h4>
    <div id="categoriesList">
        <ul id="categoriesListUl"></ul>
    </div>
  </span>
  <span class="col-9">
    <div id="tableProduct" class="row">
        <span class="col-6">
            <img src="<%= product.getImmagine()%>" width="100%">
        </span>
        <span class="col-6">
            <span class="col-12"><h4 id="headerProducts"> <%=product.getMarca()%></h4><h5 style="margin-top: 7px"><%=product.getModello()%></h5></span>
            <span class="col-12" style="display: flex"><p>Identificativo: </p><%=product.getCodiceABarre()%></span>
            <span class="col-12" style="display: flex"><b class="text-success" style="font-family: Helvetica,serif; font-size: 150%"><%=product.getPrezzo()%> &#x20AC</b><p style="margin-top: 1%; margin-left: 4%">tasse incluse</p></span>
            <span class="col-12" style="display: flex"><p id="spinnerParag" style="width: 50%"> <label for="quantitaSpinner">Quantità:</label><input id="quantitaSpinner" name="spinner" value="1" style="width: 80% ;margin:1%"></p></span>
            <span class="col-12" ><button id="btnAggiungiAlCarrello" type="button" class="btn btn-success">Aggiungi al carrello</button></span>
            <span class="col-12" style="display: block"><i><%=product.isDisponibilita()?"Disponibile":"Esaurito"%></i></span>
        </span>
    <div id="specificheDiv" style="width: 90%; margin-top: 5%">
          <ul>
            <li><a href="#divDescrizione">Descrizione</a></li>
            <li><a href="#divSpecifiche">Specifiche</a></li>
          </ul>
          <div id="divDescrizione">
            <p><%=product.getDescrizione()%></p>
          </div>
          <div id="divSpecifiche">
            <p><%=product.getSpecifiche()%></p>
          </div>
    </div>
    </div>
  </span>
</div>

<div id="pieDiPagina" class="row jumbotron">
    <div class="col-4">
    <span id="infoSpan" class="row">
      <h5 class="col-12">Informazioni</h5>
      <a class="col-12" href="homepage.html">Termini e condizioni</a>
      <a class="col-12" href="homepage.html">Metodi di pagamento</a>
      <a class="col-12" href="homepage.html">Politiche di reso</a>
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

<script>


    $( function() {
        $( "#specificheDiv" ).tabs();
    } );

    $(
        function()
        {
            $( "#categoriesList" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
            $( "#categoriesList li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
            $("#categoriesList").css("border", 0);
        }
    );

    $
    (
        function ()
        {
            $( "#quantitaSpinner" ).spinner({
                min: 1,
                max: 2500,
                step: 1,
                start: 1,
                numberFormat: "C"
            });
        }
    )

    var categorie;
    $.ajax
    (
        {
            type: "GET",
            url: "getCategoriesServ",
            dataType: "json",
            //data
            success: function (data)
            {
                buildTableCategories(data);
            },
            error: function (){alert("error")}
        }
    );

    var spinner = $( "#quantitaSpinner" ).spinner();
    var prodotto=
        {
            "codiceABarre" : "<%= product.getCodiceABarre()%>",
            "marca" : "<%= product.getMarca()%>",
            "modello" : "<%= product.getModello()%>",
            "prezzo" : <%= product.getPrezzo()%>,
            "descrizione" : "<%= product.getDescrizione()%>",
            "specifiche" : "<%= product.getSpecifiche()%>",
            "immagine" : "<%= product.getImmagine()%>",
            "disponibilita" : <%= product.isDisponibilita()%>,
            "quantitaProdotto" : <%= product.getQuantitaProdotto()%>,
            "dataInserimento" : "<%= DateUtil.getStringFromCalendar(product.getDataInserimento())%>"
        };
    $("#btnAggiungiAlCarrello").on("click", function ()
    {
        prodotto.quantitaCarrello=$("#quantitaSpinner").spinner("value");
        aggiornaCarrello(prodotto);
    });
    redirectToCarrello();
</script>

</main>
</body>
</html>
