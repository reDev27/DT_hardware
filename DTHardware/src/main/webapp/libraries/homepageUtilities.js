function buildTableCategories(data)
{
    var newRows="";
    var n = data.length;
    for(i=0; i<n; i++)
    {

        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\"><a href='#btnCategorie'>"+ data[i].nome +"</a></li>";
    }
    //ui-tabs-active ui-state-active
    document.getElementById("categoriesListUl").innerHTML=newRows;
    $( "#categoriesList" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    $( "#categoriesList li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
    $( "#listItem0" ).removeClass( "ui-state-active" );
    $( "#listItem0" ).removeClass( "ui-tabs-active" );
    $("#categoriesList").css("border", 0);
}

function buildTableProductHomepage(data)
{
    var n=data.length;
    var newRows="";
    for(i=0; i<n; i++)
    {
        newRows+= "<li className=\"ui-state-default\"><span id='prodottoSpan' class='row' style='padding: 1.5%'><img class='col-12' width='160' height='160' src=\"" + data[0].immagine+"\">" + "<p class='col-12'>" + data[0].marca +"</p> <p class='col-12'>"+ data[0].modello + "</p><p class='col-12'> " + data[0].prezzo + "</p><button type=\"button\" class=\"btn btn-primary cols-12\">Aggiungi al carrello</button><p class='col-12'> "+ data[0].disponibilita + "</p></span></li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "100%" });
    $("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "180px", "height": "360px", "font-size": "1em", "text-align" : "center" });
    $("#selectableTableProducts ol").css("alignment", "center");
}

/*var n=data.length;
var newRows="";
for(i=0; i<n; i++)
{
    newRows+= "<li className=\"ui-state-default\">" + data[i].marca + " " + data[i].modello + " " + data[i].prezzo + "</li>";
}
document.getElementById("selectableTableProducts").innerHTML=newRows;
$("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "70%" });
$("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "200px", "height": "250px", "font-size": "1em", "text-align" : "center" });
$("#selectableTableProducts ol").css("alignment", "center");*/