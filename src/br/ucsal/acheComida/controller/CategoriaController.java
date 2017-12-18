package br.ucsal.acheComida.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.acheComida.dao.CategoriaDAO;
import br.ucsal.acheComida.dao.UsuarioDAO;
import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.model.Usuario;

@WebServlet("/categorias")
public class CategoriaController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String q = request.getParameter("q");

		if (q != null && q.equals("new")) {
			request.getRequestDispatcher("categoriaForm.jsp").forward(request, response);
			return;
		}

		CategoriaDAO dao = new CategoriaDAO();

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			Categoria categoria = dao.getByID(Integer.parseInt(id));
			request.setAttribute("categoria", categoria);
			request.getRequestDispatcher("categoriaForm.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			dao.excluir(Integer.parseInt(id));
		}

		// Excessao Abafada
		if (!response.isCommitted()) {
			request.setAttribute("lista", dao.listar());
			request.getRequestDispatcher("categoriaList.jsp").forward(request, response);
		
		}
	
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String descricao = request.getParameter("descricao");

		Categoria categoria = new Categoria(descricao);
		CategoriaDAO dao = new CategoriaDAO();

		if (id.isEmpty()) {
			dao.inserir(categoria);
		} else {
			categoria.setId(Integer.parseInt(id));
			dao.atualizar(categoria);
			
		}

		request.setAttribute("listaOrdem", dao.organizar());
		request.getRequestDispatcher("categoriaOrdem.jsp").forward(request, response);

	}
}
