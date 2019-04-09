package testbusinesslogic;

import businesslogic.Pizza;
import presentation.FakeUI;
import businesslogic.Controller;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.UI;

/**
 * @author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class OpretBestillingTest {

    @Test
    public void opretBestillingEnPizza() throws SQLException {
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "2", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.newOrder();

        //assert
        assertTrue(ui.output.get(0).contains("Skriv hvilket pizzanummer kunden har bestilt"));
        assertTrue(ui.output.get(1).contains("Skriv hvor mange af pizzaen kunde har bestilt"));
        assertTrue(ui.output.get(2).contains("Har kunden bestilt flere pizzaer?"));
        assertTrue(ui.output.get(10).contains("Ordrernummeret er"));
        assertTrue(ui.output.get(12).contains("Amerikaner"));
        assertTrue(ctrl.getActiveOrders().size() == 1);
        assertTrue(ctrl.getActiveOrders().get(0).getPizzaList().size() == 1);
        assertEquals("Amerikaner", ctrl.getActiveOrders().get(0).getPizzaList().get(0).getPizzaName());
    }

    @Test
    public void opretBestillingToAfSammePizza() throws SQLException {
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "2", "2", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.newOrder();

        //assert
        assertTrue(ui.output.get(0).contains("Skriv hvilket pizzanummer kunden har bestilt"));
        assertTrue(ui.output.get(1).contains("Skriv hvor mange af pizzaen kunde har bestilt"));
        assertTrue(ui.output.get(2).contains("Har kunden bestilt flere pizzaer?"));
        assertTrue(ui.output.get(10).contains("Ordrernummeret er"));
        assertTrue(ui.output.get(12).contains("Amerikaner"));
        assertTrue(ctrl.getActiveOrders().size() == 1);
        assertTrue(ctrl.getActiveOrders().get(0).getPizzaList().size() == 2);
        assertEquals("Amerikaner", ctrl.getActiveOrders().get(0).getPizzaList().get(0).getPizzaName());
        assertEquals("Amerikaner", ctrl.getActiveOrders().get(0).getPizzaList().get(1).getPizzaName());
    }

    @Test
    public void opretBestillingToForskelligePizza() throws SQLException {
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "1", "1", "1", "2", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.newOrder();

        //assert
        assertTrue(ui.output.get(0).contains("Skriv hvilket pizzanummer kunden har bestilt"));
        assertTrue(ui.output.get(1).contains("Skriv hvor mange af pizzaen kunde har bestilt"));
        assertTrue(ui.output.get(2).contains("Har kunden bestilt flere pizzaer?"));
        assertTrue(ui.output.get(5).contains("Skriv hvilket pizzanummer kunden har bestilt"));
        assertTrue(ui.output.get(6).contains("Skriv hvor mange af pizzaen kunde har bestilt"));
        assertTrue(ui.output.get(7).contains("Har kunden bestilt flere pizzaer?"));
        assertTrue(ui.output.get(15).contains("Ordrernummeret er"));
        assertTrue(ui.output.get(17).contains("Amerikaner"));
        assertTrue(ctrl.getActiveOrders().size() == 1);
        assertTrue(ctrl.getActiveOrders().get(0).getPizzaList().size() == 2);
        assertEquals("Amerikaner", ctrl.getActiveOrders().get(0).getPizzaList().get(0).getPizzaName());
        assertEquals("Vesuvio", ctrl.getActiveOrders().get(0).getPizzaList().get(1).getPizzaName());
    }

    @Test
    public void opretToBestillingerAfEnPizza() throws SQLException {
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "2", "Anykey", "1", "1", "2", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.newOrder();
        ctrl.newOrder();

        //assert
        assertTrue(ui.output.get(0).contains("Skriv hvilket pizzanummer kunden har bestilt"));
        assertTrue(ui.output.get(1).contains("Skriv hvor mange af pizzaen kunde har bestilt"));
        assertTrue(ui.output.get(2).contains("Har kunden bestilt flere pizzaer?"));
        assertTrue(ui.output.get(10).contains("Ordrernummeret er"));
        assertTrue(ui.output.get(12).contains("Amerikaner"));
        assertTrue(ui.output.get(13).contains("Press any key to return to the main menu"));
        assertTrue(ui.output.get(26).contains("Vesuvio"));
        assertTrue(ctrl.getActiveOrders().size() == 2);
        assertTrue(ctrl.getActiveOrders().get(0).getPizzaList().size() == 1);
        assertTrue(ctrl.getActiveOrders().get(1).getPizzaList().size() == 1);
        assertEquals("Amerikaner", ctrl.getActiveOrders().get(0).getPizzaList().get(0).getPizzaName());
        assertEquals("Vesuvio", ctrl.getActiveOrders().get(1).getPizzaList().get(0).getPizzaName());
    }

}
