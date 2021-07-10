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
      <img class="cols-1" src="image/telephone.png"> <span class="cols-1">Numero di telefono: 0123456789</span>
      <img class="cols-1" src="image/email.png" style="margin-left: 2%"> <span class="cols-8" >E-mail: DTHardware@gmail.com</span>
      <span class="cols-1" id="accediRef"><a href="login.html" title="Accedi o crea un account">Accedi</a> oppure <a href="iscrizione.html" title="Accedi o crea un account">crea un account</a></span>
    </span>
    </header>

    <span class="row" id="searchAndCarrello" style="flex-wrap: nowrap; width: 92%">
    <span class="col-4" id="logoSpan"><a href="manager.html"><img src="image/logo_DT.png" width="100%" id="logoIcon"></a></span>
	</span>

    <div class="container-fluid">
        <h5>Gestione prodotti</h5>

        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Codice a barre</th>
                    <th>Prezzo</th>
                    <th>Marca</th>
                    <th>Modello</th>
                    <th>Quantità</th>
                    <th>Data d'inserimento</th>
                </tr>
                </thead>
                <tbody id="tableGestioneProducts">
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <span class="col-4" style="display: grid; place-items: center"><button id="btnAggiungi" class="btn btn-success">Aggiungi</button></span>
        <span class="col-4" style="display: grid; place-items: center"><button id="btnModifica" class="btn btn-light">Modifica</button></span>
        <span class="col-4" style="display: grid; place-items: center"><button id="btnElimina" class="btn btn-danger">Elimina</button></span>
    </div>


    <div id="aggiungiOption" class="container-fluid" style="margin-top: 5%">
        <input type="text" id="codiceABarre" name="codiceABarre" placeholder="Codice a barre" style="margin-bottom: 2%"><br>
        <input type="text" id="marca" name="marca" placeholder="Marca" style="margin-bottom: 2%"><br>
        <input type="text" id="modello" name="modello" placeholder="Modello" style="margin-bottom: 2%"><br>
        <input type="text" id="prezzo" name="prezzo" placeholder="Prezzo" style="margin-bottom: 2%"><br>
        <select class="form-control" id="selectCategory" name="selectCategory" style="margin-bottom: 2%; width: 20%; display: inline"></select><br>
        <input type="text" id="quantita" name="quantita" placeholder="Quantità disponibile" style="margin-bottom: 2%"><br>
        <textarea id="descrizione" name="descrizione" placeholder="Descrizione..." style="margin-bottom: 2%; width: 40%; height: 15%"></textarea><br>
        <textarea id="specifiche" name="specifiche" placeholder="Specifiche..." style="margin-bottom: 2%; width: 40%; height: 15%"></textarea><br>
        <label for="imageFile" style="margin-bottom: 2%">Immagine</label><i class="fas fa-file-image" style="margin-left: 2%; margin-right: 2%"></i><input type="file" id="imageFile" name="image" style="margin-bottom: 2%"/><br>
        <button id="btnInvioProduct" class="btn btn-success">Invio</button><br>
    </div>

    <div id="modificaOption" class="container-fluid" style="margin-top: 5%">
        <input type="text" id="codiceABarreModify" name="codiceABarre" placeholder="Codice a barre" style="margin-bottom: 2%" disabled><br>
        <input type="text" id="marcaModify" name="marca" placeholder="Marca" style="margin-bottom: 2%"><br>
        <input type="text" id="modelloModify" name="modello" placeholder="Modello" style="margin-bottom: 2%"><br>
        <input type="text" id="prezzoModify" name="prezzo" placeholder="Prezzo" style="margin-bottom: 2%"><br>
        <select class="form-control" id="selectCategoryModify" name="selectCategory" style="margin-bottom: 2%; width: 20%; display: inline"></select><br>
        <input type="text" id="quantitaModify" name="quantita" placeholder="Quantità disponibile" style="margin-bottom: 2%"><br>
        <textarea id="descrizioneModify" name="descrizione" placeholder="Descrizione..." style="margin-bottom: 2%; width: 40%; height: 15%"></textarea><br>
        <textarea id="specificheModify" name="specifiche" placeholder="Specifiche..." style="margin-bottom: 2%; width: 40%; height: 15%"></textarea><br>
        <label for="imageFile" style="margin-bottom: 2%">Immagine</label><i class="fas fa-file-image" style="margin-left: 2%; margin-right: 2%"></i><input type="file" id="imageFileModify" name="image" style="margin-bottom: 2%"/><br>
        <button id="btnInvioProductModify" class="btn btn-success">Invio</button><br>
    </div>

    <div id="eliminaOption" class="container-fluid" style="margin-top: 5%">
        <p>Sei sicuro di voler eliminare il seguente prodotto: <mark id="toEliminateProductId"></mark> <mark id="toEliminateProduct"></mark></p>
        <button id="btnInvioProductDelete" class="btn btn-danger">Invio</button>
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
        var products=<%= request.getAttribute("productsJson")%>;
        showProductsGestione(products);
        $("#aggiungiOption").hide();
        $("#modificaOption").hide();
        $("#eliminaOption").hide();
        $("#btnAggiungi").click(function ()
        {
            $("#aggiungiOption").show();
            $("#modificaOption").hide();
            $("#eliminaOption").hide();
        });
        $("#btnModifica").click(function ()
        {
            $("#modificaOption").show();
            $("#aggiungiOption").hide();
            $("#eliminaOption").hide();
        });
        $("#btnElimina").click(function ()
        {
            $("#eliminaOption").show();
            $("#aggiungiOption").hide();
            $("#modificaOption").hide();
        })
        $("#btnInvioProduct").click(function ()
        {
            verificaInputs();
        })
        $("#btnInvioProductModify").click(function () {
            verificaInputsModify();
        })
        $("#btnInvioProductDelete").click(function () {
            deleteProduct();
        })
        $.ajax
        (
            {
                url : "getCategoriesServ",
                method : "get",
                dataType: "json",
                success : function (data)
                {
                    buildSelectCategory(data);
                },
                error : function ()
                {
                    alert("error");
                }
            }
        )
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

    <script>
        var imgConverted;
        document.getElementById("imageFile").onchange = function()
        {
            let reader = new FileReader();
            reader.onload = function (e)
            {
                imgConverted=e.target.result;
            };
            reader.readAsDataURL(this.files[0]);
        }

        document.getElementById("imageFileModify").onchange = function()
        {
            let reader = new FileReader();
            reader.onload = function (e)
            {
                imgConverted=e.target.result;
            };
            reader.readAsDataURL(this.files[0]);
        }
    </script>
</main>

</body>
</html>
