<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintStream" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Scanner" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<import value="net.sf.jasperreports.engine.util.JRImageLoader"></import>
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

    byte[] immagine=in.readAllBytes();
   /* int bufferSize = 1024;
    byte[] buffer = new byte[bufferSize];
    while ((length = in.read(buffer)) != -1)
    {
    	printWriter.write(immagine, 0, length);
        //outStream.write(buffer, 0, length);
    }*/
    request.setAttribute("imm", immagine);
%>
<imageExpression class="java.awt.Image"><br />
    net.sf.jasperreports.engine.util.JRImageLoader.loadImage(buffer)<br />
</imageExpression>
<img id="provaImmagine" src="">
<script>
    document.getElementById("provaImmagine").src = "data:image/png;base64," + <%=request.getAttribute("imm")%>;
</script>
</body>
</html>