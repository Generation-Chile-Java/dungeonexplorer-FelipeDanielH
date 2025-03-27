package main.java.com.dungeonexplorer.rooms;

import java.util.Map;

public class HabitacionComun extends Habitacion{

    public HabitacionComun(int id, String nombre, String descripcion, String tipo, Map<String, Integer> adyacentes) {
        super(id, nombre, descripcion, tipo="comun", adyacentes);

    }
}
