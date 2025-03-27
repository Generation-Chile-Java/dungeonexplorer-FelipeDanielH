package main.java.com.dungeonexplorer.ui;

public class MainMenu {

    private GameUI  gameUI;

    public MainMenu(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void display(){
        gameUI.showMessage("\n ---Menu Principal---"
        + "\n 1. Nueva Partida"
        + "\n 2. Continuar"
        + "\n 0. Salir");
        int eleccion = gameUI.getUserInput("Choose an option: ");

        switch (eleccion) {
            case 1:
                gameUI.showMessage("Comenzando un juego nuevo");
                break;
            case 2:
                gameUI.showMessage("Cargando juego");
                break;
            case 0:
                gameUI.showMessage("Saliendo");
                System.exit(0);
                break;
            default:
                gameUI.showMessage("Opcion incorrecta, intentelo denuevo");
                display();
                break;
        }

    }
}
