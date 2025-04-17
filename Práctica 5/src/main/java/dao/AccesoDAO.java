package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

public class AccesoDAO {

    private DataSource ds;
    public AccesoDAO() throws NamingException {
        Context ctx = new InitialContext();
        ds = (DataSource) ctx.lookup("java:comp/env/jdbc/LogDB");
    }
    public void registrarAcceso(Timestamp fechaHora, String ip, String recurso, String navegador) throws SQLException {
        String sql = "INSERT INTO log_accesos (fecha_hora, ip_cliente, recurso_solicitado, navegador) VALUES (?, ?, ?, ?)";
        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, fechaHora);
            ps.setString(2, ip);
            ps.setString(3, recurso);
            ps.setString(4, navegador);
            ps.executeUpdate();
        }       
    }
    public List<Map<String, String>> obtenerTodos() throws SQLException {
        List<Map<String, String>> lista = new ArrayList<>();
        String sql = "SELECT fecha_hora, ip_cliente, recurso_solicitado, navegador FROM log_accesos ORDER BY fecha_hora DESC";

        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, String> fila = new HashMap<>();
                fila.put("fecha", rs.getTimestamp("fecha_hora").toString());
                fila.put("ip", rs.getString("ip_cliente"));
                fila.put("recurso", rs.getString("recurso_solicitado"));
                fila.put("navegador", rs.getString("navegador"));
                lista.add(fila);
            }
        }

        return lista;
    }
} 