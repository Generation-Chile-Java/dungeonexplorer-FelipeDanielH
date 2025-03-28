package main.java.com.dungeonexplorer.game;


import main.java.com.dungeonexplorer.game.engine.DungeonEngine;

import main.java.com.dungeonexplorer.game.engine.GameInit;
import main.java.com.dungeonexplorer.rooms.Habitacion;
import main.java.com.dungeonexplorer.rooms.HabitacionObjeto;
import main.java.com.dungeonexplorer.ui.GameUI;

import java.io.FileNotFoundException;
import java.util.Set;

import static main.java.com.dungeonexplorer.game.engine.DungeonEngine.DIFICULTAD;

public class DungeonExplorer {

    public static boolean CONTINUAR = true;


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        GameUI consola = new GameUI();
        int opcionDificultad = 0;
        Set<Integer> opcionesDificultadValidas = Set.of(1, 2, 3, 0, 1995);

        do {
            try{
                consola.difficultyMenu();
                opcionDificultad = consola.getUserInput("Cual sera tu eleccion:");
                switch (opcionDificultad) {
                    case 1:
                        DIFICULTAD = "FACIL";
                        break;
                    case 2:
                        DIFICULTAD = "MEDIO";
                        break;
                    case 3:
                        DIFICULTAD = "DIFICIL";
                        break;
                    case 1995:
                        DIFICULTAD = "TEST";
                        break;
                    case 0:
                        consola.showMessage("Cobarde");
                        System.exit(0);
                    default:
                        consola.showMessage("Opcion no valida");
                        break;
                }
            }catch (NumberFormatException e){
                consola.showMessage("debes introducir un numero");
            }
        }while(!opcionesDificultadValidas.contains(opcionDificultad));


        String nombre = consola.getUserStringInput("Elige el nombre de tu personaje: ");

        while (CONTINUAR) {
            GameInit.init(nombre);
        }
    }
}
