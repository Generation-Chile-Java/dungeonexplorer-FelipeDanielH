package main.java.com.dungeonexplorer.objects;

public interface IObjeto<Entidad> {

    public int getId();
    public int getCantidad();
    public void setCantidad(int cantidad);
    public String getNombre();
    public void usar(Entidad entidad);
}


