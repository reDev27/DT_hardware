<%@ page import="Model.Cliente" %>
<%@ page import="Model.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Product" %><%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 30/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <title>Riepilogo ordine</title>
</head>
<body>

<script src="libraries/Utilities.js"></script>
<link id="linkCssLibrary" rel="stylesheet" type="text/css" href="homepageStyle.css">
<script src="libraries/jQuery_current.js"></script>
<script src="libraries/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="libraries/jquery-ui-1.12.1/jquery-ui.css">
<script src="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="libraries/bootstrap-5.0.1-dist/bootstrap-5.0.1-dist/css/bootstrap.css">

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
        margin: auto;
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
      <span class="cols-1" id="accediRef"><a href="login.html" title="Accedi o crea un account">Accedi</a> oppure <a href="iscrizione.html" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

<%
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");
%>

<div id="riepilogoOrdineSpan" style="border : 1px solid rgba(0,0,0,.125); display: grid; margin: 2% 1%; padding-left: 1.5%">
    <h3>Riepilogo ordine</h3>
    <span class="row">
        <span id="userInfo" class="col-8">
            <span id="utenteSpan">Nome e cognome: <%= cliente.getNome() + " " + cliente.getCognome()%></span><br>
            <span id="mailSpan">E-mail: <%= cliente.getEmail()%></span><br>
            <span id="telefonoSpan">Telefono: <%= cliente.getnTelefono()%></span><br>
            <span id="indirizzo">Indirizzo: <%= cliente.getAddresses().get(0).toString()%></span><br>
            <span id="cartaDiCredito">Carta di credito: <%= cliente.getCreditCards().get(0).toString()%></span><br>
            <span id="carrello">
                <script>
                    var quantitaTotale=0;
                </script>
                <%
                    ArrayList<Product> products= carrello.getProdotti();
                    for(Product prodotto:products)
                    {

                %>
                        <script>
                            quantitaTotale+=<%= prodotto.getQuantitaCarrello()%>;
                        </script>
                        <span style="display: flex; align-items: center"><img src="<%= prodotto.getImmagine()%>" width='160' height='160'><span id="infoProduct"><p> <%= prodotto.getMarca() + " " + prodotto.getModello()%>  &#x20AC <%= prodotto.getPrezzo()%><br> <%= prodotto.getQuantitaCarrello()%></p></span></span><br>
                <%
                    }
                %>
            </span>
            <span id="totaliESpedizione">
                <span id="quantitaTotaleProdotti"></span><br>
                <span>Subtotale: &#x20AC <%= carrello.getTotale()%></span><br>
                <span>Spedizione: &#x20AC 9.90</span><br>
                <span><h6>Totale: &#x20AC <%= carrello.getTotale() + 9.90%></h6></span>
            </span>
        </span>
        <span id="btnOrdinaSpan" class="col-3" style="margin-left: 8%">
            <button id="btnOrdina" class="btn btn-success" type="button" style="width: 100%; margin-bottom: 20%"><div id="spinnerOrdina" class="spinner-grow text-success spinner-border-sm" style="color: #dbd259 !important;"></div><span>Ordina e paga</span></button>
            <button id="btnAnnulla" class="btn btn-danger" type="button" style="width: 100%; align-self: end">Annulla</button>
        </span>
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
        $("#spinnerOrdina").hide();

        document.getElementById("quantitaTotaleProdotti").innerHTML="Quantità totale prodotti: " + quantitaTotale;

        $("#btnAnnulla").click(function () {
            window.location.href="viewCarrello.jsp";
        })

        $("#btnOrdina").click(function () {
            $.ajax(
                {
                    url : "CheckOutServ",
                    method : "post",
                    success : function (){
                        document.getElementById("esitoP").innerHTML = "Acquisto effettuato con successo! Verrai reindirizzato alla homepage a breve.";
                        $("#dialogEsito").dialog("open");
                        setTimeout(function () {
                            window.location.href = "homepage.html";
                        }, 4000);
                    },
                    error : function () {
                        document.getElementById("esitoP").innerHTML = "Errore in fare di check-out. Per favore riprova, se il problema persiste contatta il nostro centro assistenza.";
                        $("#dialogEsito").dialog("open");
                    },
                    beforeSend : function () {
                        $("#spinnerOrdina").show();
                    },
                    complete : function () {
                        $("#spinnerOrdina").hide();
                    }
                }
            )
        })

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
