<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.jsp">Find Food</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">Categoria <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/acheComida/categorias">Listar Categorias</a></li>
					<li><a href="/acheComida/categorias?q=new">Cadastrar
							Categoria</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">Produto <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/acheComida/produtos">Listar Produtos</a></li>
					<li><a href="/acheComida/produtos?q=new">Cadastrar Produto</a></li>
				</ul></li>
				
		</ul>
		</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/acheComida/vendedores?q=new"><span
					class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>
					Login</a></li>
		</ul>
	</div>
</nav>