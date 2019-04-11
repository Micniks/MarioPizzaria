/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.DBFacade;
import businesslogic.Pizza;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
@author Michael N. Korsgaard
@author Nicolai Gregersen
@author Jens Brønd
@author Oscar Laurberg
*/
public class SeHistorikTest {

    @Test
    public void testSeHistorik() throws SQLException {
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menukort.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menukort.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        String[] input = {"1"};
        FakeUI ui = new FakeUI(input);
        DBFacade db = new DBFacade(ui.getPassword());
        Controller ctrl = new Controller(ui, menukort, db);
        
        //act
        ctrl.newOrder();
        ctrl.finishOrder();
        ctrl.displayHistory();
        
        //assume
        assertTrue(ctrl.getActiveOrders().isEmpty());
        assertTrue(ui.output.get(3).contains("Historik"));
        assertTrue(ui.output.get(4).contains("Vesuvio"));
        
    }

}
