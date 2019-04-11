package testbusinesslogic;

import businesslogic.Controller;
import datasource.FakeFacade;
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

public class SeAktiveOrdrerTest {

    @Test
    public void testSeEnAktiveOrdrer() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "2", "AnyKey", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.newOrder();
        ctrl.displayActiveOrders();

        //assert
        assertEquals(1, ctrl.getActiveOrders().size());
        assertTrue(ui.output.get(10).contains("Ordrernummeret er: 1"));
        assertTrue(ui.output.get(14).contains("OrdreNummer: 1"));
        assertTrue(ui.output.get(14).contains("Amerikaner"));
    }

    @Test
    public void testSeEnStorAktiveOrdrer() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "1", "1", "3", "1", "3", "2", "2", "AnyKey", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.newOrder();
        ctrl.displayActiveOrders();

        //assert
        assertEquals(1, ctrl.getActiveOrders().size());
        assertTrue(ui.output.get(20).contains("Ordrernummeret er: 1"));
        assertTrue(ui.output.get(29).contains("OrdreNummer: 1"));
        assertTrue(ui.output.get(29).contains("Amerikaner"));
        assertTrue(ui.output.get(29).contains("Vesuvio"));
        assertTrue(ui.output.get(29).contains("Cacciatore"));
    }

    @Test
    public void testSeToAktiveOrdrer() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"2", "1", "2", "AnyKey", "1", "1", "2", "AnyKey", "AnyKey"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, menukort, db);

        //act
        ctrl.newOrder();
        ctrl.newOrder();
        ctrl.displayActiveOrders();

        //assert
        assertEquals(2, ctrl.getActiveOrders().size());
        assertTrue(ui.output.get(10).contains("Ordrernummeret er: 1"));
        assertTrue(ui.output.get(24).contains("Ordrernummeret er: 2"));
        assertTrue(ui.output.get(28).contains("OrdreNummer: 1"));
        assertTrue(ui.output.get(28).contains("Amerikaner"));
        assertTrue(ui.output.get(29).contains("OrdreNummer: 2"));
        assertTrue(ui.output.get(29).contains("Vesuvio"));

    }

}
