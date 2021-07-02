<%@ page import="Model.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Product" %>
<%@ page import="Model.DAO.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 21/06/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <title>Carrello</title>
</head>
<%Carrello carrello=(Carrello) session.getAttribute("carrello");%>
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


<div class="row">
            <div class="col-8">
                <div class="card cart-container" style="border: 1px solid rgba(0,0,0,.125); margin-top: 2%; margin-left: 1%">
                    <div class="card-block row" style="padding: 1%; margin: 0 1%">
                        <h1 class="h1-carrello" style=" margin-bottom:0; margin-top: 0; padding-bottom: 1%; padding-left:0;  font-size: 25px">CARRELLO</h1>
                        <div id="showProductDiv" class="row"></div>
                    </div>
                </div>
            </div>
    <div class="col-3" style="float: right; margin-top: 1.5%; margin-left: 8%">
        <div class="card cart-summary">
                <div class="cart-summary-line row">
                    <span class="">
                        <span class="label"> Quantità </span>
                        <span id="nQuantitaTotale" class="value" style="float: right; margin-right: 5%">0</span>
                    </span>
                    <span>
                        <span>Spedizione</span>
                        <span style="float: right; margin-right: 5%">9.90 &#x20AC</span>
                    </span>
                    <span>
                        <span>Sub-totale</span>
                        <span id="subTotalValue" style="float: right; margin-right: 5%">0 &#x20AC</span>
                    </span>
                </div>
            <div class = "card-block" style="border: 1px solid rgba(165, 141, 141, 0.46)">
                <div class="summary">
                    <span class="label-total row"><h5 class="col-6">Totale</h5><h5 id="totalValue" class="value-total text-end col-6" style="padding-right: 8%">0 &#x20AC</h5></span>
                </div>
            </div>
            <button id="btnCheckOut" class="btn btn-success" style="margin-top: 5%">Procedi al check-out</button>
        </div>
    </div>
</div>

<span id="riepilogoOrdineSpan" style="border : 1px solid rgba(0,0,0,.125); display: grid; margin: 2% 1%; padding-left: 1.5%">
    <h3>Riepilogo ordine</h3>
    <span class="row" style="padding-left: 1%">
        <span id="userInfo" class="col-8"></span>
        <span id="btnOrdinaSpan" class="col-3" style="margin-left: 8%"><button id="btnOrdina" class="btn btn-success" type="submit" style="width: 100%">Ordina</button></span>
    </span>
</span>

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
        $( function() {
            $( "#dialogEsito" ).dialog({autoOpen: false});
        } );
    </script>

    <div id="dialogEsito" title="Espressione non valida">
        <p id="esitoP"></p>
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
      $("#riepilogoOrdineSpan").hide();
      $("#btnCheckOut").on("click", function ()
      {
          checkOut();
          $("#riepilogoOrdineSpan").show();
      })

      window.onbeforeunload(function ()
      {
          sessionStorage.removeItem("selectedAddress");
          sessionStorage.removeItem("selectedCard");
      })

      $("#btnOrdina").click(function ()
      {
          var selectedParameters=
              {
                  selectedAddress : sessionStorage.getItem("selectedAddress"),
                  selectedCard : sessionStorage.getItem("selectedCard")
              }
          if(selectedParameters.selectedCard!==null && selectedParameters.selectedAddress!==null)
          {
              $.ajax
              (
                  {
                      url: "BeforeCheckOutServ",
                      method: "post",
                      data: selectedParameters,
                      success: function () {
                          window.location.href = "riepilogoCheckout.jsp";
                      },
                      error: function () {
                          alert("error");
                      }
                  }

              )
              sessionStorage.removeItem("selectedAddress");
              sessionStorage.removeItem("selectedCard");
          }
          else
          {
              if(selectedParameters.selectedAddress===null) {
                  document.getElementById("esitoP").innerHTML = "Non è stato selezionato ne inserito nessun indirizzo. Prego, riprovare.";
                  $("#dialogEsito").dialog("open");
              }
              if(selectedParameters.selectedCard===null)
              {
                  document.getElementById("esitoP").innerHTML = "Non è stata selezionata ne inserito nessuna carta di credito. Prego, riprovare.";
                  $("#dialogEsito").dialog("open");
              }
          }
      })

      var products=[];
      <%

         if(carrello!=null)
         {
         	ArrayList<Product> prodotti=carrello.getProdotti();
            for(int i=0;i<prodotti.size(); i++)
            {
      %>
            products.push
            ({
                  "codiceABarre": "<%= prodotti.get(i).getCodiceABarre()%>",
                  "marca": "<%= prodotti.get(i).getMarca()%>",
                  "modello": "<%= prodotti.get(i).getModello()%>",
                  "prezzo": <%= prodotti.get(i).getPrezzo()%>,
                  "descrizione": "<%= prodotti.get(i).getDescrizione()%>",
                  "specifiche": "<%= prodotti.get(i).getSpecifiche()%>",
                  "immagine": "<%= prodotti.get(i).getImmagine()%>",
                  "disponibilita": <%= prodotti.get(i).isDisponibilita()%>,
                  "quantitaProdotto": <%= prodotti.get(i).getQuantitaProdotto()%>,
                  "dataInserimento": "<%= DateUtil.getStringFromCalendar(prodotti.get(i).getDataInserimento())%>",
                  "quantitaCarrello": <%= prodotti.get(i).getQuantitaCarrello()%>
            });
      <%
            }
         }
      %>
      var quantitaCarrelloTotale="" + calcolaTotaleQuantita(products);
      document.getElementById("nQuantitaTotale").innerHTML=quantitaCarrelloTotale;
      showCarrello(products);
      calcolaTotaleSpese(products);
      //checkOut();
  </script>
</main>
</body>
</html>
