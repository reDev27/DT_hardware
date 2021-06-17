function buildTableCategories(data)
{
    var newRows="";
    var n = data.length;
    for(i=0; i<n; i++)
    {

        newRows+="<li id=\"listItem" + i +"\" tabindex=\""+ i +"\" class=\"ui-tabs-tab ui-state-default ui-tab ui-corner-left\" aria-controls=\"tabs-"+ i +"\" aria-labelledby=\"ui-id-"+ i +"\" aria-selected=\"false\" aria-expanded=\"false\"><a href=\"#productList\">"+ data[i].nome +"</a></li>";
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
        newRows+= "<li className=\"ui-state-default\"><img width='160' height='160' src=\"" + data[0].immagine+"\">" + " " + data[0].marca +" "+ data[0].modello + " " + data[0].prezzo + " "+ data[0].disponibilita + "</li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "100%" });
    $("#selectableTableProducts li").css({"margin": "2%", "padding": "3px", "float": "left", "width": "180px", "height": "275px", "font-size": "1em", "text-align" : "center" });
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