package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.Pizza;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class AfslutOrdreTest {
    
    @Test
    public void testÁfslutOrdre() {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"3", "2", "1", "1"};
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.newOrder();
        assertTrue(ctrl.getActiveOrders().size() == 3);
        ctrl.finishOrder();

        //assume
        assertTrue(ctrl.getActiveOrders().isEmpty());
    }
}
