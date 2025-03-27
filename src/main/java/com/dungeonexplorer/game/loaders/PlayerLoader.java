package main.java.com.dungeonexplorer.game.loaders;

import main.java.com.dungeonexplorer.entities.Personaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.java.com.dungeonexplorer.game.engine.DungeonEngine.DIFICULTAD;


public class PlayerLoader extends Loader<Personaje>{


    public static final File ARCHIVO_PERSONAJE = new File("src/main/resources/"+ DIFICULTAD + "/jugador.txt");

    @Override
    public Personaje load() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(ARCHIVO_PERSONAJE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> lineas = new ArrayList<>();

        scanner.nextLine(); // Omitir la cabecera

        while (scanner.hasNextLine()) {
            lineas.add(scanner.nextLine());
        }

        scanner.close();

        for (String line : lineas) {
            String[] parts = line.split("\\|");

            int id = Integer.parseInt(parts[0].trim());

            if(id == 1){
                String name = parts[1].trim();
                int health = Integer.parseInt(parts[2].trim());
                int attack = Integer.parseInt(parts[3].trim());
                int ubicacion = Integer.parseInt(parts[4].trim());

                return new Personaje(id,name,health,attack,ubicacion);
            }
        }
        return null;
    }
}
