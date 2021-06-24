function isLogged()
{
    var bool=false;
    $(document).ready(function ()
    {
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
                error : function (){alert("error")}
            }
        );
    })
    return bool;
}

function checkOut()
{
    $("#btnCheckOut").on("click", function ()
    {
        $.ajax
        (
            {
                url: "CheckOutServ",
                method: "get",
                dataType: "json",
                success: function (){},
                error: function () {alert("error");}
            }
        )
    })
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
        newRows += "<span id='prodotto"+i+"' style='display: flex; border: #022a0c solid; align-items: center'><img src='" + products[i].immagine +"' width='160' height='160'><span id='spanInfoProdotto"+i+"' style='display: flex; align-items: baseline' ><h6>" + products[i].marca + " " + products[i].modello + "</h6><h6 style='margin-left: 5%; margin-right: 5%'>" + products[i].prezzo + " &#x20AC</h6><input id=\"quantitaSpinner"+i+"\" name=\"spinner\" value=\""+products[i].quantitaCarrello+"\" style=\"width: 80% ;margin:1%\"><button class='btn btn-danger' style='margin-left: 5%'>elimina</button></span></span>";
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
            var quantitaCarrelloTotale="" + calcolaTotaleQuantita(products);
            document.getElementById("nQuantitaTotale").innerHTML=quantitaCarrelloTotale;
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