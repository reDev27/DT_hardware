var canvas = document.createElement("canvas");
var img1=document.createElement("img");

function getBase64Image(data)
{
    img1.setAttribute('src', data);
    canvas.width = 300;
    canvas.height = 300;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img1, 0, 0);
    var x=canvas.toDataURL("image/png");
    return x;
}