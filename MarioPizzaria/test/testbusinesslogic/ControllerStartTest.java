package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.DBFacade;
import businesslogic.FakeFacade;
import businesslogic.Pizza;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 * @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class ControllerStartTest {

    @Test
    public void testStart() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"1", "AnyKey", "8"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.start();

        //assume
        assertTrue(ui.output.get(5).contains("Vælg en af følgende muligheder"));
        assertTrue(ui.output.get(6).contains("1. Vis menu"));
        assertTrue(ui.output.get(7).contains("2. Opret bestilling"));
        assertTrue(ui.output.get(8).contains("3. Se aktive ordrer"));
        assertTrue(ui.output.get(9).contains("4. Afslut bestilling"));
        assertTrue(ui.output.get(10).contains("5. Annullere en bestilling"));
        assertTrue(ui.output.get(11).contains("6. Se Historik"));
        assertTrue(ui.output.get(12).contains("7. Vis statistik"));
        assertTrue(ui.output.get(13).contains("8. Afslut program"));
        assertTrue(ui.output.get(14).contains("Vesuvio"));
        assertTrue(ui.output.get(15).contains("Amerikaner"));
        assertTrue(ui.output.get(16).contains("Cacciatore"));
        assertTrue(ui.output.get(17).contains("Press any key to return to the main menu"));
        assertTrue(ui.output.get(23).contains("Vælg en af følgende muligheder"));
        assertTrue(ctrl.getActiveOrders().isEmpty());
    }

}
