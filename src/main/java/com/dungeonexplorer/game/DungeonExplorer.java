package main.java.com.dungeonexplorer.game;


import main.java.com.dungeonexplorer.game.engine.DungeonEngine;

import main.java.com.dungeonexplorer.rooms.Habitacion;
import main.java.com.dungeonexplorer.rooms.HabitacionObjeto;
import main.java.com.dungeonexplorer.ui.GameUI;

import java.io.FileNotFoundException;
import java.util.Set;

public class DungeonExplorer {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        GameUI consola = new GameUI();
        DungeonEngine game = new DungeonEngine();
        Set<Integer> opcionesAccionesValidas = Set.of();

        int accion = 999;
        // SE REPITE CADA VEZ QUE EL USUARIO NO ESCOGE UNA OPCION VALIDA DENTRO DEL MENU

        do {

            int ubicacionActualJugador = game.getPersonaje().getUbicacion();
            Habitacion habitacionActual = game.getDungeon().get(ubicacionActualJugador);

            if (habitacionActual.getTipo().equals("objeto")) {
                opcionesAccionesValidas = Set.of(1, 2, 3, 4);
            } else {
                opcionesAccionesValidas = Set.of(1, 2, 3);
            }

            consola.showMessage("\nEstas en: " + habitacionActual.getNombre());
            consola.showMessage(habitacionActual.getDescripcion());
            consola.showMenu(habitacionActual);

            try {
                accion = consola.getUserInput("Elige una opcion: ");
            } catch (NumberFormatException e) {
                consola.showMessage("Se debe ingresar un numero");

            }

            switch (accion) {
                case 1: // VER CAMINOS
                    consola.showCaminos(habitacionActual);
                    int coordenada = 0;

//                  SE REPITE EN CASO DE NO SER VALIDA LA OPCION DE ELEGIR A DONDE MOVERSE
                    do {
//                        TRY CATCH PARA VERIFICAR QUE NO SE INTRODUZCA UNA LETRA
                        try {
                            coordenada = consola.getUserInput("Elige una opcion: ");

                            if (coordenada < 0 || coordenada > habitacionActual.getAdyacentes().size()) {
                                consola.showMessage("opcion no valida");
                            }else if(coordenada == 0) {
                                break;
                            }else {
                                game.moverPersonaje(game.getPersonaje(), habitacionActual, coordenada);
                            }

                        } catch (Exception e) {
                            consola.showMessage("Se debe ingresar un numero");

                        }
                    } while (coordenada < 0 || coordenada > habitacionActual.getAdyacentes().size());

                    break;
                case 2:
                    game.usarObjetoInventario();
                    break;
                case 3:
                    consola.showPersonaje(game.getPersonaje());
                      
                    break;
                case 4: // RECOJER OBJETOS DE LA SALA
                    if (habitacionActual.getTipo().equals("objeto")) {
                        int opcionObjeto=0;
                        boolean accesoObjeto=false;
//                        CICLO DO WHILE PARA VERIFICAR QUE EL USUARIO NO INGRESE UNA OPCION QUE NO SEA DE LAS QUE SE LE PROPONEN
                        do {
//                            TRY CATCH PARA QUE EL USUARIO NO INGRESE LETRAS
                            try{
                                HabitacionObjeto habitacionAuxiliar = (HabitacionObjeto) habitacionActual;
                                consola.showObjetosHabitacion((HabitacionObjeto) habitacionActual);
                                opcionObjeto = consola.getUserInput("Elige una opcion: ");

                                if(opcionObjeto==0){
                                    accesoObjeto=true;
                                } else if (opcionObjeto < 0 || opcionObjeto > habitacionAuxiliar.getInventario().size()) {
                                    System.out.println("opcion no valida");

                                      
                                }else{
                                    game.recogerObjeto(game.getPersonaje(), (HabitacionObjeto) habitacionActual, opcionObjeto - 1);
                                }
                            }catch (Exception e) {
                                consola.showMessage("Se debe ingresar un numero");

                            }
                        } while (!accesoObjeto);
                          
                    } else {
                        System.out.println("opcion no valida \n");

                          
                    }
                    break;
                case 0: // SALIR DEL JUEGO
                    consola.showMessage("Adios");
                    break;
                default:
                    System.out.println("opcion no valida \n");

                      
                    break;
            }

        } while (opcionesAccionesValidas.contains(accion) || accion != 0);

    }
}
