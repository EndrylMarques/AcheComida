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
		<form action="produtos" method="post">
			<input type="hidden" name="id" class=""	value="<c:out value="${produto.id}" />"/>
			Nome:<input type="text" name="descricao" value="<c:out value="${produto.descricao}" />"/><br>
			Categoria:	
 		
		<select  name="categorias" >
		<option value="<c:out value="${catProd.id}" />"><c:out value="${catProd.descricao}" /></option>
			<c:forEach var="c" items="${listaCategoria}">	
					<c:if test="${catProd.id != c.id}">
				<option value="<c:out value="${c.id}" />"><c:out value="${c.descricao}" /></option>
				</c:if>
			</c:forEach>
		</select> <br />

			
			Preço: R$<input type="text" name="valor" value="<c:out value="${produto.valor}" />"/><br>
			<input type="submit" value="Salvar" />
		</form>
	</div>

<c:import url="rodape.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>