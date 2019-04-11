
package presentation;

import businesslogic.Order;
import businesslogic.Pizza;
import java.util.ArrayList;

/**
 *@author Michael N. Korsgaard
 * @author Nicolai Gregersen
 * @author Jens Brønd
 * @author Oscar Laurberg
 */
public interface UI {
  
    public int selectPizza();
    //TODO: overvej at ændre visPizzaValg til at modtage en pizza
    public void showPizzaSelection(String str);
    public void displayOrderNumber(int ordreNummer);
    public void displayMenu(ArrayList<Pizza> menukort);
    public void displayMainMenu();
    //TODO: overvej at ændre hovedMenuValg til at sende en int
    public String mainMenuSelection();
    public void displayHistory(ArrayList<String> læsHistorik);
    public int selectOrder();
    public void pressAnyKey();
    public void displayOrders(Order bestilling);
    public int selectPizzaAmount();
    public Boolean selectMorePizza();
    public void showPizzaListSelection(ArrayList<Pizza> pizzaList);
    public void cancelOrderMsg(Order order);
    public String getPassword();
    public int askFacade();
    
}
