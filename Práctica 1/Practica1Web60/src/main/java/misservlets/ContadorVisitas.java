package misservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContadorVisitas
 */
@WebServlet("/ContadorVisitas")
public class ContadorVisitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static int contador = 0;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContadorVisitas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        contador++;

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Este servlet lo visitaron: " + contador + " usuario/s</h1>");
        response.getWriter().println("</body></html>");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
