<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<form class="form-signin" action="LoginServlet" method="post">
			<h3 class="form-signin-heading">Autenticação do Usuário</h3>
			<label for="inputEmail" class="sr-only">Email</label> <input
				type="email" name="inputEmail" class="form-control"
				placeholder="Email" required autofocus> <label
				for="inputPassword" class="sr-only">Senha</label> <input
				type="password" name="inputPassword" class="form-control"
				placeholder="Senha" required>
			<button class="btn btn-primary btn-block" type="submit">Entrar</button>
			
		</form>
	</div>

</body>
</html>