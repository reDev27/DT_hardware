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

}