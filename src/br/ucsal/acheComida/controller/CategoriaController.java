+package br.ucsal.acheComida.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.acheComida.dao.CategoriaDAO;
import br.ucsal.acheComida.model.Categoria;

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
			request.getRequestDispatcher("categoriaform.jsp").forward(request, response);
		} else {
			CategoriaDAO dao = new CategoriaDAO();
			request.setAttribute("lista", dao.listar());
			request.getRequestDispatcher("categorialist.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descricao = request.getParameter("descricao");
	
		Categoria categoria = new Categoria(descricao);
		CategoriaDAO dao = new CategoriaDAO();
		dao.inserir(categoria);

		request.setAttribute("lista", dao.listar());
		request.getRequestDispatcher("categorialist.jsp").forward(request, response);

	}
}

