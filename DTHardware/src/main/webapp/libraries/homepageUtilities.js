function buildTableCategories(data)
{
    var newRows="<thead>Categorie</thead>";
    var n = data.length;
    for(i=0; i<n; i++)
    {
        newRows+="<tr><td>"+ data[i].nome +"</td></tr>";
    }
    //$("#tableCategories").html(newRows);
    document.getElementById("tableCategories").innerHTML=newRows;
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