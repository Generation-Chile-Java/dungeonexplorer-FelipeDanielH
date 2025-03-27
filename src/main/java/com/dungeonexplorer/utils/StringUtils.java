package main.java.com.dungeonexplorer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtils {

    // Parse a string of key-value pairs into a map
    public static Map<String, Integer> parseAdyacentes(String adyacentesStr) {
        Map<String, Integer> adyacentes = new HashMap<>();
        if (adyacentesStr.isEmpty()) return adyacentes;

        String[] pairs = adyacentesStr.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String direccion = keyValue[0].trim().toLowerCase();
                int habitacionId = Integer.parseInt(keyValue[1].trim());
                adyacentes.put(direccion, habitacionId);
            }
        }
        return adyacentes;
    }

    public static List<String> listarObjetos(String lista){

        String[] elementos = lista.split(",");
        List<String> listaPares = new ArrayList<>();

        for (String elemento : elementos) {
            elemento = elemento.trim();  // Eliminar espacios extra

            listaPares.add(elemento); // Agregar a la lista
        }

        return listaPares;
    }
}