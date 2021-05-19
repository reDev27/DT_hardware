<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<br>
<br>
<%
    Blob immagineBlob=(Blob) request.getAttribute("immagine");
    InputStream in=null;
    int length = 0;
    try
    {
        in=immagineBlob.getBinaryStream();
        length = (int) immagineBlob.length();
    }
    catch (SQLException throwables)
    {
        throwables.printStackTrace();
    }
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] immagine=new byte[200000];
    int bufferSize = 1024;
    byte[] buffer = new byte[bufferSize];
    int i;
    //while ((length = in.read(buffer)) != -1)
    while ((i = in.read(immagine, 0, length)) != -1)
    {
        output.write(immagine, 0, i);
        //outStream.write(buffer, 0, length);
    }

    //String s= Base64.getEncoder().encodeToString(output.toByteArray());
    String s=Arrays.toString(output.toByteArray());
    request.setAttribute("imm", s);
%>


<img id="provaImmagine" src=""></img>
<script>
    document.getElementById("provaImmagine").src = "data:image/png;base64," + btoa(String.fromCharCode.apply(null, new Uint8Array(<%=request.getAttribute("imm")%>)));
</script>

</body>
</html>