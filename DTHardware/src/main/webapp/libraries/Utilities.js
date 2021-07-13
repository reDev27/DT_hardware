function breakPointBodyCarrello()
{
    if(window.matchMedia("(max-width: 800px)").matches)
    {
        $("#carrelloDiv").removeClass("col-8");
        $("#riepilogoDiv").removeClass("col-3");
        //$(".ui-spinner-input a").addClass("to-low-width-spinner");
    }
    else
    {
        $("#carrelloDiv").addClass("col-8");
        $("#riepilogoDiv").addClass("col-3");
        //$(".ui-spinner-input a").removeClass("to-low-width-spinner");
    }
}

function breakPointBodyProduct()
{
    if(window.matchMedia("(max-width: 800px)").matches)
    {
        $("#categorySpan").removeClass("col-3");

    }
    else
    {
        $("#categorySpan").addClass("col-3");
    }
}

function breakPointIntestazioneCategorie()
{
    if (window.matchMedia("(max-width: 800px)").matches || window.matchMedia("(max-device-width: 550px)").matches)
    {
        $("#spanTabellaProdotti").removeClass("col-9");
        $("#spanTabellaCategorie").removeClass("col-3");
        $("#logoSpan").removeClass("col-4");
        $("#txtSearchSpan").removeClass("col-3");
        $("#btnCercaSpan").removeClass("col-1");
        $("#btnCarrelloSpan").removeClass("col-3")
    }
    else
    {
        $("#logoSpan").addClass("col-4");
        $("#txtSearchSpan").addClass("col-3");
        $("#btnCercaSpan").addClass("col-1");
        $("#btnCarrelloSpan").addClass("col-3")
        $("#spanTabellaProdotti").addClass("col-9");
        $("#spanTabellaCategorie").addClass("col-3");
    }

    if (window.matchMedia("(max-width: 500px)").matches || window.matchMedia("(max-device-width: 500px)").matches)
    {
        document.getElementById("btnCarrello").innerHTML="<i class=\"fas fa-cart-arrow-down\" style=\"margin-right: 5%\"></i>";
    }
    else
    {
        document.getElementById("btnCarrello").innerHTML="<i class=\"fas fa-cart-arrow-down\" style=\"margin-right: 5%\"></i>Carrello";
    }
}

function showDialog(title, text)
{
    let div=document.createElement("div");
    div.id="divMsg";
    let p=document.createElement("p");
    p.innerHTML=text;
    div.appendChild(p);
    document.body.appendChild(div);
    $( function()
    {
        $( "#divMsg" ).dialog({title : title});
    });
}

function deleteOrder()
{
    let clienteToEliminate={toEliminate : document.getElementById("toEliminateOrderId").innerText};
    $.ajax
    (
        {
            url : "DeleteOrderServ",
            method : "post",
            data : clienteToEliminate,
            success : function (){window.location.reload();},
            error : function () {showDialog("Errore", "Qualcosa è andato storto, l'ordine non è stato eliminato");}
        }
    );
}

