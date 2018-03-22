<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Food Finder</title>

<link href="/acheComida/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/acheComida/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">


</head>
<body>
<c:import url="topo.jsp"></c:import>


	<h1>Home</h1>
	<table class="table"  >
<tr>

<th> Nome </th>
<th> Produto </th>
<th> Valor </th>
<th> Telefone </th>
</tr>

<c:forEach var="e" items="${lista}">
<tr>

 <td> ${e.nome} </td>
 <td> ${e.produto.descricao} </td>
 <td> ${e.produto.valor} </td>
 <td> ${e.telefone} </td>

 
 
<tr>
</c:forEach>
</table>
<c:import url="rodape.jsp"></c:import>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>