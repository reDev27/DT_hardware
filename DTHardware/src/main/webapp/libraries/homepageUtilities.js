function buildTableCategories(data)
{
    document.getElementById("tableCategories").innerText="<thead>" + "Categorie" + "</thead>";
    var categorie=data;
    console.log(categorie[0].nome);
    for(var i=0; i<categorie.lenght; i++)
    {
        var newRows;
        document.getElementById("tableCategories").innerText="<tr><td>categorie.nome</td></tr>";
    }
}