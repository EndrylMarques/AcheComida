package br.ucsal.acheComida.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.acheComida.dao.CategoriaDAO;
import br.ucsal.acheComida.dao.ProdutoDAO;
import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.model.Produto;

@WebServlet("/produtos")
public class ProdutoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");
		if (q != null && q.equals("new")) {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			request.setAttribute("listaCategoria", categoriaDAO.listar());
			request.getRequestDispatcher("produtoForm.jsp").forward(request, response);
		} else {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			request.setAttribute("lista", produtoDAO.listar());
			request.getRequestDispatcher("produtoList.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descricao = request.getParameter("descricao");
		String categoriaID = request.getParameter("categoriaid");
		String valor = request.getParameter("valor");

		Produto produto = new Produto();
		produto.setDescricao(descricao);

		CategoriaDAO cDao = new CategoriaDAO();
		int id = Integer.parseInt(categoriaID);
		Categoria categoria = cDao.getByID(id);
		produto.setCategoria(categoria);

		Double v = Double.parseDouble(valor);
		produto.setValor(v);

		ProdutoDAO dao = new ProdutoDAO();

		dao.inserir(produto);

		request.setAttribute("lista", dao.listar());
		request.getRequestDispatcher("produtoList.jsp").forward(request, response);
	}
}
