package misservlets.practica2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Servlet implementation class Facturar
 */
@WebServlet("/Facturar")
public class Facturar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facturar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession(false);
                

        writer.write("<!DOCTYPE html>");
        writer.write("<html>");
        writer.write("<head><title>Factura</title></head>");
        writer.write("<body>");
        writer.write("<h1>Resumen de Compra</h1>");
        writer.write("<table border='1'>");
        writer.write("<tr><th>Nombre</th><th>Cantidad</th><th>Precio Total</th></tr>");

        double totalGeneral = 0;
        
        @SuppressWarnings("unchecked")
        
        ServletContext context = getServletContext();
        Hashtable<String, Double> golosinas = (Hashtable<String, Double>) context.getAttribute("stock");
        

        for (Map.Entry<String, Double> entry : golosinas.entrySet()) {
            String nombreGolosina = entry.getKey();
            Integer cantidad = (Integer) session.getAttribute(nombreGolosina + "_quantity");
            Double precio = (Double) entry.getValue();
            
            if (cantidad != null && cantidad > 0) {
                double precioTotal = cantidad * precio;
                totalGeneral += precioTotal;

                writer.write("<tr>");
                writer.write("<td>" + nombreGolosina + "</td>");
                writer.write("<td>" + cantidad + "</td>");
                writer.write("<td>" + precioTotal + "</td>");
                writer.write("</tr>");
            }
        }

        writer.write("<tr><td colspan='2'><strong>Total General</strong></td>");
        writer.write("<td><strong>" + totalGeneral + "</strong></td></tr>");
        writer.write("</table>");

        writer.write("<br>");
        writer.write("<a href='Productos'>Seguir comprando</a>");
        writer.write("<br>");
        writer.write("<a href='TerminarSesion'>Salir</a>");

        writer.write("</body>");
        writer.write("</html>");
        writer.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
