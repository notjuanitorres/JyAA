package misservlets.practica2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Locale;


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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String language = (String) request.getAttribute("language");
    	if (language == null) language = "es";

    	ResourceBundle textos = ResourceBundle.getBundle("resources.texto", new Locale(language));

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Login</title></head><body>");
        out.println("<h2>" + textos.getString("tit") + "</h2>");
        out.println("<form method='post' action='/compras'>");
        out.println(textos.getString("usr") + ": <input type='text' name='username'><br>");
        out.println(textos.getString("pwd") + ": <input type='password' name='password'><br>");
        out.println("<input type='submit' value='Goat'>");
        out.println("</form>");
        out.println("</body></html>");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
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
