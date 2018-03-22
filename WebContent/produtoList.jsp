<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
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
<link href="/acheComida//css/app.css" rel="stylesheet">


</head>

<body>
	<c:import url="topo.jsp"></c:import>
	<div class="container">
		<div class="page-header">
			<h1>Lista de Produtos</h1>
		</div>

		<table class="table">
			<tr>
				<th>Nome</th>
				<th>Categoria</th>
				<th>Preço</th>
				<th>Excluir Produto</th>
			</tr>

			<c:forEach var="c" items="${lista}">
				<tr>
					<td><a href="/acheComida/produtos?q=editar&id=${c.id}">${c.descricao}
					</a></td>
					<td>${c.categoria.descricao}</td>
					<td>${c.valor}</td>
					<td><a href="/acheComida/produtos?q=excluir&id=${c.id}"><span
							class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
				<tr>
			</c:forEach>
		</table>
	</div>

	<c:import url="rodape.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>