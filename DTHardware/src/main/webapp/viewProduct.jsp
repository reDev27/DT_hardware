<%@ page import="Model.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 18/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script src="libraries/homepageUtilities.js"></script>
<script src="libraries/viewProductUtilities.js"></script>
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
    <span class="cols-4" id="txtSearchSpan"><textarea id="txtSearch" placeholder="Cerca..." rows="1" class="cols-4 "></textarea></span>
    <span class="cols-4" id="btnCarrelloSpan"><button type="button" class="btn btn-success cols-4" id="btnCarrello">Carrello</button></span>
  </span>

<% Product product=(Product) request.getAttribute("product");%>
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
            <span class="col-12"><h4 id="headerProducts" style="margin-left: 3%"> <%=product.getMarca() + " " + product.getModello()%></h4></span>
            <span class="col-12"><p>Identificativo: </p><%=product.getCodiceABarre()%></span>
            <span class="col-12"><p><%=product.getPrezzo()%> &#x20AC</p>  tasse incluse</span>
            <span class="col-12"><p> <label for="spinner">Amount to donate:</label><input id="spinner" name="spinner" value="5"></p></span>
            <span class="col-12"><button type="submit" class="btn btn-success">Aggiungi al carrello</button></span>
            <span class="col-12"><i><%=product.isDisponibilita()?"Disponibile":"Esaurito"%></i></span>
        </span>
        <div id="tabs">
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
    )
    $(
        function()
        {
            $( "#categoriesList" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
            $( "#categoriesList li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
            $("#categoriesList").css("border", 0);
        }
    );

    $( function() {
        $( "#currency" ).on( "change", function() {
            $( "#spinner" ).spinner( "option", "culture", $( this ).val() );
        });

        $( "#spinner" ).spinner({
            min: 5,
            max: 2500,
            step: 25,
            start: 1000,
            numberFormat: "C"
        });
    } );

    $( function() {
        $( "#tabs" ).tabs();
    } );
</script>

</main>
</body>
</html>
