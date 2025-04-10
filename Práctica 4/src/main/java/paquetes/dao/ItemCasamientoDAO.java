package paquetes.dao;

import paquetes.model.ItemCasamiento;
import java.util.List;

public interface ItemCasamientoDAO {
    List<ItemCasamiento> obtenerTodos();
    void guardar(ItemCasamiento item);
}