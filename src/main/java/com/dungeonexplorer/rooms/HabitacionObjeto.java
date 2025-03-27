package main.java.com.dungeonexplorer.rooms;

import main.java.com.dungeonexplorer.objects.IObjeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HabitacionObjeto extends Habitacion{

    List<IObjeto> inventario;

    public HabitacionObjeto(int id, String nombre, String descripcion, String tipo, Map<String, Integer> adyacentes, List<IObjeto> inventario) {
        super(id, nombre, descripcion, tipo="objeto", adyacentes);
        this.inventario = inventario;
    }


    public List<IObjeto> getInventario() {
        return inventario;
    }

    public void setInventario(List<IObjeto> inventario) {
        this.inventario = inventario;
    }

    public void quitarDeInventario(int index){
        this.inventario.remove(index);
    }

    public IObjeto getObjeto(int index){
        return this.inventario.get(index);
    }

}
