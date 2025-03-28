package main.java.com.dungeonexplorer.ui;

import main.java.com.dungeonexplorer.entities.Personaje;
import main.java.com.dungeonexplorer.objects.IObjeto;
import main.java.com.dungeonexplorer.rooms.Habitacion;
import main.java.com.dungeonexplorer.rooms.HabitacionObjeto;

import java.util.List;
import java.util.Scanner;

public class GameUI {
    private Scanner scanner;

    public GameUI() {
        this.scanner = new Scanner(System.in);
    }

    // Muestra un mensaje en la consola
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void limpieza(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    // Obtiene la entrada del usuario
    public int getUserInput(String prompt) {
        System.out.print(prompt + " ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getUserStringInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    // Muestra el menú de opciones
    public void showMenu(Habitacion habitacion) {

        System.out.println("\n--- Dungeon Explorer ---");
        System.out.println("1. Moverse");
        System.out.println("2. Revisar Inventario");
        System.out.println("3. Revisar Estadisticas");
        if (habitacion.getTipo().equals("objeto")) {
            System.out.println("4. Mirar Sala");
        }
        System.out.println("0. Salir");
        System.out.println("------------------------");
    }

    public void showPersonaje(Personaje personaje) {
           
        System.out.println("\n--- Estadisticas ---"
                + "\nNombre: " + personaje.getNombre()
                + "\nVida: " + personaje.getVidaActual() + "/" + personaje.getVidaMax()
                + "\nAtaque: " + personaje.getAtaque()
        );
        System.out.println("----------------------------");
        try{
            Scanner s = new Scanner(System.in);
            System.out.println("Pulsa Enter para continuar...");
            s.nextLine();
        }catch (Exception e){}

    }

    public void showCaminos(Habitacion habitacion) {
           
        int i = 1;
        System.out.println("\n|| Caminos ||");
        for( String camino : habitacion.getAdyacentes().keySet() ) {
            System.out.print(i + ":" + camino + " ");
            i++;
        }
        System.out.print("\n0:no hacer nada");
        System.out.println();
        System.out.println("----------------------------");
    }

    public void menuPelea(){
        System.out.println("\n--- Menu Batalla ---");
        System.out.println("1. Atacar");
        System.out.println("2. Revisar Inventario");
        System.out.println("3. Ver Estadisticas");
        System.out.println("4. Retroceder \n");
    }

    public void showInventario(List<IObjeto> inventario) {
           
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("\n--- Inventario ---");
        if(inventario.size() == 0){
            sb.append("\n(no tienes ningun objeto)");
        }
        for (IObjeto objeto : inventario) {
            sb.append("\n"+ i++ +". " + objeto.getNombre() + ": " + objeto.getCantidad());
        }
        sb.append("\n0. Salir\n");

        System.out.println(sb);

    }

    public void showObjetosHabitacion(HabitacionObjeto habitacion){
           
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("\n--- Objetos ---");
        for (IObjeto objeto : habitacion.getInventario()) {
            sb.append("\n"+ i++ + ". " + objeto.getNombre() + ": " + objeto.getCantidad());
        }

        sb.append("\n0. Salir\n");

        System.out.println(sb);
    }

    public void difficultyMenu(){
        System.out.println("\n--- Escoge el nivel de desafio (Cada dificultad es un escenario diferente) ---");
        System.out.println("1. FACIL");
        System.out.println("2. MEDIO");
        System.out.println("3. DIFICIL");
        System.out.println("0. Prefiero no Intentarlo \n");
    }

    public void showMessageContinuar(){
        System.out.println("¿Empezar denuevo?");
        System.out.println("\n1. Si");
        System.out.println("2. No");
    }

    public void showMessageTerminarJuego(){
           
        System.out.println("Has recorrido todas las salas. Desear terminar el juego o continuar explorando");
        System.out.println("\n1. Salir");
        System.out.println("2. Seguir Explorando");
    }

    public void showSalaNuevaMessage(int tamanio, int actual){
           
        int restantes = tamanio - actual;
        System.out.println("\n¡No habias visitado esta sala antes!");

        if(restantes == 1){
            System.out.println("Aun resta " + restantes + " sala");
        }else{
            System.out.println("Aun restan " + restantes + " salas");
        }

    }

    public void close() {
        scanner.close();
    }
}
