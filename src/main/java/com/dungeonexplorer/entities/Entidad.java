package main.java.com.dungeonexplorer.entities;

abstract class Entidad implements IEntidad {

    protected int id;
    protected int vidaMax;
    protected String nombre;
    protected int ataque;

    private int vidaActual;

    public Entidad(int id,String nombre, int vidaMax, int ataque) {
        this.id = id;
        this.vidaMax = vidaMax;
        this.nombre = nombre;
        this.ataque = ataque;
        this.vidaActual = vidaMax;
    }

    public int getId() {
        return vidaMax;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }


    public int getVidaMaxima() {
        return vidaMax;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMax = vidaMaxima;
    }

    public void atacar(Entidad entidad, int danio){
        entidad.setVidaActual(entidad.getVidaActual() - danio);
    }
}
