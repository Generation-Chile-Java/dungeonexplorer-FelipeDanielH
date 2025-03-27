package main.java.com.dungeonexplorer.rooms;

import java.util.Map;

public interface IHabitacion {
    int getId();
    String getTipo();
    String getNombre();
    String getDescripcion();
    Map<String, Integer> getAdyacentes();
}