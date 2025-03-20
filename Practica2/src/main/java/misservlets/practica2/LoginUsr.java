package misservlets.practica2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Servlet implementation class LoginUsr
 */
@WebServlet(
		initParams = {
				@WebInitParam(name = "juani", value = "goat"),
				@WebInitParam(name = "goat", value = "em"),
				@WebInitParam(name = "em", value = "juani"),
		},
		urlPatterns={""}
		)
public class LoginUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private Hashtable<String, String> logins;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsr() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
        logins = new Hashtable<String, String>();
        Enumeration<String> parameterNames = getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
        	String paramName = parameterNames.nextElement();
        	String paramValue = getInitParameter(paramName);
        	logins.put(paramName, paramValue);
        	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/compras/login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        if (logins.containsKey(username) && logins.get(username).equals(password)) {
        	HttpSession session = request.getSession(true);
        	
        	session.setMaxInactiveInterval(60);
        	
        	session.setAttribute("username", username);
        	
        	
        	response.sendRedirect("/compras/Productos");
        } else {
        	response.getWriter().println("<html><head><title>Error</title></head><body>");
        	response.getWriter().println("<h2>Usuario o contraseña incorrectos</h2>");
        	response.getWriter().println("<a href='/compras'>Intentar nuevamente</a>");
        	response.getWriter().println("</body></html>");
        }
    }

}
