function checkRegisterForm(checkHelp)
{
    let esito;
    var username = document.getElementById("username").value;
    if (username.length < 1)
    {
        document.getElementById("esitoP").innerHTML = "Username non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        checkHelp.username=false;
    }
    else
        checkHelp.username = true;

    var psw = document.getElementById("psw").value;
    if (psw.length < 1) {
        document.getElementById("esitoP").innerHTML = "Password non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        checkHelp.password=false;
    }
    else
        checkHelp.password = true;

    var nome = document.getElementById("nome").value;
    if (nome.length < 1) {
        document.getElementById("esitoP").innerHTML = "Nome non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        checkHelp.nome=false;
    }
    else
        checkHelp.nome=true;

    var cognome = document.getElementById("nome").value;
    if (cognome.length < 1) {
        document.getElementById("esitoP").innerHTML = "Cognome non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.cognome=false;
    }
    else
        checkHelp.cognome=true;

    var telefono = document.getElementById("ntelefono").value;
    if (telefono.length < 1) {
        document.getElementById("esitoP").innerHTML = "Numero di telefono non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.ntelefono=false;
    }
    else
        checkHelp.ntelefono=true;

    var via = document.getElementById("AddVia").value;
    if (via.length < 1) {
        document.getElementById("esitoP").innerHTML = "Via non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.via=false;
    }
    else
        checkHelp.via=true;

    var civico = document.getElementById("AddCivico").value;
    if (civico.length < 1) {
        document.getElementById("esitoP").innerHTML = "Numero civico non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.numeroCivico=false;
    }
    else
        checkHelp.numeroCivico=true;

    var citta = document.getElementById("AddCitta").value;
    if (citta.length < 1) {
        document.getElementById("esitoP").innerHTML = "Città non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.citta=false;
    }
    else
        checkHelp.citta=true;

    var cap = document.getElementById("AddCap").value;
    if (cap.length < 1) {
        document.getElementById("esitoP").innerHTML = "CAP non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.cap=false;
    }
    else
        checkHelp.cap=true;

    esito = checkHelp.username && checkHelp.email && checkHelp.password && checkHelp.nome && checkHelp.cognome
            && checkHelp.ntelefono && checkHelp.via && checkHelp.numeroCivico && checkHelp.citta && checkHelp.cap;
    return esito;
}

function isLogged()
{
    var bool=false;
    $.ajax
    (
        {
            url : "IsLoggedServ",
            method : "get",
            dataType : "json",
            success : function (data)
            {
                    bool=data.L;
            },
            error : function () {alert("error")}
        }
    );
    return bool;
}

