function buildTableCategories(data)
{
    var newRows="";
    var n = data.length;
    for(i=0; i<n; i++)
    {
        newRows+="<li><a href=\"#productList\">"+ data[i].nome +"</a></li>";
    }
    document.getElementById("categoriesListUl").innerHTML=newRows;
}

function buildTableProductHomepage(data)
{
    var n=data.length;
    var newRows="";
    for(i=0; i<n; i++)
    {
        newRows+= "<li className=\"ui-state-default\">" + data[i].marca + " " + data[i].modello + " " + data[i].prezzo + "</li>";
    }
    document.getElementById("selectable").innerHTML=newRows;
}