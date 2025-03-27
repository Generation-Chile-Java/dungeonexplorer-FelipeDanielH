package main.java.com.dungeonexplorer.rooms;


import java.util.Map;

public abstract class Habitacion implements IHabitacion {
    protected int id;
    protected String tipo;
    protected String nombre;
    protected String descripcion;
    protected boolean visitado;

    protected Map<String,Integer> adyacentes;

    public Habitacion(int id,
                      String nombre,
                      String descripcion,
                      String tipo,
                      Map<String, Integer> adyacentes
    ){
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.adyacentes = adyacentes;
        this.visitado = false;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Map<String, Integer> getAdyacentes() {
        return adyacentes;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
