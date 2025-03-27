package main.java.com.dungeonexplorer.game.loaders;

import main.java.com.dungeonexplorer.entities.Enemigo;
import main.java.com.dungeonexplorer.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static main.java.com.dungeonexplorer.game.engine.DungeonEngine.DIFICULTAD;

public class EnemyLoader extends Loader<ArrayList<Enemigo>> {
    public static final File ARCHIVO_ENEMIGO = new File("src/main/resources/"+ DIFICULTAD + "/enemigos.txt");

    @Override
    public ArrayList<Enemigo> load() {

        String[] enemigosArchivo;
        ArrayList<Enemigo> enemigosList = new ArrayList<Enemigo>();

        try {
            enemigosArchivo = FileUtils.readFile(ARCHIVO_ENEMIGO).split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (String enemigo : enemigosArchivo) {
            String[] atributos = enemigo.split("\\|");

            int id = Integer.parseInt(atributos[0].trim());
            String nombre = atributos[1].trim();
            int vida = Integer.parseInt(atributos[2].trim());
            int ataque = Integer.parseInt(atributos[3].trim());
            int ubicacion = Integer.parseInt(atributos[4].trim());

            enemigosList.add(new Enemigo(id, nombre, vida, ataque,ubicacion));
        }

        return enemigosList;
    }
}