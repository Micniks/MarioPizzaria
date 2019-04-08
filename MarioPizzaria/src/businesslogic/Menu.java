package businesslogic;

import businesslogic.Pizza;
import java.util.ArrayList;

/**
 *@author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public class Menu {

    ArrayList<Pizza> menu = new ArrayList();

    public Menu() {

        menu.add(new Pizza(1, "Vesuvio", 57.0, "tomatsauce, ost, skinke og oregano"));
        menu.add(new Pizza(2, "Amerikaner", 53.0, "tomatsauce, ost, oksefars og oregano"));
        menu.add(new Pizza(3, "Cacciatore", 57.0, "tomatsauce, ost, pepperoni og oregano"));
        menu.add(new Pizza(4, "Carbona", 63.0, "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano"));
        menu.add(new Pizza(5, "Dennis", 65.0, "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano"));
        menu.add(new Pizza(6, "Bertil", 57.0, "tomatsauce, ost, bacon og oregano"));
        menu.add(new Pizza(7, "Silvia", 61.0, "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano"));
        menu.add(new Pizza(8, "Victoria", 61.0, "tomatsauce, ost, skinke, ananas, champignon, løg og oregano"));
        menu.add(new Pizza(9, "Toronfo", 61.0, "tomatsauce, ost, skinke, bacon, kebab, chili og oregano"));
        menu.add(new Pizza(10, "Capricciosa", 61.0, "tomatsauce, ost, skinke, champignon og oregano"));
        menu.add(new Pizza(11, "Hawai", 61.0, "tomatsauce, ost, skinke, ananas og oregano"));
        menu.add(new Pizza(12, "Le Blissola", 61.0, "tomatsauce, ost, skinke, rejer og oregano"));
        menu.add(new Pizza(13, "Venezia", 61.0, "tomatsauce, ost, skinke, bacon og oregano"));
        menu.add(new Pizza(14, "Mafia", 61.0, "tomatsauce, ost, pepperoni, bacon, løg og oregano"));
    }

    public ArrayList<Pizza> getMenu() {
        return menu;
    }

    
    
}
