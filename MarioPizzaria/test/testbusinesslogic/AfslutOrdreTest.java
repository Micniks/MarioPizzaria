package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.Pizza;
import businesslogic.FakeFacade;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/* @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurblad*/

public class AfslutOrdreTest {

    @Test
    public void testÁfslutOrdre() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "2", "AnyKey", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.newOrder();
        assertTrue(ctrl.getActiveOrders().size() == 1);
        ctrl.finishOrder();

        //assert
        assertTrue(ctrl.getActiveOrders().isEmpty());
    }
}
