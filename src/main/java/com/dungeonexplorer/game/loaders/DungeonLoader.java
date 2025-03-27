package main.java.com.dungeonexplorer.game.loaders;
import static main.java.com.dungeonexplorer.game.engine.DungeonEngine.DIFICULTAD;

import main.java.com.dungeonexplorer.entities.Enemigo;
import main.java.com.dungeonexplorer.objects.IObjeto;
import main.java.com.dungeonexplorer.objects.PocionDeVida;
import main.java.com.dungeonexplorer.rooms.Habitacion;
import main.java.com.dungeonexplorer.rooms.HabitacionComun;
import main.java.com.dungeonexplorer.rooms.HabitacionEnemigo;
import main.java.com.dungeonexplorer.rooms.HabitacionObjeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import static main.java.com.dungeonexplorer.utils.StringUtils.listarObjetos;
import static main.java.com.dungeonexplorer.utils.StringUtils.parseAdyacentes;

public class DungeonLoader extends Loader<Map<Integer,Habitacion>> {

    public File ARCHIVO_MAPA = new File("src/main/resources/" + DIFICULTAD + "/mapa.txt");

    @Override
    public Map<Integer,Habitacion> load() throws FileNotFoundException {

        Map<Integer,Habitacion> listaHabitaciones = new HashMap<>();
        ArrayList<Enemigo>          enemigos    = new EnemyLoader().load();


        Scanner scanner = null;
        try {
            scanner = new Scanner(ARCHIVO_MAPA);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> habitaciones = new ArrayList<>();

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            habitaciones.add(scanner.nextLine());
        }

        scanner.close();

        for (String habitacion : habitaciones) {

            String[] atributos = habitacion.split("\\|");

            int id = Integer.parseInt(atributos[0].trim());
            String nombre = atributos[1].trim();
            String descripcion = atributos[2].trim();
            String tipo = atributos[3].trim();
            Map<String, Integer> adyacentes = parseAdyacentes(atributos[4]);
            List<IObjeto> objetos = new ArrayList<IObjeto>();

            // En caso de que sea una habitacion cn objetos
            if (!atributos[5].trim().equals("null")) {

                List<String> objetosListados = new ArrayList<>(listarObjetos(atributos[5].trim()));

                for (String objeto : objetosListados) {
                    String[] atributosObjeto = objeto.split(":");
                    int idObjeto = Integer.parseInt(atributosObjeto[0].trim());
                    int cantidad = Integer.parseInt(atributosObjeto[1].trim());

                    switch (idObjeto) {
                        case 1:
                            objetos.add(new PocionDeVida(cantidad));
                    }

                }

            }

//            System.out.println("id: " + id + " | nombre: " + nombre + " | descripcion:" + descripcion + " | tipo: " + tipo + " | adyacentes: " + adyacentes +  " | objetos: " + objetos);

            switch (tipo.toLowerCase()) {
                case "objeto":
                    listaHabitaciones.put(id,new HabitacionObjeto(id, nombre, descripcion, tipo, adyacentes, objetos));
                    break;
                case "comun":
                    listaHabitaciones.put(id,new HabitacionComun(id, nombre, descripcion, tipo, adyacentes));
                    break;
                case "enemigo":
                    for (Enemigo enemigo : enemigos) {
                       if(enemigo.getUbicacion() == id) {
                           listaHabitaciones.put(id, new HabitacionEnemigo(id, nombre, descripcion, tipo, adyacentes,enemigo));
                       }
                    }
                    break;
            }
        }

        return listaHabitaciones;
    }
}
