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

public class SeHistorikTest {

    @Test
    public void testSeHistorik() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"1", "1", "2", "AnyKey", "1", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.newOrder();
        ctrl.finishOrder();
        ctrl.displayHistory();

         //assert
        assertTrue(ctrl.getActiveOrders().isEmpty());
        assertTrue(ui.output.get(20).contains("Historik"));
        assertTrue(ui.output.get(21).contains("OrdreNummer: 1"));
        assertTrue(ui.output.get(21).contains("Vesuvio"));

    }

}
