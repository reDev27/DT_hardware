<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<script src="${pageContext.request.contextPath}/libraries/jQuery_current.js"></script>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    .hello
    {
        color : green;
    }

    .ciao
    {
        color : #cd1313;
    }
</style>
<body>
<h1 id="hello"><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet" id="uatto" class="ciao" style="text-shadow: crimson; color: aqua; background-color: darkgreen">mannagg</a>
<p id="jquery01">questo lo midifico con jquery</p>
<h1 id="uao" style="text-shadow: crimson; color: aqua; background-color: darkmagenta">ciao</h1>
<script>
    $("#jquery01").mouseenter(function (){$("#jquery01").addClass("hello"); $("#jquery01").addClass("ciao");});
    $("#jquery01").mouseleave(function (){$("#jquery01").removeClass("ciao"); $("#jquery01").addClass("hello");});
    document.getElementById("uao").ondblclick=function ()
    {
        navigator.cookieEnabled;
        document.getElementById("hello").id="true";
        document.getElementById("true").innerText="ho cambiato l'ID";
        //document.write("<h1 id=\"o\">ho cancellato tutto</h1>")
    };
    var b=new Date();
    b.getDate();
</script>
</body>
</html>