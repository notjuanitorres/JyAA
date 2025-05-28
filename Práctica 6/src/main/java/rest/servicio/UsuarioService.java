package rest.servicio;

import rest.modelo.Usuario;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioService {
    private static final Map<Integer, Usuario> usuarios = new HashMap<>();
    private static final AtomicInteger secuencia = new AtomicInteger(1);

    public Usuario crear(Usuario u) {
        int id = secuencia.getAndIncrement();
        u.setId(id);
        usuarios.put(id, u);
        return u;
    }

    public Usuario buscar(int id) {
        return usuarios.get(id);
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios.values());
    }

    public boolean actualizar(int id, Usuario nuevo) {
        if (!usuarios.containsKey(id)) return false;
        nuevo.setId(id);
        usuarios.put(id, nuevo);
        return true;
    }

    public boolean eliminar(int id) {
        return usuarios.remove(id) != null;
    }
}
