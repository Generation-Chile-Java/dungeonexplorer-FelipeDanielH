package main.java.com.dungeonexplorer.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {

    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
//          Elimina la cabecera
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine())
                        .append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
