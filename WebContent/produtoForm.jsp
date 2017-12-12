<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Food Finder</title>
<link href="/acheComida/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/acheComida/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/acheComida//css/app.css" rel="stylesheet">
</head>

<body>
	<c:import url="topo.jsp"></c:import>
	<div class="container">
		<div class="page-header">
			<h1>Inserir Produto</h1>
		</div>
		<form action="produtos" method="get">
			Nome:<input type="text" name="descricao" /><br>
			Categoria:	
		<select name="categorias" >
			<option value="" selected>Selecione</option>
			<c:forEach var="categorias" items="${lista}">
				<option value="${categorias.id}">${categorias.descricao}</option>
			</c:forEach>
		</select> <br />
			Preço: R$<input type="number" name="valor" /><br> <br>
	
			<input type="submit" value="Salvar" />
		</form>
	</div>

<c:import url="rodape.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>