package main.java.com.dungeonexplorer.entities;

import main.java.com.dungeonexplorer.objects.IObjeto;
import main.java.com.dungeonexplorer.rooms.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class Personaje extends Entidad {

    private int ubicacion;
    List<IObjeto> inventario;

    public Personaje(int id, String nombre, int vidaMax, int ataque, int ubicacion) {
        super(id,nombre,vidaMax, ataque);
        this.ubicacion = ubicacion;
        this.inventario = new ArrayList<>();
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<IObjeto> getInventario() {
        return inventario;
    }

    public void aniadirObjeto(IObjeto objeto) {
        System.out.println("objeto recogido: " + objeto.getNombre());
        for(IObjeto objetoInventario : inventario){
            if(objetoInventario.getId() == objeto.getId()){
                objetoInventario.setCantidad(objetoInventario.getCantidad() + objeto.getCantidad());
                return;
            }
        }
        inventario.add(objeto);
    }

    public void quitarObjeto(int index){
        IObjeto objeto = inventario.get(index);
        objeto.setCantidad(objeto.getCantidad() - 1);
        if(this.inventario.get(index).getCantidad() <= 0){
            this.inventario.remove(index);
        }
    }

}