function displayUserInfo(cliente)
{
    var nAddress=cliente.addresses.length;
    var nCard=cliente.creditCards.length;
    var newRows="";
    newRows += "<span><p>"+cliente.nome+"</p><p>"+cliente.cognome+"</p><p>"+cliente.nTelefono+"</p><p>"+cliente.email+"</p></span>";
    for(let iAddress=0; iAddress<nAddress; iAddress++)
    {
        newRows += "<span id='addressSpan"+iAddress+"' style='margin-right: 5%'><input type='radio' name='indirizzo"+iAddress+"' id='radioAddress"+iAddress+"'><label>" + cliente.addresses[iAddress].via + " " + cliente.addresses[iAddress].nCivico +"</label></span>";
    }
    newRows += "<span id='newAddressSpan' style='margin-top: 3%'><button class='btn btn-success' style='margin-bottom: 3%'>aggiungi indirizzo</button><span id='textBoxNewAddress'></span></span><br>"
    for(let iCard=0; iCard<nCard; iCard++)
    {
        newRows += "<span id='creditCardSpan' style='margin-right: 5%'><p>" + cliente.creditCards[iCard].nCarta + "</p></span>";
    }
    newRows += "<span id='newCreditCardSpan' style='margin-top: 3%'><button class='btn btn-success'>aggiungi carta di credito</button><span id='textBoxNewCard'></span></span>"
    document.getElementById("userInfo").innerHTML=newRows;
    $("#newAddressSpan button").on("click", function ()
    {
        newRows = "<input type=\"text\" id=\"via\" name=\"AddVia\" placeholder=\"Via\">"
            + "<br>"
            + "<input type=\"text\" id=\"civico\" name=\"AddCivico\" placeholder=\"Numero Civico\">"
            + "<br>"
            + "<input type=\"text\" id=\"citta\" name=\"AddCitta\" placeholder=\"Città\">"
            + "<br>"
            + "<input type=\"text\" id=\"cap\" name=\"AddCap\" placeholder=\"CAP\">"
            + "<br>"
            + "<button id='btnInvioAddress' type='button' class='btn btn-success'>Invio</button>"
            + "<div id=\"dialogConfirmAddress\" title=\"Memorizzare indirizzo?\">\n"
            + "<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:12px 12px 20px 0;\"></span>Vuoi conservare questo indirizzo per i prossimi acquisti?</p>\n"
            + "</div>";
        document.getElementById("textBoxNewAddress").innerHTML = newRows;
        $( function() {
            $( "#dialogConfirmAddress" ).dialog({
                autoOpen: false,
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Memorizza": function() {
                        $( this ).dialog( "close" );
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        } );
        $( "#btnInvioAddress" ).on("click",function (){$( "#dialogConfirmAddress" ).dialog("open");});
    });

    $("#newCreditCardSpan button").on("click", function ()
    {
        newRows = "<br><input type=\"text\" id=\"nCarta\" name=\"nCarta\" placeholder=\"Numero carta\">"
            + "<br>"
            + "<input type=\"date\" id=\"scadenza\" name=\"scadenza\" placeholder=\"Data di scadenza\">"
            + "<br>"
            + "<input type=\"text\" id=\"cvv\" name=\"cvv\" placeholder=\"CVV\">"
            + "<button id='btnInvioCard' type='button' class='btn btn-success'>Invio</button>"
            + "<div id=\"dialogConfirmCard\" title=\"Memorizzare carta di credito?\">\n"
            + "<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:12px 12px 20px 0;\"></span>Vuoi conservare questa carta di credito per i prossimi acquisti?</p>\n"
            + "</div><br>";
        document.getElementById("textBoxNewCard").innerHTML = newRows;
        $( function() {
            $( "#dialogConfirmCard" ).dialog({
                autoOpen: false,
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Memorizza": function() {
                        $( this ).dialog( "close" );
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        } );
        $( "#btnInvioCard" ).on("click",function (){$( "#dialogConfirmCard" ).dialog("open");});
    })
}


function checkOut()
{
    $.ajax
    (
        {
            url: "ValidateCheckOutServ",
            method: "get",
            dataType: "json",
            success: function (data) {
                displayUserInfo(data);
            },
            error: function () {
                alert("error");
            }
        }
    )
}

function calcolaTotaleQuantita(products)
{
    let n=products.length;
    let totale=0;
    for(let i=0; i<n; i++)
    {
        totale+=products[i].quantitaCarrello;
    }
    return totale;
}

function calcolaTotaleSpese(products)
{
    let n=products.length;
    var totale=0;
    for(let i=0; i<n; i++)
    {
        totale+=products[i].quantitaCarrello*products[i].prezzo;
    }
    document.getElementById("subTotalValue").innerHTML="" + totale.toFixed(2) + "&#x20AC";
    totale+=9.90;
    document.getElementById("totalValue").innerHTML="" + totale.toFixed(2) + "&#x20AC";
}

function showCarrello(products)
{
    let newRows="";
    let n=products.length;
    for(let i=0; i<n; i++)
    {
        newRows += "<span id='prodotto"+i+"' style='display: flex; border: 1px solid rgba(0,0,0,.125); align-items: center'><img src='" + products[i].immagine +"' width='160' height='160'><span id='spanInfoProdotto"+i+"' style='display: flex; align-items: baseline' ><h6>" + products[i].marca + " " + products[i].modello + "</h6><h6 style='margin-left: 5%; margin-right: 5%'>" + products[i].prezzo + " &#x20AC</h6><input id=\"quantitaSpinner"+i+"\" name=\"spinner\" value=\""+products[i].quantitaCarrello+"\" style=\"width: 80% ;margin:1%\"><button class='btn btn-danger' style='margin-left: 5%'>elimina</button></span></span>";
    }
    document.getElementById("showProductDiv").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $( "#quantitaSpinner"+i ).spinner({
            min: 1,
            max: 2500,
            step: 1,
            start: 1,
            numberFormat: "C"
        });
        $( "#quantitaSpinner"+i).spinner();
        $("#spanInfoProdotto"+i+" .ui-spinner").css({"width" : "15%", "margin-left" : "3%", "margin-right" : "3%"});
        $("#quantitaSpinner"+i).change(function ()
        {
            products[i].quantitaCarrello=$("#quantitaSpinner"+i).spinner("value")-products[i].quantitaCarrello;
            aggiornaCarrello(products[i]);
            document.getElementById("nQuantitaTotale").innerHTML="" + calcolaTotaleQuantita(products);
            calcolaTotaleSpese(products);
        });
        $("#spanInfoProdotto"+i+" .ui-spinner a").click(function ()
        {
            let appoggio;
            let valoreAttuale=$("#quantitaSpinner"+i).spinner("value");
            if(valoreAttuale-products[i].quantitaCarrello>0)
            {
                appoggio=products[i].quantitaCarrello+1;
                products[i].quantitaCarrello = 1;
            }
            else if(valoreAttuale-products[i].quantitaCarrello<0)
            {
                appoggio=products[i].quantitaCarrello-1;
                products[i].quantitaCarrello=-1;
            }
            if(appoggio!==undefined)
            {
                aggiornaCarrello(products[i]);
                products[i].quantitaCarrello = appoggio;
                var quantitaCarrelloTotale = "" + calcolaTotaleQuantita(products);
                document.getElementById("nQuantitaTotale").innerHTML = quantitaCarrelloTotale;
            }
            calcolaTotaleSpese(products);
        });
        $("#spanInfoProdotto" + i + " button").on("click", function ()
        {
            products[i].quantitaCarrello=0;
            aggiornaCarrello(products[i]);
            window.location.href="viewCarrello.jsp?";
        });
    }
}

function aggiornaCarrello(product)
{
    $.ajax
    (
        {
            url: "updateCarrelloServ",
            method: "post",
            data: product,
            success: function (){},
            error: function (){alert("error")}
        }
    );
}

function redirectToCarrello()
{
    $("#btnCarrello").on("click", function()
    {
        window.location.href="viewCarrello.jsp?";
    });
}

function buildTableCategoriesProductPage(categories)
{
    var newRows="";
    var n = categories.length;
    for(let i=0; i<n; i++)
    {
        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\"><p style='margin-top: 5%; margin-bottom: 5%; cursor: pointer'>"+ categories[i].nome +"</p></li>";
    }
    document.getElementById("categoriesListUl").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $( "#listItem" + i ).on( "click", function()
        {
            //document.getElementById("headerProducts").innerText=categories[i].nome;
            //var category={"category" : categories[i].nome};
            sessionStorage.setItem("category", categories[i].nome);
            window.location.href="homepage.html";
        });
    }
}

function buildTableCategories(categories)
{
    var newRows="";
    var n = categories.length;
    for(let i=0; i<n; i++)
    {
        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\"><p style='margin-top: 5%; margin-bottom: 5%; cursor: pointer'>"+ categories[i].nome +"</p></li>";
    }
    document.getElementById("categoriesListUl").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $( "#listItem" + i ).on( "click", function()
        {
            document.getElementById("headerProducts").innerText=categories[i].nome;
            var category={"category":categories[i].nome};
            $.ajax
            (
                {
                    url: "getProductByCategoryServ",
                    method: "post",
                    dataType: "json",
                    data: category,
                    success: function (data)
                    {
                        buildTableProductHomepage(data);
                    },
                    error: function (){alert("error");}
                }
            )
        });
    }
}

function buildTableProductHomepage(products)
{
    var n=products.length;
    var newRows="";
    for(let i=0; i<n; i++)
    {
        newRows+= "<li id='prodotto"+ i +"' class=\"ui-state-default\"><span id='prodottoSpan' class='row' style='padding: 1.75%'><img class='col-12' width='160' height='160' src=\"" + products[i].immagine+"\">" + "<p class='col-12'>" + products[i].marca +"</p> <p class='col-12'>"+ products[i].modello + "</p><p class='col-12'> " + products[i].prezzo + "</p><button id='btnz' type=\"button\" class=\"btn btn-primary cols-12\">Aggiungi al carrello</button><p class='col-12'> "+ isAvailable(products[i].disponibilita) + "</p></span></li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "100%" });
    $("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "180px", "height": "360px", "font-size": "1em", "text-align" : "center" });
    $("#selectableTableProducts ol").css("alignment", "center");
    for(let i=0; i<n; i++)
    {
        $("#prodotto"+i + " span button").on("click", function()
        {
            let s=toStringDate(products[i].dataInserimento);
            products[i].dataInserimento=s;
            products[i].quantitaCarrello=1;
            aggiornaCarrello(products[i]);
        });
        $("#prodotto"+i + " span img").on("click", function()
        {
            window.location.href="GetProductByCodeServ?codiceABarre=" + products[i].codiceABarre;
        });
        $("#prodotto"+i + " span p").on("click", function()
        {
            window.location.href="GetProductByCodeServ?codiceABarre=" + products[i].codiceABarre;
        });
    }
}

function isAvailable(availability)
{
    if(availability)
        return "Disponibile";
    else
        return "Esaurito";
}

function toStringDate(toConvert)
{
    return "" + toConvert.year + "-" + toConvert.month + "-" + toConvert.dayOfMonth + " " + toConvert.hourOfDay + ":" + toConvert.minute + ":" + toConvert.second;
}