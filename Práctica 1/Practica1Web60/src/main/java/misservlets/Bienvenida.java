package misservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Bienvenida
 */
@WebServlet("/Bienvenida")
public class Bienvenida extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Bienvenida() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("nombre");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Bienvenida</title></head><body>");
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            response.getWriter().println("<h1>Bienvenido, " + nombre + "!</h1>");
        } else {
            response.getWriter().println("<h1>Bienvenido, visitante!</h1>");
        }
        
        response.getWriter().println("</body></html>");
        
		response.getWriter().append("If you are here, you're the GOAT.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
