package main;

import businesslogic.Controller;
import businesslogic.Menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import presentation.SystemUI;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("New project");

        Menu menu = new Menu();
        UI ui = new SystemUI();
        Controller ctrl = new Controller(ui, menu.getMenu());
        ctrl.start();

        /*     Til DatabaseFacade
        String user = "root";
        String password = "Gunstar1";
        String IP = "localhost";
        String PORT = "3306";
        String DATABASE = "mario";
        String serverTime = "serverTimezone=UTC";
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE+"?"+serverTime;
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM Pizza");
        while (result.next()) {
            int resultat = result.getInt("pizzaNo");
            System.out.println(resultat);
         */
    }

}
