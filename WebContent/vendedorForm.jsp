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
			<h1>Inserir Vendedor</h1>
		</div>
		<form action="vendedores" method="post">
		    <input type="hidden" name="id" class=""	value="<c:out value="${vendedor.id}" />"/>
		    Nome:<input type="text" name="nome" value="<c:out value="${vendedor.nome}" />"/><br>
			Email:<input type="email" name="email" value="<c:out value="${vendedor.email}" />" /><br>
			Senha:<input type="password" name="senha" value="<c:out value="${vendedor.senha}" />"/><br>
			Telefone:<input type="text" name="telefone" value="<c:out value="${vendedor.telefone}" />"/><br>
			Produtos:	
 	
 			<select  name="produtos" >
			<option value="${vendedor.produto.id}"><c:out value="${vendedor.produto}" /></option>
			<c:forEach var="c" items="${listaProduto}">	
					
				<option value="<c:out value="${c.id}" />"><c:out value="${c.descricao}" /></option>
			</c:forEach>
		</select> <br />
 
		
			<br />
			
			<input type="submit" value="Salvar" />
		</form>
	</div>

<c:import url="rodape.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>