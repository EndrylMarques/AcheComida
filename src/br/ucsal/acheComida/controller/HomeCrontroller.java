package br.ucsal.acheComida.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.acheComida.dao.VendedorDAO;

/**
 * Servlet implementation class HomeCrontroller
 */
@WebServlet("/")
public class HomeCrontroller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
    
     */
    public HomeCrontroller() {
   
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		request.setAttribute("listaVendedor", vendedorDAO.listar());
		request.getRequestDispatcher("indexUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
