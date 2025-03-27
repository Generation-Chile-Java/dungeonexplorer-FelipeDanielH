package main.java.com.dungeonexplorer.game.loaders;

import java.io.FileNotFoundException;

abstract class Loader<Entidad> {
    public abstract Entidad load() throws FileNotFoundException;
}
