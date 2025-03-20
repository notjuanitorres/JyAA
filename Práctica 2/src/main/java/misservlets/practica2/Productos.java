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
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class Productos
 */
@WebServlet(
	    urlPatterns = "/Productos",
	    initParams = {
	        @WebInitParam(name = "names", value = "Chocolate,Caramelo,Chicle"),
	        @WebInitParam(name = "prices", value = "10.0,5.0,2.0")
	    }
	)
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private Map<String, Double> golosinas;
	
	@Override
    public void init() throws ServletException {
        golosinas = new HashMap<>();

        String nombres = getServletConfig().getInitParameter("names");
        String precios = getServletConfig().getInitParameter("prices");

        String[] nombresArray = nombres.split(",");
        String[] preciosArray = precios.split(",");

        if (nombresArray.length != preciosArray.length) {
            throw new ServletException("Los parámetros 'nombres' y 'precios' no coinciden en longitud.");
        }

        for (int i = 0; i < nombresArray.length; i++) {
            golosinas.put(nombresArray[i].trim(), Double.parseDouble(preciosArray[i].trim()));
        }
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Productos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }
    	
    	response.setContentType("text/html");
    	    	
        PrintWriter writer = response.getWriter();

        writer.write("<!DOCTYPE html>");
        writer.write("<html>");
        writer.write("<head><title>Formulario de Golosinas</title></head>");
        writer.write("<body>");
        writer.write("<h1>Seleccione las golosinas que desea comprar</h1>");
        writer.write("<form action='Productos' method='post'>");
        writer.write("<table border='1'>");
        writer.write("<tr><th>Nombre</th><th>Precio Unitario</th><th>Cantidad</th></tr>");

        for (Map.Entry<String, Double> entry : golosinas.entrySet()) {
            writer.write("<tr>");
            writer.write("<td>" + entry.getKey() + "</td>");
            writer.write("<td>" + entry.getValue() + "</td>");
            writer.write("<td><input type='number' name='quantity_" + entry.getKey() + "' min='0'></td>");
            writer.write("</tr>");
        }

        writer.write("</table>");
        writer.write("<br>");

        writer.write("<div style='display: flex; gap: 10px;'>");
        writer.write("<input type='submit' value='Facturar'>");
        writer.write("<button type='button' onclick=\"window.location.href='/compras/TerminarSesion';\">Salir</button>");
        writer.write("</div>");

        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");

        writer.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        
        
        session.setAttribute("golosinas", golosinas);

        for (Map.Entry<String, Double> entry : golosinas.entrySet()) {
            String quantityParam = request.getParameter("quantity_" + entry.getKey());
            if (quantityParam != null && !quantityParam.isEmpty()) {
            	
            	
                int cantidad = Integer.parseInt(quantityParam);
                
                Integer cantidadPrevia = (Integer) session.getAttribute(entry.getKey() + "_quantity");
                if (cantidadPrevia == null) {
                    cantidadPrevia = 0;
                }
                
                session.setAttribute(entry.getKey() + "_quantity", cantidad+cantidadPrevia);
                session.setAttribute(entry.getKey() + "_price", (cantidad+cantidadPrevia)*entry.getValue());
            }
        }

        response.sendRedirect("Facturar");
    }

}
