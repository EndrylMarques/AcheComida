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
			<h1>Lista de Usuarios</h1>
		</div>

		<table class="table">
			<tr>

				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>Excluir Usuario</th>
			</tr>

			<c:forEach var="e" items="${lista}">
				<tr>

					<td><a href="/acheComida/usuarios?q=editar&id=${e.id}">${e.nome}
					</a></td>
					<td>${e.email}</td>
					<td>${e.telefone}</a></td>

					<td><a href = "#" data-href="/acheComida/usuarios?q=excluir&id=${e.id}"  data-toggle="modal" data-target="#confirm-delete"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
						</td>
				<tr>
			</c:forEach>
		</table>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
                </div>
            
                <div class="modal-body">
                    <p>Deseja excluir Registro?</p>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-danger btn-ok">Delete</a>
                </div>
            </div>
        </div>
    </div>

	<c:import url="rodape.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script src="/acheComida/bootstrap/js/bootstrap.min.js"></script>
<script>
$('#confirm-delete').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});
</script>
</body>

</html>