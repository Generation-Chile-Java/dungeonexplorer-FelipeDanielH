package main.java.com.dungeonexplorer.rooms;

import main.java.com.dungeonexplorer.entities.Enemigo;

import java.util.Map;

public class HabitacionEnemigo extends Habitacion{

    private Enemigo enemigo;

    public HabitacionEnemigo(int id, String nombre, String descripcion ,String tipo, Map<String, Integer> adyacentes, Enemigo enemigo) {
        super(id, nombre, descripcion, tipo="enemigo", adyacentes);
        this.enemigo = enemigo;
    }


    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }
}
