package br.ucsal.acheComida.controller;

import java.io.IOException;
import java.util.List;

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
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String q = request.getParameter("q");
		if (q != null && q.equals("new")) {
			request.setAttribute("listaCategoria", categoriaDAO.listar());
			request.getRequestDispatcher("produtoForm.jsp").forward(request, response);
			return;
		}

		ProdutoDAO dao = new ProdutoDAO();

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			Produto produto = dao.getByID(Integer.parseInt(id));
			List<Categoria> listaCategoria = categoriaDAO.listar();
			Categoria categoriaProduto = new Categoria();
			for (Categoria categoria : listaCategoria) {
				if (categoria.getId() == produto.getCategoria().getId()) {
					categoriaProduto = categoria;
				}
			}
			request.setAttribute("listaCategoria", listaCategoria);
			request.setAttribute("produto", produto);
			request.setAttribute("catProd", categoriaProduto);
			request.getRequestDispatcher("produtoForm.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			dao.delete(Integer.parseInt(id));
		}

		// Excessao Abafada
		if (!response.isCommitted()) {
			request.setAttribute("lista", dao.listar());
			request.getRequestDispatcher("produtoList.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Produto produto = new Produto();

		produto.setDescricao(request.getParameter("descricao"));
		produto.setValor(Double.parseDouble(request.getParameter("valor")));

		CategoriaDAO cDao = new CategoriaDAO();
		int c = Integer.parseInt(request.getParameter("categorias"));
		Categoria categoria = cDao.getByID(c);
		produto.setCategoria(categoria);

		ProdutoDAO dao = new ProdutoDAO();

		if (id.isEmpty()) {
			dao.inserir(produto);
		} else {
			produto.setId(Integer.parseInt(id));
			dao.update(produto);
		}

		request.setAttribute("lista", dao.listar());
		request.getRequestDispatcher("produtoList.jsp").forward(request, response);

	}
}
