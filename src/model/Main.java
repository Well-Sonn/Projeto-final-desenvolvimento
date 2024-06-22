package model;

import controller.AmbienteController;
import controller.ReservaController;
import controller.UsuarioController;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        AmbienteController ambienteController = new AmbienteController();
        ReservaController reservaController = new ReservaController();

        // Iniciar a aplicação através da MainView
        MainView mainView = new MainView(usuarioController, ambienteController, reservaController);
        mainView.mainMenu();
    }
}
