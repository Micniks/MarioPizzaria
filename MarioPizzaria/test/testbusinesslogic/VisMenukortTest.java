package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.FakeFacade;
import businesslogic.Pizza;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg*/

public class VisMenukortTest {

    @Test
    public void testVisMenukort() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"AnyKey"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.displayMenu();

        //assert
        assertTrue(ui.output.get(0).contains("Vesuvio"));
        assertTrue(ui.output.get(1).contains("Amerikaner"));
        assertTrue(ui.output.get(2).contains("Cacciatore"));

    }

}
