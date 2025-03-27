package main.java.com.dungeonexplorer.entities;

public interface IEntidad {
    int getId();
    int getVidaMax();
    String getNombre();
    int getAtaque();
    int getVidaActual();
    void setVidaActual(int vidaActual);
}
