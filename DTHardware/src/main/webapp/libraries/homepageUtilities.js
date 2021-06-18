function buildTableCategories(categories)
{
    var newRows="";
    var n = categories.length;
    for(let i=0; i<n; i++)
    {
        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\"><a href='#btnCategorie'>"+ categories[i].nome +"</a></li>";
    }
    document.getElementById("categoriesListUl").innerHTML=newRows;
    for(let i=0; i<n; i++)
    {
        $( "#listItem" + i ).on( "click", function()
        {
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
                        $( "#selectableTableProducts" ).selectable();
                    },
                    error: function (){alert("error");}
                }
            )
        });
    }
}

function buildTableProductHomepage(products)
{
    document.getElementById("selectableTableProducts").innerHTML="";
    var n=products.length;
    var newRows="";
    for(let i=0; i<n; i++)
    {
        newRows+= "<li id='prodotto"+ i +"' class=\"ui-state-default\"><span id='prodottoSpan' class='row' style='padding: 1.5%'><img class='col-12' width='160' height='160' src=\"" + products[i].immagine+"\">" + "<p class='col-12'>" + products[i].marca +"</p> <p class='col-12'>"+ products[i].modello + "</p><p class='col-12'> " + products[i].prezzo + "</p><button type=\"button\" class=\"btn btn-primary cols-12\">Aggiungi al carrello</button><p class='col-12'> "+ products[i].disponibilita + "</p></span></li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "100%" });
    $("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "180px", "height": "360px", "font-size": "1em", "text-align" : "center" });
    $("#selectableTableProducts ol").css("alignment", "center");
    for(let i=0; i<n; i++)
    {
        $("#prodotto"+i+" span").on("click", function() {
            alert( "clicked"+i );
        });
    }
}