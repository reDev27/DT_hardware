var p;
var canvas = document.createElement("canvas");
var img1=document.createElement("img");

function getBase64Image(elementId){
    p=document.getElementById(elementId).value;
    img1.setAttribute('src', p);
    canvas.width = img1.width;
    canvas.height = img1.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img1, 0, 0);
    var dataURL = canvas.toDataURL("image/png");
    alert("from getbase64 function"+dataURL );
    return dataURL;
}