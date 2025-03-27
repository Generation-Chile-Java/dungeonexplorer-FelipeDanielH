package main.java.com.dungeonexplorer.objects;

import main.java.com.dungeonexplorer.entities.Personaje;

public class PocionDeVida implements IObjeto<Personaje> {

    private int id;
    private String nombre;
    private int cantidad;

    public PocionDeVida(int cantidad) {
        this.id = 1;
        this.nombre = "Pocion de Vida";
        this.cantidad = cantidad;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void usar(Personaje personaje) {
        int vidaCurada = 10;
        personaje.setVidaActual(personaje.getVidaActual() + 10);
        if (personaje.getVidaActual() > personaje.getVidaMaxima()) {
            vidaCurada = personaje.getVidaActual() - personaje.getVidaMaxima();
            personaje.setVidaActual(personaje.getVidaMaxima());
        }
        System.out.println("¡Has recuperado " + vidaCurada + " de vida!\n");
    }
}
