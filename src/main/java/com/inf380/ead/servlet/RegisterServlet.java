package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoginRegisterService logRegServ;

    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() throws IOException {
        super();
        // TODO Auto-generated constructor stub
		logRegServ = new LoginRegisterService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		boolean userExist = logRegServ.userExist(username);
		if(userExist){
			response.sendRedirect("RegisterFailure.jsp");
			
		}else{
			 // fetch the session from request, create new session if session
	           // is not present in the request
				request.setAttribute("registration", "success");
	           HttpSession session = request.getSession(true); 
	           session.setAttribute("FirstName", username);
	           //create user
	           logRegServ.register(username, password);
	           // redirect to success page
	           response.sendRedirect("Profile.jsp"); 
		}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
