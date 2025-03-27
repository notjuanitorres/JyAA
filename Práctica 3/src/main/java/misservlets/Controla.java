package misservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Controla
 */
@WebServlet("/Controla")
public class Controla extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Controla() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		
        String opcion = request.getParameter("opcion");

        if ("Hola".equals(opcion)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("HolaServlet");
            dispatcher.forward(request, response);
        } else if ("Productos".equals(opcion)) {
    		ServletContext context = getServletContext().getContext("/compras");
    		RequestDispatcher reDisp = context.getRequestDispatcher("/Productos");
    		reDisp.forward(request,  response);
        }
        else if ("Google".equals(opcion)) {
        	response.sendRedirect("https://www.google.com");
        }
        else
        {
            response.sendRedirect("/pruebas");
        }
    }

}
