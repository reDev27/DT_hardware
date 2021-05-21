<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: rEDOx
  Date: 19/05/2021
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ciao</h1>
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
  int i;
  while ((i = in.read(immagine, 0, length)) != -1)
  {
    output.write(immagine, 0, i);
  }
  String encoded = Base64.getEncoder().encodeToString(immagine);
  request.setAttribute("imm", encoded);
  output.flush();
  output.close();

%>


<img id="provaImmagine" src="">
<script>
  var laMiaImmagine="<%=request.getAttribute("imm")%>";
  document.getElementById("provaImmagine").src = "data:image/png;base64," + laMiaImmagine;
</script>
</body>
</html>
