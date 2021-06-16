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
        newRows+= "<li className=\"ui-state-default\">" + data[i].marca + " " + data[i].modello + " " + data[i].prezzo + "</li>";
    }
    document.getElementById("selectableTableProducts").innerHTML=newRows;
    $("#selectableTableProducts").css({"cursor": "pointer","list-style-type": "none", "margin": "0", "padding": "0", "width": "450px" });
    $("#selectableTableProducts li").css({"margin": "3px", "padding": "1px", "float": "left", "width": "100px", "height": "80px", "font-size": "1em", "text-align" : "center" });
    $("#selectableTableProducts ol").css("alignment", "center");
}