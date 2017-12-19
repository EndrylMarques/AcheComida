package br.ucsal.acheComida.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucsal.acheComida.dao.LoginDAO;
import br.ucsal.acheComida.model.Vendedor;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("inputEmail");
		String senha = request.getParameter("inputPassword");
		Vendedor vendedor = new Vendedor();
		try {
			vendedor = LoginDAO.checkUser(email, senha);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		
		if(vendedor != null){
			response.sendRedirect("index.jsp");
			HttpSession sessao = request.getSession();
			sessao.setAttribute("sessaoUsuario",vendedor);
		}else{
			RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
			rs.include(request, response);
		}

		
		
}

}
