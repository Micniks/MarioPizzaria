package main;

import businesslogic.Controller;
import businesslogic.DBFacade;
import businesslogic.Facade;
import businesslogic.FileFacade;
import businesslogic.Menu;
import java.sql.SQLException;
import presentation.SystemUI;
import presentation.UI;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("New project");

        Menu menu = new Menu();
        UI ui = new SystemUI();
        Facade facade;
        int facadeType = ui.askFacade();
        if (facadeType == 1) {
            facade = new DBFacade(ui.getPassword());
        } else {
            facade = new FileFacade();
        }

        Controller ctrl = new Controller(ui, menu.getMenu(), facade);
        ctrl.start();

    }

}
