<%@ page import="Model.Product" %>
<%@ page import="Model.DAO.DateUtil" %>
<%@ page import="Model.StringUtility" %>
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
    #selectableTableProducts .ui-selecting
    {
        background: rgba(188, 255, 179, 0.89);
    }

    #selectableTableProducts .ui-selected
    {
        background: #86d804;
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
    #btnCarrello{
        background-color: #86d804;
    }

    #spinnerParag span
    {
        width: 80%;
    }

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

    <script>
        $( function() {
            $( "#specificheDescrizioneDiv" ).tabs();
        } );

        $
        (
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

        var spinner = $( "#quantitaSpinner" ).spinner();
    </script>

<div class="row" style="margin-top: 3%">
  <span id="categorySpan" class="col-3">
    <h4 style="margin-left: 5%">Categorie</h4>
    <div id="categoriesList">
        <ul id="categoriesListUl"></ul>
    </div>
  </span>
  <span id="infoProdottoSpan" class="col-9">
    <div id="tableProduct" class="row">
        <span class="col-6">
            <img src="<%= product.getImmagine()%>" width="100%">
        </span>
        <span class="col-6">
            <span class="col-12"><h4 id="headerProducts"> <%=product.getMarca()%></h4><h5 style="margin-top: 7px"><%=product.getModello()%></h5></span>
            <span class="col-12" style="display: flex"><p>Identificativo: </p><%=product.getCodiceABarre()%></span>
            <span class="col-12" style="display: flex"><b class="text-success"  style="font-family: Helvetica,serif;font-size: 150%; color:#86d804 !important;"><%=product.getPrezzo()%> &#x20AC</b><p style="margin-top: 1%; margin-left: 4%">tasse incluse</p></span>
            <span class="col-12" style="display: flex"><p id="spinnerParag" style="width: 50%"> <label for="quantitaSpinner">Quantità:</label><input id="quantitaSpinner" name="spinner" value="1" style="width: 80% ;margin:1%"></p></span>
            <span class="col-12" ><button id="btnAggiungiAlCarrello" type="button" class="btn btn-success">Aggiungi al carrello</button></span>
            <span class="col-12" style="display: block"><i id="iconDisponibilita"></i><%=product.isDisponibilita()?"Disponibile":"Esaurito"%></span>
            <%
                if(product.isDisponibilita())
                {
            %>
            <script>
                $("#iconDisponibilita").addClass("fas fa-check-circle");
                $("#iconDisponibilita").css("color", "#198754");
            </script>
            <%
                }
                else
                {
            %>
                <script>
                    $("#iconDisponibilita").addClass("fas fa-times-circle");
                    $("#iconDisponibilita").css("color", "rgb(221 47 47)");
                </script>
            <%
                }
            %>
        </span>
    <div id="specificheDescrizioneDiv" style="width: 90%; margin-top: 5%">
          <ul>
            <li id="btnDescrizioneDiv"><a href="#divDescrizione">Descrizione</a></li>
            <li id="btnSpecificheDiv"><a href="#divSpecifiche">Specifiche</a></li>
          </ul>
          <div id="divDescrizione">
              <p><%=StringUtility.subVirgolette(StringUtility.subBlankNWithBR(product.getDescrizione()))%></p>
          </div>
          <div id="divSpecifiche">
              <p><%= StringUtility.subVirgolette(StringUtility.subBlankNWithBR(product.getSpecifiche()))%></p>
          </div>
    </div>
    </div>
  </span>
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
    $(document).ready(function () {
        breakPointIntestazioneCategorie();
        breakPointBodyProduct();
    })

    $(window).resize(function () {
        breakPointIntestazioneCategorie();
        breakPointBodyProduct();
    });
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
                buildTableCategoriesProductPage(data);
            },
            error: function (){alert("error")}
        }
    );

    let disponibilita= <%= product.isDisponibilita()%>;
    if(!disponibilita)
        $("#btnAggiungiAlCarrello").prop("disabled", true);

    var prodotto=
        {
            "codiceABarre" : "<%= product.getCodiceABarre()%>",
            "marca" : "<%= product.getMarca()%>",
            "modello" : "<%= product.getModello()%>",
            "prezzo" : <%= product.getPrezzo()%>,
            "descrizione" : "<%= StringUtility.subVirgolette(StringUtility.subBlankNWithSpace(product.getDescrizione()))%>",
            "specifiche" : "<%= StringUtility.subVirgolette(StringUtility.subBlankNWithSpace(product.getSpecifiche()))%>",
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
