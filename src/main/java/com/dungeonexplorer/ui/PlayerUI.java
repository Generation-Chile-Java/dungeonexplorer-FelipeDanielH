package main.java.com.dungeonexplorer.ui;

import main.java.com.dungeonexplorer.entities.Personaje;

public class PlayerUI {
    public void showPersonaje(Personaje personaje){
        System.out.println("\n--- Estadisticas ---"
                + "\nNombre: " + personaje.getNombre()
                + "\nVida: " + personaje.getVidaActual() + "/" + personaje.getVidaMax()
                + "\nAtaque: " + personaje.getAtaque()
        );
        System.out.println("----------------------------");
    }

    public void showInventario(){
        System.out.println("inventario en construccion");
    }
}
