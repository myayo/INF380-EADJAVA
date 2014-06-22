package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoginRegisterService logRegSer;

    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() throws IOException {
        super();
        // TODO Auto-generated constructor stub
        LoginRegisterService logRegServ = new LoginRegisterService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		boolean userExist = logRegServ.log(username, password);
		if(userExist){
			 // fetch the session from request, create new session if session
           // is not present in the request
			request.setAttribute("login", "success");
           HttpSession session = request.getSession(true); 
           session.setAttribute("FirstName", username);
           // redirect to success page
           response.sendRedirect("Profile.jsp"); 
		}else{
			response.sendRedirect("LoginFailure.jsp");
		}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
