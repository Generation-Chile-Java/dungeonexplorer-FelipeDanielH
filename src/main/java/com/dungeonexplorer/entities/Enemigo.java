package main.java.com.dungeonexplorer.entities;

public class Enemigo extends Entidad {

    private int ubicacion;

    public Enemigo(int id, String nombre, int vidaMax, int ataque, int ubicacion) {
        super(id, nombre, vidaMax, ataque);
        this.ubicacion = ubicacion;
    }

    public int getUbicacion() {
        return ubicacion;
    }


}
