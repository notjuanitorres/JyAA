package misservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class Encuesta
 */
@WebServlet("/Encuesta")
public class Encuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final HashMap<String, Integer> votos = new HashMap<>();

    static {
        votos.put("Perro", 0);
        votos.put("Caballo", 0);
        votos.put("Hamster", 0);
        votos.put("Tortuga", 0);
        votos.put("Cangrejo", 0);
        votos.put("Gato", 0);
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Encuesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("mascotas.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] seleccionados = request.getParameterValues("mascotas");

        if (seleccionados != null) {
            synchronized (votos) {
                for (String mascota : seleccionados) {
                    votos.put(mascota, votos.get(mascota) + 1);
                }
            }
        }

        String mascotaMasVotada = null;
        int maxVotos = 0;
        
        for (String mascota : votos.keySet()) {
            if (votos.get(mascota) > maxVotos) {
                maxVotos = votos.get(mascota);
                mascotaMasVotada = mascota;
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>Resultados de la Encuesta</title></head><body>");
        response.getWriter().println("<h2>Resultados de la Encuesta</h2>");
        response.getWriter().println("<table border='1'><tr><th>Mascota</th><th>Votos</th></tr>");

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(votos.entrySet());
        lista.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : lista) {
            response.getWriter().println("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
        }

        response.getWriter().println("</table>");
        response.getWriter().println("<h3>La mascota más votada es: <b>" + (mascotaMasVotada != null ? mascotaMasVotada : "Ninguna") + "</b></h3>");
        response.getWriter().println("<br><a href='mascotas.html'>Volver a la encuesta</a>");
        response.getWriter().println("</body></html>");
    }

}
