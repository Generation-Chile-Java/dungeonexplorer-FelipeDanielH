package main.java.com.dungeonexplorer.game.engine;

import main.java.com.dungeonexplorer.entities.Enemigo;
import main.java.com.dungeonexplorer.entities.Personaje;
import main.java.com.dungeonexplorer.game.loaders.DungeonLoader;
import main.java.com.dungeonexplorer.game.loaders.PlayerLoader;
import main.java.com.dungeonexplorer.objects.IObjeto;
import main.java.com.dungeonexplorer.rooms.Habitacion;
import main.java.com.dungeonexplorer.rooms.HabitacionEnemigo;
import main.java.com.dungeonexplorer.rooms.HabitacionObjeto;
import main.java.com.dungeonexplorer.ui.GameUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DungeonEngine {

    public static String DIFICULTAD = "TEST";

    // Variables importantes
    Personaje jugador = new PlayerLoader().load();
    Map<Integer, Habitacion> dungeon = new DungeonLoader().load();
    Habitacion habitacionAnterior;
    GameUI consola = new GameUI();
    int contadorHabitacion;

    public DungeonEngine() throws FileNotFoundException {
        dungeon.get(1).setVisitado(true);
        this.contadorHabitacion = 1;
    }

    // Metodos
    public Personaje getPersonaje() {
        return jugador;
    }

    public Map<Integer, Habitacion> getDungeon() {
        return this.dungeon;
    }

    public void moverPersonaje(Personaje personaje, Habitacion habitacion, int index) {

        List<String> caminos = new ArrayList<>(habitacion.getAdyacentes().keySet());
        this.habitacionAnterior = habitacion;
        int siguienteHabitacion = habitacion.getAdyacentes().get(caminos.get(index - 1));
        jugador.setUbicacion(siguienteHabitacion);

        if (!dungeon.get(siguienteHabitacion).isVisitado()) {
            this.contadorHabitacion++;
            if (!dungeon.get(siguienteHabitacion).getTipo().equals("enemigo")){
                consola.showSalaNuevaMessage(dungeon.size(), this.contadorHabitacion);
            }
            dungeon.get(siguienteHabitacion).setVisitado(true);
        }else{
             
        }

        switch (dungeon.get(siguienteHabitacion).getTipo()) {
            case "enemigo":
                HabitacionEnemigo habitacionEnemigo = (HabitacionEnemigo) dungeon.get(siguienteHabitacion);
                if (this.pelea(personaje, habitacionEnemigo.getEnemigo())) {
                    consola.showMessage("El Enemigo ha sido derrotado!");
                    this.dungeon.get(siguienteHabitacion).setTipo("Comun");
                    consola.showSalaNuevaMessage(dungeon.size(), this.contadorHabitacion);
                } else {
                    this.contadorHabitacion--;
                    dungeon.get(siguienteHabitacion).setVisitado(false);
                }
        }

        // CONDICIONAL PARA GANAR EL JUEGO
        if (this.contadorHabitacion == dungeon.size()) {
            consola.showMessageTerminarJuego();
            Set<Integer> opcionesFinalesValidas = Set.of(1, 2);
            int opcionFinal = 0;

            do {
                try {
                    opcionFinal = consola.getUserInput("Que haras?");
                    switch (opcionFinal) {
                        case 1:
                            terminarJuego("Has completado el juego. Felicidades");
                            break;
                        case 2:
                            break;
                        default:
                            consola.showMessage("Opcion no valida");

                             
                            break;
                    }
                } catch (Exception e) {
                    consola.showMessage("Debe introducir numeros");

                }
            } while (!opcionesFinalesValidas.contains(opcionFinal));
        }
    }

    public boolean pelea(Personaje personaje, Enemigo enemigo) {
         
        consola.showMessage("\nHa aparecido un enemigo: " + enemigo.getNombre());
        Set<Integer> opcionesBatallaValidas = Set.of(1, 2, 3, 4);
        int opcion = 0;

//      REPITE EL MENU DE BATALLA HASTA QUE MUERA EL ENEMIGO O SE ESCAPE EL JUGADOR
        do {
            consola.showMessage("Enemigo: " + enemigo.getNombre() + " " + enemigo.getVidaActual() + "/" + enemigo.getVidaMax());
            consola.showMessage(personaje.getNombre() + " " + personaje.getVidaActual() + "/" + personaje.getVidaMax());
            consola.menuPelea();
//            REPITE LAS OPCIONES HASTA QUE DE CON UNA OPCION VALIDA
            do {
                try {
                    opcion = consola.getUserInput("que haras?");
                    switch (opcion) {
                        case 1:
                             
                            consola.showMessage("El personaje: " + personaje.getNombre() + " Ha atacado a " + enemigo.getNombre() + "...has realizado " + personaje.getAtaque() + " de danio");
                            jugador.atacar(enemigo, jugador.getAtaque());

                            if(enemigo.getVidaActual() > 0){
                                consola.showMessage("El enemigo: " + enemigo.getNombre() + " Ha atacado a " + personaje.getNombre() + "...realizando " + enemigo.getAtaque() + " de danio");
                                enemigo.atacar(jugador, enemigo.getAtaque());
                            }
                            break;

                        case 2:
                            this.usarObjetoInventario();
                            break;
                        case 3:
                            consola.showPersonaje(personaje);
                            break;
                        case 4:
                            jugador.setUbicacion(habitacionAnterior.getId());
                            enemigo.setVidaActual(enemigo.getVidaMaxima());
                             
                            return false;
                        default:
                            consola.showMessage("Opcion no valida");
                             
                    }
                } catch (Exception e) {
                    consola.showMessage("Debe introducir numeros");

                }

            } while (!opcionesBatallaValidas.contains(opcion));

            if (jugador.getVidaActual() <= 0) {
                this.terminarJuego("Has sido derrotado. Vuelve a intentarlo");
            }

        } while (enemigo.getVidaActual() > 0 && jugador.getUbicacion() != habitacionAnterior.getId());

        return true;
    }

    public void recogerObjeto(Personaje personaje, HabitacionObjeto habitacion, int index) {
        IObjeto objeto = habitacion.getObjeto(index);
        personaje.aniadirObjeto(objeto);
        habitacion.quitarDeInventario(index);
    }

    public void usarObjeto(Personaje personaje, int index) {
        IObjeto objetoPersonaje = personaje.getInventario().get(index);

        switch (objetoPersonaje.getId()) {
            case 1:
                objetoPersonaje.usar(personaje);
                personaje.quitarObjeto(index);
                break;
        }
    }

    public void usarObjetoInventario() {

        int opcionInventario = 0;
        boolean acceso = false;
//      SE REPITE EN CASO DE ELEGIR UNA OPCION DE INVENTARIO NO VALIDA
        do {
//          TRY CATCH PARA VERIFICAR QUE NO SE INTRODUZCA UNA LETRA
            try {

                do {//PARA QUE NO SE SALGA DEL MENU HASTA OPRIMIR 0
                    consola.showInventario(this.getPersonaje().getInventario());
                    opcionInventario = consola.getUserInput("Elige una opcion: ");
                    if (opcionInventario == 0) {
                        acceso = true;
                    } else if (opcionInventario < 0 || opcionInventario > this.getPersonaje().getInventario().size()) {
                        System.out.println("opcion no valida");
                    } else {
                        acceso = true;
                        this.usarObjeto(this.getPersonaje(), opcionInventario - 1);
                    }
                }while (opcionInventario != 0);
            } catch (Exception e) {
                consola.showMessage("Se debe ingresar un numero");

            }
        } while (!acceso);
         
    }

    public void esperar(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            // Si ocurre una interrupción, simplemente se puede ignorar o manejar de otra forma
            Thread.currentThread().interrupt();  // Restablecer el estado de interrupción
        }
    }

    public void terminarJuego(String mensaje) {
        consola.showMessage(mensaje);
        System.exit(0);
    }
}
