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
    newRows += "<button id='btnAggiungiAddress' class='btn btn-success' type='button' style='margin-bottom: 3%'>Aggiungi</button><span id='inputAddress'></span>";
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
            + "<button id='btnInvioAddress' type='button' style='margin-bottom: 3%' class='btn btn-success'>Invio</button>"
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
    /*for(let i=0; i<n; i++)
    {
        newRows += ;
    }*/
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
            error : function () {alert("error")}
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
            error : function () {},
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
            error : function () {alert("error");},
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
    newRows += "<span id='newAddressSpan' style='margin-top: 3%'><button class='btn btn-success' style='margin-bottom: 3%'>aggiungi indirizzo</button><span id='textBoxNewAddress'></span></span><br>"
    for(let iCard=0; iCard<nCard; iCard++)
    {
        newRows += "<span id='creditCardSpan' style='margin-right: 5%'><input type='radio' id='radioCard"+iCard+"' name='card'><label for='radioCard"+iCard+"'>" + cliente.creditCards[iCard].nCarta + "</label></span>";
    }
    newRows += "<span id='newCreditCardSpan' style='margin-top: 3%'><button class='btn btn-success'>aggiungi carta di credito</button><span id='textBoxNewCard'></span></span>"
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
            + "<button id='btnInvioAddress' type='button' style='margin-bottom: 3%' class='btn btn-success'>Invio</button>"
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
            + "<input type=\"date\" id=\"scadenza\" name=\"scadenza\" placeholder=\"Data di scadenza\">"
            + "<br>"
            + "<input type=\"text\" id=\"CVV\" name=\"cvv\" placeholder=\"CVV\">"
            + "<button id='btnInvioCard' type='button' class='btn btn-success'>Invio</button>"
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

function toStringDateMonthYear(toConvert)
{
    return "" + toConvert.year + "-" + toConvert.month;
}