package br.ucsal.acheComida.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.acheComida.dao.ProdutoDAO;
import br.ucsal.acheComida.dao.VendedorDAO;
import br.ucsal.acheComida.model.Produto;
import br.ucsal.acheComida.model.Vendedor;

@WebServlet("/vendedores")
public class VendedorController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");

		if (q != null && q.equals("new")) {
			request.getRequestDispatcher("vendedorForm.jsp").forward(request, response);
			return;
		}

		VendedorDAO dao = new VendedorDAO();

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			Vendedor vendedor = dao.getByID(Integer.parseInt(id));
			request.setAttribute("vendedor", vendedor);
			request.getRequestDispatcher("vendedorForm.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			dao.delete(Integer.parseInt(id));
		}

		request.setAttribute("lista", dao.listar());
		request.getRequestDispatcher("vendedorList.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String telefone = request.getParameter("telefone");
		String produtoID = request.getParameter("produto");

		Vendedor vendedor = new Vendedor();
		vendedor.setNome(nome);
		vendedor.setEmail(email);
		vendedor.setSenha(senha);
		vendedor.setTelefone(telefone);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		int idProduto = Integer.parseInt(produtoID);
		Produto produto = produtoDAO.getByID(idProduto);

		vendedor.setProduto(produto);

		VendedorDAO dao = new VendedorDAO();
		dao.inserir(vendedor);

		if (id != null && !id.isEmpty()) {
			vendedor.setId(Integer.parseInt(id));
			dao.update(vendedor);
		} else {
			dao.inserir(vendedor);
		}

		request.setAttribute("lista", dao.listar());
		request.getRequestDispatcher("vendedorList.jsp").forward(request, response);

	}
}