function modifyOrder()
{
    let esito=true;
    let id=document.getElementById("idModify").value;

    let fattura=document.getElementById("fatturaModify").value;
    if(fattura.length<1 || fattura.length>1000)
    {
        document.getElementById("esitoPModify").innerHTML = "Fattura ha una lunghezza massima di 1000 caratteri, e non può essere vuota.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let totale=document.getElementById("totaleModify").value;
    if(/\s/.test(totale) || !/[0-9]/.test(totale) || totale.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Totale non può contenere spazi, non può essere vuoto e può contenere unicamente cifre numeriche.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let dataAcquisto=document.getElementById("dataAcquistoModify").value;
    if(!/([0-9] | [-] | [:])/.test(dataAcquisto) || dataAcquisto.length>19 )             //!/:{2}/g.test(dataAcquisto) || !/-{2}/g.test(dataAcquisto) ||
    {
        document.getElementById("esitoP").innerHTML = "Data d'acquisto, formato accettato: YYYY-MM-GG HH:MM:SS.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let username=document.getElementById("usernameModify").value;

    let order;
    if(esito)
    {
        order=
            {
                id : id,
                fattura : fattura,
                totale : totale,
                dataAcquisto : dataAcquisto,
                username : username
            };
        $.ajax
        (
            {
                url : "UpdateOrderServ",
                method : "post",
                data : order,
                success : function(){window.location.reload();},
                error : function(){showDialog("Errore", "Qualcosa è andato storto, l'ordine non è stato modificato")}
            }
        );
    }
}

function showOrdersGestione(orders)
{
    let n=orders.length;
    let newRows="";
    for(let i=0; i<n; i++)
    {
        newRows +=  "<tr id='order"+i+"' style='cursor: pointer'><td>"+orders[i].id+"</td><td>"+orders[i].totale.toFixed(2)+"</td><td>"+toStringDatePlusMonth(orders[i].dataAcquisto)+
            "</td><td>"+orders[i].username+"</td></tr>";
    }
    document.getElementById("tableGestioneOrders").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $("#order"+i).click
        (
            function ()
            {
                document.getElementById("idModify").value=orders[i].id;
                document.getElementById("fatturaModify").value=orders[i].fattura;
                document.getElementById("totaleModify").value=orders[i].totale.toFixed(2);
                document.getElementById("dataAcquistoModify").value=toStringDatePlusMonth(orders[i].dataAcquisto);
                document.getElementById("usernameModify").value=orders[i].username;
                document.getElementById("toEliminateOrderId").innerHTML="" + orders[i].id;
                document.getElementById("toEliminateOrder").innerHTML= "" + orders[i].totale + " " + orders[i].dataAcquisto;
            }
        )
    }
}

function deleteCliente()
{
    let clienteToEliminate={toEliminate : document.getElementById("toEliminateClienteId").innerText};
    $.ajax
    (
        {
            url : "DeleteClienteServ",
            method : "post",
            data : clienteToEliminate,
            success : function (){window.location.reload();},
            error : function () {showDialog("Errore", "Qualcosa è andato storto, cliente non eliminato");}
        }
    );
}

function modifyCliente()
{
    let esito=true;
    let username=document.getElementById("usernameModify").value;
    if(/\s/.test(username) || username.length>30 || username.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Username non può contenere spazi, non può essere vuoto ed ha una lunghezza massima di 30 caratteri.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let email=document.getElementById("emailModify").value;
    if(email.length<1 || email.length>320 || !/[@]+/.test(email))
    {
        document.getElementById("esitoPModify").innerHTML = "E-mail ha una lunghezza massima di 320 caratteri, e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let nome=document.getElementById("nomeModify").value;
    if(/\s/.test(nome) || nome.length>50 || !/\b[A-Z]/.test(nome) || nome.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Nome non può contenere spazi, non può essere vuoto, ha una lunghezza massima di 50 caratteri e la prima lettera deve essre maiuscola.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let cognome=document.getElementById("cognomeModify").value;
    if(/\s/.test(cognome) || cognome.length>50 || !/\b[A-Z]/.test(cognome) || cognome.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Cognome non può contenere spazi, ha una lunghezza massima di 50 caratteri, non può essere vuoto e la prima lettera deve essre maiuscola.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let nTelefono=document.getElementById("nTelefonoModify").value;
    if(/\s/.test(nTelefono) || nTelefono.length!==10 || !/[0-9]/g.test(nTelefono) || nTelefono.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Numero di telefono non può contenere spazi, ha una lunghezza fissata di 10 caratteri, non può essere vuoto e può contenere solo cifre numeriche.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let cliente;
    if(esito)
    {
        cliente=
            {
                username : username,
                email : email,
                nome : nome,
                cognome : cognome,
                ntelefono : nTelefono
            };
        $.ajax
        (
            {
                url : "ModifyClienteServ",
                method : "post",
                data : cliente,
                success : function(){window.location.reload();},
                error : function(){window.location.reload();}
            }
        );
    }
}

function addCliente()
{
    let esito=true;
    let username=document.getElementById("username").value;
    if(/\s/.test(username) || username.length>30 || username.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Username non può contenere spazi, non può essere vuoto ed ha una lunghezza massima di 30 caratteri.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let email=document.getElementById("email").value;
    if(email.length<1 || email.length>320 || !/[@]+/.test(email))
    {
        document.getElementById("esitoP").innerHTML = "E-mail ha una lunghezza massima di 320 caratteri, e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let psw=document.getElementById("pwd").value;
    if(/\s/.test(psw) || !/[A-Z]+/.test(psw) || !/[a-z]+/.test(psw) || !/[0-9]+/.test(psw) || psw.length>20 || psw.length<6)
    {
        document.getElementById("esitoP").innerHTML = "Password non può contenere spazi, ha una lunghezza massima di 20 caratteri, una lunghezza minima di 6 caratteri, " +
            "Deve contenere almeno una lettera maiuscola, una minuscola e un numero.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let nome=document.getElementById("nome").value;
    if(/\s/.test(nome) || nome.length>50 || !/\b[A-Z]/.test(nome) || nome.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Nome non può contenere spazi, non può essere vuoto, ha una lunghezza massima di 50 caratteri e la prima lettera deve essre maiuscola.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let cognome=document.getElementById("cognome").value;
    if(/\s/.test(cognome) || cognome.length>50 || !/\b[A-Z]/.test(cognome) || cognome.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Cognome non può contenere spazi, ha una lunghezza massima di 50 caratteri, non può essere vuoto e la prima lettera deve essre maiuscola.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let nTelefono=document.getElementById("nTelefono").value;
    if(/\s/.test(nTelefono) || nTelefono.length!==10 || !/[0-9]/g.test(nTelefono) || nTelefono.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Numero di telefono non può contenere spazi, ha una lunghezza fissata di 10 caratteri, non può essere vuoto e può contenere solo cifre numeriche.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let cliente;
    if(esito)
    {
        cliente=
            {
                username : username,
                email : email,
                psw : psw,
                nome : nome,
                cognome : cognome,
                ntelefono : nTelefono
            };
        $.ajax
        (
            {
                url : "RegisterServ",
                method : "post",
                data : cliente,
                success : function(){window.location.reload();},
                error : function(){window.location.reload();}
            }
        );
    }
}

function showClientiGestione(clienti)
{
    let n=clienti.length;
    let newRows="";
    for(let i=0; i<n; i++)
    {
        newRows +=  "<tr id='cliente"+i+"' style='cursor: pointer'><td>"+clienti[i].username+"</td><td>"+clienti[i].email+"</td>" +
                    "<td>"+clienti[i].nome+"</td><td>"+clienti[i].cognome+"</td><td>"+clienti[i].nTelefono+"</td></tr>";
    }
    document.getElementById("tableGestioneClienti").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $("#cliente"+i).click
        (
            function ()
            {
                document.getElementById("usernameModify").value=clienti[i].username;
                document.getElementById("emailModify").value=clienti[i].email;
                document.getElementById("nomeModify").value=clienti[i].nome;
                document.getElementById("cognomeModify").value=clienti[i].cognome;
                document.getElementById("nTelefonoModify").value=clienti[i].nTelefono;
                document.getElementById("toEliminateClienteId").innerHTML="" + clienti[i].username;
                document.getElementById("toEliminateCliente").innerHTML= "" + clienti[i].cognome + " " + clienti[i].nome;
            }
        )
    }
}

function deleteProduct()
{
    let productToEliminate={toEliminate : document.getElementById("toEliminateProductId").innerText};
    $.ajax
    (
        {
            url : "DeleteProductServ",
            method : "post",
            data : productToEliminate,
            success : function (){window.location.reload();},
            error : function () {
                showDialog("Errore", "Qualcosa è andato storto, prodotto non eliminato");
            }
        }
    );
}

function buildSelectCategory(categories)
{
    let newRows="";
    let n=categories.length;
    for(let i=0; i<n; i++)
    {
        newRows += "<option>"+categories[i].nome+"</option>";
    }
    document.getElementById("selectCategory").innerHTML = newRows;
    document.getElementById("selectCategoryModify").innerHTML = newRows;
}

function verificaInputsModify()
{
    let esito=true;
    let codiceABarre=document.getElementById("codiceABarreModify").value;
    if(/\s/.test(codiceABarre) || codiceABarre.length>12 || codiceABarre.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Codice a barre non può contenere spazi, deve avere una lunghezza massima di 12 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let marca=document.getElementById("marcaModify").value;
    if(marca.length<1 || marca.length>50)
    {
        document.getElementById("esitoP").innerHTML = "Marca ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let modello=document.getElementById("modelloModify").value;
    if(modello<1 || modello.length>50)
    {
        document.getElementById("esitoP").innerHTML = "Modello ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let prezzo=document.getElementById("prezzoModify").value;
    if(!/([0-9])/.test(prezzo) || prezzo.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Prezzo può contenere solo cifre da 0 a 9(per separare la parte decimale e quella intera usi il .) e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let categoria=document.getElementById("selectCategoryModify").value;
    if(!/[a-z]/i.test(categoria) || categoria.length>50 || categoria.length<1)
    {
        document.getElementById("esitoP").innerHTML = "La categoria può contenere solo lettere, ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let quantita=document.getElementById("quantitaModify").value;
    if(!/[0-9]/.test(quantita) || quantita.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Quantità magazzino può contenere solo cifre numeriche e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let descrizione=document.getElementById("descrizioneModify").value;
    if(descrizione.length>1000)
    {
        document.getElementById("esitoP").innerHTML = "Descrizione ha una lunghezza massima di 1000 caratteri.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let specifiche=document.getElementById("specificheModify").value;
    if(specifiche.length<1 || specifiche.length>1000)
    {
        document.getElementById("esitoP").innerHTML = "Specifiche ha una lunghezza massima di 1000 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let product;

    if(esito)
    {
        product=
            {
                codiceABarre : codiceABarre,
                marca : marca,
                modello : modello,
                prezzo : prezzo,
                categoria : categoria,
                quantita : quantita,
                descrizione : descrizione,
                specifiche : specifiche,
                image : imgConverted
            };
        $.ajax
        (
            {
                url : "UpdateProductServ",
                method : "post",
                data : product,
                success : function(){window.location.reload();},
                error : function(){showDialog("Errore", "Qualcosa è andato storto, prodotto non modificato")}
            }
        );
    }

}

function verificaInputs()
{
    let esito=true;
    let codiceABarre=document.getElementById("codiceABarre").value;
    if(/\s/.test(codiceABarre) || codiceABarre.length>12 || codiceABarre.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Codice a barre non può contenere spazi, deve avere una lunghezza massima di 12 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let marca=document.getElementById("marca").value;
    if(marca.length<1 || marca.length>50)
    {
        document.getElementById("esitoP").innerHTML = "Marca ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let modello=document.getElementById("modello").value;
    if(modello<1 || modello.length>50)
    {
        document.getElementById("esitoP").innerHTML = "Modello ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let prezzo=document.getElementById("prezzo").value;
    if(!/([0-9])/.test(prezzo) || prezzo.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Prezzo può contenere solo cifre da 0 a 9(per separare la parte decimale e quella intera usi il .) e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let categoria=document.getElementById("selectCategory").value;
    if(!/[a-z]/i.test(categoria) || categoria.length>50 || categoria.length<1)
    {
        document.getElementById("esitoP").innerHTML = "La categoria può contenere solo lettere, ha una lunghezza massima di 50 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let quantita=document.getElementById("quantita").value;
    if(!/[0-9]/.test(quantita) || quantita.length<1)
    {
        document.getElementById("esitoP").innerHTML = "Quantità magazzino può contenere solo cifre numeriche e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let descrizione=document.getElementById("descrizione").value;
    if(descrizione.length>1000)
    {
        document.getElementById("esitoP").innerHTML = "Descrizione ha una lunghezza massima di 1000 caratteri.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let specifiche=document.getElementById("specifiche").value;
    if(specifiche.length<1 || specifiche.length>1000)
    {
        document.getElementById("esitoP").innerHTML = "Specifiche ha una lunghezza massima di 1000 caratteri e non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        esito=false;
    }

    let product;
    if(esito)
    {
        product=
            {
                codiceABarre : codiceABarre,
                marca : marca,
                modello : modello,
                prezzo : prezzo,
                categoria : categoria,
                quantita : quantita,
                descrizione : descrizione,
                specifiche : specifiche,
                image : imgConverted
            };
        $.ajax
        (
            {
                url : "AddProductServ",
                method : "post",
                data : product,
                success : function(){window.location.reload();},
                error : function(){showDialog("Errore", "Qualcosa è andato storto, prodotto non aggiunto")}
            }
        );
    }
}

function showProductsGestione(products)
{
    let newRows="";
    let n=products.length;
    for(let i=0; i<n; i++)
    {
        newRows += "<tr id='product"+i+"' style='cursor: pointer'><td>"+products[i].codiceABarre+"</td><td>"+products[i].prezzo.toFixed(2)+"</td><td>"+products[i].marca+"</td>" +
            "<td>"+products[i].modello+"</td><td>"+products[i].quantitaProdotto+"</td><td>"+toStringDatePlusMonth(products[i].dataInserimento)+"</td></tr>";
    }
    document.getElementById("tableGestioneProducts").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $("#product"+i).click
        (
            function ()
            {
                document.getElementById("codiceABarreModify").value=products[i].codiceABarre;
                document.getElementById("marcaModify").value=products[i].marca;
                document.getElementById("modelloModify").value=products[i].modello;
                document.getElementById("prezzoModify").value=products[i].prezzo.toFixed(2);
                document.getElementById("selectCategoryModify").value=products[i].nomeCategoria;
                document.getElementById("quantitaModify").value=products[i].quantitaProdotto;
                document.getElementById("descrizioneModify").value=products[i].descrizione;
                document.getElementById("specificheModify").value=products[i].specifiche;
                document.getElementById("toEliminateProductId").innerHTML="" + products[i].codiceABarre;
                document.getElementById("toEliminateProduct").innerHTML= "" + products[i].marca + " " + products[i].modello;
            }
        )
    }
}

function searchProductsRedirect()
{
    let toSearch=document.getElementById("txtSearch").value;
    if(toSearch.length>1)
    {
        var keyWord = {keyToSearch: toSearch};
        $.ajax
        (
            {
                url: "SearchRedirectServ",
                method: "get",
                data: keyWord,
                dataType: "json",
                success: function (data)
                {
                    buildTableProductHomepage(data);
                    document.getElementById("suggerimentiDiv").innerHTML="";
                    document.getElementById("headerProducts").innerHTML="Risultati trovati: " + data.length;
                },
                error: function ()
                {
                    showDialog("Errore", "Qualcosa è andato storto, non è stato possibile recuperare i dati sulla tua ricerca");
                },
            }
        )
    }
}

function searchRedirectUtility()
{
    sessionStorage.setItem('toSearch', document.getElementById('txtSearch').value);
    window.location.href="homepage.html";
}

function searchProducts()
{
    var toSearch = document.getElementById("txtSearch").value;
    if(toSearch.length>1)
    {
        var keyWord = {keyToSearch: toSearch};
        $.ajax
        (
            {
                url: "SearchServ",
                method: "get",
                data: keyWord,
                dataType: "json",
                success: function (data)
                {
                    showSuggerimenti(data);
                },
                error: function ()
                {
                    showDialog("Errore", "Qualcosa è andato storto, non è stato possibile recuperare i dati sulla tua ricerca");
                },
            }
        )
    }
    else
        document.getElementById("suggerimentiDiv").innerHTML="";
}

function showSuggerimenti(products)
{
    $("#suggerimentiDiv").css("width", document.getElementById("txtSearch").offsetWidth);
    var newRows="";
    var n=products.length;
    for(let i=0; i<n; i++)
    {
        newRows += "<a href=\"GetProductByCodeServ?codiceABarre=" + products[i].codiceABarre + "\" class=\"list-group-item list-group-item-action\" style='border: 1px solid rgb(0 0 0 / 60%); background-color: #c0d0a8'>" + products[i].marca + " " + products[i].modello +"</a>";
    }
    document.getElementById("suggerimentiDiv").innerHTML=newRows;
    $(window).resize(function () {
        $("#suggerimentiDiv").css("width", document.getElementById("txtSearch").offsetWidth);
    })

}

function formatFattura(fattura)
{
    let fatturaAppoggio;
    do
    {
        fatturaAppoggio=fattura;
        fattura=fattura.replace("\n", "<br>");
    }while(fattura.normalize() !== fatturaAppoggio.normalize())
    return fattura;
}

function showAddressChange(addresses)
{
    var checkHelpAddress=
        {
            via: true,
            numeroCivico: true,
            citta: true,
            cap: true
        };
    var checkHelpCard=
        {
            nCarta: true,
            data: true,
            cvv: true
        };
    let n=addresses.length;
    let newRows="";
    for(let i=0; i<n; i++)
    {
        newRows += "<span id='address" + i + "' style='display: flex; align-items: center; margin-bottom: 3%; justify-content: space-between'>"+addresses[i].via + " " + addresses[i].nCivico + " " + addresses[i].citta + " " + addresses[i].CAP + "<button class='btn btn-danger' type='button'>Elimina</button></span>";
    }
    newRows += "<button id='btnAggiungiAddress' class='btn btn-success' type='button' style='margin-bottom: 3%; background-color: #86d804'>Aggiungi</button><span id='inputAddress'></span>";
    document.getElementById("addresses").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $("#address" + i + " button").on("click", function ()
        {
            var toEliminate={via : addresses[i].via, nCivico : addresses[i].nCivico};
            $.ajax
            (
                {
                    url : "DeleteAddressServ",
                    method : "post",
                    data : toEliminate,
                    success : function () {window.location.reload();}
                }
            )
        })
    }
    newRows="";
    $("#btnAggiungiAddress").on("click", function ()
    {
        document.getElementById("inputAddress").innerHTML="<br><input type=\"text\" id=\"AddVia\" name=\"AddVia\" placeholder=\"Via\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCivico\" name=\"AddCivico\" placeholder=\"Numero Civico\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCitta\" name=\"AddCitta\" placeholder=\"Città\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCap\" name=\"AddCap\" placeholder=\"CAP\">"
            + "<br>"
            + "<button id='btnInvioAddress' type='button' style='background-color: #86d804; margin-bottom: 3%' class='btn btn-success'>Invio</button>"
            + "<div id=\"dialogConfirmAddress\" title=\"Espressione non valida\">\n"
            + "<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:12px 12px 20px 0;\"></span>Vuoi conservare questo indirizzo per i prossimi acquisti?</p>\n"
            + "</div>";
        $("#btnInvioAddress").click(function ()
        {
            var addressToSend=
                {
                    via : "" + document.getElementById("AddVia").value,
                    nCivico : "" + document.getElementById("AddCivico").value,
                    citta : "" + document.getElementById("AddCitta").value,
                    CAP : "" + document.getElementById("AddCap").value
                }
            if(validateAddress(checkHelpAddress))
            {
                $.ajax
                (
                    {
                        url : "AddAddressServ",
                        method : "post",
                        data : addressToSend,
                        success : function () {window.location.reload();}
                    }
                );

            }
        });
    });

    $("#AddVia").change(function ()
    {
        var via=document.getElementById("AddVia").value;
        if(via.length>50)
        {
            document.getElementById("esitoP").innerHTML = "Via ha una lunghezza massima di 50 caratteri.";
            $("#dialogEsito").dialog("open");
            $("#btnInvioAddress").prop("disabled", true);
            checkHelpAddress.via=false;
        }
        else
        {
            if(!checkHelpAddress.via)
            {
                checkHelpAddress.via = true;
                $("#btnInvioAddress").prop("disabled", false);
            }
        }
    });
    $("#AddCivico").change(function ()
    {
        var civico=document.getElementById("AddCivico").value;
        if(/\s/.test(civico) || !/[0-9]/g.test(civico))
        {
            document.getElementById("esitoP").innerHTML = "Numero civico non può contenere spazi e può contenere solo cifre numeriche.";
            $("#dialogEsito").dialog("open");
            $("#btnInvioAddress").prop("disabled", true);
            checkHelpAddress.numeroCivico=false;
        }
        else
        {
            if(!checkHelpAddress.numeroCivico)
            {
                checkHelpAddress.numeroCivico = true;
                $("#btnInvioAddress").prop("disabled", false);
            }
        }
    });
    $("#AddCitta").change(function ()
    {
        var citta=document.getElementById("AddCitta").value;
        if(citta.length>20 || /[0-9]/.test(citta))
        {
            document.getElementById("esitoP").innerHTML = "Città ha una lunghezza massima di 20 caratteri e non può contenere cifre numeriche.";
            $("#dialogEsito").dialog("open");
            $("#btnInvioAddress").prop("disabled", true);
            checkHelpAddress.citta=false;
        }
        else
        {
            if(!checkHelpAddress.citta)
            {
                checkHelpAddress.citta = true;
                $("#btnInvioAddress").prop("disabled", false);
            }
        }
    });
    $("#AddCap").change(function ()
    {
        var cap=document.getElementById("AddCap").value;
        if(/\s/.test(cap) || !/[0-9]/g.test(cap) || cap.length!==5)
        {
            document.getElementById("esitoP").innerHTML = "CAP non può contenere spazi, può contenere solo cifre numeriche e la sua lunghezza è fissata a 5.";
            $("#dialogEsito").dialog("open");
            $("#btnInvioAddress").prop("disabled", true);
            checkHelpAddress.cap=false;
        }
        else
        {
            if(!checkHelpAddress.cap)
            {
                checkHelpAddress.cap = true;
                $("#btnInvioAddress").prop("disabled", false);
            }
        }
    });
}

function showAnagrafica(cliente)
{
    $("#infoUser h5").html("Utente: " + cliente.username)
    document.getElementById("anagrafica").innerHTML=   "Nome: " + cliente.nome + ";<br>Cognome: " + cliente.cognome +";<br>Email: " + cliente.email +
                                                                ";<br>Numero di Telefono: " + cliente.nTelefono;
    let addresses=cliente.addresses;
    let n=addresses.length;
    let newRows="";
    for(let i=0; i<n; i++)
    {
        newRows += "<span id='address" + i + "'>Via: "+addresses[i].via + " " + addresses[i].nCivico + " " + addresses[i].citta + " " + addresses[i].CAP + ";</span><br>"
    }
    newRows += "<br>";
    document.getElementById("address").innerHTML=newRows;
    let cards=cliente.creditCards;
    n=cards.length;
    newRows="";
    for(let i=0; i<n; i++)
    {
        newRows += "<span id='card"+i+"'>Numero carta: "+cards[i].nCarta+ ";<br>Data di scadenza: "+ toStringDateMonthYear(cards[i].scadenza) +";</span><br>";
    }
    document.getElementById("cards").innerHTML=newRows;
}

function showDettagliOrdine(orders, index)
{
    let newRows="";
    let products=orders[index].products.prodotti;
    let n=products.length;
    document.getElementById("dettagliOrdineDiv").innerHTML= "<h5>Dettagli ordine:</h5><br>" + formatFattura(orders[index].fattura);
}

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
                return data.L;
            },
            error : function () {}
        }
    );
    return bool;
}

function validateAddress(checkHelp)
{
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

    return checkHelp.via && checkHelp.numeroCivico && checkHelp.citta && checkHelp.cap;
}

function ajaxAddress(address, checkHelp)
{
    let esito=validateAddress(checkHelp);
    if(!esito) return;
    $.ajax
    (
        {
            url : "UpdateIndirizzoServ",
            method : "post",
            data: address,
            success : function () {},
            error : function () {showDialog("Errore", "Qualcosa è andato storto, non è stato possibile usare i dati sul tuo indirizzo");},
        }
    )
}

function validateCard(checkHelp)
{
    var nCarta = document.getElementById("Ncarta").value;
    if (nCarta.length < 1)
    {
        document.getElementById("esitoP").innerHTML = "Numero carta non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.nCarta=false;
    }
    else
        checkHelp.nCarta=true;

    var scadenza = document.getElementById("scadenza").value;
    if (scadenza.length < 1)
    {
        document.getElementById("esitoP").innerHTML = "Data di scadenza non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.scadenza=false;
    }
    else
        checkHelp.scadenza=true;

    var cvv = document.getElementById("CVV").value;
    if (cvv.length < 1)
    {
        document.getElementById("esitoP").innerHTML = "CVV non può essere vuoto.";
        $("#dialogEsito").dialog("open");
        $("#btnInvio").prop("disabled", true);
        checkHelp.cvv=false;
    }
    else
        checkHelp.cvv=true;

    return checkHelp.nCarta && checkHelp.scadenza && checkHelp.cvv;
}

function ajaxCard(card, checkHelp)
{
    let esito=validateCard(checkHelp);
    if(!esito) return;
    $.ajax
    (
        {
            url : "UpdateCartaServ",
            method : "post",
            data: card,
            success : function () {},
            error : function () {showDialog("Errore", "Qualcosa è andato storto, non è stato possibile usare i dati sulla tua carta di credito");},
        }
    )
}

function displayUserInfo(cliente)
{
    var checkHelpAddress=
        {
            via: true,
            numeroCivico: true,
            citta: true,
            cap: true
        };
    var checkHelpCard=
        {
            nCarta: true,
            data: true,
            cvv: true
        };

    var nAddress=cliente.addresses.length;
    var nCard=cliente.creditCards.length;
    var newRows="";
    newRows += "<span><p>"+cliente.nome+"</p><p>"+cliente.cognome+"</p><p>"+cliente.nTelefono+"</p><p>"+cliente.email+"</p></span>";
    for(let iAddress=0; iAddress<nAddress; iAddress++)
    {
        newRows += "<span id='addressSpan"+iAddress+"' style='margin-right: 5%'><input type='radio' name='indirizzo' id='radioAddress"+iAddress+"'><label for='radioAddress"+iAddress+"'>" + cliente.addresses[iAddress].via + " " + cliente.addresses[iAddress].nCivico +"</label></span>";
    }
    newRows += "<span id='newAddressSpan' style='margin-top: 3%'><button class='btn btn-success' style='margin-bottom: 3%; background-color: #86d804'>aggiungi indirizzo</button><span id='textBoxNewAddress'></span></span><br>"
    for(let iCard=0; iCard<nCard; iCard++)
    {
        newRows += "<span id='creditCardSpan' style='margin-right: 5%'><input type='radio' id='radioCard"+iCard+"' name='card'><label for='radioCard"+iCard+"'>" + cliente.creditCards[iCard].nCarta + "</label></span>";
    }
    newRows += "<span id='newCreditCardSpan' style='margin-top: 3%'><button class='btn btn-success' style='background-color: #86d804'>aggiungi carta di credito</button><span id='textBoxNewCard'></span></span>"
    document.getElementById("userInfo").innerHTML=newRows;
    for(let iAddress=0; iAddress<nAddress; iAddress++)
    {
        $("#radioAddress" + iAddress).change(function () {
            sessionStorage.setItem("selectedAddress", "" + iAddress);
        })
    }
    for(let iCard=0; iCard<nCard; iCard++)
    {
        $("#radioCard" + iCard).change(function () {
            sessionStorage.setItem("selectedCard", "" + iCard);
        })
    }
    $("#newAddressSpan button").on("click", function ()
    {
        newRows = "<br><input type=\"text\" id=\"AddVia\" name=\"AddVia\" placeholder=\"Via\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCivico\" name=\"AddCivico\" placeholder=\"Numero Civico\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCitta\" name=\"AddCitta\" placeholder=\"Città\">"
            + "<br>"
            + "<input type=\"text\" id=\"AddCap\" name=\"AddCap\" placeholder=\"CAP\">"
            + "<br>"
            + "<button id='btnInvioAddress' type='button' style='margin-bottom: 3%; background-color: #0c63e4' class='btn btn-success'>Invio</button>"
            + "<div id=\"dialogConfirmAddress\" title=\"Memorizzare indirizzo?\">\n"
            + "<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:12px 12px 20px 0;\"></span>Vuoi conservare questo indirizzo per i prossimi acquisti?</p>\n"
            + "</div>";
        document.getElementById("textBoxNewAddress").innerHTML = newRows;

        var addressToSend;
        $( function()
        {
            $( "#dialogConfirmAddress" ).dialog
            ({
                autoOpen: false,
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons:
                    {
                    "Memorizza": function()
                    {
                        $( this ).dialog( "close" );
                        addressToSend=
                             {
                                 via : "" + document.getElementById("AddVia").value,
                                 nCivico : "" + document.getElementById("AddCivico").value,
                                 citta : "" + document.getElementById("AddCitta").value,
                                 CAP : "" + document.getElementById("AddCap").value,
                                 isToStore : true
                             };
                        ajaxAddress(addressToSend, checkHelpAddress);
                    },
                    "Annulla": function()
                    {
                        $( this ).dialog( "close" );
                        addressToSend=
                            {
                                via : "" + document.getElementById("AddVia").value,
                                nCivico : "" + document.getElementById("AddCivico").value,
                                citta : "" + document.getElementById("AddCitta").value,
                                CAP : "" + document.getElementById("AddCap").value,
                                isToStore : false
                            };
                        ajaxAddress(addressToSend, checkHelpAddress);
                    }
                }
            });
        } );
        $( "#btnInvioAddress" ).on("click",function (){$( "#dialogConfirmAddress" ).dialog("open"); sessionStorage.setItem("selectedAddress", "" + -1)});
        $("#AddVia").change(function ()
        {
            var via=document.getElementById("AddVia").value;
            if(via.length>50)
            {
                document.getElementById("esitoP").innerHTML = "Via ha una lunghezza massima di 50 caratteri.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioAddress").prop("disabled", true);
                checkHelpAddress.via=false;
            }
            else
            {
                if(!checkHelpAddress.via)
                {
                    checkHelpAddress.via = true;
                    $("#btnInvioAddress").prop("disabled", false);
                }
            }
        })
        $("#AddCivico").change(function ()
        {
            var civico=document.getElementById("AddCivico").value;
            if(/\s/.test(civico) || !/[0-9]/g.test(civico))
            {
                document.getElementById("esitoP").innerHTML = "Numero civico non può contenere spazi e può contenere solo cifre numeriche.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioAddress").prop("disabled", true);
                checkHelpAddress.numeroCivico=false;
            }
            else
            {
                if(!checkHelpAddress.numeroCivico)
                {
                    checkHelpAddress.numeroCivico = true;
                    $("#btnInvioAddress").prop("disabled", false);
                }
            }
        })
        $("#AddCitta").change(function ()
        {
            var citta=document.getElementById("AddCitta").value;
            if(citta.length>20 || /[0-9]/.test(citta))
            {
                document.getElementById("esitoP").innerHTML = "Città ha una lunghezza massima di 20 caratteri e non può contenere cifre numeriche.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioAddress").prop("disabled", true);
                checkHelpAddress.citta=false;
            }
            else
            {
                if(!checkHelpAddress.citta)
                {
                    checkHelpAddress.citta = true;
                    $("#btnInvioAddress").prop("disabled", false);
                }
            }
        })
        $("#AddCap").change(function ()
        {
            var cap=document.getElementById("AddCap").value;
            if(/\s/.test(cap) || !/[0-9]/g.test(cap) || cap.length!==5)
            {
                document.getElementById("esitoP").innerHTML = "CAP non può contenere spazi, può contenere solo cifre numeriche e la sua lunghezza è fissata a 5.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioAddress").prop("disabled", true);
                checkHelpAddress.cap=false;
            }
            else
            {
                if(!checkHelpAddress.cap)
                {
                    checkHelpAddress.cap = true;
                    $("#btnInvioAddress").prop("disabled", false);
                }
            }
        })
    });


    $("#newCreditCardSpan button").on("click", function ()
    {
        newRows = "<br><input type=\"text\" id=\"Ncarta\" name=\"nCarta\" placeholder=\"Numero carta\">"
            + "<br>"
            + "<input type=\"month\" id=\"scadenza\" name=\"scadenza\" placeholder=\"Data di scadenza\">"
            + "<br>"
            + "<input type=\"text\" id=\"CVV\" name=\"cvv\" placeholder=\"CVV\">"
            + "<button id='btnInvioCard' type='button' class='btn btn-success' style='background-color: #86d804'>Invio</button>"
            + "<div id=\"dialogConfirmCard\" title=\"Memorizzare carta di credito?\">\n"
            + "<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:12px 12px 20px 0;\"></span>Vuoi conservare questa carta di credito per i prossimi acquisti?</p>\n"
            + "</div><br>";
        document.getElementById("textBoxNewCard").innerHTML = newRows;
        var cardToSend;
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
                        cardToSend=
                            {
                                nCarta : document.getElementById("Ncarta").value,
                                scadenza : document.getElementById("scadenza").value,
                                CVV : document.getElementById("CVV").value,
                                isToStore : true
                            };
                            ajaxCard(cardToSend, checkHelpCard);
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                        cardToSend=
                            {
                                nCarta : document.getElementById("Ncarta").value,
                                scadenza : document.getElementById("scadenza").value,
                                CVV : document.getElementById("CVV").value,
                                isToStore : false
                            };
                        ajaxCard(cardToSend, checkHelpCard);
                    }
                }
            });
        } );
        $( "#btnInvioCard" ).on("click",function (){$( "#dialogConfirmCard" ).dialog("open"); sessionStorage.setItem("selectedCard", "" + -1)});
        $("#Ncarta").change(function ()
        {
            var nCarta=document.getElementById("Ncarta").value;
            if(/\s/.test(nCarta) || nCarta.length!==12 || !/\b[0-9]/g.test(nCarta))
            {
                document.getElementById("esitoP").innerHTML = "Numero carta non può contenere spazi, può contenere solo cifre numeriche e la sua lunghezza è fissata a 12.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioCard").prop("disabled", true);
                checkHelpCard.nCarta=false;
            }
            else
            {
                if(!checkHelpCard.nCarta)
                {
                    checkHelpCard.nCarta = true;
                    $("#btnInvioCard").prop("disabled", false);
                }
            }
        })
        $("#CVV").change(function ()
        {
            var cvv=document.getElementById("CVV").value;
            if(/\s/.test(cvv) || cvv.length!==3 || !/\b[0-9]/g.test(cvv))
            {
                document.getElementById("esitoP").innerHTML = "CVV non può contenere spazi, può contenere solo cifre numeriche e la sua lunghezza è fissata a 3.";
                $("#dialogEsito").dialog("open");
                $("#btnInvioCard").prop("disabled", true);
                checkHelpCard.cvv=false;
            }
            else
            {
                if(!checkHelpCard.cvv) {
                    checkHelpCard.cvv = true;
                    $("#btnInvioCard").prop("disabled", false);
                }
            }
        })
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
                window.location.href="login.html";
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
        newRows += "<span id='prodotto"+i+"' style='display: flex; border: 1px solid rgba(0,0,0,.125); align-items: center'><img src='" + products[i].immagine +"' width='160' height='160'><span id='spanInfoProdotto"+i+"' style='display: flex; align-items: baseline;  justify-content: space-between'><h6>" + products[i].marca + " " + products[i].modello + "</h6><h6 style='margin-left: 5%; margin-right: 5%'>" + products[i].prezzo.toFixed(2) + " &#x20AC</h6><input id=\"quantitaSpinner"+i+"\" name=\"spinner\" value=\""+products[i].quantitaCarrello+"\" style=\"width: 80% ;margin:1%\"><button class='btn btn-danger' style='margin-left: 5%'><i class='fas fa-trash-alt'></i></button></span></span>";
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
            success: function (){showDialog("Prodotto aggiunto", "Aggiunto al carrello con successo");},
            error: function (){showDialog("Errore", "Non è stato possibile aggiornare il carrello")}
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

function showProdottiEsauriti(data)
{
    let prodotti=JSON.parse(data.responseText);
    if(prodotti!==undefined)
    {
        let n=prodotti.length;
        let newRows="I seguenti prodotti non sono disponibili nella quantità richiesta: ";
        for(let i=0; i<n; i++)
        {
            newRows += prodotti[i].codiceABarre + " ";
        }
        document.getElementById("esitoP").innerHTML = newRows;
    }
    else
        document.getElementById("esitoP").innerHTML = "Errore in fase di check-out. Per favore riprova, se il problema persiste contatta il nostro centro assistenza.";
    $("#dialogEsito").dialog("open");
}

function buildTableCategoriesProductPage(categories)
{
    var newRows="";
    var n = categories.length;
    for(let i=0; i<n; i++)
    {
        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\" style='position: inherit; padding-left: 2%'><p style='margin-top: 5%; margin-bottom: 5%; cursor: pointer'>"+ categories[i].nome +"</p></li>";
    }
    document.getElementById("categoriesListUl").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $( "#listItem" + i ).on( "click", function()
        {
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
        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\" style='position: inherit; padding-left: 2%'><p style='margin-top: 5%; margin-bottom: 5%; cursor: pointer'>"+ categories[i].nome +"</p></li>";
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
                    error: function (){showDialog("Errore", "Non è stato possibile caricare i prodotti")}
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
        newRows+= "<li id='prodotto"+ i +"' class=\"ui-state-default\"><span id='prodottoSpan' class='row' style='padding: 1.75%'><img class='col-12' width='160' height='160' src=\"" + products[i].immagine+"\">" + "<p class='col-12'>" + products[i].marca +"</p> <p class='col-12'>"+ products[i].modello + "</p><p class='col-12'> &#x20AC " + products[i].prezzo.toFixed(2) + "</p><button id='btnAggiungi"+i+"' type=\"button\" class=\"btn btn-success cols-12\">Aggiungi al carrello</button><p id='disponibilitaP' class='col-12'> "+ isAvailable(products[i].disponibilita, i) + "<i id='icon"+i+"' style='margin-left: 3%'></i></p></span></li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "100%" });
    $("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "180px", "height": "360px", "font-size": "1em", "text-align" : "center" });
    $("#selectableTableProducts ol").css("alignment", "center");
    for(let i=0; i<n; i++)
    {
        if(products[i].disponibilita===false)
        {
            $("#prodotto" + i + " span button").prop("disabled", true);
            $("#icon" + i).css("color", "rgb(221 47 47)");
            $("#icon" + i).addClass("fas fa-times-circle");
        }
        else
        {
            $("#icon" + i).css("color", "#198754");
            $("#icon" + i).addClass("fas fa-check-circle");
        }

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
    {
        return "Disponibile";
    }
    else
    {
        return "Esaurito";
    }
}

function toStringDatePlusMonth(toConvert)
{
    let month=1+toConvert.month;
    return "" + toConvert.year + "-" + month + "-" + toConvert.dayOfMonth + " " + toConvert.hourOfDay + ":" + toConvert.minute + ":" + toConvert.second;
}

function toStringDate(toConvert)
{
    return "" + toConvert.year + "-" + toConvert.month + "-" + toConvert.dayOfMonth + " " + toConvert.hourOfDay + ":" + toConvert.minute + ":" + toConvert.second;
}

function toStringDateMonthYear(toConvert)
{
    return "" + toConvert.year + "-" + toConvert.month;
}