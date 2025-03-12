package misservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginUsr
 */
@WebServlet("/LoginUsr")
public class LoginUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String[] users = { "juani", "goat", "em" };
    private static final String[] passwords = { "goat", "em", "juani" };
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        boolean isValid = false;

        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(usuario) && passwords[i].equals(password)) {
                isValid = true;
                break;
            }
        }

        response.setContentType("text/html");
        
        if (isValid) {
        	response.getWriter().println("<html><head><title>Login Correcto</title></head><body>");
        	response.getWriter().println("<h2>Bienvenido, " + usuario + "!</h2>");
        	response.getWriter().println("<a href='login.html'>Cerrar sesión</a>");
        	response.getWriter().println("</body></html>");
        } else {
        	response.getWriter().println("<html><head><title>Error</title></head><body>");
        	response.getWriter().println("<h2>Usuario o contraseña incorrectos</h2>");
        	response.getWriter().println("<a href='login.html'>Intentar nuevamente</a>");
        	response.getWriter().println("</body></html>");
        }
    }

}
